
/*----------------------------------------------------------------------------------*
 * The class WorkerThread represent the objects in the Chain of Responsibility that
 * are in charge of handling a request passed to the daemon thread and received by
 * each worker thread through the buffer.
 *----------------------------------------------------------------------------------*/

import java.util.Random;

public class WorkerThread implements Handler{

    private int workerId;
    private boolean isLookingForWork;
    private boolean isWorking;
    public WorkerThread successor = null;

    //Random seed for the simulation of the request handling process.
    private Random r = new Random(System.currentTimeMillis());


    public WorkerThread(int someId){
        isLookingForWork = true;
        isWorking = false;
        workerId = someId + 1;
        System.out.println("Worker thread #" + workerId + " successfully created.");
    }//end WorkerThread constructor.

    public void handleRequest(){
        while(isLookingForWork) {
            try {
                isLookingForWork = false;
                isWorking = true;
                System.out.println("Thread #" + workerId + " is working.");
                Thread.sleep(r.nextInt(1000));
                System.out.println("Thread #" + workerId + " finished working.");
                isLookingForWork = true;
                break;
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
        //If the current thread is working, then ask the successor to handle the request.
        if(isWorking){
            System.out.println("Thread #" + workerId + " is busy.");
            if(successor != null){
                //Chain of Responsibility behavior.
                System.out.println("Passing request to Thread #" + successor.workerId);
                if(successor.isLookingForWork){
                    successor.handleRequest();
                }
            }
            //If no thread was available, wait until one is free.
            else{
                try {
                    Thread.sleep(5000);
                }
                catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        }
    }//End handleRequest method.

    //Method run for classes that implement the Runnable interface.
    @Override
    public void run(){
        handleRequest();
    }//end run method.
}

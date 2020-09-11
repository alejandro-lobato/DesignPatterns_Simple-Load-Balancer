import java.util.Vector;
import java.util.Random;

/*----------------------------------------------------------------------------------*
 * The class Buffer acts as a Proxy between the request made to the Daemon process
 * and the actual worker threads in the pool that handle the request. Buffer is a
 * Singleton object as well because only one pool of threads should available.
 *----------------------------------------------------------------------------------*/


public class Buffer implements Proxy{

    public static Buffer uniqueInstance = new Buffer();
    private Vector<WorkerThread> threadPool;

    private Buffer(){
        //Construct the thread pool.
        threadPool = new Vector<>();

        for(int i = 0; i < 5; i++){
            threadPool.add(new WorkerThread(i));
        }//end for.

        //Assigning successors for each thread.
        for(int i = 1; i < 5; i++){
            threadPool.get(i - 1).successor = threadPool.get(i);
        }//end for.

    }//End buffer constructor.

    //Ensures only 1 instance of the thread pool buffer exists.
    public static Buffer instance(){
        return uniqueInstance;
    }//end instance method.

    //Randomly select a thread from the pool to handle the request.
    public void request(){
        Random r = new Random();
        int index = r.nextInt(4);
        WorkerThread aThread = threadPool.get(index);
        new Thread(aThread).start();


    }//end request method.
}

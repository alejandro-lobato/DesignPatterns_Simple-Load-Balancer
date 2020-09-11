
/*-----------------------------------------------------------------*
 * Daemon is a class that uses the Singleton design pattern.
 * In order to implement our process management for multiple calls,
 * we can only have 1 Daemon listening for calls on a method.
 *------------------------------------------------------------------*/


import java.util.Date;

public class Daemon implements Runnable
{
    private static Daemon uniqueInstance = new Daemon();
    private Date creationStamp;


    private Daemon(){
        creationStamp = new Date();
    } //end Daemon constructor.

    public static Daemon instance(){
        return uniqueInstance;
    } //end instance method.

    @Override
    public synchronized void run(){

        System.out.println("Starting service.");
        System.out.println("Daemon is waiting for calls...");
        System.out.println(creationStamp);
        Buffer buffer = Buffer.instance();

        //Creating several requests to test the behavior of the threads.
        System.out.println("Request 1");
        buffer.request();
        System.out.println("Request 2");
        buffer.request();
        System.out.println("Request 3");
        buffer.request();
        System.out.println("Request 4");
        buffer.request();
        System.out.println("Request 5");
        buffer.request();
        System.out.println("Request 6");
        buffer.request();

        //The  Buffer acts as a Proxy for the daemon to handle the request.


    } //end run method.

}

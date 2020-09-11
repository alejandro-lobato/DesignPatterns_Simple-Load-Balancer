
/*----------------------------------------------------------------------------------*
 * @Author: Alejandro Lobato Molina
 * Teacher: PhD. Gerardo Ayala San Martin
 * Course: Software Design Patterns
 * Final Project - Use of at least 3 design patterns.
 *  DESIGN PATTERNS USED: Singleton, Proxy and Chain of Responsibility.
 *
 *  Notes: Refer to the README.txt document for further details on the project.
 *----------------------------------------------------------------------------------*/

public class Client
{
    public static void main(String [] argonauts)
    {
        Daemon daemon = Daemon.instance();
        new Thread(daemon).start();
    }
}

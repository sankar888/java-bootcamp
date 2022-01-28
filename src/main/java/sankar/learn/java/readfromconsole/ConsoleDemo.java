package sankar.learn.java.readfromconsole;

import java.io.Console;

/**
 * There are multiple ways to read input interactively from user.
 * 1. Console class
 * 2. Reading from System.in
 * 3. Using Scanner Class
 */
public class ConsoleDemo {
    /**
     * {@link java.io.Console Console} class has methods to read and write data from JVM Console if it has one
     * If the program is executed in background and doesn't have a console <code>System.console()</code> returns <code>null</code>
     *
     * To Run the program: from root src folder:
     * javac sankar/learn/java/readfromconsole/ConsoleDemo.java
     * java sankar/learn/java/readfromconsole/ConsoleDemo
     */
    public static void main(String[] args) {
        Console con = System.console();
        if (con != null) {
            con.printf("Program %s says Write Something. type 'quit' to exit\n", ConsoleDemo.class.getName());
            String input = con.readLine();
            while(!input.trim().equalsIgnoreCase("end")) {
                con.printf("bot says: %s \n", input);
                con.flush();
                input = con.readLine();
            }
            con.printf("bot says: Bye! Bye!");
            con.flush();
        } else {
            System.err.println("The program doesn't have a Console!");
        }
    }
}

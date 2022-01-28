package sankar.learn.java.readfromconsole;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.function.Predicate;

/**
 * There are multiple ways to read input interactively from user.
 * 1. Console class
 * 2. Reading from System.in
 * 3. Using Scanner Class
 *
 * This class demonstrate reading using Reader and Scanner
 */
public class ReaderDemo {

    private static final String EXIT_COMMAND = "quit";
    private static Predicate<String> isExitCommand = command -> command.trim().equalsIgnoreCase(EXIT_COMMAND);

    public static void main(String[] args) {
        //readUsingBufferedBuffer();
        readUsingReader();
        //only one method can be executed at a time, cause the System.in is closed
        //Refer: https://stackoverflow.com/questions/27286690/in-java-is-it-possible-to-re-open-system-in-after-closing-it
    }

    private static void readUsingReader() {
        System.out.printf("Reading using char[] : Type something and hit enter. enter %s to exit\n", EXIT_COMMAND);
        try (Reader reader =  new InputStreamReader(System.in)) {
            char[] buffer = new char[100];
            int charsRead = 0;
            String input = "";
            while(!isExitCommand.test(input) && (charsRead = reader.read(buffer)) != -1) {
                input = String.valueOf(buffer, 0, charsRead).trim();
                System.out.printf("bot:-> %s\n\n", input);
            }
            System.out.println("bot:-> Bye! Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readUsingBufferedBuffer() {
        System.out.printf("Reading using BufferedReader : Type something and hit enter. enter %s to exit\n", EXIT_COMMAND);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input = "";
            while(!isExitCommand.test(input) && (input = reader.readLine()) != null) {
                System.out.printf("bot:-> %s\n\n", input);
            }
            System.out.println("bot:-> Bye! Bye!");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

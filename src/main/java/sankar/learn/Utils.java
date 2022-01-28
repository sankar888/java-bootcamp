package sankar.learn;

import com.google.common.base.Preconditions;

import java.util.Arrays;

public class Utils {

    public static final float bytesToMB(long bytes) {
        return bytes / (1024 * 1024);
    }

    public static final void heading(String msg) {
        Preconditions.checkArgument(msg != null && !msg.trim().isEmpty(), "Header message should not be null or empty");
        char[] underline = new char[msg.length()+3];
        Arrays.fill(underline, '-');
        System.out.printf("# %s:\n", msg);
        System.out.println(underline);
    }

    public static final void end() {
        System.out.println("\n");
    }

    public static final void newLine(){
        System.out.println("");
    }
}

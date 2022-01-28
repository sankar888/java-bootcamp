package sankar.learn.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
    public static void main(String[] args) {
        //continuous loop, read regex, read charsequence, print matching information
        while (true) {

        }
    }

    private static void printMatchInfo(String regex, String charSequence) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(charSequence);
        boolean matches = matcher.matches();
        if (matches) {
            System.out.printf("The regex %s matches the string %s , groupCount: %s", regex, charSequence, matcher.groupCount());
        } else {
            System.out.println("No Match");
        }
    }
}

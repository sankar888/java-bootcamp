package sankar.learn.java.util.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 * {@link java.util.regex.Pattern Pattern} class represents the regex pattern
 */
public class RegexDemo {

    @Test
    public void simpleUsage() {
        String charSeq = "abbbbc";
        Pattern pattern = Pattern.compile("a.*c");
        Matcher matcher = pattern.matcher(charSeq);
        System.out.printf("Pattern %s matches string %s : %s",pattern.pattern(), charSeq, matcher.matches());
    }

    @Test
    public void createARegexPattern() {
        String regex = "a.*c";
        Pattern pattern = Pattern.compile(regex); //Pattern doesn't have a constructor, Pattern instances are immutable and so threadsafe
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE); //Regex pattern flags, which specify how to match the character sequence
        boolean matches = Pattern.matches(regex, "abbbbc"); //A shorthand for one time use
    }


}

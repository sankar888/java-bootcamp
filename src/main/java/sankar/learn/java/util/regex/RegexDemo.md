### Regular Expression in Java
Regular expressions are search patterns which can be used to search for a match in character sequence.

The java.util.regex package primarily consists of three classes: Pattern, Matcher, and PatternSyntaxException.
 - A **Pattern** object is a compiled representation of a regular expression. The Pattern class provides no public constructors. 
   To create a pattern, you must first invoke one of its public static compile methods, which will then return a Pattern object. These methods accept a regular expression as the first argument
   Pattern objects are immutable and thus threadsafe
   
 - A **Matcher** object is the engine that interprets the pattern and performs match operations against an input character sequence. 
   Like the Pattern class, Matcher defines no public constructors. You obtain a Matcher object by invoking the matcher method on a Pattern object.
   
 - A **PatternSyntaxException** object is an unchecked exception that indicates a syntax error in a regular expression pattern

#### Simple Usage
```java
class RegexDemo {
    public static void main(String[]args) {
        String charSeq = "abbbbc";
        Pattern pattern = Pattern.compile("a.*c");
        Matcher matcher = pattern.matcher(charSeq);
        System.out.printf("Pattern %s matches string %s : %s",pattern.pattern(), charSeq, matcher.matches());
    }
}
```



### Resources
[Regex tutorial from Oracle](https://docs.oracle.com/javase/tutorial/essential/regex/index.html)
[Regex character classes](https://docs.oracle.com/javase/tutorial/essential/regex/char_classes.html)
[Pattern Character Reference](https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html)

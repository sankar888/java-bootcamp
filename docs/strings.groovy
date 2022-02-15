//This is a comment
/*
Multi line
Comment
*/
/**
 * This is a groovy document
 * It could spin up multiline like java-doc
 */
/**
 * String in groovy
 * Groovy supports two types of strings—regular Java strings that are instances of java.lang.String and GStrings, which are instances of groovy.lang.GString
 * Regular Groovy strings are declared by surrounding sequences of characters with single, double, or triple quotes.
 */
String name = "Sankar" //Double quoted string
String name1 = 'sankar' //single quoted string
String multiLineStr = '''
This is a multiline
  string which preserves
  whitespace
''' //multiline triple quoted string
println(multiLineStr)

/**
 * Groovy allows method calls, statements, and variable names inside the ${...} Groovy expressions.
 * Groovy strings can be used as templates
 */
user = "User!"
String greetings = "Hello ${user}"
String greetings1 = "Hello $user" //if user is a simple variable the braces {} can be omitted
println(greetings)
println(greetings1)

greetings3 = "Hello ${user.toUpperCase()} ${user.length()}" 
println(greetings3)

greetings4 = 'Hello $user' //variable interpolation strings should de double quoted
println(greetings4) //prints Hello $user

greetings5 = '''
 Hello $user
 Welcome!
'''
println(greetings5) //prints  Hello $user Welcome!, only double quoted strings are interpolated
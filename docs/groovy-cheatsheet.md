groovy -v
groovyConsole.bat - to open groovy gui based editor
groovysh.bat - to open groovy shell

You can exit the Groovy shell by running the command :exit or :quit. You can also run the :help command to list available shell commands or get details on an individual command.

Groovy absorbs much of its syntax from Java and has added simplifications that make it an easy to use. One such simplification is dropping the semicolon requirement at the end of a line when the line contains only one statement.
* Methods and classes in Groovy are public by default.
* The return statement is optional in methods. If Groovy doesn’t find a return statement, it returns the last evaluated expression.
* Checked exceptions need not be caught or declared. Groovy automatically wraps those exceptions as a RuntimeException.
* By default, the following packages are imported—java.lang.*, java.util.*, java.util.regex.*, java.net.*, java.io.*, groovy.lang.*, groovy.util.*, java.math.BigDecimal, and java.math.BigInteger.

```groovy
//This is a single line comment
/*
 Multi line
 Comment
*/
/**
 This is a groovy document
 It could spin up multiline like java-doc
 */
void printHello() {
  println("Hello")
}
printHello()
```

Groovy preserves the whitespace in a multi-line text declared using triple quotes
Like Java, Strings in Groovy are immutable.

Numbers

Groovy supports both integers and floating-point numbers. Unlike Java, Groovy doesn’t offer primitive data types. Instead everything in Groovy is an object. This allows you to invoke methods on what looks like primitives as shown here:

8.toString()
4.times {
// Run a task
}
7.next()

By default, integers in Groovy are instances of java.lang.Integer, java.lang.Long, or java.math.BigInteger. Groovy will automatically pick the smallest class to accommodate the integer’s value.

[groovy strings](strings.groovy)
[groovy numbers](numbers.groovy)
[datastructures in groovy](datastructures.groovy)
[closures in groovy](closures.groovy)


### Resources
* [Groovy Language Documentation](http://www.groovy-lang.org/single-page-documentation.html)
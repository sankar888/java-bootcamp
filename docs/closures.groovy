/**
Closure:
Closure is a block of executable code.
Closures in Groovy are instances of groovy.lang.Closure.
Since closures are objects, they can be assigned to variables or passed around as parameters to methods. Since they contain code, closures can be executed.
Closures are often referred to as anonymous functions.

syntax:
{ list of arguments -> closure body }
Closures are enclosed by curly braces {}. 
The list of arguments are separated by commas (,) and the closure body contains one or more statements. 
The symbol -> separates the argument list from the body. If the closure doesn’t have a return statement, the output of the last statement is returned

Scope:
Closures may access the variables declared within their scope. 
For example, if a closure is defined inside a method, 
it can access all the variables that the method has access to (parameters, local variables, class variables, etc.).
*/
addTwoNumbers = {a,b -> a+b} //returns last statement
sum = addTwoNumbers.call(1,2)
println(sum) //3

addTwoIntegers = {Integer a, Integer b -> {
 sum = a + b
 return sum //multi line closure
}}
println(addTwoIntegers.call(1,2)) //prints 3
println(addTwoIntegers(3,4)) //prints 7

def func0(){
 println("function func()")
}
func0()

void func1() {
  println("function void func()")
}
func1()

class Person {
    String name;
    Person(String name) {
        this.name  = name;
    }
}

p = new Person("sankar")
println(p.class) //class Person
println(p.name) //sankar

increment = {e -> print(e+1)}
range = 1..10
range.each(increment) //closure can be passed as arguments //prints 234567891011


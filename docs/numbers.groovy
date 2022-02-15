/**
Numbers in groovy
Groovy supports both integers and floating-point numbers. Unlike Java, Groovy doesn’t offer primitive data types. 
Instead everything in Groovy is an object.
*/
Integer num0 = 1
println(num0.plus(10)) //prints 11
println(num0 + 3) //prints 4
println("2 + 2 is ${2.plus(2)}") //Everything is object in groovy, so methods can be invoked on numbers

/**
By default, integers in Groovy are instances of java.lang.Integer, java.lang.Long, or java.math.BigInteger. 
Groovy will automatically pick the smallest class to accommodate the integer’s value.
Floating-point numbers by default are instances of java.math.BigDecimal
*/
num0 = 12
println(num0.class) //prints class java.lang.Integer

num1 = 123.78
println(num1.class) //prints class java.math.BigDecimal

num2 = 73617467863478623786478623784
println(num2.class) //prints class java.math.BigInteger

/**
Unlike Java, Groovy performs floating-point division by default. 
For example, the operation 2/4 would result in 0.5.
*/
println("10/3 would be ${10/3}") //prints 10/3 would be 3.3333333333
println("10.33 * 10 would be ${10.33 * 10}") //10.33 * 10 would be 103.30
println("10%3 would be ${10%3}") //gives the remainder 1
println("10**2 would be ${10**2}") //** is power prints 100
println("-3 would be ${-3}") // -3
println("-3 + 3 would be ${-3+3}") // prints 0
/**
Lists
Lists in Groovy are an ordered collection of items. By default, Groovy lists are instances of java.util.ArrayList
*/
println '''

****************
List In Groovy
****************
'''
List l = [1,2,3,4]
println(l) //[1,2,3,4]

l = [1,2,'string',4]
println(l) //[1,2,string,4]
println(l[2].class) //class java.lang.String
println(l.class) //class java.util.ArrayList

println("[1,2,3,4].get(1) outputs ${[1,2,3,4].get(1)}") //prints 2
println("[1,2,3,4].getAt(-1) outputs ${[1,2,3,4].getAt(-1)}") //Prints 4, the first element from last

List list = [1,2,3,4]
list.set(1,10) //put value at given index
println(list) //[1,10, 3, 4]

list.add 7 //adds the value after last element
println(list) //[1, 10, 3, 4, 7]

list.remove 0 //removes 1
println(list) //[10, 3, 4, 7]

list.pop() //removes 0th index item
println(list)

sum = 0
list.each {e -> sum += e} //sum of [3, 4, 7]
println(sum) //14

list.each { print it } //it is a default value holder variable //prints 347


/**
Range in Groovy
*/
println '''

****************
Range In Groovy
****************
'''
Range range0 = 1..10
def range = 1..10
range = 1..10

println(range.get(0)) //prints 1
println(range[0])  //prints 1
println(range) //1..10
println(range.last()) //10
range.each {e -> print(e)} //prints 1 to 10 one after another

/**
Maps in groovy
Maps in Groovy contain key/value pairs. By default Groovy maps are instances of java.util.LinkedHashMap
Elements in a map can be accessed in several ways. The most common approach is to use the "." notation, others are using get() and map[]
*/
println '''

****************
Maps In Groovy
****************
'''
Map map = [ 'key' : 'val', 'key0' : 'val0' ]
println(map) //[key:val, key0:val0]

map = [
1 : "one",
2 : "two"
]
println(map) //[1:one, 2:two]
println(map.get(1)) //prints one
println(map[2]) //prints two

map = [
"red" : 1,
"blue" : 2
]
println(map.red) //prints 1 , string keys can be also accessed using . notation

//Add or replace key value pairs to map
map.put("blue", 4)
println(map) //[red:1, blue:4]

map.put("green", 10)
println(map) //[red:1, blue:4, green:10]

map.each {e -> println("e.key = ${e.key} and e.val = ${e.value}")}
map.each { println("it.key = ${it.key} and it.val = ${it.value}")} //it denotes to default iterator variable
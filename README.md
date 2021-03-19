# java-bootcamp
A project to try out various coding problems and try out new features and technologies related to java ecosystem

### 1hr Learning
These are small coding problems or tasks expected to be completed with in an hr.

- Java, How bytes are converted to character ?
Bytes are 8 bit in size, Byte has the range of 2^8=256 (-128 to 1287)
Char is 16 bit in size, char can have the range of 2^16=5536 (0 - 65636) characters.It has a minimum value of '\u0000' (or 0) and a maximum value of '\uffff'

The following conversion combines both widening and narrowing primitive conversions:
- byte to char
First, the byte is converted to an int via widening primitive conversion (§5.1.2), and then the resulting int is converted to a char by narrowing primitive conversion
- A code point is the atomic unit of information. Text is a sequence of code points. Each code point is a number which is given meaning by the Unicode standard. 
A code unit is the unit of storage of a part of an encoded code point. In UTF-8 this means 8-bits, in UTF-16 this means 16-bits. A single code unit may represent a full code point, or part of a code point. For example, the snowman glyph (☃) is a single code point but 3 UTF-8 code units, and 1 UTF-16 code unit.
- [Java primitive Types](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.htm)
- [Useful Resources on byte to char](https://stackoverflow.com/questions/17912640/byte-and-char-conversion-in-java/17912706)
- [Byte to char conversion](https://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.1.4)
- [Code Points](https://stackoverflow.com/questions/27331819/whats-the-difference-between-a-character-a-code-point-a-glyph-and-a-grapheme)
- [Unicode Points](http://tutorials.jenkov.com/unicode/index.html)
- [Why all unicode cannot be represented in char](https://codeahoy.com/2016/05/08/the-char-type-in-java-is-broken/)
- [Supplementary characters in java](https://www.oracle.com/technical-resources/articles/javase/supplementary.html)

### Usage of java optional
java optional is container object which may or may not contain a non-null value. If a value is present, **isPresent()** will return true and **get()** will return the value.
Optional is intended to be used with java stream api and not in method parameters or return values or getter or setter 
- [java optonal api documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)
- [How to use optional](https://dzone.com/articles/using-optional-correctly-is-not-optional)

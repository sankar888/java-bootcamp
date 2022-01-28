### Read Input Interactively From User
There are multiple ways to read input interactively from user.
1. Console class
2. Reading from System.in
3. Using Scanner Class
    
*java.io.Console* - has methods to access the character-based console device, if any, associated with the current Java virtual machine.
Whether a virtual machine has a console is dependent upon the underlying platform and also upon the manner in which the virtual machine is invoked.
If the virtual machine is started from an interactive command line without redirecting the standard input and output streams then its console will exist and will typically be connected to the keyboard and display from which the virtual machine was launched. 
If the virtual machine is started automatically, for example by a background job scheduler, then it will typically not have a console.
Then *System.console()* will return *null*


### Resources
[Console class](https://docs.oracle.com/javase/8/docs/api/java/io/Console.html)
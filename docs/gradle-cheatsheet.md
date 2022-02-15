
Gradle provides a convenient way to declare your project dependencies. It then automatically downloads those dependencies (along with transitive dependencies) and allows you to use them in your projects
Gradle uses a Groovy (http://groovy-lang.org/) based domain specific language (DSL—see https://docs.gradle.org/current/dsl/) for declaring builds
Gradle provides sensible defaults and conventions for Java, Groovy, web, Scala, Android, and OSGi projects.
Incremental Builds

Complex projects often run into slow build times as the build tool tries to “clean” and rebuild everything. Gradle addresses this problem by providing incremental builds that skip the execution of a task if neither the inputs nor the outputs have changed.
Gradle Wrapper

Gradle Wrapper is simply a batch file (gradlew.bat) in the Windows environment and a shell script for Linux/Mac environments. When it runs, the wrapper script downloads and installs a fresh copy of Gradle runtime on the machine and executes a Gradle build.


gradle -v
gradle -help
gradle -gui

### Resources
* [gradlw website](https://gradle.org/)
* [Groovy Language](http://groovy-lang.org/)
* [Gradle Groovy DSL Language](https://docs.gradle.org/current/dsl/)
* [Gradle Tutorials](https://guides.gradle.org/)
* [Groovy Language Documentation](http://www.groovy-lang.org/single-page-documentation.html)

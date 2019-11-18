# Driven Search Engine

Simple program to search words inside files from directory

# Build project
sbt compile

# Run project after build
The easiest way to start the project is to run following commands:
    
    >>> sbt assembly
The following jar should be generated DrivenSearchEngine-assembly-0.1.jar after that you can run following command
    
    >>> java -jar target/scala-2.13/DrivenSearchEngine-assembly-0.1.jar /pathToDIrectory
    
 After that the program should be up and running
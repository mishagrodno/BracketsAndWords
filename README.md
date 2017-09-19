# **BRACKETS AND WORDS**

## WHAT IS IT?

This project implements two tasks: 
 1) Search the most frequent words in the text file, without taking into account words from blacklist
 
 2) Check bracket sequence from text file for correctness

## INSTALLATION

1) Download and install [Apache Maven](http://www.apache-maven.ru/install.html)

2) Download and install [Far Manager](http://www.farmanager.com/download.php?l=ru) or other console manager

3) Open \BracketsAndWords\TextAnalyzer in console manager

4) On command line, type in the following commands:
 
    $ mvn clean package
    $ mvn exec:java
 
6) Ready for use

To change words in blacklist, add them to \BracketsAndWords\TextAnalyzer\src\main\resources\WordsNotToAddInTop.properties 
seperated by commas without spaces.

## HOW TO USE?

There is a simple interface with 3 commands:
 1) The most frequent words from text file - search the most frequent words from your text file, without taking into
    account words from blacklist.
    
    **Note**: Warning: blacklist not found - file \BracketsAndWords\TextAnalyzer\src\main\resources\WordsNotToAddInTop.properties
    not found
 
 2) Analyze bracket sequence from text file - check the bracket sequence from a file
 
 3) Stop - stop program

## CONTACTS

mishagrodno@mail.ru
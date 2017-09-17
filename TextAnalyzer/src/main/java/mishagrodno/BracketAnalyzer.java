package mishagrodno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class BracketAnalyzer {

    // Stack for storing opening brackets
    // When we find opening bracket,
    // we put it into Stack
    // When we find closing bracket,
    // We check the last element of stack
    // and if this two brackets make a pair
    // we just pop element from stack,
    // else brackets sequence is incorrect
    private Stack<Character> openingBrackets;

    public BracketAnalyzer() {
        openingBrackets = new Stack<>();
    }

    // input: fileName - way to the text file
    // return correct if Bracket sequence is correct
    // else return incorrect

    public String Analyzer(String fileName){
        //Reading file
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            //Character read.
            int symbol;
            while((symbol = bufferedReader.read())!=-1){
                // Current symbol
                char currSymbol = (char) symbol;
                // Check current symbol
            }
            //end of reading file
            bufferedReader.close();
            return "";
        }
        catch (Exception e){
            return "File not found";
        }
    }



}


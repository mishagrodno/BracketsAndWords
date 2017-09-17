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
                int result = newSymbol(currSymbol);
                // if current symbol makes bracket sequence incorrect
                // close reader and return "incorrect"
                if(result==-1) {
                    bufferedReader.close();
                    return "incorrect";
                }
            }
            //end of reading file
            bufferedReader.close();
            //if after reading file no more opening brackets bracket sequence is correct
            if(openingBrackets.isEmpty()) return "correct";
            else return "incorrect";
        }
        catch (Exception e){
            return "File not found";
        }
    }


    // input: symbol - next character from file
    // if symbol makes Bracket sequence incorrect return -1
    // else return 0

    private int newSymbol(char symbol){
        if(symbol =='(' || symbol =='[' || symbol == '{'){
            openingBrackets.push(symbol);
        }
        if(symbol == ')' || symbol == ']' || symbol == '}'){
            if(openingBrackets.size()==0) return -1;
            if(isBracketPair(openingBrackets.peek(),symbol)) openingBrackets.pop();
            else return -1;
        }
        return 0;
    }

    // input: bracket1 - opening bracket from openingBrackets, bracket2 - closing bracket
    // if brackets make pair return true
    // else return false

    private boolean isBracketPair(char bracket1, char bracket2) {
        return (bracket1 == '(' && bracket2 == ')') || (bracket1 == '[' && bracket2 == ']') || (bracket1 == '{' && bracket2 == '}');
    }
}


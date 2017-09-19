package mishagrodno;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //Count words in file
    private WordCounter wordCounter = new WordCounter();
    //Check bracket sequence
    private BracketAnalyzer bracketAnalyzer = new BracketAnalyzer();
    //read from console
    private Scanner in = new Scanner(System.in);
    //true - file with bad words exists, else false
    //if file not exist we consider, there are no bad words

    public static void main(String args[]){
        Main main = new Main();
        main.runApplication();
    }

    private void runApplication() {
        while (true) {
            System.out.println("-------------------------------------------");
            System.out.println("Choose command:");
            System.out.println("1 - The most frequent words from text file");
            //if file not exist, say about it
            if(!wordCounter.isBlackListLoaded()) System.out.println("Warning: blacklist not found");
            System.out.println("2 - Analyze bracket sequence from text file");
            System.out.println("3 - Stop");
            System.out.println("-------------------------------------------");
            String answer = in.next();
            if (answer.length() > 1 || !(Character.isDigit(answer.charAt(0))))
                System.out.println("Unknown command");
            else {
                int ans = Integer.parseInt(answer);

                switch (ans) {
                    case 1: countWords();
                    break;
                    case 2: analyzeBrackets();
                    break;
                    case 3: return;
                    default:System.out.println("Unknown command");
                    break;
                }
            }
        }
    }

    //work with wordCounter
    private void countWords(){
        System.out.println("-------------------------------------------");
        System.out.println("The most frequent words from text file");
        if(!wordCounter.isBlackListLoaded()) System.out.println("Warning: blacklist not found");
        System.out.println("-------------------------------------------");
        System.out.println("Add file location: ");
        String fileName = in.next();
        List<Map.Entry<String,Integer>> result;
        try{
            result = wordCounter.topWords(fileName);
            System.out.println("The most frequent words: ");
            for(Map.Entry<String,Integer> entry: result)
                System.out.println(entry.getKey() + " - " + entry.getValue());
            System.out.println("-------------------------------------------");
        }
        catch (FileNotFoundException e){
            System.out.println("File not found " + fileName);
        }
    }

    //work with bracketAnalyzer
    private void analyzeBrackets(){
        System.out.println("-------------------------------------------");
        System.out.println("Analyze bracket sequence from text file");
        System.out.println("-------------------------------------------");
        System.out.println("Add file location: ");
        String fileName = in.next();
        String result;
        try {
            result = bracketAnalyzer.analyzer(fileName);
            System.out.println("Bracket sequence is " + result);
        }
        catch (Exception e){
            System.out.println("File not found " + fileName);
        }
    }

}


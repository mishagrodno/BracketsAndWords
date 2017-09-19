package mishagrodno;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private WordCounter wordCounter;
    private BracketAnalyzer bracketAnalyzer;
    private Scanner in;
    private boolean fileResourcesFound = true;

    public Main(){
        try {
            wordCounter = new WordCounter();
            bracketAnalyzer = new BracketAnalyzer();
            in = new Scanner(System.in);
        }
        catch (ResourceFileNotFoundException e) {
            fileResourcesFound = false;
        }
    }

    public static void main(String args[]){
        Main main = new Main();
        main.dialog();
    }

    private void dialog() {
        while (true) {
            System.out.println("-------------------------------------------");
            System.out.println("Choose command:");
            System.out.println("1 - The most frequent words from text file");
            if(!fileResourcesFound) System.out.println("Warning: Resource file src/main/resources/WordsNotToAddInTop.properties not found");
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
                    case 2: AnalyzeBrackets();
                    break;
                    case 3: return;
                    default:System.out.println("Unknown command");
                    break;
                }
            }
        }
    }

    private void countWords(){
        System.out.println("-------------------------------------------");
        System.out.println("The most frequent words from text file");
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
        catch (Exception e){
            System.out.println("File not found " + fileName);
        }

    }

    private void AnalyzeBrackets(){
        System.out.println("-------------------------------------------");
        System.out.println("Analyze bracket sequence from text file");
        System.out.println("-------------------------------------------");
        System.out.println("Add file location: ");
        String fileName = in.next();
        String result;
        try {
            result = bracketAnalyzer.Analyzer(fileName);
            System.out.println("Bracket sequence is " + result);
        }
        catch (Exception e){
            System.out.println("File not found " + fileName);
        }
    }

}


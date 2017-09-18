package mishagrodno;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.*;

public class WordCounter {
    // properties file, which stores what words we shouldn't add
    private static String notToAddWords = "src/main/resources/WordsNotToAddInTop.properties";
    //stores bad word from notToAddWords
    private String[] badWords;
    //stores all words from given text file and its amount in text
    private HashMap<String,Integer> wordCount;

    public WordCounter(){
        wordCount = new HashMap<>();
        LoadBadWords();
        //add bad words to wordCount with amount = -1
        for (String badWord : badWords)
            wordCount.put(badWord, -1);
    }

    //loading words that we shouldn't count
    private void LoadBadWords(){
        FileInputStream fileInputStream;
        Properties property = new Properties();

        try{
            // try to load property
            fileInputStream = new FileInputStream(notToAddWords);
            property.load(fileInputStream);
            //words contains all bad words from file notToAddWords
            String words = property.getProperty("notToAdd");
            //fill in badWords with bad words
            badWords = words.split(",");
        }
        catch (Exception e){
            System.out.println("File not found " + notToAddWords);
        }
    }

    private void readWordsFromFile(String fileName){
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            //reading by line
            while((line = bufferedReader.readLine())!=null){
                //delete punctuation
                String lineWithoutPunctuation = removePunctuation(line);
                //add words from line
                wordsFromLine(lineWithoutPunctuation);
            }
        }
        catch (Exception e){
            System.out.println("File not found " + fileName);
        }
    }

    //delete punctuation from line
    private String removePunctuation(String str){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            //symbols ' and - are not delete< because they can be inside words
            if(Character.isAlphabetic(c) || Character.isSpaceChar(c) || c=='\'' || c=='-')
                result.append(c);
        }
        return result.toString();
    }


    private void wordsFromLine(String line){
        String[] words =  line.split(" ");
        for (String word : words) {
            // we didn't delete dashes in removePunctuation
            if (Objects.equals(word, "-")) continue;
            //if we've already put such word, increase count, else put word
            if (wordCount.containsKey(word)) {
                Integer currCount = wordCount.get(word);
                //if currCount=-1, it means that word is bad
                if (currCount == -1) continue;
                currCount++;
                wordCount.put(word, currCount);
            } else {
                wordCount.put(word, 1);
            }
        }
    }



}

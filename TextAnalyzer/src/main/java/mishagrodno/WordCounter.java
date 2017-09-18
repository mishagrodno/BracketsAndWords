package mishagrodno;

import java.io.FileInputStream;
import java.util.*;

public class WordCounter {
    // properties file, which stores what words we shouldn't add
    private static String notToAddWords = "src/main/resources/WordsNotToAddInTop.properties";
    //stores bad word from notToAddWords
    private String[] badWords;
    //stores all words from given text file and its amount int text
    HashMap<String,Integer> hm;

    public WordCounter(){
        hm = new HashMap<>();
        LoadBadWords();
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
            System.out.println("Couldn't find file " + notToAddWords);
        }
    }





}

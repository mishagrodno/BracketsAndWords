package mishagrodno;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class WordCounter {
    // properties file, which stores what words we shouldn't add
    private static String blackList = "src/main/resources/WordsNotToAddInTop.properties";
    //stores bad word from notToAddWords
    private String[] badWords;
    //stores all words from given text file and its amount in text
    //and bad words with amount -1
    private Map<String,Integer> wordCount = new HashMap<>();
    // number of words to return
    private int countOfWords = 10;
    //true - successfully loaded blackList, else false
    private boolean isBlackListExists;

    public WordCounter(){
        loadBadWords();
    }

    //return list of words and their amount in text
    public List<Map.Entry<String,Integer>> topWords(String fileName) throws FileNotFoundException{
        //add words
        readWordsFromFile(fileName);
        //sort by amount
        sort();
        //list entry to return
        List<Map.Entry<String,Integer>> topWords = new LinkedList<>();
        //i - count of words to return
        int i = countOfWords;
        for(Map.Entry<String,Integer> entry: wordCount.entrySet()){
            //if entry.value()=-1 it means that the words from badWords began
            if(entry.getValue()==-1 || i==0) break;
            //add word to result
            topWords.add(entry);
            //decrease count of word to return
            i--;
        }
        return topWords;
    }

    public boolean isBlackListLoaded(){
      return isBlackListExists;
    }

    //loading words that we shouldn't count
    private void loadBadWords(){
        //loading words from notToAddWords
        FileInputStream fileInputStream;
        Properties property = new Properties();

        try{
            // try to load property
            fileInputStream = new FileInputStream(blackList);
            property.load(fileInputStream);
            //words contains all bad words from file notToAddWords
            String words = property.getProperty("notToAdd");
            //fill in badWords with bad words
            badWords = words.split(",");
            //add bad words to wordCount with amount = -1
            for (String badWord : badWords)
                wordCount.put(badWord, -1);
            isBlackListExists = true;
        }
        catch (Exception e){
            isBlackListExists = false;
        }
    }

    private void readWordsFromFile(String fileName) throws FileNotFoundException{
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            //reading by line
            while((line = bufferedReader.readLine())!=null){
                //lowercase line
                line = line.toLowerCase();
                //delete punctuation
                String lineWithoutPunctuation = removePunctuation(line);
                //add words from line
                wordsFromLine(lineWithoutPunctuation);
            }
        }
        catch (Exception e){
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    //delete punctuation from line
    private String removePunctuation(String str){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<str.length();i++){
            char currSymbol = str.charAt(i);
            //symbols ' and - are not delete, because they can be inside words
            if(Character.isAlphabetic(currSymbol) || Character.isSpaceChar(currSymbol) || currSymbol=='\'' || currSymbol=='-')
                result.append(currSymbol);
            else result.append(' ');
        }
        //delete multiple spaces
        return result.toString().replaceAll("\\s+"," ");
    }

    // add words from line to wordCount
    private void wordsFromLine(String line){
        //not add empty lines
        if(line.isEmpty()) return;
        //divide line into words
        String[] words =  line.split(" ");
        for (String word : words) {
            // we didn't delete dashes in removePunctuation, so do it now
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

    //sorting wordCount id descending order
    private void sort(){
        Set<Map.Entry<String,Integer>> mapEntries = wordCount.entrySet();
        List<Map.Entry<String,Integer>> aList = new LinkedList<>(mapEntries);
        Collections.sort(aList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        wordCount = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> entry: aList)
            wordCount.put(entry.getKey(), entry.getValue());
    }
}

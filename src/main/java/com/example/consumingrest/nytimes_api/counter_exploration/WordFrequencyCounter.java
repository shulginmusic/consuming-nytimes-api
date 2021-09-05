package com.example.consumingrest.nytimes_api.counter_exploration;

import java.util.*;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        //A sample string
        String s = "apples, oranges, apples, apples, oranges, apples, apples, oranges, apples, apples, oranges, apples";
        //Declare hashmap to hold words and their occurences
        HashMap<String, Integer> wordsMap = new HashMap<>();
        //Create an String array to hold the words of the string
        String[] words = s.split("\\W+");
        //Populate hashmap with words
        for (String word : words) {
            if (!wordsMap.containsKey(word)) {
                wordsMap.put(word, 1);
            } else {
                int count = wordsMap.get(word);
                wordsMap.replace(word, count + 1);
            }
        }
        System.out.println(wordsMap.toString());

        int maxCount = Collections.max(wordsMap.values());
        ArrayList<Map.Entry<String, Integer>> mostCommonWords = new ArrayList<>();
        mostCommonWords.addAll(wordsMap.entrySet());
        mostCommonWords.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o1.getValue(), o2.getValue()); //Ref: https://www.baeldung.com/java-comparator-comparable
            }
        });
        //Reverse the most common words list
        Collections.reverse(mostCommonWords);
        System.out.println(mostCommonWords.get(3));


    }
}

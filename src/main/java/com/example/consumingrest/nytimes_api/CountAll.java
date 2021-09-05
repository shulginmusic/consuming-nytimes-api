package com.example.consumingrest.nytimes_api;

public class CountAll {
    public static void main(String[] args) {
        //This is just a test file to explore how to count words in a String
        String test = "apples apples apples apples apples";
        int count = 0;
        String[] words = test.split("\\W+");
        for (String word : words) {
            if (word.equalsIgnoreCase("apples")) {
                count++;
            }
        }
        System.out.println(count);
    }
}

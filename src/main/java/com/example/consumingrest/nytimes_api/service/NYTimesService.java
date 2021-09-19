package com.example.consumingrest.nytimes_api.service;

import com.example.consumingrest.nytimes_api.model.Doc;
import com.example.consumingrest.nytimes_api.payload.response.AvgImagesResponse;
import com.example.consumingrest.nytimes_api.payload.response.CountResponse;
import com.example.consumingrest.nytimes_api.payload.response.MostCommonWordsResponse;
import com.example.consumingrest.nytimes_api.payload.response.NYTimesAPIResponse;
import com.example.consumingrest.nytimes_api.utility.StopWord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@PropertySource("classpath:application.properties")
public class NYTimesService {

    private String apikey;
    private RestTemplate restTemplate;
    private String apiKeySnippet;

    private final String BASE_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json?q=";

    @Autowired
    public NYTimesService(@Value("${api_key}") String apikey, RestTemplate restTemplate) {
        this.apikey = apikey;
        this.restTemplate = restTemplate;
        this.apiKeySnippet = "&api-key=" + apikey; //your NY Times API key here, formatted like: "&api-key=KEY"
    }

    /**
     * This method returns a New York Times response for a query
     *
     * @param query the query to be searched for
     * @return the New York times API response in JSON
     */

    public NYTimesAPIResponse getArticles(String query) {
        //Get API response
        NYTimesAPIResponse response =
                restTemplate.getForObject(
                        BASE_URL + query + "&page=" + 0 + apiKeySnippet, NYTimesAPIResponse.class);
        return response;
    }

    /**
     * This method returns a New York Times response for a query for a specified page (each page is 10 articles)
     *
     * @param query the query to be searched for
     * @param page  the page specified (pages start from 0)
     * @return the New York times API response in JSON
     */

    public NYTimesAPIResponse getArticlesWithPage(String query, String page) {
        String url = BASE_URL + query + "page=" + page + apiKeySnippet;
        //Get API response
        NYTimesAPIResponse response =
                restTemplate.getForObject(
                        BASE_URL + query + "&page=" + page + apiKeySnippet, NYTimesAPIResponse.class);
        return response;
    }

    /**
     * This method returns the count of a word for a particular query. It searches for the word in abstract string,
     * snippet, lead paragraph, and both main and print headlines.
     *
     * @param query   the query to be searched for
     * @param keyword the word to be counted
     * @return the number of times a word appears for a given query
     */

    public CountResponse countOccurences(String query, String keyword) {
        //Declare a count variable
        int count = 0;
        //This String array will hold the words from a String
        String[] words = null;
        //Get API response
        NYTimesAPIResponse response =
                restTemplate.getForObject(
                        BASE_URL + query + "&page=" + 0 + apiKeySnippet, NYTimesAPIResponse.class);
        //Loop over all the Doc objects in the docs array of the response
        for (Doc doc : response.getResponse().getDocs()) {

            //First, we split the String into individual words using a Regex
            words = doc.abstractStr.split("\\W+");
            count += countWords(words, keyword); //Thank you Ryan Desmond

            //Then proceed to do the same with snippets:
            words = doc.snippet.split("\\W+");
            count += countWords(words, keyword); //Thank you Ryan Desmond

            //Then proceed to do the same with lead paragraph:
            words = doc.lead_paragraph.split("\\W+");
            count += countWords(words, keyword); //Thank you Ryan Desmond

            //Then proceed to do the same with main headline:
            words = doc.headline.mainStr.split("\\W+");
            count += countWords(words, keyword); //Thank you Ryan Desmond

            //Then proceed to do the same with print headline:
//          Not every article has a print headline, so we must avoid the NullPointerException
            if (doc.headline.print_headline != null) {
                words = doc.headline.print_headline.split("\\W+");
                count += countWords(words, keyword); //Thank you Ryan Desmond
            }
        }

        CountResponse countResponse = new CountResponse(query, keyword, count);
        return countResponse;
    }

    /**
     * This method returns the n most common words in a query, filtered by an english stop word list
     *
     * @param query         the query to be searched for
     * @param numberOfWords the n most common words the user wants
     * @return a simple pojo with the n most common words and their occurences
     */

    public MostCommonWordsResponse getMostCommonWordsInQuery(String query, int numberOfWords) {
        //This String array will hold the words from the String objects
        ArrayList<String> words = new ArrayList<>();

        //Get API response
        NYTimesAPIResponse response =
                restTemplate.getForObject(
                        BASE_URL + query + "&page=" + 0 + apiKeySnippet, NYTimesAPIResponse.class);

        //Loop over all the Doc objects in the docs array of the response
        //Splitting the String objects and adding to the words ArrayList
        for (Doc doc : response.getResponse().getDocs()) {
            //Split the abstract string and add every word to the words array list
            words.addAll(Arrays.asList(doc.abstractStr.split("\\W+")));
            //Then proceed to do the same with snippet:
            words.addAll(Arrays.asList(doc.snippet.split("\\W+")));//
            //Then proceed to do the same with lead paragraph:
            words.addAll(Arrays.asList(doc.lead_paragraph.split("\\W+")));
            //Then proceed to do the same with main headline:
            words.addAll(Arrays.asList(doc.headline.mainStr.split("\\W+")));
            //Then proceed to do the same with print headline:
//          Not every article has a print headline, so we must avoid the NullPointerException
            if (doc.headline.print_headline != null) {
                words.addAll(Arrays.asList(doc.headline.print_headline.split("\\W+")));
            }
        }
        //Now that we have all the words from the String object in our words ArrayList,
        //time to map the words to their frequencies!
        //Declare hashmap to hold words and their occurences
        HashMap<String, Integer> wordsMap = new HashMap<>();
        //Populate hashmap with words as keys and the number of times they occur as values
        for (String word : words) {
            //If no such key exists in the map
            if (!wordsMap.containsKey(word)) {
                //Add the word and give it an initial count of 1
                wordsMap.put(word, 1);
            } else {
                //If a word has already occured
                int count = wordsMap.get(word); //get its current count
                wordsMap.replace(word, count + 1); //update the word key by incrementing its count (value) by 1
            }
        }

        //Now that we have the map of words and their occurences, declare a new array list of map entries
        List<Map.Entry<String, Integer>> mostCommonWords = new ArrayList<>();
        //Add all the entries to the mostCommonWords ArrayList
        mostCommonWords.addAll(wordsMap.entrySet());
        //Sort the ArrayList by the values of the entries, first ascending in order
        mostCommonWords.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return Integer.compare(o1.getValue(), o2.getValue()); //Ref: https://www.baeldung
                // .com/java-comparator-comparable
            }
        });
        //Now reverse the order of the list
        Collections.reverse(mostCommonWords);
        //Filter mostCommonWords
        mostCommonWords = filterWords(mostCommonWords);
        //Declare a new array list of entries and add to it according to how many common
        //words the user requested
        ArrayList<Map.Entry<String, Integer>> commonWordArrayList = new ArrayList<>();

        mostCommonWords = mostCommonWords.subList(0, numberOfWords);
        //Return a new MostCommonWords POJO
        return new MostCommonWordsResponse(query, numberOfWords, mostCommonWords);

    }

    /**
     * This method returns the average number of images per article in this API response
     *
     * @param query the query to be searched for
     * @return average number of images per article
     */

    public AvgImagesResponse getAverageImages(String query) {
        //Get API response
        NYTimesAPIResponse response =
                restTemplate.getForObject(
                        BASE_URL + query + "&page=" + 0 + apiKeySnippet, NYTimesAPIResponse.class);

        //Variable to hold how many images appear in response total
        int totalImages = 0;

        //Variable to hold how many docs we have in the response
        //(default is 10, but that might change later on
        int totalDocs = response.getResponse().getDocs().length;

        //Loop over the docs and find how many images appear
        for (Doc doc : response.getResponse().getDocs()) {
            totalImages += doc.multimedia.length;
        }

        int averageImages = totalImages / totalDocs;

        return new AvgImagesResponse(query, averageImages);

    }

    /**
     * This method loops over the words array,
     * looking for the keyword,
     * counting up every time we find one
     *
     * @param words   the word array
     * @param keyword the word we're looking for
     * @return how many times a word appears in the string
     */

    private int countWords(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }

    /**
     * This method returns a filtered ArrayList of Map entries, to omit stop words typical in the English Language
     *
     * @param words
     * @return a filtered ArrayList of Map entries
     *
     * References:
     * 1. https://stackoverflow.com/questions/43614641/words-to-exclude-from-a-search
     * 2. https://github.com/Alir3z4/stop-words
     * 3. https://stackoverflow.com/questions/15824733/option-to-ignore-case-with-contains-method
     * 4. https://docs.oracle.com/javase/7/docs/api/java/util/ConcurrentMod`ificationException.html
     */


    private List<Map.Entry<String, Integer>> filterWords(List<Map.Entry<String, Integer>> words) {
        //Declare the StopWordPOJO
        StopWord stop = new StopWord();
        //Declare new array list of entries for filtered common words
        ArrayList<Map.Entry<String, Integer>> filteredCommonWords = new ArrayList<>();
        //loop over the argument array list
        for (Map.Entry<String, Integer> entry : words) {
            if (!StringUtils.containsIgnoreCase(stop.stopWords, entry.getKey())) {
                //if key is not in stop words string, add the entry to the filtered common words
                filteredCommonWords.add(entry);
            }
        }
        return filteredCommonWords;
    }

}

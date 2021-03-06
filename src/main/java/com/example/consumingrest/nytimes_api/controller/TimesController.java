package com.example.consumingrest.nytimes_api.controller;

import com.example.consumingrest.nytimes_api.payload.response.AvgImagesResponse;
import com.example.consumingrest.nytimes_api.payload.response.CountResponse;
import com.example.consumingrest.nytimes_api.payload.response.MostCommonWordsResponse;
import com.example.consumingrest.nytimes_api.payload.response.NYTimesAPIResponse;
import com.example.consumingrest.nytimes_api.service.NYTimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimesController {
    @Autowired
    NYTimesService service;

    /**
     * This method returns a New York Times response for a query
     *
     * @param query the query to be searched for
     * @return the New York times API response in JSON
     */
    @RequestMapping("/nytimes")//?q="query"
    public NYTimesAPIResponse getArticles(@RequestParam("q") String query) {
        return service.getArticles(query);
    }

    /**
     * This method returns a New York Times response for a query for a specified page (each page is 10 articles)
     *
     * @param query the query to be searched for
     * @param page  the page specified (pages start from 0)
     * @return the New York times API response in JSON
     */
    @RequestMapping("nytimes/page")//?q="query"&page="page-number"
    public NYTimesAPIResponse getArticlesWithPage(@RequestParam("q") String query,
                                                  @RequestParam("p") String page) {
        return service.getArticlesWithPage(query, page);
    }

    /**
     * This method returns the count of a word for a particular query. It searches for the word in abstract string,
     * snippet, lead paragraph, and both main and print headlines.
     *
     * @param query   the query to be searched for
     * @param keyword the word to be counted
     * @return the number of times a word appears for a given query
     */

    @RequestMapping("/nytimes/count")//?q="query"&keyword="keyword"
    public CountResponse countOccurences(@RequestParam("q") String query, @RequestParam("keyword") String keyword) {
        return service.countOccurences(query, keyword);
    }

    /**
     * This method returns the n most common words in a query, filtered by an english stop word list
     * @param query the query to be searched for
     * @param numberOfWords the n most common words the user wants
     * @return a simple pojo with the n most common words and their occurrences
     */

    @GetMapping("/nytimes/mostcommon")//?q="query"&n="numberOfWords"
    public MostCommonWordsResponse mostCommon(@RequestParam("q") String query,
                                              @RequestParam("n") int numberOfWords) {
        return service.getMostCommonWordsInQuery(query, numberOfWords);
    }

    /**
     * This method returns the average number of images per article in this API response
     *
     * @param query the query to be searched for
     * @return average number of images per article
     */

    @RequestMapping("/nytimes/avg-images")//?q="query"
    public AvgImagesResponse getAverageImages(@RequestParam("q") String query) {
        return service.getAverageImages(query);
    }

}

















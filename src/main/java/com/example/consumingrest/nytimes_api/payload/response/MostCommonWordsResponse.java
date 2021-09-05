package com.example.consumingrest.nytimes_api.payload.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MostCommonWordsResponse {
    String query;
    int numberOfWords;
    List<Map.Entry<String, Integer>> mostCommonWords;
}

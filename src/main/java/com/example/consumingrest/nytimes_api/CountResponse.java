package com.example.consumingrest.nytimes_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CountResponse {
    String query;
    String keyword;
    int occurences;
}

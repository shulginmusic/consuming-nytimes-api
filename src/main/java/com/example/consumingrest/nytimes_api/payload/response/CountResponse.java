package com.example.consumingrest.nytimes_api.payload.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountResponse {
    String query;
    String keyword;
    int occurences;
}

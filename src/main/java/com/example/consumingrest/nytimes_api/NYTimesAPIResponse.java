package com.example.consumingrest.nytimes_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class NYTimesAPIResponse {
    String status;
    String copyright;
    Response response;
}

package com.example.consumingrest.nytimes_api.payload.response;

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

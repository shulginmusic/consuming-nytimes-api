package com.example.consumingrest.nytimes_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Headline {
    @JsonProperty("main")
    String mainStr;
    String print_headline;
}

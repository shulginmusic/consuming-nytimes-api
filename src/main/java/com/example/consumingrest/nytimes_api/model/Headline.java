package com.example.consumingrest.nytimes_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Headline {
    @JsonProperty("main")
    public String mainStr;
    public String print_headline;
}

package com.example.consumingrest.nytimes_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Keyword {
    String name;
    String value;
    int rank;
    String major;
}

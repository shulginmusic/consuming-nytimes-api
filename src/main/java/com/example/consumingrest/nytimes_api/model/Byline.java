package com.example.consumingrest.nytimes_api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Byline {
    String original;
    Person[] person;
    String organization;
}

package com.example.consumingrest.nytimes_api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    String firstname;
    String middlename;
    String lastname;
    String qualifier;
    String title;
    String role;
    String organization;
    int rank;
}

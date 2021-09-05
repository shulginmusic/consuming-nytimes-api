package com.example.consumingrest.nytimes_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    int hits;
    int offset;
    int time;
}

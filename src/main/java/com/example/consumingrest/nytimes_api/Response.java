package com.example.consumingrest.nytimes_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    Doc[] docs;
    Meta meta;
}

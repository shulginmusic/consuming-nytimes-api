package com.example.consumingrest.nytimes_api.payload.response;

import com.example.consumingrest.nytimes_api.model.Doc;
import com.example.consumingrest.nytimes_api.model.Meta;
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

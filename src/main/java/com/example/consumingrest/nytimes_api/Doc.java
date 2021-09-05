package com.example.consumingrest.nytimes_api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doc {
    @JsonProperty("abstract")
    String abstractStr;
    String web_url;
    String snippet;
    String lead_paragraph;
    String print_section;
    String print_page;
    String source;
    Multimedia[] multimedia;
    Headline headline;
    Keyword[] keywords;
    String pub_date;
    String document_type;
    String news_desk;
    String section_name;
    Byline byline;
    String type_of_material;
    String _id;
    int word_count;
    String uri;

}

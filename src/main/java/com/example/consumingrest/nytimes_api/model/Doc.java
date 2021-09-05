package com.example.consumingrest.nytimes_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Doc {
    @JsonProperty("abstract")
    public String abstractStr;
    public String web_url;
    public String snippet;
    public String lead_paragraph;
    public String print_section;
    public String print_page;
    public String source;
    public Multimedia[] multimedia;
    public Headline headline;
    public Keyword[] keywords;
    public String pub_date;
    public String document_type;
    public String news_desk;
    public String section_name;
    public Byline byline;
    public String type_of_material;
    public String _id;
    public int word_count;
    public String uri;

}

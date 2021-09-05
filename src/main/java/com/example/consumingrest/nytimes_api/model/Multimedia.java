package com.example.consumingrest.nytimes_api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Multimedia {
    int rank;
    String subtype;
    String caption;
    String credit;
    String type;
    String url;
    int height;
    int width;
//    Legacy legacy;
    String subType;
    String crop_name;

}

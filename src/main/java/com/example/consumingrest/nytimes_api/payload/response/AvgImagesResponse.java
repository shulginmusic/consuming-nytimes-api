package com.example.consumingrest.nytimes_api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AvgImagesResponse {
    String query;
    String message;
    int averageImages;

    public AvgImagesResponse(String query, int averageImages) {
        this.query = query;
        this.message = "Average images per query: " + this.query;
        this.averageImages = averageImages;
    }
}


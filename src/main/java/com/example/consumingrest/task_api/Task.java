package com.example.consumingrest.task_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    int id;
    int userId;
    String name;
    String description;
    long createdAt;
    long updatedAt;
    boolean completed;

}

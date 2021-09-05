package com.example.consumingrest.task_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    int id;
    String first_name;
    String last_name;
    String email;
    long createdAt;
    long updatedAt;

}

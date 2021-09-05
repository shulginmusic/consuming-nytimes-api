package com.example.consumingrest.task_api;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TasksAPIResponse<T> {
    T data;
    String error;
    int statusCode;


}

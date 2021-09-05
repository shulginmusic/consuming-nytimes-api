package com.example.consumingrest.task_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;

@RestController
public class TaskController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/efim")
    public void testMethod() {
        TasksAPIResponse<User> u = restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/5", TasksAPIResponse.class);

        User userFive = mapper.convertValue(
                u.getData(),
                new TypeReference<User>() {
                }
        );
        System.out.println(userFive.getFirst_name());

        TasksAPIResponse<ArrayList<User>> response =
                restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/", TasksAPIResponse.class);
        ArrayList<User> users = mapper.convertValue(
                response.getData(),
                new TypeReference<ArrayList<User>>(){}
        );
        users.forEach(System.out::println);

    }

    @RequestMapping("/users")
    public ArrayList<User> getUsers() {
        TasksAPIResponse<ArrayList<User>> response =
                restTemplate.getForObject("http://demo.codingnomads.co:8080/tasks_api/users/", TasksAPIResponse.class);
        ArrayList<User> users = mapper.convertValue(
                response.getData(),
                new TypeReference<ArrayList<User>>(){}
        );
        return users;
    }
}

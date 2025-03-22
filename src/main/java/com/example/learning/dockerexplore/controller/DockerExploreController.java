package com.example.learning.dockerexplore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker/v1")
public class DockerExploreController {
    @GetMapping("/hello")
    public String getHelloWorld(String data){

        if(data != null && (!data.isEmpty())){
            return data;
        }
        return "Hello World";
    }
}

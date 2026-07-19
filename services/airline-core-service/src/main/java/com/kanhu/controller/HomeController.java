package com.kanhu.controller;

import com.kanhu.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ApiResponse homeController(){
        return new ApiResponse("Hay Everyone, i am airline core service i i'll manage airlines, aircraft fleet etc. ");
    }
}

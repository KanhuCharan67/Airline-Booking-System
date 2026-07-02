package com.kanhu.controller;

import com.kanhu.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeController(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("location service of airline microservice");
        return apiResponse;
    }
}

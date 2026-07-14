package com.kanhu.controller;

import com.kanhu.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse apiResponse(){
        ApiResponse apiResponse = new ApiResponse("Home controller");
        return apiResponse;
    }
}

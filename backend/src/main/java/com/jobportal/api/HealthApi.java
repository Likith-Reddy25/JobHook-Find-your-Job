package com.jobportal.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthApi {

    @GetMapping("/health")
    public String Health(){
        return "OK";
    }
}

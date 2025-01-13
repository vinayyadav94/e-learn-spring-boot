package com.elearn.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String test(){
        return "post testing...";
    }

    @GetMapping
    @PreAuthorize("hasRole('GUEST')")
    public String getTest(){
        return "get testing...";
    }

    @GetMapping("/all")
    public String all(){
        return "all... open api endpoint";
    }

}

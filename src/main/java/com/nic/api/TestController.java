package com.nic.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping
    public String hello(){
        return "hello";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("test")
    public String test(){
        return "test hello";
    }
}

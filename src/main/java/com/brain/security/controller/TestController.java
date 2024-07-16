package com.brain.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firecode16
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        System.out.println("is one test controller");
        return "Test spring rest controller";
    }
}

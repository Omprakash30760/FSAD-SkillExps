package com.example.skill13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaForwardController {

    @GetMapping(value = {"/", "/app"})
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}

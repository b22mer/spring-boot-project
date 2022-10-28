package com.ssafy.home.main.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Api(tags = {"index"})
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}

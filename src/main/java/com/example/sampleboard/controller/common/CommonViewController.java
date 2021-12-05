package com.example.sampleboard.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonViewController {

    @RequestMapping("/login")
    public String login(){
        return "/common/login";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "/common/signup";
    }

    @RequestMapping("/denied")
    public String denied(){
        return "/common/denied";
    }

}

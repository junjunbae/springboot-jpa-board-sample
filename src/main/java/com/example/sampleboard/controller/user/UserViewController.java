package com.example.sampleboard.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserViewController {

    @RequestMapping("/main")
    public String main(){
        return "/user/main";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "/user/admin";
    }

}

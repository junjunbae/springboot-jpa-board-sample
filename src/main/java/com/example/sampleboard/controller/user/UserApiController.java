package com.example.sampleboard.controller.user;

import com.example.sampleboard.entity.user.dto.UserRequestDto;
import com.example.sampleboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user")
    public String signup(UserRequestDto userDto){
        userService.save(userDto);
        return "redirect:/login";
    }

//

}

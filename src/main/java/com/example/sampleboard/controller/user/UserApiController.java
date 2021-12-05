package com.example.sampleboard.controller.user;

import com.example.sampleboard.entity.user.dto.UserDto;
import com.example.sampleboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user")
    public String signup(UserDto userDto){
        userService.save(userDto);
        return "redirect:/login";
    }

//

}

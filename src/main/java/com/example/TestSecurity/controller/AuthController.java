package com.example.TestSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author karpeykin
 * @Date 22.01.2021
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping("/login")
    public String getLoginPage() {
        return "/login";
    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "/success";
    }
}

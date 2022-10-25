package com.ssafy.home.member.controller;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Member member) {
        return memberService.login(member);
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody Member member) {
        System.out.println(member.getId());
        memberService.register(member);
        return "register ok";
    }

}

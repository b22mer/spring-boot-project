package com.ssafy.home.member.controller;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.service.MemberService;
import com.ssafy.home.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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
    public Member login(@RequestBody Member member, HttpServletRequest req) {
        // 로그인 프로세스 추가
        Member m = memberService.login(member);
        HttpSession session = req.getSession();
        session.setAttribute("member", m);
        return m;

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

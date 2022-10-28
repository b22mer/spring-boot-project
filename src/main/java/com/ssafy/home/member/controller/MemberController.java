package com.ssafy.home.member.controller;

import com.ssafy.home.member.dto.LoginDTO;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = {"users"})
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    public ResponseEntity<Member> login(
            @ApiParam(value = "member")
            @RequestBody LoginDTO member,
            HttpServletRequest req) throws Exception {
        // 로그인 프로세스 추가
        Member user = memberService.login(member);
        HttpSession session = req.getSession();
        session.setAttribute("member", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/register")
    public String register() {
        return "user/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody Member member) throws Exception {
        System.out.println(member.getId());
        memberService.register(member);
        return "register ok";
    }

    @GetMapping("/info")
    public String info() {
        System.out.println("user info");
        return "user/info";
    }
}

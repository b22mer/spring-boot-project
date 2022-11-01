package com.ssafy.home.member.controller;

import com.ssafy.home.dto.ResponseDTO;
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
import java.util.HashMap;
import java.util.Map;

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

    /**
     * @param member: 사용자 id, pw
     * @param req     : request
     * @return : body
     * @throws Exception : 암호화 관련 예외 포함
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    public ResponseEntity<?> login(
            @ApiParam(value = "member")
            @RequestBody LoginDTO member,
            HttpServletRequest req) {
        try {
            // 로그인 프로세스 추가
            Member user = memberService.login(member);
            HttpSession session = req.getSession();
            session.setAttribute("member", user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> exception = new HashMap<>();
            exception.put("msg", "로그인 정보가 잘못되었습니다. 다시 로그인해 주세요");
            return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
        }
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

    @GetMapping("/info") // 테스트용
    public String info(HttpServletRequest req) {
        System.out.println("user 받기");
        System.out.println(req.getAttribute("user"));
        System.out.println("user info");
        return "user/info";
    }

    @PostMapping("idchck")
    @ResponseBody
    public ResponseEntity<ResponseDTO> idCheck(@RequestParam String userId) {
        int isId = memberService.checkId(userId);
        ResponseDTO res = new ResponseDTO();
        if (isId > 0) {
            // id 존재
            res.setStatus("fail");
            res.setErrMsg("id가 존재합니다.");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        } else {
            //id 생성 가능
            res.setStatus("success");
            res.setMsg("id는 생성 가능합니다.");
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }
}

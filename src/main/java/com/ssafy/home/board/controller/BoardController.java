package com.ssafy.home.board.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.WriteBoardDTO;
import com.ssafy.home.board.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Api(tags = {"board"})
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // 생성자 주입
public class BoardController {
    private final BoardService boardService; // 생성자 주입을 위한 처리

    @PostMapping("/list")
    @ResponseBody
    public PageInfo<?> selectAll(HttpServletRequest request) {
        PageHelper.startPage(request);
        List<Board> list = boardService.selectAll();
        return PageInfo.of(list);
    }

    @GetMapping("/selectAll")
    public String list() {
        return "board/list";
    }

    @GetMapping("/write")
    public String writeBoard(HttpSession session) {
        String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("CSRF_TOKEN", csrfToken);
        System.out.println(csrfToken);
        return "boardWriteForm";
    }

    @PostMapping("/writeboard")
    public void writeBoard(
            @ApiParam(value = "board")
            @RequestBody WriteBoardDTO board,
            @ApiParam(value = "csrf_token")
            @RequestBody String csrf_token,
            HttpSession session,
            HttpServletRequest request) {
        System.out.println(board);
        System.out.println("받은 토큰: " + csrf_token);
        String CSRF_TOKEN = (String) session.getAttribute("CSRF_TOKEN");
        if (CSRF_TOKEN != null && CSRF_TOKEN.equals(csrf_token)) {
            long res = boardService.writeBoard(board);
            if (res > 0) {
                // 정상적으로 저장됨
                System.out.println("insert success");
            } else {
                System.out.println("insert fail");
            }
        } else {
            System.out.println(request.getRemoteAddr() + "fail");
        }

    }
}

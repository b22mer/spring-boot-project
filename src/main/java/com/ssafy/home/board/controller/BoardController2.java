package com.ssafy.home.board.controller;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.WriteBoardDTO;
import com.ssafy.home.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("board2")
public class BoardController2 {//동기적 처리
    @Autowired
    BoardService boardService;

    @GetMapping("selectAll2")
    public ModelAndView selectAll2() {
        System.out.println("selectAll2");
        List<Board> list = boardService.selectAll();

        ModelAndView mav = new ModelAndView("boardList");
        mav.addObject("articlesList", list);
        return mav;
    }

//    @PostMapping("boardWrite2")
//    public ModelAndView boardWrite(WriteBoardDTO vo) {
//        System.out.println("=======>" + vo);
//        boardService.writeBoard(vo);
//
//        return selectAll2();
//    }

    @GetMapping("writeForm")
    public ModelAndView writeForm(HttpSession session) {
        String token = UUID.randomUUID().toString();
        session.setAttribute("CSRF_TOKEN", token);
        System.out.println(token);
        return new ModelAndView("board/boardWriteForm");
    }

    @PostMapping("boardWrite2")
    public void boardWrite(WriteBoardDTO vo, String csrf_token, HttpSession session, HttpServletRequest request) {
        System.out.println("=======>" + csrf_token);
        String CSRF_TOKEN = (String) session.getAttribute("CSRF_TOKEN");

        if (CSRF_TOKEN != null && CSRF_TOKEN.equals(csrf_token)) {
            long res = boardService.writeBoard(vo);
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

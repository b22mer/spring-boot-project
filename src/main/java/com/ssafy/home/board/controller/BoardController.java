package com.ssafy.home.board.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

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

}

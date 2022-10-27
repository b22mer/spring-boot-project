package com.ssafy.home.board.controller;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<List<Board>> selectAll() {
        List<Board> list = boardService.selectAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/selectAll")
    public String list() {
        return "board/list";
    }

}

package com.ssafy.home.board.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.BoardDto;
import com.ssafy.home.board.dto.WriteBoardDTO;
import com.ssafy.home.board.service.BoardService;
import com.ssafy.home.common.dto.ResponseDTO;
import com.ssafy.home.member.dto.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*https://goodteacher.tistory.com/351*/

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
        List<BoardDto> list = boardService.selectAll();
        return PageInfo.of(list);
    }

    @GetMapping("/selectall")
    public String list() {
        return "board/list";
    }

    @PostMapping("/writeboard")
    public void writeBoard(
            @ApiParam(value = "board")
            @RequestBody WriteBoardDTO board) {
        System.out.println(board);
        long res = boardService.writeBoard(board);
        if (res > 0) {
            // 정상적으로 저장됨
            System.out.println("insert success");
        } else {
            System.out.println("insert fail");
        }

    }

}

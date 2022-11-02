package com.ssafy.home.board.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.BoardDto;
import com.ssafy.home.board.dto.FileDTO;
import com.ssafy.home.board.service.BoardService;
import com.ssafy.home.common.dto.ResponseDTO;
import com.ssafy.home.member.dto.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Api(tags = {"board"})
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // 생성자 주입
public class BoardController {
    private final BoardService boardService; // 생성자 주입을 위한 처리

    @PostMapping("update")
    public String update(
            HttpServletRequest req,
            @ApiParam(value = "files")
            @RequestParam MultipartFile[] files,
            @ApiParam(value = "boardDto")
            BoardDto boardDto
    ) throws IOException {
        BoardDto reqBoard = (BoardDto) req.getAttribute("detail");
        reqBoard.setTitle(boardDto.getTitle());
        reqBoard.setContent(boardDto.getContent());
        List<FileDTO> list = new ArrayList<>();
        for (MultipartFile file :
                files) {
            FileDTO dto = new FileDTO(
                    UUID.randomUUID().toString(),
                    file.getOriginalFilename(),
                    file.getContentType());
            list.add(dto);
            File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
            file.transferTo(newFileName);
        }
        boardDto.setFileInfos(list);
        boardService.updateBoard(reqBoard);
        return "board/list";
    }

    @GetMapping("detail/{code}")
    public String detail(@PathVariable String code, HttpServletRequest req) {
        BoardDto boardDto = boardService.select(code);
        req.setAttribute("detail", boardDto);
        return "board/detail";
    }

    @GetMapping("upload")
    public String upload() {
        return "board/upload";
    }

    @PostMapping("upload")
    public String upload(
            HttpServletRequest req,
            @ApiParam(value = "files")
            @RequestParam MultipartFile[] files,
            @ApiParam(value = "boardDto")
            BoardDto boardDto
    ) throws IOException {
        Member member = (Member) req.getAttribute("member");
        boardDto.setId(member.getId());
        boardDto.setWriter(member.getName());
        ResponseDTO res = new ResponseDTO();
        List<FileDTO> list = new ArrayList<>();
        for (MultipartFile file :
                files) {
            FileDTO dto = new FileDTO(
                    UUID.randomUUID().toString(),
                    file.getOriginalFilename(),
                    file.getContentType());
            list.add(dto);
            File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
            file.transferTo(newFileName);
        }
        boardDto.setFileInfos(list);
        boardService.writeBoard(boardDto);
        return "board/list";
    }

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


}

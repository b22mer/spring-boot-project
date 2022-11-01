package com.ssafy.home.board.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.home.board.dto.BoardDto;
import com.ssafy.home.board.dto.FileDTO;
import com.ssafy.home.board.dto.WriteBoardDTO;
import com.ssafy.home.board.service.BoardService;
import com.ssafy.home.common.dto.ResponseDTO;
import com.ssafy.home.member.dto.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/*https://goodteacher.tistory.com/351*/

@Api(tags = {"board"})
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // 생성자 주입
public class BoardController {
    private final BoardService boardService; // 생성자 주입을 위한 처리


    @GetMapping("upload")
    public String upload() {
        return "board/upload";
    }

    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity<ResponseDTO> upload(
            HttpServletRequest req,
            @ApiParam(value = "files")
            @RequestParam MultipartFile[] files,
            @ApiParam(value = "boardDto")
            BoardDto boardDto
    ) throws IOException {
        Member member = (Member) req.getAttribute("member");
        boardDto.setUserId(member.getId());
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
        Map<String, List<?>> map = new HashMap<>();
        res.setStatus("success");
        map.put("file", list);
        res.setBody(map);
        res.setMsg("file upload");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/selectall")
    public String list() {
        return "board/list";
    }

//    @PostMapping("/writeboard")
//    public void writeBoard(
//            @ApiParam(value = "board")
//            @RequestBody WriteBoardDTO board) {
//        System.out.println(board);
//        long res = boardService.writeBoard(board);
//        if (res > 0) {
//            // 정상적으로 저장됨
//            System.out.println("insert success");
//        } else {
//            System.out.println("insert fail");
//        }

//    }

}

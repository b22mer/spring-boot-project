package com.ssafy.home.board.service;

import com.ssafy.home.board.dto.BoardDto;
import com.ssafy.home.board.dto.FileDTO;
import com.ssafy.home.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<BoardDto> selectAll() {
        return boardMapper.selectAll();
    }

    @Transactional
    public void writeBoard(BoardDto board) {
        List<FileDTO> list = board.getFileInfos();

        if (list!=null && !list.isEmpty()){
            boardMapper.addFile(board);
        }
        System.out.println("file insert success");
        boardMapper.writeBoard(board);
        System.out.println("board insert success");
    }
}

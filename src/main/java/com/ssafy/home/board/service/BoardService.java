package com.ssafy.home.board.service;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.WriteBoardDTO;
import com.ssafy.home.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    public List<Board> selectAll() {
        return boardMapper.selectAll();
    }

    public long writeBoard(WriteBoardDTO board) {
        return boardMapper.writeBoard(board);
    }
}

package com.ssafy.home.board.service;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<Board> selectAll() {
        return boardMapper.selectAll();
    }

}

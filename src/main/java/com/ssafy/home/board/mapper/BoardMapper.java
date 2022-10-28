package com.ssafy.home.board.mapper;

import com.ssafy.home.board.dto.Board;
import com.ssafy.home.board.dto.WriteBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public List<Board> selectAll();

    void writeBoard(WriteBoardDTO board);
}

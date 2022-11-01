package com.ssafy.home.board.mapper;

import com.ssafy.home.board.dto.BoardDto;
import com.ssafy.home.board.dto.WriteBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDto> selectAll();

    long writeBoard(WriteBoardDTO board);
}

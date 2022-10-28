package com.ssafy.home.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "게시글 작성")
@Getter
@Setter
public class WriteBoardDTO {
    @Schema(description = "int")
    private int originNo;
    @Schema(description = "int")
    private int groupOrd;
    @Schema(description = "int")
    private int groupLayer;
    @Schema(description = "글 제목")
    private String title;
    @Schema(description = "글 내용")
    private String content;
    @Schema(description = "사용자 이름")
    private String writer;

}

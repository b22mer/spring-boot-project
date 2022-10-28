package com.ssafy.home.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginDTO {
    @Schema(name = "사용자 id", description = "사용자 id")
    private String id;
    @Schema(description = "사용자 비밀번호")
    private String pw;
}

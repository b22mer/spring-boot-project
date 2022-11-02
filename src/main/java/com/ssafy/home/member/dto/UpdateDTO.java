package com.ssafy.home.member.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateDTO {
	 private String name, position, email, phoneNumber;

}

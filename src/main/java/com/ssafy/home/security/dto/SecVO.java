package com.ssafy.home.security.dto;

import lombok.Data;

@Data
public class SecVO {
    String userId, uuid, salt;
}

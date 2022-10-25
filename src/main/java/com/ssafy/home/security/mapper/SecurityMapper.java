package com.ssafy.home.security.mapper;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.security.dto.SecVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityMapper {
    void insertSecurity(SecVO secVO);

    SecVO selectByUserId(String userId);
}

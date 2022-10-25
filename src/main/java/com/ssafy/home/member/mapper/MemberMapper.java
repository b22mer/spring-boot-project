package com.ssafy.home.member.mapper;

import com.ssafy.home.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    String login(Member member);
}

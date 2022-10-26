package com.ssafy.home.member.mapper;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.util.MyException;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberMapper {
    Member login(Map<String, String> map);

    void register(Member member);
}

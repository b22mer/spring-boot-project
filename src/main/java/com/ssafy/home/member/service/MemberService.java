package com.ssafy.home.member.service;

import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    public String login(Member member) {
        return memberMapper.login(member);
    }
}

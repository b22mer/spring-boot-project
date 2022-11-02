package com.ssafy.home.member.service;

import com.ssafy.home.member.dto.LoginDTO;
import com.ssafy.home.member.dto.Member;
import com.ssafy.home.member.mapper.MemberMapper;
import com.ssafy.home.security.mapper.SecurityMapper;
import com.ssafy.home.security.dto.SecVO;
import com.ssafy.home.util.CipherUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final SecurityMapper securityMapper;

    public Member userInfo(String userId) {
        return memberMapper.userInfo(userId);
    }

    public Member login(LoginDTO member) throws Exception {
        SecVO secVO = securityMapper.selectByUserId(member.getId());
        String hashedPassword = new String(CipherUtil.getSHA256(member.getPw(), secVO.getSalt()));
        Map<String, String> map = new HashMap<>();
        map.put("id", member.getId());
        map.put("pw", hashedPassword);
        Member user = memberMapper.login(map);
        String name = CipherUtil.aesDecrypt(user.getName(), CipherUtil.hexToByteArray(secVO.getSalt()));
        user.setName(name);
        return user;
    }
    
    public void delete(Member member)throws Exception{
        System.out.println(member.getId());
    	memberMapper.userDelete(member);
    	
    }
    
    

    public void register(Member member) throws Exception {
        // 사용자 정보 암호화
        byte[] key = CipherUtil.generateKey("AES", 128);
        SecVO secVO = new SecVO();
        secVO.setSalt(CipherUtil.byteArrayToHex(key));
        secVO.setUuid(UUID.randomUUID().toString());
        secVO.setUserId(member.getId());
        securityMapper.insertSecurity(secVO);

        member.setName(CipherUtil.aesEncrypt(member.getName(), key));
        member.setPw(new String(CipherUtil.getSHA256(member.getPw(), secVO.getSalt())));
        memberMapper.register(member);
    }

    public int checkId(String userId) {
        return memberMapper.idCheck(userId);
    }
}

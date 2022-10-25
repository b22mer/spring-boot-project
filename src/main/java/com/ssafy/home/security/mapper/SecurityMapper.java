package com.ssafy.home.security.mapper;

import com.ssafy.home.security.dto.SecVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityMapper {
    void insertSecurity(SecVO secVO);

}

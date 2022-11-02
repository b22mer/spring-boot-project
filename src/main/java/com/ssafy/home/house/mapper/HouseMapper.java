package com.ssafy.home.house.mapper;

import com.ssafy.home.house.dto.DealInfoDTO;
import com.ssafy.home.house.dto.DongCodeDTO;
import com.ssafy.home.house.dto.HouseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HouseMapper {
    List<String> getGugun(String sidoName);

    List<String> getDong(Map<String, String> map);

    List<HouseDTO> getHouseDealInfo(DealInfoDTO dealInfoDTO);
}

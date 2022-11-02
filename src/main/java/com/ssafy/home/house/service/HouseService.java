package com.ssafy.home.house.service;

import com.ssafy.home.house.dto.DealInfoDTO;
import com.ssafy.home.house.dto.DongCodeDTO;
import com.ssafy.home.house.dto.HouseDTO;
import com.ssafy.home.house.mapper.HouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HouseService {
    private final HouseMapper houseMapper;

    public List<String> gugun(String sidoName) {
        List<String> res = houseMapper.getGugun(sidoName);
        return houseMapper.getGugun(sidoName);
    }

    public List<String> getDong(String sidoName, String gugunName) {
        Map<String, String> map = new HashMap<>();
        map.put("sidoName", sidoName);
        map.put("gugunName", gugunName);
        return houseMapper.getDong(map);
    }

    public List<HouseDTO> getHouseDealInfo(DealInfoDTO dealInfoDTO) {
        return houseMapper.getHouseDealInfo(dealInfoDTO);
    }
}

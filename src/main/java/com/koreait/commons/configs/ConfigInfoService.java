package com.koreait.commons.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.entities.Configs;
import com.koreait.repositories.ConfigsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigInfoService {
    /**
     * 키, 벨류 값 조회하기
     */

    private final ConfigsRepository repository;

    public <T> T get(String code,Class<T> clazz){
        Configs configs = repository.findById(code).orElse(null);
        if(configs == null || configs.getValue()== null || configs.getValue().isBlank()){
            return null;
        }

        String value = configs.getValue();

        ObjectMapper om = new ObjectMapper();
        T data = null;
        try {
           data = om.readValue(value,clazz);
        } catch (JsonProcessingException e) {
                e.printStackTrace();
        }

        return data;
    }
}

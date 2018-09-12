package com.zhuochen.spring.session.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Log4j2
@Service
public class CommonService {

    public String convertObjectToJson(@NotNull Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("convertObjectToJson error: ", e);
            return "";
        }
    }

    public <T>T convertJsonToObject(@NotNull String json, Class<T> tClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (Exception e) {
            log.error("convertJsonToObject error: ", e);
            return null;
        }
    }
}

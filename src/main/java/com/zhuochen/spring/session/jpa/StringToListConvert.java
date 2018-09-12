package com.zhuochen.spring.session.jpa;



import com.zhuochen.spring.session.service.CommonStaticService;

import javax.persistence.AttributeConverter;
import java.util.List;

public class StringToListConvert implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        return CommonStaticService.joinListToString(strings);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return CommonStaticService.splitStringToStringList(s);
    }
}

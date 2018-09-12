package com.zhuochen.spring.session.service;

import com.zhuochen.spring.session.config.constants.CommonSymbol;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
public class CommonStaticService {

    public static void copyPropertiesWithoutNullProperty(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);

        return Stream.of(wrappedSource.getPropertyDescriptors())
                .filter(propertyDescriptor -> {
                    if (propertyDescriptor.getPropertyType().equals(String.class)) {
                        return Optional
                                .ofNullable(wrappedSource.getPropertyValue(propertyDescriptor.getName()))
                                .map(o -> StringUtils.isBlank(String.valueOf(o)))
                                .orElse(false);
                    } else {
                        return false;
                    }
                })
                .map(FeatureDescriptor::getName)
                .toArray(String[]::new);
    }

    public static String joinListToString(List<?> list) {
        return Optional.ofNullable(list)
                .map(l -> String.join(CommonSymbol.SPLIT_SYMBOL, l.parallelStream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())))
                .orElse(null);
    }

    public static List<Long> splitStringToLongList(String string) {
        return Optional.ofNullable(string)
                .filter(StringUtils::isNotBlank)
                .map(s -> Arrays.stream(s.split(CommonSymbol.SPLIT_SYMBOL))
                        .map(Long::valueOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public static List<Integer> splitStringToIntegerList(String string) {
        return Optional.ofNullable(string)
                .filter(StringUtils::isNotBlank)
                .map(s -> Arrays.stream(s.split(CommonSymbol.SPLIT_SYMBOL))
                        .map(Integer::valueOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public static List<Double> splitStringToDoubleList(String string) {
        return Optional.ofNullable(string)
                .filter(StringUtils::isNotBlank)
                .map(s -> Arrays.stream(s.split(CommonSymbol.SPLIT_SYMBOL))
                        .map(Double::valueOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public static List<String> splitStringToStringList(String string) {
        return Optional.ofNullable(string)
                .filter(StringUtils::isNotBlank)
                .map(s -> Arrays.stream(s.split(CommonSymbol.SPLIT_SYMBOL)).collect(Collectors.toList()))
                .orElse(Collections.<String>emptyList());
    }

}

package com.zhuochen.spring.session.config.validate;

import com.google.common.collect.Lists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    private List<String> valueList;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Optional.ofNullable(value)
                .map(v -> valueList.contains(v))
                .orElse(true);
    }

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClazz();

        valueList = Lists.newArrayList(constraintAnnotation.enumClazz().getEnumConstants())
                .parallelStream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}

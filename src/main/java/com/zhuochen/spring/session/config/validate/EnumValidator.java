package com.zhuochen.spring.session.config.validate;

import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
@ReportAsSingleViolation
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClazz();

    String message() default "Value is not valid enum";

//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
}

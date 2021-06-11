package com.xss.AnnotationSanitize.validation.annotation;

import com.xss.AnnotationSanitize.validation.validator.NoHtmlValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NoHtmlValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoHtml {
  String message() default "Parâmetro fora do padrão!";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

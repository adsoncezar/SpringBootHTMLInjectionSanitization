package com.xss.AnnotationSanitize.validation.validator;

import com.xss.AnnotationSanitize.Utils.SanitizerUtils;
import com.xss.AnnotationSanitize.exception.XSSValidatorException;
import com.xss.AnnotationSanitize.validation.annotation.NoHtml;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {

  private static final PolicyFactory POLICY_FACTORY = new HtmlPolicyBuilder().toFactory();
  private String annotationMessage;

  @Override
  public void initialize(NoHtml constraintAnnotation) {
    annotationMessage = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    var valueSanitized = SanitizerUtils.clearXss(value);

    if (valueSanitized.equals(value)) {
      return true;
    }

    var contexts =
        ((ConstraintValidatorContextImpl) constraintValidatorContext)
            .getConstraintViolationCreationContexts();
    var field = contexts.get(0).getPath().getLeafNode().asString();
    throw new XSSValidatorException(field + ": " + annotationMessage);
  }
}

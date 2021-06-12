package com.xss.annotationsanitize.sanitization;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {

    private static final PolicyFactory POLICY_FACTORY = new HtmlPolicyBuilder().toFactory();
    private String annotationMessage;

    @Override
    public void initialize(NoHtml constraintAnnotation) {
        annotationMessage = constraintAnnotation.message();
    }

    public String clearXss(String value) {
        var s = POLICY_FACTORY.sanitize(value);
        return Jsoup.clean(s, Whitelist.none());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        var valueSanitized = clearXss(value);

        if (valueSanitized.equals(value)) {
            return true;
        }

        var contexts =
                ((ConstraintValidatorContextImpl) constraintValidatorContext)
                        .getConstraintViolationCreationContexts();
        var field = contexts.get(0).getPath().getLeafNode().asString();
        throw new InputViolationException(field + ": " + annotationMessage);
    }

}

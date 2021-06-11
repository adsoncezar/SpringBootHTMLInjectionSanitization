package com.xss.AnnotationSanitize.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class XSSValidatorException extends RuntimeException {

  public XSSValidatorException(String message) {
    super(message);
  }
}

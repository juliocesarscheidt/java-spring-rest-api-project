package com.github.juliocesarscheidt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public FileNotFoundException(String exception) {
    super(exception);
  }

  public FileNotFoundException(String exception, Throwable cause) {
    super(exception, cause);
  }
}

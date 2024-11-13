package org.example.pay.exception;

public class NotEnoughSumException extends RuntimeException {

  public NotEnoughSumException(String message) {
    super(message);
  }
}

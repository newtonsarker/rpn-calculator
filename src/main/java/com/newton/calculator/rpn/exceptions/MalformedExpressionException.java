package com.newton.calculator.rpn.exceptions;

public class MalformedExpressionException extends Exception {

    public MalformedExpressionException(String message, Throwable exception) {
        super(message, exception);
    }

    public MalformedExpressionException(String message) {
        super(message);
    }
}

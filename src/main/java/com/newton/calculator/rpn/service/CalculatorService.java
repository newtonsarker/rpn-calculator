package com.newton.calculator.rpn.service;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;

import java.util.Stack;

public interface CalculatorService {

    void calculate(Stack<Integer> stack) throws MalformedExpressionException;

}

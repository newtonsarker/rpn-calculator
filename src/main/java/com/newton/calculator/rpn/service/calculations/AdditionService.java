package com.newton.calculator.rpn.service.calculations;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import com.newton.calculator.rpn.service.CalculatorService;

import java.util.EmptyStackException;
import java.util.Stack;

public class AdditionService implements CalculatorService {

    @Override
    public void calculate(Stack<Integer> stack) throws MalformedExpressionException {
        try {
            stack.push(stack.pop() + stack.pop());
        } catch (EmptyStackException exception) {
            throw new MalformedExpressionException("Could not process invalid expression!", exception);
        }
    }
}

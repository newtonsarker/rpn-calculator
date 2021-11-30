package com.newton.calculator.rpn.service.calculations;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import com.newton.calculator.rpn.service.CalculatorService;

import java.util.EmptyStackException;
import java.util.Stack;

public class SubstractionService implements CalculatorService {

    @Override
    public void calculate(Stack<Integer> stack) throws MalformedExpressionException {
        try {
            final var val1 = stack.pop();
            final var val2 = stack.pop();
            stack.push(val2 - val1);
        } catch (EmptyStackException exception) {
            throw new MalformedExpressionException("Could not process invalid expression!", exception);
        }
    }

}

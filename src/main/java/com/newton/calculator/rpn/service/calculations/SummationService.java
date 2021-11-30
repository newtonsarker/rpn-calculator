package com.newton.calculator.rpn.service.calculations;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import com.newton.calculator.rpn.service.CalculatorService;

import java.util.Stack;

public class SummationService implements CalculatorService {

    @Override
    public void calculate(Stack<Integer> stack) throws MalformedExpressionException {
        int sum = 0;
        while (!stack.empty()) {
            sum += stack.pop();
        }
        stack.push(sum);
    }

}

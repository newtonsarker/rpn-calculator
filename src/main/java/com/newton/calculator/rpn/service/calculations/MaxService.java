package com.newton.calculator.rpn.service.calculations;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import com.newton.calculator.rpn.service.CalculatorService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class MaxService implements CalculatorService {

    @Override
    public void calculate(Stack<Integer> stack) throws MalformedExpressionException {
        final var valueList = new ArrayList<Integer>();
        while (!stack.empty()) {
            valueList.add(stack.pop());
        }
        if (valueList.size() > 0) {
            stack.push(Collections.max(valueList));
        }
    }
}

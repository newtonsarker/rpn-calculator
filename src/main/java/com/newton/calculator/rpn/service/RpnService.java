package com.newton.calculator.rpn.service;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import com.newton.calculator.rpn.utils.StringTokenizer;

import java.util.Locale;
import java.util.Stack;

public class RpnService {

    private final ServiceSelector serviceSelector = new ServiceSelector();

    public int calculate(String expression) throws MalformedExpressionException {
        final Stack<Integer> stack = new Stack<>();

        for (String token : StringTokenizer.tokenize(expression)) {
            final var uToken = token.toUpperCase(Locale.ROOT);
            if (this.serviceSelector.selectService(uToken) != null) {
                this.serviceSelector.selectService(uToken).calculate(stack);
            } else {
                try {
                    stack.push(Integer.parseInt(uToken));
                } catch (NumberFormatException exception) {
                    throw new MalformedExpressionException("Expression contains invalid characters!", exception);
                }
            }
        }

        if (stack.empty()) {
            throw new MalformedExpressionException("Could not process invalid expression!");
        }
        return stack.pop();
    }

}

package com.newton.calculator.rpn.servlets;

import com.newton.calculator.rpn.exceptions.InvalidHttpRequestException;
import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import com.newton.calculator.rpn.service.RpnService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CalculatorServlet extends HttpServlet {

    private final RpnService rpnService = new RpnService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try {
            final var expression = this.extractExpressionFromPath(req);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(this.rpnService.calculate(expression));
        } catch (InvalidHttpRequestException | MalformedExpressionException exception) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(exception.getMessage());
        }
    }

    private String extractExpressionFromPath(HttpServletRequest req) throws InvalidHttpRequestException {
        final var pathVariables = req.getPathInfo().split("/");
        if (pathVariables[1] != null && pathVariables[1].length() > 0) {
            return pathVariables[1];
        }
        throw new InvalidHttpRequestException("Expression not available");
    }
}

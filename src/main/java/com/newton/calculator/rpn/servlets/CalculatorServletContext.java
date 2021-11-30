package com.newton.calculator.rpn.servlets;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class CalculatorServletContext {

    public ServletContextHandler createContext() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        this.addServlets(context);
        return context;
    }

    private void addServlets(ServletContextHandler context) {
        context.addServlet(new ServletHolder(new CalculatorServlet()), "/calculate/*");
    }

}

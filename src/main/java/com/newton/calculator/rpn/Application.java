package com.newton.calculator.rpn;

import com.newton.calculator.rpn.servlets.CalculatorServletContext;
import org.eclipse.jetty.server.Server;

public class Application {

    public static void main(String... args) {
        var server = new Server(8080);
        var servletContext = new CalculatorServletContext();
        try {
            server.setHandler(servletContext.createContext());
            server.start();
            server.join();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}

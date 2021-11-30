package com.newton.calculator.rpn.service;

import com.newton.calculator.rpn.service.calculations.AdditionService;
import com.newton.calculator.rpn.service.calculations.AvgService;
import com.newton.calculator.rpn.service.calculations.DivisionService;
import com.newton.calculator.rpn.service.calculations.ExponentService;
import com.newton.calculator.rpn.service.calculations.MaxService;
import com.newton.calculator.rpn.service.calculations.MinService;
import com.newton.calculator.rpn.service.calculations.MultiplicationService;
import com.newton.calculator.rpn.service.calculations.SubstractionService;
import com.newton.calculator.rpn.service.calculations.SummationService;

import java.util.HashMap;
import java.util.Map;

public class ServiceSelector {

    private final Map<String, CalculatorService> serviceMap;

    public ServiceSelector() {
        this.serviceMap = new HashMap<>();
        this.serviceMap.put("+", new AdditionService());
        this.serviceMap.put("-", new SubstractionService());
        this.serviceMap.put("*", new MultiplicationService());
        this.serviceMap.put("/", new DivisionService());
        this.serviceMap.put("^", new ExponentService());
        this.serviceMap.put("SUM", new SummationService());
        this.serviceMap.put("MIN", new MinService());
        this.serviceMap.put("MAX", new MaxService());
        this.serviceMap.put("AVG", new AvgService());
    }

    public CalculatorService selectService(String token) {
        return this.serviceMap.get(token);
    }

}

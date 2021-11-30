package com.newton.calculator.rpn.utils;

public final class StringTokenizer {

    private StringTokenizer() {
    }

    public static String[] tokenize(String string) {
        // remove unexpected spaces
        final var cleanedString = string.trim().replaceAll(" +", " ");

        // extract tokens
        return cleanedString.split(" ");
    }
}

package com.newton.calculator.rpn.servlets;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.AbstractMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorServletTest {

    public static final Map<String, String> SPECIAL_CHAR_MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("+", "%2b"),
            new AbstractMap.SimpleEntry<>("-", "%2d"),
            new AbstractMap.SimpleEntry<>("*", "%2a"),
            new AbstractMap.SimpleEntry<>("/", "%2f"),
            new AbstractMap.SimpleEntry<>("^", "%5e"),
            new AbstractMap.SimpleEntry<>(" ", "%20")
    );
    public static final int SERVER_PORT = 8083;
    public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    public static Server server;

    @BeforeAll
    public static void start() throws Exception {
        server = new Server(SERVER_PORT);
        var servletContext = new CalculatorServletContext();
        server.setHandler(servletContext.createContext());
        server.start();
    }

    @Test
    public void invalid_expression_should_return_status_400_and_error_message() throws Exception {
        // given
        var expression = "x * y";
        var httpRequest = this.createRequest(expression);

        // when
        HttpResponse<String> response = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // then
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.body().trim()).isEqualTo("Expression contains invalid characters!");
    }

    @Test
    public void valid_expression_should_return_status_200_and_result() throws Exception {
        // given
        var expression = "1 3 + 5 6 MIN 8 *";
        var httpRequest = this.createRequest(expression);

        // when
        HttpResponse<String> response = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // then
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body().trim()).isEqualTo("32");
    }

    @AfterAll
    public static void stop() throws Exception {
        server.stop();
    }

    private HttpRequest createRequest(String expression) {
        var encodedExpression = "";
        var charArray = expression.split("");
        for (int i = 0; i < charArray.length; i++) {
            if (SPECIAL_CHAR_MAP.get(charArray[i]) != null) {
                charArray[i] = SPECIAL_CHAR_MAP.get(charArray[i]);
            }
            encodedExpression += charArray[i];
        }
        final var uri = URI.create("http://localhost:" + SERVER_PORT + "/calculate/" + encodedExpression);
        return HttpRequest.newBuilder().uri(uri).GET().build();
    }

}

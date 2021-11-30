package com.newton.calculator.rpn.service;

import com.newton.calculator.rpn.exceptions.MalformedExpressionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RpnServiceTest {

    @Test
    public void should_be_able_to_evaluate_expression_correctly() throws MalformedExpressionException {
        // given
        var expression1 = "1 2 3 * +";
        var expression2 = "7 2 5 SUM 2 +";
        var expression3 = "3 4 9 6 3 AVG 5 /";
        var expression4 = "1 3 + 5 6 MIN 8 *";
        var expression5 = "1 3 + 5 6 min 8 *";

        // when
        var rpnService = new RpnService();
        var result1 = rpnService.calculate(expression1);
        var result2 = rpnService.calculate(expression2);
        var result3 = rpnService.calculate(expression3);
        var result4 = rpnService.calculate(expression4);
        var result5 = rpnService.calculate(expression5);

        // then
        assertThat(result1).isEqualTo(7);
        assertThat(result2).isEqualTo(16);
        assertThat(result3).isEqualTo(1);
        assertThat(result4).isEqualTo(32);
        assertThat(result5).isEqualTo(32);
    }

    @Test
    public void should_remove_unexpected_spaces_form_the_expression() throws MalformedExpressionException {
        // given
        var expression = "1 2     +";

        // when
        var rpnService = new RpnService();
        var result = rpnService.calculate(expression);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void should_raise_exception_if_division_by_zero_is_tried() {
        // given
        var expression = "1 0 /";

        // when
        var rpnService = new RpnService();
        Exception exception = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression));

        // then
        assertThat(exception.getMessage()).isEqualTo("Division by zero is not allowed!");
    }

    @Test
    public void should_raise_exception_if_expression_contains_invalid_character() {
        // given
        var expression = "1 x /";

        // when
        var rpnService = new RpnService();
        Exception exception = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression));

        // then
        assertThat(exception.getMessage()).isEqualTo("Expression contains invalid characters!");
    }

    @Test
    public void should_raise_exception_if_the_expression_is_not_valid() {
        // given
        var expression1 = "+";
        var expression2 = "-";
        var expression3 = "*";
        var expression4 = "/";
        var expression5 = "^";
        var expression7 = "MIN";
        var expression8 = "MAX";
        var expression9 = "AVG";

        // when
        var rpnService = new RpnService();
        Exception exception1 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression1));
        Exception exception2 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression2));
        Exception exception3 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression3));
        Exception exception4 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression4));
        Exception exception5 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression5));
        Exception exception7 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression7));
        Exception exception8 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression8));
        Exception exception9 = assertThrows(MalformedExpressionException.class, () -> rpnService.calculate(expression9));

        // then
        assertThat(exception1.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception2.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception3.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception4.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception5.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception7.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception8.getMessage()).isEqualTo("Could not process invalid expression!");
        assertThat(exception9.getMessage()).isEqualTo("Could not process invalid expression!");
    }

}

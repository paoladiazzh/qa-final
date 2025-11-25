package org.example.wip;

import org.example.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorWIPTest {

    @Test
    void sum_isSuccess() {
        // Arrange
        var calculator = new Calculator();
        var a = 5;
        var b = 6;

        // Act
        var res = calculator.add(a, b);

        // Assert
        assertEquals(11, res);
    }

    @Test
    void whenDivideBy0ThenThrowException() {
        // Arrange
        var calculator = new Calculator();
        var a = 5;
        var b = 0;

        // Act & Assert
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(a, b);
        });
    }

    @Test
    void whenNumberIsEvenThenTrue() {
        // Arrange
        var calculator = new Calculator();
        var a = 12;

        // Act
        var result = calculator.isEven(a);

        // Assert
        assertTrue(result);
    }

    @Test
    void whenNumberIsOddThenFalse() {
        // Arrange
        var calculator = new Calculator();
        var a = 11;

        // Act
        var result = calculator.isEven(a);

        // Assert
        assertFalse(result);
    }

    @Test
    void whenNumberIsPositiveThenReturnNumber() {
        // Arrange
        var calculator = new Calculator();
        var number = 33;

        // Act
        var result = calculator.absolute(number);

        //Assert
        assertEquals(number, result);
    }

    @Test
    void whenNumberIsNegativeThenReturnPositiveNumber() {
        // Arrange
        var calculator = new Calculator();
        var number = -33;

        // Act
        var result = calculator.absolute(number);

        //Assert
        assertEquals(-number, result);
    }

























}

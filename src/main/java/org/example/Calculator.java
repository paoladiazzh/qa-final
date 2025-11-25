package org.example;

/**
 * A simple calculator class that performs basic arithmetic operations.
 * This class is used to demonstrate unit testing concepts.
 */
public class Calculator {

    /**
     * Adds two numbers
     * @param a first number
     * @param b second number
     * @return sum of a and b
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Subtracts b from a
     * @param a first number
     * @param b second number
     * @return difference of a and b
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Multiplies two numbers
     * @param a first number
     * @param b second number
     * @return product of a and b
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Divides a by b
     * @param a dividend
     * @param b divisor
     * @return quotient of a divided by b
     * @throws ArithmeticException if b is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        var result = (double) a / b;

        return result;
    }

    /**
     * Checks if a number is even
     * @param number the number to check
     * @return true if the number is even, false otherwise
     */
    public boolean isEven(int number) {
        var result = number % 2 == 0;

        return result;
    }

    /**
     * Returns the absolute value of a number
     * @param number the number
     * @return absolute value
     */
    public int absolute(int number) {
        if (number < 0) {
            return - number;
        }

        return number;
    }
}

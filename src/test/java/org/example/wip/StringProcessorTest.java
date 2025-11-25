package org.example.wip;

import org.example.StringProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StringProcessor Unit Tests")
public class StringProcessorTest {

    private StringProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new StringProcessor();
    }

    // ==================== REVERSE TESTS ====================

    @Test
    @DisplayName("Should reverse a simple string")
    void whenReverseSimpleStringThenReturnReversed() {
        // Arrange
        String input = "hello";

        // Act
        String result = processor.reverse(input);

        // Assert
        assertEquals("olleh", result);
    }

    @Test
    @DisplayName("Should reverse an empty string")
    void whenReverseEmptyStringThenReturnEmpty() {
        // Arrange
        String input = "";

        // Act
        String result = processor.reverse(input);

        // Assert
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should throw exception when reversing null string")
    void whenReverseNullThenThrowException() {
        // Arrange
        String input = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            processor.reverse(input);
        });
    }

    @Test
    @DisplayName("Should reverse a single character")
    void whenReverseSingleCharacterThenReturnSame() {
        // Arrange
        String input = "a";

        // Act
        String result = processor.reverse(input);

        // Assert
        assertEquals("a", result);
    }

    // ==================== PALINDROME TESTS ====================

    @Test
    @DisplayName("Should return true for simple palindrome")
    void whenCheckSimplePalindromeThenReturnTrue() {
        // Arrange
        String input = "racecar";

        // Act
        boolean result = processor.isPalindrome(input);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return true for palindrome with mixed case")
    void whenCheckMixedCasePalindromeThenReturnTrue() {
        // Arrange
        String input = "RaceCar";

        // Act
        boolean result = processor.isPalindrome(input);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return true for palindrome phrase")
    void whenCheckPalindromePhraseThenReturnTrue() {
        // Arrange
        String input = "A man a plan a canal Panama";

        // Act
        boolean result = processor.isPalindrome(input);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false for non-palindrome")
    void whenCheckNonPalindromeThenReturnFalse() {
        // Arrange
        String input = "hello";

        // Act
        boolean result = processor.isPalindrome(input);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false for null string")
    void whenCheckNullPalindromeThenReturnFalse() {
        // Arrange
        String input = null;

        // Act
        boolean result = processor.isPalindrome(input);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false for empty string")
    void whenCheckEmptyPalindromeThenReturnFalse() {
        // Arrange
        String input = "";

        // Act
        boolean result = processor.isPalindrome(input);

        // Assert
        assertFalse(result);
    }

    // ==================== COUNT VOWELS TESTS ====================

    @Test
    @DisplayName("Should count vowels in string with vowels")
    void whenCountVowelsInStringThenReturnCount() {
        // Arrange
        String input = "hello";

        // Act
        int result = processor.countVowels(input);

        // Assert
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Should count vowels with mixed case")
    void whenCountVowelsMixedCaseThenReturnCount() {
        // Arrange
        String input = "AEIOUaeiou";

        // Act
        int result = processor.countVowels(input);

        // Assert
        assertEquals(10, result);
    }

    @Test
    @DisplayName("Should return zero for string with no vowels")
    void whenCountVowelsNoVowelsThenReturnZero() {
        // Arrange
        String input = "bcdfg";

        // Act
        int result = processor.countVowels(input);

        // Assert
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Should return zero for null string")
    void whenCountVowelsNullThenReturnZero() {
        // Arrange
        String input = null;

        // Act
        int result = processor.countVowels(input);

        // Assert
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Should return zero for empty string")
    void whenCountVowelsEmptyThenReturnZero() {
        // Arrange
        String input = "";

        // Act
        int result = processor.countVowels(input);

        // Assert
        assertEquals(0, result);
    }

    // ==================== CAPITALIZE WORDS TESTS ====================

    @Test
    @DisplayName("Should capitalize first letter of each word")
    void whenCapitalizeWordsThenReturnCapitalized() {
        // Arrange
        String input = "hello world";

        // Act
        String result = processor.capitalizeWords(input);

        // Assert
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Should handle already capitalized words")
    void whenCapitalizeAlreadyCapitalizedThenReturnSame() {
        // Arrange
        String input = "Hello World";

        // Act
        String result = processor.capitalizeWords(input);

        // Assert
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Should handle mixed case words")
    void whenCapitalizeMixedCaseThenReturnCapitalized() {
        // Arrange
        String input = "hELLo WoRLd";

        // Act
        String result = processor.capitalizeWords(input);

        // Assert
        assertEquals("Hello World", result);
    }

    @Test
    @DisplayName("Should return null for null input")
    void whenCapitalizeNullThenReturnNull() {
        // Arrange
        String input = null;

        // Act
        String result = processor.capitalizeWords(input);

        // Assert
        assertNull(result);
    }

    @Test
    @DisplayName("Should return empty for empty input")
    void whenCapitalizeEmptyThenReturnEmpty() {
        // Arrange
        String input = "";

        // Act
        String result = processor.capitalizeWords(input);

        // Assert
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should capitalize single word")
    void whenCapitalizeSingleWordThenReturnCapitalized() {
        // Arrange
        String input = "hello";

        // Act
        String result = processor.capitalizeWords(input);

        // Assert
        assertEquals("Hello", result);
    }

    // ==================== IS NUMERIC TESTS ====================

    @Test
    @DisplayName("Should return true for numeric string")
    void whenCheckNumericStringThenReturnTrue() {
        // Arrange
        String input = "12345";

        // Act
        boolean result = processor.isNumeric(input);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false for string with letters")
    void whenCheckAlphaNumericThenReturnFalse() {
        // Arrange
        String input = "123abc";

        // Act
        boolean result = processor.isNumeric(input);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false for string with spaces")
    void whenCheckNumericWithSpacesThenReturnFalse() {
        // Arrange
        String input = "123 456";

        // Act
        boolean result = processor.isNumeric(input);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false for null string")
    void whenCheckNullNumericThenReturnFalse() {
        // Arrange
        String input = null;

        // Act
        boolean result = processor.isNumeric(input);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false for empty string")
    void whenCheckEmptyNumericThenReturnFalse() {
        // Arrange
        String input = "";

        // Act
        boolean result = processor.isNumeric(input);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return false for negative numbers")
    void whenCheckNegativeNumberThenReturnFalse() {
        // Arrange
        String input = "-123";

        // Act
        boolean result = processor.isNumeric(input);

        // Assert
        assertFalse(result);
    }
}
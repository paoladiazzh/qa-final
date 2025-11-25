package org.example;

/**
 * A utility class for string processing operations.
 * Demonstrates testing with String objects and null handling.
 */
public class StringProcessor {

    /**
     * Reverses a string
     * @param input the string to reverse
     * @return reversed string
     * @throws IllegalArgumentException if input is null
     */
    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Checks if a string is a palindrome
     * @param input the string to check
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");
        return cleaned.contentEquals(new StringBuilder(cleaned).reverse());
    }

    /**
     * Counts the number of vowels in a string
     * @param input the string to analyze
     * @return number of vowels (a, e, i, o, u)
     */
    public int countVowels(String input) {
        if (input == null) {
            return 0;
        }
        int count = 0;
        String vowels = "aeiouAEIOU";
        for (char c : input.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    /**
     * Capitalizes the first letter of each word
     * @param input the string to capitalize
     * @return string with each word capitalized
     */
    public String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                result.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    result.append(words[i].substring(1).toLowerCase());
                }
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    /**
     * Checks if a string contains only digits
     * @param input the string to check
     * @return true if string contains only digits, false otherwise
     */
    public boolean isNumeric(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("\\d+");
    }
}

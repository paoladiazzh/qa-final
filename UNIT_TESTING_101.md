# Unit Testing 101: A Beginner's Guide

## What is Unit Testing?

Unit testing is a software testing method where individual units (components) of source code are tested in isolation to determine if they work correctly. A "unit" is the smallest testable part of an application - typically a single method or function.

### Why Unit Testing?

1. **Early Bug Detection**: Catch bugs early in the development cycle when they're cheaper to fix
2. **Documentation**: Tests serve as living documentation showing how code should behave
3. **Confidence in Changes**: Make changes knowing tests will catch breaking modifications
4. **Better Design**: Writing testable code often leads to better software design
5. **Regression Prevention**: Ensure old bugs don't reappear after fixes

---

## Key Concepts

### 1. Test Structure: AAA Pattern

Most unit tests follow the **Arrange-Act-Assert** pattern:

```java
@Test
void testAddition() {
    // Arrange: Set up test data and preconditions
    Calculator calculator = new Calculator();
    int a = 5;
    int b = 3;

    // Act: Execute the method being tested
    int result = calculator.add(a, b);

    // Assert: Verify the result is correct
    assertEquals(8, result);
}
```

### 2. Test Independence

Each test should be:
- **Independent**: Not rely on other tests
- **Isolated**: Test only one thing
- **Repeatable**: Produce same results every time
- **Self-contained**: Include all necessary setup

### 3. What Makes a Good Unit Test?

**FIRST Principles:**
- **F**ast: Tests should run quickly
- **I**ndependent: Tests shouldn't depend on each other
- **R**epeatable: Same results in any environment
- **S**elf-validating: Pass or fail, no manual verification
- **T**imely: Written alongside (or before) production code

---

## JUnit 5 Basics

### Essential Annotations

```java
@Test                    // Marks a method as a test
@BeforeEach             // Runs before each test method
@AfterEach              // Runs after each test method
@BeforeAll              // Runs once before all tests (must be static)
@AfterAll               // Runs once after all tests (must be static)
@DisplayName("...")     // Custom test name for reports
@Disabled               // Temporarily disable a test
```

### Common Assertions

```java
// Equality checks
assertEquals(expected, actual);
assertEquals(expected, actual, "Custom failure message");
assertEquals(3.14, actual, 0.001); // For floating point with delta

// Boolean checks
assertTrue(condition);
assertFalse(condition);

// Null checks
assertNull(object);
assertNotNull(object);

// Reference checks
assertSame(expected, actual);      // Same object reference
assertNotSame(expected, actual);

// Array/Collection checks
assertArrayEquals(expectedArray, actualArray);
assertIterableEquals(expected, actual);

// Exception testing
assertThrows(ExceptionType.class, () -> {
    // Code that should throw exception
});

// Multiple assertions (all run even if one fails)
assertAll(
    () -> assertEquals(expected1, actual1),
    () -> assertEquals(expected2, actual2),
    () -> assertTrue(condition)
);
```

---

## Testing Different Scenarios

### 1. Testing Normal Cases (Happy Path)

Test the expected, standard use cases:

```java
@Test
void testAddPositiveNumbers() {
    Calculator calc = new Calculator();
    assertEquals(8, calc.add(5, 3));
}
```

### 2. Testing Edge Cases

Test boundary conditions and extreme values:

```java
@Test
void testAddWithZero() {
    Calculator calc = new Calculator();
    assertEquals(5, calc.add(5, 0));
    assertEquals(0, calc.add(0, 0));
}

@Test
void testAddNegativeNumbers() {
    Calculator calc = new Calculator();
    assertEquals(-8, calc.add(-5, -3));
}
```

### 3. Testing Exception Cases

Verify code handles errors correctly:

```java
@Test
void testDivideByZeroThrowsException() {
    Calculator calc = new Calculator();

    ArithmeticException exception = assertThrows(
        ArithmeticException.class,
        () -> calc.divide(10, 0)
    );

    assertEquals("Cannot divide by zero", exception.getMessage());
}
```

### 4. Testing Null Handling

Always test how code handles null inputs:

```java
@Test
void testReverseNullThrowsException() {
    StringProcessor processor = new StringProcessor();
    assertThrows(
        IllegalArgumentException.class,
        () -> processor.reverse(null)
    );
}
```

### 5. Testing State Changes

For objects that maintain state (like BankAccount):

```java
@Test
void testDepositChangesBalance() {
    BankAccount account = new BankAccount("ACC001", 100.0);

    account.deposit(50.0);

    assertEquals(150.0, account.getBalance(), 0.001);
}
```

---

## Best Practices

### 1. Use Descriptive Test Names

```java
// Good
@Test
void shouldReturnTrueWhenNumberIsEven() { }

@Test
@DisplayName("Should throw exception when dividing by zero")
void testDivideByZero() { }

// Less clear
@Test
void test1() { }

@Test
void testCalculator() { }
```

### 2. Test One Thing Per Test

```java
// Good - focused test
@Test
void testDeposit() {
    account.deposit(50.0);
    assertEquals(150.0, account.getBalance(), 0.001);
}

// Bad - testing multiple things
@Test
void testBankAccount() {
    account.deposit(50.0);
    assertEquals(150.0, account.getBalance(), 0.001);
    account.withdraw(30.0);
    assertEquals(120.0, account.getBalance(), 0.001);
    // ... more operations
}
```

### 3. Use @BeforeEach for Common Setup

```java
class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testAdd() {
        // calculator is already initialized
        assertEquals(8, calculator.add(5, 3));
    }
}
```

### 4. Include Meaningful Assertion Messages

```java
@Test
void testWithdraw() {
    account.withdraw(30.0);
    assertEquals(70.0, account.getBalance(), 0.001,
                "Balance should be 70 after withdrawing 30 from 100");
}
```

### 5. Test Both Success and Failure Cases

```java
@Test
void shouldSucceedWithValidInput() {
    // Test normal case
}

@Test
void shouldThrowExceptionWithInvalidInput() {
    // Test error case
}
```

---

## Common Testing Patterns

### 1. Equivalence Partitioning

Group inputs with similar behavior and test one from each group:

```java
// For isEven() method, test:
@Test void testPositiveEven() { assertTrue(calc.isEven(4)); }
@Test void testPositiveOdd() { assertFalse(calc.isEven(3)); }
@Test void testNegativeEven() { assertTrue(calc.isEven(-4)); }
@Test void testNegativeOdd() { assertFalse(calc.isEven(-3)); }
@Test void testZero() { assertTrue(calc.isEven(0)); }
```

### 2. Boundary Value Analysis

Test at boundaries (min, max, just inside, just outside):

```java
@Test void testMinimumValue() { }
@Test void testMaximumValue() { }
@Test void testJustAboveMinimum() { }
@Test void testJustBelowMaximum() { }
```

### 3. State Testing

Test object state before and after operations:

```java
@Test
void testAccountStateAfterDeposit() {
    // Verify initial state
    assertEquals(100.0, account.getBalance());
    assertTrue(account.isActive());

    // Perform operation
    account.deposit(50.0);

    // Verify new state
    assertEquals(150.0, account.getBalance());
    assertTrue(account.isActive()); // Still active
}
```

---

## Running Tests

### Via Maven Command Line

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorTest

# Run specific test method
mvn test -Dtest=CalculatorTest#testAdd

# Skip tests during build
mvn install -DskipTests
```

### Via IDE

- **IntelliJ IDEA**: Right-click on test class/method → Run
- **Eclipse**: Right-click on test class/method → Run As → JUnit Test

### Understanding Test Results

```
Tests run: 45, Failures: 0, Errors: 0, Skipped: 0
```

- **Tests run**: Total number of tests executed
- **Failures**: Assertion failures (test expected X but got Y)
- **Errors**: Unexpected exceptions
- **Skipped**: Tests marked with @Disabled

---

## Test Coverage

**Test coverage** measures how much of your code is executed during tests:

- **Line Coverage**: % of code lines executed
- **Branch Coverage**: % of decision branches tested
- **Method Coverage**: % of methods called

**Guidelines:**
- Aim for 70-80% coverage minimum
- 100% coverage doesn't guarantee bug-free code
- Focus on testing critical business logic thoroughly
- Some code (getters/setters) may not need explicit tests

---

## Common Mistakes to Avoid

1. **Testing Too Much in One Test**
   - Keep tests focused on one behavior

2. **Not Testing Edge Cases**
   - Always test null, empty, zero, negative values

3. **Ignoring Exception Testing**
   - Verify your error handling works

4. **Tests Depending on Each Other**
   - Each test should run independently

5. **Not Using Descriptive Names**
   - Test names should explain what's being tested

6. **Testing Implementation Details**
   - Test behavior, not internal implementation

7. **Hard-Coding Values**
   - Use constants or clear variables for magic numbers

---

## Resources for Further Learning

- JUnit 5 User Guide: https://junit.org/junit5/docs/current/user-guide/
- Martin Fowler on Unit Testing: https://martinfowler.com/testing/
- Test-Driven Development by Kent Beck
- Clean Code by Robert C. Martin (Chapter on Unit Tests)

---

## Quick Reference Card

### Test Method Template
```java
@Test
@DisplayName("Should [expected behavior] when [condition]")
void test[MethodName][Scenario]() {
    // Arrange
    // ... setup

    // Act
    // ... execute

    // Assert
    // ... verify
}
```

### Common Assertions
```java
assertEquals(expected, actual);
assertNotEquals(unexpected, actual);
assertTrue(condition);
assertFalse(condition);
assertNull(object);
assertNotNull(object);
assertThrows(Exception.class, () -> code());
```

### Test Lifecycle
```java
@BeforeAll    // Once before all tests (static)
@BeforeEach   // Before each test
@Test         // Test method
@AfterEach    // After each test
@AfterAll     // Once after all tests (static)
```

---

Remember: **Good tests are as important as good code!**

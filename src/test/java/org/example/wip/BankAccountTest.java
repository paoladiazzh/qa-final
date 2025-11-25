package org.example.wip;

import org.example.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BankAccount Unit Tests")
public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("ACC001", 100.0);
    }

    // ==================== CONSTRUCTOR TESTS ====================

    @Test
    @DisplayName("Should create account with zero balance")
    void whenCreateAccountWithoutBalanceThenBalanceIsZero() {
        // Arrange & Act
        BankAccount newAccount = new BankAccount("ACC002");

        // Assert
        assertEquals(0.0, newAccount.getBalance(), 0.001);
        assertEquals("ACC002", newAccount.getAccountNumber());
        assertTrue(newAccount.isActive());
    }

    @Test
    @DisplayName("Should create account with initial balance")
    void whenCreateAccountWithInitialBalanceThenBalanceIsSet() {
        // Arrange & Act
        BankAccount newAccount = new BankAccount("ACC003", 500.0);

        // Assert
        assertEquals(500.0, newAccount.getBalance(), 0.001);
        assertEquals("ACC003", newAccount.getAccountNumber());
        assertTrue(newAccount.isActive());
    }

    @Test
    @DisplayName("Should throw exception for null account number")
    void whenCreateAccountWithNullNumberThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(null);
        });
    }

    @Test
    @DisplayName("Should throw exception for empty account number")
    void whenCreateAccountWithEmptyNumberThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("");
        });
    }

    @Test
    @DisplayName("Should throw exception for blank account number")
    void whenCreateAccountWithBlankNumberThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("   ");
        });
    }

    @Test
    @DisplayName("Should throw exception for negative initial balance")
    void whenCreateAccountWithNegativeBalanceThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount("ACC004", -100.0);
        });
    }

    // ==================== DEPOSIT TESTS ====================

    @Test
    @DisplayName("Should increase balance after deposit")
    void whenDepositThenBalanceIncreases() {
        // Arrange
        double initialBalance = account.getBalance();

        // Act
        account.deposit(50.0);

        // Assert
        assertEquals(initialBalance + 50.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should handle multiple deposits")
    void whenMultipleDepositsThenBalanceIncreasesCorrectly() {
        // Act
        account.deposit(25.0);
        account.deposit(75.0);

        // Assert
        assertEquals(200.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should throw exception for zero deposit")
    void whenDepositZeroThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0.0);
        });
    }

    @Test
    @DisplayName("Should throw exception for negative deposit")
    void whenDepositNegativeAmountThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-50.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when depositing to inactive account")
    void whenDepositToInactiveAccountThenThrowException() {
        // Arrange
        account.closeAccount();

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            account.deposit(50.0);
        });
    }

    // ==================== WITHDRAW TESTS ====================

    @Test
    @DisplayName("Should decrease balance after withdrawal")
    void whenWithdrawThenBalanceDecreases() {
        // Arrange
        double initialBalance = account.getBalance();

        // Act
        account.withdraw(30.0);

        // Assert
        assertEquals(initialBalance - 30.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should handle multiple withdrawals")
    void whenMultipleWithdrawalsThenBalanceDecreasesCorrectly() {
        // Act
        account.withdraw(20.0);
        account.withdraw(30.0);

        // Assert
        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should throw exception for zero withdrawal")
    void whenWithdrawZeroThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0.0);
        });
    }

    @Test
    @DisplayName("Should throw exception for negative withdrawal")
    void whenWithdrawNegativeAmountThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-50.0);
        });
    }

    @Test
    @DisplayName("Should throw exception for insufficient funds")
    void whenWithdrawMoreThanBalanceThenThrowException() {
        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(200.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when withdrawing from inactive account")
    void whenWithdrawFromInactiveAccountThenThrowException() {
        // Arrange
        account.closeAccount();

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            account.withdraw(50.0);
        });
    }

    // ==================== ACCOUNT STATUS TESTS ====================

    @Test
    @DisplayName("Should be active after creation")
    void whenAccountCreatedThenIsActive() {
        // Assert
        assertTrue(account.isActive());
    }

    @Test
    @DisplayName("Should be inactive after closing")
    void whenAccountClosedThenIsInactive() {
        // Act
        account.closeAccount();

        // Assert
        assertFalse(account.isActive());
    }

    @Test
    @DisplayName("Should maintain balance after closing")
    void whenAccountClosedThenBalanceRemains() {
        // Arrange
        double balanceBeforeClose = account.getBalance();

        // Act
        account.closeAccount();

        // Assert
        assertEquals(balanceBeforeClose, account.getBalance(), 0.001);
    }

    // ==================== GETTERS TESTS ====================

    @Test
    @DisplayName("Should return correct account number")
    void whenGetAccountNumberThenReturnCorrectNumber() {
        // Act
        String accountNumber = account.getAccountNumber();

        // Assert
        assertEquals("ACC001", accountNumber);
    }

    @Test
    @DisplayName("Should return correct balance")
    void whenGetBalanceThenReturnCorrectBalance() {
        // Act
        double balance = account.getBalance();

        // Assert
        assertEquals(100.0, balance, 0.001);
    }

    // ==================== TRANSFER TESTS ====================

    @Test
    @DisplayName("Should transfer money between accounts")
    void whenTransferThenBothBalancesUpdate() {
        // Arrange
        BankAccount targetAccount = new BankAccount("ACC002", 50.0);

        // Act
        account.transfer(targetAccount, 30.0);

        // Assert
        assertEquals(70.0, account.getBalance(), 0.001);
        assertEquals(80.0, targetAccount.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should throw exception when transferring to null account")
    void whenTransferToNullAccountThenThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(null, 50.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when transferring from inactive account")
    void whenTransferFromInactiveAccountThenThrowException() {
        // Arrange
        BankAccount targetAccount = new BankAccount("ACC002", 50.0);
        account.closeAccount();

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            account.transfer(targetAccount, 30.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when transferring to inactive account")
    void whenTransferToInactiveAccountThenThrowException() {
        // Arrange
        BankAccount targetAccount = new BankAccount("ACC002", 50.0);
        targetAccount.closeAccount();

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            account.transfer(targetAccount, 30.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when transferring more than balance")
    void whenTransferMoreThanBalanceThenThrowException() {
        // Arrange
        BankAccount targetAccount = new BankAccount("ACC002", 50.0);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            account.transfer(targetAccount, 200.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when transferring zero amount")
    void whenTransferZeroThenThrowException() {
        // Arrange
        BankAccount targetAccount = new BankAccount("ACC002", 50.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(targetAccount, 0.0);
        });
    }

    @Test
    @DisplayName("Should throw exception when transferring negative amount")
    void whenTransferNegativeAmountThenThrowException() {
        // Arrange
        BankAccount targetAccount = new BankAccount("ACC002", 50.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(targetAccount, -50.0);
        });
    }

    // ==================== INTEGRATION TESTS ====================

    @Test
    @DisplayName("Should handle deposit and withdraw operations in sequence")
    void whenDepositAndWithdrawThenBalanceUpdatesCorrectly() {
        // Act
        account.deposit(100.0);  // 200.0
        account.withdraw(50.0);  // 150.0
        account.deposit(25.0);   // 175.0
        account.withdraw(75.0);  // 100.0

        // Assert
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should verify account state remains consistent")
    void whenPerformingMultipleOperationsThenStateIsConsistent() {
        // Arrange
        String originalAccountNumber = account.getAccountNumber();

        // Act
        account.deposit(50.0);
        account.withdraw(30.0);

        // Assert
        assertEquals(120.0, account.getBalance(), 0.001);
        assertEquals(originalAccountNumber, account.getAccountNumber());
        assertTrue(account.isActive());
    }
}
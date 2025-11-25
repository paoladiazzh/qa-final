package org.example;

/**
 * A simple bank account class for demonstrating stateful testing.
 * Shows how to test methods that modify object state.
 */
public class BankAccount {
    private double balance;
    private final String accountNumber;
    private boolean isActive;

    /**
     * Creates a new bank account with zero balance
     * @param accountNumber the account number
     */
    public BankAccount(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.isActive = true;
    }

    /**
     * Creates a new bank account with an initial balance
     * @param accountNumber the account number
     * @param initialBalance the initial balance
     */
    public BankAccount(String accountNumber, double initialBalance) {
        this(accountNumber);
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = initialBalance;
    }

    /**
     * Deposits money into the account
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if amount is negative or zero
     * @throws IllegalStateException if account is not active
     */
    public void deposit(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Cannot deposit to inactive account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    /**
     * Withdraws money from the account
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if amount is negative or zero
     * @throws IllegalStateException if insufficient funds or account is not active
     */
    public void withdraw(double amount) {
        if (!isActive) {
            throw new IllegalStateException("Cannot withdraw from inactive account");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }

    /**
     * Gets the current balance
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account number
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Checks if the account is active
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Closes the account
     */
    public void closeAccount() {
        isActive = false;
    }

    /**
     * Nice to have
     * Transfers money to another account
     * @param targetAccount the account to transfer to
     * @param amount the amount to transfer
     * @throws IllegalArgumentException if target account is null or amount is invalid
     * @throws IllegalStateException if insufficient funds or either account is inactive
     */
    public void transfer(BankAccount targetAccount, double amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if (!this.isActive) {
            throw new IllegalStateException("Cannot transfer from inactive account");
        }
        if (!targetAccount.isActive()) {
            throw new IllegalStateException("Cannot transfer to inactive account");
        }

        // Withdraw from this account
        this.withdraw(amount);

        // Deposit to target account
        targetAccount.deposit(amount);
    }
}

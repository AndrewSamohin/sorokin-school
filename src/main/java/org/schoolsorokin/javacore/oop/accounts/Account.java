package org.schoolsorokin.javacore.oop.accounts;

import java.util.Random;

public abstract class Account {

    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public abstract void withdraw(double amount);

}

class SavingAccount extends Account implements Printable {

    public SavingAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (getBalance() - amount < 0) {
            System.out.println("Insufficient funds. Cannot go below 0.");
            return;
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public void printInfo() {
        System.out.println("Account type: Saving" + "\nBalance: " + getBalance());
    }
}

class CreditAccount extends Account implements Printable {
    private double creditLimit = -100.0;

    public CreditAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return;
        }
        if (getBalance() - amount < creditLimit) {
            System.out.println("Credit limit exceeded.");
            return;
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public void printInfo() {
        System.out.println("Account type: Credit" + "\nBalance: ");
    }
}
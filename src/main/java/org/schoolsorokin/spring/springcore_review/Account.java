package org.schoolsorokin.spring.springcore_review;

public class Account {

    private final int accountId;
    private final int userId;
    private int defaultAmount;

    public Account(int accountId, int userId, int defaultAmount) {
        this.accountId = accountId;
        this.userId = userId;
        this.defaultAmount = defaultAmount;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(int defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + accountId +
                ", userId=" + userId +
                ", moneyAmount=" + defaultAmount +
                '}';
    }
}

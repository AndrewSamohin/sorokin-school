package org.schoolsorokin.spring.springcore_review;

import java.math.BigDecimal;

public class Account {

    private final int accountId;
    private final int userId;
    private BigDecimal defaultAmount;

    public Account(int accountId, int userId, BigDecimal defaultAmount) {
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

    public BigDecimal getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(BigDecimal defaultAmount) {
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

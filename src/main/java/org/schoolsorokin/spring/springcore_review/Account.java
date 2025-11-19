package org.schoolsorokin.spring.springcore_review;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "amount")
    private BigDecimal defaultAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Account(int accountId, BigDecimal defaultAmount) {
        this.accountId = accountId;
        this.defaultAmount = defaultAmount;
    }

    public Account() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAccountId() {
        return accountId;
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
                ", userId=" + user.getUserId() +
                ", moneyAmount=" + defaultAmount +
                '}';
    }
}

package org.schoolsorokin.spring.springcore_review;

import java.util.List;

public class User {

    private final int userId;
    private final String login;
    private final List<Account> accountList;

    public User(
            int userId,
            String login,
            List<Account> accountList
    ) {
        this.userId = userId;
        this.login = login;
        this.accountList = accountList;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}

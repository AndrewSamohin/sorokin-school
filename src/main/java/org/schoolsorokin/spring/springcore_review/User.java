package org.schoolsorokin.spring.springcore_review;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Account> accountList;

    public User(
            Long userId,
            String login,
            List<Account> accountList
    ) {
        this.userId = userId;
        this.login = login;
        this.accountList = accountList;
    }

    public User() {

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getUserId() {
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

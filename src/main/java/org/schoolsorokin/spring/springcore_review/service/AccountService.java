package org.schoolsorokin.spring.springcore_review.service;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.AccountProperties;
import org.schoolsorokin.spring.springcore_review.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private int idCounter = 0;
    private final List<Account> accounts = new ArrayList<>();
    private final UserService userService;
    private final AccountProperties accountProperties;

    public AccountService(UserService userService, AccountProperties accountProperties) {
        this.userService = userService;
        this.accountProperties = accountProperties;
    }

    //Создание счета
    public Account createAccount(int userId) {
        Optional<User> optionalUser = userService.searchUser(userId);
        //Проверка на наличие пользователя с таким ID
        if (optionalUser.isEmpty()) {
            System.out.println("User with ID " + userId + " not found.");
            return null;
        }

        idCounter++;
        User user = optionalUser.get();
        int defaultAmount = accountProperties.getDefaultAmount();
        Account account = new Account(idCounter, user.getUserId(), defaultAmount);

        user.getAccountList().add(account);
        accounts.add(account);

        return account;
    }

    //Пополнение счета
    public void deposit(int accountId, int amount) {
        Account account = findById(accountId);
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        account.setDefaultAmount(account.getDefaultAmount() + amount);
    }

    //Снятие средств со счета
    public void withdrawn(int accountId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }

        Account account = findById(accountId);
        if (account.getDefaultAmount() < amount) {
            throw new IllegalArgumentException(
                    "Not enough funds on account: id=" + accountId +
                    ", current balance=" + account.getDefaultAmount());
        }

        account.setDefaultAmount(account.getDefaultAmount() - amount);
    }

    //Перевод средств между счетами
    public void transfer(int fromAccountId, int toAccountId, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }

        Account fromAccount = findById(fromAccountId);
        Account toAccount = findById(toAccountId);

        if (fromAccount.getDefaultAmount() < amount) {
            throw new IllegalArgumentException("Not enough funds on account " + fromAccountId);
        }

        fromAccount.setDefaultAmount(fromAccount.getDefaultAmount() - amount);
        //Если разные пользователи принимаем комиссию
        if (fromAccount.getUserId() != toAccount.getUserId()) {
            int afterCommission = (int) (amount * (1 - accountProperties.getTransferCommission()));
            toAccount.setDefaultAmount(toAccount.getDefaultAmount() + afterCommission);
        } else  {
            toAccount.setDefaultAmount(toAccount.getDefaultAmount() + amount);
        }
    }

    //Закрытие счета
    public void closeAccount(int accountId) {
        Account account = findById(accountId);
        User user = userService.searchUser(account.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found for account ID " + accountId));

        List<Account> accountList = user.getAccountList();

        if (accountList.size() <= 1) {
            throw new IllegalArgumentException(
                    "Cannot close the only account of user: " + user.getLogin()
            );
        }

        //Выбираем другой счет для перевода остатка
        Account target = accountList.stream()
                .filter(acc -> acc.getAccountId() != accountId)
                .findFirst()
                .orElseThrow(() -> new
                        IllegalArgumentException("No alternative account found."));

        int balance = account.getDefaultAmount();
        if (balance > 0) {
            target.setDefaultAmount(target.getDefaultAmount() + balance);
        }

        accountList.remove(account);
        accounts.remove(account);
    }

    //Найти счет по ID
    public Account findById(int userId) {
        return accounts.stream()
                .filter(account -> account.getAccountId() == userId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Account not found with id: " + userId
                ));
    }
}

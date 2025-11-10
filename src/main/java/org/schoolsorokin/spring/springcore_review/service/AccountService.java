package org.schoolsorokin.spring.springcore_review.service;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.AccountProperties;
import org.schoolsorokin.spring.springcore_review.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class AccountService {

    private int idCounter = 0;
    private final List<Account> accounts = new ArrayList<>();
    private final UserService userService;
    private final AccountProperties accountProperties;
    private final Logger log = Logger.getLogger(AccountService.class.getName());

    public AccountService(
            UserService userService,
            AccountProperties accountProperties
    ) {
        this.userService = userService;
        this.accountProperties = accountProperties;
    }

    //Создание счета
    public Account createAccount(int userId) {
        log.info("Attempting to create account for user ID: " + userId);

        User user = userService.searchUser(userId)
                .orElseThrow(() -> {
                    log.warning("User with ID " + userId + " not found.");
                    return new NoSuchElementException(
                            "User with ID " + userId + " not found.");
                });

        idCounter++;
        BigDecimal defaultAmount = BigDecimal.valueOf(
                accountProperties.getDefaultAmount());
        Account account = new Account(idCounter, user.getUserId(), defaultAmount);

        user.getAccountList().add(account);
        accounts.add(account);

        log.info("Created account ID: " + account.getAccountId()
                + ", for user '" + user.getLogin()
                + "' with default amount: " + defaultAmount);

        return account;
    }

    //Пополнение счета
    public void deposit(int accountId, BigDecimal amount) {
        log.info("Attempting to deposit " + amount + " to account ID: " + accountId);

        Account account = findById(accountId);

        account.setDefaultAmount(account.getDefaultAmount().add(amount));
        log.info("Deposit successful. New balance for account ID: "
                + accountId + ": " + account.getDefaultAmount());
    }

    //Снятие средств со счета
    public void withdraw(int accountId, BigDecimal amount) {
        log.info("Attempting to withdraw " + amount + " from account ID: " + accountId);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("Withdraw amount must be positive. Provided: " + amount);
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }

        Account account = findById(accountId);
        if (account.getDefaultAmount().compareTo(amount) < 0) {
            log.warning("Not enough funds on account ID: " + accountId
                    + ". Current balance: " + account.getDefaultAmount());
            throw new IllegalArgumentException(
                    "Not enough funds on account: id=" + accountId +
                    ", current balance=" + account.getDefaultAmount());
        }

        account.setDefaultAmount(account.getDefaultAmount().subtract(amount));
        log.info("Withdraw successful. New balance for account ID: "
                + accountId + ": " + account.getDefaultAmount());
    }

    //Перевод средств между счетами
    public void transfer(int fromAccountId, int toAccountId, BigDecimal amount) {
        log.info("Attempting to transfer " + amount
                + " from account ID: " + fromAccountId
                + ", to account ID: " + toAccountId);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("Transfer amount must be positive. Provided: " + amount);
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }

        Account fromAccount = findById(fromAccountId);
        Account toAccount = findById(toAccountId);

        if (fromAccount.getDefaultAmount().compareTo(amount) < 0) {
            log.warning("Not enough funds on account ID: " + fromAccountId
                    + ". Current balance: " + fromAccount.getDefaultAmount());
            throw new IllegalArgumentException("Not enough funds on account " + fromAccountId);
        }

        //Списываем средства
        fromAccount.setDefaultAmount(fromAccount.getDefaultAmount().subtract(amount));

        //Если разные пользователи принимаем комиссию
        if (fromAccount.getUserId() != toAccount.getUserId()) {
            BigDecimal commission = BigDecimal.valueOf(
                    accountProperties.getTransferCommission());
            BigDecimal afterCommission = amount.multiply(
                    BigDecimal.ONE.subtract(commission));

            toAccount.setDefaultAmount(toAccount.getDefaultAmount().add(afterCommission));

            log.info("Transfer completed with commission. "
                    + "Sent: " + amount
                    + ", received: " + afterCommission
                    + ", commission: " + amount.subtract(afterCommission));
        } else  {
            toAccount.setDefaultAmount(toAccount.getDefaultAmount().add(amount));
            log.info("Transfer completed (no commission). Amount: " + amount);
        }

        log.info("New balances: FromAccount: " + fromAccount.getDefaultAmount()
                + ", ToAccount: " + toAccount.getDefaultAmount());
    }

    //Закрытие счета
    public void closeAccount(int accountId) {
        log.info("Attempting to close account ID: " + accountId);

        Account account = findById(accountId);
        User user = userService.searchUser(account.getUserId())
                .orElseThrow(() -> {
                    log.warning("User not found for account ID " + accountId);
                    return new IllegalArgumentException(
                            "User not found for account ID " + accountId);
                });

        List<Account> accountList = user.getAccountList();

        if (accountList.size() <= 1) {
            log.warning("Cannot close the only account of user: " + user.getLogin());
            throw new IllegalArgumentException(
                    "Cannot close the only account of user: " + user.getLogin()
            );
        }

        //Выбираем другой счет для перевода остатка
        Account target = accountList.stream()
                .filter(acc -> acc.getAccountId() != accountId)
                .findFirst()
                .orElseThrow(() -> {
                    log.warning("No alternative account found for transferring funds.");
                    return new IllegalArgumentException("No alternative account found.");
                });

        BigDecimal balance = account.getDefaultAmount();
        if (balance.compareTo(BigDecimal.ZERO) > 0) {
            target.setDefaultAmount(target.getDefaultAmount().add(balance));
            log.info("Transferred remaining balance '"
                    + balance + "' to account ID="
                    + target.getAccountId());
        }

        accountList.remove(account);
        accounts.remove(account);
        log.info("Account ID: " + accountId + ", successfully closed.");
    }

    //Найти счет по ID
    public Account findById(int accountId) {
        return accounts.stream()
                .filter(account -> account.getAccountId() == accountId)
                .findFirst()
                .orElseThrow(() -> {
                    log.warning("Account not found with ID: " + accountId);
                    return new IllegalArgumentException(
                            "Account not found with id: " + accountId
                    );
                });
    }
}

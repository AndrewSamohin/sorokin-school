package org.schoolsorokin.spring.springcore_review.service;

import jakarta.transaction.TransactionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.AccountProperties;
import org.schoolsorokin.spring.springcore_review.TransactionHelper;
import org.schoolsorokin.spring.springcore_review.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class AccountService {

    private final UserService userService;
    private final AccountProperties accountProperties;
    private final Logger log = Logger.getLogger(AccountService.class.getName());

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public AccountService(
            UserService userService,
            AccountProperties accountProperties,
            SessionFactory sessionFactory,
            TransactionHelper transactionHelper) {
        this.userService = userService;
        this.accountProperties = accountProperties;
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
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

        Account account = new Account();
        account.setUser(user);
        account.setDefaultAmount(
                BigDecimal.valueOf(accountProperties.getDefaultAmount())
        );

        transactionHelper.executeInTransactionVoid(session -> {
            session.persist(account);
            user.getAccountList().add(account);
        });

        log.info("Created account ID: " + account.getAccountId()
                + ", for user '" + user.getLogin()
                + "' with default amount: " + account.getDefaultAmount());

        return account;
    }

    //Пополнение счета
    public void deposit(int accountId, BigDecimal amount) {
        log.info("Attempting to deposit " + amount + " to account ID: " + accountId);

        transactionHelper.executeInTransactionVoid(session -> {
            Account account = session.find(Account.class, accountId);
            if (account == null)
                throw new IllegalArgumentException("Account not found");

            account.setDefaultAmount(account.getDefaultAmount().add(amount));
        });

        log.info("Deposit successful. New balance for account ID: "
                + accountId + ": " + amount);
    }

    //Снятие средств со счета
    public void withdraw(int accountId, BigDecimal amount) {
        log.info("Attempting to withdraw " + amount + " from account ID: " + accountId);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("Withdraw amount must be positive. Provided: " + amount);
            throw new IllegalArgumentException("Withdraw amount must be positive.");
        }

        transactionHelper.executeInTransactionVoid(session -> {
            Account account = session.find(Account.class, accountId);

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
        });
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

        transactionHelper.executeInTransactionVoid(session -> {
            Account fromAccount = session.find(Account.class, fromAccountId);
            Account toAccount = session.find(Account.class, toAccountId);

            if (fromAccountId == toAccountId) {
                throw new IllegalArgumentException("Cannot transfer to the same account");
            }

            if (fromAccount.getDefaultAmount().compareTo(amount) < 0) {
                log.warning("Not enough funds on account ID: " + fromAccountId
                        + ". Current balance: " + fromAccount.getDefaultAmount());
                throw new IllegalArgumentException("Not enough funds on account " + fromAccountId);
            }

            //Списываем средства
            fromAccount.setDefaultAmount(fromAccount.getDefaultAmount().subtract(amount));

            //Если разные пользователи принимаем комиссию
            if (fromAccount.getUser().getUserId() != toAccount.getUser().getUserId()) {
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
        });
    }

    //Закрытие счета
    public void closeAccount(int accountId) {
        log.info("Attempting to close account ID: " + accountId);

        transactionHelper.executeInTransactionVoid(session -> {
            Account account = session.find(Account.class, accountId);
            User user = account.getUser();

            if (user == null) {
                log.warning("User not found for account ID " + accountId);
                throw new IllegalArgumentException("User not found for account ID " + accountId);
            }

            if (user.getAccountList().size() <= 1) {
                log.warning("Cannot close the only account of user: " + user.getLogin());
                throw new IllegalArgumentException(
                        "Cannot close the only account of user: " + user.getLogin()
                );
            }

            //Выбираем другой счет для перевода остатка
            Account target = user.getAccountList().stream()
                    .filter(acc -> acc.getAccountId() != accountId)
                    .findFirst()
                    .orElseThrow(() -> {
                        log.warning("No alternative account found for transferring funds.");
                        return new IllegalArgumentException("No alternative account found.");
                    });

            if (account.getDefaultAmount().compareTo(BigDecimal.ZERO) > 0) {
                target.setDefaultAmount(target
                        .getDefaultAmount()
                        .add(account.getDefaultAmount()));
                log.info("Transferred remaining balance to account ID="
                        + target.getAccountId());
            }

            session.remove(account);
            log.info("Account ID: " + accountId + ", successfully closed.");
        });
    }

    //Найти счет по ID
    public Account findById(int accountId) {
        return transactionHelper.executeInTransaction(session -> {
            Account acc = session.find(Account.class, accountId);
            if (acc == null) {
                log.warning("Account not found with ID: " + accountId);
                throw new IllegalArgumentException("Account not found with ID: " + accountId);
            }
            return acc;
        });
    }
}

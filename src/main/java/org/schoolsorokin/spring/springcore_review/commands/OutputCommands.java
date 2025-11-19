package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OutputCommands {

    public void UserCrateOutPut(User user) {
        System.out.println("User created: " + user);
    }

    public void ShowAllUsersOutPut(List<User> users) {
        System.out.println("List of all users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void AccountCrateOutPut(Account account) {
        System.out.println("New account created with ID: "
                + account.getAccountId()
                + " for user: " + account.getUser().getUserId());
    }

    public void AccountCloseOutPut(int accountId) {
        System.out.println("Account with ID " + accountId + " has been closed.");
    }

    public void AccountWithdrawOutPut(BigDecimal amount, int accountId) {
        System.out.println("Amount " + amount + " withdrawn from account ID: " + accountId);
    }

    public void AccountDepositOutPut(BigDecimal amount, int accountId) {
        System.out.println("Amount " + amount + " deposited to account ID: " + accountId);
    }

    public void AccountTransferOutPut(BigDecimal amount, int fromId, int toId) {
        System.out.printf("Amount %s transferred from account ID %s to account ID %s.\n",
                amount, fromId, toId);
    }
}

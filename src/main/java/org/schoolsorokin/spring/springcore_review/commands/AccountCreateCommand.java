package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.User;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.schoolsorokin.spring.springcore_review.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

//Создание нового счета для пользователя.
@Component
public class AccountCreateCommand implements OperationCommand {
    private final UserService userService;
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public AccountCreateCommand(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("Enter the user id for which to create an account: ");
        int userId = Integer.parseInt(sc.nextLine());

        Optional<User> userOpt = userService.searchUser(userId);
        if (userOpt.isEmpty()) {
            System.out.println("User with ID " + userId + " not found.");
            return;
        }

        User user = userOpt.get();

        Account account = accountService.createAccount(userId);

        System.out.println("New account created with ID: "
                + account.getAccountId()
                + " for user: " + user.getLogin());
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}

package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.User;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.schoolsorokin.spring.springcore_review.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//Создание нового пользователя.
@Component
public class UserCreateCommand implements OperationCommand {
    private final UserService userService;
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public UserCreateCommand(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter login for new user: ");
        String login = sc.nextLine();

        User user = userService.createUser(login);

        Account account = accountService.createAccount(user.getUserId());

        System.out.println("User crated: " + user);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }
}

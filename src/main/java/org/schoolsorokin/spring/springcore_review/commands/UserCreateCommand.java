package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.User;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.schoolsorokin.spring.springcore_review.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

//Создание нового пользователя.
@Component
public class UserCreateCommand implements OperationCommand {

    private final OutputCommands outputCommands;
    private final UserService userService;
    private final AccountService accountService;
    private final Scanner sc;
    private static final Logger log = Logger.getLogger(UserCreateCommand.class.getName());

    public UserCreateCommand(
            UserService userService,
            AccountService accountService,
            OutputCommands outputCommands,
            Scanner sc
    ) {
        this.userService = userService;
        this.accountService = accountService;
        this.outputCommands = outputCommands;
        this.sc = sc;
    }

    @Override
    public void execute() {
        log.info("Executing USER_CREATE command.");

        System.out.println("Enter login for new user: ");
        String login = sc.nextLine();

        User user = userService.createUser(login);
        Account account = accountService.createAccount(user.getUserId());

        log.info("User created successfully: " + user.getLogin()
                + ", account attached: " + account.getAccountId());
        outputCommands.UserCrateOutPut(user);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.USER_CREATE;
    }

}

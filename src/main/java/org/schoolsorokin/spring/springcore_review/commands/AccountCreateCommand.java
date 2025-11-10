package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.Account;
import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Создание нового счета для пользователя.
 * @since 10.11.2025
 * @author Samohin Andrew
 */
@Component
public class AccountCreateCommand implements OperationCommand {

    private static final Logger log = Logger.getLogger(AccountCreateCommand.class.getName());
    private final OutputCommands outputCommands;
    private final AccountService accountService;
    private final Scanner sc;

    public AccountCreateCommand(
            AccountService accountService,
            OutputCommands outputCommands,
            Scanner sc
    ) {
        this.accountService = accountService;
        this.outputCommands = outputCommands;
        this.sc = sc;
    }

    @Override
    public void execute() {
        log.info("Executing ACCOUNT_CREATE command.");

        try {
            System.out.println("Enter the user id for which to create an account: ");
            int userId = Integer.parseInt(sc.nextLine());

            Account account = accountService.createAccount(userId);
            outputCommands.AccountCrateOutPut(account);

            log.info("Account successfully created for user ID=" + userId);
        } catch (NumberFormatException e) {
            log.warning("Invalid number format during account creation.");
            System.out.println("Invalid input. Please enter a valid number for user ID.");
        } catch (NoSuchElementException e) {
            log.warning("Account creation failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}

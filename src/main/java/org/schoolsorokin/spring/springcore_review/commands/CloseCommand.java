package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//Закрытие счета.
@Component
public class CloseCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public CloseCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter account ID to close: ");
            int accountId = Integer.parseInt(sc.nextLine());

            accountService.closeAccount(accountId);

            System.out.println("Account with ID " + accountId + " has been closed.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}

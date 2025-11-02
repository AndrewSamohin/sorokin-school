package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//Перевод средств между счетами.
@Component
public class TransferCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public TransferCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter source account ID: ");
            int fromId = Integer.parseInt(sc.nextLine());

            System.out.println("Enter target account ID: ");
            int toId = Integer.parseInt(sc.nextLine());

            System.out.println("Enter amount to transfer: ");
            int amount = Integer.parseInt(sc.nextLine());

            accountService.transfer(fromId, toId, amount);

            System.out.printf("Amount %s transferred from account ID %s to account ID %s.\n",
                    amount, fromId, toId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}

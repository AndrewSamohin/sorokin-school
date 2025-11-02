package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//Запрашивает ID счета и сумму. Снимает указанную сумму со счета.
@Component
public class WithdrawCommand implements OperationCommand {

    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public WithdrawCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter account ID to withdraw from: ");
            int accountId = Integer.parseInt(sc.nextLine());

            System.out.println("Enter amount to withdraw: ");
            int amount = Integer.parseInt(sc.nextLine());

            accountService.withdrawn(accountId, amount);

            System.out.println("Amount " + amount + " withdrawn from account ID: " + accountId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}

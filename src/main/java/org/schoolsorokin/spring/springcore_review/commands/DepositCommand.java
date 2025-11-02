package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DepositCommand implements OperationCommand{
    private final AccountService accountService;
    private final Scanner sc = new Scanner(System.in);

    public DepositCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void execute() {
        System.out.println("Enter account ID: ");
        int accountId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter amount to deposit: ");
        int amount = Integer.parseInt(sc.nextLine());

        accountService.deposit(accountId, amount);

        System.out.println("Amount " + amount + " deposited to account ID: " + accountId);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}

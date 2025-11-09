package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class DepositCommand implements OperationCommand{

    private static final Logger log = Logger.getLogger(DepositCommand.class.getName());
    private final OutputCommands outputCommands;
    private final AccountService accountService;
    private Scanner sc;

    public DepositCommand(
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
        log.info("Executing ACCOUNT_DEPOSIT command.");

        System.out.println("Enter account ID: ");
        int accountId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter amount to deposit: ");
        BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(sc.nextLine()));

        accountService.deposit(accountId, amount);
        outputCommands.AccountDepositOutPut(amount, accountId);

        log.info("Deposit successful for account ID="
                + accountId + ", amount=" + amount);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}

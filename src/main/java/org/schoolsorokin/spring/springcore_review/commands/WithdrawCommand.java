package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Запрашивает ID счета и сумму.
 * Снимает указанную сумму со счета.
 * @since 10.11.2025
 * @author Samohin Andrew
 */
@Component
public class WithdrawCommand implements OperationCommand {

    private static final Logger log = Logger.getLogger(WithdrawCommand.class.getName());
    private final OutputCommands outputCommands;
    private final AccountService accountService;
    private final Scanner sc;

    public WithdrawCommand(
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
        log.info("Executing ACCOUNT_WITHDRAW command.");

        try {
            System.out.println("Enter account ID to withdraw from: ");
            int accountId = Integer.parseInt(sc.nextLine());

            System.out.println("Enter amount to withdraw: ");
            BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(sc.nextLine()));

            accountService.withdraw(accountId, amount);
            outputCommands.AccountWithdrawOutPut(amount, accountId);

            log.info("Withdrawal successful for account ID="
                    + accountId + ", amount=" + amount);
        } catch (NumberFormatException e) {
            log.warning("Invalid number format entered during withdrawal.");
            System.out.println("Invalid number format. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            log.warning("Withdrawal failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }
}
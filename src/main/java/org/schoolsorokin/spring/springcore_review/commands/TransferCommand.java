package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Перевод средств между счетами.
 * @since 10.11.2025
 * @author Samohin Andrew
 */
@Component
public class TransferCommand implements OperationCommand {

    private static final Logger log = Logger.getLogger(TransferCommand.class.getName());
    private final OutputCommands outputCommands;
    private final AccountService accountService;
    private Scanner sc;

    public TransferCommand(
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
        log.info("Executing ACCOUNT_TRANSFER command.");

        try {
            System.out.println("Enter source account ID: ");
            int fromId = Integer.parseInt(sc.nextLine());

            System.out.println("Enter target account ID: ");
            int toId = Integer.parseInt(sc.nextLine());

            // Проверка на одинаковые ID
            if (fromId == toId) {
                log.warning("Transfer failed: account ID are the same (" + fromId + ").");
                throw new IllegalArgumentException("Account ID cannot be the same.");
            }

            System.out.println("Enter amount to transfer: ");
            BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(sc.nextLine()));

            // Проверка, что сумма больше нуля
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                log.warning("Transfer failed: entered non-positive amount (" + amount + ").");
                throw new IllegalArgumentException("Transfer amount must be greater than zero.");
            }

            accountService.transfer(fromId, toId, amount);
            outputCommands.AccountTransferOutPut(amount, fromId, toId);

            log.info("Transfer successful: " + amount + " from "
                    + fromId + " to " + toId);
        } catch (NumberFormatException e) {
            log.warning("Invalid number format during transfer.");
            System.out.println("Invalid number format. Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            log.warning("Transfer failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }
}

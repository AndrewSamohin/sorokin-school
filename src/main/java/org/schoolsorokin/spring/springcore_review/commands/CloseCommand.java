package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Закрытие счета.
 * @since 10.11.2025
 * @author Samohin Andrew
 */
@Component
public class CloseCommand implements OperationCommand {

    private static final Logger log = Logger.getLogger(CloseCommand.class.getName());
    private final OutputCommands outputCommands;
    private final AccountService accountService;
    private Scanner sc;

    public CloseCommand(
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
        log.info("Executing ACCOUNT_CLOSE command.");

        try {
            System.out.println("Enter account ID to close: ");
            int accountId = Integer.parseInt(sc.nextLine());

            accountService.closeAccount(accountId);
            outputCommands.AccountCloseOutPut(accountId);

            log.info("Account with ID=" + accountId + " closed successfully.");
        } catch (NumberFormatException e) {
            log.warning("Invalid number format during account closing attempt.");
            System.out.println("Invalid number format.");
        } catch (IllegalArgumentException e) {
            log.warning("Account closing failed: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}

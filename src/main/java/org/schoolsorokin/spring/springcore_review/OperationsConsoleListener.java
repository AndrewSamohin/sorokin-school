package org.schoolsorokin.spring.springcore_review;

import org.schoolsorokin.spring.springcore_review.commands.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class OperationsConsoleListener implements Runnable {

    private static final Logger log = Logger.getLogger(OperationsConsoleListener.class.getName());
    private final Map<ConsoleOperationType, OperationCommand> commands = new
           EnumMap<>(ConsoleOperationType.class);
   private final Scanner sc = new Scanner(System.in);

    private static final String EXIT_COMMAND = "EXIT";

   public OperationsConsoleListener(List<OperationCommand> commandList) {
       for (OperationCommand command : commandList) {
           commands.put(command.getOperationType(), command);
       }
   }

   @Override
   public void run() {
        while (true) {
            log.info("Waiting for user command input.");

            System.out.println("Please enter one of operation type: ");
            for (ConsoleOperationType type : ConsoleOperationType.values()) {
                System.out.println("-" + type);
            }

            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase(EXIT_COMMAND)) {
                log.info("Application exiting.");
                System.out.println("Program exiting.");
                break;
            }

            try {
                ConsoleOperationType operationType = ConsoleOperationType
                        .valueOf(input.toUpperCase());
                OperationCommand command = commands.get(operationType);

                if (command == null) {
                    log.warning("Unknown command entered: " + input);
                    System.out.println("Unknown command: " + input);
                    continue;
                }

                log.info("Executing command: " + operationType);
                command.execute();
            } catch (IllegalArgumentException e) {
                log.warning("Invalid command input: " + input);
                System.out.println("Invalid command: " + input);
            } catch (Exception e) {
                log.severe("Error executing command: " + e.getMessage());
                System.out.println("Error executing command: " + e.getMessage()); 
            }
        }
   }
}

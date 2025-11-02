package org.schoolsorokin.spring.springcore_review;

import org.schoolsorokin.spring.springcore_review.commands.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationsConsoleListener implements Runnable {

   private final Map<ConsoleOperationType, OperationCommand> commands = new
           EnumMap<>(ConsoleOperationType.class);
   private final Scanner sc = new Scanner(System.in);

   public OperationsConsoleListener(List<OperationCommand> commandList) {
       for (OperationCommand command : commandList) {
           commands.put(command.getOperationType(), command);
       }
   }

   @Override
   public void run() {
        while (true) {
            System.out.println("Please enter one of operation type: ");
            for (ConsoleOperationType type : ConsoleOperationType.values()) {
                System.out.println("-" + type);
            }

            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("Program exiting.");
                break;
            }

            try {
                ConsoleOperationType operationType = ConsoleOperationType
                        .valueOf(input.toUpperCase());
                OperationCommand command = commands.get(operationType);

                if (command == null) {
                    System.out.println("Unknown command: " + input);
                    continue;
                }

                command.execute();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command: " + input);
            } catch (Exception e) {
                System.out.println("Error executing command: " + e.getMessage()); 
            }
        }
   }
}

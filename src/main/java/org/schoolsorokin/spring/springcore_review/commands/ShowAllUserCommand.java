package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.User;
import org.schoolsorokin.spring.springcore_review.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

//Отображение списка всех пользователей.
@Component
public class ShowAllUserCommand implements OperationCommand {

    private static final Logger log = Logger.getLogger(ShowAllUserCommand.class.getName());
    private final OutputCommands outputCommands;
    private final UserService userService;

    public ShowAllUserCommand(
            UserService userService,
            OutputCommands outputCommands
    ) {
        this.userService = userService;
        this.outputCommands = outputCommands;
    }

    @Override
    public void execute() {
        log.info("Executing SHOW_ALL_USERS command.");

        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            log.warning("No users found in system.");
            System.out.println("No users found");
            return;
        }
        outputCommands.ShowAllUsersOutPut(users);

        log.info("Displayed " + users.size() + " users successfully.");
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}

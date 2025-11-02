package org.schoolsorokin.spring.springcore_review.commands;

import org.schoolsorokin.spring.springcore_review.ConsoleOperationType;
import org.schoolsorokin.spring.springcore_review.User;
import org.schoolsorokin.spring.springcore_review.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

//Отображение списка всех пользователей.
@Component
public class ShowAllUserCommand implements OperationCommand {
    private final UserService userService;

    public ShowAllUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found");
            return;
        }

        System.out.println("List of all users:");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS;
    }
}

package org.schoolsorokin.spring.springcore_review.service;

import org.schoolsorokin.spring.springcore_review.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private int idCounter = 0;
    private final List<User> users = new ArrayList<>();
    private static final Logger log = Logger.getLogger(UserService.class.getName());

    //Создание пользователя
    public User createUser(String login) {
        log.info("Attempting to create new user with login: " + login);

        // Проверка на null, пустую строку и пробелы
        if (login == null || login.trim().isEmpty()) {
            log.warning("User creation failed: login is empty or null.");
            throw new IllegalArgumentException("Error: login cannot be empty.");
        }

        // Проверка, существует ли пользователь с таким логином
        boolean exists = users.stream()
                .anyMatch(user -> user.getLogin().equalsIgnoreCase(login.trim()));

        if (exists) {
            log.warning("User creation failed: login '" + login + "' already exists.");
            throw new IllegalArgumentException("Error: a user with this login already exists.");
        }

        idCounter++;
        User user = new User(idCounter, login, new ArrayList<>());
        users.add(user);

        log.info("New user successfully created: ID=" + user.getUserId()
                + ", login='" + user.getLogin() + "'");
        return user;
    }

    //Поиск пользователя по ID
    public Optional<User> searchUser(int userId) {
        log.info("Searching for user with ID: " + userId);

        return users.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst();
    }

    //Получение списка всех пользователей
    public List<User> getAllUsers() {
        log.info("Retrieving all users. Total count: " + users.size());
        return users;
    }
}
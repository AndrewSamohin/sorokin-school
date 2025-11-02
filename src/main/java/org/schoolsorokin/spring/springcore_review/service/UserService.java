package org.schoolsorokin.spring.springcore_review.service;

import org.schoolsorokin.spring.springcore_review.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private int idCounter = 0;
    private final List<User> users = new ArrayList<>();

    //Создание пользователя
    public User createUser(String login) {
        idCounter++;
        User user = new User(idCounter, login, new ArrayList<>());
        users.add(user);
        return user;
    }

    //Поиск пользователя по ID
    public Optional<User> searchUser(int userId) {
        return users.stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst();
    }

    //Получение списка всех пользователей
    public List<User> getAllUsers() {
        return users;
    }
}
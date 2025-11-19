package org.schoolsorokin.spring.springcore_review.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.schoolsorokin.spring.springcore_review.User;
import org.schoolsorokin.spring.springcore_review.TransactionHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();
    private static final Logger log = Logger.getLogger(UserService.class.getName());

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public UserService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    //Создание пользователя
    public User createUser(String login) {
        log.info("Attempting to create new user with login: " + login);

        // Проверка на null, пустую строку и пробелы
        if (login == null || login.trim().isEmpty()) {
            log.warning("User creation failed: login is empty or null.");
            throw new IllegalArgumentException("Error: login cannot be empty.");
        }

        // Проверка, существует ли пользователь с таким логином
        return transactionHelper.executeInTransaction(session -> {
            Long count = session.createQuery(
                            "SELECT COUNT(u) FROM User u WHERE LOWER(u.login) = LOWER(:login)",
                            Long.class
                    ).setParameter("login", login.trim())
                    .uniqueResult();

            if (count != null && count > 0) {
                log.warning("User creation failed: login '" + login + "' already exists.");
                throw new IllegalArgumentException("Error: a user with this login already exists.");
            }

            User user = new User();
            user.setLogin(login);
            session.persist(user);

            log.info("Created user with login: " + login);
            log.info("New user successfully created: ID=" + user.getUserId()
                    + ", login='" + user.getLogin() + "'");
            return user;
        });
    }

    //Поиск пользователя по ID
    public Optional<User> searchUser(int userId) {
        log.info("Searching for user with ID: " + userId);

        return Optional.ofNullable(
                transactionHelper.executeInTransaction(
                        session -> session.find(User.class, userId)
                )
        );
    }

    //Получение списка всех пользователей
    public List<User> getAllUsers() {
        log.info("Retrieving all users. Total count: " + users.size());

        return transactionHelper.executeInTransaction(
                session -> session.createQuery("FROM User", User.class).list()
        );
    }
}
package org.schoolsorokin.javacore.testing.users;

public interface UserRepository {
    User findUserById(int id);

    User saveUser(User user);
}

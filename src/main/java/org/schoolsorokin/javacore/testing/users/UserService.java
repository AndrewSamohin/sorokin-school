package org.schoolsorokin.javacore.testing.users;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Метод возвращает имя пользователя, если пользователь найден, или "Unknown"
    public String getUserName(int id) {
        User user = userRepository.findUserById(id);
        return user != null ? user.getName() : "Unknown";
    }

    public String saveUser(User user) {
        User savedUser = userRepository.saveUser(user);
        return savedUser != null ? (user.getName() + " saved!") : "Unknown";
    }
}

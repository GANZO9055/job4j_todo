package ru.job4j.todo.service.user;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> create(User user);

    Optional<User> getByLoginAndPassword(String login, String password);
}

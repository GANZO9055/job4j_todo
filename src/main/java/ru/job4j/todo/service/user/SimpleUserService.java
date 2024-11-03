package ru.job4j.todo.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.user.UserStore;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {
    private final UserStore userStore;
    @Override
    public Optional<User> create(User user) {
        return userStore.create(user);
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
        return userStore.getByLoginAndPassword(login, password);
    }
}

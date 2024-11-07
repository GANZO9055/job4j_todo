package ru.job4j.todo.store.user;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.store.CrudStore;

import javax.management.Query;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

@Repository
@AllArgsConstructor
public class HbnUserStore implements UserStore {
    private final CrudStore crudStore;
    private final Logger logger = LoggerFactory.getLogger(HbnUserStore.class);
    @Override
    public Optional<User> create(User user) {
        try {
            crudStore.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLoginAndPassword(String login, String password) {
        try {
            return crudStore.optional(
                    "FROM User WHERE login = :login AND password = :password",
                    User.class,
                    Map.of("login", login, "password", password)
            );
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return Optional.empty();
    }
}

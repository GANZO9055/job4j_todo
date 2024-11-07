package ru.job4j.todo.store;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class CrudStore {
    private final SessionFactory sf;

    public <T> List<T> query(String query, Class<T> tClass) {
        Function<Session, List<T>> command = session -> session
                .createQuery(query, tClass)
                .list();
        return tx(command);
    }

    public <T> Optional<T> optional(String query, Class<T> tClass, Map<String, Object> args) {
        Function<Session, Optional<T>> command = session -> {
            var result = session.createQuery(query, tClass);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                result.setParameter(arg.getKey(), arg.getValue());
            }
            return result.uniqueResultOptional();
        };
        return tx(command);
    }

    public void run(Consumer<Session> command) {
        tx(session -> {
                    command.accept(session);
                    return null;
                }
        );
    }

    public boolean query(String query, Map<String, Object> args) {
        Function<Session, Boolean> command = session -> {
            var result = session.createQuery(query);
            for (Map.Entry<String, Object> arg : args.entrySet()) {
                result.setParameter(arg.getKey(), arg.getValue());
            }
            return result.executeUpdate() > 0;
        };
        return tx(command);
    }

    public <T> T tx(Function<Session, T> command) {
        Session session = sf.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            var result = command.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}

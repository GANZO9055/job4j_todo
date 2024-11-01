package ru.job4j.todo.store.task;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnTaskStore implements TaskStore {
    private final SessionFactory sf;

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findSuccess() {
        return null;
    }

    @Override
    public List<Task> findNew() {
        return null;
    }

    @Override
    public Optional<Task> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Task create(Task task) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public boolean update(Task task) {
        return false;
    }
}

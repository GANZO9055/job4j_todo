package ru.job4j.todo.store.task;

import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskStore {
    List<Task> findAll();
    List<Task> findCompleted();
    List<Task> findNew();
    Optional<Task> findById(int id);
    Task create(Task task);

    void deleteById(int id);
    boolean update(Task task);
}

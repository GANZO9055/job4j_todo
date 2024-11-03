package ru.job4j.todo.service;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.store.task.HbnTaskStore;
import ru.job4j.todo.store.task.TaskStore;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SimpleTaskService implements TaskService {
    private final TaskStore taskStore;


    @Override
    public List<Task> findAll() {
        return taskStore.findAll();
    }

    @Override
    public List<Task> findCompleted() {
        return taskStore.findCompleted();
    }

    @Override
    public List<Task> findNew() {
        return taskStore.findNew();
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskStore.findById(id);
    }

    @Override
    public Task create(Task task) {
        return taskStore.create(task);
    }

    @Override
    public boolean deleteById(int id) {
        return taskStore.deleteById(id);
    }

    @Override
    public boolean update(Task task) {
        return taskStore.update(task);
    }
}

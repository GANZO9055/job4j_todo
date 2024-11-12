package ru.job4j.todo.store.priority;

import ru.job4j.todo.model.Priority;

import java.util.List;
import java.util.Optional;

public interface PriorityStore {
    Optional<Priority> findById(int id);
    List<Priority> findAll();
}

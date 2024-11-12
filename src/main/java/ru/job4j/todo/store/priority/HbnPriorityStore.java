package ru.job4j.todo.store.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.store.CrudStore;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnPriorityStore implements PriorityStore {
    private final CrudStore crudStore;

    @Override
    public Optional<Priority> findById(int id) {
        return crudStore.optional(
                "FROM Priority WHERE id = :id",
                Priority.class,
                Map.of("id", id)
        );
    }

    @Override
    public List<Priority> findAll() {
        return crudStore.query(
                "FROM Priority",
                Priority.class
        );
    }
}

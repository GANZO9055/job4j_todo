package ru.job4j.todo.store.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.CrudStore;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class HbnCategoryStore implements CategoryStore {

    private final CrudStore crudStore;

    @Override
    public List<Category> findAll() {
        return crudStore.query(
                "FROM Category",
                Category.class
        );
    }

    @Override
    public List<Category> getAllById(List<Integer> categoriesId) {
        return crudStore.query(
                "FROM Category c WHERE c.id IN :id",
                Category.class,
                Map.of("id", categoriesId)
        );
    }
}

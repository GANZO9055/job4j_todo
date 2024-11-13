package ru.job4j.todo.service.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.store.category.CategoryStore;

import java.util.List;

@Service
@AllArgsConstructor
public class SimpleCategoryService implements CategoryService {

    private final CategoryStore categoryStore;

    @Override
    public List<Category> findAll() {
        return categoryStore.findAll();
    }

    @Override
    public List<Category> getAllById(List<Integer> categoriesId) {
        return categoryStore.getAllById(categoriesId);
    }
}

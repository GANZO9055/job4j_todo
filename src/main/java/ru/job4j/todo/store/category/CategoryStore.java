package ru.job4j.todo.store.category;

import ru.job4j.todo.model.Category;

import java.util.List;

public interface CategoryStore {
    List<Category> findAll();
    List<Category> getAllById(List<Integer> categoriesId);
}

package ru.job4j.todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.service.task.TaskService;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/completed")
    public String getCompleted(Model model) {
        model.addAttribute("tasks", taskService.findCompleted());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String getNew(Model model) {
        model.addAttribute("tasks", taskService.findNew());
        return "tasks/list";
    }

    @GetMapping("/created")
    public String getCreatePage() {
        return "tasks/created";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Task task) {
        taskService.create(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        Optional<Task> taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "error/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/description";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Optional<Task> taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "error/404";
        }
        model.addAttribute("task", taskOptional.get());
        return "tasks/updated";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Task task, Model model) {
        var result = taskService.update(task);
        if (!result) {
            model.addAttribute("message", "Задачу не удалось обновить");
            return "error/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        boolean result = taskService.deleteById(id);
        if (!result) {
            model.addAttribute("message", "Задача с указанным идентификатором не найдена");
            return "error/404";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/completed/{id}")
    public String completed(Model model, @PathVariable int id) {
        Optional<Task> taskOptional = taskService.findById(id);
        if (taskOptional.isEmpty()) {
            model.addAttribute("message", "Задача не найдена");
            return "error/404";
        }
        boolean result = taskService.completedTask(taskOptional.get());
        if (!result) {
            model.addAttribute("message", "Задача не переведена в состояние Выполнено");
            return "error/404";
        }
        return "redirect:/tasks";
    }
}

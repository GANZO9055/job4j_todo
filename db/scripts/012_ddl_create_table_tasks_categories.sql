create table tasks_categories (
    id SERIAL PRIMARY KEY,
    task_id int references tasks(id),
    category_id int references categories(id),
    UNIQUE(task_id, category_id)
);
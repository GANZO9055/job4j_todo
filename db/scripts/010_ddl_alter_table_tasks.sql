ALTER TABLE tasks
ADD COLUMN category_id INT REFERENCES categories(id);
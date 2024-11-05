package ru.job4j.todo.store.task;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnTaskStore implements TaskStore {
    private final SessionFactory sf;
    private final Logger logger = LoggerFactory.getLogger(HbnTaskStore.class);

    @Override
    public List<Task> findAll() {
        Session session = sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("FROM Task", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findCompleted() {
        Session session = sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("FROM Task WHERE done = true", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Task> findNew() {
        Session session = sf.openSession();
        List<Task> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("FROM Task WHERE done = false", Task.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        Optional<Task> result = Optional.empty();
        try {
            session.beginTransaction();
            result = session.createQuery("FROM Task WHERE id = :id", Task.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Task create(Task task) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public boolean deleteById(int id) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            Query query = session.createQuery("DELETE Task WHERE id = :id")
                    .setParameter("id", id);
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean update(Task task) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            Query query = session.createQuery("""
                    UPDATE Task
                    SET title = :title, description = :description, done = :done
                    WHERE id = :id
                    """)
                    .setParameter("id", task.getId())
                    .setParameter("title", task.getTitle())
                    .setParameter("description", task.getDescription())
                    .setParameter("done", task.isDone());
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean completedTask(Task task) {
        Session session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            Query query = session.createQuery("""
                    UPDATE Task
                    SET done = :done
                    WHERE id = :id
                    """)
                    .setParameter("id", task.getId())
                    .setParameter("done", true);
            result = query.executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}

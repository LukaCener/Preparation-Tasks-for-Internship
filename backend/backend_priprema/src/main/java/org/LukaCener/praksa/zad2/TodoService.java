package org.LukaCener.praksa.zad2;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TodoService {

    private final Map<Long, Todo> todos = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }

    public Todo create(String title, Boolean completed) {
        long id = idGenerator.getAndIncrement();
        Todo todo = new Todo(id, title, completed != null && completed);
        todos.put(id, todo);
        return todo;
    }

    public Optional<Todo> update(long id, String title, Boolean completed) {
        Todo todo = todos.get(id);
        if (todo == null) {
            return Optional.empty();
        }
        if (title != null) {
            todo.title = title;
        }
        if (completed != null) {
            todo.completed = completed;
        }
        return Optional.of(todo);
    }

    public boolean delete(long id) {
        return todos.remove(id) != null;
    }
}
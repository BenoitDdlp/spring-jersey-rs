package fr.benoitddlp.service;

import fr.benoitddlp.entity.Todo;

import java.util.List;

public interface TodoService {
    public boolean save(Todo todo);
    public List<Todo> getAll();
    public Todo getById(int id);
    public boolean delete(Todo todo);
    public boolean update(Todo todo);
    public Todo find(Todo todo);
}

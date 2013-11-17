package fr.benoitddlp.service.jpa;

import fr.benoitddlp.entity.Todo;
import fr.benoitddlp.service.TodoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service("todoService")
public class TodoServiceJpa implements TodoService {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional(readOnly = true)
    public Todo getById(int id) {
        // TODO Auto-generated method stub
        return entityManager.find(Todo.class, id);
    }
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Todo> getAll() {
        Query query = entityManager.createNamedQuery("Todo.findAll");
        return query.getResultList();
    }

    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public boolean save(Todo todo) {

        entityManager.persist(todo);
        entityManager.flush();

        return true;
    }
    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public boolean update(Todo todo) {
        entityManager.merge(todo);
        entityManager.flush();
        return true;
    }
    @Transactional(readOnly=false, propagation=Propagation.REQUIRED)
    public boolean delete(Todo todo) {
        todo = entityManager.getReference(Todo.class, todo.getId());
        if (todo == null)
            return false;
        entityManager.remove(todo);
        entityManager.flush();
        return true;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Todo find(Todo todo) {
        Todo result = null;
        Query queryFindTodo = entityManager.createNamedQuery("Todo.find");
        queryFindTodo.setParameter("summary", todo.getSummary());
        queryFindTodo.setParameter("description", todo.getDescription());
        List<Todo> todos = queryFindTodo.getResultList();
        if(todos.size() > 0) {
            result = todos.get(0);
        }
        return result;
    }
}

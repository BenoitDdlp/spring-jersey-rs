package fr.benoitddlp.rest;

import fr.benoitddlp.entity.Todo;
import fr.benoitddlp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

// The Java class will be hosted at the URI path "/myresource"
@Path("/todos")
@Component
@Scope("request")
public class TodoResource {
    @Autowired
    TodoService todoService;
    @Context
    UriInfo ui;
	private Logger log = Logger.getLogger(this.getClass().getName());

    /*// The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")
    public String getIt() {

        StringBuilder buffer = new StringBuilder();
        List<Todo> todos = todoService.getAll();
        for (Todo todo : todos) {
            buffer.append(todo.getSummary())
                    .append(":")
                    .append(todo.getDescription())
                    .append("\n");
        }

        return "todo list: "+buffer.toString();
    }*/

    //get all
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Todo> getTodos() {
        log.info("getTodos entered");
        final List<Todo> todos = todoService.getAll();
        return todos;
    }

    //get one
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Todo getTodo(@PathParam("id") final String id) {
        final Todo todo = todoService.getById(Integer.parseInt(id));
        return todo;
    }

    //update one
    @PUT
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/update")
    public void updateTodo(final Todo todo) {
        todoService.update(todo);
    }

    //add one
    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void addTodo(final Todo todo) {
        log.info("addTodo entered");
        todoService.save(todo);
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/plain")
    public void deleteTodo(@PathParam("id") final String id) {
        deleteTodoById(id);
        final MultivaluedMap<String, String> queryParams = ui
                .getQueryParameters();
        final List<String> ids = queryParams.get("id");

        if (ids == null) {
            log.warning("\n\nThe ids is null");
        } else {
            log.info("The ids are: "+ids.size());
            for (String currentid : ids) {
                log.info("Delete: "+currentid);
                deleteTodoById(currentid);
            }
        }
    }

    private void deleteTodoById(final String id) {
        log.info("Delete Id: " + id);
        final Todo todo = todoService.getById(Integer.parseInt(id));
        if (todo == null) {
            log.info("Null was returned for ID: " + id);
        } else {
            todoService.delete(todo);
        }
    }

    @GET
    @Path("/test")
    public String simpleTest() {
        return "Hello";
    }

}

package org.LukaCener.praksa.zad2;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    TodoService service;

    @GET
    public List<TodoResponse> getAll() {
        return service.findAll().stream()
                .map(t -> new TodoResponse(t.id, t.title, t.completed))
                .toList();
    }

    @POST
    public Response create(TodoRequest request) {
        if (request.title() == null || request.title().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Title is required")
                    .build();
        }

        Todo todo = service.create(request.title(), request.completed());
        return Response.status(Response.Status.CREATED)
                .entity(new TodoResponse(todo.id, todo.title, todo.completed))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, TodoRequest request) {
        return service.update(id, request.title(), request.completed())
                .map(todo -> Response.ok(
                        new TodoResponse(todo.id, todo.title, todo.completed)
                ).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        boolean removed = service.delete(id);
        if (!removed) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
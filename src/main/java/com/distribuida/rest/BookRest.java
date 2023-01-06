package com.distribuida.rest;

import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import com.distribuida.servicios.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class BookRest {
    @Inject private BookService bookService;

    @Inject private DbConfig config;

    //Listar 1
    @GET
    @Path("{id}")
    public Book findBy(@PathParam("id") Integer id){
        return bookService.findById(id);
    }

    //Listar todos
    @GET
    public List<Book> findAll() {
        return bookService.findAll();
    }

    //Crear
    @POST
    public void create(Book book) {
        bookService.create(book);
    }

    //Actualizar
    @PUT
    @Path("{id}")
    public void update(@PathParam("id") Integer id, Book book) {
        bookService.update(id,book);
    }

    //Eliminar
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        bookService.delete(id);
    }
}

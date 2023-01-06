package com.distribuida.servicios;


import com.distribuida.config.DbConfig;
import com.distribuida.db.Book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Handle;

import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class BookServiceImpl implements BookService{

    @Inject
    private DbConfig conexion;

    private List <Book> books = new ArrayList<>();

    //Listar 1
    public Book findById(Integer id) {
        Book book = null;
        try {
            Handle h = conexion.poolConexion();
            book = h.select("SELECT * FROM books WHERE id=?", id)
                    .mapToBean(Book.class)
                    .one();
            h.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return book;
    }

    //Listar todos
    public List<Book> findAll() {
        try {
            Handle h = conexion.poolConexion();
            books = h.createQuery("SELECT * FROM books")
                    .mapToBean(Book.class)
                    .list();
            h.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    //Crear
    public void create(Book book){
        Handle h = null;
        try {
            h = conexion.poolConexion();
            h.execute("INSERT INTO books(isbn, title, author, price) VALUES (?, ?, ?, ?)",book.getIsbn(), book.getTitle(), book.getAuthor(),book.getPrice());
            h.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Actualizar
    public void update(Integer id, Book book){
        Handle h = null;
        try {
            try  {
                h = conexion.poolConexion();
                h.createUpdate("UPDATE books SET isbn = :isbn, title = :title, author = :author, price = :price WHERE id = :id")
                        .bind("id",book.getId())
                        .bind("isbn", book.getIsbn())
                        .bind("title", book.getTitle())
                        .bind("author", book.getAuthor())
                        .bind("price", book.getPrice())
                        .execute();
                h.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Eliminar
    public void delete(Integer id){
        Handle h = null;
        try {
            h = conexion.poolConexion();
            h.execute("DELETE FROM books WHERE id = " + id);
            h.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

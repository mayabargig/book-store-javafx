package org.hit.server.dao;

import org.hit.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BookFileImpl {

    private static BookFileImpl instance;

    private List<Book> books = new ArrayList<>();

    private BookFileImpl() {}

    public static BookFileImpl getInstance() {
        if (instance == null) {
            instance = new BookFileImpl();
        }
        return instance;
    }

    public void save(Book book) {
        books.add(book);
    }

    public boolean delete(Book book) {

        return books.removeIf(
                b -> b.getId().equals(book.getId())
        );
    }

    public List<Book> getAll() {
        return books;
    }
}
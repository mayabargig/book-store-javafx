package org.hit.server.service;

import org.hit.common.Response;
import org.hit.models.Book;
import org.hit.server.dao.BookFileImpl;
import org.hit.algorithms.AlgorithmFactory;
import org.hit.algorithms.ILCSAlgorithm;

import java.util.List;

public class BookService {

    private BookFileImpl dao = BookFileImpl.getInstance();

    public Response addBook(Object obj) {

        if (!(obj instanceof Book)) {
            return new Response(
                    "ERROR",
                    "Invalid book data"
            );
        }

        Book book = (Book) obj;

        boolean saved = dao.save(book);

        if (saved) {
            return new Response(
                    "OK",
                    "Book added successfully"
            );
        }

        return new Response(
                "ERROR",
                "Book could not be added. ID may already exist"
        );
    }

    public Response deleteBook(Object obj) {


        Book book = (Book)obj;


        boolean deleted = dao.delete(book);


        if(deleted){

            return new Response(
                    "OK",
                    "Book deleted"
            );

        }
        else{

            return new Response(
                    "ERROR",
                    "Book not found"
            );
        }
    }

    public Response getAllBooks() {

        return new Response("OK", dao.getAll());
    }

    public Response searchBook(Object obj, String algorithmName) {

        String query = ((String)obj).toLowerCase();


        ILCSAlgorithm algo =
                AlgorithmFactory.getAlgorithm(algorithmName);


        List<Book> books = dao.getAll();


        Book best = null;
        int bestScore = 0;


        for (Book b : books) {

            int score = algo.compare(
                    query,
                    b.getName().toLowerCase()
            );


            if (score > bestScore) {

                bestScore = score;
                best = b;
            }
        }


        return new Response("OK", best);
    }

    public Response updateBook(Object obj) {

        if (!(obj instanceof Book)) {
            return new Response(
                    "ERROR",
                    "Invalid book data"
            );
        }

        Book book = (Book) obj;

        boolean updated = dao.update(book);

        if (updated) {
            return new Response(
                    "OK",
                    "Book updated successfully"
            );
        }

        return new Response(
                "ERROR",
                "Book not found"
        );
    }
}
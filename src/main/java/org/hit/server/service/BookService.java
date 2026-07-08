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

        Book book = (Book) obj;
        dao.save(book);

        return new Response("OK", "Book added");
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
}
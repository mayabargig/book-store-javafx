package org.hit.server.controllers;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.server.service.BookService;

public class BookController {

    private BookService service = new BookService();

    public Response handle(Request request) {

        String action = request.getAction();

        switch (action) {

            case "book/add":
                return service.addBook(request.getData());

            case "book/delete":
                return service.deleteBook(request.getData());

            case "book/search":
                return service.searchBook(
                        request.getData(),
                        request.getAlgorithmName()
                );

            case "book/getAll":
                return service.getAllBooks();

            default:
                return new Response("ERROR", "Unknown book action");
        }
    }
}
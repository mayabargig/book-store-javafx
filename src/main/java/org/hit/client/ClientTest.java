package org.hit.client;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Book;

public class ClientTest {

    public static void main(String[] args) {

        Client client = new Client("localhost", 34567);

        // ------------------------
        // TEST 1 - ADD BOOK
        // ------------------------
        System.out.println("=== TEST 1: ADD BOOK ===");

        Book book1 = new Book("1", "Harry Potter");

        Response response = client.send(
                new Request("book/add", book1, null));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 2 - ADD SECOND BOOK
        // ------------------------
        System.out.println("\n=== TEST 2: ADD SECOND BOOK ===");

        Book book2 = new Book("2", "The Hobbit");

        response = client.send(
                new Request("book/add", book2, null));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 3 - GET ALL BOOKS
        // ------------------------
        System.out.println("\n=== TEST 3: GET ALL BOOKS ===");

        response = client.send(
                new Request("book/getAll", null, null));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 4 - SEARCH (DP)
        // ------------------------
        System.out.println("\n=== TEST 4: SEARCH (DP) ===");

        response = client.send(
                new Request("book/search", "Harry", "dp"));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 5 - DELETE BOOK
        // ------------------------
        System.out.println("\n=== TEST 5: DELETE BOOK ===");

        response = client.send(
                new Request("book/delete", book1, null));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 6 - GET ALL AFTER DELETE
        // ------------------------
        System.out.println("\n=== TEST 6: GET ALL AFTER DELETE ===");

        response = client.send(
                new Request("book/getAll", null, null));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 7 - SEARCH DELETED BOOK (DP)
        // ------------------------
        System.out.println("\n=== TEST 7: SEARCH DELETED BOOK (DP) ===");

        response = client.send(
                new Request("book/search", "Harry", "dp"));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 8 - SEARCH (NAIVE)
        // ------------------------
        System.out.println("\n=== TEST 8: SEARCH (NAIVE) ===");

        response = client.send(
                new Request("book/search", "Harry Potter", "naive"));

        System.out.println(response.getStatus());
        System.out.println(response.getData());

        // ------------------------
        // TEST 9 - SEARCH (SPACE OPTIMIZED)
        // ------------------------
        System.out.println("\n=== TEST 9: SEARCH (SPACE OPTIMIZED) ===");

        response = client.send(
                new Request("book/search", "Harry Potter", "space"));

        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
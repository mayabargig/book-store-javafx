package org.hit.server;

import java.io.IOException;

public class ServerDriver {

    public static final int BOOK_SERVER_PORT = 34567;
    public static final int CUSTOMER_SERVER_PORT = 34568;

    public static void main(String[] args) {

        try {
            BookServer bookServer =
                    new BookServer(BOOK_SERVER_PORT);

            CustomerServer customerServer =
                    new CustomerServer(CUSTOMER_SERVER_PORT);

            Thread bookServerThread =
                    new Thread(bookServer, "book-server-thread");

            Thread customerServerThread =
                    new Thread(customerServer, "customer-server-thread");

            bookServerThread.start();
            customerServerThread.start();

            System.out.println("Both servers are running.");

        } catch (IOException e) {

            System.err.println(
                    "Could not start servers: " + e.getMessage()
            );
        }
    }
}
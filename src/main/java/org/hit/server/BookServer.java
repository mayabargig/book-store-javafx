package org.hit.server;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.server.controllers.BookController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BookServer implements Runnable {

    private final int port;
    private final ServerSocket serverSocket;
    private final BookController bookController;

    public BookServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.bookController = new BookController();
    }

    @Override
    public void run() {

        System.out.println("Book Server started on port " + port);

        while (!serverSocket.isClosed()) {

            try {
                Socket clientSocket = serverSocket.accept();

                System.out.println(
                        "Client connected to Book Server: "
                                + clientSocket.getRemoteSocketAddress()
                );

                new Thread(() -> handleClient(clientSocket)).start();

            } catch (IOException e) {

                if (!serverSocket.isClosed()) {
                    System.err.println(
                            "Book Server communication error: "
                                    + e.getMessage()
                    );
                }
            }
        }
    }

    private void handleClient(Socket clientSocket) {

        try (
                Socket socket = clientSocket;
                ObjectOutputStream out =
                        new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in =
                        new ObjectInputStream(socket.getInputStream())
        ) {

            Request request = (Request) in.readObject();

            System.out.println(
                    "Book Server received: " + request.getAction()
            );

            Response response;

            if (request.getAction() == null
                    || !request.getAction().startsWith("book/")) {

                response = new Response(
                        "ERROR",
                        "Book Server supports only book actions"
                );

            } else {
                response = bookController.handle(request);
            }

            out.writeObject(response);
            out.flush();

        } catch (Exception e) {
            System.err.println(
                    "Book Server failed to handle client: "
                            + e.getMessage()
            );
        }
    }

    public void stop() {

        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(
                    "Could not stop Book Server: " + e.getMessage()
            );
        }
    }
}
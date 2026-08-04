package org.hit.server;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.server.controllers.CustomerController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomerServer implements Runnable {

    private final int port;
    private final ServerSocket serverSocket;
    private final CustomerController customerController;

    public CustomerServer(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.customerController = new CustomerController();
    }

    @Override
    public void run() {

        System.out.println("Customer Server started on port " + port);

        while (!serverSocket.isClosed()) {

            try {
                Socket clientSocket = serverSocket.accept();

                System.out.println(
                        "Client connected to Customer Server: "
                                + clientSocket.getRemoteSocketAddress()
                );

                new Thread(() -> handleClient(clientSocket)).start();

            } catch (IOException e) {

                if (!serverSocket.isClosed()) {
                    System.err.println(
                            "Customer Server communication error: "
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
                    "Customer Server received: " + request.getAction()
            );

            Response response;

            if (request.getAction() == null
                    || !request.getAction().startsWith("customer/")) {

                response = new Response(
                        "ERROR",
                        "Customer Server supports only customer actions"
                );

            } else {
                response = customerController.handle(request);
            }

            out.writeObject(response);
            out.flush();

        } catch (Exception e) {
            System.err.println(
                    "Customer Server failed to handle client: "
                            + e.getMessage()
            );
        }
    }

    public void stop() {

        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println(
                    "Could not stop Customer Server: "
                            + e.getMessage()
            );
        }
    }
}
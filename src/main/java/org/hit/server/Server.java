package org.hit.server;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.server.controllers.BookController;
import org.hit.server.controllers.CustomerController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                new Thread(() -> handleClient(clientSocket)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {

        try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {

            Request request = (Request) in.readObject();
            System.out.println("Received: " + request);

            String action = request.getAction();

            Response response;

            switch (action.split("/")[0]) {

                case "book":
                    response = new BookController().handle(request);
                    break;

                case "customer":
                    response = new CustomerController().handle(request);
                    break;

                default:
                    response = new Response("ERROR", "Unknown action");
            }

            out.writeObject(response);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
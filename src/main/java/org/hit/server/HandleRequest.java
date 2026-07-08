package org.hit.server;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.server.controllers.BookController;
import org.hit.server.controllers.CustomerController;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandleRequest {

    private Socket socket;

    public HandleRequest(Socket socket) {
        this.socket = socket;
    }

    public void handle() {

        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {

            // קבלת Request מהלקוח
            Request request = (Request) in.readObject();

            String action = request.getAction();   // ✅ תיקון חשוב

            Object response;

            switch (action.split("/")[0]) {

                case "book":
                    BookController bookController = new BookController();
                    response = bookController.handle(request);
                    break;

                case "customer":
                    CustomerController customerController = new CustomerController();
                    response = customerController.handle(request);
                    break;

                default:
                    response = new Response("ERROR", "Unknown action");
            }

            // שליחת Response חזרה ללקוח
            out.writeObject(response);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
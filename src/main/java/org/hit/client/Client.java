package org.hit.client;

import org.hit.common.Request;
import org.hit.common.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Response send(Request request) {

        try (Socket socket = new Socket(host, port);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            // שליחה לשרת
            out.writeObject(request);
            out.flush();

            // קבלת תשובה
            return (Response) in.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return new Response("ERROR", e.getMessage());
        }
    }
}
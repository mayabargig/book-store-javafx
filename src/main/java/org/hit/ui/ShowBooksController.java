package org.hit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.hit.client.Client;
import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Book;

import java.io.IOException;
import java.util.List;

public class ShowBooksController {

    @FXML
    private ListView<Book> booksList;


    private Client client = new Client("localhost",34567);


    @FXML
    public void loadBooks() {

        Response response = client.send(
                new Request("book/getAll", null, null)
        );


        List<Book> books =
                (List<Book>) response.getData();


        booksList.getItems().clear();
        booksList.getItems().addAll(books);


        System.out.println(response.getData());
    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(SceneManager.loadScene("Main.fxml"));
        stage.show();
    }
}
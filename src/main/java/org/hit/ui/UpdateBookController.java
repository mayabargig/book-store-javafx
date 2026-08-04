package org.hit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hit.client.Client;
import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Book;

import java.io.IOException;

public class UpdateBookController {

    private static final int BOOK_SERVER_PORT = 34567;

    private final Client client =
            new Client("localhost", BOOK_SERVER_PORT);

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label resultLabel;

    @FXML
    private void updateBook() {

        String id = txtId.getText().trim();
        String name = txtName.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {

            resultLabel.setText(
                    "❌ Please fill in all fields"
            );

            resultLabel.setStyle(
                    "-fx-text-fill: red;"
            );

            return;
        }

        Book updatedBook = new Book(
                id,
                name
        );

        Response response = client.send(
                new Request(
                        "book/update",
                        updatedBook,
                        null
                )
        );

        if ("OK".equals(response.getStatus())) {

            resultLabel.setText(
                    "✅ Book updated successfully"
            );

            resultLabel.setStyle(
                    "-fx-text-fill: green;"
            );

            txtId.clear();
            txtName.clear();

        } else {

            resultLabel.setText(
                    "❌ " + response.getData()
            );

            resultLabel.setStyle(
                    "-fx-text-fill: red;"
            );
        }
    }

    @FXML
    private void backToMenu(ActionEvent event)
            throws IOException {

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setTitle("Book Store Management System");
        stage.setScene(
                SceneManager.loadScene("Main.fxml")
        );

        stage.show();
    }
}
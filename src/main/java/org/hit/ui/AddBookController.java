package org.hit.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hit.client.Client;
import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Book;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.control.Label;


public class AddBookController {


    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;


    private Client client = new Client("localhost", 34567);


    @FXML
    private void addBook() {

        String id = txtId.getText().trim();
        String name = txtName.getText().trim();


        // בדיקת שדות ריקים
        if (id.isEmpty() || name.isEmpty()) {

            resultLabel.setText("❌ Please fill all fields");
            resultLabel.setStyle("-fx-text-fill: red;");

            return;
        }


        Book book = new Book(id, name);


        Response response = client.send(
                new Request("book/add", book, null)
        );


        if (response.getStatus().equals("OK")) {

            resultLabel.setText("✅ Book added successfully");
            resultLabel.setStyle("-fx-text-fill: green;");

            txtId.clear();
            txtName.clear();

        } else {

            resultLabel.setText("❌ Failed to add book");
            resultLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void backToMenu(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setScene(SceneManager.loadScene("Main.fxml"));
        stage.show();
    }

    @FXML
    private Label resultLabel;
}
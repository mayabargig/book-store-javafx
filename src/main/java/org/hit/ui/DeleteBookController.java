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

public class DeleteBookController {


    @FXML
    private TextField txtId;


    @FXML
    private Label resultLabel;


    private Client client =
            new Client("localhost", 34567);



    @FXML
    private void deleteBook() {


        String id = txtId.getText().trim();


        // בדיקה שהמשתמש הכניס ID
        if (id.isEmpty()) {

            resultLabel.setText("❌ Please enter Book ID");
            resultLabel.setStyle("-fx-text-fill: #E74C3C;");

            return;
        }


        Book book = new Book();
        book.setId(id);



        Response response = client.send(
                new Request(
                        "book/delete",
                        book,
                        null
                )
        );


        String message = response.getData() != null
                ? response.getData().toString()
                : "";



        // הצלחה
        if (message.toLowerCase().contains("deleted")
                || message.toLowerCase().contains("success")) {


            resultLabel.setText("✅ " + message);
            resultLabel.setStyle("-fx-text-fill: #27AE60;");

            txtId.clear();

        }

        // הספר לא נמצא
        else if (message.toLowerCase().contains("not found")
                || message.toLowerCase().contains("doesn't exist")) {


            resultLabel.setText("❌ " + message);
            resultLabel.setStyle("-fx-text-fill: #E74C3C;");

        }

        // כל שגיאה אחרת
        else {

            resultLabel.setText("❌ " + message);
            resultLabel.setStyle("-fx-text-fill: #E74C3C;");

        }



        System.out.println(response.getStatus());
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
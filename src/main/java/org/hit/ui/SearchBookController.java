package org.hit.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hit.client.Client;
import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Book;
import java.io.IOException;


public class SearchBookController {


    @FXML
    private TextField txtSearch;


    @FXML
    private ComboBox<String> algorithmBox;


    @FXML
    private Label resultLabel;



    private Client client =
            new Client("localhost",34567);



    @FXML
    public void initialize(){

        algorithmBox.getItems().addAll(
                "Dynamic Programming (LCS)",
                "Naive Word Search"
        );

        algorithmBox.setValue("Dynamic Programming (LCS)");
    }




    @FXML
    private void searchBook(){


        String query = txtSearch.getText().trim();


        if(query.isEmpty()) {

            resultLabel.setText("❌ Please enter book name");
            resultLabel.setStyle("-fx-text-fill: #E74C3C;");

            return;
        }

        String algorithm;

        if (algorithmBox.getValue().startsWith("Dynamic")) {

            algorithm = "dp";

        } else {

            algorithm = "naive";

        }


        Response response = client.send(
                new Request(
                        "book/search",
                        query,
                        algorithm
                )
        );



        Book book =
                (Book) response.getData();



        if(book != null){

            resultLabel.setText(
                    "Found: " + book
            );

        }
        else{

            resultLabel.setText(
                    "No book found"
            );
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
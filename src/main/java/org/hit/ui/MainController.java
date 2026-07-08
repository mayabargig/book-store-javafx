package org.hit.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class MainController {

    @FXML
    private void handleAdd(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/hit/ui/AddBook.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage)
                    ((Node) event.getSource())
                            .getScene()
                            .getWindow();
            stage.setTitle("Add Book");
            stage.setScene(createScene(root));

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearch(ActionEvent event){

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/org/hit/ui/SearchBook.fxml")
                    );


            Parent root = loader.load();


            Stage stage = (Stage)
                    ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setTitle("Search Book");

            stage.setScene(
                    createScene(root)
            );

            stage.show();


        } catch(Exception e){

            e.printStackTrace();

        }
    }

    @FXML
    private void handleShowAll(ActionEvent event) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource("/org/hit/ui/ShowBooks.fxml")
                    );

            Parent root = loader.load();

            Stage stage = (Stage)
                    ((Node) event.getSource())
                            .getScene()
                            .getWindow();
            stage.setTitle("All Books");
            stage.setScene(createScene(root));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete(ActionEvent event){

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass()
                                    .getResource("/org/hit/ui/DeleteBook.fxml")
                    );


            Parent root = loader.load();


            Stage stage = (Stage)
                    ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setTitle("Delete Book");

            stage.setScene(
                    createScene(root)
            );


            stage.show();


        } catch(Exception e){

            e.printStackTrace();

        }
    }

    private Scene createScene(Parent root) {

        Scene scene = new Scene(root, 600, 450);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/org/hit/ui/style.css")
                        .toExternalForm()
        );

        return scene;
    }
}
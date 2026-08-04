package org.hit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class BookMenuController {

    @FXML
    private void openAddBook(ActionEvent event) {
        openScene(event, "AddBook.fxml", "Add Book");
    }

    @FXML
    private void openUpdateBook(ActionEvent event) {
        openScene(event, "UpdateBook.fxml", "Update Book");
    }

    @FXML
    private void openSearchBook(ActionEvent event) {
        openScene(event, "SearchBook.fxml", "Search Book");
    }

    @FXML
    private void openShowAllBooks(ActionEvent event) {
        openScene(event, "ShowBooks.fxml", "All Books");
    }

    @FXML
    private void openDeleteBook(ActionEvent event) {
        openScene(event, "DeleteBook.fxml", "Delete Book");
    }

    @FXML
    private void backToMainMenu(ActionEvent event) {
        openScene(
                event,
                "Main.fxml",
                "Book Store Management System"
        );
    }

    private void openScene(
            ActionEvent event,
            String fxmlFile,
            String title
    ) {

        try {
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setTitle(title);
            stage.setScene(
                    SceneManager.loadScene(fxmlFile)
            );
            stage.show();

        } catch (IOException e) {
            System.err.println(
                    "Could not open " + fxmlFile
                            + ": " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}
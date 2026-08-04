package org.hit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerMenuController {

    @FXML
    private void openAddCustomer(ActionEvent event) {
        openScene(
                event,
                "AddCustomer.fxml",
                "Add Customer"
        );
    }

    @FXML
    private void openUpdateCustomer(ActionEvent event) {
        openScene(
                event,
                "UpdateCustomer.fxml",
                "Update Customer"
        );
    }

    @FXML
    private void openSearchCustomer(ActionEvent event) {
        openScene(
                event,
                "SearchCustomer.fxml",
                "Search Customer"
        );
    }

    @FXML
    private void openShowAllCustomers(ActionEvent event) {
        openScene(
                event,
                "ShowCustomers.fxml",
                "All Customers"
        );
    }

    @FXML
    private void openDeleteCustomer(ActionEvent event) {
        openScene(
                event,
                "DeleteCustomer.fxml",
                "Delete Customer"
        );
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
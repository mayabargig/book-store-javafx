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
import org.hit.models.Customer;

import java.io.IOException;

public class DeleteCustomerController {

    private static final int CUSTOMER_SERVER_PORT = 34568;

    private final Client client =
            new Client("localhost", CUSTOMER_SERVER_PORT);

    @FXML
    private TextField txtId;

    @FXML
    private Label resultLabel;

    @FXML
    private void deleteCustomer() {

        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            showError("Please enter a customer ID");
            return;
        }

        Customer customerToDelete =
                new Customer(id, "", "");

        Response response = client.send(
                new Request(
                        "customer/delete",
                        customerToDelete,
                        null
                )
        );

        if ("OK".equals(response.getStatus())) {

            resultLabel.setText(
                    "✅ Customer deleted successfully"
            );

            resultLabel.setStyle(
                    "-fx-text-fill: green;"
            );

            txtId.clear();

        } else {
            showError(
                    String.valueOf(response.getData())
            );
        }
    }

    private void showError(String message) {
        resultLabel.setText("❌ " + message);
        resultLabel.setStyle("-fx-text-fill: red;");
    }

    @FXML
    private void backToCustomerMenu(ActionEvent event)
            throws IOException {

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        stage.setTitle("Customer Management");
        stage.setScene(
                SceneManager.loadScene(
                        "CustomerMenu.fxml"
                )
        );
        stage.show();
    }
}
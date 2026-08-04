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

public class SearchCustomerController {

    private static final int CUSTOMER_SERVER_PORT = 34568;

    private final Client client =
            new Client("localhost", CUSTOMER_SERVER_PORT);

    @FXML
    private TextField txtId;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private void searchCustomer() {

        clearCustomerDetails();

        String id = txtId.getText().trim();

        if (id.isEmpty()) {
            showError("Please enter a customer ID");
            return;
        }

        Response response = client.send(
                new Request(
                        "customer/get",
                        id,
                        null
                )
        );

        if ("OK".equals(response.getStatus())
                && response.getData() instanceof Customer) {

            Customer customer =
                    (Customer) response.getData();

            idLabel.setText(
                    "ID: " + customer.getId()
            );

            nameLabel.setText(
                    "Name: " + customer.getName()
            );

            emailLabel.setText(
                    "Email: " + customer.getEmail()
            );

            resultLabel.setText(
                    "✅ Customer found"
            );

            resultLabel.setStyle(
                    "-fx-text-fill: green;"
            );

        } else {
            showError(
                    String.valueOf(response.getData())
            );
        }
    }

    private void clearCustomerDetails() {
        idLabel.setText("");
        nameLabel.setText("");
        emailLabel.setText("");
        resultLabel.setText("");
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
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

public class AddCustomerController {

    private static final int CUSTOMER_SERVER_PORT = 34568;

    private final Client client =
            new Client("localhost", CUSTOMER_SERVER_PORT);

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label resultLabel;

    @FXML
    private void addCustomer() {

        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String email = txtEmail.getText().trim();

        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            showError("Please fill in all fields");
            return;
        }

        if (!isValidEmail(email)) {
            showError("Please enter a valid email address");
            return;
        }

        Customer customer = new Customer(
                id,
                name,
                email
        );

        Response response = client.send(
                new Request(
                        "customer/add",
                        customer,
                        null
                )
        );

        if ("OK".equals(response.getStatus())) {

            resultLabel.setText(
                    "✅ Customer added successfully"
            );

            resultLabel.setStyle(
                    "-fx-text-fill: green;"
            );

            txtId.clear();
            txtName.clear();
            txtEmail.clear();

        } else {

            showError(
                    String.valueOf(response.getData())
            );
        }
    }

    private boolean isValidEmail(String email) {

        return email.contains("@")
                && email.indexOf("@") > 0
                && email.lastIndexOf(".")
                > email.indexOf("@") + 1;
    }

    private void showError(String message) {

        resultLabel.setText("❌ " + message);
        resultLabel.setStyle("-fx-text-fill: red;");
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
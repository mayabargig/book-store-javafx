package org.hit.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.hit.client.Client;
import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Customer;

import java.io.IOException;
import java.util.List;

public class ShowCustomersController {

    private static final int CUSTOMER_SERVER_PORT = 34568;

    private final Client client =
            new Client("localhost", CUSTOMER_SERVER_PORT);

    @FXML
    private ListView<String> customersList;

    @FXML
    private Label resultLabel;

    @FXML
    private void initialize() {
        loadCustomers();
    }

    @FXML
    private void loadCustomers() {

        customersList.getItems().clear();
        resultLabel.setText("");

        Response response = client.send(
                new Request(
                        "customer/getAll",
                        null,
                        null
                )
        );

        if (!"OK".equals(response.getStatus())) {
            showError(
                    String.valueOf(response.getData())
            );
            return;
        }

        if (!(response.getData() instanceof List<?>)) {
            showError("Invalid customer list");
            return;
        }

        List<?> customers =
                (List<?>) response.getData();

        if (customers.isEmpty()) {

            resultLabel.setText(
                    "No customers were found"
            );

            resultLabel.setStyle(
                    "-fx-text-fill: #6C7A89;"
            );

            return;
        }

        for (Object item : customers) {

            if (item instanceof Customer) {

                Customer customer = (Customer) item;

                customersList.getItems().add(
                        customer.getId()
                                + " | "
                                + customer.getName()
                                + " | "
                                + customer.getEmail()
                );
            }
        }

        resultLabel.setText(
                "✅ " + customersList.getItems().size()
                        + " customers loaded"
        );

        resultLabel.setStyle(
                "-fx-text-fill: green;"
        );
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
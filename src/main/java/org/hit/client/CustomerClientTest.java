package org.hit.client;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.models.Customer;

import java.util.List;

public class CustomerClientTest {

    private static final String HOST = "localhost";
    private static final int CUSTOMER_SERVER_PORT = 34568;

    public static void main(String[] args) {

        Client client = new Client(
                HOST,
                CUSTOMER_SERVER_PORT
        );

        String customerId = "100";

        testAddCustomer(client, customerId);
        testGetCustomer(client, customerId);
        testUpdateCustomer(client, customerId);
        testGetAllCustomers(client);
        testDeleteCustomer(client, customerId);
        testCustomerDeleted(client, customerId);
    }

    private static void testAddCustomer(
            Client client,
            String customerId
    ) {

        Customer customer = new Customer(
                customerId,
                "Maya",
                "maya@email.com"
        );

        Request request = new Request(
                "customer/add",
                customer,
                null
        );

        Response response = client.send(request);

        printResult(
                "ADD CUSTOMER",
                response
        );
    }

    private static void testGetCustomer(
            Client client,
            String customerId
    ) {

        Request request = new Request(
                "customer/get",
                customerId,
                null
        );

        Response response = client.send(request);

        printResult(
                "GET CUSTOMER",
                response
        );
    }

    private static void testUpdateCustomer(
            Client client,
            String customerId
    ) {

        Customer updatedCustomer = new Customer(
                customerId,
                "Maya Bargig",
                "maya.bargig@email.com"
        );

        Request request = new Request(
                "customer/update",
                updatedCustomer,
                null
        );

        Response response = client.send(request);

        printResult(
                "UPDATE CUSTOMER",
                response
        );
    }

    private static void testGetAllCustomers(Client client) {

        Request request = new Request(
                "customer/getAll",
                null,
                null
        );

        Response response = client.send(request);

        printResult(
                "GET ALL CUSTOMERS",
                response
        );

        if (response.getData() instanceof List<?>) {

            List<?> customers =
                    (List<?>) response.getData();

            System.out.println(
                    "Number of customers: "
                            + customers.size()
            );

            for (Object customer : customers) {
                System.out.println(
                        "Customer: " + customer
                );
            }
        }
    }

    private static void testDeleteCustomer(
            Client client,
            String customerId
    ) {

        Customer customerToDelete = new Customer(
                customerId,
                "",
                ""
        );

        Request request = new Request(
                "customer/delete",
                customerToDelete,
                null
        );

        Response response = client.send(request);

        printResult(
                "DELETE CUSTOMER",
                response
        );
    }

    private static void testCustomerDeleted(
            Client client,
            String customerId
    ) {

        Request request = new Request(
                "customer/get",
                customerId,
                null
        );

        Response response = client.send(request);

        printResult(
                "VERIFY CUSTOMER DELETED",
                response
        );
    }

    private static void printResult(
            String testName,
            Response response
    ) {

        System.out.println();
        System.out.println(
                "========== " + testName + " =========="
        );

        if (response == null) {
            System.out.println("Response is null");
            return;
        }

        System.out.println(
                "Status: " + response.getStatus()
        );

        System.out.println(
                "Data: " + response.getData()
        );
    }
}
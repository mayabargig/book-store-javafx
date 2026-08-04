package org.hit.server.service;

import org.hit.common.Response;
import org.hit.models.Customer;
import org.hit.server.dao.CustomerFileImpl;

import java.util.List;

public class CustomerService {

    private final CustomerFileImpl dao;

    public CustomerService() {
        this.dao = CustomerFileImpl.getInstance();
    }

    public Response addCustomer(Object obj) {

        if (!(obj instanceof Customer)) {

            return new Response(
                    "ERROR",
                    "Invalid customer data"
            );
        }

        Customer customer = (Customer) obj;

        boolean saved = dao.save(customer);

        if (saved) {

            return new Response(
                    "OK",
                    "Customer added successfully"
            );
        }

        return new Response(
                "ERROR",
                "Customer could not be added. ID may already exist"
        );
    }

    public Response updateCustomer(Object obj) {

        if (!(obj instanceof Customer)) {

            return new Response(
                    "ERROR",
                    "Invalid customer data"
            );
        }

        Customer customer = (Customer) obj;

        boolean updated = dao.update(customer);

        if (updated) {

            return new Response(
                    "OK",
                    "Customer updated successfully"
            );
        }

        return new Response(
                "ERROR",
                "Customer not found"
        );
    }

    public Response deleteCustomer(Object obj) {

        if (!(obj instanceof Customer)) {

            return new Response(
                    "ERROR",
                    "Invalid customer data"
            );
        }

        Customer customer = (Customer) obj;

        boolean deleted = dao.delete(customer);

        if (deleted) {

            return new Response(
                    "OK",
                    "Customer deleted successfully"
            );
        }

        return new Response(
                "ERROR",
                "Customer not found"
        );
    }

    public Response getCustomer(Object obj) {

        if (!(obj instanceof String)) {

            return new Response(
                    "ERROR",
                    "Customer ID must be a string"
            );
        }

        String id = (String) obj;

        Customer customer = dao.getById(id);

        if (customer == null) {

            return new Response(
                    "ERROR",
                    "Customer not found"
            );
        }

        return new Response(
                "OK",
                customer
        );
    }

    public Response getAllCustomers() {

        List<Customer> customers = dao.getAll();

        return new Response(
                "OK",
                customers
        );
    }
}
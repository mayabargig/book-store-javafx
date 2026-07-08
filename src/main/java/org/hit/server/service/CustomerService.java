package org.hit.server.service;

import org.hit.common.Response;
import org.hit.models.Customer;
import org.hit.server.dao.CustomerFileImpl;

import java.util.List;

public class CustomerService {

    private CustomerFileImpl dao = new CustomerFileImpl();

    public Response addCustomer(Object obj) {

        Customer customer = (Customer) obj;
        dao.save(customer);

        return new Response("OK", "Customer added");
    }

    public Response deleteCustomer(Object obj) {

        Customer customer = (Customer) obj;
        dao.delete(customer);

        return new Response("OK", "Customer deleted");
    }

    public Response getCustomer(Object obj) {

        String id = (String) obj;

        Customer customer = dao.getById(id);

        return new Response("OK", customer);
    }

    public Response getAllCustomers() {

        List<Customer> customers = dao.getAll();

        return new Response("OK", customers);
    }
}
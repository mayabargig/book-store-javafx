package org.hit.server.controllers;

import org.hit.common.Request;
import org.hit.common.Response;
import org.hit.server.service.CustomerService;

public class CustomerController {

    private CustomerService service = new CustomerService();

    public Response handle(Request request) {

        String action = request.getAction();

        switch (action) {

            case "customer/add":
                return service.addCustomer(request.getData());

            case "customer/delete":
                return service.deleteCustomer(request.getData());

            case "customer/get":
                return service.getCustomer(request.getData());

            case "customer/getAll":
                return service.getAllCustomers();

            default:
                return new Response("ERROR", "Unknown customer action");
        }
    }
}
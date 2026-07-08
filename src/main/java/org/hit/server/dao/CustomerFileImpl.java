package org.hit.server.dao;

import org.hit.models.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFileImpl {

    private final String filePath = "customers.txt";

    public void save(Customer customer) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(customer.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Customer customer) {

        List<Customer> customers = getAll();

        customers.removeIf(c -> c.getId().equals(customer.getId()));

        saveAll(customers);
    }

    public Customer getById(String id) {

        for (Customer c : getAll()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }

        return null;
    }

    public List<Customer> getAll() {

        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                Customer c = new Customer(parts[0], parts[1], parts[2]);

                customers.add(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }

    private void saveAll(List<Customer> customers) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Customer c : customers) {
                writer.write(c.toString());
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
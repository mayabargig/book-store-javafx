package org.hit.server.dao;

import org.hit.api.IDAO;
import org.hit.models.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFileImpl implements IDAO<Customer> {

    private static final String FILE_PATH = "DataSource.txt";
    private static final String CUSTOMER_PREFIX = "CUSTOMER";

    private static CustomerFileImpl instance;

    private CustomerFileImpl() {
        createFileIfNeeded();
    }

    public static synchronized CustomerFileImpl getInstance() {

        if (instance == null) {
            instance = new CustomerFileImpl();
        }

        return instance;
    }

    @Override
    public synchronized boolean save(Customer customer) {

        if (!isValidCustomer(customer)) {
            return false;
        }

        if (getById(customer.getId()) != null) {
            return false;
        }

        try (
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(FILE_PATH, true))
        ) {

            writer.write(convertToLine(customer));
            writer.newLine();

            return true;

        } catch (IOException e) {

            System.err.println(
                    "Could not save customer: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public synchronized boolean update(Customer updatedCustomer) {

        if (!isValidCustomer(updatedCustomer)) {
            return false;
        }

        List<String> lines = readAllLines();
        boolean updated = false;

        for (int i = 0; i < lines.size(); i++) {

            String line = lines.get(i);

            if (!line.startsWith(CUSTOMER_PREFIX + "|")) {
                continue;
            }

            Customer existingCustomer = convertFromLine(line);

            if (existingCustomer != null
                    && existingCustomer.getId()
                    .equals(updatedCustomer.getId())) {

                lines.set(i, convertToLine(updatedCustomer));
                updated = true;
                break;
            }
        }

        if (updated) {
            writeAllLines(lines);
        }

        return updated;
    }

    @Override
    public synchronized boolean delete(Customer customer) {

        if (customer == null || customer.getId() == null) {
            return false;
        }

        List<String> lines = readAllLines();

        boolean deleted = lines.removeIf(line -> {

            if (!line.startsWith(CUSTOMER_PREFIX + "|")) {
                return false;
            }

            Customer existingCustomer = convertFromLine(line);

            return existingCustomer != null
                    && existingCustomer.getId()
                    .equals(customer.getId());
        });

        if (deleted) {
            writeAllLines(lines);
        }

        return deleted;
    }

    @Override
    public synchronized Customer getById(String id) {

        if (id == null || id.isBlank()) {
            return null;
        }

        for (Customer customer : getAll()) {

            if (id.equals(customer.getId())) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public synchronized List<Customer> getAll() {

        List<Customer> customers = new ArrayList<>();

        for (String line : readAllLines()) {

            if (!line.startsWith(CUSTOMER_PREFIX + "|")) {
                continue;
            }

            Customer customer = convertFromLine(line);

            if (customer != null) {
                customers.add(customer);
            }
        }

        return customers;
    }

    private boolean isValidCustomer(Customer customer) {

        return customer != null
                && customer.getId() != null
                && !customer.getId().isBlank()
                && customer.getName() != null
                && !customer.getName().isBlank()
                && customer.getEmail() != null
                && !customer.getEmail().isBlank();
    }

    private String convertToLine(Customer customer) {

        return CUSTOMER_PREFIX
                + "|"
                + sanitize(customer.getId())
                + "|"
                + sanitize(customer.getName())
                + "|"
                + sanitize(customer.getEmail());
    }

    private Customer convertFromLine(String line) {

        String[] parts = line.split("\\|", -1);

        if (parts.length != 4
                || !CUSTOMER_PREFIX.equals(parts[0])) {

            return null;
        }

        return new Customer(
                parts[1],
                parts[2],
                parts[3]
        );
    }

    private String sanitize(String value) {

        if (value == null) {
            return "";
        }

        return value.replace("|", " ");
    }

    private void createFileIfNeeded() {

        File file = new File(FILE_PATH);

        try {

            if (file.createNewFile()) {

                System.out.println(
                        "Created DataSource file: "
                                + file.getAbsolutePath()
                );
            }

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Could not create DataSource.txt",
                    e
            );
        }
    }

    private List<String> readAllLines() {

        createFileIfNeeded();

        List<String> lines = new ArrayList<>();

        try (
                BufferedReader reader =
                        new BufferedReader(new FileReader(FILE_PATH))
        ) {

            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {

            System.err.println(
                    "Could not read DataSource.txt: "
                            + e.getMessage()
            );
        }

        return lines;
    }

    private void writeAllLines(List<String> lines) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(FILE_PATH))
        ) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {

            System.err.println(
                    "Could not write DataSource.txt: "
                            + e.getMessage()
            );
        }
    }
}
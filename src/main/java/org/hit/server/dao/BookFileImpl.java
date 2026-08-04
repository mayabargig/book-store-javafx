package org.hit.server.dao;

import org.hit.api.IDAO;
import org.hit.models.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookFileImpl implements IDAO<Book> {

    private static final String FILE_PATH = "DataSource.txt";
    private static final String BOOK_PREFIX = "BOOK";

    private static BookFileImpl instance;

    private BookFileImpl() {
        createFileIfNeeded();
    }

    public static synchronized BookFileImpl getInstance() {

        if (instance == null) {
            instance = new BookFileImpl();
        }

        return instance;
    }

    @Override
    public synchronized boolean save(Book book) {

        if (book == null || book.getId() == null) {
            return false;
        }

        if (getById(book.getId()) != null) {
            return false;
        }

        try (
                BufferedWriter writer =
                        new BufferedWriter(new FileWriter(FILE_PATH, true))
        ) {

            writer.write(convertToLine(book));
            writer.newLine();

            return true;

        } catch (IOException e) {
            System.err.println(
                    "Could not save book: " + e.getMessage()
            );

            return false;
        }
    }

    @Override
    public synchronized boolean update(Book updatedBook) {

        if (updatedBook == null || updatedBook.getId() == null) {
            return false;
        }

        List<String> lines = readAllLines();
        boolean updated = false;

        for (int i = 0; i < lines.size(); i++) {

            String line = lines.get(i);

            if (!line.startsWith(BOOK_PREFIX + "|")) {
                continue;
            }

            Book existingBook = convertFromLine(line);

            if (existingBook != null
                    && existingBook.getId().equals(updatedBook.getId())) {

                lines.set(i, convertToLine(updatedBook));
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
    public synchronized boolean delete(Book book) {

        if (book == null || book.getId() == null) {
            return false;
        }

        List<String> lines = readAllLines();

        boolean deleted = lines.removeIf(line -> {

            if (!line.startsWith(BOOK_PREFIX + "|")) {
                return false;
            }

            Book existingBook = convertFromLine(line);

            return existingBook != null
                    && existingBook.getId().equals(book.getId());
        });

        if (deleted) {
            writeAllLines(lines);
        }

        return deleted;
    }

    @Override
    public synchronized Book getById(String id) {

        if (id == null) {
            return null;
        }

        for (Book book : getAll()) {

            if (id.equals(book.getId())) {
                return book;
            }
        }

        return null;
    }

    @Override
    public synchronized List<Book> getAll() {

        List<Book> books = new ArrayList<>();

        for (String line : readAllLines()) {

            if (!line.startsWith(BOOK_PREFIX + "|")) {
                continue;
            }

            Book book = convertFromLine(line);

            if (book != null) {
                books.add(book);
            }
        }

        return books;
    }

    private String convertToLine(Book book) {

        return BOOK_PREFIX
                + "|"
                + sanitize(book.getId())
                + "|"
                + sanitize(book.getName());
    }

    private Book convertFromLine(String line) {

        String[] parts = line.split("\\|", -1);

        if (parts.length != 3
                || !BOOK_PREFIX.equals(parts[0])) {

            return null;
        }

        return new Book(parts[1], parts[2]);
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
# 📚 Book Store Management System

> A multi-server Java desktop application that simulates a bookstore management system using JavaFX, TCP Socket Programming, Client-Server architecture, and the DAO design pattern.

---

## 🚀 Overview

The **Book Store Management System** is a Java desktop application developed as part of a Computer Science project.

The application follows a **layered architecture** and demonstrates communication between a JavaFX client and two independent socket-based servers.

The system allows users to manage books and customers independently while persisting data locally using a generic DAO interface.

---

## ✨ Features

### 📖 Book Management
- ➕ Add new books
- ✏️ Update existing books
- 🔍 Search books using multiple search algorithms
- 📚 Display all books
- 🗑 Delete books

### 👤 Customer Management
- ➕ Add new customers
- ✏️ Update customer information
- 🔍 Search customers by ID
- 👥 Display all customers
- 🗑 Delete customers

### 🌐 Client–Server Communication
- TCP Socket communication
- Two independent ServerSocket services
- Book Server (Port **34567**)
- Customer Server (Port **34568**)
- Multi-threaded request handling

### 💾 Data Persistence
- Generic `IDAO<T>` interface
- DAO design pattern
- File-based persistence
- Shared `DataSource.txt` storage

---

## 🏛️ Design Patterns

- DAO Pattern
- Factory Pattern (Algorithm Factory)
- Layered Architecture
- Generic Interface (`IDAO<T>`)

---

## 🛠️ Technologies

| Category | Technologies |
|----------|--------------|
| Language | Java |
| GUI | JavaFX |
| Build Tool | Maven |
| Networking | TCP Sockets |
| Architecture | Client–Server |
| Design Pattern | DAO, Factory |
| Concurrency | Multithreading |
| Version Control | Git & GitHub |

---

## 🏗️ System Architecture

```text
                     JavaFX Client
                            │
                TCP Socket Communication
                            │
             ┌──────────────┴──────────────┐
             │                             │
             ▼                             ▼
      Book Server                    Customer Server
      Port 34567                     Port 34568
             │                             │
             ▼                             ▼
      BookController              CustomerController
             │                             │
             ▼                             ▼
       BookService                 CustomerService
             │                             │
             ▼                             ▼
      BookFileImpl                CustomerFileImpl
               \                  /
                \                /
                 ▼              ▼
                  IDAO<T>
                     │
                     ▼
               DataSource.txt
```

---

## 📂 Project Structure

```text
src
│
├── api
│   └── IDAO.java
│
├── client
│
├── common
│
├── models
│
├── server
│   ├── controllers
│   ├── dao
│   ├── service
│   ├── BookServer.java
│   ├── CustomerServer.java
│   └── ServerDriver.java
│
└── ui
```

---

## 🔎 Search Algorithms

The application allows users to choose between two search algorithms:

- **Dynamic Programming (LCS)** – Finds the longest common subsequence between the search text and the book title, providing more flexible matching.
- **Naive Search** – Performs a straightforward comparison between the search text and the stored book titles.

The selected algorithm is created using the **Algorithm Factory** pattern.

---

## 📸 Application Screenshots

### 🏠 Main Menu

*(Add screenshot here)*

### 📚 Book Management

*(Add screenshot here)*

### 👤 Customer Management

*(Add screenshot here)*

### ➕ Add Book

*(Add screenshot here)*

### 🔍 Search Book

*(Add screenshot here)*

### 👥 Show All Customers

*(Add screenshot here)*

---

## ▶️ Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/mayabargig/book-store-javafx.git
```

### 2. Open the project

Open the project using **IntelliJ IDEA**.

### 3. Start the servers

Run:

- `ServerDriver`

This starts:

- 📚 Book Server (Port **34567**)
- 👤 Customer Server (Port **34568**)

### 4. Launch the JavaFX Client

Run the JavaFX application and access both **Book Management** and **Customer Management** modules.

---

## 📚 Learning Objectives

This project demonstrates practical implementation of:

- Object-Oriented Programming (OOP)
- Client–Server Architecture
- TCP Socket Programming
- JavaFX GUI Development
- Layered Software Architecture
- DAO Design Pattern
- Factory Design Pattern
- Generic Interfaces
- File Persistence
- Multi-threaded Servers

---

## 🚀 Future Improvements

- Database integration (MySQL / MongoDB)
- User authentication
- REST API
- Spring Boot backend
- Unit Testing
- Docker deployment
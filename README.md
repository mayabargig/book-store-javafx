# 📚 Book Store Management System

> A multi-server Java desktop application that simulates a bookstore management system using Client-Server architecture, JavaFX, Socket Programming, and the DAO design pattern.

---

## 🚀 Overview

The Book Store Management System is a Java desktop application developed as part of a Computer Science project.

The application follows a layered architecture and demonstrates communication between a JavaFX client and multiple socket-based servers.

The system allows users to manage books and customers independently through dedicated services while persisting data locally using the DAO pattern.

---

## ✨ Key Features

### 📖 Book Management
- Add new books
- Update existing books
- Delete books
- Search books using multiple algorithms
- View all books

### 👤 Customer Management
- Add customers
- Update customer information
- Delete customers
- Retrieve customer by ID
- View all customers

### 🌐 Client–Server Communication
- TCP Socket communication
- Two independent ServerSocket services
- Book Server (Port 34567)
- Customer Server (Port 34568)
- Multi-threaded request handling

## 🏛️ Design Patterns
- DAO Pattern
- Factory Pattern (Algorithm Factory)
- Layered Architecture
- Generic Interface (IDAO<T>)
  
### 💾 Data Persistence
- Generic DAO interface
- File-based persistence
- Shared DataSource.txt storage

---

## 🛠️ Technologies

| Category | Technologies |
|----------|--------------|
| Language | Java |
| GUI | JavaFX |
| Build Tool | Maven |
| Networking | Java Sockets |
| Architecture | Client-Server |
| Design Pattern | DAO |
| Concurrency | Multithreading |
| Version Control | Git & GitHub |

---

## 🏗️ System Architecture

```text
                JavaFX Client
        │
 TCP Socket Communication
        │
 ┌───────────────┬───────────────┐
 │                               │
 ▼                               ▼
Book Server                 Customer Server
Port 34567                  Port 34568
 │                               │
 ▼                               ▼
BookController           CustomerController
 │                               │
 ▼                               ▼
BookService              CustomerService
 │                               │
 ▼                               ▼
BookFileImpl             CustomerFileImpl
      \                   /
       \                 /
        ▼               ▼
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
│   ├── BookServer
│   └── CustomerServer
│
└── ui
```

---

## 📸 Application Screenshots

### Main Menu

*(Add screenshot here)*

### Add Book

*(Add screenshot here)*

### Search Book

*(Add screenshot here)*

### Show All Books

*(Add screenshot here)*

---

## ▶️ Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/mayabargig/book-store-javafx.git
```

### 2. Open the project

Open the project using IntelliJ IDEA.

### 3. Start the servers

Run:

- ServerDriver

The application starts:

- 📚 Book Server (Port 34567)
- 👤 Customer Server (Port 34568)

### 4. Run the JavaFX Client

Launch the JavaFX application.

---

## 📚 Learning Objectives

This project demonstrates practical implementation of:

- Object-Oriented Programming (OOP)
- Client-Server Architecture
- Java Socket Programming
- JavaFX GUI Development
- Layered Software Architecture
- DAO Design Pattern
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

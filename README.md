# TaskTracker-CLI
Java CLI Task Tracker with JSON storage for managing tasks.
# Task Tracker CLI (Java)

A simple **Command Line Interface (CLI)** task management application written in **Java**.
This allows users to manage their tasks directly from the terminal while storing them persistently in a **JSON file**.

The application supports creating, updating, deleting, and tracking the status of tasks.

---

## Features

* Add new tasks
* Update existing tasks
* Delete tasks
* Mark tasks as **done**
* Mark tasks as **in-progress**
* List all tasks
* Filter tasks by status
* Persistent storage using a **JSON file**
* Automatically creates the JSON file if it doesn't exist

---

## Technologies Used

* Java
* Java Collections (`ArrayList`)
* File Handling (`java.nio.file`)
* CLI interaction (`Scanner`)
* JSON file formatting (manual implementation)

---

## Project Structure

```
TaskTracker/
│
├── Task.java
├── TaskTracker.java
├── tasks.json
└── README.md
```

---

## Task Properties

Each task contains the following information:

| Property    | Description                              |
| ----------- | ---------------------------------------- |
| id          | Unique identifier for each task          |
| description | Short description of the task            |
| status      | Current status (todo, in-progress, done) |
| createdAt   | Timestamp when the task was created      |
| updatedAt   | Timestamp when the task was last updated |

---

## How to Run

### 1. Clone the repository

```
git clone https://github.com/your-username/task-tracker-java.git
```

### 2. Navigate into the folder

```
cd task-tracker-java
```

### 3. Compile the program

```
javac Task.java TaskTracker.java
```

### 4. Run the program

```
java TaskTracker
```

---

## Example Usage

After running the program, a menu will appear:

```
1 Add task
2 Update task
3 Delete task
4 Mark task as done
5 Mark task as in progress
6 List all tasks
7 List tasks that are done
8 List tasks that are todo
9 List tasks that are in progress
```

Example workflow:

```
Add task → Enter description
List tasks → View current tasks
Mark task done → Update status
```

---

## Data Storage

Tasks are stored in a local file:

```
tasks.json
```

Example JSON structure:

```
[
 {
  "id": 1,
  "description": "Buy groceries",
  "status": "todo",
  "createdAt": "2026-03-14T12:00",
  "updatedAt": "2026-03-14T12:00"
 }
]
```

---

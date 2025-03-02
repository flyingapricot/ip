# Orange Chatbot - User Guide

## Introduction
**Orange** is a command-line-based chatbot designed to help users manage their tasks efficiently. 
Users can add different types of tasks, mark them as completed, delete them, find tasks, and check tasks on specific dates. 
Tasks are automatically saved and loaded from a file, ensuring persistence across sessions.

---
## Quick Start
1. Ensure you have **Java 11** or later installed on your computer.
2. Download the `Orange.jar` file.
3. Open a terminal or command prompt in the directory containing `Orange.jar`.
4. Run the chatbot using the command:
   ```sh
   java -jar Orange.jar
   ```
5. Start entering commands to interact with **Orange**.

---
## Features
### 1. Listing all tasks: `list`
Displays all tasks stored in Orange.

**Usage:**
```
list
```
**Example Output:**
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][X] Submit assignment (by: Mar 10 2024 23:59)
3. [E][ ] Team meeting (from: Mar 12 2024 14:00 to: Mar 12 2024 15:00)
```

---
### 2. Adding a To-Do task: `todo`
Adds a To-Do task to the list.

**Usage:**
```
todo TASK_DESCRIPTION
```
**Example:**
```
todo Read a book
```
**Expected Output:**
```
Got it. I've added this task:
[T][ ] Read a book
Now you have 4 tasks in the list.
```

---
### 3. Adding a Deadline task: `deadline`
Adds a Deadline task with a specified due date and time.

**Usage:**
```
deadline TASK_DESCRIPTION /by YYYY-MM-DD HH:mm
```
**Example:**
```
deadline Finish report /by 2024-03-15 18:00
```
**Expected Output:**
```
Got it. I've added this task:
[D][ ] Finish report (by: Mar 15 2024 18:00)
```

---
### 4. Adding an Event task: `event`
Adds an Event task with a specified start and end time.

**Usage:**
```
event TASK_DESCRIPTION /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm
```
**Example:**
```
event Conference /from 2024-04-01 09:00 /to 2024-04-01 17:00
```
**Expected Output:**
```
Got it. I've added this task:
[E][ ] Conference (from: Apr 01 2024 09:00 to: Apr 01 2024 17:00)
```

---
### 5. Marking a task as done: `mark`
Marks a specified task as completed.

**Usage:**
```
mark TASK_NUMBER
```
**Example:**
```
mark 2
```
**Expected Output:**
```
Nice! I've marked this task as done:
[D][X] Finish report (by: Mar 15 2024 18:00)
```

---
### 6. Unmarking a task: `unmark`
Marks a specified task as not completed.

**Usage:**
```
unmark TASK_NUMBER
```
**Example:**
```
unmark 2
```
**Expected Output:**
```
OK, I've marked this task as not done yet:
[D][ ] Finish report (by: Mar 15 2024 18:00)
```

---
### 7. Deleting a task: `delete`
Deletes a specified task from the list.

**Usage:**
```
delete TASK_NUMBER
```
**Example:**
```
delete 3
```
**Expected Output:**
```
Noted. I've removed this task:
[E][ ] Conference (from: Apr 01 2024 09:00 to: Apr 01 2024 17:00)
```

---
### 8. Finding tasks by keyword: `find`
Searches for tasks that contain a specific keyword.

**Usage:**
```
find KEYWORD
```
**Example:**
```
find report
```
**Expected Output:**
```
Here are the matching tasks in your list:
[D][ ] Finish report (by: Mar 15 2024 18:00)
```

---
### 9. Checking tasks on a specific date: `checkondate`
Lists all tasks that are due or end on a given date.

**Usage:**
```
checkondate YYYY-MM-DD
```
**Example:**
```
checkondate 2024-03-15
```
**Expected Output:**
```
Here are the tasks on Mar 15 2024:
[D][ ] Finish report (by: Mar 15 2024 18:00)
```

---
### 10. Exiting the program: `bye`
Terminates the chatbot session.

**Usage:**
```
bye
```
**Expected Output:**
```
Bye. Hope to see you again soon!
```

---
## Command Summary
| Command | Format |
|---------|--------|
| **list** | `list` |
| **todo** | `todo TASK_DESCRIPTION` |
| **deadline** | `deadline TASK_DESCRIPTION /by YYYY-MM-DD HH:mm` |
| **event** | `event TASK_DESCRIPTION /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm` |
| **mark** | `mark TASK_NUMBER` |
| **unmark** | `unmark TASK_NUMBER` |
| **delete** | `delete TASK_NUMBER` |
| **find** | `find KEYWORD` |
| **checkondate** | `checkondate YYYY-MM-DD` |
| **bye** | `bye` |

---
## Saving and Loading
- Orange automatically **saves** tasks to a file (`saved.csv`) after every command that modifies tasks.
- Tasks are **loaded** from `saved.csv` when the chatbot starts.

---
## Error Handling
If you enter an invalid command, Orange will prompt you with an appropriate error message.

Example:
```
invalidcommand
```
**Expected Output:**
```
No idea what that means, start with a keyword man! List of keywords: "mark", "unmark", "list", "todo", "event", "deadline", "delete", "checkondate", "find"
```

---
## Acknowledgements
- Inspired by the **Duke** chatbot project from NUS Software Engineering Education.
- Uses Java for implementation.


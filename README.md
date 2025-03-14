# Orange Chatbot

Orange is a command-line-based chatbot that helps users manage their tasks efficiently. It allows users to add, mark, unmark, delete, search, and view tasks. The tasks are automatically saved and loaded from a file, ensuring persistence across sessions.

## Features
- **Task Management**: Add To-Do, Deadline, and Event tasks.
- **Task Status**: Mark tasks as completed or uncompleted.
- **Task Search**: Search for tasks by keyword or check tasks on specific dates.
- **Persistent Storage**: Tasks are saved in a file and automatically loaded on startup.

## Commands
- **list**: View all tasks.
- **todo**: Add a To-Do task.
- **deadline**: Add a task with a deadline.
- **event**: Add an event task with a specified start and end time.
- **mark**: Mark a task as completed.
- **unmark**: Mark a task as incomplete.
- **delete**: Delete a task.
- **find**: Search for tasks by keyword.
- **checkondate**: View tasks on a specific date.
- **bye**: Exit the chatbot session.

## Saving and Loading
- Tasks are automatically saved to a file (saved.csv) after any modification and loaded when the chatbot starts.

## Error Handling
- Invalid commands will prompt appropriate error messages with guidance on how to use the correct commands.


## Quick Start
1. Ensure you have **Java 17** or later installed.
2. Download the `Orange.jar` file.
3. Open a terminal in the directory containing `Orange.jar`.
4. Run the chatbot using:
   ```bash
   java -jar Orange.jar

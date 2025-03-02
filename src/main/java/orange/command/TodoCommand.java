
package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.TaskList;
import orange.task.Todo;
/**
 * Handles the execution of the todo command.
 * This command creates a new todo task and adds it to the task list.
 *
 * @see Command
 * @see Todo
 * @see TaskList
 * @see Parser
 * @see Storage
 * @see Ui
 */
public class TodoCommand extends Command {
    /**
     * Executes the todo command.
     * <p>
     * Parses the user input to extract the todo task description,
     * creates a new Todo task, adds it to the task list, updates the UI,
     * and saves the task to the storage file.
     * </p>
     *
     * @throws OrangeException If there is an error in parsing the todo command.
     */
    @Override
    public void executeCommand() throws OrangeException {
        String toDoDescription = Parser.parseTodo();
        Todo newToDo = new Todo(toDoDescription);
        TaskList.getInstance().addTask(newToDo);
        Ui.showAddTask(newToDo);
        Storage.updateTaskFile();
    }
}
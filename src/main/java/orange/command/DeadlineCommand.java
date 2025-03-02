/**
 * Handles the execution of the deadline command.
 * This command creates a new deadline task and adds it to the task list.
 *
 * @see Command
 * @see Deadline
 * @see TaskList
 * @see Parser
 * @see Storage
 * @see Ui
 */
package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.Deadline;
import orange.task.TaskList;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    /**
     * Executes the deadline command.
     * <p>
     * Parses the user input to extract the deadline task description and due date,
     * creates a new Deadline task, adds it to the task list, updates the UI,
     * and saves the task to the storage file.
     * </p>
     *
     * @throws OrangeException If there is an error in parsing the deadline command.
     */
    @Override
    public void executeCommand() throws OrangeException {
        ArrayList<String> deadlineTaskAndDoBy = Parser.parseDeadline();
        Deadline newDeadline = new Deadline(deadlineTaskAndDoBy.get(0), deadlineTaskAndDoBy.get(1));
        TaskList.getInstance().addTask(newDeadline);
        Ui.showAddTask(newDeadline);
        Storage.updateTaskFile();
    }
}

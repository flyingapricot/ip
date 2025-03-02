/**
 * Handles the execution of the delete command.
 * This command deletes a specified task from the task list.
 *
 * @see Command
 * @see Task
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
import orange.task.Task;
import orange.task.TaskList;

import static orange.exception.ExceptionType.INVALID_TASKNUMBER;

public class DeleteCommand extends Command {
    /**
     * Executes the delete command.
     * <p>
     * Parses the user input to extract the task number, retrieves the corresponding task,
     * removes it from the task list, updates the UI, and saves the updated task list to storage.
     * </p>
     *
     * @throws OrangeException If the task number is invalid.
     */
    @Override
    public void executeCommand() throws OrangeException {
        int taskNumber = Parser.parseDelete();
        Task taskToDelete;
        try {
            taskToDelete = TaskList.getInstance().getTasks().get(taskNumber);
            TaskList.getInstance().deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        Ui.showDeleteTask(taskToDelete);
        Storage.updateTaskFile();
    }
}

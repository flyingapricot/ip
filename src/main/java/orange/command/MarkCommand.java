
package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;

import static orange.exception.ExceptionType.INVALID_TASKNUMBER;
/**
 * Handles the execution of the mark command.
 * This command marks a specified task as completed.
 *
 * @see Command
 * @see Task
 * @see TaskList
 * @see Parser
 * @see Storage
 * @see Ui
 */
public class MarkCommand extends Command {
    /**
     * Executes the mark command.
     * <p>
     * Parses the user input to extract the task number, updates the completion status of the task,
     * updates the UI, and saves the updated task list to storage.
     * </p>
     *
     * @throws OrangeException If the task number is invalid.
     */
    @Override
    public void executeCommand() throws OrangeException {
        int taskNumber = Parser.parseMark();
        try {
            TaskList.getInstance().updateCompletionStatus(taskNumber, true);
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        Ui.showMarkTask(TaskList.getInstance().getTasks().get(taskNumber));
        Storage.updateTaskFile();
    }
}
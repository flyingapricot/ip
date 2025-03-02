/**
 * Handles the execution of the unmark command.
 * This command marks a specified task as not completed.
 *
 * @see Command
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
import orange.task.TaskList;

import static orange.exception.ExceptionType.INVALID_TASKNUMBER;

public class UnmarkCommand extends Command {
    /**
     * Executes the unmark command.
     * <p>
     * Parses the user input to extract the task number, updates the completion status of the task,
     * updates the UI, and saves the updated task list to storage.
     * </p>
     *
     * @throws OrangeException If the task number is invalid.
     */
    @Override
    public void executeCommand() throws OrangeException {
        int taskNumber = Parser.parseUnMark();
        try {
            TaskList.getInstance().updateCompletionStatus(taskNumber, false);
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        Ui.showUnmarkTask(TaskList.getInstance().getTasks().get(taskNumber));
        Storage.updateTaskFile();
    }
}
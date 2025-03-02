
package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.task.Task;
import orange.task.TaskList;

import java.util.ArrayList;
/**
 * Handles the execution of the find command.
 * This command searches for tasks in the task list that match a given keyword.
 *
 * @see Command
 * @see Task
 * @see TaskList
 * @see Parser
 * @see Ui
 */
public class FindCommand extends Command {
    /**
     * Executes the find command.
     * <p>
     * Parses the user input to extract the search keyword, searches for matching tasks in the task list,
     * and displays the results in the UI.
     * </p>
     *
     * @throws OrangeException If there is an error in parsing the find command.
     */
    @Override
    public void executeCommand() throws OrangeException {
        String taskToFind = Parser.parseFind();

        // Search the task list
        ArrayList<Task> tasksWithMatchingName = new ArrayList<>();
        for (Task task : TaskList.getInstance().getTasks()) {
            if (task.getDescription().contains(taskToFind)) {
                tasksWithMatchingName.add(task);
            }
        }

        // Send results to UI
        Ui.showFoundTasks(tasksWithMatchingName);
    }
}
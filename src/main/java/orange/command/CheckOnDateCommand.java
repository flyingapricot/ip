/**
 * Handles the execution of the "check on date" command.
 * This command finds and lists all tasks that are scheduled for a specific date.
 *
 * @see Command
 * @see Task
 * @see TaskList
 * @see Parser
 * @see Ui
 * @see DateParser
 */
package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.DateParser;
import orange.parser.Parser;
import orange.task.Deadline;
import orange.task.Events;
import orange.task.Task;
import orange.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckOnDateCommand extends Command {
    /**
     * Executes the "check on date" command.
     * <p>
     * Parses the user input to extract the date, searches for tasks in the task list
     * that are scheduled on the specified date, and displays the results in the UI.
     * </p>
     *
     * @throws OrangeException If there is an error in parsing the date.
     */
    @Override
    public void executeCommand() throws OrangeException {
        LocalDate checkDate = Parser.parseCheckTasksOnDate();

        // Search for tasks matching the specified date
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : TaskList.getInstance().getTasks()) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getLocalDateTime().toLocalDate().isEqual(checkDate)) {
                    matchingTasks.add(d);
                }
            } else if (task instanceof Events) {
                Events e = (Events) task;
                System.out.println(DateParser.getStringFromLocalDate(e.getEndLocalDateTime().toLocalDate()));
                if (e.getEndLocalDateTime().toLocalDate().isEqual(checkDate)) {
                    matchingTasks.add(e);
                }
            }
        }

        // Display matching tasks in the UI
        Ui.showMatchingTasks(matchingTasks, checkDate);
    }
}
/**
 * Handles the execution of the event command.
 * This command creates a new event task and adds it to the task list.
 *
 * @see Command
 * @see Events
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
import orange.task.Events;
import orange.task.TaskList;

import java.util.ArrayList;

public class EventCommand extends Command {
    /**
     * Executes the event command.
     * <p>
     * Parses the user input to extract the event task description, start time, and end time,
     * creates a new Event task, adds it to the task list, updates the UI,
     * and saves the task to the storage file.
     * </p>
     *
     * @throws OrangeException If there is an error in parsing the event command.
     */
    @Override
    public void executeCommand() throws OrangeException {
        ArrayList<String> eventTaskStartOnDoBy = Parser.parseEvent();
        Events newEvent = new Events(eventTaskStartOnDoBy.get(0), eventTaskStartOnDoBy.get(1), eventTaskStartOnDoBy.get(2));
        TaskList.getInstance().addTask(newEvent);
        Ui.showAddTask(newEvent);
        Storage.updateTaskFile();
    }
}

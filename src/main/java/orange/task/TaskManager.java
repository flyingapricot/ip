package orange.task;

import orange.command.CheckOnDateCommand;
import orange.command.CommandHandler;
import orange.command.DeadlineCommand;
import orange.command.DeleteCommand;
import orange.command.EventCommand;
import orange.command.FindCommand;
import orange.command.ListCommand;
import orange.command.MarkCommand;
import orange.command.TodoCommand;
import orange.command.UnmarkCommand;

import java.util.HashMap;

/**
 * Manages the mapping of commands to their respective handlers. The TaskManager class stores a
 * command map and provides methods to retrieve and execute specific task commands.
 *
 * @see CommandHandler
 */
public class TaskManager {
    /** A mapping of command keywords to their respective command handlers. */
    private HashMap<String, CommandHandler> commandMap;

    /** The current task number being processed. */
    private Integer taskNumber;

    /**
     * Constructs a TaskManager instance and initializes the command map.
     *
     * <p>The command map associates command strings with their respective command handlers using
     * method references.
     */
    public TaskManager() {
        commandMap = new HashMap<>();
        commandMap.put(
                "list",
                new ListCommand()
                        ::executeCommand); // Method reference to point to executeCommand method
        commandMap.put("unmark", new UnmarkCommand()::executeCommand);
        commandMap.put("mark", new MarkCommand()::executeCommand);
        commandMap.put("todo", new TodoCommand()::executeCommand);
        commandMap.put("deadline", new DeadlineCommand()::executeCommand);
        commandMap.put("event", new EventCommand()::executeCommand);
        commandMap.put("delete", new DeleteCommand()::executeCommand);
        commandMap.put("find", new FindCommand()::executeCommand);
        commandMap.put("checkondate", new CheckOnDateCommand()::executeCommand);
    }

    /**
     * Updates the current task number being processed.
     *
     * @param number The new task number.
     */
    public void updateTaskNumber(int number) {
        this.taskNumber = number;
    }

    /**
     * Retrieves the command handler for the specified task command.
     *
     * @param task The command keyword.
     * @return The corresponding CommandHandler, or null if the command is not found.
     */
    public CommandHandler getTask(String task) {
        return commandMap.getOrDefault(task, null);
    }
}

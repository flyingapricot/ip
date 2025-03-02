package orange.task;

import orange.command.*;

import java.util.HashMap;

public class TaskManager {
    private HashMap<String, CommandHandler> commandMap;
    private Integer taskNumber;

    public void updateTaskNumber(int number) {
        this.taskNumber = number;
    }

    public TaskManager() {
        commandMap = new HashMap<>();
        commandMap.put("list", new ListCommand()::executeCommand); //Method reference to point to executeCommand method
        commandMap.put("unmark", new UnmarkCommand()::executeCommand);
        commandMap.put("mark",  new MarkCommand()::executeCommand);
        commandMap.put("todo", new TodoCommand()::executeCommand);
        commandMap.put("deadline", new DeadlineCommand()::executeCommand);
        commandMap.put("event", new EventCommand()::executeCommand);
        commandMap.put("delete", new DeleteCommand()::executeCommand);
        commandMap.put("find", new FindCommand()::executeCommand);
        commandMap.put("checkondate", new CheckOnDateCommand()::executeCommand);
    }

    public CommandHandler getTask(String task) {
        return commandMap.getOrDefault(task, null);
    }
}

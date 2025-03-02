package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.Deadline;
import orange.task.Events;
import orange.task.TaskList;

import java.util.ArrayList;

public class EventCommand extends Command{
    @Override
    public void executeCommand() throws OrangeException {
        ArrayList<String> eventTaskStartOnDoBy = Parser.parseEvent();
        Events newEvent = new Events(eventTaskStartOnDoBy.get(0),eventTaskStartOnDoBy.get(1),eventTaskStartOnDoBy.get(2));
        TaskList.getInstance().addTask(newEvent);
        Ui.showAddTask(newEvent);
        Storage.updateTaskFile();
    }
}

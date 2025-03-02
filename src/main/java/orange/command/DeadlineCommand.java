package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.DateParser;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.Deadline;
import orange.task.TaskList;
import orange.task.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeadlineCommand extends Command{
    @Override
    public void executeCommand() throws OrangeException {
        ArrayList<String> deadlineTaskAndDoBy = Parser.parseDeadline();
        Deadline newDeadline = new Deadline(deadlineTaskAndDoBy.get(0), deadlineTaskAndDoBy.get(1));
        TaskList.getInstance().addTask(newDeadline);
        Ui.showAddTask(newDeadline);
        Storage.updateTaskFile();
    }
}

package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.TaskList;
import orange.task.Todo;

public class TodoCommand extends Command{

    @Override
    public void executeCommand() throws OrangeException {
        String toDoDescription = Parser.parseTodo();
        Todo newToDo = new Todo(toDoDescription);
        TaskList.getInstance().addTask(newToDo);
        Ui.showAddTask(newToDo);
        Storage.updateTaskFile();
    }
}

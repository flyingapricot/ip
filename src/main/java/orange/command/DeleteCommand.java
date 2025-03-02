package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;

import static orange.exception.ExceptionType.INVALID_TASKNUMBER;

public class DeleteCommand extends Command{

    @Override
    public void executeCommand() throws OrangeException {
        //For a given task number mark it as completed
        int taskNumber = Parser.parseDelete();
        Task taskToDelete = null;
        try {
            taskToDelete = TaskList.getInstance().getTasks().get(taskNumber);
            TaskList.getInstance().deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException i) {
            //Invalid task number
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        Ui.showDeleteTask(taskToDelete);

        //Update the save file
        Storage.updateTaskFile();
    }
}

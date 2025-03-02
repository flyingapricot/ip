package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.Task;
import orange.task.TaskList;

import static orange.exception.ExceptionType.INVALID_TASKNUMBER;
import static orange.parser.Parser.parseMark;

public class MarkCommand extends Command{


    @Override
    public void executeCommand() throws OrangeException {
        //For a given task number mark it as completed
        int taskNumber = Parser.parseMark();

        try {
            TaskList.getInstance().updateCompletionStatus(taskNumber,true);
        } catch (IndexOutOfBoundsException i) {
            //Invalid task number
            throw new OrangeException(INVALID_TASKNUMBER);
        }
        Ui.showMarkTask(TaskList.getInstance().getTasks().get(taskNumber));
        //Update the save file
        Storage.updateTaskFile();
    }
}

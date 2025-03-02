package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.TaskList;

import static orange.exception.ExceptionType.INVALID_TASKNUMBER;

public class UnmarkCommand extends Command{


    @Override
    public void executeCommand() throws OrangeException {
        //For a given task number mark it as not completed
        int taskNumber = Parser.parseUnMark();
        try {
            TaskList.getInstance().updateCompletionStatus(taskNumber,false);
        } catch (IndexOutOfBoundsException i) {
            //Invalid task number
            throw new OrangeException(INVALID_TASKNUMBER);
        }
        Ui.showUnmarkTask(TaskList.getInstance().getTasks().get(taskNumber));
        //Update the task file
        Storage.updateTaskFile();
    }
}

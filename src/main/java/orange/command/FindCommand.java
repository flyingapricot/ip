package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.task.Task;
import orange.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{

    @Override
    public void executeCommand() throws OrangeException {
        String taskToFind = Parser.parseFind();
        //Search the task list
        ArrayList<Task> tasksWithMatchingName = new ArrayList<>();

        for(Task task: TaskList.getInstance().getTasks()) {
            if(task.getDescription().contains(taskToFind)) {
                tasksWithMatchingName.add(task);
            }
        }

        //Send to UI
        Ui.showFoundTasks(tasksWithMatchingName);
    }
}

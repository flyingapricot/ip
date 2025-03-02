package orange.command;

import orange.Ui.Ui;
import orange.exception.OrangeException;
import orange.parser.DateParser;
import orange.parser.Parser;
import orange.task.Deadline;
import orange.task.Events;
import orange.task.Task;
import orange.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckOnDateCommand extends Command{

    @Override
    public void executeCommand() throws OrangeException {
        LocalDate checkDate = Parser.parseCheckTasksOnDate();

        //Compare against all dates in the list, and list out the dates that are matching with the checkDate
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for(Task task: TaskList.getInstance().getTasks()) {
            if(task.getClass().getName().equals("orange.task.Deadline")) {
                Deadline d = (Deadline) task;
                if(d.getLocalDateTime().toLocalDate().isEqual(checkDate)) {
                    matchingTasks.add(d);
                }
            }else if(task.getClass().getName().equals("orange.task.Events")) {

                Events e = (Events) task;
                System.out.println(DateParser.getStringFromLocalDate(e.getEndLocalDateTime().toLocalDate()));
                if(e.getEndLocalDateTime().toLocalDate().isEqual(checkDate)) {
                    matchingTasks.add(e);
                }
            }
        }

        //Send to UI
        Ui.showMatchingTasks(matchingTasks,checkDate);

    }
}

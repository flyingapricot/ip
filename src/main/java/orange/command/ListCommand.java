package orange.command;

import orange.Ui.Ui;

public class ListCommand extends Command{
    public ListCommand() {}


    @Override
    public void executeCommand() {
        Ui.showListOfTasks();
    }
}

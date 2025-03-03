    package orange.command;

    import orange.Ui.Ui;

    /**
     * Handles the execution of the list command. This command displays all tasks in the task list.
     *
     * @see Command
     * @see Ui
     */
    public class ListCommand extends Command {
        /** Constructs a ListCommand instance. */
        public ListCommand() {}

        /**
         * Executes the list command.
         *
         * <p>Calls the UI method to display all tasks in the task list.
         */
        @Override
        public void executeCommand() {
            Ui.showListOfTasks();
        }
    }

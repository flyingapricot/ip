    package orange.command;

    import orange.exception.OrangeException;

    /**
     * Represents an abstract command that can be executed. All specific command classes must extend
     * this class and implement the executeCommand method.
     *
     * @see OrangeException
     */
    public abstract class Command {
        /**
         * Executes the command.
         *
         * @throws OrangeException If an error occurs during command execution.
         */
        public abstract void executeCommand() throws OrangeException;
    }

package orange.command;

import orange.exception.OrangeException;

@FunctionalInterface
public interface CommandHandler {
    void execute() throws OrangeException;
}


package orange.command;

import orange.exception.OrangeException;
/**
 * Functional interface for handling command execution.
 * Implementing classes must provide an implementation for the execute method.
 *
 * @see OrangeException
 */
@FunctionalInterface
public interface CommandHandler {
    /**
     * Executes the command.
     *
     * @throws OrangeException If an error occurs during command execution.
     */
    void execute() throws OrangeException;
}
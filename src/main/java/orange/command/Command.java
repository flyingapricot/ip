package orange.command;
import orange.exception.OrangeException;


public abstract class Command {
    public abstract void executeCommand() throws OrangeException;
}

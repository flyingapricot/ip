package orange;

import orange.Ui.Ui;
import orange.command.CommandHandler;
import orange.exception.OrangeException;
import orange.parser.Parser;
import orange.storage.Storage;
import orange.task.TaskManager;

import java.util.Scanner;

/**
 * The Orange class represents the main entry point of the Orange chatbot. It initializes the
 * necessary components and manages user input and command execution.
 *
 * <p>The chatbot continuously listens for user input and processes commands until the user types
 * "bye" to exit.
 *
 * @see Storage
 * @see TaskManager
 * @see Parser
 */
public class Orange {
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private TaskManager taskManager;

    /**
     * Constructs an instance of the Orange chatbot. Initializes the storage and task management
     * components.
     */
    public Orange() {
        try {
            storage = new Storage();
        } catch (OrangeException o) {
            System.out.println(o.getCustomMessage());
        }
        taskManager = new TaskManager();
    }

    /**
     * Starts the chatbot and processes user input in a loop.
     *
     * <p>The chatbot scans for commands, executes them, and handles exceptions if any errors occur
     * during execution.
     */
    public void run() {
        Scanner in = new Scanner(System.in);
        Ui.greeting();
        String line = "";
        line = in.nextLine();
        parser = new Parser(line);

        // Main chatbot loop
        while (!line.equals("bye")) {
            String commandWord = parser.scanForCommandWord();
            CommandHandler command = taskManager.getTask(commandWord);

            if (command != null) {
                try {
                    command.execute();
                } catch (OrangeException e) {
                    // Error message
                    System.out.println(e.getCustomMessage());
                }
            }
            line = in.nextLine();
            parser.updateLine(line);
        }

        Ui.goodbye();
    }

    /**
     * The main method that starts the Orange chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Orange o = new Orange();
        o.run();
    }
}

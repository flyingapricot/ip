/**
 * Parses user input and extracts command information for processing.
 * This class handles parsing different command types and extracting relevant data.
 *
 * @see OrangeException
 * @see Ui
 */
package orange.parser;

import orange.exception.ExceptionType;
import orange.exception.OrangeException;
import orange.Ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static orange.exception.ExceptionType.*;

public class Parser {
    /**
     * A set of valid command keywords.
     */
    private static final HashSet<String> commands = new HashSet<>(Arrays.asList("mark", "unmark", "list", "todo", "event", "deadline", "delete", "find"));

    /**
     * Stores the user input command line.
     */
    protected static String line;

    /**
     * Constructs a Parser instance with the given user input.
     *
     * @param line The user input command line.
     */
    public Parser(String line) {
        Parser.line = line;
    }

    /**
     * Updates the stored command line with new user input.
     *
     * @param line The new user input command line.
     */
    public void updateLine(String line) {
        Parser.line = line;
    }

    /**
     * Splits the user input into individual components.
     *
     * @return An array of strings representing the user input components.
     */
    private String[] getUserInput() {
        return line.split(" ");
    }

    /**
     * Extracts and validates the command word from the user input.
     *
     * @return The extracted command word if valid, otherwise an empty string.
     */
    public String scanForCommandWord() {
        String commandWord = getUserInput()[0];
        try {
            if (!commands.contains(commandWord)) throw new OrangeException(ExceptionType.UNKNOWN_COMMAND);
        } catch (OrangeException o) {
            Ui.showError(o.getCustomMessage());
            return "";
        }
        return commandWord;
    }

    /**
     * Parses a Todo task command and extracts the task description.
     *
     * @return The extracted todo task description.
     * @throws OrangeException If the todo task description is missing.
     */
    public static String parseTodo() throws OrangeException {
        try {
            String TodoTask = (line.substring(5)).trim();
            if (TodoTask.isEmpty()) {
                throw new OrangeException(MISSING_TODO_DESCRIPTION);
            }
            return TodoTask;
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(MISSING_TODO_DESCRIPTION);
        }
    }

    /**
     * Parses a Deadline task command and extracts the task description and due date.
     *
     * @return A list containing the task description and due date.
     * @throws OrangeException If the deadline format is incorrect or missing.
     */
    public static ArrayList<String> parseDeadline() throws OrangeException {
        int position = line.indexOf("/by");
        if (position == -1) throw new OrangeException(MISSING_DEADLINE_BYWORD);

        String deadlineTask = "", doTaskBy = "";
        try {
            deadlineTask = line.substring(8, position).trim();
            if (deadlineTask.isEmpty()) {
                throw new OrangeException(MISSING_DEADLINE_DESCRIPTION);
            }
            doTaskBy = line.substring(position + 4).trim();
            if (doTaskBy.isEmpty()) {
                throw new OrangeException(MISSING_DEADLINE_DOBY);
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_DEADLINE_DOBY);
        }
        return new ArrayList<>(Arrays.asList(deadlineTask, doTaskBy));
    }

    /**
     * Parses an Event task command and extracts the task description, start time, and end time.
     *
     * @return A list containing the task description, start time, and end time.
     * @throws OrangeException If the event format is incorrect or missing.
     */
    public static ArrayList<String> parseEvent() throws OrangeException {
        int fromPosition = line.indexOf("/from");
        if (fromPosition == -1) throw new OrangeException(MISSING_EVENT_FROMWORD);

        int byPosition = line.indexOf("/to");
        if (byPosition == -1) throw new OrangeException(MISSING_EVENT_TOWORD);

        String eventTask = "", startEventBy = "", doEventBy = "";
        try {
            eventTask = line.substring(5, fromPosition).trim();
            startEventBy = line.substring(fromPosition + 5, byPosition).trim();
            doEventBy = line.substring(byPosition + 3).trim();
            if (eventTask.isEmpty() || startEventBy.isEmpty() || doEventBy.isEmpty()) {
                throw new OrangeException(MISSING_EVENT_DESCRIPTION);
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_DESCRIPTION);
        }
        return new ArrayList<>(Arrays.asList(eventTask, startEventBy, doEventBy));
    }

    /**
     * Parses and extracts the task number for marking as done.
     *
     * @return The task number to mark.
     * @throws OrangeException If the task number is missing or invalid.
     */
    public static int parseMark() throws OrangeException {
        return parseIntegerCommand(4);
    }

    /**
     * Parses and extracts the task number for unmarking as done.
     *
     * @return The task number to unmark.
     * @throws OrangeException If the task number is missing or invalid.
     */
    public static int parseUnMark() throws OrangeException {
        return parseIntegerCommand(6);
    }

    /**
     * Parses and extracts the task number for deletion.
     *
     * @return The task number to delete.
     * @throws OrangeException If the task number is missing or invalid.
     */
    public static int parseDelete() throws OrangeException {
        return parseIntegerCommand(6);
    }

    /**
     * Parses and extracts the search keyword for finding tasks.
     *
     * @return The search keyword.
     * @throws OrangeException If the keyword is missing.
     */
    public static String parseFind() throws OrangeException {
        return parseStringCommand(4, INVALID_FINDTASK);
    }

    /**
     * Helper method to parse an integer command.
     */
    private static int parseIntegerCommand(int startIndex) throws OrangeException {
        try {
            String numberStr = line.substring(startIndex).trim();
            return Integer.parseInt(numberStr) - 1;
        } catch (Exception e) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }
    }

    /**
     * Helper method to parse a string command.
     */
    private static String parseStringCommand(int startIndex, ExceptionType exceptionType) throws OrangeException {
        String result = line.substring(startIndex).trim();
        if (result.isEmpty()) {
            throw new OrangeException(exceptionType);
        }
        return result;
    }
}

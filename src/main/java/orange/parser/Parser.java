package orange.parser;

import orange.Ui.Ui;
import orange.exception.ExceptionType;
import orange.exception.OrangeException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static orange.exception.ExceptionType.*;

/**
 * Parses user input and extracts command information for processing. This class handles parsing
 * different command types and extracting relevant data.
 *
 * @see OrangeException
 * @see Ui
 * @see DateParser
 */
public class Parser {
    // In Java, instance variables, static variables, and constants (like private static final)
    // should be declared in a certain order, generally:
    // Instance variables (non-static fields).
    // Static variables (static fields, like your commands field).
    // Constants (typically declared static final).
    // Constructors.
    // Methods.

    /** Stores the user input command line. */
    protected static String line;

    /** A set of valid command keywords. */
    private static final HashSet<String> commands =
            new HashSet<>(
                    Arrays.asList(
                            "mark",
                            "unmark",
                            "list",
                            "todo",
                            "event",
                            "deadline",
                            "delete",
                            "checkondate",
                            "find"));

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
            if (!commands.contains(commandWord)) {
                throw new OrangeException(ExceptionType.UNKNOWN_COMMAND);
            }
        } catch (OrangeException o) {
            Ui.showError(o.getCustomMessage());
            return "";
        }
        return commandWord;
    }

    /**
     * Parses a "todo" task command and extracts the task description.
     *
     * @return The extracted todo task description.
     * @throws OrangeException If the todo task description is missing.
     */
    public static String parseTodo() throws OrangeException {
        String TodoTask = "";
        try {
            TodoTask = (line.substring(5)).trim(); // Remove any trailing spaces
            if (TodoTask.isEmpty()) {
                throw new OrangeException(MISSING_TODO_DESCRIPTION);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(MISSING_TODO_DESCRIPTION);
        }

        return TodoTask;
    }

    /**
     * Parses a "deadline" task command and extracts the task description and due date.
     *
     * @return A list containing the task description and due date.
     * @throws OrangeException If the deadline format is incorrect or missing.
     */
    public static ArrayList<String> parseDeadline() throws OrangeException {
        int position = line.indexOf("/by");
        if (position == -1) {
            throw new OrangeException(MISSING_DEADLINE_BYWORD);
        }

        int oldPosition = position;
        position = line.indexOf("/by", position + 1);
        if (position != -1) {
            throw new OrangeException(EXTRA_BY_IN_DEADLINE);
        }
        position = oldPosition;

        String deadlineTask = "";
        String doTaskBy = "";

        try {
            deadlineTask = line.substring(8, position).trim();
            if (deadlineTask.isEmpty()) {
                throw new OrangeException(
                        MISSING_DEADLINE_DESCRIPTION); // Throw exception if the task is empty
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_DEADLINE_DESCRIPTION);
        }

        try {
            doTaskBy = line.substring(position + 4).trim();
            if (doTaskBy.isEmpty()) {
                throw new OrangeException(
                        MISSING_DEADLINE_DOBY); // Throw exception if the task is empty
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_DEADLINE_DOBY);
        }

        return new ArrayList<>(Arrays.asList(deadlineTask, doTaskBy));
    }

    /**
     * Parses an "event" task command and extracts the task description, start time, and end time.
     *
     * @return A list containing the task description, start time, and end time.
     * @throws OrangeException If the event format is incorrect or missing.
     */
    public static ArrayList<String> parseEvent() throws OrangeException {

        int fromPosition = line.indexOf("/from");
        if (fromPosition == -1) {
            throw new OrangeException(MISSING_EVENT_FROMWORD);
        }

        int oldFromPosition = fromPosition;
        fromPosition = line.indexOf("/by", fromPosition + 1);
        if (fromPosition != -1) {
            throw new OrangeException(EXTRA_FROM_IN_EVENT);
        }
        fromPosition = oldFromPosition;

        int byPosition = line.indexOf("/to");
        if (byPosition == -1) {
            throw new OrangeException(MISSING_EVENT_TOWORD);
        }

        int oldByPosition = byPosition;
        byPosition = line.indexOf("/by", byPosition + 1);
        if (byPosition != -1) {
            throw new OrangeException(EXTRA_TO_IN_EVENT);
        }
        byPosition = oldByPosition;

        String eventTask = "";
        String doEventBy = "";
        String startEventBy = "";

        try {
            eventTask = line.substring(5, fromPosition).trim();
            if (eventTask.isEmpty()) {
                throw new OrangeException(
                        MISSING_EVENT_DESCRIPTION); // Throw exception if the task is empty
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_DESCRIPTION);
        }

        try {
            startEventBy = line.substring(fromPosition + 5, byPosition).trim();
            if (startEventBy.isEmpty()) {
                throw new OrangeException(
                        MISSING_EVENT_STARTON); // Throw exception if the task is empty
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_STARTON);
        }

        try {
            doEventBy = line.substring(byPosition + 4).trim();
            if (doEventBy.isEmpty()) {
                throw new OrangeException(
                        MISSING_EVENT_DOBY); // Throw exception if the task is empty after trimming
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_DOBY);
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
        String taskToMarkString = "";
        int taskToMark = -1;
        try {
            taskToMarkString = line.substring(4).trim(); // Remove any trailing spaces
            if (taskToMarkString.isEmpty()) {
                throw new OrangeException(INVALID_TASKNUMBER);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        try {
            taskToMark = Integer.parseInt(taskToMarkString);
        } catch (NumberFormatException n) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }
        return taskToMark - 1;
    }

    /**
     * Parses and extracts the task number for unmarking as done.
     *
     * @return The task number to unmark.
     * @throws OrangeException If the task number is missing or invalid.
     */
    public static int parseUnMark() throws OrangeException {
        String taskToUnMarkString = "";
        int taskToUnMark = -1;
        try {
            taskToUnMarkString = line.substring(6).trim(); // Remove any trailing spaces
            if (taskToUnMarkString.isEmpty()) {
                throw new OrangeException(INVALID_TASKNUMBER);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        try {
            taskToUnMark = Integer.parseInt(taskToUnMarkString);
        } catch (NumberFormatException n) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        return taskToUnMark - 1;
    }

    /**
     * Parses and extracts the task number for deletion.
     *
     * @return The task number to delete.
     * @throws OrangeException If the task number is missing or invalid.
     */
    public static int parseDelete() throws OrangeException {
        String taskToDeleteString = "";
        int taskToDelete = -1;
        try {
            taskToDeleteString = line.substring(6).trim(); // Remove any trailing spaces
            if (taskToDeleteString.isEmpty()) {
                throw new OrangeException(INVALID_TASKNUMBER);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        try {
            taskToDelete = Integer.parseInt(taskToDeleteString);
        } catch (NumberFormatException n) {
            throw new OrangeException(INVALID_TASKNUMBER);
        }

        return taskToDelete - 1;
    }

    /**
     * Parses and extracts the search keyword for finding tasks.
     *
     * @return The search keyword.
     * @throws OrangeException If the keyword is missing.
     */
    public static String parseFind() throws OrangeException {
        String taskToFind = "";
        try {
            taskToFind = line.substring(4).trim(); // Remove any trailing spaces
            if (taskToFind.isEmpty()) {
                throw new OrangeException(INVALID_FINDTASK);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(INVALID_FINDTASK);
        }

        return taskToFind;
    }
    /**
     * Parses and extracts the date for checking tasks scheduled on that date.
     *
     * @return The parsed LocalDate object representing the date.
     * @throws OrangeException If the date is missing or invalid.
     */
    public static LocalDate parseCheckTasksOnDate() throws OrangeException {
        String givenDate = "";
        try {
            givenDate = line.substring(11).trim();
            if (givenDate.isEmpty()) {
                throw new OrangeException(
                        MISSING_CHECKON_DATE); // Throw exception if the task is empty after
            }
        } catch (IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_CHECKON_DATE);
        }
        return DateParser.getDateObject(givenDate);
    }
}

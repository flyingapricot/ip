package orange.parser;

import orange.exception.ExceptionType;
import orange.exception.OrangeException;
import orange.Ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static orange.exception.ExceptionType.*;

public class Parser {
    private static final HashSet<String> commands = new HashSet<>(Arrays.asList("mark","unmark","list","todo","event","deadline","delete","checkondate","find"));
    protected static String line;

    public Parser(String line) {
        Parser.line = line;
    }

    public void updateLine(String line) {
        Parser.line = line;
    }

    private String[] getUserInput() {
        return line.split(" ");
    }

    public String scanForCommandWord() {
        //Any command given to the chatbot must start with one of the command keywords defined in the commands hashset
        String commandWord = getUserInput()[0];

        //Try Catch Block to catch no keyword used
        try{
            if(!commands.contains(commandWord)) throw new OrangeException(ExceptionType.UNKNOWN_COMMAND);
        }catch(OrangeException o) {
            Ui.showError(o.getCustomMessage());
            return "";
        }

        return commandWord; //Valid command word detected
    }

    //Parse a todo task
    public static String parseTodo() throws OrangeException{
        String TodoTask = "";
        try {
            TodoTask = (line.substring(5)).trim(); //Remove any trailing spaces
            if(TodoTask.isEmpty()) {
                throw new OrangeException(MISSING_TODO_DESCRIPTION);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(MISSING_TODO_DESCRIPTION);
        }

        return TodoTask;
    }

    public static ArrayList<String> parseDeadline() throws OrangeException{
        int position = line.indexOf("/by");
        if(position == -1) throw new OrangeException(MISSING_DEADLINE_BYWORD);

        int oldPosition = position;
        position = line.indexOf("/by",position+1);
        if(position != -1) throw new OrangeException(EXTRA_BY_IN_DEADLINE);
        position = oldPosition;

        String deadlineTask = "";
        String doTaskBy = "";

        try {
            deadlineTask = line.substring(8, position).trim();
            if (deadlineTask.isEmpty()) {
                throw new OrangeException(MISSING_DEADLINE_DESCRIPTION);  // Throw exception if the task is empty after trimming
            }
        } catch(IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_DEADLINE_DESCRIPTION);
        }

        try {
            doTaskBy = line.substring(position+4).trim();
            if (doTaskBy.isEmpty()) {
                throw new OrangeException(MISSING_DEADLINE_DOBY);  // Throw exception if the task is empty after trimming
            }
        } catch(IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_DEADLINE_DOBY);
        }


        return new ArrayList<>(Arrays.asList(deadlineTask, doTaskBy));
    }

    public static ArrayList<String> parseEvent() throws OrangeException{

        int fromPosition = line.indexOf("/from");
        if(fromPosition == -1) throw new OrangeException(MISSING_EVENT_FROMWORD);

        int oldFromPosition = fromPosition;
        fromPosition = line.indexOf("/by",fromPosition+1);
        if(fromPosition != -1) throw new OrangeException(EXTRA_FROM_IN_EVENT);
        fromPosition = oldFromPosition;


        int byPosition = line.indexOf("/to");
        if(byPosition == -1) throw new OrangeException(MISSING_EVENT_TOWORD);

        int oldByPosition = byPosition;
        byPosition = line.indexOf("/by",byPosition+1);
        if(byPosition != -1) throw new OrangeException(EXTRA_TO_IN_EVENT);
        byPosition = oldByPosition;


        String eventTask = "";
        String doEventBy = "";
        String startEventBy = "";



        try {
            eventTask = line.substring(5, fromPosition).trim();
            if (eventTask.isEmpty()) {
                throw new OrangeException(MISSING_EVENT_DESCRIPTION);  // Throw exception if the task is empty after trimming
            }
        } catch(IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_DESCRIPTION);
        }

        try {
            startEventBy = line.substring(fromPosition+5,byPosition).trim();
            if (startEventBy.isEmpty()) {
                throw new OrangeException(MISSING_EVENT_STARTON);  // Throw exception if the task is empty after trimming
            }
        } catch(IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_STARTON);
        }

        try {
            doEventBy = line.substring(byPosition+4).trim();
            if (doEventBy.isEmpty()) {
                throw new OrangeException(MISSING_EVENT_DOBY);  // Throw exception if the task is empty after trimming
            }
        } catch(IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_EVENT_DOBY);
        }

        return new ArrayList<>(Arrays.asList(eventTask,startEventBy,doEventBy));
    }

    public static int parseMark() throws OrangeException {
        String taskToMarkString = "";
        int taskToMark = -1;
        try {
            taskToMarkString = line.substring(4).trim(); //Remove any trailing spaces
            if(taskToMarkString.isEmpty()) {
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

    public static int parseUnMark() throws OrangeException {
        String taskToUnMarkString = "";
        int taskToUnMark = -1;
        try {
            taskToUnMarkString = line.substring(6).trim(); //Remove any trailing spaces
            if(taskToUnMarkString.isEmpty()) {
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

    public static int parseDelete() throws OrangeException {
        String taskToDeleteString = "";
        int taskToDelete = -1;
        try {
            taskToDeleteString = line.substring(6).trim(); //Remove any trailing spaces
            if(taskToDeleteString.isEmpty()) {
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

    public static String parseFind() throws OrangeException {
        String taskToFind = "";
        try {
            taskToFind = line.substring(4).trim(); //Remove any trailing spaces
            if(taskToFind.isEmpty()) {
                throw new OrangeException(INVALID_FINDTASK);
            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new OrangeException(INVALID_FINDTASK);
        }

        return taskToFind;

    }

    public static LocalDate parseCheckTasksOnDate() throws OrangeException {
        String givenDate = "";
        try {
            givenDate = line.substring(11).trim();
            if (givenDate.isEmpty()) {
                throw new OrangeException(MISSING_DEADLINE_DESCRIPTION);  // Throw exception if the task is empty after trimming
            }
        } catch(IndexOutOfBoundsException i) {
            throw new OrangeException(MISSING_DEADLINE_DESCRIPTION);
        }
        return DateParser.getDateObject(givenDate);
    }

}

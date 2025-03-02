package orange.exception;

import orange.task.TaskList;

public enum ExceptionType {
    UNKNOWN_COMMAND(1, "No idea what that means, start with a keyword man! List of keywords: \"mark\", \"unmark\", \"list\", \"todo\", \"event\", \"deadline\", \"delete\", \"checkondate\""),
    MISSING_DEADLINE(2, "You didn't provide an orange.task.Deadline date and time! Usage: /by [Date And Time orange.task.Deadline orange.task.Task Is Due]"),
    INVALID_TASKNUMBER(3, "You have provided an invalid task number. Try again using a number from the range 0 - " + (TaskList.getInstance().getSize()) + "Usage: mark/unmark/delete [taskNumber] (Add a space between the keyword and taskNumber)" ),
    MISSING_TODO_DESCRIPTION(4, "You have provided an invalid/missing todo description. Todo descriptions cannot be empty. Try again. Usage: todo [todo task description]"),
    MISSING_DEADLINE_BYWORD(5, "You have not used the /by in your deadline command. Try this: deadline [deadline task description] /by [Date And Time task is due]"),
    MISSING_DEADLINE_DESCRIPTION(6, "You have not provided a deadline task description. Deadline descriptions cannot be empty. Try this: deadline [deadline task description] /by [Date And Time task is due]"),
    MISSING_DEADLINE_DOBY(7, "You have not provided a date and time by which to complete the deadline. Deadline descriptions cannot be empty. Try this: deadline [deadline task description] /by [Date And Time task is due]"),
    MISSING_EVENT_TOWORD(8, "You have not used the /to in your event command. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    MISSING_EVENT_DESCRIPTION(9, "You have not provided a event task description. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    MISSING_EVENT_STARTON(10, "You have not provided the start date for your event. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    MISSING_EVENT_DOBY(11, "You have not provided a date and time by which to complete the event. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    MISSING_EVENT_FROMWORD(12, "You have not used the /from in your event command. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    EXTRA_BY_IN_DEADLINE(13, "You have used extra /by in the deadline command, this is not allowed. Try this: deadline [deadline task description] /by [Date And Time task is due]"),
    EXTRA_FROM_IN_EVENT(14, "You have used extra /from in the event command, this is not allowed. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    EXTRA_TO_IN_EVENT(15, "You have used extra /to in the event command, this is not allowed. Try this: event [event task description] /from [Date And Time task starts on] /to [Date And Time task is due]"),
    INVALID_FINDTASK(16, "You have not entered a task. Try this: find [task]"),
    INCORRECT_DATE_AND_TIME_FORMAT(17, "Date And Time Format Provided Is Incorrect. Try in this format: yyyy-mm-dd HH:mm"),
    INVALID_DATE_OR_TIME(18, "You have entered an invalid date or time, the format is correct. Try in this format: yyyy-mm-dd HH:mm"),
    INVALID_DATE(19, "You have entered an invalid date. Try in this format: yyyy-mm-dd");




    private final int code;
    private final String message;

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }


}

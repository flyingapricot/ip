package orange.exception;

public enum ExceptionType {
    UNKNOWN_COMMAND(1, "No idea what that means, start with a keyword man! List of keywords: \"mark\", \"unmark\", \"list\", \"todo\", \"event\", \"deadline\", \"delete\""),
    MISSING_DEADLINE(2, "You didn't provide an orange.task.Deadline date and time! Usage: /by [Date And Time orange.task.Deadline orange.task.Task Is Due]");

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

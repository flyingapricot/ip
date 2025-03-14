package orange.task;

import orange.exception.OrangeException;
import orange.parser.DateParser;

import java.time.LocalDateTime;

/**
 * Represents an event task with a specific start and end date/time. This class extends Task and
 * includes functionality for handling date parsing and formatting.
 *
 * @see Task
 * @see DateParser
 * @see OrangeException
 */
public class Events extends Task {
    /** The start date and time of the event as a LocalDateTime object. */
    protected LocalDateTime startDateAndTime;

    /** The formatted string representation of the start date and time. */
    protected String newStartDateTimeString;

    /** The original string representation of the start date and time as provided by the user. */
    protected String originalStartDateTimeString;

    /** The end date and time of the event as a LocalDateTime object. */
    protected LocalDateTime endDateAndTime;

    /** The formatted string representation of the end date and time. */
    protected String newEndDateTimeString;

    /** The original string representation of the end date and time as provided by the user. */
    protected String originalEndDateTimeString;

    /**
     * Constructs an Events task with a description, completion status, start time, and end time.
     *
     * @param description The description of the event.
     * @param isDone The completion status of the event.
     * @param originalStartDateTimeString The original start date-time string provided by the user.
     * @param originalEndDateTimeString The original end date-time string provided by the user.
     * @throws OrangeException If there is an error in parsing the date.
     */
    public Events(
            String description,
            boolean isDone,
            String originalStartDateTimeString,
            String originalEndDateTimeString)
            throws OrangeException {
        super(description, isDone);
        this.originalStartDateTimeString = originalStartDateTimeString;
        this.startDateAndTime = DateParser.getDateTimeObject(originalStartDateTimeString);
        this.newStartDateTimeString = DateParser.getDifferentFormat(startDateAndTime);

        this.originalEndDateTimeString = originalEndDateTimeString;
        this.endDateAndTime = DateParser.getDateTimeObject(originalEndDateTimeString);
        this.newEndDateTimeString = DateParser.getDifferentFormat(endDateAndTime);
    }

    /**
     * Constructs an Events task with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param originalStartDateTimeString The original start date-time string provided by the user.
     * @param originalEndDateTimeString The original end date-time string provided by the user.
     * @throws OrangeException If there is an error in parsing the date.
     */
    public Events(
            String description,
            String originalStartDateTimeString,
            String originalEndDateTimeString)
            throws OrangeException {
        super(description);
        this.originalStartDateTimeString = originalStartDateTimeString;
        this.startDateAndTime = DateParser.getDateTimeObject(originalStartDateTimeString);
        this.newStartDateTimeString = DateParser.getDifferentFormat(startDateAndTime);

        this.originalEndDateTimeString = originalEndDateTimeString;
        this.endDateAndTime = DateParser.getDateTimeObject(originalEndDateTimeString);
        this.newEndDateTimeString = DateParser.getDifferentFormat(endDateAndTime);
    }

    /** Prints the event task with its completion status. */
    @Override
    public void printTaskWithCompletion() {
        if (this.isDone) {
            System.out.print("[E][X] " + this.description);
        } else {
            System.out.print("[E][ ] " + this.description);
        }
    }

    /**
     * Returns the formatted string representation of the event task with its completion status.
     *
     * @return The formatted event task string.
     */
    @Override
    public String getTaskWithCompletion() {
        if (this.isDone) {
            return "[E][X] "
                    + this.description
                    + " (from: "
                    + newStartDateTimeString
                    + " to: "
                    + newEndDateTimeString
                    + ")";
        } else {
            return "[E][ ] "
                    + this.description
                    + " (from: "
                    + newStartDateTimeString
                    + " to: "
                    + newEndDateTimeString
                    + ")";
        }
    }

    /**
     * Returns the original start date and time string.
     *
     * @return The original start date-time string.
     */
    public String getStartDateAndTime() {
        return originalStartDateTimeString;
    }

    /**
     * Returns the original end date and time string.
     *
     * @return The original end date-time string.
     */
    public String getEndDateAndTime() {
        return originalEndDateTimeString;
    }

    /**
     * Returns the LocalDateTime representation of the end date and time.
     *
     * @return The LocalDateTime object representing the end date and time.
     */
    public LocalDateTime getEndLocalDateTime() {
        return endDateAndTime;
    }
}

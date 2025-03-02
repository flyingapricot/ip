/**
 * Represents a deadline task with a specific due date and time.
 * This class extends Task and includes functionality for handling date parsing and formatting.
 *
 * @see Task
 * @see DateParser
 * @see OrangeException
 */
package orange.task;

import orange.exception.OrangeException;
import orange.parser.DateParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
  /**
   * The due date and time of the deadline task as a LocalDateTime object.
   */
  protected LocalDateTime dateAndTime;

  /**
   * The formatted string representation of the due date and time.
   */
  protected String newDateTimeString;

  /**
   * The original string representation of the due date and time as provided by the user.
   */
  protected String originalDateTimeString;

  /**
   * Constructs a Deadline task with a description, completion status, and due date/time.
   *
   * @param description The description of the task.
   * @param isDone The completion status of the task.
   * @param originalDateTimeString The original date-time string provided by the user.
   * @throws OrangeException If there is an error in parsing the date.
   */
  public Deadline(String description, boolean isDone, String originalDateTimeString) throws OrangeException {
    super(description, isDone);
    this.originalDateTimeString = originalDateTimeString;
    this.dateAndTime = DateParser.getDateTimeObject(originalDateTimeString);
    this.newDateTimeString = DateParser.getDifferentFormat(dateAndTime);
  }

  /**
   * Constructs a Deadline task with a description and due date/time.
   *
   * @param description The description of the task.
   * @param originalDateTimeString The original date-time string provided by the user.
   * @throws OrangeException If there is an error in parsing the date.
   */
  public Deadline(String description, String originalDateTimeString) throws OrangeException {
    super(description);
    this.originalDateTimeString = originalDateTimeString;
    this.dateAndTime = DateParser.getDateTimeObject(originalDateTimeString);
    this.newDateTimeString = DateParser.getDifferentFormat(dateAndTime);
  }

  /**
   * Prints the task with its completion status.
   */
  @Override
  public void printTaskWithCompletion() {
    if (this.isDone) {
      System.out.print("[D][X] " + this.description);
    } else {
      System.out.print("[D][ ] " + this.description);
    }
  }

  /**
   * Returns the formatted string representation of the task with its completion status.
   *
   * @return The formatted task string.
   */
  @Override
  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[D][X] " + this.description + " (by: " + newDateTimeString + ")";
    } else {
      return "[D][ ] " + this.description + " " + "(by: " + newDateTimeString + ")";
    }
  }

  /**
   * Returns the original due date and time string.
   *
   * @return The original date-time string.
   */
  public String getDateAndTime() {
    return originalDateTimeString;
  }

  /**
   * Returns the LocalDateTime representation of the due date and time.
   *
   * @return The LocalDateTime object representing the due date and time.
   */
  public LocalDateTime getLocalDateTime() {
    return dateAndTime;
  }
}
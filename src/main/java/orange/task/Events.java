/**
 * Represents an Event task with a start and end date/time.
 * An Event task is a type of task that includes a completion status
 * and a time period during which the event takes place.
 *
 * @see Task
 */
package orange.task;

public class Events extends Task {

  /**
   * The start date and time of the event.
   */
  protected String startDateAndTime;

  /**
   * The end date and time of the event.
   */
  protected String endDateAndTime;

  /**
   * Constructs an Event task with a description, completion status, start, and end time.
   *
   * @param description The description of the event.
   * @param isDone The completion status of the event.
   * @param startDateAndTime The start date and time of the event.
   * @param endDateAndTime The end date and time of the event.
   */
  public Events(String description, boolean isDone, String startDateAndTime, String endDateAndTime) {
    super(description, isDone);
    this.startDateAndTime = startDateAndTime;
    this.endDateAndTime = endDateAndTime;
  }

  /**
   * Constructs an Event task with a description, start, and end time.
   * The task is initially not completed.
   *
   * @param description The description of the event.
   * @param startDateAndTime The start date and time of the event.
   * @param endDateAndTime The end date and time of the event.
   */
  public Events(String description, String startDateAndTime, String endDateAndTime) {
    super(description);
    this.startDateAndTime = startDateAndTime;
    this.endDateAndTime = endDateAndTime;
  }

  /**
   * Prints the task with its completion status.
   * <p>
   * The task format includes an "E" to indicate an event task,
   * followed by a completion indicator ([X] for done, [ ] for not done).
   * </p>
   */
  @Override
  public void printTaskWithCompletion() {
    if (this.isDone) {
      System.out.print("[E][X] " + this.description);
    } else {
      System.out.print("[E][ ] " + this.description);
    }
  }

  /**
   * Returns a string representation of the task, including its completion status and time period.
   * <p>
   * The format is [E][X] for completed tasks and [E][ ] for incomplete tasks,
   * followed by the task description and time period.
   * </p>
   *
   * @return A string representing the task with its completion status, start, and end time.
   */
  @Override
  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[E][X] " + this.description + " (from: " + startDateAndTime + " to: " + endDateAndTime + ")";
    } else {
      return "[E][ ] " + this.description + " (from: " + startDateAndTime + " to: " + endDateAndTime + ")";
    }
  }

  /**
   * Retrieves the start date and time of the event.
   *
   * @return The start date and time of the event.
   */
  public String getStartDateAndTime() {
    return startDateAndTime;
  }

  /**
   * Retrieves the end date and time of the event.
   *
   * @return The end date and time of the event.
   */
  public String getEndDateAndTime() {
    return endDateAndTime;
  }
}
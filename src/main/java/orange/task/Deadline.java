/**
 * Represents a Deadline task with a specified due date and time.
 * A Deadline task is a type of task that includes a completion status
 * and a deadline by which it must be completed.
 *
 * @see Task
 */
package orange.task;

public class Deadline extends Task {

  /**
   * The date and time by which the task must be completed.
   */
  protected String dateAndTime;

  /**
   * Constructs a Deadline task with a description, completion status, and deadline.
   *
   * @param description The description of the task.
   * @param isDone The completion status of the task.
   * @param dateAndTime The deadline for the task.
   */
  public Deadline(String description, boolean isDone, String dateAndTime) {
    super(description, isDone);
    this.dateAndTime = dateAndTime;
  }

  /**
   * Constructs a Deadline task with a description and deadline.
   * The task is initially not completed.
   *
   * @param description The description of the task.
   * @param dateAndTime The deadline for the task.
   */
  public Deadline(String description, String dateAndTime) {
    super(description);
    this.dateAndTime = dateAndTime;
  }

  /**
   * Prints the task with its completion status.
   * <p>
   * The task format includes a "D" to indicate a deadline task,
   * followed by a completion indicator ([X] for done, [ ] for not done).
   * </p>
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
   * Returns a string representation of the task, including its completion status and deadline.
   * <p>
   * The format is [D][X] for completed tasks and [D][ ] for incomplete tasks,
   * followed by the task description and deadline.
   * </p>
   *
   * @return A string representing the task with its completion status and deadline.
   */
  @Override
  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[D][X] " + this.description + " (by: " + dateAndTime + ")";
    } else {
      return "[D][ ] " + this.description + " " + "(by: " + dateAndTime + ")";
    }
  }

  /**
   * Retrieves the deadline date and time of the task.
   *
   * @return The deadline date and time of the task.
   */
  public String getDateAndTime() {
    return dateAndTime;
  }
}

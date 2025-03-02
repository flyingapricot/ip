package orange.task;

public class Deadline extends Task {

  protected String dateAndTime;

  public Deadline(String description, boolean isDone, String dateAndTime) {
    super(description, isDone);
    this.dateAndTime = dateAndTime;
  }

  public Deadline(String description, String dateAndTime) {
    super(description);
    this.dateAndTime = dateAndTime;
  }

  @Override
  public void printTaskWithCompletion() {
    if (this.isDone) {
      System.out.print("[D][X] " + this.description);
    } else {
      System.out.print("[D][ ] " + this.description);
    }
  }

  @Override
  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[D][X] " + this.description + "(by: " + dateAndTime + ")";
    } else {
      return "[D][ ] " + this.description + " " + "(by: " + dateAndTime + ")";
    }
  }

  public String getDateAndTime() {
    return dateAndTime;
  }
}

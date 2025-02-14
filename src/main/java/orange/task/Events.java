package orange.task;

public class Events extends Task {

  protected String startDateAndTime;
  protected String endDateAndTime;

  public Events(
      String description, boolean isDone, String startDateAndTime, String endDateAndTime) {
    super(description, isDone);
    this.startDateAndTime = startDateAndTime;
    this.endDateAndTime = endDateAndTime;
  }

  public Events(String description, String startDateAndTime, String endDateAndTime) {
    super(description);
    this.startDateAndTime = startDateAndTime;
    this.endDateAndTime = endDateAndTime;
  }

  @Override
  public void printTaskWithCompletion() {
    if (this.isDone) {
      System.out.print("[E][X] " + this.description);
    } else {
      System.out.print("[E][ ] " + this.description);
    }
  }

  @Override
  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[E][X] "
          + this.description
          + " "
          + "(from:"
          + startDateAndTime
          + "to:"
          + endDateAndTime
          + ")";
    } else {
      return "[E][ ] "
          + this.description
          + " "
          + "(from:"
          + startDateAndTime
          + "to:"
          + endDateAndTime
          + ")";
    }
  }
}

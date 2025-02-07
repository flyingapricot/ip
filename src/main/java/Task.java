public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description, boolean isDone) {
    this.description = description;
    this.isDone = isDone;
  }

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean getIsDone() {
    return isDone;
  }

  public void setIsDone(boolean isDone) {
    this.isDone = isDone;
  }

  public void printTaskWithCompletion() {
    if (this.isDone) {
      System.out.print("[X] " + this.description);
    } else {
      System.out.print("[ ] " + this.description);
    }
  }

  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[X] " + this.description;
    } else {
      return "[ ] " + this.description;
    }
  }
}

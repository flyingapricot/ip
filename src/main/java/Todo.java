public class Todo extends Task{

  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

  public Todo(String description) {
    super(description);
  }

  @Override
  public void printTaskWithCompletion() {
    if (this.isDone) {
      System.out.print("[T][X] " + this.description);
    } else if (!this.isDone) {
      System.out.print("[T][ ] " + this.description);
    }
  }

  @Override
  public String GetTaskWithCompletion() {
    if (this.isDone) {
      return "[T][X] " + this.description;
    } else if (!this.isDone) {
      return "[T][ ] " + this.description;
    }
    return "";
  }

}

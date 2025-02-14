package orange;

public class OrangeException extends Exception{
  protected int exceptionID;
  public OrangeException(int exceptionID) {
    this.exceptionID = exceptionID;
  }
  //Class to handle exceptions specific to orange.Orange Chatbot
  public String getMessage() {
    switch(exceptionID) {
      case 1:
        return "No idea what that means, start with a keyword man!";
      case 2:
        return "You didn't provide a orange.task.Deadline date and time! Usage: /by [Date And Time orange.task.Deadline orange.task.Task Is Due]";
    };
    return "";
  }
}

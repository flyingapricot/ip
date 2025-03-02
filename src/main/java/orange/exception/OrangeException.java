package orange.exception;

public class OrangeException extends Exception{
  protected ExceptionType exception;

  public OrangeException(ExceptionType exception) {
    super(exception.getMessage());  // Set message
    this.exception = exception;
  }

  // Returns the message for this specific exception type
  public String getCustomMessage() {
    return exception.getMessage();
  }

  // Returns the code for this specific exception type
  public int getExceptionCode() {
    return exception.getCode();
  }

}

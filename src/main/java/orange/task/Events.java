package orange.task;

import orange.exception.OrangeException;
import orange.parser.DateParser;

import java.time.LocalDateTime;

public class Events extends Task {

  protected LocalDateTime startDateAndTime;
  protected String newStartDateTimeString;
  protected String originalStartDateTimeString;

  protected LocalDateTime endDateAndTime;
  protected String newEndDateTimeString;
  protected String originalEndDateTimeString;



  public Events(
      String description, boolean isDone, String originalStartDateTimeString, String originalEndDateTimeString) throws OrangeException {
    super(description, isDone);
    this.originalStartDateTimeString = originalStartDateTimeString;
    this.startDateAndTime = DateParser.getDateTimeObject(originalStartDateTimeString);
    this.newStartDateTimeString = DateParser.getDifferentFormat(startDateAndTime);


    this.originalEndDateTimeString = originalEndDateTimeString;
    this.endDateAndTime = DateParser.getDateTimeObject(originalEndDateTimeString);
    this.newEndDateTimeString = DateParser.getDifferentFormat(endDateAndTime);

  }

  public Events(String description, String originalStartDateTimeString, String originalEndDateTimeString) throws OrangeException {
    super(description);
    this.originalStartDateTimeString = originalStartDateTimeString;
    this.startDateAndTime = DateParser.getDateTimeObject(originalStartDateTimeString);
    this.newStartDateTimeString = DateParser.getDifferentFormat(startDateAndTime);


    this.originalEndDateTimeString = originalEndDateTimeString;
    this.endDateAndTime = DateParser.getDateTimeObject(originalEndDateTimeString);
    this.newEndDateTimeString = DateParser.getDifferentFormat(endDateAndTime);
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
          + "(from: "
          + newStartDateTimeString
          + " to: "
          + newStartDateTimeString
          + ")";
    } else {
      return "[E][ ] "
          + this.description
          + " "
          + "(from: "
          + newStartDateTimeString
          + " to: "
          + newStartDateTimeString
          + ")";
    }
  }

  public String getStartDateAndTime() {
    return originalStartDateTimeString;
  }

  public String getEndDateAndTime() {
    return originalEndDateTimeString;
  }

  public LocalDateTime getEndLocalDateTime()
  {
    return endDateAndTime;
  }

}

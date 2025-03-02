package orange.task;

import orange.exception.OrangeException;
import orange.parser.DateParser;

import java.time.LocalDateTime;

public class Deadline extends Task {

  protected LocalDateTime dateAndTime;
  protected String newDateTimeString;
  protected String originalDateTimeString;

  public Deadline(String description, boolean isDone, String originalDateTimeString) throws OrangeException {
    super(description, isDone);
    this.originalDateTimeString = originalDateTimeString;
    this.dateAndTime = DateParser.getDateTimeObject(originalDateTimeString);
    this.newDateTimeString = DateParser.getDifferentFormat(dateAndTime);
  }

  public Deadline(String description, String originalDateTimeString) throws OrangeException {
    super(description);
    this.originalDateTimeString = originalDateTimeString;
    this.dateAndTime = DateParser.getDateTimeObject(originalDateTimeString);
    this.newDateTimeString = DateParser.getDifferentFormat(dateAndTime);
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
      return "[D][X] " + this.description + " (by: " + newDateTimeString + ")";
    } else {
      return "[D][ ] " + this.description + " " + "(by: " + newDateTimeString + ")";
    }
  }

  public String getDateAndTime() {
    return originalDateTimeString;
  }

  public LocalDateTime getLocalDateTime() {
    return dateAndTime;
  }

}

package orange;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import orange.exception.OrangeException;
import orange.task.Deadline;
import orange.task.Events;
import orange.task.Task;
import orange.task.Todo;
import java.io.IOException;
import java.nio.file.*;


public class Orange {
  private static final String HORIZONTAL_LINE = "\t" + "-".repeat(50);
  private static final HashSet<String> commands = new HashSet<>(Arrays.asList("mark","unmark","list","todo","event","deadline","delete"));
  private static final String TASKFILE = "./saved.csv";

  private static final String CHATBOT_NAME = "ORANGE";
  private static final ArrayList<Task> tasks =
      new ArrayList<Task>(); // Array List to store tasks entered by users

  public static void greeting() {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "Hello! I'm " + CHATBOT_NAME);
    System.out.println("\t" + "What can I do for you?");
    System.out.println(HORIZONTAL_LINE);
  }

  public static void goodbye() {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "Bye. Hope to see you again soon!");
    System.out.println(HORIZONTAL_LINE);
  }

  public static void loadTask() {
    Path path = Paths.get(TASKFILE);

    // Check if the file exists
    if (Files.exists(path)) {
      System.out.println("File exists, reading task file: " + TASKFILE);
    } else {
      // File doesn't exist, so create it
      try {
        Files.createFile(path);
        System.out.println("Task File created at: " + TASKFILE);
      } catch (IOException e) {
        System.out.println("Error creating Task file: " + e.getMessage());
      }
    }

    //Read the task file and load any tasks
    //Task type,status,task name,from date,to date
    Path taskPath = Paths.get(TASKFILE);
    try {
      List<String> lines = Files.readAllLines(taskPath);
      for (String line : lines) {
        String[] values = line.split(",");
        switch(values[0]) {
          case "T":
            int status = Integer.parseInt(values[1]);
            Boolean taskStatus = status == 1;
            Todo(values[2],taskStatus);
            break;
          case "D":
            int deadlines = Integer.parseInt(values[1]);
            Boolean deadlineStatus = deadlines == 1;
            Deadline(values[2],values[4],deadlineStatus);
            break;
          case "E":
            int events = Integer.parseInt(values[1]);
            Boolean eventsStatus = events == 1;
            Event(values[2],values[3],values[4],eventsStatus);
            break;
        }
      }

    }catch (IOException e) {
        System.out.println("Error reading the file: " + e.getMessage());
    }

  }

  public static void echo() {
    String line;
    Scanner in = new Scanner(System.in);
    line = in.nextLine();
    while (!(line.equals("bye"))) {
      System.out.println(HORIZONTAL_LINE);
      System.out.println("\t" + line);
      System.out.println(HORIZONTAL_LINE);
      line = in.nextLine();
    }
    goodbye();
  }

  public static void ListTasks() {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tHere are the tasks in your list:");
    int count = 1;
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println("\t" + count + ". " + tasks.get(i).GetTaskWithCompletion());
      count++;
    }
    System.out.println(HORIZONTAL_LINE);
  }

  //Adding orange.task.Task
  public static void AddTask(String task) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "added: " + task);
    Task t = new Task(task, false);
    tasks.add(t);
    System.out.println(HORIZONTAL_LINE);
  }

  //Unmarking orange.task.Task
  public static void Unmark(String taskNumber) {
    // Mark the task as not completed
    tasks.get(Integer.parseInt(taskNumber) - 1).setIsDone(false);
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "OK, I've marked this task as not done yet:");
    System.out.println(
        "\t\t" + tasks.get(Integer.parseInt(taskNumber) - 1).GetTaskWithCompletion());
    System.out.println(HORIZONTAL_LINE);

  }

  //Marking orange.task.Task As Complete
  public static void Mark(String taskNumber) {
    // Mark the task as completed
    tasks.get(Integer.parseInt(taskNumber) - 1).setIsDone(true);
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "Nice! I've marked this task as done:");
    System.out.println(
        "\t\t" + tasks.get(Integer.parseInt(taskNumber) - 1).GetTaskWithCompletion());
    System.out.println(HORIZONTAL_LINE);

  }

  //Adding orange.task.Todo orange.task.Task
  public static void Todo(String task,Boolean status) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tGot it. I've added this task:");
    Task t = new Todo(task, status);
    System.out.print("\t\t");
    System.out.println(t.GetTaskWithCompletion());
    tasks.add(t);
    System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    System.out.println(HORIZONTAL_LINE);

    //Save the task inside tasks.csv
    try {
      Path path = Paths.get(TASKFILE);
      // Write data rows
      //Task type,status,task name,from date,to date
      String finalTask = "T," + "0," + task +",-,-\n";
      Files.write(path, Arrays.asList(finalTask), StandardOpenOption.APPEND);
      System.out.println("Data written to CSV successfully.");
    } catch (IOException e) {
      System.out.println("Error writing to CSV file: " + e.getMessage());
    }

  }

  //Adding orange.task.Deadline orange.task.Task
  public static void Deadline(String task,String dateAndTime,Boolean deadlineStatus) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tGot it. I've added this task:");
    Task t = new Deadline(task, deadlineStatus,dateAndTime);
    System.out.print("\t\t");
    System.out.println(t.GetTaskWithCompletion());
    tasks.add(t);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println(HORIZONTAL_LINE);

    //Save the task inside tasks.csv
    try {
      Path path = Paths.get(TASKFILE);
      // Write data rows
      //Task type,status,task name,from date,to date
      String finalDeadline = "D,0," + task + ",-," + dateAndTime + "\n";
      Files.write(path, Arrays.asList(finalDeadline), StandardOpenOption.APPEND);
      System.out.println("Data written to CSV successfully.");
    } catch (IOException e) {
      System.out.println("Error writing to CSV file: " + e.getMessage());
    }

  }

  //Adding Event orange.task.Task
  public static void Event(String task,String fromDateAndTime,String toDateAndTime,Boolean EventStatus) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tGot it. I've added this task:");
    Task t = new Events(task, EventStatus,fromDateAndTime,toDateAndTime);
    System.out.print("\t\t");
    System.out.println(t.GetTaskWithCompletion());
    tasks.add(t);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println(HORIZONTAL_LINE);

    //Save the task inside tasks.csv
    try {
      Path path = Paths.get(TASKFILE);
      // Write data rows
      //Task type,status,task name,from date,to date
      String finalEvent = "D,0," + task + "," + fromDateAndTime + "," + toDateAndTime + "\n";
      Files.write(path, Arrays.asList(finalEvent), StandardOpenOption.APPEND);
      System.out.println("Data written to CSV successfully.");
    } catch (IOException e) {
      System.out.println("Error writing to CSV file: " + e.getMessage());
    }

  }

  public static void main(String[] args) throws OrangeException {
    loadTask();
    greeting(); // Print out the greeting
    String line = "";
    Scanner in = new Scanner(System.in);
    String commandWord = "";
    String[] userInput;

    while (!(line.equals("bye"))) {
      switch(commandWord) {
        case "list":
          ListTasks();
          break;
        case "unmark":
          //Get the task number to be unmarked
          String taskNumberUnMarked = line.substring(7);
          Unmark(taskNumberUnMarked);
          break;
        case "mark":
          //Get the task number to be marked
          String taskNumberMarked = line.substring(5);
          Mark(taskNumberMarked);
          break;
        case "todo":
          String TodoTask = "";
          try{
            TodoTask = (line.substring(5)).trim(); //Remove any trailing spaces
          }
          catch (StringIndexOutOfBoundsException s) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + "Description of a todo cannot be empty! Try again.");
            System.out.println(HORIZONTAL_LINE);
          }
          if(!TodoTask.isEmpty()) Todo(TodoTask,false);
          break;
        case "deadline":
          //Scan for /by
          int position;
          String deadlineTask = "";
          String givenDeadline = "";
          try {
            position = line.indexOf("/by");
            if(position == -1) {
              throw new OrangeException(2);
            }
            deadlineTask = line.substring(8,position);
            givenDeadline = line.substring(position+4);
          } catch(OrangeException o) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + "Format of calling a deadline is wrong. Try this: /by [Date And Time task is due] [orange.task.Task]");
            System.out.println(HORIZONTAL_LINE);
          }
          if(!deadlineTask.isEmpty() && !givenDeadline.isEmpty()) Deadline(deadlineTask,givenDeadline,false);
          break;
        case "event":
          //Scan for /by
          int from = -1;
          try{
            from = line.indexOf("/from");
          }catch(StringIndexOutOfBoundsException s) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + "Incorrect Format for event. Use this format: event [Event] /from [Start] /to [End]");
            System.out.println(HORIZONTAL_LINE);
          }
          String eventTask = "";
          try {
            eventTask = line.substring(0,from);
          } catch(StringIndexOutOfBoundsException s) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + "Incorrect Format for event. Use this format: event [Event] /from [Start] /to [End]");
            System.out.println(HORIZONTAL_LINE);
          }
          int to = -1;
          try {
            to = line.indexOf("/to");
          } catch(StringIndexOutOfBoundsException s) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + "Incorrect Format for event. Use this format: event [Event] /from [Start] /to [End]");
            System.out.println(HORIZONTAL_LINE);
          }
          if (from != -1 && to != -1 && !eventTask.isEmpty()) {
            String fromDate = line.substring(from + 5, to);
            String toDate = line.substring(to + 3);
            Event(eventTask, fromDate, toDate,false);
          }
          break;
        case "delete":
          int numberToCheck = Integer.parseInt(line.substring(7));
          System.out.println(HORIZONTAL_LINE);
          System.out.println("Noted. I've removed this task:");
          System.out.println("\t" + tasks.get(numberToCheck-1).GetTaskWithCompletion());
          tasks.remove(numberToCheck-1);
          System.out.println("Now you have " + tasks.size() + " tasks in the list.");
          break;

      }
      line = in.nextLine();
      userInput = line.split(" ");
      commandWord = userInput[0];
      if(!commands.contains(commandWord)) commandWord = "";

      //Try Catch Block to catch no keyword used
      try{
        if(!commands.contains(commandWord)) throw new OrangeException(1);
      }catch(OrangeException o) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + o.getMessage());
        System.out.println(HORIZONTAL_LINE);
      }

    }
    goodbye();
  }
}

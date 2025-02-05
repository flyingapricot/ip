import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Orange {
  private static final String HORIZONTAL_LINE = "\t" + "-".repeat(50);
  private static final HashSet<String> commands = new HashSet<>(Arrays.asList("mark","unmark","list","todo","event","deadline"));

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

  public static void AddTask(String task) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "added: " + task);
    Task t = new Task(task, false);
    tasks.add(t);
    System.out.println(HORIZONTAL_LINE);
  }

  public static void Unmark(String taskNumber) {
    // Mark the task as not completed
    tasks.get(Integer.parseInt(taskNumber) - 1).setIsDone(false);
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "OK, I've marked this task as not done yet:");
    System.out.println(
        "\t\t" + tasks.get(Integer.parseInt(taskNumber) - 1).GetTaskWithCompletion());
    System.out.println(HORIZONTAL_LINE);

  }

  public static void Mark(String taskNumber) {
    // Mark the task as completed
    tasks.get(Integer.parseInt(taskNumber) - 1).setIsDone(true);
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "Nice! I've marked this task as done:");
    System.out.println(
        "\t\t" + tasks.get(Integer.parseInt(taskNumber) - 1).GetTaskWithCompletion());
    System.out.println(HORIZONTAL_LINE);

  }

  //Adding Todo Task
  public static void Todo(String task) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tGot it. I've added this task:");
    Task t = new Todo(task, false);
    System.out.print("\t\t");
    System.out.println(t.GetTaskWithCompletion());
    tasks.add(t);
    System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    System.out.println(HORIZONTAL_LINE);
  }

  //Adding Deadline Task
  public static void Deadline(String task,String dateAndTime) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tGot it. I've added this task:");
    Task t = new Deadline(task, false,dateAndTime);
    System.out.print("\t\t");
    System.out.println(t.GetTaskWithCompletion());
    tasks.add(t);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println(HORIZONTAL_LINE);
  }

  //Adding Event Task
  public static void Event(String task,String fromDateAndTime,String toDateAndTime) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\tGot it. I've added this task:");
    Task t = new Events(task, false,fromDateAndTime,toDateAndTime);
    System.out.print("\t\t");
    System.out.println(t.GetTaskWithCompletion());
    tasks.add(t);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    System.out.println(HORIZONTAL_LINE);
  }

  public static void main(String[] args) {
    greeting(); // Print out the greeting
    String line;
    Scanner in = new Scanner(System.in);
    line = in.nextLine(); //Read the input

    //Split the input by space delimeter
    //check if there is any command word used (mark,unmark....)
    String[] userInput = line.split(" ");

    String commandWord = userInput[0];
    if(!commands.contains(commandWord)) commandWord = "";

    while(!(line.equals("bye"))) {
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
          String TodoTask = line.substring(5);
          Todo(TodoTask);
          break;
        case "deadline":
          //Scan for /by
          int position = line.indexOf("/by");
          String deadlineTask = line.substring(8,position);
          String givenDeadline = line.substring(position+4);
          Deadline(deadlineTask,givenDeadline);
          break;
        case "event":
          //Scan for /by
          int from = line.indexOf("/from");
          String eventTask = line.substring(0,from);
          int to = line.indexOf("/to");
          String fromDate = line.substring(from+5,to);
          String toDate = line.substring(to+3);
          String givenDeadline2 = line.substring(0,from);
          Event(eventTask,fromDate,toDate);
          break;
        default:
          //Add Tasks
          AddTask(line);
      }
      line = in.nextLine();
      userInput = line.split(" ");
      commandWord = userInput[0];
      if(!commands.contains(commandWord)) commandWord = "";
    }
    goodbye();
  }
}

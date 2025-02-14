import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

  //Adding Task
  public static void AddTask(String task) {
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "added: " + task);
    Task t = new Task(task, false);
    tasks.add(t);
    System.out.println(HORIZONTAL_LINE);
  }

  //Unmarking Task
  public static void Unmark(String taskNumber) {
    // Mark the task as not completed
    tasks.get(Integer.parseInt(taskNumber) - 1).setIsDone(false);
    System.out.println(HORIZONTAL_LINE);
    System.out.println("\t" + "OK, I've marked this task as not done yet:");
    System.out.println(
        "\t\t" + tasks.get(Integer.parseInt(taskNumber) - 1).GetTaskWithCompletion());
    System.out.println(HORIZONTAL_LINE);

  }

  //Marking Task As Complete
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

  public static void main(String[] args) throws OrangeException {
    greeting(); // Print out the greeting
    String line = "";
    Scanner in = new Scanner(System.in);
    String commandWord = "";
    String[] userInput;

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
          String TodoTask = "";
          try{
            TodoTask = (line.substring(5)).trim(); //Remove any trailing spaces
          }
          catch (StringIndexOutOfBoundsException s) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + "Description of a todo cannot be empty! Try again.");
            System.out.println(HORIZONTAL_LINE);
          }
          if(!TodoTask.isEmpty()) Todo(TodoTask);
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
            System.out.println("\t" + "Format of calling a deadline is wrong. Try this: /by [Date And Time task is due] [Task]");
            System.out.println(HORIZONTAL_LINE);
          }
          if(!deadlineTask.isEmpty() && !givenDeadline.isEmpty()) Deadline(deadlineTask,givenDeadline);
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
            Event(eventTask, fromDate, toDate);
          }
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

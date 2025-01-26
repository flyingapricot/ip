import java.util.ArrayList;
import java.util.Scanner;

public class Orange {
    private static final String HORIZONTAL_LINE = "\t" + "-".repeat(50);
    private static final String CHATBOT_NAME = "ORANGE";
    private static ArrayList<Task> tasks = new ArrayList<Task>(); //Array List to store tasks entered by users

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
        while(!(line.equals("bye"))) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("\t" + line);
            System.out.println(HORIZONTAL_LINE);
            line = in.nextLine();
        }
        goodbye();
    }

    public static void ListTasks() {
        System.out.println(HORIZONTAL_LINE);
        int count = 1;
        for(int i =0;i<tasks.size();i++) {
            System.out.println("\t" + count + ". " + tasks.get(i).GetTaskWithCompletion());
            count++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void AddTask(String task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "added: " + task);
        Task t = new Task(task,false);
        tasks.add(t);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        greeting(); //Print out the greeting
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!(line.equals("bye"))) {
            if(line.equals("list")) {
                ListTasks();
                line = in.nextLine();
                continue;
            }else if(line.contains("unmark")) {
                //Mark the task as not completed
                String taskNumber = line.substring(7);
                tasks.get(Integer.parseInt(taskNumber)-1).setIsDone(false);
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "OK, I've marked this task as not done yet:");
                System.out.println("\t\t" + tasks.get(Integer.parseInt(taskNumber)-1).GetTaskWithCompletion());
                System.out.println(HORIZONTAL_LINE);
                line = in.nextLine();
            }else if(line.contains("mark")) {
                //Mark the task as completed
                String taskNumber = line.substring(5);
                tasks.get(Integer.parseInt(taskNumber)-1).setIsDone(true);
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Nice! I've marked this task as done:");
                System.out.println("\t\t" + tasks.get(Integer.parseInt(taskNumber)-1).GetTaskWithCompletion());
                System.out.println(HORIZONTAL_LINE);
                line = in.nextLine();
            }else {
                AddTask(line);
                line = in.nextLine();
            }
        }
        goodbye();
    }
}

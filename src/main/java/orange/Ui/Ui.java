package orange.Ui;

import orange.task.Task;
import orange.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import orange.parser.DateParser;

public class Ui {
    private static final String HORIZONTAL_LINE = "\t" + "-".repeat(50);
    private static final String CHATBOT_NAME = "ORANGE";

    public Ui() {}

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

    public static void showListOfTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\tHere are the tasks in your list:");
        TaskList.getInstance().listTasks();
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showAddTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\tGot it. I've added this task:");
        System.out.print("\t\t");
        System.out.println(t.GetTaskWithCompletion());
        System.out.println("\tNow you have " + TaskList.getInstance().getSize() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showMarkTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println(
                "\t\t" + t.GetTaskWithCompletion());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showUnmarkTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println(
                "\t\t" + t.GetTaskWithCompletion());
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showDeleteTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + t.GetTaskWithCompletion());
        System.out.println("Now you have " + TaskList.getInstance().getSize() + " tasks in the list.");
    }

    public static void showError(String errorMessage) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + errorMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showFoundTasks(ArrayList<Task> matchingTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Here are the matching tasks in your list:");
        for(Task task: matchingTasks) {
            System.out.println(
                    "\t\t" + task.GetTaskWithCompletion());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void showMatchingTasks(ArrayList<Task> matchingTasks, LocalDate checkDate) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the list of tasks that are due on " + DateParser.getStringFromLocalDate(checkDate));
        for(Task t: matchingTasks) {
            System.out.println("\t" + t.GetTaskWithCompletion());
        }
        System.out.println("You have " + matchingTasks.size() + " tasks in the list.");
    }

}

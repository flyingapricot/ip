package orange.Ui;

import orange.task.Task;
import orange.task.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import orange.parser.DateParser;

/**
 * Provides user interface functionalities for the Orange chatbot.
 * This class handles displaying messages, tasks, and errors to the user.
 * <p>
 * The class contains methods for greeting the user, displaying task lists,
 * showing success/error messages, and handling user interactions.
 * </p>
 *
 * @see TaskList
 * @see Task
 */
public class Ui {
    /**
     * A horizontal line separator for formatting chatbot responses.
     */
    private static final String HORIZONTAL_LINE = "\t" + "-".repeat(50);

    /**
     * The name of the chatbot.
     */
    private static final String CHATBOT_NAME = "ORANGE";

    /**
     * Constructs a Ui instance.
     */
    public Ui() {}

    /**
     * Displays the greeting message when the chatbot starts.
     * <p>
     * This method prints a welcome message to the user, introducing the chatbot.
     * </p>
     */
    public static void greeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm " + CHATBOT_NAME);
        System.out.println("\t" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }


    /**
     * Displays the goodbye message when the chatbot exits.
     * <p>
     * This method prints a farewell message to the user before exiting.
     * </p>
     */
    public static void goodbye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the list of tasks currently stored.
     */
    public static void showListOfTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\tHere are the tasks in your list:");
        TaskList.getInstance().listTasks();
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message Interface when a task is added.
     * <p>
     * This method prints the task details and updates the total task count.
     * </p>
     *
     * @param t The task that was added.
     */
    public static void showAddTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\tGot it. I've added this task:");
        System.out.print("\t\t");
        System.out.println(t.GetTaskWithCompletion());
        System.out.println("\tNow you have " + TaskList.getInstance().getSize() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message Interface when a task is marked as completed.
     *
     * @param t The task that was unmarked.
     */

    public static void showMarkTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println(
                "\t\t" + t.GetTaskWithCompletion());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message Interface when a task is unmarked as completed.
     *
     * @param t The task that was unmarked.
     */

    public static void showUnmarkTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println(
                "\t\t" + t.GetTaskWithCompletion());
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param t The task that was deleted.
     */
    public static void showDeleteTask(Task t) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + t.GetTaskWithCompletion());
        System.out.println("Now you have " + TaskList.getInstance().getSize() + " tasks in the list.");
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to display.
     */
    public static void showError(String errorMessage) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + errorMessage);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the list of matching tasks found.
     *
     * @param matchingTasks The list of tasks that match the search criteria.
     */
    public static void showFoundTasks(ArrayList<Task> matchingTasks) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Here are the matching tasks in your list:");
        for(Task task: matchingTasks) {
            System.out.println(
                    "\t\t" + task.GetTaskWithCompletion());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a list of tasks that are due on a specific date.
     *
     * @param matchingTasks An ArrayList of tasks that match the given date.
     * @param checkDate The date to check for matching tasks.
     */
    public static void showMatchingTasks(ArrayList<Task> matchingTasks, LocalDate checkDate) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the list of tasks that are due on " + DateParser.getStringFromLocalDate(checkDate));
        for(Task t: matchingTasks) {
            System.out.println("\t" + t.GetTaskWithCompletion());
        }
        System.out.println("You have " + matchingTasks.size() + " tasks in the list.");
    }

}

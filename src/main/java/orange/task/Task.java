package orange.task;

/**
 * Represents a generic task with a description and completion status. This serves as a base class
 * for specific task types such as Deadlines, Events And Todo.
 *
 * @see Deadline
 * @see Events
 */
public class Task {
    /** The description of the task. */
    protected String description;

    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Constructs a Task with a specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a Task with a specified description. The task is initially not completed.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of the task.
     *
     * @param description The new task description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param isDone The new completion status of the task.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Prints the task with its completion status.
     *
     * <p>The format is [X] for completed tasks and [ ] for incomplete tasks, followed by the task
     * description.
     */
    public void printTaskWithCompletion() {
        if (this.isDone) {
            System.out.print("[X] " + this.description);
        } else {
            System.out.print("[ ] " + this.description);
        }
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * <p>The format is [X] for completed tasks and [ ] for incomplete tasks, followed by the task
     * description.
     *
     * @return A string representing the task with its completion status.
     */
    public String getTaskWithCompletion() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

package orange.task;
/**
 * Represents a Todo task. A Todo task is a type of task that consists of a description and
 * completion status but does not include any date or time information.
 *
 * @see Task
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with a description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Constructs a Todo task with a description. The task is initially not completed.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Prints the task with its completion status.
     *
     * <p>The task format includes a "T" to indicate a todo task, followed by a completion indicator
     * ([X] for done, [ ] for not done).
     */
    @Override
    public void printTaskWithCompletion() {
        if (this.isDone) {
            System.out.print("[T][X] " + this.description);
        } else {
            System.out.print("[T][ ] " + this.description);
        }
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * <p>The format is [T][X] for completed tasks and [T][ ] for incomplete tasks, followed by the
     * task description.
     *
     * @return A string representing the task with its completion status.
     */
    @Override
    public String getTaskWithCompletion() {
        if (this.isDone) {
            return "[T][X] " + this.description;
        } else {
            return "[T][ ] " + this.description;
        }
    }
}

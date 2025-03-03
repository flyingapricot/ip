    package orange.task;

    import java.util.ArrayList;

    /**
     * Manages a list of tasks using the singleton pattern. This class allows adding, updating,
     * deleting, and listing tasks.
     *
     * @see Task
     */
    public class TaskList {
        /** The single instance of TaskList. */
        private static TaskList instance;

        /** The list of tasks (shared by all Task objects). */
        private static ArrayList<Task> taskList = new ArrayList<>();

        /** Private constructor to prevent instantiation from other classes. */
        private TaskList() {}

        /**
         * Retrieves the singleton instance of TaskList.
         *
         * @return The single instance of TaskList.
         */
        public static TaskList getInstance() {
            if (instance == null) {
                instance = new TaskList();
            }
            return instance;
        }

        /**
         * Adds a task to the task list.
         *
         * @param task The task to be added.
         */
        public void addTask(Task task) {
            taskList.add(task);
        }

        /**
         * Updates a task at the specified index in the task list.
         *
         * @param taskNumber The index of the task to update.
         * @param newTask The new task to replace the old one.
         * @throws IndexOutOfBoundsException If the task number is out of bounds.
         */
        public void updateTask(int taskNumber, Task newTask) throws IndexOutOfBoundsException {
            if (taskNumber >= taskList.size() || taskNumber < 0) {
                throw new IndexOutOfBoundsException();
            }
            taskList.set(taskNumber, newTask);
        }

        /**
         * Deletes a task at the specified index in the task list.
         *
         * @param taskNumber The index of the task to delete.
         * @throws IndexOutOfBoundsException If the task number is out of bounds.
         */
        public void deleteTask(int taskNumber) throws IndexOutOfBoundsException {
            if (taskNumber >= taskList.size() || taskNumber < 0) {
                throw new IndexOutOfBoundsException();
            }
            taskList.remove(taskNumber);
        }

        /** Lists all tasks in the task list with their respective numbers. */
        public void listTasks() {
            int count = 1;
            for (Task task : taskList) {
                System.out.println("\t" + count + ". " + task.getTaskWithCompletion());
                count++;
            }
        }

        /**
         * Retrieves a copy of the task list to prevent external modification.
         *
         * @return A copy of the task list.
         */
        public ArrayList<Task> getTasks() {
            return new ArrayList<>(taskList);
        }

        /**
         * Retrieves the number of tasks in the task list.
         *
         * @return The size of the task list.
         */
        public int getSize() {
            return taskList.size();
        }

        /**
         * Updates the completion status of a task at a given index.
         *
         * @param taskNumber The index of the task to update.
         * @param status The new completion status of the task.
         * @throws IndexOutOfBoundsException If the task number is out of bounds.
         */
        public void updateCompletionStatus(int taskNumber, boolean status)
                throws IndexOutOfBoundsException {
            if (taskNumber >= taskList.size() || taskNumber < 0) {
                throw new IndexOutOfBoundsException();
            }
            taskList.get(taskNumber).setIsDone(status);
        }
    }

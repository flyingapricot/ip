package orange.storage;

import orange.exception.OrangeException;
import orange.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles loading tasks from a file and saving tasks into a file. This class manages persistent
 * storage of tasks in a CSV file.
 *
 * @see Task
 * @see TaskList
 * @see Todo
 * @see Deadline
 * @see Events
 */
public class Storage {
    /** Path to the task storage file. */
    private static final String TASKFILE = "./saved.csv";

    /** Indicates if the save file is valid and can be used. */
    private static boolean saveFileValid = true;

    /**
     * Constructs a Storage object and initializes the task file.
     *
     * @throws OrangeException If there is an error loading tasks.
     */
    public Storage() throws OrangeException {
        try {
            initaliseTaskfile();
        } catch (IOException e) {
            System.out.println(e);
            saveFileValid = false;
        }
        if (saveFileValid) {
            loadTasks();
        }
    }

    /**
     * Initializes the task file by creating it if it does not exist.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    private void initaliseTaskfile() throws IOException {
        Path path = Paths.get(TASKFILE);
        if (Files.exists(path)) {
            System.out.println("File exists, reading task file: " + TASKFILE);
        } else {
            Files.createFile(path);
            System.out.println("Task File created at: " + TASKFILE);
        }
    }

    /** Updates the task storage file with the current task list. */
    public static void updateTaskFile() {
        try {
            FileWriter writer = new FileWriter(TASKFILE, false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error clearing CSV file: " + e.getMessage());
        }

        int i = 0;
        ArrayList<Task> taskList = TaskList.getInstance().getTasks();
        for (Task task : taskList) {
            try {
                Path path = Paths.get(TASKFILE);
                String finalTask = "";
                switch (task.getClass().getName()) {
                case "orange.task.Todo":
					finalTask = "T," + task.getIsDone() + "," + task.getDescription() + ",-,-";
					break;
                case "orange.task.Deadline":
					finalTask =
					"D,"
					+ task.getIsDone()
					+ ","
					+ task.getDescription()
					+ ",-,"
					+ ((Deadline) task).getDateAndTime();
					break;
                case "orange.task.Events":
					finalTask =
					"E,"
					+ task.getIsDone()
					+ ","
					+ task.getDescription()
					+ ","
					+ ((Events) task).getStartDateAndTime()
					+ ","
					+ ((Events) task).getEndDateAndTime();
					break;
                default:
                }
                Files.write(path, List.of(finalTask), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
            }
            i++;
        }
    }

    /**
     * Loads tasks from the storage file into the task manager's list.
     *
     * @throws OrangeException If an error occurs while parsing the task file.
     */
    private void loadTasks() throws OrangeException {
        try {
            Path taskPath = Paths.get(TASKFILE);
            List<String> lines = Files.readAllLines(taskPath);
            for (String line : lines) {
                String[] values = line.split(",");
                switch (values[0]) {
                case "T":
					TaskList.getInstance()
					.addTask(new Todo(values[2], Boolean.parseBoolean(values[1])));
					break;
                case "D":
					TaskList.getInstance()
					.addTask(
					new Deadline(
					values[2],
					Boolean.parseBoolean(values[1]),
					values[4]));
					break;
                case "E":
					TaskList.getInstance()
					.addTask(
					new Events(
					values[2],
					Boolean.parseBoolean(values[1]),
					values[4],
					values[3]));
					break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file and loading tasks: " + e.getMessage());
            saveFileValid = false;
        }
    }

    /**
     * Returns whether the save file is valid.
     *
     * @return True if the save file is valid, false otherwise.
     */
    public boolean getSaveFileValid() {
        return saveFileValid;
    }
}

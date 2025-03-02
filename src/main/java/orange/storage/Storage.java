/**
 * Manages the loading and saving of tasks to a file.
 * This class handles reading tasks from a CSV file and updating it with new tasks.
 *
 * @see Task
 * @see TaskList
 * @see Deadline
 * @see Events
 * @see Todo
 */
package orange.storage;

import orange.task.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    /**
     * The file path for storing tasks.
     */
    private static final String TASKFILE = "./saved.csv";

    /**
     * Indicates whether the save file is valid.
     */
    private static boolean saveFileValid = true;

    /**
     * Initializes the task file by creating it if it does not exist.
     *
     * @throws IOException If an I/O error occurs while creating the file.
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

    /**
     * Updates the task file by saving the current task list to a CSV file.
     */
    public static void updateTaskFile() {
        try {
            FileWriter writer = new FileWriter(TASKFILE, false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error clearing csv file: " + e.getMessage());
        }

        ArrayList<Task> taskList = TaskList.getInstance().getTasks();
        int i = 0;
        for (Task task : taskList) {
            try {
                Path path = Paths.get(TASKFILE);
                String taskEntry;
                switch (task.getClass().getName()) {
                    case "orange.task.Todo":
                        taskEntry = "T," + task.getIsDone() + "," + task.getDescription() + ",-,-";
                        break;
                    case "orange.task.Deadline":
                        taskEntry = "D," + task.getIsDone() + "," + task.getDescription() + ",-," + ((Deadline) task).getDateAndTime();
                        break;
                    case "orange.task.Events":
                        taskEntry = "E," + task.getIsDone() + "," + task.getDescription() + "," + ((Events) task).getStartDateAndTime() + "," + ((Events) task).getEndDateAndTime();
                        break;
                    default:
                        continue;
                }
                Files.write(path, List.of(taskEntry), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
            }
            i++;
        }
    }

    /**
     * Loads tasks from the task file into the task manager.
     */
    private void loadTasks() {
        try {
            Path taskPath = Paths.get(TASKFILE);
            List<String> lines = Files.readAllLines(taskPath);
            for (String line : lines) {
                String[] values = line.split(",");
                switch (values[0]) {
                    case "T":
                        TaskList.getInstance().addTask(new Todo(values[2], values[1].equals("true")));
                        break;
                    case "D":
                        TaskList.getInstance().addTask(new Deadline(values[2], values[1].equals("true"), values[4]));
                        break;
                    case "E":
                        TaskList.getInstance().addTask(new Events(values[2], values[1].equals("true"), values[4], values[3]));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file and loading tasks: " + e.getMessage());
            saveFileValid = false;
        }
    }

    /**
     * Constructs a Storage instance.
     * <p>
     * Initializes the task file by creating it if it does not exist and loads any saved tasks.
     * </p>
     */
    public Storage() {
        try {
            initaliseTaskfile();
        } catch (IOException e) {
            System.out.println(e);
            saveFileValid = false;
        }
        if (saveFileValid) loadTasks();
    }

    /**
     * Retrieves the status of the save file.
     *
     * @return True if the save file is valid, false otherwise.
     */
    public boolean getSaveFileValid() {
        return saveFileValid;
    }
}

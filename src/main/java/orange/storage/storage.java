package orange.storage;

import orange.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


//Deals with loading tasks from the file and saving tasks in the file
public class storage {

    private static final String TASKFILE = "./saved.csv"; //location of the taskfile
    private static boolean saveFileValid = true; //if save file is not valid, it will not be used

    //On initalisation, create file if it does not exist
    private void initaliseTaskfile() throws IOException {
        Path path = Paths.get(TASKFILE);
        // Check if the file exists
        if (Files.exists(path)) {
            System.out.println("File exists, reading task file: " + TASKFILE);
        } else {
            // File doesn't exist, so create it
            Files.createFile(path);
            System.out.println("Task File created at: " + TASKFILE);
        }
    }

    //Update the csv
    public void updateTaskFile() {
        //Based on the type of the task there is a different way of updating the csv
        int i = 0;
        ArrayList<Task> taskList = TaskList.getInstance().getTasks();
        for(Task task: taskList) {
            switch(task.getClass().getName()) {
                case "task.Todo":
                    try {
                        Path path = Paths.get(TASKFILE);
                        // Write data rows
                        //Task type,status,task name,from date,to date
                        String finalTask = "T," + "0," + task +",-,-\n";
                        Files.write(path, List.of(finalTask), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
                    }
                    break;
                case "task.Deadline":
                    try {
                        Path path = Paths.get(TASKFILE);
                        // Write data rows
                        //Task type,status,task name,from date,to date
                        String finalDeadline = "D,0," + task + ",-," + ((Deadline) task).getDateAndTime() + "\n";
                        Files.write(path, List.of(finalDeadline), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
                    }
                    break;
                case "task.Event":
                    try {
                        Path path = Paths.get(TASKFILE);
                        // Write data rows
                        //Task type,status,task name,from date,to date
                        String finalEvent = "D,0," + task + "," + ((Events) task).getStartDateAndTime() + "," + ((Events) task).getEndDateAndTime() + "\n";
                        Files.write(path, List.of(finalEvent), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
                    }
                    break;
            }
            i++;
        }
    }

    //Load Tasks into taskmanager's arraylist
    private void loadTasks() {
        try {
            Path taskPath = Paths.get(TASKFILE);
            List<String> lines = Files.readAllLines(taskPath);
            for (String line : lines) {
                String[] values = line.split(",");
                switch(values[0]) {
                    case "T":
                        int status = Integer.parseInt(values[1]);
                        boolean taskStatus = status == 1;
                        Todo todo = new Todo(values[2],taskStatus);
                        TaskList.getInstance().addTask(todo);
                        break;
                    case "D":
                        int deadlines = Integer.parseInt(values[1]);
                        boolean deadlineStatus = deadlines == 1;
                        Deadline deadline = new Deadline(values[2],deadlineStatus,values[4]);
                        TaskList.getInstance().addTask(deadline);
                        break;
                    case "E":
                        int events = Integer.parseInt(values[1]);
                        boolean eventsStatus = events == 1;
                        Events event = new Events(values[2],eventsStatus,values[4],values[3]);
                        TaskList.getInstance().addTask(event);
                        break;
                }
            }

        }catch (IOException e) {
            System.out.println("Error reading the file and loading tasks: " + e.getMessage());
            saveFileValid = false;
        }

    }

    //Constructor
    public storage(ArrayList<Task> tasks) {
        //Initalises task file (creates it if it does not exist)
        try {
            initaliseTaskfile();
        } catch (IOException e) {
            //Critical Error
            //If storage file cannot be created, chatbot can be used, but there will be no task saving feature
            saveFileValid = false;
        }

        //Read the task file and load any tasks into the arraylist of tasks
        //Task type,status,task name,from date,to date
        if(saveFileValid) loadTasks();
    }

    public boolean getSaveFileValid() {
        return saveFileValid;
    }

}
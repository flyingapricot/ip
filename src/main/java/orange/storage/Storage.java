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


//Deals with loading tasks from the file and saving tasks in the file
public class Storage {

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
    public static void updateTaskFile() {
        //Based on the type of the task there is a different way of updating the csv
        try {
            FileWriter writer = new FileWriter(TASKFILE, false); // false to overwrite
            writer.write("");  // Write the new content
            writer.close();
        } catch(IOException e) {
            System.out.println("Error clearing csv file" + e.getMessage());
        }
        int i = 0;
        ArrayList<Task> taskList = TaskList.getInstance().getTasks();
        for(Task task: taskList) {
            switch(task.getClass().getName()) {
                case "orange.task.Todo":
                    try {
                        Path path = Paths.get(TASKFILE);
                        // Write data rows
                        //Task type,status,task name,from date,to date
                        String finalTask = "T," + task.getIsDone() + "," + task.getDescription() +",-,-";
                        Files.write(path, List.of(finalTask), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
                    }
                    break;
                case "orange.task.Deadline":
                    try {
                        Path path = Paths.get(TASKFILE);
                        // Write data rows
                        //Task type,status,task name,from date,to date
                        String finalDeadline = "D,"  + task.getIsDone() + "," + task.getDescription() + ",-," + ((Deadline) task).getDateAndTime();
                        Files.write(path, List.of(finalDeadline), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Error writing Task " + i + " to CSV file: " + e.getMessage());
                    }
                    break;
                case "orange.task.Events":
                    try {
                        Path path = Paths.get(TASKFILE);
                        // Write data rows
                        //Task type,status,task name,from date,to date
                        String finalEvent = "E," + task.getIsDone() + "," + task.getDescription() + "," + ((Events) task).getStartDateAndTime() + "," + ((Events) task).getEndDateAndTime();
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
    private void loadTasks() throws OrangeException {
        try {
            Path taskPath = Paths.get(TASKFILE);
            List<String> lines = Files.readAllLines(taskPath);
            for (String line : lines) {
                String[] values = line.split(",");
                switch(values[0]) {
                    case "T":
                        boolean taskStatus = values[1].equals("true");
                        Todo todo = new Todo(values[2],taskStatus);
                        TaskList.getInstance().addTask(todo);
                        break;
                    case "D":
                        boolean deadlineStatus = values[1].equals("true");
                        Deadline deadline = new Deadline(values[2],deadlineStatus,values[4]);
                        TaskList.getInstance().addTask(deadline);
                        break;
                    case "E":
                        boolean eventsStatus = values[1].equals("true");
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
    public Storage() throws OrangeException{
        //Initalises task file (creates it if it does not exist)
        try {
            initaliseTaskfile();
        } catch (IOException e) {
            //Critical Error
            //If storage file cannot be created, chatbot can be used, but there will be no task saving feature
            System.out.println(e);
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
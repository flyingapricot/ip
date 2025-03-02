package orange.task;


import orange.exception.OrangeException;

import java.util.ArrayList;

public class TaskList {
    //Single instance of task manager
    private static TaskList instance;

    //Single list of tasks (shared by all Task objects)
    private static ArrayList<Task> taskList = new ArrayList<>();

    // Private constructor to prevent instantiation from other classes
    private TaskList() {}

    // Static method to get the instance of TaskList
    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    //Add to task list
    public void addTask(Task task) {
        taskList.add(task);
    }

    //Method to update the task list
    public void updateTask(int taskNumber, Task newTask) throws IndexOutOfBoundsException {
        if(taskNumber >= taskList.size() || taskNumber < 0) {
            //Task does not exist
            throw new IndexOutOfBoundsException();
        }
        //Update the task at the given task number
        taskList.set(taskNumber, newTask);
    }

    //Method to delete task
    public void deleteTask(int taskNumber) throws IndexOutOfBoundsException {
        if(taskNumber >= taskList.size() || taskNumber < 0) {
            //Task does not exist
            throw new IndexOutOfBoundsException();
        }
        taskList.remove(taskNumber);
    }

    //Method to list all tasks
    public void listTasks() {
        int count = 1;
        for (Task task : taskList) {
            System.out.println("\t" + count + ". " + task.GetTaskWithCompletion());
            count++;
        }
    }

    //Get the taskList
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(taskList); //Sending a copy to prevent external modification
    }

    //Get the taskList size
    public int getSize() {
        return taskList.size();
    }

    //Get the task at a given index
    public void updateCompletionStatus(int taskNumber,boolean status) throws IndexOutOfBoundsException {
        if(taskNumber >= taskList.size() || taskNumber < 0) {
            //Task does not exist
            throw new IndexOutOfBoundsException();
        }
        taskList.get(taskNumber).setIsDone(status);
    }

}

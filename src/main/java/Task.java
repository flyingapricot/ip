public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description,boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void printTaskWithCompletion() {
        if(this.isDone == true) {
            System.out.print("[X] " + this.description);
        }else if(this.isDone == false) {
            System.out.print("[ ] " + this.description);
        }
    }

    public String GetTaskWithCompletion() {
        if(this.isDone == true) {
            return "[X] " + this.description;
        }else if(this.isDone == false) {
            return "[ ] " + this.description;
        }
        return "";
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}

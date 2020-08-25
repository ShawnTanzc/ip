public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String task) { //constructor
        this.taskName = task;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String isDone() {
        String checker;
        if (this.isDone == true) {
            checker = "[\u2713]";
        } else {
            checker = "[\u2718]";
        }
        return checker;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }
}

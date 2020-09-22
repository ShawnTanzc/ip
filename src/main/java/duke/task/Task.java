package duke.task;

/**
 * Represents a general superclass over deadline, event and todo
 * Sets and gets several information that are common between the 3
 * types of task
 */
public abstract class Task {
    private String taskName;
    private boolean isDone;

    public Task(String task) {
        this.taskName = task;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName.trim();
    }

    public abstract String getTaskType();

    public boolean isDoneChecker() {
        boolean checker;
        if (this.isDone) {
            checker = true;
        } else {
            checker = false;
        }
        return checker;
    }

    public abstract String getTaskDetails();

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

    @Override
    public String toString() {
        return isDone() + " " + getTaskName();
    }
}

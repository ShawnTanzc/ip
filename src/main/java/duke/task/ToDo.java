package duke.task;

/**
 * Handles todo-related task and sort them into a more readable format
 */
public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getTaskDetails() {
        return "";
    }
}

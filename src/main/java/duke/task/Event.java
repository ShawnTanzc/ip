package duke.task;

/**
 * Handles event-related task and sort them into a more readable format
 */
public class Event extends Task {
    private String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    /**
     * Return a fixed format when user inputs a event task
     *
     * @return event task for printing
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String getTaskDetails() {
        return at;
    }
}

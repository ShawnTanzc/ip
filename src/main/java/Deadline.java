public class Deadline extends Task {
    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
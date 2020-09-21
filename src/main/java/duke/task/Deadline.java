package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getTaskDetails() {
        return String.valueOf(by);
    }
}

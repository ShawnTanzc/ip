package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline(String taskName, LocalDate byDate, LocalTime byTime) {
        super(taskName);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public LocalDate getByDate() {
        return byDate;
    }
    public LocalTime getByTime() {
        return byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByDate().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + getByTime().format(DateTimeFormatter.ofPattern("hh:mm a")) + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String getTaskDetails() {
        return String.valueOf(byDate) + "," + String.valueOf(byTime);
    }
}

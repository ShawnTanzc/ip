package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import static duke.command.TaskList.printExceptionMessage;

public class Storage {

    private static final String ERROR_DATE_TIME_FORMAT = "Incorrect date format provided. Use YYYY-MM-DD for the date.";
    private final String SEGMENT_DIVIDER = " | ";
    private String file;

    public Storage(String file) {
        setFile(file);
    }

    public String getFile() {
        return file;
    }


    public void setFile(String file) {
        this.file = file;
    }

    public void saveFile(ArrayList<Task> taskList) throws IOException {

        FileWriter fw = new FileWriter(file);
        String inputs = "";
        for (Task task: taskList) {
            inputs += task.getTaskType();
            inputs += SEGMENT_DIVIDER;
            inputs += task.isDoneChecker() ? "1" : "0";
            inputs += SEGMENT_DIVIDER;
            inputs += task.getTaskName();
            inputs += SEGMENT_DIVIDER;
            inputs += task.getTaskDetails();
            inputs += "\n";
        }
        fw.write(inputs);
        fw.close();
    }
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(file);
        Scanner scanner = new Scanner(f);
        String line;
        String[] items;
        String taskType;
        String isDoneChecker;
        String taskDetails;
        String deadlineDate = "";

        try {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                items = line.split(" \\| ");
                taskType = items[0];
                isDoneChecker = items[1];
                taskDetails = items[2];
                if (items.length == 4) {
                    deadlineDate = items[3];
                }
                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(taskDetails);
                    if (isDoneChecker.equals("1")) {
                        todo.setDone(true);
                    }
                    taskList.add(todo);
                    break;
                case "D":
                    LocalDate deadlineDateFormat = LocalDate.parse(deadlineDate);
                    Deadline deadline = new Deadline(taskDetails, deadlineDateFormat);
                    if (isDoneChecker.equals("1")) {
                        deadline.setDone(true);
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskDetails, deadlineDate);
                    if (isDoneChecker.equals("1")) {
                        event.setDone(true);
                    }
                    taskList.add(event);
                    break;
                }
            }
            return taskList;
        } catch (DateTimeException e) {
            printExceptionMessage(ERROR_DATE_TIME_FORMAT);
        }
        return taskList;
    }
}

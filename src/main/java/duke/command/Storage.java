package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import static duke.command.TaskList.printExceptionMessage;

/**
 * Deals with loading tasks from the file and saving task
 * in the file
 */
public class Storage {

    private static final String ERROR_DATE_TIME_FORMAT = "Incorrect date/time format provided. Use YYYY-MM-DD,HH:MM.";
    private static final String ERROR_READING_FILE = "Unable to read file. Check the text file again";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int DONE_CHECKER_INDEX = 1;
    private static final int TASK_DETAILS_INDEX = 2;
    private static final int DATE_TIME_INDEX = 3;
    private static final String DONE = "1";
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

    /**
     * Writes file in a specific format with individual segments
     * of the task in the taskList
     *
     * @param taskList the list that stores the current tasks
     * @throws IOException exception with inputs and outputs with writing
     */
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

    /**
     * Loads the text file and extracts relevant information
     * to recover the previously saved task list
     *
     * @return taskList the list that stores the current tasks
     * @throws FileNotFoundException if file cant be read
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(file);
        Scanner scanner = new Scanner(f);
        String dateAndTimeDetails = "";
        try {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] items = line.split(" \\| ");
                String taskType = items[TASK_TYPE_INDEX];
                String DoneChecker = items[DONE_CHECKER_INDEX];
                String taskDetails = items[TASK_DETAILS_INDEX];
                if (items.length == 4) {
                    dateAndTimeDetails = items[DATE_TIME_INDEX];
                }
                switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(taskDetails);
                    setToDoIfDone(DoneChecker, todo);
                    taskList.add(todo);
                    break;
                case "D":
                    Deadline deadline = getDeadlineFormat(dateAndTimeDetails, taskDetails);
                    checkDeadlineIfDone(DoneChecker, deadline);
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(taskDetails, dateAndTimeDetails);
                    checkEventIfDone(DoneChecker, event);
                    taskList.add(event);
                    break;
                }
            }
            return taskList;
        } catch (ArrayIndexOutOfBoundsException e) {
            printExceptionMessage(ERROR_READING_FILE);
        } catch (DateTimeException e) {
            printExceptionMessage(ERROR_DATE_TIME_FORMAT);
        }
        return taskList;
    }

    /**
     * Arrange the different segments into proper deadline format
     * @param dateAndTimeDetails input date and time details
     * @param taskDetails input name of task
     * @return Deadline in the format to be printed
     */
    public Deadline getDeadlineFormat(String dateAndTimeDetails, String taskDetails) {
        String[] dateAndTimeParts = dateAndTimeDetails.split(",");
        LocalDate deadlineDateFormat = LocalDate.parse(dateAndTimeParts[0]);
        LocalTime deadlineTimeFormat = LocalTime.parse(dateAndTimeParts[1]);
        Deadline deadline = new Deadline(taskDetails, deadlineDateFormat, deadlineTimeFormat);
        return deadline;
    }

    /**
     * Check and set ToDo Task as Done
     * @param doneChecker input to be check if done
     * @param todo ToDo task to be set
     */
    public void setToDoIfDone(String doneChecker, ToDo todo) {
        if (isDoneForCheck(doneChecker)) {
            todo.setDone(true);
        }
    }

    /**
     * Check and set Event Task as Done
     * @param doneChecker input to be check if done
     * @param event Event task to be set
     */
    public void checkEventIfDone(String doneChecker, Event event) {
        if (isDoneForCheck(doneChecker)) {
            event.setDone(true);
        }
    }

    /**
     * Check and set Deadline Task as Done
     * @param doneChecker input to be check if done
     * @param deadline Deadline task to be set
     */
    public void checkDeadlineIfDone(String doneChecker, Deadline deadline) {
        if (isDoneForCheck(doneChecker)) {
            deadline.setDone(true);
        }
    }

    /**
     * Returns true if task is checked to be done
     *
     * @param doneChecker String that value of completion
     * @return boolean true or false
     */
    public boolean isDoneForCheck(String doneChecker) {
        return doneChecker.equals(DONE);
    }
}

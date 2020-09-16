package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {
    private final String SEGMENT_DIVIDER = "|";
    private String file;

    public FileIO (String file) {
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
        String inputs = "My Task(s): \n";
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
    public void loadFile(ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(file);
        Scanner scanner = new Scanner(f);
        String taskType;
        String isDoneChecker;
        String taskDetails;
        String dateAndTime = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] items;
            items = line.split("|");
            taskType = items[0];
            isDoneChecker = items[1];
            taskDetails = items[2];
            if (items.length == 4) {
                dateAndTime = items[3];
            }
            switch (taskType) {
            case "T":
                ToDo todo = new ToDo("todo " + taskDetails);
                if (isDoneChecker.equals("1")) {
                    todo.setDone(true);
                }
                taskList.add(todo);
                break;
            case "D":
                Deadline deadline = new Deadline("deadline " + taskDetails, dateAndTime);
                if (isDoneChecker.equals("1")) {
                    deadline.setDone(true);
                }
                taskList.add(deadline);
                break;
            case "E":
                Event event = new Event("event " + taskDetails, dateAndTime);
                if (isDoneChecker.equals("1")) {
                    event.setDone(true);
                }
                taskList.add(event);
                break;
            default:
                break;
            }
        }
    }
}

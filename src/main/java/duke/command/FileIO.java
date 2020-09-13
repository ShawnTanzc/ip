package duke.command;

import duke.task.Task;
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
}

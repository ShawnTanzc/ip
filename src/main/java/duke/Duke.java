package duke;

import duke.command.DukeException;
import duke.command.TaskList;
import duke.task.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.command.Parser.*;
import static duke.command.Ui.*;
import static duke.command.TaskList.*;

public class Duke {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        TaskList taskList = new TaskList();
        greet();
        String username = in.nextLine().trim();
        displayUsername(username);
        String userReply = in.nextLine();
        taskList.loadTaskList();

        while (true) {
            String userRequest = in.nextLine();
            if (isTypedList(userRequest)) {
                taskList.printTaskList();
            } else if (isTypedBye(userRequest)) {
                taskList.saveTaskList();
                bye();
                return;
            } else if (isTypedDone(userRequest)) {
                taskList.done(userRequest);
            } else if (isTypedDelete(userRequest)) {
                deleteTask(userRequest);
            } else {
                addTask(userRequest);
            }
        }
    }
}

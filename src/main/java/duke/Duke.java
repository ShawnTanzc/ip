package duke;

import duke.command.TaskList;
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
        String anythingReply = in.nextLine();
        taskList.loadTaskList();


        while (true) {
            String userRequest = in.nextLine();
            if (isTypedList(userRequest)) {
                printTaskList();
            } else if (isTypedBye(userRequest)) {
                saveTaskList();
                bye();
                break;
            } else if (isTypedDone(userRequest)) {
                done(userRequest);
            } else if (isTypedDelete(userRequest)) {
                deleteTask(userRequest);
            } else if (isTypeFind(userRequest)) {
                findKeyword(userRequest);
            } else {
                addTask(userRequest);
            }
        }
    }

}

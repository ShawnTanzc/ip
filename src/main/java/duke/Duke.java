package duke;

import duke.command.TaskList;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.command.Parser.isTypeFind;
import static duke.command.Parser.isTypedBye;
import static duke.command.Parser.isTypedDelete;
import static duke.command.Parser.isTypedDone;
import static duke.command.Parser.isTypedList;
import static duke.command.TaskList.addTask;
import static duke.command.TaskList.deleteTask;
import static duke.command.TaskList.done;
import static duke.command.TaskList.findKeyword;
import static duke.command.TaskList.printTaskList;
import static duke.command.TaskList.saveTaskList;
import static duke.command.Ui.bye;
import static duke.command.Ui.displayUsername;
import static duke.command.Ui.greet;

/**
 * Represents the main program which takes in various task entry and
 * multiple task tracking commands by the user
 */
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

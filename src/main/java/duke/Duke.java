package duke;

import duke.command.DukeException;
import duke.command.TaskType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String ERROR_LIST_EMPTY = "List is currently empty. Please insert task.";
    private static final String ERROR_INCORRECT_FORMAT = "Missing details. Please use the correct format.";
    private static final String ERROR_NO_SUCH_TASK = "Task not detected. Use \"todo\", \"deadline\" or \"event\".";
    private static final String ERROR_TASK_NOT_SET = "Task not created yet. Please create the task first.";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        String username;
        username = in.nextLine().trim();
        System.out.println("Hello " + username + "! What can I do for you?");
        addHorizontalLine();
        while (true) {
            String userRequest = in.nextLine();
            if (userRequest.equals("list")) {
                if (taskList.size() == 0) {
                    printExceptionMessage(ERROR_LIST_EMPTY);
                } else {
                    int listIndex = 1;
                    addHorizontalLine();
                    System.out.println("Here are the tasks in your list:");
                    for (Task task : taskList) {
                        System.out.println(listIndex + ". " + task.toString());
                        listIndex++;
                    }
                    addHorizontalLine();
                }
            } else if (userRequest.equals("bye")) {
                bye();
                return;
            } else if (userRequest.startsWith("done") && (userRequest.split(" ").length == 2)) {
                done(taskList, userRequest);
            } else if (userRequest.startsWith("delete") && (userRequest.split(" ").length == 2)) {
                delete(taskList, userRequest);
            } else {
                try {
                    TaskType newRequest = extractTaskType(userRequest);
                    Task taskEntry = null;
                    if (newRequest != null) {
                        switch (newRequest) {
                        case DEADLINE:
                            String deadlineName = getTaskName(userRequest);
                            String deadlineDetail = getTaskDetail(userRequest);
                            taskEntry = new Deadline(deadlineName, deadlineDetail);
                            break;

                        case EVENT:
                            String eventName = getTaskName(userRequest);
                            String eventDetail = getTaskDetail(userRequest);
                            taskEntry = new Event(eventName, eventDetail);
                            break;

                        case TODO:
                            String toDoName = getToDoName(userRequest);
                            taskEntry = new ToDo(toDoName);
                            break;
                        }
                        addHorizontalLine();
                        System.out.println("Got it. I've added this task: " + taskEntry.toString());
                        taskList.add(taskEntry);
                        numberOfTaskTracker(taskList);
                        addHorizontalLine();
                    } else {
                        printExceptionMessage(ERROR_NO_SUCH_TASK);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    printExceptionMessage(ERROR_INCORRECT_FORMAT);
                } catch (DukeException e) {
                    printExceptionMessage(ERROR_INCORRECT_FORMAT);
                }
            }
        }
    }
    
    public static void greet() {
        addHorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What is your name, sir?");
        addHorizontalLine();
    }

    public static void bye(){
        addHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        addHorizontalLine();
    }

    public static void done(ArrayList<Task> taskList, String userRequest) {
        try {
            String[] items = userRequest.split(" ");
            int index = Integer.parseInt(items[1]) - 1;
            taskList.get(index).setDone(true);
            addHorizontalLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(taskList.get(index).toString());
            addHorizontalLine();
        } catch (IndexOutOfBoundsException e) {
            printExceptionMessage(ERROR_TASK_NOT_SET);
        }
    }

    public static void delete(ArrayList<Task> taskList, String userRequest) {
        try {
            String[] items = userRequest.split(" ");
            int index = Integer.parseInt(items[1]) - 1;
            if (index < taskList.size()) {
                addHorizontalLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.get(index).toString());
                taskList.remove(index);
                numberOfTaskTracker(taskList);
                addHorizontalLine();
            } else {
                printExceptionMessage(ERROR_TASK_NOT_SET);
            }
        } catch (IndexOutOfBoundsException e) {
            printExceptionMessage(ERROR_TASK_NOT_SET);
        }
    }

    public static TaskType extractTaskType(String userRequest) throws DukeException {
        userRequest = userRequest.toLowerCase().trim();
        TaskType currentTaskType;
        if (userRequest.contains("/by") && userRequest.startsWith("deadline")) {
            currentTaskType = TaskType.DEADLINE;
        } else if (userRequest.contains("/at") && userRequest.startsWith("event")) {
            currentTaskType = TaskType.EVENT;
        } else if (userRequest.startsWith("todo")) {
            currentTaskType =  TaskType.TODO;

        } else if (!userRequest.contains("/at") && userRequest.startsWith("event") ||
                    !userRequest.contains("/by") && userRequest.startsWith("deadline")) {
            throw new DukeException();
        } else {
            currentTaskType = null;
        }
        return currentTaskType;
    }

    public static String getTaskName(String userRequest) {
        int startPointIndex = userRequest.indexOf(" ");
        int endPointIndex = userRequest.indexOf("/");
        return userRequest.substring(startPointIndex, endPointIndex);
    }

    public static String getTaskDetail(String userRequest) {
        int startPointIndex = userRequest.lastIndexOf("/");
        return userRequest.substring(startPointIndex + 4);
    }

    public static String getToDoName(String userRequest) {
        int startPointIndex = userRequest.indexOf(" ");
        return userRequest.substring(startPointIndex);
    }

    public static void numberOfTaskTracker(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
    }

    public static void printExceptionMessage(String exceptionMessage) {
        addHorizontalLine();
        System.out.println(exceptionMessage);
        addHorizontalLine();
    }

    public static void addHorizontalLine() {
        System.out.println("_________________________________________");
    }
}

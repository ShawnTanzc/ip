package duke.command;

import duke.task.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static duke.command.Ui.addHorizontalLine;

public class TaskList {

    private static final String ERROR_LIST_EMPTY = "List is currently empty. Please insert task.";
    private static final String ERROR_INCORRECT_FORMAT = "Missing details. Please use the correct format.";
    private static final String ERROR_NO_SUCH_TASK = "Task not detected. Use \"todo\", \"deadline\" or \"event\".";
    private static final String ERROR_TASK_NOT_SET = "Task not created yet. Please create the task first.";

    private static ArrayList<Task> taskList;
    private static Storage savedFile = new Storage("duke.txt");

    public void loadTaskList() throws FileNotFoundException {
        try {
            System.out.println("Loading... Please Wait.");
            taskList = savedFile.loadFile();
            System.out.println(savedFile.getFile() + " has been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error! " + savedFile.getFile() + "cannot be loaded.");
        }
    }

    public void printTaskList() {
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
    }

    public static void done(String userRequest) {
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

    public static void deleteTask(String userRequest) {
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

    public static void addTask(String userRequest) {
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

    public void saveTaskList() {
        try {
            savedFile.saveFile(taskList);
            addHorizontalLine();
            System.out.println("Task have been saved. File name: " + savedFile.getFile());
        } catch (IOException e) {
            printExceptionMessage("Unable to save file");
        }
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
}

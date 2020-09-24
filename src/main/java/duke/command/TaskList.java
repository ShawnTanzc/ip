package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static duke.command.Ui.addHorizontalLine;

public class TaskList {

    private static final String ERROR_LIST_EMPTY = "List is currently empty. Please insert task.";
    private static final String ERROR_INCORRECT_FORMAT = "Missing details. Please use the correct format.";
    private static final String ERROR_NO_SUCH_TASK = "Task not detected. Use \"todo\", \"deadline\" or \"event\".";
    private static final String ERROR_TASK_NOT_SET = "Task not created yet. Please create the task first.";
    private static final String ERROR_DATE_TIME_FORMAT = "Incorrect date/time format provided. Use YYYY-MM-DD,HH:MM.";

    private static ArrayList<Task> taskList;
    private static final Storage savedFile = new Storage("duke.txt");

    /**
     * Register the text file "duke.txt" and load the content into
     * the arraylist taskList
     *
     */
    public void loadTaskList() {
        try {
            System.out.println("Loading... Please Wait.");
            taskList = savedFile.loadFile();
            System.out.println(savedFile.getFile() + " has been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error! " + savedFile.getFile() + " cannot be found.");
        }
    }

    /**
     * Print all the tasks in the latest task list with its completeness
     * Inform user if task list is currently empty
     */
    public static void printTaskList() {
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

    /**
     * Extracts the index of the task user requested
     * and checks the task as done
     *
      * @param userRequest the request typed in by the user
     */
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

    /**
     * Extracts the index of the task user requested
     * and remove the task from the task list
     *
     * @param userRequest the request typed in by the user
     */
    public static void deleteTask(String userRequest) {
        try {
            String[] items = userRequest.split(" ");
            int index = Integer.parseInt(items[1]) - 1;
            if (index < taskList.size()) {
                addHorizontalLine();
                System.out.println("Noted. I've removed this task:");
                System.out.println(taskList.get(index).toString());
                taskList.remove(index);
                trackNumberOfTasks(taskList);
                addHorizontalLine();
            } else {
                printExceptionMessage(ERROR_TASK_NOT_SET);
            }
        } catch (IndexOutOfBoundsException e) {
            printExceptionMessage(ERROR_TASK_NOT_SET);
        }
    }

    /**
     * Extracts the keyword the user typed and run through the
     * task list for the same keyword and display the list of
     * matching task
     *
     * @param userRequest the request typed in by the user
     */
    public static void findKeyword(String userRequest) {
        int startPointIndex = userRequest.indexOf(" ");
        int searchListIndex = 1;
        String keyword = userRequest.substring(startPointIndex + 1);
        addHorizontalLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task currentTask: taskList) {
            if(currentTask.getTaskName().contains(keyword)) {
                System.out.println(searchListIndex + ". " + currentTask.toString());
                searchListIndex++;
            }
        }
        addHorizontalLine();
    }

    /**
     * Takes in the userRequest and sort it into various types of task
     * Extracts individual details of the task and add it into the task list
     * If userRequest does not match any of the categories, it informs user
     * that there is no such task
     * If userRequest matches one of the types but miss some required details,
     * it informs user that format is incorrect
     *
     * @param userRequest the request typed in by the user
     */
    public static void addTask(String userRequest) {
        try {
            TaskType newRequest = extractTaskType(userRequest);
            Task taskEntry = null;
            if (newRequest != null) {
                switch (newRequest) {
                case DEADLINE:
                    taskEntry = getDeadlineEntry(userRequest);
                    break;
                case EVENT:
                    taskEntry = getEventEntry(userRequest);
                    break;
                case TODO:
                    taskEntry = getToDoEntry(userRequest);
                    break;
                }
                addHorizontalLine();
                System.out.println("Got it. I've added this task: " + taskEntry.toString());
                taskList.add(taskEntry);
                trackNumberOfTasks(taskList);
                addHorizontalLine();
            } else {
                printExceptionMessage(ERROR_NO_SUCH_TASK);
            }
        } catch (StringIndexOutOfBoundsException | DukeException e) {
            printExceptionMessage(ERROR_INCORRECT_FORMAT);
        } catch (DateTimeException e) {
            printExceptionMessage(ERROR_DATE_TIME_FORMAT);
        }
    }

    /**
     * Form up individual segments of the todo task and present
     * it in a task entry to be printed with the right format
     * @param userRequest the request typed in by the user
     * @return task entry to be printed with the right format
     */
    public static Task getToDoEntry(String userRequest) {
        Task taskEntry;
        String toDoName = getToDoName(userRequest);
        taskEntry = new ToDo(toDoName);
        return taskEntry;
    }

    /**
     * Form up individual segments of the event task and present
     * it in a task entry to be printed with the right format
     * @param userRequest the request typed in by the user
     * @return task entry to be printed with the right format
     */
    public static Task getEventEntry(String userRequest) {
        Task taskEntry;
        String eventName = getTaskName(userRequest);
        String eventDetail = getTaskDetail(userRequest);
        taskEntry = new Event(eventName, eventDetail);
        return taskEntry;
    }

    /**
     * Form up individual segments of the deadline task and present
     * it in a task entry to be printed with the right format
     * @param userRequest the request typed in by the user
     * @return task entry to be printed with the right format
     */
    public static Task getDeadlineEntry(String userRequest) {
        Task taskEntry;
        String deadlineName = getTaskName(userRequest);
        String deadlineDetail = getTaskDetail(userRequest);
        String[] detailParts = deadlineDetail.split(",");
        LocalDate deadlineDateFormat = LocalDate.parse(detailParts[0]);
        LocalTime deadlineTimeFormat = LocalTime.parse(detailParts[1]);
        taskEntry = new Deadline(deadlineName, deadlineDateFormat, deadlineTimeFormat);
        return taskEntry;
    }

    /**
     * Identifies and extracts the type of task namely: Deadline, Event or Todo
     *
     * @param userRequest the request typed in by the user
     * @return the current task type to be sorted into various requirement
     * @throws DukeException print error for which the format is incomplete or incorrect
     */
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

    /**
     * Extracts the name of the task from userRequest
     *
     * @param userRequest the request typed in by the user
     * @return String extracted name of task
     */
    public static String getTaskName(String userRequest) {
        int startPointIndex = userRequest.indexOf(" ");
        int endPointIndex = userRequest.indexOf("/");
        return userRequest.substring(startPointIndex, endPointIndex);
    }

    /**
     * Extracts the details of the task from userRequest such as
     * date and time
     *
     * @param userRequest the request typed in by the user
     * @return String extracted details of task
     */
    public static String getTaskDetail(String userRequest) {
        int startPointIndex = userRequest.lastIndexOf("/");
        return userRequest.substring(startPointIndex + 4);
    }

    /**
     * Extracts the name of the ToDo task from the user Request
     *
     * @param userRequest the request typed in by the user
     * @return String extracted name of the ToDo task
     */
    public static String getToDoName(String userRequest) {
        int startPointIndex = userRequest.indexOf(" ");
        return userRequest.substring(startPointIndex);
    }

    /**
     * Stores the content of the taskList into the text file specified
     */
    public static void saveTaskList() {
        try {
            savedFile.saveFile(taskList);
            addHorizontalLine();
            System.out.println("Task have been saved. File name: " + savedFile.getFile());
        } catch (IOException e) {
            printExceptionMessage("Unable to save file.");
        }
    }

    /**
     * Tracks the number of task currently in the taskList
     *
     * @param taskList the list that store tasks
     */
    public static void trackNumberOfTasks(ArrayList<Task> taskList) {
        int numberOfTasks = taskList.size();
        System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
    }

    /**
     * Prints any exception message provided
     *
     * @param exceptionMessage message for user if any issues encountered
     */
    public static void printExceptionMessage(String exceptionMessage) {
        addHorizontalLine();
        System.out.println(exceptionMessage);
        addHorizontalLine();
    }
}

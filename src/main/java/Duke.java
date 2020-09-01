import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
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
        while (true) {
            String userRequest = in.nextLine();
            if (userRequest.equals("list")) {
                int listIndex = 1;
                addHorizontalLine();
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(listIndex + ". " + task.toString());
                    listIndex++;
                }
                addHorizontalLine();
            } else if (userRequest.equals("bye")) {
                bye();
                return;
            } else if (userRequest.startsWith("done") && userRequest.split(" ").length == 2) {
                done(taskList, userRequest);
            } else if (userRequest.startsWith("done") && userRequest.split("").length != 2) {
                printErrorMessage();
            } else {
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
                    default:
                        printErrorMessage();
                    }
                    addHorizontalLine();
                    System.out.println("Got it. I've added this task: " + taskEntry.toString());
                    numberOfTaskTracker(taskList);
                    addHorizontalLine();
                    taskList.add(taskEntry);
                } else {
                    printErrorMessage();
                }

            }
        }
    }

    public static void greet() {
        Scanner in = new Scanner(System.in);
        addHorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What is your name, sir?");
        addHorizontalLine();
        String username;
        username = in.nextLine().trim();
        System.out.println("Hello " + username + "! What can I do for you?");
        addHorizontalLine();
    }

    public static void bye(){
        addHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        addHorizontalLine();
    }

    public static void done(ArrayList<Task> taskList, String userRequest) {
        String[] items = userRequest.split(" ");
        int index = Integer.parseInt(items[1])-1;
        taskList.get(index).setDone(true);
        addHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(index).toString());
        addHorizontalLine();
    }

    public static void addHorizontalLine() {
        System.out.println("_________________________________________");
    }

    public static TaskType extractTaskType(String userRequest) {
        userRequest = userRequest.toLowerCase().trim();
        TaskType currentTaskType;
        if (userRequest.contains("/by") && userRequest.startsWith("deadline")) {
            currentTaskType = TaskType.DEADLINE;
        } else if (userRequest.contains("/at") && userRequest.startsWith("event")) {
            currentTaskType = TaskType.EVENT;
        } else if (userRequest.startsWith("todo")) {
            currentTaskType =  TaskType.TODO;
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
        int numberOfTasks = taskList.size() + 1;
        System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
    }

    public static void printErrorMessage() {
        System.out.println("Error! Please create task in the correct format.");
    }
}

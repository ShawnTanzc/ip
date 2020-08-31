import java.util.Scanner;
import java.util.ArrayList;

public class    Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        String userRequest;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        while (true) {
            userRequest = in.nextLine().toLowerCase();
            if (userRequest.equals("list")) {
                list(taskList);
            } else if (userRequest.equals("bye")) {
                bye();
                return;
            } else if (userRequest.startsWith("done") && userRequest.split(" ").length == 2) {
                String[] items = userRequest.split(" ");
                int index = Integer.parseInt(items[1])-1;
                taskList.get(index).setDone(true);
                horizontalLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index).toString());
                horizontalLine();
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
                        System.out.println("Error! Please create task in the correct format.");
                    }
                    horizontalLine();
                    System.out.println("Got it. I've added this task: " + taskEntry.toString());
                    int numberOfTasks = taskList.size() + 1;
                    System.out.println("Now you have " + numberOfTasks + " task(s) in the list.");
                    horizontalLine();
                    taskList.add(taskEntry);
                } else {
                    System.out.println("Error! Please create task in the correct format.");
                }

            }
        }
    }
    public static void greet() {
        Scanner in = new Scanner(System.in);
        horizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What is your name, sir?");
        horizontalLine();
        String username;
        username = in.nextLine();
        System.out.println("Hello " + username + "! What can I do for you?");
        horizontalLine();

    }

    public static void list(ArrayList<Task> taskList) {
        int listIndex = 1;
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(listIndex + ". " + task.toString());
            listIndex++;
        }
        horizontalLine();
    }


    public static void bye(){
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public static void horizontalLine() {
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
}

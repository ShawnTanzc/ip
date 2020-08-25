import java.lang.reflect.Array;
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
            } else if (userRequest.startsWith("done") && userRequest.split(" ").length == 2) {
                String[] items = userRequest.split(" ");
                int index = Integer.parseInt(items[1])-1;
                taskList.get(index).setDone(true);
                HorizontalLine();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index).isDone() + " " + taskList.get(index).getTaskName());
                HorizontalLine();
            } else {
                Task newRequest = new Task(userRequest);
                taskList.add(newRequest);
                HorizontalLine();
                System.out.println("added: " + userRequest);
                HorizontalLine();
            }
        }

    }
    public static void greet() {
        Scanner in = new Scanner(System.in);
        HorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What is your name, sir?");
        HorizontalLine();
        String username;
        username = in.nextLine();
        System.out.println("Hello " + username + "! What can I do for you?");
        HorizontalLine();

    }

    public static void list(ArrayList<Task> taskList) {
        int listIndex = 1;
        HorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(listIndex + ". " + task.isDone() + " " + task.getTaskName());
            listIndex++;
        }
        HorizontalLine();
    }

    public static void bye(){
        HorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        HorizontalLine();
    }

    public static void HorizontalLine() {
        System.out.println("_________________________________________");
    }
}

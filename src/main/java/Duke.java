import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class    Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
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
            } else {
                taskList.add(userRequest);
                System.out.println("added: " + userRequest);
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


    public static void list(ArrayList<String> taskList) {
        HorizontalLine();
        int listIndex = 1;
        for (String task : taskList) {
            System.out.println(listIndex + ". " + task);
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

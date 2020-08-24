import java.util.Scanner;
import java.util.Arrays;

public class    Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String username = greet();
        request(username);
    }
    public static String greet() {
        Scanner in = new Scanner(System.in);
        HorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What is your name, sir?");
        HorizontalLine();
        String username;
        username = in.nextLine();

        return username;

    }

    public static void request(String username) {
        Scanner in = new Scanner(System.in);
        String userRequest;
        HorizontalLine();
        while (true) {
            System.out.println("Hello " + username + "! What can I do for you?");
            HorizontalLine();
            userRequest = in.nextLine().toLowerCase();
            if(userRequest.equals("list")) {
                list();
                continue;
            }
            else if(userRequest.equals("blah")) {
                blah();
                continue;
            }
            else if(userRequest.equals("bye")) {
                bye(username);
                break;
            }

        }
    }

    public static void list() {
        HorizontalLine();
        System.out.println("list");
        HorizontalLine();
        return;
    }

    public static void blah() {
        HorizontalLine();
        System.out.println("blah");
        HorizontalLine();
        return;
    }

    public static void bye(String username){
        HorizontalLine();
        System.out.println("Bye " + username + ". Hope to see you again soon!");
        HorizontalLine();
        return;
    }

    public static void HorizontalLine() {
        System.out.println("_________________________________________");
    }
}

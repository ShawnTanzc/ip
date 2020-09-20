package duke.command;

public class Ui {
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        addHorizontalLine();
        System.out.println("Hello I'm Duke");
        System.out.println("What is your name, sir?");
        addHorizontalLine();
    }

    public static void displayUsername(String username) {
        System.out.println("Hello " + username + "! Press any key to continue.");
        addHorizontalLine();
    }

    public static void bye() {
        addHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        addHorizontalLine();
    }

    public static void addHorizontalLine() {
        System.out.println("_________________________________________");
    }
}

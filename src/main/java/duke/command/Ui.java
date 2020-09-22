package duke.command;

/**
 * deals with the interaction with user
 */
public class Ui {
    /**
     * Greets with introduction and request for
     * user's name
     */
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

    /**
     * Greet user by its name
     *
     * @param username the name input by user
     */
    public static void displayUsername(String username) {
        System.out.println("Hello " + username + "! Press any key to continue.");
        addHorizontalLine();
    }

    /**
     * Send a goodbye message to user
     */
    public static void bye() {
        addHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        addHorizontalLine();
    }

    public static void addHorizontalLine() {
        System.out.println("_________________________________________");
    }
}

package duke.command;

public class Parser {



    public static boolean isTypedList(String userRequest) {
        return userRequest.equals("list");
    }

    public static boolean isTypedBye(String userRequest) {
        return userRequest.equals("bye");
    }

    public static boolean isTypedDone(String userRequest) {
        return userRequest.startsWith("done") && (userRequest.split(" ").length == 2);
    }

    public static boolean isTypedDelete(String userRequest) {
        return userRequest.startsWith("delete") && (userRequest.split(" ").length == 2);
    }

    public static boolean isTypeFind(String userRequest) {
        return userRequest.startsWith("find");
    }
}

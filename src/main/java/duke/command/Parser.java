package duke.command;

/**
 * Deals with identifying the type of command by the user
 */
public class Parser {

    /**
     * Returns true if user request contains "list", else return false
     *
     * @param userRequest the request typed in by the user
     * @return boolean true or false
     */
    public static boolean isTypedList(String userRequest) {
        return userRequest.equals("list");
    }

    /**
     * Returns true if user request contains "bye", else return false
     *
     * @param userRequest the request typed in by the user
     * @return boolean true or false
     */
    public static boolean isTypedBye(String userRequest) {
        return userRequest.equals("bye");
    }

    /**
     * Returns true if user request contains "done" and an index from the list,
     * else return false
     *
     * @param userRequest the request typed in by the user
     * @return boolean true or false
     */
    public static boolean isTypedDone(String userRequest) {
        return userRequest.startsWith("done") && (userRequest.split(" ").length == 2);
    }

    /**
     * Returns true if user request contains "delete" and an index from the list,
     * else return false
     *
     * @param userRequest the request typed in by the user
     * @return boolean true or false
     */
    public static boolean isTypedDelete(String userRequest) {
        return userRequest.startsWith("delete") && (userRequest.split(" ").length == 2);
    }

    /**
     * Returns true if user request contains "find", else return false
     *
     * @param userRequest the request typed in by the user
     * @return boolean true or false
     */
    public static boolean isTypeFind(String userRequest) {
        return userRequest.startsWith("find");
    }
}

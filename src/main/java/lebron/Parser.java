package lebron;

/**
 * Parser utility class for parsing user input commands.
 * Provides static methods to extract command words and parse various command formats.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding Command object.
     *
     * @param input The user's input string
     * @return The Command object representing the parsed command
     * @throws LebronException If the command is invalid or cannot be parsed
     */
    public static Command parse(String input) throws LebronException {
        String cmd = getCommandWord(input);

        switch (cmd) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIndex(input));
        case "unmark":
            return new UnmarkCommand(parseIndex(input));
        case "todo":
            return new TodoCommand(parseTodo(input));
        case "deadline":
            String[] dd = parseDeadline(input);
            return new DeadlineCommand(dd[0], dd[1]);
        case "event":
            String[] ev = parseEvent(input);
            return new EventCommand(ev[0], ev[1], ev[2]);
        case "delete":
            return new DeleteCommand(parseIndex(input));
        case "find":
            return new FindCommand(parseFind(input));
        case "help":
            return new HelpCommand();
        default:
            throw new LebronException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Extracts the command word from user input.
     * The command word is the first word in the input string.
     *
     * @param input The user's input string
     * @return The command word (first word of the input)
     */
    public static String getCommandWord(String input) {
        return input.trim().split("\\s+")[0];
    }

    /**
     * Parses and extracts a task index from user input.
     * Converts from 1-based user input to 0-based array index.
     *
     * @param input The user's input string containing a task number
     * @return The zero-based index of the task
     * @throws LebronException If the input doesn't contain a valid positive number
     */
    public static int parseIndex(String input) throws LebronException {
        try {
            int idx = Integer.parseInt(input.replaceAll(".*?(\\d+)$", "$1")) - 1;
            if (idx < 0) {
                throw new NumberFormatException();
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new LebronException("Please provide a valid task number."); // Catches poor input from user
        }
    }

    /**
     * Parses a deadline command to extract description and due date.
     * Expected format: "deadline DESCRIPTION /by DATE"
     *
     * @param input The full deadline command string
     * @return Array with [description, date] where both are trimmed
     * @throws LebronException If the format is invalid or fields are empty
     */
    public static String[] parseDeadline(String input) throws LebronException {
        // "deadline <desc> /by <by>"
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new LebronException("Usage: deadline <desc> /by <date>");
        }
        String desc = parts[0].trim();
        String by = parts[1].trim();
        if (desc.isEmpty() || by.isEmpty()) {
            throw new LebronException("lebron.Deadline needs a description and /by.");
        }
        return new String[]{desc, by};
    }

    /**
     * Parses a todo command to extract the task description.
     * Expected format: "todo DESCRIPTION"
     *
     * @param input The full todo command string
     * @return The task description (trimmed)
     * @throws LebronException If the description is empty
     */
    public static String parseTodo(String input) throws LebronException {
        String desc = input.length() > 4 ? input.substring(5).trim() : "";
        if (desc.isEmpty()) {
            throw new LebronException("Missing Description");
        }
        return desc;
    }

    /**
     * Parses an event command to extract description, start time, and end time.
     * Expected format: "event DESCRIPTION /from START /to END"
     *
     * @param input The full event command string
     * @return Array with [description, from, to] where all are trimmed
     * @throws LebronException If the format is invalid or any field is empty
     */
    public static String[] parseEvent(String input) throws LebronException {
        // "event <desc> /from <from> /to <to>"
        String without = input.substring(6);
        String[] partsFrom = without.split("/from", 2);
        if (partsFrom.length < 2) {
            throw new LebronException("Usage: event <desc> /from <start> /to <end>");
        }

        String desc = partsFrom[0].trim();
        String[] partsTo = partsFrom[1].split("/to", 2);
        if (partsTo.length < 2) {
            throw new LebronException("Usage: event <desc> /from <start> /to <end>");
        }

        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new LebronException("lebron.Event needs a description, /from and /to.");
        }
        return new String[]{desc, from, to};
    }

    /**
     * Parses a find command to extract the search keyword.
     * Expected format: "find KEYWORD"
     *
     * @param input The full find command string
     * @return The search keyword (trimmed)
     * @throws LebronException If the keyword is empty
     */
    public static String parseFind(String input) throws LebronException {
        String trimmed = input.trim();

        // Command word "find" is 4 chars
        if (trimmed.length() <= 4) {
            throw new LebronException("Usage: find <keyword>");
        }

        String keyword = trimmed.substring(4).trim();
        if (keyword.isEmpty()) {
            throw new LebronException("Usage: find <keyword>");
        }
        return keyword;
    }




}

package lebron;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main entry point of the Lebron chatbot application.
 * Lebron coordinates the interaction between the UI, storage,
 * task list, and parser.
 */
public class Lebron {

    private static final String DEFAULT_FILE_PATH = "data/lebron.txt";

    // Handles all user interactions (input/output)
    private final Ui ui;

    // Handles loading from and saving to the data file
    private final Storage storage;

    // Manages the list of tasks in memory
    private TaskList tasks;

    /**
     * Constructs a Lebron instance.
     * Initialises the UI, storage, and loads existing tasks from file.
     *
     * @param filePath Path to the data file used for persistent storage
     */
    public Lebron(String filePath) {
        ui = new Ui(); // Responsible for user interaction
        storage = new Storage(filePath); // Responsible for file I/O
        assert ui != null : "UI should be initialised";
        assert storage != null : "Storage should be initialised";

        // Attempt to load tasks from storage
        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "TaskList should not be null after loading";
        } catch (FileNotFoundException e) {
            // If the file does not exist, start with an empty task list
            ui.showMessage("File not found, starting with an empty list.");
            tasks = new TaskList();
        }
    }


    public Lebron() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Processes a command and returns the response message.
     * This method handles all command logic without performing I/O operations.
     *
     * @param input The user's command input
     * @return The response message to display
     * @throws LebronException If the command is invalid or cannot be executed
     */
    private String processCommand(String input) throws LebronException {
        assert input != null : "Input must not be null";
        assert tasks != null : "Task list must be initialised";

        String cmd = Parser.getCommandWord(input);
        StringBuilder out = new StringBuilder();

        if (cmd.equals("bye")) {
            return "Bye! Hope to see you again soon!";

        } else if (cmd.equals("list")) {
            return tasks.toDisplayString();

        } else if (cmd.equals("mark")) {
            int idx = Parser.parseIndex(input);
            if (idx < 0 || idx >= tasks.size()) {
                throw new LebronException("Task number " + (idx + 1) + " does not exist.");
            }
            tasks.mark(idx);
            return "Nice! I've marked this task as done:\n" + tasks.get(idx);

        } else if (cmd.equals("unmark")) {
            int idx = Parser.parseIndex(input);
            if (idx < 0 || idx >= tasks.size()) {
                throw new LebronException("Task number " + (idx + 1) + " does not exist.");
            }
            tasks.unmark(idx);
            return "OK, I've marked this task as not done yet:\n" + tasks.get(idx);

        } else if (cmd.equals("todo")) {
            String desc = Parser.parseTodo(input);
            tasks.add(new Todo(desc));
            return "Got it. I've added this task:\n"
                    + tasks.get(tasks.size() - 1)
                    + "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if (cmd.equals("deadline")) {
            String[] dd = Parser.parseDeadline(input);
            tasks.add(new Deadline(dd[0], dd[1]));
            return "Got it. I've added this task:\n"
                    + tasks.get(tasks.size() - 1)
                    + "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if (cmd.equals("event")) {
            String[] ev = Parser.parseEvent(input);
            tasks.add(new Event(ev[0], ev[1], ev[2]));
            return "Got it. I've added this task:\n"
                    + tasks.get(tasks.size() - 1)
                    + "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if (cmd.equals("delete")) {
            int idx = Parser.parseIndex(input);
            if (idx < 0 || idx >= tasks.size()) {
                throw new LebronException("Task number " + (idx + 1) + " does not exist.");
            }
            Task removed = tasks.remove(idx);
            return "Noted, I've removed this task:\n"
                    + removed
                    + "\nNow you have " + tasks.size() + " tasks in the list.";

        } else if (cmd.equals("find")) {
            String keyword = Parser.parseFind(input);
            java.util.List<Task> matches = tasks.findMatches(keyword);

            out.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matches.size(); i++) {
                out.append(i + 1).append(". ").append(matches.get(i)).append("\n");
            }
            if (matches.isEmpty()) {
                out.append("(No matching tasks found)");
            }
            return out.toString();

        } else if (cmd.equals("help")) {
            return ui.getHelpMessage();

        } else {
            throw new LebronException("No valid task was created, oh no!");
        }
    }

    /**
     * Runs the main command loop of the chatbot.
     * Continuously reads user input, processes commands,
     * and updates the task list until the user exits.
     */
    public void run() {
        ui.showWelcome();
        assert tasks != null : "Task list must be initialised before run()";

        // Main input-processing loop
        while (true) {
            try {
                String input = ui.readCommand();
                String cmd = Parser.getCommandWord(input);

                if (cmd.equals("bye")) {
                    break;
                }

                String response = processCommand(input);
                ui.showMessage(response);
                ui.showLine();

            } catch (LebronException e) {
                ui.showLine();
                ui.showMessage(e.getMessage());
                ui.showLine();
            }
        }

        // Save tasks to storage before exiting
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks: " + e.getMessage());
        }

        ui.showBye();
    }

    /**
     * Program entry point.
     * Creates a Lebron instance and starts the chatbot.
     */
    public static void main(String[] args) {
        System.out.println("Hello!");
    }

    /**
     * Generates a response for the user's chat message.
     * This method is used by the GUI to process commands.
     *
     * @param input The user's input command
     * @return The response message to display
     */
    public String getResponse(String input) {
        assert input != null : "Input must not be null";
        assert tasks != null : "Task list must be initialised before getResponse()";

        try {
            String cmd = Parser.getCommandWord(input);

            // Handle bye command specially for GUI
            if (cmd.equals("bye")) {
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    return "Bye!\n(But I failed to save tasks: " + e.getMessage() + ")";
                }
                return "Bye! Hope to see you again soon!";
            }

            // Process all other commands
            String response = processCommand(input);

            // Save after every successful command (GUI-friendly)
            try {
                storage.save(tasks);
            } catch (IOException e) {
                response += "\n(Warning: failed to save tasks: " + e.getMessage() + ")";
            }

            return response;

        } catch (LebronException e) {
            return e.getMessage();
        }
    }

}


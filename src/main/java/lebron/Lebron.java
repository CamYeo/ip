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
     * Runs the main command loop of the chatbot.
     * Continuously reads user input, processes commands,
     * and updates the task list until the user exits.
     */
    public void run() {
        ui.showWelcome();
        assert tasks != null : "Task list must be initialised before run()";

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                String response = command.execute(tasks, ui, storage);
                ui.showMessage(response);
                ui.showLine();
                isExit = command.isExit();
            } catch (LebronException e) {
                ui.showLine();
                ui.showMessage(e.getMessage());
                ui.showLine();
            }
        }
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
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);

            // Save after every successful command (except bye which saves itself)
            if (!command.isExit()) {
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    response += "\n(Warning: failed to save tasks: " + e.getMessage() + ")";
                }
            }

            return response;

        } catch (LebronException e) {
            return e.getMessage();
        }
    }

}


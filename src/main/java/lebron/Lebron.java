package lebron;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The main entry point of the Lebron chatbot application.
 * Lebron coordinates the interaction between the UI, storage,
 * task list, and parser.
 */
public class Lebron {

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
        ui = new Ui();                    // Responsible for user interaction
        storage = new Storage(filePath);  // Responsible for file I/O

        // Attempt to load tasks from storage
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            // If the file does not exist, start with an empty task list
            ui.showMessage("File not found, starting with an empty list.");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main command loop of the chatbot.
     * Continuously reads user input, processes commands,
     * and updates the task list until the user exits.
     */
    public void run() {
        ui.showWelcome();

        // Main input-processing loop
        while (true) {
            try {
                String input = ui.readCommand();              // Read user input
                String cmd = Parser.getCommandWord(input);    // Determine command type

                if (cmd.equals("bye")) {
                    // Exit the loop and terminate the program
                    break;

                } else if (cmd.equals("list")) {
                    // Display all tasks
                    ui.showList(tasks);

                } else if (cmd.equals("mark")) {
                    // Mark a task as done
                    int idx = Parser.parseIndex(input);
                    tasks.mark(idx);
                    ui.showMessage("Nice! I've marked this task as done:");
                    ui.showMessage(tasks.get(idx).toString());

                } else if (cmd.equals("unmark")) {
                    // Mark a task as not done
                    int idx = Parser.parseIndex(input);
                    tasks.unmark(idx);
                    ui.showMessage("OK, I've marked this task as not done yet:");
                    ui.showMessage(tasks.get(idx).toString());

                } else if (cmd.equals("todo")) {
                    // Add a todo task
                    String desc = Parser.parseTodo(input);
                    tasks.add(new Todo(desc));
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.get(tasks.size() - 1).toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("deadline")) {
                    // Add a deadline task
                    String[] dd = Parser.parseDeadline(input);
                    tasks.add(new Deadline(dd[0], dd[1]));
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.get(tasks.size() - 1).toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("event")) {
                    // Add an event task
                    String[] ev = Parser.parseEvent(input);
                    tasks.add(new Event(ev[0], ev[1], ev[2]));
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.get(tasks.size() - 1).toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("delete")) {
                    // Delete a task
                    int idx = Parser.parseIndex(input);
                    Task removed = tasks.remove(idx);
                    ui.showMessage("Noted, I've removed this task:");
                    ui.showMessage(removed.toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("find")) {
                    String keyword = Parser.parseFind(input);

                    ui.showMessage("Here are the matching tasks in your list:");
                    java.util.List<Task> matches = tasks.findMatches(keyword);

                    for (int i = 0; i < matches.size(); i++) {
                        ui.showMessage((i + 1) + ". " + matches.get(i));
                    }
                } else {
                    // Unknown command
                    throw new LebronException("No valid task was created, oh no!");
                }

                ui.showLine();
            } catch (LebronException e) {
                // Handle user input errors gracefully
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
        new Lebron("./data/lebron.txt").run();
    }
}

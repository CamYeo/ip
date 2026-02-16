package lebron;

import java.util.Scanner;

/**
 * Handles user interaction through the command line interface.
 * Manages input reading and output display for the application.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("_".repeat(60));
    }


    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello from Lebron!");
        showLine();
    }

    /**
     * Displays the goodbye message when the application exits.
     */
    public void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Reads a command from the user via standard input.
     *
     * @return The user's input as a string
     */
    public String readCommand() {
        return sc.nextLine(); //reads using scanner
    }

    /**
     * Displays a message to the user.
     *
     * @param msg The message to display
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Displays all tasks in the given task list.
     *
     * @param tasks The task list to display
     */
    public void showList(TaskList tasks) {
        // Prints the list
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Returns a formatted help message describing all available commands.
     *
     * @return The help message as a string
     */
    public String getHelpMessage() {
        return """
            Hello! Here are the commands you can use:

            list
              Show all tasks

            todo <description>
              Add a todo task
              Example: todo read book

            deadline <description> by <date>
              Add a deadline task
              Example: deadline submit report by Friday

            event <description> from <start> to <end>
              Add an event task
              Example: event meeting from 2pm to 4pm

            mark <task number>
            unmark <task number>
              Mark or unmark a task

            delete <task number>
              Delete a task

            find <keyword>
              Search for tasks containing a keyword

            help
              Show this help message

            bye
              Exit the application
            """;
    }

}

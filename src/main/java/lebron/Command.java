package lebron;

/**
 * Represents an executable command in the Lebron chatbot.
 * Each command encapsulates the logic for a specific user action.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param tasks   The task list to operate on
     * @param ui      The UI for user interaction
     * @param storage The storage for saving tasks
     * @return The response message to display to the user
     * @throws LebronException If the command execution fails
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException;

    /**
     * Returns whether this command should exit the application.
     *
     * @return true if the application should exit after this command
     */
    public boolean isExit() {
        return false;
    }
}

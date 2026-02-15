package lebron;

/**
 * Command to delete a task from the list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The zero-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        if (index < 0 || index >= tasks.size()) {
            throw new LebronException("Task number " + (index + 1) + " does not exist.");
        }
        Task removed = tasks.remove(index);
        return "Noted, I've removed this task:\n"
                + removed
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

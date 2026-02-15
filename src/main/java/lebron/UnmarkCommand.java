package lebron;

/**
 * Command to unmark a task (mark as not done).
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The zero-based index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        if (index < 0 || index >= tasks.size()) {
            throw new LebronException("Task number " + (index + 1) + " does not exist.");
        }
        tasks.unmark(index);
        return "OK, I've marked this task as not done yet:\n" + tasks.get(index);
    }
}

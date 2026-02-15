package lebron;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The zero-based index of the task to mark
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        if (index < 0 || index >= tasks.size()) {
            throw new LebronException("Task number " + (index + 1) + " does not exist.");
        }
        tasks.mark(index);
        return "Nice! I've marked this task as done:\n" + tasks.get(index);
    }
}

package lebron;

/**
 * Command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline.
     *
     * @param description The description of the deadline task
     * @param by          The deadline date/time
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        return "Got it. I've added this task:\n"
                + deadline
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

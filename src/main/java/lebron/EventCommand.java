package lebron;

/**
 * Command to add an event task.
 */
public class EventCommand extends Command {

    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand with the specified details.
     *
     * @param description The description of the event
     * @param from        The start time of the event
     * @param to          The end time of the event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        Task event = new Event(description, from, to);
        tasks.add(event);
        return "Got it. I've added this task:\n"
                + event
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

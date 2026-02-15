package lebron;

/**
 * Command to add a todo task.
 */
public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        Task todo = new Todo(description);
        tasks.add(todo);
        return "Got it. I've added this task:\n"
                + todo
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}

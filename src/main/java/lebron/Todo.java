package lebron;

/**
 * Represents a simple task without any date/time information.
 * A Todo task only has a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task.
     *
     * @param name The description of the todo task
     */
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toStorageString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

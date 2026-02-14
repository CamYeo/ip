package lebron;

/**
 * Represents a task with a description and completion status.
 * This is the base class for all task types (Todo, Deadline, Event).
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a new Task with the given name.
     * The task is initially marked as not done.
     *
     * @param name The description of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Returns the name/description of this task.
     *
     * @return The task description
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether this task is marked as done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation suitable for saving to storage.
     * Subclasses should override this method to include their specific data.
     *
     * @return Storage format string
     */
    public String toStorageString() {
        return (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}

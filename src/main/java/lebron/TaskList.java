package lebron;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the chatbot.
 * TaskList encapsulates all operations related to managing tasks,
 * such as adding, removing, and updating task states.
 */
public class TaskList {

    // Internal list that stores all tasks
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList using a pre-loaded list of tasks.
     * A defensive copy is made to protect internal state.
     *
     * @param loaded List of tasks loaded from storage
     */
    public TaskList(List<Task> loaded) {
        this.tasks = new ArrayList<>(loaded);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index Index of the task (0-based)
     * @return Task at the given index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param t Task to be added
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param index Index of the task to remove
     * @return The removed task
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index Index of the task to mark
     */
    public void mark(int index) {
        tasks.get(index).mark();
    }

    /**
     * Marks a task as not completed.
     *
     * @param index Index of the task to unmark
     */
    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    /**
     * Returns the underlying list of tasks.
     * Intended for iteration or saving to storage.
     *
     * @return List of tasks
     */
    public List<Task> asList() {
        return tasks;
    }
}

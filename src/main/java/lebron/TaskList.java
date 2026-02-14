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

    /**
     * Finds all tasks that match the given keyword.
     * The search is case-insensitive and matches against the full string representation of tasks.
     *
     * @param keyword The keyword to search for
     * @return List of tasks containing the keyword
     */
    public java.util.List<Task> findMatches(String keyword) {
        java.util.List<Task> matches = new java.util.ArrayList<>();
        String k = keyword.toLowerCase();

        for (Task t : tasks) {
            // Uses toString so it works for Todo/Deadline/Event without extra getters
            if (t.toString().toLowerCase().contains(k)) {
                matches.add(t);
            }
        }
        return matches;
    }

    /**
     * Returns a formatted string representation of all tasks in the list.
     * If the list is empty, returns a message indicating so.
     *
     * @return Formatted string showing all tasks with numbers
     */
    public String toDisplayString() {
        if (this.size() == 0) {
            return "Your task list is empty.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");

        for (int i = 0; i < this.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(this.get(i))
                    .append("\n");
        }

        return sb.toString();
    }
}

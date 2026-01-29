package lebron;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> loaded) {
        this.tasks = new ArrayList<>(loaded);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void mark(int index) {
        tasks.get(index).mark();
    }

    public void unmark(int index) {
        tasks.get(index).unmark();
    }

    public List<Task> asList() {
        return tasks;
    }

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
}

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
}

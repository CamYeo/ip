package lebron;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        return FileReadingDemo.loadTasks(filePath);
    }

    public void save(TaskList tasks) throws IOException {
        FileWritingDemo.writeAllTasks(filePath, tasks.asList());
    }
}
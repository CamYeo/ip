package lebron;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Handles loading and saving of tasks to persistent storage.
 * Acts as a facade for file I/O operations, abstracting the underlying
 * file format and read/write implementation details.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file
     * @throws FileNotFoundException If the storage file does not exist
     */
    public List<Task> load() throws FileNotFoundException {
        return FileReadingDemo.loadTasks(filePath); // loads file from filepath
    }

    /**
     * Saves all tasks in the task list to the storage file.
     * Creates the parent directory if it doesn't exist.
     * Overwrites any existing file content.
     *
     * @param tasks The TaskList containing all tasks to save
     * @throws IOException If an error occurs during file writing
     */
    public void save(TaskList tasks) throws IOException {
        FileWritingDemo.writeAllTasks(filePath, tasks.asList()); // writes arraylist into filepath
    }
}

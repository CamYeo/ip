package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {

    private static final String TEST_FILE_PATH = "data/test_lebron.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Clean up test file after each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void save_createsDirAndFile() throws IOException {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("test task"));

        storage.save(tasks);

        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());
    }

    @Test
    public void save_andLoad_preservesTasks() throws Exception {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("return book", "Monday"));
        tasks.add(new Event("meeting", "2pm", "4pm"));
        tasks.mark(0); // mark first task as done

        storage.save(tasks);

        var loaded = storage.load();
        assertEquals(3, loaded.size());
        assertTrue(loaded.get(0).isDone());
        assertFalse(loaded.get(1).isDone());
        assertEquals("read book", loaded.get(0).getName());
    }

    @Test
    public void load_todoTask_correctlyLoaded() throws Exception {
        // Write test data
        Files.writeString(Path.of(TEST_FILE_PATH), "T | 0 | read book\n");

        var loaded = storage.load();
        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof Todo);
        assertEquals("read book", loaded.get(0).getName());
        assertFalse(loaded.get(0).isDone());
    }

    @Test
    public void load_deadlineTask_correctlyLoaded() throws Exception {
        Files.writeString(Path.of(TEST_FILE_PATH), "D | 1 | return book | Monday\n");

        var loaded = storage.load();
        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof Deadline);
        assertEquals("return book", loaded.get(0).getName());
        assertTrue(loaded.get(0).isDone());
    }

    @Test
    public void load_eventTask_correctlyLoaded() throws Exception {
        Files.writeString(Path.of(TEST_FILE_PATH), "E | 0 | meeting | 2pm | 4pm\n");

        var loaded = storage.load();
        assertEquals(1, loaded.size());
        assertTrue(loaded.get(0) instanceof Event);
        assertEquals("meeting", loaded.get(0).getName());
        assertFalse(loaded.get(0).isDone());
    }

    @Test
    public void load_multipleTasks_allLoaded() throws Exception {
        String content = "T | 1 | read book\n"
                + "D | 0 | return book | Monday\n"
                + "E | 1 | meeting | 2pm | 4pm\n";
        Files.writeString(Path.of(TEST_FILE_PATH), content);

        var loaded = storage.load();
        assertEquals(3, loaded.size());
    }

    @Test
    public void load_emptyFile_returnsEmptyList() throws Exception {
        Files.writeString(Path.of(TEST_FILE_PATH), "");

        var loaded = storage.load();
        assertEquals(0, loaded.size());
    }

    @Test
    public void load_malformedLine_skipsLine() throws Exception {
        String content = "T | 1 | read book\n"
                + "INVALID LINE\n"
                + "D | 0 | return book | Monday\n";
        Files.writeString(Path.of(TEST_FILE_PATH), content);

        var loaded = storage.load();
        assertEquals(2, loaded.size()); // Only valid tasks loaded
    }

    @Test
    public void save_emptyTaskList_createsEmptyFile() throws IOException {
        TaskList tasks = new TaskList();
        storage.save(tasks);

        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists());
        assertEquals(0, file.length());
    }
}

package lebron;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for reading tasks from file storage.
 * Provides methods to load and parse task data from text files.
 */
public class FileReadingDemo {

    /**
     * Prints the contents of a file to standard output.
     *
     * @param filePath The path to the file to print
     * @throws FileNotFoundException If the file does not exist
     */
    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Loads tasks from a file and returns them as a list.
     * The file should contain tasks in the format: TYPE | STATUS | DESCRIPTION | [EXTRA_DATA]
     * where TYPE is T (Todo), D (Deadline), or E (Event).
     *
     * @param filePath The path to the file containing task data
     * @return List of tasks loaded from the file
     * @throws FileNotFoundException If the file does not exist
     */
    public static List<Task> loadTasks(String filePath) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        File file = new File(filePath);
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                // Expected formats:
                // T | 1 | read book
                // D | 0 | return book | June 6th
                // E | 1 | meeting | 2pm | 4pm
                String[] parts = line.split(" \\| ");

                // Validate we have enough parts
                if (parts.length < 3) {
                    continue; // skip malformed line
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");

                Task task = null;
                if (type.equals("T")) {
                    task = new Todo(parts[2]);
                } else if (type.equals("D") && parts.length >= 4) {
                    task = new Deadline(parts[2], parts[3]);
                } else if (type.equals("E") && parts.length >= 5) {
                    task = new Event(parts[2], parts[3], parts[4]);
                }

                if (task != null) {
                    if (isDone) {
                        task.mark();
                    }
                    tasks.add(task);
                }
            }
        }

        return tasks;
    }
}



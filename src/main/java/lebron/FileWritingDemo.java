package lebron;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for writing tasks to file storage.
 * Provides methods to save task data to text files.
 */
public class FileWritingDemo {

    /**
     * Writes text to a file, overwriting any existing content.
     *
     * @param filePath The path to the file
     * @param textToAdd The text to write
     * @throws IOException If an error occurs during file writing
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(textToAdd); //adds text directly to txt file
        }
    }

    /**
     * Appends text to a file without overwriting existing content.
     *
     * @param filePath The path to the file
     * @param textToAppend The text to append
     * @throws IOException If an error occurs during file writing
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        try (FileWriter fw = new FileWriter(filePath, true)) { // create a FileWriter in append mode
            fw.write(textToAppend);
        }
    }

    /**
     * Writes all tasks to a file, overwriting any existing content.
     * Creates the parent directory if it doesn't exist.
     *
     * @param filePath The path to the file
     * @param tasks The list of tasks to write
     * @throws IOException If an error occurs during file writing
     */
    public static void writeAllTasks(String filePath, List<Task> tasks) throws IOException {
        File file = new File(filePath);

        // ensure ./data directory exists
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        try (FileWriter fw = new FileWriter(file, false)) { // overwrite
            for (Task t : tasks) {
                fw.write(toStorageString(t));
                fw.write(System.lineSeparator());
            }
        }
    }


    // Uses polymorphism to serialize tasks to storage format
    private static String toStorageString(Task t) {
        return t.toStorageString();
    }

}

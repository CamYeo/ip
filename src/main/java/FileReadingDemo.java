import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReadingDemo {

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    public static List<Task> loadTasks(String filePath) throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();

        File file = new File(filePath);
        Scanner sc = new Scanner(file);

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

            String type = parts[0];
            boolean isDone = parts[1].equals("1");

            Task task;
            if (type.equals("T")) {
                task = new Todo(parts[2]);
            } else if (type.equals("D")) {
                task = new Deadline(parts[2], parts[3]);
            } else if (type.equals("E")) {
                task = new Event(parts[2], parts[3], parts[4]);
            } else {
                continue; // skip unknown line type
            }

            if (isDone) {
                task.mark();
            }

            tasks.add(task);
        }

        sc.close();
        return tasks;
    }
}



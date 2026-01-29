package lebron;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class FileWritingDemo {

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeAllTasks(String filePath, List<Task> tasks) throws IOException {
        File file = new File(filePath);

        // ensure ./data directory exists
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        FileWriter fw = new FileWriter(file, false); // overwrite

        for (Task t : tasks) {
            fw.write(toStorageString(t));
            fw.write(System.lineSeparator());
        }

        fw.close();
    }

    private static String toStorageString(Task t) {
        String done = (t.status == 1) ? "1" : "0";

        if (t instanceof Todo) {
            return "T | " + done + " | " + t.name;
        }
        if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return "D | " + done + " | " + d.name + " | " + d.rawBy;
        }
        if (t instanceof Event) {
            Event e = (Event) t;
            return "E | " + done + " | " + e.name + " | " + e.from + " | " + e.to;
        }
        return "";
    }

}

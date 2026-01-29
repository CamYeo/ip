import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;   // parsed date-time (may be null)
    protected String rawBy;       // original user input

    public Deadline(String description, String rawBy) {
        super(description);
        this.rawBy = rawBy;
        this.by = parseBy(rawBy);
    }

    private static LocalDateTime parseBy(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return null;
        }

        raw = raw.trim();

        // 1) yyyy-MM-dd → default time 23:59
        try {
            LocalDate d = LocalDate.parse(raw);
            return d.atTime(LocalTime.of(23, 59));
        } catch (DateTimeParseException ignored) { }

        // 2) d/M/yyyy HHmm → exact date & time
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(raw, f);
        } catch (DateTimeParseException ignored) { }

        return null; // unable to parse
    }

    // For saving to file
    public String toStorageString() {
        String byStr = (by == null) ? rawBy : by.toString();
        return "D | " + status + " | " + name + " | " + byStr;
    }

    @Override
    public String toString() {
        if (by == null) {
            return "[D]" + super.toString() + " (by: " + rawBy + ")";
        }

        DateTimeFormatter out =
                DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + by.format(out) + ")";
    }
}
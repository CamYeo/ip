package lebron;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code Deadline} stores both the raw user input for the deadline
 * as well as a parsed {@link LocalDateTime} representation when possible.
 * If the deadline string cannot be parsed, the raw input is preserved
 * and displayed to the user.
 */
public class Deadline extends Task {

    /**
     * Parsed deadline date-time.
     * <p>
     * This value may be {@code null} if the user's input cannot be
     * converted into a valid date-time.
     */
    private LocalDateTime by;

    /**
     * The original deadline string entered by the user.
     * This is used for display and storage when parsing fails.
     */
    private String rawBy;

    /**
     * Constructs a {@code Deadline} task with the given description
     * and deadline string.
     *
     * @param description Description of the task
     * @param rawBy Raw deadline string entered by the user
     */
    public Deadline(String description, String rawBy) {
        super(description);
        this.rawBy = rawBy;
        this.by = parseBy(rawBy);
    }

    /**
     * Returns the parsed deadline as a LocalDateTime.
     *
     * @return The parsed deadline, or null if parsing failed
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the raw deadline string entered by the user.
     *
     * @return The raw deadline string
     */
    public String getRawBy() {
        return rawBy;
    }

    /**
     * Attempts to parse a deadline string into a {@link LocalDateTime}.
     * <p>
     * Supported formats:
     * <ul>
     *     <li>{@code yyyy-MM-dd} (defaults time to 23:59)</li>
     *     <li>{@code d/M/yyyy HHmm}</li>
     * </ul>
     *
     * @param raw Raw deadline string
     * @return Parsed {@code LocalDateTime}, or {@code null} if parsing fails
     */
    private static LocalDateTime parseBy(String raw) {
        if (raw == null || raw.trim().isEmpty()) {
            return null;
        }

        raw = raw.trim();

        // yyyy-MM-dd → default time 23:59
        try {
            LocalDate d = LocalDate.parse(raw);
            return d.atTime(LocalTime.of(23, 59));
        } catch (DateTimeParseException e) {
            // Ignore and try next format
        }

        // d/M/yyyy HHmm → exact date and time
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(raw, f);
        } catch (DateTimeParseException e) {
            // Ignore - unable to parse
        }

        return null; // unable to parse deadline
    }

    @Override
    public String toStorageString() {
        String byStr = (by == null) ? rawBy : rawBy;
        return "D | " + (isDone() ? "1" : "0") + " | " + getName() + " | " + byStr;
    }

    /**
     * Returns a user-friendly string representation of the deadline task.
     * <p>
     * If the deadline could not be parsed, the raw input is shown instead.
     *
     * @return Formatted string representation of this task
     */
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

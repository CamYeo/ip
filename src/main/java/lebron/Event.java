package lebron;

/**
 * Represents a task that occurs during a specific time period.
 * An Event has a start time and end time.
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructs a new Event task.
     *
     * @param name The description of the event
     * @param from The start time/date of the event
     * @param to The end time/date of the event
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of this event.
     *
     * @return The end time
     */
    public String getTo() {
        return to;
    }

    @Override
    public String toStorageString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getName() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

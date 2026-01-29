package lebron;

public class Event extends Task {

    protected String from;
    protected String to;

    // Regular Event Constructor
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    // Override normal toString method
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

package lebron;

public class Todo extends Task {

    // Basic Contstructor
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
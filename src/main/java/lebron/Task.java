package lebron;

public class Task {
    String name;
    int status;

    public Task(String name) {
        this.name = name;
        this.status = 0;
    }

    // Uses status 1 and 0 to Mark Status
    public void mark() {
        this.status = 1;
    }

    public void unmark() {
        this.status = 0;
    }


    @Override
    public String toString() {
        if (status == 1) {
            return "[X] " + this.name;
        }
        else {
            return "[ ] " + this.name;
        }
    }
}

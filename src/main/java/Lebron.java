import java.io.FileNotFoundException;
import java.io.IOException;

public class Lebron {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    public Lebron(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showMessage("File not found, starting with an empty list.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            try {
                String input = ui.readCommand();
                String cmd = Parser.getCommandWord(input);

                if (cmd.equals("bye")) {
                    break;
                } else if (cmd.equals("list")) {
                    ui.showList(tasks);

                } else if (cmd.equals("mark")) {
                    int idx = Parser.parseIndex(input);
                    tasks.mark(idx);
                    ui.showMessage("Nice! I've marked this task as done:");
                    ui.showMessage(tasks.get(idx).toString());

                } else if (cmd.equals("unmark")) {
                    int idx = Parser.parseIndex(input);
                    tasks.unmark(idx);
                    ui.showMessage("OK, I've marked this task as not done yet:");
                    ui.showMessage(tasks.get(idx).toString());

                } else if (cmd.equals("todo")) {
                    String desc = Parser.parseTodo(input);
                    tasks.add(new Todo(desc));
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.get(tasks.size() - 1).toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("deadline")) {
                    String[] dd = Parser.parseDeadline(input);
                    tasks.add(new Deadline(dd[0], dd[1]));
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.get(tasks.size() - 1).toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("event")) {
                    String[] ev = Parser.parseEvent(input);
                    tasks.add(new Event(ev[0], ev[1], ev[2]));
                    ui.showMessage("Got it. I've added this task:");
                    ui.showMessage(tasks.get(tasks.size() - 1).toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else if (cmd.equals("delete")) {
                    int idx = Parser.parseIndex(input);
                    Task removed = tasks.remove(idx);
                    ui.showMessage("Noted, I've removed this task:");
                    ui.showMessage(removed.toString());
                    ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");

                } else {
                    throw new LebronException("No valid task was created, oh no!");
                }

                ui.showLine();
            } catch (LebronException e) {
                ui.showLine();
                ui.showMessage(e.getMessage());
                ui.showLine();
            }
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks: " + e.getMessage());
        }

        ui.showBye();
    }

    public static void main(String[] args) {
        new Lebron("./data/lebron.txt").run();
    }
}


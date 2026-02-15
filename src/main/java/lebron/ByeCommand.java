package lebron;

import java.io.IOException;

/**
 * Command to exit the application.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws LebronException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return "Bye!\n(But I failed to save tasks: " + e.getMessage() + ")";
        }
        return "Bye! Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

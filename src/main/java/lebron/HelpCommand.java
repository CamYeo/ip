package lebron;

/**
 * Command to display help information.
 */
public class HelpCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getHelpMessage();
    }
}

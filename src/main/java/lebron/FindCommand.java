package lebron;

import java.util.List;

/**
 * Command to find tasks matching a keyword.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.findMatches(keyword);

        StringBuilder out = new StringBuilder();
        out.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            out.append(i + 1).append(". ").append(matches.get(i)).append("\n");
        }
        if (matches.isEmpty()) {
            out.append("(No matching tasks found)");
        }
        return out.toString();
    }
}

package lebron;

public class Parser {

    public static String getCommandWord(String input) {
        return input.trim().split("\\s+")[0];
    }

    public static int parseIndex(String input) throws LebronException {
        try {
            int idx = Integer.parseInt(input.replaceAll(".*?(\\d+)$", "$1")) - 1;
            if (idx < 0) throw new NumberFormatException();
            return idx;
        } catch (NumberFormatException e) {
            throw new LebronException("Please provide a valid task number."); // Catches poor inpit from user
        }
    }

    public static String[] parseDeadline(String input) throws LebronException {
        // "deadline <desc> /by <by>"
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) throw new LebronException("Usage: deadline <desc> /by <date>");
        String desc = parts[0].trim();
        String by = parts[1].trim();
        if (desc.isEmpty() || by.isEmpty()) throw new LebronException("lebron.Deadline needs a description and /by.");
        return new String[]{desc, by};
    }

    public static String parseTodo(String input) throws LebronException {
        String desc = input.length() > 4 ? input.substring(5).trim() : "";
        if (desc.isEmpty()) throw new LebronException("Missing Description");
        return desc;
    }

    public static String[] parseEvent(String input) throws LebronException {
        // "event <desc> /from <from> /to <to>"
        String without = input.substring(6);
        String[] partsFrom = without.split("/from", 2);
        if (partsFrom.length < 2) throw new LebronException("Usage: event <desc> /from <start> /to <end>");

        String desc = partsFrom[0].trim();
        String[] partsTo = partsFrom[1].split("/to", 2);
        if (partsTo.length < 2) throw new LebronException("Usage: event <desc> /from <start> /to <end>");

        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        if (desc.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new LebronException("lebron.Event needs a description, /from and /to.");
        }
        return new String[]{desc, from, to};
    }

    public static String parseFind(String input) throws LebronException {
        String trimmed = input.trim();

        // Command word "find" is 4 chars
        if (trimmed.length() <= 4) {
            throw new LebronException("Usage: find <keyword>");
        }

        String keyword = trimmed.substring(4).trim();
        if (keyword.isEmpty()) {
            throw new LebronException("Usage: find <keyword>");
        }
        return keyword;
    }




}

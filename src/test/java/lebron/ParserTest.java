package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void getCommandWord_simpleCommand_returnsFirst() {
        assertEquals("list", Parser.getCommandWord("list"));
        assertEquals("bye", Parser.getCommandWord("bye"));
        assertEquals("help", Parser.getCommandWord("help"));
    }

    @Test
    public void getCommandWord_withArgs_returnsCommand() {
        assertEquals("todo", Parser.getCommandWord("todo read book"));
        assertEquals("mark", Parser.getCommandWord("mark 1"));
        assertEquals("find", Parser.getCommandWord("find book"));
    }

    @Test
    public void getCommandWord_extraWhitespace_trimsCorrectly() {
        assertEquals("list", Parser.getCommandWord("  list  "));
        assertEquals("todo", Parser.getCommandWord("   todo    read book"));
    }

    @Test
    public void parseIndex_validNumber_returnsZeroBasedIndex() throws LebronException {
        assertEquals(0, Parser.parseIndex("mark 1"));
        assertEquals(4, Parser.parseIndex("delete 5"));
        assertEquals(9, Parser.parseIndex("unmark 10"));
    }

    @Test
    public void parseIndex_invalidNumber_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseIndex("mark"));
        assertThrows(LebronException.class, () -> Parser.parseIndex("delete abc"));
        assertThrows(LebronException.class, () -> Parser.parseIndex("mark 0"));
    }

    @Test
    public void parseTodo_validInput_returnsDescription() throws LebronException {
        assertEquals("read book", Parser.parseTodo("todo read book"));
        assertEquals("buy milk", Parser.parseTodo("todo buy milk"));
    }

    @Test
    public void parseTodo_emptyDescription_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseTodo("todo"));
        assertThrows(LebronException.class, () -> Parser.parseTodo("todo "));
    }

    @Test
    public void parseDeadline_validInput_returnsArray() throws LebronException {
        // Legacy format with /by
        String[] result = Parser.parseDeadline("deadline return book /by Monday");
        assertEquals(2, result.length);
        assertEquals("return book", result[0]);
        assertEquals("Monday", result[1]);

        // Intuitive format without /
        String[] result2 = Parser.parseDeadline("deadline return book by Monday");
        assertEquals(2, result2.length);
        assertEquals("return book", result2[0]);
        assertEquals("Monday", result2[1]);
    }

    @Test
    public void parseDeadline_missingBy_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseDeadline("deadline return book"));
    }

    @Test
    public void parseDeadline_emptyDescription_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseDeadline("deadline /by Monday"));
    }

    @Test
    public void parseDeadline_emptyDate_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseDeadline("deadline return book /by"));
        assertThrows(LebronException.class, () -> Parser.parseDeadline("deadline return book /by "));
    }

    @Test
    public void parseEvent_validInput_returnsArray() throws LebronException {
        // Legacy format with /from and /to
        String[] result = Parser.parseEvent("event meeting /from 2pm /to 4pm");
        assertEquals(3, result.length);
        assertEquals("meeting", result[0]);
        assertEquals("2pm", result[1]);
        assertEquals("4pm", result[2]);

        // Intuitive format without /
        String[] result2 = Parser.parseEvent("event meeting from 2pm to 4pm");
        assertEquals(3, result2.length);
        assertEquals("meeting", result2[0]);
        assertEquals("2pm", result2[1]);
        assertEquals("4pm", result2[2]);
    }

    @Test
    public void parseEvent_missingFrom_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseEvent("event meeting /to 4pm"));
    }

    @Test
    public void parseEvent_missingTo_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseEvent("event meeting /from 2pm"));
    }

    @Test
    public void parseEvent_emptyDescription_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseEvent("event /from 2pm /to 4pm"));
    }

    @Test
    public void parseEvent_emptyTimes_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseEvent("event meeting /from /to 4pm"));
        assertThrows(LebronException.class, () -> Parser.parseEvent("event meeting /from 2pm /to"));
    }

    @Test
    public void parseFind_validInput_returnsKeyword() throws LebronException {
        assertEquals("book", Parser.parseFind("find book"));
        assertEquals("meeting", Parser.parseFind("find meeting"));
    }

    @Test
    public void parseFind_emptyKeyword_throwsException() {
        assertThrows(LebronException.class, () -> Parser.parseFind("find"));
        assertThrows(LebronException.class, () -> Parser.parseFind("find "));
    }
}

package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void constructor_createsUndoneEvent() {
        Event event = new Event("meeting", "2pm", "4pm");
        assertFalse(event.isDone());
        assertEquals("meeting", event.getName());
        assertEquals("2pm", event.getFrom());
        assertEquals("4pm", event.getTo());
    }

    @Test
    public void mark_marksEventAsDone() {
        Event event = new Event("meeting", "2pm", "4pm");
        event.mark();
        assertTrue(event.isDone());
    }

    @Test
    public void unmark_marksEventAsNotDone() {
        Event event = new Event("meeting", "2pm", "4pm");
        event.mark();
        event.unmark();
        assertFalse(event.isDone());
    }

    @Test
    public void toString_undoneEvent_showsUncheckedBox() {
        Event event = new Event("meeting", "2pm", "4pm");
        String str = event.toString();
        assertTrue(str.contains("[ ]"));
        assertTrue(str.contains("meeting"));
        assertTrue(str.contains("[E]"));
        assertTrue(str.contains("from: 2pm"));
        assertTrue(str.contains("to: 4pm"));
    }

    @Test
    public void toString_doneEvent_showsCheckedBox() {
        Event event = new Event("meeting", "2pm", "4pm");
        event.mark();
        assertTrue(event.toString().contains("[X]"));
    }

    @Test
    public void toStorageString_undoneEvent_correctFormat() {
        Event event = new Event("meeting", "2pm", "4pm");
        assertEquals("E | 0 | meeting | 2pm | 4pm", event.toStorageString());
    }

    @Test
    public void toStorageString_doneEvent_correctFormat() {
        Event event = new Event("meeting", "2pm", "4pm");
        event.mark();
        assertEquals("E | 1 | meeting | 2pm | 4pm", event.toStorageString());
    }
}

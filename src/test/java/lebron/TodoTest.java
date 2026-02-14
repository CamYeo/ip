package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void constructor_createsUndoneTodo() {
        Todo todo = new Todo("read book");
        assertFalse(todo.isDone());
        assertEquals("read book", todo.getName());
    }

    @Test
    public void mark_marksTodoAsDone() {
        Todo todo = new Todo("read book");
        todo.mark();
        assertTrue(todo.isDone());
    }

    @Test
    public void unmark_marksTodoAsNotDone() {
        Todo todo = new Todo("read book");
        todo.mark();
        todo.unmark();
        assertFalse(todo.isDone());
    }

    @Test
    public void toString_undoneTodo_showsUncheckedBox() {
        Todo todo = new Todo("read book");
        assertTrue(todo.toString().contains("[ ]"));
        assertTrue(todo.toString().contains("read book"));
        assertTrue(todo.toString().contains("[T]"));
    }

    @Test
    public void toString_doneTodo_showsCheckedBox() {
        Todo todo = new Todo("read book");
        todo.mark();
        assertTrue(todo.toString().contains("[X]"));
        assertTrue(todo.toString().contains("read book"));
    }

    @Test
    public void toStorageString_undoneTodo_correctFormat() {
        Todo todo = new Todo("read book");
        assertEquals("T | 0 | read book", todo.toStorageString());
    }

    @Test
    public void toStorageString_doneTodo_correctFormat() {
        Todo todo = new Todo("read book");
        todo.mark();
        assertEquals("T | 1 | read book", todo.toStorageString());
    }
}

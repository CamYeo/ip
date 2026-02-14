package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList list;

    @BeforeEach
    public void setUp() {
        list = new TaskList();
    }

    @Test
    public void add_increasesSize() {
        list.add(new Todo("read book"));
        assertEquals(1, list.size());

        list.add(new Todo("buy milk"));
        assertEquals(2, list.size());
    }

    @Test
    public void remove_decreasesSizeAndReturnsTask() {
        Todo todo = new Todo("read book");
        list.add(todo);
        list.add(new Todo("buy milk"));

        Task removed = list.remove(0);
        assertEquals(1, list.size());
        assertEquals(todo, removed);
    }

    @Test
    public void get_returnsCorrectTask() {
        Todo todo = new Todo("read book");
        list.add(todo);
        assertEquals(todo, list.get(0));
    }

    @Test
    public void mark_marksTaskAsDone() {
        list.add(new Todo("read book"));
        list.mark(0);
        assertTrue(list.get(0).isDone());
    }

    @Test
    public void unmark_marksTaskAsNotDone() {
        list.add(new Todo("read book"));
        list.mark(0);
        list.unmark(0);
        assertFalse(list.get(0).isDone());
    }

    @Test
    public void findMatches_returnsMatchingTasks() {
        list.add(new Todo("read book"));
        list.add(new Todo("buy milk"));
        list.add(new Deadline("return book", "Monday"));

        var matches = list.findMatches("book");
        assertEquals(2, matches.size());
        assertTrue(matches.get(0).toString().contains("book"));
        assertTrue(matches.get(1).toString().contains("book"));
    }

    @Test
    public void findMatches_caseInsensitive() {
        list.add(new Todo("Read Book"));
        var matches = list.findMatches("book");
        assertEquals(1, matches.size());
    }

    @Test
    public void findMatches_noMatches_returnsEmpty() {
        list.add(new Todo("read book"));
        var matches = list.findMatches("xyz");
        assertEquals(0, matches.size());
    }

    @Test
    public void toDisplayString_emptyList_showsMessage() {
        String display = list.toDisplayString();
        assertTrue(display.contains("empty"));
    }

    @Test
    public void toDisplayString_withTasks_showsNumberedList() {
        list.add(new Todo("task 1"));
        list.add(new Todo("task 2"));

        String display = list.toDisplayString();
        assertTrue(display.contains("1."));
        assertTrue(display.contains("2."));
        assertTrue(display.contains("task 1"));
        assertTrue(display.contains("task 2"));
    }
}

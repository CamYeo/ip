package lebron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void add_increasesSize() {
        TaskList list = new TaskList();
        list.add(new Todo("read book"));

        assertEquals(1, list.size());
    }
}
package lebron;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void validIsoDate_parsesCorrectly() {
        Deadline d = new Deadline("return book", "2019-10-15");
        assertTrue(d.toString().contains("Oct"));
    }

    @Test
    public void invalidDate_keepsRawInput() {
        Deadline d = new Deadline("return book", "Sunday");
        assertTrue(d.toString().contains("Sunday"));
    }
}

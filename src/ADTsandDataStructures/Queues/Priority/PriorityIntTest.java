import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PriorityIntTest {

    @Test
    void testIsMoreImportant() {
        PriorityInt priority1 = new PriorityInt(10);
        PriorityInt priority2 = new PriorityInt(5);
        PriorityInt priority3 = new PriorityInt(10);

        assertTrue(priority1.isMoreImportant(priority2));
        assertFalse(priority2.isMoreImportant(priority1));
        assertFalse(priority1.isMoreImportant(priority3));
    }

    @Test
    void testGetValue() {
        PriorityInt priority = new PriorityInt(42);
        assertEquals(42, priority.getValue());
    }

    @Test
    void testToString() {
        PriorityInt priority = new PriorityInt(7);
        assertEquals("7", priority.toString());
    }
}
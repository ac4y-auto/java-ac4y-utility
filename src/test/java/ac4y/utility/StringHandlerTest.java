package ac4y.utility;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringHandlerTest {

    @Test
    public void testGetSimpled() {
        StringHandler handler = new StringHandler();
        String result = handler.getSimpled("test123");

        assertNotNull(result);
        assertEquals("TEST123", result);
    }

    @Test
    public void testGetExtended() {
        StringHandler handler = new StringHandler();

        String result1 = handler.getExtended(true, "before-", "-after", "base");
        assertEquals("before-base-after", result1);

        String result2 = handler.getExtended(false, "before-", "-after", "base");
        assertEquals("base", result2);
    }

    @Test
    public void testGetEncoded() {
        StringHandler handler = new StringHandler();
        String result = handler.getEncoded("test value");

        assertNotNull(result);
        assertEquals("test+value", result);
    }

    @Test
    public void testGetLastPart() {
        StringHandler handler = new StringHandler();
        String result = handler.getLastPart("part1.part2.part3", "\\.");

        assertEquals("part3", result);
    }

    @Test
    public void testConcatSmart() {
        StringHandler handler = new StringHandler();

        String result1 = handler.concatSmart("first", "second", "-");
        assertEquals("first-second", result1);

        String result2 = handler.concatSmart(null, "second", "-");
        assertEquals("second", result2);
    }
}

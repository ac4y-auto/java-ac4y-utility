package ac4y.utility;

import org.junit.Test;
import static org.junit.Assert.*;

public class GUIDHandlerTest {

    @Test
    public void testGetGUID() {
        GUIDHandler handler = new GUIDHandler();
        String guid1 = handler.getGUID();
        String guid2 = handler.getGUID();

        assertNotNull(guid1);
        assertNotNull(guid2);
        assertNotEquals(guid1, guid2);
        assertTrue(guid1.length() > 0);
    }

    @Test
    public void testGUIDFormat() {
        GUIDHandler handler = new GUIDHandler();
        String guid = handler.getGUID();
        assertTrue(guid.matches("[0-9a-fA-F-]+"));
    }
}

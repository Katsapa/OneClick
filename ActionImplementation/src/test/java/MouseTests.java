
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
public class MouseTests {

    @Test
    void testMouseClickActionException(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MouseClickAction(6, "double click");
        });
        assertEquals("Not existed mouse button", exception.getMessage());
    }
}

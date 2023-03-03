import org.junit.Test;
import properties.GameProperties;

import static org.junit.Assert.*;

public class PropertiesTest {

    GameProperties t = new GameProperties();

    @Test
    public void testProperties(){
        assertEquals(t.getRounds(),2);
        assertEquals(t.getQuestions(), 2);
    }
}

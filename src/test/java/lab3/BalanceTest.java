package lab3;

import edu.lsbf.lab3.SetAsTree;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class BalanceTest {
    @Test
    public void testAll() {
        SetAsTree s = new SetAsTree();
        IntStream.range(0, 1000).forEach(s::insert);
        assertTrue(s.isBalanced());
        assertEquals(999, s.max());
        assertEquals(0, s.min());

        IntStream.range(0, 800).forEach(s::delete);
        assertTrue(s.isBalanced());
        assertEquals(800, s.min());
        assertEquals(999, s.max());

        IntStream.range(800, 1000).forEach(s::delete);
        assertTrue(s.isBalanced());
        assertTrue(s.emptySet());
    }
}

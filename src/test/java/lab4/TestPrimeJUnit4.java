package lab4;

import edu.lsbf.lab4.Prime;
import org.jmlspecs.runtime.JmlAssertionError;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class TestPrimeJUnit4 {

  public static void main(String[] args) {
    String testClass = "Prime.TestPrimeJUnit4";
    org.junit.runner.JUnitCore.main(testClass);
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    org.jmlspecs.runtime.Utils.useExceptions = true;
  }

  @Test
  public void testSequence_0() {
    boolean b = Prime.is_prime(5);
  }

  @Test
  public void testSequence_1() {
    boolean b = Prime.is_prime(4);
  }

  @Test
  public void testSequence_2() {
    assertTrue(Prime.is_prime(3175553));
  }

  @Test
  public void testSequence_3() {
    assertFalse(Prime.is_prime(3175555));
  }
  @Test
  public void testSequence_10() {
    Prime s = new Prime(5);
  }

  @Test(expected = JmlAssertionError.class)
  public void testSequence_11() {
    Prime s = new Prime(4);
  }

}

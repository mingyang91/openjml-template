package prime;

import edu.lsbf.prime.Prime;
import java.util.Arrays;
import java.util.Collection;
import org.jmlspecs.runtime.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)

public class TestPrimeJUnit4Param {

  static int nb_inconclusive = 0;
  @Parameter(value = 0)
  public int n;

  @Parameters(name = "{index}: n={0}")
  public static Collection<Object[]> params() {
    return Arrays.asList(
        new Object[] {7},
        new Object[] {8}
    );
  }

  public static void main(String[] args) {
    String testClass = "Prime.TestPrimeJUnit4Param";
    org.junit.runner.JUnitCore.main(testClass);
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    nb_inconclusive = 0;
    org.jmlspecs.runtime.Utils.useExceptions = true;
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    System.out.println("\n inconclusive tests: " + nb_inconclusive);
  }

  @Test
  public void testSequence_0() {
    boolean b = Prime.is_prime(n);
  }

  // Le test ci-dessous peut devenir inconclusif.
  @Test
  public void testSequence_1() {
    try {
      Prime s = new Prime(n);
    } catch (JmlAssertionError e) {
      System.out.println("\n INCONCLUSIVE " + (new Exception().getStackTrace()[0].getMethodName()) +
          " with param " + n + "\n\t " + e.getMessage());
      nb_inconclusive++;
    }
  }

}

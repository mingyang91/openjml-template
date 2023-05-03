package lab3;

import edu.lsbf.lab3.SetAsTree;
import org.jmlspecs.runtime.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SetAsTreeTest {

  static int nb_inc = 0;
  static int nb_fail = 0;

  @BeforeClass
  public static void setUpBeforeClass() {
    nb_inc = 0;
    nb_fail = 0;
  }

  @AfterClass
  public static void tearDownAfterClass() {
    System.out.println("\n inconclusive tests: " + nb_inc + " -- failures : " + nb_fail);
  }

  private void handleJMLAssertionError(JmlAssertionError e) {

    if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
      System.out.println(
          "\n INCONCLUSIVE " + (new Exception().getStackTrace()[0].getMethodName()) + "\n\t " +
              e.getMessage());
      nb_inc++;
    } else {
      // test failure
      nb_fail++;
      Assert.fail("\n\t" + e.getMessage());
    }
  }

  @Test
  public void testSequence_0() {
    try {
      SetAsTree s = new SetAsTree(5);
      s.insert(10);
      s.insert(1);
      s.delete(5);
    } catch (Exception e) {
      System.err.println(e);
    } catch (JmlAssertionError e) {
      handleJMLAssertionError(e);
    }
  }

}

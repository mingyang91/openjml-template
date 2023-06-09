package lab3;

import static org.junit.Assert.assertThrows;

import edu.lsbf.lab3.SetAsTree;
import org.jmlspecs.runtime.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class SetAsTreeFailingTest {

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

  @Test(expected = JmlAssertionError.class)
  public void testSequence_1() {
    SetAsTree s5 = new SetAsTree(5);
    SetAsTree s1 = new SetAsTree(1);
    s5.setRtree(s1);
//      s5.skip();
  }


  @Test(expected = JmlAssertionError.class)
  public void testSequence_2() {
    SetAsTree s5 = new SetAsTree(5);
    SetAsTree s0 = new SetAsTree(0);
    s5.setLtree(s0);
    s5.setVal(null);
  }

}

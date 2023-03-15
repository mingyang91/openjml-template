
import edu.lsbf.SetAsTree;
import org.jmlspecs.utils.JmlAssertionError;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class SetAsTreeFailingTest {

  static int nb_inc = 0;
  static int nb_fail = 0;


  private void handleJMLAssertionError(JmlAssertionError e) {

    if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
      System.out.println(
          "\n INCONCLUSIVE " + (new Exception().getStackTrace()[0].getMethodName()) + "\n\t " +
              e.getMessage());
      nb_inc++;
    } else {
      // test failure
      nb_fail++;
      Assertions.fail("\n\t" + e.getMessage());
    }
  }


  @BeforeAll
  public static void setUpBeforeClass() {
    nb_inc = 0;
    nb_fail = 0;
  }

  @AfterAll
  public static void tearDownAfterClass() {
    System.out.println("\n inconclusive tests: " + nb_inc + " -- failures : " + nb_fail);
  }

  @Test
  public void testSequence_1() {
    try {
      SetAsTree s5 = new SetAsTree(5);
      SetAsTree s1 = new SetAsTree(1);
      s5.setRtree(s1);
    } catch (JmlAssertionError e) {
      handleJMLAssertionError(e);
    }
  }


  @Test
  public void testSequence_2() {
    try {
      SetAsTree s5 = new SetAsTree(5);
      SetAsTree s0 = new SetAsTree(0);
      s5.setLtree(s0);
      s0.setVal(null);
      //s5.skip();
    } catch (JmlAssertionError e) {
      handleJMLAssertionError(e);
    }
  }

}

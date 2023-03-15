
import edu.lsbf.SetAsTree;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
;

public class SetAsTreeTest {

  static int nb_inc = 0;
  static int nb_fail = 0;

//    private void handleJMLAssertionError(org.jmlspecs.jmlrac.runtime.JMLAssertionError e) {
//    	if (e.getClass().equals(org.jmlspecs.jmlrac.runtime.JMLEntryPreconditionError.class)) {
//    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[0].getMethodName())+ "\n\t "+ e.getMessage());
//            nb_inc++;}
//    else{
//	    // test failure
//	    nb_fail++;
//	    junit.framework.Assert.fail("\n\t" + e.getMessage());
//		}
//    }


  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    nb_inc = 0;
    nb_fail = 0;
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
    System.out.println("\n inconclusive tests: " + nb_inc + " -- failures : " + nb_fail);
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
    }
//			catch(org.jmlspecs.jmlrac.runtime.JMLAssertionError e){
//				handleJMLAssertionError(e);
//
//
//		}
  }

}

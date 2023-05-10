package lab7;

import static org.junit.Assert.*;

import edu.lsbf.lab7.Explosives;
import org.jmlspecs.runtime.JmlAssertionError;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestExplosivesJUnit4 {

    static int nb_inc = 0;
    static int nb_fail = 0;

    Explosives e;

    public static void main(String args[]) {
    	String testClass = "TestExplosivesJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }


    private void handleJMLAssertionError(JmlAssertionError e) {
    	if (e.getClass().equals(JmlAssertionError.PreconditionEntry.class)) {
    	    System.out.println("\n INCONCLUSIVE "+(new Exception().getStackTrace()[0].getMethodName())+ "\n\t "+ e.getMessage());
            nb_inc++;}
    else{
	    // test failure	
	    nb_fail++;
		}
    }
	
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		nb_inc = 0;
		nb_fail = 0;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	     System.out.println("\n inconclusive tests: "+nb_inc+" -- failures : "+nb_fail );
	}

	@Test(expected = JmlAssertionError.class)
	public void  testSequence_0() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_Nitro","Prod_Glycerine");
			e.add_incomp("Prod_Dyna","Prod_Mite");
			e.add_assign("Bat_1","Prod_Dyna");
			e.add_assign("Bat_1","Prod_Nitro");
			e.add_assign("Bat_2","Prod_Mite");
			e.add_assign("Bat_1","Prod_Glycerine");
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);
				throw e;
		}  
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_1() {
		try{
			e=new Explosives();
			e.nb_inc = -1;
			e.skip();
		} 	catch(JmlAssertionError e){
				handleJMLAssertionError(e);
				throw e;
		}
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_2() {
		try{
			e=new Explosives();
			e.nb_assign = -1;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);
			throw e;
		}
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_3() {
		try{
			e=new Explosives();
			e.add_incomp("User_1","User_2");
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);
			throw e;
		}
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_4() {
		try{
			e=new Explosives();
			e.add_assign("User_1","User_2");
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);
			throw e;
		}
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_5() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_1","Prod_1");
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);
			throw e;
		}
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_6() {
		try{
			e=new Explosives();
			e.incomp[0][0] = "Prod_1";
			e.incomp[0][1] = "Prod_1";
			e.nb_inc = 1;
			e.skip();
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);
			throw e;
		}
	}

	@Test(expected = JmlAssertionError.class)
	public void testProp_7() {
		try{
			e=new Explosives();
			e.add_incomp("Prod_1","Prod_2");
			e.add_assign("Bat_1","Prod_1");
			e.add_assign("Bat_1","Prod_2");
		} 	catch(JmlAssertionError e){
			handleJMLAssertionError(e);
			throw e;
		}
	}
}

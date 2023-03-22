package prime;

import edu.lsbf.prime.PrimeArray;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestPrimeArrayJUnit4 {

    public static void main(String args[]) {
    	String testClass = "Prime.TestPrimeArrayJUnit4";
     	org.junit.runner.JUnitCore.main(testClass);
     }
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		org.jmlspecs.runtime.Utils.useExceptions = true;
	}

	@Test
	public void  testSequence_0() {
		PrimeArray p = new PrimeArray();
		p.grow();
	}
		
}

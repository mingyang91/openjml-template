package lab4;

import edu.lsbf.lab4.PrimeArray;
import org.jmlspecs.runtime.JmlAssertionError;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;


public class TestPrimeArrayJUnit4 {

  public static void main(String[] args) {
    String testClass = "Prime.TestPrimeArrayJUnit4";
    org.junit.runner.JUnitCore.main(testClass);
  }

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    org.jmlspecs.runtime.Utils.useExceptions = true;
  }

  @Test
  public void testSequence_0() {
    PrimeArray p = new PrimeArray();
    p.grow();
  }

  @Test(expected = JmlAssertionError.class)
  public void testPremier() {
    int[] groundTruth = generatePrimes(100);
    int[] a = new int[100];
    Random rand = new Random();
    for (int i = 0; i < 100; i++) {
      a[i] = groundTruth[rand.nextInt(100)];
    }

    PrimeArray p = new PrimeArray(a);
  }

  @Test(expected = JmlAssertionError.class)
  public void testDeuxième() {
    int[] groundTruth = generatePrimes(1000);
    int[] a = new int[100];
    Random rand = new Random();
    for (int i = 0, j = i; i < 100; i++, j++) {
      int skip = rand.nextInt(2);
      j += skip;
      a[i] = groundTruth[j];
    }

    PrimeArray p = new PrimeArray(a);
  }

  int[] generatePrimes(int n) {
    // use a sieve of Eratosthenes to generate primes
    int[] primes = new int[n];
    int count = 0;
    boolean[] isPrime = new boolean[n * n];
    for (int i = 2; i < n * n; i++) {
      isPrime[i] = true;
    }
    for (int i = 2; i < n * n; i++) {
      if (isPrime[i]) {
        primes[count++] = i;
        if (count == n) {
          return primes;
        }
        for (int j = i * i; j < n * n; j += i) {
          isPrime[j] = false;
        }
      }
    }
    return primes;
  }
}

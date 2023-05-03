package edu.lsbf.prime;

public class Prime {
  private /*@ spec_public @*/ int p;
  //@ public invariant is_prime(p);

  public Prime() {
    p = 3;
  }

  //@ requires x > 1;
  //@ ensures p == x;
  public Prime(int x) {
    p = x;
  }

  //@ ensures \result == true <==> (n > 1 ) && (\forall int d; 2<= d && d<= n-1; n % d != 0);
  /*@ pure helper @*/
  public static boolean is_prime(int n) {
    // Miller - Rabin primality test
    if (n <= 1 || n == 4) {
      return false;
    }
    if (n <= 3) {
      return true;
    }

    var d = n - 1;
    while (d % 2 == 0) {
      d /= 2;
    }

    for (var i = 0; i < 10; i++) {
      if (!miller_rabin_test(d, n)) {
        return false;
      }
    }

    return true;
  }

  private static boolean miller_rabin_test(int d, int n) {
    final var a = 2 + (int) (Math.random() % (n - 4));
    var x = mod_exp(a, d, n);

    if (x == 1 || x == n - 1) {
      return true;
    }

    while (d != n - 1) {
      x = (x * x) % n;
      d *= 2;

      if (x == 1) {
        return false;
      }
      if (x == n - 1) {
        return true;
      }
    }

    return false;
  }

  private static int mod_exp(int a, int d, int n) {
    var res = 1;
    a = a % n;

    while (d > 0) {
      if (d % 2 == 1) {
        res = (res * a) % n;
      }

      d /= 2;
      a = (a * a) % n;
    }

    return res;
  }


  //@ ensures \result == p;
  //@ pure
  public int get_p() {
    return p;
  }

  //@ requires x > 1;
  //@ ensures x == p;
  public void set_p(int x) {
    p = x;
  }
}

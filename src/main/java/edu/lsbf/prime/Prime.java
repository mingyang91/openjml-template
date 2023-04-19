package edu.lsbf.prime;

public class Prime {
  private /*@ spec_public @*/ int p;
  /*@ public invariant
      @ (* A COMPLETER *);
      @*/

  public Prime() {
    p = 3;
  }

  //@ requires (* A COMPLETER *);
  //@ ensures (* A COMPLETER *);
  public Prime(int x) {
    p = x;
  }

  /*@ ensures \result == true <==>
      @   (n > 1 ) && (\forall int d; 2<= d && d<= n-1; n % d != 0);
      @*/
  public static /*@ pure helper @*/ boolean is_prime(int n) {
    return true; // A MODIFIER
  }

  //@ ensures (* A COMPLETER *);
  public /*@ pure @*/ int get_p() {
    return p;
  }

  //@ requires (* A COMPLETER *);
  //@ ensures (* A COMPLETER *);
  public void set_p(int x) {
    p = x;
  }
}

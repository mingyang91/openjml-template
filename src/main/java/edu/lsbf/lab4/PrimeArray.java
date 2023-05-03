package edu.lsbf.lab4;

public class PrimeArray {

  private final /*@ spec_public @*/ Prime[] l;
  private /*@ spec_public @*/ int size = 0;


  /*@ public invariant // Premier invariant
    @ (* Les size premiers elements sont ranges en ordre croissant *);
    @*/
  //@ public invariant size < 2 || (\forall int j; 0 <= j && j < size - 1; l[j].p < l[j + 1].p);
  /*@ public invariant // Deuxi�me invariant
    @ (* Les entiers compris entre deux elements consecutifs, 
    @ parmi les size premiers elements, ne sont pas premiers *);
    @*/
  /*@ public invariant size < 2 ||
        (\forall int j; 0 <= j && j < size - 1;
          (\forall int k; l[j].p < k && k < l[j + 1].p;
            !Prime.is_prime(k)));
    @*/

  /*@ ensures size == 0; @*/
  public PrimeArray() {
    l = new Prime[100];
    size = 0;
  }

  //@ requires (\forall int i; 0 <= i && i < init.length; Prime.is_prime(init[i]));
  //@ requires init.length <= 100;
  //@ ensures size == init.length;
  public PrimeArray(int[] init) {
    l = new Prime[100];
    size = init.length;
    for (var i = 0; i < size; i++) {
      l[i] = new Prime(init[i]);
    }
  }

  /*@ ensures \old(size)+1 == size; @*/
  public void grow() {
    if (size == 0) {
      l[0] = new Prime(2);
      size++;
      return;
    }

    // find next prime
    var i = l[size - 1].get_p() + 2;
    while (true) {
      // try div with previous all primes
      var is_prime = true;
      for (var j = 0; j < size; j++) {
        if (i % l[j].get_p() == 0) {
          is_prime = false;
          break;
        }
      }

      if (is_prime) {
        l[size] = new Prime(i);
        size++;
        return;
      }

      // try next
      i += 2;
    }

  }
}

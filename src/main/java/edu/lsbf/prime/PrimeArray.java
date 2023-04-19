package edu.lsbf.prime;

public class PrimeArray {

  private final /*@ spec_public @*/ Prime[] l;
  private /*@ spec_public @*/ int size = 0;


  /*@ public invariant // Premier invariant
    @ (* Les size premiers elements sont ranges en ordre croissant *);
    @*/
  /*@ public invariant // Deuxi�me invariant
    @ (* Les entiers compris entre deux elements consecutifs, 
    @ parmi les size premiers elements, ne sont pas premiers *);
    @*/

  /*@ ensures size == 0;
      @*/
  public PrimeArray() {
    l = new Prime[100];
    size = 0;
  }

  /*@ ensures \old(size)+1 == size; @*/
  public void grow() {
  }
}

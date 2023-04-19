package edu.lsbf.AssemblyByTree;

/*@ nullable_by_default @*/
public class SetAsTree {
  public Integer val;
  public SetAsTree ltree;
  public SetAsTree rtree;

  //@ public invariant notNull();
  //@ public invariant ((ltree == null) || (!ltree.emptySet() && ( ltree.max() < val )));
  //@ public invariant ((rtree == null) || (!rtree.emptySet() && ( rtree.min() > val )));
  //@ public invariant balanceFactor() < 2 && balanceFactor() > -2;
  //@ public invariant (* no cycle in the tree *);

  // Constructor
  public SetAsTree() { // produces an empty set
    val = null;
    ltree = null;
    rtree = null;
  }

  public SetAsTree(int v) { // produces a singleton node
    val = v;
    ltree = null;
    rtree = null;
  }

  public SetAsTree(int v, SetAsTree l, SetAsTree r) { // arbitrary node
    val = v;
    ltree = l;
    rtree = r;
  }

  //@ pure
  //@ helper
  public boolean notNull() {
    System.out.println("val: " + val + " ltree: " + ltree + " rtree: " + rtree);
    return ((val != null) || (ltree == null && rtree == null));
  }

  //getters and setters
  public Integer getVal() {
    return val;
  }

  public void setVal(Integer val) {
    this.val = val;
  }

  public SetAsTree getLtree() {
    return ltree;
  }

  public void setLtree(SetAsTree ltree) {
    this.ltree = ltree;
  }

  public SetAsTree getRtree() {
    return rtree;
  }

  public void setRtree(SetAsTree rtree) {
    this.rtree = rtree;
  }

  // Application specific methods

  //@ ensures contains(v);
  public void insert(int v) {
  }

  //@ ensures !contains(v);
  public void delete(int v) {
  }

  //@ pure
  protected int getHeight() {
    final var lh = ltree == null ? 0 : ltree.getHeight();
    final var rh = rtree == null ? 0 : rtree.getHeight();
    return Math.max(lh, rh) + 1;
  }

  //@ pure
  //@ helper
  public int balanceFactor() {
    final var lh = ltree == null ? 0 : ltree.getHeight();
    final var rh = rtree == null ? 0 : rtree.getHeight();
    return Math.abs(lh - rh);
  }

  // Pure functions used in the specification
  //@ pure
  //@ helper
  public boolean emptySet() {
    return val == null;
  }

  //@ requires !emptySet();
  //@ pure
  //@ helper
  public int min() {
      if (ltree != null && ltree.getVal() < val) {
          return ltree.min();
      } else {
          return val;
      }
  }

  //@ requires !emptySet();
  //@ pure
  //@ helper
  public int max() {
      if (rtree != null && rtree.getVal() > val) {
          return rtree.max();
      } else {
          return val;
      }
  }

  //@ pure
  public boolean contains(int v) {
    if (val == null) {
      return false;
    } else if (v == val) {
      return true;
    } else if (v > val && (rtree != null)) {
      return rtree.contains(v);
    } else if (v < val && (ltree != null)) {
      return ltree.contains(v);
    } else {
      return false;
    }
  }

  // Non side-effecting methods
  public /*@ non_null @*/  String toString() {
    String s = "";
    if (ltree != null) {
      s = s + ltree.toString();
    }
    ;
    s = s + " " + val + " ";
    if (rtree != null) {
      s = s + rtree.toString();
    }
    ;
    return s;
  }

  public void skip() {
  } // useful to test the invariant.
}

package edu.lsbf.lab3;

/*@ nullable_by_default @*/
public class SetAsTree {
  public Integer val;
  public SetAsTree ltree;
  public SetAsTree rtree;

  //@ public invariant notNull();
  //@ public invariant ((ltree == null) || (!ltree.emptySet() && ( ltree.max() < val )));
  //@ public invariant ((rtree == null) || (!rtree.emptySet() && ( rtree.min() > val )));
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
  //@ spec_public
  private boolean notNull() {
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
  //@ ensures isBalanced();
  public void insert(int v) {
    insertImpl(v);
  }

  void insertImpl(int v) {
    if (val == null) {
      val = v;
    } else if (v > val) {
      if (rtree == null) {
        rtree = new SetAsTree(v);
      } else {
        rtree.insertImpl(v);
      }
    } else if (v < val) {
      if (ltree == null) {
        ltree = new SetAsTree(v);
      } else {
        ltree.insertImpl(v);
      }
    }
    rebalanced();
  }

  //@ ensures !contains(v);
  //@ ensures isBalanced();
  public void delete(int v) {
    deleteImpl(v);
  }

  void deleteImpl(int v) {
    if (val == null) {
      return;
    } else if (v > val) {
      if (rtree != null) {
        rtree.deleteImpl(v);
        if (rtree.val == null) rtree = null;
      }
    } else if (v < val) {
      if (ltree != null) {
        ltree.deleteImpl(v);
        if (ltree.val == null) ltree = null;
      }
    } else {
      if (ltree == null && rtree == null) {
        val = null;
      } else if (ltree == null) {
        val = rtree.getVal();
        ltree = rtree.getLtree();
        rtree = rtree.getRtree();
      } else if (rtree == null) {
        val = ltree.getVal();
        rtree = ltree.getRtree();
        ltree = ltree.getLtree();
      } else {
        val = rtree.min();
        rtree.deleteImpl(val);
        if (rtree.val == null) rtree = null;
      }
    }
    rebalanced();
  }

  private void rebalanced() {
    if (balanceFactor() > 1) {
      if (rtree.balanceFactor() < 0) {
        rtree.rotateRight();
      }
      rotateLeft();
    } else if (balanceFactor() < -1) {
      if (ltree.balanceFactor() > 0) {
        ltree.rotateLeft();
      }
      rotateRight();
    }
  }

  private void rotateLeft() {
    final var newRoot = new SetAsTree(val, ltree, rtree.getLtree());
    val = rtree.getVal();
    rtree = rtree.getRtree();
    ltree = newRoot;
  }

  private void rotateRight() {
    final var newRoot = new SetAsTree(val, ltree.getRtree(), rtree);
    val = ltree.getVal();
    ltree = ltree.getLtree();
    rtree = newRoot;
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
    return rh - lh;
  }

  //@ pure
  //@ helper
  public boolean isBalanced() {
    final var isBalanced = Math.abs(balanceFactor()) < 2;
    return isBalanced;
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

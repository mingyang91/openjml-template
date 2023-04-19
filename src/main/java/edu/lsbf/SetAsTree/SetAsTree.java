package edu.lsbf.SetAsTree;

/*@ nullable_by_default @*/
public class SetAsTree {
  public Integer val;
  public SetAsTree ltree;
  public SetAsTree rtree;

  //@ public invariant ((val != null) || (ltree == null && rtree == null));
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
  public void insert(int v) {
    if (val == null) {
      val = v;
    } else if (v < val) {
      if (ltree == null) {
        ltree = new SetAsTree(v);
      } else {
        ltree.insert(v);
      }
    } else if (v > val) {
      if (rtree == null) {
        rtree = new SetAsTree(v);
      } else {
        rtree.insert(v);
      }
    } else {
      System.out.println("Value " + v + " already in the set.");
    }
  }

  public void delete(int v) {
    if (val == null) {
      System.out.println("Value " + v + " not in the set.");
    } else if (v < val) {
      if (ltree == null) {
        System.out.println("Value " + v + " not in the set.");
      } else {
        ltree.delete(v);
        if (ltree.emptySet()) {
          ltree = null;
        }
      }
    } else if (v > val) {
      if (rtree == null) {
        System.out.println("Value " + v + " not in the set.");
      } else {
        rtree.delete(v);
        if (rtree.emptySet()) {
          rtree = null;
        }
      }
    } else {
      if (ltree == null && rtree == null) {
        val = null;
      } else if (ltree == null) {
        val = rtree.getVal();
        ltree = rtree.ltree;
        rtree = rtree.rtree;
      } else if (rtree == null) {
        val = ltree.getVal();
        rtree = ltree.rtree;
        ltree = ltree.ltree;
      } else {
        val = ltree.max();
        ltree.delete(val);
        if (ltree.emptySet()) {
          ltree = null;
        }
      }
    }
  }

  // Pure functions used in the specification
  //@ pure
  public boolean emptySet() {
    return val == null;
  }

  //@ pure
  public int min() {
    if (ltree != null && ltree.getVal() < val) {
      return ltree.min();
    } else {
      return val;
    }
  }

  //@ pure
  public int max() {
    if (rtree != null && rtree.getVal() > val) {
      return rtree.max();
    } else {
      return val;
    }
  }

  // Non side-effecting methods
  public /*@ non_null @*/  String toString() {
    String s = "";
    if (ltree != null) {
      s = s + ltree;
    }
    s = s + " " + val + " ";
    if (rtree != null) {
      s = s + rtree;
    }
    return s;
  }

  public void skip() {} // useful to test the invariant.
}

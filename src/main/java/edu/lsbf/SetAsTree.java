/*@ nullable_by_default @*/
public class SetAsTree{
    public Integer val;
    public SetAsTree ltree;
    public SetAsTree rtree;

    //@ public invariant ((val != null) || (ltree == null && rtree == null));
    //@ public invariant ((ltree == null) || (!ltree.emptySet() && ( ltree.max() < val.intValue() )));
    //@ public invariant ((rtree == null) || (!rtree.emptySet() && ( rtree.min() > val.intValue() )));
    //@ public invariant (* no cycle in the tree *);
      
    // Constructor
    public SetAsTree(){ // produces an empty set
        val = null;
        ltree = null;
        rtree = null;
    }
    public SetAsTree(int v){ // produces a singleton node
        val = new Integer(v);
        ltree = null;
        rtree = null;
    }
    public SetAsTree(int v, SetAsTree l, SetAsTree r){ // arbitrary node
        val = new Integer(v);
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
    public void insert(int v){
        if (val == null)
            val = new Integer(v);
        else if (v < val.intValue())
            if (ltree == null) ltree = new SetAsTree(v);
            else ltree.insert(v);
        else if (v > val.intValue())
            if (rtree == null) rtree = new SetAsTree(v);
            else rtree.insert(v);
        else
            System.out.println("Value "+v+" already in the set.");
    }
    public void delete(int v){
        if (val == null)
            System.out.println("Value "+v+" not in the set.");
        else if (v < val.intValue())
            if (ltree == null) System.out.println("Value "+v+" not in the set.");
            else ltree.delete(v);
        else if (v > val.intValue())
            if (rtree == null) System.out.println("Value "+v+" not in the set.");
            else rtree.delete(v);
        else{
            if (ltree == null && rtree == null) val = null;
            else if (ltree == null) val = rtree.getVal();
            else if (rtree == null) val = ltree.getVal();
            else{
                val = new Integer(ltree.max());
                ltree.delete(val.intValue());
            }
        }
    }
    
    // Pure functions used in the specification
    public /*@ pure @*/ boolean emptySet(){
         return val == null;
    } 
    public /*@ pure @*/ int min(){
        if (ltree != null && ltree.getVal().intValue() < val.intValue()){return ltree.min();}
        else return val.intValue();
    }    
    public /*@ pure @*/ int max(){
        if (rtree != null && rtree.getVal().intValue() > val.intValue()){return rtree.max();}
        else return val.intValue();
    }
    
    // Non side-effecting methods
    public /*@ non_null @*/  String toString(){
        String s = "";
        if (ltree != null) {s=s+ltree.toString();};
        s = s+" "+val+" ";
        if (rtree != null) {s=s+rtree.toString();};
        return s;
    }
    public void skip(){ } // useful to test the invariant.
}

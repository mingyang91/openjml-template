package edu.lsbf.lab7.extra;// Based on a B specification from Marie-Laure Potet.

public class Explosives {
    public int nb_inc = 0;
    public String [][] incomp = new String[50][2];
    public int nb_assign = 0;
    public String [][] assign = new String[30][2];
    // Prop 1 nb_inc is length of incomp table, and is less than 50
    //@ public invariant (0 <= nb_inc && nb_inc < 50);
    // Prop 2 nb_assign is length of assign table, and is less than 30
    //@ public invariant (0 <= nb_assign && nb_assign < 30);
    // Prop 3 incomp table only contains Prod, Prod pairs
    /*@ public invariant
            (\forall int i; 0 <= i && i < nb_inc;
               incomp[i][0].startsWith("Prod") && incomp[i][1].startsWith("Prod"));
     */
    // Prop 4 assign table only contains Bat, Prod pairs
    /*@ public invariant
       (\forall int i; 0 <= i && i < nb_assign;
               assign[i][0].startsWith("Bat") && assign[i][1].startsWith("Prod"));
      */
    // Prop 5 the two products in an incomp pair are different
    /*@ public invariant
       (\forall int i; 0 <= i && i < nb_inc; !(incomp[i][0]).equals(incomp[i][1]));
      */
    // Prop 6 if two products are incomp, then they are incomp in both orders
    /*@ public invariant
       (\forall int i; 0 <= i && i < nb_inc;
              (\exists int j; 0 <= j && j < nb_inc;
                 (incomp[i][0]).equals(incomp[j][1])
                    && (incomp[j][0]).equals(incomp[i][1])));
      */
    // Prop 7 the incomp products cannot be assigned to the same bat
    /*@ public invariant
       (\forall int i; 0 <= i &&  i < nb_assign;
           (\forall int j; 0 <= j && j < nb_assign;
              (i != j && (assign[i][0]).equals(assign [j][0])) ==>
              (\forall int k; 0 <= k && k < nb_inc;
                 (!(assign[i][1]).equals(incomp[k][0]))
                    || (!(assign[j][1]).equals(incomp[k][1])))));
      */


    //@ requires prod1.startsWith("Prod") && prod2.startsWith("Prod");
    //@ requires !(prod1.equals(prod2));
    //@ requires (\forall int i; 0 <= i && i < nb_inc; !(prod1.equals(incomp[i][0])) || !(prod2.equals(incomp[i][1])));
    //@ ensures nb_inc == \old(nb_inc) + 2;
    public void add_incomp(String prod1, String prod2){
        incomp[nb_inc][0] = prod1;
        incomp[nb_inc][1] = prod2;
        incomp[nb_inc+1][1] = prod1;
        incomp[nb_inc+1][0] = prod2;
        nb_inc = nb_inc+2;
    }

    //@ requires bat.startsWith("Bat") && prod.startsWith("Prod");
    //@ requires (\forall int i; 0 <= i && i < nb_assign; !(bat.equals(assign[i][0])) || !(prod.equals(assign[i][1])));
    //@ ensures nb_assign == \old(nb_assign) + 1;
    public void add_assign(String bat, String prod){
        assign[nb_assign][0] = bat;
        assign[nb_assign][1] = prod;
        nb_assign = nb_assign+1;
    }
    public void skip(){
    }
}

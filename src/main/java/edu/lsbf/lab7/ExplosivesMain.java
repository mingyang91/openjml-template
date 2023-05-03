class ExplosivesMain{

    public static void main(String[] args) {
       Explosives e = new Explosives();
	System.out.println("Inserting Prod_Nitro Prod_Glycerine");
	e.add_incomp("Prod_Nitro","Prod_Glycerine");
	System.out.println("Inserting Prod_Dyna Prod_Mite");
	e.add_incomp("Prod_Dyna","Prod_Mite");
	System.out.println("Assigning Prod_Dyna to Bat_1");
	e.add_assign("Bat_1","Prod_Dyna");
	System.out.println("Assigning Prod_Nitro to Bat_1");
	e.add_assign("Bat_1","Prod_Nitro");
	System.out.println("Assigning Prod_Mite to Bat_2");
	e.add_assign("Bat_2","Prod_Mite");
	System.out.println("Assigning Prod_Glycerine to Bat_1");
	e.add_assign("Bat_1","Prod_Glycerine");
	System.out.println("It should have exploded at this point");
	System.out.println("-------------------------");
	System.out.println("Incompatibilities");
	for(int i=0; i< e.nb_inc; i++){System.out.println(e.incomp[i][0] + "  " +e.incomp[i][1] );}
	System.out.println("-------------------------");
	System.out.println("Assignments");
	for(int i=0; i< e.nb_assign; i++){System.out.println(e.assign[i][0] + "  " +e.assign[i][1] );}
	System.out.println("-------------------------");
    }
}

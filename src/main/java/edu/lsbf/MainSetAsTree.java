public class MainSetAsTree{

    public static void main(String[] args) {
	
    SetAsTree s = new SetAsTree(5);
	System.out.println(s);
	s.insert(10);
	System.out.println(s);
	s.insert(1);
	System.out.println(s);
	s.delete(5);
	System.out.println(s);
    }
}

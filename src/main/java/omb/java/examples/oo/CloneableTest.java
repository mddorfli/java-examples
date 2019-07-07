package omb.java.examples.oo;

public class CloneableTest {
	int val;

	static class NotCloneable extends CloneableTest { 
	}
	
	static class IsCloneable extends CloneableTest implements Cloneable {
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	
	public static void main(String[] args) {
		NotCloneable nc = new NotCloneable();
		nc.val = 5;
		try {
			NotCloneable clone = (NotCloneable) nc.clone(); // accessible only because we are in the CloneableTest class
			System.out.println("original val: "+nc.val+", clone val: "+clone.val);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		IsCloneable ic = new IsCloneable();
		ic.val = 5;
		try {
			IsCloneable clone = (IsCloneable) ic.clone(); // accessible only because we are in the CloneableTest class
			System.out.println("original val: "+ic.val+", clone val: "+clone.val);
		} catch (CloneNotSupportedException e) {
			System.out.println("Nope: ");
			e.printStackTrace();
		}
	}
}

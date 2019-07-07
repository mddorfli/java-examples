package omb.java.examples.oo;

import java.util.List;

public class GenericTypeTest {

	class A {
		
	}
	class B extends A {
		
	}
	class C extends B {
		
	}
	
	A a = new A();
	C c = new C();
	
	public void addData1(List<? super B> list) {
//		list.add(a);
		list.add(c);
		Object object = list.get(0);
	}
	public void addData2(List<? extends B> list) {
//		list.add(a);
//		list.add(c);
		A a = list.get(0);
		B b = list.get(0);
//		C c = list.get(0);
	}
	
}

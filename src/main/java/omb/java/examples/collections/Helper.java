package omb.java.examples.collections;

import java.util.LinkedList;
import java.util.Queue;

class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}

public class Helper {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void helpPeople(Queue people, Queue helped) {
		do {
			Person p = (Person) people.poll();
			System.out.println("Helped : " + p + " ");
			helped.offer(p.getName());
		} while (!people.isEmpty());
	}

	public static void main(String[] args) {
		Queue<Person> q = new LinkedList<Person>();
		q.offer(new Person("Pope"));
		q.offer(new Person("John"));
		Queue<Person> helpedQ = new LinkedList<Person>();
		Helper h = new Helper();
		h.helpPeople(q, helpedQ);
		for (Object person : helpedQ) {
			System.out.println(person);
		}
		for (Person person : helpedQ) {
			System.out.println(person);
		}
	}
}
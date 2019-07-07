package omb.java.examples.collections;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DequeTest {
	public static void main(String[] args) {
		Deque<Integer> dq = new ArrayDeque<>(Arrays.asList(0));
		
		System.out.println(dq);
		System.out.println("\nAdd to front: ");
		dq.push(-1);
		System.out.printf("push(%d) -> %s\n", -1, dq);
		dq.addFirst(-2);
		System.out.printf("addFirst(%d) -> %s\n", -2, dq);
		dq.offerFirst(-3);
		System.out.printf("offerFirst(%d) -> %s\n", -3, dq);

		System.out.println("\nAdd to end: ");
		dq.offer(1);
		System.out.printf("offer(%d) -> %s\n", 1, dq);
		dq.add(2);
		System.out.printf("add(%d) -> %s\n", 2, dq);
		dq.addLast(3);
		System.out.printf("addLast(%d) -> %s\n", 3, dq);
		dq.offer(4);
		System.out.printf("offerLast(%d) -> %s\n", 4, dq);
		
		System.out.println("\nPeeking from the front: ");
		System.out.printf("peek -> %d  %s\n",dq.peek(),dq);
		System.out.printf("element -> %d  %s\n",dq.element(),dq);
		System.out.printf("getFirst -> %d  %s\n",dq.getFirst(),dq);
		System.out.printf("peekFirst -> %d  %s\n",dq.peekFirst(),dq);
		
		System.out.println("\nPeeking from the end: ");
		System.out.printf("getLast -> %d  %s\n",dq.getLast(),dq);
		System.out.printf("peekLast -> %d  %s\n",dq.peekLast(),dq);
		
		System.out.println("\nRemoving from the front: ");
		System.out.printf("pop -> %d  %s\n",dq.pop(),dq);
		System.out.printf("poll -> %d  %s\n",dq.poll(),dq);
		System.out.printf("remove -> %d  %s\n",dq.remove(),dq);
		System.out.printf("pollFirst -> %d  %s\n",dq.pollFirst(),dq);
		System.out.printf("removeFirst -> %d  %s\n",dq.removeFirst(),dq);

		System.out.println("\nRemoving from the end: ");
		System.out.printf("removeLast -> %d  %s\n",dq.removeLast(),dq);
		System.out.printf("pollLast -> %d  %s\n",dq.pollLast(),dq);
	}
}

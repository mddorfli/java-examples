package omb.java.examples.concurrency;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<BigInteger> {

	private static final long serialVersionUID = 1L;
	private int from;
	private int to;

	public ForkJoinTest(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	protected BigInteger compute() {
		BigInteger result;
		if (from == to) {
			// compute square
			result = new BigInteger("2").pow(to-1);
			System.out.println("Square "+to+" cost "+result+" grains.");
			
		} else {
			// split the work
			int midpoint = from + ((to - from) / 2);
			ForkJoinTest leftHalf = new ForkJoinTest(from, midpoint);
			leftHalf.fork();
			
			ForkJoinTest rightHalf = new ForkJoinTest(midpoint+1, to);
			BigInteger leftHalfResult = rightHalf.compute();
			BigInteger rightHalfResult = leftHalf.join();
			result = leftHalfResult.add(rightHalfResult);
		}
		return result;
	}


	public static void main(String[] args) {
		System.out.println("The king was bored.");
		System.out.println("So he summoned the brightest genius in his kingdom to fashion for him a game, with which he might amuse himself.");
		System.out.println("The man invented a game on a checkered, 8x8 grid which he called 'chess'.");
		System.out.println("The king was so impressed with the game, he said the man could name any price and he would pay for it.");
		System.out.println("The man said 'The first square costs one grain of rice.'");
		System.out.println("'The second square costs double. The third, four and so fort.'");
		System.out.println("The king foolishly proclaimed 'Any price! You shall have it!'");
		System.out.println("Unfortunately for the king, fork/join had not been invented.");
		ForkJoinPool fjp = new ForkJoinPool();
		ForkJoinTest job = new ForkJoinTest(1, 8*8);
		BigInteger result = fjp.invoke(job);
		System.out.format("The king had to pay %,d grains of rice.\n", result);
	}

}

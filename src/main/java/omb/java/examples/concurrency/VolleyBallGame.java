package omb.java.examples.concurrency;

import java.util.concurrent.ThreadLocalRandom;

public class VolleyBallGame {

	static int TEAM_A_SCORE = 0;
	static int TEAM_B_SCORE = 0;

	static Ball BALL = new Ball();

	static class Ball {
		static final int STATE_WAITING = 0;
		static final int STATE_IN_PLAY = 1;
		int state = STATE_WAITING;
		boolean servingSide;
	}
	
	static class Player implements Runnable {
		private String name;
		private boolean side;
		
		public Player(String name, boolean side) {
			this.name = name;
			this.side = side;
		}

		@Override
		public void run() {
			while(TEAM_A_SCORE < 20 && TEAM_B_SCORE < 20) {
				synchronized (BALL) {
					switch(BALL.state) {
					case Ball.STATE_IN_PLAY:
						System.out.printf("Player %s (team %c) tries to receive...", name, (side?'A':'B'));
						try {
							BALL.wait(1000);
						} catch (InterruptedException e) {
						}
						int hit = ThreadLocalRandom.current().nextInt(0, 100);
						if (hit > 50) {
							System.out.println("...and succeds!");
							// ball goes on to next player
						} else {
							System.out.println("...and fails!");
							boolean winningTeam = BALL.servingSide != side;
							if (winningTeam) {
								TEAM_A_SCORE++;
							} else {
								TEAM_B_SCORE++;
							}
							System.out.printf("Team A: %d! Team B: %d!\n", TEAM_A_SCORE, TEAM_B_SCORE);
							BALL.state=Ball.STATE_WAITING;
						}

						break;
					case Ball.STATE_WAITING:
						System.out.printf("Player %s (team %c) serves...\n", name, (side?'A':'B'));
						try {
							BALL.wait(5000);
						} catch (InterruptedException e) {
						}
						BALL.state = Ball.STATE_IN_PLAY;
						BALL.servingSide = side;
						break;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String[] players = {"bob", "charlie", "frank", "fred", "jason", "harry", "montgomery", "napoleon","xena", "veronica", "barry"};
		for (int i = 0; i < 10; i++) {
			Player player = new Player(players[i], i < 5);
			new Thread(player).start();
		}
	}
}

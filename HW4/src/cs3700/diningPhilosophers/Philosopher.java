package cs3700.diningPhilosophers;

import java.util.Random;

public class Philosopher implements Runnable {
	private Random rand;
	private int id;
	private int timesEaten;
	private ForkStructured leftFork;
	private ForkStructured rightFork;

	Philosopher(int id, ForkStructured leftFork, ForkStructured rightFork) {
		rand = new Random();
		setId(id);
		setLeftFork(leftFork);
		setRightFork(rightFork);
		setTimesEaten(0);
	}

	@Override
	public void run() {
		try {
			while (true) {
				think();
				if (leftFork.pickUp(id)) {
					if (rightFork.pickUp(id)) {
						eat();
						rightFork.putDown(id);
					}
					leftFork.putDown(id);
				}
			}
		} catch (InterruptedException e) {
			// This philosopher has been interrupted!
		} finally {
			System.out.printf("Philosopher %d has eaten %d times!%n", id,
					timesEaten);
		}
	}

	private void think() throws InterruptedException {
		System.out.printf("Philosopher %d is thinking...%n", id);
		Thread.sleep(1000 * (rand.nextInt(2) + 1));
	}

	private void eat() throws InterruptedException {
		System.out.printf("Philosopher %d is eating...%n", id);
		timesEaten++;
		Thread.sleep(1000 * (rand.nextInt(5) + 1));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ForkStructured getLeftFork() {
		return leftFork;
	}

	public void setLeftFork(ForkStructured leftFork) {
		this.leftFork = leftFork;
	}

	public ForkStructured getRightFork() {
		return rightFork;
	}

	public void setRightFork(ForkStructured rightFork) {
		this.rightFork = rightFork;
	}

	public void setTimesEaten(int timesEaten) {
		this.timesEaten = timesEaten;
	}

	public int getTimesEaten() {
		return timesEaten;
	}

}

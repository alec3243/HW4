package cs3700.diningPhilosophers;

import java.util.Random;

public class Philosopher implements Runnable {
	private Random rand;
	private int id;
	private int timesEaten;
	private Fork leftFork;
	private Fork rightFork;
	private boolean useStructuredLocks;

	Philosopher(int id, Fork leftFork, Fork rightFork,
			boolean useStructuredLocks) {
		setId(id);
		rand = new Random();
		setTimesEaten(0);
		setLeftFork(leftFork);
		setRightFork(rightFork);
		setUseStructuredLocks(useStructuredLocks);
	}

	@Override
	public void run() {
		try {
			if (useStructuredLocks) {
				runStructured();
			} else {
				runUnstructured();
			}
		} catch (InterruptedException e) {
			// This philosopher has been interrupted!
		} finally {
			System.out.printf("Philosopher %d has eaten %d times!%n", id,
					timesEaten);
		}
	}

	private void runStructured() throws InterruptedException {
		while (true) {
			think();
			synchronized (leftFork) {
				synchronized (rightFork) {
					eat();
				}
			}
		}
	}

	private void runUnstructured() throws InterruptedException {
		while (true) {
			think();
			leftFork.pickUpUnstructured();
			rightFork.pickUpUnstructured();
			eat();
			if (rightFork.isHeldByCurrentThread()) {
				rightFork.putDownUnstructured();
			}
			if (leftFork.isHeldByCurrentThread()) {
				leftFork.putDownUnstructured();
			}
		}

	}

	private void think() throws InterruptedException {
		int time = rand.nextInt(5) + 1;
		System.out.printf("Philosopher %d is thinking for %d seconds...%n", id,
				time);
		Thread.sleep(time * 1000);
	}

	private void eat() throws InterruptedException {
		int time = rand.nextInt(10) + 1;
		System.out.printf("Philosopher %d is eating for %d seconds...%n", id,
				time);
		timesEaten++;
		Thread.sleep(time * 1000);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Fork getLeftFork() {
		return leftFork;
	}

	public void setLeftFork(Fork leftFork) {
		this.leftFork = leftFork;
	}

	public Fork getRightFork() {
		return rightFork;
	}

	public void setRightFork(Fork rightFork) {
		this.rightFork = rightFork;
	}

	public void setTimesEaten(int timesEaten) {
		this.timesEaten = timesEaten;
	}

	public int getTimesEaten() {
		return timesEaten;
	}

	public void setUseStructuredLocks(boolean useStructuredLocks) {
		this.useStructuredLocks = useStructuredLocks;
	}

}

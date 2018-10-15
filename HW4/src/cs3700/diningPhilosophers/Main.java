package cs3700.diningPhilosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		final int PHILOSOPHER_COUNT = 5;

		// Begin - Structured Locks
		ExecutorService executor = Executors
				.newFixedThreadPool(PHILOSOPHER_COUNT);
		List<Philosopher> philosophers = new ArrayList<>();
		List<Fork> forks = new ArrayList<>();
		for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
			forks.add(new Fork());
		}
		Philosopher philosopher;
		for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
			// Last philosopher thinks that the left fork is on the right
			if (i == PHILOSOPHER_COUNT) {
				philosopher = new Philosopher(i, forks.get(0), forks.get((i)
						% PHILOSOPHER_COUNT), true);
			} else {
				philosopher = new Philosopher(i, forks.get(i),
						forks.get((i + 1) % PHILOSOPHER_COUNT), true);
			}
			philosophers.add(philosopher);
			executor.execute(philosopher);
		}
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 120000) { // 2 minutes
			Thread.yield();
		}
		executor.shutdownNow();
		// End - Structured Locks

		// Begin - Unstructured Locks
		executor = Executors.newFixedThreadPool(PHILOSOPHER_COUNT);
		philosophers = new ArrayList<>();
		forks = new ArrayList<>();

		for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
			forks.add(new Fork());
		}
		philosopher = null;
		for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
			philosopher = new Philosopher(i, forks.get(i), forks.get((i + 1)
					% PHILOSOPHER_COUNT), false);
			philosophers.add(philosopher);
			executor.execute(philosopher);
		}
		start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 120000) { // 2 minutes
			Thread.yield();
		}
		executor.shutdownNow();
		// End - Unstructured Locks
	}

}

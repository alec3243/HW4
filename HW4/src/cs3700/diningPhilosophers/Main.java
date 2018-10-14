package cs3700.diningPhilosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		final int PHILOSOPHER_COUNT = 5;
		ExecutorService executor = Executors
				.newFixedThreadPool(PHILOSOPHER_COUNT);
		List<Philosopher> philosophers = new ArrayList<>();
		List<ForkStructured> structuredForks = new ArrayList<>();

		for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
			structuredForks.add(new ForkStructured());
		}
		Philosopher philosopher;
		for (int i = 0; i < PHILOSOPHER_COUNT; i++) {
			philosopher = new Philosopher(i, structuredForks.get(i),
					structuredForks.get((i + 1) % PHILOSOPHER_COUNT));
			philosophers.add(philosopher);
			executor.execute(philosopher);
		}
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 21000000) { // 20 minutes
			Thread.yield();
		}
		executor.shutdownNow();

	}

}

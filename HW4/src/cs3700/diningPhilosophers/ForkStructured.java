package cs3700.diningPhilosophers;

import java.util.concurrent.locks.ReentrantLock;

public class ForkStructured extends ReentrantLock {

	ForkStructured() {
		super();
	}

	public boolean pickUp(int id) {
		if (tryLock()) {
			return true;
		}
		return false;
	}

	public void putDown(int id) {
		unlock();
	}

}

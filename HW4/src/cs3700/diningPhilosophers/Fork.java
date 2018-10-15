package cs3700.diningPhilosophers;

import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("serial")
public class Fork extends ReentrantLock {

	Fork() {
		super();
	}

	public boolean pickUpUnstructured() {
		if (tryLock()) {
			return true;
		}
		return false;
	}

	public void putDownUnstructured() {
		unlock();
	}
}

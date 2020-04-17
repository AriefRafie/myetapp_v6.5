package lebah.template;

public class UID {
	
	static long current= System.currentTimeMillis();
	static public synchronized long get() {
		return current++;
	}
	static public synchronized String getUID() {
		return Long.toString(current++);
	}

}

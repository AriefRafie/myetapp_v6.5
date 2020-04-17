package ekptg.model.utils;

public class UniqueStringId {
	  static long current = System.currentTimeMillis();

	  public static synchronized String get()
	  {
	    return Long.toString(current++);
	  }
}
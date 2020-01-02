package ekptg.engine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MemWorkout {
  private static final int K = 1024;
  private int maxStep;
  private LinkedList blobs = new LinkedList();
  private long totalAllocs;
  private long totalUnrefs;
  private long unrefs;

  public String toString() {
    return "MemWorkout allocs=" + totalAllocs + " unrefs=" + totalUnrefs;
  }

  private static class Blob {
    public final int size;
    private final byte[] data;
    public Blob(int size) {
      this.size = size;
      data = new byte[size];
    }
  }

  private void grow(long goal) {
    long totalGrowth = 0;
    long allocs = 0;
    while (totalGrowth < goal) {
      int grow = (int)(Math.random() * maxStep);
      blobs.add(new Blob(grow));
      allocs++;
      totalGrowth += grow;
    }
    totalAllocs += allocs;
    System.out.println("" + allocs + " allocs, " + totalGrowth + " bytes");
  }

  private void shrink(long goal) {
    long totalShrink = 0;
    unrefs = 0;
    try {
      while (totalShrink < goal) {
        totalShrink += shrinkNext();
      }
    } catch (NoSuchElementException nsee) {
      System.out.println("all items removed");
    }
    totalUnrefs+= unrefs;
    System.out.println("" + unrefs + " unreferenced objs, " + totalShrink + " bytes");
  } 

  private long shrinkNext() {
    //choice of FIFO/LIFO very important!
    Blob b = (Blob) blobs.removeFirst();
    //Blob b = (Blob) blobs.removeLast();
    unrefs++;
    return b.size;
  }

  public MemWorkout(int maxStep) {
    this.maxStep = maxStep;
  }

  public static void main(String [] args) {
    if (args.length < 1) {
      //throw new Error ("usage MemWorkout maxStepKB");
    	//args[1] = "5";
    }
    int maxStep = Integer.parseInt(args[0]) * K;
    if (maxStep < (K)) throw new Error("maxStep must be at least 1KB");
    MemWorkout mw = new MemWorkout(maxStep);
    try {
      while (true) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        logMemStats();
        System.out.println("{intMB} allocates, {-intMB} deallocates, GC collects garbage, EXIT exits");
        String s = br.readLine();
        if (s.equals("GC")) {
          System.gc();
          System.runFinalization();
          continue;
        }
        long alloc = Integer.parseInt(s) * 1024* 1024;
        if (alloc > 0) {
          mw.grow(alloc);
        } else {
          mw.shrink(-alloc);
        }
      }
    } catch (NumberFormatException ne) {
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.out.println(mw);
  }

  public static void logMemStats() {
    Runtime rt = Runtime.getRuntime();
    System.out.println("total mem: " + (rt.totalMemory()/K) +
                       "K free mem: " + (rt.freeMemory()/K) + "K");
  }
}


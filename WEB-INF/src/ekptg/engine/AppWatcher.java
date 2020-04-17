package ekptg.engine;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import lebah.db.Db;

import org.apache.log4j.Logger;


public final class AppWatcher
  implements Runnable
{
  static Logger myLogger = Logger.getLogger(AppWatcher.class);
  private static AppWatcher instance = null;
  private Thread thread;
  private long seconds;
  private long totalMem;
  private long freeMem;
  private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

  public static AppWatcher getInstance()
  {
    if (instance == null) instance = new AppWatcher();
    return instance;
  }

  public void run() {
	  //myLogger.info("eTapp Watcher is running..");
	  //Get System Properties
	  
//	  while(true) {
//	  try {
//		  getMemoryStatus();
//		  thread.sleep(1000*60*10); //1 second * 60 * 10 = every 10 minit
//	    } catch (InterruptedException e) {
//	    	//What to do here??
//	    }
//	  }
	  

  }

  public void start() {
    this.thread = new Thread(this);
    this.thread.setName("eTapp Watcher.");
    this.thread.start();
    myLogger.info("Started!");
  }

  public void stop() {
    this.thread = null;
    myLogger.info("Stopped!");
  }

  public static void main(String[] args) {
    AppWatcher w = getInstance();
    w.start();
  }

  public void getMemoryStatus() {
	  //System.gc();
//	  Runtime.getRuntime().gc(); //Free up memory using garbage collector
//	  Runtime.getRuntime().runFinalization();
//	  String txt = this.now() + " =>" +
//	  "Total Memory: "+Runtime.getRuntime().totalMemory()/1048576+" MB" +
//	  "|Free: "+Runtime.getRuntime().freeMemory()/1048576+" MB";
	  
	  //myLogger.info(txt);  // 1024 x 1024 = 1K x 1K = 1Meg
  }
  
  public void getDBConnectionStatus()  throws Exception {
	  	Db db = null; 
		try { 
		//Open the database connection
		db = new Db(); 
		String sql ="select count(*) from users"; 
		//get some data 
		ResultSet rs = db.getStatement().executeQuery(sql); 
		if ( rs.next() ) { 
			int total = rs.getInt(1); 
		} 
		} finally { 
		//Close the database connection 
		if ( db != null ) db.close(); 
		} 
  }
  
  public String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    return sdf.format(cal.getTime());
  }

 
}

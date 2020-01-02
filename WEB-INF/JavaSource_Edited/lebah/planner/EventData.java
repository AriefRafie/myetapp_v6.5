package lebah.planner;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.db.UniqueID;
import lebah.util.DateTool;

public class EventData
{
	public static boolean isNumeric(String inputData) {
		  return inputData.matches("-?\\d+(.\\d+)?");
	}
	
  public static void addEvent(int year, int month, int day, Hashtable event)
    throws Exception
  {
    Db db = null;
    String sql = "";
    try {
    	String EVENT_ID = (String)event.get("eventId");
    	if (!"".equalsIgnoreCase(EVENT_ID)) {
    		if (EVENT_ID.length() > 30) {
    			EVENT_ID = EVENT_ID.substring(1, 30);
    		}
    	}
    	db = new Db();
    	SQLRenderer r = new SQLRenderer();
    	
    	//---------------------------
    	// kmie, 20100728
    	// put all in variables
    	String EVENT_TEXT = (String)event.get("eventText");
    	String VIEW_SCOPE = (String)event.get("viewScope");
    	
    	if ("".equalsIgnoreCase(EVENT_TEXT)) {
    		EVENT_TEXT = EVENT_ID;
    	}
    	//---------------------------
    	// kmie, 20100728
    	// ensure user_id is numeric, if not then query from table USERS
    	Statement stmt = db.getStatement();
    	ResultSet rs = null;
    	
    	String USER_ID = (String) event.get("userId");
    	if (!isNumeric(USER_ID)) {
    		// query from table
    		r.add("USER_ID");
    		r.add("USER_LOGIN", USER_ID);
    		sql = r.getSQLSelect("USERS");
    		r.clear();
    		rs = stmt.executeQuery(sql);
    		if (rs.next()) {
    			USER_ID = rs.getString(1) == null ? "0" : rs.getString(1);
    		} else {
    			USER_ID = "0";
    		}
    	}
    	//---------------------------
    	
    	r.add("id", UniqueID.getUID());
    	//r.add("user_id", (String)event.get("userId"));
    	r.add("user_id", USER_ID);
    	r.add("event_id", EVENT_ID);
    	r.add("event_text", EVENT_TEXT);
    	r.add("view_scope", VIEW_SCOPE);
    	r.add("event_date", r.unquote("TO_DATE('" + DateTool.getDateStr(year, month, day) + "', 'yyyy-MM-dd')"));
    	sql = r.getSQLInsert("calendar_event");
    	db.getStatement().executeUpdate(sql);
    }
    finally {
      if (db != null) db.close();
    }
  }

  public static Collection getEvent(String user, int year, int month, int day) throws Exception {
    Db db = null;
    String sql = "";
    try {
      Hashtable event;
      Vector localVector1;
      Vector list = new Vector();
      db = new Db();
      SQLRenderer r = new SQLRenderer();

      r.add("id");
      r.add("event_id");
      r.add("event_text");
      r.add("event_date");
      r.add("user_id");
      r.add("event_date", DateTool.getDateStr(year, month, day));
      r.add("view_scope", "public");
      sql = r.getSQLSelect("calendar_event");

      ResultSet rs = db.getStatement().executeQuery(sql);

      while (rs.next()) {
        event = new Hashtable();
        event.put("id", rs.getString(1));
        event.put("event_id", rs.getString(2));
        event.put("event_text", rs.getString(3));
        event.put("event_date", DateTool.getDateStr(rs.getDate(4)));
        event.put("user_id", rs.getString(5));
        list.addElement(event);
      }

      r.clear();
      r.add("id");
      r.add("event_id");
      r.add("event_text");
      r.add("event_date");
      r.add("event_date", DateTool.getDateStr(year, month, day));
      r.add("view_scope", "private");
      r.add("user_id", user);
      sql = r.getSQLSelect("calendar_event");

      rs = db.getStatement().executeQuery(sql);

      while (rs.next()) {
        event = new Hashtable();
        event.put("id", rs.getString(1));
        event.put("event_id", rs.getString(2));
        event.put("event_text", rs.getString(3));
        event.put("event_date", DateTool.getDateStr(rs.getDate(4)));
        event.put("user_id", user);
        list.addElement(event);
      }

      return list;
    } finally {
      if (db != null) db.close();
    }
  }

  public static void deleteEvent(String uid) throws Exception
  {
    Db db = null;
    try {
      db = new Db();
      String sql = "delete from calendar_event where id = '" + uid + "'";
      db.getStatement().executeUpdate(sql);
    } finally {
      if (db != null) db.close();
    }
  }
}
package ekptg.model.sms;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class PopupSMSHistoryData {
	
	Vector<Hashtable<String,String>> historyData = null;
	
	public void historyData(String jenisSMS) throws Exception{
		Db db = null;
	    String sql = "";
	    try {
	    	  historyData = new Vector<Hashtable<String,String>>();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      if("0".equals(jenisSMS)){
		    	  sql = "SELECT FILENAME,FOLDER_NAME FROM MYSMS_HISTORY " +
		    			"";
			      sql +=" ORDER BY FILENAME DESC";
		      
		      }else{
		    	  sql = "SELECT FILENAME,FOLDER_NAME FROM MYSMS_HISTORY WHERE JENIS_SMS='"+jenisSMS+"'";
			      sql +=" ORDER BY FILENAME DESC";
		      
		      }
		      	    
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable<String,String> h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", String.valueOf(bil));
		    	  h.put("FILENAME", rs.getString("FILENAME")==null?"":rs.getString("FILENAME"));
		    	  h.put("FOLDER_NAME", rs.getString("FOLDER_NAME")==null?"":rs.getString("FOLDER_NAME"));
		    	  historyData.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if(count == 0){
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", "");
		    	  h.put("FILENAME", "Tiada rekod.");
		    	  h.put("FOLDER_NAME", "");
		    	  historyData.addElement(h);
		      }
		     
		    } finally {
		      if (db != null) db.close();
		    }
		      
		      
	}
	public Vector<Hashtable<String,String>> getHistoryData(String jenisSMS) throws Exception{
		Db db = null;
	    String sql = "";
	    try {
	    	  historyData = new Vector<Hashtable<String,String>>();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      if("0".equals(jenisSMS)){
		    	  sql = "SELECT FILENAME,FOLDER_NAME FROM MYSMS_HISTORY " +
		    	  		" WHERE FILENAME like '%.xls' ";		    	  	 
		    	  //sql +=" ORDER BY FILENAME DESC";
		      
		      }else{
		    	  sql = "SELECT FILENAME,FOLDER_NAME FROM MYSMS_HISTORY WHERE JENIS_SMS='"+jenisSMS+"'";
			      //sql +=" ORDER BY FILENAME DESC";
		      
		      }
		      sql +=" ORDER BY TARIKH_MASUK DESC";
      
		      ResultSet rs = stmt.executeQuery(sql);  
		      Hashtable<String,String> h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", String.valueOf(bil));
		    	  h.put("FILENAME", rs.getString("FILENAME")==null?"":rs.getString("FILENAME"));
		    	  h.put("FOLDER_NAME", rs.getString("FOLDER_NAME")==null?"":rs.getString("FOLDER_NAME"));
		    	  historyData.addElement(h);
		    	  bil++;
		    	  count++;
		    	  
		      }
		      if(count == 0){
		    	  h = new Hashtable<String,String>();
		    	  h.put("bil", "");
		    	  h.put("FILENAME", "Tiada rekod.");
		    	  h.put("FOLDER_NAME", "");
		    	  historyData.addElement(h);
		      }
		     
		    } finally {
		      if (db != null) db.close();
		    }
	    return historyData;
		      
	}
	
	public  Vector<Hashtable<String,String>> getHistoryData(){
		return historyData;
		
	}
	

}

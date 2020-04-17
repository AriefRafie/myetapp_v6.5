package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

public class SenaraiSemak {
	
	private static Vector list = new Vector();
	private static Format formatter = new SimpleDateFormat("dd/MM/yyyy");

	public static void add(Hashtable data) throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	
//	    	long id_senaraisemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
//	    	String idSenaraisemak = (String) data.get("id_senaraisemak");
	    	String id_senaraisemak = (String)data.get("id_senaraisemak");
//	    	int id_permohonan = Integer.parseInt(data.get("id_permohonan").toString());
	    	String semak1 = (String)data.get("semak1");
	    	String semak2 = (String)data.get("semak2");
	    	String semak3 = (String)data.get("semak3");
	    	String semak4 = (String)data.get("semak4");
	    	String semak5 = (String)data.get("semak5");
	    	String semak6 = (String)data.get("semak6");
	    	String semak7 = (String)data.get("semak7");
	    	
	    	 String id_user = (String)data.get("id_user");
	    	 
//	    	long id_senaraisemak = Long.parseLong(idSenaraisemak);
	      
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.update("id_senaraisemak", id_senaraisemak);
//	      r.update("id_permohonan", id_permohonan);
	      r.add("semak1", semak1);
	      r.add("semak2", semak2);
	      r.add("semak3", semak3);
	      r.add("semak4", semak4);
	      r.add("semak5", semak5);
	      r.add("semak6", semak6);
	      r.add("semak7", semak7);
	      r.add("id_kemaskini",id_user);
		  r.add("tarikh_kemaskini",r.unquote("sysdate"));
	      sql = r.getSQLUpdate("Tblpptsenaraisemak");
	      stmt.executeUpdate(sql);
	      	      
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static Vector setList(int id_permohonan) throws Exception {
		  
	    Db db = null;
	    list.clear();
	    String sql = "";
	    try {
	      Vector localVector1;
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_senaraisemak");
	      r.add("id_permohonan");
	      r.add("semak1");
	      r.add("semak2");
	      r.add("semak3");
	      r.add("semak4");
	      r.add("semak5");
	      r.add("semak6");
	      r.add("semak7");
	      
	      
	      r.add("id_permohonan", id_permohonan, "=");
	      sql = r.getSQLSelect("Tblpptsenaraisemak");
	      ResultSet rs = stmt.executeQuery(sql);
	      //Vector v = new Vector();
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	h = new Hashtable();
	    	h.put("bil", bil);
	        h.put("id_senaraisemak",rs.getLong("id_senaraisemak"));
	    	h.put("id_permohonan",rs.getInt("id_permohonan"));
	    	if(rs.getString("semak1") == null){
	    		h.put("semak1","");
	    	}else{
	    		h.put("semak1",rs.getString("semak1"));
	    	}
	    	if(rs.getString("semak2") == null){
	    		h.put("semak2","");
	    	}else{
	    		h.put("semak2",rs.getString("semak2"));
	    	}
	    	if(rs.getString("semak3") == null){
	    		h.put("semak3","");
	    	}else{
	    		h.put("semak3",rs.getString("semak3"));
	    	}
	    	if(rs.getString("semak4") == null){
	    		h.put("semak4","");
	    	}else{
	    		h.put("semak4",rs.getString("semak4"));
	    	}
	    	if(rs.getString("semak5") == null){
	    		h.put("semak5","");
	    	}else{
	    		h.put("semak5",rs.getString("semak5"));
	    	}
	    	if(rs.getString("semak6") == null){
	    		h.put("semak6","");
	    	}else{
	    		h.put("semak6",rs.getString("semak6"));
	    	}
	    	if(rs.getString("semak7") == null){
	    		h.put("semak7","");
	    	}else{
	    		h.put("semak7",rs.getString("semak7"));
	    	}
	    	
	    	list.addElement(h);
	    	bil++;
	      }
	      return list;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  public static Vector getList(){
		  return list;
	  } 

	  public static void update(Hashtable data) throws Exception 
	  {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	
//		    	long id_senaraisemak = DB.getNextID("TBLPPTSENARAISEMAK_SEQ");
		    	int id_senaraisemak = Integer.parseInt(data.get("id_senaraisemak").toString());
		    	int id_permohonan = Integer.parseInt(data.get("id_permohonan").toString());
		    	String semak1 = (String)data.get("semak1");
		    	String semak2 = (String)data.get("semak2");
		    	String semak3 = (String)data.get("semak3");
		    	String semak4 = (String)data.get("semak4");
		    	String semak5 = (String)data.get("semak5");
		    	String semak6 = (String)data.get("semak6");
		    	String semak7 = (String)data.get("semak7");
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.update("id_senaraisemak", id_senaraisemak);
		      r.add("id_permohonan", id_permohonan);
		      r.add("semak1", semak1);
		      r.add("semak2", semak2);
		      r.add("semak3", semak3);
		      r.add("semak4", semak4);
		      r.add("semak5", semak5);
		      r.add("semak6", semak6);
		      r.add("semak7", semak7);
		      
		      sql = r.getSQLInsert("Tblpptsenaraisemak");
		      stmt.executeUpdate(sql);
		      	      
		    } finally {
		      if (db != null) db.close();
		    }
		  }
	  public static void delete(String uid) throws Exception {
	    Db db = null;
	    int id_senaraisemak= Integer.parseInt(uid);
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_senaraisemak", id_senaraisemak);
	      sql = r.getSQLDelete("Tblpptsenaraisemak");
	      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
}
package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniSubMinitMesyuaratData {
	
	private static Vector paparSubMinit = new Vector();
	
	public static void setDataSubPara(int id)throws Exception {
		
		 Db db = null;
		 paparSubMinit.clear();
		 String sql = "";
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitmesyuaratsubpara");
		      r.add("b.id_Minitmesyuaratpara");
		      r.add("a.sub_Para");
		     
		      
		      r.add("a.id_Minitmesyuaratpara",r.unquote("b.id_Minitmesyuaratpara"));	
		      r.add("a.id_Minitmesyuaratsubpara",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitmesyuaratsubpara a, Tblpfdminitmesyuaratpara b");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitmesyuaratsubpara",rs.getString("id_Minitmesyuaratsubpara"));
		    	  h.put("id_Minitmesyuaratpara",rs.getString("id_Minitmesyuaratpara"));
		    	  h.put("sub_Para",rs.getString("sub_Para"));
		    	 
		    	  paparSubMinit.addElement(h);
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("id_Minitmesyuaratsubpara",0);
		    	  h.put("id_Minitmesyuaratpara",0);
		    	  h.put("sub_Para","");
		    	 
		    	  paparSubMinit.addElement(h);
		    	  
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataSubPara(){
		 
		  return paparSubMinit;
	  }
	 public static int  add(Hashtable data)throws Exception {
			
			Db db = null;
		    String sql = "";
		   
		    try
		    {	 
		    	  long idMinitmesyuaratsubpara = DB.getNextID("TBLPFDMINITMESYUARATPARA_SEQ");
		    	  String idMinitmesyuaratpara = (String)data.get("id_Minitmesyuaratpara");
			      String subPara = (String)data.get("sub_Para");
			     
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Minitmesyuaratsubpara",idMinitmesyuaratsubpara);
			      r.add("id_Minitmesyuaratpara",idMinitmesyuaratpara);
			      r.add("sub_Para", subPara);
			      
			      sql = r.getSQLInsert("tblpfdminitmesyuaratsubpara");  
			      stmt.executeUpdate(sql);
			      return (int)idMinitmesyuaratsubpara;
			    } finally {
			      if (db != null) db.close();
			    }

		}
		public static int update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	  int idMinitmesyuaratsubpara = (Integer)data.get("id_Minitmesyuaratsubpara");
			      String subPara = (String)data.get("sub_Para");
			     
				  
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Minitmesyuaratsubpara", idMinitmesyuaratsubpara);
			      r.add("sub_Para", subPara);
			     
				
				  sql = r.getSQLUpdate("tblpfdminitmesyuaratsubpara");
			      stmt.executeUpdate(sql);
			      return idMinitmesyuaratsubpara;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }
	

}

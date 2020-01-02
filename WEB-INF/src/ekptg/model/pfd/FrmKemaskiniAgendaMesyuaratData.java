package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;



public class FrmKemaskiniAgendaMesyuaratData{
	private static Vector paparAgenda = new Vector();
	public static void setDataAgenda(int id)throws Exception {
		
		 Db db = null;
		 paparAgenda.clear();
		 String sql = "";
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Agendamesyuarat");
		      r.add("b.id_Mesyuarat");
		      r.add("a.agenda_Mesyuarat");
		     
		      
		      r.add("a.id_Mesyuarat",r.unquote("b.id_Mesyuarat"));	
		      r.add("a.id_Agendamesyuarat",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdagendamesyuarat a, Tblpfdmesyuarat b");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Agendamesyuarat",rs.getString("id_Agendamesyuarat"));
		    	  h.put("id_Mesyuarat",rs.getString("id_Mesyuarat"));
		    	  h.put("agenda_Mesyuarat",rs.getString("agenda_Mesyuarat"));
		    	 
		    	  paparAgenda.addElement(h);
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("id_Agendamesyuarat",0);
		    	  h.put("id_Mesyuarat",0);
		    	  h.put("agenda_Mesyuarat","");
		    	  
		    	  paparAgenda.addElement(h);
		    	  
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataAgenda(){
		 
		  return paparAgenda;
	  }
	 public static int  add(Hashtable data)throws Exception {
			
			Db db = null;
		    String sql = "";
		   
		    try
		    {	 
		    	  long idAgendamesyuarat = DB.getNextID("TBLPFDAGENDAMESYUARAT_SEQ");
		    	  String idMesyuarat = (String)data.get("id_Mesyuarat");
			      String agendaMsyrt = (String)data.get("agenda");
			     
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Agendamesyuarat",idAgendamesyuarat);
			      r.add("id_Mesyuarat",idMesyuarat);
			      r.add("agenda_Mesyuarat", agendaMsyrt);
			      
			      sql = r.getSQLInsert("tblpfdagendamesyuarat");  
			      stmt.executeUpdate(sql);
			      return (int)idAgendamesyuarat;
			    } finally {
			      if (db != null) db.close();
			    }

		}
		public static int update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	  int idAgendamesyuarat = (Integer)data.get("idAgendamesyuarat");
			      String agendaMsyrt = (String)data.get("agenda");
			     
				  
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Agendamesyuarat", idAgendamesyuarat);
			      r.add("agenda_Mesyuarat", agendaMsyrt);
			     
				
				  sql = r.getSQLUpdate("tblpfdagendamesyuarat");
				  
			      stmt.executeUpdate(sql);
			      return idAgendamesyuarat;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }
	
	

}

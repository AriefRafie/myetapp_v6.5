package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmFailLainKemaskiniData{
	private static Vector<Hashtable<String,String>> paparAgenda = new Vector<Hashtable<String,String>>();
	static Logger mylog = Logger.getLogger(ekptg.model.htp.FrmFailLainKemaskiniData.class);
	
	public static Vector<Hashtable<String, String>> getDataFailLain(String id)throws Exception {
		 Vector<Hashtable<String, String>> paparAgenda1 = new Vector<Hashtable<String, String>>();

		 Db db = null;
		 String sql = "";	 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("ID_HTPFAILLAIN");
		      r.add("NO_FAIL_LAIN");
		      r.add("ID_FAIL");
		      r.add("CASE "+
		    		  "when (select count(*) from tblpfdfail f "+
		    		  "where f.no_fail=NO_FAIL_LAIN "+
		          		") > 0 THEN '1' "+
		    		  "ELSE '0' "+    
		    		  "END ISURL ");
		      r.add("NVL((select F.ID_FAIL from tblpfdfail f "+
		    		  "where f.no_fail=NO_FAIL_LAIN "+
		      			"),0) ID_FAILURL ");	      
		      //r.add("a.id_Mesyuarat",r.unquote("b.id_Mesyuarat"));	
		      r.add("ID_FAIL",id);		     
		    
		      sql = r.getSQLSelect("TBLHTPFAILLAIN ");
		      //mylog.info("add:"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable<String, String> h;
		      while (rs.next()) {
		    	  h = new Hashtable<String, String>();		    	  
		    	  h.put("idFailLain",rs.getString("ID_HTPFAILLAIN"));
		    	  h.put("idFail",rs.getString("ID_FAIL"));
		    	  h.put("noFail",rs.getString("NO_FAIL_LAIN"));
		    	  h.put("isURL",rs.getString("ISURL"));
		    	  h.put("idFailURL",rs.getString("ID_FAILURL"));
		    	  paparAgenda1.addElement(h);
		    	  
		      }
		      /*if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("id_Agendamesyuarat",0);
		    	  h.put("id_Mesyuarat",0);
		    	  h.put("agenda_Mesyuarat","");
		    	  
		    	  paparAgenda.addElement(h);
		    	  
		      }*/
		     return paparAgenda1;
		 }finally {
			 if (db != null) db.close();
		 }  
		 
	}

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
		     
		      Hashtable<String,String> h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable<String,String>();	    	  
		    	  h.put("id_Agendamesyuarat",rs.getString("id_Agendamesyuarat"));
		    	  h.put("id_Mesyuarat",rs.getString("id_Mesyuarat"));
		    	  h.put("agenda_Mesyuarat",rs.getString("agenda_Mesyuarat"));		    	 
		    	  paparAgenda.addElement(h);
		    	  count++;
		    	  
		      }
		      if (count == 0){
		    	  h = new Hashtable<String,String>();
		    	  h.put("id_Agendamesyuarat",String.valueOf(0));
		    	  h.put("id_Mesyuarat",String.valueOf(0));
		    	  h.put("agenda_Mesyuarat","");		    	  
		    	  paparAgenda.addElement(h);
		    	  
		      }
		     
		 }finally {
		      if (db != null) db.close();
		 }  
		 		 
	}
	public static Vector<Hashtable<String,String>> getDataAgenda(){	 
		return paparAgenda;
	}
	 
	 public static String add(Hashtable<String,String> data)throws Exception {			
		 Db db = null;
		 String sql = "";
		 try	{	 
			 long idFaiLain = DB.getNextID("TBLHTPFAILLAIN_SEQ");
			 String idFail = (String)data.get("id_Mesyuarat");
			 String agendaMsyrt = (String)data.get("agenda");
			 String idmasuk = (String)data.get("idmasuk");
			 String con = String.valueOf(data.get("con"));
			     			      
			 db = new Db();
			 Statement stmt = db.getStatement();
			 SQLRenderer r = new SQLRenderer();
			 if(!con.equals("")) {
				 r.update("id_fail",idFail);
				 r.add("flag_aktif","N");
				 sql = r.getSQLUpdate("tblhtpfaillain");
				 stmt.executeUpdate(sql);
				 
			 }
			      
			 r = new SQLRenderer();
			 r.add("ID_HTPFAILLAIN",idFaiLain);
			 r.add("ID_FAIL",idFail);
			 r.add("NO_FAIL_LAIN", agendaMsyrt);
			 r.add("ID_MASUK",r.unquote(idmasuk));				      
			 r.add("TARIKH_MASUK",r.unquote("sysdate")); 
			 r.add("ID_KEMASKINI",r.unquote(idmasuk));				      
			 r.add("TARIKH_KEMASKINI",r.unquote("sysdate")); 
			      
			 sql = r.getSQLInsert("tblhtpfaillain");  
			 mylog.info("add:"+sql);
			 stmt.executeUpdate(sql);
			 return String.valueOf(idFaiLain);
			      
		 } finally {
			 if (db != null) db.close();
		 }
	 
	 }
	 
	public static String update(Hashtable<String,String> data) throws Exception {
		Db db = null;
	    String sql = "";
	    try{
		   	String idAgendamesyuarat = String.valueOf(data.get("idAgendamesyuarat"));
		    String agendaMsyrt = (String)data.get("agenda");
		   	String idmasuk = (String)data.get("idmasuk");
			     				  
		    db = new Db();
		   	Statement stmt = db.getStatement();
		   	SQLRenderer r = new SQLRenderer();
		   	r.update("ID_HTPFAILLAIN", idAgendamesyuarat);
		   	r.add("NO_FAIL_LAIN", agendaMsyrt);
		   	r.add("ID_KEMASKINI",r.unquote(idmasuk));				      
		   	r.add("TARIKH_KEMASKINI",r.unquote("sysdate")); 
		   	sql = r.getSQLUpdate("tblhtpfaillain");
		   	mylog.info("update:"+sql);
		   	stmt.executeUpdate(sql);
		   	return idAgendamesyuarat;
			    
	    }finally {
	    	if (db != null) db.close();
	    	}
		
	}
		
		  public static void deleteFailLain(String uid) throws Exception {
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      r.add("ID_HTPFAILLAIN", r.unquote(uid));
		      sql = r.getSQLDelete("tblhtpfaillain");
		      stmt.executeUpdate(sql);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
		  

}

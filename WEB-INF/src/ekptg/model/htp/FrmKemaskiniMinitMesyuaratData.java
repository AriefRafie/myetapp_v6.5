package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniMinitMesyuaratData {
	
	private static Vector paparMesyuarat = new Vector();
	private static Vector paparMinit = new Vector();
	private static Vector paparPara = new Vector();
	private static Vector senaraiPerkaraMinit = new Vector();
	private static Vector senaraiMinit = new Vector();
	private static Vector senaraiSubMinit = new Vector();
	public static void setDataMesyuarat(int id)throws Exception {
		
		 Db db = null;
		 db = new Db();
		 long totalFail = 0;
		 paparMesyuarat.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      
		     
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Mesyuarat");
		      r.add("a.bil_Mesyuarat");
		      r.add("a.tajuk_Mesyuarat");
		      r.add("a.tarikh_Mesyuarat");
		      
		      r.add("a.id_Mesyuarat",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdmesyuarat a");
			  Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      System.out.println("sql minit ::"+sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Mesyuarat",rs.getString("id_Mesyuarat"));
		    	  h.put("bil_Mesyuarat", rs.getString("bil_Mesyuarat"));
		    	  h.put("tajuk_Mesyuarat",rs.getString("tajuk_Mesyuarat")== null?"":rs.getString("tajuk_Mesyuarat"));
		    	  h.put("tarikh_Mesyuarat",rs.getDate("tarikh_Mesyuarat")== null?"":sdf.format(rs.getDate("tarikh_Mesyuarat")));
		    	  paparMesyuarat.addElement(h);
		      }
		    
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataMesyuarat(){
		 
		  return paparMesyuarat;
	  }
	 public static void setDataPerkaraMinit(int id)throws Exception {
			
		 Db db = null;
		 db = new Db();
		
		 paparMinit.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      
		     
		      SQLRenderer r = new SQLRenderer();
		    
		      sql = "SELECT A.TAJUK_MINIT,B.ID_AGENDAMESYUARAT,A.ID_MINITMESYUARAT" +
		      		" FROM TBLPFDAGENDAMESYUARAT B , TBLPFDMINITMESYUARAT A" +
		      		" WHERE A.ID_MINITMESYUARAT =" + id + " AND A.ID_AGENDAMESYUARAT = B.ID_AGENDAMESYUARAT";
  
       
  
		      Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitmesyuarat",rs.getString("ID_MINITMESYUARAT"));
		    	  h.put("id_Agendamesyuarat", rs.getString("ID_AGENDAMESYUARAT"));
		    	  h.put("tajuk_Minit",rs.getString("TAJUK_MINIT")== null?"":rs.getString("TAJUK_MINIT"));
		    	  paparMinit.addElement(h);
		    	  count ++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitmesyuarat",0);
		    	  h.put("id_Agendamesyuarat", 0);
		    	  h.put("tajuk_Minit","");
		    	  paparMinit.addElement(h);
		      }
		   
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataPerkaraMinit(){
		 
		  return paparMinit;
	  }
	 public static void setDataPara(int id)throws Exception {
			
		 Db db = null;
		 db = new Db();
		
		 paparPara.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 try {
		      
		     
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitmesyuaratpara");
		      r.add("b.id_Minitmesyuarat");
		      r.add("a.para");
		      r.add("a.pihak_Bertindak");
		      r.add("a.maklumbalas");
		      r.add("c.id_Agendamesyuarat");
		    
		      
		      r.add("a.id_Minitmesyuarat", r.unquote("b.id_Minitmesyuarat"));
		      r.add("b.id_Agendamesyuarat",r.unquote("c.id_Agendamesyuarat"));
		      r.add("a.id_Minitmesyuaratpara",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitmesyuaratpara a, Tblpfdminitmesyuarat b, Tblpfdagendamesyuarat c");
			  Statement stmt = db.getStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		    
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitmesyuaratpara",rs.getString("id_Minitmesyuaratpara"));
		    	  h.put("id_Agendamesyuarat", rs.getString("id_Agendamesyuarat"));
		    	  h.put("id_Minitmesyuarat", rs.getString("id_Minitmesyuarat"));
		    	  h.put("para",rs.getString("para")== null?"":rs.getString("para"));
		    	  h.put("pihak_Bertindak",rs.getString("pihak_Bertindak")== null?"":rs.getString("pihak_Bertindak"));
		    	  h.put("maklumbalas",rs.getString("maklumbalas")== null?"":rs.getString("maklumbalas"));
		    	  paparPara.addElement(h);
		    	  count++;
		      }
		      if(count == 0){
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitmesyuaratpara",0);
		    	  h.put("id_Agendamesyuarat",0);
		    	  h.put("id_Minitmesyuarat", 0);
		    	  h.put("para","");
		    	  h.put("pihak_Bertindak","");
		    	  h.put("maklumbalas","");
		    	  paparPara.addElement(h);
		    	  
		      }
		   
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataPara(){
		 
		  return paparPara;
	  }
	 public static void  setListPerkaraMinit(int id)throws Exception {
		    Db db = null;
		    senaraiPerkaraMinit.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitmesyuarat");
		      r.add("b.agenda_Mesyuarat");
		      r.add("a.tajuk_Minit");

		      r.add("a.id_Agendamesyuarat",r.unquote("b.id_Agendamesyuarat"));
		      r.add("a.id_Mesyuarat",id);

		      sql = r.getSQLSelect("Tblpfdminitmesyuarat a, Tblpfdagendamesyuarat b","b.agenda_Mesyuarat asc");
		      ResultSet rs = stmt.executeQuery(sql);
		    
		    
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	
		    	  h.put("id_Minitmesyuarat",rs.getString("id_Minitmesyuarat"));
		    	  h.put("agenda_Mesyuarat", rs.getString("agenda_Mesyuarat")== null?"":rs.getString("agenda_Mesyuarat"));
		    	  h.put("tajuk_Minit", rs.getString("tajuk_Minit")== null?"":rs.getString("tajuk_Minit"));
		    	  
		    	  senaraiPerkaraMinit.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	
		    	  h.put("id_Minitmesyuarat","");
		    	  h.put("agenda_Mesyuarat", "Tiada rekod.");
		    	  h.put("tajuk_Minit", "");
		    	  
		    	  senaraiPerkaraMinit.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListPerkaraMinit(){
			 
		return senaraiPerkaraMinit;
	}
	public static void  setListMinit(int id)throws Exception {
	    Db db = null;
	    senaraiMinit.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_Minitmesyuaratpara");
	      r.add("b.tajuk_Minit");
	      r.add("a.para");
	      r.add("a.pihak_Bertindak");
	      r.add("a.maklumbalas");
	      r.add("c.agenda_Mesyuarat");
	      
	     
	      r.add("a.id_Minitmesyuarat",r.unquote("b.id_Minitmesyuarat"));
	      r.add("b.id_Agendamesyuarat",r.unquote("c.id_Agendamesyuarat"));
	      r.add("a.id_Mesyuarat",id);
	     
	     
	
	      sql = r.getSQLSelect("Tblpfdminitmesyuaratpara a, Tblpfdminitmesyuarat b,Tblpfdagendamesyuarat c");
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  
	    	  h.put("id_Minitmesyuaratpara",rs.getString("id_Minitmesyuaratpara"));
	    	  h.put("agenda_Mesyuarat",rs.getString("agenda_Mesyuarat"));
	    	  h.put("tajuk_Minit",rs.getString("tajuk_Minit"));
	    	  h.put("para", rs.getString("para")== null?"":rs.getString("para"));
	    	  h.put("pihak_Bertindak", rs.getString("pihak_Bertindak")== null?"":rs.getString("pihak_Bertindak"));
	    	  h.put("maklumbalas", rs.getString("maklumbalas")== null?"":rs.getString("maklumbalas"));
	    	  
	    	  senaraiMinit.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  
	    	  h.put("id_Minitmesyuaratpara","");
	    	  h.put("agenda_Mesyuarat","Tiada rekod.");
	    	  h.put("tajuk_Minit","");
	    	  h.put("para", "");
	    	  h.put("pihak_Bertindak", "");
	    	  h.put("maklumbalas", "");
	    	  
	    	  senaraiMinit.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	public static Vector getListMinit(){
			 
		return senaraiMinit;
	}
	public static void  setListSubMinit(int id)throws Exception {
	    Db db = null;
	    senaraiSubMinit.clear();
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_Minitmesyuaratsubpara");
	      r.add("a.id_Minitmesyuaratpara");
	      r.add("a.sub_Para");
	     
	      
	     
	      r.add("a.id_Minitmesyuaratpara",id);
	     
	     
	
	      sql = r.getSQLSelect("Tblpfdminitmesyuaratsubpara a","a.id_Minitmesyuaratsubpara asc");
	      ResultSet rs = stmt.executeQuery(sql);
	    
	      Hashtable h;
	      int bil = 1;

	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  
	    	  h.put("id_Minitmesyuaratsubpara",rs.getString("id_Minitmesyuaratsubpara"));
	    	  h.put("id_Minitmesyuaratpara",rs.getString("id_Minitmesyuaratpara"));
	    	  h.put("sub_Para", rs.getString("sub_Para")== null?"":rs.getString("sub_Para"));
	    	 
	    	  senaraiSubMinit.addElement(h);
	    	  bil++;
	      }
	      
	    } finally {
	      if (db != null) db.close();
	    }
	}
	public static Vector getListSubMinit(){
			 
		return senaraiSubMinit;
	}
	public static void addPerkaraMinit(Hashtable data) throws Exception{
		 	Db db = null;
		    String sql = "";

		    try
		    {	 
	
		    	  long idMinitmesyuarat = DB.getNextID("TBLPFDMINITMESYUARAT_SEQ");
		    	  String agendaMesyuarat = (String)data.get("id_Agendamesyuarat");
			      String tajukMinit = (String)data.get("tajuk_Minit");
			      String idMesyuarat = (String)data.get("idMesyuarat");
			      
			      if (tajukMinit != ""){
		
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Minitmesyuarat",idMinitmesyuarat);
			      r.add("id_Agendamesyuarat",agendaMesyuarat);
			      r.add("tajuk_Minit", tajukMinit);
			      r.add("id_Mesyuarat",idMesyuarat);
			     
			      sql = r.getSQLInsert("tblpfdminitmesyuarat");  
			      stmt.executeUpdate(sql);
			      }
			     
		  
			    } finally {
			      if (db != null) db.close();
			    }

			      
	 }
	 
	 public static void updatePerkaraMinit(Hashtable data) throws Exception{
		 Db db = null;
		    String sql = "";
		  
		    try
		    {
		    	  int idMinitmesyuarat = (Integer)data.get("id_Minitmesyuarat");
		    	  String agendaMesyuarat = (String)data.get("id_Agendamesyuarat");
			      String tajukMinit = (String)data.get("tajuk_Minit");
			      
			      if (tajukMinit != ""){

				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Minitmesyuarat", idMinitmesyuarat);
				  r.add("id_Agendamesyuarat",agendaMesyuarat);
			      r.add("tajuk_Minit", tajukMinit);
				
				  sql = r.getSQLUpdate("tblpfdminitmesyuarat");
			      stmt.executeUpdate(sql);
			      }
			      
			    }
			    finally {
			      if (db != null) db.close();
			    }
		 

	 }
	 public static void addMinitPara(Hashtable data) throws Exception{
		 Db db = null;
		    String sql = "";
		   
		   
		    try
		    {	 
		    	
		    	  long idMinitmesyuaratpara = DB.getNextID("TBLPFDMINITMESYUARATPARA_SEQ");
			      String para = (String)data.get("para");
			      String idMinitmesyuarat1 = (String)data.get("id_Minitmesyuarat");
			      String pihakBertindak = (String)data.get("pihak_Bertindak");
			      String maklumbalas = (String)data.get("maklumbalas");
			      String idMesyuarat = (String)data.get("idMesyuarat");

			      if (para != ""){
		
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Minitmesyuaratpara",idMinitmesyuaratpara);
			      r.add("para",para);
			      r.add("id_Minitmesyuarat", idMinitmesyuarat1);
			      r.add("pihak_Bertindak",pihakBertindak);
			      r.add("maklumbalas", maklumbalas);
			      r.add("id_Mesyuarat",idMesyuarat);
			      
			      sql = r.getSQLInsert("tblpfdminitmesyuaratpara");  
			      stmt.executeUpdate(sql);
			      }
		  
			    } finally {
			      if (db != null) db.close();
			    }

			      
	 }
	 
	 public static void updateMinitPara(Hashtable data) throws Exception{
		 Db db = null;
		    String sql = "";
		  
		    try
		    {
		    	  int idMinitmesyuaratpara = (Integer)data.get("id_Minitmesyuaratpara");
			      String para = (String)data.get("para");
			      String idMinitmesyuarat = (String)data.get("id_Minitmesyuarat");
			      String pihakBertindak = (String)data.get("pihak_Bertindak");
			      String maklumbalas = (String)data.get("maklumbalas");
			      
			      if (para != ""){
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
			      
			      r.update("id_Minitmesyuaratpara",idMinitmesyuaratpara);
			      r.add("para",para);
			      r.add("id_Minitmesyuarat", idMinitmesyuarat);
			      r.add("pihak_Bertindak",pihakBertindak);
			      r.add("maklumbalas", maklumbalas);
			      
			      sql = r.getSQLUpdate("tblpfdminitmesyuaratpara");  
			      stmt.executeUpdate(sql);
			      }
			      
			    }
			    finally {
			      if (db != null) db.close();
			    }
		 

	 }
	 
}

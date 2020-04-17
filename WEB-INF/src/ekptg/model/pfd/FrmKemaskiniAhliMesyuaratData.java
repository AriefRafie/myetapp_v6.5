package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniAhliMesyuaratData {
	private static Vector paparAhli = new Vector();
	
	public static void setDataAhli(int id)throws Exception {
		
		 Db db = null;
		 paparAhli.clear();
		 String sql = "";
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Ahlimesyuarat");
		      r.add("b.id_Pegawai");
		      r.add("c.id_Mesyuarat");
		      r.add("a.emel");
		      r.add("a.nama_Ahlimesyuarat");
		      r.add("a.jawatan");
		      r.add("d.id_Seksyen");
		      r.add("a.agensi_Luar");
		      r.add("a.flag_Pengerusi");
		      
		      r.add("a.id_Pegawai",r.unquote("b.id_Pegawai(+)"));
		      r.add("a.id_Mesyuarat",r.unquote("c.id_Mesyuarat"));
		      r.add("a.id_Seksyen",r.unquote("d.id_Seksyen(+)"));
		      		
		      r.add("a.id_Ahlimesyuarat",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdahlimesyuarat a, Tblrujpegawai b, Tblpfdmesyuarat c, Tblrujseksyen d");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Ahlimesyuarat",rs.getString("id_Ahlimesyuarat"));
		    	  h.put("id_Pegawai", rs.getString("id_Pegawai")== null?0:rs.getString("id_Pegawai"));
		    	  h.put("id_Mesyuarat",rs.getString("id_Mesyuarat"));
		    	  h.put("emel",rs.getString("emel")== null?"":rs.getString("emel"));
		    	  h.put("nama_Ahlimesyuarat", rs.getString("nama_Ahlimesyuarat")== null?"":rs.getString("nama_Ahlimesyuarat"));
		    	  h.put("jawatan",rs.getString("jawatan")== null?"":rs.getString("jawatan"));
		    	  h.put("id_Seksyen",rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("agensi_Luar", rs.getString("agensi_Luar")== null?"":rs.getString("agensi_Luar"));
		    	  h.put("flag_Pengerusi",rs.getString("flag_Pengerusi")== null?"":rs.getString("flag_Pengerusi"));
		    	  
		    	  paparAhli.addElement(h);
		    	  count++;
		    	  System.out.println(count);
		    	  
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("id_Ahlimesyuarat",0);
		    	  h.put("id_Pegawai",0);
		    	  h.put("id_Mesyuarat",0);
		    	  h.put("emel","");
		    	  h.put("nama_Ahlimesyuarat", "");
		    	  h.put("jawatan","");
		    	  h.put("id_Seksyen","");
		    	  h.put("agensi_Luar", "");
		    	  h.put("flag_Pengerusi","");
		    	  paparAhli.addElement(h);
		    	  
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataAhli(){
		 
		  return paparAhli;
	  }
	 public static int  add(Hashtable data)throws Exception {
			
			Db db = null;
		    String sql = "";
		   
		    try
		    {	 
		    	  long idAhlimesyuarat = DB.getNextID("TBLPFDAHLIMESYUARAT_SEQ");
		    	  String idMesyuarat = (String)data.get("id_Mesyuarat");
			      String idPegawai = (String)data.get("id_Pegawai");
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String namaAhliMsyrt = (String)data.get("nama_Ahlimesyuarat");
			      String agensi = (String)data.get("agensi_Luar");
			      String jawatan = (String)data.get("jawatan");
			      String emel = (String)data.get("emel");
			      String pengerusi = (String)data.get("pengerusi");
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Ahlimesyuarat",idAhlimesyuarat);
			      r.add("id_Mesyuarat",idMesyuarat);
			      r.add("id_Pegawai", idPegawai);
			      r.add("id_Seksyen", idSeksyen);
			      r.add("nama_Ahlimesyuarat",namaAhliMsyrt);
			      r.add("agensi_Luar",agensi);
			      r.add("jawatan", jawatan);
			      r.add("emel", emel);
			      r.add("flag_Pengerusi", pengerusi);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			      
			      sql = r.getSQLInsert("tblpfdahlimesyuarat");  
			      stmt.executeUpdate(sql);
			      return (int)idAhlimesyuarat;
			    } finally {
			      if (db != null) db.close();
			    }

		}
		public static int update(Hashtable data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
		    	  int idAhlimesyuarat = (Integer)data.get("id_Ahlimesyuarat");
			      String idPegawai = (String)data.get("id_Pegawai");
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String namaAhliMsyrt = (String)data.get("nama_Ahlimesyuarat");
			      String agensi = (String)data.get("agensi_Luar");
			      String jawatan = (String)data.get("jawatan");
			      String emel = (String)data.get("emel");
			      String pengerusi = (String)data.get("pengerusi");
				  
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Ahlimesyuarat", idAhlimesyuarat);
			      r.add("id_Pegawai", idPegawai);
			      r.add("id_Seksyen", idSeksyen);
			      r.add("nama_Ahlimesyuarat",namaAhliMsyrt);
			      r.add("agensi_Luar",agensi);
			      r.add("jawatan", jawatan);
			      r.add("emel", emel);
			      r.add("flag_Pengerusi", pengerusi);
				
				  sql = r.getSQLUpdate("tblpfdahlimesyuarat");
				  
			      stmt.executeUpdate(sql);
			      return idAhlimesyuarat;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		    }

}

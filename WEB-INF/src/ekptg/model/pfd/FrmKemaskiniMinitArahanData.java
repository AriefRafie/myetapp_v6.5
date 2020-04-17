package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniMinitArahanData {
	
	private static Vector list = new Vector();	
	public static int  add(Hashtable data)throws Exception {
		
		Db db = null;
	    String sql = "";
	   
	    
	    Date now = new Date();
	    try
	    {	 
	    	  long idMinitarahan = DB.getNextID("TBLPFDMINITARAHAN_SEQ");
	    	  String idDokumen = (String)data.get("id_Dokumen");
	    	  String minitArahan = (String)data.get("minit_Arahan");
		      String pegawaiMengarah = (String)data.get("id_Pegawai_Ygmengarah");
		      String pegawaiMenerima = (String)data.get("id_Pegawai_Ygmenerima");
		      String statusTindakan = (String)data.get("status_Tindakan");
		      String tkhMinitArahan = (String)data.get("tarikh_Minit_Arahan");
			  String tarikhMinitArahan = "to_date('" + tkhMinitArahan + "','dd/MM/yyyy')";

		      
		      
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("id_Minitarahan",idMinitarahan);
		      r.add("id_Dokumen",idDokumen);
		      r.add("minit_Arahan", minitArahan);
		      r.add("id_Pegawai_Ygmengarah", pegawaiMengarah);
		      r.add("id_Pegawai_Ygmenerima", pegawaiMenerima);
		      r.add("status_Tindakan", statusTindakan);
		      r.add("tarikh_Minit_Arahan",r.unquote(tarikhMinitArahan)); 
		      
		      sql = r.getSQLInsert("tblpfdminitarahan");
		      
		     
		      stmt.executeUpdate(sql);
		      return (int)idMinitarahan;
		    } finally {
		      if (db != null) db.close();
		    }

	}
	public static int update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	    try
	    {
	    	  int idMinitarahan = (Integer)data.get("id_Minitarahan");
	    	  String minitArahan = (String)data.get("minit_Arahan");
		      String pegawaiMengarah = (String)data.get("id_Pegawai_Ygmengarah");
		      String pegawaiMenerima = (String)data.get("id_Pegawai_Ygmenerima");
		      String statusTindakan = (String)data.get("status_Tindakan");
		      String tkhMinitArahan = (String)data.get("tarikh_Minit_Arahan");
			  String tarikhMinitArahan = "to_date('" + tkhMinitArahan + "','dd/MM/yyyy')";
			  
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_Minitarahan", idMinitarahan);
			  r.add("minit_Arahan", minitArahan);
		      r.add("id_Pegawai_Ygmengarah", pegawaiMengarah);
		      r.add("id_Pegawai_Ygmenerima", pegawaiMenerima);
		      r.add("status_Tindakan", statusTindakan);
		      r.add("tarikh_Minit_Arahan",r.unquote(tarikhMinitArahan)); 
			
			  sql = r.getSQLUpdate("tblpfdminitarahan");
		      stmt.executeUpdate(sql);
		      return idMinitarahan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	    }
	

	public static void setData(int id)throws Exception {
		
		 Db db = null;
		 list.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Minitarahan");
		      r.add("a.minit_Arahan");
		      r.add("b.id_Pegawai as PegawaiA");
		      r.add("c.id_Pegawai as PegawaiB");
		      r.add("a.status_Tindakan");
		      r.add("a.tarikh_Minit_Arahan");
		      
		      r.add("a.id_Pegawai_Ygmengarah",r.unquote("b.id_Pegawai"));
		      r.add("a.id_Pegawai_Ygmenerima",r.unquote("c.id_Pegawai"));
		      r.add("a.id_Minitarahan",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdminitarahan a, Tblrujpegawai b, Tblrujpegawai c");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitarahan", rs.getString("id_Minitarahan"));
		    	  h.put("minit_Arahan", rs.getString("minit_Arahan"));
		    	  h.put("pegawai_Mengarah",rs.getString("PegawaiA")== null?"":rs.getString("PegawaiA"));
		    	  h.put("pegawai_Menerima",rs.getString("PegawaiB")== null?"":rs.getString("PegawaiB"));
		    	  h.put("tarikh_Minit_Arahan", rs.getDate("tarikh_Minit_Arahan")== null?"":sdf.format(rs.getDate("tarikh_Minit_Arahan")));
		    	  h.put("status_Tindakan", rs.getString("status_Tindakan")== null?0:rs.getString("status_Tindakan"));
		    	  list.addElement(h);
		    	  count++;
		    	  
		    	  
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Minitarahan", 0);
		    	  h.put("minit_Arahan", "");
		    	  h.put("pegawai_Mengarah",0);
		    	  h.put("pegawai_Menerima",0);
		    	  h.put("tarikh_Minit_Arahan", "");
		    	  h.put("status_Tindakan", 0);
		    	  list.addElement(h);
		    	 
		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getData(){
		 
		  return list;
	  }


}

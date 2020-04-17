package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniMesyuaratDataRutin {
	
	private static Vector paparMesyuarat = new Vector();
	private static Vector senaraiAhliMesyuarat = new Vector();
	private static Vector senaraiAgendaMesyuarat = new Vector();
	public static void setData(String id)throws Exception {
		
		 Db db = null;
		 paparMesyuarat.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Mesyuarat");
		      r.add("a.bil_Mesyuarat");
		      r.add("a.tajuk_Mesyuarat");
		      r.add("a.kategori_Mesyuarat");
		      r.add("a.tarikh_Mesyuarat");
		      r.add("a.masa_Mesyuarat_Dari");
		      r.add("a.waktu_Mesyuarat_Dari");
		      r.add("a.masa_Mesyuarat_Hingga");
		      r.add("a.waktu_Mesyuarat_Hingga");
		      r.add("a.catatan");
		      r.add("b.id_Seksyen");
		      r.add("c.id_Status");
		      r.add("d.id_Lokasi");
		      r.add("e.id_Fail");
		      r.add("f.id_Pegawai");
		      		      
		      r.add("a.id_Seksyen",r.unquote("b.id_Seksyen"));
		      r.add("a.id_Status",r.unquote("c.id_Status"));
		      r.add("a.id_Lokasi",r.unquote("d.id_Lokasi"));
		      r.add("a.id_Fail",r.unquote("e.id_Fail(+)"));
		      r.add("a.id_Pegawai",r.unquote("f.id_Pegawai"));
		     
		      
		      r.add("a.id_Mesyuarat",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdmesyuarat a, Tblrujseksyen b,Tblrujstatus c, Tblpfdrujlokasimesyuarat d, Tblpfdfail e, Tblrujpegawai f");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("papar Mesyuarat--"+sql);
		      
		      Hashtable h;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("id_Mesyuarat", rs.getString("id_Mesyuarat"));
		    	  h.put("bil_Mesyuarat", rs.getString("bil_Mesyuarat"));
		    	  h.put("tajuk_Mesyuarat",rs.getString("tajuk_Mesyuarat"));
		    	  h.put("kategori_Mesyuarat",rs.getString("kategori_Mesyuarat"));
		    	  h.put("tarikh_Mesyuarat",rs.getDate("tarikh_Mesyuarat")== null?"":sdf.format(rs.getDate("tarikh_Mesyuarat")));
		    	  h.put("masa_Mesyuarat_Dari",rs.getString("masa_Mesyuarat_Dari")== null?"":rs.getString("masa_Mesyuarat_Dari"));
		    	  h.put("waktu_Mesyuarat_Dari",rs.getString("waktu_Mesyuarat_Dari")== null?"":rs.getString("waktu_Mesyuarat_Dari"));
		    	  h.put("masa_Mesyuarat_Hingga",rs.getString("masa_Mesyuarat_Hingga")== null?"":rs.getString("masa_Mesyuarat_Hingga"));
		    	  h.put("waktu_Mesyuarat_Hingga",rs.getString("waktu_Mesyuarat_Hingga")== null?"":rs.getString("waktu_Mesyuarat_Hingga"));
		    	  h.put("id_Seksyen", rs.getString("id_Seksyen")== null?0:rs.getString("id_Seksyen"));
		    	  h.put("id_Status", rs.getString("id_Status")== null?0:rs.getString("id_Status"));
		    	  h.put("id_Pegawai",rs.getString("id_Pegawai")== null?0:rs.getString("id_Pegawai"));
		    	  h.put("id_Lokasi",rs.getString("id_Lokasi")== null?0:rs.getString("id_Lokasi"));
		    	  h.put("id_Fail",rs.getString("id_Fail")== null?0:rs.getString("id_Fail"));
		    	  h.put("catatan",rs.getString("catatan")== null?"":rs.getString("catatan"));

		    	  paparMesyuarat.addElement(h);
		    	  count++;
		      }
		     if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("id_Mesyuarat", 0);
		    	  h.put("bil_Mesyuarat", "");
		    	  h.put("tajuk_Mesyuarat","");
		    	  h.put("kategori_Mesyuarat",0);
		    	  h.put("tarikh_Mesyuarat","");
		    	  h.put("masa_Mesyuarat_Dari","");
		    	  h.put("waktu_Mesyuarat_Dari",0);
		    	  h.put("masa_Mesyuarat_Hingga","");
		    	  h.put("waktu_Mesyuarat_Hingga",0);
		    	  h.put("id_Seksyen", 0);
		    	  h.put("id_Status", 0);
		    	  h.put("id_Pegawai",0);
		    	  h.put("id_Lokasi",0);
		    	  h.put("id_Fail",0);
		    	  h.put("catatan","");
		    	  paparMesyuarat.addElement(h);

		     }
		      
		 }finally {
		      if (db != null) db.close();
		    }  
	}
	 public static Vector getData(){
		 
		  return paparMesyuarat;
	  }
	 public static String add(Hashtable data) throws Exception{
		 Db db = null;
		    String sql = "";
		   
		    try
		    {	 
		    	  long idMesyuarat = DB.getNextID("TBLPFDMESYUARAT_SEQ");
		    	  String bilMesyuarat = (String)data.get("bil_Mesyuarat");
			      String tajukMesyuarat = (String)data.get("tajuk_Mesyuarat");
			      String kategoriMesyuarat = (String)data.get("kategori_Mesyuarat");
			      String tarikhMesyuarat = (String)data.get("tarikh_Mesyuarat");
			      String tkhMsyrt = "to_date('" + tarikhMesyuarat + "','dd/MM/yyyy')";
			      String masaMesyuaratDari = (String)data.get("masa_Mesyuarat_Dari");
			      String waktuMesyuaratDari = (String)data.get("waktu_Mesyuarat_Dari");
			      String masaMesyuaratHingga = (String)data.get("masa_Mesyuarat_Hingga");
			      String waktuMesyuaratHingga = (String)data.get("waktu_Mesyuarat_Hingga");
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String idStatus = (String)data.get("id_Status");
			      String idPegawai = (String)data.get("id_Pegawai");
			      String idLokasi = (String)data.get("id_Lokasi");
			      String idFail = (String)data.get("id_Fail");
			      String catatan = (String)data.get("catatan");
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Mesyuarat",idMesyuarat);
			      r.add("bil_Mesyuarat",bilMesyuarat);
			      r.add("tajuk_Mesyuarat", tajukMesyuarat);
			      r.add("kategori_Mesyuarat", kategoriMesyuarat);
			      r.add("tarikh_Mesyuarat", r.unquote(tkhMsyrt));
			      r.add("masa_Mesyuarat_Dari", masaMesyuaratDari);
			      r.add("waktu_Mesyuarat_Dari", waktuMesyuaratDari);
			      r.add("masa_Mesyuarat_Hingga", masaMesyuaratHingga);
			      r.add("waktu_Mesyuarat_Hingga", waktuMesyuaratHingga);
			      r.add("id_Seksyen", idSeksyen);
			      r.add("id_Status",idStatus); 
			      r.add("id_Pegawai",idPegawai); 
			      r.add("id_Lokasi", idLokasi);
			      r.add("id_Fail",idFail); 
			      r.add("catatan",catatan); 
			      
			      sql = r.getSQLInsert("tblpfdmesyuarat");  
			      stmt.executeUpdate(sql);
			      return ""+idMesyuarat;
			    } finally {
			      if (db != null) db.close();
			    }

			      
	 }
	 
	 public static String update(Hashtable data) throws Exception{
		 Db db = null;
		    String sql = "";
		    try
		    {
		    	  String idMesyuarat = (String)data.get("id_Mesyuarat");
		    	  String bilMesyuarat = (String)data.get("bil_Mesyuarat");
			      String tajukMesyuarat = (String)data.get("tajuk_Mesyuarat");
			      String kategoriMesyuarat = (String)data.get("kategori_Mesyuarat");
			      String tarikhMesyuarat = (String)data.get("tarikh_Mesyuarat");
			      String tkhMsyrt = "to_date('" + tarikhMesyuarat + "','dd/MM/yyyy')";
			      String masaMesyuaratDari = (String)data.get("masa_Mesyuarat_Dari");
			      String waktuMesyuaratDari = (String)data.get("waktu_Mesyuarat_Dari");
			      String masaMesyuaratHingga = (String)data.get("masa_Mesyuarat_Hingga");
			      String waktuMesyuaratHingga = (String)data.get("waktu_Mesyuarat_Hingga");
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String idStatus = (String)data.get("id_Status");
			      String idPegawai = (String)data.get("id_Pegawai");
			      String idLokasi = (String)data.get("id_Lokasi");
			      String idFail = (String)data.get("id_Fail");
			      String catatan = (String)data.get("catatan");
				  
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Mesyuarat", idMesyuarat);
				  r.add("bil_Mesyuarat",bilMesyuarat);
			      r.add("tajuk_Mesyuarat", tajukMesyuarat);
			      r.add("kategori_Mesyuarat", kategoriMesyuarat);
			      r.add("tarikh_Mesyuarat", r.unquote(tkhMsyrt));
			      r.add("masa_Mesyuarat_Dari", masaMesyuaratDari);
			      r.add("waktu_Mesyuarat_Dari", waktuMesyuaratDari);
			      r.add("masa_Mesyuarat_Hingga", masaMesyuaratHingga);
			      r.add("waktu_Mesyuarat_Hingga", waktuMesyuaratHingga);
			      r.add("id_Seksyen", idSeksyen);
			      r.add("id_Status",idStatus); 
			      r.add("id_Pegawai",idPegawai); 
			      r.add("id_Lokasi", idLokasi);
			      r.add("id_Fail",idFail); 
			      r.add("catatan",catatan); 
				
				  sql = r.getSQLUpdate("tblpfdmesyuarat");
			      stmt.executeUpdate(sql);
			      return idMesyuarat;
			    }
			    finally {
			      if (db != null) db.close();
			    }
		 

	 }
	 public static void  setListAhliMesyuarat(int id)throws Exception {
		    Db db = null;
		    senaraiAhliMesyuarat.clear();
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Ahlimesyuarat");
		      r.add("b.nama_Pegawai");
		      r.add("c.kod_Seksyen");
		      r.add("a.nama_Ahlimesyuarat");
		      r.add("a.agensi_Luar");
		     
		      
		      r.add("a.id_Pegawai",r.unquote("b.id_Pegawai(+)"));
		      r.add("a.id_Seksyen",r.unquote("c.id_Seksyen(+)"));
		      r.add("a.id_Mesyuarat",id);
		     
		     
		
		      sql = r.getSQLSelect("Tblpfdahlimesyuarat a, Tblrujpegawai b, Tblrujseksyen c","a.id_Ahlimesyuarat asc");
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	
		    	  h.put("id_Ahlimesyuarat",rs.getString("id_Ahlimesyuarat"));
		    	  h.put("nama_Pegawai", rs.getString("nama_Pegawai")== null?"":rs.getString("nama_Pegawai"));
		    	  h.put("kod_Seksyen", rs.getString("kod_Seksyen")== null?"":rs.getString("kod_Seksyen"));
		    	  h.put("nama_Ahlimesyuarat",rs.getString("nama_Ahlimesyuarat")== null?"":rs.getString("nama_Ahlimesyuarat"));
		    	  h.put("agensi_Luar", rs.getString("agensi_Luar")== null?"":rs.getString("agensi_Luar"));
		    	  
		    	  senaraiAhliMesyuarat.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	
		    	  h.put("id_Ahlimesyuarat","");
		    	  h.put("nama_Pegawai", "Tiada rekod.");
		    	  h.put("kod_Seksyen", "");
		    	  h.put("nama_Ahlimesyuarat","");
		    	  h.put("agensi_Luar", "");
		    	  
		    	  senaraiAhliMesyuarat.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListAhliMesyuarat(){
			 
		return senaraiAhliMesyuarat;
	}
	public static void  setListAgendaMesyuarat(int id)throws Exception {
	    Db db = null;
	    senaraiAgendaMesyuarat.clear();
	    String sql = "";
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      r.add("a.id_Agendamesyuarat");
	      r.add("a.agenda_Mesyuarat");
	
	      r.add("a.id_Mesyuarat",id);
	      r.add("a.id_Mesyuarat",r.unquote("b.id_Mesyuarat"));
	      
	      sql = r.getSQLSelect("Tblpfdagendamesyuarat a, Tblpfdmesyuarat b","a.id_Agendamesyuarat asc");
	      ResultSet rs = stmt.executeQuery(sql);
	     
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_Agendamesyuarat",rs.getString("id_Agendamesyuarat"));
	    	  h.put("agenda_Mesyuarat", rs.getString("agenda_Mesyuarat")== null?"":rs.getString("agenda_Mesyuarat"));
	    	  
	    	  senaraiAgendaMesyuarat.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	       if (count == 0){
	    	   h = new Hashtable();
		       h.put("bil", "");
		       h.put("id_Agendamesyuarat","");
		       h.put("agenda_Mesyuarat", "Tiada rekod.");
		       senaraiAgendaMesyuarat.addElement(h);
	       }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	public static Vector getListAgendaMesyuarat(){
			 
		return senaraiAgendaMesyuarat;
	}

}

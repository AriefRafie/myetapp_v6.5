package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniPergerakanFailData {
	
	private static Vector paparFail = new Vector();
	private static Vector paparTarikhPinjamDari = new Vector();
	private static Vector paparTarikhPinjamHingga = new Vector();
	private static Vector paparNoFail = new Vector();
	private static Vector paparPergerakanFail = new Vector();
	private static Vector senaraiPergerakanFail = new Vector();
	private static Vector senaraiPergerakanPemohonFailSelesai = new Vector();
	private static Vector senaraiPergerakanFailUser = new Vector();
	private static Vector senaraiPergerakanPemohonFail = new Vector();
	private static Vector senaraiPTFail = new Vector();
	private static Vector paparLatestPergerakanPegawai = null;
	
	
	public static void setDataFail(String idFail)throws Exception {
		
		 Db db = null;
		 paparFail.clear();
		 String sql = "";
		 
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Fail");
		      r.add("a.no_Fail");
		      r.add("a.tajuk_Fail");
		      r.add("b.keterangan");
		      r.add("d.keterangan as status_pergerakan");
		      r.add("e.user_Name");
		      
		      r.add("a.id_Fail",idFail);
		      r.add("a.id_Status",r.unquote("b.id_Status(+)"));
		      r.add("a.id_Fail",r.unquote("c.id_Fail(+)"));
		      r.add("c.id_Status",r.unquote("d.id_Status(+)"));
		      r.add("a.id_Masuk",r.unquote("e.user_Id(+)"));
		     
		    
		      sql = r.getSQLSelect("Tblpfdfail a, Tblrujstatus b, Tblpfdpergerakanfail c, Tblrujstatus d, users e");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("idFail",rs.getString("id_Fail"));
		    	  h.put("noFail", rs.getString("no_Fail"));
		    	  h.put("tajukFail",rs.getString("tajuk_Fail")== null?"":rs.getString("tajuk_Fail"));
		    	  h.put("keterangan1",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
		    	  h.put("keterangan2",rs.getString("status_pergerakan")== null?"":rs.getString("status_pergerakan"));
		    	  h.put("user_Name", rs.getString("user_Name") == null?"":rs.getString("user_Name"));
		    	  paparFail.addElement(h);
		    	  
		    	  
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataFail(){
		 
		  return paparFail;
	  }
	 public static void setNoFail(int idFail)throws Exception {
			
		 Db db = null;
		 paparNoFail.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 
		 try {
			 
			 sql = "SELECT a.id_Fail, a.tarikh_Masuk, b.id_Fail as Fail, b.tarikh_Fail_Masuk, b.id_Status" +
			 		" FROM Tblpfdfail a, Tblpfdpergerakanfail b" +
//			 		" WHERE a.id_Fail = b.id_Fail(+)" +
//			 		" AND a.id_Fail = " + id;
			 		" WHERE a.id_Fail = " + idFail +
			 		" AND b.id_Status is null  AND a.id_Fail = b.id_Fail(+)";
			 
			 
			 
			 
		      db = new Db();
		      Statement stmt = db.getStatement();
		      System.out.println("1--"+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      
//		      if (rs.getString("Fail")!= null){
//		    	  sql = sql + " AND b.id_Status is null";
//		      }
//		      
		      int count = 0;
		      Hashtable h;
		      
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  
		    	  h.put("idFail1",rs.getString("id_Fail"));
		    	  h.put("idFail2",rs.getString("Fail")== null?"":rs.getString("Fail"));
		    	  h.put("tarikh_Fail_Masuk",rs.getDate("tarikh_Fail_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Fail_Masuk")));
		    	  h.put("tarikh_Masuk",rs.getDate("tarikh_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Masuk")));
		    	  paparNoFail.addElement(h);
		    	  count++;
		      }
//		      if (count == 0){
//		    	  sql = "SELECT a.id_Fail, a.tarikh_Masuk, b.id_Fail as Fail, b.tarikh_Fail_Masuk, b.id_Status" +
//			 		" FROM Tblpfdfail a, Tblpfdpergerakanfail b" +
//			 		" WHERE a.id_Fail = " + id +
//			 		" AND b.id_Status is not null  AND a.id_Fail = b.id_Fail(+)";
//		    	  
//		    	  Statement stmt1 = db.getStatement();
//			      System.out.println("2--" +sql);
//			      ResultSet rs1 = stmt.executeQuery(sql);
//			      
//			      while (rs1.next()) {
//			    	  h = new Hashtable();
//			    	  
//			    	  h.put("idFail1",rs.getString("id_Fail"));
//			    	  h.put("idFail2",rs.getString("Fail")== null?"":rs.getString("Fail"));
//			    	  h.put("tarikh_Fail_Masuk",rs.getDate("tarikh_Fail_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Fail_Masuk")));
//			    	  h.put("tarikh_Masuk",rs.getDate("tarikh_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Masuk")));
//			    	  paparNoFail.addElement(h);
//			    	 
//			      }
			      
		    	  
//		      }
		     
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getNoFail(){
		 
		  return paparNoFail;
	  }
	 public static void setDataPergerakanFail(int id)throws Exception {
			
		 Db db = null;
		 paparPergerakanFail.clear();
		 String sql = "";
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Pergerakanfail");
		      r.add("b.id_Pegawai as penghantar");
		      r.add("c.id_Pegawai as penerima");
		      r.add("a.tarikh_Fail_Masuk");
		      r.add("a.tarikh_Fail_Keluar");
		      r.add("a.tempoh_Sdp_Dari");
		      r.add("a.tempoh_Sdp_Hingga");
		      r.add("a.catatan");
		     
		     
		      
		      r.add("a.id_Pegawaipenerima",r.unquote("b.id_Pegawai"));
		      r.add("a.id_Pegawaipenghantar",r.unquote("c.id_Pegawai"));
		      r.add("a.id_Pergerakanfail",id);
		     
		    
		      sql = r.getSQLSelect("Tblpfdpergerakanfail a, Tblrujpegawai b,Tblrujpegawai c");
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		       
		      while (rs.next()) {
		    	  h.put("id_Pergerakanfail",rs.getString("id_Pergerakanfail"));
		    	  h.put("id_Pegawaipenghantar", rs.getString("penghantar"));
		    	  h.put("id_Pegawaipenerima",rs.getString("penerima"));
		    	  h.put("tarikh_Fail_Masuk", rs.getDate("tarikh_Fail_Masuk")== null?"":sdf.format(rs.getDate("tarikh_Fail_Masuk")));
		    	  h.put("tarikh_Fail_Keluar",rs.getDate("tarikh_Fail_Keluar")== null?"":sdf.format(rs.getDate("tarikh_Fail_Keluar")));
		    	  h.put("tempoh_Sdp_Dari",rs.getDate("tempoh_Sdp_Dari")== null?"":sdf.format(rs.getDate("tempoh_Sdp_Dari")));
		    	  h.put("tempoh_Sdp_Hingga",rs.getDate("tempoh_Sdp_Hingga")== null?"":sdf.format(rs.getDate("tempoh_Sdp_Hingga")));
		    	  h.put("catatan",rs.getString("catatan")== null?"":rs.getString("catatan"));
		    	  
		    	  
		    	  paparPergerakanFail.addElement(h); 
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		 
		 
	}
	 public static Vector getDataPergerakanFail(){
		 
		  return paparPergerakanFail;
	  }
	 public static void  setListPergerakanFail(int id)throws Exception {
		    Db db = null;
		    senaraiPergerakanFail.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      sql = "SELECT A.ID_PERGERAKANFAIL, E.NO_FAIL, C.NAMA_PEGAWAI AS PEGAWAIPENGHANTAR, D.NAMA_PEGAWAI AS PEGAWAIPENERIMA, A.TARIKH_FAIL_MASUK, A.TARIKH_FAIL_KELUAR, A.NAMA_KERANI_FAIL " +
				  "FROM TBLPFDPERGERAKANFAIL A, TBLRUJPEGAWAI C, TBLRUJPEGAWAI D, TBLPFDFAIL E " +
				  "WHERE A.ID_FAIL = E.ID_FAIL(+) " +
				  "AND A.ID_PEGAWAIPENERIMA = D.ID_PEGAWAI " +
				  "AND A.ID_PEGAWAIPENGHANTAR = C.ID_PEGAWAI " +
				  "AND A.ID_FAIL='"+id+"'";
		      sql = sql + "ORDER BY ID_PERGERAKANFAIL DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idPergerakanfail",rs.getString(1));
		    	  h.put("no_Fail",rs.getString(2));
		    	  h.put("nama_Pegawai1", rs.getString(3));
		    	  h.put("nama_Pegawai2",rs.getString(4));
		    	  h.put("tarikh_Fail_Masuk", rs.getDate(5)== null?"":sdf.format(rs.getDate(5)));
		    	  h.put("tarikh_Fail_Keluar",rs.getDate(6)== null?"":sdf.format(rs.getDate(6)));
		    	  h.put("didaftar_Oleh",rs.getString(7));

		    	  senaraiPergerakanFail.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "");
		    	
		    	  h.put("idPergerakanfail","");
		    	  h.put("no_Fail", "Tiada rekod.");
		    	  h.put("nama_Pegawai1", "");
		    	  h.put("nama_Pegawai2","");
		    	  h.put("tarikh_Fail_Masuk","");
		    	  h.put("tarikh_Fail_Keluar","");
		    	  h.put("didaftar_Oleh","");

		    	  senaraiPergerakanFail.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListPergerakanFail(){
			 
		return senaraiPergerakanFail;
	}
	public static String  add(Hashtable data)throws Exception {
			
		 Db db = new Db();
		    String sql = "";
		    try
		    {
		    	
		    	  long idPergerakanfail = DB.getNextID("TBLPFDPERGERAKANFAIL_SEQ");
		    	  String nama_pegawai_pinjamfail = (String)data.get("nama_pegawai_pinjamfail");
		    	  String id_pegawai_pinjamfail = (String)data.get("id_pegawai_pinjamfail");
		    	  String id_pegawai_PTfail = (String)data.get("id_pegawai_PTfail");
		    	  //String namaPenghantar = (String) data.get("namaPenghantar");
			      String tempoh_Pinjam_Dari = (String)data.get("tempoh_Pinjam_Dari");
			      String tempoh_Pinjam_Hingga = (String)data.get("tempoh_Pinjam_Hingga");
			      String tujuan = (String)data.get("tujuan");
			      String status_pinjam = (String)data.get("status_pinjaman_fail");
			      String idFail = (String)data.get("id_Fail");
			      String idMasuk = (String)data.get("id_Masuk");
			    			      
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("ID_PERGERAKANFAIL", idPergerakanfail);
			      r.add("NAMA_PEGAWAI_PINJAMFAIL", nama_pegawai_pinjamfail);
			      r.add("ID_PEGAWAI_PINJAMFAIL", id_pegawai_pinjamfail);
			      r.add("ID_PT_FAIL", id_pegawai_PTfail);
			      r.add("TEMPOH_PINJAM_DARI", r.unquote("to_date('" + tempoh_Pinjam_Dari + "', 'dd/MM/yyyy')"));
			      r.add("TEMPOH_PINJAM_HINGGA", r.unquote("to_date('" + tempoh_Pinjam_Hingga + "', 'dd/MM/yyyy')"));
			      r.add("TUJUAN_PINJAMAN", tujuan);
			      r.add("STATUS_PINJAMAN_FAIL", status_pinjam);
			      r.add("ID_FAIL", idFail);
			      r.add("id_Masuk",idMasuk);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			  
			      sql = r.getSQLInsert("TBLPFDPERGERAKANFAIL");
			      
			      stmt.executeUpdate(sql);
			      
			      return "" +idPergerakanfail;
			      
			    } finally {
			      if (db != null) db.close();
			    }
	
		}
	
	public static String update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
		String idPergerakanfail;
	   
	    try
	    {
	    	  idPergerakanfail = (String)data.get("idPergerakanfail");
	    	  String nama_pegawai_pinjamfail = (String)data.get("nama_pegawai_pinjamfail");
	    	  String id_pegawai_pinjamfail = (String)data.get("id_pegawai_pinjamfail");
	    	  String id_pegawai_PTfail = (String)data.get("id_pegawai_PTfail");
	    	  //String namaPenghantar = (String) data.get("namaPenghantar");
		      String tempoh_Pinjam_Dari = (String)data.get("tempoh_Pinjam_Dari");
		      String tempoh_Pinjam_Hingga = (String)data.get("tempoh_Pinjam_Hingga");
		      String tujuan = (String)data.get("tujuan");
		      String status_pinjam = (String)data.get("status_pinjaman_fail");
		      String idFail = (String)data.get("id_Fail");
		      String idMasuk = (String)data.get("id_Masuk");
		    	
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.update("ID_PERGERAKANFAIL", idPergerakanfail);
		      r.add("NAMA_PEGAWAI_PINJAMFAIL", nama_pegawai_pinjamfail);
		      r.add("ID_PEGAWAI_PINJAMFAIL", id_pegawai_pinjamfail);
		      r.add("ID_PT_FAIL", id_pegawai_PTfail);
		      r.add("TEMPOH_PINJAM_DARI", r.unquote("to_date('" + tempoh_Pinjam_Dari + "', 'dd/MM/yyyy')"));
		      r.add("TEMPOH_PINJAM_HINGGA", r.unquote("to_date('" + tempoh_Pinjam_Hingga + "', 'dd/MM/yyyy')"));
		      r.add("TUJUAN_PINJAMAN", tujuan);
		      r.add("STATUS_PINJAMAN_FAIL", status_pinjam);
		      r.add("ID_FAIL", idFail);
		      r.add("id_Masuk",idMasuk);
		      r.add("tarikh_Masuk",r.unquote("sysdate")); 
		  
		      sql = r.getSQLUpdate("TBLPFDPERGERAKANFAIL");
		      
		      stmt.executeUpdate(sql);
		      
		      return "" +idPergerakanfail;
		      
		    } finally {
		      if (db != null) db.close();
		    }

    }
	
	public static Vector paparPergerakanFail(String idPergerakanfail) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");		
		String sql = "";
		Db db = null;
		
		try{
			Vector paparPergerakanFail = new Vector();
			paparPergerakanFail.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.ID_PERGERAKANFAIL, A.NAMA_KERANI_FAIL, A.ID_PEGAWAIPENERIMA, A.ID_PEGAWAIPENGHANTAR, A.TARIKH_FAIL_MASUK, A.TARIKH_FAIL_KELUAR, A.TEMPOH_SDP_DARI, A.TEMPOH_SDP_HINGGA, A.CATATAN " +
				  "FROM TBLPFDPERGERAKANFAILALL A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C " +
				  "WHERE A.ID_PEGAWAIPENERIMA = B.ID_PEGAWAI(+) " +
				  "AND A.ID_PEGAWAIPENGHANTAR = C.ID_PEGAWAI(+) " +
				  "AND A.ID_PERGERAKANFAIL='"+idPergerakanfail+"'";
			
			sql = sql + " ORDER BY A.ID_PERGERAKANFAIL DESC";
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idPergerakanfail",rs.getString("ID_PERGERAKANFAIL")==null?"":rs.getString("ID_PERGERAKANFAIL"));
				h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("namaKerani",rs.getString("NAMA_KERANI_FAIL")==null?"":rs.getString("NAMA_KERANI_FAIL"));
				h.put("idPenerima",rs.getString("ID_PEGAWAIPENERIMA")==null?"":rs.getString("ID_PEGAWAIPENERIMA"));
				h.put("idPenghantar",rs.getString("ID_PEGAWAIPENGHANTAR")==null?"":rs.getString("ID_PEGAWAIPENGHANTAR"));
				//h.put("namaPenghantar",rs.getString("PEGAWAI_PENGHANTAR")==null?"":rs.getString("PEGAWAI_PENGHANTAR"));
				h.put("tarikhMasuk",rs.getString("TARIKH_FAIL_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_FAIL_MASUK")));
				h.put("tarikhKeluar",rs.getString("TARIKH_FAIL_KELUAR")==null?"":sdf.format(rs.getDate("TARIKH_FAIL_KELUAR")));
				h.put("tarikhSDPdari",rs.getString("TEMPOH_SDP_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_SDP_DARI")));
				h.put("tarikhSDPhingga",rs.getString("TEMPOH_SDP_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_SDP_HINGGA")));
				h.put("catatan",rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
				
				paparPergerakanFail.addElement(h);
				
			}
			
			
			return paparPergerakanFail;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector paparMaklumatFail(String idFail) throws Exception {
		String sql = "";
		Db db = null;
		
		try{
			Vector paparMaklumat = new Vector();
			paparMaklumat.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL AS ID_FAIL, A.NO_FAIL AS NO_FAIL, A.TAJUK_FAIL AS TAJUK_FAIL, B.KETERANGAN AS STATUS " +
				  "FROM TBLPFDFAIL A, TBLRUJSTATUS B " +
				  "WHERE A.ID_STATUS = B.ID_STATUS " +
				  "AND ID_FAIL='"+idFail+"'";
			
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
				h.put("status",rs.getString("STATUS")==null?"":rs.getString("STATUS"));
//				h.put("tarikhKuatkuasa",rs.getString("TARIKH_KUATKUASA")==null?"":sdf.format(rs.getDate("TARIKH_KUATKUASA")));
//				h.put("tarikhMansuh",rs.getString("TARIKH_MANSUH")==null?"":sdf.format(rs.getDate("TARIKH_MANSUH")));
			
	
				paparMaklumat.addElement(h);
				
			}
			
			
			return paparMaklumat;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector paparMaklumatPergerakanFail(String idFail) throws Exception {
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		
		try{
			Vector paparMaklumatPergerakan = new Vector();
			paparMaklumatPergerakan.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NAMA_KERANI_FAIL, A.ID_PEGAWAIPENERIMA, A.ID_PEGAWAIPENGHANTAR, A.TARIKH_FAIL_MASUK, A.TARIKH_FAIL_KELUAR, A.TEMPOH_SDP_DARI, A.TEMPOH_SDP_HINGGA, A.CATATAN " +
				  "FROM TBLPFDPERGERAKANFAIL A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C " +
				  "WHERE A.ID_PEGAWAIPENERIMA = B.ID_PEGAWAI " +
				  "AND A.ID_PEGAWAIPENGHANTAR = C.ID_PEGAWAI " +
				  "AND A.ID_FAIL='"+idFail+"'";
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("namaKerani",rs.getString("NAMA_KERANI_FAIL")==null?"":rs.getString("NAMA_KERANI_FAIL"));
				h.put("idPenerima",rs.getString("ID_PEGAWAIPENERIMA")==null?"":rs.getString("ID_PEGAWAIPENERIMA"));
				h.put("idPenghantar",rs.getString("ID_PEGAWAIPENGHANTAR")==null?"":rs.getString("ID_PEGAWAIPENGHANTAR"));
				h.put("tarikhMasuk",rs.getString("TARIKH_FAIL_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_FAIL_MASUK")));
				h.put("tarikhKeluar",rs.getString("TARIKH_FAIL_KELUAR")==null?"":sdf.format(rs.getDate("TARIKH_FAIL_KELUAR")));
				h.put("tarikhSDPdari",rs.getString("TEMPOH_SDP_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_SDP_DARI")));
				h.put("tarikhSDPhingga",rs.getString("TEMPOH_SDP_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_SDP_HINGGA")));
				h.put("catatan",rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
			
	
				paparMaklumatPergerakan.addElement(h);
				
			}
			
			
			return paparMaklumatPergerakan;
		}
		finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector paparCheckPergerakanFail(String idFail) throws Exception {
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		
		try{
			Vector paparCheckPergerakanFail = new Vector();
			paparCheckPergerakanFail.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_FAIL, A.NAMA_KERANI_FAIL, A.ID_PEGAWAIPENERIMA, A.ID_PEGAWAIPENGHANTAR, A.TARIKH_FAIL_MASUK, A.TARIKH_FAIL_KELUAR, A.TEMPOH_SDP_DARI, A.TEMPOH_SDP_HINGGA, A.CATATAN " +
				  "FROM TBLPFDPERGERAKANFAIL A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C " +
				  "WHERE A.ID_PEGAWAIPENERIMA = B.ID_PEGAWAI " +
				  "AND A.ID_PEGAWAIPENGHANTAR = C.ID_PEGAWAI " +
				  "AND A.ID_FAIL='"+idFail+"'";
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("namaKerani",rs.getString("NAMA_KERANI_FAIL")==null?"":rs.getString("NAMA_KERANI_FAIL"));
				h.put("idPenerima",rs.getString("ID_PEGAWAIPENERIMA")==null?"":rs.getString("ID_PEGAWAIPENERIMA"));
				h.put("idPenghantar",rs.getString("ID_PEGAWAIPENGHANTAR")==null?"":rs.getString("ID_PEGAWAIPENGHANTAR"));
				h.put("tarikhMasuk",rs.getString("TARIKH_FAIL_MASUK")==null?"":sdf.format(rs.getDate("TARIKH_FAIL_MASUK")));
				h.put("tarikhKeluar",rs.getString("TARIKH_FAIL_KELUAR")==null?"":sdf.format(rs.getDate("TARIKH_FAIL_KELUAR")));
				h.put("tarikhSDPdari",rs.getString("TEMPOH_SDP_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_SDP_DARI")));
				h.put("tarikhSDPhingga",rs.getString("TEMPOH_SDP_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_SDP_HINGGA")));
				h.put("catatan",rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
			
	
				paparCheckPergerakanFail.addElement(h);
				
			}
			
			
			return paparCheckPergerakanFail;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	public static String getPreviousPenerimaFail(String ID_FAIL) throws Exception {
		String sql = "";
		Db db = null;
		String NamaPegawai = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			
			sql = "SELECT C.NAMA_PEGAWAI FROM TBLPFDPERGERAKANFAIL M, TBLRUJPEGAWAI C WHERE M.ID_PEGAWAIPENERIMA = C.ID_PEGAWAI AND " +
				"M.ID_FAIL = " + ID_FAIL;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				NamaPegawai = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return NamaPegawai;
		
	}
	
	public static void setDataLatestPergerakanPegawai(String id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLatestPergerakanPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      sql = "SELECT ID_PEGAWAIPENERIMA FROM TBLPFDPERGERAKANFAIL WHERE ID_FAIL = '"+id+"' AND TARIKH_MASUK = (select max(TARIKH_MASUK) from tblpfdpergerakanfail WHERE ID_FAIL = '"+id+"')";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idPenerima",rs.getString("ID_PEGAWAIPENERIMA")==null?"":rs.getString("ID_PEGAWAIPENERIMA"));
				  paparLatestPergerakanPegawai.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("idPenerima","0");
					 
		    	  paparLatestPergerakanPegawai.addElement(h);
		    	  
		      }
		 }
		 finally {
		      if (db != null) db.close();
		    } 
		
	}
	public static Vector getDataLatestPergerakanPegawai(String idFail) {
		// TODO Auto-generated method stub
		return paparLatestPergerakanPegawai;
	}

	public static void setListPTFail(String user_id, String user_negeri) throws Exception {
	    Db db = null;
	    senaraiPTFail.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
//	      sql = "select user_id,user_name from users where user_id in " +
//	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
//	      		"and id_jawatan=24)";

	      sql =  " SELECT U.user_id,U.user_name,TJ.shortname_jawatan FROM USERS U, USERS_INTERNAL UI, TBLRUJJAWATAN TJ WHERE U.USER_ID = UI.USER_ID and UI.ID_JAWATAN = TJ.ID_JAWATAN ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN IN (24)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk','partest', 'testpt' ) and u.user_login not in ('partest', 'testpt')";	   
	      
	      
	      sql += "ORDER BY U.user_name ASC ";
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				

	    	  senaraiPTFail.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
	}
		
	public static Vector getListPTFail() {
	
		return senaraiPTFail;
	}

	public static void setListPergerakanPemohonFail(String user_id) throws Exception {
	    Db db = null;
	    senaraiPergerakanPemohonFail.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      sql = "select a.id_pergerakanfail, b.no_fail, b.tajuk_fail, a.id_fail, a.status_pinjaman_fail, a.nama_pegawai_pinjamfail, a.id_pt_fail, a.nama_pt_fail, a.tarikh_sah_pinjamfail, a.tarikh_sah_pulangfail, a.tempoh_pinjam_dari, a.tempoh_pinjam_hingga " +
	      		"from TBLPFDPERGERAKANFAIL A, TBLPFDFAIL B " +
	      		"where a.id_fail = b.id_fail and id_pt_fail = "+user_id+"" +
	      		"and a.status_pinjaman_fail in (1,2,0)";
	      sql = sql + "ORDER BY ID_PERGERAKANFAIL DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPergerakanfail",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Fail",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("nama_fail", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("idFail", rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("status_pergerakan_fail",rs.getString(5)==null?"":rs.getString(5));
	    	  h.put("peminjam_fail",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("id_pt_fail", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("nama_pt_fail",rs.getString(8)==null?"":rs.getString(8));
	    	  h.put("tarikh_sah_pinjamfail",rs.getString(9)==null?"":sdf.format(rs.getDate(9)));
	    	  h.put("tarikh_sah_pulangfail",rs.getString(10)==null?"":sdf.format(rs.getDate(10)));
	    	  h.put("tempoh_pinjam_dari",rs.getString(11)==null?"":sdf.format(rs.getDate(11)));
	    	  h.put("tempoh_pinjam_hingga",rs.getString(12)==null?"":sdf.format(rs.getDate(12)));

	    	  senaraiPergerakanPemohonFail.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idPergerakanfail","");
	    	  h.put("no_Fail", "");
	    	  h.put("nama_fail", "");
	    	  h.put("idFail", "");
	    	  h.put("status_pergerakan_fail","");
	    	  h.put("peminjam_fail","");
	    	  h.put("id_pt_fail", "");
	    	  h.put("nama_pt_fail","");
	    	  h.put("tarikh_sah_pinjamfail","");
	    	  h.put("tarikh_sah_pulangfail","");

	    	  senaraiPergerakanPemohonFail.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListPergerakanPemohonFail() {
		
		return senaraiPergerakanPemohonFail;
	}
	
	public static Vector paparPeminjamFail(String idPergerakan) throws Exception {
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Db db = null;
		
		try{
			Vector paparPeminjamFail = new Vector();
			paparPeminjamFail.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			 sql = "select a.id_pergerakanfail, b.no_fail, b.tajuk_fail, a.id_fail, a.status_pinjaman_fail, a.nama_pegawai_pinjamfail, a.id_pt_fail, a.nama_pt_fail,a.tempoh_pinjam_dari,a.tempoh_pinjam_hingga, a.tarikh_sah_pinjamfail, a.tarikh_sah_pulangfail, a.catatan " +
	      		"from TBLPFDPERGERAKANFAIL A, TBLPFDFAIL B " +
	      		"where a.id_fail = b.id_fail and id_pergerakanfail = "+idPergerakan+" ";
			 sql = sql + "ORDER BY ID_PERGERAKANFAIL DESC";
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idPergerakanfail",rs.getString("id_pergerakanfail")==null?"":rs.getString("id_pergerakanfail"));
				//h.put("namaKerani",rs.getString("no_fail")==null?"":rs.getString("no_fail"));
				//h.put("idPenerima",rs.getString("tajuk_fail")==null?"":rs.getString("tajuk_fail"));
				//h.put("idPenghantar",rs.getString("id_fail")==null?"":rs.getString("id_fail"));
				h.put("status_pinjaman_fail",rs.getString("status_pinjaman_fail")==null?"":rs.getString("status_pinjaman_fail"));
				//h.put("nama_peminjam_fail",rs.getString("nama_pegawai_pinjamfail")==null?"":rs.getString("nama_pegawai_pinjamfail"));
				h.put("nama_peminjam_fail",rs.getString("nama_pegawai_pinjamfail")==null?"":rs.getString("nama_pegawai_pinjamfail"));
				//h.put("",rs.getString("id_pt_fail")==null?"":rs.getString("id_pt_fail"));
				//h.put("",rs.getString("nama_pt_fail")==null?"":rs.getString("nama_pt_fail"));
				h.put("tarikhPinjamanDisahkan",rs.getString("tarikh_sah_pinjamfail")==null?"":sdf.format(rs.getDate("tarikh_sah_pinjamfail")));
				h.put("tarikhPulangDisahkan",rs.getString("tarikh_sah_pulangfail")==null?"":sdf.format(rs.getDate("tarikh_sah_pulangfail")));
				h.put("tempohPinjamDari",rs.getString("tempoh_pinjam_dari")==null?"":sdf.format(rs.getDate("tempoh_pinjam_dari")));
				h.put("tempohPinjamHingga",rs.getString("tempoh_pinjam_hingga")==null?"":sdf.format(rs.getDate("tempoh_pinjam_hingga")));
				h.put("catatan",rs.getString("CATATAN")==null?"":rs.getString("CATATAN"));
			
	
				paparPeminjamFail.addElement(h);
				
			}
			
			
			return paparPeminjamFail;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	
	public static void updatePengesahanFail(Hashtable data) throws Exception{
		 Db db = null;
		    String sql = "";
		    try
		    {
		    	  String idPergerakanfail = (String)data.get("idPergerakanfail");
		    	  String status_pinjaman_fail = (String)data.get("status_pinjaman_fail");
		    	  String nama_peminjam_fail = (String)data.get("nama_peminjam_fail");
			      String tarikhPinjamanDisahkan = "to_date('" + data.get("tarikhPinjamanDisahkan") + "','dd/MM/yyyy')";
			      String tarikhPulangDisahkan = "to_date('" + data.get("tarikhPulangDisahkan")  + "','dd/MM/yyyy')";
			      String tempohPinjamDari = "to_date('" + data.get("tempohPinjamDari")  + "','dd/MM/yyyy')";
			      String tempohPinjamHingga = "to_date('" + data.get("tempohPinjamHingga")  + "','dd/MM/yyyy')";
			      String catatan = (String)data.get("catatan");
			      String disahkanOleh = (String)data.get("disahkan_Oleh");
				 
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Pergerakanfail", idPergerakanfail);
				  r.add("status_pinjaman_fail", status_pinjaman_fail);
				  r.add("nama_pt_fail", disahkanOleh);
				  r.add("nama_pegawai_pinjamfail", nama_peminjam_fail);
			      r.add("tarikh_sah_pinjamfail", r.unquote(tarikhPinjamanDisahkan));
			      r.add("tarikh_sah_pulangfail", r.unquote(tarikhPulangDisahkan));
			      r.add("tarikh_pulang_fail", r.unquote(tarikhPulangDisahkan));
			      r.add("tempoh_Pinjam_Dari", r.unquote(tempohPinjamDari));
			      r.add("tempoh_Pinjam_Hingga", r.unquote(tempohPinjamHingga));
			      r.add("catatan",catatan); 
				
				  sql = r.getSQLUpdate("tblpfdpergerakanfail");
			      stmt.executeUpdate(sql);
			      
			     
			    }
		    	
			    finally {
			      if (db != null) db.close();
			    }
	}

	public static void setListPergerakanPemohonFailUser(String idFail) throws Exception {
		Db db = null;
		senaraiPergerakanFailUser.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      sql = "select a.id_pergerakanfail, b.no_fail, b.tajuk_fail, a.id_fail, a.status_pinjaman_fail, a.nama_pegawai_pinjamfail, a.id_pt_fail, a.nama_pt_fail, a.tarikh_sah_pinjamfail, a.tarikh_sah_pulangfail, a.tempoh_pinjam_dari, a.tempoh_pinjam_hingga, a.tarikh_pulang_fail, a.ID_PEGAWAI_PINJAMFAIL " +
	      		"from TBLPFDPERGERAKANFAIL A, TBLPFDFAIL B " +
	      		"where a.id_fail = b.id_fail and a.id_fail = "+idFail+" ";
	      sql = sql + "ORDER BY ID_PERGERAKANFAIL DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPergerakanfail",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Fail",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("nama_fail", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("idFail", rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("status_pergerakan_fail",rs.getString(5)==null?"":rs.getString(5));
	    	  h.put("peminjam_fail",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("id_pt_fail", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("nama_pt_fail",rs.getString(8)==null?"":rs.getString(8));
	    	  h.put("tarikh_sah_pinjamfail",rs.getString(9)==null?"":sdf.format(rs.getDate(9)));
	    	  h.put("tarikh_sah_pulangfail",rs.getString(10)==null?"":sdf.format(rs.getDate(10)));
	    	  h.put("tempoh_pinjam_dari",rs.getString(11)==null?"":sdf.format(rs.getDate(11)));
	    	  h.put("tempoh_pinjam_hingga",rs.getString(12)==null?"":sdf.format(rs.getDate(12)));
	    	  h.put("tarikh_pulang_fail",rs.getString(13)==null?"":sdf.format(rs.getDate(13)));
	    	  h.put("ID_PEGAWAI_PINJAMFAIL",rs.getString(14)==null?"":rs.getString(14));


	    	  senaraiPergerakanFailUser.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	
	    	  h.put("idPergerakanfail","");
	    	  h.put("no_Fail", "");
	    	  h.put("nama_fail", "");
	    	  h.put("idFail", "");
	    	  h.put("status_pergerakan_fail","");
	    	  h.put("peminjam_fail","");
	    	  h.put("id_pt_fail", "");
	    	  h.put("nama_pt_fail","");
	    	  h.put("tarikh_sah_pinjamfail","");
	    	  h.put("tarikh_sah_pulangfail","");
	    	  h.put("tempoh_pinjam_dari","");
	    	  h.put("tempoh_pinjam_hingga","");
	    	  h.put("tarikh_pulang_fail","");
	    	  h.put("ID_PEGAWAI_PINJAMFAIL","");
	    	  

	    	  senaraiPergerakanFailUser.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	public static Vector getListPergerakanPemohonFailUser(String idFail) {
			
		return senaraiPergerakanFailUser;

	}
	public static Vector paparSimpanPergerakanFail(String idPergerakanfail) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			Vector paparSimpanPergerakanFail = new Vector();
			paparSimpanPergerakanFail.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_PERGERAKANFAIL, A.NAMA_PEGAWAI_PINJAMFAIL, A.ID_PT_FAIL, A.TUJUAN_PINJAMAN, A.TEMPOH_PINJAM_DARI, A.TEMPOH_PINJAM_HINGGA " +
				  "FROM TBLPFDPERGERAKANFAIL A " +
				  "WHERE A.ID_PERGERAKANFAIL='"+idPergerakanfail+"'";
			
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("idPergerakanfail",rs.getString("ID_PERGERAKANFAIL")==null?"":rs.getString("ID_PERGERAKANFAIL"));
				h.put("nama_pegawai_pinjamfail",rs.getString("NAMA_PEGAWAI_PINJAMFAIL")==null?"":rs.getString("NAMA_PEGAWAI_PINJAMFAIL"));
				h.put("IDPTFAIL",rs.getString("ID_PT_FAIL")==null?"":rs.getString("ID_PT_FAIL"));
				h.put("tujuan",rs.getString("TUJUAN_PINJAMAN")==null?"":rs.getString("TUJUAN_PINJAMAN"));
				h.put("tempohPinjamDari",rs.getDate("TEMPOH_PINJAM_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_DARI")));
				h.put("tempohPinjamHingga",rs.getDate("TEMPOH_PINJAM_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
				paparSimpanPergerakanFail.addElement(h);

			}
			
			
			return paparSimpanPergerakanFail;
		}
		finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector paparTarikhPinjamDari(String idFail) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			Vector paparTarikhPinjamDari = new Vector();
			paparTarikhPinjamDari.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT MAX(B.TEMPOH_PINJAM_DARI) AS TEMPOH_PINJAM_DARI " +
				  "FROM TBLPFDFAIL A, TBLPFDPERGERAKANFAIL B " +
				  "WHERE A.ID_FAIL = B.ID_FAIL " +
				  "AND A.ID_FAIL='"+idFail+"'";
			
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
		    int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				
//				if(rs.getDate("TEMPOH_PINJAM_DARI")!=null){
//				Calendar currentDate = new GregorianCalendar(); 
//				Calendar cal = new GregorianCalendar(); 
//				Date date = sdf.parse(sdf.format(rs.getDate("TEMPOH_PINJAM_DARI")));
//				cal.setTime(date);
//				
//				cal.add(Calendar.DATE, 1);
			
				//r.add("tempohPinjamDari", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','dd/MM/yyyy')"));
				
				
//				h.put("tempohPinjamDari",sdf.format(cal.getTime()));
//				}
				h.put("tempohPinjamDari",rs.getDate("TEMPOH_PINJAM_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_DARI")));
				paparTarikhPinjamDari.addElement(h);
		    	bil++;
		    	count++;
			}
			
			if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		      	  h.put("tempohPinjamDari","");
		    	 // h.put("tempohPinjamHingga","");
		    	  paparTarikhPinjamDari.addElement(h);
		    }
			
			return paparTarikhPinjamDari;
			 
		}
		
		
		finally {
			if (db != null)
				db.close();
		}
	
	}

	public static Vector paparTarikhPinjamHingga(String idFail) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			Vector paparTarikhPinjamHingga = new Vector();
			paparTarikhPinjamHingga.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NVL(MAX(B.TEMPOH_PINJAM_HINGGA),SYSDATE) AS TEMPOH_PINJAM_HINGGA " +
				  "FROM TBLPFDFAIL A, TBLPFDPERGERAKANFAIL B " +
				  "WHERE A.ID_FAIL = B.ID_FAIL " +
				  "AND A.ID_FAIL='"+idFail+"'";
			
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
		    int count = 0;
		    
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				if(rs.getDate("TEMPOH_PINJAM_HINGGA")!=null){
					Calendar currentDate = new GregorianCalendar(); 
					Calendar cal = new GregorianCalendar(); 
					Date date = sdf.parse(sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
					cal.setTime(date);
					
					cal.add(Calendar.DATE, 1);
				
					//r.add("tempohPinjamDari", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','dd/MM/yyyy')"));
					
					
					h.put("tempohPinjamHingga",sdf.format(cal.getTime()));
					}
				//h.put("tempohPinjamDari",rs.getDate("TEMPOH_PINJAM_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_DARI")));
				//h.put("tempohPinjamHingga",rs.getDate("TEMPOH_PINJAM_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
				paparTarikhPinjamHingga.addElement(h);
		    	bil++;
		    	count++;
			}
			
			if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		      	  //h.put("tempohPinjamDari","");
		    	  h.put("tempohPinjamHingga","");
		    	  paparTarikhPinjamDari.addElement(h);
		    }
			
			return paparTarikhPinjamHingga;
			 
		}
		
		
		finally {
			if (db != null)
				db.close();
		}
	
	}
	public static Vector paparTarikhPinjamHinggaAdd3(String idFail) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			Vector paparTarikhPinjamHingga = new Vector();
			paparTarikhPinjamHingga.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT NVL(MAX(B.TEMPOH_PINJAM_HINGGA),SYSDATE) AS TEMPOH_PINJAM_HINGGA " +
				  "FROM TBLPFDFAIL A, TBLPFDPERGERAKANFAIL B " +
				  "WHERE A.ID_FAIL = B.ID_FAIL " +
				  "AND A.ID_FAIL='"+idFail+"'";
			
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
		    int count = 0;
		    
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				if(rs.getDate("TEMPOH_PINJAM_HINGGA")!=null){
					Calendar currentDate = new GregorianCalendar(); 
					Calendar cal = new GregorianCalendar(); 
					Date date = sdf.parse(sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
					cal.setTime(date);
					
					cal.add(Calendar.DATE, 3);
				
					//r.add("tempohPinjamDari", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','dd/MM/yyyy')"));
					
					
					h.put("tempohPinjamHingga",sdf.format(cal.getTime()));
					}
				//h.put("tempohPinjamDari",rs.getDate("TEMPOH_PINJAM_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_DARI")));
				//h.put("tempohPinjamHingga",rs.getDate("TEMPOH_PINJAM_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
				paparTarikhPinjamHingga.addElement(h);
		    	bil++;
		    	count++;
			}
			
			if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		      	  //h.put("tempohPinjamDari","");
		    	  h.put("tempohPinjamHingga","");
		    	  paparTarikhPinjamDari.addElement(h);
		    }
			
			return paparTarikhPinjamHingga;
			 
		}
		
		
		finally {
			if (db != null)
				db.close();
		}
	}
	public static Vector paparTarikhPinjamHinggaAdd3Kosong(String idFail) throws Exception {
		String sql = "";
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			Vector paparTarikhPinjamHingga = new Vector();
			paparTarikhPinjamHingga.clear();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT to_char(sysdate, 'DD/MM/YYYY') as TEMPOH_PINJAM_HINGGA " +
				  "FROM TBLPFDFAIL A, TBLPFDPERGERAKANFAIL B " +
				  "WHERE A.ID_FAIL = B.ID_FAIL " +
				  "AND A.ID_FAIL='"+idFail+"'";
			
			
			System.out.println("sql" +sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
		    int count = 0;
		    
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				if(rs.getDate("TEMPOH_PINJAM_HINGGA")!=null){
					Calendar currentDate = new GregorianCalendar(); 
					Calendar cal = new GregorianCalendar(); 
					Date date = sdf.parse(sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
					cal.setTime(date);
					
					cal.add(Calendar.DATE, 3);
				
					//r.add("tempohPinjamDari", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','dd/MM/yyyy')"));
					
					
					h.put("tempohPinjamHingga",sdf.format(cal.getTime()));
					}
				//h.put("tempohPinjamDari",rs.getDate("TEMPOH_PINJAM_DARI")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_DARI")));
				//h.put("tempohPinjamHingga",rs.getDate("TEMPOH_PINJAM_HINGGA")==null?"":sdf.format(rs.getDate("TEMPOH_PINJAM_HINGGA")));
				paparTarikhPinjamHingga.addElement(h);
		    	bil++;
		    	count++;
			}
			
			if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		      	  //h.put("tempohPinjamDari","");
		    	  h.put("tempohPinjamHingga","");
		    	  paparTarikhPinjamDari.addElement(h);
		    }
			
			return paparTarikhPinjamHingga;
			 
		}
		
		
		finally {
			if (db != null)
				db.close();
		}
	}
	
	public static void setListPergerakanPemohonFailSelesai(String user_id) throws Exception {
	    Db db = null;
	    senaraiPergerakanPemohonFailSelesai.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      sql = "select a.id_pergerakanfail, b.no_fail, b.tajuk_fail, a.id_fail, a.status_pinjaman_fail, a.nama_pegawai_pinjamfail, a.id_pt_fail, a.nama_pt_fail, a.tarikh_sah_pinjamfail, a.tarikh_sah_pulangfail, a.tempoh_pinjam_dari, a.tempoh_pinjam_hingga " +
	      		"from TBLPFDPERGERAKANFAIL A, TBLPFDFAIL B " +
	      		"where a.id_fail = b.id_fail and id_pt_fail = "+user_id+"" +
	      		" and a.status_pinjaman_fail in (3)";
	      sql = sql + " ORDER BY ID_PERGERAKANFAIL DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPergerakanfail",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Fail",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("nama_fail", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("idFail", rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("status_pergerakan_fail",rs.getString(5)==null?"":rs.getString(5));
	    	  h.put("peminjam_fail",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("id_pt_fail", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("nama_pt_fail",rs.getString(8)==null?"":rs.getString(8));
	    	  h.put("tarikh_sah_pinjamfail",rs.getString(9)==null?"":sdf.format(rs.getDate(9)));
	    	  h.put("tarikh_sah_pulangfail",rs.getString(10)==null?"":sdf.format(rs.getDate(10)));
	    	  h.put("tempoh_pinjam_dari",rs.getString(11)==null?"":sdf.format(rs.getDate(11)));
	    	  h.put("tempoh_pinjam_hingga",rs.getString(12)==null?"":sdf.format(rs.getDate(12)));

	    	  senaraiPergerakanPemohonFailSelesai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	
	    	  h.put("idPergerakanfail","");
	    	  h.put("no_Fail", "");
	    	  h.put("nama_fail", "");
	    	  h.put("idFail", "");
	    	  h.put("status_pergerakan_fail","");
	    	  h.put("peminjam_fail","");
	    	  h.put("id_pt_fail", "");
	    	  h.put("nama_pt_fail","");
	    	  h.put("tarikh_sah_pinjamfail","");
	    	  h.put("tarikh_sah_pulangfail","");

	    	  senaraiPergerakanPemohonFailSelesai.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListPergerakanPemohonFailSelesai() {
		
		return senaraiPergerakanPemohonFailSelesai;
	}
	
	public static Vector getListPergerakanPemohonFailCarian(String noFail,
			String tajukFail, String tarikh, String user_id) throws Exception {
	    Db db = null;
	    
		Vector senaraiPergerakanPemohonFail = new Vector();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	      sql = "select a.id_pergerakanfail, b.no_fail, b.tajuk_fail, a.id_fail, a.status_pinjaman_fail, a.nama_pegawai_pinjamfail, a.id_pt_fail, a.nama_pt_fail, a.tarikh_sah_pinjamfail, a.tarikh_sah_pulangfail, a.tempoh_pinjam_dari, a.tempoh_pinjam_hingga " +
    		"from TBLPFDPERGERAKANFAIL A, TBLPFDFAIL B " +
    		"where a.id_fail = b.id_fail and id_pt_fail = "+user_id+"";
	      
	  	boolean setLimit = true;
	      
	      //NO FAIL
	      if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(b.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      //TAJUK FAIL
	      if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(b.TAJUK_FAIL) LIKE '%' ||'" + tajukFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      
	      //TARIKH DAFTAR FAIL	      
	     // SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (tarikh != null) {	  	
				if (!tarikh.toString().trim().equals("")) {		
					setLimit = false;
//					sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
					sql = sql + " AND TO_CHAR(a.tarikh_sah_pinjamfail,'DD/MM/YYYY') = '" + tarikh +"'";
					
				}
			}
	      
	      sql = sql + "ORDER BY ID_PERGERAKANFAIL DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPergerakanfail",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Fail",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("nama_fail", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("idFail", rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("status_pergerakan_fail",rs.getString(5)==null?"":rs.getString(5));
	    	  h.put("peminjam_fail",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("id_pt_fail", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("nama_pt_fail",rs.getString(8)==null?"":rs.getString(8));
	    	  h.put("tarikh_sah_pinjamfail",rs.getString(9)==null?"":sdf.format(rs.getDate(9)));
	    	  h.put("tarikh_sah_pulangfail",rs.getString(10)==null?"":sdf.format(rs.getDate(10)));
	    	  h.put("tempoh_pinjam_dari",rs.getString(11)==null?"":sdf.format(rs.getDate(11)));
	    	  h.put("tempoh_pinjam_hingga",rs.getString(12)==null?"":sdf.format(rs.getDate(12)));

	    	  if ((setLimit && bil <= 50) || setLimit == false) {	//RESERVED BY AZAM
	    		  senaraiPergerakanPemohonFail.addElement(h);			
				}

	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	
	    	  h.put("idPergerakanfail","");
	    	  h.put("no_Fail", "");
	    	  h.put("nama_fail", "");
	    	  h.put("idFail", "");
	    	  h.put("status_pergerakan_fail","");
	    	  h.put("peminjam_fail","");
	    	  h.put("id_pt_fail", "");
	    	  h.put("nama_pt_fail","");
	    	  h.put("tarikh_sah_pinjamfail","");
	    	  h.put("tarikh_sah_pulangfail","");

	    	  senaraiPergerakanPemohonFail.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		return senaraiPergerakanPemohonFail;
	}
	public static Vector getPTFail(String idPergerakanfail) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 Vector paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PAR AND A.ID_FAIL = '"+idPergerakanfail+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      System.out.println("error test :"+sql.toUpperCase());
		      
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
		    	  paparPegawai.addElement(h); 
		      }
		      
		      return paparPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static void hapus(Hashtable data) throws Exception {
		 Db db = null;
		    String sql = "";
			String idPergerakanfail;
		   
		    try
		    {
		    	  idPergerakanfail = (String)data.get("idPergerakanfail");
			      String idFail = (String)data.get("id_Fail");
			      String idMasuk = (String)data.get("id_Masuk");
			    	
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      sql = "DELETE FROM TBLPFDPERGERAKANFAIL WHERE ID_PERGERAKANFAIL = '"+idPergerakanfail+"'";
			   		      
			      stmt.executeUpdate(sql);
			      
			    } finally {
			      if (db != null) db.close();
			    }
		
	}

}

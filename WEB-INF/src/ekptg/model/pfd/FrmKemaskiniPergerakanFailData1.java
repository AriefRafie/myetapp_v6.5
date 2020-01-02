package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;

public class FrmKemaskiniPergerakanFailData1 {
	
	private static Vector paparFail = new Vector();
	private static Vector paparNoFail = new Vector();
	private static Vector paparPergerakanFail = new Vector();
	private static Vector senaraiPergerakanFail = new Vector();
	
	
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
		      
		      sql = "SELECT A.ID_PERGERAKANFAIL, E.NO_FAIL, C.NAMA_PEGAWAI AS PEGAWAIPENERIMA, D.NAMA_PEGAWAI AS PEGAWAIPENGHANTAR, A.TARIKH_FAIL_MASUK, A.TARIKH_FAIL_KELUAR, A.NAMA_KERANI_FAIL " +
				  "FROM TBLPFDPERGERAKANFAIL A, TBLRUJPEGAWAI C, TBLRUJPEGAWAI D, TBLPFDFAIL E " +
				  "WHERE A.ID_FAIL = E.ID_FAIL " +
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
		    	  String nama_kerani = (String)data.get("nama_kerani");
		    	  Integer pegawaiPenerima = (Integer)data.get("idPegawaipenerima");
		    	  Integer pegawaiPenghantar = (Integer)data.get("idPegawaipenghantar");
			      String tarikhFailMasuk = (String)data.get("tarikh_Fail_Masuk");
			      String tarikhFailKeluar = (String)data.get("tarikh_Fail_Keluar");
			      String tarikhSdpDari = (String)data.get("tempoh_Sdp_Dari");
			      String tarikhSdpHingga = (String)data.get("tempoh_Sdp_Hingga");
			      String catatan = (String)data.get("catatan");
			      String idFail = (String)data.get("id_Fail");
			      String idMasuk = (String)data.get("id_Masuk");
			    			      
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("ID_PERGERAKANFAIL", idPergerakanfail);
			      r.add("NAMA_KERANI_FAIL", nama_kerani);
			      r.add("ID_FAIL", idFail);
			      r.add("ID_PEGAWAIPENERIMA", pegawaiPenerima);
			      r.add("ID_PEGAWAIPENGHANTAR", pegawaiPenghantar);
			      r.add("TARIKH_FAIL_MASUK", tarikhFailMasuk);
			      r.add("TARIKH_FAIL_KELUAR", tarikhFailKeluar);
			      r.add("TEMPOH_SDP_DARI", tarikhSdpDari);
			      r.add("TEMPOH_SDP_HINGGA", tarikhSdpHingga);
			      r.add("CATATAN", catatan);
			      r.add("id_Masuk",idMasuk);
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			  
			      sql = r.getSQLInsert("TBLPFDPERGERAKANFAIL");
			      
			      stmt.executeUpdate(sql);
			      
			      return "" +idPergerakanfail;
			      
			    } finally {
			      if (db != null) db.close();
			    }
	
		}
	
	public static void update(Hashtable data) throws Exception {
	    Db db = null;
	    String sql = "";
	   
	    try
	    {
	    	  String idPergerakanfail = (String)data.get("idPergerakanfail");
	    	  String nama_kerani = (String)data.get("nama_kerani");
	    	  Integer pegawaiPenerima = (Integer)data.get("idPegawaipenerima");
	    	  Integer pegawaiPenghantar = (Integer)data.get("idPegawaipenghantar");
		      String tarikhFailMasuk = (String)data.get("tarikh_Fail_Masuk");
		      String tarikhFailKeluar = (String)data.get("tarikh_Fail_Keluar");
		      String tarikhSdpDari = (String)data.get("tempoh_Sdp_Dari");
		      String tarikhSdpHingga = (String)data.get("tempoh_Sdp_Hingga");
		      String catatan = (String)data.get("catatan");
		      String idFail = (String)data.get("id_Fail");
		      String id_Kemaskini = (String)data.get("id_Kemaskini");
		    			      
		      db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
		      
		      r.update("ID_PERGERAKANFAIL", idPergerakanfail);
		      r.add("NAMA_KERANI_FAIL", nama_kerani);
		      r.add("ID_FAIL", idFail);
		      r.add("ID_PEGAWAIPENERIMA", pegawaiPenerima);
		      r.add("ID_PEGAWAIPENGHANTAR", pegawaiPenghantar);
		      r.add("TARIKH_FAIL_MASUK", tarikhFailMasuk);
		      r.add("TARIKH_FAIL_KELUAR", tarikhFailKeluar);
		      r.add("TEMPOH_SDP_DARI", tarikhSdpDari);
		      r.add("TEMPOH_SDP_HINGGA", tarikhSdpHingga);
		      r.add("CATATAN", catatan);
		      r.add("id_Kemaskini",id_Kemaskini);
		      r.add("tarikh_kemaskini",r.unquote("sysdate")); 
			
			  sql = r.getSQLUpdate("tblpfdpergerakanfail");
		      stmt.executeUpdate(sql);
	    }
	    finally {
	      if (db != null) db.close();
	    }
    }
//	public static String update(Hashtable data) throws Exception {
//	    Db db = null;
//	    db = new Db();
//	    String sql = "";
//	    long idStatus1 = 0;
//	    long idStatus2 = 0;
//	    String sql2 = "SELECT a.id_status,a.kod_status,b.id_status,b.kod_status"+
//	    " FROM tblrujstatus a,tblrujstatus b"+
//	    " WHERE a.id_seksyen = 6 AND a.kod_status = 06" +
//	    " AND b.id_seksyen = 6 AND b.kod_status = 08 ";
//
//	    Statement stmt = db.getStatement();
//	    ResultSet rs = stmt.executeQuery(sql2);
//	    rs.next();
//	    idStatus1 = rs.getLong(1);
//	    idStatus2 = rs.getLong(3);
//	   
//	    try
//	    {
//	    	  String idPergerakanfail = (String)data.get("id_Pergerakanfail");
//	    	  String namaPenghantar = (String)data.get("id_Pegawaipenghantar");
//		      String namaPenerima = (String)data.get("id_Pegawaipenerima");
//		      String tarikhFailMsk = (String)data.get("tarikh_Fail_Masuk");
//		      String tkhFailMsk = "to_date('" + tarikhFailMsk + "','dd/MM/yyyy')";
//		      String tarikhFailKeluar = (String)data.get("tarikh_Fail_Keluar");
//		      String tkhFailKeluar = "to_date('" + tarikhFailKeluar + "','dd/MM/yyyy')";
//		      String tempohSdpDari = (String)data.get("tempoh_Sdp_Dari");
//		      String sdpDari = "to_date('" + tempohSdpDari + "','dd/MM/yyyy')";
//		      String tempohSdpHingga = (String)data.get("tempoh_Sdp_Hingga");
//		      String sdpHingga = "to_date('" + tempohSdpHingga + "','dd/MM/yyyy')";
//		      String catatan = (String)data.get("catatan");
//		     
//			  
//			  
//			 
//			  SQLRenderer r = new SQLRenderer();
//			  r.update("id_Pergerakanfail", idPergerakanfail);
//			  r.add("id_Pegawaipenghantar",namaPenghantar);
//		      r.add("id_Pegawaipenerima", namaPenerima);
//		      r.add("tarikh_Fail_Masuk", r.unquote(tkhFailMsk));
//		      r.add("tarikh_Fail_Keluar", r.unquote(tkhFailKeluar));
//		      if (tarikhFailKeluar!= "" && tempohSdpDari == "" ){
//		    	  
//		    	  r.add("id_Status", rs.getLong(1));
//		      }
//		      else if (tarikhFailKeluar!= "" && tempohSdpDari != "" ){
//		    	  r.add("id_Status",rs.getLong(3));
//		      }
//		      else {
//		    	  r.add("id_Status","");
//		      }
//		      r.add("tempoh_Sdp_Dari", r.unquote(sdpDari));
//		      r.add("tempoh_Sdp_Hingga", r.unquote(sdpHingga));
//		      r.add("catatan", catatan);
//			
//			  sql = r.getSQLUpdate("tblpfdpergerakanfail");
//		      stmt.executeUpdate(sql);
//		      return idPergerakanfail;
//		    }
//		    finally {
//		      if (db != null) db.close();
//		    }
//	    }
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
				  "FROM TBLPFDPERGERAKANFAIL A, TBLRUJPEGAWAI B, TBLRUJPEGAWAI C " +
				  "WHERE A.ID_PEGAWAIPENERIMA = B.ID_PEGAWAI " +
				  "AND A.ID_PEGAWAIPENGHANTAR = C.ID_PEGAWAI " +
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

}

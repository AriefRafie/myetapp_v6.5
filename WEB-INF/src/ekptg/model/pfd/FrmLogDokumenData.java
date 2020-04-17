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
import ekptg.helpers.Utils;

public class FrmLogDokumenData {
	
	private static Vector listPegawaiKeluar = new Vector();
	private static Vector listPT = new Vector();
	private static Vector listLampiran = new Vector();
	private static Vector senaraiPTFail = new Vector();
	private static Vector senaraiPegawai = new Vector();
	private static Vector paparLogDokumen = null;
	private static Vector paparStatusLogDokumen = null;
	private static Vector paparPegawai = null;
	private static Vector paparPAPegawai = null;
	private static Vector paparPTFail = null;
	private static Vector paparPAR = null;
	private static Vector paparPegawaiSeksyenLain = null;
	private static Vector paparSeksyen = null;
	private static Vector paparUnit = null;
	private static Vector paparLampiranLogDokumen = null;
	private static Vector paparLampiranLogDokumenMasuk = null;
	private static Vector paparLampiranLogDokumenKeluar = null;
	private static Vector paparLampiranLogDokumenKeluarPapar = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void setListPegawai(String user_id,String user_negeri) throws Exception {
	    Db db = null;
	    senaraiPegawai.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	  /*    
	      sql = "select user_id,user_name from users where user_id in " +
	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
	      		"and id_jawatan in (0,1,2,3,4,5,6))";

	      sql = "select user_id,user_name from users where user_id in " +
    		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
    		"and id_jawatan in (0,1,2,3,4,5,6))";
    		
    		*/
	      
	      sql =  " SELECT U.user_id,initcap(U.user_name) as user_name FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN NOT IN (0,1,2,3)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk') ";	   
	      
	      
	      sql += "ORDER BY U.USER_NAME ASC ";

	      
	      System.out.println("SQL USER PENGARAH ::"+sql.toUpperCase());
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
			  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				

			  senaraiPegawai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListPegawai() {
		// TODO Auto-generated method stub
		return senaraiPegawai;
	}
	
	public static void setListPTFail(String user_id) throws Exception {
	    Db db = null;
	    senaraiPTFail.clear();
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "select distinct user_id,initcap(user_name) as user_name from users where user_id in " +
	      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
	      		"and id_negeri =(select id_negeri from users_internal where user_id="+user_id+")" +
	      		"and id_jawatan in (15,20,21,22,23,24,25,26,27,28)) order by user_name asc";
          
	      System.out.println("SQL USER PA ::"+sql.toUpperCase());
	      
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

	public static String addMasuk(Hashtable data)throws Exception {
		
		Db db = new Db();
		String sql = "";
		String sql2 = "";
		try
			{
			    	
			long idLogDokumen = DB.getNextID("TBLPFDLOGDOKUMEN_SEQ");
			long idTagDokumen = DB.getNextID("TBLPFDTAGDOKUMEN_SEQ");
			String id_Jenisdokumen = (String)data.get("id_Jenisdokumen");
			String no_Rujukan_Lain = (String)data.get("no_Rujukan_Lain");
			String idMinit = (String)data.get("idMinit");
		    String idLaporan = (String)data.get("idLaporan");
		    String idCD = (String)data.get("idCD");
			String tajuk_Dokumen = (String)data.get("tajuk_Dokumen");
			String pengirim_Dokumen = (String) data.get("pengirim_Dokumen")== null?"":(String)data.get("pengirim_Dokumen");
			String id_PenerimaDokumen = (String)data.get("id_PenerimaDokumen")== null?"":(String)data.get("id_PenerimaDokumen");
			//String id_PengirimDokumen = (String) data.get("id_PengirimDoku men")== null?"":(String)data.get("id_PengirimDokumen");
			//String penerima_Dokumen = (String)data.get("penerima_Dokumen")== null?"":(String)data.get("penerima_Dokumen");
			String tarikh_Dokumen = (String)data.get("tarikh_Dokumen");
			String tarikh_Dokumen_Diterima = (String)data.get("tarikh_Dokumen_Diterima");
			String id_PTFail = (String)data.get("id_PTFail");
			String idNegeri = (String)data.get("idNegeri");
			String idSeksyen = (String)data.get("idSeksyen");
			String flag_LogDokumen = (String)data.get("flag_LogDokumen");
			String status_LogDokumen = (String)data.get("status_LogDokumen");
			//String idFail = (String)data.get("id_Fail");
			String idMasuk = (String)data.get("id_Masuk");
			String tag_dokumen = (String)data.get("tag_dokumen");
				    			      
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				      
			r.add("ID_LOGDOKUMEN", idLogDokumen);
			r.add("ID_JENISDOKUMEN", id_Jenisdokumen);
			r.add("ID_MINIT", idMinit);
			r.add("ID_LAPORAN", idLaporan);
			r.add("CD", idCD);
			r.add("NO_RUJUKAN", no_Rujukan_Lain);
			r.add("TAJUK_DOKUMEN", tajuk_Dokumen);
			r.add("PENGIRIM_MASUK", pengirim_Dokumen);
			r.add("ID_PENERIMA_MASUK", id_PenerimaDokumen);
			//r.add("ID_PENGIRIM_KELUAR", id_PengirimDokumen);
			//r.add("PENERIMA_KELUAR", penerima_Dokumen);
			r.add("TARIKH_DOKUMEN", r.unquote("to_date('" + tarikh_Dokumen + "', 'dd/MM/yyyy')"));
			r.add("TARIKH_DOKUMENDITERIMA", r.unquote("to_date('" + tarikh_Dokumen_Diterima + "', 'dd/MM/yyyy')"));
			r.add("FLAG_LOGDOKUMEN", flag_LogDokumen);
			r.add("ID_SEKSYEN", idSeksyen);
			r.add("ID_NEGERI", idNegeri);
			r.add("STATUS_LOGDOKUMEN", status_LogDokumen);
			r.add("ID_PTFAIL",id_PTFail);
			r.add("id_Masuk",idMasuk);
			r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
			sql = r.getSQLInsert("TBLPFDLOGDOKUMEN");
				      
			stmt.executeUpdate(sql);
			
			db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.add("id_tagdokumen",idTagDokumen);
		      t.add("id_Dokumen",idLogDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Masuk",r.unquote("sysdate")); 
		      t.add("id_Masuk",idMasuk);
		      
  
		      sql2 = t.getSQLInsert("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
	  	      
				      
			return "" +idLogDokumen;
				      
		} finally {
			if (db != null) db.close();
		}
		

	}
	
	public static String addKeluar(Hashtable data)throws Exception {
		
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try
			{
				long idTagDokumen = DB.getNextID("TBLPFDTAGDOKUMEN_SEQ");
				long idLampiran = DB.getNextID("TBLPFDRUJLAMPIRANLOGDOK_SEQ");
			    String tkhDokDihantar = (String)data.get("tarikh_Dokumen_Dihantar");
			    String tarikhDokDihantar = "to_date('" + tkhDokDihantar + "','dd/MM/yyyy')";		     
			    String idKemaskini = (String)data.get("id_Kemaskini");
			    String idSeksyen = (String)data.get("idSeksyen");
			    String idNegeri = (String)data.get("idNegeri");
			    String idLogDokumen = (String)data.get("idLogDokumen");
			    String idDokumen = (String)data.get("idDokumen");
			    String status_logdokumen = (String)data.get("status_logdokumen");
			    String flag_logdokumen = (String)data.get("flag_LogDokumen");
			    String cara_Penghantaran = (String)data.get("jenis_Penghantaran");
			    String idMasuk = (String)data.get("id_Masuk");
			    String tag_dokumen = (String)data.get("tag_dokumen");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();

			  sql = "UPDATE   tblpfdlogdokumen SET   (id_jenisdokumen, tajuk_dokumen, id_pengirim_keluar, penerima_keluar, tarikh_dokumen, " +
	      			"flag_logdokumen, no_rujukan, status_logdokumen, id_minit, id_laporan, cd, tarikh_dokumendihantar, id_negeri, id_seksyen, cara_penghantaran,id_masuk,tarikh_masuk) =" +
	      	    	"(SELECT id_jenisdokumen, tajuk_dokumen, id_namapengirim, nama_penerima, tarikh_dokumen,'"+flag_logdokumen+"', no_rujukan_dokumen,'"+status_logdokumen+"', " +
	      	    	"id_minit, id_laporan, cd, "+tarikhDokDihantar+", '"+idNegeri+"', '"+idSeksyen+"', '"+cara_Penghantaran+"','"+idMasuk+"',sysdate " +
	      	    	"FROM TBLPFDDOKUMEN WHERE   ID_LOGDOKUMEN = '"+idLogDokumen+"')"+
	      	    	"WHERE ID_LOGDOKUMEN = '"+idLogDokumen+"'";
			  
		      stmt.executeUpdate(sql); 

		      sql1 = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_lampiran,id_logdokumen, content, nama_fail, jenis_mime)"+
	    	  		 " (SELECT '"+idLampiran+"','"+idLogDokumen+"',content, nama_fail, jenis_mime from tblpfdrujlampiran WHERE id_dokumen = "+idDokumen+")";
		    	
			  stmt.executeUpdate(sql1);    

		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.add("id_tagdokumen",idTagDokumen);
		      t.add("id_Dokumen",idLogDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Masuk",t.unquote("sysdate")); 
		      t.add("id_Masuk",idMasuk);
		      
  
		      sql2 = t.getSQLInsert("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
		      
		      return "" +idLogDokumen;
		      
		} finally {
			if (db != null) db.close();
		}
		

	}

public static String addDalaman(Hashtable data)throws Exception {
		
		Db db = new Db();
		String sql = "";
		String sql1 = "";
		String sql2 = "";
		try
			{
				long idTagDokumen = DB.getNextID("TBLPFDTAGDOKUMEN_SEQ");
				long idLampiran = DB.getNextID("TBLPFDRUJLAMPIRANLOGDOK_SEQ");
			    String tkhDokDihantar = (String)data.get("tarikh_Dokumen_Dihantar");
			    String tarikhDokDihantar = "to_date('" + tkhDokDihantar + "','dd/MM/yyyy')";		     
			    String idKemaskini = (String)data.get("id_Kemaskini");
			    String idSeksyen = (String)data.get("idSeksyen");
			    String idNegeri = (String)data.get("idNegeri");
			    String idLogDokumen = (String)data.get("idLogDokumen");
			    String idDokumen = (String)data.get("idDokumen");
			    String status_logdokumen = (String)data.get("status_logdokumen");
			    String flag_logdokumen = (String)data.get("flag_LogDokumen");
			    String cara_Penghantaran = (String)data.get("jenis_Penghantaran");
			    String idMasuk = (String)data.get("id_Masuk");
			    String tag_dokumen = (String)data.get("tag_dokumen");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();

			  sql = "UPDATE   tblpfdlogdokumen SET   (id_jenisdokumen, tajuk_dokumen, id_pengirim_keluar, penerima_keluar, tarikh_dokumen, " +
	      			"flag_logdokumen, no_rujukan, status_logdokumen, id_minit, id_laporan, cd, tarikh_dokumendihantar, id_negeri, id_seksyen, cara_penghantaran,id_masuk,tarikh_masuk) =" +
	      	    	"(SELECT id_jenisdokumen, tajuk_dokumen, id_namapengirim, nama_penerima, tarikh_dokumen,'"+flag_logdokumen+"', no_rujukan_dokumen,'"+status_logdokumen+"', " +
	      	    	"id_minit, id_laporan, cd, "+tarikhDokDihantar+", '"+idNegeri+"', '"+idSeksyen+"', '"+cara_Penghantaran+"','"+idMasuk+"',sysdate " +
	      	    	"FROM TBLPFDDOKUMEN WHERE   ID_LOGDOKUMEN = '"+idLogDokumen+"')"+
	      	    	"WHERE ID_LOGDOKUMEN = '"+idLogDokumen+"'";
			  
		      stmt.executeUpdate(sql); 

		      sql1 = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_lampiran,id_logdokumen, content, nama_fail, jenis_mime)"+
	    	  		 " (SELECT '"+idLampiran+"','"+idLogDokumen+"',content, nama_fail, jenis_mime from tblpfdrujlampiran WHERE id_dokumen = "+idDokumen+")";
		    	
			  stmt.executeUpdate(sql1);    

		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.add("id_tagdokumen",idTagDokumen);
		      t.add("id_Dokumen",idLogDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Masuk",t.unquote("sysdate")); 
		      t.add("id_Masuk",idMasuk);
		      
  
		      sql2 = t.getSQLInsert("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
		      
		      return "" +idLogDokumen;
		      
		} finally {
			if (db != null) db.close();
		}
		

	}



	public static Vector getDataLogDokumen(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_LOGDOKUMEN, A.ID_LOGDOKUMENKPTG, A.ID_MINIT, A.ID_LAPORAN, A.CD, A.ID_JENISDOKUMEN, A.NO_RUJUKAN, A.TAJUK_DOKUMEN, A.PENGIRIM_MASUK, A.ID_PENERIMA_MASUK, A.ID_PENGIRIM_KELUAR, " +
		      		"A.PENERIMA_KELUAR, A.TARIKH_DOKUMEN, A.TARIKH_DOKUMENDITERIMA, A.TARIKH_DOKUMENDIHANTAR, A.FLAG_LOGDOKUMEN, A.STATUS_LOGDOKUMEN, A.CARA_PENGHANTARAN,A.CATATAN_MINIT,A.ID_PTFAIL, C.TAG_DOKUMEN, C.ID_TAGDOKUMEN FROM TBLPFDLOGDOKUMEN A, USERS B, TBLPFDTAGDOKUMEN C WHERE A.ID_LOGDOKUMEN = C.ID_DOKUMEN(+) AND B.USER_ID(+) = A.ID_PTFAIL AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      
		      
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idLogDokumen", rs.getString("ID_LOGDOKUMEN")==null?"":rs.getString("ID_LOGDOKUMEN"));
		    	  h.put("idLogDokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("idMinit", rs.getString("ID_MINIT")==null?"":rs.getString("ID_MINIT"));
		    	  h.put("idLaporan", rs.getString("ID_LAPORAN")==null?"":rs.getString("ID_LAPORAN"));
		    	  h.put("idCD", rs.getString("CD")==null?"":rs.getString("CD"));
		    	  h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")==null?"":rs.getString("NO_RUJUKAN"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("pengirim_Dokumen", rs.getString("PENGIRIM_MASUK")==null?"":rs.getString("PENGIRIM_MASUK"));
		    	  h.put("id_penerima", rs.getString("ID_PENERIMA_MASUK")==null?"":rs.getString("ID_PENERIMA_MASUK"));
		    	  h.put("id_pengirim", rs.getString("ID_PENGIRIM_KELUAR")==null?"":rs.getString("ID_PENGIRIM_KELUAR"));
		    	  h.put("penerima_Dokumen", rs.getString("PENERIMA_KELUAR")==null?"":rs.getString("PENERIMA_KELUAR"));
		    	  h.put("tarikh_Dokumen", rs.getDate("TARIKH_DOKUMEN")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("tarikh_Dokumen_Diterima", rs.getDate("TARIKH_DOKUMENDITERIMA")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMENDITERIMA")));
		    	  h.put("tarikh_Dokumen_Dihantar", rs.getDate("TARIKH_DOKUMENDIHANTAR")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMENDIHANTAR")));
		    	  h.put("flag_logdokumen", rs.getString("FLAG_LOGDOKUMEN")==null?"":rs.getString("FLAG_LOGDOKUMEN"));
		    	  h.put("status_LogDokumen", rs.getString("STATUS_LOGDOKUMEN")==null?"":rs.getString("STATUS_LOGDOKUMEN"));
		    	  h.put("cara_Penghantaran", rs.getString("CARA_PENGHANTARAN")==null?"":rs.getString("CARA_PENGHANTARAN"));	 
		    	  h.put("catatan", rs.getString("CATATAN_MINIT")==null?"":rs.getString("CATATAN_MINIT"));
		    	  h.put("idPTFail", rs.getString("ID_PTFAIL")==null?"":rs.getString("ID_PTFAIL"));
		    	  h.put("tag_Dokumen",rs.getString("tag_Dokumen")== null?"":rs.getString("tag_Dokumen"));
		    	  h.put("id_tagdokumen",rs.getString("id_tagdokumen")== null?"":rs.getString("id_tagdokumen"));
		    	  paparLogDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparLogDokumen.addElement(h);
		    	  
		      }
		      
		      return paparLogDokumen;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
		
	}
	
	public static Vector getDataLogDokumenKPTG(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_LOGDOKUMENKPTG, A.ID_MINIT, A.ID_LAPORAN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN, A.TAJUK_DOKUMEN, A.PENGIRIM_MASUK, A.ID_PENERIMA_MASUK, A.ID_PENGIRIM_KELUAR, " +
		      		"A.PENERIMA_KELUAR, A.TARIKH_DOKUMEN, A.TARIKH_DOKUMENDITERIMA, A.TARIKH_DOKUMENDIHANTAR, A.FLAG_LOGDOKUMEN, A.STATUS_LOGDOKUMEN, A.CARA_PENGHANTARAN,A.CATATAN_MINIT FROM TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PTFAIL AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idLogDokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("idMinit", rs.getString("ID_MINIT")==null?"":rs.getString("ID_MINIT"));
		    	  h.put("idLaporan", rs.getString("ID_LAPORAN")==null?"":rs.getString("ID_LAPORAN"));
		    	  h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")==null?"":rs.getString("NO_RUJUKAN"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("pengirim_Dokumen", rs.getString("PENGIRIM_MASUK")==null?"":rs.getString("PENGIRIM_MASUK"));
		    	  h.put("id_penerima", rs.getString("ID_PENERIMA_MASUK")==null?"":rs.getString("ID_PENERIMA_MASUK"));
		    	  h.put("id_pengirim", rs.getString("ID_PENGIRIM_KELUAR")==null?"":rs.getString("ID_PENGIRIM_KELUAR"));
		    	  h.put("penerima_Dokumen", rs.getString("PENERIMA_KELUAR")==null?"":rs.getString("PENERIMA_KELUAR"));
		    	  h.put("tarikh_Dokumen", rs.getDate("TARIKH_DOKUMEN")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("tarikh_Dokumen_Diterima", rs.getDate("TARIKH_DOKUMENDITERIMA")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMENDITERIMA")));
		    	  h.put("tarikh_Dokumen_Dihantar", rs.getDate("TARIKH_DOKUMENDIHANTAR")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMENDIHANTAR")));
		    	  h.put("flag_logdokumen", rs.getString("FLAG_LOGDOKUMEN")==null?"":rs.getString("FLAG_LOGDOKUMEN"));
		    	  h.put("status_LogDokumen", rs.getString("STATUS_LOGDOKUMEN")==null?"":rs.getString("STATUS_LOGDOKUMEN"));
		    	  h.put("cara_Penghantaran", rs.getString("CARA_PENGHANTARAN")==null?"":rs.getString("CARA_PENGHANTARAN"));	 
		    	  h.put("catatan", rs.getString("CATATAN_MINIT")==null?"":rs.getString("CATATAN_MINIT"));	
		    	  paparLogDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparLogDokumen.addElement(h);
		    	  
		      }
		      
		      return paparLogDokumen;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
		
	}

	public static Vector getDataPegawai(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PENERIMA_MASUK AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		     
		      
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

	public static Vector getDataPTFail(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPTFail = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PTFAIL AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      
		      
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPTFail.addElement(h); 
		      }
		      
		      return paparPTFail;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static String updateMasuk(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		String sql2 = "";
		try
			{
			    	
			String idLogDokumen = (String)data.get("idLogDokumen");
			String idFail = (String)data.get("id_Fail");
			String id_Jenisdokumen = (String)data.get("id_Jenisdokumen");
			String no_Rujukan_Lain = (String)data.get("no_Rujukan_Lain");
			String idMinit = (String)data.get("idMinit");
		    String idLaporan = (String)data.get("idLaporan");
		    String idCD = (String)data.get("idCD");
			String tajuk_Dokumen = (String)data.get("tajuk_Dokumen");
			String pengirim_Dokumen = (String) data.get("pengirim_Dokumen")== null?"":(String)data.get("pengirim_Dokumen");
			String id_PenerimaDokumen = (String)data.get("id_PenerimaDokumen")== null?"":(String)data.get("id_PenerimaDokumen");
			//String id_PengirimDokumen = (String) data.get("id_PengirimDoku men")== null?"":(String)data.get("id_PengirimDokumen");
			//String penerima_Dokumen = (String)data.get("penerima_Dokumen")== null?"":(String)data.get("penerima_Dokumen");
			String tarikh_Dokumen = (String)data.get("tarikh_Dokumen");
			String tarikh_Dokumen_Diterima = (String)data.get("tarikh_Dokumen_Diterima");
			String id_PTFail = (String)data.get("id_PTFail");
			String idNegeri = (String)data.get("idNegeri");
			String idSeksyen = (String)data.get("idSeksyen");
			String flag_LogDokumen = (String)data.get("flag_LogDokumen");
			String status_LogDokumen = (String)data.get("status_LogDokumen");
			String tag_dokumen = (String)data.get("tag_dokumen");
			String id_tagdokumen = (String)data.get("id_tagdokumen");

//			String id_Jenisdokumen = (String)data.get("id_Jenisdokumen")== null?"":(String)data.get("id_Jenisdokumen");
//			String no_Rujukan_Lain = (String)data.get("no_Rujukan_Lain")== null?"":(String)data.get("no_Rujukan_Lain");
//			String tajuk_Dokumen = (String)data.get("tajuk_Dokumen")== null?"":(String)data.get("tajuk_Dokumen");
//			String pengirim_Dokumen = (String) data.get("pengirim_Dokumen")== null?"":(String)data.get("pengirim_Dokumen");
//			String id_PenerimaDokumen = (String)data.get("id_PenerimaDokumen")== null?"":(String)data.get("id_PenerimaDokumen");
//			String id_PengirimDokumen = (String) data.get("id_PengirimDokumen")== null?"":(String)data.get("id_PengirimDokumen");
//			String penerima_Dokumen = (String)data.get("penerima_Dokumen")== null?"":(String)data.get("penerima_Dokumen");
//			String tarikh_Dokumen = (String)data.get("tarikh_Dokumen");
//			String tarikh_Dokumen_Diterima = (String)data.get("tarikh_Dokumen_Diterima");
//			String id_PTFail = (String)data.get("id_PTFail")== null?"":(String)data.get("id_PTFail");
//			String flag_LogDokumen = (String)data.get("flag_LogDokumen")== null?"":(String)data.get("flag_LogDokumen");
			String id_Kemaskini = (String)data.get("id_Kemaskini");
				    			      
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				      
			r.update("ID_LOGDOKUMEN", idLogDokumen);
			r.add("ID_JENISDOKUMEN", id_Jenisdokumen);
			r.add("ID_MINIT", idMinit);
			r.add("ID_LAPORAN", idLaporan);
			r.add("CD", idCD);
			r.add("NO_RUJUKAN", no_Rujukan_Lain);
			r.add("TAJUK_DOKUMEN", tajuk_Dokumen);
			r.add("PENGIRIM_MASUK", pengirim_Dokumen);
			r.add("ID_PENERIMA_MASUK", id_PenerimaDokumen);
			//r.add("ID_PENGIRIM_KELUAR", id_PengirimDokumen);
			//r.add("PENERIMA_KELUAR", penerima_Dokumen);
			r.add("TARIKH_DOKUMEN", r.unquote("to_date('" + tarikh_Dokumen + "', 'dd/MM/yyyy')"));
			r.add("TARIKH_DOKUMENDITERIMA", r.unquote("to_date('" + tarikh_Dokumen_Diterima + "', 'dd/MM/yyyy')"));
			r.add("FLAG_LOGDOKUMEN", flag_LogDokumen);
			r.add("ID_SEKSYEN", idSeksyen);
			r.add("ID_NEGERI", idNegeri);
			r.add("STATUS_LOGDOKUMEN", status_LogDokumen);
			r.add("ID_PTFAIL",id_PTFail);
			r.add("id_Kemaskini",id_Kemaskini);
			r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				  
			sql = r.getSQLUpdate("TBLPFDLOGDOKUMEN");
				      
			stmt.executeUpdate(sql);
			
			 db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.update("id_tagdokumen",id_tagdokumen);
		      t.add("id_Dokumen",idLogDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Kemaskini",r.unquote("sysdate")); 
		      t.add("id_Kemaskini",id_Kemaskini);
		      
   
		      sql2 = t.getSQLUpdate("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
		      
				      
			return "" +idLogDokumen;
				      
		} finally {
			if (db != null) db.close();
		}
	}

	public static String getListStatusLogDokumen(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT STATUS_LOGDOKUMEN " +
		      		"FROM  TBLPFDLOGDOKUMEN WHERE ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("status_logdokumen")==null?"":rs.getString("status_logdokumen");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static void setSeksyen(String user_id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparSeksyen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select a.id_seksyen, a.id_negeri,b.kod_seksyen, b.nama_seksyen, b.kod_baru_seksyen, c.kod_negeri, c.nama_negeri from users_internal a, tblrujseksyen b,tblrujnegeri c where a.id_negeri = c.id_negeri and a.id_seksyen = b.id_seksyen and user_id = '"+user_id+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  h.put("kod_seksyen", rs.getString("kod_seksyen")==null?"":rs.getString("kod_seksyen"));
		    	  h.put("nama_seksyen", rs.getString("nama_seksyen")==null?"":rs.getString("nama_seksyen"));
		    	  h.put("kod_baru_seksyen", rs.getString("kod_baru_seksyen")==null?"":rs.getString("kod_baru_seksyen"));
		    	  h.put("kod_negeri", rs.getString("kod_negeri")==null?"":rs.getString("kod_negeri"));
		    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
		    	  paparSeksyen.addElement(h); 
		      }
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
		
	}

	public static Vector getDataSeksyen() {
		// TODO Auto-generated method stub
		return paparSeksyen;
	}
	
	public static void setUnit(String user_id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparUnit = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "SELECT  B.ID_PEJABATJKPTG,B.NAMA_PEJABAT FROM USERS_INTERNAL A, TBLRUJPEJABATJKPTG B WHERE A.ID_PEJABATJKPTG = B.ID_PEJABATJKPTG AND A.USER_ID = '"+user_id+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("NAMA_PEJABAT", rs.getString("NAMA_PEJABAT")==null?"":rs.getString("NAMA_PEJABAT"));
		    	  h.put("ID_PEJABATJKPTG", rs.getString("ID_PEJABATJKPTG")==null?"":rs.getString("ID_PEJABATJKPTG"));
		    	 
		    	  paparUnit.addElement(h); 
		      }
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
		
	}

	public static Vector getDataUnit() {
		// TODO Auto-generated method stub
		return paparUnit;
	}


	public static void setLampiranLogDokumen(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogdok where id_logdokumen = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_logdokumen", rs.getString("id_logdokumen")==null?"":rs.getString("id_logdokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranLogDokumen.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }  
		
	}

	public static Vector getListLampiranLogDokumen() {
		// TODO Auto-generated method stub
		return paparLampiranLogDokumen;
	}

	public static Vector getDataPegawaiKeluar(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 listPegawaiKeluar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PENGIRIM_KELUAR AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  listPegawaiKeluar.addElement(h); 
		      }
		      
		      return listPegawaiKeluar;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getListLampiranLogDokumenMasuk(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenMasuk = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogdok where id_logdokumen = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_logdokumen", rs.getString("id_logdokumen")==null?"":rs.getString("id_logdokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranLogDokumenMasuk.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      return paparLampiranLogDokumenMasuk;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getDataPT(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 listPT = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PTFAIL AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  listPT.addElement(h); 
		      }
		      
		      return listPT;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static void updateLogDokumenKeluar(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  String idLogDokumen = (String)data.get("idLogDokumen");
		      String tkhDokDihantar = (String)data.get("tarikh_Dokumen_Dihantar");
		      String tarikhDokDihantar = "to_date('" + tkhDokDihantar + "','dd/MM/yyyy')";	
		      String tkhDok = (String)data.get("tarikh_Dokumen");
		      String tarikhDok = "to_date('" + tkhDok + "','dd/MM/yyyy')";	
		      String id_JenisDokumen = (String)data.get("id_JenisDokumen");
		      String id_PenghantarKeluar = (String)data.get("id_PenghantarKeluar");
		      String no_Rujukan = (String)data.get("no_Rujukan");
		      String tajuk_Dokumen = (String)data.get("tajuk_Dokumen");
		      String penerima_Dokumen = (String)data.get("penerima_Dokumen");
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String idSeksyen = (String)data.get("idSeksyen");
			  String idNegeri = (String)data.get("idNegeri");
			  String idMinit = (String)data.get("idMinit");
		      String idLaporan = (String)data.get("idLaporan");
		      String idCD = (String)data.get("idCD");
			  //String idDokumen = (String)data.get("idDokumen");
			  String status_logdokumen = (String)data.get("status_logdokumen");
			  String cara_Penghantaran = (String)data.get("cara_Penghantaran");
			  String tag_dokumen = (String)data.get("tag_dokumen");
			  String id_tagdokumen = (String)data.get("id_tagdokumen");
			  String id_Kemaskini = (String)data.get("id_Kemaskini");
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_LogDokumen", idLogDokumen);
		      r.add("status_logdokumen",status_logdokumen);
		      r.add("id_seksyen",idSeksyen);
		      r.add("id_negeri",idNegeri);
			  r.add("id_minit",idMinit); 
			  r.add("id_laporan",idLaporan); 
			  r.add("cd",idCD); 
			  r.add("id_jenisDokumen",id_JenisDokumen); 
			  r.add("tarikh_dokumen",r.unquote(tarikhDok)); 
			  r.add("tarikh_dokumendihantar",r.unquote(tarikhDokDihantar)); 
			  r.add("no_Rujukan",no_Rujukan); 
			  r.add("tajuk_Dokumen",tajuk_Dokumen); 
			  r.add("penerima_keluar",penerima_Dokumen); 
			  r.add("id_pengirim_keluar",id_PenghantarKeluar);
			  r.add("cara_penghantaran",cara_Penghantaran);
			  
			  sql = r.getSQLUpdate("tblpfdlogdokumen");
		      stmt.executeUpdate(sql);
		      
		      db = new Db();
		      Statement stmt2 = db.getStatement();
		      SQLRenderer t = new SQLRenderer();
		      
		      t.update("id_tagdokumen",id_tagdokumen);
		      t.add("id_Dokumen",idLogDokumen);
		      t.add("tag_dokumen",tag_dokumen);
		      t.add("tarikh_Kemaskini",t.unquote("sysdate")); 
		      t.add("id_Kemaskini",id_Kemaskini);
		      
   
		      sql2 = t.getSQLUpdate("tblpfdtagdokumen");  
	  	      stmt2.executeUpdate(sql2);
		    }
		    finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListLogDokumenMasuk() {
		// TODO Auto-generated method stub
		return null;
	}


	public static Vector getListLampiranLogDokumenKeluar(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenKeluar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_dokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiran where id_dokumen = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("idDokumen", rs.getString("id_dokumen")==null?"":rs.getString("id_dokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranLogDokumenKeluar.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      return paparLampiranLogDokumenKeluar;
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getListLampiranLogDokumenKeluarPapar(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenKeluarPapar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumen, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogdok where id_logdokumen = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("idLogDokumen", rs.getString("id_logdokumen")==null?"":rs.getString("id_logdokumen"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranLogDokumenKeluarPapar.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      return paparLampiranLogDokumenKeluarPapar;
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPegawaiSeksyenLain(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawaiSeksyenLain = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ARAHAN_DARI AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawaiSeksyenLain.addElement(h); 
		      }
		      
		      return paparPegawaiSeksyenLain;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static void updateLogDokumenSeksyenLain(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  String idLogDokumen = (String)data.get("idLogDokumen");
	    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
		      String tkhDokDiterima = (String)data.get("tarikh_Dokumen_Diterima");
		      String tarikhDokDiterima = "to_date('" + tkhDokDiterima + "','dd/MM/yyyy')";		     
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String idSeksyen = (String)data.get("idSeksyen");
			  String idNegeri = (String)data.get("idNegeri");
			  String id_ptfail = (String)data.get("id_ptfail");
			  String idDokumen = (String)data.get("idDokumen");
			  String id_nama_penerima = (String)data.get("id_nama_penerima");
			  String status_logdokumen = (String)data.get("status_logdokumen");
			 // String cara_Penghantaran = (String)data.get("cara_Penghantaran");
			  
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_LogDokumen", idLogDokumen);
		      r.add("tarikh_DokumenDiterima", r.unquote(tarikhDokDiterima));
		      r.add("status_logdokumen",status_logdokumen);
		      r.add("id_penerima_masuk",id_nama_penerima);
		      r.add("id_ptfail",id_ptfail);
		      r.add("id_seksyen",idSeksyen);
		      r.add("id_negeri",idNegeri);
		      r.add("flag_logdokumen",1);
			 // r.add("cara_penghantaran",cara_Penghantaran); 
			
			  sql = r.getSQLUpdate("tblpfdlogdokumen");
		      stmt.executeUpdate(sql);
		      
		      String sqlInsert = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_logdokumen, content, nama_fail, jenis_mime)";
			      String sqlSelect = "SELECT '"+idLogDokumen+"',content, nama_fail, jenis_mime from tblpfdrujlampiran WHERE id_dokumen = "+idDokumen+"";
			      
			      sql1 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql1);

//				  String sqlInsert2 = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_logdokumen, content, nama_fail, jenis_mime)";
//			      String sqlSelect2 = "SELECT '"+idLogDokumen+"',content, nama_fail, jenis_mime from tblpfdrujlampiranKPTG WHERE id_logdokumenKPTG = "+idLogDokumenKPTG+"";
//			      
//			      sql2 = sqlInsert2+" "+sqlSelect2;
//			      stmt.executeUpdate(sql2);  

			     
		 

		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		
	}

	public static void updateLogDokumenSeksyenKPTG(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  String idLogDokumen = (String)data.get("idLogDokumen");
	    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
		      String tkhDokDiterima = (String)data.get("tarikh_Dokumen_Diterima");
		      String tarikhDokDiterima = "to_date('" + tkhDokDiterima + "','dd/MM/yyyy')";		     
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String idSeksyen = (String)data.get("idSeksyen");
			  String idNegeri = (String)data.get("idNegeri");
			  String id_ptfail = (String)data.get("id_ptfail");
			  String idDokumen = (String)data.get("idDokumen");
			  String id_nama_penerima = (String)data.get("id_nama_penerima");
			  String status_logdokumen = (String)data.get("status_logdokumen");
			 // String cara_Penghantaran = (String)data.get("cara_Penghantaran");
			  
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_LogDokumen", idLogDokumen);
		      r.add("tarikh_DokumenDiterima", r.unquote(tarikhDokDiterima));
		      r.add("status_logdokumen",status_logdokumen);
		      r.add("id_penerima_masuk",id_nama_penerima);
		      r.add("id_ptfail",id_ptfail);
		      r.add("id_seksyen",idSeksyen);
		      r.add("id_negeri",idNegeri);
		      r.add("flag_logdokumen",1);
			 // r.add("cara_penghantaran",cara_Penghantaran); 
			
			  sql = r.getSQLUpdate("tblpfdlogdokumen");
		      stmt.executeUpdate(sql);
		      

				  String sqlInsert2 = "INSERT INTO TBLPFDRUJLAMPIRANLOGDOK (id_logdokumen, content, nama_fail, jenis_mime)";
			      String sqlSelect2 = "SELECT '"+idLogDokumen+"',content, nama_fail, jenis_mime from tblpfdrujlampiranlogKPTG WHERE id_logdokumenKPTG = "+idLogDokumenKPTG+"";
			      
			      sql2 = sqlInsert2+" "+sqlSelect2;
			      stmt.executeUpdate(sql2);  

			     
		 

		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getListLampiranLogDokumenKPTG(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenKeluar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumenkptg, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogkptg where id_logdokumenkptg = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_logdokumenkptg", rs.getString("id_logdokumenkptg")==null?"":rs.getString("id_logdokumenkptg"));
		    	  h.put("content", rs.getString("content")==null?"":rs.getString("content"));
		    	  h.put("nama_fail", rs.getString("nama_fail")==null?"":rs.getString("nama_fail"));
		    	  h.put("jenis_mime", rs.getString("jenis_mime")==null?"":rs.getString("jenis_mime"));
		    	  paparLampiranLogDokumenKeluar.addElement(h);
		    	  System.out.println(h);
		    	  bil++;
		    	  count++;
		      }
		      return paparLampiranLogDokumenKeluar;
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPegawaiSeksyenKPTG(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawaiSeksyenLain = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMEN A, USERS B WHERE B.USER_ID = A.ARAHAN_DARI AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawaiSeksyenLain.addElement(h); 
		      }
		      
		      return paparPegawaiSeksyenLain;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }

	}

	public static Vector getDataPAPegawai(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_SETIAUSAHA AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPAPegawai.addElement(h); 
		      }
		      
		      return paparPAPegawai;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPARKeluar(String idDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PAR AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPAR.addElement(h); 
		      }
		      
		      return paparPAR;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static void deleteLogDokumenMasuk(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  String idLogDokumen = (String)data.get("idLogDokumen");	     
			  String idKemaskini = (String)data.get("id_Kemaskini");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  
	
			  sql = "DELETE from tblpfdlogdokumen where id_logdokumen = '"+idLogDokumen+"'";		     
			  stmt.executeUpdate(sql);  
			  
			  sql1 = "DELETE from TBLPFDRUJLAMPIRANLOGDOK where id_logdokumen = '"+idLogDokumen+"'";		     
			  stmt.executeUpdate(sql1);  

		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		
	}

	public static String checkPTFail(String idLogDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select id_ptfail from tblpfdlogdokumen where id_logdokumen = '"+idLogDokumen+"'" ;
   
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("id_ptfail")==null?"":rs.getString("id_ptfail");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static String getEmailPTFail(String idPTFail) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPTFail+"'" ;
  
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  returnValue = rs.getString("emel")==null?"":rs.getString("emel");
		      }
		      
		      return returnValue;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	 public static void  setListLampiran(String id)throws Exception {
		    Db db = null;
		    String sql = "";
		    String sql1 = "";
		    try {
		    	listLampiran = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		      r.add("a.id_Lampiran");
		      r.add("a.nama_Fail");
		      r.add("a.jenis_Mime");
		     
		      
		      r.add("a.ID_LOGDOKUMEN",id);

		      sql = r.getSQLSelect("TBLPFDRUJLAMPIRANLOGDOK a");
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      

		      
		      
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_Lampiran", rs.getString("id_Lampiran"));
		    	  h.put("nama_Fail",rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
		    	  h.put("jenis_Mime", rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
		    	  listLampiran.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil","");
		    	  h.put("id_Lampiran", "");
		    	  h.put("nama_Fail","Tiada rekod.");
		    	  h.put("jenis_Mime", "");
		    	  listLampiran.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		}
	public static Vector getListLampiran(){
			 
		return listLampiran;
	}
	
	public static void hapus(String idLampiran) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
//			//TBLPFDMINITARAHAN
//			r.add("ID_DOKUMEN", idDokumen);
//			sql = r.getSQLDelete("TBLPFDMINITARAHAN");
//			stmt.executeUpdate(sql);
			
			//TBLPFDRUJLAMPIRANLOGDOK
			r.add("ID_LAMPIRAN", idLampiran);
			sql = r.getSQLDelete("TBLPFDRUJLAMPIRANLOGDOK");
			stmt.executeUpdate(sql);
			
			//TBLPFDDOKUMEN
//			r.add("ID_DOKUMEN", idDokumen);
//			sql = r.getSQLDelete("TBLPFDDOKUMEN");
//			stmt.executeUpdate(sql);

		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Vector getDataDokumen(String idLogDokumen) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		    //  select id_dokumen,id_logdokumen,id_minit,id_laporan,cd,id_jenisdokumen,no_rujukan_dokumen, tajuk_dokumen, nama_penerima, tarikh_dokumen from tblpfddokumen where id_logdokumen = 1610619
		      sql = "SELECT A.ID_DOKUMEN, A.ID_LOGDOKUMEN, A.ID_MINIT, A.ID_LAPORAN, A.CD, A.ID_JENISDOKUMEN, A.NO_RUJUKAN_DOKUMEN, A.TAJUK_DOKUMEN, A.NAMA_PENERIMA, A.TARIKH_DOKUMEN, B.TAG_DOKUMEN, B.ID_TAGDOKUMEN " +
		      		"FROM TBLPFDDOKUMEN A, TBLPFDTAGDOKUMEN B WHERE B.ID_DOKUMEN(+)=A.ID_DOKUMEN AND A.ID_LOGDOKUMEN = '"+idLogDokumen+"'";
		      
		      System.out.println("sql ::::::::::: "+sql.toUpperCase());
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idLogDokumen", rs.getString("ID_LOGDOKUMEN")==null?"":rs.getString("ID_LOGDOKUMEN"));
		    	  h.put("idDokumen", rs.getString("ID_DOKUMEN")==null?"":rs.getString("ID_DOKUMEN"));
		    	  h.put("idMinit", rs.getString("ID_MINIT")==null?"":rs.getString("ID_MINIT"));
		    	  h.put("idLaporan", rs.getString("ID_LAPORAN")==null?"":rs.getString("ID_LAPORAN"));
		    	  h.put("idCD", rs.getString("CD")==null?"":rs.getString("CD"));
		    	  h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN_DOKUMEN")==null?"":rs.getString("NO_RUJUKAN_DOKUMEN"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("penerima_Dokumen", rs.getString("NAMA_PENERIMA")==null?"":rs.getString("NAMA_PENERIMA"));
		    	  h.put("tarikh_Dokumen", rs.getDate("TARIKH_DOKUMEN")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));	
		    	  h.put("tag_Dokumen",rs.getString("TAG_DOKUMEN")== null?"":rs.getString("TAG_DOKUMEN"));
		    	  h.put("id_tagdokumen",rs.getString("ID_TAGDOKUMEN")==null?"":rs.getString("ID_TAGDOKUMEN"));
		    	  paparLogDokumen.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparLogDokumen.addElement(h);
		    	  
		      }
		      
		      return paparLogDokumen;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
		
	}

	public static Vector getDataPegawaiKeluarBaru(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 listPegawaiKeluar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_NAMAPENGIRIM AND A.ID_DOKUMEN = '"+idDokumen+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();

		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  listPegawaiKeluar.addElement(h); 
		      }
		      
		      return listPegawaiKeluar;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getDataPTKeluar(String idDokumen) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDDOKUMEN A, USERS B WHERE B.USER_ID = A.ID_PAR AND A.ID_DOKUMEN = '"+idDokumen+"'";
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
	public static void deleteLogDokumenKeluar(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  String idLogDokumen = (String)data.get("idLogDokumen");	     
			  String idKemaskini = (String)data.get("id_Kemaskini");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  
	
			  sql = "DELETE from tblpfdlogdokumen where id_logdokumen = '"+idLogDokumen+"'";		     
			  stmt.executeUpdate(sql);  
			  
			  sql1 = "DELETE from TBLPFDRUJLAMPIRANLOGDOK where id_logdokumen = '"+idLogDokumen+"'";		     
			  stmt.executeUpdate(sql1);  

		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		
	}


}

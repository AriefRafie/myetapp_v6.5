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

public class FrmLogDokumenTKPKData {

	private static Vector paparSeksyen = null;
	private static Vector paparTKP = null;
	private static Vector paparPAR = null;
	private static Vector paparPAR1 = null;
	private static Vector paparPAR2 = null;
	private static Vector paparPAR3 = null;
	private static Vector paparPAR4 = null;
	private static Vector paparPAR5 = null;
	private static Vector paparPegawaiSeksyen1 = null;
	private static Vector paparPegawaiSeksyen2 = null;
	private static Vector paparPegawaiSeksyen3 = null;
	private static Vector paparPegawaiSeksyenLain = null;
	private static Vector paparPARSeksyen1 = null;
	private static Vector paparPARSeksyen2 = null;
	private static Vector paparPARSeksyen3 = null;
	private static Vector paparPegawai = null;
	private static Vector paparLogDokumen = null;
	private static Vector paparMinitArahanSeksyen1 = null;
	private static Vector paparMinitArahanSeksyen2 = null;
	private static Vector paparMinitArahanSeksyen3 = null;
	private static Vector paparLampiranLogDokumenMasuk = null;
	private static Vector paparLampiranLogDokumenKeluar = null;
	private static Vector senaraiTKP = new Vector();
	private static Vector senaraiPegawai = new Vector();
	private static Vector senaraiPengarahSeksyen = new Vector();
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private static Vector senaraiMinitPengarah = null;
	private static Vector listLampiran = null;
	
	public static void setSeksyen(String user_id) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparSeksyen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+user_id+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
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

	public static void setListTKP(String user_id) throws Exception {
		 Db db = null;
		    senaraiTKP.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select user_id,user_name from users where user_id in " +
		      		"(select user_id from users_internal where id_seksyen =(select id_seksyen from users_internal where user_id="+user_id+") " +
		      		"and id_jawatan in (1,2))";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiTKP.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListTKP() {
		// TODO Auto-generated method stub
		return senaraiTKP;
	}




	public static Vector getListTKP(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparTKP = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMENKPTG A, USERS B WHERE B.USER_ID = A.ID_TKP AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparTKP.addElement(h); 
		      }
		      
		      return paparTKP;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	
	public static String addMasuk(Hashtable data) throws Exception {
		Db db = new Db();
		String sql = "";
		try
			{
			    	
			long idLogDokumenKPTG = DB.getNextID("TBLPFDLOGDOKUMENKPTG_SEQ");
			String id_Jenisdokumen = (String)data.get("id_Jenisdokumen");
			String no_Rujukan_Lain = (String)data.get("no_Rujukan_Lain");
			String idMinit = (String)data.get("idMinit");
		    String idLaporan = (String)data.get("idLaporan");
			String tajuk_Dokumen = (String)data.get("tajuk_Dokumen");
			String pengirim_Dokumen = (String) data.get("pengirim_Dokumen")== null?"":(String)data.get("pengirim_Dokumen");
			String id_PenerimaDokumen = (String)data.get("id_PenerimaDokumen")== null?"":(String)data.get("id_PenerimaDokumen");
			//String id_PengirimDokumen = (String) data.get("id_PengirimDoku men")== null?"":(String)data.get("id_PengirimDokumen");
			//String penerima_Dokumen = (String)data.get("penerima_Dokumen")== null?"":(String)data.get("penerima_Dokumen");
			String tarikh_Dokumen = (String)data.get("tarikh_Dokumen");
			String tarikh_Diterima_Dihantar = (String)data.get("tarikh_Diterima_Dihantar");
			String id_TKP = (String)data.get("id_TKP");
			String idNegeri = (String)data.get("idNegeri");
			String idSeksyen = (String)data.get("idSeksyen");
			String flag_LogDokumen = (String)data.get("flag_LogDokumen");
			String dirujuk_kepada = (String)data.get("dirujuk_kepada");
			String status_LogDokumen = (String)data.get("status_LogDokumen");
			//String idFail = (String)data.get("id_Fail");
			String idMasuk = (String)data.get("id_Masuk");
				    			      
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				      
			r.add("ID_LOGDOKUMENKPTG", idLogDokumenKPTG);
			r.add("ID_JENISDOKUMEN", id_Jenisdokumen);
			r.add("ID_MINIT", idMinit);
			r.add("ID_LAPORAN", idLaporan);
			r.add("NO_RUJUKAN", no_Rujukan_Lain);
			r.add("TAJUK_DOKUMEN", tajuk_Dokumen);
			r.add("PENGIRIM_MASUK", pengirim_Dokumen);
			r.add("TARIKH_DOKUMEN", r.unquote("to_date('" + tarikh_Dokumen + "', 'dd/MM/yyyy')"));
			r.add("TARIKH_DITERIMA_DIHANTAR", r.unquote("to_date('" + tarikh_Diterima_Dihantar + "', 'dd/MM/yyyy')"));
			r.add("FLAG_LOGDOKUMEN", flag_LogDokumen);
			r.add("DIRUJUK_KEPADA", dirujuk_kepada);
			r.add("ID_SEKSYEN", idSeksyen);
			r.add("ID_NEGERI", idNegeri);
			r.add("STATUS_LOGDOKUMEN", status_LogDokumen);
			r.add("ID_TKP",id_TKP);
			r.add("id_Masuk",idMasuk);
			r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
			sql = r.getSQLInsert("TBLPFDLOGDOKUMENKPTG");
				      
			stmt.executeUpdate(sql);
				      
			return "" +idLogDokumenKPTG;
				      
		} finally {
			if (db != null) db.close();
		}
	}

	public static Vector getDataLogKPTGDokumenMasuk(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_LOGDOKUMENKPTG, A.ID_MINIT, A.ID_LAPORAN, A.ID_JENISDOKUMEN, A.ID_JENISPENGHANTARAN, A.NO_RUJUKAN, A.TAJUK_DOKUMEN, A.PENGIRIM_MASUK, " +
		      		" A.TARIKH_DOKUMEN, A.TARIKH_DITERIMA_DIHANTAR, A.FLAG_LOGDOKUMEN, A.STATUS_LOGDOKUMEN, A.ID_PENGIRIM_KELUAR, A.DIRUJUK_KEPADA, A.ID_LOGDOKUMENKPTGSEKLAIN, A.ID_TKP FROM TBLPFDLOGDOKUMENKPTG A, USERS B WHERE B.USER_ID = A.ID_TKP AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idLogDokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("idMinit", rs.getString("ID_MINIT")==null?"":rs.getString("ID_MINIT"));
		    	  h.put("idLaporan", rs.getString("ID_LAPORAN")==null?"":rs.getString("ID_LAPORAN"));
		    	  h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("jenis_Penghantaran", rs.getString("ID_JENISPENGHANTARAN")==null?"":rs.getString("ID_JENISPENGHANTARAN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")==null?"":rs.getString("NO_RUJUKAN"));
		    	  h.put("dirujuk_Kepada", rs.getString("DIRUJUK_KEPADA")==null?"":rs.getString("DIRUJUK_KEPADA"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("pengirim_Dokumen", rs.getString("PENGIRIM_MASUK")==null?"":rs.getString("PENGIRIM_MASUK"));
		    	  //h.put("id_penerima", rs.getString("ID_PENERIMA_MASUK")==null?"":rs.getString("ID_PENERIMA_MASUK"));
		    	  h.put("tarikh_Dokumen", rs.getDate("TARIKH_DOKUMEN")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("tarikh_Diterima_Dihantar", rs.getDate("TARIKH_DITERIMA_DIHANTAR")==null?"":sdf.format(rs.getDate("TARIKH_DITERIMA_DIHANTAR")));
		    	  h.put("flag_logdokumen", rs.getString("FLAG_LOGDOKUMEN")==null?"":rs.getString("FLAG_LOGDOKUMEN"));
		    	  h.put("status_LogDokumen", rs.getString("STATUS_LOGDOKUMEN")==null?"":rs.getString("STATUS_LOGDOKUMEN"));
		    	  h.put("idLogDokumenKPTGSekLain", rs.getString("ID_LOGDOKUMENKPTGSEKLAIN")==null?"":rs.getString("ID_LOGDOKUMENKPTGSEKLAIN"));
		    	  h.put("ID_TKP", rs.getString("ID_TKP")==null?"":rs.getString("ID_TKP"));
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

	public static Vector getListLampiranLogDokumenMasuk(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenMasuk = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumenKPTG, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_logdokumenKPTG", rs.getString("id_logdokumenKPTG")==null?"":rs.getString("id_logdokumenKPTG"));
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

	public static void setListPengarahSeksyen1(String user_id) throws Exception {
		 Db db = null;
		    senaraiPengarahSeksyen.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as user_id,a.user_name as user_name from users a, users_internal b where a.user_id = b.user_id and b.id_jawatan = 4";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiPengarahSeksyen.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	

	public static Vector getListPengarahSeksyen1() {
		// TODO Auto-generated method stub
		return senaraiPengarahSeksyen;
	}
	
	public static void setListPengarahSeksyen2(String user_id) throws Exception {
		 Db db = null;
		    senaraiPengarahSeksyen.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as user_id,a.user_name as user_name from users a, users_internal b where a.user_id = b.user_id and b.id_jawatan = 4";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiPengarahSeksyen.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	

	public static Vector getListPengarahSeksyen2() {
		// TODO Auto-generated method stub
		return senaraiPengarahSeksyen;
	}
	
	public static void setListPengarahSeksyen3(String user_id) throws Exception {
		 Db db = null;
		    senaraiPengarahSeksyen.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as user_id,a.user_name as user_name from users a, users_internal b where a.user_id = b.user_id and b.id_jawatan = 4";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiPengarahSeksyen.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	

	public static Vector getListPengarahSeksyen3() {
		// TODO Auto-generated method stub
		return senaraiPengarahSeksyen;
	}
	
	public static void setListPengarahSeksyen4(String user_id) throws Exception {
		 Db db = null;
		    senaraiPengarahSeksyen.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as user_id,a.user_name as user_name from users a, users_internal b where a.user_id = b.user_id and b.id_jawatan = 4";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiPengarahSeksyen.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	

	public static Vector getListPengarahSeksyen4() {
		// TODO Auto-generated method stub
		return senaraiPengarahSeksyen;
	}
	
	public static void setListPengarahSeksyen5(String user_id) throws Exception {
		 Db db = null;
		    senaraiPengarahSeksyen.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as user_id,a.user_name as user_name from users a, users_internal b where a.user_id = b.user_id and b.id_jawatan = 4";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiPengarahSeksyen.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	

	public static Vector getListPengarahSeksyen5() {
		// TODO Auto-generated method stub
		return senaraiPengarahSeksyen;
	}
	
	public static void setListPengarahSeksyen6(String user_id) throws Exception {
		 Db db = null;
		    senaraiPengarahSeksyen.clear();
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select a.user_id as user_id,a.user_name as user_name from users a, users_internal b where a.user_id = b.user_id and b.id_jawatan = 4";

		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
					

				  senaraiPengarahSeksyen.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		
		    } finally {
		      if (db != null) db.close();
		    }
		
	}
	

	public static Vector getListPengarahSeksyen6() {
		// TODO Auto-generated method stub
		return senaraiPengarahSeksyen;
	}
	
	
	public static String addKeluar(Hashtable data) throws Exception {
		// TODO Auto-generated method stub
		Db db = new Db();
		String sql = "";
		try
			{
			    	
			long idLogDokumenKPTG = DB.getNextID("TBLPFDLOGDOKUMENKPTG_SEQ");
			String id_Jenisdokumen = (String)data.get("id_Jenisdokumen");
			String id_Jenispenghantaran = (String)data.get("id_Jenispenghantaran");
			String no_Rujukan_Lain = (String)data.get("no_Rujukan_Lain");
			String idMinit = (String)data.get("idMinit");
		    String idLaporan = (String)data.get("idLaporan");
			String tajuk_Dokumen = (String)data.get("tajuk_Dokumen");
			String penerima_Dokumen = (String) data.get("penerima_Dokumen")== null?"":(String)data.get("penerima_Dokumen");
			//String id_PengirimDokumen = (String) data.get("id_PengirimDoku men")== null?"":(String)data.get("id_PengirimDokumen");
			//String penerima_Dokumen = (String)data.get("penerima_Dokumen")== null?"":(String)data.get("penerima_Dokumen");
			String tarikh_Dokumen = (String)data.get("tarikh_Dokumen");
			String tarikh_Diterima_Dihantar = (String)data.get("tarikh_Diterima_Dihantar");
			String id_PengirimKeluar = (String)data.get("id_PengirimKeluar");
			String idNegeri = (String)data.get("idNegeri");
			String idSeksyen = (String)data.get("idSeksyen");
			String flag_LogDokumen = (String)data.get("flag_LogDokumen");
			String status_LogDokumen = (String)data.get("status_LogDokumen");
			//String idFail = (String)data.get("id_Fail");
			String idMasuk = (String)data.get("id_Masuk");
				    			      
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
				      
			r.add("ID_LOGDOKUMENKPTG", idLogDokumenKPTG);
			r.add("ID_JENISDOKUMEN", id_Jenisdokumen);
			r.add("ID_JENISPENGHANTARAN", id_Jenispenghantaran);
			r.add("ID_MINIT", idMinit);
			r.add("ID_LAPORAN", idLaporan);
			r.add("NO_RUJUKAN", no_Rujukan_Lain);
			r.add("TAJUK_DOKUMEN", tajuk_Dokumen);
			r.add("DIRUJUK_KEPADA", penerima_Dokumen);
			//r.add("ID_PENGIRIM_KELUAR", id_PengirimDokumen);
			//r.add("PENERIMA_KELUAR", penerima_Dokumen);
			r.add("TARIKH_DOKUMEN", r.unquote("to_date('" + tarikh_Dokumen + "', 'dd/MM/yyyy')"));
			r.add("TARIKH_DITERIMA_DIHANTAR", r.unquote("to_date('" + tarikh_Diterima_Dihantar + "', 'dd/MM/yyyy')"));
			r.add("FLAG_LOGDOKUMEN", flag_LogDokumen);
			r.add("ID_SEKSYEN", idSeksyen);
			r.add("ID_NEGERI", idNegeri);
			r.add("STATUS_LOGDOKUMEN", status_LogDokumen);
			r.add("ID_PENGIRIM_KELUAR",id_PengirimKeluar);
			r.add("id_Masuk",idMasuk);
			r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
			sql = r.getSQLInsert("TBLPFDLOGDOKUMENKPTG");
				      
			stmt.executeUpdate(sql);
				      
			return "" +idLogDokumenKPTG;
				      
		} finally {
			if (db != null) db.close();
		}
	}

	public static Vector getDataLogKPTGDokumenKeluar(String idLogDokumenKPTG) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_LOGDOKUMENKPTG, A.ID_MINIT, A.ID_LAPORAN, A.ID_JENISDOKUMEN, A.ID_JENISPENGHANTARAN, A.NO_RUJUKAN, A.TAJUK_DOKUMEN, A.PENGIRIM_MASUK, " +
		      		" A.TARIKH_DOKUMEN, A.TARIKH_DITERIMA_DIHANTAR, A.FLAG_LOGDOKUMEN, A.STATUS_LOGDOKUMEN, A.ID_PENGIRIM_KELUAR, A.DIRUJUK_KEPADA FROM TBLPFDLOGDOKUMENKPTG A WHERE A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idLogDokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("idMinit", rs.getString("ID_MINIT")==null?"":rs.getString("ID_MINIT"));
		    	  h.put("idLaporan", rs.getString("ID_LAPORAN")==null?"":rs.getString("ID_LAPORAN"));
		    	  h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("jenis_Penghantaran", rs.getString("ID_JENISPENGHANTARAN")==null?"":rs.getString("ID_JENISPENGHANTARAN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")==null?"":rs.getString("NO_RUJUKAN"));
		    	  h.put("penerima_Dokumen", rs.getString("DIRUJUK_KEPADA")==null?"":rs.getString("DIRUJUK_KEPADA"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  //h.put("pengirim_keluar", rs.getString("ID_PENGIRIM_KELUAR")==null?"":rs.getString("ID_PENGIRIM_KELUAR"));
		    	  h.put("tarikh_Dokumen", rs.getDate("TARIKH_DOKUMEN")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("tarikh_Diterima_Dihantar", rs.getDate("TARIKH_DITERIMA_DIHANTAR")==null?"":sdf.format(rs.getDate("TARIKH_DITERIMA_DIHANTAR")));
		    	  h.put("flag_logdokumen", rs.getString("FLAG_LOGDOKUMEN")==null?"":rs.getString("FLAG_LOGDOKUMEN"));
		    	  h.put("status_LogDokumen", rs.getString("STATUS_LOGDOKUMEN")==null?"":rs.getString("STATUS_LOGDOKUMEN"));
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

	public static Vector getListPengirimKeluar(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparTKP = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMENKPTG A, USERS B WHERE B.USER_ID = A.ID_PENGIRIM_KELUAR AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparTKP.addElement(h); 
		      }
		      
		      return paparTKP;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}


	public static Vector getListPengarahSeksyen(String socPengarahSeksyen1) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawai = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT USER_ID, USER_NAME " +
		      		"FROM USERS WHERE USER_ID = '"+socPengarahSeksyen1+"'";
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

	public static Vector getListPAR(String socPAR) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT USER_ID, USER_NAME " +
		      		"FROM USERS WHERE USER_ID = '"+socPAR+"'";
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

	public static Vector getDataMinitArahanSeksyen1(String idMinitArahanSeksyen1) throws Exception {
		Db db = null;
		 String sql = "";
		
		 Date now = new Date();
		 try {
			 paparMinitArahanSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_MINITARAHANKPTG, A.ID_LOGDOKUMENKPTG, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_PEGAWAI_MENERIMA1, A.ID_PAR1, A.ID_SEKSYEN1 " +
		      		"FROM TBLPFDMINITARAHANKPTG A WHERE A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen1+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idMinitArahanKPTG", rs.getString("ID_MINITARAHANKPTG")==null?"":rs.getString("ID_MINITARAHANKPTG"));
		    	  h.put("idLogdokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("minit_Arahan", rs.getString("MINIT_ARAHAN")==null?"":rs.getString("MINIT_ARAHAN"));
		    	  h.put("tarikh_Minit_Arahan", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
		    	  h.put("idPenerima1", rs.getString("ID_PEGAWAI_MENERIMA1")==null?"":rs.getString("ID_PEGAWAI_MENERIMA1"));
		    	  h.put("idPAR1", rs.getString("ID_PAR1")==null?"":rs.getString("ID_PAR1"));
		    	  h.put("idSeksyen1", rs.getString("ID_SEKSYEN1")==null?"":rs.getString("ID_SEKSYEN1"));
		    	  paparMinitArahanSeksyen1.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparMinitArahanSeksyen1.addElement(h);
		    	  
		      }
		      
		      return paparMinitArahanSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataMinitArahanSeksyen2(String idMinitArahanSeksyen2) throws Exception {
		Db db = null;
		 String sql = "";
		
		 Date now = new Date();
		 try {
			 paparMinitArahanSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_MINITARAHANKPTG, A.ID_LOGDOKUMENKPTG, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_PEGAWAI_MENERIMA1, A.ID_PAR1, A.ID_SEKSYEN1, A.ID_PEGAWAI_MENERIMA2, A.ID_PAR2, A.ID_SEKSYEN2 " +
		      		"FROM TBLPFDMINITARAHANKPTG A WHERE A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen2+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idMinitArahanKPTG", rs.getString("ID_MINITARAHANKPTG")==null?"":rs.getString("ID_MINITARAHANKPTG"));
		    	  h.put("idLogdokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("minit_Arahan", rs.getString("MINIT_ARAHAN")==null?"":rs.getString("MINIT_ARAHAN"));
		    	  h.put("tarikh_Minit_Arahan", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
		    	  h.put("idPenerima1", rs.getString("ID_PEGAWAI_MENERIMA1")==null?"":rs.getString("ID_PEGAWAI_MENERIMA1"));
		    	  h.put("idPAR1", rs.getString("ID_PAR1")==null?"":rs.getString("ID_PAR1"));
		    	  h.put("idSeksyen1", rs.getString("ID_SEKSYEN1")==null?"":rs.getString("ID_SEKSYEN1"));
		    	  h.put("idPenerima2", rs.getString("ID_PEGAWAI_MENERIMA2")==null?"":rs.getString("ID_PEGAWAI_MENERIMA2"));
		    	  h.put("idPAR2", rs.getString("ID_PAR2")==null?"":rs.getString("ID_PAR2"));
		    	  h.put("idSeksyen2", rs.getString("ID_SEKSYEN2")==null?"":rs.getString("ID_SEKSYEN2"));
		    	  paparMinitArahanSeksyen1.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparMinitArahanSeksyen1.addElement(h);
		    	  
		      }
		      
		      return paparMinitArahanSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataMinitArahanSeksyen3(String idMinitArahanSeksyen3) throws Exception {
		Db db = null;
		 String sql = "";
		
		 Date now = new Date();
		 try {
			 paparMinitArahanSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ID_MINITARAHANKPTG, A.ID_LOGDOKUMENKPTG, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, A.ID_PEGAWAI_MENERIMA1, A.ID_PAR1, A.ID_SEKSYEN1, A.ID_PEGAWAI_MENERIMA2, A.ID_PAR2, A.ID_SEKSYEN2, A.ID_PEGAWAI_MENERIMA3, A.ID_PAR3, A.ID_SEKSYEN3 " +
		      		"FROM TBLPFDMINITARAHANKPTG A WHERE A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen3+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	  h.put("idMinitArahanKPTG", rs.getString("ID_MINITARAHANKPTG")==null?"":rs.getString("ID_MINITARAHANKPTG"));
		    	  h.put("idLogdokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("minit_Arahan", rs.getString("MINIT_ARAHAN")==null?"":rs.getString("MINIT_ARAHAN"));
		    	  h.put("tarikh_Minit_Arahan", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
		    	  h.put("idPenerima1", rs.getString("ID_PEGAWAI_MENERIMA1")==null?"":rs.getString("ID_PEGAWAI_MENERIMA1"));
		    	  h.put("idPAR1", rs.getString("ID_PAR1")==null?"":rs.getString("ID_PAR1"));
		    	  h.put("idSeksyen1", rs.getString("ID_SEKSYEN1")==null?"":rs.getString("ID_SEKSYEN1"));
		    	  h.put("idPenerima2", rs.getString("ID_PEGAWAI_MENERIMA2")==null?"":rs.getString("ID_PEGAWAI_MENERIMA2"));
		    	  h.put("idPAR2", rs.getString("ID_PAR2")==null?"":rs.getString("ID_PAR2"));
		    	  h.put("idSeksyen2", rs.getString("ID_SEKSYEN2")==null?"":rs.getString("ID_SEKSYEN2"));
		    	  h.put("idPenerima3", rs.getString("ID_PEGAWAI_MENERIMA3")==null?"":rs.getString("ID_PEGAWAI_MENERIMA3"));
		    	  h.put("idPAR3", rs.getString("ID_PAR3")==null?"":rs.getString("ID_PAR3"));
		    	  h.put("idSeksyen3", rs.getString("ID_SEKSYEN3")==null?"":rs.getString("ID_SEKSYEN3"));
		    	  paparMinitArahanSeksyen1.addElement(h); 
		    	  count++;
		      }
		      
		      if (count == 0){
		    	 
		    	  h.put("bil_Minit_Dokumen",1);
		    	  paparMinitArahanSeksyen1.addElement(h);
		    	  
		      }
		      
		      return paparMinitArahanSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}


	
	public static Vector getDataSeksyenPAR1(String socPAR1) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+socPAR1+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  paparPAR1.addElement(h); 
		      }
		      
		      return paparPAR1;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getDataSeksyenPAR2(String socPAR2) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+socPAR2+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  paparPAR1.addElement(h); 
		      }
		      
		      return paparPAR1;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getDataSeksyenPAR3(String socPAR3) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+socPAR3+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  paparPAR1.addElement(h); 
		      }
		      
		      return paparPAR1;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getDataSeksyenPAR4(String socPAR4) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+socPAR4+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  paparPAR1.addElement(h); 
		      }
		      
		      return paparPAR1;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getDataSeksyenPAR5(String socPAR5) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+socPAR5+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  paparPAR1.addElement(h); 
		      }
		      
		      return paparPAR1;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}
	
	public static Vector getDataSeksyenPAR6(String socPAR6) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPAR1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_seksyen, id_negeri from users_internal where user_id = '"+socPAR6+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("id_seksyen", rs.getString("id_seksyen")==null?"":rs.getString("id_seksyen"));
		    	  h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
		    	  paparPAR1.addElement(h); 
		      }
		      
		      return paparPAR1;
		 }
		 finally {
		      if (db != null) db.close();
		    }
		
	}


	public static String addSeksyen1(Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String sql2 = "";
		   
		    try
		    {	 
			    
		    	  long idMinitArahanKPTG = DB.getNextID("TBLPFDMINITARAHANKPTG_SEQ");
		    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String pegawaiMenerima1 = (String)data.get("pegawaiMenerima1");
			      String idSeksyen1 = (String)data.get("idSeksyen1");
			      String PAR1 = (String)data.get("PAR1");
			      String minitArahan = (String)data.get("minitArahan");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhMinit");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("status_LogDokumen");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("ID_MINITARAHANKPTG",idMinitArahanKPTG);
			      r.add("id_LogDokumenKPTG",idLogDokumenKPTG);
			      r.add("id_pegawai_mengarah",pegawaiMengarah );
			      r.add("id_pegawai_menerima1",pegawaiMenerima1 );
			      r.add("id_seksyen1", idSeksyen1);
			      r.add("id_par1", PAR1);
			      r.add("minit_arahan", minitArahan);
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      
			      sql = r.getSQLInsert("TBLPFDMINITARAHANKPTG");  
			      stmt.executeUpdate(sql);
			      
			      
			      String sqlInsert = "";
			      String sqlSelect = "";
			      if("8".equalsIgnoreCase(idSeksyen1) || "9".equalsIgnoreCase(idSeksyen1)){
			    	   sqlInsert = "insert into tblpfdlogdokumenkptg (id_ptfail,id_logdokumenKPTGSekLain, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			    	   sqlSelect = "select '"+PAR1+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
			      
			      }
			      else{
			    	   sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
					   sqlSelect = "select '"+PAR1+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
	  
			      }
			      
			      sql1 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql1);
			      
			      String sqlUpdate = "";
			      if("8".equalsIgnoreCase(idSeksyen1) || "9".equalsIgnoreCase(idSeksyen1)){
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTGseklain = '"+idLogDokumenKPTG+"'";
			      }
			      else{
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
					     
			      }
			      sql2 = sqlUpdate;
			      stmt.executeUpdate(sql2);
	      
			      return ""+idMinitArahanKPTG;
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
	public static void updateSeksyen1(String idMinitArahanSeksyen1, Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		   
		    try
		    {	 
			    
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String pegawaiMenerima1 = (String)data.get("pegawaiMenerima1");
			      String idSeksyen1 = (String)data.get("idSeksyen1");
			      String PAR1 = (String)data.get("PAR1");
			      String minitArahan = (String)data.get("minitArahan");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhMinit");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("status_LogDokumen");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.update("ID_MINITARAHANKPTG",idMinitArahanSeksyen1);

			      r.add("id_pegawai_mengarah",pegawaiMengarah );
			      r.add("id_pegawai_menerima1",pegawaiMenerima1 );
			      r.add("id_seksyen1", idSeksyen1);
			      r.add("id_par1", PAR1);
			      r.add("minit_arahan", minitArahan);
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      
			      sql = r.getSQLUpdate("TBLPFDMINITARAHANKPTG");  
			      stmt.executeUpdate(sql);
			      
			      
//			      String sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect = "select '"+PAR1+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql1 = sqlInsert+" "+sqlSelect;
//			      stmt.executeUpdate(sql1);
//	      
//			      return ""+idMinitArahanKPTG;
			    } finally {
			      if (db != null) db.close();
			    }
	}

	public static String addSeksyen2(Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String sql2 = "";
		    String sql3 = "";
		    String sql4 = "";
		   
		    try
		    {	 
			    
		    	  long idMinitArahanKPTG = DB.getNextID("TBLPFDMINITARAHANKPTG_SEQ");
		    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String pegawaiMenerima1 = (String)data.get("pegawaiMenerima2");
			      String idSeksyen1 = (String)data.get("idSeksyen2");
			      String PAR2 = (String)data.get("PAR2");
			      String pegawaiMenerima2 = (String)data.get("pegawaiMenerima3");
			      String idSeksyen2 = (String)data.get("idSeksyen3");
			      String PAR3 = (String)data.get("PAR3");
			      String minitArahan = (String)data.get("minitArahan");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhMinit");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("status_LogDokumen");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("ID_MINITARAHANKPTG",idMinitArahanKPTG);
			      r.add("id_LogDokumenKPTG",idLogDokumenKPTG);
			      r.add("id_pegawai_mengarah",pegawaiMengarah );
			      r.add("id_pegawai_menerima1",pegawaiMenerima1 );
			      r.add("id_seksyen1", idSeksyen1);
			      r.add("id_par1", PAR2);
			      r.add("id_pegawai_menerima2",pegawaiMenerima2 );
			      r.add("id_seksyen2", idSeksyen2);
			      r.add("id_par2", PAR3);
			      r.add("minit_arahan", minitArahan);
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      
			      sql = r.getSQLInsert("TBLPFDMINITARAHANKPTG");  
			      stmt.executeUpdate(sql);
		      
			      
			      String sqlInsert = "";
			      String sqlSelect = "";
			      if("8".equalsIgnoreCase(idSeksyen1) || "9".equalsIgnoreCase(idSeksyen1)){
			    	   sqlInsert = "insert into tblpfdlogdokumenkptg (id_ptfail,id_logdokumenKPTGSekLain, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			    	   sqlSelect = "select '"+PAR2+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
			      
			      }
			      else{
			    	   sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
					   sqlSelect = "select '"+PAR2+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
	  
			      }
			      
			      sql1 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql1);
			      
			      if("8".equalsIgnoreCase(idSeksyen2) || "9".equalsIgnoreCase(idSeksyen2)){
			    	   sqlInsert = "insert into tblpfdlogdokumenkptg (id_ptfail,id_logdokumenKPTGSekLain, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			    	   sqlSelect = "select '"+PAR3+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
			      
			      }
			      else{
			    	   sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
					   sqlSelect = "select '"+PAR3+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
	  
			      }
			      
			      sql2 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql2);
			      
			      
			      String sqlUpdate = "";
			      if("8".equalsIgnoreCase(idSeksyen1) || "9".equalsIgnoreCase(idSeksyen1)){
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTGseklain = '"+idLogDokumenKPTG+"'";
			      }
			      else{
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
					     
			      }
			      sql3 = sqlUpdate;
			      stmt.executeUpdate(sql3);
			      
			      if("8".equalsIgnoreCase(idSeksyen2) || "9".equalsIgnoreCase(idSeksyen2)){
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTGseklain = '"+idLogDokumenKPTG+"'";
			      }
			      else{
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
					     
			      }
			      sql4 = sqlUpdate;
			      stmt.executeUpdate(sql4);
			      
			      
//			      String sqlInsert1 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect1 = "select '"+PAR2+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql1 = sqlInsert1+" "+sqlSelect1;
//			      stmt.executeUpdate(sql1);
//			      
//			      String sqlInsert2 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect2 = "select '"+PAR3+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql2 = sqlInsert2+" "+sqlSelect2;
//			      stmt.executeUpdate(sql2);
//			      
//			      String sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//    
//			      sql3 = sqlUpdate;
//			      stmt.executeUpdate(sql3);
	      
			      return ""+idMinitArahanKPTG;
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
	public static void simpanKemaskiniSeksyen2(String idMinitArahanSeksyen2, Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String sql2 = "";
		   
		    try
		    {	 
			    
		    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String pegawaiMenerima1 = (String)data.get("pegawaiMenerima2");
			      String idSeksyen1 = (String)data.get("idSeksyen2");
			      String PAR2 = (String)data.get("PAR2");
			      String pegawaiMenerima2 = (String)data.get("pegawaiMenerima3");
			      String idSeksyen2 = (String)data.get("idSeksyen3");
			      String PAR3 = (String)data.get("PAR3");
			      String minitArahan = (String)data.get("minitArahan");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhMinit");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("status_LogDokumen");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.update("ID_MINITARAHANKPTG",idMinitArahanSeksyen2);
			      
			      r.add("id_pegawai_mengarah",pegawaiMengarah );
			      r.add("id_pegawai_menerima1",pegawaiMenerima1 );
			      r.add("id_seksyen1", idSeksyen1);
			      r.add("id_par1", PAR2);
			      r.add("id_pegawai_menerima2",pegawaiMenerima2 );
			      r.add("id_seksyen2", idSeksyen2);
			      r.add("id_par2", PAR3);
			      r.add("minit_arahan", minitArahan);
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      
			      sql = r.getSQLUpdate("TBLPFDMINITARAHANKPTG");  
			      stmt.executeUpdate(sql);
		      
//			      
//			      String sqlInsert1 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect1 = "select '"+PAR2+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql1 = sqlInsert1+" "+sqlSelect1;
//			      stmt.executeUpdate(sql1);
//			      
//			      String sqlInsert2 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect2 = "select '"+PAR3+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql2 = sqlInsert2+" "+sqlSelect2;
//			      stmt.executeUpdate(sql2);
//	      
//			      return ""+idMinitArahanKPTG;
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
	public static String addSeksyen3(Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String sql2 = "";
		    String sql3 = "";
		    String sql4 = "";
		    String sql5 = "";
		    String sql6 = "";
		   
		    try
		    {	 
			    
		    	 long idMinitArahanKPTG = DB.getNextID("TBLPFDMINITARAHANKPTG_SEQ");
		    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String pegawaiMenerima4 = (String)data.get("pegawaiMenerima4");
			      String idSeksyen4 = (String)data.get("idSeksyen4");
			      String PAR4 = (String)data.get("PAR4");
			      String pegawaiMenerima5 = (String)data.get("pegawaiMenerima5");
			      String idSeksyen5 = (String)data.get("idSeksyen5");
			      String PAR5 = (String)data.get("PAR5");
			      String pegawaiMenerima6 = (String)data.get("pegawaiMenerima6");
			      String idSeksyen6 = (String)data.get("idSeksyen6");
			      String PAR6 = (String)data.get("PAR6");
			      String minitArahan = (String)data.get("minitArahan");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhMinit");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("status_LogDokumen");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("ID_MINITARAHANKPTG",idMinitArahanKPTG);
			      r.add("id_LogDokumenKPTG",idLogDokumenKPTG);
			      r.add("id_pegawai_mengarah",pegawaiMengarah );
			      r.add("id_pegawai_menerima1",pegawaiMenerima4 );
			      r.add("id_seksyen1", idSeksyen4);
			      r.add("id_par1", PAR4);
			      r.add("id_pegawai_menerima2",pegawaiMenerima5 );
			      r.add("id_seksyen2", idSeksyen5);
			      r.add("id_par2", PAR5);
			      r.add("id_pegawai_menerima3",pegawaiMenerima6 );
			      r.add("id_seksyen3", idSeksyen6);
			      r.add("id_par3", PAR6);
			      r.add("minit_arahan", minitArahan);
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      
			      sql = r.getSQLInsert("TBLPFDMINITARAHANKPTG");  
			      stmt.executeUpdate(sql);
		      
			      String sqlInsert = "";
			      String sqlSelect = "";
			      if("8".equalsIgnoreCase(idSeksyen4) || "9".equalsIgnoreCase(idSeksyen4)){
			    	   sqlInsert = "insert into tblpfdlogdokumenkptg (id_ptfail,id_logdokumenKPTGSekLain, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			    	   sqlSelect = "select '"+PAR4+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
			      
			      }
			      else{
			    	   sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
					   sqlSelect = "select '"+PAR4+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
	  
			      }
			      
			      sql1 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql1);
			      
			      if("8".equalsIgnoreCase(idSeksyen5) || "9".equalsIgnoreCase(idSeksyen5)){
			    	   sqlInsert = "insert into tblpfdlogdokumenkptg (id_ptfail,id_logdokumenKPTGSekLain, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			    	   sqlSelect = "select '"+PAR5+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
			      
			      }
			      else{
			    	   sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
					   sqlSelect = "select '"+PAR5+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
	  
			      }
			      
			      sql2 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql2);
			      
			      if("8".equalsIgnoreCase(idSeksyen6) || "9".equalsIgnoreCase(idSeksyen6)){
			    	   sqlInsert = "insert into tblpfdlogdokumenkptg (id_ptfail,id_logdokumenKPTGSekLain, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
			    	   sqlSelect = "select '"+PAR6+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
			      
			      }
			      else{
			    	   sqlInsert = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
					   sqlSelect = "select '"+PAR6+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
	  
			      }
			      
			      sql3 = sqlInsert+" "+sqlSelect;
			      stmt.executeUpdate(sql3);
			      
			      String sqlUpdate = "";
			      if("8".equalsIgnoreCase(idSeksyen4) || "9".equalsIgnoreCase(idSeksyen4)){
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTGseklain = '"+idLogDokumenKPTG+"'";
			      }
			      else{
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
					     
			      }
			      sql4 = sqlUpdate;
			      stmt.executeUpdate(sql4);
			      
			      if("8".equalsIgnoreCase(idSeksyen5) || "9".equalsIgnoreCase(idSeksyen5)){
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTGseklain = '"+idLogDokumenKPTG+"'";
			      }
			      else{
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
					     
			      }
			      sql5 = sqlUpdate;
			      stmt.executeUpdate(sql5);
			      
			      if("8".equalsIgnoreCase(idSeksyen6) || "9".equalsIgnoreCase(idSeksyen6)){
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTGseklain = '"+idLogDokumenKPTG+"'";
			      }
			      else{
			    	  sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
					     
			      }
			      sql6 = sqlUpdate;
			      stmt.executeUpdate(sql6);
			      
			      
//			      String sqlInsert1 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect1 = "select '"+PAR4+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql1 = sqlInsert1+" "+sqlSelect1;
//			      stmt.executeUpdate(sql1);
//			      
//			      String sqlInsert2 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect2 = "select '"+PAR5+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql2 = sqlInsert2+" "+sqlSelect2;
//			      stmt.executeUpdate(sql2);
//			      
//			      String sqlInsert3 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect3 = "select '"+PAR6+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql3 = sqlInsert3+" "+sqlSelect3;
//			      stmt.executeUpdate(sql3);
//			      
//			      String sqlUpdate = "update tblpfdlogdokumenkptg set status_logdokumen = 0 where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql4 = sqlUpdate;
//			      stmt.executeUpdate(sql4);
	      
			      return ""+idMinitArahanKPTG;
			    } finally {
			      if (db != null) db.close();
			    }
	}
	
	public static void updateSeksyen3(String idMinitArahanSeksyen3, Hashtable data) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Db db = null;
		    String sql = "";
		    String sql1 = "";
		    String sql2 = "";
		    String sql3 = "";
		   
		    try
		    {	 
		    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
			      String pegawaiMengarah = (String)data.get("pegawaiMengarah");
			      String pegawaiMenerima4 = (String)data.get("pegawaiMenerima4");
			      String idSeksyen4 = (String)data.get("idSeksyen4");
			      String PAR4 = (String)data.get("PAR4");
			      String pegawaiMenerima5 = (String)data.get("pegawaiMenerima5");
			      String idSeksyen5 = (String)data.get("idSeksyen5");
			      String PAR5 = (String)data.get("PAR5");
			      String pegawaiMenerima6 = (String)data.get("pegawaiMenerima6");
			      String idSeksyen6 = (String)data.get("idSeksyen6");
			      String PAR6 = (String)data.get("PAR6");
			      String minitArahan = (String)data.get("minitArahan");
			      //String tarikhArahan = (String)data.get("tarikhArahan");
			      String tkhArahan = (String)data.get("tarikhMinit");
			      String tarikhArahan = "to_date('" + tkhArahan + "','dd/MM/yyyy')";
			      String id_statusdokumen = (String)data.get("status_LogDokumen");
			      
			      
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.update("ID_MINITARAHANKPTG",idMinitArahanSeksyen3);

			      r.add("id_pegawai_mengarah",pegawaiMengarah );
			      r.add("id_pegawai_menerima1",pegawaiMenerima4 );
			      r.add("id_seksyen1", idSeksyen4);
			      r.add("id_par1", PAR4);
			      r.add("id_pegawai_menerima2",pegawaiMenerima5 );
			      r.add("id_seksyen2", idSeksyen5);
			      r.add("id_par2", PAR5);
			      r.add("id_pegawai_menerima3",pegawaiMenerima6 );
			      r.add("id_seksyen3", idSeksyen6);
			      r.add("id_par3", PAR6);
			      r.add("minit_arahan", minitArahan);
			      r.add("tarikh_minit_Arahan",r.unquote(tarikhArahan)); 
			      r.add("id_statusdokumen",id_statusdokumen);
			      
			      sql = r.getSQLUpdate("TBLPFDMINITARAHANKPTG");  
			      stmt.executeUpdate(sql);
//		      
//			      
//			      String sqlInsert1 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect1 = "select '"+PAR4+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql1 = sqlInsert1+" "+sqlSelect1;
//			      stmt.executeUpdate(sql1);
//			      
//			      String sqlInsert2 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect2 = "select '"+PAR5+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql2 = sqlInsert2+" "+sqlSelect2;
//			      stmt.executeUpdate(sql2);
//			      
//			      String sqlInsert3 = "insert into tblpfdlogdokumen (id_ptfail,id_logdokumenKPTG, pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_dokumenditerima, no_rujukan, id_minit, id_laporan,FLAG_LOGDOKUMEN,status_logdokumen,catatan_minit,arahan_dari)";
//			      String sqlSelect3 = "select '"+PAR6+"',id_logdokumenKPTG,pengirim_masuk, tarikh_dokumen, id_jenisdokumen, tajuk_dokumen, tarikh_diterima_dihantar, no_rujukan, id_minit, id_laporan,'4','1','"+minitArahan+"','"+pegawaiMengarah+"' from tblpfdlogdokumenKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
//			      
//			      sql3 = sqlInsert3+" "+sqlSelect3;
//			      stmt.executeUpdate(sql3);
//	      
//			      return ""+idMinitArahanKPTG;
			    } finally {
			      if (db != null) db.close();
			    }
	}

	public static Vector getListIDPenghantar(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparTKP = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMENKPTG A, USERS B WHERE B.USER_ID = A.ID_PENGIRIM_KELUAR AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("user_name",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparTKP.addElement(h); 
		      }
		      
		      return paparTKP;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getListLampiranLogDokumenKeluar(String idLogDokumenKPTG) throws Exception {
		Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenKeluar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumenKPTG, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogKPTG where id_logdokumenKPTG = '"+idLogDokumenKPTG+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = null;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil",bil);
		    	  h.put("id_lampiran",Utils.isNull(rs.getString("id_lampiran")));
		    	  h.put("id_logdokumenKPTG", rs.getString("id_logdokumenKPTG")==null?"":rs.getString("id_logdokumenKPTG"));
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

	public static Vector getDataPegawaiSeksyen1(String idMinitArahanSeksyen1) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawaiSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHANKPTG A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_MENERIMA1 AND A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen1+"'";
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("namaPegawaiSeksyen1",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawaiSeksyen1.addElement(h); 
		      }
		      
		      return paparPegawaiSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPARSeksyen1(String idMinitArahanSeksyen1) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPARSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHANKPTG A, USERS B WHERE B.USER_ID = A.ID_PAR1 AND A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen1+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("namaPARSeksyen1",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPARSeksyen1.addElement(h); 
		      }
		      
		      return paparPARSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPegawaiSeksyen2(String idMinitArahanSeksyen2) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawaiSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHANKPTG A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_MENERIMA2 AND A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen2+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("namaPegawaiSeksyen2",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawaiSeksyen1.addElement(h); 
		      }
		      
		      return paparPegawaiSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPARSeksyen2(String idMinitArahanSeksyen2) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPARSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHANKPTG A, USERS B WHERE B.USER_ID = A.ID_PAR2 AND A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen2+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("namaPARSeksyen2",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPARSeksyen1.addElement(h); 
		      }
		      
		      return paparPARSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}
	public static Vector getDataPegawaiSeksyen3(String idMinitArahanSeksyen3) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawaiSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHANKPTG A, USERS B WHERE B.USER_ID = A.ID_PEGAWAI_MENERIMA3 AND A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen3+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("namaPegawaiSeksyen3",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPegawaiSeksyen1.addElement(h); 
		      }
		      
		      return paparPegawaiSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static Vector getDataPARSeksyen3(String idMinitArahanSeksyen3) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPARSeksyen1 = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDMINITARAHANKPTG A, USERS B WHERE B.USER_ID = A.ID_PAR3 AND A.ID_MINITARAHANKPTG = '"+idMinitArahanSeksyen3+"'";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      if (rs.next()) {
		    	  h.put("user_id",rs.getString("user_id")==null?"":rs.getString("user_id"));
				  h.put("namaPARSeksyen3",rs.getString("user_name")==null?"":rs.getString("user_name"));
				  paparPARSeksyen1.addElement(h); 
		      }
		      
		      return paparPARSeksyen1;  
		      
		 }
		 finally {
		      if (db != null) db.close();
		    }
	}

	public static void setDataMinitArahanKetuaPengarah1(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

	      sql = "SELECT A.ID_MINITARAHANKPTG, A.ID_PEGAWAI_MENGARAH, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, B.NO_RUJUKAN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA  "+
	    	  	"FROM TBLPFDMINITARAHANKPTG A, TBLPFDLOGDOKUMENKPTG B, USERS C, USERS D "+
	      		"WHERE A.ID_LOGDOKUMENKPTG = B.ID_LOGDOKUMENKPTG "+
	      		"AND A.ID_PEGAWAI_MENGARAH = C.USER_ID(+) "+
	      		"AND A.ID_PEGAWAI_MENERIMA1 = D.USER_ID(+) "+
	      		//"AND A.ID_PEGAWAI_MENERIMA2 = D.USER_ID(+) "+
	      		//"AND A.ID_PEGAWAI_MENERIMA3 = D.USER_ID(+) "+
	      		"AND A.ID_LOGDOKUMENKPTG= '"+id+"' "+
	      		//"AND A.LEVEL_ARAHAN = 1"+
	      		"ORDER BY A.ID_MINITARAHANKPTG ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPegawaiMengarah",rs.getString("ID_PEGAWAI_MENGARAH"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN")== null?"":rs.getString("NO_RUJUKAN"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	 // h.put("pegawaiMenerima2", rs.getString("PEGAWAI_MENERIMA2")== null?"":rs.getString("PEGAWAI_MENERIMA2"));
	    	 // h.put("pegawaiMenerima3", rs.getString("PEGAWAI_MENERIMA3")== null?"":rs.getString("PEGAWAI_MENERIMA3"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
//	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("0")){
//		    	  h.put("statusTindakan", "");
//	    	  }
	    	  


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPengarah.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");

	    	  senaraiMinitPengarah.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getlistMinitArahanKetuaPengarah1() {
		// TODO Auto-generated method stub
		return senaraiMinitPengarah;
	}
	
	public static void setDataMinitArahanKetuaPengarah2(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

		      sql = "SELECT A.ID_MINITARAHANKPTG, A.ID_PEGAWAI_MENGARAH, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, B.NO_RUJUKAN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA  "+
	    	  	"FROM TBLPFDMINITARAHANKPTG A, TBLPFDLOGDOKUMENKPTG B, USERS C, USERS D "+
	      		"WHERE A.ID_LOGDOKUMENKPTG = B.ID_LOGDOKUMENKPTG "+
	      		"AND A.ID_PEGAWAI_MENGARAH = C.USER_ID(+) "+
	      		"AND A.ID_PEGAWAI_MENERIMA2 = D.USER_ID(+) "+
	      		//"AND A.ID_PEGAWAI_MENERIMA2 = D.USER_ID(+) "+
	      		//"AND A.ID_PEGAWAI_MENERIMA3 = D.USER_ID(+) "+
	      		"AND A.ID_LOGDOKUMENKPTG= '"+id+"' "+
	      		//"AND A.LEVEL_ARAHAN = 1"+
	      		"ORDER BY A.ID_MINITARAHANKPTG ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPegawaiMengarah",rs.getString("ID_PEGAWAI_MENGARAH"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN")== null?"":rs.getString("NO_RUJUKAN"));
	    	  //h.put("pegawaiMenerima1", rs.getString("PEGAWAI_MENERIMA1")== null?"":rs.getString("PEGAWAI_MENERIMA1"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	 // h.put("pegawaiMenerima3", rs.getString("PEGAWAI_MENERIMA3")== null?"":rs.getString("PEGAWAI_MENERIMA3"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
//	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("0")){
//		    	  h.put("statusTindakan", "");
//	    	  }
	    	  


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPengarah.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");

	    	  senaraiMinitPengarah.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getlistMinitArahanKetuaPengarah2() {
		// TODO Auto-generated method stub
		return senaraiMinitPengarah;
	}
	
	public static void setDataMinitArahanKetuaPengarah3(String id) throws Exception {
	    Db db = null;
	    String sql = "";
	    String sql1 = "";
	    
	    try {
	    	senaraiMinitPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

		      sql = "SELECT A.ID_MINITARAHANKPTG, A.ID_PEGAWAI_MENGARAH, A.MINIT_ARAHAN, A.TARIKH_MINIT_ARAHAN, B.NO_RUJUKAN, C.USER_NAME AS PEGAWAI_MENGARAH, D.USER_NAME AS PEGAWAI_MENERIMA  "+
	    	  	"FROM TBLPFDMINITARAHANKPTG A, TBLPFDLOGDOKUMENKPTG B, USERS C, USERS D "+
	      		"WHERE A.ID_LOGDOKUMENKPTG = B.ID_LOGDOKUMENKPTG "+
	      		"AND A.ID_PEGAWAI_MENGARAH = C.USER_ID(+) "+
	      		"AND A.ID_PEGAWAI_MENERIMA3 = D.USER_ID(+) "+
	      		//"AND A.ID_PEGAWAI_MENERIMA2 = D.USER_ID(+) "+
	      		//"AND A.ID_PEGAWAI_MENERIMA3 = D.USER_ID(+) "+
	      		"AND A.ID_LOGDOKUMENKPTG= '"+id+"' "+
	      		//"AND A.LEVEL_ARAHAN = 1"+
	      		"ORDER BY A.ID_MINITARAHANKPTG ASC";;
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idPegawaiMengarah",rs.getString("ID_PEGAWAI_MENGARAH"));
	    	  h.put("pegawaiMengarah",rs.getString("PEGAWAI_MENGARAH"));
	    	  h.put("minitArahan", rs.getString("MINIT_ARAHAN")== null?"":rs.getString("MINIT_ARAHAN"));
	    	  h.put("noRujukanDokumen", rs.getString("NO_RUJUKAN")== null?"":rs.getString("NO_RUJUKAN"));
	    	 // h.put("pegawaiMenerima1", rs.getString("PEGAWAI_MENERIMA1")== null?"":rs.getString("PEGAWAI_MENERIMA1"));
	    	 // h.put("pegawaiMenerima2", rs.getString("PEGAWAI_MENERIMA2")== null?"":rs.getString("PEGAWAI_MENERIMA2"));
	    	  h.put("pegawaiMenerima", rs.getString("PEGAWAI_MENERIMA")== null?"":rs.getString("PEGAWAI_MENERIMA"));
	    	  h.put("tarikh", rs.getDate("TARIKH_MINIT_ARAHAN")==null?"":sdf.format(rs.getDate("TARIKH_MINIT_ARAHAN")));
	    	  //h.put("statusTindakan", rs.getString("ID_STATUSTINDAKAN")== null?"":rs.getString("ID_STATUSTINDAKAN"));
//	    	  if (rs.getString("ID_STATUSTINDAKAN").equals("0")){
//		    	  h.put("statusTindakan", "");
//	    	  }
	    	  


	    	  bil++;
	    	  count++;
	    	  	 
	    	  senaraiMinitPengarah.addElement(h);
	      }
	      
	     
	      
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("minitArahan","Tiada");
	    	  h.put("pegawaiMengarah","");
	    	  h.put("noRujukanDokumen","");
	    	  h.put("pegawaiMenerima","");
	    	  h.put("tarikh","");

	    	  senaraiMinitPengarah.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getlistMinitArahanKetuaPengarah3() {
		// TODO Auto-generated method stub
		return senaraiMinitPengarah;
	}

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
	      
	      sql =  " SELECT U.user_id,U.user_name FROM USERS U, USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID ";
	      
	      if(user_negeri.equals("16"))
	      {      
	      sql += " AND UI.ID_SEKSYEN  = (SELECT ID_SEKSYEN "+
	    		 " FROM USERS_INTERNAL WHERE USER_ID = "+user_id+") ";
	      }	
	      
	      
	      sql += " AND UI.ID_NEGERI  = "+user_negeri+" AND UI.ID_JAWATAN IN (2,3,4, 5, 6)"+
	    		 " AND U.USER_ROLE NOT IN ('jpph','jlm','jpbd','jim','adminint', 'adminppk') ";	   
	      
	      
	      sql += "ORDER BY UI.ID_JAWATAN ASC ";

	      
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

	public static Vector getDataLogDokumenKPTG(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLogDokumen = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT A.ARAHAN_DARI, A.ID_LOGDOKUMENKPTG, A.ID_MINIT, A.ID_LAPORAN, A.ID_JENISDOKUMEN, A.NO_RUJUKAN, A.TAJUK_DOKUMEN, A.PENGIRIM_MASUK, A.TARIKH_DOKUMEN, " +
		      		"A.TARIKH_DITERIMA_DIHANTAR, A.FLAG_LOGDOKUMEN, A.STATUS_LOGDOKUMEN,A.CATATAN_MINIT FROM TBLPFDLOGDOKUMENKPTG A, USERS B WHERE B.USER_ID = A.ID_PTFAIL AND A.ID_LOGDOKUMENKPTGSEKLAIN = '"+idLogDokumenKPTG+"'";
		      
		      System.out.println("sql ::::::::::: "+sql.toUpperCase());
		      
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      Hashtable h = new Hashtable();
		      int count = 0;
		      while (rs.next()) {
		    	//  h.put("idLogDokumenKPTGSekLain", rs.getString("ID_LOGDOKUMENKPTGSEKLAIN")==null?"":rs.getString("ID_LOGDOKUMENKPTGSEKLAIN"));
		    	  h.put("idLogDokumenKPTG", rs.getString("ID_LOGDOKUMENKPTG")==null?"":rs.getString("ID_LOGDOKUMENKPTG"));
		    	  h.put("idMinit", rs.getString("ID_MINIT")==null?"":rs.getString("ID_MINIT"));
		    	  h.put("idLaporan", rs.getString("ID_LAPORAN")==null?"":rs.getString("ID_LAPORAN"));
		    	  h.put("jenis_Dokumen", rs.getString("ID_JENISDOKUMEN")==null?"":rs.getString("ID_JENISDOKUMEN"));
		    	  h.put("no_Rujukan_Lain", rs.getString("NO_RUJUKAN")==null?"":rs.getString("NO_RUJUKAN"));
		    	  h.put("tajuk_Dokumen", rs.getString("TAJUK_DOKUMEN")==null?"":rs.getString("TAJUK_DOKUMEN"));
		    	  h.put("pengirim_Dokumen", rs.getString("PENGIRIM_MASUK")==null?"":rs.getString("PENGIRIM_MASUK"));
		    	 // h.put("id_penerima", rs.getString("ID_PENERIMA_MASUK")==null?"":rs.getString("ID_PENERIMA_MASUK"));
		    	  h.put("id_pengirim", rs.getString("ARAHAN_DARI")==null?"":rs.getString("ARAHAN_DARI"));
		    	 // h.put("penerima_Dokumen", rs.getString("PENERIMA_KELUAR")==null?"":rs.getString("PENERIMA_KELUAR"));
		    	  h.put("tarikh_Dokumen", rs.getDate("TARIKH_DOKUMEN")==null?"":sdf.format(rs.getDate("TARIKH_DOKUMEN")));
		    	  h.put("tarikh_Diterima_Dihantar", rs.getDate("TARIKH_DITERIMA_DIHANTAR")==null?"":sdf.format(rs.getDate("TARIKH_DITERIMA_DIHANTAR")));
		    	  h.put("flag_logdokumen", rs.getString("FLAG_LOGDOKUMEN")==null?"":rs.getString("FLAG_LOGDOKUMEN"));
		    	  h.put("status_LogDokumen", rs.getString("STATUS_LOGDOKUMEN")==null?"":rs.getString("STATUS_LOGDOKUMEN"));
		    	//  h.put("cara_Penghantaran", rs.getString("CARA_PENGHANTARAN")==null?"":rs.getString("CARA_PENGHANTARAN"));	 
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
	
	public static Vector getDataPegawaiSeksyenLain(String idLogDokumenKPTG) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparPegawaiSeksyenLain = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "SELECT B.USER_ID, B.USER_NAME " +
		      		"FROM  TBLPFDLOGDOKUMENKPTG A, USERS B WHERE B.USER_ID = A.ARAHAN_DARI AND A.ID_LOGDOKUMENKPTG = '"+idLogDokumenKPTG+"'";
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

	public static Vector getLampiranLogDokumenTKPKDariSeksyenLain (String idLogDokumenKPTGSekLain) throws Exception {
				 Db db = null;
				 String sql = "";
				 Date now = new Date();
				 try {
					 paparLampiranLogDokumenKeluar = new Vector();
				      db = new Db();
				      Statement stmt = db.getStatement();
				     
				      sql = "select id_lampiran, id_logdokumenkptg, content, nama_fail, jenis_mime from " +
				      		"tblpfdrujlampiranlogkptg where id_logdokumenkptg = '"+idLogDokumenKPTGSekLain+"'";
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

	public static void updateLogDokumenSeksyenLainKPTG(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  //String idLogDokumen = (String)data.get("idLogDokumen");
	    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");
		      String tkhDokDiterima = (String)data.get("tarikh_Dokumen_Diterima");
		      String tarikhDokDiterima = "to_date('" + tkhDokDiterima + "','dd/MM/yyyy')";		     
			  String idKemaskini = (String)data.get("id_Kemaskini");
			  String idSeksyen = (String)data.get("idSeksyen");
			  String idNegeri = (String)data.get("idNegeri");
			  //String id_ptfail = (String)data.get("id_ptfail");
			  //String idDokumen = (String)data.get("idDokumen");
			  String id_TKP = (String)data.get("id_nama_penerima");
			  String status_logdokumen = (String)data.get("status_logdokumen");
			 // String cara_Penghantaran = (String)data.get("cara_Penghantaran");
			  
			  
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  r.update("id_LogDokumenKPTG", idLogDokumenKPTG);
		      r.add("tarikh_Diterima_Dihantar", r.unquote(tarikhDokDiterima));
		      r.add("status_logdokumen",status_logdokumen);
		      r.add("id_TKP",id_TKP);
		     // r.add("id_ptfail",id_ptfail);
		      r.add("id_seksyen",idSeksyen);
		      r.add("id_negeri",idNegeri);
		      r.add("flag_logdokumen",1);
			 // r.add("cara_penghantaran",cara_Penghantaran); 
			  r.add("id_Masuk",idKemaskini);
			  r.add("tarikh_Masuk",r.unquote("sysdate")); 
			
			  sql = r.getSQLUpdate("tblpfdlogdokumenKPTG");
		      stmt.executeUpdate(sql);
		      
//		      String sqlInsert = "INSERT INTO TBLPFDRUJLAMPIRANLOGKPTG (id_logdokumenkptg, content, nama_fail, jenis_mime)";
//			      String sqlSelect = "SELECT '"+idLogDokumen+"',content, nama_fail, jenis_mime from tblpfdrujlampiran WHERE id_dokumen = "+idDokumen+"";
//			      
//			      sql1 = sqlInsert+" "+sqlSelect;
//			      stmt.executeUpdate(sql1);

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

	public static Vector getLampiranLogDokumenDariSeksyenLainKPTG(String idLogDokumenKPTGSekLain) throws Exception {
		 Db db = null;
		 String sql = "";
		 Date now = new Date();
		 try {
			 paparLampiranLogDokumenKeluar = new Vector();
		      db = new Db();
		      Statement stmt = db.getStatement();
		     
		      sql = "select id_lampiran, id_logdokumenkptg, content, nama_fail, jenis_mime from " +
		      		"tblpfdrujlampiranlogkptg where id_logdokumenkptg = '"+idLogDokumenKPTGSekLain+"'";
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

	public static void deleteLogDokumenMasukKPTG(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    String sql1 = "";
	    String sql2 = "";
	    try
	    {
	    	  String idLogDokumenKPTG = (String)data.get("idLogDokumenKPTG");	     
			  String idKemaskini = (String)data.get("id_Kemaskini");

			  db = new Db();
			  Statement stmt = db.getStatement();
			  
	
			  sql = "DELETE from tblpfdlogdokumenkptg where id_LogDokumenKPTG = '"+idLogDokumenKPTG+"'";		     
			  stmt.executeUpdate(sql);  
			  
			  sql1 = "DELETE from TBLPFDRUJLAMPIRANLOGKPTG where id_LogDokumenKPTG = '"+idLogDokumenKPTG+"'";		     
			  stmt.executeUpdate(sql1);  

		      
		    }
		    finally {
		      if (db != null) db.close();
		    }
		
	}

	public static String getEmailPTFail(String id_tkp) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+id_tkp+"'" ;
 
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

	public static String getEmailPenerima1(String idPenerima1) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPenerima1+"'" ;
 
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

	public static String getEmailPenerima2(String idPenerima2) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPenerima2+"'" ;
 
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

	public static String getEmailPenerima3(String idPenerima3) throws Exception {
		Db db = null;
		 String sql = "";
		 String returnValue = "";
		 try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      
		      sql = "select emel from users_internal where user_id = '"+idPenerima3+"'" ;
 
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
		     
		      
		      r.add("a.ID_LOGDOKUMENKPTG",id);

		      sql = r.getSQLSelect("TBLPFDRUJLAMPIRANLOGKPTG a");
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
			sql = r.getSQLDelete("TBLPFDRUJLAMPIRANLOGKPTG");
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
	

}


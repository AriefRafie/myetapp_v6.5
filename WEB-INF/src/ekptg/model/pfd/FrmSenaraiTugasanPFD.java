package ekptg.model.pfd;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;





public class FrmSenaraiTugasanPFD {
	
		private static Vector listLogDokumenById = new Vector();
		private static Vector listLogDokumenByIdSelesai = new Vector();
		private static Vector listAgihanTugasanById = new Vector();
		private static Vector listAgihanTugasanByIdSelesai = new Vector();
		private static Vector listPenerimaTugasanById = new Vector();
		private static Vector listPenerimaTugasanByIdSelesai = new Vector();
		private static Vector listTugasanByIdSelesai = new Vector();
		
		public static void setListPergerakanPemohonFail(String user_id) throws Exception {
	    Db db = null;
	    listLogDokumenById.clear();
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	        
	      sql = "select a.ID_LOGDOKUMEN, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DOKUMENDITERIMA, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_PTFAIL, a.ID_DOKUMEN, a.ID_LOGDOKUMENKPTG, d.ID_FAIL " +
	      		"FROM TBLPFDLOGDOKUMEN a, USERS b, TBLPFDDOKUMEN c, TBLPFDFAIL d " +
	      		"where a.ID_PTFAIL = b.USER_ID " +
	      		"and a.STATUS_LOGDOKUMEN = '1' " +
	      		"and a.ID_LOGDOKUMEN = c.ID_LOGDOKUMEN(+) "+
//	      		"and a.id_dokumen = c.id_dokumen "+
                "and c.ID_FAIL = d.ID_FAIL(+) "+
	      		"and a.id_ptfail = "+user_id+" ";
	      sql = sql + "ORDER BY ID_LOGDOKUMEN DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idLogDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
	    	  h.put("tarikh_DokumenDiterima",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
	    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("id_ptfail", rs.getString(8)==null?"":rs.getString(8));
	    	  h.put("idDokumen", rs.getString(9)==null?"":rs.getString(9));
	    	  h.put("idLogDokumenKPTG", rs.getString(10)==null?"":rs.getString(10));
	    	  h.put("idFail", rs.getString(11)==null?"":rs.getString(11));
	    	  
	    	  listLogDokumenById.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idLogDokumen","");
	    	  h.put("no_Rujukan_Lain", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen", "");
	    	  h.put("tarikh_DokumenDiterima","");
	    	  h.put("status_Logdokumen","");
	    	  h.put("id_ptfail", "");

	    	  listLogDokumenById.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public static Vector getListLogDokumen() {
		
		return listLogDokumenById;
	}

	public static void setListAgihanTugasan(String user_id) throws Exception {
	    Db db = null;
	    listAgihanTugasanById.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	        
	      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
	      		"FROM TBLPFDDOKUMEN a " +
	      		"where (A.ID_NAMAPENERIMA = "+user_id+" or A.ID_SETIAUSAHA = "+user_id+") " +
	      		"and status_minit_pengarah = 1";
	      sql = sql + "ORDER BY ID_DOKUMEN DESC";
	      
//	      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
//    		"FROM TBLPFDDOKUMEN a, USERS b " +
//    		"where a.ID_NAMAPENERIMA = b.USER_ID " +
//    		//"and a.ID_NAMAPENERIMA = "+user_id+"" +
//    		"and a.ID_SETIAUSAHA = "+user_id+"";
//	      sql = sql + "ORDER BY ID_DOKUMEN DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Dokumen",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("no_Rujukan_Lain", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tajuk_Dokumen", rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("tarikh_Dokumen",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
	    	  h.put("nama_Pengirim",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("id_namaPenerima", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("idFail", rs.getString(8)==null?"":rs.getString(8));

	    	  listAgihanTugasanById.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idDokumen","");
	    	  h.put("no_Rujukan_Dokumen", "");
	    	  h.put("no_Rujukan_Lain", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen","");
	    	  h.put("nama_Pengirim","");
	    	  h.put("id_namaPenerima", "");
	    	  h.put("idFail", "");

	    	  listAgihanTugasanById.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListAgihanTugasan() {
		
		return listAgihanTugasanById;
	}


	public static void setListPenerimaTugasan(String user_id) throws Exception {
		Db db = null;
		listPenerimaTugasanById.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	        
//	      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
//	      		"FROM TBLPFDDOKUMEN a " +
//	      		"where (A.ID_NAMAPENERIMA = "+user_id+" or A.ID_SETIAUSAHA = "+user_id+") " ;
//	      sql = sql + "ORDER BY ID_DOKUMEN DESC";
//	      
//	      sql = "select b.ID_DOKUMEN,a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
//    		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b " +
//    		"where a.ID_DOKUMEN = b.ID_DOKUMEN " +
//    		"and b.STATUS_TUGASAN = '1'"+
//    		"and (b.ID_PEGAWAI_YGMENERIMA1 = "+user_id+"" +
//    		"or b.ID_PEGAWAI_YGMENERIMA2 = "+user_id+"" +
//    		"or b.ID_PEGAWAI_YGMENERIMA3 = "+user_id+")";
//	      sql = sql + "ORDER BY b.TARIKH_MINIT_ARAHAN DESC";
	      
	      sql = "select b.ID_DOKUMEN,a.NO_RUJUKAN_DOKUMEN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
	  		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b, TBLPFDMINITARAHANPEGAWAI c " +
	  		"where a.ID_DOKUMEN = b.ID_DOKUMEN " +
	  		"and c.STATUS_TUGASAN = '1' "+
	  		"and c.ID_minitarahan = b.ID_minitarahan " +
	  		"and c.ID_PEGAWAIYGMENERIMA = "+user_id+" ";
	      sql = sql + "ORDER BY b.TARIKH_MINIT_ARAHAN DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Dokumen", rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tarikh_Dokumen",rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
	    	  h.put("nama_Pengirim",rs.getString(5)==null?"":rs.getString(5));
	    	  h.put("idFail", rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("idMinitArahan", rs.getString(7)==null?"":rs.getString(7));

	    	  listPenerimaTugasanById.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idDokumen","");
	    	  h.put("no_Rujukan_Dokumen", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen","");
	    	  h.put("nama_Pengirim","");
	    	  h.put("id_namaPenerima", "");
	    	  h.put("idFail", "");
	    	  h.put("idMinitArahan", "");

	    	  listPenerimaTugasanById.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}
	
	public static Vector getListPenerimaTugasan() {
		// TODO Auto-generated method stub
		return listPenerimaTugasanById;
	}

	public static void setListLogDokumenByUserIdSelesai(String user_id) throws Exception {
		 Db db = null;
		    listLogDokumenByIdSelesai.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select  a.ID_LOGDOKUMEN, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DOKUMENDITERIMA, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_PTFAIL, a.ID_DOKUMEN, a.ID_LOGDOKUMENKPTG, d.ID_FAIL " +
		      		"FROM TBLPFDLOGDOKUMEN a, USERS b, TBLPFDDOKUMEN c, TBLPFDFAIL d " +
		      		"where a.ID_PTFAIL = b.USER_ID " +
		      		"and a.STATUS_LOGDOKUMEN = '0' " +
		      		"and a.ID_LOGDOKUMEN = c.ID_LOGDOKUMEN "+
		      		"and c.ID_FAIL = d.ID_FAIL(+) "+
		      		"and a.id_ptfail = "+user_id+" ";
		      sql = sql + "ORDER BY ID_LOGDOKUMEN DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumen",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
		    	  h.put("tarikh_DokumenDiterima",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
		    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("id_ptfail", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("idDokumen", rs.getString(9)==null?"":rs.getString(9));
		    	  h.put("idLogDokumenKPTG", rs.getString(10)==null?"":rs.getString(10));
		    	  h.put("idFail", rs.getString(11)==null?"":rs.getString(11));

		    	  listLogDokumenByIdSelesai.addElement(h);
		      	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idLogDokumen","");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_DokumenDiterima","");
		    	  h.put("status_Logdokumen","");
		    	  h.put("id_ptfail", "");

		    	  listLogDokumenByIdSelesai.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListLogDokumenSelesai() {
		// TODO Auto-generated method stub
		return listLogDokumenByIdSelesai;
	}

	public static void setListAgihanTugasanTindakan(String user_id) throws Exception {
		  Db db = null;
		  listAgihanTugasanByIdSelesai.clear();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL, MA.ID_DOKUMEN, MA.ID_MINITARAHAN " +
		      		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN MA " +
		      		"where MA.ID_PEGAWAI_YGMENGARAH = '"+user_id+"'"+
		      		"and a.id_dokumen = MA.id_dokumen(+)";
		      	//	"and MA.status_tugasan = 0";
		      sql = sql + "ORDER BY MA.TARIKH_MINIT_ARAHAN DESC";
		      
//		      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL, MA.ID_DOKUMEN, MA.ID_MINITARAHAN, MS.ID_MINITARAHANSEKLAIN FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN MA, TBLPFDMINITARAHANSEKLAIN MS where MA.ID_PEGAWAI_YGMENGARAH = '"+user_id+"' and (a.id_dokumen = MA.id_dokumen or a.id_dokumen = MS.id_dokumen) ORDER BY MA.TARIKH_MINIT_ARAHAN DESC";
		      
//		      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
//	    		"FROM TBLPFDDOKUMEN a, USERS b " +
//	    		"where a.ID_NAMAPENERIMA = b.USER_ID " +
//	    		//"and a.ID_NAMAPENERIMA = "+user_id+"" +
//	    		"and a.ID_SETIAUSAHA = "+user_id+"";
//		      sql = sql + "ORDER BY ID_DOKUMEN DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Dokumen",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("no_Rujukan_Lain", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tajuk_Dokumen", rs.getString(4)==null?"":rs.getString(4));
		    	  h.put("tarikh_Dokumen",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
		    	  h.put("nama_Pengirim",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("id_namaPenerima", rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("idFail", rs.getString(8)==null?"":rs.getString(8));

		    	  listAgihanTugasanByIdSelesai.addElement(h);
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idDokumen","");
		    	  h.put("no_Rujukan_Dokumen", "");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen","");
		    	  h.put("nama_Pengirim","");
		    	  h.put("id_namaPenerima", "");
		    	  h.put("idFail", "");

		    	  listAgihanTugasanByIdSelesai.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
		
	}

	public static Vector getListAgihanTugasanTindakan() {
		// TODO Auto-generated method stub
		return listAgihanTugasanByIdSelesai;
	}

	public static void setListPenerimaTugasanTindakan(String user_id) throws Exception {
		Db db = null;
		listPenerimaTugasanByIdSelesai.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	        
//	      sql = "select b.ID_DOKUMEN,a.NO_RUJUKAN_LAIN,a.NO_RUJUKAN_DOKUMEN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
//		  		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b " +
//		  		"where a.ID_DOKUMEN = b.ID_DOKUMEN " +
//		  		"and b.STATUS_TUGASAN = '0'"+
//		  		"and (b.ID_PEGAWAI_YGMENERIMA1 = "+user_id+"" +
//		  		"or b.ID_PEGAWAI_YGMENERIMA2 = "+user_id+"" +
//		  		"or b.ID_PEGAWAI_YGMENERIMA3 = "+user_id+")";
//	      sql = sql + "ORDER BY b.TARIKH_MINIT_ARAHAN DESC";
	      
	      sql = "select b.ID_DOKUMEN, a.NO_RUJUKAN_LAIN, a.NO_RUJUKAN_DOKUMEN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
	  		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b, TBLPFDMINITARAHANPEGAWAI c " +
	  		"where a.ID_DOKUMEN = b.ID_DOKUMEN " +
	  		"and c.STATUS_TUGASAN = '0' "+
	  		"and c.ID_minitarahan = b.ID_minitarahan " +
	  		"and c.ID_PEGAWAIYGMENERIMA = "+user_id+" ";
	      sql = sql + "ORDER BY b.TARIKH_MINIT_ARAHAN DESC";
	      
	     
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Lain", rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("no_Rujukan_Dokumen", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tajuk_Dokumen",rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("tarikh_Dokumen",rs.getString(5)==null?"":sdf.format(rs.getDate(5)));
	    	  h.put("nama_Pengirim", rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("idFail", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("idMinitArahan", rs.getString(8)==null?"":rs.getString(8));

	    	  listPenerimaTugasanByIdSelesai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idDokumen","");
	    	  h.put("no_Rujukan_Dokumen", "");
	    	  h.put("no_Rujukan_Lain", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen","");
	    	  h.put("nama_Pengirim","");
	    	  h.put("id_namaPenerima", "");
	    	  h.put("idFail", "");
	    	  h.put("idMinitArahan","");

	    	  listPenerimaTugasanByIdSelesai.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}

	public static Vector getListPenerimaTugasanTindakan() {
		// TODO Auto-generated method stub
		return listPenerimaTugasanByIdSelesai;
	}

	@SuppressWarnings("unchecked")
	public static Vector getListMesyuaratMyInfo(String user_id) throws DbException, Exception {
		String sql = "";
		Db db = new Db();
		Vector listMesyuarat = new Vector();
		
		try {
			
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_SEKSYEN, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT, FAIL.NO_FAIL " +
				"FROM TBLPFDMESYUARAT M, TBLRUJSEKSYEN RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
				"WHERE M.ID_SEKSYEN = RS.ID_SEKSYEN(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " +
				"AND M.ID_MESYUARAT IN (SELECT ID_MESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PEGAWAI = "+user_id+")"+
				"AND M.TARIKH_MESYUARAT >= sysdate "+
				"ORDER BY M.TARIKH_MESYUARAT";
			
			
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			String Mesyuarat_Dari = "", Mesyuarat_Hingga = "", WAKTU_MESYUARAT_DARI = "", WAKTU_MESYUARAT_HINGGA = "";
			while (rs.next()) {
				WaktuMesyuarat = rs.getString(10) == null ? "" : rs.getString(10);
				if (!"".equalsIgnoreCase(WaktuMesyuarat)) {
					if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " AM";
					} else if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " PM";
					} else {
						WaktuMesyuarat = " PM";
					}
				}
				
				Mesyuarat_Dari = rs.getString(6) == null ? "" : rs.getString(6);
				Mesyuarat_Hingga = rs.getString(7) == null ? "" : rs.getString(7);
				if (Mesyuarat_Dari.length() < 4) {
					Mesyuarat_Dari = String.format("0000", Mesyuarat_Dari);
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = String.format("0000", Mesyuarat_Hingga);
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				WAKTU_MESYUARAT_DARI = Mesyuarat_Dari.substring(0, 2);
				WAKTU_MESYUARAT_HINGGA = Mesyuarat_Hingga.substring(0, 2);
				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
					WAKTU_MESYUARAT_DARI = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
					WAKTU_MESYUARAT_DARI = " PM";
				} else {
					WAKTU_MESYUARAT_DARI = " PM";
				}
				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
					WAKTU_MESYUARAT_HINGGA = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
					WAKTU_MESYUARAT_HINGGA = " PM";
				} else {
					WAKTU_MESYUARAT_HINGGA = " PM";
				}
				Mesyuarat_Dari = Mesyuarat_Dari.substring(0, 2) + ":" + Mesyuarat_Dari.substring(2, 4) + WAKTU_MESYUARAT_DARI;
				Mesyuarat_Hingga = Mesyuarat_Hingga.substring(0, 2) + ":" + Mesyuarat_Hingga.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				h = new Hashtable();
				h.put("ListNo", ListNo);
				h.put("ListIDMesyuarat", rs.getString(1) == null ? "" : rs.getString(1));
				h.put("ListUrusetiaSeksyen", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("ListBil", rs.getString(3) == null ? "" : rs.getString(3));
				h.put("ListTajuk", rs.getString(4) == null ? "" : rs.getString(4));
				h.put("ListTarikh", rs.getDate(5) == null ? "" : sdf1.format(rs.getDate(5)));
				h.put("ListMasa", Mesyuarat_Dari + " - " + Mesyuarat_Hingga);
				h.put("ListLokasi", rs.getString(8) == null ? "" : rs.getString(8));
				h.put("ListStatus", rs.getString(9) == null ? "" : rs.getString(9));
				h.put("ListNoFail", rs.getString(10) == null ? "" : rs.getString(10));
				listMesyuarat.add(h);
				ListNo++;
			}
			rs.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listMesyuarat;
	}
	
	
	public static boolean isNumeric(String inputData) {
		  return inputData.matches("-?\\d+(.\\d+)?");
	}

	public static Vector getListLogDokumenCarian(String noFail,
			String tajukFail, String tarikh, String user_id) throws Exception {
		 Db db = null;
		   // listLogDokumenById.clear();
		 Vector listLogDokumenByUserId = new Vector();
		    
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      SQLRenderer r = new SQLRenderer();
		      
		        
		      sql = "select a.ID_LOGDOKUMEN, a.NO_RUJUKAN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.TARIKH_DOKUMENDITERIMA, a.STATUS_LOGDOKUMEN, a.FLAG_LOGDOKUMEN, a.ID_PTFAIL, a.ID_DOKUMEN, a.ID_LOGDOKUMENKPTG, d.ID_FAIL " +
		      		"FROM TBLPFDLOGDOKUMEN a, USERS b, TBLPFDDOKUMEN c, TBLPFDFAIL d " +
		      		"where a.ID_PTFAIL = b.USER_ID " +
		      		//"and a.STATUS_LOGDOKUMEN = '1' " 
		      		"and a.ID_LOGDOKUMEN = c.ID_LOGDOKUMEN "+
                    "and c.ID_FAIL = d.ID_FAIL(+) "+
		      		"and a.id_ptfail = "+user_id+" ";
		      
				boolean setLimit = true;
			      
			      //NO FAIL
			      if (noFail != null) {
						if (!noFail.trim().equals("")) {
							setLimit = false;
							sql = sql + " AND UPPER(a.NO_RUJUKAN) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
						}
					}
			      
			      //TAJUK FAIL
			      if (tajukFail != null) {
						if (!tajukFail.trim().equals("")) {
							setLimit = false;
							sql = sql + " AND UPPER(a.TAJUK_DOKUMEN) LIKE '%' ||'" + tajukFail.toUpperCase().trim() + "'|| '%'";
						}
					}
			      
			      
			      //TARIKH DAFTAR FAIL	      
			     // SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			      if (tarikh != null) {	  	
						if (!tarikh.toString().trim().equals("")) {		
							setLimit = false;
//							sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
							sql = sql + " AND TO_CHAR(a.TARIKH_DOKUMEN,'DD/MM/YYYY') = '" + tarikh +"'";
							
						}
					}
		      
		      
		      
		      sql = sql + " ORDER BY ID_LOGDOKUMEN DESC";
		   
		      System.out.println("sql"+sql);
		      
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      Hashtable h;
		      int bil = 1;
		      int count = 0;
		      while (rs.next()) {
		    	  h = new Hashtable();
		    	  h.put("bil", bil);
		    	  h.put("idLogDokumen",rs.getString(1)==null?"":rs.getString(1));
		    	  h.put("no_Rujukan_Lain",rs.getString(2)==null?"":rs.getString(2));
		    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
		    	  h.put("tarikh_Dokumen", rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
		    	  h.put("tarikh_DokumenDiterima",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));		    	  
		    	  h.put("status_Logdokumen",rs.getString(6)==null?"":rs.getString(6));
		    	  h.put("flag_Logdokumen",rs.getString(7)==null?"":rs.getString(7));
		    	  h.put("id_ptfail", rs.getString(8)==null?"":rs.getString(8));
		    	  h.put("idDokumen", rs.getString(9)==null?"":rs.getString(9));
		    	  h.put("idLogDokumenKPTG", rs.getString(10)==null?"":rs.getString(10));
		    	  h.put("idFail", rs.getString(11)==null?"":rs.getString(11));

				if ((setLimit && bil <= 50) || setLimit == false) {	//RESERVED BY AZAM
					listLogDokumenByUserId.addElement(h);			
				}
		    	  
		    	  bil++;
		    	  count++;
		      }
		      
		      if (count == 0){
		    	  h = new Hashtable();
		    	  h.put("bil", "Tiada rekod.");
		    	  h.put("idLogDokumen","");
		    	  h.put("no_Rujukan_Lain", "");
		    	  h.put("tajuk_Dokumen", "");
		    	  h.put("tarikh_Dokumen", "");
		    	  h.put("tarikh_DokumenDiterima","");
		    	  h.put("status_Logdokumen","");
		    	  h.put("id_ptfail", "");

		    	  listLogDokumenByUserId.addElement(h);
		      }
		      //return list;
		    } finally {
		      if (db != null) db.close();
		    }
			return listLogDokumenByUserId;
	}

	public static Vector getListAgihanTugasanCarian(String noFail,
			String tajukFail, String tarikh, String user_id) throws Exception {
	    Db db = null;
	    //listAgihanTugasanById.clear();
	    Vector listAgihanTugasanById = new Vector();
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	        
	      sql = "select a.ID_DOKUMEN, a.NO_RUJUKAN_DOKUMEN, a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_NAMAPENERIMA, a.ID_FAIL " +
	      		"FROM TBLPFDDOKUMEN a " +
	      		"where (A.ID_NAMAPENERIMA = "+user_id+" or A.ID_SETIAUSAHA = "+user_id+") ";
	      	//	"and status_minit_pengarah = 1";
	      
	  	boolean setLimit = true;
	      
	      //NO FAIL
	      if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(a.NO_RUJUKAN_DOKUMEN) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      //TAJUK FAIL
	      if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND UPPER(a.TAJUK_DOKUMEN) LIKE '%' ||'" + tajukFail.toUpperCase().trim() + "'|| '%'";
				}
			}
	      
	      
	      //TARIKH DAFTAR FAIL	      
	     // SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
	      if (tarikh != null) {	  	
				if (!tarikh.toString().trim().equals("")) {		
					setLimit = false;
//					sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
					sql = sql + " AND TO_CHAR(a.TARIKH_DOKUMEN,'DD/MM/YYYY') = '" + tarikh +"'";
					
				}
			}
    
	      
	      sql = sql + "ORDER BY ID_DOKUMEN DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Dokumen",rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("no_Rujukan_Lain", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tajuk_Dokumen", rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("tarikh_Dokumen",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
	    	  h.put("nama_Pengirim",rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("id_namaPenerima", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("idFail", rs.getString(8)==null?"":rs.getString(8));

	  		if ((setLimit && bil <= 50) || setLimit == false) {	//RESERVED BY AZAM
	  			listAgihanTugasanById.addElement(h);			
			}
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idDokumen","");
	    	  h.put("no_Rujukan_Dokumen", "");
	    	  h.put("no_Rujukan_Lain", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen","");
	    	  h.put("nama_Pengirim","");
	    	  h.put("id_namaPenerima", "");
	    	  h.put("idFail", "");

	    	  listAgihanTugasanById.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		return listAgihanTugasanById;
	}

	public static Vector getListPenerimaTugasanCarian(String noFail,
			String tajukFail, String tarikh, String user_id) throws Exception {
		Db db = null;

		Vector listPenerimaTugasanById = new Vector();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	            
//	      sql = "select b.ID_DOKUMEN,a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
//    		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b " +
//    		"where a.ID_DOKUMEN = b.ID_DOKUMEN " +
//    		"and b.STATUS_TUGASAN = '1'"+
//    		"and (b.ID_PEGAWAI_YGMENERIMA1 = "+user_id+"" +
//    		"or b.ID_PEGAWAI_YGMENERIMA2 = "+user_id+"" +
//    		"or b.ID_PEGAWAI_YGMENERIMA3 = "+user_id+")";
	      
	      sql = "select b.ID_DOKUMEN,a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
	  		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b, TBLPFDMINITARAHANPEGAWAI c " +
	  		"where a.ID_DOKUMEN = b.ID_DOKUMEN " +
	  		"and c.STATUS_TUGASAN = '1'"+
	  		"and c.ID_minitarahan = b.ID_minitarahan" +
	  		"and c.ID_PEGAWAIYGMENERIMA = "+user_id+"";
	      
//	      select b.ID_DOKUMEN,a.NO_RUJUKAN_LAIN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN
//	      FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b, TBLPFDMINITARAHANPEGAWAI c
//	      where a.ID_DOKUMEN = b.ID_DOKUMEN
//	      and c.STATUS_TUGASAN = '1'
//	      and c.ID_minitarahan = b.ID_minitarahan
//	      and c.ID_PEGAWAIYGMENERIMA = '16101820'
	      
		  	boolean setLimit = true;
		      
		      //NO FAIL
		      if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(a.NO_RUJUKAN_LAIN) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
		      //TAJUK FAIL
		      if (tajukFail != null) {
					if (!tajukFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(a.TAJUK_DOKUMEN) LIKE '%' ||'" + tajukFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
		      
		      //TARIKH DAFTAR FAIL	      
		     // SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
		      if (tarikh != null) {	  	
					if (!tarikh.toString().trim().equals("")) {		
						setLimit = false;
//						sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
						sql = sql + " AND TO_CHAR(a.TARIKH_DOKUMEN,'DD/MM/YYYY') = '" + tarikh +"'";
						
					}
				}
	      
	      sql = sql + "ORDER BY ID_MINITARAHAN DESC";
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Lain", rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("tajuk_Dokumen", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tarikh_Dokumen",rs.getDate(4)==null?"":sdf.format(rs.getDate(4)));
	    	  h.put("nama_Pengirim",rs.getString(5)==null?"":rs.getString(5));
	    	  h.put("idFail", rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("idMinitArahan", rs.getString(7)==null?"":rs.getString(7));
		  		
	    	  if ((setLimit && bil <= 50) || setLimit == false) {	//RESERVED BY AZAM
	    		  listPenerimaTugasanById.addElement(h);			
				}

	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idDokumen","");
	    	  h.put("no_Rujukan_Dokumen", "");
	    	  h.put("no_Rujukan_Lain", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen","");
	    	  h.put("nama_Pengirim","");
	    	  h.put("id_namaPenerima", "");
	    	  h.put("idFail", "");
	    	  h.put("idMinitArahan", "");

	    	  listPenerimaTugasanById.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		return listPenerimaTugasanById;
	}

	public static Vector getListMesyuaratMyInfoCarian(String noFail,
			String tajukFail, String tarikh, String user_id) throws Exception {
		String sql = "";
		Db db = new Db();
		Vector listMesyuarat = new Vector();
		
		try {
			
			String SQL_Search_Seksyen = "";
			String SQL_Search_Negeri = "";
			sql = "SELECT M.ID_MESYUARAT, RS.NAMA_SEKSYEN, M.BIL_MESYUARAT, M.TAJUK_MESYUARAT, M.TARIKH_MESYUARAT, M.MASA_MESYUARAT_DARI, M.MASA_MESYUARAT_HINGGA, RL.LOKASI, RT.STATUS_MESYUARAT, FAIL.NO_FAIL " +
			"FROM TBLPFDMESYUARAT M, TBLRUJSEKSYEN RS, TBLPFDRUJLOKASIMESYUARAT RL, TBLPFDRUJSTATUSMESYUARAT RT, TBLPFDFAIL FAIL " + 
			"WHERE M.ID_SEKSYEN = RS.ID_SEKSYEN(+) AND M.ID_LOKASI = RL.ID_LOKASI(+) AND M.ID_STATUS = RT.ID_STATUS(+) AND M.ID_FAIL = FAIL.ID_FAIL " +
			"AND M.ID_MESYUARAT IN (SELECT ID_MESYUARAT FROM TBLPFDAHLIMESYUARAT WHERE ID_PEGAWAI = "+user_id+")"+
			"AND M.TARIKH_MESYUARAT <= sysdate ";
			//"ORDER BY M.TARIKH_MESYUARAT";
		  	boolean setLimit = true;
		      
		      //NO FAIL
		      if (noFail != null) {
					if (!noFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(FAIL.NO_FAIL) LIKE '%' ||'" + noFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
		      //TAJUK FAIL
		      if (tajukFail != null) {
					if (!tajukFail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(M.TAJUK_MESYUARAT) LIKE '%' ||'" + tajukFail.toUpperCase().trim() + "'|| '%'";
					}
				}
		      
		      
		      //TARIKH DAFTAR FAIL	      
		     // SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
		      if (tarikh != null) {	  	
					if (!tarikh.toString().trim().equals("")) {		
						setLimit = false;
//						sql = sql + " AND A.TARIKH_DAFTAR_FAIL = '" + sdf1.format(sdf.parse(tarikhDaftar)).toUpperCase() +"'";
						sql = sql + " AND TO_CHAR(M.TARIKH_MESYUARAT,'DD/MM/YYYY') = '" + tarikh +"'";
						
					}
				}

			
		    sql = sql + "ORDER BY M.TARIKH_MESYUARAT";
			
			
			int ListNo = 1;
			String WaktuMesyuarat = "";
			Hashtable h = null;
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			rs = stmt.executeQuery(sql);
			
			String Mesyuarat_Dari = "", Mesyuarat_Hingga = "", WAKTU_MESYUARAT_DARI = "", WAKTU_MESYUARAT_HINGGA = "";
			while (rs.next()) {
				WaktuMesyuarat = rs.getString(10) == null ? "" : rs.getString(10);
				if (!"".equalsIgnoreCase(WaktuMesyuarat)) {
					if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " AM";
					} else if ("1".equalsIgnoreCase(WaktuMesyuarat)) {
						WaktuMesyuarat = " PM";
					} else {
						WaktuMesyuarat = " PM";
					}
				}
				
				Mesyuarat_Dari = rs.getString(6) == null ? "" : rs.getString(6);
				Mesyuarat_Hingga = rs.getString(7) == null ? "" : rs.getString(7);
				if (Mesyuarat_Dari.length() < 4) {
					Mesyuarat_Dari = String.format("0000", Mesyuarat_Dari);
				}
				if (Mesyuarat_Hingga.length() < 4) {
					Mesyuarat_Hingga = String.format("0000", Mesyuarat_Hingga);
				}
				if (!isNumeric(Mesyuarat_Dari)) {
					Mesyuarat_Dari = "1000";
				}
				if (!isNumeric(Mesyuarat_Hingga)) {
					Mesyuarat_Hingga = "1100";
				}
				WAKTU_MESYUARAT_DARI = Mesyuarat_Dari.substring(0, 2);
				WAKTU_MESYUARAT_HINGGA = Mesyuarat_Hingga.substring(0, 2);
				if (Integer.parseInt(WAKTU_MESYUARAT_DARI) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 12) {
					WAKTU_MESYUARAT_DARI = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_DARI) > 11 && Integer.parseInt(WAKTU_MESYUARAT_DARI) < 19) {
					WAKTU_MESYUARAT_DARI = " PM";
				} else {
					WAKTU_MESYUARAT_DARI = " PM";
				}
				if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) >= 0 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 12) {
					WAKTU_MESYUARAT_HINGGA = " AM";
				} else if (Integer.parseInt(WAKTU_MESYUARAT_HINGGA) > 11 && Integer.parseInt(WAKTU_MESYUARAT_HINGGA) < 19) {
					WAKTU_MESYUARAT_HINGGA = " PM";
				} else {
					WAKTU_MESYUARAT_HINGGA = " PM";
				}
				Mesyuarat_Dari = Mesyuarat_Dari.substring(0, 2) + ":" + Mesyuarat_Dari.substring(2, 4) + WAKTU_MESYUARAT_DARI;
				Mesyuarat_Hingga = Mesyuarat_Hingga.substring(0, 2) + ":" + Mesyuarat_Hingga.substring(2, 4) + WAKTU_MESYUARAT_HINGGA;
				
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				
				h = new Hashtable();
				h.put("ListNo", ListNo);
				h.put("ListIDMesyuarat", rs.getString(1) == null ? "" : rs.getString(1));
				h.put("ListUrusetiaSeksyen", rs.getString(2) == null ? "" : rs.getString(2));
				h.put("ListBil", rs.getString(3) == null ? "" : rs.getString(3));
				h.put("ListTajuk", rs.getString(4) == null ? "" : rs.getString(4));
				h.put("ListTarikh", rs.getDate(5) == null ? "" : sdf1.format(rs.getDate(5)));
				h.put("ListMasa", Mesyuarat_Dari + " - " + Mesyuarat_Hingga);
				h.put("ListLokasi", rs.getString(8) == null ? "" : rs.getString(8));
				h.put("ListStatus", rs.getString(9) == null ? "" : rs.getString(9));
				h.put("ListNoFail", rs.getString(10) == null ? "" : rs.getString(10));
				listMesyuarat.add(h);
				ListNo++;
			}
			rs.close();
		} finally {
			if (db != null) {
				db.close();
			}
		}
		return listMesyuarat;
	}

	public void setListTugasanSelesai(String user_id) throws Exception {
		Db db = null;
		listTugasanByIdSelesai.clear();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      
	        
	      sql = "select b.ID_DOKUMEN,a.NO_RUJUKAN_LAIN,a.NO_RUJUKAN_DOKUMEN, a.TAJUK_DOKUMEN, a.TARIKH_DOKUMEN, a.NAMA_PENGIRIM, a.ID_FAIL, b.ID_MINITARAHAN " +
		  		"FROM TBLPFDDOKUMEN a, TBLPFDMINITARAHAN b " +
		  		"where a.ID_DOKUMEN = b.ID_DOKUMEN(+) " +
		  		"and b.ID_STATUSTINDAKAN = '4'";
//		  		"and ( b.ID_PEGAWAI_YGMENGARAH = "+user_id+""+
//		  		"or b.ID_PEGAWAI_YGMENERIMA1 = "+user_id+"" +
//		  		"or b.ID_PEGAWAI_YGMENERIMA2 = "+user_id+"" +
//		  		"or b.ID_PEGAWAI_YGMENERIMA3 = "+user_id+")";
	      sql = sql + "ORDER BY b.TARIKH_MINIT_ARAHAN DESC";
	      
	   
	      System.out.println("sql"+sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("idDokumen",rs.getString(1)==null?"":rs.getString(1));
	    	  h.put("no_Rujukan_Lain", rs.getString(2)==null?"":rs.getString(2));
	    	  h.put("no_Rujukan_Dokumen", rs.getString(3)==null?"":rs.getString(3));
	    	  h.put("tajuk_Dokumen",rs.getString(4)==null?"":rs.getString(4));
	    	  h.put("tarikh_Dokumen",rs.getDate(5)==null?"":sdf.format(rs.getDate(5)));
	    	  h.put("nama_Pengirim", rs.getString(6)==null?"":rs.getString(6));
	    	  h.put("idFail", rs.getString(7)==null?"":rs.getString(7));
	    	  h.put("idMinitArahan", rs.getString(8)==null?"":rs.getString(8));

	    	  listTugasanByIdSelesai.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if (count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "Tiada rekod.");
	    	  h.put("idDokumen","");
	    	  h.put("no_Rujukan_Dokumen", "");
	    	  h.put("no_Rujukan_Lain", "");
	    	  h.put("tajuk_Dokumen", "");
	    	  h.put("tarikh_Dokumen","");
	    	  h.put("nama_Pengirim","");
	    	  h.put("id_namaPenerima", "");
	    	  h.put("idFail", "");
	    	  h.put("idMinitArahan","");

	    	  listTugasanByIdSelesai.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
		
	}

	public Vector getListTugasanSelesai() {

		return listTugasanByIdSelesai;
	}

}

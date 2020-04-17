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

public class FrmPendaftaranFailData {
	
	static Vector paparFailLama = null;
	static Vector paparFailBaru = null;
	static Vector paparFail = null;
	static Vector paparSemuaFail = null;
	static Vector paparNoTurutan = null;
	static Vector paparFailArkib = null;
	static Vector listUrusan = null;
	static Vector listSubUrusan = null;
	static Vector listUrusanArkib = null;
	static Vector listSubSubUrusan = null;
	static Vector listSubUrusanByIdUrusan = null;
	static Vector listSubUrusanByIdUrusanIdSuburusan = null;
	static Vector listSeksyen = null;
	static Vector listTaraf = null;
	static Vector listStatus = null;
	static Vector listLokasi= null;

	
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Boolean noFailExists(String noFail, String user_negeri) throws Exception {
		// kmie, 20100803
		// to check a nombor fail exists or not
		Boolean returnValue = false;
		
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
//			SQLRenderer r = new SQLRenderer();
//			r.add("ID_FAIL");
//			r.add("NO_FAIL", noFail);
//			sql = r.getSQLSelect("TBLPFDFAIL");
//			r.clear();
			
			sql = "SELECT ID_FAIL, NO_FAIL FROM TBLPFDFAIL WHERE NO_FAIL LIKE '" + noFail + "%' and ID_STATUS <> '999' and ID_NEGERI = '"+user_negeri+"'";
			
			rs = stmt.executeQuery(sql);
			returnValue = rs.next();
			
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	public static void getUrusan() throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listUrusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_URUSAN, NAMA_URUSAN, KOD_URUSAN " +
				  "FROM TBLRUJURUSAN " +
				  "WHERE ID_NEGERI IS NULL "+
				  "ORDER BY NAMA_URUSAN";
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_URUSAN",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("NAMA_URUSAN",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
				h.put("KOD_URUSAN",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
				
				listUrusan.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListUrusan(){
		 
		  return listUrusan;
	  }
	public static void getFailArkib(String id_FailArkib) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			paparFailArkib = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.ID_URUSAN,A.KOD_URUSAN,A.NAMA_URUSAN,B.ID_SUBURUSAN,B.KOD_SUBURUSAN,B.NAMA_SUBURUSAN,C.ID_SUBSUBURUSAN,C.KOD_SUBSUBURUSAN,C.NAMA_SUBSUBURUSAN,"+
				  " D.ID_SUBSUBSUBURUSAN,D.KOD_SUBSUBSUBURUSAN,D.NAMA_SUBSUBSUBURUSAN, E.NO_FAIL_ARKIB" +
				  " FROM TBLRUJURUSAN A, TBLRUJSUBURUSAN B, TBLRUJSUBSUBURUSAN C, TBLRUJSUBSUBSUBURUSAN D, TBLRUJFAILARKIB E" +
				  " WHERE E.ID_FAILARKIB = "+id_FailArkib+
				  " AND A.ID_URUSAN = E.ID_URUSAN"+
				  " AND B.ID_SUBURUSAN = E.ID_SUBURUSAN"+
				  " AND C.ID_SUBSUBURUSAN = E.ID_SUBSUBURUSAN"+
				  " AND D.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN";
				 
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_URUSAN",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
				h.put("KOD_URUSAN",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
				h.put("NAMA_URUSAN",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
				h.put("ID_SUBURUSAN",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
				h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
				h.put("ID_SUBSUBURUSAN",rs.getString("ID_SUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBURUSAN"));
				h.put("KOD_SUBSUBURUSAN",rs.getString("KOD_SUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBURUSAN"));
				h.put("NAMA_SUBSUBURUSAN",rs.getString("NAMA_SUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBURUSAN"));
				h.put("ID_SUBSUBSUBURUSAN",rs.getString("ID_SUBSUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBSUBURUSAN"));
				h.put("KOD_SUBSUBSUBURUSAN",rs.getString("KOD_SUBSUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBSUBURUSAN"));
				h.put("NAMA_SUBSUBSUBURUSAN",rs.getString("NAMA_SUBSUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBSUBURUSAN"));
				h.put("NO_FAIL_ARKIB",rs.getString("NO_FAIL_ARKIB")==null?"":rs.getString("NO_FAIL_ARKIB"));
			
				paparFailArkib.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector paparFailArkib(){
		 
		  return paparFailArkib;
	  }
	public static void getUrusanArkib() throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listUrusanArkib = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
		sql = "Select id_urusan,kod_urusan,nama_urusan from tblrujurusan where kod_urusan in ('100','200','300','400','500')"
				+ " and nama_urusan in ('PENTADBIRAN','PENGURUSAN TANAH DAN BANGUNAN','PENGURUSAN ASET','PENGURUSAN KEWANGAN','PENGURUSAN SUMBER MANUSIA') and id_negeri is null order by kod_urusan asc";
				
		ResultSet rs = stmt.executeQuery(sql);
		Hashtable h;
		while (rs.next()) {
			h = new Hashtable();
			h.put("id_urusan",rs.getString("id_urusan")==null?"":rs.getString("id_urusan"));
			h.put("kod_urusan",rs.getString("kod_urusan")==null?"":rs.getString("kod_urusan"));
			h.put("nama_urusan",rs.getString("nama_urusan")==null?"":rs.getString("nama_urusan"));
			
			listUrusanArkib.addElement(h);
		}
			
		} finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListUrusanArkib(){
		 
		  return listUrusanArkib;
	  }
	public static void getSubUrusanByIdUrusan(String idUrusan) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listSubUrusanByIdUrusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SUBURUSAN, NAMA_SUBURUSAN, KOD_SUBURUSAN " +
				  "FROM TBLRUJSUBURUSAN " +
				  "WHERE ID_URUSAN = "+idUrusan+
				  " ORDER BY KOD_SUBURUSAN";
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
				
				listSubUrusanByIdUrusan.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListSubUrusanByIdUrusan(){
		 
		  return listSubUrusanByIdUrusan;
	  }
	public static void getSeksyen(String idSeksyen) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listSeksyen = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SEKSYEN, KOD_SEKSYEN, NAMA_SEKSYEN " +
				  "FROM TBLRUJSEKSYEN " +
				  "WHERE ID_SEKSYEN = "+idSeksyen;
				 
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SEKSYEN",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
				h.put("KOD_SEKSYEN",rs.getString("KOD_SEKSYEN")==null?"":rs.getString("KOD_SEKSYEN"));
				h.put("NAMA_SEKSYEN",rs.getString("NAMA_SEKSYEN")==null?"":rs.getString("NAMA_SEKSYEN"));
				
				listSeksyen.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListSeksyen(){
		 
		  return listSeksyen;
	  }
	public static void getTaraf(String idTaraf) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listTaraf = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_TARAFKESELAMATAN, KOD_TARAF_KESELAMATAN, TARAF_KESELAMATAN " +
				  "FROM TBLPFDRUJTARAFKESELAMATAN " +
				  "WHERE ID_TARAFKESELAMATAN = "+idTaraf;
				 
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_TARAFKESELAMATAN",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
				h.put("KOD_TARAF_KESELAMATAN",rs.getString("KOD_TARAF_KESELAMATAN")==null?"":rs.getString("KOD_TARAF_KESELAMATAN"));
				h.put("TARAF_KESELAMATAN",rs.getString("TARAF_KESELAMATAN")==null?"":rs.getString("TARAF_KESELAMATAN"));
				
				listTaraf.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListTaraf(){
		 
		  return listTaraf;
	  }
	
	public static void getStatusFail(String idStatus) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listStatus = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_STATUS, KETERANGAN "+
				  "FROM TBLRUJSTATUS " +
				  "WHERE ID_STATUS = "+idStatus;
				 
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_STATUS",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
				h.put("KETERANGAN",rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
				
				listStatus.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListStatus(){
		 
		  return listStatus;
	  }
	
	public static void getLokasiFail(String idLokasi) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listLokasi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_LOKASIFAIL, LOKASI_FAIL "+
				  "FROM TBLPFDRUJLOKASIFAIL " +
				  "WHERE ID_LOKASIFAIL = "+idLokasi;
				 
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_LOKASIFAIL",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
				h.put("LOKASI_FAIL",rs.getString("LOKASI_FAIL")==null?"":rs.getString("LOKASI_FAIL"));
				
				listLokasi.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListLokasi(){
		 
		  return listLokasi;
	  }
    public static void getSubUrusanByIdUrusanIdSuburusan(String idUrusan,String idSuburusan) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listSubUrusanByIdUrusanIdSuburusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SUBURUSAN, NAMA_SUBURUSAN, KOD_SUBURUSAN " +
				  "FROM TBLRUJSUBURUSAN " +
				  "WHERE ID_URUSAN = "+idUrusan+
				  " AND ID_SUBURUSAN = "+idSuburusan+
				  " ORDER BY KOD_SUBURUSAN";
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
				
				listSubUrusanByIdUrusanIdSuburusan.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListSubUrusanByIdUrusanIdSuburusan(){
		 
		  return listSubUrusanByIdUrusanIdSuburusan;
	  }
	public static void getSubUrusan(String idUrusan) throws Exception {
		
		String sql = "";
		Db db = null;
		
		try{
			listSubUrusan= new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT ID_SUBURUSAN, NAMA_SUBURUSAN, KOD_SUBURUSAN " +
				  "FROM TBLRUJSUBURUSAN " +
				  "WHERE ID_URUSAN = "+idUrusan+
				  " ORDER BY KOD_SUBURUSAN";
				  
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_SUBURUSAN",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
				h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
				h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
				
				listSubUrusan.addElement(h);
				
			}
			
			
			
		}
		finally {
			if (db != null)
				db.close();
		}
		
	}
	public static Vector getListSubUrusan(){
		 
		  return listSubUrusan;
	  }
	 public static void getSubSubUrusan(String idSubSubUrusan) throws Exception {
			
		 	String sql = "";
			Db db = null;
			
			try{
				listSubSubUrusan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
		       
				sql = "Select a.id_SubSuburusan,a.kod_SubSuburusan,a.nama_SubSuburusan" +
		    		" from tblrujsubsuburusan a, tblrujsuburusan b where a.id_suburusan = b.id_suburusan and a.id_SubSuburusan = "+idSubSubUrusan+"" +
		    		" order by a.kod_SubSuburusan ";
				System.out.println("sql subsuburusan---"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("id_SubSuburusan",rs.getString("id_SubSuburusan")==null?"":rs.getString("id_SubSuburusan"));
					h.put("kod_SubSuburusan",rs.getString("kod_SubSuburusan")==null?"":rs.getString("kod_SubSuburusan"));
					h.put("nama_SubSuburusan",rs.getString("nama_SubSuburusan")==null?"":rs.getString("nama_SubSuburusan"));
					
					listSubSubUrusan.addElement(h);
					
				}
				
			} finally {
				if (db != null)
					db.close();
			}
			
		  }
	 public static Vector getListSubSubUrusan(){
		 
		  return listSubSubUrusan;
	  }    
//	public String getNoFail(boolean isFailSeksyen, int ID_TARAFFAIL, int ID_SEKSYEN, int ID_NEGERI, int ID_DAERAH, int ID_URUSAN, int ID_SUBURUSAN, int ID_SUBSUBURUSAN, boolean haveAktiviti) throws Exception {
//	
//			// kmie, 20100803
//		// to generate nombor fail
//		String returnValue = "";
//		Db db = new Db();
//		
//		try {
//			String sql = "";
//			Statement stmt = db.getStatement();
//			ResultSet rs = null;
//			SQLRenderer r = new SQLRenderer();
//			Boolean error = false;
//			
//			String BASE = "JKPTG", TARAFFAIL = "", SEKSYEN = "", NEGERI = "", DAERAH = "", URUSAN = "", SUBURUSAN = "", SUBSUBURUSAN = "", AKTIVITI = "";
//			
//			// is taraf keselamatan valid?
//			r.add("KOD_TARAF_KESELAMATAN");
//			r.add("ID_TARAFKESELAMATAN", ID_TARAFFAIL);
//			sql = r.getSQLSelect("TBLPFDRUJTARAFKESELAMATAN");
//			r.clear();
//			rs = stmt.executeQuery(sql);
//			if (rs.next()) {
//				TARAFFAIL = rs.getString(1) == null ? "" : rs.getString(1);
//				if (!"".equalsIgnoreCase(TARAFFAIL)) {
//					TARAFFAIL = "(" + TARAFFAIL + ")";
//				}
//			} else {
//				error = true;
//			}
//			TARAFFAIL += "/";
//			
//			if (!error) {
//				if (isFailSeksyen) {
//					// is seksyen valid?
//					
//					r.add("KOD_SEKSYEN");
//					r.add("ID_SEKSYEN", ID_SEKSYEN);
//					sql = r.getSQLSelect("TBLRUJSEKSYEN");
//					r.clear();
//					rs = stmt.executeQuery(sql);
//					if (rs.next()) {
//						SEKSYEN = rs.getString(1) == null ? "" : rs.getString(1);
//						SEKSYEN += "/";
//					} else {
//						error = true;
//					}
//					NEGERI = "";
//				} else {
//					// is negeri valid?
//					r.add("ABBREV");
//					r.add("ID_NEGERI", ID_NEGERI);
//					sql = r.getSQLSelect("TBLRUJNEGERI");
//					r.clear();
//					rs = stmt.executeQuery(sql);
//					if (rs.next()) {
//						NEGERI = rs.getString(1) == null ? "" : rs.getString(1);
//						NEGERI += "/"; 
//						
//						r.add("KOD_DAERAH");
//						r.add("ID_DAERAH", ID_DAERAH);
//						sql = r.getSQLSelect("TBLRUJDAERAH");
//						r.clear();
//						rs = stmt.executeQuery(sql);
//						if (rs.next()) {
//							DAERAH = rs.getString(1) == null ? "" : rs.getString(1);
//							DAERAH += "/"; 
//						} else {
//							error = true;
//						}
//					} else {
//						error = true;
//					}
//					SEKSYEN = "";
//				}
//			}
//			if (!error) {
//				if (ID_URUSAN > 0) {
//					// is urusan valid?
//					r.add("KOD_URUSAN");
//					r.add("ID_URUSAN", ID_URUSAN);
//					sql = r.getSQLSelect("TBLRUJURUSAN");
//					r.clear();
//					rs = stmt.executeQuery(sql);
//					if (rs.next()) {
//						URUSAN = rs.getString(1) == null ? "" : rs.getString(1);
//						URUSAN += "/";
//					} else {
//						error = true;
//					}
//				}
//			}
//			if (!error) {
//				if (ID_SUBURUSAN > 0 && ID_URUSAN > 0) {
//					// is suburusan valid?
//					r.add("KOD_SUBURUSAN");
//					r.add("ID_SUBURUSAN", ID_SUBURUSAN);
//					r.add("ID_URUSAN", ID_URUSAN);
//					sql = r.getSQLSelect("TBLRUJSUBURUSAN");
//					r.clear();
//					rs = stmt.executeQuery(sql);
//					if (rs.next()) {
//						SUBURUSAN = rs.getString(1) == null ? "" : rs.getString(1);
//						SUBURUSAN += "/";
//					} else {
//						error = true;
//					}
//				}
//			}
//			if (!error) {
//				if (ID_SUBSUBURUSAN > 0 & ID_SUBURUSAN > 0 && ID_URUSAN > 0) {
//					// is subsuburusan valid?
//					r.add("KOD_SUBSUBURUSAN");
//					r.add("ID_SUBSUBURUSAN", ID_SUBSUBURUSAN);
//					r.add("ID_SUBURUSAN", ID_SUBURUSAN);
//					r.add("ID_URUSAN", ID_URUSAN);
//					sql = r.getSQLSelect("TBLRUJSUBSUBURUSAN");
//					r.clear();
//					rs = stmt.executeQuery(sql);
//					if (rs.next()) {
//						SUBSUBURUSAN = rs.getString(1) == null ? "" : rs.getString(1);
//						SUBSUBURUSAN += "/";
//					} else {
//						error = true;
//					}
//				}
//			}
//			
//			if (!error && haveAktiviti) {
//				// check if this no fail already exist
//				String NOFAIL_CHK = BASE + TARAFFAIL + "101/" + SEKSYEN + NEGERI + DAERAH + URUSAN + SUBURUSAN + SUBSUBURUSAN;
//				int NOFAIL_COUNTER = 0;
//				
//				sql = "SELECT COUNT(ID_FAIL) FROM TBLPFDFAIL WHERE NO_FAIL LIKE '" + NOFAIL_CHK + "%'";
//				rs = stmt.executeQuery(sql);
//				if (rs.next()) {
//					// exists, add counter
//					NOFAIL_COUNTER = rs.getInt(1);
//				}
//				NOFAIL_COUNTER = NOFAIL_COUNTER + 1;
//				AKTIVITI = "-" + NOFAIL_COUNTER;
//				
//				if ("".equalsIgnoreCase(SUBSUBURUSAN)) {
//					if ("".equalsIgnoreCase(SUBURUSAN)) {
//						if (URUSAN.endsWith("/")) {
//							URUSAN = URUSAN.substring(0, URUSAN.length() - 1);
//						}
//					} else {
//						if (SUBURUSAN.endsWith("/")) {
//							SUBURUSAN = SUBURUSAN.substring(0, SUBURUSAN.length() - 1);
//						}
//					}
//				} else {
//					if (SUBSUBURUSAN.endsWith("/")) {
//						SUBSUBURUSAN = SUBSUBURUSAN.substring(0, SUBSUBURUSAN.length() - 1);
//					}
//				}
//			}
//			
//			if (!error) {
//				returnValue = BASE + TARAFFAIL + "101/" + SEKSYEN + NEGERI + DAERAH + URUSAN + SUBURUSAN + SUBSUBURUSAN + AKTIVITI;
//				if (returnValue.endsWith("/")) {
//					returnValue = returnValue.substring(0, returnValue.length() - 1);
//				}
//			}
//		} finally {
//			if (db != null) db.close();
//		}
//		return returnValue;
//	}
	

		public static Vector getPaparFailLama(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparFailLama = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_LOKASIFAIL, FAHARASAT, ID_STATUS, NO_TURUTAN, NO_TURUTAN_SUBJAKET, NO_TURUTAN_JLD, NO_FAIL_ROOT " +
					  "FROM TBLPFDFAIL " +
					  "WHERE ID_FAIL='"+idFail+"'";
				
				System.out.println("sql papar fail lama--"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("noFailRoot",rs.getString("NO_FAIL_ROOT")==null?"":rs.getString("NO_FAIL_ROOT"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idDaerah",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("noTurutan",rs.getString("NO_TURUTAN")==null?"":rs.getString("NO_TURUTAN"));
					h.put("noJilid",rs.getString("NO_TURUTAN_JLD")==null?"":rs.getString("NO_TURUTAN_JLD"));
					h.put("noSubjaket",rs.getString("NO_TURUTAN_SUBJAKET")==null?"":rs.getString("NO_TURUTAN_SUBJAKET"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					
					
					paparFailLama.addElement(h);
					
				}
				
				
				return paparFailLama;
			}
			finally {
				if (db != null)
					db.close();
			}
		}

		public static Vector getPaparFailBaru(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparFailBaru = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, ID_DAERAH, ID_URUSAN, ID_SUBURUSAN,ID_SUBSUBURUSAN,ID_AKTIVITI,TARIKH_DAFTAR_FAIL, TAJUK_FAIL, NO_FAIL, ID_NEGERI, ID_LOKASIFAIL, FAHARASAT, ID_STATUS " +
					  "FROM TBLPFDFAIL " +
					  "WHERE ID_FAIL='"+idFail+"'";
				
				System.out.println("sql papar fail baru--"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idDaerah",rs.getString("ID_DAERAH")==null?"":rs.getString("ID_DAERAH"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("idSubSubUrusan",rs.getString("ID_SUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBURUSAN"));
					h.put("idAktiviti",rs.getString("ID_AKTIVITI")==null?"":rs.getString("ID_AKTIVITI"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
//					h.put("noFailArkib",rs.getString("NO_FAIL_ARKIB")==null?"":rs.getString("NO_FAIL_ARKIB"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					
					
					paparFailBaru.addElement(h);
					
				}
				
				
				return paparFailBaru;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
		
		public static Vector getPaparFailArkib(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparFailBaru = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT G.NO_FAIL AS NO_FAIL_ASAL,A.ID_FAIL, A.ID_TARAFKESELAMATAN, A. ID_SEKSYEN, A.ID_AKTIVITI, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, A.ID_LOKASIFAIL, A.FAHARASAT, A.ID_STATUS, " +
					  "B.KOD_URUSAN, B.NAMA_URUSAN, C.KOD_SUBURUSAN, C.NAMA_SUBURUSAN, D.KOD_SUBSUBURUSAN, D.NAMA_SUBSUBURUSAN, E.KOD_SUBSUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN "+
					  "FROM TBLPFDFAIL A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E, TBLPFDFAILMAPPING F, TBLPFDFAIL G " +
					  "WHERE " +
					  "A.ID_URUSAN = B.ID_URUSAN " +
					  "AND A.ID_SUBURUSAN = C.ID_SUBURUSAN " +
					  "AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN " +
					  "AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN(+) " +
					  "AND A.ID_FAIL = F.ID_FAILARKIB(+) "+
					  "AND F.ID_FAILASAL = G.ID_FAIL(+) "+
					  "AND A.ID_FAIL='"+idFail+"'";
				
				System.out.println("sql papar arkib--"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("KOD_URUSAN",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
					h.put("NAMA_URUSAN",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
					h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
					h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
					h.put("KOD_SUBSUBURUSAN",rs.getString("KOD_SUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBURUSAN"));
					h.put("NAMA_SUBSUBURUSAN",rs.getString("NAMA_SUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBURUSAN"));
					h.put("KOD_SUBSUBSUBURUSAN",rs.getString("KOD_SUBSUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBSUBURUSAN"));
					h.put("NAMA_SUBSUBSUBURUSAN",rs.getString("NAMA_SUBSUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBSUBURUSAN"));
					h.put("idAktiviti",rs.getString("ID_AKTIVITI")==null?"":rs.getString("ID_AKTIVITI"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("kabinet",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					h.put("noFailLama",rs.getString("NO_FAIL_ASAL")==null?"":rs.getString("NO_FAIL_ASAL"));
					
					paparFailBaru.addElement(h);
					
				}
				
				
				return paparFailBaru;
			}
			finally {
				if (db != null)
					db.close();
			}
		}
		public static Vector getPaparFail(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparFail = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT A.ID_FAIL AS ID_FAIL, A.ID_TARAFKESELAMATAN AS ID_TARAFKESELAMATAN, A.ID_SEKSYEN AS ID_SEKSYEN, A.ID_URUSAN AS ID_URUSAN, A.ID_SUBURUSAN AS ID_SUBURUSAN, A.TARIKH_DAFTAR_FAIL AS TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL AS TAJUK_FAIL, A.NO_FAIL AS NO_FAIL, A.ID_NEGERI AS ID_NEGERI, A.LOKASI_FAIL AS LOKASI_FAIL, A.FAHARASAT AS FAHARASAT, A.ID_STATUS AS ID_STATUS, A.ID_MASUK AS ID_MASUK, U.USER_ID AS USER_ID, U.USER_NAME AS USER_NAME " +
					  "FROM TBLPFDFAIL A, USERS U " +
					  "WHERE A.ID_MASUK=U.USER_ID " +
					  "AND A.ID_FAIL = '"+idFail+"'";
				
				System.out.println("sql fail baru ::"+sql);
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("lokasi",rs.getString("LOKASI_FAIL")==null?"":rs.getString("LOKASI_FAIL"));
					h.put("faharasat",rs.getString("FAHARASAT")==null?"":rs.getString("FAHARASAT"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				//	h.put("keterangan",rs.getString("keterangan") == null?"":rs.getString("keterangan"));
			    	h.put("user_Name", rs.getString("USER_NAME") == null?"":rs.getString("USER_NAME"));
					
					paparFail.addElement(h);
					
				}
				
				
				return paparFail;
			}
			finally {
				if (db != null)
					db.close();
			}
		}

		public static Vector getNoFailExist(String noFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				Vector noFailExist = new Vector();
				noFailExist.clear();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT NO_FAIL FROM TBLPDTAKTA WHERE NO_FAIL LIKE '"+noFail+"'";
				System.out.println("sql" +sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					
					noFailExist.addElement(h);
					
				}
				
				
				return noFailExist;
			}
			finally {
				if (db != null)
					db.close();
			}
		}

		public static Object updateFailLama(Hashtable h) {
			// TODO Auto-generated method stub
			return null;
		}

		public static String addFailLamaSeksyen(Hashtable data) throws Exception{
		 	Db db = null;
		    String sql = "";
		   
		    
		    Date now = new Date();
		    try
		    {	 
		    	  
		    	  long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
		    	  String noFail = (String)data.get("no_Fail");
		    	  String noFailRoot = (String)data.get("noFailRoot");
		    	  int noTurutan = (Integer)data.get("noTurutan");
		    	  int noJilid = (Integer)data.get("noJilid");
		    	  int noSubjaket = (Integer)data.get("noSubjaket");
		    	  int negeri = (Integer)data.get("id_Negeri");
		    	  int seksyen = (Integer)data.get("id_Seksyen");
		    	  int urusan = (Integer)data.get("id_Urusan");
			      int suburusan = (Integer)data.get("id_Suburusan");
//			      int subsuburusan = (Integer)data.get("id_Subsuburusan");
			      String taraf = (String)data.get("id_Tarafkeselamatan");
			      String tajukFail = (String)data.get("tajuk_Fail");
			      String status = (String)data.get("id_Status");
			      String lokasi = (String)data.get("id_Lokasi");
			      String faharasat = (String)data.get("kabinet");
			      String tarikhDaftar = (String)data.get("tarikhDaftar");
			      String idMasuk = (String)data.get("id_Masuk");
			      int flag_jenis_Fail = (Integer)data.get("flag_jenis_Fail");
			      int viewFail = (Integer)data.get("view_Fail");
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Fail", idFail);
			      r.add("no_Fail", noFail.toUpperCase());
			      r.add("no_Fail_root", noFailRoot);
			      r.add("no_Turutan", noTurutan);
			      r.add("NO_TURUTAN_JLD", noJilid);
			      r.add("NO_TURUTAN_SUBJAKET", noSubjaket);		
			      r.add("id_Negeri", negeri);
			      r.add("id_Seksyen", seksyen);
			      r.add("id_Urusan", urusan);
			      r.add("id_Suburusan", suburusan);
//			      r.add("id_SubSuburusan", subsuburusan);
			      r.add("flag_jenis_Fail", flag_jenis_Fail);
			      r.add("flag_Fail", flag_jenis_Fail);
			      r.add("FLAG_VIEW_FILE", viewFail);
			      r.add("id_Tarafkeselamatan", taraf);
			      r.add("tajuk_Fail", tajukFail);
			      r.add("id_Status", status);
			      r.add("id_lokasifail", lokasi);
			      r.add("faharasat", faharasat);
			  	  r.add("tarikh_daftar_fail", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			      r.add("id_Masuk",idMasuk);
			      sql = r.getSQLInsert("tblpfdfail");
			      
			      
			      stmt.executeUpdate(sql);
			      
			      return "" +idFail;
			      
			    } finally {
			      if (db != null) db.close();
			    }
			     
		}

		public static String addFailLamaNegeri(Hashtable data) throws Exception {
			Db db = null;
		    String sql = "";
		   
		    
		    Date now = new Date();
		    try
		    {	 
		    	  
		    	  long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
		    	  String noFail = (String)data.get("no_Fail");
		    	  String noFailRoot = (String)data.get("noFailRoot");
		    	  String noTurutan = (String)data.get("noTurutan");
		    	  String noJilid = (String)data.get("noJilid");
		    	  String noSubjaket = (String)data.get("noSubjaket");
			      Integer negeri = (Integer)data.get("id_Negeri");
			      Integer daerah = (Integer)data.get("id_Daerah");
			      Integer seksyen = (Integer)data.get("id_Seksyen");
			      Integer urusan = (Integer)data.get("id_Urusan");
			      String suburusan = (String)data.get("id_Suburusan");
			      String taraf = (String)data.get("id_Tarafkeselamatan");
			      String tajukFail = (String)data.get("tajuk_Fail");
			      String status = (String)data.get("id_Status");
			      String lokasi = (String)data.get("id_Lokasi");
			      String faharasat = (String)data.get("kabinet");
			      String tarikhDaftar = (String)data.get("tarikhDaftar");
			      String idMasuk = (String)data.get("id_Masuk");
			      int flag_jenis_Fail = (Integer)data.get("flag_jenis_Fail");
			      int viewFail = (Integer)data.get("view_Fail");
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();
			      
			      r.add("id_Fail", idFail);
			      r.add("no_Fail", noFail);
			      r.add("no_Fail_root", noFailRoot);
			      r.add("no_Turutan", noTurutan);
			      r.add("NO_TURUTAN_JLD", noJilid);
			      r.add("NO_TURUTAN_SUBJAKET", noSubjaket);	
			      r.add("id_Negeri", negeri);
			      r.add("id_Daerah", daerah);
			      r.add("id_Seksyen", seksyen);
			      r.add("id_Urusan", urusan);
			      r.add("id_Suburusan", suburusan);
			      r.add("flag_jenis_Fail", flag_jenis_Fail);
			      r.add("flag_Fail", flag_jenis_Fail);
			      r.add("FLAG_VIEW_FILE", viewFail);
			      r.add("id_Tarafkeselamatan", taraf);
			      r.add("tajuk_Fail", tajukFail);
			      r.add("id_Status", status);
			      r.add("id_lokasifail", lokasi);
			      r.add("faharasat", faharasat);
			  	  r.add("tarikh_daftar_fail", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
			      r.add("tarikh_Masuk",r.unquote("sysdate")); 
			      r.add("id_Masuk",idMasuk);
			      sql = r.getSQLInsert("tblpfdfail");
			      
			      
			      stmt.executeUpdate(sql);
			      
			      return "" +idFail;
			      
			    } finally {
			      if (db != null) db.close();
			    }
		}

		public static String addFailBaruNegeri(Hashtable data) throws Exception {
			 Db db = new Db();
			    String sql = "";
			    try
			    {
			    	  long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			    	  String seksyen = (String)data.get("id_Seksyen");
			    	  String negeri = (String)data.get("id_Negeri");
			    	  String daerah = (String)data.get("id_Daerah");
			    	  String urusan = (String)data.get("id_Urusan");
			    	  String suburusan = (String)data.get("id_Suburusan");
			    	  String subsuburusan = (String)data.get("id_SubSubUrusan");
			    	  String idAktiviti = (String)data.get("id_Aktiviti");
			    	  String taraf = (String)data.get("id_Tarafkeselamatan");
				      String tajukFail = (String)data.get("tajuk_Fail");
				      String status = (String)data.get("id_Status");
				      String lokasi = (String)data.get("id_Lokasi");
				      String faharasat = (String)data.get("kabinet");
				      String tarikhDaftar = (String)data.get("tarikhDaftar");
				      String idMasuk = (String)data.get("id_Masuk");
				      int flag_jenis_Fail = (Integer)data.get("flag_jenis_Fail");
				      int viewFail = (Integer)data.get("view_Fail");
				    
	
				    			      
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
				    //  String nofail =  generateNoFailNegeri(negeri,daerah,urusan,suburusan,subsuburusan,idAktiviti,taraf, );
				     // String selectNoTurutan = selectMaxNoTurutan(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
				      String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
				      int noTurutan = 1;
				      
				      if("".equalsIgnoreCase(check)){
				    	 noTurutan = 1;
						
				      }
				      else{
						 noTurutan = Integer.parseInt(check)+1;
				      }
				      
//				      String nofail = generateNoFailNegeri(seksyen,negeri,daerah,urusan,suburusan,subsuburusan,taraf,idAktiviti,noTurutan);

				      r.add("id_Fail", idFail);
				      r.add("id_Seksyen", seksyen);
				      r.add("id_Negeri", negeri);
				      r.add("id_Daerah", daerah);
				      r.add("id_Urusan", urusan);
				      r.add("id_Suburusan", suburusan);
				      r.add("id_SubSubUrusan", subsuburusan);
				      r.add("id_Aktiviti", idAktiviti);
				      r.add("id_Tarafkeselamatan", taraf);
				      r.add("tajuk_Fail", tajukFail);
				      r.add("id_Status", status);
				      r.add("flag_jenis_Fail", flag_jenis_Fail);
				      r.add("flag_Fail", flag_jenis_Fail);
				      r.add("FLAG_VIEW_FILE", viewFail);
				      r.add("id_Lokasifail", lokasi);
				      r.add("faharasat", faharasat);
				  	  r.add("tarikh_daftar_fail", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
//				  	  r.add("no_Fail",nofail);
//				  	  r.add("no_Fail_root",nofail);
//				  	  r.add("no_turutan",noTurutan );
				      r.add("id_Masuk",idMasuk);
				      r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
				      sql = r.getSQLInsert("tblpfdfail");
				      
				      stmt.executeUpdate(sql);
				      
				      return "" +idFail;
				      
				    } finally {
				      if (db != null) db.close();
				    }
		}

		public static String addFailBaruSeksyen(Hashtable data) throws Exception {
			 Db db = new Db();
			    String sql = "";
			    try
			    {
			    	  long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			    	  String negeri = (String)data.get("id_Negeri");
			    	  String seksyen = (String)data.get("id_Seksyen");
			    	  String urusan = (String)data.get("id_Urusan");
			    	  String suburusan = (String)data.get("id_Suburusan");
			    	  String subsuburusan = (String)data.get("id_SubSubUrusan");
			    	  String idAktiviti = (String)data.get("id_Aktiviti");
			    	  String taraf = (String)data.get("id_Tarafkeselamatan");
				      String tajukFail = (String)data.get("tajuk_Fail");
				      String status = (String)data.get("id_Status");
				      String lokasi = (String)data.get("id_Lokasi");
				      String kabinet = (String)data.get("kabinet");
				      String tarikhDaftar = (String)data.get("tarikhDaftar");
				      String idMasuk = (String)data.get("id_Masuk");
				      int flag_jenis_Fail = (Integer)data.get("flag_jenis_Fail");
				      int viewFail = (Integer)data.get("view_Fail");
				    
				      
				    			      
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();
//				      String nofail = "";
//				      String selectNoTurutan = "";
				      //generateNoFailBaruSeksyen(ID_SEKSYEN,ID_NEGERI,ID_URUSAN,ID_SUBURUSAN,ID_SUBSUBURUSAN,ID_TARAFFAIL,ID_AKTIVITI);
				      String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
				      int noTurutan = 0;
				      
				      if("".equalsIgnoreCase(check)){
				    	 noTurutan = 0;
						
				      }
				      else{
						 noTurutan = Integer.parseInt(check)+1;
				      }
						
//				      String nofail = generateNoFailBaruSeksyen(seksyen,negeri,urusan,suburusan,subsuburusan,taraf,idAktiviti,noTurutan);
//				      Hashtable nofail = generateNoFailBaruSeksyen(seksyen,negeri,urusan,suburusan,subsuburusan,taraf,idAktiviti,noTurutan);
//				      String nofail = generateNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
//					  String selectNoTurutan = selectMaxNoTurutan(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
//				      String no_fail = (String) nofail.get("noFail");
//				      String no_fail_arkib = (String) nofail.get("noFailArkib");

				      r.add("id_Fail", idFail);
				      r.add("id_Negeri", negeri);
				      r.add("id_Seksyen", seksyen);
				      r.add("id_Urusan", urusan);
				      r.add("id_Suburusan", suburusan);
				      r.add("id_SubSubUrusan", subsuburusan);
				      r.add("id_Aktiviti", idAktiviti);
				      r.add("id_Tarafkeselamatan", taraf);
				      r.add("tajuk_Fail", tajukFail);
				      r.add("id_Status", status);
				      r.add("flag_jenis_Fail", flag_jenis_Fail);
				      r.add("flag_Fail", flag_jenis_Fail);
				      r.add("FLAG_VIEW_FILE", viewFail);
				      r.add("id_Lokasifail", lokasi);
				      r.add("faharasat", kabinet);
				  	  r.add("tarikh_daftar_fail", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
//				  	  r.add("no_Fail",nofail);
//				  	  r.add("no_Fail_root",nofail);
////				      r.add("no_Fail",nofail.get("noFail"));
//				      r.add("no_Fail_Arkib",nofail.get("noFailArkib"));
//				      r.add("no_Fail_root",nofail.get("noFail"));
				      r.add("no_turutan",noTurutan );
				      r.add("id_Masuk",idMasuk);
				      r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
				      sql = r.getSQLInsert("tblpfdfail");
				      
				      stmt.executeUpdate(sql);
				      
				      return "" +idFail;
				      
				    } finally {
				      if (db != null) db.close();
				    }
		}
		
		public static String addFailArkib(Hashtable data) throws Exception {
			 Db db = new Db();
			    String sql = "";
			    try
			    {
			    	  long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
			    	  long idFailMapping = DB.getNextID("TBLPFDFAILMAPPING_SEQ");
			    	  String no_fail = (String)data.get("no_fail");
			    	  String id_FailLama = (String)data.get("id_FailLama");
			    	  String negeri = (String)data.get("id_Negeri");
			    	  String seksyen = (String)data.get("id_Seksyen");
//			    	  String id_pejabatjkptg = (String)data.get("id_pejabatjkptg");
			    	  String urusan = (String)data.get("id_Urusan");
			    	  String suburusan = (String)data.get("id_Suburusan");
			    	  String subsuburusan = (String)data.get("id_SubSubUrusan");
			    	  String subsubsuburusan = (String)data.get("id_SubSubSubUrusan");
			    	  String idAktiviti = (String)data.get("id_Aktiviti");
			    	  String taraf = (String)data.get("id_Tarafkeselamatan");
				      String tajukFail = (String)data.get("tajuk_Fail");
				      String status = (String)data.get("id_Status");
				      String lokasi = (String)data.get("id_Lokasi");
				      String kabinet = (String)data.get("kabinet");
				      String tarikhDaftar = (String)data.get("tarikhDaftar");
				      String idMasuk = (String)data.get("id_Masuk");
				      int flag_jenis_Fail = (Integer)data.get("flag_jenis_Fail");
				      int viewFail = (Integer)data.get("view_Fail");
				    
				      String check = checkNoFailArkib(negeri,seksyen,urusan,suburusan,subsuburusan,subsubsuburusan,idAktiviti,taraf);
				      int noTurutan = 0;
				      
				      if("".equalsIgnoreCase(check)){
				    	 noTurutan = 0;
						
				      }
				      else{
						 noTurutan = Integer.parseInt(check)+1;
				      }
				      
				      String nofailBertaraf = "";
				      
				      if("16".equalsIgnoreCase(negeri)){
				    	  nofailBertaraf = FrmPendaftaranFailData.generateNoFailBaruSeksyen(seksyen,negeri,urusan,suburusan,subsuburusan,subsubsuburusan,taraf,idAktiviti,noTurutan);
				      }
				      else{
				    	  nofailBertaraf = FrmPendaftaranFailData.generateNoFailNegeri(seksyen,negeri,urusan,suburusan,subsuburusan,subsubsuburusan,taraf,idAktiviti,noTurutan);
				      }
				      
				      
				    
				      
				      Statement stmt = db.getStatement();
				      SQLRenderer r = new SQLRenderer();

				      r.add("id_Fail", idFail);
				      r.add("id_Negeri", negeri);
				      r.add("id_Seksyen", seksyen);
//				      r.add("id_Pejabatjkptg", id_pejabatjkptg);
				      r.add("id_Urusan", urusan);
				      r.add("id_Suburusan", suburusan);
				      r.add("id_SubSubUrusan", subsuburusan);
				      r.add("id_SubSubSubUrusan", subsubsuburusan);
				      r.add("id_Aktiviti", idAktiviti);
				      r.add("id_Tarafkeselamatan", taraf);
				      r.add("tajuk_Fail", tajukFail);
				      r.add("id_Status", status);
				      r.add("flag_jenis_Fail", flag_jenis_Fail);
				      r.add("flag_Fail", flag_jenis_Fail);
				      r.add("FLAG_VIEW_FILE", viewFail);
				      r.add("id_Lokasifail", lokasi);
				      r.add("faharasat", kabinet);
				  	  r.add("tarikh_daftar_fail", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
				  	  r.add("no_Fail",nofailBertaraf);
				  	  r.add("no_Fail_root",nofailBertaraf);
				  	  r.add("no_turutan",noTurutan );
				      r.add("id_Masuk",idMasuk);
				      r.add("tarikh_Masuk",r.unquote("sysdate")); 
				  
				      sql = r.getSQLInsert("tblpfdfail");
				      
				      System.out.println("negeri--"+negeri);
				      System.out.println("nofailBertaraf--"+nofailBertaraf);
				      System.out.println("SQL SIMPAN FAIL ARKIB---"+sql);
				      stmt.executeUpdate(sql);
				      
				      System.out.println("id_FailLama---"+id_FailLama);
				      
				      if(id_FailLama != ""){
				      
				      SQLRenderer rF = new SQLRenderer();

				      rF.add("id_Failmapping",idFailMapping);
				      rF.add("id_FailArkib",idFail);
				      rF.add("id_Failasal",id_FailLama);
				      rF.add("id_Masuk",idMasuk);
				      rF.add("tarikh_Masuk",rF.unquote("sysdate")); 
				  
				      sql = rF.getSQLInsert("tblpfdfailmapping");		      
				      stmt.executeUpdate(sql);
				      
				      }
				      
				      return "" +idFail;
				      
				    } finally {
				      if (db != null) db.close();
				    }
		}
		//--- baru
//		public static String addFailBaruSeksyen(Hashtable data) throws Exception {
//			 Db db = new Db();
//			    String sql = "";
//			    try
//			    {
//			    	 long idFail = DB.getNextID("TBLPFDFAIL_SEQ");
//			    	  Integer negeri = (Integer)data.get("id_Negeri");
//			    	  Integer seksyen = (Integer)data.get("id_Seksyen");
//			    	  Integer urusan = (Integer)data.get("id_Urusan");
//			    	  String suburusan = (String)data.get("id_Suburusan");
//			    	  String subsuburusan = (String)data.get("id_SubSubUrusan");
//			    	  String idAktiviti = (String)data.get("id_Aktiviti");
//			    	  String taraf = (String)data.get("id_Tarafkeselamatan");
//				      String tajukFail = (String)data.get("tajuk_Fail");
//				      String status = (String)data.get("id_Status");
//				      String lokasi = (String)data.get("id_Lokasi");
//				      String kabinet = (String)data.get("kabinet");
//				      String tarikhDaftar = (String)data.get("tarikhDaftar");
//				      String idMasuk = (String)data.get("id_Masuk");
//				      int flag_jenis_Fail = (Integer)data.get("flag_jenis_Fail");
//				      int viewFail = (Integer)data.get("view_Fail");
//				    
//				      
//				    			      
//				      Statement stmt = db.getStatement();
//				      SQLRenderer r = new SQLRenderer();
////				      String nofail = "";
////				      String selectNoTurutan = "";
//				      String nofail = generateNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
//					  String selectNoTurutan = selectMaxNoTurutan(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
//				      
//				      r.add("id_Fail", idFail);
//				      r.add("id_Negeri", negeri);
//				      r.add("id_Seksyen", seksyen);
//				      r.add("id_Urusan", urusan);
//				      r.add("id_Suburusan", suburusan);
//				      r.add("id_SubSubUrusan", subsuburusan);
//				      r.add("id_Aktiviti", idAktiviti);
//				      r.add("id_Tarafkeselamatan", taraf);
//				      r.add("tajuk_Fail", tajukFail);
//				      r.add("id_Status", status);
//				      r.add("flag_jenis_Fail", flag_jenis_Fail);
//				      r.add("flag_Fail", flag_jenis_Fail);
//				      r.add("FLAG_VIEW_FILE", viewFail);
//				      r.add("id_Lokasifail", lokasi);
//				      r.add("faharasat", kabinet);
//				  	  r.add("tarikh_daftar_fail", r.unquote("to_date('"+ data.get("tarikhDaftar")+ "','dd/MM/yyyy')"));
//				      r.add("no_Fail",nofail );
//				      r.add("no_Fail_root",nofail );
//				      r.add("no_turutan",selectNoTurutan );
//				      r.add("id_Masuk",idMasuk);
//				      r.add("tarikh_Masuk",r.unquote("sysdate")); 
//				  
//				      sql = r.getSQLInsert("tblpfdfail");
//				      
//				      stmt.executeUpdate(sql);
//				      
//				      return "" +idFail;
//				      
//				    } finally {
//				      if (db != null) db.close();
//				    }
//		}

		private static String selectMaxNoTurutan(Integer negeri,Integer seksyen, Integer urusan, String suburusan, String subsuburusan, String idAktiviti, String taraf) throws Exception {
			// TODO Auto-generated method stub
			Db db = null;
			 String sql = "";
			 String returnValue = "";
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      sql = "select MAX(no_turutan) as no_turutan from TBLRUJSEQFAILEXTENDED where"+
					" id_negeri = "+ negeri + 
					" and id_seksyen = "+ seksyen + 
					" and id_urusan = "+ urusan;
					if(!"".equalsIgnoreCase(suburusan)){
						sql = sql +  " and id_suburusan = "+ suburusan;
					}
			      	if(!"".equalsIgnoreCase(subsuburusan)){
			      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
			      	}
			      
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h = new Hashtable();
			      if (rs.next()) {
			    	  returnValue = rs.getString("no_turutan")==null?"":rs.getString("no_turutan");
			      }
			      
			      return returnValue;  
			      
			 }
			 finally {
			      if (db != null) db.close();
			    }
		}

		public static Vector getPaparSemuaFail(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparSemuaFail = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT A.ID_FAIL, A.ID_TARAFKESELAMATAN, A.ID_SEKSYEN, A.ID_URUSAN, A.ID_SUBURUSAN, A.TARIKH_DAFTAR_FAIL, A.TAJUK_FAIL, A.NO_FAIL, A.ID_NEGERI, A.ID_LOKASIFAIL, A.ID_FAHARASAT, A.ID_STATUS, A.FLAG_VIEW_FILE, " +
					 "B.KOD_URUSAN, B.NAMA_URUSAN, C.KOD_SUBURUSAN, C.NAMA_SUBURUSAN, D.KOD_SUBSUBURUSAN, D.NAMA_SUBSUBURUSAN, E.KOD_SUBSUBSUBURUSAN, E.NAMA_SUBSUBSUBURUSAN "+
					  "FROM TBLPFDFAIL A, TBLRUJURUSAN B, TBLRUJSUBURUSAN C, TBLRUJSUBSUBURUSAN D, TBLRUJSUBSUBSUBURUSAN E " +
					  "WHERE " +
					  "A.ID_URUSAN = B.ID_URUSAN(+) " +
					  "AND A.ID_SUBURUSAN = C.ID_SUBURUSAN(+) " +
					  "AND A.ID_SUBSUBURUSAN = D.ID_SUBSUBURUSAN(+) " +
					  "AND A.ID_SUBSUBSUBURUSAN = E.ID_SUBSUBSUBURUSAN(+) " +
					  "AND A.ID_FAIL='"+idFail+"'";
				
				
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idStatus",rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS"));
					h.put("idTaraf",rs.getString("ID_TARAFKESELAMATAN")==null?"":rs.getString("ID_TARAFKESELAMATAN"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("KOD_URUSAN",rs.getString("KOD_URUSAN")==null?"":rs.getString("KOD_URUSAN"));
					h.put("NAMA_URUSAN",rs.getString("NAMA_URUSAN")==null?"":rs.getString("NAMA_URUSAN"));
					h.put("KOD_SUBURUSAN",rs.getString("KOD_SUBURUSAN")==null?"":rs.getString("KOD_SUBURUSAN"));
					h.put("NAMA_SUBURUSAN",rs.getString("NAMA_SUBURUSAN")==null?"":rs.getString("NAMA_SUBURUSAN"));
					h.put("KOD_SUBSUBURUSAN",rs.getString("KOD_SUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBURUSAN"));
					h.put("NAMA_SUBSUBURUSAN",rs.getString("NAMA_SUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBURUSAN"));
					h.put("KOD_SUBSUBSUBURUSAN",rs.getString("KOD_SUBSUBSUBURUSAN")==null?"":rs.getString("KOD_SUBSUBSUBURUSAN"));
					h.put("NAMA_SUBSUBSUBURUSAN",rs.getString("NAMA_SUBSUBSUBURUSAN")==null?"":rs.getString("NAMA_SUBSUBSUBURUSAN"));
					h.put("tajukFail",rs.getString("TAJUK_FAIL")==null?"":rs.getString("TAJUK_FAIL"));
					h.put("noFail",rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idLokasi",rs.getString("ID_LOKASIFAIL")==null?"":rs.getString("ID_LOKASIFAIL"));
					h.put("idFaharasat",rs.getString("ID_FAHARASAT")==null?"":rs.getString("ID_FAHARASAT"));
					h.put("flag_view_file",rs.getString("FLAG_VIEW_FILE")==null?"":rs.getString("FLAG_VIEW_FILE"));
					h.put("tarikhDaftar",rs.getString("TARIKH_DAFTAR_FAIL")==null?"":sdf.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
					
					
					paparSemuaFail.addElement(h);
					
				}
				
				
				return paparSemuaFail;
			}
			finally {
				if (db != null)
					db.close();
			}
		}

		public static void getPaparFailLamaAll(String idFail) {
		
		}
		
		
		 public static Vector getSubSubUrusanByIdSubUrusan(String idSubUrusan) throws Exception {
				
				Db db = null;
				Vector v = null;
				String sql = "";
				try {

					db = new Db();
					Statement stmt = db.getStatement();
					sql = 	"select C.ID_SUBSUBURUSAN, C.KOD_SUBSUBURUSAN, C.NAMA_SUBSUBURUSAN from tblrujurusan a, tblrujsuburusan b, tblrujsubsuburusan c "+ 
					  		"where A.ID_URUSAN = B.ID_URUSAN "+
					  		"and B.ID_SUBURUSAN = C.ID_SUBURUSAN "+
					  		"and B.ID_SUBURUSAN = '"+ idSubUrusan+"'"+
					  		" ORDER BY nama_urusan asc";
					ResultSet rs = stmt.executeQuery(sql);
					v = new Vector();

					Hashtable h;
					while (rs.next()) {
						h = new Hashtable();
						h.put("ID_SUBSUBURUSAN", rs.getString("ID_SUBSUBURUSAN") == null ? "" : rs.getString("ID_SUBSUBURUSAN"));
						h.put("KOD_SUBSUBURUSAN", rs.getString("KOD_SUBSUBURUSAN") == null ? "" : rs.getString("KOD_SUBSUBURUSAN").toUpperCase());
						h.put("NAMA_SUBSUBURUSAN", rs.getString("NAMA_SUBSUBURUSAN") == null ? "" : rs.getString("NAMA_SUBSUBURUSAN").toUpperCase());
						v.addElement(h);
					}
					
				} catch (Exception e) {e.printStackTrace();} 
				finally {
					if (db != null)
						db.close();
				}
				return v;
			
			}
		public Boolean checkfailwujud(String noFail) throws Exception {
			// TODO Auto-generated method stub
			Boolean  returnValue =false;
			String no_fail = "";
			Db db = new Db();
			String sql = "";
			
			try {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String SQL_SEARCH = "";
			
				sql = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE NO_FAIL = '" + noFail + "'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
						no_fail =  rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
				}
				
				if ("".equalsIgnoreCase(no_fail)){
					returnValue = false;
				}else{				
					returnValue = true;
				}
			} finally {
				if (db != null)
					db.close();
			}
			return returnValue;
		}

		public static void updateNoTurutan(String idFail) {
			// TODO Auto-generated method stub
			
		}

		public static Vector getPaparNoTurutan(String idFail) throws Exception {
			String sql = "";
			Db db = null;
			
			try{
				paparNoTurutan = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_FAIL, ID_SEKSYEN, ID_NEGERI, ID_URUSAN, ID_SUBURUSAN,ID_SUBSUBURUSAN,NO_TURUTAN " +
					  "FROM TBLPFDFAIL " +
					  "WHERE ID_FAIL='"+idFail+"'";
				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				while (rs.next()) {
					h = new Hashtable();
					h.put("idFail",rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
					h.put("idSeksyen",rs.getString("ID_SEKSYEN")==null?"":rs.getString("ID_SEKSYEN"));
					h.put("idNegeri",rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
					h.put("idUrusan",rs.getString("ID_URUSAN")==null?"":rs.getString("ID_URUSAN"));
					h.put("idSuburusan",rs.getString("ID_SUBURUSAN")==null?"":rs.getString("ID_SUBURUSAN"));
					h.put("idSubSubUrusan",rs.getString("ID_SUBSUBURUSAN")==null?"":rs.getString("ID_SUBSUBURUSAN"));
					h.put("noTurutan",rs.getString("NO_TURUTAN")==null?"":rs.getString("NO_TURUTAN"));

					paparNoTurutan.addElement(h);
					
				}
				
				
				return paparNoTurutan;
			}
			finally {
				if (db != null)
					db.close();
			}
		}

		public String cekNoTurutan(String id_seksyen, String id_negeri,
				String id_urusan, String id_suburusan, String id_subsuburusan) throws Exception {
			// TODO Auto-generated method stub
			Db db = null;
			 String sql = "";
			 String returnValue = "";
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
					" id_negeri = "+ id_negeri + 
					" and id_seksyen = "+ id_seksyen + 
					" and id_urusan = "+ id_urusan;
					if(!"".equalsIgnoreCase(id_suburusan)){
						sql = sql +  " and id_suburusan = "+ id_suburusan;
					}
			      	if(!"".equalsIgnoreCase(id_subsuburusan)){
			      	sql = sql +  " and id_subsuburusan = "+ id_subsuburusan ;
			      	}
			      
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h = new Hashtable();
			      if (rs.next()) {
			    	  returnValue = rs.getString("no_turutan")==null?"":rs.getString("no_turutan");
			      }
			      
			      return returnValue;  
			      
			 }
			 finally {
			      if (db != null) db.close();
			    }
		}

		public static String generateNoFailBaruSeksyen(String seksyen,String negeri, String urusan, String suburusan,String subsuburusan, String subsubsuburusan, String taraf, String idAktiviti, Integer noTurutan) throws Exception {
			String sql = "";
			String noFail = "";
			String noFailArkib = "";
			Integer intsuburusan= 0;
			Integer intsubsuburusan= 0;
			String noFailStandard = "JKPTG-";
			
			Hashtable h = new Hashtable();
			Db db = null;
			db = new Db();
			if (suburusan == ""){
				intsuburusan = 0;
			}else{
			 intsuburusan = Integer.parseInt(suburusan);
			}
			if (subsuburusan == ""){
				intsubsuburusan = 0;
			}else{
				intsubsuburusan = Integer.parseInt(subsuburusan);
			}
			 Statement stmt = db.getStatement();
		  
//		    if (subsuburusan != ""){
		    	
		    	sql = "select a.kod_subSuburusan, b.kod_subUrusan, c.kod_urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_baru_Seksyen, g.kod_subsubsuburusan"+
				" from  tblrujsubsuburusan a,tblrujsuburusan b,tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujseksyen f, tblrujsubsubsuburusan g"+
				" where a.id_Subsuburusan = " + subsuburusan + 
				" and b.id_Suburusan = " + suburusan +
				" and c.id_Urusan = " + urusan + 
				" and d.id_Tarafkeselamatan = " + taraf +
				" and e.id_Negeri = " + negeri +
				" and f.id_Seksyen = " + seksyen+
				" and g.id_Subsubsuburusan = "+subsubsuburusan;
				
		    	
		    	
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				
				
				
				if(taraf.equals("1")){
				
				
				noFail =  noFailStandard + rs.getString(6) + "/" + rs.getString(2) + "/" + rs.getString(1) + "/" + rs.getString(7);
				
				}
				else{
					noFail =  noFailStandard + rs.getString(6) + "(" + rs.getString(4) + ")/" + rs.getString(2) + "/" + rs.getString(1) + "/" + rs.getString(7);
				}
				/*
				// seksyen e-tanah
				if("161023".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					
					if (!"".equalsIgnoreCase(check)){
						// taraf keselamatan selain terbuka
						if (rs.getString(4) != null){
		
							noFail =  "NRE/"+ rs.getString(5) + "("+ rs.getString(4) + ")/72/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						// terbuka
						else{
							noFail =  "NRE/"+ rs.getString(5) + "/72/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-"+ noTurutan;

						}
					}
					//klu blm ade fail
					else{
						// taraf keselamatan selain terbuka
						if (rs.getString(4) != null){
							noFail =  "NRE/"+ rs.getString(5) + "("+ rs.getString(4) + ")/72/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						// terbuka
						else{
							noFail =  "NRE/"+ rs.getString(5) + "/72/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
					}


				}
				
				//seksyen pusaka
				else if("2".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/PES/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"PES/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/PES/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "PES/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}			
				}
				//seksyen petugas khas
				else if("161021".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/SPPK/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-"+ noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"SPPK/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/SPPK/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "SPPK/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen SPPT
				else if("14".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/KPU/SPPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SPPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/KPU/SPPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SPPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen SPTB/SKPT
				else if("16".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
							noFail =  "NRE("+ rs.getString(4) + ")/101/SKPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  "NRE/101/SKPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(4) != null){
							noFail =  "NRE("+ rs.getString(4) + ")/101/SKPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  "NRE/101/SKPT/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen skpp
				else if("18".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/KPU/SKPP/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SKPP/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(4) != null){
								noFail =  "JKPTG("+ rs.getString(4) + ")/101/KPU/SKPP/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SKPP/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen shs
				else if("12".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/KPU/SHMS" +"/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SHMS/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/KPU/SHMS/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SHMS/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				
				// seksyen selain e-tanah
				else
				{
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);

					if (!"".equalsIgnoreCase(check)){
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;
							//noFailArkib =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(6);
						}
						// selain terbuka
						else{
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;
						}
					}
					
					else{
						if (rs.getString(4) != null){
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);

						}
						// selain terbuka
						else{
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(3) + "/" + rs.getString(2) + "/" + rs.getString(1);
							
						}
					}
	
				}*/

				return noFail;
//				h.put("noFail", noFail);
//				h.put("noFailArkib", noFailArkib);
//				return h;
			/*}
		    
		    //ada suburusan
		    else if (suburusan != ""){
		    	sql = "select b.kod_Suburusan, c.kod_Urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_Seksyen, b.kod_Suburusan_Arkib"+
				" from  tblrujsuburusan b,tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujseksyen f"+
				" where b.id_Suburusan = " + suburusan + 
				" and c.id_Urusan = " + urusan + 
				" and d.id_Tarafkeselamatan = " + taraf +
				" and e.id_Negeri = " + negeri +
				" and f.id_Seksyen = " + seksyen;
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				
				if("161023".equalsIgnoreCase(seksyen))
				{
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						// taraf keselamatan selain terbuka
						if (rs.getString(3) != null){
							noFail =  "NRE/"+ rs.getString(5) + "("+ rs.getString(3) + ")/72/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						// terbuka
						else{
							noFail =  "NRE/"+ rs.getString(5) + "/72/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}
					}
					//klu blm ade fail
					else{
						// taraf keselamatan selain terbuka
						if (rs.getString(3) != null){
							noFail =  "NRE/"+ rs.getString(5) + "("+ rs.getString(3) + ")/72/" + rs.getString(2) + "/" + rs.getString(1);

						}
						// terbuka
						else{
							noFail =  "NRE/"+ rs.getString(5) + "/72/" + rs.getString(2)+ "/" + rs.getString(1);

						}
					}
					
				}
				
				//seksyen pusaka
				else if("2".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/PES/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"PES/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/PES/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "PES/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}			
				}
				//seksyen petugas khas
				else if("161021".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/SPPK/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"SPPK/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/SPPK/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "SPPK/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen SPPT
				else if("14".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/KPU/SPPT/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SPPT/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/KPU/SPPT/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SPPT/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen SPTB/SKPT
				else if("16".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							noFail =  "NRE("+ rs.getString(3) + ")/101/SKPT/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  "NRE/101/SKPT/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(3) != null){
							noFail =  "NRE("+ rs.getString(3) + ")/101/SKPT/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  "NRE/101/SKPT/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen skpp
				else if("18".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/KPU/SKPP/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SKPP/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/KPU/SKPP/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SKPP/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//seksyen shs
				else if("12".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/KPU/SHMS" +"/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SHMS/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/KPU/SHMS/" + rs.getString(2) + "/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SHMS/" + rs.getString(2) + "/" + rs.getString(1);

						}				
					}
				}
				//selain etanah
				else
				{
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						// taraf keselamatan selain terbuka
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(5) + "/" + rs.getString(2) + "/" + rs.getString(1)+ "-" + noTurutan;
							noFailArkib =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6) + "-" + noTurutan;
						}
						// terbuka
						else{
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(2)+ "/" + rs.getString(1) + "-" + noTurutan;
							noFailArkib =  noFailStandard + rs.getString(5) + "/" + rs.getString(6) + "-" + noTurutan;
						}
					}
					//klu blm ade
					else{
						// taraf keselamatan selain terbuka
						if (rs.getString(3) != null){
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(5) + "/" + rs.getString(2) + "/" + rs.getString(1);
							noFailArkib =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6);
						}
						// terbuka
						else{
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(2)+ "/" + rs.getString(1);
							noFailArkib =  noFailStandard + rs.getString(5) + "/" + rs.getString(6);
						}
					}
					
				}

				return noFail;
//				h.put("noFail", noFail);
//				h.put("noFailArkib", noFailArkib);
//				return h;
			}
		    
		    //ada urusan sahaja
		    else{
		    	sql = "select c.kod_Urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_Seksyen"+
				" from tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujseksyen f"+
				" where c.id_Urusan = " + urusan + 
				" and d.id_Tarafkeselamatan = " + taraf +
				" and e.id_Negeri = " + negeri +
				" and f.id_Seksyen = " + seksyen;
		    	
		    	ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				// etanah
				if("161023".equalsIgnoreCase(seksyen))
				{
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							noFail =  "NRE/"+ rs.getString(4) + "("+ rs.getString(2) + ")/72/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							noFail =  "NRE/"+ rs.getString(4) + "/72/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(2) != null){
							noFail =  "NRE/"+ rs.getString(4) + "("+ rs.getString(2) + ")/72/" + rs.getString(1);
	
						}
						//terbuka
						else{
								noFail =  "NRE/"+ rs.getString(4) + "/72/" + rs.getString(1);
	
						}
					}
				}
				
				//seksyen pusaka
				else if("2".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/PES/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"PES/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/PES/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "PES/" + rs.getString(1);

						}				
					}			
				}
				//seksyen petugas khas
				else if("161021".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/SPPK/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"SPPK/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
								noFail =  "JKPTG("+ rs.getString(2) + ")/101/SPPK/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "SPPK/" + rs.getString(1);

						}				
					}
				}
				//seksyen SPPT
				else if("14".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
								noFail =  "JKPTG("+ rs.getString(2) + ")/101/KPU/SPPT/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
								noFail =  noFailStandard +"KPU/SPPT/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/KPU/SPPT/" + rs.getString(1);

						}
						//terbuka
						else{
								noFail =  noFailStandard + "KPU/SPPT/" + rs.getString(1);

						}				
					}
				}
				//seksyen SPTB/SKPT
				else if("16".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
								noFail =  "NRE("+ rs.getString(2) + ")/101/SKPT/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
									noFail =  "NRE/101/SKPT/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
									noFail =  "NRE("+ rs.getString(2) + ")/101/SKPT/" + rs.getString(1);

						}
						//terbuka
						else{
								noFail =  "NRE/101/SKPT/" + rs.getString(1);

						}				
					}
				}
				//seksyen skpp
				else if("18".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/KPU/SKPP/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
							noFail =  noFailStandard +"KPU/SKPP/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/KPU/SKPP/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SKPP/" + rs.getString(1);

						}				
					}
				}
				//seksyen shs
				else if("12".equalsIgnoreCase(seksyen)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
	
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/KPU/SHMS" +
									"/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{

							noFail =  noFailStandard +"KPU/SHMS/" + rs.getString(1)+ "-" + noTurutan;

						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/KPU/SHMS/" + rs.getString(1);

						}
						//terbuka
						else{
							noFail =  noFailStandard + "KPU/SHMS/" + rs.getString(1);

						}				
					}
				}
				
				
				//selain
				else
				{
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(4) + "/" + rs.getString(1)+ "-" + noTurutan ;
//							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(6);
						}
						//terbuka
						else{
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(1)+ "-" + noTurutan;
//							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(6)+ "-" + noTurutan;
						}				
					}
					//klu blm ade no fail
					else{
						//sulit
						if (rs.getString(2) != null){
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(4) + "/" + rs.getString(1);

						}
						//terbuka
						else{
	
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(1);

						}				
					}			

				}
				
				return noFail;
//				h.put("noFail", noFail);
//				h.put("noFailArkib", noFailArkib);
//				return h;
		    }	*/
	
	}
//-------------------------------------------------------------------------------------		
		public static String generateNoFailNegeri(String seksyen, String negeri, String urusan, String suburusan, String subsuburusan, String subsubsuburusan, String taraf, String idAktiviti, Integer noTurutan) throws Exception{
 		String sql = "";
		String noFail = "";
		String noFailStandard = "JKPTGN-";
		Db db = null;
		db = new Db();
	    Statement stmt = db.getStatement();
	    
		Integer intsuburusan= 0;
		Integer intsubsuburusan= 0;
	    
	    if (suburusan == ""){
			intsuburusan = 0;
		}else{
		 intsuburusan = Integer.parseInt(suburusan);
		}
		if (subsuburusan == ""){
			intsubsuburusan = 0;
		}else{
			intsubsuburusan = Integer.parseInt(subsuburusan);
		}
	 


	   // if (subsuburusan != ""){
	    	sql = "select a.kod_subsuburusan, b.kod_Suburusan, c.kod_Urusan, d.kod_Taraf_Keselamatan, e.kod_negeri, f.kod_daerah, g.kod_subsubsuburusan"+
			" from  tblrujsubsuburusan a,tblrujsuburusan b,tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujdaerah f, tblrujsubsubsuburusan g"+
			" where a.id_subsuburusan =" + subsuburusan +
			" and b.id_Suburusan = " + suburusan + 
			" and c.id_Urusan = " + urusan + 
			" and d.id_Tarafkeselamatan = " + taraf +
			" and g.id_Subsubsuburusan = "+subsubsuburusan+
			" and e.id_negeri = "+negeri;
	    	
	    	
	    	
	    	ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			if(taraf.equals("1")){
				noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(2) + "/" + rs.getString(1) + "/" + rs.getString(7);
			}
			else{
				
				
				noFail =  noFailStandard + rs.getString(5) + "(" + rs.getString(4) + ")/" + rs.getString(2) + "/" + rs.getString(1) + "/" + rs.getString(7);
			}
	    	
			
		/*	if(negeri.equals("1") || negeri.equals("3") || negeri.equals("4") || negeri.equals("5") || negeri.equals("6") || negeri.equals("8") || negeri.equals("10") || negeri.equals("14") ){
				sql = sql + " and e.id_Negeri = " + negeri+
						" and f.id_daerah = " + daerah;
			}
			else
			{
				sql = sql + " and e.id_Negeri = " + negeri;
			}
	    	
	    	ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			if (negeri != null){
				// johor
				if  ("1".equalsIgnoreCase(negeri)){
					
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
					//sulit
						if (rs.getString(4) != null){
			
							noFail =  "JKPTG(J)/("+ rs.getString(4) + ")/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  "JKPTG(J)/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG(J)/("+ rs.getString(4) + ")/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/"+ noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  "JKPTG(J)/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+"/"+ noTurutan;
	
						}
					}
				}
				
				// kedah
				else if  ("2".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
				
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
					else
					{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
				}
				
				//kelantan
				else if  ("3".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
	
							noFail =  "JKPTG.KN/("+ rs.getString(4) + ")224/PK/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  "JKPTG.KN.224/PK/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else{
						//sulit
						if (rs.getString(4) != null){
	
							noFail =  "JKPTG.KN/("+ rs.getString(4) + ")224/PK/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  "JKPTG.KN.224/PK/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// melaka
				else if  ("4".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
							//sulit
						if (rs.getString(4) != null){
	
							noFail =  "JKPTG/M/("+ rs.getString(4) + ")/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
	
							noFail =  "JKPTG/M/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}		
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG/M/("+ rs.getString(4) + ")/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
	
							noFail =  "JKPTG/M/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// n.9
				else if  ("5".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
					//sulit
						if (rs.getString(4) != null){
	
							noFail =  "JKPTG.NS/("+ rs.getString(4) + ")/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  "JKPTG.NS/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG.NS/("+ rs.getString(4) + ")/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  "JKPTG.NS/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					
				}
				
				// pahang
				else if  ("6".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
		
							noFail =  "JKPTG("+ rs.getString(4) + ").PHG/P&K/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.PHG/P&K/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else
					{
						//sulit
						if (rs.getString(4) != null){
		
							noFail =  "JKPTG("+ rs.getString(4) + ").PHG/P&K/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.PHG/P&K/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// p.pinang
				else if  ("7".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
					
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						//sulit
						if (rs.getString(4) != null){
					
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				//perak
				else if  ("8".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
					
							noFail =  "JKPTG.PK("+ rs.getString(4) + ")" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.PK." + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG.PK("+ rs.getString(4) + ")" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.PK." + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// perlis
				else if  ("9".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else
					{
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				// selangor
				else if  ("10".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG(SEL)" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-4/" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  "JKPTG(SEL)" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG(SEL)" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-4/" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  "JKPTG(SEL)" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// terengganu
				else if  ("11".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
	
				}
				
				// sabah
				else if  ("12".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
					else{
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}				}
				
				// sarawak
				else if  ("13".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
					
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						//sulit
						if (rs.getString(4) != null){
					
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				//KL
				else if  ("14".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
				
							noFail =  "PTG/WP9/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan+"("+ rs.getString(4) + ")";
	
						}
						//terbuka
						else{
				
							noFail =  "PTG/WP9/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "PTG/WP9/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan+"("+ rs.getString(4) + ")";
	
						}
						//terbuka
						else{
				
							noFail =  "PTG/WP9/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					
				}
				
				// selain negeri 1-15
				else {
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(4) != null){
						
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(4) != null){
							
							noFail =  "JKPTG("+ rs.getString(4) + ")/101/"+ rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  noFailStandard + rs.getString(5) + "/" + rs.getString(6)+ "/" + rs.getString(3)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}	
				

			}

			return noFail;
		}
	    
	    //ada suburusan
	    else if (suburusan != ""){
	    	
	    	sql = "select b.kod_Suburusan, c.kod_Urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_daerah"+
			" from  tblrujsuburusan b,tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujdaerah f"+
			" where b.id_Suburusan = " + suburusan + 
			" and c.id_Urusan = " + urusan + 
			" and d.id_Tarafkeselamatan = " + taraf;
			
			if(negeri.equals("1") || negeri.equals("3") || negeri.equals("4") || negeri.equals("5") || negeri.equals("6") || negeri.equals("8") || negeri.equals("10") || negeri.equals("14") ){
				sql = sql + " and e.id_Negeri = " + negeri+
						" and f.id_daerah = " + daerah;
			}
			else
			{
				sql = sql + " and e.id_Negeri = " + negeri;
			}	
	    	
	    	ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			if (negeri != null){
				// johor
				if  ("1".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
		
							noFail =  "JKPTG(J)/("+ rs.getString(3) + ")/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG(J)/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else
					{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG(J)/("+ rs.getString(3) + ")/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG(J)/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// kedah
				else if  ("2".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
			
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				//kelantan
				else if  ("3".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
				
							noFail =  "JKPTG.KN/("+ rs.getString(3) + ")224/PK/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  "JKPTG.KN.224/PK/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG.KN/("+ rs.getString(3) + ")224/PK/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  "JKPTG.KN.224/PK/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// melaka
				else if  ("4".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
							//sulit
						if (rs.getString(3) != null){
			
							noFail =  "JKPTG/M/("+ rs.getString(3) + ")/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG/M/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG/M/("+ rs.getString(3) + ")/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG/M/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// n.9
				else if  ("5".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
					//sulit
						if (rs.getString(3) != null){
			
							noFail =  "JKPTG.NS/("+ rs.getString(3) + ")/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG.NS/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG.NS/("+ rs.getString(3) + ")/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG.NS/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// pahang
				else if  ("6".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
					
							noFail =  "JKPTG("+ rs.getString(3) + ").PHG/P&K/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
	
							noFail =  "JKPTG.PHG/P&K/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG("+ rs.getString(3) + ").PHG/P&K/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
	
							noFail =  "JKPTG.PHG/P&K/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// p.pinang
				else if  ("7".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
		
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						//sulit
						if (rs.getString(3) != null){
		
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				//perak
				else if  ("8".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
						
							noFail =  "JKPTG.PK("+ rs.getString(3) + ")" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  "JKPTG.PK." + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG.PK("+ rs.getString(3) + ")" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
			
							noFail =  "JKPTG.PK." + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// perlis
				else if  ("9".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else
					{
						//sulit
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				// selangor
				else if  ("10".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
					//sulit
						if (rs.getString(3) != null){
						
							noFail =  "JKPTG(SEL)" + rs.getString(2)+ "/" + rs.getString(1)+ "-4/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG(SEL)" + rs.getString(2)+ "-" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else
					{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG(SEL)" + rs.getString(2)+ "/" + rs.getString(1)+ "-4/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG(SEL)" + rs.getString(2)+ "-" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// terengganu
				else if  ("11".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
		
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
					else
					{
						//sulit
						if (rs.getString(3) != null){
		
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
		
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				// sabah
				else if  ("12".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
						
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}	
					}
					else{
	
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}	
					}
				}
				
				// sarawak
				else if  ("13".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
						
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(3) != null){
							
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					
				}
				
				//KL
				else if  ("14".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
						
							noFail =  "PTG/WP9/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan+"("+ rs.getString(3) + ")";
	
						}
						//terbuka
						else{
						
							noFail =  "PTG/WP9/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						if (rs.getString(3) != null){
							
							noFail =  "PTG/WP9/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan+"("+ rs.getString(3) + ")";
	
						}
						//terbuka
						else{
						
							noFail =  "PTG/WP9/" + rs.getString(2)+ "/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// selain negeri 1-15
				else {
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(3) != null){
						
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else
					{
						//sulit
						if (rs.getString(3) != null){
						
							noFail =  "JKPTG("+ rs.getString(3) + ")/101/"+ rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(4) + "/" + rs.getString(5)+ "/" + rs.getString(2)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
			}

			return noFail;
		}
	    
	    //ada urusan sahaja
	    else{
	    	sql = "select c.kod_Urusan, d.kod_Taraf_Keselamatan, e.abbrev, f.kod_daerah, f.id_daerah"+
			" from tblrujurusan c,tblpfdrujtarafkeselamatan d, tblrujnegeri e, tblrujdaerah f"+
			" where c.id_Urusan = " + urusan + 
			" and d.id_Tarafkeselamatan = " + taraf;
			
			if(negeri.equals("1") || negeri.equals("3") || negeri.equals("4") || negeri.equals("5") || negeri.equals("6") || negeri.equals("8") || negeri.equals("10") || negeri.equals("14") ){
				sql = sql + " and e.id_Negeri = " + negeri;
			}
			else
			{
				sql = sql + " and e.id_Negeri = " + negeri;
			}
	    	
	    	ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			// ade negeri
			if (negeri != null){
				// johor
				if  ("1".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
					//sulit
						if (rs.getString(2) != null){
					
							noFail =  "JKPTG(J)/("+ rs.getString(2) + ")/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG(J)/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG(J)/("+ rs.getString(2) + ")/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG(J)/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// kedah
				else if  ("2".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
					
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
					else{
						//sulit
						if (rs.getString(2) != null){
					
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
							
				}
				
				//kelantan
				else if  ("3".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG.KN/("+ rs.getString(2) + ")224/PK/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG.KN.224/PK/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG.KN/("+ rs.getString(2) + ")224/PK/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG.KN.224/PK/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// melaka
				else if  ("4".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
					
							noFail =  "JKPTG/M/("+ rs.getString(2) + ")/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG/M/" + rs.getString(1)+ "/" + noTurutan;
	
						}		
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG/M/("+ rs.getString(2) + ")/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
				
							noFail =  "JKPTG/M/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// n.9
				else if  ("5".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG.NS/("+ rs.getString(2) + ")/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.NS/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG.NS/("+ rs.getString(2) + ")/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.NS/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// pahang
				else if  ("6".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ").PHG/P&K/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.PHG/P&K/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ").PHG/P&K/" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG.PHG/P&K/" + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// p.pinang
				else if  ("7".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				//perak
				else if  ("8".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG.PK("+ rs.getString(2) + ")" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  "JKPTG.PK." + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG.PK("+ rs.getString(2) + ")" + rs.getString(1)+ "/" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  "JKPTG.PK." + rs.getString(1)+ "/" + noTurutan;
	
						}	
					}
				}
				
				// perlis
				else if  ("9".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
				}
				
				// selangor
				else if  ("10".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG(SEL)" + rs.getString(1)+ "-4/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG(SEL)" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else
					{
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG(SEL)" + rs.getString(1)+ "-4/" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  "JKPTG(SEL)" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
				}
				
				// terengganu
				else if  ("11".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
					else
					{
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}		
					}
				}
				
				// sabah
				else if  ("12".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
					}
					else{
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
						
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}		
					}
				}
				
				// sarawak
				else if  ("13".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
						
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else
					{
						if (rs.getString(2) != null){
							
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
							
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					
				}
				
				//KL
				else if  ("14".equalsIgnoreCase(negeri)){
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "PTG/WP9/" + rs.getString(1)+ "/" + noTurutan+"("+ rs.getString(2) + ")";
	
						}
						//terbuka
						else{
						
							noFail =  "PTG/WP9/" + rs.getString(1)+ "/" + noTurutan;
	
						}
					}
					else{
						//sulit
						if (rs.getString(2) != null){
							
							noFail =  "PTG/WP9/" + rs.getString(1)+ "/" + noTurutan+"("+ rs.getString(2) + ")";

						}
						//terbuka
						else{
						
							noFail =  "PTG/WP9/" + rs.getString(1)+ "/" + noTurutan;

						}
					}
					
				}
				
				// selain negeri 1-15
				else {
					String check = checkNoFail(negeri,seksyen,urusan,suburusan,subsuburusan,idAktiviti,taraf);
					//klu dah ade no fail
					if (!"".equalsIgnoreCase(check)){
						//sulit
						if (rs.getString(2) != null){
			
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;
	
						}	
					}
					else{
						//sulit
						if (rs.getString(2) != null){
			
							noFail =  "JKPTG("+ rs.getString(2) + ")/101/"+ rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}
						//terbuka
						else{
					
							noFail =  noFailStandard + rs.getString(3) + "/" + rs.getString(4)+ "/" + rs.getString(1)+ "-" + noTurutan;

						}	
					}
				}
			}*/

			return noFail;
	    	
	   // }
	}

		
		private static String checkNoFail(String negeri, String seksyen,String urusan, String suburusan, String subsuburusan,String idAktiviti, String taraf) throws Exception {
			
			Db db = null;
			 String sql = "";
			 String returnValue = "";
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      if("16".equalsIgnoreCase(negeri)){
				      sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
						" id_negeri = "+ negeri + 
						" and id_seksyen = "+ seksyen + 
						" and id_urusan = "+ urusan;
						if(!"".equalsIgnoreCase(suburusan)){
							sql = sql +  " and id_suburusan = "+ suburusan;
						}
				      	if(!"".equalsIgnoreCase(subsuburusan)){
				      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
				      	}
			      }
			      else
			      {
			    	  sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
						" id_negeri = "+ negeri + 
						//" and id_seksyen = "+ seksyen + 
						" and id_urusan = "+ urusan;
						if(!"".equalsIgnoreCase(suburusan)){
							sql = sql +  " and id_suburusan = "+ suburusan;
						}
				      	if(!"".equalsIgnoreCase(subsuburusan)){
				      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
				      	}
			      }
			      
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h = new Hashtable();
			      if (rs.next()) {
			    	  returnValue = rs.getString("no_turutan")==null?"":rs.getString("no_turutan");
			      }
			      
			      return returnValue;  
			      
			 }
			 finally {
			      if (db != null) db.close();
			    }

	}
		private static String checkNoFailArkib(String negeri, String seksyen,String urusan, String suburusan, String subsuburusan,String subsubsuburusan,String idAktiviti, String taraf) throws Exception {
			
			Db db = null;
			 String sql = "";
			 String returnValue = "";
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      if("16".equalsIgnoreCase(negeri)){
				      sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
						" id_negeri = "+ negeri + 
						" and id_seksyen = "+ seksyen + 
						" and id_urusan = "+ urusan;
						if(!"".equalsIgnoreCase(suburusan)){
							sql = sql +  " and id_suburusan = "+ suburusan;
						}
				      	if(!"".equalsIgnoreCase(subsuburusan)){
				      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
				      	}
				      	if(!"".equalsIgnoreCase(subsubsuburusan)){
					      	sql = sql +  " and id_subsubsuburusan = "+ subsubsuburusan ;
					      	}
			      }
			      else
			      {
			    	  sql = "select MAX(no_turutan) as no_turutan from TBLPFDFAIL where"+
						" id_negeri = "+ negeri + 
						//" and id_seksyen = "+ seksyen + 
						" and id_urusan = "+ urusan;
						if(!"".equalsIgnoreCase(suburusan)){
							sql = sql +  " and id_suburusan = "+ suburusan;
						}
				      	if(!"".equalsIgnoreCase(subsuburusan)){
				      	sql = sql +  " and id_subsuburusan = "+ subsuburusan ;
				      	}
				      	if(!"".equalsIgnoreCase(subsubsuburusan)){
					      	sql = sql +  " and id_subsubsuburusan = "+ subsubsuburusan ;
					      	}
			      }
			      
			     
			      ResultSet rs = stmt.executeQuery(sql);
			     
			      Hashtable h = new Hashtable();
			      if (rs.next()) {
			    	  returnValue = rs.getString("no_turutan")==null?"":rs.getString("no_turutan");
			      }
			      
			      return returnValue;  
			      
			 }
			 finally {
			      if (db != null) db.close();
			    }

	}


		public void updateSubjaketNJilid(String idFail) throws Exception {
			Db db = null;
			 String sql = "";
			 String sql2 = "";
			 String returnValue = "";
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      sql = "UPDATE TBLPFDFAIL SET NO_TURUTAN_JLD = '1' WHERE ID_FAIL = '"+idFail+"'";
			      
			      stmt.executeQuery(sql);
			      
			      sql2 = "UPDATE TBLPFDFAIL SET NO_TURUTAN_SUBJAKET = '0' WHERE ID_FAIL = '"+idFail+"'";
			      
			      stmt.executeQuery(sql2);
			      
			 }
			 finally {
			      if (db != null) db.close();
			    }
		}

		public void hapus(String idFail) throws Exception {
			Db db = null;
			 String sql = "";
			 String sql2 = "";
			 String returnValue = "";
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      
			      sql = "UPDATE TBLPFDFAIL SET ID_STATUS = '999', NO_TURUTAN_JLD = '1', NO_TURUTAN_SUBJAKET = '0', NO_TURUTAN = '0' WHERE ID_FAIL = '"+idFail+"'";
			      
			      stmt.executeQuery(sql);
			      
			 }
			 finally {
			      if (db != null) db.close();
			    }
		}
		


}

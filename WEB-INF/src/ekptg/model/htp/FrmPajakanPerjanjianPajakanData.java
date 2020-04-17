package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPajakanPerjanjianPajakanData {
		
	private static Logger myLog = Logger.getLogger(FrmPajakanPerjanjianPajakanData.class);

	private Vector<Hashtable <String,String>> senaraiFail = null;
	private Vector<Hashtable <String,String>> beanMaklumatMOA = null;
	private Vector<Hashtable <String,String>> beanMaklumat15A = null;
	private Vector<Hashtable <String,String>> beanMaklumatJemaahMenteri = null;
	private Vector<Hashtable <String,String>> senaraiDraf = null;
	private Vector<Hashtable<String,String>> beanMaklumatDraf = null;
	private Vector<Hashtable <String,String>>senaraiPajakan = null;
	private Vector<Hashtable <String,String>> beanMaklumatPajakan = null;
	private Db db = null;
	private Connection conn = null;
	private String sql = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void carianFail(String noFail, String tarikhTerima) throws Exception {
		try {
			senaraiFail = new Vector<Hashtable <String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18) AND B.ID_STATUS IN (69, 86, 87)";

			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}

			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void seterusnya(String idFail, String idPermohonan, String subUrusan, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("9",subUrusan,"=");
			Long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("9",subUrusan,"=");
			
			//TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", setIdStatus);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");
			
			r.add("AKTIF", "0");
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			r = new SQLRenderer();
			long idSuburusanstatusfail = DB.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			/*if ("7".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "23"); //MAKLUMAT PERMOHONAN TANAH
			} else if ("17".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "192"); //MAKLUMAT PERMOHONAN TANAH
			} else if ("18".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "201"); //MAKLUMAT PERMOHONAN TANAH
			}*/
			r.add("ID_SUBURUSANSTATUS", setIdSuburusanstatus);
			r.add("AKTIF", "1");	
			r.add("ID_FAIL", idFail);
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatMOA(String idPermohonan) throws Exception {		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatMOA = new Vector<Hashtable <String,String>>();
			
			sql = "SELECT A.ID_MOA, A.ID_PERMOHONAN, A.TARIKH_HANTAR, ";
			sql += "A.TARIKH_TERIMA, A.NO_RUJUKAN_MOA, A.TARIKH_TANDATANGAN, ";
			sql += "A.TARIKH_DAFTAR, A.TARIKH_BAYARAN, A.TARIKH_HANTAR_PENGARAH ";
			sql += "FROM TBLHTPMOA A ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable <String,String> h;
			while(rs.next()){
				h = new Hashtable <String,String>();
				h.put("idMOA", rs.getString("ID_MOA") == null ? "" : rs.getString("ID_MOA"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhTandatangan", rs.getDate("TARIKH_TANDATANGAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TANDATANGAN")));
				h.put("tarikhKembali", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("tarikhBayar", rs.getDate("TARIKH_BAYARAN") == null ? "" : sdf.format(rs.getDate("TARIKH_BAYARAN")));
				h.put("noPerjanjian", rs.getString("NO_RUJUKAN_MOA") == null ? "" : rs.getString("NO_RUJUKAN_MOA"));
				h.put("tarikhHantarPengarah", rs.getDate("TARIKH_HANTAR_PENGARAH") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_PENGARAH")));

				beanMaklumatMOA.addElement(h);		
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateMOA(String idPermohonan, Hashtable <String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLHTPMOA
			r.update("ID_PERMOHONAN", idPermohonan);
			
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTandatangan"))){
				r.add("TARIKH_TANDATANGAN", r.unquote("to_date('" + hash.get("tarikhTandatangan") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhKembali"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhKembali") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhDaftar"))){
				r.add("TARIKH_DAFTAR", r.unquote("to_date('" + hash.get("tarikhDaftar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhBayar"))){
				r.add("TARIKH_BAYARAN", r.unquote("to_date('" + hash.get("tarikhBayar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarPengarah"))){
				r.add("TARIKH_HANTAR_PENGARAH", r.unquote("to_date('" + hash.get("tarikhHantarPengarah") + "','dd/MM/yyyy')"));
			}	
			r.add("NO_RUJUKAN_MOA", hash.get("noPerjanjian"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPMOA");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	public Hashtable<String,String> getMaklumat15A(String idPermohonan){
		Hashtable <String,String> h = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT A.FLAG_NOTIFIKASI, A.ID_BORANGPAJAKAN, A.ID_PERMOHONAN, A.NO_PERSERAHAN, ";
			sql += "A.TARIKH_TERIMA, A.TARIKH_HANTARPEMOHON, A.TARIKH_TANDATANGANPTP, ";
			sql += "A.TARIKH_DAFTAR, A.TARIKH_TERIMAHAKMILIK, A.TARIKH_KEMASKINIHAKMILIK ";
			sql += "FROM  TBLHTPBORANGPAJAKAN A ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			//myLog.info("getMaklumat15A("+idPermohonan+"):sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				h = new Hashtable <String,String>();
				h.put("idBorangPajakan", rs.getString("ID_BORANGPAJAKAN") == null ? "" : rs.getString("ID_BORANGPAJAKAN"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhTandatangan", rs.getDate("TARIKH_TANDATANGANPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TANDATANGANPTP")));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTARPEMOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPEMOHON")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("tarikhTerimaHakmilik", rs.getDate("TARIKH_TERIMAHAKMILIK") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAHAKMILIK")));
				h.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINIHAKMILIK") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINIHAKMILIK")));
				h.put("flag_notifikasi", rs.getString("FLAG_NOTIFIKASI") == null ? "" : rs.getString("FLAG_NOTIFIKASI"));
				//beanMaklumat15A.addElement(h);		
			}

		} catch(Exception e){
			e.printStackTrace();
		}
		return h;
		
	}
	public void setMaklumat15A(String idPermohonan){
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumat15A = new Vector<Hashtable <String,String>>();
			
			sql = "SELECT A.FLAG_NOTIFIKASI, A.ID_BORANGPAJAKAN, A.ID_PERMOHONAN, A.NO_PERSERAHAN, ";
			sql += "A.TARIKH_TERIMA, A.TARIKH_HANTARPEMOHON, A.TARIKH_TANDATANGANPTP, ";
			sql += "A.TARIKH_DAFTAR, A.TARIKH_TERIMAHAKMILIK, A.TARIKH_KEMASKINIHAKMILIK ";
			sql += "FROM  TBLHTPBORANGPAJAKAN A ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			//myLog.info("setMaklumat15A("+idPermohonan+"):sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable <String,String> h;
			while(rs.next()){
				h = new Hashtable <String,String>();
				h.put("idBorangPajakan", rs.getString("ID_BORANGPAJAKAN") == null ? "" : rs.getString("ID_BORANGPAJAKAN"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhTandatangan", rs.getDate("TARIKH_TANDATANGANPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TANDATANGANPTP")));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTARPEMOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPEMOHON")));
				h.put("tarikhDaftar", rs.getDate("TARIKH_DAFTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_DAFTAR")));
				h.put("tarikhTerimaHakmilik", rs.getDate("TARIKH_TERIMAHAKMILIK") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAHAKMILIK")));
				h.put("tarikhKemaskini", rs.getDate("TARIKH_KEMASKINIHAKMILIK") == null ? "" : sdf.format(rs.getDate("TARIKH_KEMASKINIHAKMILIK")));
				h.put("flag_notifikasi", rs.getString("FLAG_NOTIFIKASI") == null ? "" : rs.getString("FLAG_NOTIFIKASI"));
				beanMaklumat15A.addElement(h);		
			}

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void update15A(String idPermohonan, Hashtable <String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLHTPBORANGPAJAKAN
			r.update("ID_PERMOHONAN", idPermohonan);
			
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTandatangan"))){
				r.add("TARIKH_TANDATANGANPTP", r.unquote("to_date('" + hash.get("tarikhTandatangan") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTARPEMOHON", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhDaftar"))){
				r.add("TARIKH_DAFTAR", r.unquote("to_date('" + hash.get("tarikhDaftar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerimaHakmilik"))){
				r.add("TARIKH_TERIMAHAKMILIK", r.unquote("to_date('" + hash.get("tarikhTerimaHakmilik") + "','dd/MM/yyyy')"));
			}	
			if (!"".equals(hash.get("tarikhKemaskini"))){
				r.add("TARIKH_KEMASKINIHAKMILIK", r.unquote("to_date('" + hash.get("tarikhKemaskini") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("flagNotifikasi"))){
				r.add("FLAG_NOTIFIKASI", hash.get("flagNotifikasi"));
			}
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPBORANGPAJAKAN");
			myLog.info("update15A:sql="+sql);
			stmt.executeUpdate(sql);			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatTabKelulusanJemaahMenteri(String idPermohonan){
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatJemaahMenteri = new Vector<Hashtable <String,String>>();
			
			sql = "SELECT A.ID_JEMAAHMENTERI, A.ID_PERMOHONAN, A.TARIKH_KUASA_PBDN, ";
			sql += "A.TARIKH_MSYRT_JEMAAH, A.NO_MEMORANDUM ";
			sql += "FROM TBLHTPJEMAAHMENTERI A ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;

			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idJemaahMenteri", rs.getString("ID_JEMAAHMENTERI") == null ? "" : rs.getString("ID_JEMAAHMENTERI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhMesyuarat", rs.getDate("TARIKH_MSYRT_JEMAAH") == null ? "" : sdf.format(rs.getDate("TARIKH_MSYRT_JEMAAH")));
				h.put("noMemo", rs.getString("NO_MEMORANDUM") == null ? "" : rs.getString("NO_MEMORANDUM"));
				h.put("tarikhPerbadanan", rs.getDate("TARIKH_KUASA_PBDN") == null ? "" : sdf.format(rs.getDate("TARIKH_KUASA_PBDN")));
				
				beanMaklumatJemaahMenteri.addElement(h);		
			}

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateJM(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLHTPJEMAAHMENTERI
			r.update("ID_PERMOHONAN", idPermohonan);
			
			if (!"".equals(hash.get("tarikhPerbadanan"))){
				r.add("TARIKH_KUASA_PBDN", r.unquote("to_date('" + hash.get("tarikhPerbadanan") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhMesyuarat"))){
				r.add("TARIKH_MSYRT_JEMAAH", r.unquote("to_date('" + hash.get("tarikhMesyuarat") + "','dd/MM/yyyy')"));
			}
			r.add("NO_MEMORANDUM", hash.get("noMemo"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPJEMAAHMENTERI");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setListDraf(String idPermohonan) throws Exception {
		try {
			senaraiDraf = new Vector<Hashtable <String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_DERAFPERJANJIAN, A.TARIKH_HANTAR_DERAF, A.TARIKH_TERIMA, A.TARIKH_HANTARPTP, A.TARIKH_TERIMAPTP, A.ULASAN_SEKSYEN"
				+ " FROM TBLHTPDERAFPERJANJIAN A WHERE A.JENIS_DOKUMEN = 'P' AND A.ID_PERMOHONAN = '" + idPermohonan + "'";		
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable <String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable <String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DERAF")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarPKP", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerimaPKP", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));
				senaraiDraf.addElement(h);
				bil++;
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public void setMaklumatDraf(String idDraf) throws Exception {
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatDraf = new Vector<Hashtable<String,String>>();
			
			sql = "SELECT A.ID_DERAFPERJANJIAN, A.TARIKH_HANTAR_DERAF, A.TARIKH_TERIMA, A.TARIKH_HANTARPTP, A.TARIKH_TERIMAPTP, A.ULASAN_SEKSYEN"
				+ " FROM TBLHTPDERAFPERJANJIAN A WHERE A.ID_DERAFPERJANJIAN = '" + idDraf + "'";		
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DERAF")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarPKP", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerimaPKP", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));				
				beanMaklumatDraf.addElement(h);		
				
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	public Vector<Hashtable<String,String>> getMaklumatDraf(String idDraf) throws Exception {
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatDraf = new Vector<Hashtable<String,String>>();
			
			sql = "SELECT DP.ID_DERAFPERJANJIAN, DP.TARIKH_HANTARPTP,DP.TARIKH_TERIMAPTP,DP.ULASAN_SEKSYEN" +
				" A.TARIKH_TERIMAPTP, A.ULASAN_SEKSYEN"
				+ " FROM TBLHTPDERAFPERJANJIAN A WHERE A.ID_DERAFPERJANJIAN = '" + idDraf + "'";		
			ResultSet rs = stmt.executeQuery(sql);
			 
			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DERAF")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarPKP", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerimaPKP", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));				
				beanMaklumatDraf.addElement(h);		
				
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatDraf;
	}

	public Hashtable<String,String> getMaklumatPerjanjian(String idDraf) throws Exception {
		Hashtable<String,String> h = null;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			//beanMaklumatDraf = new Vector<Hashtable<String,String>>();
			
			sql = "SELECT A.ID_DERAFPERJANJIAN, A.TARIKH_HANTAR_DERAF, A.TARIKH_TERIMA, A.TARIKH_HANTARPTP, A.TARIKH_TERIMAPTP, A.ULASAN_SEKSYEN"
				+ " FROM TBLHTPDERAFPERJANJIAN A WHERE A.ID_DERAFPERJANJIAN = '" + idDraf + "'";		
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DERAF")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarPKP", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerimaPKP", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));				
				//beanMaklumatDraf.addElement(h);		
				
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return h;
	
	}
	
	public void saveDraf(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
	//public void saveDraf(String idPermohonan, Hashtable<String,String> hash) throws Exception { //2018
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPDERAFPERJANJIAN
			long idDerafPerjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");
			r.add("ID_DERAFPERJANJIAN", idDerafPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);		
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR_DERAF", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarPKP"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantarPKP") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerimaPKP"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerimaPKP") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan"));
			r.add("JENIS_DOKUMEN","P");	
			
			r.add("ID_MASUK", userId);
			//r.add("ID_MASUK", hash.get("userId")); //2018
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPDERAFPERJANJIAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void saveUpdateDraf(String idDraf, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPDERAFPERJANJIAN
			r.update("ID_DERAFPERJANJIAN", idDraf);
					
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR_DERAF", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarPKP"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantarPKP") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerimaPKP"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerimaPKP") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan"));	
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPDERAFPERJANJIAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public void hapusDraf(String idDraf) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPDERAFPERJANJIAN
			r.add("ID_DERAFPERJANJIAN", r.unquote(idDraf));
			sql = r.getSQLDelete("TBLHTPDERAFPERJANJIAN");
			//myLog.info("sql="+sql);
			stmt.executeUpdate(sql);			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
		
	}
	
	public void setListPajakan(String idPermohonan) throws Exception {
		try {
			senaraiPajakan = new Vector<Hashtable <String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			//sql = "SELECT A.ID_PAJAKAN, A.TARIKH_TANDATANGAN, A.TARIKH_MULA_PAJAKAN, A.TARIKH_TAMATPAJAKAN, A.TEMPOH_PAJAKAN, A.KADAR_CUKAI, A.KADAR_PAJAKAN, C.KETERANGAN, A.CARA_BAYAR"
			//	+ " FROM TBLHTPPAJAKAN A, TBLRUJCARABAYAR C WHERE A.CARA_BAYAR = C.ID_CARABAYAR AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			sql = " SELECT a.id_pajakan, a.tarikh_tandatangan, a.tarikh_mula_pajakan, "+
					" a.tarikh_tamatpajakan, a.tempoh_pajakan, a.kadar_cukai, "+
					" nvl(a.kadar_pajakan,0) kadar_pajakan, c.keterangan, a.cara_bayar, "+
					" case "+
					" when ADD_MONTHS(a.tarikh_tamatpajakan,-(a.notifikasi_peringatan*12)) <= SYSDATE THEN 'Y' "+
					" else 'N' "+
					" end AS papar_notifikasi, a.notifikasi_peringatan "+
					" FROM tblhtppajakan a, tblrujcarabayar c "+
					" WHERE a.cara_bayar = c.id_carabayar(+) " +
					"AND a.id_permohonan = '"+idPermohonan+"' " +
					"";
			
			myLog.info("setListPajakan:sql= "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable <String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable <String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idPajakan", rs.getString("ID_PAJAKAN") == null ? "" : rs.getString("ID_PAJAKAN"));
				h.put("tarikhTandatangan", rs.getDate("TARIKH_TANDATANGAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TANDATANGAN")));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA_PAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA_PAJAKAN")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMATPAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMATPAJAKAN")));
				h.put("tempoh", rs.getString("TEMPOH_PAJAKAN") == null ? "" : rs.getString("TEMPOH_PAJAKAN"));
				h.put("kadar", Utils.format2Decimal(Double.parseDouble(rs.getString("KADAR_CUKAI") == null ? "0" : rs.getString("KADAR_CUKAI"))));
				h.put("kadarPajakan", Utils.format2Decimal(Double.parseDouble(rs.getString("KADAR_PAJAKAN") == null ? "" : rs.getString("KADAR_PAJAKAN"))));
				h.put("caraBayar", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idCaraBayar", rs.getString("CARA_BAYAR") == null ? "" : rs.getString("CARA_BAYAR"));
				h.put("papar_notifikasi", rs.getString("PAPAR_NOTIFIKASI") == null ? "" : rs.getString("PAPAR_NOTIFIKASI"));
				senaraiPajakan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPajakan(String idPajakan) throws Exception {
		try {
			beanMaklumatPajakan = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NOTIFIKASI_PERINGATAN, A.ID_PAJAKAN, A.TARIKH_TANDATANGAN, A.TARIKH_MULA_PAJAKAN, A.TARIKH_TAMATPAJAKAN" +
				", A.KADAR_PAJAKAN, A.TEMPOH_PAJAKAN, A.KADAR_CUKAI, C.KETERANGAN, A.CARA_BAYAR" +
				", A.KATEGORI_CUKAI, A.TARIKH_PATUT_BAYAR,A.CATATAN,A.DENDA,A.FLAG_AKTIF " +
				" FROM TBLHTPPAJAKAN A, TBLRUJCARABAYAR C " +
				" WHERE A.CARA_BAYAR = C.ID_CARABAYAR(+) " +
				" AND A.ID_PAJAKAN = '" + idPajakan + "'";
			myLog.info("setMaklumatPajakan:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idPajakan", rs.getString("ID_PAJAKAN") == null ? "" : rs.getString("ID_PAJAKAN"));
				h.put("tarikhTandatangan", rs.getDate("TARIKH_TANDATANGAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TANDATANGAN")));
				h.put("tarikhMula", rs.getDate("TARIKH_MULA_PAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_MULA_PAJAKAN")));
				h.put("tarikhTamat", rs.getDate("TARIKH_TAMATPAJAKAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TAMATPAJAKAN")));
				h.put("tempoh", rs.getString("TEMPOH_PAJAKAN") == null ? "0" : rs.getString("TEMPOH_PAJAKAN"));
				h.put("kadar", rs.getString("KADAR_CUKAI") == null ? "0" : rs.getString("KADAR_CUKAI"));
				h.put("kadarPajakan", rs.getString("KADAR_PAJAKAN") == null ? "0" : rs.getString("KADAR_PAJAKAN"));
				h.put("caraBayar", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("idCaraBayar", rs.getString("CARA_BAYAR") == null ? "0" : rs.getString("CARA_BAYAR"));
				h.put("katCukai", rs.getString("KATEGORI_CUKAI") == null ? "" : rs.getString("KATEGORI_CUKAI"));
				h.put("notifikasiPeringatan", String.valueOf(
					rs.getString("NOTIFIKASI_PERINGATAN") == null ? "0" : rs.getInt("NOTIFIKASI_PERINGATAN")));
				h.put("tarikhPatut", rs.getDate("TARIKH_PATUT_BAYAR") == null ? "" : sdf.format(rs.getDate("TARIKH_PATUT_BAYAR")));
				//myLog.info("catatan"+ rs.getString("CATATAN"));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs.getString("CATATAN"));
				h.put("status", rs.getString("FLAG_AKTIF") == null ? "Y" : rs.getString("FLAG_AKTIF"));
				h.put("denda", rs.getString("DENDA") == null ? "" : rs.getString("DENDA"));
				beanMaklumatPajakan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusPajakan(String idPajakan) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			//TBLHTPPAJAKAN
			r.add("ID_PAJAKAN", idPajakan);
			sql = r.getSQLDelete("TBLHTPPAJAKAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
	
	public void savePajakan(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();				
			//System.out.println("kadar : "+hash.get("kadar"));
			
			//TBLHTPPAJAKAN
			long idPajakan = DB.getNextID("TBLHTPPAJAKAN_SEQ");
			r.add("ID_PAJAKAN", idPajakan);
			r.add("ID_PERMOHONAN", idPermohonan);		
			if (!"".equals(hash.get("tarikhTandatangan"))){
				r.add("TARIKH_TANDATANGAN", r.unquote("to_date('" + hash.get("tarikhTandatangan") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhMula"))){
				r.add("TARIKH_MULA_PAJAKAN", r.unquote("to_date('" + hash.get("tarikhMula") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTamat"))){
				r.add("TARIKH_TAMATPAJAKAN", r.unquote("to_date('" + hash.get("tarikhTamat") + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH_PAJAKAN", hash.get("tempoh"));
			r.add("KADAR_CUKAI", hash.get("kadar"));
			r.add("KADAR_PAJAKAN", hash.get("kadarPajakan"));
			r.add("CARA_BAYAR", hash.get("idCaraBayar"));
			r.add("KATEGORI_CUKAI", hash.get("katCukai"));		
			//PENAMBAHBAIKAN FASA3. 27112014. SYAZ. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT
			r.add("NOTIFIKASI_PERINGATAN",hash.get("socNotifikasiPeringatan"));		
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			//2017/05/09
			if (!"".equals(hash.get("tarikhPatut"))){
				r.add("TARIKH_PATUT_BAYAR", r.unquote("to_date('" + hash.get("tarikhPatut") + "','dd/MM/yyyy')"));
			}
			r.add("CATATAN", hash.get("catatan"));
			r.add("FLAG_AKTIF", hash.get("status"));
			r.add("DENDA", hash.get("denda"));

			sql = r.getSQLInsert("TBLHTPPAJAKAN");
			//System.out.println("sql 1 "+sql);
			stmt.executeUpdate(sql);

			//TBLHTPMOA
			r = new SQLRenderer();
			r.add("ID_MOA");
			r.add("ID_PERMOHONAN",idPermohonan);				      
			sql = r.getSQLSelect("TBLHTPMOA");
			rs = stmt.executeQuery(sql);
			if(rs.next()==false){
				r = new SQLRenderer();			
				long idMOA = DB.getNextID("TBLHTPMOA_SEQ");
				r.add("ID_MOA", idMOA);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLHTPMOA");
				//System.out.println("sql 2 "+sql);
				stmt.executeUpdate(sql);
				
			}		
			
			//TBLHTPBORANGPAJAKAN
			r = new SQLRenderer();
			r.add("ID_BORANGPAJAKAN");
			r.add("ID_PERMOHONAN",idPermohonan);				      
			sql = r.getSQLSelect("TBLHTPBORANGPAJAKAN");
			rs = stmt.executeQuery(sql);
			if(rs.next()==false){
				r = new SQLRenderer();
				long idBorangPajakan = DB.getNextID("TBLHTPBORANGPAJAKAN_SEQ");
				r.add("ID_BORANGPAJAKAN", idBorangPajakan);
				r.add("ID_PERMOHONAN", idPermohonan);	
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));	
				sql = r.getSQLInsert("TBLHTPBORANGPAJAKAN");
				//System.out.println("sql 3 "+sql);
				stmt.executeUpdate(sql);
				
			}
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public void saveUpdatePajakan(String idPajakan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPPAJAKAN
			r.update("ID_PAJAKAN", r.unquote(idPajakan));
						
			if (!"".equals(hash.get("tarikhTandatangan"))){
				r.add("TARIKH_TANDATANGAN", r.unquote("to_date('" + hash.get("tarikhTandatangan") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhMula"))){
				r.add("TARIKH_MULA_PAJAKAN", r.unquote("to_date('" + hash.get("tarikhMula") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTamat"))){
				r.add("TARIKH_TAMATPAJAKAN", r.unquote("to_date('" + hash.get("tarikhTamat") + "','dd/MM/yyyy')"));
			}
			r.add("TEMPOH_PAJAKAN", hash.get("tempoh"));
			r.add("KADAR_CUKAI", hash.get("kadar"));
			r.add("KADAR_PAJAKAN", hash.get("kadarPajakan"));
			r.add("CARA_BAYAR", hash.get("idCaraBayar"));
			r.add("KATEGORI_CUKAI", hash.get("katCukai"));		
			//PENAMBAHBAIKAN FASA3. 27112014. SYAZ. POPUP/NOTIFIKASI PERINGATAN SEMULA / LUPUT
			r.add("NOTIFIKASI_PERINGATAN",hash.get("socNotifikasiPeringatan"));			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			//2017/05/09
			if (!"".equals(hash.get("tarikhPatut"))){
				r.add("TARIKH_PATUT_BAYAR", r.unquote("to_date('" + hash.get("tarikhPatut") + "','dd/MM/yyyy')"));
			}
			r.add("CATATAN", hash.get("catatan"));
			r.add("FLAG_AKTIF", hash.get("status"));
			r.add("DENDA", hash.get("denda"));
			
			sql = r.getSQLUpdate("TBLHTPPAJAKAN");
			myLog.info("saveUpdatePajakan:sql="+sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("saveUpdatePajakan,Ralat: Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public Vector<Hashtable <String,String>> getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector<Hashtable <String,String>> senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector<Hashtable <String,String>> getBeanMaklumatMOA() {
		return beanMaklumatMOA;
	}

	public void setBeanMaklumatMOA(Vector<Hashtable <String,String>> beanMaklumatMOA) {
		this.beanMaklumatMOA = beanMaklumatMOA;
	}

	public Vector<Hashtable <String,String>> getBeanMaklumat15A() {
		return beanMaklumat15A;
	}

	public void setBeanMaklumat15A(Vector<Hashtable <String,String>> beanMaklumat15A) {
		this.beanMaklumat15A = beanMaklumat15A;
	}

	public Vector<Hashtable <String,String>> getBeanMaklumatJemaahMenteri() {
		return beanMaklumatJemaahMenteri;
	}

	public void setBeanMaklumatJemaahMenteri(Vector<Hashtable <String,String>> beanMaklumatJemaahMenteri) {
		this.beanMaklumatJemaahMenteri = beanMaklumatJemaahMenteri;
	}

	public Vector<Hashtable <String,String>> getSenaraiDraf() {
		return senaraiDraf;
	}

	public void setSenaraiDraf(Vector<Hashtable <String,String>> senaraiDraf) {
		this.senaraiDraf = senaraiDraf;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatDraf() {
		return beanMaklumatDraf;
	}

	public void setBeanMaklumatDraf(Vector<Hashtable <String,String>> beanMaklumatDraf) {
		this.beanMaklumatDraf = beanMaklumatDraf;
	}

	public Vector<Hashtable <String,String>> getSenaraiPajakan() {
		return senaraiPajakan;
	}

	public void setSenaraiPajakan(Vector<Hashtable <String,String>> senaraiPajakan) {
		this.senaraiPajakan = senaraiPajakan;
	}

	public Vector<Hashtable <String,String>> getBeanMaklumatPajakan() {
		return beanMaklumatPajakan;
	}

	public void setBeanMaklumatPajakan(Vector<Hashtable <String,String>> beanMaklumatPajakan) {
		this.beanMaklumatPajakan = beanMaklumatPajakan;
	}
}

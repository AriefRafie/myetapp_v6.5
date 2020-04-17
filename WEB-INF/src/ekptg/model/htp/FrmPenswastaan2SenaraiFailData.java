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
import ekptg.helpers.File;

/**
 * 
 *	Modified by Fauzul
 *	Modified by Mohamad Rosli
 */
public class FrmPenswastaan2SenaraiFailData {
	
	private static Logger myLog = Logger.getLogger(FrmPenswastaan2SenaraiFailData.class);
	private Hashtable<String,String> h = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";
	private Vector<Hashtable<String,String>> senaraiFail = null;
	private Vector<Hashtable<String,String>> beanMaklumatPermohonan = null;
	public String  strIdPermohonan = "";	

	public void carianFail(String noFail, String tarikhTerima) throws Exception {
		Db db = null;
		try {
			senaraiFail = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA" +
				",RN.NAMA_NEGERI,A.TAJUK_FAIL"+
				" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJNEGERI RN"+
				" WHERE B.ID_FAIL = A.ID_FAIL AND A.ID_URUSAN = '4' " +
				" AND RN.ID_NEGERI=A.ID_NEGERI ";
				//+ " WHERE B.ID_FAIL = A.ID_FAIL AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '4' AND A.ID_SUBURUSAN IN (8, 9, 10, 11, 12)";

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
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MMM-yy') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}

			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			ResultSet rs = stmt.executeQuery(sql);
//			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	//Created by Fauzul on 13/04/2010
	//modified by Rosli 
	public Vector<Hashtable<String,String>> carianFail(String noFail, String tarikhTerima,String userId) throws Exception {
		Db db = null;
		try {
			senaraiFail = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, " +
				  " C.NAMA_NEGERI, A.TAJUK_FAIL, S.KETERANGAN,B.TARIKH_KEMASKINI "+
				  " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJNEGERI C, " +
				  " TBLRUJSUBURUSANSTATUSFAIL SF, TBLRUJSUBURUSANSTATUS SS,TBLRUJSTATUS S"+
				  " WHERE B.ID_FAIL = A.ID_FAIL AND A.ID_URUSAN = 4 AND A.ID_NEGERI = C.ID_NEGERI " +
				  " AND B.ID_FAIL = SF.ID_FAIL  AND B.ID_PERMOHONAN=SF.ID_PERMOHONAN"+
				  " AND SF.ID_SUBURUSANSTATUS  = SS.ID_SUBURUSANSTATUS " +
				  " AND SS.ID_STATUS = S.ID_STATUS " +
				  //" AND SF.AKTIF = '1'" +
				  "";
			//" AND B.ID_STATUS = SS.ID_STATUS  ";

			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			//SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd/MM/yyyy') = '" + sdf.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}

			//user Id
			if (userId != null) {
				if (!userId.toString().trim().equals("")) {
					sql = sql + " AND A.ID_MASUK = " + userId;
				}
			}			
			
			sql = sql + " ORDER BY B.TARIKH_KEMASKINI DESC";
			myLog.info("list fail ::"+sql);
			ResultSet rs = stmt.executeQuery(sql);
//			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				senaraiFail.addElement(h);
				bil++;
			}
			return senaraiFail;
			
		} finally {
			if (db != null)
				db.close();
		}
	}
		
	public String simpan(String idNegeri, String idKementerian, String idAgensi, String idSuburusan, String idStatusTanah,
			String idJenisFail, String noFailKJP, String tarikhSuratKJP, String noFailLain, String tarikhAgihan, String tajuk, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
//		String sql = "";
		String idFailString = "";		
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String TKJP = "to_date('" + tarikhSuratKJP + "','dd/MM/yyyy')";
			String TA = "to_date('" + tarikhAgihan + "','dd/MM/yyyy')";

			//TBLPFDFAIL
			long idFail = DB.getNextID(db, "TBLPFDFAIL_SEQ");
			idFailString = String.valueOf(idFail);
			r.add("ID_FAIL", idFail);
			r.add("ID_URUSAN", "4");
			r.add("ID_SUBURUSAN", idSuburusan);
			r.add("ID_TARAFKESELAMATAN", idJenisFail);
			r.add("ID_SEKSYEN", "3");
			r.add("FLAG_FAIL", "1");
			r.add("TARIKH_DAFTAR_FAIL", r.unquote("SYSDATE"));
			r.add("TAJUK_FAIL", tajuk);
			
			String kodUrusan = "906";
			
			r.add("NO_FAIL", generateNoFail(idJenisFail,kodUrusan, getKodKementerian(idKementerian), idKementerian, getKodMampu(idNegeri), idNegeri));
			r.add("ID_NEGERI", idNegeri);
			r.add("ID_KEMENTERIAN", idKementerian);	
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			long idPermohonan = DB.getNextID(db, "TBLPERMOHONAN_SEQ");
			strIdPermohonan = ""+idPermohonan;
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_JKPTG", "1");
			r.add("ID_FAIL", idFail);
			r.add("ID_STATUS", "6");
			if (!"".equals(tarikhSuratKJP)){
				r.add("TARIKH_SURAT", r.unquote(TKJP));
			}			
			r.add("TARIKH_TERIMA", r.unquote("SYSDATE"));
			r.add("TUJUAN", tajuk);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			//Penambahbaikan. Syaz. 01122014. Untuk function pengesahan (2 layer)
			/**
			 * N = BARU SAVE
			 * H = HANTAR
			 * S = SAH (TELAH DISAHKAN)
			 * 
			 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
			 */
			r.add("FLAG_MOHON_FAIL", "N");
			
			sql = r.getSQLInsert("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			long idHTPPermohonan = DB.getNextID(db, "TBLHTPPERMOHONAN_SEQ");
			r.add("ID_HTPPERMOHONAN", idHTPPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_JENISTANAH", idStatusTanah);
			r.add("NO_RUJUKAN_KJP", noFailKJP);
			r.add("NO_RUJUKAN_LAIN", noFailLain);
			if (!"".equals(tarikhAgihan)){
				r.add("TARIKH_AGIHAN", r.unquote(TA));
			}	
			r.add("ID_PEGAWAI", userId);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLHTPPEMAJU
			r = new SQLRenderer();
			long idPemaju = DB.getNextID(db, "TBLHTPPEMAJU_SEQ");
			r.add("ID_PEMAJU", idPemaju);
			r.add("ID_PERMOHONAN", idPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPPEMAJU");
			stmt.executeUpdate(sql);
			
			//TBLHTPJEMAAHMENTERI
			r = new SQLRenderer();
			long idJemaahMenteri = DB.getNextID(db, "TBLHTPJEMAAHMENTERI_SEQ");
			r.add("ID_JEMAAHMENTERI", idJemaahMenteri);
			r.add("ID_PERMOHONAN", idPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPJEMAAHMENTERI");
			stmt.executeUpdate(sql);
			
			Long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("1",idSuburusan,"=");

			
//			if (idSuburusan != null && idSuburusan.trim().length() != 0){
//				//TBLRUJSUBURUSANSTATUSFAIL
				r = new SQLRenderer();
				long idSuburusanstatusfail = DB.getNextID(db, "TBLRUJSUBURUSANSTATUSFAIL_SEQ");
				r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
				r.add("ID_PERMOHONAN", idPermohonan);
//				if ("7".equals(idSuburusan)){
//					r.add("ID_SUBURUSANSTATUS", "15"); //PENERIMAAN PERMOHONAN
//				} else if ("17".equals(idSuburusan)){
//					r.add("ID_SUBURUSANSTATUS", "186"); //PENERIMAAN PERMOHONAN
//				} else if ("18".equals(idSuburusan)){
//				}				

				r.add("ID_SUBURUSANSTATUS", r.unquote(String.valueOf(setIdSuburusanstatus)));
				r.add("AKTIF", "1");	
				r.add("ID_FAIL", idFail);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//
				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);
//			}
			
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
		return idFailString;
	}
	
	public String generateNoFail(String idJenisFail,String kodUrusan, String kodKementerian, String idKementerian, String kodMampu, String idNegeri) throws Exception{
		String noFail = "";
		//noFail = "JKPTG/101/" + kodUrusan + "/" + kodKementerian + "/" +  kodMampu + "-" + String.format("%06d", File.getSeqNo(3, 3, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri)));
		// Modified by Rosli 01/04/2010 
		//myLog.info("generateNoFail:"+idJenisFail);
		if(idJenisFail.equals("3"))
			noFail = "JKPTG(S)/101/" + kodUrusan + "/" + kodKementerian + "/" +  kodMampu + "-" + File.getSeqNo(3, 4, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));
		else	
			noFail = "JKPTG/101/" + kodUrusan + "/" + kodKementerian + "/" +  kodMampu + "-" + File.getSeqNo(3, 4, Integer.parseInt(idKementerian), Integer.parseInt(idNegeri));

		return noFail;
	
	}
	
	public String getKodMampu(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();			
			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '" + idNegeri + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				return rs.getString("KOD_MAMPU").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getKodKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();			
			sql = "SELECT KOD_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '" + idKementerian + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				return rs.getString("KOD_KEMENTERIAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdPermohonanbyIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();			
			sql = "SELECT ID_PERMOHONAN FROM TBLPERMOHONAN WHERE ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				return rs.getString("ID_PERMOHONAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getIdHTPPermohonanbyIdFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();			
			sql = "SELECT B.ID_HTPPERMOHONAN FROM TBLPERMOHONAN A, TBLHTPPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '" + idFail + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				return rs.getString("ID_HTPPERMOHONAN").toString();
			} else {
				return "";
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPermohonan(String idFail) throws Exception {
		Db db = null;
//		String sql = "";
		try {
			beanMaklumatPermohonan = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, D.ID_NEGERI, D.NAMA_NEGERI, E.ID_KEMENTERIAN, E.NAMA_KEMENTERIAN, F.ID_AGENSI, F.NAMA_AGENSI, A.ID_SUBURUSAN, G.NAMA_SUBURUSAN, H.ID_JENISTANAH, H.KETERANGAN, I.ID_TARAFKESELAMATAN, I.TARAF_KESELAMATAN, A.NO_FAIL,"
				+ " C.NO_RUJUKAN_KJP, B.TARIKH_SURAT, C.NO_RUJUKAN_LAIN, C.TARIKH_AGIHAN, A.TAJUK_FAIL, B.ID_STATUS"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLHTPPERMOHONAN C, TBLRUJNEGERI D, TBLRUJKEMENTERIAN E, TBLRUJAGENSI F, TBLRUJSUBURUSAN G, TBLRUJJENISTANAH H, TBLPFDRUJTARAFKESELAMATAN I"
				+ " WHERE B.ID_PERMOHONAN = C.ID_PERMOHONAN AND B.ID_FAIL = A.ID_FAIL AND A.ID_NEGERI = D.ID_NEGERI AND A.ID_KEMENTERIAN = E.ID_KEMENTERIAN AND C.ID_AGENSI = F.ID_AGENSI AND"
				+ " A.ID_SUBURUSAN = G.ID_SUBURUSAN AND C.ID_JENISTANAH = H.ID_JENISTANAH AND A.ID_TARAFKESELAMATAN = I.ID_TARAFKESELAMATAN"
				+ " AND A.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);	
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("idSuburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN").toUpperCase());
				h.put("namaSubUrusan", rs.getString("NAMA_SUBURUSAN") == null ? "" : rs.getString("NAMA_SUBURUSAN").toUpperCase());
				h.put("idStatusTanah", rs.getString("ID_JENISTANAH") == null ? "" : rs.getString("ID_JENISTANAH").toUpperCase());
				h.put("statusTanah", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				h.put("idJenisFail", rs.getString("ID_TARAFKESELAMATAN") == null ? "" : rs.getString("ID_TARAFKESELAMATAN").toUpperCase());
				h.put("jenisFail", rs.getString("TARAF_KESELAMATAN") == null ? "" : rs.getString("TARAF_KESELAMATAN").toUpperCase());
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("noFailKJP", rs.getString("NO_RUJUKAN_KJP") == null ? "" : rs.getString("NO_RUJUKAN_KJP").toUpperCase());
				h.put("tarikhSuratKJP", rs.getDate("TARIKH_SURAT") == null ? "" : sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noFailLain", rs.getString("NO_RUJUKAN_LAIN") == null ? "" : rs.getString("NO_RUJUKAN_LAIN").toUpperCase());
				h.put("tarikhAgihan", rs.getDate("TARIKH_AGIHAN") == null ? "" : sdf.format(rs.getDate("TARIKH_AGIHAN")));
				h.put("tajuk", rs.getString("TAJUK_FAIL") == null ? "" : rs.getString("TAJUK_FAIL").toUpperCase());
				beanMaklumatPermohonan.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void saveUpdate(String idFail, String noFailKJP, String tarikhSuratKJP, String noFailLain, String tarikhAgihan, String tajuk, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String TKJP = "to_date('" + tarikhSuratKJP + "','dd/MM/yyyy')";
			String TA = "to_date('" + tarikhAgihan + "','dd/MM/yyyy')";

			//TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", tajuk);
			
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", getIdPermohonanbyIdFail(idFail));
			if (!"".equals(tarikhSuratKJP)){
				r.add("TARIKH_SURAT", r.unquote(TKJP));
			}			
			r.add("TUJUAN", tajuk);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			//TBLHTPPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_HTPPERMOHONAN", getIdHTPPermohonanbyIdFail(idFail));
			r.add("NO_RUJUKAN_KJP", noFailKJP);
			r.add("NO_RUJUKAN_LAIN", noFailLain);
			if (!"".equals(tarikhAgihan)){
				r.add("TARIKH_AGIHAN", r.unquote(TA));
			}	

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPPERMOHONAN");
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

	
	//Penambahbaikan. Syaz. 01122014. Untuk function pengesahan (2 layer)
	/**
	 * N = BARU SAVE
	 * H = HANTAR
	 * S = SAH (TELAH DISAHKAN)
	 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
	 */
	public void saveHantarPengesahan(String idFail,HttpSession session,String statusSah) throws Exception {		
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
	
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", getIdPermohonanbyIdFail(idFail));
			r.add("FLAG_MOHON_FAIL", statusSah);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah kemaskini flag pengesahan " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		
	}//close
	
	
	public Vector<Hashtable<String,String>> getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector<Hashtable<String,String>> senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public void setBeanMaklumatPermohonan(Vector<Hashtable<String,String>> beanMaklumatPermohonan) {
		this.beanMaklumatPermohonan = beanMaklumatPermohonan;
	}


}

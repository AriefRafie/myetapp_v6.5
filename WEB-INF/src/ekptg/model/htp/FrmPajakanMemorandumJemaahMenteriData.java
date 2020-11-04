/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.apache.openjpa.lib.conf.StringValue;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujsuburusanstatusfail;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPajakanMemorandumJemaahMenteriData {

	private static Logger myLog = Logger.getLogger(ekptg.model.htp.FrmPajakanMemorandumJemaahMenteriData.class);
	private Db db = null;
	private Connection conn = null;

	private Vector<Hashtable<String,String>> senaraiFail = null;
	private Vector<Hashtable<String, String>> pemohonPajakan = null;
	private Vector<Hashtable<String, String>> memoJemaahMenteri = null;
	private Vector<Hashtable<String, String>> perakuanJawatankuasa = null;
	private Vector<Hashtable<String,String>> ulasanKJP = null;
	private Vector<Hashtable<String, String>> senaraiUlasanJPPH = null;
	private Vector<Hashtable<String, String>> beanMaklumatUlasanJPPH = null;
	private Vector<Hashtable<String, String>> senaraiDraf = null;
	private Vector<Hashtable<String,String>> beanMaklumatDraf = null;
	private Vector<Hashtable<String, String>> senaraiUlasanKJP = null;
	private Vector<Hashtable<String, String>> senaraiUlasanSPHP = null;
	private Vector<Hashtable<String, String>> beanMaklumatUlasanSPHP = null;
	private IHTPStatus iStatus = null;
	private IHtp iHTP = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";

	public void carianFail(String noFail, String tarikhTerima) throws Exception {
		try {
			senaraiFail = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18) AND B.ID_STATUS IN (12, 63, 65)";

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
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd/MON//YYYY') = '" + sdf.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
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

	public void setMaklumatPemohonPajakan(String idPermohonan) throws Exception {
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			pemohonPajakan = new Vector<Hashtable<String, String>>();

			sql = "SELECT A.ID_PEMOHON, A.ID_PERMOHONAN, A.NO_PEMOHON, A.NAMA_PEMOHON, ";
			sql += "A.ALAMAT_PEMOHON1, A.ALAMAT_PEMOHON2, A.ALAMAT_PEMOHON3, ";
			sql += "A.POSKOD, A.ID_NEGERI, A.ID_DAERAH,A.ID_BANDAR, A.NO_TEL, A.NO_FAX, A.EMEL ";
			sql += "FROM TBLHTPPEMOHON A ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noPemohon", rs.getString("NO_PEMOHON") == null ? "" : rs.getString("NO_PEMOHON"));
				h.put("nama", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("alamat1", rs.getString("ALAMAT_PEMOHON1") == null ? "" : rs.getString("ALAMAT_PEMOHON1"));
				h.put("alamat2", rs.getString("ALAMAT_PEMOHON2") == null ? "" : rs.getString("ALAMAT_PEMOHON2"));
				h.put("alamat3", rs.getString("ALAMAT_PEMOHON3") == null ? "" : rs.getString("ALAMAT_PEMOHON3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "99999" : rs.getString("ID_DAERAH"));
				h.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("idBandar", rs.getString("ID_BANDAR") == null ? "0" : rs.getString("ID_BANDAR"));
				h.put("emel", Utils.isNull(rs.getString("EMEL")));
				pemohonPajakan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public Vector<Hashtable<String, String>> getMaklumatPemohonPajakan(String idPermohonan) throws Exception {
		pemohonPajakan = new Vector<Hashtable<String, String>>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEMOHON, A.ID_PERMOHONAN, A.NO_PEMOHON, A.NAMA_PEMOHON, ";
			sql += "A.ALAMAT_PEMOHON1, A.ALAMAT_PEMOHON2, A.ALAMAT_PEMOHON3, ";
			sql += "A.POSKOD, A.ID_NEGERI, A.ID_DAERAH,A.ID_BANDAR, A.NO_TEL, A.NO_FAX, A.EMEL ";
			sql += "FROM TBLHTPPEMOHON A ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			while(rs.next()){
				h = new Hashtable<String, String>();
				h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noPemohon", rs.getString("NO_PEMOHON") == null ? "" : rs.getString("NO_PEMOHON"));
				h.put("nama", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
				h.put("alamat1", rs.getString("ALAMAT_PEMOHON1") == null ? "" : rs.getString("ALAMAT_PEMOHON1"));
				h.put("alamat2", rs.getString("ALAMAT_PEMOHON2") == null ? "" : rs.getString("ALAMAT_PEMOHON2"));
				h.put("alamat3", rs.getString("ALAMAT_PEMOHON3") == null ? "" : rs.getString("ALAMAT_PEMOHON3"));
				h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "99999" : rs.getString("ID_DAERAH"));
				h.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
				h.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
				h.put("idBandar", rs.getString("ID_BANDAR") == null ? "0" : rs.getString("ID_BANDAR"));
				h.put("emel", Utils.isNull(rs.getString("EMEL")));
				pemohonPajakan.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();

		}
		return pemohonPajakan;

	}

	public void updatePemohon(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPPEMOHON
			if(isMaklumatPemohonPajakan(idPermohonan)){
				r.update("ID_PERMOHONAN", idPermohonan);

				r.add("NAMA_PEMOHON", hash.get("nama"));
				r.add("ALAMAT_PEMOHON1", hash.get("alamat1"));
				r.add("ALAMAT_PEMOHON2", hash.get("alamat2"));
				r.add("ALAMAT_PEMOHON3", hash.get("alamat3"));
				r.add("POSKOD", hash.get("poskod"));
				r.add("ID_DAERAH", FrmGadaianHakmilikData.getDaerahMengikutBandar(String.valueOf(hash.get("idBandar"))));
				r.add("ID_NEGERI", hash.get("idNegeri"));
				r.add("NO_TEL", hash.get("noTel"));
				r.add("NO_FAX", hash.get("noFax"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				r.add("NO_PEMOHON", hash.get("noRujukan"));
				r.add("ID_BANDAR", hash.get("idBandar"));
				r.add("EMEL", hash.get("email"));
				sql = r.getSQLUpdate("TBLHTPPEMOHON");
				//myLog.info("updatePemohon:sql="+sql);
				stmt.executeUpdate(sql);

			}else{
				//TBLHTPPEMOHON
				long idPemohon = DB.getNextID("TBLHTPPEMOHON_SEQ");
				r.add("ID_PEMOHON", idPemohon);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("NAMA_PEMOHON", hash.get("nama"));
				r.add("ALAMAT_PEMOHON1", hash.get("alamat1"));
				r.add("ALAMAT_PEMOHON2", hash.get("alamat2"));
				r.add("ALAMAT_PEMOHON3", hash.get("alamat3"));
				r.add("POSKOD", hash.get("poskod"));
				r.add("ID_DAERAH", FrmGadaianHakmilikData.getDaerahMengikutBandar(String.valueOf(hash.get("idBandar"))));
				r.add("ID_NEGERI", hash.get("idNegeri"));
				r.add("NO_TEL", hash.get("noTel"));
				r.add("NO_FAX", hash.get("noFax"));
				r.add("NO_PEMOHON", hash.get("noRujukan"));
				r.add("ID_BANDAR", hash.get("idBandar"));
				r.add("EMEL", hash.get("email"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLHTPPEMOHON");
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

	public void setMaklumatMemorandumJemaahMenteri(String idPermohonan) throws Exception{
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			memoJemaahMenteri = new Vector<Hashtable<String, String>>();

			sql = "SELECT A.ID_JEMAAHMENTERI, A.ID_PERMOHONAN, A.TARIKH_HANTAR_DASAR, ";
			sql += "A.TARIKH_TERIMA, A.TARIKH_HANTAR_KSU, ";
			sql += "A.TARIKH_MSYRT_JEMAAH, A.TARIKH_TERIMA_KSU, A.NO_MEMORANDUM, ";
			sql += "A.STATUS_KEPUTUSAN, A.TINDAKAN_LANJUT, A.TARIKH_HANTAR_PEMOHON ";
			sql += "FROM TBLHTPJEMAAHMENTERI a ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			//myLog.info("setMaklumatMemorandumJemaahMenteri:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			while(rs.next()){
				h = new Hashtable<String, String>();
				h.put("idJemaahMenteri", rs.getString("ID_JEMAAHMENTERI") == null ? "" : rs.getString("ID_JEMAAHMENTERI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhHantarPTP", rs.getDate("TARIKH_HANTAR_DASAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DASAR")));
				h.put("tarikhTerimaPTP", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarKSU", rs.getDate("TARIKH_HANTAR_KSU") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_KSU")));
				h.put("tarikhMesyuaratJM", rs.getDate("TARIKH_MSYRT_JEMAAH") == null ? "" : sdf.format(rs.getDate("TARIKH_MSYRT_JEMAAH")));
				h.put("tarikhTerimaKeputusan", rs.getDate("TARIKH_TERIMA_KSU") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA_KSU")));
				h.put("noMemorandum", rs.getString("NO_MEMORANDUM") == null ? "" : rs.getString("NO_MEMORANDUM"));
				h.put("keputusan", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				h.put("tindakanLanjut", rs.getString("TINDAKAN_LANJUT") == null ? "" : rs.getString("TINDAKAN_LANJUT"));
				h.put("tarikhMaklumanKeputusan", rs.getDate("TARIKH_HANTAR_PEMOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_PEMOHON")));

				memoJemaahMenteri.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public Vector<Hashtable<String, String>> getMaklumatMemorandumJemaahMenteri(String idPermohonan) throws Exception{
		memoJemaahMenteri = new Vector<Hashtable<String, String>>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_JEMAAHMENTERI, A.ID_PERMOHONAN, A.TARIKH_HANTAR_DASAR, ";
			sql += "A.TARIKH_TERIMA, A.TARIKH_HANTAR_KSU, ";
			sql += "A.TARIKH_MSYRT_JEMAAH, A.TARIKH_TERIMA_KSU, A.NO_MEMORANDUM, ";
			sql += "A.STATUS_KEPUTUSAN, A.TINDAKAN_LANJUT, A.TARIKH_HANTAR_PEMOHON ";
			sql += "FROM TBLHTPJEMAAHMENTERI a ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			//myLog.info("setMaklumatMemorandumJemaahMenteri:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			while(rs.next()){
				h = new Hashtable<String, String>();
				h.put("idJemaahMenteri", rs.getString("ID_JEMAAHMENTERI") == null ? "" : rs.getString("ID_JEMAAHMENTERI"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tarikhHantarPTP", rs.getDate("TARIKH_HANTAR_DASAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DASAR")));
				h.put("tarikhTerimaPTP", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarKSU", rs.getDate("TARIKH_HANTAR_KSU") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_KSU")));
				h.put("tarikhMesyuaratJM", rs.getDate("TARIKH_MSYRT_JEMAAH") == null ? "" : sdf.format(rs.getDate("TARIKH_MSYRT_JEMAAH")));
				h.put("tarikhTerimaKeputusan", rs.getDate("TARIKH_TERIMA_KSU") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA_KSU")));
				h.put("noMemorandum", rs.getString("NO_MEMORANDUM") == null ? "" : rs.getString("NO_MEMORANDUM"));
				h.put("keputusan", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				h.put("tindakanLanjut", rs.getString("TINDAKAN_LANJUT") == null ? "" : rs.getString("TINDAKAN_LANJUT"));
				h.put("tarikhMaklumanKeputusan", rs.getDate("TARIKH_HANTAR_PEMOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_PEMOHON")));

				memoJemaahMenteri.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return memoJemaahMenteri;

	}

	public void setMaklumatPerakuanJawatankuasa(String idPermohonan) throws Exception{
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			perakuanJawatankuasa = new Vector<Hashtable<String, String>>();

			sql = "SELECT A.ID_PERAKUANJAWATANKUASA, A.ID_PERMOHONAN, ";
			sql += "A.STATUS_PERAKUAN, A.ULASAN, A.TARIKH_PERAKUAN ";
			sql += "FROM TBLHTPPERAKUANJAWATANKUASA a ";
			sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
			myLog.info("setMaklumatPerakuanJawatankuasa:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			while(rs.next()){
				h = new Hashtable<String, String>();
				h.put("idPeraku", rs.getString("ID_PERAKUANJAWATANKUASA") == null ? "" : rs.getString("ID_PERAKUANJAWATANKUASA"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("keputusan", rs.getString("STATUS_PERAKUAN") == null ? "" : rs.getString("STATUS_PERAKUAN"));
				h.put("ulasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				h.put("tarikhMaklumanKeputusan", rs.getDate("TARIKH_PERAKUAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERAKUAN")));

				perakuanJawatankuasa.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public Vector<Hashtable<String, String>> getMaklumatPerakuanJawatankuasa(String idPermohonan) throws Exception{
		perakuanJawatankuasa = new Vector<Hashtable<String, String>>();
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERAKUANJAWATANKUASA, A.ID_PERMOHONAN, ";
			sql += " A.STATUS_PERAKUAN, A.ULASAN, A.TARIKH_PERAKUAN, ";
			sql += " NVL((SELECT NAMA_FAIL FROM TBLHTPPERAKUANATTACH ";
			sql += " WHERE ID_PERAKUANJAWATANKUASA=A.ID_PERAKUANJAWATANKUASA ) ,0) LAMPIRAN";
			sql += " FROM TBLHTPPERAKUANJAWATANKUASA A ";
			sql += " WHERE A.ID_PERMOHONAN = " + idPermohonan;
			myLog.info("getMaklumatPerakuanJawatankuasa:sql="+sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			while(rs.next()){
				h = new Hashtable<String, String>();
				h.put("idPerakuan", rs.getString("ID_PERAKUANJAWATANKUASA") == null ? "" : rs.getString("ID_PERAKUANJAWATANKUASA"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("keputusan", rs.getString("STATUS_PERAKUAN") == null ? "" : rs.getString("STATUS_PERAKUAN"));
				h.put("ulasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				h.put("tarikhMaklumanKeputusan", rs.getDate("TARIKH_PERAKUAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERAKUAN")));
				h.put("lampiran", Utils.isNull(rs.getString("LAMPIRAN")));

				perakuanJawatankuasa.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return perakuanJawatankuasa;

	}

	public void updateMemo(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//TBLHTPJEMAAHMENTERI
			if(isMaklumatMemorandumJemaahMenteri(idPermohonan)){
				r.update("ID_PERMOHONAN", idPermohonan);

				if (!"".equals(hash.get("tarikhTHPTP"))){
					r.add("TARIKH_HANTAR_DASAR", r.unquote("to_date('" + hash.get("tarikhTHPTP") + "','dd/MM/yyyy')"));
				}else{
					r.add("TARIKH_HANTAR_DASAR", r.unquote("to_date('01/01/1900','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTTPTP"))){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTTPTP") + "','dd/MM/yyyy')"));
				}else{
					r.add("TARIKH_TERIMA", r.unquote("to_date('01/01/1900','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTHTUP"))){
					r.add("TARIKH_HANTAR_KSU", r.unquote("to_date('" + hash.get("tarikhTHTUP") + "','dd/MM/yyyy')"));
				}else{
					r.add("TARIKH_HANTAR_KSU", r.unquote("to_date('01/01/1900','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTMJM"))){
					r.add("TARIKH_MSYRT_JEMAAH", r.unquote("to_date('" + hash.get("tarikhTMJM") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTTK"))){
					r.add("TARIKH_TERIMA_KSU", r.unquote("to_date('" + hash.get("tarikhTTK") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhHantarPemohon"))){
					r.add("TARIKH_HANTAR_PEMOHON", r.unquote("to_date('" + hash.get("tarikhHantarPemohon") + "','dd/MM/yyyy')"));
				}else{
					r.add("TARIKH_HANTAR_PEMOHON", r.unquote("to_date('01/01/1900','dd/MM/yyyy')"));
				}

				r.add("NO_MEMORANDUM", hash.get("noMemo"));
				r.add("STATUS_KEPUTUSAN", hash.get("keputusan"));
				r.add("TINDAKAN_LANJUT", hash.get("tindakanLanjut"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLHTPJEMAAHMENTERI");
				myLog.info(sql);
				stmt.executeUpdate(sql);
				conn.commit();

				if(String.valueOf(hash.get("keputusan")).equals("L")){
					Long setIdStatus = 0L;
					setIdStatus = FrmUtilData.getIdStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(Long.parseLong(idPermohonan));
					s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					s.setAktif("1");
					s.setIdMasuk(Long.parseLong(userId));
					s.setIdKemaskini(Long.parseLong(userId));
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);

					Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
					Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
					sBaru.setIdPermohonan(Long.parseLong(idPermohonan));
					sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);

				}
				if(String.valueOf(hash.get("keputusan")).equals("TL")){
					Long setIdStatus = 0L;
					setIdStatus = FrmUtilData.getIdStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(Long.parseLong(idPermohonan));
					s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					s.setAktif("1");
					s.setIdMasuk(Long.parseLong(userId));
					s.setIdKemaskini(Long.parseLong(userId));
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);

					Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
					Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
					sBaru.setIdPermohonan(Long.parseLong(idPermohonan));
					sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);

				}

			}else{
				long idJemaahMenteri = DB.getNextID("TBLHTPJEMAAHMENTERI_SEQ");
				r.add("ID_JEMAAHMENTERI", idJemaahMenteri);
				r.add("ID_PERMOHONAN", idPermohonan);
				if (!"".equals(hash.get("tarikhTHPTP"))){
					r.add("TARIKH_HANTAR_DASAR", r.unquote("to_date('" + hash.get("tarikhTHPTP") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTTPTP"))){
					r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTTPTP") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTHTUP"))){
					r.add("TARIKH_HANTAR_KSU", r.unquote("to_date('" + hash.get("tarikhTHTUP") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTMJM"))){
					r.add("TARIKH_MSYRT_JEMAAH", r.unquote("to_date('" + hash.get("tarikhTMJM") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhTTK"))){
					r.add("TARIKH_TERIMA_KSU", r.unquote("to_date('" + hash.get("tarikhTTK") + "','dd/MM/yyyy')"));
				}
				if (!"".equals(hash.get("tarikhHantarPemohon"))){
					r.add("TARIKH_HANTAR_PEMOHON", r.unquote("to_date('" + hash.get("tarikhHantarPemohon") + "','dd/MM/yyyy')"));
				}

				r.add("NO_MEMORANDUM", hash.get("noMemo"));
				r.add("STATUS_KEPUTUSAN", hash.get("keputusan"));
				r.add("TINDAKAN_LANJUT", hash.get("tindakanLanjut"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLHTPJEMAAHMENTERI");
				myLog.info("INSERT:sql="+sql);
				stmt.executeUpdate(sql);
				conn.commit();

				if(String.valueOf(hash.get("keputusan")).equals("L")){
					Long setIdStatus = 0L;
					setIdStatus = FrmUtilData.getIdStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(Long.parseLong(idPermohonan));
					s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					s.setAktif("1");
					s.setIdMasuk(Long.parseLong(userId));
					s.setIdKemaskini(Long.parseLong(userId));
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);

					Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
					Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
					sBaru.setIdPermohonan(Long.parseLong(idPermohonan));
					sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);

				}
				if(String.valueOf(hash.get("keputusan")).equals("TL")){
					Long setIdStatus = 0L;
					setIdStatus = FrmUtilData.getIdStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
					Long setIdSuburusanstatus = 0L;
					setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
					Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
					s.setIdPermohonan(Long.parseLong(idPermohonan));
					s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					s.setAktif("1");
					s.setIdMasuk(Long.parseLong(userId));
					s.setIdKemaskini(Long.parseLong(userId));
					s.setIdSuburusanstatus(setIdSuburusanstatus);
					s.setUrl("-");
					getStatus().simpanKemaskiniStatus(s,setIdStatus);

					Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
					Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
					sBaru.setIdPermohonan(Long.parseLong(idPermohonan));
					sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
					sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
					getStatus().hapusSubUrusanStatusPermohonan(sBaru);

				}

			}

		} catch (SQLException ex) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		//throw new Exception("Rollback error : " + e.getMessage());
				getIHTP().getErrorHTML(ex.toString());
	    	}
			getIHTP().getErrorHTML(ex.toString());
	    	//throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

	    } finally {
			if (db != null)
				db.close();
		}

	}

	public String updateMaklumatPerakuanJawatankuasa(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String returnValue = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//TBLHTPJEMAAHMENTERI
			if(isMaklumatPerakuanJawatankuasa(idPermohonan)){

				returnValue = hash.get("idPerakuan");
				r.update("ID_PERMOHONAN", idPermohonan);

				if (!"".equals(hash.get("tarikhHantarPemohon"))){
					r.add("TARIKH_PERAKUAN", r.unquote("to_date('" + hash.get("tarikhPerakuan") + "','dd/MM/yyyy')"));
				}else{
					r.add("TARIKH_PERAKUAN", r.unquote("to_date('01/01/1900','dd/MM/yyyy')"));
				}

				r.add("STATUS_PERAKUAN", hash.get("keputusan"));
				r.add("ULASAN", hash.get("tindakanLanjut"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLHTPPERAKUANJAWATANKUASA");
				myLog.info("UPDATE MAKLUMAT PERAKUAN:sql="+sql);
				stmt.executeUpdate(sql);
				conn.commit();
			}else{
				long idPerakuanJawatankuasa = DB.getNextID("TBLHTPPERAKUANJAWATANKUASA_SEQ");
				returnValue = String.valueOf(idPerakuanJawatankuasa);
				r.add("ID_PERAKUANJAWATANKUASA", idPerakuanJawatankuasa);
				r.add("ID_PERMOHONAN", idPermohonan);
				if (!"".equals(hash.get("tarikhHantarPemohon"))){
					r.add("TARIKH_PERAKUAN", r.unquote("to_date('" + hash.get("tarikhPerakuan") + "','dd/MM/yyyy')"));
				}

				r.add("STATUS_PERAKUAN", hash.get("keputusan"));
				r.add("ULASAN", hash.get("tindakanLanjut"));
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLHTPPERAKUANJAWATANKUASA");
				myLog.info("INSERT MAKLUMAT PERAKUAN:sql="+sql);
				stmt.executeUpdate(sql);
				conn.commit();
			}

		} catch (SQLException ex) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		//throw new Exception("Rollback error : " + e.getMessage());
				getIHTP().getErrorHTML(ex.toString());
	    	}
			getIHTP().getErrorHTML(ex.toString());
	    	//throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());

	    } finally {
			if (db != null)
				db.close();
		}
		return returnValue;

	}

	public void setListUlasanKJP(String idPermohonan)throws Exception{
		Hashtable<String, String> hashListUlasanKJP;
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_ULASANKJP, A.ID_PERMOHONAN,A.NO_RUJUKAN, ";
			sql += "A.TARIKH_HANTAR, A.TARIKH_TERIMA, A.ULASAN, A.STATUS_KEPUTUSAN " +
					" , "+
					" (case "+
					"    when A.STATUS_KEPUTUSAN='S' THEN 'SETUJU' "+
					"    when A.STATUS_KEPUTUSAN='TS' THEN 'TIDAK SETUJU' "+
					"    else '' "+
					" end) KEPUTUSAN ";
			sql += "FROM TBLHTPULASANKJP A  ";
			sql += "WHERE A.ID_PERMOHONAN ='" + idPermohonan + "'";
			myLog.info("setListUlasanKJP : " + sql);
			ResultSet rs = stmt.executeQuery(sql);
			senaraiUlasanKJP = new Vector<Hashtable<String, String>>();
			int bil = 1;
			while(rs.next()){
				hashListUlasanKJP = new Hashtable<String, String>();
				hashListUlasanKJP.put("bil", String.valueOf(bil));
				hashListUlasanKJP.put("idUlasanKJP", rs.getString("ID_ULASANKJP") == null ? "" : rs.getString("ID_ULASANKJP"));
				hashListUlasanKJP.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				hashListUlasanKJP.put("noRujukan", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				hashListUlasanKJP.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				hashListUlasanKJP.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				hashListUlasanKJP.put("ulasanKJP", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				hashListUlasanKJP.put("status", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				hashListUlasanKJP.put("statusName",Utils.isNull(rs.getString("KEPUTUSAN")));
				senaraiUlasanKJP.addElement(hashListUlasanKJP);
				bil++;
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void setMaklumatUlasanKJP(String idUlasanKJP) throws Exception{
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			ulasanKJP = new Vector<Hashtable<String,String>>();

			sql = "SELECT A.ID_ULASANKJP, A.ID_PERMOHONAN, A.TARIKH_HANTAR, A.NO_RUJUKAN, ";
			sql += "A.TARIKH_TERIMA, A.ULASAN, A.STATUS_KEPUTUSAN ";
			sql += "FROM TBLHTPULASANKJP A ";
			sql += "WHERE A.ID_ULASANKJP = " + idUlasanKJP;
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h = null;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idUlasanKJP", rs.getString("ID_ULASANKJP") == null ? "" : rs.getString("ID_ULASANKJP"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noRujukan", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("ulasanKJP", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				h.put("status", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));

				if(rs.getString("STATUS_KEPUTUSAN").equalsIgnoreCase("S")){
					h.put("statusName", "SETUJU");
				}else{
					h.put("statusName", "TIDAK SETUJU");
				}
				ulasanKJP.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void saveUlasanKJP(String idPermohonan, Hashtable<String,String> hash, HttpSession session)throws Exception{
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try{
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//TBLHTPULASANKJP
			long idUlasanKJP = DB.getNextID("TBLHTPULASANKJP_SEQ");
			r.add("ID_ULASANKJP",idUlasanKJP);
			r.add("ID_PERMOHONAN",idPermohonan);

			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}

			r.add("ULASAN", hash.get("ulasan"));
			r.add("NO_RUJUKAN", hash.get("noRujukanKJP"));
			r.add("STATUS_KEPUTUSAN", hash.get("status"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPULASANKJP");
			myLog.info("saveUlasanKJP : sql=" + sql);
			stmt.executeQuery(sql);
//			if(String.valueOf(hash.get("status")).equals("TS")){
//				Long setIdStatus = 0L;
//				setIdStatus = FrmUtilData.getIdStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
//				Long setIdSuburusanstatus = 0L;
//				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
//				Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
//				s.setIdPermohonan(Long.parseLong(idPermohonan));
//				s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
//				s.setAktif("1");
//				s.setIdMasuk(Long.parseLong(userId));
//				s.setIdKemaskini(Long.parseLong(userId));
//				s.setIdSuburusanstatus(setIdSuburusanstatus);
//				s.setUrl("-");
//				getStatus().simpanKemaskiniStatus(s,setIdStatus);
//
//			}
			myLog.info("1S");
			if(String.valueOf(hash.get("status")).equals("S")){
				Long setIdStatus = 0L;
				setIdStatus = FrmUtilData.getIdStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
				Long setIdSuburusanstatus = 0L;
				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
				Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
				//s.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				s.setIdPermohonan(Long.parseLong(String.valueOf(idPermohonan)));
				s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				s.setAktif("1");
				s.setIdMasuk(Long.parseLong(userId));
				s.setIdKemaskini(Long.parseLong(userId));
				s.setIdSuburusanstatus(setIdSuburusanstatus);
				s.setUrl("-");
				getStatus().simpanKemaskiniStatus(s,setIdStatus);

				Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
				Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
				//sBaru.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				sBaru.setIdPermohonan(Long.parseLong(String.valueOf(idPermohonan)));
				sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
				getStatus().hapusSubUrusanStatusPermohonan(sBaru);

			}
			myLog.info("2TS");
			if(String.valueOf(hash.get("status")).equals("TS")){
				Long setIdStatus = 0L;
				setIdStatus = FrmUtilData.getIdStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
				Long setIdSuburusanstatus = 0L;
				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
				Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
				//s.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				s.setIdPermohonan(Long.parseLong(String.valueOf(idPermohonan)));
				s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				s.setAktif("1");
				s.setIdMasuk(Long.parseLong(userId));
				s.setIdKemaskini(Long.parseLong(userId));
				s.setIdSuburusanstatus(setIdSuburusanstatus);
				s.setUrl("-");
				getStatus().simpanKemaskiniStatus(s,setIdStatus);

				Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
				Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
				//sBaru.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				sBaru.setIdPermohonan(Long.parseLong(String.valueOf(idPermohonan)));
				sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
				getStatus().hapusSubUrusanStatusPermohonan(sBaru);

			}
			conn.commit();

		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();

		}

	}

	public void updateUlasanKJP(String idUlasanKJP, Hashtable <String,String>hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//TBLHTPULASANKJP
			r.update("ID_ULASANKJP", idUlasanKJP);

			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}

			r.add("ULASAN", hash.get("ulasan"));
			r.add("NO_RUJUKAN", hash.get("noRujukanKJP"));
			r.add("STATUS_KEPUTUSAN", hash.get("status"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPULASANKJP");
			stmt.executeUpdate(sql);

//			if(String.valueOf(hash.get("status")).equals("TS")){
//				Long setIdStatus = 0L;
//				setIdStatus = FrmUtilData.getIdStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
//				Long setIdSuburusanstatus = 0L;
//				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
//				Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
//				s.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
//				s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
//				s.setAktif("1");
//				s.setIdMasuk(Long.parseLong(userId));
//				s.setIdKemaskini(Long.parseLong(userId));
//				s.setIdSuburusanstatus(setIdSuburusanstatus);
//				s.setUrl("-");
//				getStatus().simpanKemaskiniStatus(s,setIdStatus);
//
//			}
			if(String.valueOf(hash.get("status")).equals("S")){
				Long setIdStatus = 0L;
				setIdStatus = FrmUtilData.getIdStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
				Long setIdSuburusanstatus = 0L;
				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
				Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
				s.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				s.setAktif("1");
				s.setIdMasuk(Long.parseLong(userId));
				s.setIdKemaskini(Long.parseLong(userId));
				s.setIdSuburusanstatus(setIdSuburusanstatus);
				s.setUrl("-");
				getStatus().simpanKemaskiniStatus(s,setIdStatus);

				Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
				Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
				sBaru.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
				getStatus().hapusSubUrusanStatusPermohonan(sBaru);

			}
			if(String.valueOf(hash.get("status")).equals("TS")){
				Long setIdStatus = 0L;
				setIdStatus = FrmUtilData.getIdStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
				Long setIdSuburusanstatus = 0L;
				setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("6",String.valueOf(hash.get("idSubUrusan")),"=");
				Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
				s.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				s.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				s.setAktif("1");
				s.setIdMasuk(Long.parseLong(userId));
				s.setIdKemaskini(Long.parseLong(userId));
				s.setIdSuburusanstatus(setIdSuburusanstatus);
				s.setUrl("-");
				getStatus().simpanKemaskiniStatus(s,setIdStatus);

				Tblrujsuburusanstatusfail sBaru = new Tblrujsuburusanstatusfail();
				Long setIdSuburusanStatusBaru = FrmUtilData.getIdSuburusanStatusByLangkah("7",String.valueOf(hash.get("idSubUrusan")),"=");
				sBaru.setIdPermohonan(Long.parseLong(String.valueOf(hash.get("idPermohonan"))));
				sBaru.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
				sBaru.setIdSuburusanstatus(setIdSuburusanStatusBaru);
				getStatus().hapusSubUrusanStatusPermohonan(sBaru);

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

	public void updateUlasanKJP2(String idUlasanKJP, Hashtable <String,String>hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//TBLHTPULASANKJP
			r.update("ID_ULASANKJP", idUlasanKJP);
			r.add("TARIKH_HANTAR",r.unquote("SYSDATE"));
			r.add("ULASAN", hash.get("ulasan"));
			r.add("NO_RUJUKAN", hash.get("noRujukanKJP"));
			r.add("STATUS_KEPUTUSAN", hash.get("status"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPULASANKJP");
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

	public void hapusKJP(String idUlasanKJP) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANKJP
			r.add("ID_ULASANKJP", idUlasanKJP);
			sql = r.getSQLDelete("TBLHTPULASANKJP");
			stmt.executeUpdate(sql);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

	    } finally {
			if (db != null)
				db.close();
			if(conn != null)
				conn.close();
		}
	}

	public void hapusMJM(String idPermohonan) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANKJP
			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLHTPJEMAAHMENTERI");
			myLog.info(sql);
			stmt.executeUpdate(sql);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

	    } finally {
			if (db != null)
				db.close();
			if(conn != null)
				conn.close();
		}
	}

	public void setListUlasanJPPH(String idPermohonan) throws Exception {
		try {
			senaraiUlasanJPPH = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ULASANTEKNIKAL, B.ID_ULASANNILAI, A.NO_RUJKJT, A.TARIKH_HANTAR, A.TARIKH_TERIMA, B.TAHUN_NILAIAN, B.NILAI_TANAH, B.SYOR_BAYARAN, A.ULASAN"
				+ " FROM TBLHTPULASANTEKNIKAL A, TBLHTPULASANNILAI B WHERE A.ID_ULASANTEKNIKAL = B.ID_ULASANTEKNIKAL AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h = null;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil", String.valueOf(bil));
				h.put("idUlasanTeknikal", rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs.getString("ID_ULASANTEKNIKAL"));
				h.put("idUlasanNilai", rs.getString("ID_ULASANNILAI") == null ? "" : rs.getString("ID_ULASANNILAI"));
				h.put("noRujukan", rs.getString("NO_RUJKJT") == null ? "" : rs.getString("NO_RUJKJT"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tahunNilaian", rs.getString("TAHUN_NILAIAN") == null ? "" : rs.getString("TAHUN_NILAIAN"));
				h.put("nilaian", Utils.format2Decimal(Double.parseDouble(rs.getString("NILAI_TANAH") == null ? "0" : rs.getString("NILAI_TANAH"))));
				h.put("syor", Utils.format2Decimal(Double.parseDouble(rs.getString("SYOR_BAYARAN") == null ? "0" : rs.getString("SYOR_BAYARAN"))));
				h.put("ulasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				senaraiUlasanJPPH.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void saveJPPH(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANTEKNIKAL
			long idUlasanTeknikal = DB.getNextID("TBLHTPULASANTEKNIKAL_SEQ");
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJKJT", hash.get("noRujukan"));
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN", hash.get("ulasan"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPULASANTEKNIKAL");
			stmt.executeUpdate(sql);

			//TBLHTPULASANNILAI
			r = new SQLRenderer();
			long idUlasanNilai = DB.getNextID("TBLHTPULASANNILAI_SEQ");
			r.add("ID_ULASANNILAI", idUlasanNilai);
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("TAHUN_NILAIAN", hash.get("tahunNilaian"));
			r.add("NILAI_TANAH", hash.get("nilaian"));
			r.add("SYOR_BAYARAN", hash.get("syor"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPULASANNILAI");
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

	public void setMaklumatUlasanJPPH(String idUlasanTeknikal) throws Exception {
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatUlasanJPPH = new Vector<Hashtable<String, String>>();

			sql = "SELECT A.ID_ULASANTEKNIKAL, A.NO_RUJKJT, A.TARIKH_HANTAR, A.TARIKH_TERIMA, B.TAHUN_NILAIAN, B.NILAI_TANAH, B.SYOR_BAYARAN, A.ULASAN"
				+ " FROM TBLHTPULASANTEKNIKAL A, TBLHTPULASANNILAI B WHERE A.ID_ULASANTEKNIKAL = B.ID_ULASANTEKNIKAL AND A.ID_ULASANTEKNIKAL = '" + idUlasanTeknikal + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			while(rs.next()){
				h = new Hashtable<String, String>();
				h.put("idUlasanTeknikal", rs.getString("ID_ULASANTEKNIKAL") == null ? "" : rs.getString("ID_ULASANTEKNIKAL"));
				h.put("noRujukan", rs.getString("NO_RUJKJT") == null ? "" : rs.getString("NO_RUJKJT"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tahunNilaian", rs.getString("TAHUN_NILAIAN") == null ? "" : rs.getString("TAHUN_NILAIAN"));
				h.put("nilaian", Utils.format2Decimal(Double.parseDouble(rs.getString("NILAI_TANAH") == null ? "0" : rs.getString("NILAI_TANAH"))));
				h.put("syor", Utils.format2Decimal(Double.parseDouble(rs.getString("SYOR_BAYARAN") == null ? "0" : rs.getString("SYOR_BAYARAN"))));
				h.put("ulasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				beanMaklumatUlasanJPPH.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	public void saveUpdateJPPH(
		String idUlasanTeknikal
		, String idUlasanNilai
		, Hashtable<String, String> hash
		, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANTEKNIKAL
			r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
			r.add("NO_RUJKJT", hash.get("noRujukan"));
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN", hash.get("ulasan"));
			sql = r.getSQLUpdate("TBLHTPULASANTEKNIKAL");
			myLog.info("saveUpdateJPPH:TBLHTPULASANTEKNIKAL::sql="+sql);
			stmt.executeUpdate(sql);

			//TBLHTPULASANNILAI
			r = new SQLRenderer();
			r.update("ID_ULASANNILAI", idUlasanNilai);
			r.add("TAHUN_NILAIAN", hash.get("tahunNilaian"));
			r.add("NILAI_TANAH", hash.get("nilaian"));
			r.add("SYOR_BAYARAN", hash.get("syor"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPULASANNILAI");
			myLog.info("saveUpdateJPPH:TBLHTPULASANNILAI::sql="+sql);
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

	public void hapusJPPH(String idUlasanTeknikal, String idUlasanNilai) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANNILAI
			r.add("ID_ULASANNILAI", idUlasanNilai);

			sql = r.getSQLDelete("TBLHTPULASANNILAI");
			stmt.executeUpdate(sql);

			//TBLHTPULASANTEKNIKAL
			r = new SQLRenderer();
			r.add("ID_ULASANTEKNIKAL", idUlasanTeknikal);

			sql = r.getSQLDelete("TBLHTPULASANTEKNIKAL");
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

	//SPHP
	public void setListUlasanSPHP(String idPermohonan) throws Exception {
		try {
			senaraiUlasanSPHP = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_ULASANSPHP, A.ID_PERMOHONAN, A.TARIKH_HANTAR, ";
			sql += "A.TARIKH_TERIMA, A.ULASAN, A.STATUS_KEPUTUSAN, A.NO_RUJUKAN "+
			" , "+
			" (case "+
			"    when A.STATUS_KEPUTUSAN='S' THEN 'SETUJU' "+
			"    when A.STATUS_KEPUTUSAN='TS' THEN 'TIDAK SETUJU' "+
			"    else '' "+
			" end) KEPUTUSAN ";
			sql += "FROM TBLHTPULASANSPHP A ";
			sql += "WHERE A.ID_PERMOHONAN ='" + idPermohonan +"'";
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil", String.valueOf(bil));
				h.put("idUlasanSPHP", rs.getString("ID_ULASANSPHP") == null ? "" : rs.getString("ID_ULASANSPHP"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noRujukan", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("ulasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				h.put("status", rs.getString("STATUS_KEPUTUSAN")== null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				h.put("statusName",Utils.isNull(rs.getString("KEPUTUSAN")));
				senaraiUlasanSPHP.addElement(h);
				bil++;

			}

		}catch(Exception e){
			e.printStackTrace();

		}finally {
			if (db != null)	db.close();
		}

	}

	public void saveSPHP(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANSPHP
			long idUlasanSPHP = DB.getNextID("TBLHTPULASANSPHP_SEQ");
			r.add("ID_ULASANSPHP", idUlasanSPHP);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("NO_RUJUKAN", hash.get("noRujukan"));
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN", hash.get("ulasan"));
			r.add("STATUS_KEPUTUSAN", hash.get("status"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLHTPULASANSPHP");
			myLog.info("saveSPHP:sql="+sql);
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (Exception e) {
			conn.rollback();
	    	e.printStackTrace();

	    } finally {
			if (db != null)
				db.close();
			if(conn != null)
				conn.close();
		}

	}

	public void setMaklumatUlasanSPHP(String idUlasanSPHP) throws Exception {
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatUlasanSPHP = new Vector<Hashtable<String,String>>();

			sql = "SELECT A.ID_ULASANSPHP, A.ID_PERMOHONAN, A.TARIKH_HANTAR, ";
			sql += "A.TARIKH_TERIMA, A.ULASAN, A.STATUS_KEPUTUSAN, A.NO_RUJUKAN "+
			" , "+
			" (case "+
			"    when A.STATUS_KEPUTUSAN='S' THEN 'SETUJU' "+
			"    when A.STATUS_KEPUTUSAN='TS' THEN 'TIDAK SETUJU' "+
			"    else '' "+
			" end) KEPUTUSAN ";
			sql += "FROM TBLHTPULASANSPHP A ";
			sql += "WHERE A.ID_ULASANSPHP ='" + idUlasanSPHP + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idUlasanSPHP", rs.getString("ID_ULASANSPHP") == null ? "" : rs.getString("ID_ULASANSPHP"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noRujukan", rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("ulasan", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
				h.put("status", rs.getString("STATUS_KEPUTUSAN")== null ? "" : rs.getString("STATUS_KEPUTUSAN"));
				h.put("statusName",Utils.isNull(rs.getString("STATUS_KEPUTUSAN")));
				beanMaklumatUlasanSPHP.addElement(h);

			}

		}catch(Exception e){
			e.printStackTrace();

		}finally {
			if (db != null)	db.close();
		}

	}

	public void saveUpdateSPHP(String idUlasanSPHP, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANSPHP
			r.update("ID_ULASANSPHP", idUlasanSPHP);
			r.add("NO_RUJUKAN", hash.get("noRujukan"));
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTAR", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN", hash.get("ulasan"));
			r.add("STATUS_KEPUTUSAN", hash.get("status"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLHTPULASANSPHP");
			stmt.executeUpdate(sql);
			conn.commit();

		} catch (Exception e) {
	    	conn.rollback();
	    	e.printStackTrace();

	    } finally {
			if (db != null)
				db.close();
			if(conn != null)
				conn.close();
		}

	}

	public void hapusSPHP(String idUlasanSPHP) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPULASANSPHP
			r.add("ID_ULASANSPHP", idUlasanSPHP);

			sql = r.getSQLDelete("TBLHTPULASANSPHP");
			stmt.executeUpdate(sql);

			conn.commit();

		} catch (Exception e) {
	    	conn.rollback();
	    	e.printStackTrace();

	    } finally {
			if (db != null)
				db.close();
			if(conn != null)
				conn.close();
		}

	}
	//end sphp

//	public void saveDraf(String idPermohonan, Hashtable hash, HttpSession session, FileItem item) throws Exception {
	public void saveDraf(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//String tarikhHantar = null;
			//String tarikhTerima = null;
			myLog.info("hash : " + hash);
//			//TBLHTPDERAFPERJANJIAN
			long idDerafPerjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");
			r.add("ID_DERAFPERJANJIAN", idDerafPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
				//tarikhHantar = r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')").toString();
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
				//tarikhTerima = r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')").toString();
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan"));
			r.add("JENIS_DOKUMEN","M");
//
//			//utk upload
//			if(!item.equals(null)){
//				r.
//				r.add("MIMETYPE","M");
//				r.add("NAMA_FAIL","M");
//			}
//
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPDERAFPERJANJIAN");
			stmt.executeUpdate(sql);

//			sql = "INSERT INTO TBLHTPDERAFPERJANJIAN " +
//				  "(ID_DERAFPERJANJIAN,ID_PERMOHONAN,TARIKH_HANTARPTP, " +
//				  "TARIKH_TERIMAPTP,ULASAN_SEKSYEN,JENIS_DOKUMEN, " +
//				  "CONTENT_DERAFPERJANJIAN,MIMETYPE,NAMA_FAIL, ID_MASUK,TARIKH_MASUK ) " +
//				  "VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE) ";
//
//			PreparedStatement ps = conn.prepareStatement(sql);
//
//			ps.setLong(1, idDerafPerjanjian);
//			ps.setLong(2, Long.parseLong(idPermohonan));
////			ps.setString(3, "to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')");
////			ps.setString(4,"to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')" );
//			ps.setString(3, tarikhHantar );
//			ps.setString(4, tarikhTerima);
//			ps.setString(5, hash.get("ulasan").toString());
//			ps.setString(6, "M");
//			ps.setBinaryStream(7, item.getInputStream(), item.getSize());
//			ps.setString(8, item.getName());
//			ps.setString(9, item.getContentType());
//			ps.setString(10, userId);
////			ps.setString(11, "");

//			log.info("Preparedstmt : " +  ps );
//
//			ps.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		}finally {
			if (db != null)
				db.close();
		}

	}

	public String simpanDraf(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String returnValue = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			//String tarikhHantar = null;
			//String tarikhTerima = null;
			//TBLHTPDERAFPERJANJIAN
			long idDerafPerjanjian = DB.getNextID("TBLHTPDERAFPERJANJIAN_SEQ");
			returnValue = String.valueOf(idDerafPerjanjian);
			r.add("ID_DERAFPERJANJIAN", idDerafPerjanjian);
			r.add("ID_PERMOHONAN", idPermohonan);
			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan"));
			r.add("JENIS_DOKUMEN","M");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLHTPDERAFPERJANJIAN");
			myLog.info("simpanDraf:sql="+sql);
			stmt.executeUpdate(sql);
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();

		}finally {
			if (db != null)
				db.close();
		}
		return returnValue;

	}

//	public void saveUpdateDraf(String idDraf, Hashtable hash, HttpSession session, FileItem item) throws Exception {
	public void saveUpdateDraf(String idDraf, Hashtable<String,String> hash) throws Exception {
		//String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPDERAFPERJANJIAN
			r.update("ID_DERAFPERJANJIAN", idDraf);

			if (!"".equals(hash.get("tarikhHantar"))){
				r.add("TARIKH_HANTARPTP", r.unquote("to_date('" + hash.get("tarikhHantar") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMAPTP", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			r.add("ULASAN_SEKSYEN", hash.get("ulasan").trim());

			r.add("ID_KEMASKINI", hash.get("userId"));
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPDERAFPERJANJIAN");
			myLog.info("saveUpdateDraf:sql="+sql);
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
			senaraiDraf = new Vector<Hashtable<String, String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_DERAFPERJANJIAN, A.TARIKH_HANTARPTP, A.TARIKH_TERIMAPTP, A.ULASAN_SEKSYEN"
				+ " FROM TBLHTPDERAFPERJANJIAN A WHERE A.JENIS_DOKUMEN = 'M' AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil", String.valueOf(bil));
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
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
			sql = "SELECT A.ID_DERAFPERJANJIAN, A.TARIKH_HANTARPTP, A.TARIKH_TERIMAPTP" +
				",A.ULASAN_SEKSYEN" +
				",NVL((SELECT NAMA_FAIL FROM TBLHTPDERAFPERJANJIANATTACH " +
				"WHERE ID_DERAFPERJANJIAN=A.ID_DERAFPERJANJIAN ) ,0) LAMPIRAN " +
				"FROM TBLHTPDERAFPERJANJIAN A WHERE A.ID_DERAFPERJANJIAN = '" + idDraf + "'";
			myLog.info("setMaklumatDraf:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));
				h.put("lampiran", Utils.isNull(rs.getString("LAMPIRAN")));
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
			sql = "SELECT DP.ID_DERAFPERJANJIAN" +
				" ,DP.TARIKH_HANTAR_DERAF" +
				" ,DP.TARIKH_TERIMA" +
				" ,DP.TARIKH_HANTARPTP" +
			    " ,DP.TARIKH_TERIMAPTP" +
			    " ,DP.TARIKH_TERIMA_PERJANJIAN" +
			    " ,DP.ULASAN_SEKSYEN" +
				" ,NVL(DPA.LAMPIRAN,'TIADA') LAMPIRANS" +
				" FROM TBLHTPDERAFPERJANJIAN DP " +
				" , (SELECT  ID_DERAFPERJANJIAN" +
				" ,RTRIM(XMLAGG(XMLELEMENT(E,ID_DERAFPERJANJIANATTACH||NAMA_FAIL || ',')).EXTRACT('//text()'),',') LAMPIRAN " +
				" FROM TBLHTPDERAFPERJANJIANATTACH" +
				" GROUP BY ID_DERAFPERJANJIAN " +
				" ) DPA" +
				" WHERE DP.ID_DERAFPERJANJIAN = DPA.ID_DERAFPERJANJIAN(+)" +
				" AND DP.ID_DERAFPERJANJIAN = '" + idDraf + "'";
			myLog.info("getMaklumatDraf:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			while(rs.next()){
				h = new Hashtable<String,String>();
				h.put("idDraf", rs.getString("ID_DERAFPERJANJIAN") == null ? "" : rs.getString("ID_DERAFPERJANJIAN"));
				h.put("tarikhHantar", rs.getDate("TARIKH_HANTAR_DERAF") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DERAF")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhHantarPTP", rs.getDate("TARIKH_HANTARPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTARPTP")));
				h.put("tarikhTerimaPTP", rs.getDate("TARIKH_TERIMAPTP") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("tarikhTerimaPer", rs.getDate("TARIKH_TERIMA_PERJANJIAN") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMAPTP")));
				h.put("ulasan", rs.getString("ULASAN_SEKSYEN") == null ? "" : rs.getString("ULASAN_SEKSYEN"));
				h.put("lampirans", Utils.isNull(rs.getString("LAMPIRANS")));
				beanMaklumatDraf.addElement(h);

			}

		} finally {
			if (db != null)
				db.close();
		}
		return beanMaklumatDraf;

	}


	public void hapusDraf(String idDraf) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			//TBLHTPDERAFPERJANJIAN
			r.add("ID_DERAFPERJANJIAN", idDraf);

			sql = r.getSQLDelete("TBLHTPDERAFPERJANJIAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			r.add("ID_DERAFPERJANJIAN", idDraf);
			sql = r.getSQLDelete("TBLHTPDERAFPERJANJIANATTACH");
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

	public void seterusnya(String idFail, String idPermohonan, String subUrusan, HttpSession session)
		throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
/*
			//TBLHTPMOA
			r = new SQLRenderer();
			long idMOA = DB.getNextID("TBLHTPMOA_SEQ");
			r.add("ID_MOA", idMOA);
			r.add("ID_PERMOHONAN", idPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPMOA");
			stmt.executeUpdate(sql);

			//TBLHTPBORANGPAJAKAN
			r = new SQLRenderer();
			long idBorangPajakan = DB.getNextID("TBLHTPBORANGPAJAKAN_SEQ");
			r.add("ID_BORANGPAJAKAN", idBorangPajakan);
			r.add("ID_PERMOHONAN", idPermohonan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPBORANGPAJAKAN");
			stmt.executeUpdate(sql);
*/
			Long setIdStatus = 0L;
			setIdStatus = FrmUtilData.getIdStatusByLangkah("8",subUrusan,"=");
			Long setIdSuburusanstatus = 0L;
			setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah("8",subUrusan,"=");

			//TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS",setIdStatus);

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
				r.add("ID_SUBURUSANSTATUS", "20"); //MAKLUMAT PERMOHONAN TANAH
			} else if ("17".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "191"); //MAKLUMAT PERMOHONAN TANAH
			} else if ("18".equals(subUrusan)){
				r.add("ID_SUBURUSANSTATUS", "200"); //MAKLUMAT PERMOHONAN TANAH
			}*/
			r.add("ID_SUBURUSANSTATUS",setIdSuburusanstatus);
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

	public Vector<Hashtable<String,String>> getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector<Hashtable<String,String>> senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

	public Vector<Hashtable<String, String>> getPemohonPajakan() {
		return pemohonPajakan;
	}

	public void setPemohonPajakan(Vector<Hashtable<String,String>> pemohonPajakan) {
		this.pemohonPajakan = pemohonPajakan;
	}

	public Vector<Hashtable<String, String>> getMemoJemaahMenteri() {
		return memoJemaahMenteri;
	}

	public void setMemoJemaahMenteri(Vector<Hashtable<String,String>> memoJemaahMenteri) {
		this.memoJemaahMenteri = memoJemaahMenteri;
	}

	public Vector<Hashtable<String, String>> getPerakuanJawatankuasa() {
		return perakuanJawatankuasa;
	}

	public void setPerakuanJawatankuasa(Vector<Hashtable<String,String>> perakuanJawatankuasa) {
		this.perakuanJawatankuasa = perakuanJawatankuasa;
	}

	public Vector<Hashtable<String,String>> getUlasanKJP() {
		return ulasanKJP;
	}

	public Vector<Hashtable<String, String>> getSenaraiUlasanKJP(){
		return senaraiUlasanKJP;
	}

	public void setSenaraiUlasanKJP(Vector<Hashtable<String,String>> senaraiUlasanKJP){
		this.senaraiUlasanKJP = senaraiUlasanKJP;
	}

	public void setUlasanKJP(Vector<Hashtable<String,String>> ulasanKJP) {
		this.ulasanKJP = ulasanKJP;
	}

	public Vector<Hashtable<String, String>> getSenaraiUlasanJPPH() {
		return senaraiUlasanJPPH;
	}

	public void setSenaraiUlasanJPPH(Vector<Hashtable<String,String>> senaraiUlasanJPPH) {
		this.senaraiUlasanJPPH = senaraiUlasanJPPH;
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatUlasanJPPH() {
		return beanMaklumatUlasanJPPH;
	}

	public void setBeanMaklumatUlasanJPPH(Vector<Hashtable<String, String>> beanMaklumatUlasanJPPH) {
		this.beanMaklumatUlasanJPPH = beanMaklumatUlasanJPPH;
	}

	public void setBeanMaklumatUlasanSPHP(Vector<Hashtable<String,String>> beanMaklumatUlasanSPHP){
		this.beanMaklumatUlasanSPHP = beanMaklumatUlasanSPHP;
	}

	public void setSenaraiUlasanSPHP(Vector<Hashtable<String,String>> senaraiUlasanSPHP){
		this.senaraiUlasanSPHP = senaraiUlasanSPHP;
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatUlasanSPHP(){
		return beanMaklumatUlasanSPHP;
	}

	public Vector<Hashtable<String, String>> getSenaraiUlasanSPHP(){
		return senaraiUlasanSPHP;
	}

	public Vector<Hashtable<String, String>> getSenaraiDraf() {
		return senaraiDraf;
	}

	public void setSenaraiDraf(Vector<Hashtable<String,String>> senaraiDraf) {
		this.senaraiDraf = senaraiDraf;
	}

	public Vector<Hashtable<String, String>> getBeanMaklumatDraf() {
		return beanMaklumatDraf;
	}

	public void setBeanMaklumatDraf(Vector<Hashtable<String,String>> beanMaklumatDraf) {
		this.beanMaklumatDraf = beanMaklumatDraf;
	}

	public boolean getLampiran(String idPerjanjian) throws Exception {
		boolean returnValue = false;
		//SQLRenderer r = null;
		try{
				db = new Db();
				conn = db.getConnection();
				Statement stmt = db.getStatement();
				sql = "SELECT ID_DERAFPERJANJIANATTACH FROM TBLHTPDERAFPERJANJIANATTACH " +
	            	"WHERE ID_DERAFPERJANJIAN= '"+idPerjanjian+"'";
	            //log.info("simpanLampiranDraf:sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);
				returnValue = rs.next();

		}catch(Exception e){
				e.getStackTrace();
		} finally {
			if (db != null)
				db.close();

		}
		return returnValue;

	}

	public boolean isMaklumatPemohonPajakan(String idPermohonan) throws Exception {
		Boolean returnValue = false;
		try{
			db = new Db();
				Statement stmt = db.getStatement();
				pemohonPajakan = new Vector<Hashtable<String, String>>();

				sql = "SELECT A.ID_PEMOHON, A.ID_PERMOHONAN, A.NO_PEMOHON, A.NAMA_PEMOHON, ";
				sql += "A.ALAMAT_PEMOHON1, A.ALAMAT_PEMOHON2, A.ALAMAT_PEMOHON3, ";
				sql += "A.POSKOD, A.ID_NEGERI, A.ID_DAERAH,A.ID_BANDAR, A.NO_TEL, A.NO_FAX, A.EMEL ";
				sql += "FROM TBLHTPPEMOHON A ";
				sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				while(rs.next()){
					h = new Hashtable<String,String>();
					h.put("idPemohon", rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON"));
					h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("noPemohon", rs.getString("NO_PEMOHON") == null ? "" : rs.getString("NO_PEMOHON"));
					h.put("nama", rs.getString("NAMA_PEMOHON") == null ? "" : rs.getString("NAMA_PEMOHON"));
					h.put("alamat1", rs.getString("ALAMAT_PEMOHON1") == null ? "" : rs.getString("ALAMAT_PEMOHON1"));
					h.put("alamat2", rs.getString("ALAMAT_PEMOHON2") == null ? "" : rs.getString("ALAMAT_PEMOHON2"));
					h.put("alamat3", rs.getString("ALAMAT_PEMOHON3") == null ? "" : rs.getString("ALAMAT_PEMOHON3"));
					h.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999" : rs.getString("ID_NEGERI"));
					h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "99999" : rs.getString("ID_DAERAH"));
					h.put("tel", rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
					h.put("fax", rs.getString("NO_FAX") == null ? "" : rs.getString("NO_FAX"));
					h.put("idBandar", rs.getString("ID_BANDAR") == null ? "0" : rs.getString("ID_BANDAR"));
					h.put("emel", Utils.isNull(rs.getString("EMEL")));
					pemohonPajakan.addElement(h);
					returnValue = true;
				}

		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;

	}

	public Boolean isMaklumatMemorandumJemaahMenteri(String idPermohonan) throws Exception{
		Boolean returnValue = false;
		try{
			db = new Db();
				Statement stmt = db.getStatement();
				memoJemaahMenteri = new Vector<Hashtable<String,String>>();

				sql = "SELECT A.ID_JEMAAHMENTERI, A.ID_PERMOHONAN, A.TARIKH_HANTAR_DASAR, ";
				sql += "A.TARIKH_TERIMA, A.TARIKH_HANTAR_KSU, ";
				sql += "A.TARIKH_MSYRT_JEMAAH, A.TARIKH_TERIMA_KSU, A.NO_MEMORANDUM, ";
				sql += "A.STATUS_KEPUTUSAN, A.TINDAKAN_LANJUT, A.TARIKH_HANTAR_PEMOHON ";
				sql += "FROM TBLHTPJEMAAHMENTERI A ";
				sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
				//myLog.info("setMaklumatMemorandumJemaahMenteri:sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				while(rs.next()){
					h = new Hashtable<String,String>();
					h.put("idJemaahMenteri", rs.getString("ID_JEMAAHMENTERI") == null ? "" : rs.getString("ID_JEMAAHMENTERI"));
					h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("tarikhHantarPTP", rs.getDate("TARIKH_HANTAR_DASAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_DASAR")));
					h.put("tarikhTerimaPTP", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
					h.put("tarikhHantarKSU", rs.getDate("TARIKH_HANTAR_KSU") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_KSU")));
					h.put("tarikhMesyuaratJM", rs.getDate("TARIKH_MSYRT_JEMAAH") == null ? "" : sdf.format(rs.getDate("TARIKH_MSYRT_JEMAAH")));
					h.put("tarikhTerimaKeputusan", rs.getDate("TARIKH_TERIMA_KSU") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA_KSU")));
					h.put("noMemorandum", rs.getString("NO_MEMORANDUM") == null ? "" : rs.getString("NO_MEMORANDUM"));
					h.put("keputusan", rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs.getString("STATUS_KEPUTUSAN"));
					h.put("tindakanLanjut", rs.getString("TINDAKAN_LANJUT") == null ? "" : rs.getString("TINDAKAN_LANJUT"));
					h.put("tarikhMaklumanKeputusan", rs.getDate("TARIKH_HANTAR_PEMOHON") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_PEMOHON")));

					memoJemaahMenteri.addElement(h);
					returnValue = true;
				}

		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;

	}

	public Boolean isMaklumatPerakuanJawatankuasa(String idPermohonan) throws Exception{
		Boolean returnValue = false;
		try{
			db = new Db();
				Statement stmt = db.getStatement();
				perakuanJawatankuasa = new Vector<Hashtable<String,String>>();

				sql = "SELECT A.ID_PERAKUANJAWATANKUASA, A.ID_PERMOHONAN, ";
				sql += "A.STATUS_PERAKUAN, A.ULASAN, A.TARIKH_PERAKUAN ";
				sql += "FROM TBLHTPPERAKUANJAWATANKUASA A ";
				sql += "WHERE A.ID_PERMOHONAN = " + idPermohonan;
				myLog.info("isMaklumatPerakuanJawatankuasa:sql="+sql);
				ResultSet rs = stmt.executeQuery(sql);

				Hashtable<String,String> h;
				while(rs.next()){
					h = new Hashtable<String,String>();
					h.put("idPerakuan", rs.getString("ID_PERAKUANJAWATANKUASA") == null ? "" : rs.getString("ID_PERAKUANJAWATANKUASA"));
					h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("keputusan", rs.getString("STATUS_PERAKUAN") == null ? "" : rs.getString("STATUS_PERAKUAN"));
					h.put("tindakanLanjut", rs.getString("ULASAN") == null ? "" : rs.getString("ULASAN"));
					h.put("tarikhMaklumanKeputusan", rs.getDate("TARIKH_PERAKUAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERAKUAN")));

					perakuanJawatankuasa.addElement(h);
					returnValue = true;
				}

		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;

	}

	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;

	}

	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;

	}


}


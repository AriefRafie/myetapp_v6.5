package ekptg.model.php2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmCetakSuratKelulusanLesenKepadaPemohonData {

	private Vector beanMaklumatSuratKelulusanLesenKepadaPemohon = null;
	private Vector listPelan = null;
	private Vector beanMaklumatPelan = null;	
	private static final Log log = LogFactory.getLog(FrmCetakSuratKelulusanLesenKepadaPemohonData.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatSuratKelulusanLesenKepadaPemohon(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSuratKelulusanLesenKepadaPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT ID_BYRNSYRTKLLSNLESENAPB, TBLPHPBYRNSYRTKLLSNLESENAPB.ID_LUAS, TBLPHPBYRNSYRTKLLSNLESENAPB.LUAS_KAWASAN, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_FEELESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.FEELESEN_BAG_SETIAP, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.KADAR_ROYALTI_PASIR, TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_ROYALTI_SELURUH, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.JUMLAH_DAHULU_ROYALTI, TBLPHPPMOHONNJDUALPERTAMA.ID_TEMPOH, "
					+ " TBLPHPPMOHONNJDUALPERTAMA.TEMPOH_DIPOHON, TBLPHPPMOHONNJDUALPERTAMA.FLAG_LUAR_PERAIRANNEGERI, "
					+ " TBLPHPPMOHONNJDUALPERTAMA.ID_NEGERI_PERAIRAN,TBLPHPPMOHONNJDUALPERTAMA.LOKASI_PERMOHONAN, "
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.KADAR_FEELESEN,INITCAP(TBLRUJLUAS.KETERANGAN) AS KETERANGAN, "
					+ " INITCAP(TBLRUJNEGERI.NAMA_NEGERI) AS NAMA_NEGERI,TBLPHPPMOHONNJDUALPERTAMA.ID_TEMPOH, TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_MULA_LESEN,"
					+ " TBLPHPBYRNSYRTKLLSNLESENAPB.TARIKH_TAMAT_LESEN, TBLPHPBYRNSYRTKLLSNLESENAPB.NO_LESEN,  TBLPHPPMOHONNJDUALPERTAMA.TEMPOH_DILULUSKAN"
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB,TBLPHPPMOHONNJDUALPERTAMA,TBLRUJLUAS, TBLRUJNEGERI "
					+ " WHERE TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = TBLPHPPMOHONNJDUALPERTAMA.ID_PERMOHONAN "
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.ID_LUAS = TBLRUJLUAS.ID_LUAS(+) "
					+ " AND TBLPHPPMOHONNJDUALPERTAMA.ID_NEGERI_PERAIRAN = TBLRUJNEGERI.ID_NEGERI(+) "
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.FLAG_AKTIF = 'Y'"
					+ " AND TBLPHPBYRNSYRTKLLSNLESENAPB.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				if(rs.isFirst()){
					h = new Hashtable();
					h.put("idByrnsyrtkllsnlesenapb", rs
							.getString("ID_BYRNSYRTKLLSNLESENAPB") == null ? ""
							: rs.getString("ID_BYRNSYRTKLLSNLESENAPB"));
					h.put("txtNoLesen",
							rs.getString("NO_LESEN") == null ? "" : rs
									.getString("NO_LESEN"));
					h.put("txtTarikhMula",
							rs.getDate("TARIKH_MULA_LESEN") == null ? "" : sdf
									.format(rs.getDate("TARIKH_MULA_LESEN")));
					h.put("tempohDipohon",
							rs.getString("TEMPOH_DIPOHON") == null ? "" : rs
									.getString("TEMPOH_DIPOHON"));
					h.put("txtTempohDiluluskan",
							rs.getString("TEMPOH_DILULUSKAN") == null ? "" : rs
									.getString("TEMPOH_DILULUSKAN"));
					h.put("txtTarikhTamat",
							rs.getDate("TARIKH_TAMAT_LESEN") == null ? "" : sdf
									.format(rs.getDate("TARIKH_TAMAT_LESEN")));
					h.put("idTempoh",
							rs.getString("ID_TEMPOH") == null ? "" : rs
									.getString("ID_TEMPOH"));
					h.put("kadarFeeLesen",
							rs.getString("KADAR_FEELESEN") == null ? "0.00" : Util
									.formatDecimal(Double.parseDouble(rs
											.getString("KADAR_FEELESEN"))));
					h.put("jumlahFeeLesen",
							rs.getString("JUMLAH_FEELESEN") == null ? "0.00" : Util
									.formatDecimal(Double.parseDouble(rs
											.getString("JUMLAH_FEELESEN"))));
					h.put("kmPersegi",
							rs.getString("FEELESEN_BAG_SETIAP") == null ? "0.00"
									: Util.formatDecimal(Double.parseDouble(rs
											.getString("FEELESEN_BAG_SETIAP"))));
					h.put("kadarRoyaltiPasir",
							rs.getString("KADAR_ROYALTI_PASIR") == null ? "0.00"
									: Util.formatDecimal(Double.parseDouble(rs
											.getString("KADAR_ROYALTI_PASIR"))));
					h.put("jumlahRoyaltiSeluruh",
							rs.getString("JUMLAH_ROYALTI_SELURUH") == null ? "0.00"
									: Utils.RemoveSymbol(rs
											.getString("JUMLAH_ROYALTI_SELURUH")));
					h.put("jumDahuluRoyalti",
							rs.getString("JUMLAH_DAHULU_ROYALTI") == null ? "0.00"
									: Utils.RemoveSymbol(rs
											.getString("JUMLAH_DAHULU_ROYALTI")));
					h.put("flagLuar",
							rs.getString("FLAG_LUAR_PERAIRANNEGERI") == null ? ""
									: rs.getString("FLAG_LUAR_PERAIRANNEGERI"));
					h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? ""
							: rs.getString("NAMA_NEGERI"));
					h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? ""
							: rs.getString("LOKASI_PERMOHONAN"));
					h.put("luas",
							rs.getString("LUAS_KAWASAN") == null ? "" : rs
									.getString("LUAS_KAWASAN"));
					h.put("jenisLuas",
							rs.getString("KETERANGAN") == null ? "" : rs
									.getString("KETERANGAN"));
					beanMaklumatSuratKelulusanLesenKepadaPemohon.addElement(h);
					bil++;
				}
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateMaklumatSuratKelulusanLesenKepadaPemohon(
			String idPermohonan, String txtNoLesen, String txtTarikhMula,
			String txtTarikhTamat, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TM = "to_date('" + txtTarikhMula + "','dd/MM/yyyy')";
			String TT = "to_date('" + txtTarikhTamat + "','dd/MM/yyyy')";

			// TBLPHPBYRNSYRTKLLSNLESENAPB
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_AKTIF", "Y");
			r.add("NO_LESEN", txtNoLesen);
			r.add("TARIKH_MULA_LESEN", r.unquote(TM));
			r.add("TARIKH_TAMAT_LESEN", r.unquote(TT));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPBYRNSYRTKLLSNLESENAPB");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610239", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiPelan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan
					+ "' AND FLAG_DOKUMEN = 'P'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listPelan.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatPelan(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE FLAG_DOKUMEN = 'P' AND ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Pelan mana pelan? "+sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaPelan", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanPelan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				beanMaklumatPelan.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanKemaskiniPelan(String idDokumen, String txtNamaPelan,
			String txtCatatan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPDOKUMEN
			r.update("ID_DOKUMEN", idDokumen);
			r.add("NAMA_DOKUMEN", txtNamaPelan);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610239", "4", null, session, "UPD",
					"FAIL [" + idDokumen + "] DIKEMASKINI");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusPelan(String idDokumen, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();

			// TBLPHPDOKUMEN
			SQLRenderer r = new SQLRenderer();
			r.add("ID_DOKUMEN", idDokumen);

			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610239", "4", null, session, "DEL",
					"FAIL [" + idDokumen + "] DIHAPUSKAN");

		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah menghapus data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
			HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPERMOHONAN
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1610207"); //LULUS

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLRUJSUBURUSANSTATUSFAIL
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("AKTIF", "1");

			r.add("AKTIF", "0");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("57", "1610207")); //LULUS
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// COPY MAKLUMAT KEPADA TBLPHPJADUALKEDUALESENAPB
			sql = "SELECT A.NO_LESEN, B.LOKASI_PERMOHONAN, A.TEMPOH_LESEN, A.ID_TEMPOH, A.TARIKH_MULA_LESEN, A.TARIKH_TAMAT_LESEN, A.ID_LUAS, A.LUAS_KAWASAN, A.LUAS_DLM_NAUTIKA, A.KADAR_ROYALTI_PASIR"
					+ " FROM TBLPHPBYRNSYRTKLLSNLESENAPB A, TBLPHPPMOHONNJDUALPERTAMA B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.FLAG_AKTIF = 'Y' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rsLesen = stmt.executeQuery(sql);

			Date tarikhMula = new Date();
			Date tarikhTamat = new Date();
			if (rsLesen.next()) {

				tarikhMula = rsLesen.getDate("TARIKH_MULA_LESEN");
				tarikhTamat = rsLesen.getDate("TARIKH_TAMAT_LESEN");
			}

			r = new SQLRenderer();
			long idJadualKedua = DB.getNextID("TBLPHPJADUALKEDUALESENAPB_SEQ");
			r.add("ID_JADUALKEDUALESENAPB", idJadualKedua);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_FAIL ", idFail);
			r.add("NO_SIRI_LESEN", rsLesen.getString("NO_LESEN") == null ? ""
					: rsLesen.getString("NO_LESEN"));
			r.add("LOKASI", rsLesen.getString("LOKASI_PERMOHONAN") == null ? ""
					: rsLesen.getString("LOKASI_PERMOHONAN"));
			r.add("TEMPOH", rsLesen.getString("TEMPOH_LESEN") == null ? ""
					: rsLesen.getString("TEMPOH_LESEN"));
			r.add("ID_TEMPOH", rsLesen.getString("TEMPOH_LESEN") == null ? ""
					: rsLesen.getString("TEMPOH_LESEN"));
			r.add("TARIKH_MULA_LESEN",
					rsLesen.getString("TARIKH_MULA_LESEN") == null ? "" : r
							.unquote("to_date('"
									+ rsLesen.getString("TARIKH_MULA_LESEN")
									+ "','dd/MM/yyyy')"));
			r.add("TARIKH_TAMATLESEN",
					rsLesen.getString("TARIKH_TAMAT_LESEN") == null ? "" : r
							.unquote("to_date('"
									+ rsLesen.getString("TARIKH_TAMAT_LESEN")
									+ "','dd/MM/yyyy')"));
			r.add("LUAS",
					rsLesen.getString("ID_LUAS") == null ? "" : rsLesen
							.getString("ID_LUAS"));
			r.add("ID_UNIT_LUAS", rsLesen.getString("LUAS_KAWASAN") == null ? ""
					: rsLesen.getString("LUAS_KAWASAN"));
			r.add("KADAR_ROYALTI",
					rsLesen.getString("KADAR_ROYALTI_PASIR") == null ? ""
							: rsLesen.getString("KADAR_ROYALTI_PASIR"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPJADUALKEDUALESENAPB");
			stmt.executeUpdate(sql);

			// COPY MAKLUMAT KEPADA TBLPHPPEMEGANG
			sql = "SELECT * FROM TBLPHPPEMOHON WHERE ID_PEMOHON = (SELECT ID_PEMOHON FROM TBLPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "')";
			ResultSet rsPemegang = stmt.executeQuery(sql);
			rsPemegang.next();

			r = new SQLRenderer();
			long idPemegang = DB.getNextID("TBLPHPPEMEGANG_SEQ");
			r.add("ID_PEMEGANG", idPemegang);
			r.add("ID_JADUALKEDUALESENAPB", idJadualKedua);
			r.add("NAMA", rsPemegang.getString("NAMA") == null ? ""
					: rsPemegang.getString("NAMA"));
			r.add("ID_JENISPENGENALAN",
					rsPemegang.getString("ID_JENISPENGENALAN") == null ? ""
							: rsPemegang.getString("ID_JENISPENGENALAN"));
			r.add("NO_KP", rsPemegang.getString("NO_PENGENALAN") == null ? ""
					: rsPemegang.getString("NO_PENGENALAN"));
			r.add("ALAMAT1_TETAP",
					rsPemegang.getString("ALAMAT1_TETAP") == null ? ""
							: rsPemegang.getString("ALAMAT1_TETAP"));
			r.add("ALAMAT2_TETAP",
					rsPemegang.getString("ALAMAT2_TETAP") == null ? ""
							: rsPemegang.getString("ALAMAT2_TETAP"));
			r.add("ALAMAT3_TETAP",
					rsPemegang.getString("ALAMAT3_TETAP") == null ? ""
							: rsPemegang.getString("ALAMAT3_TETAP"));
			r.add("POSKOD_TETAP",
					rsPemegang.getString("POSKOD_TETAP") == null ? ""
							: rsPemegang.getString("POSKOD_TETAP"));
			r.add("ID_BANDARTETAP",
					rsPemegang.getString("ID_BANDARTETAP") == null ? ""
							: rsPemegang.getString("ID_BANDARTETAP"));
			r.add("ID_NEGERITETAP",
					rsPemegang.getString("ID_NEGERITETAP") == null ? ""
							: rsPemegang.getString("ID_NEGERITETAP"));
			r.add("NO_TEL_RUMAH",
					rsPemegang.getString("NO_TEL_RUMAH") == null ? ""
							: rsPemegang.getString("NO_TEL_RUMAH"));
			r.add("NO_TEL_PEJABAT",
					rsPemegang.getString("NO_TEL_PEJABAT") == null ? ""
							: rsPemegang.getString("NO_TEL_PEJABAT"));
			r.add("NO_FAX", rsPemegang.getString("NO_FAX") == null ? ""
					: rsPemegang.getString("NO_FAX"));
			r.add("NO_TEL_BIMBIT",
					rsPemegang.getString("NO_TEL_BIMBIT") == null ? ""
							: rsPemegang.getString("NO_TEL_BIMBIT"));
			r.add("EMEL", rsPemegang.getString("EMEL") == null ? ""
					: rsPemegang.getString("EMEL"));
			r.add("PEKERJAAN", rsPemegang.getString("PEKERJAAN") == null ? ""
					: rsPemegang.getString("PEKERJAAN"));
			r.add("MODAL_DIBENARKAN",
					rsPemegang.getString("MODAL_DIBENARKAN") == null ? ""
							: rsPemegang.getString("MODAL_DIBENARKAN"));
			r.add("MODAL_JELAS",
					rsPemegang.getString("MODAL_JELAS") == null ? ""
							: rsPemegang.getString("MODAL_JELAS"));

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMEGANG");

			stmt.executeUpdate(sql);

//			if (!tarikhMula.equals(null) && !tarikhTamat.equals(null)) {
//				generateLaporan(idJadualKedua, tarikhMula, tarikhTamat, db,
//						session);
//			} else {
//				generateLaporan(idJadualKedua, null, null, db, session);
//			}

			conn.commit();
			
			AuditTrail.logActivity("1610207", "4", null, session, "UPD",
					"FAIL [" + idPermohonan + "] PROSES SETERUSNYA");

		} catch (SQLException ex) {
			try {
				ex.printStackTrace();
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Rollback error : " + e.getMessage());
			}
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void generateLaporan(Long idJadualKedua, Date tarikhMula,
			Date tarikhTamat, Db db, HttpSession session) throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			Calendar calTarikhMula = new GregorianCalendar();
			Date dateMula = sdf.parse(sdf.format(tarikhMula));
			calTarikhMula.setTime(dateMula);

			Calendar calTarikhTamat = new GregorianCalendar();
			Date dateTamat = sdf.parse(sdf.format(tarikhTamat));
			calTarikhTamat.setTime(dateTamat);

			calTarikhMula.add(Calendar.MONTH, 1);
			while (calTarikhMula.getTime().before(calTarikhTamat.getTime())) {

				// TBLPHPLAPORANPASIR
				r = new SQLRenderer();
				long idLaporanPasir = DB.getNextID("TBLPHPLAPORANPASIR_SEQ");
				r.add("ID_LAPORANPASIR", idLaporanPasir);
				r.add("ID_JADUALKEDUALESENAPB", idJadualKedua);
				r.add("BULAN_PENGAMBILAN",
						(calTarikhMula.get(Calendar.MONTH)) + 1);
				r.add("TAHUN_PENGAMBILAN", (calTarikhMula.get(Calendar.YEAR)));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPLAPORANPASIR");
				stmt.executeUpdate(sql);

				calTarikhMula.add(Calendar.MONTH, 1);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdSuburusanstatus(String idSuburusan, String idStatus)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_SUBURUSANSTATUS FROM TBLRUJSUBURUSANSTATUS WHERE ID_STATUS = '"
					+ idStatus + "' AND ID_SUBURUSAN = '" + idSuburusan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_SUBURUSANSTATUS");
			} else {
				return "";
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkMaklumatPenyediaanLesen(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;
		String mula = "";
		String tamat = "";
		String lesen = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT  TARIKH_MULA_LESEN, TARIKH_TAMAT_LESEN, NO_LESEN "
					+ "FROM TBLPHPBYRNSYRTKLLSNLESENAPB WHERE FLAG_AKTIF = 'Y' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				mula = rs.getString("TARIKH_MULA_LESEN");
				tamat = rs.getString("TARIKH_TAMAT_LESEN");
				lesen = rs.getString("NO_LESEN");
			}

			if (mula == null) {
				bool = true;
			}

			if (tamat == null) {
				bool = true;
			}

			if (lesen == null) {
				bool = true;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
		return bool;
	}

	public Vector getBeanMaklumatSuratKelulusanLesenKepadaPemohon() {
		return beanMaklumatSuratKelulusanLesenKepadaPemohon;
	}
	
	public Vector getListPelan() {
		return listPelan;
	}

	public void setListPelan(Vector listPelan) {
		this.listPelan = listPelan;
	}

	public Vector getBeanMaklumatPelan() {
		return beanMaklumatPelan;
	}

	public void setBeanMaklumatPelan(Vector beanMaklumatPelan) {
		this.beanMaklumatPelan = beanMaklumatPelan;
	}
}
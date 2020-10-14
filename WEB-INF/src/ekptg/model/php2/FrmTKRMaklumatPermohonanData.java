package ekptg.model.php2;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.php2.modelTable.TblPhpHakmilikModel;
import ekptg.model.php2.modelTable.TblPhpHakmilikPermohonanModel;

public class FrmTKRMaklumatPermohonanData {

	private Vector beanMaklumatTukarguna = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatAgensi = null;
	private Vector beanMaklumatPejabat = null;
	private Vector listTanahGanti = null;
	private Vector beanMaklumatTanahGanti = null;
	private Vector beanMaklumatLuasTG = null;
	private Vector beanListMaklumatPemohon = null;
	private Vector listPelan = null;
	private Vector beanMaklumatPelan = null;
	private static final Log log = LogFactory.getLog(FrmTKRMaklumatPermohonanData.class);


	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void saveMaklumatTanah(String idHakmilikAgensiTG, String idPermohonan, String idHakmilik, Vector beanMaklumatTanah, HttpSession session) throws Exception {
//		TblPhpHakmilikPermohonanModel phpHakmilikMohonModel = new TblPhpHakmilikPermohonanModel();
//		phpHakmilikMohonModel.setIdHakmilikAgensi(Long.parseLong(idHakmilikAgensiTG));
//		phpHakmilikMohonModel.setIdPermohonan(Long.parseLong(idPermohonan));
//		phpHakmilikMohonModel.setIdHakmilik(Long.parseLong(idHakmilik));
//		phpHakmilikMohonModel.setFlagPost("update");
//		String idHakmilikPermohonan = phpHakmilikMohonModel.save(phpHakmilikMohonModel, session);

		if (beanMaklumatTanah.size() > 0){
			TblPhpHakmilikModel phpHakmilikModel = new TblPhpHakmilikModel();
			Hashtable hashMaklumatTanah = (Hashtable) beanMaklumatTanah.get(0);
			phpHakmilikModel.setIdHakmilik(Long.parseLong(idHakmilik));
			phpHakmilikModel.setIdNegeri(Long.parseLong((String) hashMaklumatTanah.get("idNegeri")));
			phpHakmilikModel.setIdDaerah(Long.parseLong((String) hashMaklumatTanah.get("idDaerah")));
			phpHakmilikModel.setIdMukim(Long.parseLong((String) hashMaklumatTanah.get("idMukim")));
			phpHakmilikModel.setIdJenisHakmilik(Long.parseLong((String) hashMaklumatTanah.get("idJenisHakmilik")));
			phpHakmilikModel.setNoHakmilik((String) hashMaklumatTanah.get("noHakmilik"));
			phpHakmilikModel.setIdLot(Long.parseLong((String) hashMaklumatTanah.get("idLot")));
			phpHakmilikModel.setNoLot((String) hashMaklumatTanah.get("noLot"));
			phpHakmilikModel.setIdKategori(Long.parseLong((String) hashMaklumatTanah.get("idKategori")));
			phpHakmilikModel.setIdKementerian(Long.parseLong((String) hashMaklumatTanah.get("idKementerian")));
			phpHakmilikModel.setIdAgensi(Long.parseLong((String) hashMaklumatTanah.get("idAgensi")));
			phpHakmilikModel.setPeganganHakmilik((String) hashMaklumatTanah.get("peganganHakmilik"));
			phpHakmilikModel.setKegunaanTanah((String) hashMaklumatTanah.get("kegunaanTanah"));
			phpHakmilikModel.setIdSubkategori(Long.parseLong((String) hashMaklumatTanah.get("idSubKategori")));
			phpHakmilikModel.setSyarat((String) hashMaklumatTanah.get("syarat"));
			phpHakmilikModel.setSekatan((String) hashMaklumatTanah.get("sekatan"));
			phpHakmilikModel.setIdLuas(Long.parseLong((String) hashMaklumatTanah.get("idLuas")));
			String luas = (String) hashMaklumatTanah.get("luas");
			luas = luas.substring(0, luas.indexOf("HEKTAR")-1);
			phpHakmilikModel.setLuas(Double.parseDouble(luas));
//			phpHakmilikModel.setIdHakmilikPermohonan(Long.parseLong(idHakmilikPermohonan));
			phpHakmilikModel.setFlagPost("update");
			phpHakmilikModel.save(phpHakmilikModel, session);
		}
	}

	@Transactional
	public void saveMaklumatTanahBerkaitan(String idHakmilikAgensiTG, String idPermohonan, String idHakmilik, Vector beanMaklumatTanah, HttpSession session) throws Exception {
		TblPhpHakmilikPermohonanModel phpHakmilikMohonModel = new TblPhpHakmilikPermohonanModel();
		phpHakmilikMohonModel.setIdHakmilikAgensi(Long.parseLong(idHakmilikAgensiTG));
		phpHakmilikMohonModel.setIdPermohonan(Long.parseLong(idPermohonan));
		phpHakmilikMohonModel.setIdHakmilik(Long.parseLong(idHakmilik));
		phpHakmilikMohonModel.setFlagPost("insert");
		phpHakmilikMohonModel.setFlagHakmilik("T");
		String idHakmilikPermohonan = phpHakmilikMohonModel.save(phpHakmilikMohonModel, session);

		if (beanMaklumatTanah.size() > 0){
			TblPhpHakmilikModel phpHakmilikModel = new TblPhpHakmilikModel();
			Hashtable hashMaklumatTanah = (Hashtable) beanMaklumatTanah.get(0);
//			phpHakmilikModel.setIdHakmilik(Long.parseLong(idHakmilik));
			phpHakmilikModel.setIdNegeri(Long.parseLong((String) hashMaklumatTanah.get("idNegeri")));
			phpHakmilikModel.setIdDaerah(Long.parseLong((String) hashMaklumatTanah.get("idDaerah")));
			phpHakmilikModel.setIdMukim(Long.parseLong((String) hashMaklumatTanah.get("idMukim")));
			phpHakmilikModel.setIdJenisHakmilik(Long.parseLong((String) hashMaklumatTanah.get("idJenisHakmilik")));
			phpHakmilikModel.setNoHakmilik((String) hashMaklumatTanah.get("noHakmilik"));
			phpHakmilikModel.setIdLot(Long.parseLong((String) hashMaklumatTanah.get("idLot")));
			phpHakmilikModel.setNoLot((String) hashMaklumatTanah.get("noLot"));
			phpHakmilikModel.setIdKategori(Long.parseLong((String) hashMaklumatTanah.get("idKategori")));
			phpHakmilikModel.setIdKementerian(Long.parseLong((String) hashMaklumatTanah.get("idKementerian")));
			phpHakmilikModel.setIdAgensi(Long.parseLong((String) hashMaklumatTanah.get("idAgensi")));
			phpHakmilikModel.setPeganganHakmilik((String) hashMaklumatTanah.get("peganganHakmilik"));
			phpHakmilikModel.setKegunaanTanah((String) hashMaklumatTanah.get("kegunaanTanah"));
			phpHakmilikModel.setIdSubkategori(Long.parseLong((String) hashMaklumatTanah.get("idSubKategori")));
			phpHakmilikModel.setSyarat((String) hashMaklumatTanah.get("syarat"));
			phpHakmilikModel.setSekatan((String) hashMaklumatTanah.get("sekatan"));
			phpHakmilikModel.setIdLuas(Long.parseLong((String) hashMaklumatTanah.get("idLuas")));
			String luas = (String) hashMaklumatTanah.get("luas");
			luas = luas.substring(0, luas.indexOf("HEKTAR")-1);
			phpHakmilikModel.setLuas(Double.parseDouble(luas));
			phpHakmilikModel.setIdHakmilikPermohonan(Long.parseLong(idHakmilikPermohonan));
			phpHakmilikModel.setFlagPost("insert");
			phpHakmilikModel.save(phpHakmilikModel, session);
		}

//		Db db = null;
//		Connection conn = null;
//		String userId = (String) session.getAttribute("_ekptg_user_id");
//		String sql = "";
//
//		try {
//			db = new Db();
//			conn = db.getConnection();
//			conn.setAutoCommit(false);
//			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
//
//			// TBLPHPHAKMILIKPERMOHONAN
//			r.add("ID_PERMOHONAN", idPermohonan);
//			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensiTG);
//			r.add("FLAG_HAKMILIK", "T");
//			r.add("ID_HAKMILIK", idHakmilik);
//			r.add("ID_HAKMILIKPERMOHONAN", DB.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ"));
//			r.add("ID_MASUK", userId);
//			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
//			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
//			stmt.executeUpdate(sql);
//
//
//
//			conn.commit();
//
//		} catch (SQLException ex) {
//			try {
//				conn.rollback();
//			} catch (SQLException e) {
//				throw new Exception("Rollback error : " + e.getMessage());
//			}
//			throw new Exception("Ralat : Masalah penyimpanan data "
//					+ ex.getMessage());
//
//		} finally {
//			if (db != null)
//				db.close();
//		}
	}

	public void simpanKemaskiniDokumen(String idDokumen, String txtNamaPelan,
			String txtCatatanPelan, HttpSession session) throws Exception {
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
			r.add("CATATAN", txtCatatanPelan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idDokumen
							+ "] DIDAFTARKAN");

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

	public void hapusDokumen(String idDokumen, HttpSession session) throws Exception {
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

			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idDokumen
							+ "] DIHAPUSKAN");

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

	public Vector getSenaraiSemak(String idPermohonan) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_RUJSENARAISEMAK, KETERANGAN,"
					+ " CASE WHEN ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG FROM TBLPHPRUJSENARAISEMAK"
					+ " WHERE FLAG_AKTIF = 'Y' AND ID_URUSAN = 6 AND ID_SUBURUSAN = 33";
			System.out.println("getSenaraiSemak :: sql >>>>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idSenaraiSemak", rs.getString("ID_RUJSENARAISEMAK") == null ? "" : rs.getString("ID_RUJSENARAISEMAK"));
				h.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				h.put("flag", rs.getString("FLAG") == null ? "" : rs.getString("FLAG"));
				senaraiSemak.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}

		return senaraiSemak;
	}

	public void updateSenaraiSemak(String idPermohonan, String[] semaks, HttpSession session) throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		Db db = new Db();
		String sql = "";

		try {
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r = new SQLRenderer();

			r.add("ID_PERMOHONAN", idPermohonan);
			sql = r.getSQLDelete("TBLPHPSENARAISEMAK");
			stmt.executeUpdate(sql);

			for (int i = 0; i < semaks.length; i++) {
			 	r = new SQLRenderer();
				long ID_SENARAISEMAK = DB.getNextID("TBLPHPSENARAISEMAK_SEQ");
				r.add("ID_SENARAISEMAK", ID_SENARAISEMAK);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("ID_RUJSENARAISEMAK", semaks[i]);
				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLPHPSENARAISEMAK");
				stmt.executeUpdate(sql);
			}
			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idPermohonan + "] DIKEMASKINI");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setMaklumatPelan(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

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
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
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
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 1;
			int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaPelan", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				listPelan.addElement(h);
				bil++;
				count++;
			}
		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void hapusPemohon(String idPemohon, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT ID_PHPPERMOHONANPELEPASAN FROM TBLPHPPERMOHONANPELEPASAN"
					+ " WHERE ID_PEMOHON = '" + idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				String idPermohonanPelepasan = rs.getString("ID_PHPPERMOHONANPELEPASAN") == null ? "" : rs.getString("ID_PHPPERMOHONANPELEPASAN");

				// TBLPHPPERMOHONANPELEPASAN
				r = new SQLRenderer();
				r.add("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasan);
				sql = r.getSQLDelete("TBLPHPPERMOHONANPELEPASAN");
				stmt.executeUpdate(sql);
			}

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			r.add("ID_PEMOHON", idPemohon);
			sql = r.getSQLDelete("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idPemohon
							+ "] DIHAPUSKAN");

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

	public void savePemohon(String idKategoriPemohon, String idFail, String idPermohonan,
			String idKementerianPemohon, String idAgensiPemohon,
			String idPejabat, HttpSession session) throws Exception {

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

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB
					.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategoriPemohon);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_FAIL", idFail);

			if ("3".equals(idKategoriPemohon)) {
				r.add("ID_KEMENTERIAN", idKementerianPemohon);
				r.add("ID_AGENSI", idAgensiPemohon);
				r.add("ID_PEJABAT", "");

				sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
						+ idAgensiPemohon + "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					r.add("NAMA", rs.getString("NAMA_AGENSI") == null ? "" : rs
							.getString("NAMA_AGENSI").toUpperCase());
					r.add("ALAMAT1_TETAP", rs.getString("ALAMAT1") == null ? ""
							: rs.getString("ALAMAT1").toUpperCase());
					r.add("ALAMAT2_TETAP", rs.getString("ALAMAT2") == null ? ""
							: rs.getString("ALAMAT2").toUpperCase());
					r.add("ALAMAT3_TETAP", rs.getString("ALAMAT3") == null ? ""
							: rs.getString("ALAMAT3").toUpperCase());
					r.add("POSKOD_TETAP", rs.getString("POSKOD") == null ? ""
							: rs.getString("POSKOD").toUpperCase());
					r.add("ID_NEGERITETAP",
							rs.getString("ID_NEGERI") == null ? "" : rs
									.getString("ID_NEGERI").toUpperCase());
				}

			} else if ("8".equals(idKategoriPemohon)) {
				r.add("ID_PEJABAT", idPejabat);
				r.add("ID_KEMENTERIAN", idKementerianPemohon);
				r.add("ID_AGENSI", idAgensiPemohon);

				sql = "SELECT * FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = '"
						+ idPejabat + "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					r.add("NAMA", rs.getString("NAMA_PEJABAT") == null ? ""
							: rs.getString("NAMA_PEJABAT").toUpperCase());
					r.add("ALAMAT1_TETAP", rs.getString("ALAMAT1") == null ? ""
							: rs.getString("ALAMAT1").toUpperCase());
					r.add("ALAMAT2_TETAP", rs.getString("ALAMAT2") == null ? ""
							: rs.getString("ALAMAT2").toUpperCase());
					r.add("ALAMAT3_TETAP", rs.getString("ALAMAT3") == null ? ""
							: rs.getString("ALAMAT3").toUpperCase());
					r.add("POSKOD_TETAP", rs.getString("POSKOD") == null ? ""
							: rs.getString("POSKOD").toUpperCase());
					r.add("ID_BANDARTETAP",
							rs.getString("ID_BANDAR") == null ? "" : rs
									.getString("ID_BANDAR").toUpperCase());
					r.add("ID_NEGERITETAP",
							rs.getString("ID_NEGERI") == null ? "" : rs
									.getString("ID_NEGERI").toUpperCase());
					r.add("NO_TEL",
							rs.getString("NO_TEL") == null ? "" : rs
									.getString("NO_TEL"));
					r.add("NO_FAX",
							rs.getString("NO_FAX") == null ? "" : rs
									.getString("NO_FAX"));
				}

			}
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

//			//TBLPHPPERMOHONANPELEPASAN
//			sql = "SELECT C.CADANGAN_KEGUNAAN, C.ID_LUASASAL, C.LUAS_ASAL, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI, C.KEMAJUAN_TANAH"
//					+ " FROM TBLPHPPERMOHONANPELEPASAN C"
//					+ " WHERE C.ID_PERMOHONAN = '"
//					+ idPermohonan + "'";
//			ResultSet rs = stmt.executeQuery(sql);

//			while (rs.next()) {
//				if(rs.isFirst()){
//					// TBLPHPPERMOHONANPELEPASAN
//					r = new SQLRenderer();
//					long idPermohonanPelepasan = DB
//							.getNextID("TBLPHPPERMOHONANPELEPASAN_SEQ");
//					r.add("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasan);
//					r.add("CADANGAN_KEGUNAAN", rs.getString("CADANGAN_KEGUNAAN") == null ? "" : rs
//							.getString("CADANGAN_KEGUNAAN"));
//					r.add("FLAG_GUNA", rs.getString("FLAG_GUNA") == null ? "" : rs
//							.getString("FLAG_GUNA"));
//					r.add("ID_LUASMHN", rs.getString("ID_LUASMHN") == null ? ""
//							: rs.getString("ID_LUASMHN"));
//					r.add("LUAS_MHN1", rs.getString("LUAS_MHN1") == null ? "" : Utils
//							.formatLuas(rs.getDouble("LUAS_MHN1")));
//					r.add("LUAS_MHN2", rs.getString("LUAS_MHN2") == null ? "" : Utils
//							.formatLuas(rs.getDouble("LUAS_MHN1")));
//					r.add("LUAS_MHN3", rs.getString("LUAS_MHN1") == null ? "" : Utils
//							.formatLuas(rs.getDouble("LUAS_MHN3")));
//					r.add("ID_UNITLUASMHNBERSAMAAN", "2");
//					r.add("LUAS_MHNBERSAMAAN", rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
//							.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
//					r.add("ID_UNITLUASBAKI", "2");
//					r.add("LUAS_BAKI", rs.getString("LUAS_BAKI") == null ? ""
//							: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
//
//					r.add("ID_MASUK", userId);
//					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
//					r.add("ID_PEMOHON", idPemohon);
//					r.add("ID_PERMOHONAN", idPermohonan);
//
//					sql = r.getSQLInsert("TBLPHPPERMOHONANPELEPASAN");
//					stmt.executeUpdate(sql);
//				}
//			}

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

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

	public void setListMaklumatPemohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanListMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMOHON, ID_KATEGORIPEMOHON, ID_KEMENTERIAN, ID_AGENSI, ID_PEJABAT, NAMA"
					+ " FROM TBLPHPPEMOHON WHERE ID_FAIL = '"
					+ idFail
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPemohon",
						rs.getString("ID_PEMOHON") == null ? "" : rs
								.getString("ID_PEMOHON"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				beanListMaklumatPemohon.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTukarguna(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTukarguna = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANPELEPASAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.CADANGAN_KEGUNAAN, C.ID_LUASASAL, C.LUAS_ASAL,"
					+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI, C.KEMAJUAN_TANAH"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJLUAS D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
//			int bil = 1;
			if (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanPelepasan", rs
						.getString("ID_PHPPERMOHONANPELEPASAN") == null ? ""
						: rs.getString("ID_PHPPERMOHONANPELEPASAN"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT"));
				h.put("perkara",
						rs.getString("TAJUK_FAIL") == null ? "" : rs
								.getString("TAJUK_FAIL"));
				h.put("cadanganKegunaan",
						rs.getString("CADANGAN_KEGUNAAN") == null ? "" : rs
								.getString("CADANGAN_KEGUNAAN"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
				h.put("idLuas", rs.getString("ID_LUASASAL") == null ? ""
						: rs.getString("ID_LUASASAL"));
				h.put("keteranganLuasAsal",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("idLuasMohon", rs.getString("ID_LUASMHN") == null ? ""
						: rs.getString("ID_LUASMHN"));
				h.put("luas1",
						rs.getString("LUAS_MHN1") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN1")));
				h.put("luas2",
						rs.getString("LUAS_MHN2") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN2")));
				h.put("luas3",
						rs.getString("LUAS_MHN3") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHN3")));
				h.put("luasBersamaan",
						rs.getString("LUAS_MHNBERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_MHNBERSAMAAN")));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				h.put("kemajuanTanah",
						rs.getString("KEMAJUAN_TANAH") == null ? "" : rs
								.getString("KEMAJUAN_TANAH"));
				beanMaklumatTukarguna.addElement(h);
//				bil++;
			}else{

				h = new Hashtable();
				h.put("idPermohonanPelepasan","");
				h.put("tarikhTerima", "");
				h.put("tarikhSurat", "");
				h.put("noRujukanSurat","");
				h.put("perkara","");
				h.put("cadanganKegunaan","");
				h.put("flagGuna","");
				h.put("luasAsal","");
				h.put("keteranganLuasAsal","");
				h.put("idLuasMohon","");
				h.put("luas1","");
				h.put("luas2","");
				h.put("luas3","");
				h.put("luasBersamaan","");
				h.put("luasBaki", "");
				h.put("kemajuanTanah","");
				beanMaklumatTukarguna.addElement(h);

			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPemohon(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMOHON, ID_KATEGORIPEMOHON, ID_KEMENTERIAN, ID_AGENSI, ID_PEJABAT "
					+ " FROM TBLPHPPEMOHON WHERE ID_PEMOHON = '"
					+ idPemohon
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));

				beanMaklumatPemohon.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAgensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_AGENSI, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, A.ID_AGENSI"
					+ " FROM TBLRUJAGENSI A, TBLRUJNEGERI B WHERE A.ID_NEGERI = B.ID_NEGERI AND A.ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? ""
						: rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				beanMaklumatAgensi.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPejabat", "");
				h.put("idAgensi", "");
				h.put("namaAgensi", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				beanMaklumatAgensi.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPejabatJKPTG(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABATJKPTG"
					+ " FROM TBLRUJPEJABATJKPTG A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABATJKPTG = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABATJKPTG") == null ? ""
						: rs.getString("ID_PEJABATJKPTG"));
				h.put("namaPejabat", rs.getString("NAMA_PEJABAT") == null ? ""
						: rs.getString("NAMA_PEJABAT").toUpperCase());
				h.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("poskod",
						rs.getString("POSKOD") == null ? "" : rs
								.getString("POSKOD"));
				h.put("bandar", rs.getString("NAMA_BANDAR") == null ? "" : rs
						.getString("NAMA_BANDAR").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("noTel", rs.getString("NO_TEL") == null ? "" : rs
						.getString("NO_TEL").toUpperCase());
				h.put("noFax", rs.getString("NO_FAX") == null ? "" : rs
						.getString("NO_FAX").toUpperCase());
				beanMaklumatPejabat.addElement(h);
				bil++;
			}

			if (bil == 1) {
				h = new Hashtable();
				h.put("idPejabat", "");
				h.put("namaPejabat", "");
				h.put("alamat1", "");
				h.put("alamat2", "");
				h.put("alamat3", "");
				h.put("poskod", "");
				h.put("bandar", "");
				h.put("negeri", "");
				h.put("idNegeri", "");
				h.put("noTel", "");
				h.put("noFax", "");
				beanMaklumatPejabat.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiTanahGanti(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTanahGanti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_TANAHGANTI, A.ID_HAKMILIKAGENSI, A.PEGANGAN_HAKMILIK, A.NO_WARTA, F.KETERANGAN, A.NO_LOT, E.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, D.NAMA_MUKIM, C.NAMA_DAERAH, B.NAMA_NEGERI"
					+ " FROM TBLPHPTANAHGANTIPLPSN A, TBLRUJNEGERI B, TBLRUJDAERAH C, TBLRUJMUKIM D, TBLRUJJENISHAKMILIK E, TBLRUJLOT F"
					+ " WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_DAERAH = C.ID_DAERAH(+) AND A.ID_MUKIM = D.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = E.ID_JENISHAKMILIK(+) "
					+ " AND A.ID_LOT = F.ID_LOT(+) AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idTanahGanti",
						rs.getString("ID_TANAHGANTI") == null ? "" : rs
								.getString("ID_TANAHGANTI"));
				h.put("idHakmilikAgensi",
						rs.getString("ID_HAKMILIKAGENSI") == null ? "" : rs
								.getString("ID_HAKMILIKAGENSI"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("noLot",
						rs.getString("KETERANGAN") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("KETERANGAN").toUpperCase()
								+ " "
								+ rs.getString("NO_LOT").toUpperCase());
				h.put("noHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null
						|| rs.getString("NO_HAKMILIK") == null ? "" : rs
						.getString("KOD_JENIS_HAKMILIK").toUpperCase()
						+ " "
						+ rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				listTanahGanti.addElement(h);
				bil++;
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void updatePermohonanTukarguna(String idFail, String idPermohonan,
			String tarikhTerima, String tarikhSurat, String txtPerkara,
			String idPermohonanPelepasan, String txtCadanganKegunaan,
			String idLuasKegunaan, String idLuas, String txtLuasMohon1,
			String txtLuasMohon2, String txtLuasMohon3,
			String txtLuasBersamaan, String txtBakiLuas, HttpSession session)
			throws Exception {

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

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// TBLPFDFAIL
			r.update("ID_FAIL", idFail);
			r.add("TAJUK_FAIL", txtPerkara);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANPELEPASAN
			r = new SQLRenderer();
			r.update("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasan);
			r.add("CADANGAN_KEGUNAAN", txtCadanganKegunaan);
			r.add("FLAG_GUNA", idLuasKegunaan);
//			r.add("ID_LUASASAL", idLuas);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", txtLuasMohon1);
			r.add("LUAS_MHN2", txtLuasMohon2);
			r.add("LUAS_MHN3", txtLuasMohon3);
			r.add("ID_UNITLUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", txtLuasBersamaan);
			r.add("ID_UNITLUASBAKI", "2");
			r.add("LUAS_BAKI", txtBakiLuas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIKEMASKINI");

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

	public void updatePemohon(String idPemohon, String idKategoriPemohon,
			String idKementerianPemohon, String idAgensiPemohon,
			String idPejabat, HttpSession session) throws Exception {

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

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			r.update("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategoriPemohon);

			if ("3".equals(idKategoriPemohon)) {
				r.add("ID_KEMENTERIAN", idKementerianPemohon);
				r.add("ID_AGENSI", idAgensiPemohon);
				r.add("ID_PEJABAT", "");

				sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
						+ idAgensiPemohon + "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					r.add("NAMA", rs.getString("NAMA_AGENSI") == null ? "" : rs
							.getString("NAMA_AGENSI").toUpperCase());
					r.add("ALAMAT1_TETAP", rs.getString("ALAMAT1") == null ? ""
							: rs.getString("ALAMAT1").toUpperCase());
					r.add("ALAMAT2_TETAP", rs.getString("ALAMAT2") == null ? ""
							: rs.getString("ALAMAT2").toUpperCase());
					r.add("ALAMAT3_TETAP", rs.getString("ALAMAT3") == null ? ""
							: rs.getString("ALAMAT3").toUpperCase());
					r.add("POSKOD_TETAP", rs.getString("POSKOD") == null ? ""
							: rs.getString("POSKOD").toUpperCase());
					r.add("ID_NEGERITETAP",
							rs.getString("ID_NEGERI") == null ? "" : rs
									.getString("ID_NEGERI").toUpperCase());
				}

			} else if ("8".equals(idKategoriPemohon)) {
				r.add("ID_PEJABAT", idPejabat);
				r.add("ID_KEMENTERIAN", idKementerianPemohon);
				r.add("ID_AGENSI", idAgensiPemohon);

				sql = "SELECT * FROM TBLRUJPEJABATJKPTG WHERE ID_PEJABATJKPTG = '"
						+ idPejabat + "'";
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()) {
					r.add("NAMA", rs.getString("NAMA_PEJABAT") == null ? ""
							: rs.getString("NAMA_PEJABAT").toUpperCase());
					r.add("ALAMAT1_TETAP", rs.getString("ALAMAT1") == null ? ""
							: rs.getString("ALAMAT1").toUpperCase());
					r.add("ALAMAT2_TETAP", rs.getString("ALAMAT2") == null ? ""
							: rs.getString("ALAMAT2").toUpperCase());
					r.add("ALAMAT3_TETAP", rs.getString("ALAMAT3") == null ? ""
							: rs.getString("ALAMAT3").toUpperCase());
					r.add("POSKOD_TETAP", rs.getString("POSKOD") == null ? ""
							: rs.getString("POSKOD").toUpperCase());
					r.add("ID_BANDARTETAP",
							rs.getString("ID_BANDAR") == null ? "" : rs
									.getString("ID_BANDAR").toUpperCase());
					r.add("ID_NEGERITETAP",
							rs.getString("ID_NEGERI") == null ? "" : rs
									.getString("ID_NEGERI").toUpperCase());
					r.add("NO_TEL",
							rs.getString("NO_TEL") == null ? "" : rs
									.getString("NO_TEL"));
					r.add("NO_FAX",
							rs.getString("NO_FAX") == null ? "" : rs
									.getString("NO_FAX"));
				}

			}
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPemohon
							+ "] DIKEMASKINI");

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
			r.add("ID_STATUS", "1610199");

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("33", "1610199")); // JABATAN
																				// TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] PROSES SETERUSNYA");

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

	//NEW METHOD FOR HAPUS
		public void doHapusFail(String idFail, String idPermohonan,
				String tarikhHapus, String txtSebab, HttpSession session)
				throws Exception {
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
				r.add("ID_STATUS", "999");
				r.add("TARIKH_HAPUS",
						r.unquote("to_date('" + tarikhHapus + "','dd/MM/yyyy')"));
				r.add("CATATAN_HAPUS", txtSebab);

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
				r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610212")); // BATAL
				r.add("AKTIF", "1");
				r.add("ID_FAIL", idFail);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
				stmt.executeUpdate(sql);

				conn.commit();

				AuditTrail.logActivity("999", "4", null, session, "UPD",
						"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] DIHAPUSKAN");

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

	public void doBatalPermohonan(String idFail, String idPermohonan,
			String tarikhBatal, String txtSebab, HttpSession session)
			throws Exception {
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
			r.add("ID_STATUS", "1610212");
			r.add("TARIKH_BATAL",
					r.unquote("to_date('" + tarikhBatal + "','dd/MM/yyyy')"));
			r.add("CATATAN_BATAL", txtSebab);

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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("33", "1610212")); // BATAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610212", "4", null, session, "UPD",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIBATALKAN");

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

	public String getNoFailByIdPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPFDFAIL.NO_FAIL FROM TBLPFDFAIL, TBLPERMOHONAN"
					+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL"
					+ " AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("NO_FAIL");
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

		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanahGanti(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanahGanti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PEGANGAN_HAKMILIK, ID_JENISHAKMILIK, NO_HAKMILIK, ID_LOT, NO_LOT, ID_LUAS, LUAS1, LUAS2, LUAS3, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN,"
					+ " NO_SYIT, NO_PELAN, SYARAT, SEKATAN, KEGUNAAN_TANAH, ID_KATEGORI, ID_SUBKATEGORI, ID_NEGERI, ID_DAERAH, ID_MUKIM"
					+ " FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			System.out.println("cek maklumat=" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			if (rs.next()) {
				h = new Hashtable();
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK"));
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "99999" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "99999" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "99999" : rs
								.getString("ID_LUAS"));
				h.put("luas1",
						rs.getString("LUAS1") == null ? "" : rs
								.getString("LUAS1"));
				h.put("luas2",
						rs.getString("LUAS2") == null ? "" : rs
								.getString("LUAS2"));
				h.put("luas3",
						rs.getString("LUAS3") == null ? "" : rs
								.getString("LUAS3"));
				h.put("idLuas",
						rs.getString("ID_LUAS_BERSAMAAN") == null ? "99999"
								: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("noSyit",
						rs.getString("NO_SYIT") == null ? "" : rs
								.getString("NO_SYIT"));
				h.put("noPelan",
						rs.getString("NO_PELAN") == null ? "" : rs
								.getString("NO_PELAN"));
				h.put("syarat",
						rs.getString("SYARAT") == null ? "" : rs
								.getString("SYARAT"));
				h.put("sekatan",
						rs.getString("SEKATAN") == null ? "" : rs
								.getString("SEKATAN"));
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH"));
				h.put("idNegeri", rs.getString("ID_NEGERI") == null ? "99999"
						: rs.getString("ID_NEGERI"));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "99999"
						: rs.getString("ID_DAERAH"));
				h.put("idMukim", rs.getString("ID_MUKIM") == null ? "99999"
						: rs.getString("ID_MUKIM"));
				h.put("idKategori",
						rs.getString("ID_KATEGORI") == null ? "99999" : rs
								.getString("ID_KATEGORI"));
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "99999" : rs
								.getString("ID_SUBKATEGORI"));

				beanMaklumatTanahGanti.addElement(h);

			} else {
				h = new Hashtable();
				h.put("idHakmilikAgensi", "");
				h.put("idHakmilik", "");
				h.put("peganganHakmilik", "");
				h.put("idJenisHakmilik", "");
				h.put("noHakmilik", "");
				h.put("hakmilik", "");
				h.put("idLot", "");
				h.put("noLot", "");
				h.put("lot", "");
				h.put("idLuas", "");
				h.put("luasBersamaan", "");
				h.put("luas", "");
				h.put("noWarta", "");
				h.put("tarikhWarta", "");
				h.put("idMukim", "");
				h.put("mukim", "");
				h.put("idDaerah", "");
				h.put("daerah", "");
				h.put("idNegeri", "");
				h.put("negeri", "");
				h.put("idKategori", "");
				h.put("kategoriTanah", "");
				h.put("idSubKategori", "");
				h.put("subKategoriTanah", "");
				h.put("syarat", "");
				h.put("sekatan", "");
				h.put("kegunaanTanah", "");
				h.put("idKementerian", "");
				h.put("kementerian", "");
				h.put("idAgensi", "");
				h.put("agensi", "");
				h.put("statusRizab", "");
				beanMaklumatTanahGanti.addElement(h);
			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatLuasTG(String idTanahGanti) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatLuasTG = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_LUAS, LUAS1, LUAS2, LUAS3, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN, ID_LUASBAKI, LUAS_BAKI, FLAG_GUNA"
					+ " FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				h.put("luas1",
						rs.getString("LUAS1") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS1")));
				h.put("luas2",
						rs.getString("LUAS2") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS2")));
				h.put("luas3",
						rs.getString("LUAS3") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS3")));
				h.put("idLuasBersamaan",
						rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs
								.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")));
				h.put("idLuasBaki", rs.getString("ID_LUASBAKI") == null ? ""
						: rs.getString("ID_LUASBAKI"));
				h.put("luasBaki", rs.getString("LUAS_BAKI") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_BAKI")));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				beanMaklumatLuasTG.addElement(h);
				bil++;
			}

		}  catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			}finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikAgensiByPeganganHakmilik(
			String peganganHakmilik, String idKategoriPemohon, String idAgensi)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			if ("3".equals(idKategoriPemohon)) {
				sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
						+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase()
						+ "' AND TBLHTPHAKMILIKAGENSI.ID_AGENSI = '"
						+ idAgensi
						+ "'";
			} else {
				sql = "SELECT TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI FROM TBLHTPHAKMILIK, TBLHTPHAKMILIKAGENSI WHERE TBLHTPHAKMILIK.ID_HAKMILIK = TBLHTPHAKMILIKAGENSI.ID_HAKMILIK"
						+ " AND UPPER(TBLHTPHAKMILIK.PEGANGAN_HAKMILIK) = '"
						+ peganganHakmilik.toUpperCase() + "'";
			}

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI");
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

	public void saveTanahGanti(String idPermohonan,
			String txtPeganganHakmilikTG, String txtNoHakmilikTG,
			String txtNoLotTG, String txtLuas1TG, String txtLuas2TG,
			String txtLuas3TG, String txtLuasBersamaanTG, String txtNoSyitTG,
			String txtNoPelanTG, String txtSyaratTG, String txtSekatanTG,
			String txtKegunaanTanahTG, String idJenisHakmilikTG,
			String idLotTG, String idLuasTG, String idNegeriTG,
			String idDaerahTG, String idMukimTG, String idKategoriTG,
			String idSubKategoriTG, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		String peganganHakmilik = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPTANAHGANTIPLPSN
			long idTanahGanti = DB.getNextID("TBLPHPTANAHGANTIPLPSN_SEQ");
			r.add("ID_TANAHGANTI", idTanahGanti);
			r.add("ID_PERMOHONAN", idPermohonan);

			if (txtPeganganHakmilikTG.trim().length() > 0) {
				r.add("PEGANGAN_HAKMILIK", txtPeganganHakmilikTG);
			} else {
				peganganHakmilik = getKodNegeri(idNegeriTG)
						+ getKodDaerah(idDaerahTG) + getKodMukim(idMukimTG)
						+ getKodJenisHakmilik(idJenisHakmilikTG)
						+ Utils.digitLastFormatted(txtNoHakmilikTG, 8);
				r.add("PEGANGAN_HAKMILIK", peganganHakmilik);
			}

			r.add("ID_JENISHAKMILIK", idJenisHakmilikTG);
			r.add("NO_HAKMILIK", txtNoHakmilikTG);
			r.add("ID_LOT", idLotTG);
			r.add("NO_LOT", txtNoLotTG);
			r.add("ID_LUAS", idLuasTG);
			r.add("LUAS1", txtLuas1TG);
			r.add("LUAS2", txtLuas2TG);
			r.add("LUAS3", txtLuas3TG);
			r.add("ID_LUAS_BERSAMAAN", "2");
			r.add("LUAS_BERSAMAAN", txtLuasBersamaanTG);
			r.add("NO_SYIT", txtNoSyitTG);
			r.add("NO_PELAN", txtNoPelanTG);
			r.add("SYARAT", txtSyaratTG);
			r.add("SEKATAN", txtSekatanTG);
			r.add("KEGUNAAN_TANAH", txtKegunaanTanahTG);
			r.add("ID_KATEGORI", idKategoriTG);
			r.add("ID_SUBKATEGORI", idSubKategoriTG);
			r.add("ID_NEGERI", idNegeriTG);
			r.add("ID_DAERAH", idDaerahTG);
			r.add("ID_MUKIM", idMukimTG);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPTANAHGANTIPLPSN");
			stmt.executeUpdate(sql);

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
			r.add("ID_LAPORANTANAH", idLaporanTanah);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENISTANAH", "G"); // TANAH DIPOHON
			r.add("FLAG_LAPORAN", "1"); // LAWATAN TAPAK
			r.add("ID_HAKMILIK", idTanahGanti);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
							+ "] DIDAFTARKAN");

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

	public String getKodNegeri(String idNegeri) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MAMPU FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ idNegeri + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_MAMPU");
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

	public String getKodDaerah(String idDaerah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_DAERAH FROM TBLRUJDAERAH WHERE ID_DAERAH = '"
					+ idDaerah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_DAERAH");
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

	public String getKodMukim(String idMukim) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_MUKIM FROM TBLRUJMUKIM WHERE ID_MUKIM = '"
					+ idMukim + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_MUKIM");
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

	public String getKodJenisHakmilik(String idJenisHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK WHERE ID_JENISHAKMILIK = '"
					+ idJenisHakmilik + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("KOD_JENIS_HAKMILIK");
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

	public void updateTanahGanti(String idTanahGanti,
			String txtPeganganHakmilikTG, String txtNoHakmilikTG,
			String txtNoLotTG, String txtLuas1TG, String txtLuas2TG,
			String txtLuas3TG, String txtLuasBersamaanTG, String txtNoSyitTG,
			String txtNoPelanTG, String txtSyaratTG, String txtSekatanTG,
			String txtKegunaanTanahTG, String idJenisHakmilikTG,
			String idLotTG, String idLuasTG, String idNegeriTG,
			String idDaerahTG, String idMukimTG, String idKategoriTG,
			String idSubKategoriTG, HttpSession session) throws Exception {

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

			// TBLPHPTANAHGANTIPLPSN
			r.update("ID_TANAHGANTI", idTanahGanti);

			r.add("PEGANGAN_HAKMILIK", txtPeganganHakmilikTG);
			r.add("ID_JENISHAKMILIK", idJenisHakmilikTG);
			r.add("NO_HAKMILIK", txtNoHakmilikTG);
			r.add("ID_LOT", idLotTG);
			r.add("NO_LOT", txtNoLotTG);
			r.add("ID_LUAS", idLuasTG);
			r.add("LUAS1", txtLuas1TG);
			r.add("LUAS2", txtLuas2TG);
			r.add("LUAS3", txtLuas3TG);
			r.add("ID_LUAS_BERSAMAAN", "2");
			r.add("LUAS_BERSAMAAN", txtLuasBersamaanTG);
			r.add("NO_SYIT", txtNoSyitTG);
			r.add("NO_PELAN", txtNoPelanTG);
			r.add("SYARAT", txtSyaratTG);
			r.add("SEKATAN", txtSekatanTG);
			r.add("KEGUNAAN_TANAH", txtKegunaanTanahTG);
			r.add("ID_KATEGORI", idKategoriTG);
			r.add("ID_SUBKATEGORI", idSubKategoriTG);
			r.add("ID_NEGERI", idNegeriTG);
			r.add("ID_DAERAH", idDaerahTG);
			r.add("ID_MUKIM", idMukimTG);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPTANAHGANTIPLPSN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idTanahGanti
							+ "] DIKEMASKINI");

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

	public String getidHakmilikAgensiByIdTanahGanti(String idTanahGanti)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIKAGENSI FROM TBLPHPTANAHGANTIPLPSN WHERE ID_TANAHGANTI = '"
					+ idTanahGanti + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("ID_HAKMILIKAGENSI");
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

	public void hapusTanahGanti(String idTanahGanti, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPTANAHGANTIPLPSN
			r = new SQLRenderer();
			r.add("ID_TANAHGANTI", idTanahGanti);
			sql = r.getSQLDelete("TBLPHPTANAHGANTIPLPSN");
			stmt.executeUpdate(sql);

			// TBLPHPLAPORANTANAH
			r = new SQLRenderer();
			r.add("ID_HAKMILIK", idTanahGanti);
			r.add("FLAG_JENISTANAH", "G");
			sql = r.getSQLDelete("TBLPHPLAPORANTANAH");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + idTanahGanti
							+ "] DIHAPUSKAN");

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

	public Vector getBeanMaklumatTukarguna() {
		return beanMaklumatTukarguna;
	}

	public void setBeanMaklumatTukarguna(Vector beanMaklumatTukarguna) {
		this.beanMaklumatTukarguna = beanMaklumatTukarguna;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}

	public void setBeanMaklumatAgensi(Vector beanMaklumatAgensi) {
		this.beanMaklumatAgensi = beanMaklumatAgensi;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public void setBeanMaklumatPejabat(Vector beanMaklumatPejabat) {
		this.beanMaklumatPejabat = beanMaklumatPejabat;
	}

	public Vector getListTanahGanti() {
		return listTanahGanti;
	}

	public void setListTanahGanti(Vector listTanahGanti) {
		this.listTanahGanti = listTanahGanti;
	}

	public Vector getBeanMaklumatTanahGanti() {
		return beanMaklumatTanahGanti;
	}

	public void setBeanMaklumatTanahGanti(Vector beanMaklumatTanahGanti) {
		this.beanMaklumatTanahGanti = beanMaklumatTanahGanti;
	}

	public Vector getBeanMaklumatLuasTG() {
		return beanMaklumatLuasTG;
	}

	public void setBeanMaklumatLuasTG(Vector beanMaklumatLuasTG) {
		this.beanMaklumatLuasTG = beanMaklumatLuasTG;
	}

	public Vector getBeanListMaklumatPemohon() {
		return beanListMaklumatPemohon;
	}

	public void setBeanListMaklumatPemohon(Vector beanListMaklumatPemohon) {
		this.beanListMaklumatPemohon = beanListMaklumatPemohon;
	}

	public Vector getlistPelan() {
		return listPelan;
	}

	public void setlistPelan(Vector listPelan) {
		this.listPelan = listPelan;
	}

	public Vector getbeanMaklumatPelan() {
		return beanMaklumatPelan;
	}

	public void setbeanMaklumatPelan(Vector beanMaklumatPelan) {
		this.beanMaklumatPelan = beanMaklumatPelan;
	}



}

/**
 * 
 */
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

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

/**
 * modified by hilda
 * 
 */
public class FrmPLPMaklumatPermohonanData {

	private Vector beanMaklumatPelepasan = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatPejabat = null;
	private Vector beanMaklumatAgensi = null;
	private Vector listTanahGanti = null;
	private Vector beanMaklumatTanahGanti = null;
	private Vector listTanahBerkaitan = null;
	private Vector beanMaklumatPelan = null;
	private Vector listPelan = null;
	private static final Log log = LogFactory.getLog(FrmPLPMaklumatPermohonanData.class);

	FrmPNWPopupSenaraiTanahData logicTanah = new FrmPNWPopupSenaraiTanahData();

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
	
	public void setSenaraiPelan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listPelan = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "' "
					+ " AND FLAG_DOKUMEN = 'P'";

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
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idDokumen + "] DIKEMASKINI");

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
			
			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"PELAN PELEPASAN [" + idDokumen + "] DIHAPUSKAN");

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

	public void setMaklumatPelepasan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPelepasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANPELEPASAN, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, C.FLAG_JENIS_PROJEK, C.NAMA_PROJEK, C.CADANGAN_KEGUNAAN, C.ID_LUASASAL, C.LUAS_ASAL,"
					+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_UNITLUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_UNITLUASBAKI, C.LUAS_BAKI, C.KEMAJUAN_TANAH"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANPELEPASAN C, TBLRUJLUAS D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
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
				h.put("flagJenisProjek",
						rs.getString("FLAG_JENIS_PROJEK") == null ? "" : rs
								.getString("FLAG_JENIS_PROJEK").toUpperCase());
				h.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? ""
						: rs.getString("NAMA_PROJEK"));
				h.put("cadanganKegunaan",
						rs.getString("CADANGAN_KEGUNAAN") == null ? "" : rs
								.getString("CADANGAN_KEGUNAAN"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_ASAL")));
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
				beanMaklumatPelepasan.addElement(h);
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

	public void updatePermohonanPelepasan(String idFail, String idPermohonan,
			String tarikhTerima, String tarikhSurat, String txtPerkara,
			String idPermohonanPelepasan, String idJenisProjek,
			String txtNamaProjek, String idLuasKegunaan, String idLuas,
			String txtLuasMohon1, String txtLuasMohon2, String txtLuasMohon3,
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
			r.add("FLAG_JENIS_PROJEK", idJenisProjek);
			r.add("NAMA_PROJEK", txtNamaProjek);
			r.add("FLAG_GUNA", idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", Utils.RemoveSymbol(txtLuasMohon1));
			r.add("LUAS_MHN2", Utils.RemoveSymbol(txtLuasMohon2));
			r.add("LUAS_MHN3", Utils.RemoveSymbol(txtLuasMohon3));
			r.add("ID_UNITLUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", Utils.RemoveSymbol(txtLuasBersamaan));
			r.add("ID_UNITLUASBAKI", "2");
			r.add("LUAS_BAKI", Utils.RemoveSymbol(txtBakiLuas));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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

	public void updateKemajuanTanah(String idPermohonanPelepasan,
			String kemajuanTanah, HttpSession session) throws Exception {

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

			// TBLPHPPERMOHONANPELEPASAN
			r.update("ID_PHPPERMOHONANPELEPASAN", idPermohonanPelepasan);
//			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("KEMAJUAN_TANAH", kemajuanTanah);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + idPermohonanPelepasan + "] DIKEMASKINI");

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

	public void setMaklumatPemohon(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMOHON, NAMA, NO_PENGENALAN, ALAMAT1_TETAP, ALAMAT2_TETAP, ALAMAT3_TETAP, ID_KEMENTERIAN, ID_AGENSI,"
					+ " POSKOD_TETAP, ID_BANDARTETAP, ID_NEGERITETAP, NO_TEL, NO_FAX,"
					+ " EMEL, ID_KATEGORIPEMOHON, ID_PEJABAT "
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
				h.put("idPejabat",
						rs.getString("ID_PEJABAT") == null ? "" : rs
								.getString("ID_PEJABAT"));
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("noPendaftaran",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri", rs.getString("ID_NEGERITETAP") == null ? ""
						: rs.getString("ID_NEGERITETAP"));
				h.put("idBandar", rs.getString("ID_BANDARTETAP") == null ? ""
						: rs.getString("ID_BANDARTETAP"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));

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
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
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

	public void setMaklumatPejabat(String idPejabat) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPejabat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NAMA_PEJABAT, A.ALAMAT1, A.ALAMAT2, A.ALAMAT3, A.POSKOD, A.ID_NEGERI, B.NAMA_NEGERI, C.KETERANGAN AS NAMA_BANDAR, A.NO_TEL, A.NO_FAX, A.ID_PEJABAT"
					+ " FROM TBLRUJPEJABAT A, TBLRUJNEGERI B, TBLRUJBANDAR C WHERE A.ID_NEGERI = B.ID_NEGERI(+) AND A.ID_BANDAR = C.ID_BANDAR(+) AND A.ID_PEJABAT = '"
					+ idPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPejabat", rs.getString("ID_PEJABAT") == null ? "" : rs
						.getString("ID_PEJABAT").toUpperCase());
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
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFax",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
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

	public void updatePemohon(String idPemohon, String idKategoriPemohon,
			String nama, String noPendaftaran, String alamat1, String alamat2,
			String alamat3, String poskod, String idBandar, String idNegeri,
			String emel, String noTel, String noFaks, String idPejabat,
			String idKementerianPemohon, String idAgensiPemohon,
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

			// TBLPHPPEMOHON
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

			}
			if ("1".equals(idKategoriPemohon) || "2".equals(idKategoriPemohon)
					|| "6".equals(idKategoriPemohon)
					|| "7".equals(idKategoriPemohon)) {
				r.add("NAMA", nama);
				r.add("NO_PENGENALAN", noPendaftaran);
				r.add("ALAMAT1_TETAP", alamat1);
				r.add("ALAMAT2_TETAP", alamat2);
				r.add("ALAMAT3_TETAP", alamat3);
				r.add("POSKOD_TETAP", poskod);
				r.add("ID_BANDARTETAP", idBandar);
				r.add("ID_NEGERITETAP", idNegeri);
				r.add("NO_TEL", noTel);
				r.add("NO_FAX", noFaks);
				r.add("EMEL", emel);

			} else if ("8".equals(idKategoriPemohon)) {
				r.add("ID_PEJABAT", idPejabat);

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

			} else if ("4".equals(idKategoriPemohon)
					|| "5".equals(idKategoriPemohon)) {
				r.add("ID_PEJABAT", idPejabat);

				sql = "SELECT * FROM TBLRUJPEJABAT WHERE ID_PEJABAT = '"
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
					"FAIL PELEPASAN [" + idPemohon
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

	public void setSenaraiTanahGanti(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTanahGanti = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_TANAHGANTI, A.PEGANGAN_HAKMILIK, F.KETERANGAN, A.NO_LOT, E.KOD_JENIS_HAKMILIK, A.NO_HAKMILIK, D.NAMA_MUKIM, C.NAMA_DAERAH, B.NAMA_NEGERI"
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
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
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
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
			}
 finally {
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
			}
 finally {
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
					"FAIL PELEPASAN [" + idTanahGanti + "] DIKEMASKINI");

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

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
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
					"FAIL PELEPASAN [" + idTanahGanti + "] DIHAPUSKAN");
			
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610199")); // JABATAN
																				// TEKNIKAL
			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// PERPARE DATA FOR KERTAS CADANGAN

			String Yb = "";
			String flagGuna = "";
			String kegunaanTanah = "";
			String lot = "";
			String noHak = "";
			String mukimTanah = "";
			String daerahTanah = "";
			String negeriTanah = "";
			String namaProjek = "";
			String nilaian = "";
			String nilaianWords = "";
			String tarikhSurat = "";
			String luasMohonBersamaan = "";
			String pemohon = "";

			sql = "SELECT "
					+ "TBLPFDFAIL.ID_FAIL, "
					+ "CASE "
					+ "WHEN TBLPHPPERMOHONANPELEPASAN.FLAG_GUNA = '1' THEN 'keseluruhan' "
					+ "WHEN TBLPHPPERMOHONANPELEPASAN.FLAG_GUNA = '2' THEN 'sebahagian' "
					+ "END AS FLAG_GUNA, "
					+ "TBLPHPPERMOHONANPELEPASAN.NAMA_PROJEK, "
					+ "TBLPHPHAKMILIKPERMOHONAN.NILAIAN, "
					+ "INITCAP(TBLPHPPEMOHON.NAMA) AS NAMA_PEMOHON, "
					+ "TO_CHAR(TBLPERMOHONAN.TARIKH_SURAT,'DD/MM/YYYY') AS TARIKH_SURAT, "
					+ "REPLACE(INITCAP(REPLACE(TRIM(DAERAHHAKMILIK.NAMA_DAERAH),'&','&#38;')),',')  AS DAERAH_HAKMILIK, "
					+ "REPLACE(REPLACE(INITCAP(REPLACE(TRIM(NEGERIHAKMILIK.NAMA_NEGERI),'&','&#38;')),','),'Negeri','') AS NEGERI_HAKMILIK, "
					+ "REPLACE(INITCAP(REPLACE(TRIM(TBLRUJMUKIM.NAMA_MUKIM),'&','&#38;')),',')  AS MUKIM_HAKMILIK, "
					+ "CASE  "
					+ "WHEN SUBSTR(ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4),1,1) = '.' THEN '0'|| ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4) "
					+ "WHEN SUBSTR(ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4),1,1) != '.' THEN '' || ROUND(TBLPHPPERMOHONANPELEPASAN.LUAS_MHNBERSAMAAN,4) "
					+ "END AS LUAS_MHN, "
					+ "CASE  "
					+ "WHEN SUBSTR(ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4),1,1) = '.' THEN '0'|| ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4) "
					+ "WHEN SUBSTR(ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4),1,1) != '.' THEN '' || ROUND(TBLHTPHAKMILIK.LUAS_BERSAMAAN,4) "
					+ "END AS LUAS_BERSAMAAN, "
					+ "CASE "
					+ "WHEN TBLHTPHAKMILIK.ID_LOT = '1' THEN 'No. Lot' "
					+ "WHEN TBLHTPHAKMILIK.ID_LOT = '3' THEN 'No. PT' "
					+ "END AS JENIS_LOT, "
					+ "INITCAP(TBLRUJLOT.KETERANGAN) || ' '||  TBLHTPHAKMILIK.NO_LOT AS LOT, "
					+ "CASE "
					+ "WHEN TBLHTPHAKMILIK.NO_WARTA IS NULL THEN TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK ||' '||TBLHTPHAKMILIK.NO_HAKMILIK  "
					+ "WHEN TBLHTPHAKMILIK.NO_HAKMILIK IS NULL THEN TBLHTPHAKMILIK.NO_WARTA  "
					+ "END AS NO_HAK, "
					+ "CASE "
					+ "WHEN TBLHTPHAKMILIK.NO_WARTA IS NULL THEN 'No. Hakmilik' "
					+ "WHEN TBLHTPHAKMILIK.NO_HAKMILIK IS NULL THEN 'No. Rizab' "
					+ "END AS JENIS_TANAH, "
					+ "INITCAP(TBLHTPHAKMILIK.KEGUNAAN_TANAH) AS KEGUNAAN_TANAH, "
					+ "INITCAP(TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN) AS KJP_TANAH, "
					+ "INITCAP(REPLACE(TBLRUJKEMENTERIAN.NAMA_KEMENTERIAN,'KEMENTERIAN','')) AS ULASAN_MENTERI "
					+ "FROM "
					+ "TBLPFDFAIL, "
					+ "TBLPERMOHONAN, "
					+ "TBLPHPPEMOHON, "
					+ "TBLPHPHAKMILIKPERMOHONAN, "
					+ "TBLPHPPERMOHONANPELEPASAN, "
					+ "TBLHTPHAKMILIKAGENSI, "
					+ "TBLHTPHAKMILIK, "
					+ "TBLPHPKERTASKERJAPELEPASAN, "
					+ "TBLRUJDAERAH DAERAHHAKMILIK, "
					+ "TBLRUJNEGERI NEGERIHAKMILIK, "
					+ "TBLRUJKEMENTERIAN, "
					+ "TBLRUJMUKIM, "
					+ "TBLRUJLOT, "
					+ "TBLRUJJENISHAKMILIK "
					+ "WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPHAKMILIKPERMOHONAN.ID_PERMOHONAN "
					+ "AND TBLPERMOHONAN.ID_PEMOHON = TBLPHPPEMOHON.ID_PEMOHON "
					+ "AND TBLPHPHAKMILIKPERMOHONAN.ID_HAKMILIKAGENSI = TBLHTPHAKMILIKAGENSI.ID_HAKMILIKAGENSI "
					+ "AND TBLHTPHAKMILIKAGENSI.ID_HAKMILIK = TBLHTPHAKMILIK.ID_HAKMILIK "
					+ "AND TBLHTPHAKMILIKAGENSI.ID_KEMENTERIAN = TBLRUJKEMENTERIAN.ID_KEMENTERIAN "
					+ "AND TBLHTPHAKMILIK.ID_DAERAH = DAERAHHAKMILIK.ID_DAERAH(+) "
					+ "AND TBLHTPHAKMILIK.ID_NEGERI = NEGERIHAKMILIK.ID_NEGERI(+)  "
					+ "AND TBLHTPHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) "
					+ "AND TBLHTPHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+) "
					+ "AND TBLHTPHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPPERMOHONANPELEPASAN.ID_PERMOHONAN "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPKERTASKERJAPELEPASAN.ID_PERMOHONAN "
					+ "AND TBLPHPKERTASKERJAPELEPASAN.FLAG_KERTAS = 1 "
					+ "AND TBLPERMOHONAN.ID_PERMOHONAN = '" + idPermohonan
					+ "'";

			ResultSet rsKertasCadangan = stmt.executeQuery(sql);
			if (rsKertasCadangan.next()) {
				Yb = rsKertasCadangan.getString("ULASAN_MENTERI") == null ? ""
						: rsKertasCadangan.getString("ULASAN_MENTERI");
				flagGuna = rsKertasCadangan.getString("FLAG_GUNA") == null ? ""
						: rsKertasCadangan.getString("FLAG_GUNA");
				kegunaanTanah = rsKertasCadangan.getString("KEGUNAAN_TANAH") == null ? ""
						: rsKertasCadangan.getString("KEGUNAAN_TANAH");
				lot = rsKertasCadangan.getString("LOT") == null ? ""
						: rsKertasCadangan.getString("LOT");
				noHak = rsKertasCadangan.getString("NO_HAK") == null ? ""
						: rsKertasCadangan.getString("NO_HAK");
				mukimTanah = rsKertasCadangan.getString("MUKIM_HAKMILIK") == null ? ""
						: rsKertasCadangan.getString("MUKIM_HAKMILIK");
				daerahTanah = rsKertasCadangan.getString("DAERAH_HAKMILIK") == null ? ""
						: rsKertasCadangan.getString("DAERAH_HAKMILIK");
				negeriTanah = rsKertasCadangan.getString("NEGERI_HAKMILIK") == null ? ""
						: rsKertasCadangan.getString("NEGERI_HAKMILIK");
				namaProjek = rsKertasCadangan.getString("NAMA_PROJEK") == null ? ""
						: rsKertasCadangan.getString("NAMA_PROJEK");
				tarikhSurat = rsKertasCadangan.getString("TARIKH_SURAT") == null ? ""
						: rsKertasCadangan.getString("TARIKH_SURAT");
				luasMohonBersamaan = rsKertasCadangan.getString("LUAS_MHN") == null ? ""
						: rsKertasCadangan.getString("LUAS_MHN");
				pemohon = rsKertasCadangan.getString("NAMA_PEMOHON") == null ? ""
						: rsKertasCadangan.getString("NAMA_PEMOHON");

			}
			String tujuan = "Kertas cadangan ini bertujuan untuk memohon persetujuan Y.B Menteri"
					+ Yb
					+ " Malaysia ke atas permohonan penyerahan balik "
					+ flagGuna
					+ " tanah milik Persekutuan tapak kegunaan "
					+ kegunaanTanah
					+ " di atas "
					+ lot
					+ ", "
					+ noHak
					+ ", "
					+ mukimTanah
					+ ", Daerah "
					+ daerahTanah
					+ ", Negeri "
					+ negeriTanah
					+ " kepada "
					+ pemohon
					+ " bagi tujuan "
					+ namaProjek + ".";
			String latarBelakangTanah = pemohon
					+ " telah mengemukakan cadangan untuk mendapatkan tanah milik Persekutuan "
					+ lot + ", " + noHak + ", " + mukimTanah + ", Daerah "
					+ daerahTanah + ", Negeri " + negeriTanah + " seluas "
					+ luasMohonBersamaan + " hektar bagi tujuan " + namaProjek
					+ ". Sesalinan hakmilik seperti di LAMPIRAN 'A'.";
			String laporanNilaian = "Jabatan Penilaian dan Perkhidmatan Harta Negeri "
					+ negeriTanah
					+ ", melalui suratnya di LAMPIRAN 'B' memaklumkan nilaian bagi "
					+ lot
					+ ", "
					+ noHak
					+ ", "
					+ mukimTanah
					+ ", Daerah "
					+ daerahTanah
					+ ", Negeri "
					+ negeriTanah
					+ " memaklumkan bahawa nilaian bagi keluasan "
					+ luasMohonBersamaan + " hektar dengan jumlah sebanyak ";
			String pemohonSerahBalik = "Permohonan daripada "
					+ pemohon
					+ " yang bertarikh "
					+ tarikhSurat
					+ " telah mengemukakan permohonan kepada Jabatan Ketua Pengarah Tanah dan Galian Persekutuan Putrajaya seperti sesalinan suratnya di LAMPIRAN 'D', mengenai penyerahan balik "
					+ flagGuna + " tanah milik persekutuan di atas " + lot
					+ ", " + noHak + ", " + mukimTanah + ", Daerah "
					+ daerahTanah + ", Negeri " + negeriTanah + " bagi tujuan "
					+ namaProjek + ".";
			String perakuanPTP = "Pesuruhjaya Tanah Persekutuan mengangkat permohonan penyerahan balik "
					+ flagGuna
					+ " tanah milik Persekutuan bagi "
					+ lot
					+ ", "
					+ noHak
					+ ", "
					+ mukimTanah
					+ ", Daerah "
					+ daerahTanah
					+ ", Negeri "
					+ negeriTanah
					+ " ("
					+ kegunaanTanah
					+ ") kepada "
					+ pemohon
					+ " bagi tujuan "
					+ namaProjek
					+ " untuk mendapat persetujuan Y.B Menteri"
					+ Yb
					+ " Malaysia.";

			// TBLPHPKERTASKERJAPELEPASAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.update("FLAG_KERTAS", "1");
			r.add("TUJUAN", tujuan);
			r.add("LATAR_BELAKANGTANAH", latarBelakangTanah);
			r.add("LAPORAN_NILAIAN", laporanNilaian);
			r.add("PEMOHON", pemohonSerahBalik);
			r.add("PERAKUAN_PTP", perakuanPTP);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPKERTASKERJAPELEPASAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610199", "4", null, session, "UPD",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
	//TODO
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
//			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("34", "1610212")); // BATAL
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
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiTanahBerkaitan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listTanahBerkaitan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PHPHMP.ID_HAKMILIKAGENSI,  PHPHMP.ID_HAKMILIKPERMOHONAN, PHPHM.ID_HAKMILIK, PHPHM.PEGANGAN_HAKMILIK, PHPHM.ID_JENISHAKMILIK, RUJJENISHM.KOD_JENIS_HAKMILIK, PHPHM.NO_HAKMILIK,"
					+ " PHPHM.ID_LOT, RUJLOT.KETERANGAN AS JENIS_LOT, PHPHM.NO_LOT, PHPHM.ID_LUAS AS ID_LUAS_BERSAMAAN, PHPHM.LUAS AS LUAS_BERSAMAAN, RUJLUAS.KETERANGAN AS JENIS_LUAS,"
					+ " PHPHM.NO_WARTA, PHPHM.TARIKH_WARTA, PHPHM.ID_MUKIM, RUJMUKIM.NAMA_MUKIM, PHPHM.ID_DAERAH, RUJDAERAH.NAMA_DAERAH, PHPHM.ID_NEGERI, RUJNEGERI.NAMA_NEGERI,"
					+ " PHPHM.ID_KATEGORI AS ID_KATEGORI, RUJKATEGORI.KETERANGAN AS KATEGORI, PHPHM.ID_SUBKATEGORI, RUJSUBKATEGORI.KETERANGAN AS SUBKATEGORI, PHPHM.KEGUNAAN_TANAH, PHPHM.SYARAT, PHPHM.SEKATAN,"
					+ " PHPHM.ID_AGENSI, RUJAGENSI.NAMA_AGENSI, RUJAGENSI.ID_KEMENTERIAN, RUJKEMENTERIAN.NAMA_KEMENTERIAN,"
					+ " PHPHM.TARIKH_BORANGK, PHPHM.CATATAN, PHPHM.NO_PERSERAHAN, PHPHM.TARIKH_CATATAN, PHPHM.TARIKH_TERIMA,"
					+ " PHPHMP.FLAG_BORANGK"

					+ " FROM TBLPHPHAKMILIKPERMOHONAN PHPHMP, TBLPHPHAKMILIK PHPHM, TBLRUJJENISHAKMILIK RUJJENISHM, TBLRUJLOT RUJLOT, TBLRUJLUAS RUJLUAS,"
					+ " TBLRUJMUKIM RUJMUKIM, TBLRUJDAERAH RUJDAERAH, TBLRUJNEGERI RUJNEGERI, TBLRUJKATEGORI RUJKATEGORI, TBLRUJSUBKATEGORI RUJSUBKATEGORI, TBLRUJAGENSI RUJAGENSI, TBLRUJKEMENTERIAN RUJKEMENTERIAN"

					+ " WHERE PHPHMP.ID_HAKMILIKPERMOHONAN = PHPHM.ID_HAKMILIKPERMOHONAN AND PHPHM.ID_JENISHAKMILIK = RUJJENISHM.ID_JENISHAKMILIK(+) AND PHPHM.ID_LOT = RUJLOT.ID_LOT(+)"
					+ " AND PHPHM.ID_LUAS = RUJLUAS.ID_LUAS(+) AND PHPHM.ID_MUKIM = RUJMUKIM.ID_MUKIM(+) AND PHPHM.ID_DAERAH = RUJDAERAH.ID_DAERAH(+) AND PHPHM.ID_NEGERI = RUJNEGERI.ID_NEGERI(+)"
					+ " AND PHPHM.ID_KATEGORI = RUJKATEGORI.ID_KATEGORI(+) AND PHPHM.ID_SUBKATEGORI = RUJSUBKATEGORI.ID_SUBKATEGORI(+) AND PHPHM.ID_AGENSI = RUJAGENSI.ID_AGENSI(+) AND RUJAGENSI.ID_KEMENTERIAN = RUJKEMENTERIAN.ID_KEMENTERIAN(+)"
					+ " AND PHPHMP.FLAG_HAKMILIK = 'T'"
					+ " AND PHPHMP.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idHakmilikPermohonan", rs
						.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs
						.getString("ID_HAKMILIKPERMOHONAN").toUpperCase());
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());

				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("idJenisHakmilik",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("hakmilik",
						(rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase())
								+ " "
								+ (rs.getString("NO_HAKMILIK") == null ? ""
										: rs.getString("NO_HAKMILIK")));
				h.put("idLot",
						rs.getString("ID_LOT") == null ? "" : rs
								.getString("ID_LOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("lot",
						(rs.getString("JENIS_LOT") == null ? "" : rs.getString(
								"JENIS_LOT").toUpperCase())
								+ " "
								+ (rs.getString("NO_LOT") == null ? "" : rs
										.getString("NO_LOT")));
				h.put("idLuas", rs.getString("ID_LUAS_BERSAMAAN") == null ? ""
						: rs.getString("ID_LUAS_BERSAMAAN"));
				h.put("luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
				h.put("luas",
						(rs.getString("LUAS_BERSAMAAN") == null ? "" : Utils
								.formatLuas(rs.getDouble("LUAS_BERSAMAAN")))
								+ " "
								+ (rs.getString("JENIS_LUAS") == null ? "" : rs
										.getString("JENIS_LUAS")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("idMukim",
						rs.getString("ID_MUKIM") == null ? "" : rs
								.getString("ID_MUKIM"));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("idDaerah",
						rs.getString("ID_DAERAH") == null ? "" : rs
								.getString("ID_DAERAH"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("idNegeri",
						rs.getString("ID_NEGERI") == null ? "" : rs
								.getString("ID_NEGERI"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				h.put("idKategori", rs.getString("ID_KATEGORI") == null ? ""
						: rs.getString("ID_KATEGORI"));
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("idSubKategori",
						rs.getString("ID_SUBKATEGORI") == null ? "" : rs
								.getString("ID_SUBKATEGORI"));
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kegunaanTanah",
						rs.getString("KEGUNAAN_TANAH") == null ? "" : rs
								.getString("KEGUNAAN_TANAH").toUpperCase());
				h.put("idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("idAgensi",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("tarikhBorangK",
						rs.getDate("TARIKH_BORANGK") == null ? "" : sdf
								.format(rs.getDate("TARIKH_BORANGK")));
				h.put("catatan", rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN").toUpperCase());
				h.put("noPerserahan",
						rs.getString("NO_PERSERAHAN") == null ? "" : rs
								.getString("NO_PERSERAHAN").toUpperCase());
				h.put("tarikhCatatan",
						rs.getDate("TARIKH_CATATAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_CATATAN")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("flagBorangK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK").toUpperCase());
				listTanahBerkaitan.addElement(h);
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

	public void doHapus(String idPermohonan, String idHakmilikPermohonan, HttpSession session)
			throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPHAKMILIK
			r = new SQLRenderer();
			r.add("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
			sql = r.getSQLDelete("TBLPHPHAKMILIK");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			r.add("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
			sql = r.getSQLDelete("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public Vector getBeanMaklumatPelepasan() {
		return beanMaklumatPelepasan;
	}

	public void setBeanMaklumatPelepasan(Vector beanMaklumatPelepasan) {
		this.beanMaklumatPelepasan = beanMaklumatPelepasan;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getBeanMaklumatPejabat() {
		return beanMaklumatPejabat;
	}

	public Vector getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
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

	public Vector getListTanahBerkaitan() {
		return listTanahBerkaitan;
	}

	public void setListTanahBerkaitan(Vector listTanahBerkaitan) {
		this.listTanahBerkaitan = listTanahBerkaitan;
	}

	public Vector getBeanMaklumatPelan() {
		return beanMaklumatPelan;
	}

	public void setBeanMaklumatPelan(Vector beanMaklumatPelan) {
		this.beanMaklumatPelan = beanMaklumatPelan;
	}

	public Vector getListPelan() {
		return listPelan;
	}

	public void setListPelan(Vector listPelan) {
		this.listPelan = listPelan;
	}

	public void simpanDaftarHakmilikBaru(String idPermohonan,
			String idHakmilikAgensi, String idHakmilikSementara,
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

			// TBLPHPHAKMILIKPERMOHONAN
			r = new SQLRenderer();
			long idhakmilikPermohonan = DB
					.getNextID("TBLPHPHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensi);
			r.add("ID_HAKMILIKSEMENTARA", idHakmilikSementara);
			r.add("FLAG_HAKMILIK", "T");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPHAKMILIK
			logicTanah.setMaklumatTanah(idHakmilikAgensi, idHakmilikSementara);
			if (logicTanah.getBeanMaklumatTanah().size() != 0) {
				Hashtable hashTanah = (Hashtable) logicTanah.getBeanMaklumatTanah().get(0);

				r = new SQLRenderer();
				long idHakmilik = DB.getNextID("TBLPHPHAKMILIK_SEQ");
				r.add("ID_HAKMILIK", idHakmilik);
				r.add("ID_HAKMILIKPERMOHONAN", idhakmilikPermohonan);
				r.add("PEGANGAN_HAKMILIK", hashTanah.get("peganganHakmilik"));
				r.add("ID_NEGERI", hashTanah.get("idNegeri"));
				r.add("ID_DAERAH", hashTanah.get("idDaerah"));
				r.add("ID_MUKIM", hashTanah.get("idMukim"));
				r.add("NO_WARTA", hashTanah.get("noWarta"));
				r.add("TARIKH_WARTA",
						r.unquote("to_date('" + hashTanah.get("tarikhWarta")
								+ "','dd/MM/yyyy')"));
				r.add("ID_JENISHAKMILIK", hashTanah.get("idJenisHakmilik"));
				r.add("NO_HAKMILIK", hashTanah.get("noHakmilik"));
				r.add("ID_LOT", hashTanah.get("idLot"));
				r.add("NO_LOT", hashTanah.get("noLot"));
				r.add("ID_LUAS", hashTanah.get("idLuas"));
				r.add("LUAS", hashTanah.get("luasBersamaan"));
				r.add("SYARAT", hashTanah.get("syarat"));
				r.add("SEKATAN", hashTanah.get("sekatan"));
				r.add("KEGUNAAN_TANAH", hashTanah.get("kegunaanTanah"));
				r.add("ID_KATEGORI", hashTanah.get("idKategori"));
				r.add("ID_SUBKATEGORI", hashTanah.get("idSubKategori"));
				r.add("ID_KEMENTERIAN", hashTanah.get("idKementerian"));
				r.add("ID_AGENSI", hashTanah.get("idAgensi"));

				sql = r.getSQLInsert("TBLPHPHAKMILIK");
				stmt.executeUpdate(sql);
				
				// TBLPHPLAPORANTANAH
				r = new SQLRenderer();
				long idLaporanTanah = DB.getNextID("TBLPHPLAPORANTANAH_SEQ");
				r.add("ID_LAPORANTANAH", idLaporanTanah);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("FLAG_JENISTANAH", "P"); // TANAH DIPOHON
				r.add("FLAG_LAPORAN", "1"); // LAWATAN TAPAK
				r.add("ID_HAKMILIK", idhakmilikPermohonan);

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLPHPLAPORANTANAH");
				stmt.executeUpdate(sql);	

			}

			conn.commit();
			
			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL PELEPASAN [" + getNoFailByIdPermohonan(idPermohonan)
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
}

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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.php2.modelTable.TblPhpPermohonanSewaModel;

public class FrmPYWMaklumatPermohonanData {

	private Vector beanMaklumatSewa = null;
	private Vector beanMaklumatPemohon = null;
	private Vector listPengarah = null;
	private Vector listTanahBerkaitan = null;
	private Vector beanSyorTolakRingkas = null;
	private Vector beanMaklumatPenamatanPenyewaan = null;
	private Vector beanMaklumatSenaraiSemak = null; //UPDATE BY AIN 12052017
	private Vector listLampiran = null;
	private Vector beanMaklumatLampiran = null;
	private static final Log log = LogFactory.getLog(FrmPYWMaklumatPermohonanData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void doSetujuTolakRingkas(String idPermohonan,String idFail, String idNegeriUser, HttpSession session) throws Exception {
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

			// TBLPHPPERMOHONAN
//			r = new SQLRenderer();
//			r.update("ID_PERMOHONAN", idPermohonan);
//			r.add("KEPUTUSAN", "TR");
//			r.add("ID_KEMASKINI", userId);
//			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
//
//			sql = r.getSQLUpdate("TBLPHPPERMOHONAN");
//			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1615199");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("ID_FAIL", idFail);
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahNegeri");
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_BUKA", "T");
			r.add("FLAG_PINDAAN", "T");
			r.add("FLAG_MT", "T");
			r.add("FLAG_REP", "0");
//			r.add("FLAG_MAKLUMAT_TAMBAHAN", "Y");
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_PEGAWAI", userId);
			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
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

	public void doTangguhMaklumatTambahan(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
			r = new SQLRenderer();
			r.update("ID_FAIL", idFail);
			r.update("FLAG_AKTIF", "Y");

			r.add("FLAG_AKTIF", "T");

			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("ID_FAIL", idFail);
			r.add("ROLE", "(PHP)PYWPenolongPegawaiTanahNegeri");
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_BUKA", "T");
			r.add("FLAG_PINDAAN", "T");
			r.add("FLAG_MT", "T");
			r.add("FLAG_REP", "0");
			r.add("FLAG_MAKLUMAT_TAMBAHAN", "Y");
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_PEGAWAI", userId);
			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idFail + "] DIKEMASKINI");

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

	public void doPengesahanPengarahNegeri(String idFail, String idNegeriUser,
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

			// TBLPHPLOGTUGASAN
//			r = new SQLRenderer();
//			r.update("ID_FAIL", idFail);
//			r.update("FLAG_AKTIF", "Y");
//
//			r.add("FLAG_AKTIF", "T");
//
//			sql = r.getSQLUpdate("TBLPHPLOGTUGASAN");
//			stmt.executeUpdate(sql);

			r = new SQLRenderer();
			long idTugasan = DB.getNextID("TBLPHPLOGTUGASAN_SEQ");
			r.add("ID_TUGASAN", idTugasan);
			r.add("ID_NEGERI", idNegeriUser);
			r.add("ROLE", "(PHP)PYWPengarahNegeri");
			r.add("TARIKH_DITUGASKAN", r.unquote("SYSDATE"));
			r.add("ID_FAIL", idFail);
			r.add("FLAG_AKTIF", "Y");
			r.add("FLAG_BUKA", "T");
			r.add("FLAG_PINDAAN", "T");
			r.add("FLAG_MT", "T");
			r.add("FLAG_REP", "0");

			r.add("ID_PEGAWAI_SEBELUM", userId);
			r.add("ID_NEGERI_SEBELUM", idNegeriUser);

			sql = r.getSQLInsert("TBLPHPLOGTUGASAN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idFail + "] DIKEMASKINI");

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

	public void doSimpanSyorTolakRingkas(String idPermohonanSewa, String idPermohonan, String sebabTolakRingkas, String tarikhHantarSurat, String catatanMaklumatTambahan, HttpSession session) throws Exception{
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

			String THS = "to_date('" + tarikhHantarSurat + "','dd/MM/yyyy')";

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("ID_STATUS", "1615200");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
//			long idPermohonanSewa = DB
//					.getNextID("TBLPHPPERMOHONANSEWA_SEQ");
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			r.add("SEBAB_TOLAK_RINGKAS", sebabTolakRingkas);	//CTH shj
			r.add("TARIKH_SURAT_TOLAK_RINGKAS", r.unquote(THS));	//CTH shj
			r.add("CATATAN_TOLAK_RINGKAS", catatanMaklumatTambahan);
//			r.add("ID_PERMOHONAN", idPermohonan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "INS",
					"FAIL [" + idPermohonan + "] DIDAFTARKAN");

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

	public void setSyorTolakRingkas(String idPermohonan, String userId) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanSyorTolakRingkas = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANSEWA, C.SEBAB_TOLAK_RINGKAS, C.TARIKH_SURAT_TOLAK_RINGKAS, C.CATATAN_TOLAK_RINGKAS"
				+ " FROM TBLPHPPERMOHONANSEWA C"
				+ " WHERE C.ID_PERMOHONAN = '"+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
//			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idPermohonanSewa",rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs.getString("ID_PHPPERMOHONANSEWA"));
				//tarikhSuratTolakRingkas
				h.put("tarikhSuratTolakRingkas", rs.getDate("TARIKH_SURAT_TOLAK_RINGKAS") == null ? "": sdf.format(rs.getDate("TARIKH_SURAT_TOLAK_RINGKAS")));
				//sebabTolakRingkas
				h.put("sebabTolakRingkas",rs.getString("SEBAB_TOLAK_RINGKAS") == null ? "" : rs.getString("SEBAB_TOLAK_RINGKAS"));
				h.put("catatanMaklumatTambahan",rs.getString("CATATAN_TOLAK_RINGKAS") == null ? "" : rs.getString("CATATAN_TOLAK_RINGKAS"));

				//flag_mt
				sql = "SELECT C.CATATAN, C.FLAG_PEMBETULAN"
					+ " FROM TBLPHPLOGTUGASAN C"
					+ " WHERE C.ID_PEGAWAI = '" + userId + "'"
					+ " AND C.ROLE = '(PHP)PYWPenolongPegawaiTanahHQ' ";
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					h.put("flagMaklumatTambahan",rs.getString("FLAG_PEMBETULAN") == null ? "" : rs.getString("FLAG_PEMBETULAN"));
				}

				beanSyorTolakRingkas.addElement(h);
//				bil++;

			}

		} catch (Exception re) {
			log.error("Error: ", re);
			throw re;
			} finally {
			if (db != null)
				db.close();
		}

	}

	public void setMaklumatSewa(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSewa = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANSEWA, B.TARIKH_TERIMA, B.TARIKH_SURAT, B.NO_RUJ_SURAT, A.TAJUK_FAIL, A.NO_FAIL_NEGERI, C.TUJUAN, C.CATATAN, C.FLAG_TEMPOHSEWA, C.ID_LUASASAL, C.LUAS_ASAL,"
					+ " D.KETERANGAN, C.FLAG_GUNA, C.ID_LUASMHN, C.LUAS_MHN1, C.LUAS_MHN2, C.LUAS_MHN3, C.ID_LUASMHNBERSAMAAN, C.LUAS_MHNBERSAMAAN, C.ID_LUASBAKI, C.LUAS_BAKI"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPERMOHONANSEWA C, TBLRUJLUAS D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_LUASASAL = D.ID_LUAS(+) AND B.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanSewa",
						rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs
								.getString("ID_PHPPERMOHONANSEWA"));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("noRujukanSurat",
						rs.getString("NO_RUJ_SURAT") == null ? "" : rs
								.getString("NO_RUJ_SURAT"));
				h.put("noFailNegeri",
						rs.getString("NO_FAIL_NEGERI") == null ? "" : rs
								.getString("NO_FAIL_NEGERI"));
				h.put("perkara",
						rs.getString("TAJUK_FAIL") == null ? "" : rs
								.getString("TAJUK_FAIL"));
				h.put("tujuan",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));
				h.put("catatan",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("flagTempohSewa",
						rs.getString("FLAG_TEMPOHSEWA") == null ? "" : rs
								.getString("FLAG_TEMPOHSEWA"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? ""
						: rs.getDouble("LUAS_ASAL"));
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
				beanMaklumatSewa.addElement(h);
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

	public void setMaklumatPemohon(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_PEMOHON, NAMA, NAMA_PEGAWAI, PEKERJAAN, NO_PENGENALAN, ALAMAT1_TETAP, ALAMAT2_TETAP, ALAMAT3_TETAP,"
					+ " POSKOD_TETAP, ID_BANDARTETAP, ID_NEGERITETAP, NO_TEL, NO_FAX, EMEL, ID_KATEGORIPEMOHON, MODAL_BENAR, MODAL_JELAS "
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
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("namaPegawai", rs.getString("NAMA_PEGAWAI") == null ? ""
						: rs.getString("NAMA_PEGAWAI"));
				h.put("noPendaftaran",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
						: rs.getString("ALAMAT1_TETAP"));
				h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
						: rs.getString("ALAMAT2_TETAP"));
				h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
						: rs.getString("ALAMAT3_TETAP"));
				h.put("poskod",
						rs.getString("POSKOD_TETAP") == null ? "" : rs
								.getString("POSKOD_TETAP"));
				h.put("idNegeri",
						rs.getString("ID_NEGERITETAP") == null ? "99999" : rs
								.getString("ID_NEGERITETAP"));
				h.put("idBandar",
						rs.getString("ID_BANDARTETAP") == null ? "99999" : rs
								.getString("ID_BANDARTETAP"));
				h.put("emel",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				h.put("noTel",
						rs.getString("NO_TEL") == null ? "" : rs
								.getString("NO_TEL"));
				h.put("noFaks",
						rs.getString("NO_FAX") == null ? "" : rs
								.getString("NO_FAX"));
				h.put("modalBenar", rs.getString("MODAL_BENAR") == null ? ""
						: Utils.format2Decimal(rs.getDouble("MODAL_BENAR")));
				h.put("modalJelas", rs.getString("MODAL_JELAS") == null ? ""
						: Utils.format2Decimal(rs.getDouble("MODAL_JELAS")));

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

	public void setSenaraiPengarah(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PENGARAH, A.PEGANGAN_SAHAM, B.KETERANGAN "
					+ " FROM TBLPHPPENGARAH A, TBLRUJWARGANEGARA B "
					+ " WHERE A.ID_WARGANEGARA = B.ID_WARGANEGARA AND A.ID_PEMOHON = '"
					+ idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPengarah", rs.getString("ID_PENGARAH") == null ? ""
						: rs.getString("ID_PENGARAH"));
				h.put("namaPengarah",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("warganegaraPengarah",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("peganganSaham",
						rs.getString("PEGANGAN_SAHAM") == null ? "" : rs
								.getString("PEGANGAN_SAHAM"));

				listPengarah.addElement(h);
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

	public void updatePermohonanSewa(String idFail, String idPermohonan,
			String tarikhTerima, String tarikhSurat, String noRujukanSurat, String noFailNegeri,
			String txtPerkara, String idPermohonanSewa, String txtTujuan, String idJenisTujuan,
			String socTempohSewa, String idLuasKegunaan, String idLuas,
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
			r.add("NO_FAIL_NEGERI", noFailNegeri);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPFDFAIL");
			stmt.executeUpdate(sql);

			// TBLPERMOHONAN
			r = new SQLRenderer();
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("NO_RUJ_SURAT", noRujukanSurat);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPERMOHONAN");
			stmt.executeUpdate(sql);

			// TBLPHPPERMOHONANSEWA
			r = new SQLRenderer();
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			if(!"".equals(txtTujuan)){
				r.add("TUJUAN", txtTujuan);
			} else {
				insertSocTujuan(idPermohonanSewa, idJenisTujuan, userId, db);
			}
			r.add("FLAG_GUNA", idLuasKegunaan);
			r.add("ID_LUASMHN", idLuas);
			r.add("LUAS_MHN1", Utils.RemoveComma(txtLuasMohon1));
			r.add("LUAS_MHN2", Utils.RemoveComma(txtLuasMohon2));
			r.add("LUAS_MHN3", Utils.RemoveComma(txtLuasMohon3));
			r.add("ID_LUASMHNBERSAMAAN", "2");
			r.add("LUAS_MHNBERSAMAAN", Utils.RemoveComma(txtLuasBersamaan));
			r.add("ID_LUASBAKI", "2");
			r.add("LUAS_BAKI", Utils.RemoveComma(txtBakiLuas));
			r.add("FLAG_TEMPOHSEWA", socTempohSewa);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
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

	private void insertSocTujuan(String idPermohonanSewa, String idJenisTujuan,
			String userId, Db db) throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPERMOHONANTUJUAN
			r = new SQLRenderer();
			long idPHPPermohonanTujuan = DB
					.getNextID("TBLPHPPERMOHONANTUJUAN_SEQ");
			r.add("ID_PHPPERMOHONANTUJUAN", idPHPPermohonanTujuan);
			r.add("ID_PHPPERMOHONANSEWA", idPermohonanSewa);
			r.add("ID_JENISTUJUAN", idJenisTujuan);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPERMOHONANTUJUAN");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	public void updatePemohon(String idPemohon, String idKategoriPemohon,
			String txtNama, String txtNamaPegawai, String txtNoPendaftaran,
			String txtAlamat1, String txtAlamat2, String txtAlamat3,
			String txtPoskod, String idBandar, String idNegeri, String txtEmel,
			String txtNoTel, String txtNoFaks, String txtModalBenar,
			String txtModalJelas, HttpSession session) throws Exception {

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
			r.add("NAMA", txtNama);
			r.add("NAMA_PEGAWAI", txtNamaPegawai);
			r.add("NO_PENGENALAN", txtNoPendaftaran);
			r.add("ALAMAT1_TETAP", txtAlamat1);
			r.add("ALAMAT2_TETAP", txtAlamat2);
			r.add("ALAMAT3_TETAP", txtAlamat3);
			r.add("POSKOD_TETAP", txtPoskod);
			r.add("ID_BANDARTETAP", idBandar);
			r.add("ID_NEGERITETAP", idNegeri);
			r.add("NO_TEL", txtNoTel);
			r.add("NO_FAX", txtNoFaks);
			r.add("EMEL", txtEmel);
			r.add("MODAL_BENAR", Utils.RemoveSymbol(txtModalBenar));
			r.add("MODAL_JELAS", Utils.RemoveSymbol(txtModalJelas));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
					"FAIL [" + idPemohon + "] DIKEMASKINI");

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
			String idSuburusan, String flagSebabTamat, HttpSession session) throws Exception {
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

			if(flagSebabTamat!=null && flagSebabTamat.length()>0){
				if(flagSebabTamat.equals("Y")){
					r.add("ID_STATUS", "1610201");
				}
			} else {
				r.add("ID_STATUS", "1610199");
			}

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
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610199")); // JABATAN
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

			if(flagSebabTamat!=null && flagSebabTamat.length()>0){
				if(flagSebabTamat.equals("Y")){
					AuditTrail.logActivity("1610201", "4", null, session, "UPD",
							"FAIL [" + idPermohonan + "] PROSES SETERUSNYA"); //MESYUARAT
				}
			} else {
				AuditTrail.logActivity("1610199", "4", null, session, "UPD",
						"FAIL [" + idPermohonan + "] PROSES SETERUSNYA"); //JABATAN TEKNIKAL
			}

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
			String idSuburusan, String tarikhBatal, String txtSebab,
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
			r.add("ID_SUBURUSANSTATUS",
					getIdSuburusanstatus(idSuburusan, "1610212")); // BATAL
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

	public String getIdTujuanByIdPermohonanSewa(String idPermohonanSewa) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT C.ID_JENISTUJUAN FROM TBLPHPPERMOHONANSEWA A, TBLPHPPERMOHONANTUJUAN B, TBLPHPRUJJENISTUJUAN C "
				+ " WHERE A.ID_PHPPERMOHONANSEWA = B.ID_PHPPERMOHONANSEWA(+) "
				+ " AND B.ID_JENISTUJUAN = C.ID_JENISTUJUAN "
				+ " AND A.ID_PHPPERMOHONANSEWA = '"+ idPermohonanSewa +"'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_JENISTUJUAN");
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

			AuditTrail.logActivity("1610198", "4", null, session, "DEL",
					"FAIL [" + getNoFailByIdPermohonan(idPermohonan)
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

	public String getFlagMT(String idFail, String idPegawai) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT FLAG_MT FROM TBLPHPLOGTUGASAN WHERE ID_FAIL = '"
					+ idFail
					+ "' AND ID_PEGAWAI = '"
					+ idPegawai
					+ "' AND FLAG_AKTIF = 'Y' AND ROLE = '(PHP)PYWPenolongPegawaiTanahNegeri'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (String) rs.getString("FLAG_MT");
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

	public Vector setMaklumatPenamatanPenyewaan(String idPermohonanSewa) throws Exception {
		TblPhpPermohonanSewaModel model = new TblPhpPermohonanSewaModel();
		Vector vec = model.getTable(idPermohonanSewa);
		return vec;
	}

	public void simpanTamatSewa(String idPermohonanSewa, String socSebabTamat, String catatanTamat, String tarikhSuratTamat, HttpSession session) throws Exception {
		TblPhpPermohonanSewaModel model = new TblPhpPermohonanSewaModel();
		model.setIdPhppermohonansewa(Long.parseLong(idPermohonanSewa));
		model.setFlagSebabTamat("Y");
		model.setTarikhSurattamat(tarikhSuratTamat);
		model.setCatatanTamat(catatanTamat);
		model.setIdSebabtamat(socSebabTamat);
		model.setFlagPost("update");
		model.save(model, session);
	}

	public Vector getBeanMaklumatSewa() {
		return beanMaklumatSewa;
	}

	public void setBeanMaklumatSewa(Vector beanMaklumatSewa) {
		this.beanMaklumatSewa = beanMaklumatSewa;
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void setBeanMaklumatPemohon(Vector beanMaklumatPemohon) {
		this.beanMaklumatPemohon = beanMaklumatPemohon;
	}

	public Vector getListPengarah() {
		return listPengarah;
	}

	public void setListPengarah(Vector listPengarah) {
		this.listPengarah = listPengarah;
	}

	public Vector getListTanahBerkaitan() {
		return listTanahBerkaitan;
	}

	public void setListTanahBerkaitan(Vector listTanahBerkaitan) {
		this.listTanahBerkaitan = listTanahBerkaitan;
	}

	public Vector getBeanSyorTolakRingkas() {
		return beanSyorTolakRingkas;
	}

	public void setBeanSyorTolakRingkas(Vector beanSyorTolakRingkas) {
		this.beanSyorTolakRingkas = beanSyorTolakRingkas;
	}

	public Vector getBeanMaklumatPenamatanPenyewaan() {
		return beanMaklumatPenamatanPenyewaan;
	}

	public void setBeanMaklumatPenamatanPenyewaan(
			Vector beanMaklumatPenamatanPenyewaan) {
		this.beanMaklumatPenamatanPenyewaan = beanMaklumatPenamatanPenyewaan;
	}

	public Vector getSenaraiSemak(String idPermohonan, String idKategoriPemohon) throws Exception {

		Db db = null;
		String sql = "";
		Vector senaraiSemak = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_RUJSENARAISEMAK, KETERANGAN,"
					+ " CASE WHEN ID_RUJSENARAISEMAK IN (SELECT ID_RUJSENARAISEMAK FROM TBLPHPSENARAISEMAK WHERE ID_PERMOHONAN = '" + idPermohonan + "')"
					+ " THEN 'Y' END AS FLAG"
					+ " FROM TBLPHPRUJSENARAISEMAK"
					+ " WHERE FLAG_AKTIF = 'Y' AND ID_KATEGORIPEMOHON = '" + idKategoriPemohon + "'";
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
					"FAIL [" + idPermohonan + "] DIKEMASKINI");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector getBeanMaklumatSenaraiSemak() {
		return beanMaklumatSenaraiSemak;
	}

	public void setBeanMaklumatSenaraiSemak(Vector beanMaklumatSenaraiSemak) {
		this.beanMaklumatSenaraiSemak = beanMaklumatSenaraiSemak;
	}

	public void setSenaraiLampiran(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN FROM TBLPHPDOKUMEN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "' AND FLAG_DOKUMEN = 'L'"
					+ " AND ID_ULASANTEKNIKAL IS NULL AND ID_MESYUARAT IS NULL AND ID_PHPHAKMILIK IS NULL AND ID_PENAWARANKJP IS NULL";

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
				listLampiran.addElement(h);
				bil++;
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatLampiran(String idDokumen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatLampiran = new Vector();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_DOKUMEN, NAMA_DOKUMEN, CATATAN, JENIS_MIME FROM TBLPHPDOKUMEN WHERE ID_DOKUMEN = '"
					+ idDokumen + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumen", rs.getString("ID_DOKUMEN"));
				h.put("namaLampiran", rs.getString("NAMA_DOKUMEN") == null ? ""
						: rs.getString("NAMA_DOKUMEN").toUpperCase());
				h.put("catatanLampiran",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				h.put("jenisMime",
						rs.getString("JENIS_MIME") == null ? "" : StringUtils.substringBefore(rs.getString("JENIS_MIME"), "/"));
				beanMaklumatLampiran.addElement(h);
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKemaskiniLampiran(String idDokumen, String txtNamaLampiran,
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
			r.add("NAMA_DOKUMEN", txtNamaLampiran);
			r.add("CATATAN", txtCatatan);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPDOKUMEN");
			stmt.executeUpdate(sql);

			conn.commit();

			AuditTrail.logActivity("1610198", "4", null, session, "UPD",
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

	public void hapusLampiran(String idDokumen, HttpSession session) throws Exception {
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

	public Vector getListLampiran() {
		return listLampiran;
	}

	public void setListLampiran(Vector listLampiran) {
		this.listLampiran = listLampiran;
	}

	public Vector getBeanMaklumatLampiran() {
		return beanMaklumatLampiran;
	}

	public void setBeanMaklumatLampiran(Vector beanMaklumatLampiran) {
		this.beanMaklumatLampiran = beanMaklumatLampiran;
	}

}

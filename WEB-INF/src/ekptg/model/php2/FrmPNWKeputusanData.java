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
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class FrmPNWKeputusanData {

	private Vector beanMaklumatTanah = null;
	private Vector listAgensi = null;
	private Vector beanMaklumatKeputusan = null;
	private HakmilikInterface iHakmilik = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatTanah(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.ID_HAKMILIKAGENSI, C.ID_HAKMILIK, D.PEGANGAN_HAKMILIK, E.KETERANGAN AS JENIS_LOT, D.NO_LOT, F.KETERANGAN AS JENIS_LUASASAL, C.LUAS_BERSAMAAN AS LUAS_ASAL,"
					+ " H.KETERANGAN AS JENIS_LUASMOHON, G.LUAS_MHNBERSAMAAN AS LUAS_MOHON, I.KOD_JENIS_HAKMILIK AS JENIS_HAKMILIK, D.NO_HAKMILIK, D.NO_WARTA, D.TARIKH_WARTA,"
					+ " J.NAMA_NEGERI, K.NAMA_DAERAH, L.NAMA_MUKIM"
					+ " FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B, TBLHTPHAKMILIKAGENSI C, TBLHTPHAKMILIK D, TBLRUJLOT E, TBLRUJLUAS F, TBLPHPPERMOHONANPELEPASAN G, TBLRUJLUAS H, TBLRUJJENISHAKMILIK I,"
					+ " TBLRUJNEGERI J, TBLRUJDAERAH K, TBLRUJMUKIM L"
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_HAKMILIKAGENSI = C.ID_HAKMILIKAGENSI AND A.ID_PERMOHONAN = G.ID_PERMOHONAN AND D.ID_JENISHAKMILIK = I.ID_JENISHAKMILIK(+)"
					+ " AND C.ID_HAKMILIK = D.ID_HAKMILIK(+) AND D.ID_LOT = E.ID_LOT(+) AND C.ID_LUAS_BERSAMAAN = F.ID_LUAS(+) AND G.ID_UNITLUASMHNBERSAMAAN = H.ID_LUAS(+) AND D.ID_NEGERI = J.ID_NEGERI(+)"
					+ " AND D.ID_DAERAH = K.ID_DAERAH(+) AND D.ID_MUKIM = L.ID_MUKIM(+)"
					+ " AND A.ID_PERMOHONAN = '" + idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik",
						rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs
								.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("noHakmilik",
						rs.getString("JENIS_HAKMILIK") == null
								|| rs.getString("NO_HAKMILIK") == null ? ""
								: rs.getString("JENIS_HAKMILIK").toUpperCase()
										+ " " + rs.getString("NO_HAKMILIK"));
				h.put("noLot",
						rs.getString("JENIS_LOT") == null
								|| rs.getString("NO_LOT") == null ? "" : rs
								.getString("JENIS_LOT").toUpperCase()
								+ " "
								+ rs.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS_ASAL") == null
								|| rs.getString("JENIS_LUASASAL") == null ? ""
								: Utils.formatLuas(rs.getDouble("LUAS_ASAL"))
										+ " " + rs.getString("JENIS_LUASASAL"));
				h.put("luasTawar",
						rs.getString("LUAS_MOHON") == null
								|| rs.getString("JENIS_LUASMOHON") == null ? ""
								: Utils.formatLuas(rs.getDouble("LUAS_MOHON"))
										+ " " + rs.getString("JENIS_LUASMOHON"));
				h.put("luasDiTawarkan", rs.getString("LUAS_MOHON") == null ? ""
						: Utils.formatLuas(rs.getDouble("LUAS_MOHON")));
				h.put("noWarta",
						rs.getString("NO_WARTA") == null ? "" : rs
								.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs
						.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs
						.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs
						.getString("NAMA_NEGERI").toUpperCase());
				beanMaklumatTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setSenaraiAgensi(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listAgensi = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PENAWARANKJP, B.NAMA_KEMENTERIAN, C.NAMA_AGENSI, A.FLAG_LULUS, A.LUAS FROM TBLPHPPENAWARANKJP A, TBLRUJKEMENTERIAN B, TBLRUJAGENSI C"
					+ " WHERE A.ID_KEMENTERIAN = B.ID_KEMENTERIAN AND A.ID_AGENSI = C.ID_AGENSI AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "' ORDER BY A.ID_PENAWARANKJP";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPenawaranKJP",
						rs.getString("ID_PENAWARANKJP") == null ? "" : rs
								.getString("ID_PENAWARANKJP"));
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				h.put("flagLulus",
						rs.getString("FLAG_LULUS") == null ? "" : rs
								.getString("FLAG_LULUS"));
				h.put("luas",
						rs.getString("LUAS") == null ? "" : Utils.formatLuas(rs
								.getDouble("LUAS")));
				listAgensi.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Object getTotalLuas(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Double total = 0D;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS FROM TBLPHPPENAWARANKJP WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString("LUAS") != null) {
					if (rs.getDouble("LUAS") != 0D) {
						total = total + rs.getDouble("LUAS");
					}
				}
			}

		} finally {
			if (db != null)
				db.close();
		}
		return Utils.formatLuas(total);
	}

	public void clearLuas(String idPermohonan) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENAWARANKJP
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_LULUS", "");
			r.add("LUAS", "");
			r.add("ID_LUAS", "");

			sql = r.getSQLUpdate("TBLPHPPENAWARANKJP");
			stmt.executeUpdate(sql);

			conn.commit();

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

	public void updateLuasAgensi(String idPenawaranKJP, String luas,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLPHPPENAWARANKJP
			r.update("ID_PENAWARANKJP", idPenawaranKJP);
			r.add("FLAG_LULUS", "Y");
			r.add("LUAS", luas);
			r.add("ID_LUAS", "2");

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENAWARANKJP");
			stmt.executeUpdate(sql);

			conn.commit();
			
			AuditTrail.logActivity("1610206", "4", null, session, "UPD",
					"FAIL PENAWARAN [" + idPenawaranKJP
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

	public void setMaklumatKeputusan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKeputusan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT KEPUTUSAN, TARIKH_HANTARKEPUTUSAN FROM TBLPHPPERMOHONANPELEPASAN"
					+ " WHERE ID_PERMOHONAN = '" + idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_HANTARKEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_HANTARKEPUTUSAN")));
				h.put("keputusan",
						rs.getString("KEPUTUSAN") == null ? "" : rs
								.getString("KEPUTUSAN"));
				beanMaklumatKeputusan.addElement(h);
				bil++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void simpanKeputusan(String idPermohonan, String txtTarikhKeputusan,
			String socKeputusan, String idStatus, HttpSession session)
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

			// TBLPHPPERMOHONANPELEPASAN
			r.update("ID_PERMOHONAN", idPermohonan);

			if (!"".equals(txtTarikhKeputusan)) {
				r.add("TARIKH_HANTARKEPUTUSAN",
						r.unquote("to_date('" + txtTarikhKeputusan
								+ "','dd/MM/yyyy')"));
			}
			if ("1610206".equals(idStatus)) {
				r.add("KEPUTUSAN", socKeputusan);
			}

			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);

			sql = r.getSQLUpdate("TBLPHPPERMOHONANPELEPASAN");
			stmt.executeUpdate(sql);

			if ("1610206".equals(idStatus)) {

				// UPDATE ID_STATUS TBLPERMOHONAN - ASNA
				r = new SQLRenderer();
				r.update("ID_PERMOHONAN", idPermohonan);
				r.add("ID_STATUS", idStatus);

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLPERMOHONAN");
				stmt.executeUpdate(sql);
			}

			conn.commit();
			
			AuditTrail.logActivity("1610206", "4", null, session, "INS",
					"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
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

	public void updateStatus(String idFail, String idPermohonan,
			String idKeputusan, HttpSession session) throws Exception {
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
			if ("L".equals(idKeputusan)) {
				r.add("ID_STATUS", "1610207"); // LULUS
			} else if ("T".equals(idKeputusan)) {
				r.add("ID_STATUS", "1610208"); // TOLAK
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
			if ("L".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("32", "1610207")); // LULUS
			} else if ("T".equals(idKeputusan)) {
				r.add("ID_SUBURUSANSTATUS",
						getIdSuburusanstatus("32", "1610208")); // TOLAK
			}

			r.add("AKTIF", "1");
			r.add("ID_FAIL", idFail);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLRUJSUBURUSANSTATUSFAIL");
			stmt.executeUpdate(sql);

			// UPDATE TBLHTPHAKMILIKAGENSI WHEN LULUS
			if ("L".equals(idKeputusan) && getFlagBorangK(idFail)) {

				String catatanHTP = "";
				catatanHTP = getTajukFail(idFail);
				String idHakmilikAgensi = getIdHakmilikAgensiByIdPermohonan(idPermohonan);
				String idHakmilik = getIdHakmilik(idHakmilikAgensi);
				Double luasAsal = getLuasAsal(idHakmilikAgensi);
				Double luasDiberi = getLuasDiberi(idPermohonan);
				Double luasBaki = 0D;
				luasBaki = luasAsal - luasDiberi;

				// DEACTIVATE ROW TBLHTPHAKMILIKAGENSI
				r = new SQLRenderer();
				r.update("ID_HAKMILIKAGENSI", idHakmilikAgensi);
				r.add("FLAG_AKTIF", "T");

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPHAKMILIKAGENSI");
				stmt.executeUpdate(sql);

				// GET ROW HTPHAKMILIKAGENSI
				if (luasBaki != 0D) {
					Hashtable hashAgensi = getMaklumatAgensi(idHakmilikAgensi);
					if (hashAgensi != null) {

						// TBLHTPHAKMILIKAGENSI
						r = new SQLRenderer();
						long idHakmilikAgensiInsert = DB
								.getNextID("TBLHTPHAKMILIKAGENSI_SEQ");
						r.add("ID_HAKMILIKAGENSI", idHakmilikAgensiInsert);
						r.add("ID_HAKMILIK", idHakmilik);
						r.add("ID_KEMENTERIAN", hashAgensi.get("idKementerian"));
						r.add("ID_AGENSI", hashAgensi.get("idAgensi"));
						r.add("ID_LUAS", "2");
						r.add("LUAS", Utils.formatLuas(luasBaki));
						r.add("ID_LUAS_BERSAMAAN", "2");
						r.add("LUAS_BERSAMAAN", Utils.formatLuas(luasBaki));
						r.add("STATUS", "T");
						r.add("FLAG_AKTIF", "Y");

						r.add("ID_MASUK", userId);
						r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

						sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
						stmt.executeUpdate(sql);

					}
				}

				// GET ROW TBLPHPPENAWARANKJP
				String catatanAgensi = "";
				Vector listPenawaran = null;
				listPenawaran = getPenawaranKJP(idPermohonan);
				if (listPenawaran.size() != 0) {
					for (int i = 0; i < listPenawaran.size(); i++) {
						Hashtable hashPenawaran = (Hashtable) listPenawaran
								.get(i);
						String idKementerian = "";
						if (hashPenawaran.get("ID_KEMENTERIAN") != null)
							idKementerian = (String) hashPenawaran
									.get("ID_KEMENTERIAN");
						String idAgensi = "";
						if (hashPenawaran.get("ID_AGENSI") != null)
							idAgensi = (String) hashPenawaran.get("ID_AGENSI");
						Double luas = 0D;
						if (hashPenawaran.get("LUAS") != null)
							luas = Double.valueOf((String) hashPenawaran
									.get("LUAS"));

						insertHakmilikAgensi(idHakmilik, idKementerian,
								idAgensi, luas, db, userId);

						if ("".equals(catatanAgensi)) {
							catatanAgensi = getNamaKementerian(idKementerian)
									+ " " + getNamaAgensi(idAgensi) + " "
									+ Utils.formatLuas(luas) + "H";
						} else {
							catatanAgensi = catatanAgensi + ", "
									+ getNamaKementerian(idKementerian) + " "
									+ getNamaAgensi(idAgensi) + " "
									+ Utils.formatLuas(luas) + "H";
						}
					}
				}

				catatanHTP = catatanHTP + " (" + catatanAgensi + ")";

				// TBLHTPHAKMILIK
				r = new SQLRenderer();
				r.update("ID_HAKMILIK", idHakmilik);

				// UPDATE CATATAN
				r.add("CATATAN", catatanHTP);

				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPHAKMILIK");
				stmt.executeUpdate(sql);

				// getHakmilik().kemaskiniHakmilikCatatan(idHakmilik,
				// catatanHTP);
			}

			conn.commit();
			
			if ("L".equals(idKeputusan)) {
				AuditTrail.logActivity("1610207", "4", null, session, "UPD",
						"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES DILULUSKAN"); // LULUS
			} else if ("T".equals(idKeputusan)) {
				AuditTrail.logActivity("1610208", "4", null, session, "UPD",
						"FAIL PENAWARAN [" + getNoFailByIdPermohonan(idPermohonan)
								+ "] PROSES DILULUSKAN"); // TOLAK
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

	private boolean getFlagBorangK(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		boolean bool = false;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT B.FLAG_BORANGK FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND A.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				if ("Y".equals(rs.getString("FLAG_BORANGK"))) {
					bool = false;
				} else {
					bool = true;
				}
			}

		} finally {
			if (db != null)
				db.close();
		}

		return bool;
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
			r.add("ID_SUBURUSANSTATUS", getIdSuburusanstatus("32", "1610212")); // BATAL
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

		} finally {
			if (db != null)
				db.close();
		}
	}

	private void insertHakmilikAgensi(String idHakmilik, String idKementerian,
			String idAgensi, Double luas, Db db, String userId)
			throws Exception {

		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			// TBLHTPHAKMILIKAGENSI
			r = new SQLRenderer();
			long idHakmilikAgensiInsert = DB
					.getNextID("TBLHTPHAKMILIKAGENSI_SEQ");
			r.add("ID_HAKMILIKAGENSI", idHakmilikAgensiInsert);
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("ID_KEMENTERIAN", idKementerian);
			r.add("ID_AGENSI", idAgensi);
			r.add("ID_LUAS", "2");
			r.add("LUAS", Utils.formatLuas(luas));
			r.add("ID_LUAS_BERSAMAAN", "2");
			r.add("LUAS_BERSAMAAN", Utils.formatLuas(luas));
			r.add("STATUS", "T");
			r.add("FLAG_AKTIF", "Y");

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPHAKMILIKAGENSI");
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());
		}
	}

	private Vector getPenawaranKJP(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";
		Vector listPenawaran = new Vector();

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_KEMENTERIAN, ID_AGENSI, LUAS FROM TBLPHPPENAWARANKJP WHERE FLAG_LULUS = 'Y' AND ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("ID_KEMENTERIAN",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				h.put("ID_AGENSI",
						rs.getString("ID_AGENSI") == null ? "" : rs
								.getString("ID_AGENSI"));
				h.put("LUAS",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				listPenawaran.addElement(h);
			}

		} finally {
			if (db != null)
				db.close();
		}
		return listPenawaran;
	}

	private Hashtable getMaklumatAgensi(String idHakmilikAgensi)
			throws Exception {
		Db db = null;
		String sql = "";
		Hashtable hash = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIK, ID_KEMENTERIAN, ID_AGENSI, ID_LUAS, LUAS, ID_LUAS_BERSAMAAN, LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			int bil = 1;
			if (rs.next()) {
				hash = new Hashtable();
				hash.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
				hash.put(
						"idKementerian",
						rs.getString("ID_KEMENTERIAN") == null ? "" : rs
								.getString("ID_KEMENTERIAN"));
				hash.put("idAgensi", rs.getString("ID_AGENSI") == null ? ""
						: rs.getString("ID_AGENSI"));
				hash.put(
						"idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				hash.put(
						"luas",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				hash.put(
						"idLuasBersamaan",
						rs.getString("ID_LUAS_BERSAMAAN") == null ? "" : rs
								.getString("ID_LUAS_BERSAMAAN"));
				hash.put(
						"luasBersamaan",
						rs.getString("LUAS_BERSAMAAN") == null ? "" : rs
								.getString("LUAS_BERSAMAAN"));
			}

		} finally {
			if (db != null)
				db.close();
		}
		return hash;
	}

	private Double getLuasDiberi(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT SUM(LUAS) AS TOTAL FROM TBLPHPPENAWARANKJP WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("TOTAL");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private Double getLuasAsal(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT LUAS_BERSAMAAN FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getDouble("LUAS_BERSAMAAN");
			} else {
				return 0D;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getTajukFail(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TAJUK_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("TAJUK_FAIL");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilikAgensiByIdPermohonan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIKAGENSI FROM TBLPHPHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIKAGENSI");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getIdHakmilik(String idHakmilikAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT ID_HAKMILIK FROM TBLHTPHAKMILIKAGENSI WHERE ID_HAKMILIKAGENSI = '"
					+ idHakmilikAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("ID_HAKMILIK");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNamaKementerian(String idKementerian) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN = '"
					+ idKementerian + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_KEMENTERIAN");
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String getNamaAgensi(String idAgensi) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NAMA_AGENSI FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
					+ idAgensi + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("NAMA_AGENSI");
			} else {
				return "";
			}

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
				return rs.getString("ID_SUBURUSANSTATUS").toString();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String setKeputusan(String idFail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLPHPPERMOHONANPELEPASAN.KEPUTUSAN FROM TBLPFDFAIL, TBLPERMOHONAN, TBLPHPPERMOHONANPELEPASAN "
					+ "WHERE TBLPFDFAIL.ID_FAIL = TBLPERMOHONAN.ID_FAIL AND TBLPERMOHONAN.ID_PERMOHONAN = TBLPHPPERMOHONANPELEPASAN.ID_PERMOHONAN AND TBLPFDFAIL.ID_FAIL = '"
					+ idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return (rs.getString("KEPUTUSAN") == null ? "" : rs
						.getString("KEPUTUSAN"));
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	public Vector getListAgensi() {
		return listAgensi;
	}

	public void setListAgensi(Vector listAgensi) {
		this.listAgensi = listAgensi;
	}

	public Vector getBeanMaklumatKeputusan() {
		return beanMaklumatKeputusan;
	}

	public void setBeanMaklumatKeputusan(Vector beanMaklumatKeputusan) {
		this.beanMaklumatKeputusan = beanMaklumatKeputusan;
	}

	public HakmilikInterface getHakmilik() {
		if (iHakmilik == null) {
			iHakmilik = new HakmilikBean();
		}
		return iHakmilik;
	}
}

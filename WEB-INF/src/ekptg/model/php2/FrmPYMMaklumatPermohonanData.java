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
import ekptg.helpers.DB;

public class FrmPYMMaklumatPermohonanData {
	private Vector beanMaklumatTanah = null;
	private Vector beanMaklumatPemohon = null;
	private Vector beanMaklumatPenyewaan = null;
	private Vector listPengarah = null;
	private Vector beanMaklumatPengarah = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void setMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatTanah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK, A.NO_HAKMILIK, B.KOD_LOT, B.KETERANGAN AS JENIS_LOT, A.NO_LOT, A.LUAS, A.NO_WARTA,"
					+ " A.TARIKH_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH, C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS"
					+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E, TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I, TBLRUJAGENSI J, TBLRUJLUAS K"
					+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+)"
					+ " AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS AND A.ID_HAKMILIK = '"
					+ idHakmilik + "'";

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
				h.put("jenisHakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null
								|| rs.getString("JENIS_HAKMILIK") == null ? ""
								: rs.getString("KOD_JENIS_HAKMILIK")
										.toUpperCase()
										+ " - "
										+ rs.getString("JENIS_HAKMILIK")
												.toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("jenisLot",
						rs.getString("KOD_LOT") == null
								|| rs.getString("JENIS_LOT") == null ? "" : rs
								.getString("KOD_LOT").toUpperCase()
								+ " - "
								+ rs.getString("JENIS_LOT").toUpperCase());
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("luasLot",
						rs.getString("LUAS") == null
								|| rs.getString("JENIS_LUAS") == null ? "" : rs
								.getString("LUAS")
								+ " "
								+ rs.getString("JENIS_LUAS"));
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
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? ""
						: rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah",
						rs.getString("SUBKATEGORI") == null ? "" : rs
								.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs
						.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs
						.getString("SEKATAN").toUpperCase());
				h.put("kementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs
						.getString("NAMA_AGENSI").toUpperCase());
				beanMaklumatTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setMaklumatPemohon(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PEMOHON,A.ID_KATEGORIPEMOHON, A.NAMA, A.ID_JENISPENGENALAN, A.NO_PENGENALAN, A.ALAMAT1_TETAP,A.ALAMAT2_TETAP,A.ALAMAT3_TETAP, "
					+ "A.POSKOD_TETAP, A.ID_BANDARTETAP, A.ID_NEGERITETAP, A.NO_TEL_PEJABAT, A.NO_FAX, A.NO_TEL_RUMAH,A.NO_TEL_BIMBIT,A.EMEL,A.PEKERJAAN,A.ID_JANTINA,A.ID_BANGSA,A.MODAL_DIBENARKAN, MODAL_JELAS "
					+ "FROM TBLPHPPEMOHON A, TBLPFDFAIL B, TBLPERMOHONAN C WHERE B.ID_FAIL = C.ID_FAIL AND A.ID_PEMOHON = C.ID_PEMOHON AND A.ID_KATEGORIPEMOHON IN(1,2) AND A.ID_PEMOHON = '"
					+ idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKategori",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				// IF PEMOHON ADALAH SYARIKAT
				if (rs.getString("ID_KATEGORIPEMOHON").equals("2")) {
					h.put("txtNamaA", rs.getString("NAMA") == null ? "" : rs
							.getString("NAMA").toUpperCase());
					h.put("idJenisPendaftaran", rs
							.getString("ID_JENISPENGENALAN") == null ? "" : rs
							.getString("ID_JENISPENGENALAN").toUpperCase());
					h.put("txtNoPendaftaran",
							rs.getString("NO_PENGENALAN") == null ? "" : rs
									.getString("NO_PENGENALAN").toUpperCase());
					h.put("txtAlamat1A",
							rs.getString("ALAMAT1_TETAP") == null ? "" : rs
									.getString("ALAMAT1_TETAP").toUpperCase());
					h.put("txtAlamat2A",
							rs.getString("ALAMAT2_TETAP") == null ? "" : rs
									.getString("ALAMAT2_TETAP").toUpperCase());
					h.put("txtAlamat3A",
							rs.getString("ALAMAT3_TETAP") == null ? "" : rs
									.getString("ALAMAT3_TETAP").toUpperCase());
					h.put("txtPoskodA",
							rs.getString("POSKOD_TETAP") == null ? "" : rs
									.getString("POSKOD_TETAP"));
					h.put("idNegeriS",
							rs.getString("ID_NEGERITETAP") == null ? "" : rs
									.getString("ID_NEGERITETAP"));
					h.put("idBandarS",
							rs.getString("ID_BANDARTETAP") == null ? "" : rs
									.getString("ID_BANDARTETAP"));
					h.put("txtEmelA",
							rs.getString("EMEL") == null ? "" : rs
									.getString("EMEL"));
					h.put("txtNoTelA",
							rs.getString("NO_TEL_PEJABAT") == null ? "" : rs
									.getString("NO_TEL_PEJABAT"));
					h.put("txtNoFaksA", rs.getString("NO_FAX") == null ? ""
							: rs.getString("NO_FAX"));
					h.put("txtModalBenar",
							rs.getString("MODAL_DIBENARKAN") == null ? "" : rs
									.getString("MODAL_DIBENARKAN"));
					h.put("txtModalJelas",
							rs.getString("MODAL_JELAS") == null ? "" : rs
									.getString("MODAL_JELAS"));
				}
				// IF PEMOHON ADALAH INDIVIDU
				else if (rs.getString("ID_KATEGORIPEMOHON").equals("1")) {
					h.put("txtNamaI", rs.getString("NAMA") == null ? "" : rs
							.getString("NAMA").toUpperCase());
					h.put("idJenisPengenalan", rs
							.getString("ID_JENISPENGENALAN") == null ? "" : rs
							.getString("ID_JENISPENGENALAN").toUpperCase());
					h.put("txtNoKP1",
							rs.getString("NO_PENGENALAN") == null ? "" : rs
									.getString("NO_PENGENALAN").toUpperCase());
					h.put("txtPekerjaan",
							rs.getString("PEKERJAAN") == null ? "" : rs
									.getString("PEKERJAAN").toUpperCase());
					h.put("idJantina", rs.getString("ID_JANTINA") == null ? ""
							: rs.getString("ID_JANTINA"));
					h.put("idBangsa", rs.getString("ID_BANGSA") == null ? ""
							: rs.getString("ID_BANGSA"));
					h.put("txtAlamat1I",
							rs.getString("ALAMAT1_TETAP") == null ? "" : rs
									.getString("ALAMAT1_TETAP").toUpperCase());
					h.put("txtAlamat2I",
							rs.getString("ALAMAT2_TETAP") == null ? "" : rs
									.getString("ALAMAT2_TETAP").toUpperCase());
					h.put("txtAlamat3I",
							rs.getString("ALAMAT3_TETAP") == null ? "" : rs
									.getString("ALAMAT3_TETAP").toUpperCase());
					h.put("txtPoskodI",
							rs.getString("POSKOD_TETAP") == null ? "" : rs
									.getString("POSKOD_TETAP"));
					h.put("idNegeriI",
							rs.getString("ID_NEGERITETAP") == null ? "" : rs
									.getString("ID_NEGERITETAP"));
					h.put("idBandarI",
							rs.getString("ID_BANDARTETAP") == null ? "" : rs
									.getString("ID_BANDARTETAP"));
					h.put("txtEmelI",
							rs.getString("EMEL") == null ? "" : rs
									.getString("EMEL"));
					h.put("txtNoTelefonI",
							rs.getString("NO_TEL_RUMAH") == null ? "" : rs
									.getString("NO_TEL_RUMAH"));
					h.put("txtNoTelefonBimbit",
							rs.getString("NO_TEL_BIMBIT") == null ? "" : rs
									.getString("NO_TEL_BIMBIT"));
					h.put("txtNoFaksI", rs.getString("NO_FAX") == null ? ""
							: rs.getString("NO_FAX").toUpperCase());
				}
				beanMaklumatPemohon.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public void updatePemohon(String idPemohon, String idSuburusan,
			String txdTarikhTerima, String txdTarikhSurat, String txtPerkara,
			String txtNamaA, String idJenisPendaftaran,
			String txtNoPendaftaran, String txtNoTelA, String txtNoFaksA,
			String txtEmelA, String txtAlamat1A, String txtAlamat2A,
			String txtAlamat3A, String txtPoskodA, String idBandarS,
			String idNegeriS, String idWarga, String txtNamaI,
			String idJenisPengenalan, String txtNoKP1, String txtNoKP2,
			String txtNoKP3, String txtPekerjaan, String idJantina,
			String idBangsa, String txtEmelI, String txtAlamat1I,
			String txtAlamat2I, String txtAlamat3I, String txtPoskodI,
			String idBandarI, String idNegeriI, String txtNoTelefonI,
			String txtNoTelefonBimbit, String txtNoFaksI, String idHakmilik,
			String idKementerian, String kodKementerian, String idNegeriTanah,
			String kodNegeriTanah, String modalBenar, String modalJelas,
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

			// GET DATA LAMA FROM TBLHTPHAKMILIK
			sql = "SELECT A.ID_KATEGORIPEMOHON FROM TBLPHPPEMOHON A "
					+ "WHERE A.ID_PEMOHON = ' " + idPemohon + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			r.update("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", rs.getString("ID_KATEGORIPEMOHON"));
			if ("2".equals(rs.getString("ID_KATEGORIPEMOHON"))) {
				r.add("NAMA", txtNamaA == null ? "" : txtNamaA.toUpperCase());
				r.add("ID_JENISPENGENALAN", idJenisPendaftaran == null ? ""
						: idJenisPendaftaran);
				r.add("NO_PENGENALAN", txtNoPendaftaran == null ? ""
						: txtNoPendaftaran);
				r.add("NO_TEL_PEJABAT", txtNoTelA == null ? "" : txtNoTelA);
				r.add("NO_FAX", txtNoFaksA == null ? "" : txtNoFaksA);
				r.add("EMEL", txtEmelA == null ? "" : txtEmelA);
				r.add("ALAMAT1_TETAP",
						txtAlamat1A == null ? "" : txtAlamat1A.toUpperCase());
				r.add("ALAMAT2_TETAP",
						txtAlamat2A == null ? "" : txtAlamat2A.toUpperCase());
				r.add("ALAMAT3_TETAP",
						txtAlamat3A == null ? "" : txtAlamat3A.toUpperCase());
				r.add("POSKOD_TETAP", txtPoskodA == null ? "" : txtPoskodA);
				r.add("ID_BANDARTETAP", idBandarS == null ? "" : idBandarS);
				r.add("ID_NEGERITETAP", idNegeriS == null ? "" : idNegeriS);
				r.add("MODAL_DIBENARKAN", modalBenar == null ? "" : modalBenar);
				r.add("MODAL_JELAS", modalJelas == null ? "" : modalJelas);

			} else {
				System.out.println("warga " + idWarga);
				r.add("ID_WARGANEGARA", idWarga == null ? "" : idWarga);
				r.add("NAMA", txtNamaI == null ? "" : txtNamaI.toUpperCase());
				r.add("ID_JENISPENGENALAN", idJenisPengenalan == null ? ""
						: idJenisPengenalan);
				r.add("NO_PENGENALAN", (txtNoKP1) == null ? "" : txtNoKP1);
				r.add("PEKERJAAN", txtPekerjaan == null ? "" : txtPekerjaan);
				r.add("ID_JANTINA", idJantina == null ? "" : idJantina);
				r.add("ID_BANGSA", idBangsa == null ? "" : idBangsa);
				r.add("EMEL", txtEmelI == null ? "" : txtEmelI);
				r.add("ALAMAT1_TETAP",
						txtAlamat1I == null ? "" : txtAlamat1I.toUpperCase());
				r.add("ALAMAT2_TETAP",
						txtAlamat2I == null ? "" : txtAlamat2I.toUpperCase());
				r.add("ALAMAT3_TETAP",
						txtAlamat3I == null ? "" : txtAlamat3I.toUpperCase());
				r.add("POSKOD_TETAP", txtPoskodI == null ? "" : txtPoskodI);
				r.add("ID_BANDARTETAP", idBandarI == null ? "" : idBandarI);
				r.add("ID_NEGERITETAP", idNegeriI == null ? "" : idNegeriI);
				r.add("NO_TEL_RUMAH", txtNoTelefonI == null ? ""
						: txtNoTelefonI);
				r.add("NO_TEL_BIMBIT", txtNoTelefonBimbit == null ? ""
						: txtNoTelefonBimbit);
				r.add("NO_FAX", txtNoFaksI == null ? "" : txtNoFaksI);
			}
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPEMOHON");
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

	public void setMaklumatPenyewaan(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPenyewaan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT C.ID_PHPPERMOHONANSEWA, C.TAJUK_SEWA, C.FLAG_GUNA, C.LUAS_MHN, C.ID_UNITLUASMHN, C.LUAS_BAKI, C.ID_UNITLUASBAKI, D.ID_LUAS, D.LUAS, E.KETERANGAN "
					+ " FROM TBLPERMOHONAN A, TBLPHPHAKMILIKPERMOHONAN B, TBLPHPPERMOHONANSEWA C, TBLHTPHAKMILIK D, TBLRUJLUAS E"
					+ " WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_HAKMILIK = D.ID_HAKMILIK AND E.ID_LUAS = D.ID_LUAS AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonanSewa",
						rs.getString("ID_PHPPERMOHONANSEWA") == null ? "" : rs
								.getString("ID_PHPPERMOHONANSEWA"));
				h.put("tajukSewa",
						rs.getString("TAJUK_SEWA") == null ? "" : rs
								.getString("TAJUK_SEWA"));
				h.put("flagGuna",
						rs.getString("FLAG_GUNA") == null ? "" : rs
								.getString("FLAG_GUNA"));
				h.put("luasMohon",
						rs.getString("LUAS_MHN") == null ? "" : rs
								.getString("LUAS_MHN"));
				h.put("idLuasMohon",
						rs.getString("ID_UNITLUASMHN") == null ? "" : rs
								.getString("ID_UNITLUASMHN"));
				h.put("luasBaki",
						rs.getString("LUAS_BAKI") == null ? "" : rs
								.getString("LUAS_BAKI"));
				h.put("idLuasBaki",
						rs.getString("ID_UNITLUASBAKI") == null ? "" : rs
								.getString("ID_UNITLUASBAKI"));
				h.put("luasLot",
						rs.getString("LUAS") == null
								|| rs.getString("KETERANGAN") == null ? "" : rs
								.getString("LUAS")
								+ " "
								+ rs.getString("KETERANGAN"));
				h.put("keteranganLuas", rs.getString("KETERANGAN") == null ? ""
						: rs.getString("KETERANGAN"));
				h.put("luasAsal",
						rs.getString("LUAS") == null ? "" : rs
								.getString("LUAS"));
				h.put("idLuas",
						rs.getString("ID_LUAS") == null ? "" : rs
								.getString("ID_LUAS"));
				beanMaklumatPenyewaan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPenyewaan() {
		return beanMaklumatPenyewaan;
	}

	public void updatePermohonanPenyewaan(String idPermohonanSewa,
			String txtTujuan, String luasKegunaan, String idLuas,
			String luasSewa, String bakiLuas, HttpSession session)
			throws Exception {

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

			// TBLPHPPERMOHONANSEWA
			r.update("ID_PHPPERMOHONANSEWA", idPermohonanSewa);

			r.add("TAJUK_SEWA", txtTujuan);
			r.add("FLAG_GUNA", luasKegunaan);
			r.add("ID_UNITLUASMHN", idLuas);
			r.add("LUAS_MHN", luasSewa);
			r.add("ID_UNITLUASBAKI", idLuas);
			r.add("LUAS_BAKI", bakiLuas);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPERMOHONANSEWA");
			System.out.println("update permohonan sewa : " + sql);
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

	public void savePengarah(String idPemohon, String idWargaPengarah,
			String txtNamaPengarah, String idJenisPengenalanPengarah,
			String txtNoPengenalan, String txtPasport, String idBangsaPengarah,
			String txtSaham, HttpSession session) throws Exception {
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

			// TBLPHPPENGARAH
			long idPengarah = DB.getNextID("TBLPHPPENGARAH_SEQ");
			r.add("ID_PENGARAH", idPengarah);
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_WARGANEGARA", idWargaPengarah);
			r.add("NAMA", txtNamaPengarah);
			r.add("ID_JENISPENGENALAN", idJenisPengenalanPengarah);
			r.add("NO_JENISPENGENALAN", txtNoPengenalan);
			r.add("NO_PASSPORT", txtPasport);
			r.add("ID_BANGSA", idBangsaPengarah);
			r.add("PEGANGAN_SAHAM", txtSaham);

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLPHPPENGARAH");
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

	public void setSenaraiPengarah(String idPemohon) throws Exception {
		Db db = null;
		String sql = "";

		try {
			listPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PENGARAH, A.ID_WARGANEGARA, A.NAMA, A.ID_JENISPENGENALAN, A.NO_JENISPENGENALAN, A.NO_PASSPORT, A.ID_BANGSA, A.PEGANGAN_SAHAM, B.KETERANGAN "
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
				h.put("idWargaPengarah",
						rs.getString("ID_WARGANEGARA") == null ? "" : rs
								.getString("ID_WARGANEGARA"));
				h.put("namaPengarah", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("idJenisPengenalanPengarah",
						rs.getString("ID_JENISPENGENALAN") == null ? "" : rs
								.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalanPengarah",
						rs.getString("NO_JENISPENGENALAN") == null ? "" : rs
								.getString("NO_JENISPENGENALAN"));
				h.put("noPasport", rs.getString("NO_PASSPORT") == null ? ""
						: rs.getString("NO_PASSPORT"));
				h.put("idBangsaPengarah",
						rs.getString("ID_BANGSA") == null ? "" : rs
								.getString("ID_BANGSA"));
				h.put("peganganSaham",
						rs.getString("PEGANGAN_SAHAM") == null ? "" : rs
								.getString("PEGANGAN_SAHAM"));
				h.put("warganegaraPengarah",
						rs.getString("KETERANGAN") == null ? "" : rs.getString(
								"KETERANGAN").toUpperCase());

				listPengarah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getListPengarah() {
		return listPengarah;
	}

	public void setMaklumatPengarah(String idPengarah) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPengarah = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PENGARAH, A.ID_WARGANEGARA, A.NAMA, A.ID_JENISPENGENALAN, A.NO_JENISPENGENALAN, A.NO_PASSPORT, A.ID_BANGSA, A.PEGANGAN_SAHAM "
					+ " FROM TBLPHPPENGARAH A "
					+ " WHERE A.ID_PENGARAH = '"
					+ idPengarah + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idWargaPengarah",
						rs.getString("ID_WARGANEGARA") == null ? "" : rs
								.getString("ID_WARGANEGARA"));
				h.put("txtNamaPengarah", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("idJenisPengenalanPengarah",
						rs.getString("ID_JENISPENGENALAN") == null ? "" : rs
								.getString("ID_JENISPENGENALAN"));
				h.put("txtNoPengenalan",
						rs.getString("NO_JENISPENGENALAN") == null ? "" : rs
								.getString("NO_JENISPENGENALAN"));
				h.put("txtPasport", rs.getString("NO_PASSPORT") == null ? ""
						: rs.getString("NO_PASSPORT"));
				h.put("idBangsaPengarah",
						rs.getString("ID_BANGSA") == null ? "" : rs
								.getString("ID_BANGSA"));
				h.put("txtSaham", rs.getString("PEGANGAN_SAHAM") == null ? ""
						: rs.getString("PEGANGAN_SAHAM"));
				beanMaklumatPengarah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getBeanMaklumatPengarah() {
		return beanMaklumatPengarah;
	}

	public void updatePengarah(String idPengarah, String idWargaPengarah,
			String txtNamaPengarah, String idJenisPengenalanPengarah,
			String txtNoPengenalan, String txtPasport, String idBangsaPengarah,
			String txtSaham, HttpSession session) throws Exception {
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

			// TBLPHPPENGARAH
			r.update("ID_PENGARAH", idPengarah);

			r.add("ID_WARGANEGARA", idWargaPengarah);
			r.add("NAMA", txtNamaPengarah);
			r.add("ID_JENISPENGENALAN", idJenisPengenalanPengarah);
			r.add("NO_JENISPENGENALAN", txtNoPengenalan);
			r.add("NO_PASSPORT", txtPasport);
			r.add("ID_BANGSA", idBangsaPengarah);
			r.add("PEGANGAN_SAHAM", txtSaham);

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPPENGARAH");
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

	public boolean checkMaklumatPermohonanLengkap(String idPermohonanSewa)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPPERMOHONANSEWA WHERE (TAJUK_SEWA IS NULL OR FLAG_GUNA IS NULL OR"
					+ " LUAS_MHN IS NULL ) AND ID_PHPPERMOHONANSEWA = '"
					+ idPermohonanSewa + "'";
			System.out.println("sql checking sewa " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public boolean checkMaklumatPemohonLengkap(String idPemohon)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLPHPPEMOHON WHERE (MODAL_DIBENARKAN IS NULL OR MODAL_JELAS IS NULL) "
					+ " AND ID_PEMOHON = '" + idPemohon + "'";

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateStatus(String idFail, String idPermohonan,
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

			// GET DATA LAMA FROM TBLHTPHAKMILIK
			sql = "SELECT A.ID_SUBURUSAN FROM TBLPFDFAIL A "
					+ "WHERE A.ID_FAIL = ' " + idFail + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			r = new SQLRenderer();
			long idSuburusanstatusfail = DB
					.getNextID("TBLRUJSUBURUSANSTATUSFAIL_SEQ");
			r.add("ID_SUBURUSANSTATUSFAIL", idSuburusanstatusfail);
			r.add("ID_PERMOHONAN", idPermohonan);
			if ("35".equals(rs.getString("ID_SUBURUSAN"))) {
				r.add("ID_SUBURUSANSTATUS", "16102583"); // JABATAN TEKNIKAL
			} else if ("36".equals(rs.getString("ID_SUBURUSAN"))) {
				r.add("ID_SUBURUSANSTATUS", "16102590"); // JABATAN TEKNIKAL
			} else if ("37".equals(rs.getString("ID_SUBURUSAN"))) {
				r.add("ID_SUBURUSANSTATUS", "16102597"); // JABATAN TEKNIKAL
			}
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
			throw new Exception("Ralat : Masalah penyimpanan data "
					+ ex.getMessage());

		} finally {
			if (db != null)
				db.close();
		}
	}

}

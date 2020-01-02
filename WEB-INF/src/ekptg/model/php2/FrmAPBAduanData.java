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

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmAPBAduanData {
	static Logger myLogger = Logger.getLogger(FrmAPBAduanData.class);
	private Vector beanMaklumatPendaftaran = null;
	private Vector beanMaklumatAgensi = null;
	private Vector beanMaklumatPengadu = null;
	private Vector senaraiFail = null;
	private Vector beanMaklumatAduan = null;
	private Vector beanMaklumatSiasatan = null;
	private Vector beanMaklumatKeputusanAduan = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianFail(String namaPengadu, String idPermohonan)
			throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_ADUANAPB,B.NAMA,A.PERKARA"
					+ " FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B,TBLPERMOHONAN C  "
					+ " WHERE A.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			// namaPengadu
			if (namaPengadu != null) {
				if (!namaPengadu.trim().equals("")) {
					sql = sql + " AND UPPER(B.NAMA) LIKE '%' ||'"
							+ namaPengadu.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY A.ID_ADUANAPB DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idPengadu", rs.getString("ID_ADUANAPB") == null ? ""
						: rs.getString("ID_ADUANAPB"));
				h.put("namaPengadu", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("perkara", rs.getString("PERKARA") == null ? "" : rs
						.getString("PERKARA").toUpperCase());
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public String daftarBaru(String idPermohonan, String tarikhTerima,
			String tarikhSurat, String txtPerkara, String idKategoriPemohon,
			String txtNama, String txtAlamat1, String txtAlamat2,
			String txtAlamat3, String txtPoskod, String idNegeri,
			String idKementerian, String idAgensi, String noRujSurat,
			HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idAduanString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// TBLPHPPEMOHON
			r = new SQLRenderer();
			long idPemohon = DB.getNextID("TBLPHPPEMOHON_SEQ");
			r.add("ID_PEMOHON", idPemohon);
			r.add("ID_KATEGORIPEMOHON", idKategoriPemohon);
			if ("3".equals(idKategoriPemohon)) {
				r.add("ID_AGENSI", idAgensi);
				r.add("ID_MENTERI", idKementerian);

				sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
						+ idAgensi + "'";
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

			} else {

				r.add("NAMA", txtNama == null ? "" : txtNama.toUpperCase());
				r.add("ALAMAT1_TETAP",
						txtAlamat1 == null ? "" : txtAlamat1.toUpperCase());
				r.add("ALAMAT2_TETAP",
						txtAlamat2 == null ? "" : txtAlamat2.toUpperCase());
				r.add("ALAMAT3_TETAP",
						txtAlamat3 == null ? "" : txtAlamat3.toUpperCase());
				r.add("POSKOD_TETAP", txtPoskod == null ? "" : txtPoskod);
				r.add("ID_NEGERITETAP", idNegeri == null ? "" : idNegeri);
			}

			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPHPPEMOHON");
			stmt.executeUpdate(sql);

			// TBLPHPADUANAPB
			r = new SQLRenderer();
			long idAduanAPB = DB.getNextID("TBLPHPADUANAPB_SEQ");
			r.add("ID_ADUANAPB", idAduanAPB);
			idAduanString = String.valueOf(idAduanAPB);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("ID_PEMOHON", idPemohon);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("PERKARA", txtPerkara);
			r.add("NO_RUJ_SURAT", noRujSurat);
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPHPADUANAPB");

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
		return idAduanString;
	}

	public void setMaklumatPendaftaran(String idPengadu) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPendaftaran = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.TARIKH_SURAT, A.TARIKH_TERIMA, A.PERKARA, A.NO_RUJ_SURAT, A.JENIS_DAFTAR, B.NO_TEL_RUMAH, B.NO_TEL_BIMBIT, B.EMEL "
					+ " FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_ADUANAPB = '"
					+ idPengadu + "'";

			myLogger.info("beanMaklumatPendaftaran :: " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhSurat", rs.getDate("TARIKH_SURAT") == null ? ""
						: sdf.format(rs.getDate("TARIKH_SURAT")));
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("perkara",
						rs.getString("PERKARA") == null ? "" : rs
								.getString("PERKARA"));
				h.put("rujSurat", rs.getString("NO_RUJ_SURAT") == null ? ""
						: rs.getString("NO_RUJ_SURAT").toUpperCase());
				h.put("socJenisDaftar",
						rs.getString("JENIS_DAFTAR") == null ? "" : rs
								.getString("JENIS_DAFTAR").toUpperCase());
				h.put("txtNoTelefon", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("txtNoHp", rs.getString("NO_TEL_BIMBIT") == null ? ""
						: rs.getString("NO_TEL_BIMBIT"));
				h.put("txtEmail",
						rs.getString("EMEL") == null ? "" : rs
								.getString("EMEL"));
				beanMaklumatPendaftaran.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatPemohon(String idPengadu) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPengadu = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT B.NAMA, B.ALAMAT1_TETAP, B.ALAMAT2_TETAP, B.ALAMAT3_TETAP,B.POSKOD_TETAP,B.ID_NEGERITETAP,"
					+ " B.ID_KATEGORIPEMOHON, B.ID_AGENSI, B.ID_MENTERI, B.NO_TEL_RUMAH, B.NO_TEL_BIMBIT, B.EMEL, A.MAKLUMBALAS_PELESEN "
					+ " FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_ADUANAPB = '"
					+ idPengadu + "'";
			myLogger.info("beanMaklumatPengadu :: " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {

				h = new Hashtable();
				h.put("idKategoriPengadu",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				if (rs.getString("ID_KATEGORIPEMOHON").equals("3")) {
					h.put("idAgensi", rs.getString("ID_AGENSI") == null ? ""
							: rs.getString("ID_AGENSI").toUpperCase());
					h.put("idKementerian",
							rs.getString("ID_MENTERI") == null ? "" : rs
									.getString("ID_MENTERI").toUpperCase());

				} else {
					h.put("nama", rs.getString("NAMA") == null ? "" : rs
							.getString("NAMA").toUpperCase());
					h.put("alamat1", rs.getString("ALAMAT1_TETAP") == null ? ""
							: rs.getString("ALAMAT1_TETAP").toUpperCase());
					h.put("alamat2", rs.getString("ALAMAT2_TETAP") == null ? ""
							: rs.getString("ALAMAT2_TETAP").toUpperCase());
					h.put("alamat3", rs.getString("ALAMAT3_TETAP") == null ? ""
							: rs.getString("ALAMAT3_TETAP").toUpperCase());
					h.put("poskod", rs.getString("POSKOD_TETAP") == null ? ""
							: rs.getString("POSKOD_TETAP"));
					h.put("idNegeri",
							rs.getString("ID_NEGERITETAP") == null ? "" : rs
									.getString("ID_NEGERITETAP"));
					h.put("txtNoTelefon",
							rs.getString("NO_TEL_RUMAH") == null ? "" : rs
									.getString("NO_TEL_RUMAH"));
					h.put("txtNoHp", rs.getString("NO_TEL_BIMBIT") == null ? ""
							: rs.getString("NO_TEL_BIMBIT"));
					h.put("txtEmail",
							rs.getString("EMEL") == null ? "" : rs
									.getString("EMEL"));
					h.put("txtCatatanMaklumbalas",
							rs.getString("MAKLUMBALAS_PELESEN") == null ? ""
									: rs.getString("MAKLUMBALAS_PELESEN"));
				}
				beanMaklumatPengadu.addElement(h);
				bil++;

			}

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

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatAduan(String idPengadu) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatAduan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.KETERANGAN_ADUAN, A.SUMBER_ADUAN"
					+ " FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_ADUANAPB = '"
					+ idPengadu + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("keteranganAduan",
						rs.getString("KETERANGAN_ADUAN") == null ? "" : rs
								.getString("KETERANGAN_ADUAN"));
				h.put("sumberAduan", rs.getString("SUMBER_ADUAN") == null ? ""
						: rs.getString("SUMBER_ADUAN"));
				beanMaklumatAduan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatSiasatan(String idPengadu) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatSiasatan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.TARIKH_SURAT_APMM, A.TARIKH_TERIMASRT_APMM, A.NO_RUJ_SURAT_APMM, A.LAPORAN_APMM, A.ULASAN_JKPTG, A.MAKLUMBALAS_PELESEN "
					+ " FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_ADUANAPB = '"
					+ idPengadu + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhSuratAPMM",
						rs.getDate("TARIKH_SURAT_APMM") == null ? "" : sdf
								.format(rs.getDate("TARIKH_SURAT_APMM")));
				h.put("tarikhTerimaSuratAPMM",
						rs.getDate("TARIKH_TERIMASRT_APMM") == null ? "" : sdf
								.format(rs.getDate("TARIKH_TERIMASRT_APMM")));
				h.put("noRujSuratAPMM",
						rs.getString("NO_RUJ_SURAT_APMM") == null ? "" : rs
								.getString("NO_RUJ_SURAT_APMM"));
				h.put("siasatanAPMM", rs.getString("LAPORAN_APMM") == null ? ""
						: rs.getString("LAPORAN_APMM"));
				h.put("siasatanJKPTG",
						rs.getString("ULASAN_JKPTG") == null ? "" : rs
								.getString("ULASAN_JKPTG"));
				h.put("txtCatatanMaklumbalas",
						rs.getString("MAKLUMBALAS_PELESEN") == null ? "" : rs
								.getString("MAKLUMBALAS_PELESEN"));

				beanMaklumatSiasatan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatKeputusanAduan(String idPengadu) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatKeputusanAduan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.TARIKH_KEPUTUSAN, A.ULASAN"
					+ " FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B WHERE A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_ADUANAPB = '"
					+ idPengadu + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("tarikhKeputusan",
						rs.getDate("TARIKH_KEPUTUSAN") == null ? "" : sdf
								.format(rs.getDate("TARIKH_KEPUTUSAN")));
				h.put("ulasanKeputusan", rs.getString("ULASAN") == null ? ""
						: rs.getString("ULASAN"));
				beanMaklumatKeputusanAduan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void updateAduan(String idAduan, String txtKeteranganAduan,
			String txtSumberAduan, HttpSession session) throws Exception {

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

			// TBLPHPADUANAPB
			r.update("ID_ADUANAPB", idAduan);
			r.add("KETERANGAN_ADUAN", txtKeteranganAduan);
			r.add("SUMBER_ADUAN", txtSumberAduan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPADUANAPB");
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

	public void updatePengadu(String idAduan, String txtNama,
			String txtAlamat1, String txtAlamat2, String txtAlamat3,
			String txtPoskod, String idNegeri, String idKementerian,
			String idAgensi, String txtNoHp, String txtNoTelefon,
			String txtEmail, HttpSession session) throws Exception {

		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idPemohon = "";
		String idKategoriPengadu = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT B.ID_PEMOHON,B.ID_KATEGORIPEMOHON FROM TBLPHPADUANAPB A, TBLPHPPEMOHON B WHERE"
					+ " A.ID_PEMOHON = B.ID_PEMOHON AND A.ID_ADUANAPB = '"
					+ idAduan + "'";

			ResultSet rs1 = stmt.executeQuery(sql);
			if (rs1.next()) {
				idPemohon = rs1.getString("ID_PEMOHON").toString();
				idKategoriPengadu = rs1.getString("ID_KATEGORIPEMOHON")
						.toString();
			} else {
				idPemohon = "";
			}

			// TBLPHPPEMOHON
			r.update("ID_PEMOHON", idPemohon);
			// r.add("ID_KATEGORIPEMOHON", idKategoriPengadu);
			if ("3".equals(idKategoriPengadu)) {
				r.add("ID_AGENSI", idAgensi);
				r.add("ID_MENTERI", idKementerian);

				sql = "SELECT * FROM TBLRUJAGENSI WHERE ID_AGENSI = '"
						+ idAgensi + "'";
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

			} else {

				r.add("NAMA", txtNama == null ? "" : txtNama.toUpperCase());
				r.add("ALAMAT1_TETAP",
						txtAlamat1 == null ? "" : txtAlamat1.toUpperCase());
				r.add("ALAMAT2_TETAP",
						txtAlamat2 == null ? "" : txtAlamat2.toUpperCase());
				r.add("ALAMAT3_TETAP",
						txtAlamat3 == null ? "" : txtAlamat3.toUpperCase());
				r.add("POSKOD_TETAP", txtPoskod == null ? "" : txtPoskod);
				r.add("ID_NEGERITETAP", idNegeri == null ? "" : idNegeri);
				r.add("NO_TEL_RUMAH", txtNoTelefon == null ? "" : txtNoTelefon);
				r.add("NO_TEL_BIMBIT", txtNoHp == null ? "" : txtNoHp);
				r.add("EMEL", txtEmail == null ? "" : txtEmail);
			}

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPPEMOHON");
			myLogger.info("UPDATE TAB MAKLUMAT PENGADU :: " + sql);
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

	public void updateSiasatan(String idAduan, String txtTarikhTerimaSuratAPMM,
			String txtTarikhSuratAPMM, String txtNoRujSuratAPMM,
			String txtSiasatanAPMM, String txtSiasatanJKPTG,
			String txtCatatanMaklumbalas, HttpSession session) throws Exception {

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

			String TTS = "to_date('" + txtTarikhTerimaSuratAPMM
					+ "','dd/MM/yyyy')";
			String TS = "to_date('" + txtTarikhSuratAPMM + "','dd/MM/yyyy')";

			// TBLPHPADUANAPB
			r.update("ID_ADUANAPB", idAduan);
			r.add("TARIKH_TERIMASRT_APMM", r.unquote(TTS));
			r.add("TARIKH_SURAT_APMM", r.unquote(TS));
			r.add("NO_RUJ_SURAT_APMM ", txtNoRujSuratAPMM);
			r.add("LAPORAN_APMM", txtSiasatanAPMM);
			r.add("ULASAN_JKPTG", txtSiasatanJKPTG);
			r.add("MAKLUMBALAS_PELESEN", txtCatatanMaklumbalas);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPADUANAPB");
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

	public void updateBasicAduan(String idAduan, String tarikhTerima,
			String tarikhSurat, String txtPerkara, String txtRujSurat,
			String socJenisDaftar, HttpSession session) throws Exception {

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

			String TT = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			String TS = "to_date('" + tarikhSurat + "','dd/MM/yyyy')";

			// TBLPHPADUANAPB
			r.update("ID_ADUANAPB", idAduan);
			r.add("TARIKH_SURAT", r.unquote(TS));
			r.add("TARIKH_TERIMA", r.unquote(TT));
			r.add("PERKARA", txtPerkara);
			r.add("NO_RUJ_SURAT", txtRujSurat);
			r.add("JENIS_DAFTAR", socJenisDaftar);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPADUANAPB");
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

	public void updateKeputusan(String idAduan, String txtTarikhKeputusan,
			String txtUlasanKeputusan, HttpSession session) throws Exception {

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

			String TK = "to_date('" + txtTarikhKeputusan + "','dd/MM/yyyy')";

			// TBLPHPADUANAPB
			r.update("ID_ADUANAPB", idAduan);
			r.add("TARIKH_KEPUTUSAN", r.unquote(TK));
			r.add("ULASAN ", txtUlasanKeputusan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLPHPADUANAPB");
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

	public Vector getBeanMaklumatPendaftaran() {
		return beanMaklumatPendaftaran;
	}

	public Vector getBeanMaklumatAgensi() {
		return beanMaklumatAgensi;
	}

	public Vector getBeanMaklumatPengadu() {
		return beanMaklumatPengadu;
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public Vector getBeanMaklumatAduan() {
		return beanMaklumatAduan;
	}

	public Vector getBeanMaklumatSiasatan() {
		return beanMaklumatSiasatan;
	}

	public Vector getBeanMaklumatKeputusanAduan() {
		return beanMaklumatKeputusanAduan;
	}

}

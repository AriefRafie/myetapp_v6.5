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
import lebah.util.Util;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPYWPopupRingkasanPertimbanganData {

	private Vector senaraiFailMesyuarat = null;
	private Vector senaraiFailPerlanjutanMesyuarat = null;
	private Vector beanKertasRingkasan = null;
	private Vector beanMaklumatPemohon = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianFail(String noFail, String idJenisPermohonan, String namaPemohon) throws Exception {
		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFailMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON, D.ID_JENIS_PERMOHONAN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPPERMOHONANSEWA D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND A.NO_FAIL IS NOT NULL AND A.ID_FAIL IS NOT NULL AND A.ID_SEKSYEN = '4' AND B.FLAG_AKTIF = 'Y'"
					+ " AND ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND B.ID_STATUS = '1610201'"
					+ " AND B.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLPHPMESYUARATPERMOHONAN WHERE FLAG_JENIS_PERMOHONAN = 'B')";
			
			if (noFail != "") {
				sql = sql + " AND A.NO_FAIL LIKE '%"+noFail+ "%'";
			}
			
			if (idJenisPermohonan != null) {
				if (!idJenisPermohonan.trim().equals("")
						&& !idJenisPermohonan.trim().equals("99999")) {
					sql = sql + " AND D.ID_JENIS_PERMOHONAN = '"
							+ idJenisPermohonan.trim() + "'";
				}
			}
			
			if (namaPemohon != "") {
				sql = sql + " AND C.NAMA LIKE '%"+namaPemohon+ "%'";
			}
			
			sql = sql + " ORDER BY A.TARIKH_DAFTAR_FAIL DESC";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				if("1".equals(rs.getString("ID_JENIS_PERMOHONAN"))) {
					h.put("jenisPermohonan", "PERMOHONAN BAHARU");
				} else if ("2".equals(rs.getString("ID_JENIS_PERMOHONAN"))) {
					h.put("jenisPermohonan", "PERMOHONAN PERLANJUTAN");
				}
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs
						.getString("NAMA_PEMOHON").toUpperCase());
				senaraiFailMesyuarat.addElement(h);
				bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiFailMesyuarat(String idFail) throws Exception {
		
		Db db = null;
		String sql = "";

		try {
			senaraiFailMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON, D.ID_JENIS_PERMOHONAN"
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPPERMOHONANSEWA D"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
					+ " AND A.NO_FAIL IS NOT NULL AND A.ID_FAIL IS NOT NULL AND A.ID_SEKSYEN = '4' AND B.FLAG_AKTIF = 'Y'"
					+ " AND ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND B.ID_STATUS = '1610201'"
					+ " AND B.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLPHPMESYUARATPERMOHONAN WHERE FLAG_JENIS_PERMOHONAN = 'B')"
			 		+ " ORDER BY A.TARIKH_DAFTAR_FAIL DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				if("1".equals(rs.getString("ID_JENIS_PERMOHONAN"))) {
					h.put("jenisPermohonan", "PERMOHONAN BAHARU");
				} else if ("2".equals(rs.getString("ID_JENIS_PERMOHONAN"))) {
					h.put("jenisPermohonan", "PERMOHONAN PERLANJUTAN");
				}
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs
						.getString("NAMA_PEMOHON").toUpperCase());
				senaraiFailMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setSenaraiFailPerlanjutanMesyuarat(String idFail) throws Exception {
		
		Db db = null;
		String sql = "";

		try {
			senaraiFailPerlanjutanMesyuarat = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT A.ID_FAIL, A.NO_FAIL, B.ID_PERMOHONAN, C.NAMA AS NAMA_PEMOHON, D.ID_JENIS_PERMOHONAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPPERMOHONANSEWA D"
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
				+ " AND A.NO_FAIL IS NOT NULL AND A.ID_FAIL IS NOT NULL AND A.ID_SEKSYEN = '4' AND B.FLAG_AKTIF = 'Y'"
				+ " AND ID_URUSAN IN (7,12,13) AND B.FLAG_PERJANJIAN = 'U' AND B.ID_STATUS = '1610201' AND D.ID_JENIS_PERMOHONAN = 2"
				+ " AND B.ID_PERMOHONAN NOT IN (SELECT ID_PERMOHONAN FROM TBLPHPMESYUARATPERMOHONAN WHERE FLAG_JENIS_PERMOHONAN = 'B')";
			
			sql = sql + " ORDER BY A.TARIKH_DAFTAR_FAIL DESC";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("idFail",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				

				h.put("namaPemohon", rs.getString("NAMA_PEMOHON") == null ? "" : rs
						.getString("NAMA_PEMOHON").toUpperCase());
				senaraiFailPerlanjutanMesyuarat.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanPilihanBaru(String idMesyuarat,String idPermohonan,HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		String sql = "";
		String idMesyuaratString = "";

		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			//INSERT
			long idMesyuaratPermohonan = DB.getNextID("TBLPHPMESYUARATPERMOHONAN_SEQ");
			r.add("ID_MESYUARAT_PERMOHONAN", idMesyuaratPermohonan);
			r.add("ID_MESYUARAT", idMesyuarat);
			r.add("ID_PERMOHONAN", idPermohonan);
			r.add("FLAG_JENIS_PERMOHONAN", "B");
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			sql = r.getSQLInsert("TBLPHPMESYUARATPERMOHONAN");
			stmt.executeUpdate(sql);
			conn.commit();
			
			AuditTrail.logActivity("1610201", "4", null, session, "INS",
					"MESYUARAT [" + idMesyuarat
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
		//return idMesyuaratString;
	}
	
	public void setMaklumatRingkasanPemohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanMaklumatPemohon = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT C.ID_PEMOHON, C.ID_KATEGORIPEMOHON, C.NAMA, C.ID_JENISPENGENALAN, C.NO_PENGENALAN,"
				+ " C.PEKERJAAN, C.MODAL_DIBENARKAN, C.MODAL_JELAS, D.TUJUAN_PENGAMBILAN, D.LOKASI_PERMOHONAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLPHPPMOHONNJDUALPERTAMA D"
				+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = D.ID_PERMOHONAN"
				+ " AND B.ID_PERMOHONAN = '" +idPermohonan+ "'";
			
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable();

				h.put("idPemohon", rs.getString("ID_PEMOHON"));
				h.put("idKategoriPemohon",
						rs.getString("ID_KATEGORIPEMOHON") == null ? "" : rs
								.getString("ID_KATEGORIPEMOHON"));
				h.put("nama",
						rs.getString("NAMA") == null ? "" : rs
								.getString("NAMA"));
				h.put("idJenisPengenalan",
						rs.getString("ID_JENISPENGENALAN") == null ? "99999"
								: rs.getString("ID_JENISPENGENALAN"));
				h.put("noPengenalan",
						rs.getString("NO_PENGENALAN") == null ? "" : rs
								.getString("NO_PENGENALAN"));
				h.put("pekerjaan",
						rs.getString("PEKERJAAN") == null ? "" : rs
								.getString("PEKERJAAN"));
				h.put("modalBenar",
						rs.getString("MODAL_DIBENARKAN") == null ? "-" : "RM" + Util
								.formatDecimal(Double.valueOf(rs
										.getString("MODAL_DIBENARKAN"))));
				h.put("modalJelas",
						rs.getString("MODAL_JELAS") == null ? "-" : "RM" + Util
								.formatDecimal(Double.valueOf(rs
										.getString("MODAL_JELAS"))));
				h.put("tujuan",
						rs.getString("TUJUAN_PENGAMBILAN") == null ? "" : rs
								.getString("TUJUAN_PENGAMBILAN"));
				h.put("lokasi",
						rs.getString("LOKASI_PERMOHONAN") == null ? "" : rs
								.getString("LOKASI_PERMOHONAN"));
				beanMaklumatPemohon.addElement(h);
				count++;
			}
		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setAPBMaklumatKertasRingkasan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKertasRingkasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_PERMOHONAN, A.ID_KERTASKERJAAPB , A.NOTA, A.ULASAN_JAS, A.ULASAN_NAHRIM, A.ULASAN_JAB_LAUT, A.ULASAN_JPS, A.ULASAN_PTG, A.ULASAN_JAB_GEOSAINS, A.ULASAN_JAB_PERIKANAN,"
					+ " A.ULASAN_PUSAT_HIDROGRAFI, A.ULASAN_POLISMARIN, A.ULASAN_KEM_KEBUDAYAAN,B.LOKASI_PERMOHONAN, A.ULASAN_JUPEM, C.CATATAN_RINGKASAN_PERTIMBANGAN "
					+ " FROM TBLPHPKERTASKERJAAPB A, TBLPHPPMOHONNJDUALPERTAMA B, TBLPHPMESYUARATPERMOHONAN C "
					+ " WHERE A.ID_PERMOHONAN = B.ID_PERMOHONAN AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.FLAG_KERTAS = '1' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("idKertasKerjaApb",
						rs.getString("ID_KERTASKERJAAPB") == null ? "" : rs
								.getString("ID_KERTASKERJAAPB"));
				h.put("lokasi", rs.getString("LOKASI_PERMOHONAN") == null ? ""
						: rs.getString("LOKASI_PERMOHONAN"));
				h.put("nota",
						rs.getString("NOTA") == null ? "" : rs
								.getString("NOTA"));
				h.put("ulasanJAS",
						rs.getString("ULASAN_JAS") == null ? "" : rs
								.getString("ULASAN_JAS"));
				h.put("ulasanNAHRIM",
						rs.getString("ULASAN_NAHRIM") == null ? "" : rs
								.getString("ULASAN_NAHRIM"));
				h.put("ulasanJLM",
						rs.getString("ULASAN_JAB_LAUT") == null ? "" : rs
								.getString("ULASAN_JAB_LAUT"));
				h.put("ulasanJPS",
						rs.getString("ULASAN_JPS") == null ? "" : rs
								.getString("ULASAN_JPS"));
				h.put("ulasanJMG",
						rs.getString("ULASAN_JAB_GEOSAINS") == null ? "" : rs
								.getString("ULASAN_JAB_GEOSAINS"));
				h.put("ulasanDOF",
						rs.getString("ULASAN_JAB_PERIKANAN") == null ? "" : rs
								.getString("ULASAN_JAB_PERIKANAN"));
				h.put("ulasanPHN",
						rs.getString("ULASAN_PUSAT_HIDROGRAFI") == null ? ""
								: rs.getString("ULASAN_PUSAT_HIDROGRAFI"));
				h.put("ulasanPolisMarin",
						rs.getString("ULASAN_POLISMARIN") == null ? "" : rs
								.getString("ULASAN_POLISMARIN"));
				h.put("ulasanKemBudaya",
						rs.getString("ULASAN_KEM_KEBUDAYAAN") == null ? "" : rs
								.getString("ULASAN_KEM_KEBUDAYAAN"));
				h.put("ulasanJUPEM", rs.getString("ULASAN_JUPEM") == null ? ""
						: rs.getString("ULASAN_JUPEM"));
				h.put("ulasanPTG", rs.getString("ULASAN_PTG") == null ? ""
						: rs.getString("ULASAN_PTG"));
				h.put("catatanRingkasanPertimbangan",
						rs.getString("CATATAN_RINGKASAN_PERTIMBANGAN") == null ? "" : rs
								.getString("CATATAN_RINGKASAN_PERTIMBANGAN"));

				beanKertasRingkasan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void setMaklumatKertasRingkasan(String idPermohonan)
			throws Exception {
		Db db = null;
		String sql = "";

		try {
			beanKertasRingkasan = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_KERTASKERJA, A.ULASAN_KJP, A.ULASAN_JPPH, A.ULASAN_JKPTGN, A.ULASAN_KEM_KEWANGAN, A.ULASAN_KEM_WP, A.ULASAN_PTG,"
					+ " A.ULASAN_DBKL, A.ULASAN_BPH, A.FLAG_PAJAKAN, A.FLAG_PENSWASTAAN, A.NAMA_PEGAWAI_HTP, A.TARIKH_RUJUKAN_HTP, B.CATATAN_RINGKASAN_PERTIMBANGAN"
					+ " FROM TBLPHPKERTASKERJAPENYEWAAN A, TBLPHPMESYUARATPERMOHONAN B WHERE A.ID_PERMOHONAN=B.ID_PERMOHONAN AND A.FLAG_KERTAS = '1' AND A.ID_PERMOHONAN = '"
					+ idPermohonan + "'";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idKertasKerja",
						rs.getString("ID_KERTASKERJA") == null ? "" : rs
								.getString("ID_KERTASKERJA"));
				h.put("ulasanKJP",
						rs.getString("ULASAN_KJP") == null ? "" : rs
								.getString("ULASAN_KJP"));
				h.put("ulasanJPPH", rs.getString("ULASAN_JPPH") == null ? ""
						: rs.getString("ULASAN_JPPH"));
				h.put("ulasanJKPTGN",
						rs.getString("ULASAN_JKPTGN") == null ? "" : rs
								.getString("ULASAN_JKPTGN"));
				h.put("ulasanKemKewangan",
						rs.getString("ULASAN_KEM_KEWANGAN") == null ? "" : rs
								.getString("ULASAN_KEM_KEWANGAN"));
				h.put("ulasanKemWP", rs.getString("ULASAN_KEM_WP") == null ? ""
						: rs.getString("ULASAN_KEM_WP"));
				h.put("ulasanPTG",
						rs.getString("ULASAN_PTG") == null ? "" : rs
								.getString("ULASAN_PTG"));
				h.put("ulasanDBKL", rs.getString("ULASAN_DBKL") == null ? ""
						: rs.getString("ULASAN_DBKL"));
				h.put("ulasanBPH",
						rs.getString("ULASAN_BPH") == null ? "" : rs
								.getString("ULASAN_BPH"));
				h.put("flagPajakan", rs.getString("FLAG_PAJAKAN") == null ? ""
						: rs.getString("FLAG_PAJAKAN"));
				h.put("flagPenswastaan",
						rs.getString("FLAG_PENSWASTAAN") == null ? "" : rs
								.getString("FLAG_PENSWASTAAN"));
				h.put("namaPegawai",
						rs.getString("NAMA_PEGAWAI_HTP") == null ? "" : rs
								.getString("NAMA_PEGAWAI_HTP"));
				h.put("tarikhRujukan",
						rs.getDate("TARIKH_RUJUKAN_HTP") == null ? "" : sdf
								.format(rs.getDate("TARIKH_RUJUKAN_HTP")));
				h.put("catatanRingkasanPertimbangan",
						rs.getString("CATATAN_RINGKASAN_PERTIMBANGAN") == null ? "" : rs
								.getString("CATATAN_RINGKASAN_PERTIMBANGAN"));
				beanKertasRingkasan.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public void simpanCatatanRingkasanPertimbangan(String idPermohonan,String catatanRingkasanPertimbangan, HttpSession session) 
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

			// UPDATE
			r.update("ID_PERMOHONAN", idPermohonan);
			r.add("CATATAN_RINGKASAN_PERTIMBANGAN", catatanRingkasanPertimbangan);
			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
			sql = r.getSQLUpdate("TBLPHPMESYUARATPERMOHONAN");
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
	
	public Vector getSenaraiFailMesyuarat() {
		return senaraiFailMesyuarat;
	}
	
	public Vector getMaklumatRingkasanPertimbangan() {
		return beanKertasRingkasan;
	}
	
	public Vector getMaklumatRingkasanPemohon() {
		return beanMaklumatPemohon;
	}
	
}

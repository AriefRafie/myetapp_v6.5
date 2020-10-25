/**
 * 
 */
package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import ekptg.view.php2.FrmPLPDashboard;
import lebah.db.Db;

public class FrmSenaraiTugasanPLPData {

	private Vector senaraiFail = null;
	static Logger log = Logger.getLogger(FrmSenaraiTugasanPLPData.class);

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianFail(String noFail, String tajukFail,
			String tarikhTerima, String idNegeri, String idDaerah,
			String idMukim, String idJenisHakmilik, String noHakmilik,
			String noWarta, String idLot, String noLot, String noPegangan,
			String idStatus, String idSuburusan, String idJenisFail, String idKementerian,
			String idAgensi) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, A.TAJUK_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS, "
				+ "H.USER_LOGIN, I.NAMA_SUBURUSAN, A.ID_SUBURUSAN, J.NAMA_NEGERI, A.TARIKH_DAFTAR_FAIL "
				+ "FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, "
				+ "TBLPHPHAKMILIK F, USERS H, TBLRUJSUBURUSAN I, TBLRUJNEGERI J, TBLPHPULASANTEKNIKAL K "
				+ "WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN = '6' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS "
				+ "AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+) AND B.ID_PEMOHON = C.ID_PEMOHON "
				+ "AND B.ID_PERMOHONAN = E.ID_PERMOHONAN AND A.NO_FAIL IS NOT NULL AND F.ID_NEGERI = J.ID_NEGERI(+) "
				+ "AND B.ID_PERMOHONAN = K.ID_PERMOHONAN(+) AND A.ID_MASUK = H.USER_ID(+) AND A.ID_SUBURUSAN = I.ID_SUBURUSAN(+) "
				+ "AND B.FLAG_AKTIF = 'Y' AND A.NO_FAIL IS NOT NULL ";

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// tajukFail
			if (tajukFail != null) {
				if (!tajukFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
							+ tajukFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			// tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql
							+ " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '"
							+ sdf1.format(sdf.parse(tarikhTerima))
									.toUpperCase() + "'";
				}
			}

			// idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("")
						&& !idNegeri.trim().equals("99999")) {
					sql = sql + " AND F.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND F.ID_DAERAH = '" + idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND F.ID_MUKIM = '" + idMukim.trim() + "'";
				}
			}

			// idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("")
						&& !idKementerian.trim().equals("99999")) {
					sql = sql + " AND F.ID_KEMENTERIAN = '"
							+ idKementerian.trim() + "'";
				}
			}

			// idAgensi
			if (idAgensi != null) {
				if (!idAgensi.trim().equals("")
						&& !idAgensi.trim().equals("99999")) {
					sql = sql + " AND F.ID_AGENSI = '" + idAgensi.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND F.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND F.ID_LOT = '" + idLot.trim() + "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(F.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}
			// noPegangan
			if (noPegangan != null) {
				if (!noPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(F.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ noPegangan.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")
						&& !idStatus.trim().equals("99999")) {
					sql = sql + " AND B.ID_STATUS = '" + idStatus.trim() + "'";
				}
			}

			// idSuburusan
			if (idSuburusan != null) {
				if (!idSuburusan.trim().equals("")
						&& !idSuburusan.trim().equals("99999")) {
					sql = sql + " AND A.ID_SUBURUSAN = '" + idSuburusan.trim()
							+ "'";
				}
			}
			
			
			if (idJenisFail != null) {
				//SENARAI FAIL YANG TAMAT TEMPOH ULASAN KJP
				if (idJenisFail.equals("K")) {
					sql = sql + " AND K.FLAG_STATUS = 1 AND K.FLAG_AKTIF = 'Y' "
							+ "AND K.FLAG_KJP = 'KJP' AND K.TARIKH_JANGKA_TERIMA IS NOT NULL "
							+ "AND TO_CHAR(K.TARIKH_JANGKA_TERIMA, 'dd-MON-YY')  < TRUNC(SYSDATE)";
				}
				
				//SENARAI FAIL YANG TAMAT TEMPOH ULASAN JKPTG
				if (idJenisFail.equals("G")) {
					sql = sql + " AND K.FLAG_STATUS = 1 AND K.FLAG_AKTIF = 'Y' "
							+ "AND K.FLAG_KJP = 'KJT' AND K.TARIKH_JANGKA_TERIMA IS NOT NULL "
							+ "AND TO_CHAR(K.TARIKH_JANGKA_TERIMA, 'dd-MON-YY')  < TRUNC(SYSDATE)";
				}
						
			}
			
			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
			ResultSet rs = stmt.executeQuery(sql);
			log.info("woi kuda keluar tak? " +sql);

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
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("tarikhBukaFail",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));
				h.put("tajukFail", rs.getString("TAJUK_FAIL") == null ? "" : rs
						.getString("TAJUK_FAIL").toUpperCase());
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("userLogin",
						rs.getString("USER_LOGIN") == null ? "" : rs
								.getString("USER_LOGIN"));
				h.put("namaSubUrusan",
						rs.getString("NAMA_SUBURUSAN") == null ? "" : rs
								.getString("NAMA_SUBURUSAN"));
				h.put("idSubUrusan", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI"));
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}
}

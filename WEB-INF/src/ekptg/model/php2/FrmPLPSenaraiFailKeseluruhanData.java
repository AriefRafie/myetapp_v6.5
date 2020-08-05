package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmPLPSenaraiFailKeseluruhanData {

	private Vector senaraiFail = null;

	public void carianFail(String noFail, String noFailNegeri, String namaPemohon,
			String noPengenalan, String tarikhTerima, String idNegeri,
			String idDaerah, String idMukim, String idJenisHakmilik,
			String noHakmilik, String noWarta, String idLot, String noLot,
			String noPegangan, String idStatus, String userId,
			String idNegeriUser, String userRole) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, A.NO_FAIL_NEGERI, A.TAJUK_FAIL, B.TARIKH_TERIMA, C.NAMA, "
					+ " D.KETERANGAN, B.ID_STATUS, A.TARIKH_DAFTAR_FAIL, H.USER_NAME "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLPHPHAKMILIK F, USERS H"
					+ " WHERE A.ID_SEKSYEN = 4 AND A.ID_URUSAN = '6' AND A.ID_SUBURUSAN = '34' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
					+ " AND E.ID_HAKMILIKPERMOHONAN = F.ID_HAKMILIKPERMOHONAN(+) AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "
					+ " AND A.NO_FAIL IS NOT NULL AND A.ID_MASUK = H.USER_ID(+) AND E.FLAG_HAKMILIK = 'U'";

			if (!"16".equals(idNegeriUser)) {
				sql = sql + " AND A.ID_NEGERI = '" + idNegeriUser + "'";
			}

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}
			
			// noFailNegeri			
			if (noFailNegeri != null) {
				if (!noFailNegeri.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL_NEGERI) LIKE '%' ||'"
							+ noFailNegeri.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noPengenalan
			if (noPengenalan != null) {
				if (!noPengenalan.trim().equals("")) {
					sql = sql + " AND UPPER(C.NO_PENGENALAN) LIKE '%' ||'"
							+ noPengenalan.trim().toUpperCase() + "'|| '%'";
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

			sql = sql + " ORDER BY B.TARIKH_TERIMA DESC NULLS LAST ";
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
				h.put("noFailNegeri", rs.getString("NO_FAIL_NEGERI") == null ? "" : rs
						.getString("NO_FAIL_NEGERI").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("userLogin",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("tarikhBukaFail",
						rs.getDate("TARIKH_DAFTAR_FAIL") == null ? "" : sdf
								.format(rs.getDate("TARIKH_DAFTAR_FAIL")));

				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	private int daysBetween(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
	}

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}
}

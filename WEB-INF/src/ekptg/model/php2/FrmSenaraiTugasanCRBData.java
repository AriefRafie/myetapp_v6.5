/**
 * 
 */
package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

public class FrmSenaraiTugasanCRBData {

	private Vector senaraiFail = null;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianFail(String noFail, String namaPemohon,
			String tarikhTerima, String idNegeri, String idDaerah,
			String idMukim, String idJenisHakmilik, String noHakmilik,
			String noWarta, String idLot, String noLot, String noPegangan,
			String idStatus, String idKementerianTanah, String idAgensiTanah,
			String kegunaanTnh) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, A.NO_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, B.ID_STATUS, G.ID_NEGERI, G.ID_DAERAH, "
					+ "G.ID_MUKIM, G.NO_HAKMILIK, G.NO_WARTA, G.ID_LOT, G.NO_LOT, B.ID_STATUS, J.KOD_JENIS_HAKMILIK, K.KETERANGAN AS KETERANGANLOT, "
					+ " L.NAMA_MUKIM, M.NAMA_DAERAH, N.NAMA_NEGERI, I.NAMA_AGENSI, H.NAMA_KEMENTERIAN "
					+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D,TBLPHPHAKMILIKPERMOHONAN E, TBLHTPHAKMILIKAGENSI F , TBLHTPHAKMILIK G,TBLRUJKEMENTERIAN H,TBLRUJAGENSI I , "
					+ " TBLRUJJENISHAKMILIK J,TBLRUJLOT K, TBLRUJMUKIM L, TBLRUJDAERAH M, TBLRUJNEGERI N "
					+ " WHERE A.ID_URUSAN = '8' AND A.ID_SUBURUSAN = '56' AND A.ID_FAIL = B.ID_FAIL AND B.ID_STATUS = D.ID_STATUS"
					+ " AND E.ID_HAKMILIKAGENSI = F.ID_HAKMILIKAGENSI AND F.ID_HAKMILIK = G.ID_HAKMILIK"
					+ " AND B.ID_PEMOHON = C.ID_PEMOHON AND B.ID_PERMOHONAN = E.ID_PERMOHONAN "
					+ " AND F.ID_KEMENTERIAN = H.ID_KEMENTERIAN AND F.ID_AGENSI = I.ID_AGENSI AND G.ID_JENISHAKMILIK = J.ID_JENISHAKMILIK(+)"
					+ " AND G.ID_LOT = K.ID_LOT(+) AND G.ID_MUKIM = L.ID_MUKIM(+) AND G.ID_DAERAH = M.ID_DAERAH(+) AND G.ID_NEGERI = N.ID_NEGERI(+) ";

			// noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			// namaPemohon
			if (namaPemohon != null) {
				if (!namaPemohon.trim().equals("")) {
					sql = sql + " AND UPPER(C.NAMA) LIKE '%' ||'"
							+ namaPemohon.trim().toUpperCase() + "'|| '%'";
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
					sql = sql + " AND G.ID_NEGERI = '" + idNegeri.trim() + "'";
				}
			}

			// idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("")
						&& !idDaerah.trim().equals("99999")) {
					sql = sql + " AND G.ID_DAERAH = '" + idDaerah.trim() + "'";
				}
			}

			// idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("")
						&& !idMukim.trim().equals("99999")) {
					sql = sql + " AND G.ID_MUKIM = '" + idMukim.trim() + "'";
				}
			}

			// idJenisHakmilik
			if (idJenisHakmilik != null) {
				if (!idJenisHakmilik.trim().equals("")
						&& !idJenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND G.ID_JENISHAKMILIK = '"
							+ idJenisHakmilik.trim() + "'";
				}
			}

			// noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			// idLot
			if (idLot != null) {
				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
					sql = sql + " AND G.ID_LOT = '" + idLot.trim() + "'";
				}
			}

			// noLot
			if (noLot != null) {
				if (!noLot.trim().equals("")) {
					sql = sql + " AND UPPER(G.NO_LOT) LIKE '%' ||'"
							+ noLot.trim().toUpperCase() + "'|| '%'";
				}
			}

			// noPegangan
			if (noPegangan != null) {
				if (!noPegangan.trim().equals("")) {
					sql = sql + " AND UPPER(G.PEGANGAN_HAKMILIK) LIKE '%' ||'"
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

			// idkementeriantanah
			if (idKementerianTanah != null) {
				if (!idKementerianTanah.trim().equals("")
						&& !idKementerianTanah.trim().equals("99999")) {
					sql = sql + " AND F.ID_KEMENTERIAN = '"
							+ idKementerianTanah.trim() + "'";
				}
			}

			// idagensitanah
			if (idAgensiTanah != null) {
				if (!idAgensiTanah.trim().equals("")
						&& !idAgensiTanah.trim().equals("99999")) {
					sql = sql + " AND F.ID_AGENSI = '" + idAgensiTanah.trim()
							+ "'";
				}
			}
			// kegunaanTanah
			if (kegunaanTnh != null) {
				if (!kegunaanTnh.trim().equals("")) {
					sql = sql + " AND UPPER(G.KEGUNAAN_TANAH) LIKE '%' ||'"
							+ kegunaanTnh.trim().toUpperCase() + "'|| '%'";
				}
			}

			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
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
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? ""
						: sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("namaKementerian",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("kodHakmilik",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK"));
				h.put("keteranganLot",
						rs.getString("KETERANGANLOT") == null ? "" : rs
								.getString("KETERANGANLOT"));
				h.put("noLot",
						rs.getString("NO_LOT") == null ? "" : rs
								.getString("NO_LOT"));
				h.put("koma", ",");
				h.put("namaMukim",
						rs.getString("NAMA_MUKIM") == null ? "" : rs
								.getString("NAMA_MUKIM"));
				h.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? ""
						: rs.getString("NAMA_DAERAH"));
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

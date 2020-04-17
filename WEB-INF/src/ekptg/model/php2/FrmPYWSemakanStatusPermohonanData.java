package ekptg.model.php2;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FrmPYWSemakanStatusPermohonanData {

	private Vector senaraiFail = null;
	private static final Log log = LogFactory.getLog(FrmPYWSemakanStatusPermohonanData.class);

	public void carianFail(String noFail, String namaPemohon,
			String noPengenalan, String tarikhTerima, String idNegeri,
			String idDaerah, String idMukim, String idJenisHakmilik,
			String noHakmilik, String noWarta, String idLot, String noLot,
			String noPegangan, String idStatus, String idNegeriUser) throws Exception {

		Db db = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sql = "";

		try {
			senaraiFail = new Vector();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.NO_FAIL, A.TAJUK_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, H.NAMA_URUSAN, I.NAMA_SUBURUSAN, B.ID_PERMOHONAN, A.ID_FAIL" +
					" FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLPHPPEMOHON C, TBLRUJSTATUS D, TBLPHPHAKMILIKPERMOHONAN E, TBLRUJURUSAN H, TBLRUJSUBURUSAN I " +
					"WHERE A.ID_SEKSYEN = 4 " +
					"AND A.ID_URUSAN IN (7,12,13) " +
					"AND A.ID_URUSAN = H.ID_URUSAN " +
					"AND A.ID_FAIL = B.ID_FAIL " +
					"AND A.NO_FAIL IS NOT NULL " +
					"AND B.ID_STATUS = D.ID_STATUS " +
					"AND B.ID_PEMOHON = C.ID_PEMOHON " +
					"AND E.FLAG_HAKMILIK = 'U' " +
					"AND A.ID_SUBURUSAN = I.ID_SUBURUSAN " +
					" AND B.FLAG_AKTIF = 'Y' ";

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

//			// idNegeri
//			if (idNegeri != null) {
//				if (!idNegeri.trim().equals("")
//						&& !idNegeri.trim().equals("99999")) {
//					sql = sql + " AND F.ID_NEGERI = '" + idNegeri.trim() + "'";
//				}
//			}

//			// idDaerah
//			if (idDaerah != null) {
//				if (!idDaerah.trim().equals("")
//						&& !idDaerah.trim().equals("99999")) {
//					sql = sql + " AND F.ID_DAERAH = '" + idDaerah.trim() + "'";
//				}
//			}

//			// idMukim
//			if (idMukim != null) {
//				if (!idMukim.trim().equals("")
//						&& !idMukim.trim().equals("99999")) {
//					sql = sql + " AND F.ID_MUKIM = '" + idMukim.trim() + "'";
//				}
//			}

//			// idJenisHakmilik
//			if (idJenisHakmilik != null) {
//				if (!idJenisHakmilik.trim().equals("")
//						&& !idJenisHakmilik.trim().equals("99999")) {
//					sql = sql + " AND F.ID_JENISHAKMILIK = '"
//							+ idJenisHakmilik.trim() + "'";
//				}
//			}

			// noHakmilik
//			if (noHakmilik != null) {
//				if (!noHakmilik.trim().equals("")) {
//					sql = sql + " AND UPPER(F.NO_HAKMILIK) LIKE '%' ||'"
//							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
//				}
//			}

//			// noWarta
//			if (noWarta != null) {
//				if (!noWarta.trim().equals("")) {
//					sql = sql + " AND UPPER(F.NO_WARTA) LIKE '%' ||'"
//							+ noWarta.trim().toUpperCase() + "'|| '%'";
//				}
//			}

//			// idLot
//			if (idLot != null) {
//				if (!idLot.trim().equals("") && !idLot.trim().equals("99999")) {
//					sql = sql + " AND F.ID_LOT = '" + idLot.trim() + "'";
//				}
//			}

//			// noLot
//			if (noLot != null) {
//				if (!noLot.trim().equals("")) {
//					sql = sql + " AND UPPER(F.NO_LOT) LIKE '%' ||'"
//							+ noLot.trim().toUpperCase() + "'|| '%'";
//				}
//			}
			
//			// noPegangan
//			if (noPegangan != null) {
//				if (!noPegangan.trim().equals("")) {
//					sql = sql + " AND UPPER(F.PEGANGAN_HAKMILIK) LIKE '%' ||'"
//							+ noPegangan.trim().toUpperCase() + "'|| '%'";
//				}
//			}

			// idStatus
			if (idStatus != null) {
				if (!idStatus.trim().equals("")
						&& !idStatus.trim().equals("99999")) {
					sql = sql + " AND B.ID_STATUS = '" + idStatus.trim() + "'";
				}
			}

			sql = sql + " GROUP BY A.NO_FAIL, A.TAJUK_FAIL, B.TARIKH_TERIMA, C.NAMA, D.KETERANGAN, H.NAMA_URUSAN, I.NAMA_SUBURUSAN, A.ID_FAIL, B.ID_PERMOHONAN ";
			System.out.println(sql);
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
				h.put("namaPemohon", rs.getString("NAMA") == null ? "" : rs
						.getString("NAMA").toUpperCase());
				h.put("tajukFail",
						rs.getString("TAJUK_FAIL") == null ? "" : rs
								.getString("TAJUK_FAIL"));
				h.put("status",
						rs.getString("KETERANGAN") == null ? "" : rs
								.getString("KETERANGAN"));
				h.put("namaUrusan", rs.getString("NAMA_URUSAN") == null ? ""
						: rs.getString("NAMA_URUSAN"));
				h.put("namaSuburusan", rs.getString("NAMA_SUBURUSAN") == null ? ""
						: rs.getString("NAMA_SUBURUSAN"));
				
//				String idStatusDB = rs.getString("ID_STATUS");
//				String idPermohonanDB = rs.getString("ID_PERMOHONAN");

//				h.put("notifikasi", "");
				
//				if(idStatusDB.equals("1610195")){
//						sql = "SELECT MONTHS_BETWEEN(TARIKH_TAMAT_PERJANJIAN, SYSDATE) AS MONTHS_DIFF " +
//								"FROM TBLPHPPERJANJIAN " +
//								"WHERE TARIKH_SURAT_NOTIFIKASI IS NULL AND TARIKH_TAMAT_PERJANJIAN BETWEEN SYSDATE AND TARIKH_TAMAT_PERJANJIAN " +
//								"AND ID_PERMOHONAN = "+idPermohonanDB;
//						rs = stmt.executeQuery(sql);
//						
//						
//						while (rs.next()) {
//							if(rs.getString("MONTHS_DIFF") != null && rs.getString("MONTHS_DIFF").length()>0){
//									Double monthDiff = Double.parseDouble(rs.getString("MONTHS_DIFF"));
//									
//									if(monthDiff<6D){
//										h.put("notifikasi", "NOTIFIKASI TAMAT TEMPOH");
//									}
//							} 
//						}
//				} 
				
				senaraiFail.addElement(h);
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

	public Vector getSenaraiFail() {
		return senaraiFail;
	}

	public void setSenaraiFail(Vector senaraiFail) {
		this.senaraiFail = senaraiFail;
	}

}

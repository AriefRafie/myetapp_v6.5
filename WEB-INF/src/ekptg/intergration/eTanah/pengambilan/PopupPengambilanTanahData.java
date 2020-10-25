package ekptg.intergration.eTanah.pengambilan;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class PopupPengambilanTanahData {

	
	
	static Logger myLogger = Logger.getLogger(PopupPengambilanTanahData.class);
	private static SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();
	String cd = dateFormat.format(date);

	public List<Hashtable> listSenaraiLotTarikBalik(String id_penarikan, Db db)
			throws Exception {

		// Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List listSenaraiLotTarikBalik = null;

		String sql = "";
		Integer count = 0;

		try {
			// db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql = " SELECT DISTINCT NO_PENARIKANBALIK,TARIKH_PENARIKANBALIK,JENIS_PENARIKANBALIK,JENIS_PB_KETERANGAN,SEBAB_PENARIKANBALIK,"
					+ " NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL FROM( ";
			sql += " SELECT DISTINCT PB.NO_PENARIKANBALIK,TO_CHAR(PB.TARIKH_PENARIKAN_BALIK,'DD/MM/YYYY') AS TARIKH_PENARIKANBALIK,PB.JENIS_PENARIKANBALIK, "
					+ " (CASE WHEN PB.JENIS_PENARIKANBALIK = '1' THEN 'SEBAHAGIAN LOT' "
					+ " WHEN PB.JENIS_PENARIKANBALIK = '2' THEN 'KESELURUHAN LOT' "
					+ " ELSE '' END) AS JENIS_PB_KETERANGAN, "
					+ " PB.SEBAB_PENARIKANBALIK, C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, " +
					" C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 

					+"TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL," +
					" TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL "
					+ "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PBH "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  ";
			sql += " AND PB.ID_PENARIKANBALIK = PBH.ID_PENARIKANBALIK AND PBH.ID_HAKMILIK = C.ID_HAKMILIK AND PB.ID_PERMOHONAN = B.ID_PERMOHONAN AND PB.ID_PENARIKANBALIK = '"
					+ id_penarikan + "' ";
			sql += "  "
					+
					// " AND A.ID_FAIL = '"+id_fail+"' " +
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NOT NULL)  ";
			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			sql += " )";

			/*
			 * sql =
			 * " SELECT DISTINCT NO_PENARIKANBALIK,TARIKH_PENARIKANBALIK,JENIS_PENARIKANBALIK,JENIS_PB_KETERANGAN,SEBAB_PENARIKANBALIK,"
			 * +
			 * " NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
			 * +
			 * " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
			 * +
			 * " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, NO_WARTA, "
			 * + " TARIKH_WARTA FROM( ";
			 * 
			 * 
			 * sql +=
			 * " SELECT DISTINCT PB.NO_PENARIKANBALIK,TO_CHAR(PB.TARIKH_PENARIKAN_BALIK,'DD/MM/YYYY') AS TARIKH_PENARIKANBALIK,PB.JENIS_PENARIKANBALIK, "
			 * +
			 * " (CASE WHEN PB.JENIS_PENARIKANBALIK = '1' THEN 'SEBAHAGIAN LOT' "
			 * + " WHEN PB.JENIS_PENARIKANBALIK = '2' THEN 'KESELURUHAN LOT' "+
			 * " ELSE '' END) AS JENIS_PB_KETERANGAN, "+
			 * " PB.SEBAB_PENARIKANBALIK, "+
			 * " C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK,   "
			 * +
			 * " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT AS ID_UNIT_LUAS_ASAL,  (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL,  "
			 * +
			 * " TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, W.NO_WARTA,   "
			 * +
			 * " TO_CHAR(W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F,  "
			 * +
			 * " TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL,  TBLRUJLUAS JL_AMBIL, TBLPPTWARTA W "
			 * + " ,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PBH  "+
			 * " WHERE  A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI  "
			 * +
			 * " AND C.ID_MUKIM = E.ID_MUKIM  AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT = JL_ASAL.ID_LUAS(+)  AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  "
			 * + " AND PB.ID_PENARIKANBALIK = W.ID_PENARIKANBALIK(+)   "+
			 * " AND (((NVL(W.NO_WARTA,'0') != '0')  "+
			 * " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPENARIKANBALIK PB1  "
			 * +
			 * " WHERE WW.ID_PENARIKANBALIK = PB1.ID_PENARIKANBALIK  AND PB1.ID_PENARIKANBALIK = '"
			 * +id_penarikan+
			 * "' AND ROWNUM < 2)) OR (NVL (W.NO_WARTA, '0') = '0'))   "+
			 * " AND PB.ID_PENARIKANBALIK = PBH.ID_PENARIKANBALIK AND PBH.ID_HAKMILIK = C.ID_HAKMILIK AND PB.ID_PERMOHONAN = B.ID_PERMOHONAN AND PB.ID_PENARIKANBALIK = '"
			 * +id_penarikan+"' "+ " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC   ";
			 * 
			 * sql += " )";
			 */

			// dapat flag
			boolean setLimit = true;

			if (setLimit) {
				// sql += " WHERE ROWNUM <= 50 ";
			}

			myLogger.info("listSenaraiLotTarikBalik :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotTarikBalik = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);

				h.put("NO_PENARIKANBALIK",
						rs.getString("NO_PENARIKANBALIK") == null ? "" : rs
								.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("TARIKH_PENARIKANBALIK", rs
						.getString("TARIKH_PENARIKANBALIK") == null ? "" : rs
						.getString("TARIKH_PENARIKANBALIK").toUpperCase());
				h.put("JENIS_PENARIKANBALIK", rs
						.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
						.getString("JENIS_PENARIKANBALIK").toUpperCase());
				h.put("JENIS_PB_KETERANGAN", rs
						.getString("JENIS_PB_KETERANGAN") == null ? "" : rs
						.getString("JENIS_PB_KETERANGAN").toUpperCase());
				h.put("JENIS_PENARIKANBALIK", rs
						.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
						.getString("JENIS_PENARIKANBALIK").toUpperCase());
				h.put("SEBAB_PENARIKANBALIK", rs
						.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
						.getString("SEBAB_PENARIKANBALIK").toUpperCase());

				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				// h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
				// rs.getString("NO_WARTA").toUpperCase());
				// h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ?
				// "" : rs.getString("TARIKH_WARTA").toUpperCase());

				listSenaraiLotTarikBalik.add(h);

				bil++;
				count++;
			}

		} finally {/*
					 * if (rs != null) rs.close(); if (stmt != null)
					 * stmt.close(); if (db != null) db.close();
					 */

		}

		return listSenaraiLotTarikBalik;
	}

	public List<Hashtable> listSenaraiLotBorangC(String id_fail, Db db)
			throws Exception {
		// Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		List listSenaraiLotBorangC = null;
		String sql = "";
		Integer count = 0;
		try {
			// db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();
			sql = " SELECT DISTINCT NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL" +
				//	" , NO_WARTA,TARIKH_WARTA  " +
					" FROM( ";
			sql += " SELECT DISTINCT C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+"TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL " 
					// " ,W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA "
					+ "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL " 
				//	" , TBLPPTWARTA W "
					+ " WHERE " +
					//" B.ID_PERMOHONAN = W.ID_PERMOHONAN(+)  AND " +
					" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  ";
			/*sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) "; */
			sql += " AND A.ID_FAIL = '"+ id_fail+ "' "
			//sql +=  " AND ROWNUM < 500 "
					+" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL)  ";
			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			sql += " )";
			myLogger.info("listSenaraiLotBorangC x :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotBorangC = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				listSenaraiLotBorangC.add(h);

				bil++;
				count++;
			}

		} finally {/*
					 * if (rs != null) rs.close(); if (stmt != null)
					 * stmt.close(); if (db != null) db.close();
					 */
		}

		return listSenaraiLotBorangC;
	}
	
	public List<Hashtable> listSenaraiLotBorangA(String id_fail, Db db)
			throws Exception {
		// Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		List listSenaraiLotBorangA = null;
		String sql = "";
		Integer count = 0;
		try {
			// db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();
			sql = " SELECT DISTINCT NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL" +
				//	" , NO_WARTA,TARIKH_WARTA  " +
					" FROM( ";
			sql += " SELECT DISTINCT C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+"TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL " 
					// " ,W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA "
					+ "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL " 
				//	" , TBLPPTWARTA W "
					+ " WHERE " +
					//" B.ID_PERMOHONAN = W.ID_PERMOHONAN(+)  AND " +
					" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  ";
			/*sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) "; */
			sql += " AND A.ID_FAIL = '"+ id_fail+ "' "
			//sql +=  " AND ROWNUM < 500 "
					//+" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL "
					+ " AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL)  ";
			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			sql += " )";
			myLogger.info("listSenaraiLotBorangA x :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotBorangA = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				listSenaraiLotBorangA.add(h);

				bil++;
				count++;
			}

		} finally {/*
					 * if (rs != null) rs.close(); if (stmt != null)
					 * stmt.close(); if (db != null) db.close();
					 */
		}

		return listSenaraiLotBorangA;
	}
	
	
	public List<Hashtable> listSenaraiKategoriLampiran(String id_lampiran,String jenis_skrin, Db db)
			throws Exception {
		// Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		List listSenaraiKategoriLampiran = null;
		String sql = "";
		Integer count = 0;
		try {
			// db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();
			sql = " SELECT T.ID_LAMPIRANETANAH, T.KETERANGAN_LAMPIRANETANAH " +
					"FROM TBLPPTKATEGORILAMPIRANETANAH T WHERE T.ID_LAMPIRANETANAH IS NOT NULL ";
			
			/*
			if(jenis_skrin.equals("BorangA"))
			{
				sql += " AND T.ID_LAMPIRANETANAH NOT IN (7,4,) "; 
			}
			*/	
					
			sql += " ORDER BY ID_LAMPIRANETANAH ";
			myLogger.info("listSenaraiLotBorangC :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiKategoriLampiran = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("ID_LAMPIRANETANAH", rs.getString("ID_LAMPIRANETANAH") == null ? ""
						: rs.getString("ID_LAMPIRANETANAH").toUpperCase());
				h.put("KETERANGAN_LAMPIRANETANAH", rs.getString("KETERANGAN_LAMPIRANETANAH") == null ? "" : rs
						.getString("KETERANGAN_LAMPIRANETANAH").toUpperCase());
				listSenaraiKategoriLampiran.add(h);
				bil++;
				count++;
			}

		} finally {
		}

		return listSenaraiKategoriLampiran;
	}

	public List<Hashtable> listSenaraiLotBorangI(String id_fail, Db db)
			throws Exception {
		// Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		List listSenaraiLotBorangI = null;
		String sql = "";
		Integer count = 0;
		try {
			// db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();
			sql = " SELECT DISTINCT TARIKH_BORANGI,FLAG_SEGERA_SEBAHAGIAN,FLAG_AMBIL_SEGERA,NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL " +
					//", NO_WARTA,TARIKH_WARTA  " +
					" FROM( ";
			sql += " SELECT DISTINCT TO_CHAR(BI.TARIKH_BORANGI,'DD/MM/YYYY') AS TARIKH_BORANGI,C.FLAG_SEGERA_SEBAHAGIAN,C.FLAG_AMBIL_SEGERA, C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					+"TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL " 
					//" ,W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA "
					+ "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL, " +
				//	"TBLPPTWARTA W," +
					" TBLPPTBORANGI BI "
					+ " WHERE " +
					//"B.ID_PERMOHONAN = W.ID_PERMOHONAN(+)  AND " +
					" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  ";
			/*
			sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) ";*/
			sql += " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL)  "
					+ " AND C.FLAG_AMBIL_SEGERA = '1' AND BI.ID_PERMOHONAN = B.ID_PERMOHONAN ";
			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			sql += " )";
			myLogger.info("listSenaraiLotBorangC :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotBorangI = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
			/*	h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("TARIKH_BORANGI",
						rs.getString("TARIKH_BORANGI") == null ? "" : rs
								.getString("TARIKH_BORANGI").toUpperCase());
				listSenaraiLotBorangI.add(h);

				bil++;
				count++;
			}

		} finally {
		}

		return listSenaraiLotBorangI;
	}

	/*
	 * 
	 * 
	 * public List<Hashtable> listSenaraiLotBorangI(String id_fail,Db db) throws
	 * Exception {
	 * 
	 * 
	 * //Db db = null; ResultSet rs = null; Statement stmt = null;
	 * SimpleDateFormat sdf = null;
	 * 
	 * List listSenaraiLotBorangI = null;
	 * 
	 * String sql = ""; Integer count = 0;
	 * 
	 * try { // db = new Db(); sdf = new SimpleDateFormat("dd/MM/yyyy"); stmt =
	 * db.getStatement();
	 * 
	 * 
	 * 
	 * sql =
	 * " SELECT DISTINCT TARIKH_BORANGI,FLAG_SEGERA_SEBAHAGIAN,FLAG_AMBIL_SEGERA,NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
	 * + " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "+
	 * " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, NO_WARTA, "+
	 * " TARIKH_WARTA FROM( ";
	 * 
	 * 
	 * sql +=
	 * " SELECT DISTINCT TO_CHAR(BI.TARIKH_BORANGI,'DD/MM/YYYY') AS TARIKH_BORANGI,C.FLAG_SEGERA_SEBAHAGIAN,C.FLAG_AMBIL_SEGERA, C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
	 * +
	 * " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT AS ID_UNIT_LUAS_ASAL, "
	 * +
	 * " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, W.NO_WARTA, "
	 * +
	 * " TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
	 * + " TBLRUJLUAS JL_AMBIL, TBLPPTWARTA W,TBLPPTBORANGI BI  " +
	 * " WHERE  A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
	 * +
	 * " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT = JL_ASAL.ID_LUAS(+) "
	 * +
	 * " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+) AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) "
	 * ;
	 * 
	 * sql +=
	 * " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
	 * +
	 * " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
	 * +id_fail+"' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) "+
	 * " AND A.ID_FAIL = '"+id_fail+
	 * "' AND C.FLAG_AMBIL_SEGERA = '1' AND BI.ID_PERMOHONAN = B.ID_PERMOHONAN "
	 * ;
	 * 
	 * sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
	 * 
	 * sql += " )";
	 * 
	 * 
	 * 
	 * 
	 * // dapat flag boolean setLimit = true;
	 * 
	 * if (setLimit) { //sql += " WHERE ROWNUM <= 50 "; }
	 * 
	 * myLogger.info("listSenaraiLotBorangI :" + sql.toUpperCase());
	 * 
	 * stmt.setFetchSize(10); rs = stmt.executeQuery(sql); int bil = 1;
	 * listSenaraiLotBorangI = Collections.synchronizedList(new ArrayList());
	 * Map h = null;
	 * 
	 * while (rs.next()) { h = Collections.synchronizedMap(new HashMap());
	 * h.put("BIL", bil); h.put("NO_SUBJAKET",rs.getString("NO_SUBJAKET") ==
	 * null ? "" : rs.getString("NO_SUBJAKET").toUpperCase());
	 * h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" :
	 * rs.getString("ID_FAIL").toUpperCase());
	 * h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" :
	 * rs.getString("NO_FAIL").toUpperCase());
	 * h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" :
	 * rs.getString("ID_NEGERI").toUpperCase());
	 * h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" :
	 * rs.getString("ID_DAERAH").toUpperCase());
	 * h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" :
	 * rs.getString("ID_MUKIM").toUpperCase());
	 * h.put("KOD_JENIS_HAKMILIK",rs.getString("KOD_JENIS_HAKMILIK") == null ?
	 * "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
	 * h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" :
	 * rs.getString("NO_HAKMILIK").toUpperCase());
	 * h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" :
	 * rs.getString("ID_JENISHAKMILIK").toUpperCase());
	 * h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" :
	 * rs.getString("NO_LOT").toUpperCase());
	 * h.put("TARIKH_PERMOHONAN",rs.getString("TARIKH_PERMOHONAN") == null ? ""
	 * : rs.getString("TARIKH_PERMOHONAN").toUpperCase());
	 * h.put("ID_UNIT_LUAS_AMBIL",rs.getString("ID_UNIT_LUAS_AMBIL") == null ?
	 * "" : rs.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
	 * h.put("ID_UNIT_LUAS_ASAL",rs.getString("ID_UNIT_LUAS_ASAL") == null ? ""
	 * : rs.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
	 * h.put("JENIS_LUAS_ASAL",rs.getString("JENIS_LUAS_ASAL") == null ? "" :
	 * rs.getString("JENIS_LUAS_ASAL").toUpperCase());
	 * h.put("JENIS_LUAS_AMBIL",rs.getString("JENIS_LUAS_AMBIL") == null ? "" :
	 * rs.getString("JENIS_LUAS_AMBIL").toUpperCase());
	 * h.put("LUAS_ASAL",rs.getString("LUAS_ASAL") == null ? "" :
	 * rs.getString("LUAS_ASAL").toUpperCase() +(rs.getString("JENIS_LUAS_ASAL")
	 * == null ? "" : " "+rs.getString("JENIS_LUAS_ASAL").toUpperCase()));
	 * h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL") == null ? "" :
	 * rs.getString("LUAS_AMBIL").toUpperCase() +
	 * (rs.getString("JENIS_LUAS_AMBIL") == null ? "" :
	 * " "+rs.getString("JENIS_LUAS_AMBIL").toUpperCase()));
	 * h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
	 * rs.getString("NO_WARTA").toUpperCase());
	 * h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ? "" :
	 * rs.getString("TARIKH_WARTA").toUpperCase());
	 * h.put("TARIKH_BORANGI",rs.getString("TARIKH_BORANGI") == null ? "" :
	 * rs.getString("TARIKH_BORANGI").toUpperCase());
	 * listSenaraiLotBorangI.add(h);
	 * 
	 * bil++; count++; }
	 * 
	 * } finally {
	 * 
	 * 
	 * }
	 * 
	 * return listSenaraiLotBorangI; }
	 */

	public List<Hashtable> listSenaraiLotBorangK(String id_fail,
			String id_hakmilik) throws Exception {

		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List listSenaraiLotBorangK = null;

		String sql = "";
		Integer count = 0;

		try {
			db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql = " SELECT DISTINCT NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, " +
					/*" NO_WARTA, "
					+ " TARIKH_WARTA, " +*/
					"TARIKH_BORANGK, STATUS_AMBIL FROM( ";

			sql += " SELECT DISTINCT C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+" TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, " +
					/*" W.NO_WARTA, "
					+ " TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA," + */
					" TO_CHAR(BK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, "
					+ " (CASE WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) =  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'P' "
					+ " WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) <>  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'S' "
					+ " ELSE '' END) AS STATUS_AMBIL "
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL "
					//" , TBLPPTWARTA W "
					+ " ,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM  AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+)  AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+) " 
					//" AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) "
					+ " AND C.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' "
					/*+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)   "*/
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' AND HBK.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK  "
					+ "";

			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";

			sql += " )";

			// dapat flag
			boolean setLimit = true;

			if (setLimit) {
				// sql += " WHERE ROWNUM <= 50 ";
			}

			myLogger.info("listSenaraiLotBorangK :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotBorangK = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_ASAL",
						rs.getString("JENIS_LUAS_ASAL") == null ? "" : rs
								.getString("JENIS_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_AMBIL",
						rs.getString("JENIS_LUAS_AMBIL") == null ? "" : rs
								.getString("JENIS_LUAS_AMBIL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("TARIKH_BORANGK",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK").toUpperCase());
				h.put("STATUS_AMBIL", rs.getString("STATUS_AMBIL") == null ? ""
						: rs.getString("STATUS_AMBIL").toUpperCase()); // P-PENUH
																		// S-SEBAHAGIAN

				listSenaraiLotBorangK.add(h);

				bil++;
				count++;
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();

		}

		return listSenaraiLotBorangK;
	}

	public List<Hashtable> listSenaraiLotPU(String id_fail, String id_hakmilik)
			throws Exception {

		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List listSenaraiLotPU = null;

		String sql = "";
		Integer count = 0;

		try {
			db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql = " SELECT DISTINCT ID_FAIL,STATUS_AMBIL,NO_SUBJAKET,ID_PERMINTAANUKUR,NO_SYIT,NO_PU,TARIKH_BORANG_PU, TARIKH_TERIMA_PA_B1_JUPEM,NO_PA,NO_LOT_BARU,LUAS_PA,ID_UNIT_LUAS_PA,NO_FAIL,"
					+ " ID_NEGERI,ID_DAERAH,ID_MUKIM,KOD_JENIS_HAKMILIK, NO_HAKMILIK,ID_JENISHAKMILIK, NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL,ID_UNIT_LUAS_ASAL,"
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL,LUAS_ASAL,LUAS_AMBIL, " +
					//" NO_WARTA ,TARIKH_WARTA," +
					" TARIKH_BORANGK FROM( ";

			sql += " SELECT DISTINCT  C.NO_SUBJAKET,PU.ID_PERMINTAANUKUR,A.ID_FAIL, "
					+ " (SELECT  "
					+ " rtrim (xmlagg (xmlelement (e, PP.NO_PELAN || ', ')).extract ('//text()'), ', ') NO_SYIT "
					+ " FROM TBLPPTNOPELANPU PPU,TBLPPTNOPELAN PP "
					+ " WHERE PP.ID_NOPELAN = PPU.ID_NOPELAN  "
					+ " AND PPU.ID_PERMINTAANUKUR = PU.ID_PERMINTAANUKUR) AS NO_SYIT, "
					+ " PU.NO_PU,TO_CHAR(PU.TARIKH_PU,'DD/MM/YYYY') AS TARIKH_BORANG_PU, TO_CHAR(PU.TARIKH_TERIMA_PA,'DD/MM/YYYY') AS TARIKH_TERIMA_PA_B1_JUPEM, "
					+ " PU.NO_PA,PU.NO_LOT_BARU,TRIM(TO_CHAR(PU.LUAS_PU,'9999999999999990.9999')) AS LUAS_PA, '2' AS ID_UNIT_LUAS_PA, "
					+ " A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					+" TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, " +
					/*"W.NO_WARTA, "
					+ " TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, " +*/
					" TO_CHAR(BK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, "
					+ " (CASE WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) =  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'P' "
					+ " WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) <>  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'S' "
					+ " ELSE '' END) AS STATUS_AMBIL "
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL, " +
					//" TBLPPTWARTA W," +
					" TBLPPTPERMINTAANUKUR PU "
					+ " ,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+) " 
					//" AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) "
					/*+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2) "*/
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+ " AND PU.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK  "
					+ " AND C.ID_HAKMILIK = '" + id_hakmilik + "' " + "";

			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";

			sql += " )";

			// dapat flag
			boolean setLimit = true;

			if (setLimit) {
				// sql += " WHERE ROWNUM <= 50 ";
			}

			myLogger.info("listSenaraiLotPU :" + sql.toUpperCase());

			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotPU = Collections.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_ASAL",
						rs.getString("JENIS_LUAS_ASAL") == null ? "" : rs
								.getString("JENIS_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_AMBIL",
						rs.getString("JENIS_LUAS_AMBIL") == null ? "" : rs
								.getString("JENIS_LUAS_AMBIL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("TARIKH_BORANGK",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK").toUpperCase());
				h.put("STATUS_AMBIL", rs.getString("STATUS_AMBIL") == null ? ""
						: rs.getString("STATUS_AMBIL").toUpperCase()); // P-PENUH
																		// S-SEBAHAGIAN

				h.put("ID_PERMINTAANUKUR",
						rs.getString("ID_PERMINTAANUKUR") == null ? "" : rs
								.getString("ID_PERMINTAANUKUR").toUpperCase());
				h.put("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs
						.getString("NO_SYIT").toUpperCase());
				h.put("NO_PU", rs.getString("NO_PU") == null ? "" : rs
						.getString("NO_PU").toUpperCase());
				h.put("TARIKH_BORANG_PU",
						rs.getString("TARIKH_BORANG_PU") == null ? "" : rs
								.getString("TARIKH_BORANG_PU").toUpperCase());
				h.put("TARIKH_TERIMA_PA_B1_JUPEM", rs
						.getString("TARIKH_TERIMA_PA_B1_JUPEM") == null ? ""
						: rs.getString("TARIKH_TERIMA_PA_B1_JUPEM")
								.toUpperCase());
				h.put("NO_PA", rs.getString("NO_PA") == null ? "" : rs
						.getString("NO_PA").toUpperCase());
				h.put("NO_LOT_BARU", rs.getString("NO_LOT_BARU") == null ? ""
						: rs.getString("NO_LOT_BARU").toUpperCase());
				h.put("LUAS_PA", rs.getString("LUAS_PA") == null ? "" : rs
						.getString("LUAS_PA").toUpperCase());
				h.put("ID_UNIT_LUAS_PA",
						rs.getString("ID_UNIT_LUAS_PA") == null ? "" : rs
								.getString("ID_UNIT_LUAS_PA").toUpperCase());

				listSenaraiLotPU.add(h);

				bil++;
				count++;
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();

		}

		return listSenaraiLotPU;
	}

	/*
	 * public List<Hashtable> listSenaraiLotTarikBalik(String id_penarikan)
	 * throws Exception {
	 * 
	 * 
	 * Db db = null; ResultSet rs = null; Statement stmt = null;
	 * SimpleDateFormat sdf = null;
	 * 
	 * List listSenaraiLotTarikBalik = null;
	 * 
	 * String sql = ""; Integer count = 0;
	 * 
	 * try { db = new Db(); sdf = new SimpleDateFormat("dd/MM/yyyy"); stmt =
	 * db.getStatement();
	 * 
	 * 
	 * sql =
	 * " SELECT DISTINCT NO_PENARIKANBALIK,TARIKH_PENARIKANBALIK,JENIS_PENARIKANBALIK,JENIS_PB_KETERANGAN,SEBAB_PENARIKANBALIK,"
	 * +
	 * " NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
	 * + " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "+
	 * " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL FROM( "; sql +=
	 * " SELECT DISTINCT PB.NO_PENARIKANBALIK,TO_CHAR(PB.TARIKH_PENARIKAN_BALIK,'DD/MM/YYYY') AS TARIKH_PENARIKANBALIK,PB.JENIS_PENARIKANBALIK, "
	 * + " (CASE WHEN PB.JENIS_PENARIKANBALIK = '1' THEN 'SEBAHAGIAN LOT' "+
	 * " WHEN PB.JENIS_PENARIKANBALIK = '2' THEN 'KESELURUHAN LOT' "+
	 * " ELSE '' END) AS JENIS_PB_KETERANGAN, "+
	 * " PB.SEBAB_PENARIKANBALIK, C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
	 * +
	 * " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT AS ID_UNIT_LUAS_ASAL, "
	 * +
	 * " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL "
	 * +
	 * "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
	 * +
	 * " TBLRUJLUAS JL_AMBIL,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PBH "
	 * +
	 * " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
	 * +
	 * " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT = JL_ASAL.ID_LUAS(+) "
	 * + " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  "; sql +=
	 * " AND PB.ID_PENARIKANBALIK = PBH.ID_PENARIKANBALIK AND PBH.ID_HAKMILIK = C.ID_HAKMILIK AND PB.ID_PERMOHONAN = B.ID_PERMOHONAN AND PB.ID_PENARIKANBALIK = '"
	 * +id_penarikan+"' "; sql +=
	 * " AND (TRANSLATE(C.NO_HAKMILIK,'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
	 * + " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NOT NULL)  "; sql +=
	 * " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC "; sql += " )";
	 * 
	 * myLogger.info("listSenaraiLotTarikBalik :" + sql.toUpperCase());
	 * 
	 * stmt.setFetchSize(10); rs = stmt.executeQuery(sql); int bil = 1;
	 * listSenaraiLotTarikBalik = Collections.synchronizedList(new ArrayList());
	 * Map h = null;
	 * 
	 * while (rs.next()) { h = Collections.synchronizedMap(new HashMap());
	 * h.put("BIL", bil);
	 * 
	 * h.put("NO_PENARIKANBALIK",rs.getString("NO_PENARIKANBALIK") == null ? ""
	 * : rs.getString("NO_PENARIKANBALIK").toUpperCase());
	 * h.put("TARIKH_PENARIKANBALIK",rs.getString("TARIKH_PENARIKANBALIK") ==
	 * null ? "" : rs.getString("TARIKH_PENARIKANBALIK").toUpperCase());
	 * h.put("JENIS_PENARIKANBALIK",rs.getString("JENIS_PENARIKANBALIK") == null
	 * ? "" : rs.getString("JENIS_PENARIKANBALIK").toUpperCase());
	 * h.put("JENIS_PB_KETERANGAN",rs.getString("JENIS_PB_KETERANGAN") == null ?
	 * "" : rs.getString("JENIS_PB_KETERANGAN").toUpperCase());
	 * h.put("JENIS_PENARIKANBALIK",rs.getString("JENIS_PENARIKANBALIK") == null
	 * ? "" : rs.getString("JENIS_PENARIKANBALIK").toUpperCase());
	 * h.put("SEBAB_PENARIKANBALIK",rs.getString("SEBAB_PENARIKANBALIK") == null
	 * ? "" : rs.getString("SEBAB_PENARIKANBALIK").toUpperCase());
	 * h.put("NO_SUBJAKET",rs.getString("NO_SUBJAKET") == null ? "" :
	 * rs.getString("NO_SUBJAKET").toUpperCase());
	 * h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" :
	 * rs.getString("ID_FAIL").toUpperCase());
	 * h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" :
	 * rs.getString("NO_FAIL").toUpperCase());
	 * h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" :
	 * rs.getString("ID_NEGERI").toUpperCase());
	 * h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" :
	 * rs.getString("ID_DAERAH").toUpperCase());
	 * h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" :
	 * rs.getString("ID_MUKIM").toUpperCase());
	 * h.put("KOD_JENIS_HAKMILIK",rs.getString("KOD_JENIS_HAKMILIK") == null ?
	 * "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
	 * h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? "" :
	 * rs.getString("NO_HAKMILIK").toUpperCase());
	 * h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") == null ? "" :
	 * rs.getString("ID_JENISHAKMILIK").toUpperCase());
	 * h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" :
	 * rs.getString("NO_LOT").toUpperCase());
	 * h.put("TARIKH_PERMOHONAN",rs.getString("TARIKH_PERMOHONAN") == null ? ""
	 * : rs.getString("TARIKH_PERMOHONAN").toUpperCase());
	 * h.put("ID_UNIT_LUAS_AMBIL",rs.getString("ID_UNIT_LUAS_AMBIL") == null ?
	 * "" : rs.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
	 * h.put("ID_UNIT_LUAS_ASAL",rs.getString("ID_UNIT_LUAS_ASAL") == null ? ""
	 * : rs.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
	 * h.put("LUAS_ASAL",rs.getString("LUAS_ASAL") == null ? "" :
	 * rs.getString("LUAS_ASAL").toUpperCase() +(rs.getString("JENIS_LUAS_ASAL")
	 * == null ? "" : " "+rs.getString("JENIS_LUAS_ASAL").toUpperCase()));
	 * h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL") == null ? "" :
	 * rs.getString("LUAS_AMBIL").toUpperCase() +
	 * (rs.getString("JENIS_LUAS_AMBIL") == null ? "" :
	 * " "+rs.getString("JENIS_LUAS_AMBIL").toUpperCase()));
	 * listSenaraiLotTarikBalik.add(h);
	 * 
	 * bil++; count++; }
	 * 
	 * } finally { if (rs != null) rs.close(); if (stmt != null) stmt.close();
	 * if (db != null) db.close();
	 * 
	 * }
	 * 
	 * return listSenaraiLotTarikBalik; }
	 */

	List<Hashtable> listSenaraiLotTarikBalik_PULL = null;

	public List<Hashtable> listSenaraiLotTarikBalik_PULL(String id_fail, Db db,
			String id_penarikan) throws Exception {

		listSenaraiLotTarikBalik_PULL = new ArrayList();
		listSenaraiLotTarikBalik_PULL.clear();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			sql = " "
					+ " SELECT DISTINCT NO_PENARIKANBALIK,TARIKH_PENARIKANBALIK,JENIS_PENARIKANBALIK, "
					+ " JENIS_PB_KETERANGAN, "
					+ " SEBAB_PENARIKANBALIK,ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TRIM(TO_CHAR(NO_HAKMILIK,'00000000')) AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					+ " FROM (SELECT DISTINCT PB.NO_PENARIKANBALIK,TO_CHAR(PB.TARIKH_PENARIKAN_BALIK,'DD/MM/YYYY') AS TARIKH_PENARIKANBALIK,PB.JENIS_PENARIKANBALIK, "
					+ " (CASE WHEN PB.JENIS_PENARIKANBALIK = '1' THEN 'SEBAHAGIAN LOT' "
					+ " WHEN PB.JENIS_PENARIKANBALIK = '2' THEN 'KESELURUHAN LOT' "
					+ " ELSE '' END) AS JENIS_PB_KETERANGAN, "
					+ " PB.SEBAB_PENARIKANBALIK,C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,E.KOD_MUKIM AS KOD_MUKIM "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PBH "
					+ " WHERE  A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) ";

			sql += " AND PB.ID_PENARIKANBALIK = PBH.ID_PENARIKANBALIK AND PBH.ID_HAKMILIK = C.ID_HAKMILIK AND PB.ID_PERMOHONAN = B.ID_PERMOHONAN "
					+ " AND PB.ID_PENARIKANBALIK = '" + id_penarikan + "' ";

			sql += " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NOT NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";
			myLogger.info("LIST listSenaraiLotBorangC_PULL :"
					+ sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);

				h.put("NO_PENARIKANBALIK",
						rs.getString("NO_PENARIKANBALIK") == null ? "" : rs
								.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("TARIKH_PENARIKANBALIK", rs
						.getString("TARIKH_PENARIKANBALIK") == null ? "" : rs
						.getString("TARIKH_PENARIKANBALIK").toUpperCase());
				h.put("JENIS_PENARIKANBALIK", rs
						.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
						.getString("JENIS_PENARIKANBALIK").toUpperCase());
				h.put("JENIS_PB_KETERANGAN", rs
						.getString("JENIS_PB_KETERANGAN") == null ? "" : rs
						.getString("JENIS_PB_KETERANGAN").toUpperCase());
				h.put("JENIS_PENARIKANBALIK", rs
						.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
						.getString("JENIS_PENARIKANBALIK").toUpperCase());
				h.put("SEBAB_PENARIKANBALIK", rs
						.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
						.getString("SEBAB_PENARIKANBALIK").toUpperCase());

				h.put("ID_HAKMILIK_ETAPP",
						rs.getString("ID_HAKMILIK_ETAPP") == null ? "" : rs
								.getString("ID_HAKMILIK_ETAPP").toUpperCase());
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("UNIT_LUAS_ASAL",
						rs.getString("UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("UNIT_LUAS_ASAL").toUpperCase());
				h.put("UNIT_LUAS_AMBIL",
						rs.getString("UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("UNIT_LUAS_AMBIL").toUpperCase());
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				// h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
				// rs.getString("NO_WARTA").toUpperCase());
				// h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ?
				// "" : rs.getString("TARIKH_WARTA").toUpperCase());
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI").toUpperCase());
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH").toUpperCase());
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM").toUpperCase());
				listSenaraiLotTarikBalik_PULL.add(h);

				bil++;
			}

			return listSenaraiLotTarikBalik_PULL;

		} finally {

		}

	}

	List<Hashtable> listSenaraiLotBorangC_PULL = null;

	public List<Hashtable> listSenaraiLotBorangC_PULL(String id_fail, Db db)
			throws Exception {

		listSenaraiLotBorangC_PULL = new ArrayList();
		// Db db = null;

		listSenaraiLotBorangC_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			sql = " "
					+ " SELECT DISTINCT ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TRIM(TO_CHAR(NO_HAKMILIK,'00000000')) AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					//",NO_WARTA,TARIKH_WARTA "
					+ " FROM (SELECT DISTINCT C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,E.KOD_MUKIM AS KOD_MUKIM " 
					//" ,W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL " 
					//" , TBLPPTWARTA W "
					+ " WHERE " +
					//" B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) AND " +
					" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) ";
/*
			sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) ";
*/
			sql += " AND A.ID_FAIL = '"+ id_fail+ "' "	
			//sql +=  " AND ROWNUM < 500 "
					// " AND ROWNUM < 472 "+
					+" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";
			myLogger.info("LIST listSenaraiLotBorangC_PULL :"
					+ sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK_ETAPP",
						rs.getString("ID_HAKMILIK_ETAPP") == null ? "" : rs
								.getString("ID_HAKMILIK_ETAPP").toUpperCase());
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("UNIT_LUAS_ASAL",
						rs.getString("UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("UNIT_LUAS_ASAL").toUpperCase());
				h.put("UNIT_LUAS_AMBIL",
						rs.getString("UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("UNIT_LUAS_AMBIL").toUpperCase());
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI").toUpperCase());
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH").toUpperCase());
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM").toUpperCase());
				listSenaraiLotBorangC_PULL.add(h);

				bil++;
			}

			return listSenaraiLotBorangC_PULL;

		} finally {
			/*
			 * if (rs != null) rs.close(); if (stmt != null) stmt.close(); if
			 * (db != null) db.close();
			 */

		}

	}

	List<Hashtable> listSenaraiLotBorangI_PULL = null;

	public List<Hashtable> listSenaraiLotBorangI_PULL(String id_fail, Db db)
			throws Exception {

		listSenaraiLotBorangI_PULL = new ArrayList();
		// Db db = null;

		listSenaraiLotBorangI_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			sql = " "
					+ " SELECT DISTINCT TARIKH_BORANGI,FLAG_SEGERA_SEBAHAGIAN,FLAG_AMBIL_SEGERA,ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TRIM(TO_CHAR(NO_HAKMILIK,'00000000')) AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					//",NO_WARTA,TARIKH_WARTA "
					+ " FROM (SELECT DISTINCT TO_CHAR(BI.TARIKH_BORANGI,'DD/MM/YYYY') AS TARIKH_BORANGI,C.FLAG_SEGERA_SEBAHAGIAN,C.FLAG_AMBIL_SEGERA,C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,E.KOD_MUKIM AS KOD_MUKIM " 
					//",W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL, " +
					//" TBLPPTWARTA W," +
					" TBLPPTBORANGI BI "
					+ " WHERE " +
					//" B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) AND " +
					" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) ";
/*
			sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) ";
*/
			sql += " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) "
					+ " AND C.FLAG_AMBIL_SEGERA = '1' AND BI.ID_PERMOHONAN = B.ID_PERMOHONAN ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";
			myLogger.info("LIST listSenaraiLotBorangC_PULL :"
					+ sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;

			while (rs.next()) {
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK_ETAPP",
						rs.getString("ID_HAKMILIK_ETAPP") == null ? "" : rs
								.getString("ID_HAKMILIK_ETAPP").toUpperCase());
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("UNIT_LUAS_ASAL",
						rs.getString("UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("UNIT_LUAS_ASAL").toUpperCase());
				h.put("UNIT_LUAS_AMBIL",
						rs.getString("UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("UNIT_LUAS_AMBIL").toUpperCase());
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI").toUpperCase());
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH").toUpperCase());
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM").toUpperCase());
				h.put("TARIKH_BORANGI",
						rs.getString("TARIKH_BORANGI") == null ? "" : rs
								.getString("TARIKH_BORANGI").toUpperCase());
				listSenaraiLotBorangI_PULL.add(h);

				bil++;
			}

			return listSenaraiLotBorangI_PULL;

		} finally {

		}

	}

	List<Hashtable> listSenaraiLotHantarCharting_PULL = null;

	public List<Hashtable> listSenaraiLotHantarCharting_PULL(String id_fail,
			Db db) throws Exception {
		listSenaraiLotHantarCharting_PULL = new ArrayList();
		// Db db = null;
		listSenaraiLotHantarCharting_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			sql = " "
					+ " SELECT DISTINCT ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TRIM(TO_CHAR(NO_HAKMILIK,'00000000')) AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					+ " FROM (SELECT DISTINCT C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,E.KOD_MUKIM AS KOD_MUKIM "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";
			myLogger.info("LIST listSenaraiLotHantarCharting_PULL :"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK_ETAPP",
						rs.getString("ID_HAKMILIK_ETAPP") == null ? "" : rs
								.getString("ID_HAKMILIK_ETAPP").toUpperCase());
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("UNIT_LUAS_ASAL",
						rs.getString("UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("UNIT_LUAS_ASAL").toUpperCase());
				h.put("UNIT_LUAS_AMBIL",
						rs.getString("UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("UNIT_LUAS_AMBIL").toUpperCase());
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				// h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
				// rs.getString("NO_WARTA").toUpperCase());
				// h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ?
				// "" : rs.getString("TARIKH_WARTA").toUpperCase());
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI").toUpperCase());
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH").toUpperCase());
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM").toUpperCase());

				listSenaraiLotHantarCharting_PULL.add(h);
				bil++;
			}
			return listSenaraiLotHantarCharting_PULL;
		} finally {
			// if (db != null)
			// db.close();
		}

	}

	List<Hashtable> listSenaraiLotBorangK_PULL = null;

	public List<Hashtable> listSenaraiLotBorangK_PULL(String id_fail,
			String id_hakmilik, Db db) throws Exception {
		listSenaraiLotBorangK_PULL = new ArrayList();
		// Db db = null;
		listSenaraiLotBorangK_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			sql = " "
					+ " SELECT DISTINCT TARIKH_BORANGK, STATUS_AMBIL,ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TRIM(TO_CHAR(NO_HAKMILIK,'00000000')) AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					+ " FROM (SELECT DISTINCT TO_CHAR(BK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, "
					+ " (CASE WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) =  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'P' "
					+ " WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) <>  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'S' "
					+ " ELSE '' END) AS STATUS_AMBIL,C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL,(JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,E.KOD_MUKIM AS KOD_MUKIM "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' AND HBK.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK AND C.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";
			myLogger.info("LIST listSenaraiLotBorangK_PULL :"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK_ETAPP",
						rs.getString("ID_HAKMILIK_ETAPP") == null ? "" : rs
								.getString("ID_HAKMILIK_ETAPP").toUpperCase());
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("UNIT_LUAS_ASAL",
						rs.getString("UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("UNIT_LUAS_ASAL").toUpperCase());
				h.put("UNIT_LUAS_AMBIL",
						rs.getString("UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("UNIT_LUAS_AMBIL").toUpperCase());
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				// h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
				// rs.getString("NO_WARTA").toUpperCase());
				// h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ?
				// "" : rs.getString("TARIKH_WARTA").toUpperCase());
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI").toUpperCase());
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH").toUpperCase());
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM").toUpperCase());

				h.put("TARIKH_BORANGK",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK").toUpperCase());
				h.put("STATUS_AMBIL", rs.getString("STATUS_AMBIL") == null ? ""
						: rs.getString("STATUS_AMBIL").toUpperCase());

				listSenaraiLotBorangK_PULL.add(h);
				bil++;
			}
			return listSenaraiLotBorangK_PULL;
		} finally {
			// if (db != null)
			// db.close();
		}

	}

	List<Hashtable> listSenaraiLotPU_PULL = null;

	public List<Hashtable> listSenaraiLotPU_PULL(String id_fail,
			String id_hakmilik, Db db) throws Exception {
		listSenaraiLotPU_PULL = new ArrayList();
		// Db db = null;
		listSenaraiLotPU_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			sql = " "
					+ " SELECT DISTINCT ID_PERMINTAANUKUR,NO_SYIT,NO_PU,TARIKH_BORANG_PU, TARIKH_TERIMA_PA_B1_JUPEM,NO_PA,NO_LOT_BARU,LUAS_PA,ID_UNIT_LUAS_PA,"
					+ " TARIKH_BORANGK, STATUS_AMBIL,ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TRIM(TO_CHAR(NO_HAKMILIK,'00000000')) AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					+ " FROM (SELECT DISTINCT PU.ID_PERMINTAANUKUR,"
					+ " (SELECT  "
					+ " rtrim (xmlagg (xmlelement (e, PP.NO_PELAN || ', ')).extract ('//text()'), ', ') NO_SYIT "
					+ " FROM TBLPPTNOPELANPU PPU,TBLPPTNOPELAN PP "
					+ " WHERE PP.ID_NOPELAN = PPU.ID_NOPELAN  "
					+ " AND PPU.ID_PERMINTAANUKUR = PU.ID_PERMINTAANUKUR) AS NO_SYIT, "
					+ " PU.NO_PU,TO_CHAR(PU.TARIKH_PU,'DD/MM/YYYY') AS TARIKH_BORANG_PU, TO_CHAR(PU.TARIKH_TERIMA_PA,'DD/MM/YYYY') AS TARIKH_TERIMA_PA_B1_JUPEM, "
					+ " PU.NO_PA,PU.NO_LOT_BARU,TRIM(TO_CHAR(PU.LUAS_PU,'9999999999999990.9999')) AS LUAS_PA, '2' AS ID_UNIT_LUAS_PA, "
					+ "TO_CHAR(BK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, "
					+ " (CASE WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) =  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'P' "
					+ " WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) <>  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'S' "
					+ " ELSE '' END) AS STATUS_AMBIL,C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,E.KOD_MUKIM AS KOD_MUKIM "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK,"
					+ " TBLPPTPERMINTAANUKUR PU "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' AND HBK.ID_HAKMILIK = C.ID_HAKMILIK "
					+ " AND HBK.ID_BORANGK = BK.ID_BORANGK AND C.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' AND PU.ID_HAKMILIK = C.ID_HAKMILIK "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";
			myLogger.info("LIST listSenaraiLotBorangK_PULL :"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("ID_HAKMILIK_ETAPP",
						rs.getString("ID_HAKMILIK_ETAPP") == null ? "" : rs
								.getString("ID_HAKMILIK_ETAPP").toUpperCase());
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("UNIT_LUAS_ASAL",
						rs.getString("UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("UNIT_LUAS_ASAL").toUpperCase());
				h.put("UNIT_LUAS_AMBIL",
						rs.getString("UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("UNIT_LUAS_AMBIL").toUpperCase());
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL_JKPTG",
						rs.getString("NO_FAIL_JKPTG") == null ? "" : rs
								.getString("NO_FAIL_JKPTG").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				// h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
				// rs.getString("NO_WARTA").toUpperCase());
				// h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ?
				// "" : rs.getString("TARIKH_WARTA").toUpperCase());
				h.put("KOD_NEGERI", rs.getString("KOD_NEGERI") == null ? ""
						: rs.getString("KOD_NEGERI").toUpperCase());
				h.put("KOD_DAERAH", rs.getString("KOD_DAERAH") == null ? ""
						: rs.getString("KOD_DAERAH").toUpperCase());
				h.put("KOD_MUKIM", rs.getString("KOD_MUKIM") == null ? "" : rs
						.getString("KOD_MUKIM").toUpperCase());

				h.put("TARIKH_BORANGK",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK").toUpperCase());
				h.put("STATUS_AMBIL", rs.getString("STATUS_AMBIL") == null ? ""
						: rs.getString("STATUS_AMBIL").toUpperCase());

				h.put("ID_PERMINTAANUKUR",
						rs.getString("ID_PERMINTAANUKUR") == null ? "" : rs
								.getString("ID_PERMINTAANUKUR").toUpperCase());
				h.put("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs
						.getString("NO_SYIT").toUpperCase());
				h.put("NO_PU", rs.getString("NO_PU") == null ? "" : rs
						.getString("NO_PU").toUpperCase());
				h.put("TARIKH_BORANG_PU",
						rs.getString("TARIKH_BORANG_PU") == null ? "" : rs
								.getString("TARIKH_BORANG_PU").toUpperCase());
				h.put("TARIKH_TERIMA_PA_B1_JUPEM", rs
						.getString("TARIKH_TERIMA_PA_B1_JUPEM") == null ? ""
						: rs.getString("TARIKH_TERIMA_PA_B1_JUPEM")
								.toUpperCase());
				h.put("NO_PA", rs.getString("NO_PA") == null ? "" : rs
						.getString("NO_PA").toUpperCase());
				h.put("NO_LOT_BARU", rs.getString("NO_LOT_BARU") == null ? ""
						: rs.getString("NO_LOT_BARU").toUpperCase());
				h.put("LUAS_PA", rs.getString("LUAS_PA") == null ? "" : rs
						.getString("LUAS_PA").toUpperCase() + " HEKTAR");
				h.put("ID_UNIT_LUAS_PA",
						rs.getString("ID_UNIT_LUAS_PA") == null ? "" : rs
								.getString("ID_UNIT_LUAS_PA").toUpperCase());

				listSenaraiLotPU_PULL.add(h);
				bil++;
			}
			return listSenaraiLotPU_PULL;
		} finally {
			// if (db != null)
			// db.close();
		}

	}

	
	List<Hashtable> listSenaraiItemMMKTarikBalik_PULL = null;
	public List<Hashtable> listSenaraiItemMMKTarikBalik_PULL(String id_penarikanbalik,
			String FLAG_JENIS_MMK, Db db) throws Exception {
		listSenaraiItemMMKTarikBalik_PULL = new ArrayList();
		// Db db = null;
		listSenaraiItemMMKTarikBalik_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT SM.FLAG_JENIS_MMK,KETERANGAN_SUBMMK,SM.ID_SUBMMK,M.ID_PERMOHONAN FROM TBLPPTMMK M,TBLPPTSUBMMK SM,TBLPPTPERMOHONAN P,TBLPFDFAIL F, " +
					" TBLPPTPENARIKANBALIK TB "+
					" WHERE  SM.ID_MMK = M.ID_MMK AND P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = TB.ID_PERMOHONAN AND TB.ID_PENARIKANBALIK = M.ID_PENARIKANBALIK " +
					" AND M.ID_PENARIKANBALIK  = '" + id_penarikanbalik + "' ";
			if (!FLAG_JENIS_MMK.equals("")) {
				sql += " AND FLAG_JENIS_MMK = '" + FLAG_JENIS_MMK + "' ";
			}
			sql += " ORDER BY SM.ID_SUBMMK  ";
			myLogger.info("LIST listSenaraiItemMMKTarikBalik_PULL :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("FLAG_JENIS_MMK",
						rs.getString("FLAG_JENIS_MMK") == null ? "" : rs
								.getString("FLAG_JENIS_MMK").toUpperCase());
				h.put("KETERANGAN_SUBMMK",
						rs.getString("KETERANGAN_SUBMMK") == null ? "" : rs
								.getString("KETERANGAN_SUBMMK"));
				listSenaraiItemMMKTarikBalik_PULL.add(h);
				bil++;
			}
			return listSenaraiItemMMKTarikBalik_PULL;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	List<Hashtable> listSenaraiItemMMK_PULL = null;
	public List<Hashtable> listSenaraiItemMMK_PULL(String id_fail,
			String FLAG_JENIS_MMK, Db db,String flag_proses) throws Exception {
		
		
		Hashtable hash_maklumatprojek = null;
		hash_maklumatprojek = getMaklumatProjek(id_fail, db);
		String id_negeri = (hash_maklumatprojek.get("ID_NEGERI").toString() == null ? ""
				: hash_maklumatprojek.get("ID_NEGERI").toString());
		
		listSenaraiItemMMK_PULL = new ArrayList();
		// Db db = null;
		listSenaraiItemMMK_PULL.clear();
		String sql = "";
		try {
			// db = new Db();
			
			
			Statement stmt = db.getStatement();
			
			
			sql = " SELECT FLAG_JENIS_MMK,KETERANGAN_SUBMMK,ID_SUBMMK,ID_PERMOHONAN FROM "+
					" ( "+
					" SELECT   SM.FLAG_JENIS_MMK, TO_CHAR(KETERANGAN_SUBMMK) AS KETERANGAN_SUBMMK, TO_CHAR(SM.ID_SUBMMK) AS ID_SUBMMK, M.ID_PERMOHONAN "+
					" FROM TBLPPTMMK M, TBLPPTSUBMMK SM, TBLPPTPERMOHONAN P, TBLPFDFAIL F "+
					" WHERE SM.ID_MMK = M.ID_MMK "+
					" AND P.ID_FAIL = F.ID_FAIL "+
					" AND P.ID_PERMOHONAN = M.ID_PERMOHONAN "+
					" AND F.ID_FAIL = '" + id_fail + "' "+
					" UNION "+
					" SELECT   'NO_RUJUKAN_PTG' AS FLAG_JENIS_MMK, TO_CHAR(P.NO_RUJUKAN_PTG) AS KETERANGAN_SUBMMK, '' AS ID_SUBMMK, P.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F "+
					" WHERE P.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_FAIL = '" + id_fail + "' "+
					" UNION "+
					" SELECT   'NO_RUJUKAN_PTD' AS FLAG_JENIS_MMK, TO_CHAR(P.NO_RUJUKAN_PTD) AS KETERANGAN_SUBMMK, '' AS ID_SUBMMK, P.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F "+
					" WHERE P.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_FAIL = '" + id_fail + "'  "+
					" UNION "+
					" SELECT   'NO_RUJUKAN_UPT' AS FLAG_JENIS_MMK, TO_CHAR(P.NO_RUJUKAN_UPT) AS KETERANGAN_SUBMMK, '' AS ID_SUBMMK, P.ID_PERMOHONAN "+
					" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F "+
					" WHERE P.ID_FAIL = F.ID_FAIL "+
					" AND F.ID_FAIL = '" + id_fail + "'  "+
					" )  ";
			
			    sql += " WHERE ID_PERMOHONAN IS NOT NULL ";
			    
			    if (!FLAG_JENIS_MMK.equals("")) {
					sql += " AND FLAG_JENIS_MMK = '" + FLAG_JENIS_MMK + "' ";
				}
				else
				{
					if(id_negeri.equals("4") && flag_proses.equals("BorangC")) //melaka seksyen 8
					{
						sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','PERIHALPERMOHONAN','PERIHALTANAH','PERIHALPEMOHON','ANGGARANPAMPASAN','ULASANTEKNIKAL','PANDANGANYB','PANDANGANPT','PERAKUANPT','ULASANPENGARAH','NO_RUJUKAN_PTG','NO_RUJUKAN_PTD','NO_RUJUKAN_UPT') ";
					}
					else if(id_negeri.equals("4") && flag_proses.equals("BorangA")) //melaka seksyen 4
					{
						sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','PERIHALPERMOHONAN','PERIHALTANAH','PERIHALPEMOHON','ANGGARANPAMPASAN','LAPORANTANAH','LAPORANTEKNIKAL','PANDANGANYB','PERAKUANPT','NO_RUJUKAN_PTG','NO_RUJUKAN_PTD','NO_RUJUKAN_UPT') ";
					}
					else if(id_negeri.equals("5") && flag_proses.equals("BorangC")) //n9 seksyen 8
					{
						sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','LATARBELAKANG','PERIHALTANAH','NILAITANAH','SYOR','ULASANPENGARAH','KEPUTUSAN','NO_RUJUKAN_PTG','NO_RUJUKAN_PTD','NO_RUJUKAN_UPT') ";
					}
					else if(id_negeri.equals("5") && flag_proses.equals("BorangA")) //n9 seksyen 4
					{
						sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','LATARBELAKANG','PERIHALTANAH','NILAITANAH','SYOR','ULASANPENGARAH','KEPUTUSAN','NO_RUJUKAN_PTG','NO_RUJUKAN_PTD','NO_RUJUKAN_UPT') ";
					}				
					
				}
			
				sql += " ORDER BY ID_SUBMMK ";
			
			
			/*
			sql = " SELECT SM.FLAG_JENIS_MMK,KETERANGAN_SUBMMK,SM.ID_SUBMMK,M.ID_PERMOHONAN FROM TBLPPTMMK M,TBLPPTSUBMMK SM,TBLPPTPERMOHONAN P,TBLPFDFAIL F "
					+ " WHERE  SM.ID_MMK = M.ID_MMK AND P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = M.ID_PERMOHONAN "
					+ " AND F.ID_FAIL = '" + id_fail + "' ";
			if (!FLAG_JENIS_MMK.equals("")) {
				sql += " AND FLAG_JENIS_MMK = '" + FLAG_JENIS_MMK + "' ";
			}
			else
			{
				if(id_negeri.equals("4") && flag_proses.equals("BorangC")) //melaka seksyen 8
				{
					sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','PERIHALPERMOHONAN','PERIHALTANAH','PERIHALPEMOHON','ANGGARANPAMPASAN','ULASANTEKNIKAL','PANDANGANYB','PANDANGANPT','PERAKUANPT','ULASANPENGARAH') ";
				}
				else if(id_negeri.equals("4") && flag_proses.equals("BorangA")) //melaka seksyen 4
				{
					sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','PERIHALPERMOHONAN','PERIHALTANAH','PERIHALPEMOHON','ANGGARANPAMPASAN','LAPORANTANAH','LAPORANTEKNIKAL','PANDANGANYB','PERAKUANPT') ";
				}
				else if(id_negeri.equals("5") && flag_proses.equals("BorangC")) //n9 seksyen 8
				{
					sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','LATARBELAKANG','PERIHALTANAH','NILAITANAH','SYOR','ULASANPENGARAH','KEPUTUSAN') ";
				}
				else if(id_negeri.equals("5") && flag_proses.equals("BorangA")) //n9 seksyen 4
				{
					sql += " AND FLAG_JENIS_MMK IN ('TUJUAN','LATARBELAKANG','PERIHALTANAH','NILAITANAH','SYOR','ULASANPENGARAH','KEPUTUSAN') ";
				}				
				
			}
			sql += " ORDER BY M.ID_SUBMMK  ";
			*/
			
			
			myLogger.info("LIST listSenaraiItemMMK_PULL :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("BIL", bil);
				h.put("FLAG_JENIS_MMK",
						rs.getString("FLAG_JENIS_MMK") == null ? "" : rs
								.getString("FLAG_JENIS_MMK").toUpperCase());
				h.put("KETERANGAN_SUBMMK",
						rs.getString("KETERANGAN_SUBMMK") == null ? "" : rs
								.getString("KETERANGAN_SUBMMK"));
				listSenaraiItemMMK_PULL.add(h);
				bil++;
			}
			return listSenaraiItemMMK_PULL;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Integer hakmilik_COUNT(String id_penarikan, String id_fail,
			String id_hakmilik, String jenis_skrin, Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("MMK_S8")
					|| jenis_skrin.equals("MMK_S4")) {
				sql = " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_HAKMILIK FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, "
						+ " TBLPPTHAKMILIK C,TBLRUJNEGERI D,TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH, "
						+ " TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL "
						+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
						+ " AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH "
						+ " AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
						+ " AND A.ID_FAIL = '"
						+ id_fail
						+ "' "
						+
						// " AND ROWNUM < 472 "+
						" AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL  AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) "
						+ " AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL ";
			} else if (jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangA") || jenis_skrin.equals("WartaS8")) {
				sql = " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_HAKMILIK FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, "
						+ " TBLPPTHAKMILIK C,TBLRUJNEGERI D,TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH, "
						+ " TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL" 
						//" , TBLPPTWARTA W "
						+ " WHERE " +
						//" B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) AND " +
						" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
						+ " AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH "
						+ " AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
						+ " AND A.ID_FAIL = '" + id_fail + "' ";
/*
				sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
						+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
						+ id_fail
						+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) ";
						*/

				// " AND ROWNUM < 472 "+
				sql += " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL  AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) "
					//	+ " AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL "
						+ " AND A.NO_FAIL IS NOT NULL ";
			}

			else if (jenis_skrin.equals("BorangI")) {

				sql = " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_HAKMILIK FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, "
						+ " TBLPPTHAKMILIK C,TBLRUJNEGERI D,TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH, "
						+ " TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL, " 
						//" TBLPPTWARTA W, " +
						+" TBLPPTBORANGI BI "
						+ " WHERE " +
						//" B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) AND " +
						" A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
						+ " AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH "
						+ " AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
						+ " AND A.ID_FAIL = '" + id_fail + "' ";
/*
				sql += " AND (((NVL(W.NO_WARTA,'0') != '0') AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
						+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
						+ id_fail
						+ "' AND ROWNUM < 2)) OR (NVL (w.no_warta, '0') = '0')) ";
						*/

				// " AND ROWNUM < 472 "+
				sql += " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL  AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) AND C.FLAG_AMBIL_SEGERA = '1' AND BI.ID_PERMOHONAN = B.ID_PERMOHONAN "
						+ " AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL ";
			}

			else if (jenis_skrin.equals("TarikBalik")) {
				sql += " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_HAKMILIK "
						+ "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
						+ " TBLRUJLUAS JL_AMBIL,TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PBH "
						+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
						+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  ";
				sql += " AND PB.ID_PENARIKANBALIK = PBH.ID_PENARIKANBALIK AND PBH.ID_HAKMILIK = C.ID_HAKMILIK AND PB.ID_PERMOHONAN = B.ID_PERMOHONAN AND PB.ID_PENARIKANBALIK = '"
						+ id_penarikan + "' ";
				sql += "  "
						+
						// " AND A.ID_FAIL = '"+id_fail+"' " +
						// " AND ROWNUM < 472 "+
						" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
						+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NOT NULL)  ";
				sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			} else if (jenis_skrin.equals("BorangK") || jenis_skrin.equals("SijilUkur")) {

				sql += " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_HAKMILIK "
						+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
						+ " TBLRUJLUAS JL_AMBIL," 
						//"  TBLPPTWARTA W ,"
						+ " TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK "
						+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM  AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
						+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+)  AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+) " 
						//"  AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) "
						+ " AND C.ID_HAKMILIK = '"
						+ id_hakmilik
						+ "' "
					/*	+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
						+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
						+ id_fail
						+ "' AND ROWNUM < 2)   "*/
						+ " AND A.ID_FAIL = '"
						+ id_fail
						+ "' AND HBK.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK  "
						+ "";

			} else if (jenis_skrin.equals("PU") ) {

				sql += " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_HAKMILIK "
						+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
						+ " TBLRUJLUAS JL_AMBIL,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK,TBLPPTPERMINTAANUKUR PU  "
						+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM  "
						+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
						+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+)  AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  "
						+ " AND HBK.ID_BORANGK = BK.ID_BORANGK AND C.ID_HAKMILIK = '"
						+ id_hakmilik
						+ "' AND PU.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_HAKMILIK = C.ID_HAKMILIK "
						+ " " + " AND A.ID_FAIL = '" + id_fail + "'   " + "";

			}

			myLogger.info("##################### hakmilik count:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {

				/*
				 * if (rs.getString("COUNT_HAKMILIK") == null) {
				 * h.put("COUNT_HAKMILIK", ""); } else { h.put("COUNT_HAKMILIK",
				 * rs.getString("COUNT_HAKMILIK").toUpperCase()); }
				 */

				h = rs.getInt("COUNT_HAKMILIK");
			}
			return h;
		} finally {
			// (db != null)
			// db.close();
		}
	}

	public static Integer hakmilikLog_COUNT(String id_fail, String id_hakmilik,
			String jenis_skrin, Db db, String id_penarikan,Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangI")
					|| jenis_skrin.equals("BorangA")
					|| jenis_skrin.equals("WartaS8")
					|| jenis_skrin.equals("MMK_S4")) {
				sql = " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_LOG_HAKMILIK FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, "
						+ " TBLPPTHAKMILIK C,TBLRUJNEGERI D,TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH, "
						+ " TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL,TBLLOGINTHAKMILIK LH "
						+ " WHERE A.ID_FAIL = LH.ID_FAIL AND C.ID_HAKMILIK = LH.ID_HAKMILIK  AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
						+ " AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH "
						+ " AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
						+ " AND A.ID_FAIL = '"
						+ id_fail
						+ "' AND LH.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' "
						+ " AND LH.TURUTAN = '"
						+ turutan
						+ "' "
						+ "  "
						+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL  AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) "
						+ " AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
						+ " AND LH.ID_PENARIKANBALIK IS NULL ";
			} /*else if (jenis_skrin.equals("TarikBalik")) {
				sql = " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_LOG_HAKMILIK FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, "
						+ " TBLPPTHAKMILIK C,TBLRUJNEGERI D,TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH, "
						+ " TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL,TBLLOGINTHAKMILIK LH "
						+ " WHERE A.ID_FAIL = LH.ID_FAIL AND C.ID_HAKMILIK = LH.ID_HAKMILIK  AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
						+ " AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH "
						+ " AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL = JL_AMBIL.ID_LUAS(+) "
						+ " AND A.ID_FAIL = '"
						+ id_fail
						+ "' AND LH.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' "
						+ "  "
						+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NOT NULL) "
						+ " AND (TRANSLATE(C.NO_HAKMILIK,'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
						+ " AND LH.ID_PENARIKANBALIK = '" + id_penarikan + "' ";
			} */else if (jenis_skrin.equals("BorangK")
					|| jenis_skrin.equals("PU") || jenis_skrin.equals("SijilUkur")) {
				sql = " SELECT DISTINCT COUNT(C.ID_HAKMILIK) AS COUNT_LOG_HAKMILIK FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B, "
						+ " TBLPPTHAKMILIK C,TBLRUJNEGERI D,TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH, "
						+ " TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL,TBLLOGINTHAKMILIK LH "
						+ " WHERE A.ID_FAIL = LH.ID_FAIL AND C.ID_HAKMILIK = LH.ID_HAKMILIK  AND A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN "
						+ " AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH "
						+ " AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
						+ " AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) "
						+ " AND A.ID_FAIL = '"
						+ id_fail
						+ "' AND LH.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' AND C.ID_HAKMILIK = '"
						+ id_hakmilik
						+ "' "
						+ " AND LH.TURUTAN = '"
						+ turutan
						+ "' "
						+ "  "
						+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL  AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) "
						+ " AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
						+ " AND LH.ID_PENARIKANBALIK IS NULL ";
			}
			myLogger.info("##################### hakmilik COUNT_LOG_HAKMILIK:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK") == null) {
				 * h.put("COUNT_LOG_HAKMILIK", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK",
				 * rs.getString("COUNT_LOG_HAKMILIK").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_LOG_HAKMILIK");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Integer dokumenLog_COUNT(String id_fail, String id_hakmilik,
			String jenis_skrin, Db db, String id_penarikan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangI")
					|| jenis_skrin.equals("BorangA")
					|| jenis_skrin.equals("MMK_S8")
					|| jenis_skrin.equals("MMK_S4")
					|| jenis_skrin.equals("WartaS4")
					|| jenis_skrin.equals("WartaS8")) {
				sql = " SELECT COUNT(PL.ID_DOKUMEN) AS COUNT_LOG_DOKUMEN  FROM TBLPPTDOKUMENINT PD,TBLLOGINTDOKUMEN PL,TBLPPTPERMOHONAN P,TBLPFDFAIL F "
						+ " WHERE PD.ID_DOKUMENINT = PL.ID_DOKUMEN AND P.ID_PERMOHONAN = PD.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL "
						+ " AND F.ID_FAIL = '"
						+ id_fail
						+ "'  AND PL.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' AND PL.ID_PENARIKANBALIK IS NULL   ";
			} else if (jenis_skrin.equals("TarikBalik")) {
				sql = " SELECT COUNT(PL.ID_DOKUMEN) AS COUNT_LOG_DOKUMEN  FROM TBLPPTDOKUMENINT PD,TBLLOGINTDOKUMEN PL,TBLPPTPERMOHONAN P,TBLPFDFAIL F "
						+ " WHERE PD.ID_DOKUMENINT = PL.ID_DOKUMEN AND P.ID_PERMOHONAN = PD.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL "
						+ " AND F.ID_FAIL = '"
						+ id_fail
						+ "'  AND PL.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' AND PL.ID_PENARIKANBALIK = '"
						+ id_penarikan
						+ "'   ";
			} else if (jenis_skrin.equals("BorangK")
					|| jenis_skrin.equals("PU") || jenis_skrin.equals("SijilUkur")) {
				sql = " SELECT COUNT(PL.ID_DOKUMEN) AS COUNT_LOG_DOKUMEN  FROM TBLPPTDOKUMENINT PD,TBLLOGINTDOKUMEN PL,TBLPPTPERMOHONAN P,TBLPFDFAIL F "
						+ " WHERE PD.ID_DOKUMENINT = PL.ID_DOKUMEN AND P.ID_PERMOHONAN = PD.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL "
						+ " AND F.ID_FAIL = '"
						+ id_fail
						+ "'  AND PL.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' AND PD.ID_HAKMILIK = '"
						+ id_hakmilik
						+ "' AND PL.ID_PENARIKANBALIK IS NULL   ";
			}
			myLogger.info("##################### dokumenLog_COUNT COUNT_LOG_DOKUMEN:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK") == null) {
				 * h.put("COUNT_LOG_HAKMILIK", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK",
				 * rs.getString("COUNT_LOG_HAKMILIK").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_LOG_DOKUMEN");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	public static Integer derafMmkPBLog_COUNT(String id_penarikan, String jenis_skrin,
			Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT COUNT(DM.ID_PENARIKANBALIK) AS COUNT_LOG_MMKPB FROM TBLLOGINTDERAFMMKPB DM "
					+ " WHERE  DM.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "'  AND DM.JENIS_SKRIN = '" + jenis_skrin + "'  ";

			myLogger.info("##################### COUNT_LOG_MMKPB DERAF MMK:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK") == null) {
				 * h.put("COUNT_LOG_HAKMILIK", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK",
				 * rs.getString("COUNT_LOG_HAKMILIK").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_LOG_MMKPB");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	
	
	

	public static Integer derafMmkLog_COUNT(String id_fail, String jenis_skrin,
			Db db,Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT COUNT(DM.ID_FAIL) AS COUNT_LOG_MMK FROM TBLLOGINTDERAFMMK DM "
					+ " WHERE  DM.ID_FAIL = '"
					+ id_fail
					+ "'  AND DM.JENIS_SKRIN = '" + jenis_skrin + "'  AND TURUTAN = '"+turutan+"'  ";

			myLogger.info("##################### dokumenLog_COUNT DERAF MMK:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK") == null) {
				 * h.put("COUNT_LOG_HAKMILIK", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK",
				 * rs.getString("COUNT_LOG_HAKMILIK").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_LOG_MMK");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	public static Integer derafMaklumatWartaLog_COUNT(String id_fail, String jenis_skrin,
			Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT COUNT(DM.ID_FAIL) AS COUNT_LOG_WARTA FROM TBLLOGINTMAKLUMATWARTA DM "
					+ " WHERE  DM.ID_FAIL = '"
					+ id_fail
					+ "'  AND DM.JENIS_SKRIN = '" + jenis_skrin + "'  ";

			myLogger.info("##################### COUNT_LOG_WARTA :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK") == null) {
				 * h.put("COUNT_LOG_HAKMILIK", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK",
				 * rs.getString("COUNT_LOG_HAKMILIK").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_LOG_WARTA");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	public static Integer derafMaklumatPengambilanLog_COUNT(String id_fail, String jenis_skrin,
			Db db,Integer turutan) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT COUNT(DM.ID_FAIL) AS COUNT_LOG_MAKLUMATPPT FROM TBLLOGINTMAKLUMATPPT DM "
					+ " WHERE  DM.ID_FAIL = '"+ id_fail+ "'  AND TURUTAN = '"+turutan+"'  " 
				//	+" AND DM.JENIS_SKRIN = '" + jenis_skrin + "'  " 
						+	" ";

			myLogger.info("##################### COUNT_LOG_MAKLUMATPPT :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK") == null) {
				 * h.put("COUNT_LOG_HAKMILIK", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK",
				 * rs.getString("COUNT_LOG_HAKMILIK").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_LOG_MAKLUMATPPT");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Integer hakmilikLog_TelahDihantar(String id_fail,
			String id_hakmilik, String jenis_skrin, Db db, String id_penarikan,Integer new_turutan)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangA")
					|| jenis_skrin.equals("WartaS8")
					|| jenis_skrin.equals("MMK_S4") || jenis_skrin.equals("PU")
					|| jenis_skrin.equals("BorangK") || jenis_skrin.equals("SijilUkur")
					|| jenis_skrin.equals("BorangI")) {
				sql = " SELECT DISTINCT COUNT(*) AS COUNT_LOG_HAKMILIK_DIHANTAR FROM TBLLOGINTHAKMILIK LH "
						+ " WHERE  LH.ID_FAIL = '"
						+ id_fail
						+ "' AND LH.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' " 
						+ " AND LH.TURUTAN = '"
						+ new_turutan
						+ "' "
						+ "  "
						+" AND LH.ID_HAKMILIK= '"
						+ id_hakmilik
						+ "' AND LH.ID_PENARIKANBALIK IS NULL " + "  ";
			} /*else if (jenis_skrin.equals("TarikBalik")) {
				sql = " SELECT DISTINCT COUNT(*) AS COUNT_LOG_HAKMILIK_DIHANTAR FROM TBLLOGINTHAKMILIK LH "
						+ " WHERE  LH.ID_FAIL = '"
						+ id_fail
						+ "' AND LH.JENIS_SKRIN = '"
						+ jenis_skrin
						+ "' AND LH.ID_HAKMILIK= '"
						+ id_hakmilik
						+ "' AND LH.ID_PENARIKANBALIK = "
						+ id_penarikan
						+ " "
						+ "  ";
			}*/
			myLogger.info("##################### hakmilik COUNT_HAKMILIK_DIHANTAR:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);
			Integer h = 0;
			// Hashtable h;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_LOG_HAKMILIK_DIHANTAR") == null) {
				 * h.put("COUNT_LOG_HAKMILIK_DIHANTAR", ""); } else {
				 * h.put("COUNT_LOG_HAKMILIK_DIHANTAR",
				 * rs.getString("COUNT_LOG_HAKMILIK_DIHANTAR").toUpperCase()); }
				 */

				h = rs.getInt("COUNT_LOG_HAKMILIK_DIHANTAR");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public List<Hashtable> listSenaraiLotHantarCharting(String id_fail, Db db)
			throws Exception {
		// Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;
		List listSenaraiLotHantarCharting = null;
		String sql = "";
		Integer count = 0;
		try {
			// db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();
			sql = " SELECT DISTINCT NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL FROM( ";
			sql += " SELECT DISTINCT C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					+" TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL "
					+ "  FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+)  ";
			sql += "  "
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+
					// " AND ROWNUM < 472 "+
					" AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL)  ";
			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			sql += " )";
			// dapat flag
			myLogger.info("listSenaraiLotHantarCharting :" + sql.toUpperCase());
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);
			int bil = 1;
			listSenaraiLotHantarCharting = Collections
					.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("BIL", bil);
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				// h.put("NO_WARTA",rs.getString("NO_WARTA") == null ? "" :
				// rs.getString("NO_WARTA").toUpperCase());
				// h.put("TARIKH_WARTA",rs.getString("TARIKH_WARTA") == null ?
				// "" : rs.getString("TARIKH_WARTA").toUpperCase());
				listSenaraiLotHantarCharting.add(h);
				bil++;
				count++;
			}

		} finally {
			/*
			 * if (rs != null) rs.close(); if (stmt != null) stmt.close(); if
			 * (db != null) db.close();
			 */
		}

		return listSenaraiLotHantarCharting;
	}

	public static Hashtable getMaklumatProjek(String id_fail, Db db)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT P.TUJUAN_BI AS TUJUAN_DALAM_ENGLISH, TUJUAN,NO_FAIL AS NO_FAIL_JKPTG,N.KOD_NEGERI AS KOD_NEGERI_PENGAMBILAN,N.NAMA_NEGERI AS NAMA_NEGERI_PENGAMBILAN, "+
					" D.KOD_DAERAH AS KOD_DAERAH_PENGAMBILAN,D.NAMA_DAERAH AS NAMA_DAERAH_PENGAMBILAN,U.NAMA_SUBURUSAN AS JENIS_PENGAMBILAN,(CASE WHEN  P.FLAG_JENISPERMOHONAN = '1' THEN 'JAJARAN' "+
					" WHEN  P.FLAG_JENISPERMOHONAN = '2' THEN 'TAPAK' ELSE '' END) AS JENIS_PROJEK_PENGAMBILAN, P.NO_RUJUKAN_SURAT AS NO_RUJUKAN_SURAT_KJP,TO_CHAR(P.TARIKH_SURAT,'DD/MM/YYYY') AS TARIKH_SURAT_KJP, "+
					" P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_PTD, P.NO_RUJUKAN_UPT,K.NAMA_KEMENTERIAN, K.ID_KEMENTERIAN AS ID_KEMENTERIAN_MYETAPP, A.NAMA_AGENSI, A.ID_AGENSI AS ID_AGENSI_MYETAPP, "+
					" TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN,P.FLAG_SEGERA AS FLAG_PERMOHONAN_SEGERA,  "+
					" (CASE WHEN P.FLAG_SEGERA = '1' THEN 'PENGAMBILAN SEGERA KESELURUHAN LOT' WHEN P.FLAG_SEGERA = '2' THEN '' WHEN P.FLAG_SEGERA = '3' THEN 'PENGAMBILAN SEGERA SEBAHAGIAN LOT' ELSE '' END) AS JENIS_PENGMABILAN_SEGERA, "+
					" F.NO_FAIL,P.TUJUAN AS NAMA_PROJEK,N.ID_NEGERI,N.NAMA_NEGERI,F.ID_FAIL,A.KOD_AGENSI, K.KOD_KEMENTERIAN," +
					" K.ALAMAT1 AS ALAMAT1_KJP,K.ALAMAT2 AS ALAMAT2_KJP,K.ALAMAT3 AS ALAMAT3_KJP,K.POSKOD AS POSKOD_KJP,N_KJP.KOD_NEGERI AS KOD_NEGERI_KJP  "+
					" FROM  TBLPFDFAIL F, TBLRUJDAERAH D,TBLRUJKEMENTERIAN K, TBLRUJSTATUS S,  TBLPPTPERMOHONAN P, TBLRUJNEGERI N, TBLRUJNEGERI N_KJP, TBLRUJAGENSI A,TBLRUJSUBURUSAN U "+
					" WHERE F.ID_FAIL(+) = P.ID_FAIL  AND K.ID_KEMENTERIAN(+) = F.ID_KEMENTERIAN  AND P.ID_AGENSI = A.ID_AGENSI(+) AND K.ID_NEGERI = N_KJP.ID_NEGERI(+)  "+
					" AND N.ID_NEGERI(+) = F.ID_NEGERI  AND S.ID_STATUS(+) = P.ID_STATUS  AND D.ID_DAERAH(+) = P.ID_DAERAH  AND F.ID_SUBURUSAN = U.ID_SUBURUSAN(+) "+
					" AND F.ID_FAIL = '" + id_fail + "' ";
			

			myLogger.info("##################### PAPAR MAKLUMAT PROJEK:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_FAIL", rs.getString("ID_FAIL"));
				if (rs.getString("NO_FAIL") == null) {h.put("NO_FAIL", "");} else {	h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase());}
				if (rs.getString("TARIKH_PERMOHONAN") == null) {h.put("TARIKH_PERMOHONAN", "");} else {h.put("TARIKH_PERMOHONAN", rs.getString("TARIKH_PERMOHONAN").toUpperCase());}
				if (rs.getString("NAMA_PROJEK") == null) {h.put("NAMA_PROJEK", "");} else {h.put("NAMA_PROJEK", rs.getString("NAMA_PROJEK").toUpperCase());}
				if (rs.getString("NAMA_KEMENTERIAN") == null) {h.put("NAMA_KEMENTERIAN", "");} else {h.put("NAMA_KEMENTERIAN", rs.getString("NAMA_KEMENTERIAN").toUpperCase());}
				if (rs.getString("ID_NEGERI") == null) {h.put("ID_NEGERI", "");} else {h.put("ID_NEGERI", rs.getString("ID_NEGERI").toUpperCase());}
				if (rs.getString("NAMA_NEGERI") == null) {h.put("NAMA_NEGERI", "");} else {h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI").toUpperCase());}
				
				if (rs.getString("TUJUAN_DALAM_ENGLISH") == null) {h.put("TUJUAN_DALAM_ENGLISH", "");} else {h.put("TUJUAN_DALAM_ENGLISH", rs.getString("TUJUAN_DALAM_ENGLISH"));}
				if (rs.getString("TUJUAN") == null) {h.put("TUJUAN", "");} else {h.put("TUJUAN", rs.getString("TUJUAN"));}
				if (rs.getString("NO_FAIL_JKPTG") == null) {h.put("NO_FAIL_JKPTG", "");} else {h.put("NO_FAIL_JKPTG", rs.getString("NO_FAIL_JKPTG"));}
				if (rs.getString("KOD_NEGERI_PENGAMBILAN") == null) {h.put("KOD_NEGERI_PENGAMBILAN", "");} else {h.put("KOD_NEGERI_PENGAMBILAN", rs.getString("KOD_NEGERI_PENGAMBILAN"));}
				if (rs.getString("NAMA_NEGERI_PENGAMBILAN") == null) {h.put("NAMA_NEGERI_PENGAMBILAN", "");} else {h.put("NAMA_NEGERI_PENGAMBILAN", rs.getString("NAMA_NEGERI_PENGAMBILAN"));}
				if (rs.getString("KOD_DAERAH_PENGAMBILAN") == null) {h.put("KOD_DAERAH_PENGAMBILAN", "");} else {h.put("KOD_DAERAH_PENGAMBILAN", rs.getString("KOD_DAERAH_PENGAMBILAN"));}
				if (rs.getString("NAMA_DAERAH_PENGAMBILAN") == null) {h.put("NAMA_DAERAH_PENGAMBILAN", "");} else {h.put("NAMA_DAERAH_PENGAMBILAN", rs.getString("NAMA_DAERAH_PENGAMBILAN"));}
				if (rs.getString("JENIS_PENGAMBILAN") == null) {h.put("JENIS_PENGAMBILAN", "");} else {h.put("JENIS_PENGAMBILAN", rs.getString("JENIS_PENGAMBILAN"));}
				if (rs.getString("JENIS_PROJEK_PENGAMBILAN") == null) {h.put("JENIS_PROJEK_PENGAMBILAN", "");} else {h.put("JENIS_PROJEK_PENGAMBILAN", rs.getString("JENIS_PROJEK_PENGAMBILAN"));}
				if (rs.getString("NO_RUJUKAN_SURAT_KJP") == null) {h.put("NO_RUJUKAN_SURAT_KJP", "");} else {h.put("NO_RUJUKAN_SURAT_KJP", rs.getString("NO_RUJUKAN_SURAT_KJP"));}
				if (rs.getString("TARIKH_SURAT_KJP") == null) {h.put("TARIKH_SURAT_KJP", "");} else {h.put("TARIKH_SURAT_KJP", rs.getString("TARIKH_SURAT_KJP"));}
				if (rs.getString("NO_RUJUKAN_PTG") == null) {h.put("NO_RUJUKAN_PTG", "");} else {h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG"));}
				if (rs.getString("NO_RUJUKAN_PTD") == null) {h.put("NO_RUJUKAN_PTD", "");} else {h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD"));}
				if (rs.getString("NO_RUJUKAN_UPT") == null) {h.put("NO_RUJUKAN_UPT", "");} else {h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT"));}
				
				if (rs.getString("ID_KEMENTERIAN_MYETAPP") == null) {h.put("ID_KEMENTERIAN_MYETAPP", "");} else {h.put("ID_KEMENTERIAN_MYETAPP", rs.getString("ID_KEMENTERIAN_MYETAPP"));}
				if (rs.getString("NAMA_AGENSI") == null) {h.put("NAMA_AGENSI", "");} else {h.put("NAMA_AGENSI", rs.getString("NAMA_AGENSI"));}
				if (rs.getString("ID_AGENSI_MYETAPP") == null) {h.put("ID_AGENSI_MYETAPP", "");} else {h.put("ID_AGENSI_MYETAPP", rs.getString("ID_AGENSI_MYETAPP"));}
				if (rs.getString("JENIS_PENGMABILAN_SEGERA") == null) {h.put("JENIS_PENGMABILAN_SEGERA", "");} else {h.put("JENIS_PENGMABILAN_SEGERA", rs.getString("JENIS_PENGMABILAN_SEGERA"));}
				if (rs.getString("FLAG_PERMOHONAN_SEGERA") == null) {h.put("FLAG_PERMOHONAN_SEGERA", "");} else {h.put("FLAG_PERMOHONAN_SEGERA", rs.getString("FLAG_PERMOHONAN_SEGERA"));}
				
				if (rs.getString("KOD_KEMENTERIAN") == null) {h.put("KOD_KEMENTERIAN", "");} else {h.put("KOD_KEMENTERIAN", rs.getString("KOD_KEMENTERIAN"));}
				if (rs.getString("KOD_AGENSI") == null) {h.put("KOD_AGENSI", "");} else {h.put("KOD_AGENSI", rs.getString("KOD_AGENSI"));}
				
				if (rs.getString("ALAMAT1_KJP") == null) {h.put("ALAMAT1_KJP", "");} else {h.put("ALAMAT1_KJP", rs.getString("ALAMAT1_KJP"));}
				if (rs.getString("ALAMAT2_KJP") == null) {h.put("ALAMAT2_KJP", "");} else {h.put("ALAMAT2_KJP", rs.getString("ALAMAT2_KJP"));}
				if (rs.getString("ALAMAT3_KJP") == null) {h.put("ALAMAT3_KJP", "");} else {h.put("ALAMAT3_KJP", rs.getString("ALAMAT3_KJP"));}
				if (rs.getString("POSKOD_KJP") == null) {h.put("POSKOD_KJP", "");} else {h.put("POSKOD_KJP", rs.getString("POSKOD_KJP"));}
				if (rs.getString("KOD_NEGERI_KJP") == null) {h.put("KOD_NEGERI_KJP", "");} else {h.put("KOD_NEGERI_KJP", rs.getString("KOD_NEGERI_KJP"));}
				
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	
	
	public static String getMaklumatKategori(String id_kategori, Db db)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " SELECT ID_LAMPIRANETANAH,KETERANGAN_LAMPIRANETANAH FROM TBLPPTKATEGORILAMPIRANETANAH  where id_lampiranetanah = '"+id_kategori+"' ";
			

			myLogger.info("##################### PAPAR MAKLUMAT PROJEK:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			String tajuk_dokumen = "";
			while (rs.next()) 
			{
				
				if (rs.getString("KETERANGAN_LAMPIRANETANAH") == null) 
				{
					tajuk_dokumen = "";
				} 
				else {
					tajuk_dokumen = rs.getString("KETERANGAN_LAMPIRANETANAH").toUpperCase();
				}
			}
			return tajuk_dokumen;
		} finally {
			// if (db != null)
			// db.close();
		}
	}
	
	
	
	public static Hashtable getMaklumatMMK(String id_penarikan,String id_fail,String jenis_skrin,Db db)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			if(jenis_skrin.equals("BorangA") || jenis_skrin.equals("BorangC"))
			{
			sql += " SELECT '' AS NO_PENARIKANBALIK,F.NO_FAIL,T.ID_MMK, T.ID_PENARIKANBALIK, T.JENIS_MMK, T.ULASAN, T.NO_RUJMMK, T.FLAG_SEMAK, "+
					" T.ID_SEMAK, T.TARIKH_SEMAK, T.FLAG_BORANGI, T.ID_PERMOHONAN, T.TUJUAN, T.LATARBELAKANG,  "+
					" T.ASAS_PERTIMBANGAN, T.KESIMPULAN, T.SYOR, T.KEDUDUKAN_LAPORAN_TNH, T.PENGESAHAN_PERUNTUKAN, T.PANDANGAN,  "+
					" T.IMPLIKASI, T.ULASAN_JABTEKNIKAL, T.PERIHAL_TANAH, T.PERIHAL_POHON, T.ANGGARAN_KOS, T.PERAKUAN_PENTADBIR_TNH,  "+
					" T.NILAI_ATAS_TANAH, T.PENGECUALIAN_UPAH_UKUR, T.TARIKH_MMK, T.STATUS_SEMAKAN, T.USER_ID,T.ID_DB, T.ULASAN_PENGARAH, T.KEPUTUSAN,  "+
					" T.PERAKUAN_SETIAUSAHA, T.TARIKH_HANTAR, T.HAL_LAIN, T.JAWATANKUASA_TANAH, T.NAMA_TUAN_TANAH, T.SEBAB_PENARIKAN,  "+
					" T.TAJUK, T.FLAG_MMK, T.FLAG_SEMAKAN_PENGARAH, T.BUTIR_ASAS, T.KEADAAN_TANAH, T.BUTIR_TANAH,  "+
					" T.KEMUDAHAN_ASAS, T.PENUTUP, " +
					" (CASE WHEN T.JENIS_WAKTU_SIDANG = '1' THEN 'Pagi' " +
					" WHEN T.JENIS_WAKTU_SIDANG = '2' THEN 'Tengah Hari' " +
					" WHEN T.JENIS_WAKTU_SIDANG = '3' THEN 'Petang' " +
					" WHEN T.JENIS_WAKTU_SIDANG = '4' THEN 'Malam' " +
					" ELSE '' END) AS WAKTU_SIDANG_KETERANGAN, "+
					"T.JENIS_WAKTU_SIDANG, " +
					"T.WAKTU_SIDANG, TO_CHAR(T.TARIKH_SIDANG,'DD/MM/YYYY') AS TARIKH_SIDANG, T.TEMPAT_SIDANG,  "+
					" T.KETERANGAN_SIDANG, T.KEDUDUKAN_TANAH, T.LOKASI_TANAH, T.JENIS_PENGGUNAAN_TNH, "+
					"  (   SELECT SUM(CASE "+
					" 		    WHEN ID_UNITLUASAMBIL_CONVERT IS NOT NULL AND ID_UNITLUASAMBIL_CONVERT = '1' AND LUAS_AMBIL IS NOT NULL THEN  "+
					" 		    CASE  "+
					" 		        WHEN SUBSTR(ROUND(LUAS_AMBIL,4),1,1) = '.' THEN '0'|| ROUND(LUAS_AMBIL,4) "+
					" 		        WHEN SUBSTR(ROUND(LUAS_AMBIL,4),1,1) != '.' THEN '' || ROUND(LUAS_AMBIL,4) "+
					" 		    END  "+
					" 		    WHEN ID_UNITLUASAMBIL_CONVERT IS NOT NULL AND ID_UNITLUASAMBIL_CONVERT = '2' AND LUAS_AMBIL IS NOT NULL THEN  "+
					" 		    CASE  "+
					" 		        WHEN SUBSTR(ROUND(LUAS_AMBIL/10000,4),1,1) = '.' THEN '0'|| ROUND(LUAS_AMBIL/10000,4) "+
					" 		        WHEN SUBSTR(ROUND(LUAS_AMBIL/10000,4),1,1) != '.' THEN '' || ROUND(LUAS_AMBIL/10000,4) "+
					" 		    END "+
					" 		    ELSE '0' "+
					" 		    END) FROM TBLPPTHAKMILIK  WHERE P.ID_PERMOHONAN = TBLPPTHAKMILIK.ID_PERMOHONAN "+
					" 		    )   AS JUM_LUAS_AMBIL,D.NAMA_DAERAH,P.TUJUAN AS NAMA_ROJEK  "+
					" FROM TBLPPTMMK T, TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJDAERAH D "+
					" WHERE F.ID_FAIL = P.ID_FAIL AND T.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_DAERAH = D.ID_DAERAH ";
			sql += " AND F.ID_FAIL = '"+id_fail+"' ";
			}/*
			else if(jenis_skrin.equals("TarikBalik"))
			{
			sql += " SELECT PB.NO_PENARIKANBALIK, F.NO_FAIL,T.ID_MMK, T.ID_PENARIKANBALIK, T.JENIS_MMK, T.ULASAN, T.NO_RUJMMK, T.FLAG_SEMAK,  "+
					" T.ID_SEMAK, T.TARIKH_SEMAK, T.FLAG_BORANGI, T.ID_PERMOHONAN, T.TUJUAN, T.LATARBELAKANG,  "+
					" T.ASAS_PERTIMBANGAN, T.KESIMPULAN, T.SYOR, T.KEDUDUKAN_LAPORAN_TNH, T.PENGESAHAN_PERUNTUKAN, T.PANDANGAN,  "+
					"  T.IMPLIKASI, T.ULASAN_JABTEKNIKAL, T.PERIHAL_TANAH, T.PERIHAL_POHON, T.ANGGARAN_KOS, T.PERAKUAN_PENTADBIR_TNH,  "+
					"  T.NILAI_ATAS_TANAH, T.PENGECUALIAN_UPAH_UKUR, T.TARIKH_MMK, T.STATUS_SEMAKAN, T.USER_ID,T.ID_DB, T.ULASAN_PENGARAH, T.KEPUTUSAN,  "+
					"  T.PERAKUAN_SETIAUSAHA, T.TARIKH_HANTAR, T.HAL_LAIN, T.JAWATANKUASA_TANAH, T.NAMA_TUAN_TANAH, T.SEBAB_PENARIKAN,  "+
					" T.TAJUK, T.FLAG_MMK, T.FLAG_SEMAKAN_PENGARAH, T.BUTIR_ASAS, T.KEADAAN_TANAH, T.BUTIR_TANAH,  "+
					" T.KEMUDAHAN_ASAS, T.PENUTUP, T.JENIS_WAKTU_SIDANG, " +
					" (CASE WHEN T.JENIS_WAKTU_SIDANG = '1' THEN 'Pagi' " +
					" WHEN T.JENIS_WAKTU_SIDANG = '2' THEN 'Tengah Hari' " +
					" WHEN T.JENIS_WAKTU_SIDANG = '3' THEN 'Petang' " +
					" WHEN T.JENIS_WAKTU_SIDANG = '4' THEN 'Malam' " +
					" ELSE '' END) AS WAKTU_SIDANG_KETERANGAN, "+
					"T.WAKTU_SIDANG, TO_CHAR(T.TARIKH_SIDANG,'DD/MM/YYYY') AS TARIKH_SIDANG, T.TEMPAT_SIDANG,  "+
					" T.KETERANGAN_SIDANG, T.KEDUDUKAN_TANAH, T.LOKASI_TANAH, T.JENIS_PENGGUNAAN_TNH "+
					" FROM TBLPPTMMK T, TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLPPTPENARIKANBALIK PB "+
					" WHERE F.ID_FAIL = P.ID_FAIL AND T.ID_PENARIKANBALIK = PB.ID_PENARIKANBALIK AND PB.ID_PERMOHONAN = P.ID_PERMOHONAN ";
			sql += " AND T.ID_PENARIKANBALIK = '"+id_penarikan+"' ";
			}*/
			myLogger.info("##################### PAPAR MAKLUMAT mmk:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NAMA_ROJEK") == null) {h.put("NAMA_ROJEK", "");} else {h.put("NAMA_ROJEK", rs.getString("NAMA_ROJEK"));}
				if (rs.getString("JUM_LUAS_AMBIL") == null) {h.put("JUM_LUAS_AMBIL", "");} else {h.put("JUM_LUAS_AMBIL", rs.getString("JUM_LUAS_AMBIL"));}
				if (rs.getString("NAMA_DAERAH") == null) {h.put("NAMA_DAERAH", "");} else {h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH"));}
				if (rs.getString("NO_PENARIKANBALIK") == null) {h.put("NO_PENARIKANBALIK", "");} else {h.put("NO_PENARIKANBALIK", rs.getString("NO_PENARIKANBALIK"));}
				if (rs.getString("NO_FAIL") == null) {h.put("NO_FAIL", "");} else {h.put("NO_FAIL", rs.getString("NO_FAIL"));}
				if (rs.getString("KETERANGAN_SIDANG") == null) {h.put("KETERANGAN_SIDANG", "");} else {h.put("KETERANGAN_SIDANG", rs.getString("KETERANGAN_SIDANG"));}
				if (rs.getString("TEMPAT_SIDANG") == null) {h.put("TEMPAT_SIDANG", "");} else {h.put("TEMPAT_SIDANG", rs.getString("TEMPAT_SIDANG"));}
				if (rs.getString("TARIKH_SIDANG") == null) {h.put("TARIKH_SIDANG", "");} else {h.put("TARIKH_SIDANG", rs.getString("TARIKH_SIDANG"));}
				if (rs.getString("WAKTU_SIDANG") == null) {h.put("WAKTU_SIDANG", "");} else {h.put("WAKTU_SIDANG", rs.getString("WAKTU_SIDANG"));}
				if (rs.getString("JENIS_WAKTU_SIDANG") == null) {h.put("JENIS_WAKTU_SIDANG", "");} else {h.put("JENIS_WAKTU_SIDANG", rs.getString("JENIS_WAKTU_SIDANG"));}
				if (rs.getString("WAKTU_SIDANG_KETERANGAN") == null) {h.put("WAKTU_SIDANG_KETERANGAN", "");} else {h.put("WAKTU_SIDANG_KETERANGAN", rs.getString("WAKTU_SIDANG_KETERANGAN"));}
				if (rs.getString("ID_MMK") == null) {h.put("ID_MMK", "");} else {h.put("ID_MMK", rs.getString("ID_MMK"));}
				if (rs.getString("ID_PENARIKANBALIK") == null) {h.put("ID_PENARIKANBALIK", "");} else {h.put("ID_PENARIKANBALIK", rs.getString("ID_PENARIKANBALIK"));}
				if (rs.getString("STATUS_SEMAKAN") == null) {h.put("STATUS_SEMAKAN", "");} else {h.put("STATUS_SEMAKAN", rs.getString("STATUS_SEMAKAN"));}
				if (rs.getString("TUJUAN") == null) {h.put("TUJUAN", "");} else {h.put("TUJUAN", rs.getString("TUJUAN"));}
				if (rs.getString("LATARBELAKANG") == null) {h.put("LATARBELAKANG", "");} else {h.put("LATARBELAKANG", rs.getString("LATARBELAKANG"));}
				if (rs.getString("IMPLIKASI") == null) {h.put("IMPLIKASI", "");} else {h.put("IMPLIKASI", rs.getString("IMPLIKASI"));}
				if (rs.getString("ULASAN") == null) {h.put("ULASAN", "");} else {h.put("ULASAN", rs.getString("ULASAN"));}
				if (rs.getString("SYOR") == null) {h.put("SYOR", "");} else {h.put("SYOR", rs.getString("SYOR"));}
				if (rs.getString("ASAS_PERTIMBANGAN") == null) {h.put("ASAS_PERTIMBANGAN", "");} else {h.put("ASAS_PERTIMBANGAN", rs.getString("ASAS_PERTIMBANGAN"));}
				if (rs.getString("NILAI_ATAS_TANAH") == null) {h.put("NILAI_ATAS_TANAH", "");} else {h.put("NILAI_ATAS_TANAH", rs.getString("NILAI_ATAS_TANAH"));}
				if (rs.getString("SEBAB_PENARIKAN") == null) {h.put("SEBAB_PENARIKAN", "");} else {h.put("SEBAB_PENARIKAN", rs.getString("SEBAB_PENARIKAN"));}
				if (rs.getString("PERIHAL_TANAH") == null) {h.put("PERIHAL_TANAH", "");} else {h.put("PERIHAL_TANAH", rs.getString("PERIHAL_TANAH"));}
				if (rs.getString("KESIMPULAN") == null) {h.put("KESIMPULAN", "");} else {h.put("KESIMPULAN", rs.getString("KESIMPULAN"));}
				//if (rs.getString("PERAKUAN_PTG") == null) {h.put("PERAKUAN_PTG", "");} else {h.put("PERAKUAN_PTG", rs.getString("PERAKUAN_PTG"));}
				if (rs.getString("TAJUK") == null) {h.put("TAJUK", "");} else {h.put("TAJUK", rs.getString("TAJUK"));}
				if (rs.getString("FLAG_MMK") == null) {h.put("FLAG_MMK", "");} else {h.put("FLAG_MMK", rs.getString("FLAG_MMK"));}
				if (rs.getString("KEPUTUSAN") == null) {h.put("KEPUTUSAN", "");} else {h.put("KEPUTUSAN", rs.getString("KEPUTUSAN"));}
				if (rs.getString("PERIHAL_POHON") == null) {h.put("PERIHAL_POHON", "");} else {h.put("PERIHAL_POHON", rs.getString("PERIHAL_POHON"));}
				if (rs.getString("NAMA_TUAN_TANAH") == null) {h.put("NAMA_TUAN_TANAH", "");} else {h.put("NAMA_TUAN_TANAH", rs.getString("NAMA_TUAN_TANAH"));}
				if (rs.getString("PERAKUAN_PENTADBIR_TNH") == null) {h.put("PERAKUAN_PENTADBIR_TNH", "");} else {h.put("PERAKUAN_PENTADBIR_TNH", rs.getString("PERAKUAN_PENTADBIR_TNH"));}
				if (rs.getString("PENGESAHAN_PERUNTUKAN") == null) {h.put("PENGESAHAN_PERUNTUKAN", "");} else {h.put("PENGESAHAN_PERUNTUKAN", rs.getString("PENGESAHAN_PERUNTUKAN"));}
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Hashtable getMaklumatHakmilik(String id_fail,
			String id_hakmilik, Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " "
					+ " SELECT DISTINCT ID_HAKMILIK_ETAPP,NO_SUBJAKET, ID_FAIL, NO_FAIL_JKPTG, ID_NEGERI, ID_DAERAH, ID_MUKIM, "
					+ " KOD_JENIS_HAKMILIK, TO_CHAR(NO_HAKMILIK,'00000000') AS NO_HAKMILIK, ID_JENISHAKMILIK, NO_LOT,TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL, JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, "
					+ " KOD_NEGERI,KOD_DAERAH,KOD_MUKIM, "
					+ " (TRIM(KOD_NEGERI) || TRIM(KOD_DAERAH) || TRIM(KOD_MUKIM) || TRIM(KOD_JENIS_HAKMILIK) || TRIM(TO_CHAR(NO_HAKMILIK,'00000000'))) AS ID_HAKMILIK, "
					+ " (CASE " +
				    " WHEN ID_UNIT_LUAS_ASAL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'M' "
					/*" WHEN ID_UNIT_LUAS_ASAL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_ASAL = '9' THEN '' "*/
					+ " WHEN ID_UNIT_LUAS_ASAL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_ASAL, "
					+ " (CASE " +
					 " WHEN ID_UNIT_LUAS_AMBIL = '1' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'M' "
					/*
					" WHEN ID_UNIT_LUAS_AMBIL = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_AMBIL = '9' THEN '' "
					*/
					+ " WHEN ID_UNIT_LUAS_AMBIL = '' THEN '' "
					+ " ELSE '' END) AS UNIT_LUAS_AMBIL "
					+ " FROM (SELECT DISTINCT C.NO_SUBJAKET, A.ID_FAIL, A.NO_FAIL AS NO_FAIL_JKPTG,A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, "
					+ " JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK,C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, C.NO_LOT,C.ID_HAKMILIK AS ID_HAKMILIK_ETAPP, "
					+ " B.TARIKH_PERMOHONAN,C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+ " TRIM (TO_CHAR (C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM (TO_CHAR (C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL,D.KOD_NEGERI AS KOD_NEGERI,F.KOD_DAERAH AS KOD_DAERAH,"
					+ " E.KOD_MUKIM AS KOD_MUKIM "
					+ " FROM TBLPFDFAIL A,TBLPPTPERMOHONAN B,TBLPPTHAKMILIK C, TBLRUJNEGERI D, "
					+ " TBLRUJMUKIM E,TBLRUJDAERAH F,TBLRUJJENISHAKMILIK JH,TBLRUJLUAS JL_ASAL,TBLRUJLUAS JL_AMBIL "
					+ " WHERE  A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI "
					+ " AND C.ID_MUKIM = E.ID_MUKIM AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) AND C.ID_UNITLUASAMBIL_CONVERT = JL_AMBIL.ID_LUAS(+) ";

			sql += " AND A.ID_FAIL = '"
					+ id_fail
					+ "'  AND C.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' "
					+ " AND (TRANSLATE(TRIM(C.NO_HAKMILIK),'_0123456789','_')) IS NULL AND A.NO_FAIL IS NOT NULL "
					+ " AND (C.FLAG_PENARIKAN_KESELURUHAN IS NULL AND C.FLAG_PEMBATALAN_KESELURUHAN IS NULL) ORDER BY C.NO_SUBJAKET, C.NO_LOT ASC) ";

			myLogger.info("##################### PAPAR MAKLUMAT HAKMILIK :"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_FAIL", rs.getString("ID_FAIL"));
				if (rs.getString("ID_HAKMILIK") == null) {
					h.put("ID_HAKMILIK_ETANAH", "");
				} else {
					h.put("ID_HAKMILIK_ETANAH", rs.getString("ID_HAKMILIK")
							.toUpperCase());
				}

				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h.put("KOD_NEGERI", rs.getString("KOD_NEGERI")
							.toUpperCase());
				}

				if (rs.getString("KOD_DAERAH") == null) {
					h.put("KOD_DAERAH", "");
				} else {
					h.put("KOD_DAERAH", rs.getString("KOD_DAERAH")
							.toUpperCase());
				}

				if (rs.getString("KOD_MUKIM") == null) {
					h.put("KOD_MUKIM", "");
				} else {
					h.put("KOD_MUKIM", rs.getString("KOD_MUKIM").toUpperCase());
				}

				if (rs.getString("KOD_JENIS_HAKMILIK") == null) {
					h.put("KOD_JENIS_HAKMILIK", "");
				} else {
					h.put("KOD_JENIS_HAKMILIK",
							rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				}

				if (rs.getString("NO_HAKMILIK") == null) {
					h.put("NO_HAKMILIK", "");
				} else {
					h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK")
							.toUpperCase());
				}

			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Hashtable getMaklumatWarta(String id_fail, Db db)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT DISTINCT A.ID_FAIL, A.NO_FAIL, B.TARIKH_PERMOHONAN,  W.NO_WARTA,UPPER(B.TUJUAN) AS NAMA_PROJEK,UPPER(M.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN, "
					+ " TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA  "
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTWARTA W,TBLRUJKEMENTERIAN M  "
					+ " WHERE A.ID_FAIL = B.ID_FAIL   "
					+ " AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+)  "
					+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F  "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)  "
					+ " AND A.ID_KEMENTERIAN = M.ID_KEMENTERIAN  "
					+ " AND A.ID_FAIL = '" + id_fail + "' ";

			myLogger.info("##################### PAPAR MAKLUMAT WARTA:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			int count = 0;
			
			
			
			
				while (rs.next()) {
					h.put("ID_FAIL", rs.getString("ID_FAIL")); 
					if (rs.getString("NO_WARTA") == null) {
						h.put("NO_WARTA", "");
					} else {
						h.put("NO_WARTA", rs.getString("NO_WARTA").toUpperCase());
					}
					if (rs.getString("TARIKH_WARTA") == null) {
						h.put("TARIKH_WARTA", "");
					} else {
						h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA")
								.toUpperCase());
					}
					count++;
				}
				
				myLogger.info("WARTA SIZE"+count);
			
			if(count == 0)
			{
				 	h.put("ID_FAIL", id_fail); 			
					h.put("NO_WARTA", "");
					h.put("TARIKH_WARTA", "");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Hashtable getMaklumatPU(String id_fail, String id_hakmilik,
			Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT DISTINCT ID_FAIL,STATUS_AMBIL,NO_SUBJAKET,ID_PERMINTAANUKUR,NO_SYIT,NO_PU,TARIKH_BORANG_PU, TARIKH_TERIMA_PA_B1_JUPEM,NO_PA,NO_LOT_BARU,LUAS_PA,ID_UNIT_LUAS_PA,NO_FAIL,"
					+ " ID_NEGERI,ID_DAERAH,ID_MUKIM,KOD_JENIS_HAKMILIK, NO_HAKMILIK,ID_JENISHAKMILIK, NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL,"
					+ " (CASE WHEN ID_UNIT_LUAS_PA = '1' THEN '' "
					+ " WHEN ID_UNIT_LUAS_PA = '2' THEN 'H' "
					+ " WHEN ID_UNIT_LUAS_PA = '3' THEN 'M' "
					+ " WHEN ID_UNIT_LUAS_PA = '4' THEN 'E' "
					+ " WHEN ID_UNIT_LUAS_PA = '5' THEN 'K' "
					+ " WHEN ID_UNIT_LUAS_PA = '6' THEN 'P' "
					+ " WHEN ID_UNIT_LUAS_PA = '7' THEN 'D' "
					+ " WHEN ID_UNIT_LUAS_PA = '8' THEN 'R' "
					+ " WHEN ID_UNIT_LUAS_PA = '9' THEN '' "
					+ " WHEN ID_UNIT_LUAS_PA = '' THEN '' "
					+ " ELSE '' END) AS KOD_UNIT_LUAS_PA, "
					+ "ID_UNIT_LUAS_ASAL,"
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL,LUAS_ASAL,LUAS_AMBIL," +
				//	" NO_WARTA ,TARIKH_WARTA," +
					" TARIKH_BORANGK FROM( ";

			sql += " SELECT DISTINCT  C.NO_SUBJAKET,PU.ID_PERMINTAANUKUR,A.ID_FAIL, "
					+ " (SELECT  "
					+ " rtrim (xmlagg (xmlelement (e, PP.NO_PELAN || ', ')).extract ('//text()'), ', ') NO_SYIT "
					+ " FROM TBLPPTNOPELANPU PPU,TBLPPTNOPELAN PP "
					+ " WHERE PP.ID_NOPELAN = PPU.ID_NOPELAN  "
					+ " AND PPU.ID_PERMINTAANUKUR = PU.ID_PERMINTAANUKUR) AS NO_SYIT, "
					+ " PU.NO_PU,TO_CHAR(PU.TARIKH_PU,'DD/MM/YYYY') AS TARIKH_BORANG_PU, TO_CHAR(PU.TARIKH_TERIMA_PA,'DD/MM/YYYY') AS TARIKH_TERIMA_PA_B1_JUPEM, "
					+ " PU.NO_PA,PU.NO_LOT_BARU,TRIM(TO_CHAR(PU.LUAS_PU,'9999999999999990.9999')) AS LUAS_PA, '2' AS ID_UNIT_LUAS_PA, "
					+ " A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					+" TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL, "
					+ " TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, " 
					/*" W.NO_WARTA, "
					+ " TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, " +*/
					+" TO_CHAR(BK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, "
					+ " (CASE WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) =  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'P' "
					+ " WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) <>  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'S' "
					+ " ELSE '' END) AS STATUS_AMBIL "
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL, "
					//" TBLPPTWARTA W," +
					+" TBLPPTPERMINTAANUKUR PU "
					+ " ,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM "
					+ " AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+) "
					+ " AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+) "
					//" AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) "
					/*+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2) "*/
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' "
					+ " AND PU.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK  "
					+ " AND C.ID_HAKMILIK = '" + id_hakmilik + "' " + "";

			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";

			sql += " )";

			myLogger.info("##################### PAPAR MAKLUMAT PU:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_ASAL",
						rs.getString("JENIS_LUAS_ASAL") == null ? "" : rs
								.getString("JENIS_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_AMBIL",
						rs.getString("JENIS_LUAS_AMBIL") == null ? "" : rs
								.getString("JENIS_LUAS_AMBIL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("TARIKH_BORANGK",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK").toUpperCase());
				h.put("STATUS_AMBIL", rs.getString("STATUS_AMBIL") == null ? ""
						: rs.getString("STATUS_AMBIL").toUpperCase()); // P-PENUH
																		// S-SEBAHAGIAN
				h.put("ID_PERMINTAANUKUR",
						rs.getString("ID_PERMINTAANUKUR") == null ? "" : rs
								.getString("ID_PERMINTAANUKUR").toUpperCase());
				h.put("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs
						.getString("NO_SYIT").toUpperCase());
				h.put("NO_PU", rs.getString("NO_PU") == null ? "" : rs
						.getString("NO_PU").toUpperCase());
				h.put("TARIKH_BORANG_PU",
						rs.getString("TARIKH_BORANG_PU") == null ? "" : rs
								.getString("TARIKH_BORANG_PU").toUpperCase());
				h.put("TARIKH_TERIMA_PA_B1_JUPEM", rs
						.getString("TARIKH_TERIMA_PA_B1_JUPEM") == null ? ""
						: rs.getString("TARIKH_TERIMA_PA_B1_JUPEM")
								.toUpperCase());
				h.put("NO_PA", rs.getString("NO_PA") == null ? "" : rs
						.getString("NO_PA").toUpperCase());
				h.put("NO_LOT_BARU", rs.getString("NO_LOT_BARU") == null ? ""
						: rs.getString("NO_LOT_BARU").toUpperCase());
				h.put("LUAS_PA", rs.getString("LUAS_PA") == null ? "" : rs
						.getString("LUAS_PA").toUpperCase() + " HEKTAR");
				h.put("ID_UNIT_LUAS_PA",
						rs.getString("ID_UNIT_LUAS_PA") == null ? "" : rs
								.getString("ID_UNIT_LUAS_PA").toUpperCase());
				h.put("KOD_UNIT_LUAS_PA",
						rs.getString("KOD_UNIT_LUAS_PA") == null ? "" : rs
								.getString("KOD_UNIT_LUAS_PA").toUpperCase());
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Hashtable getMaklumatBorangK(String id_fail,
			String id_hakmilik, Db db) throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT DISTINCT NO_SUBJAKET,ID_FAIL, NO_FAIL, ID_NEGERI, ID_DAERAH, ID_MUKIM, KOD_JENIS_HAKMILIK, NO_HAKMILIK, ID_JENISHAKMILIK, "
					+ " NO_LOT, TARIKH_PERMOHONAN, ID_UNIT_LUAS_AMBIL, ID_UNIT_LUAS_ASAL, "
					+ " JENIS_LUAS_ASAL,JENIS_LUAS_AMBIL, LUAS_ASAL, LUAS_AMBIL, " +
				/*	" NO_WARTA, "
					+ " TARIKH_WARTA, " +*/
					" TARIKH_BORANGK, STATUS_AMBIL FROM( ";

			sql += " SELECT DISTINCT C.NO_SUBJAKET,A.ID_FAIL, A.NO_FAIL, A.ID_NEGERI, B.ID_DAERAH, C.ID_MUKIM, JH.KOD_JENIS_HAKMILIK AS KOD_JENIS_HAKMILIK, C.NO_HAKMILIK, JH.ID_JENISHAKMILIK, "
					+ " C.NO_LOT, B.TARIKH_PERMOHONAN, C.ID_UNITLUASAMBIL_CONVERT AS ID_UNIT_LUAS_AMBIL, C.ID_UNITLUASLOT_CONVERT AS ID_UNIT_LUAS_ASAL, "
					
					
					//+ " (JL_ASAL.KETERANGAN) AS JENIS_LUAS_ASAL," 			
					//+" (JL_AMBIL.KETERANGAN) AS JENIS_LUAS_AMBIL, " 
					
					+ " (CASE WHEN C.ID_UNITLUASLOT_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASLOT_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_ASAL, "
					
					+ " (CASE WHEN C.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'HEKTAR' "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT= '2' THEN 'METER PERSEGI' "
					+ " ELSE '' END) AS JENIS_LUAS_AMBIL, " 
					
					
					+" TRIM(TO_CHAR(C.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,TRIM(TO_CHAR(C.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, "
					/*"  W.NO_WARTA, "
					+ " TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA," + */
					+ " TO_CHAR(BK.TARIKH_BORANGK,'DD/MM/YYYY') AS TARIKH_BORANGK, "
					+ " (CASE WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) =  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'P' "
					+ " WHEN "
					+ " ((CASE  WHEN C.ID_UNITLUASLOT_CONVERT = 1 THEN C.LUAS_LOT * 1 WHEN C.ID_UNITLUASLOT_CONVERT = 2 THEN C.LUAS_LOT / 10000     ELSE 0 END) <>  (CASE  WHEN C.ID_UNITLUASAMBIL_CONVERT = 1 THEN C.LUAS_AMBIL * 1   "
					+ " WHEN C.ID_UNITLUASAMBIL_CONVERT = 2 THEN C.LUAS_AMBIL / 10000  ELSE 0 END)) THEN 'S' "
					+ " ELSE '' END) AS STATUS_AMBIL "
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTHAKMILIK C, TBLRUJNEGERI D, TBLRUJMUKIM E, TBLRUJDAERAH F, TBLRUJJENISHAKMILIK JH, TBLRUJLUAS JL_ASAL, "
					+ " TBLRUJLUAS JL_AMBIL " 
					//" , TBLPPTWARTA W "
					+ " ,TBLPPTHAKMILIKBORANGK HBK,TBLPPTBORANGK BK "
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND A.ID_NEGERI = D.ID_NEGERI AND C.ID_MUKIM = E.ID_MUKIM  AND B.ID_DAERAH = F.ID_DAERAH AND C.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
					+ " AND C.ID_UNITLUASLOT_CONVERT = JL_ASAL.ID_LUAS(+)  AND C.ID_UNITLUASAMBIL= JL_AMBIL.ID_LUAS(+) " 
					//" AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) "
					+ " AND C.ID_HAKMILIK = '"
					+ id_hakmilik
					+ "' "
				/*	+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F "
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"
					+ id_fail
					+ "' AND ROWNUM < 2)   "*/
					+ " AND A.ID_FAIL = '"
					+ id_fail
					+ "' AND HBK.ID_HAKMILIK = C.ID_HAKMILIK AND HBK.ID_BORANGK = BK.ID_BORANGK  "
					+ "";
			sql += " ORDER BY C.NO_SUBJAKET,C.NO_LOT ASC ";
			sql += " )";

			myLogger.info("##################### PAPAR MAKLUMAT BORANG K:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("NO_SUBJAKET", rs.getString("NO_SUBJAKET") == null ? ""
						: rs.getString("NO_SUBJAKET").toUpperCase());
				h.put("ID_FAIL", rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL").toUpperCase());
				h.put("NO_FAIL", rs.getString("NO_FAIL") == null ? "" : rs
						.getString("NO_FAIL").toUpperCase());
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI").toUpperCase());
				h.put("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs
						.getString("ID_DAERAH").toUpperCase());
				h.put("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs
						.getString("ID_MUKIM").toUpperCase());
				h.put("KOD_JENIS_HAKMILIK",
						rs.getString("KOD_JENIS_HAKMILIK") == null ? "" : rs
								.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? ""
						: rs.getString("NO_HAKMILIK").toUpperCase());
				h.put("ID_JENISHAKMILIK",
						rs.getString("ID_JENISHAKMILIK") == null ? "" : rs
								.getString("ID_JENISHAKMILIK").toUpperCase());
				h.put("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs
						.getString("NO_LOT").toUpperCase());
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN").toUpperCase());
				h.put("ID_UNIT_LUAS_AMBIL",
						rs.getString("ID_UNIT_LUAS_AMBIL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				h.put("ID_UNIT_LUAS_ASAL",
						rs.getString("ID_UNIT_LUAS_ASAL") == null ? "" : rs
								.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_ASAL",
						rs.getString("JENIS_LUAS_ASAL") == null ? "" : rs
								.getString("JENIS_LUAS_ASAL").toUpperCase());
				h.put("JENIS_LUAS_AMBIL",
						rs.getString("JENIS_LUAS_AMBIL") == null ? "" : rs
								.getString("JENIS_LUAS_AMBIL").toUpperCase());
				h.put("LUAS_ASAL",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase());
				h.put("LUAS_ASAL_DISPLAY",
						rs.getString("LUAS_ASAL") == null ? "" : rs.getString(
								"LUAS_ASAL").toUpperCase()
								+ (rs.getString("JENIS_LUAS_ASAL") == null ? ""
										: " "
												+ rs.getString(
														"JENIS_LUAS_ASAL")
														.toUpperCase()));
				h.put("LUAS_AMBIL",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase());
				h.put("LUAS_AMBIL_DISPLAY",
						rs.getString("LUAS_AMBIL") == null ? ""
								: rs.getString("LUAS_AMBIL").toUpperCase()
										+ (rs.getString("JENIS_LUAS_AMBIL") == null ? ""
												: " "
														+ rs.getString(
																"JENIS_LUAS_AMBIL")
																.toUpperCase()));
				/*
				h.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
				h.put("TARIKH_BORANGK",
						rs.getString("TARIKH_BORANGK") == null ? "" : rs
								.getString("TARIKH_BORANGK").toUpperCase());
				h.put("STATUS_AMBIL", rs.getString("STATUS_AMBIL") == null ? ""
						: rs.getString("STATUS_AMBIL").toUpperCase()); // P-PENUH
																		// S-SEBAHAGIAN
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Hashtable getMaklumatTarikBalik(String id_penarikan, Db db)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = " SELECT DISTINCT NO_PENARIKANBALIK,TARIKH_PENARIKANBALIK,JENIS_PENARIKANBALIK,JENIS_PB_KETERANGAN,SEBAB_PENARIKANBALIK" 
					//" , NO_WARTA,  TARIKH_WARTA "
					+ " FROM(  SELECT DISTINCT PB.NO_PENARIKANBALIK,TO_CHAR(PB.TARIKH_PENARIKAN_BALIK,'DD/MM/YYYY') AS TARIKH_PENARIKANBALIK,PB.JENIS_PENARIKANBALIK,  "
					+ " (CASE WHEN PB.JENIS_PENARIKANBALIK = '1' THEN 'SEBAHAGIAN LOT'  WHEN PB.JENIS_PENARIKANBALIK = '2' THEN 'KESELURUHAN LOT'  ELSE '' END) AS JENIS_PB_KETERANGAN,   "
					+ " PB.SEBAB_PENARIKANBALIK" 
					//" ,W.NO_WARTA,    TO_CHAR(W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA  "
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, "
					//" TBLPPTWARTA W  ," +
					+" TBLPPTPENARIKANBALIK PB,TBLPPTPENARIKANHAKMILIK PBH    "
					+ " WHERE  A.ID_FAIL = B.ID_FAIL  "
					//+ " AND PB.ID_PENARIKANBALIK = W.ID_PENARIKANBALIK(+)     "
					/*+ " AND (((NVL(W.NO_WARTA,'0') != '0')   AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPENARIKANBALIK PB1  "
					+ " WHERE WW.ID_PENARIKANBALIK = PB1.ID_PENARIKANBALIK  AND PB1.ID_PENARIKANBALIK = '"
					+ id_penarikan
					+ "' AND ROWNUM < 2)) OR (NVL (W.NO_WARTA, '0') = '0'))     "*/
					+ " AND PB.ID_PENARIKANBALIK = PBH.ID_PENARIKANBALIK AND PB.ID_PERMOHONAN = B.ID_PERMOHONAN  "
					+ " AND PB.ID_PENARIKANBALIK = '" + id_penarikan + "' ) ";

			myLogger.info("##################### PAPAR MAKLUMAT TARIKBALIK:"
					+ sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("NO_PENARIKANBALIK",
						rs.getString("NO_PENARIKANBALIK") == null ? "" : rs
								.getString("NO_PENARIKANBALIK").toUpperCase());
				h.put("TARIKH_PENARIKANBALIK", rs
						.getString("TARIKH_PENARIKANBALIK") == null ? "" : rs
						.getString("TARIKH_PENARIKANBALIK").toUpperCase());
				h.put("JENIS_PENARIKANBALIK", rs
						.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
						.getString("JENIS_PENARIKANBALIK").toUpperCase());
				h.put("JENIS_PB_KETERANGAN", rs
						.getString("JENIS_PB_KETERANGAN") == null ? "" : rs
						.getString("JENIS_PB_KETERANGAN").toUpperCase());
				h.put("JENIS_PENARIKANBALIK", rs
						.getString("JENIS_PENARIKANBALIK") == null ? "" : rs
						.getString("JENIS_PENARIKANBALIK").toUpperCase());
				h.put("SEBAB_PENARIKANBALIK", rs
						.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs
						.getString("SEBAB_PENARIKANBALIK").toUpperCase());
				/*
				 * h.put("NO_SUBJAKET",rs.getString("NO_SUBJAKET") == null ? ""
				 * : rs.getString("NO_SUBJAKET").toUpperCase());
				 * h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" :
				 * rs.getString("ID_FAIL").toUpperCase());
				 * h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" :
				 * rs.getString("NO_FAIL").toUpperCase());
				 * h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" :
				 * rs.getString("ID_NEGERI").toUpperCase());
				 * h.put("ID_DAERAH",rs.getString("ID_DAERAH") == null ? "" :
				 * rs.getString("ID_DAERAH").toUpperCase());
				 * h.put("ID_MUKIM",rs.getString("ID_MUKIM") == null ? "" :
				 * rs.getString("ID_MUKIM").toUpperCase());
				 * h.put("KOD_JENIS_HAKMILIK",rs.getString("KOD_JENIS_HAKMILIK")
				 * == null ? "" :
				 * rs.getString("KOD_JENIS_HAKMILIK").toUpperCase());
				 * h.put("NO_HAKMILIK",rs.getString("NO_HAKMILIK") == null ? ""
				 * : rs.getString("NO_HAKMILIK").toUpperCase());
				 * h.put("ID_JENISHAKMILIK",rs.getString("ID_JENISHAKMILIK") ==
				 * null ? "" : rs.getString("ID_JENISHAKMILIK").toUpperCase());
				 * h.put("NO_LOT",rs.getString("NO_LOT") == null ? "" :
				 * rs.getString("NO_LOT").toUpperCase());
				 * h.put("TARIKH_PERMOHONAN",rs.getString("TARIKH_PERMOHONAN")
				 * == null ? "" :
				 * rs.getString("TARIKH_PERMOHONAN").toUpperCase());
				 * h.put("ID_UNIT_LUAS_AMBIL",rs.getString("ID_UNIT_LUAS_AMBIL")
				 * == null ? "" :
				 * rs.getString("ID_UNIT_LUAS_AMBIL").toUpperCase());
				 * h.put("ID_UNIT_LUAS_ASAL",rs.getString("ID_UNIT_LUAS_ASAL")
				 * == null ? "" :
				 * rs.getString("ID_UNIT_LUAS_ASAL").toUpperCase());
				 * h.put("JENIS_LUAS_ASAL",rs.getString("JENIS_LUAS_ASAL") ==
				 * null ? "" : rs.getString("JENIS_LUAS_ASAL").toUpperCase());
				 * h.put("JENIS_LUAS_AMBIL",rs.getString("JENIS_LUAS_AMBIL") ==
				 * null ? "" : rs.getString("JENIS_LUAS_AMBIL").toUpperCase());
				 * h.put("LUAS_ASAL",rs.getString("LUAS_ASAL") == null ? "" :
				 * rs.getString("LUAS_ASAL").toUpperCase()
				 * +(rs.getString("JENIS_LUAS_ASAL") == null ? "" :
				 * " "+rs.getString("JENIS_LUAS_ASAL").toUpperCase()));
				 * h.put("LUAS_AMBIL",rs.getString("LUAS_AMBIL") == null ? "" :
				 * rs.getString("LUAS_AMBIL").toUpperCase() +
				 * (rs.getString("JENIS_LUAS_AMBIL") == null ? "" :
				 * " "+rs.getString("JENIS_LUAS_AMBIL").toUpperCase()));
				 */
				/*.put("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs
						.getString("NO_WARTA").toUpperCase());
				h.put("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? ""
						: rs.getString("TARIKH_WARTA").toUpperCase());*/
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

	public static Integer dokumen_COUNT(String id_permohonan,
			String id_hakmilik, String jenis_skrin, Db db, String id_penarikan)
			throws Exception {

		// Db db = null;
		String sql = "";
		try {
			// db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			if (jenis_skrin.equals("hantarPelanChartingS8")
					|| jenis_skrin.equals("hantarPelanChartingS4")
					|| jenis_skrin.equals("BorangC")
					|| jenis_skrin.equals("BorangI")
					|| jenis_skrin.equals("BorangA")
					|| jenis_skrin.equals("MMK_S8")
					|| jenis_skrin.equals("MMK_S4")
					|| jenis_skrin.equals("WartaS8")
					|| jenis_skrin.equals("WartaS4")) {
				sql = " SELECT DISTINCT COUNT(ID_DOKUMENINT) AS COUNT_DOKUMEN  ";
				sql += " FROM TBLPPTDOKUMENINT ";
				sql += " WHERE ID_PERMOHONAN = '" + id_permohonan
						+ "' AND JENIS_DOKUMENINT_INTEGRASI  = '" + jenis_skrin
						+ "' AND ID_PENARIKANBALIK IS NULL ";
				if (!id_hakmilik.equals("") && !id_hakmilik.equals(null)) {
					sql += " AND ID_HAKMILIK = '" + id_hakmilik + "' ";
				}
			} else if (jenis_skrin.equals("TarikBalik")) {
				sql = " SELECT DISTINCT COUNT(ID_DOKUMENINT) AS COUNT_DOKUMEN  ";
				sql += " FROM TBLPPTDOKUMENINT ";
				sql += " WHERE ID_PERMOHONAN = '" + id_permohonan
						+ "' AND JENIS_DOKUMENINT_INTEGRASI  = '" + jenis_skrin
						+ "' AND ID_PENARIKANBALIK = '" + id_penarikan + "' ";
				if (!id_hakmilik.equals("") && !id_hakmilik.equals(null)) {
					sql += " AND  ID_HAKMILIK = '" + id_hakmilik + "' ";
				}
			} else if (jenis_skrin.equals("BorangK")
					|| jenis_skrin.equals("PU") || jenis_skrin.equals("SijilUkur")) {
				sql = " SELECT DISTINCT COUNT(ID_DOKUMENINT) AS COUNT_DOKUMEN  ";
				sql += " FROM TBLPPTDOKUMENINT ";
				sql += " WHERE ID_PERMOHONAN = '" + id_permohonan
						+ "' AND JENIS_DOKUMENINT_INTEGRASI  = '" + jenis_skrin
						+ "' AND ID_PENARIKANBALIK IS NULL ";
				sql += " AND ID_HAKMILIK = '" + id_hakmilik + "' ";

			}
			myLogger.info("##################### dokumen_COUNT:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Integer h = 0;
			// h = new Hashtable();
			while (rs.next()) {
				/*
				 * if (rs.getString("COUNT_DOKUMEN") == null) {
				 * h.put("COUNT_DOKUMEN", ""); } else { h.put("COUNT_DOKUMEN",
				 * rs.getString("COUNT_DOKUMEN").toUpperCase()); }
				 */
				h = rs.getInt("COUNT_DOKUMEN");
			}
			return h;
		} finally {
			// if (db != null)
			// db.close();
		}
	}

}

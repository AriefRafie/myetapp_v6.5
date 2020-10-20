package ekptg.intergration.eTanah.pengambilan;

import integrasi.rest.etanah.wpkl.RESTInvoker;
import integrasi.rest.etanah.wpkl.entities.Hakmilik;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujdokumen;

public class PopupeTanahData {

	protected Db db;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
	private static Logger myLog = Logger.getLogger(PopupeTanahData.class);
	
	public String getIdPermohonanIntegrasi(String idFail, String idPPTWarta, String idPPTHakmilik, String idPPTPenarikanBalik, String flagUrusan, Db db) {
		String idPermohonanIntegrasi = "";
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			if ("D".equals(flagUrusan)) {
				sql = "SELECT ID_PERMOHONAN FROM TBLINTANAHPERMOHONAN"
						+ " WHERE ID_PERMOHONAN = '" + idPPTWarta + "' AND FLAG_URUSAN = '" + flagUrusan + "'";
			}
			
			if ("K".equals(flagUrusan)) {
				sql = "SELECT A.ID_PERMOHONAN FROM INT_PPTPERMOHONAN A, TBLPFDFAIL B, INT_PPTHAKMILIKPERMOHONAN C"
						+ " WHERE A.NO_FAIL = B.NO_FAIL AND A.ID_PERMOHONAN = C.ID_PERMOHONAN"
						+ " AND B.ID_FAIL = '" + idFail + "' AND A.FLAG_URUSAN = '" + flagUrusan + "'"
						+ " AND C.ID_HAKMILIK = '" + getUPIIdHakmilik(idPPTHakmilik, db) + "' ORDER BY A.ID_PERMOHONAN DESC";
			}
			
			if ("PD".equals(flagUrusan)) {
				sql = "SELECT ID_PERMOHONAN FROM INT_PPTPERMOHONAN"
						+ " WHERE ID_PENARIKANBALIK = '" + idPPTPenarikanBalik + "' AND FLAG_URUSAN = '" + flagUrusan + "'";
			}			
			myLog.info(" SQL >>> getIdPermohonanIntegrasi : "+sql);
			if (!"".equals(sql)) {
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					idPermohonanIntegrasi = rs.getString("ID_PERMOHONAN");
				}
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return idPermohonanIntegrasi;
	}

	private String getUPIIdHakmilik(String idPPTHakmilik, Db db) {
		String sql = "";
		String idHakmilik = "";
		try {
			Statement stmt = db.getStatement();
			sql = "SELECT DISTINCT TBLRUJNEGERI.KOD_NEGERI || TBLRUJDAERAH.KOD_DAERAH || TBLRUJMUKIM.KOD_MUKIM ||"
					+ " TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK || TRIM(TO_CHAR(TBLPPTHAKMILIK.NO_HAKMILIK,'00000000')) AS ID_HAKMILIK"
					+ " FROM TBLPPTHAKMILIK, TBLRUJNEGERI, TBLRUJMUKIM, TBLRUJDAERAH, TBLRUJJENISHAKMILIK"
					+ " WHERE TBLPPTHAKMILIK.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPPTHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) AND TBLPPTHAKMILIK.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+)"
					+ " AND TBLPPTHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) AND TBLPPTHAKMILIK.ID_HAKMILIK = '" + idPPTHakmilik + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				idHakmilik = rs.getString("ID_HAKMILIK");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return idHakmilik;
	}

	public String generatePermohonanD(String idPermohonanIntegrasi, String idFail, String idPPTWarta, String flagUrusan, String idPengguna, Db db) {
		String sql = "";
		String idPermohonanIntegrasiString = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "DELETE FROM TBLINTANAHPERMOHONANMILIK WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			//sql = "DELETE FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM TBLINTANAHDOKUMEN WHERE ID_RUJUKAN = '" + idPermohonanIntegrasi + "'";
			//sql = "DELETE FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM TBLINTANAHPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			//sql = "DELETE FROM INT_PPTPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			stmt.executeUpdate(sql);

			sql = "SELECT DISTINCT W.ID_WARTA, A.ID_FAIL, A.NO_FAIL, UPPER (B.TUJUAN) AS NAMA_PROJEK, UPPER (M.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN,"
					+ " W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA"
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTWARTA W, TBLRUJKEMENTERIAN M"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+)"
					+ " AND A.ID_KEMENTERIAN = M.ID_KEMENTERIAN AND A.ID_FAIL = '" + idFail + "'"
					+ " AND W.ID_WARTA = '" + idPPTWarta + "'";
			myLog.info("generatePermohonanD permohonan : "+sql);
			ResultSet rsPermohonan = stmt.executeQuery(sql);

			List listPermohonan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			if (rsPermohonan.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("ID_WARTA", rsPermohonan.getString("ID_WARTA") == null ? "" : rsPermohonan.getString("ID_WARTA"));
				h.put("NO_FAIL", rsPermohonan.getString("NO_FAIL") == null ? "" : rsPermohonan.getString("NO_FAIL"));
				h.put("NAMA_KEMENTERIAN", rsPermohonan.getString("NAMA_KEMENTERIAN") == null ? "" : rsPermohonan.getString("NAMA_KEMENTERIAN"));
				h.put("NAMA_PROJEK", rsPermohonan.getString("NAMA_PROJEK") == null ? "" : rsPermohonan.getString("NAMA_PROJEK"));
				h.put("TARIKH_WARTA", rsPermohonan.getString("TARIKH_WARTA") == null ? "" : rsPermohonan.getString("TARIKH_WARTA"));
				h.put("NO_WARTA", rsPermohonan.getString("NO_WARTA") == null ? "" : rsPermohonan.getString("NO_WARTA"));
				listPermohonan.add(h);
			}

			for (int i = 0; i < listPermohonan.size(); i++) {
				Map mapPermohonan = (Map) listPermohonan.get(i);
				if (mapPermohonan != null) {
					String ID_WARTA = (String) mapPermohonan.get("ID_WARTA");
					String NO_FAIL = (String) mapPermohonan.get("NO_FAIL");
					String NAMA_KEMENTERIAN = (String) mapPermohonan.get("NAMA_KEMENTERIAN");
					String NAMA_PROJEK = (String) mapPermohonan.get("NAMA_PROJEK");
					String TARIKH_WARTA = (String) mapPermohonan.get("TARIKH_WARTA");
					String NO_WARTA = (String) mapPermohonan.get("NO_WARTA");

					r = new SQLRenderer();
					long idPermohonan = DB.getNextID(db, "INT_PPTPERMOHONAN_SEQ");
					idPermohonanIntegrasiString = String.valueOf(idPermohonan);
					r.add("ID_PERMOHONAN", idPermohonan);
					r.add("ID_WARTA", ID_WARTA);
					r.add("NO_FAIL", NO_FAIL);
					r.add("NO_JILID", "");
					r.add("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					r.add("NAMA_PROJEK", NAMA_PROJEK);
					r.add("NO_WARTA", NO_WARTA);
					r.add("ID_PENGGUNA", idPengguna);
					if (TARIKH_WARTA != null)
						r.add("TARIKH_WARTA", r.unquote("to_date('" + TARIKH_WARTA + "','dd/MM/yyyy')"));
					r.add("FLAG_URUSAN", flagUrusan);
					sql = r.getSQLInsert("INT_PPTPERMOHONAN");
					myLog.info("sql insert INT_PPTPERMOHONAN :::: "+sql);
					stmt.executeUpdate(sql);

					sql = "SELECT DISTINCT TBLRUJNEGERI.KOD_NEGERI || '00' || TBLRUJMUKIM.KOD_MUKIM || TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK || TRIM(TO_CHAR(TBLPPTHAKMILIK.NO_HAKMILIK,'00000000')) AS ID_HAKMILIK,"
							+ " TBLRUJNEGERI.KOD_NEGERI, TBLRUJDAERAH.KOD_DAERAH, TBLRUJMUKIM.KOD_MUKIM, TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK, TO_CHAR (TBLPPTBORANGK.TARIKH_BORANGK, 'DD/MM/YYYY') AS TARIKH_BORANGK, TO_CHAR (TBLPPTWARTA.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, TBLPPTWARTA.NO_WARTA,"
							+ " TBLPPTHAKMILIK.NO_HAKMILIK, UPPER(TBLRUJLOT.KETERANGAN) AS KOD_LOT, TBLPPTHAKMILIK.NO_PT, TBLPPTHAKMILIK.NO_LOT,"
							+ " (CASE WHEN TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = '1' THEN 'Hektar' WHEN TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = '2' THEN 'Meter Persegi'  ELSE '' END) AS KOD_LUAS_ASAL,"
							+ " TRIM(TO_CHAR(TBLPPTHAKMILIK.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,"
							+ " (CASE WHEN TBLPPTHAKMILIK.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'Hektar' WHEN TBLPPTHAKMILIK.ID_UNITLUASAMBIL_CONVERT = '2' THEN 'Meter Persegi' ELSE '' END) AS KOD_LUAS_AMBIL,"
							+ " TRIM(TO_CHAR(TBLPPTHAKMILIK.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, TBLPPTHAKMILIK.NO_SUBJAKET,CASE WHEN LUAS_LOT <> LUAS_AMBIL THEN 'SEBAHAGIAN' ELSE 'KESELURUHAN' END AS STATUS_AMBIL, TBLPPTHAKMILIK.ID_HAKMILIK AS ID_PPTHAKMILIK"
							+ " FROM TBLPFDFAIL, TBLPPTPERMOHONAN, TBLPPTHAKMILIK, TBLRUJNEGERI, TBLRUJMUKIM, TBLRUJDAERAH, TBLRUJJENISHAKMILIK, TBLRUJLUAS RUJLUAS_ASAL, TBLRUJLUAS RUJLUAS_AMBIL, TBLRUJLOT, TBLPPTBORANGK, TBLPPTWARTA, TBLPPTHAKMILIKBORANGK"
							+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPPTPERMOHONAN.ID_FAIL AND TBLPPTPERMOHONAN.ID_PERMOHONAN = TBLPPTHAKMILIK.ID_PERMOHONAN AND TBLPPTBORANGK.ID_BORANGK(+) = TBLPPTHAKMILIKBORANGK.ID_BORANGK"
							+ " AND TBLPPTHAKMILIK.ID_HAKMILIK = TBLPPTHAKMILIKBORANGK.ID_HAKMILIK(+) AND TBLPPTPERMOHONAN.ID_PERMOHONAN = TBLPPTWARTA.ID_PERMOHONAN(+)"
							+ " AND TBLPFDFAIL.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPPTHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) AND TBLPPTPERMOHONAN.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+)"
							+ " AND TBLPPTHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) AND TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = RUJLUAS_ASAL.ID_LUAS(+)"
							+ " AND TBLPPTHAKMILIK.ID_UNITLUASAMBIL= RUJLUAS_AMBIL.ID_LUAS(+) AND TBLPPTHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+)"
							+ " AND TBLPFDFAIL.ID_FAIL = '"+ idFail + "' AND TBLPPTWARTA.ID_WARTA = '" + idPPTWarta + "'"
							+ " AND (TRANSLATE(TRIM(TBLPPTHAKMILIK.NO_HAKMILIK),'_0123456789','_')) IS NULL AND TBLPFDFAIL.NO_FAIL IS NOT NULL  AND (TBLPPTHAKMILIK.FLAG_PENARIKAN_KESELURUHAN IS NULL AND TBLPPTHAKMILIK.FLAG_PEMBATALAN_KESELURUHAN IS NULL)"
							+ " AND TBLPPTHAKMILIK.ID_ENDORSAN_D IS NULL"
							+ " ORDER BY TBLPPTHAKMILIK.NO_SUBJAKET,TBLPPTHAKMILIK.NO_LOT ASC";
					myLog.info("sql select HAKMILIK :::: "+sql);
					ResultSet rsHakmilik = stmt.executeQuery(sql);

					List listHakmilik = Collections.synchronizedList(new ArrayList());
					Map hHakmilik = null;
					while (rsHakmilik.next()) {
						hHakmilik = Collections.synchronizedMap(new HashMap());
						hHakmilik.put("NO_FAIL", NO_FAIL == null ? "" : NO_FAIL);
						hHakmilik.put("ID_PPTHAKMILIK", rsHakmilik.getString("ID_PPTHAKMILIK") != null ? rsHakmilik.getString("ID_PPTHAKMILIK") : "");
						hHakmilik.put("ID_HAKMILIK", rsHakmilik.getString("ID_HAKMILIK") != null ? rsHakmilik.getString("ID_HAKMILIK") : "");
						hHakmilik.put("KOD_NEGERI", rsHakmilik.getString("KOD_NEGERI") != null ? rsHakmilik.getString("KOD_NEGERI") : "");
						hHakmilik.put("KOD_DAERAH", "00");
						hHakmilik.put("KOD_MUKIM", rsHakmilik.getString("KOD_MUKIM") != null ? rsHakmilik.getString("KOD_MUKIM") : "");
						hHakmilik.put("KOD_JENIS_HAKMILIK", rsHakmilik.getString("KOD_JENIS_HAKMILIK") != null ? rsHakmilik.getString("KOD_JENIS_HAKMILIK") : "");
						hHakmilik.put("NO_HAKMILIK", rsHakmilik.getString("NO_HAKMILIK") != null ? rsHakmilik.getString("NO_HAKMILIK") : "");
						String kodLot = rsHakmilik.getString("KOD_LOT");
						if (kodLot != null && !"".equals(kodLot)) {
							hHakmilik.put("KOD_LOT", kodLot);
							hHakmilik.put("NO_LOT", rsHakmilik.getString("NO_PT") != null ? rsHakmilik.getString("NO_PT") : "");
						} else {
							hHakmilik.put("KOD_LOT", "LOT");
							hHakmilik.put("NO_LOT", rsHakmilik.getString("NO_LOT") != null ? rsHakmilik.getString("NO_LOT") : "");
						}						
						hHakmilik.put("NO_SEKSYEN", "");
						hHakmilik.put("KOD_LUAS_ASAL", rsHakmilik.getString("KOD_LUAS_ASAL") != null ? rsHakmilik.getString("KOD_LUAS_ASAL") : "");
						hHakmilik.put("LUAS_ASAL", rsHakmilik.getString("LUAS_ASAL") != null ? rsHakmilik.getString("LUAS_ASAL") : "");
						hHakmilik.put("KOD_LUAS_AMBIL", rsHakmilik.getString("KOD_LUAS_AMBIL") != null ? rsHakmilik.getString("KOD_LUAS_AMBIL") : "");
						hHakmilik.put("LUAS_AMBIL", rsHakmilik.getString("LUAS_AMBIL") != null ? rsHakmilik.getString("LUAS_AMBIL") : "");
						hHakmilik.put("NO_WARTA", rsHakmilik.getString("NO_WARTA") != null ? rsHakmilik.getString("NO_WARTA") : "");
						hHakmilik.put("TARIKH_WARTA", rsHakmilik.getString("TARIKH_WARTA") != null ? rsHakmilik.getString("TARIKH_WARTA") : "");
						hHakmilik.put("TARIKH_BORANGK", rsHakmilik.getString("TARIKH_BORANGK") != null ? rsHakmilik.getString("TARIKH_BORANGK") : "");					
						hHakmilik.put("STATUS_AMBIL", rsHakmilik.getString("STATUS_AMBIL") != null ? rsHakmilik.getString("STATUS_AMBIL") : "");
						hHakmilik.put("NO_SUBJAKET", rsHakmilik.getString("NO_SUBJAKET") != null ? rsHakmilik.getString("NO_SUBJAKET") : "");
						listHakmilik.add(hHakmilik);
					}

					for (int x = 0; x < listHakmilik.size(); x++) {
						Map mapHakmilik = (Map) listHakmilik.get(x);
						if (mapHakmilik != null) {
							myLog.info("mapHakmilik object :: " + mapHakmilik);
							insertHakmilikPermohonan(idPermohonan, idPengguna, mapHakmilik, db);
						}
					}

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return idPermohonanIntegrasiString;
	}
	
	public String generatePermohonanK(String idFail, String idPPTHakmilik, String flagUrusan, String idPermohonanIntegrasi, String idPengguna, Db db) {
		String sql = "";
		String idPermohonanIntegrasiString = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "DELETE FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN IN ('" + idPermohonanIntegrasi + "')";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN IN ('" + idPermohonanIntegrasi + "')";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPTPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "' AND FLAG_URUSAN = '" + flagUrusan + "'";
			stmt.executeUpdate(sql);

			sql = "SELECT DISTINCT A.ID_FAIL, A.NO_FAIL, UPPER (B.TUJUAN) AS NAMA_PROJEK, UPPER (M.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN,"
					+ " W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA"
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTWARTA W, TBLRUJKEMENTERIAN M"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+)"
					+ " AND A.ID_KEMENTERIAN = M.ID_KEMENTERIAN AND A.ID_FAIL = '" + idFail + "'"
					+ " AND W.TARIKH_WARTA = (SELECT MAX (TARIKH_WARTA) FROM TBLPPTWARTA WW, TBLPPTPERMOHONAN P, TBLPFDFAIL F"
					+ " WHERE WW.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = '"
					+ idFail + "' AND ROWNUM < 2)";
			ResultSet rsPermohonan = stmt.executeQuery(sql);

			List listPermohonan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			if (rsPermohonan.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("NO_FAIL", rsPermohonan.getString("NO_FAIL") == null ? "" : rsPermohonan.getString("NO_FAIL"));
				h.put("NAMA_KEMENTERIAN", rsPermohonan.getString("NAMA_KEMENTERIAN") == null ? "" : rsPermohonan.getString("NAMA_KEMENTERIAN"));
				h.put("NAMA_PROJEK", rsPermohonan.getString("NAMA_PROJEK") == null ? "" : rsPermohonan.getString("NAMA_PROJEK"));
				h.put("TARIKH_WARTA", rsPermohonan.getString("TARIKH_WARTA") == null ? "" : rsPermohonan.getString("TARIKH_WARTA"));
				h.put("NO_WARTA", rsPermohonan.getString("NO_WARTA") == null ? "" : rsPermohonan.getString("NO_WARTA"));
				listPermohonan.add(h);
			}

			for (int i = 0; i < listPermohonan.size(); i++) {
				Map mapPermohonan = (Map) listPermohonan.get(i);
				if (mapPermohonan != null) {
					String NO_FAIL = (String) mapPermohonan.get("NO_FAIL");
					String NAMA_KEMENTERIAN = (String) mapPermohonan.get("NAMA_KEMENTERIAN");
					String NAMA_PROJEK = (String) mapPermohonan.get("NAMA_PROJEK");
					String TARIKH_WARTA = (String) mapPermohonan.get("TARIKH_WARTA");
					String NO_WARTA = (String) mapPermohonan.get("NO_WARTA");
					
					r = new SQLRenderer();
					long idPermohonan = DB.getNextID(db, "INT_PPTPERMOHONAN_SEQ");
					idPermohonanIntegrasiString = String.valueOf(idPermohonan);
					r.add("ID_PERMOHONAN", idPermohonan);
					r.add("NO_FAIL", NO_FAIL);
					r.add("NO_JILID", "");
					r.add("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					r.add("NAMA_PROJEK", NAMA_PROJEK);
					r.add("NO_WARTA", NO_WARTA);
					r.add("ID_PENGGUNA", idPengguna);
					if (TARIKH_WARTA != null)
						r.add("TARIKH_WARTA", r.unquote("to_date('" + TARIKH_WARTA + "','dd/MM/yyyy')"));
					r.add("FLAG_URUSAN", flagUrusan);
					sql = r.getSQLInsert("INT_PPTPERMOHONAN");
					stmt.executeUpdate(sql);

					sql = "SELECT DISTINCT TBLRUJNEGERI.KOD_NEGERI || '00' || TBLRUJMUKIM.KOD_MUKIM || TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK || TRIM(TO_CHAR(TBLPPTHAKMILIK.NO_HAKMILIK,'00000000')) AS ID_HAKMILIK,"
							+ " TBLRUJNEGERI.KOD_NEGERI, TBLRUJDAERAH.KOD_DAERAH, TBLRUJMUKIM.KOD_MUKIM, TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK, TO_CHAR (TBLPPTBORANGK.TARIKH_BORANGK, 'DD/MM/YYYY') AS TARIKH_BORANGK, TO_CHAR (TBLPPTWARTA.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, TBLPPTWARTA.NO_WARTA,"
							+ " TBLPPTHAKMILIK.NO_HAKMILIK, UPPER(TBLRUJLOT.KETERANGAN) AS KOD_LOT, TBLPPTHAKMILIK.NO_PT, TBLPPTHAKMILIK.NO_LOT,"
							+ " (CASE WHEN TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = '1' THEN 'Hektar' WHEN TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = '2' THEN 'Meter Persegi'  ELSE '' END) AS KOD_LUAS_ASAL,"
							+ " TRIM(TO_CHAR(TBLPPTHAKMILIK.LUAS_LOT,'9999999999999990.9999')) AS LUAS_ASAL,"
							+ " (CASE WHEN TBLPPTHAKMILIK.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'Hektar' WHEN TBLPPTHAKMILIK.ID_UNITLUASAMBIL_CONVERT = '2' THEN 'Meter Persegi' ELSE '' END) AS KOD_LUAS_AMBIL,"
							+ " TRIM(TO_CHAR(TBLPPTHAKMILIK.LUAS_AMBIL,'9999999999999990.9999')) AS LUAS_AMBIL, TBLPPTHAKMILIK.NO_SUBJAKET, CASE WHEN LUAS_LOT <> LUAS_AMBIL THEN 'SEBAHAGIAN' ELSE 'KESELURUHAN' END AS STATUS_AMBIL, TBLPPTHAKMILIK.ID_HAKMILIK AS ID_PPTHAKMILIK"
							+ " FROM TBLPFDFAIL, TBLPPTPERMOHONAN, TBLPPTHAKMILIK, TBLRUJNEGERI, TBLRUJMUKIM, TBLRUJDAERAH, TBLRUJJENISHAKMILIK, TBLRUJLUAS RUJLUAS_ASAL, TBLRUJLUAS RUJLUAS_AMBIL, TBLRUJLOT, TBLPPTBORANGK, TBLPPTWARTA, TBLPPTHAKMILIKBORANGK"
							+ " WHERE TBLPFDFAIL.ID_FAIL = TBLPPTPERMOHONAN.ID_FAIL AND TBLPPTPERMOHONAN.ID_PERMOHONAN = TBLPPTHAKMILIK.ID_PERMOHONAN AND TBLPPTBORANGK.ID_BORANGK(+) = TBLPPTHAKMILIKBORANGK.ID_BORANGK"
							+ " AND TBLPPTHAKMILIK.ID_HAKMILIK = TBLPPTHAKMILIKBORANGK.ID_HAKMILIK(+) AND TBLPPTPERMOHONAN.ID_PERMOHONAN = TBLPPTWARTA.ID_PERMOHONAN(+)"
							+ " AND TBLPFDFAIL.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPPTHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) AND TBLPPTPERMOHONAN.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+)"
							+ " AND TBLPPTHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) AND TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = RUJLUAS_ASAL.ID_LUAS(+)"
							+ " AND TBLPPTHAKMILIK.ID_UNITLUASAMBIL= RUJLUAS_AMBIL.ID_LUAS(+) AND TBLPPTHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+)"
							+ " AND TBLPFDFAIL.ID_FAIL = '"+ idFail + "' AND TBLPPTHAKMILIK.ID_HAKMILIK = '" + idPPTHakmilik + "'"
							+ " AND (TRANSLATE(TRIM(TBLPPTHAKMILIK.NO_HAKMILIK),'_0123456789','_')) IS NULL AND TBLPFDFAIL.NO_FAIL IS NOT NULL  AND (TBLPPTHAKMILIK.FLAG_PENARIKAN_KESELURUHAN IS NULL AND TBLPPTHAKMILIK.FLAG_PEMBATALAN_KESELURUHAN IS NULL)"
							+ " AND TBLPPTHAKMILIK.ID_ENDORSAN_K IS NULL"
							+ " ORDER BY TBLPPTHAKMILIK.NO_SUBJAKET,TBLPPTHAKMILIK.NO_LOT ASC";
					ResultSet rsHakmilik = stmt.executeQuery(sql);
					
					System.out.println("jana permohonan borang k" + sql);

					List listHakmilik = Collections.synchronizedList(new ArrayList());
					Map hHakmilik = null;
					while (rsHakmilik.next()) {
						hHakmilik = Collections.synchronizedMap(new HashMap());
						hHakmilik.put("NO_FAIL", NO_FAIL == null ? "" : NO_FAIL);
						hHakmilik.put("ID_PPTHAKMILIK", rsHakmilik.getString("ID_PPTHAKMILIK") != null ? rsHakmilik.getString("ID_PPTHAKMILIK") : "");
						hHakmilik.put("ID_HAKMILIK", rsHakmilik.getString("ID_HAKMILIK") != null ? rsHakmilik.getString("ID_HAKMILIK") : "");
						hHakmilik.put("KOD_NEGERI", rsHakmilik.getString("KOD_NEGERI") != null ? rsHakmilik.getString("KOD_NEGERI") : "");
						hHakmilik.put("KOD_DAERAH", "00");
						hHakmilik.put("KOD_MUKIM", rsHakmilik.getString("KOD_MUKIM") != null ? rsHakmilik.getString("KOD_MUKIM") : "");
						hHakmilik.put("KOD_JENIS_HAKMILIK", rsHakmilik.getString("KOD_JENIS_HAKMILIK") != null ? rsHakmilik.getString("KOD_JENIS_HAKMILIK") : "");
						hHakmilik.put("NO_HAKMILIK", rsHakmilik.getString("NO_HAKMILIK") != null ? rsHakmilik.getString("NO_HAKMILIK") : "");
						String kodLot = rsHakmilik.getString("KOD_LOT");
						if (kodLot != null && !"".equals(kodLot)) {
							hHakmilik.put("KOD_LOT", kodLot);
							hHakmilik.put("NO_LOT", rsHakmilik.getString("NO_PT") != null ? rsHakmilik.getString("NO_PT") : "");
						} else {
							hHakmilik.put("KOD_LOT", "LOT");
							hHakmilik.put("NO_LOT", rsHakmilik.getString("NO_LOT") != null ? rsHakmilik.getString("NO_LOT") : "");
						}	
						hHakmilik.put("NO_SEKSYEN", "");
						hHakmilik.put("KOD_LUAS_ASAL", rsHakmilik.getString("KOD_LUAS_ASAL") != null ? rsHakmilik.getString("KOD_LUAS_ASAL") : "");
						hHakmilik.put("LUAS_ASAL", rsHakmilik.getString("LUAS_ASAL") != null ? rsHakmilik.getString("LUAS_ASAL") : "");
						hHakmilik.put("KOD_LUAS_AMBIL", rsHakmilik.getString("KOD_LUAS_AMBIL") != null ? rsHakmilik.getString("KOD_LUAS_AMBIL") : "");
						hHakmilik.put("LUAS_AMBIL", rsHakmilik.getString("LUAS_AMBIL") != null ? rsHakmilik.getString("LUAS_AMBIL") : "");
						hHakmilik.put("NO_WARTA", rsHakmilik.getString("NO_WARTA") != null ? rsHakmilik.getString("NO_WARTA") : "");
						hHakmilik.put("TARIKH_WARTA", rsHakmilik.getString("TARIKH_WARTA") != null ? rsHakmilik.getString("TARIKH_WARTA") : "");
						hHakmilik.put("TARIKH_BORANGK", rsHakmilik.getString("TARIKH_BORANGK") != null ? rsHakmilik.getString("TARIKH_BORANGK") : "");
						hHakmilik.put("STATUS_AMBIL", rsHakmilik.getString("STATUS_AMBIL") != null ? rsHakmilik.getString("STATUS_AMBIL") : "");
						hHakmilik.put("NO_SUBJAKET", rsHakmilik.getString("NO_SUBJAKET") != null ? rsHakmilik.getString("NO_SUBJAKET") : "");
						hHakmilik.put("ID_PPTHAKMILIK", rsHakmilik.getString("ID_PPTHAKMILIK") != null ? rsHakmilik.getString("ID_PPTHAKMILIK") : "");
						listHakmilik.add(hHakmilik);
					}

					for (int x = 0; x < listHakmilik.size(); x++) {
						Map mapHakmilik = (Map) listHakmilik.get(x);
						if (mapHakmilik != null) {
							insertHakmilikPermohonan(idPermohonan, idPengguna, mapHakmilik, db);
						}
					}

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return idPermohonanIntegrasiString;
	}

	private void insertHakmilikPermohonan(long idPermohonan, String idPengguna, Map mapHakmilik, Db db) {
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			long idHakmilikPermohonan = DB.getNextID(db, "INT_PPTHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);
			r.add("ID_PERMOHONAN", idPermohonan);
			String idHakmilik = (String) mapHakmilik.get("ID_HAKMILIK") != null ? (String) mapHakmilik.get("ID_HAKMILIK") : "";
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("KOD_NEGERI", (String) mapHakmilik.get("KOD_NEGERI") != null ? (String) mapHakmilik.get("KOD_NEGERI") : "");
			r.add("KOD_DAERAH", (String) mapHakmilik.get("KOD_DAERAH") != null ? (String) mapHakmilik.get("KOD_DAERAH") : "");
			r.add("KOD_MUKIM", (String) mapHakmilik.get("KOD_MUKIM") != null ? (String) mapHakmilik.get("KOD_MUKIM") : "");
			r.add("KOD_HAKMILIK", (String) mapHakmilik.get("KOD_JENIS_HAKMILIK") != null ? (String) mapHakmilik.get("KOD_JENIS_HAKMILIK") : "");
			r.add("NO_HAKMILIK", (String) mapHakmilik.get("NO_HAKMILIK") != null ? (String) mapHakmilik.get("NO_HAKMILIK") : "");
			r.add("KOD_LOT", (String) mapHakmilik.get("KOD_LOT") != null ? (String) mapHakmilik.get("KOD_LOT") : "");
			r.add("NO_LOT", (String) mapHakmilik.get("NO_LOT") != null ? (String) mapHakmilik.get("NO_LOT") : "");
			r.add("LUAS_AMBIL", (String) mapHakmilik.get("LUAS_AMBIL") != null ? (String) mapHakmilik.get("LUAS_AMBIL") : "");
			r.add("NO_SEKSYEN", "");
			r.add("KOD_LUAS_ASAL", (String) mapHakmilik.get("KOD_LUAS_ASAL") != null ? (String) mapHakmilik.get("KOD_LUAS_ASAL") : "");
			r.add("LUAS_ASAL", (String) mapHakmilik.get("LUAS_ASAL") != null ? (String) mapHakmilik.get("LUAS_ASAL") : "");
			r.add("KOD_LUAS_AMBIL", (String) mapHakmilik.get("KOD_LUAS_AMBIL") != null ? (String) mapHakmilik.get("KOD_LUAS_AMBIL") : "");
			r.add("NO_WARTA", (String) mapHakmilik.get("NO_WARTA") != null ? (String) mapHakmilik.get("NO_WARTA") : "");
			r.add("ID_PENGGUNA", idPengguna);
			
			String TARIKH_WARTA  = (String) mapHakmilik.get("TARIKH_WARTA");
			if (!TARIKH_WARTA.equals(""))
				r.add("TARIKH_WARTA", r.unquote("to_date('" + TARIKH_WARTA + "','dd/MM/yyyy')"));
			String TARIKH_BORANGK  = (String) mapHakmilik.get("TARIKH_BORANGK");
			if (!TARIKH_BORANGK.equals(""))
				r.add("TARIKH_BORANGK", r.unquote("to_date('" + TARIKH_BORANGK + "','dd/MM/yyyy')"));
			r.add("STATUS_AMBIL", (String) mapHakmilik.get("STATUS_AMBIL") != null ? (String) mapHakmilik.get("STATUS_AMBIL") : "");
			r.add("NO_SUBJAKET", (String) mapHakmilik.get("NO_SUBJAKET") != null ? (String) mapHakmilik.get("NO_SUBJAKET") : "");
			r.add("SEBAB_PENARIKANBALIK", "");
			
			String catatanHakmilik = getCatatanHakmilik(idPengguna, idHakmilik);
			r.add("CATATAN_HAKMILIK", catatanHakmilik);
			r.add("ID_PPTHAKMILIK", (String) mapHakmilik.get("ID_PPTHAKMILIK") != null ? (String) mapHakmilik.get("ID_PPTHAKMILIK") : "");
			
			sql = r.getSQLInsert("INT_PPTHAKMILIKPERMOHONAN");
			myLog.info("INT_PPTHAKMILIKPERMOHONAN ::::::::: "+sql);
			stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String getCatatanHakmilik(String idPengguna, String idHakmilik) {
		String catatan = "";
		Hakmilik hakmilik = null;
		try {
			hakmilik = RESTInvoker.getMaklumatHakmilik(idPengguna, idHakmilik, "");
			if (hakmilik == null) {
				catatan = "HAKMILIK TIDAK WUJUD DI SISTEM E-TANAH";
			} else {
				if ("BATAL".equalsIgnoreCase(hakmilik.getStatusHakmilik())) {
					catatan = "STATUS HAKMILIK ADALAH BATAL DI SISTEM E-TANAH";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return catatan;
	}

	public Hashtable<String,String> getMaklumatPermohonan(String idPermohonanIntegrasi, Db db) {
		String sql = "";
		Hashtable<String,String> maklumatPermohonan = null;

		try {
			Statement stmt = db.getStatement();
			sql = "SELECT F.NO_FAIL,P.ID_PERMOHONAN,TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY') TARIKH_PERMOHONAN "+
			" ,RK.ID_KEMENTERIAN,RK.KOD_KEMENTERIAN,RK.NAMA_KEMENTERIAN,P.TUJUAN NAMA_PROJEK,P.TUJUAN_BI NAMA_PROJEKBI"+
			" ,PI.NO_PERMOHONAN,PI.TARIKH_HANTAR "+
			" ,PI.TARIKH_TERIMA,PI.NO_JILID "+
			" ,PI.FLAG_AKTIF,PI.FLAG_ENDORSAN,PI.FLAG_HANTAR,PI.FLAG_TRANSAKSI,PI.FLAG_URUSAN,PI.KETERANGAN_TRANSAKSI "+
			" ,RA.NAMA_AGENSI, RA.ID_AGENSI,RA.KOD_AGENSI "+
			" ,RD.KOD_DAERAH,RD.NAMA_DAERAH "+
		    ",RK.ALAMAT1,RK.ALAMAT2,RK.ALAMAT3,RK.POSKOD,RN.KOD_NEGERI,RN.NAMA_NEGERI" + 
			" ,(" + 
			" CASE WHEN F.ID_SUBURUSAN = 51" + 
			"  THEN 'PENGAMBILAN TANAH DI BAWAH SEKSYEN 4'" + 
			"  ELSE" + 
			"  'PENGAMBILAN TANAH DI BAWAH SEKSYEN 8'" + 
			"END ) JENIS_PENGAMBILAN" + 
			",(CASE WHEN  P.FLAG_JENISPERMOHONAN = '1' THEN 'JAJARAN' " + 
			"	WHEN  P.FLAG_JENISPERMOHONAN = '2' THEN 'TAPAK' ELSE '' " + 
			"END) JENIS_PROJEK_PENGAMBILAN "+
			" FROM TBLPFDFAIL F,TBLPPTPERMOHONAN P "+
			" ,TBLINTANAHPERMOHONAN PI "+
			" ,TBLRUJKEMENTERIAN RK,TBLRUJAGENSI RA "+
			" ,TBLRUJNEGERI RN,TBLRUJNEGERI RNA,TBLRUJDAERAH RD "+
			" WHERE  "+
			" F.ID_FAIL = P.ID_FAIL "+
			" AND F.ID_KEMENTERIAN = RK.ID_KEMENTERIAN "+
			" AND RK.ID_NEGERI = RNA.ID_NEGERI "+
			" AND F.ID_NEGERI = RN.ID_NEGERI "+
			" AND P.ID_PERMOHONAN = PI.ID_PERMOHONAN(+) "+
			" AND P.ID_AGENSI = RA.ID_AGENSI(+) "+
			" AND P.ID_DAERAH = RD.ID_DAERAH(+) "+
			" AND P.ID_PERMOHONAN ='" + idPermohonanIntegrasi + "'"+
			"";
//			sql = "SELECT * FROM TBLINTANAHPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			myLog.info("sql:"+sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				maklumatPermohonan = new Hashtable<String,String>();
				maklumatPermohonan.put("alamat1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
				maklumatPermohonan.put("alamat2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
				maklumatPermohonan.put("alamat3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
				maklumatPermohonan.put("poskod", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));

				maklumatPermohonan.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				maklumatPermohonan.put("kodNegeri", rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				maklumatPermohonan.put("namaNegeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				
				maklumatPermohonan.put("kodDaerah", rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH"));
				maklumatPermohonan.put("namaDaerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				
				maklumatPermohonan.put("idAgensi", rs.getString("ID_AGENSI") == null ? "" : rs.getString("ID_AGENSI"));
				maklumatPermohonan.put("kodAgensi", rs.getString("KOD_AGENSI") == null ? "" : rs.getString("KOD_AGENSI"));
				maklumatPermohonan.put("namaAgensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI"));

				maklumatPermohonan.put("idKementerian", rs.getString("ID_KEMENTERIAN") == null ? "" : rs.getString("ID_KEMENTERIAN"));
				maklumatPermohonan.put("kodKementerian", rs.getString("KOD_KEMENTERIAN") == null ? "" : rs.getString("KOD_KEMENTERIAN"));
				maklumatPermohonan.put("namaKementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
				
				maklumatPermohonan.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				maklumatPermohonan.put("noJilid", rs.getString("NO_JILID") == null ? "" : rs.getString("NO_JILID"));
				maklumatPermohonan.put("namaProjek", rs.getString("NAMA_PROJEK") == null ? "" : rs.getString("NAMA_PROJEK"));
				maklumatPermohonan.put("namaProjekBI", rs.getString("NAMA_PROJEKBI") == null ? "" : rs.getString("NAMA_PROJEKBI"));

				//maklumatPermohonan.put("tarikhPermohonan", rs.getDate("TARIKH_PERMOHONAN") == null ? "" : sdf.format(rs.getDate("TARIKH_PERMOHONAN")));
//				maklumatPermohonan.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
//				maklumatPermohonan.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				maklumatPermohonan.put("tarikhHantar", rs.getDate("TARIKH_HANTAR") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR")));
				maklumatPermohonan.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				maklumatPermohonan.put("flagEndorsan", rs.getString("FLAG_ENDORSAN") == null ? "" : rs.getString("FLAG_ENDORSAN"));
				maklumatPermohonan.put("flagHantar", rs.getString("FLAG_HANTAR") == null ? "T" : rs.getString("FLAG_HANTAR"));
				maklumatPermohonan.put("flagUrusan", rs.getString("FLAG_URUSAN") == null ? "" : rs.getString("FLAG_URUSAN"));
				maklumatPermohonan.put("flagAktif", rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
				maklumatPermohonan.put("flagTransaksi", rs.getString("FLAG_TRANSAKSI") == null ? "" : rs.getString("FLAG_TRANSAKSI"));
				maklumatPermohonan.put("keteranganTransaksi", rs.getString("KETERANGAN_TRANSAKSI") == null ? "" : rs.getString("KETERANGAN_TRANSAKSI"));
				
				maklumatPermohonan.put("jenisPengambilan", rs.getString("JENIS_PENGAMBILAN") == null ? "" : rs.getString("JENIS_PENGAMBILAN"));
				maklumatPermohonan.put("jenisProjek", rs.getString("JENIS_PROJEK_PENGAMBILAN") == null ? "" : rs.getString("JENIS_PROJEK_PENGAMBILAN"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return maklumatPermohonan;
		
	}

	public Vector<Hashtable<String,String>> getSenaraiHakmilik(String idPermohonanIntegrasi, Db db) {
		String sql = "";
		Vector listHakmilik = null;
		Hashtable<String,String> h;

		try {
			listHakmilik = new Vector();

			Statement stmt = db.getStatement();
			sql = " SELECT DISTINCT "+
			" RN.KOD_NEGERI,RD.KOD_DAERAH,RM.KOD_MUKIM,'OO' NO_SEKSYEN "+
			" ,RJ.KOD_JENIS_HAKMILIK KOD_HAKMILIK,H.NO_HAKMILIK" +
			" ,RL.KOD_LOT,NVL(H.NO_LOT,H.NO_PT) NO_LOT "+
			" ,LO.KOD_LUAS KOD_LUAS_ASAL,H.LUAS_LOT LUAS_ASAL,LT.KOD_LUAS KOD_LUAS_AMBIL,H.LUAS_AMBIL "+
			" ,H.CATATAN CATATAN_HAKMILIK "+
			//--H.*
			" ,HI.ID_PERMOHONANMILIK ID_HAKMILIKPERMOHONAN,H.ID_HAKMILIK,F.NO_FAIL"+
			" FROM TBLPPTHAKMILIK H,TBLINTANAHPERMOHONANMILIK HI "+
			" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
			" ,TBLRUJLUAS LO,TBLRUJLUAS LT "+
			" ,TBLRUJJENISHAKMILIK RJ "+
			" ,TBLRUJLOT RL,TBLPFDFAIL F,TBLPPTPERMOHONAN P "+
			" WHERE H.ID_NEGERI = RN.ID_NEGERI "+
			" AND H.ID_DAERAH = RD.ID_DAERAH "+
			" AND H.ID_DAERAH = RM.ID_MUKIM "
			+ " AND F.ID_FAIL = P.ID_FAIL "
			+ " AND P.ID_PERMOHONAN = H.ID_PERMOHONAN "+
			" AND H.ID_HAKMILIK = HI.ID_HAKMILIK(+) "+
			" AND H.ID_UNITLUASLOT = LO.ID_LUAS(+) "+
			" AND H.ID_UNITLUASAMBIL = LT.ID_LUAS(+) "+
			" AND H.ID_LOT = RL.ID_LOT(+) "+
			" AND H.ID_JENISHAKMILIK = RJ.ID_JENISHAKMILIK(+) "+
			" AND H.ID_PERMOHONAN ='" + idPermohonanIntegrasi + "'"+
			"";
			
//			sql = " SELECT H.*,HI.ID_PERMOHONANMILIK ID_HAKMILIKPERMOHONAN FROM TBLPPTHAKMILIK H,TBLINTANAHPERMOHONANMILIK HI "+
//				" WHERE H.ID_HAKMILIK = HI.ID_HAKMILIK(+) "+
//				" AND H.ID_PERMOHONAN =  '" + idPermohonanIntegrasi + "'"+
//				"";
			//sql = "SELECT * FROM TBLINTANAHPERMOHONANMILIK WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			myLog.info("getSenaraiHakmilik:sql="+sql);
			sql = sql + " ORDER BY H.ID_HAKMILIK ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilikPermohonan", rs.getString("ID_HAKMILIKPERMOHONAN") == null ? "" : rs.getString("ID_HAKMILIKPERMOHONAN"));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("kodNegeri", rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				h.put("kodDaerah", rs.getString("KOD_DAERAH") == null ? "" : rs.getString("KOD_DAERAH"));
				h.put("kodMukim", rs.getString("KOD_MUKIM") == null ? "" : rs.getString("KOD_MUKIM"));
				h.put("kodHakmilik", rs.getString("KOD_HAKMILIK") == null ? "" : rs.getString("KOD_HAKMILIK"));
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("kodLot", rs.getString("KOD_LOT") == null ? "" : rs.getString("KOD_LOT"));
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("noSeksyen", rs.getString("NO_SEKSYEN") == null ? "" : rs.getString("NO_SEKSYEN"));
				h.put("kodLuasAsal", rs.getString("KOD_LUAS_ASAL") == null ? "" : rs.getString("KOD_LUAS_ASAL"));
				h.put("luasAsal", rs.getString("LUAS_ASAL") == null ? "" : rs.getString("LUAS_ASAL"));
				h.put("kodLuasAmbil", rs.getString("KOD_LUAS_AMBIL") == null ? "" : rs.getString("KOD_LUAS_AMBIL"));
				h.put("luasAmbil", rs.getString("LUAS_AMBIL") == null ? "" : rs.getString("LUAS_AMBIL"));
				
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				//h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				//h.put("tarikhWarta", rs.getString("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				//h.put("tarikhBorangK", rs.getString("TARIKH_BORANGK") == null ? "" : sdf.format(rs.getDate("TARIKH_BORANGK")));
				//h.put("statusAmbil", rs.getString("STATUS_AMBIL") == null ? "" : rs.getString("STATUS_AMBIL"));
				//h.put("noSubJaket", rs.getString("NO_SUBJAKET") == null ? "" : rs.getString("NO_SUBJAKET"));
				//h.put("sebabPenarikanBalik", rs.getString("SEBAB_PENARIKANBALIK") == null ? "" : rs.getString("SEBAB_PENARIKANBALIK"));
				//h.put("catatanHakmilik", rs.getString("CATATAN_HAKMILIK") == null ? "" : rs.getString("CATATAN_HAKMILIK"));
				//h.put("idPPTHakmilik", rs.getString("ID_PPTHAKMILIK") == null ? "" : rs.getString("ID_PPTHAKMILIK"));
				//h.put("idHakmilikSambungan", rs.getString("ID_HAKMILIK_SAMBUNGAN") == null ? "" : rs.getString("ID_HAKMILIK_SAMBUNGAN"));
				//h.put("kodLuasSambungan", rs.getString("KOD_LUAS_SAMBUNGAN") == null ? "" : rs.getString("KOD_LUAS_SAMBUNGAN"));
				//h.put("luasSambungan", rs.getString("LUAS_SAMBUNGAN") == null ? "" : rs.getString("LUAS_SAMBUNGAN"));				
				//h.put("noPerserahan", rs.getString("NO_PERSERAHAN") == null ? "" : rs.getString("NO_PERSERAHAN"));
				//h.put("tarikhEndorsan", rs.getString("TARIKH_ENDORSAN") == null ? "" : sdf.format(rs.getDate("TARIKH_ENDORSAN")));
				//h.put("masaEndorsan", rs.getString("MASA_ENDORSAN") == null ? "" : rs.getString("MASA_ENDORSAN"));
				//h.put("statusEndorsan", rs.getString("STATUS_ENDORSAN") == null ? "" : rs.getString("STATUS_ENDORSAN"));
				listHakmilik.addElement(h);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listHakmilik;
	}

	public String generatePermohonanPD(String idFail, String idPermohonanIntegrasi, String idPPTPenarikanBalik,String flagUrusan, String idPengguna, Db db) {
		String sql = "";

		String idPermohonanIntegrasiString = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "DELETE FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN IN ('" + idPermohonanIntegrasi + "')";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_PERMOHONAN IN ('" + idPermohonanIntegrasi + "')";
			stmt.executeUpdate(sql);
			sql = "DELETE FROM INT_PPTPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "' AND FLAG_URUSAN = '" + flagUrusan + "'";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql);

			sql = "SELECT DISTINCT A.ID_FAIL, A.NO_FAIL, UPPER (B.TUJUAN) AS NAMA_PROJEK, UPPER (M.NAMA_KEMENTERIAN) AS NAMA_KEMENTERIAN,"
					+ " W.NO_WARTA, TO_CHAR (W.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, PB.ID_PENARIKANBALIK"
					+ " FROM TBLPFDFAIL A, TBLPPTPERMOHONAN B, TBLPPTWARTA W, TBLRUJKEMENTERIAN M, TBLPPTPENARIKANBALIK PB"
					+ " WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = W.ID_PERMOHONAN(+) AND B.ID_PERMOHONAN = PB.ID_PERMOHONAN(+)"
					+ " AND A.ID_KEMENTERIAN = M.ID_KEMENTERIAN AND A.ID_FAIL = '" + idFail + "'"
					+ " AND PB.ID_PENARIKANBALIK = '" + idPPTPenarikanBalik + "'";
			ResultSet rsPermohonan = stmt.executeQuery(sql);

			List listPermohonan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			if (rsPermohonan.next()) {
				h = Collections.synchronizedMap(new HashMap());
				h.put("NO_FAIL", rsPermohonan.getString("NO_FAIL") == null ? "" : rsPermohonan.getString("NO_FAIL"));
				h.put("NAMA_KEMENTERIAN", rsPermohonan.getString("NAMA_KEMENTERIAN") == null ? "" : rsPermohonan.getString("NAMA_KEMENTERIAN"));
				h.put("NAMA_PROJEK", rsPermohonan.getString("NAMA_PROJEK") == null ? "" : rsPermohonan.getString("NAMA_PROJEK"));
				h.put("TARIKH_WARTA", rsPermohonan.getString("TARIKH_WARTA") == null ? "" : rsPermohonan.getString("TARIKH_WARTA"));
				h.put("NO_WARTA", rsPermohonan.getString("NO_WARTA") == null ? "" : rsPermohonan.getString("NO_WARTA"));
				h.put("ID_PENARIKANBALIK", rsPermohonan.getString("ID_PENARIKANBALIK") == null ? "" : rsPermohonan.getString("ID_PENARIKANBALIK"));
				listPermohonan.add(h);
			}

			for (int i = 0; i < listPermohonan.size(); i++) {
				Map mapPermohonan = (Map) listPermohonan.get(i);
				if (mapPermohonan != null) {
					String NO_FAIL = (String) mapPermohonan.get("NO_FAIL");
					String NAMA_KEMENTERIAN = (String) mapPermohonan.get("NAMA_KEMENTERIAN");
					String NAMA_PROJEK = (String) mapPermohonan.get("NAMA_PROJEK");
					String TARIKH_WARTA = (String) mapPermohonan.get("TARIKH_WARTA");
					String NO_WARTA = (String) mapPermohonan.get("NO_WARTA");
					String ID_PENARIKANBALIK = (String) mapPermohonan.get("ID_PENARIKANBALIK");


					r = new SQLRenderer();
					long idPermohonan = DB.getNextID(db, "INT_PPTPERMOHONAN_SEQ");
					idPermohonanIntegrasiString = String.valueOf(idPermohonan);
					r.add("ID_PERMOHONAN", idPermohonan);
					r.add("NO_FAIL", NO_FAIL);
					r.add("NO_JILID", "");
					r.add("NAMA_KEMENTERIAN", NAMA_KEMENTERIAN);
					r.add("NAMA_PROJEK", NAMA_PROJEK);
					r.add("NO_WARTA", NO_WARTA);
					r.add("ID_PENGGUNA", idPengguna);
					if (TARIKH_WARTA != null)
						r.add("TARIKH_WARTA", r.unquote("to_date('" + TARIKH_WARTA + "','dd/MM/yyyy')"));
					r.add("FLAG_URUSAN", flagUrusan);
					r.add("ID_PENARIKANBALIK", ID_PENARIKANBALIK);
					sql = r.getSQLInsert("INT_PPTPERMOHONAN");
					stmt.executeUpdate(sql);

					sql = "SELECT DISTINCT TBLRUJNEGERI.KOD_NEGERI || '00' || TBLRUJMUKIM.KOD_MUKIM || TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK || TRIM (TO_CHAR (TBLPPTHAKMILIK.NO_HAKMILIK, '00000000')) "
							+ " AS ID_HAKMILIK, TBLRUJNEGERI.KOD_NEGERI, TBLRUJDAERAH.KOD_DAERAH, TBLRUJMUKIM.KOD_MUKIM, TBLRUJJENISHAKMILIK.KOD_JENIS_HAKMILIK, TO_CHAR (TBLPPTWARTA.TARIKH_WARTA, 'DD/MM/YYYY') AS TARIKH_WARTA, TBLPPTWARTA.NO_WARTA, TBLPPTHAKMILIK.NO_HAKMILIK, TBLPPTPENARIKANBALIK.SEBAB_PENARIKANBALIK, "
							+ " UPPER (TBLRUJLOT.KETERANGAN) AS KOD_LOT, TBLPPTHAKMILIK.NO_PT, TBLPPTHAKMILIK.NO_LOT, (CASE WHEN TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = '1' THEN 'Hektar' WHEN TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = '2' THEN 'Meter Persegi' ELSE '' "
							+ " END ) AS KOD_LUAS_ASAL, TRIM (TO_CHAR (TBLPPTHAKMILIK.LUAS_LOT, '9999999999999990.9999') ) AS LUAS_ASAL, (CASE WHEN TBLPPTHAKMILIK.ID_UNITLUASAMBIL_CONVERT = '1' THEN 'Hektar' WHEN TBLPPTHAKMILIK.ID_UNITLUASAMBIL_CONVERT = '2' "
							+ " THEN 'Meter Persegi' ELSE '' END ) AS KOD_LUAS_AMBIL, TRIM (TO_CHAR (TBLPPTPENARIKANHAKMILIK.LUAS_AMBIL, '9999999999999990.9999' ) ) AS LUAS_AMBIL, TBLPPTHAKMILIK.NO_SUBJAKET, TBLPPTHAKMILIK.ID_HAKMILIK,CASE WHEN TBLPPTHAKMILIK.LUAS_LOT <> TBLPPTHAKMILIK.LUAS_AMBIL THEN 'SEBAHAGIAN' ELSE 'KESELURUHAN' END AS STATUS_AMBIL, "
							+ " TBLPPTWARTA.ID_PENARIKANBALIK, TBLPPTHAKMILIK.ID_HAKMILIK AS ID_PPTHAKMILIK"
							+ " FROM TBLPFDFAIL, TBLPPTPERMOHONAN, TBLPPTHAKMILIK, TBLRUJNEGERI, TBLRUJMUKIM, TBLRUJDAERAH, TBLRUJJENISHAKMILIK, TBLRUJLUAS RUJLUAS_ASAL, TBLRUJLUAS RUJLUAS_AMBIL, TBLRUJLOT, TBLPPTWARTA, TBLPPTPENARIKANBALIK, "
							+ " TBLPPTPENARIKANHAKMILIK WHERE TBLPFDFAIL.ID_FAIL = TBLPPTPERMOHONAN.ID_FAIL AND TBLPPTPERMOHONAN.ID_PERMOHONAN = TBLPPTHAKMILIK.ID_PERMOHONAN AND TBLPPTHAKMILIK.ID_HAKMILIK = TBLPPTPENARIKANHAKMILIK.ID_HAKMILIK AND TBLPPTPENARIKANHAKMILIK.ID_PENARIKANBALIK = TBLPPTPENARIKANBALIK.ID_PENARIKANBALIK "
							+ " AND TBLPPTWARTA.ID_PENARIKANBALIK = TBLPPTPENARIKANBALIK.ID_PENARIKANBALIK AND TBLPFDFAIL.ID_NEGERI = TBLRUJNEGERI.ID_NEGERI(+) AND TBLPPTHAKMILIK.ID_MUKIM = TBLRUJMUKIM.ID_MUKIM(+) AND TBLPPTPERMOHONAN.ID_DAERAH = TBLRUJDAERAH.ID_DAERAH(+) "
							+ " AND TBLPPTHAKMILIK.ID_JENISHAKMILIK = TBLRUJJENISHAKMILIK.ID_JENISHAKMILIK(+) AND TBLPPTHAKMILIK.ID_UNITLUASLOT_CONVERT = RUJLUAS_ASAL.ID_LUAS(+) AND TBLPPTHAKMILIK.ID_UNITLUASAMBIL = RUJLUAS_AMBIL.ID_LUAS(+) AND TBLPPTHAKMILIK.ID_LOT = TBLRUJLOT.ID_LOT(+) "
							+ " AND TBLPFDFAIL.ID_FAIL = '" + idFail + "' "
							+ " AND TBLPPTWARTA.ID_PENARIKANBALIK = '" + idPPTPenarikanBalik + "' "
							+ " AND (TRANSLATE (TRIM (TBLPPTHAKMILIK.NO_HAKMILIK), '_0123456789', '_' ) ) IS NULL AND TBLPFDFAIL.NO_FAIL IS NOT NULL AND (    TBLPPTHAKMILIK.FLAG_PENARIKAN_KESELURUHAN = 'Y' OR TBLPPTHAKMILIK.FLAG_PENARIKAN_BALIK = 'Y' ) "
							+ " AND TBLPPTHAKMILIK.ID_ENDORSAN_PD IS NULL"
							+ " ORDER BY TBLPPTHAKMILIK.NO_SUBJAKET, TBLPPTHAKMILIK.NO_LOT ASC";
					ResultSet rsHakmilik = stmt.executeQuery(sql);
					List listHakmilik = Collections.synchronizedList(new ArrayList());
					Map hHakmilik = null;
					while (rsHakmilik.next()) {
						hHakmilik = Collections.synchronizedMap(new HashMap());
						hHakmilik.put("ID_PPTHAKMILIK", rsHakmilik.getString("ID_PPTHAKMILIK") != null ? rsHakmilik.getString("ID_PPTHAKMILIK") : "");
						hHakmilik.put("ID_HAKMILIK", rsHakmilik.getString("ID_HAKMILIK") != null ? rsHakmilik.getString("ID_HAKMILIK") : "");
						hHakmilik.put("KOD_NEGERI", rsHakmilik.getString("KOD_NEGERI") != null ? rsHakmilik.getString("KOD_NEGERI") : "");
						hHakmilik.put("KOD_DAERAH", "00");
						hHakmilik.put("KOD_MUKIM", rsHakmilik.getString("KOD_MUKIM") != null ? rsHakmilik.getString("KOD_MUKIM") : "");
						hHakmilik.put("KOD_JENIS_HAKMILIK", rsHakmilik.getString("KOD_JENIS_HAKMILIK") != null ? rsHakmilik.getString("KOD_JENIS_HAKMILIK") : "");
						hHakmilik.put("NO_HAKMILIK", rsHakmilik.getString("NO_HAKMILIK") != null ? rsHakmilik.getString("NO_HAKMILIK") : "");
						String kodLot = rsHakmilik.getString("KOD_LOT");
						if (kodLot != null && !"".equals(kodLot)) {
							hHakmilik.put("KOD_LOT", kodLot);
							hHakmilik.put("NO_LOT", rsHakmilik.getString("NO_PT") != null ? rsHakmilik.getString("NO_PT") : "");
						} else {
							hHakmilik.put("KOD_LOT", "LOT");
							hHakmilik.put("NO_LOT", rsHakmilik.getString("NO_LOT") != null ? rsHakmilik.getString("NO_LOT") : "");
						}
						hHakmilik.put("NO_SEKSYEN", "");
						hHakmilik.put("KOD_LUAS_ASAL", rsHakmilik.getString("KOD_LUAS_ASAL") != null ? rsHakmilik.getString("KOD_LUAS_ASAL") : "");
						hHakmilik.put("LUAS_ASAL", rsHakmilik.getString("LUAS_ASAL") != null ? rsHakmilik.getString("LUAS_ASAL") : "");
						hHakmilik.put("KOD_LUAS_AMBIL", rsHakmilik.getString("KOD_LUAS_AMBIL") != null ? rsHakmilik.getString("KOD_LUAS_AMBIL") : "");
						hHakmilik.put("LUAS_AMBIL", rsHakmilik.getString("LUAS_AMBIL") != null ? rsHakmilik .getString("LUAS_AMBIL") : "");
						hHakmilik.put("NO_WARTA", rsHakmilik.getString("NO_WARTA") != null ? rsHakmilik.getString("NO_WARTA") : "");
						hHakmilik.put("TARIKH_WARTA", rsHakmilik.getString("TARIKH_WARTA") != null ? rsHakmilik.getString("TARIKH_WARTA") : "");
						hHakmilik.put("STATUS_AMBIL", rsHakmilik.getString("STATUS_AMBIL") != null ? rsHakmilik.getString("STATUS_AMBIL") : "");
						hHakmilik.put("NO_SUBJAKET", rsHakmilik.getString("NO_SUBJAKET") != null ? rsHakmilik.getString("NO_SUBJAKET") : "");
						hHakmilik.put("SEBAB_PENARIKANBALIK", rsHakmilik.getString("SEBAB_PENARIKANBALIK") != null ? rsHakmilik.getString("SEBAB_PENARIKANBALIK") : "");

						listHakmilik.add(hHakmilik);
					}

					for (int x = 0; x < listHakmilik.size(); x++) {
						Map mapHakmilik = (Map) listHakmilik.get(x);
						if (mapHakmilik != null) {
							insertHakmilikPermohonanTarikBalik(idPermohonan, idPengguna, mapHakmilik, db);
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return idPermohonanIntegrasiString;
	}

	private void insertHakmilikPermohonanTarikBalik(long idPermohonan, String idPengguna, Map mapHakmilik, Db db) {
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			long idHakmilikPermohonan = DB.getNextID(db, "INT_PPTHAKMILIKPERMOHONAN_SEQ");
			r.add("ID_PERMOHONAN", idPermohonan);
			String idHakmilik = (String) mapHakmilik.get("ID_HAKMILIK") != null ? (String) mapHakmilik.get("ID_HAKMILIK") : "";
			r.add("ID_HAKMILIK", idHakmilik);
			r.add("KOD_NEGERI", (String) mapHakmilik.get("KOD_NEGERI") != null ? (String) mapHakmilik.get("KOD_NEGERI") : "");
			r.add("KOD_DAERAH", (String) mapHakmilik.get("KOD_DAERAH") != null ? (String) mapHakmilik.get("KOD_DAERAH") : "");
			r.add("KOD_MUKIM", (String) mapHakmilik.get("KOD_MUKIM") != null ? (String) mapHakmilik.get("KOD_MUKIM") : "");
			r.add("KOD_HAKMILIK", (String) mapHakmilik.get("KOD_JENIS_HAKMILIK") != null ? (String) mapHakmilik.get("KOD_JENIS_HAKMILIK") : "");
			r.add("NO_HAKMILIK", (String) mapHakmilik.get("NO_HAKMILIK") != null ? (String) mapHakmilik.get("NO_HAKMILIK") : "");
			r.add("KOD_LOT", (String) mapHakmilik.get("KOD_LOT") != null ? (String) mapHakmilik.get("KOD_LOT") : "");
			r.add("NO_LOT", (String) mapHakmilik.get("NO_LOT") != null ? (String) mapHakmilik.get("NO_LOT") : "");
			r.add("NO_SEKSYEN", "");
			r.add("KOD_LUAS_ASAL", (String) mapHakmilik.get("KOD_LUAS_ASAL") != null ? (String) mapHakmilik.get("KOD_LUAS_ASAL") : "");
			r.add("LUAS_ASAL", (String) mapHakmilik.get("LUAS_ASAL") != null ? (String) mapHakmilik.get("LUAS_ASAL") : "");
			r.add("KOD_LUAS_AMBIL", (String) mapHakmilik.get("KOD_LUAS_AMBIL") != null ? (String) mapHakmilik.get("KOD_LUAS_AMBIL") : "");
			r.add("LUAS_AMBIL", (String) mapHakmilik.get("LUAS_AMBIL") != null ? (String) mapHakmilik.get("LUAS_AMBIL") : "");
			r.add("NO_WARTA", (String) mapHakmilik.get("NO_WARTA") != null ? (String) mapHakmilik.get("NO_WARTA") : "");
			if ((String) mapHakmilik.get("TARIKH_WARTA") != null)
				r.add("TARIKH_WARTA", r.unquote("to_date('" + (String) mapHakmilik.get("TARIKH_WARTA") + "','dd/MM/yyyy')"));
			r.add("STATUS_AMBIL", (String) mapHakmilik.get("STATUS_AMBIL") != null ? (String) mapHakmilik.get("STATUS_AMBIL") : "");
			r.add("NO_SUBJAKET", (String) mapHakmilik.get("NO_SUBJAKET") != null ? (String) mapHakmilik.get("NO_SUBJAKET") : "");
			r.add("SEBAB_PENARIKANBALIK", (String) mapHakmilik.get("SEBAB_PENARIKANBALIK") != null ? (String) mapHakmilik.get("SEBAB_PENARIKANBALIK") : "");
				
			String catatanHakmilik = getCatatanHakmilik(idPengguna, idHakmilik);
			r.add("CATATAN_HAKMILIK", catatanHakmilik);
			r.add("ID_PPTHAKMILIK", (String) mapHakmilik.get("ID_PPTHAKMILIK") != null ? (String) mapHakmilik.get("ID_PPTHAKMILIK") : "");
			sql = r.getSQLInsert("INT_PPTHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Vector<Tblrujdokumen> getSenaraiDokumen(String idPermohonanIntegrasi,String tapisan, Db db) {
		String sql = "";
		Vector<Tblrujdokumen> listDokumen = null;
		Hashtable h;
		Tblrujdokumen dokumen = null;

		try {
			listDokumen = new Vector();

			Statement stmt = db.getStatement();
			sql = "SELECT D.ID_DOKUMEN, DI.ID_TANAHDOKUMEN,D.ID_JENISDOKUMEN,D.NAMA_FAIL,D.JENIS_MIME,D.CONTENT "
				+ ",(CASE"  
				+	"	WHEN D.ID_JENISDOKUMEN =1 THEN 'A'"  
				+	"	WHEN D.ID_JENISDOKUMEN =2 THEN 'B'"  
				+	"	WHEN D.ID_JENISDOKUMEN =3 THEN 'C'"  
				+	"	WHEN D.ID_JENISDOKUMEN =4 THEN 'D'"  
				+	"	WHEN D.ID_JENISDOKUMEN =5 THEN 'K'" 
				+ 	"	WHEN D.ID_JENISDOKUMEN =6 THEN 'IH'" 
				+	"	WHEN D.ID_JENISDOKUMEN =7 THEN 'SPU'" 
				+	"	WHEN D.ID_JENISDOKUMEN =1 THEN 'MMKNe'" 
				+	"	WHEN D.ID_JENISDOKUMEN =1 THEN 'SII'" 
				+	"END ) KOD_JENISDOKUMEN "
				+ " FROM TBLINTANAHDOKUMEN DI,TBLPPTDOKUMEN D "
				+ " WHERE "
				+ " D.ID_DOKUMEN = DI.ID_DOKUMEN(+)"
				+ " AND D.ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			if(!tapisan.equals(""))
				sql +=" AND D.TAJUK='"+tapisan+"'";
//				+ " AND DI.ID_RUJUKAN = '" + idPermohonanIntegrasi + "'";
			sql = sql + " ORDER BY ID_TANAHDOKUMEN ASC";
			myLog.info("getSenaraiDokumen:sql="+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dokumen = new Tblrujdokumen();
				dokumen.setIdDokumen(rs.getString("ID_TANAHDOKUMEN") == null ? rs.getString("ID_DOKUMEN") : rs.getString("ID_TANAHDOKUMEN"));
				dokumen.setNamaDokumen(rs.getString("NAMA_FAIL"));
				//dokumen.setKodDokumen(rs.getString("JENIS_MIME"));

				Blob b = rs.getBlob("CONTENT");
				InputStream is = b.getBinaryStream();
				byte [] b2 = IOUtils.toByteArray(is);
				dokumen.setKandungan(Base64.encodeToString(b2));
				dokumen.setIdJenis(getJenisDokumen(rs.getString("KOD_JENISDOKUMEN")));	
//				h = new Hashtable();
//				h.put("idDokumenPermohonan", rs.getString("ID_TANAHDOKUMEN") == null ? "" : rs.getString("ID_TANAHDOKUMEN"));
//				//h.put("flagDokumen", rs.getString("FLAG_DOKUMEN") == null ? "" : rs.getString("FLAG_DOKUMEN"));
//				String jenisDokumen = getJenisDokumen(rs.getString("ID_JENISDOKUMEN"));
//				h.put("jenisDokumen", jenisDokumen);
//				h.put("namaDokumen", rs.getString("NAMA_FAIL") == null ? "" : rs.getString("NAMA_FAIL"));
//				h.put("jenisMime", rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));
				//h.put("kodDokumen", rs.getString("KOD_DOKUMEN") == null ? "" : rs.getString("KOD_DOKUMEN"));
				listDokumen.addElement(dokumen);
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listDokumen;
	}
	
	public Vector getSenaraiDokumenEndorsan(String idPermohonanIntegrasi, Db db) {
		String sql = "";
		Vector listDokumen = null;
		Hashtable h;

		try {
			listDokumen = new Vector();

			Statement stmt = db.getStatement();

			sql = "SELECT * FROM TBLINTANAHDOKUMENEND WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "'";
			sql = sql + " ORDER BY ID_DOKUMENENDORSAN ASC";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				h = new Hashtable();
				h.put("idDokumenEndorsan", rs.getString("ID_DOKUMENENDORSAN") == null ? "" : rs.getString("ID_DOKUMENENDORSAN"));
				h.put("flagDokumen", rs.getString("FLAG_DOKUMEN") == null ? "" : rs.getString("FLAG_DOKUMEN"));
				String jenisDokumen = getJenisDokumen(rs.getString("FLAG_DOKUMEN"));
				h.put("jenisDokumen", jenisDokumen);
				h.put("namaDokumen", rs.getString("NAMA_DOKUMEN") == null ? "" : rs.getString("NAMA_DOKUMEN"));
				h.put("jenisMime", rs.getString("JENIS_MIME") == null ? "" : rs.getString("JENIS_MIME"));
				listDokumen.addElement(h);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return listDokumen;
	}

	private String getJenisDokumen(String flagDokumen) {
		String jenisDokumen = "";

		if ("1".equals(flagDokumen)) {
			jenisDokumen = "BORANG D";
		} else if ("2".equals(flagDokumen)) {
			jenisDokumen = "WARTA KERAJAAN PERSEKUTUAN";
		} else if ("3".equals(flagDokumen)) {
			jenisDokumen = "SURAT IRINGAN BORANG D";
		} else if ("4".equals(flagDokumen)) {
			jenisDokumen = "JADUAL BEARING DISTANCE";
		} else if ("5".equals(flagDokumen)) {
			jenisDokumen = "PELAN PENGAMBILAN DIGITAL";
		} else if ("6".equals(flagDokumen)) {
			jenisDokumen = "SURAT MAKLUM";
		} else if ("7".equals(flagDokumen)) {
			jenisDokumen = "BORANG K";
		} else if ("8".equals(flagDokumen)) {
			jenisDokumen = "SURAT IRINGAN BORANG K";
		} else if ("9".equals(flagDokumen)) {
			jenisDokumen = "WARTA BORANG D";
		} else if ("10".equals(flagDokumen)) {
			jenisDokumen = "SURAT IRINGAN BATALKAN ENDORSAN BORANG D";
		} else if ("11".equals(flagDokumen)) {
			jenisDokumen = "WARTA TARIK BALIK";
		} else if ("12".equals(flagDokumen)) {
			jenisDokumen = "SURAT IRINGAN TARIK BALIK";
		}

		return jenisDokumen;
	}

	public void deleteDokumen(String idDokumen, Db db) {
		String sql = "";
		Connection conn = null;
		String idPermohonanIntegrasiString = "";
		try {
			Statement stmt = db.getStatement();

			sql = "DELETE FROM TBLPPTDOKUMEN WHERE ID_DOKUMEN = '" + idDokumen + "'";
//			sql = "DELETE FROM INT_PPTDOKUMENPERMOHONAN WHERE ID_DOKUMENPERMOHONAN = '" + idDokumen + "'";
			stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean checkSenaraiHakmilik(String idPengguna, String idPermohonanIntegrasi, Db db) {
		boolean bool = true;
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT ID_HAKMILIKPERMOHONAN, ID_HAKMILIK FROM INT_PPTHAKMILIKPERMOHONAN WHERE ID_PERMOHONAN = '" + idPermohonanIntegrasi + "' ORDER BY ID_HAKMILIK ASC";
			ResultSet rsHakmilik = stmt.executeQuery(sql);
			List listHakmilik = Collections.synchronizedList(new ArrayList());
			Map hHakmilik = null;
			while (rsHakmilik.next()) {
				hHakmilik = Collections.synchronizedMap(new HashMap());

				hHakmilik.put("ID_HAKMILIKPERMOHONAN", rsHakmilik.getString("ID_HAKMILIKPERMOHONAN") != null ? rsHakmilik.getString("ID_HAKMILIKPERMOHONAN") : "");
				hHakmilik.put("ID_HAKMILIK", rsHakmilik.getString("ID_HAKMILIK") != null ? rsHakmilik.getString("ID_HAKMILIK") : "");
				
				listHakmilik.add(hHakmilik);
			}

			for (int x = 0; x < listHakmilik.size(); x++) {
				Map mapHakmilik = (Map) listHakmilik.get(x);
				if (mapHakmilik != null) {
					String catatanHakmilik = getCatatanHakmilik(idPengguna, (String) mapHakmilik.get("ID_HAKMILIK"));
					if (catatanHakmilik != null && !"".equals(catatanHakmilik)) {
						bool = false;
					}
					updateCatatanHakmilik((String) mapHakmilik.get("ID_HAKMILIKPERMOHONAN"), catatanHakmilik, db);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bool;
	}
	
	private void updateCatatanHakmilik(String idHakmilikPermohonan, String catatanHakmilik, Db db) {
		String sql = "";

		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.update("ID_HAKMILIKPERMOHONAN", idHakmilikPermohonan);			
			r.add("CATATAN_HAKMILIK", catatanHakmilik);
			
			sql = r.getSQLUpdate("INT_PPTHAKMILIKPERMOHONAN");
			stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}


package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Agensi;
import ekptg.model.entities.Daerah;
import ekptg.model.entities.Kementerian;
import ekptg.model.entities.Mukim;
import ekptg.model.entities.Negeri;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.htp.entity.HakmilikPajakan;
import ekptg.model.htp.permohonan.IPermohonanPerizapan;
import ekptg.model.htp.permohonan.PermohonanPerizapanBean;
/**
 *
 *
 */
public class FrmPajakanPopupSenaraiTanahData {

	private ekptg.model.htp.permohonan.IPermohonanPerizapan ipr= null;
	private Vector<Hashtable<String,String>> senaraiTanah = null;
	private Vector<Hashtable<String,String>> beanMaklumatTanah = null;
	private static Logger myLog = Logger.getLogger(FrmPajakanPopupSenaraiTanahData.class);
	private String idHakmilikUrusan = "";

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void carianTanah(String idJenisTanah, String peganganHakmilik, String jenisHakmilik, String noHakmilik, String jenisLot,
			String lot, String noWarta, String tarikhWarta, String idNegeri, String idDaerah, String idMukim) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiTanah = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA" +
				" ,E.NAMA_MUKIM, D.NAMA_DAERAH,C.NAMA_NEGERI "+
				" ,A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA "+
				" ,A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI "+
				" ,A.ID_SEKSYENUPI,F.NAMA_SEKSYENUPI " +
		       	" ,(SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "+
		       	" 	WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK " +
		       	" ) KOD_JENIS_HAKMILIK "+
				" FROM TBLHTPHAKMILIK A,TBLRUJSEKSYENUPI F, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E "+
				" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) "+
				" AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+) "+
				" AND A.ID_SEKSYENUPI = F.ID_SEKSYENUPI(+)";

			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						sql = sql + " AND NVL(A.NO_HAKMILIK,' ') <> ' ' ";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						sql = sql + " AND NVL(A.NO_WARTA,' ') <> ' ' ";
					}
				}
			}

			//peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			//jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("") && !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND A.ID_JENISHAKMILIK = '" + jenisHakmilik.trim().toUpperCase() + "'";
				}
			}

			//noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			//jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("") && !jenisLot.trim().equals("99999")) {
					sql = sql + " AND A.ID_LOT = '" + jenisLot.trim().toUpperCase() + "'";
				}
			}

			//lot
			if (lot != null) {
				if (!lot.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'"
							+ lot.trim().toUpperCase() + "'|| '%'";
				}
			}

			//noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			//tarikhWarta
			if (tarikhWarta != null) {
				if (!tarikhWarta.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_WARTA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhWarta)).toUpperCase() +"'";
				}
			}

			//idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("") && !idNegeri.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeri.trim().toUpperCase() + "'";
				}
			}

			//idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("") && !idDaerah.trim().equals("99999")) {
					sql = sql + " AND A.ID_DAERAH = '" + idDaerah.trim().toUpperCase() + "'";
				}
			}

			//idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("") && !idMukim.trim().equals("99999")) {
					sql = sql + " AND A.ID_MUKIM = '" + idMukim.trim().toUpperCase() + "'";
				}
			}

			sql = sql + " ORDER BY A.ID_HAKMILIK ASC";
			myLog.info("carianTanah :: sql >>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("lot", rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				String kodHakmilik = rs.getString("KOD_JENIS_HAKMILIK")== null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
				String hakmilik = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
				//h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				if("".equals(hakmilik)){
					h.put("noHakmilik", "");
				}else{
					h.put("noHakmilik", kodHakmilik+hakmilik);
				}

				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("mukim",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("namaSeksyen", rs.getString("NAMA_SEKSYENUPI") == null ? "TIADA SEKSYEN" : rs.getString("NAMA_SEKSYENUPI"));
				senaraiTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void carianTanahOnline(String idKementerian,String idJenisTanah, String peganganHakmilik, String jenisHakmilik, String noHakmilik, String jenisLot,
			String lot, String noWarta, String tarikhWarta, String idNegeri, String idDaerah, String idMukim) throws Exception {

		Db db = null;
		String sql = "";

		try {
			senaraiTanah = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK, B.KETERANGAN, A.NO_LOT, A.NO_HAKMILIK, A.NO_WARTA, E.NAMA_MUKIM, D.NAMA_DAERAH,"
				+ " C.NAMA_NEGERI, A.ID_JENISHAKMILIK, A.ID_LOT, A.TARIKH_WARTA, A.ID_MUKIM, A.ID_DAERAH, A.ID_NEGERI"
		       	+ ",(SELECT RJH.KOD_JENIS_HAKMILIK FROM TBLRUJJENISHAKMILIK RJH "
		       	+ " WHERE RJH.ID_JENISHAKMILIK=A.ID_JENISHAKMILIK ) KOD_JENIS_HAKMILIK "
				+ " FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E"
				+ " WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) AND A.ID_MUKIM = E.ID_MUKIM(+)";

			//idJenisTanah
			if (idJenisTanah != null) {
				if (!idJenisTanah.trim().equals("")) {
					if (Integer.parseInt(idJenisTanah) == 1){
						sql = sql + " AND A.STATUS_RIZAB IS NOT NULL";
					} else if (Integer.parseInt(idJenisTanah) == 2){
						sql = sql + " AND A.STATUS_RIZAB IS NULL";
					}
				}
			}

			//idKementerian
			if (idKementerian != null) {
				if (!idKementerian.trim().equals("") && !idKementerian.trim().equals("99999")) {
					sql = sql + " AND A.ID_KEMENTERIAN = '" + idKementerian.trim().toUpperCase() + "'";
				}
			}

			//peganganHakmilik
			if (peganganHakmilik != null) {
				if (!peganganHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.PEGANGAN_HAKMILIK) LIKE '%' ||'"
							+ peganganHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			//jenisHakmilik
			if (jenisHakmilik != null) {
				if (!jenisHakmilik.trim().equals("") && !jenisHakmilik.trim().equals("99999")) {
					sql = sql + " AND A.ID_JENISHAKMILIK = '" + jenisHakmilik.trim().toUpperCase() + "'";
				}
			}

			//noHakmilik
			if (noHakmilik != null) {
				if (!noHakmilik.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_HAKMILIK) LIKE '%' ||'"
							+ noHakmilik.trim().toUpperCase() + "'|| '%'";
				}
			}

			//jenisLot
			if (jenisLot != null) {
				if (!jenisLot.trim().equals("") && !jenisLot.trim().equals("99999")) {
					sql = sql + " AND A.ID_LOT = '" + jenisLot.trim().toUpperCase() + "'";
				}
			}

			//lot
			if (lot != null) {
				if (!lot.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_LOT) LIKE '%' ||'"
							+ lot.trim().toUpperCase() + "'|| '%'";
				}
			}

			//noWarta
			if (noWarta != null) {
				if (!noWarta.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_WARTA) LIKE '%' ||'"
							+ noWarta.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			//tarikhWarta
			if (tarikhWarta != null) {
				if (!tarikhWarta.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(A.TARIKH_WARTA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhWarta)).toUpperCase() +"'";
				}
			}

			//idNegeri
			if (idNegeri != null) {
				if (!idNegeri.trim().equals("") && !idNegeri.trim().equals("99999")) {
					sql = sql + " AND A.ID_NEGERI = '" + idNegeri.trim().toUpperCase() + "'";
				}
			}

			//idDaerah
			if (idDaerah != null) {
				if (!idDaerah.trim().equals("") && !idDaerah.trim().equals("99999")) {
					sql = sql + " AND A.ID_DAERAH = '" + idDaerah.trim().toUpperCase() + "'";
				}
			}

			//idMukim
			if (idMukim != null) {
				if (!idMukim.trim().equals("") && !idMukim.trim().equals("99999")) {
					sql = sql + " AND A.ID_MUKIM = '" + idMukim.trim().toUpperCase() + "'";
				}
			}

			sql = sql + " ORDER BY C.NAMA_NEGERI ASC";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("POPUP=="+sql);
			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK").toUpperCase());
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("lot", rs.getString("KETERANGAN") == null || rs.getString("NO_LOT") == null ? "" : rs.getString("KETERANGAN").toUpperCase() + " " + rs.getString("NO_LOT").toUpperCase());
				String kodHakmilik = rs.getString("KOD_JENIS_HAKMILIK")== null ? "" : rs.getString("KOD_JENIS_HAKMILIK");
				String hakmilik = rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK");
				//h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				if("".equals(hakmilik)){
					h.put("noHakmilik", "");
				}else{
					h.put("noHakmilik", kodHakmilik+hakmilik);
				}

				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("mukim",rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM"));
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH"));
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				senaraiTanah.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void setMaklumatTanah(String idHakmilik) throws Exception {
		Db db = null;
		String sql = "";
		try {
			beanMaklumatTanah = new Vector<Hashtable<String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_HAKMILIK,A.PEGANGAN_HAKMILIK,A.NO_HAKMILIK,A.NO_LOT,A.LUAS, A.ID_LUAS" +
				" ,A.NO_WARTA,A.TARIKH_WARTA" +
				" ,F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK" +
				" ,B.KOD_LOT, B.KETERANGAN AS JENIS_LOT"+
				" ,E.NAMA_MUKIM, D.NAMA_DAERAH" +
				" ,A.ID_SEKSYENUPI,F.NAMA_SEKSYENUPI" +
				" ,C.NAMA_NEGERI, G.KETERANGAN AS SUBKATEGORI, H.KETERANGAN AS KATEGORI, A.SYARAT, A.SEKATAN, I.NAMA_KEMENTERIAN, J.NAMA_AGENSI, K.KETERANGAN AS JENIS_LUAS, K.KOD_LUAS AS KOD_LUAS"+
				" FROM TBLHTPHAKMILIK A, TBLRUJSEKSYENUPI F, TBLRUJLOT B" +
				" , TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E" +
				" , TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I, TBLRUJAGENSI J, TBLRUJLUAS K" +
				" WHERE A.ID_NEGERI = C.ID_NEGERI AND A.ID_DAERAH = D.ID_DAERAH AND A.ID_MUKIM = E.ID_MUKIM "+
				" AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK AND A.ID_LOT = B.ID_LOT(+) "+
				" AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) AND G.ID_KATEGORI = H.ID_KATEGORI(+) "+
				" AND A.ID_SEKSYENUPI = F.ID_SEKSYENUPI(+) AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS "+
				" AND A.ID_HAKMILIK = '" + idHakmilik + "'";

			System.out.println("setMaklumatTanah :: sql >>>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
//			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idHakmilik", rs.getString("ID_HAKMILIK") == null ? "" : rs.getString("ID_HAKMILIK"));
				h.put("peganganHakmilik", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK").toUpperCase());
				h.put("jenisHakmilik", rs.getString("KOD_JENIS_HAKMILIK") == null || rs.getString("JENIS_HAKMILIK") == null? "" : rs.getString("KOD_JENIS_HAKMILIK").toUpperCase() + " - " + rs.getString("JENIS_HAKMILIK").toUpperCase());
				h.put("noHakmilik", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				h.put("jenisLot", rs.getString("KOD_LOT") == null || rs.getString("JENIS_LOT") == null? "" : rs.getString("KOD_LOT").toUpperCase() + " - " + rs.getString("JENIS_LOT").toUpperCase());
				h.put("noLot", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("luasLot", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS") + " " + rs.getString("JENIS_LUAS"));
				h.put("luasAsal", rs.getString("LUAS") == null || rs.getString("JENIS_LUAS") == null ? "" : rs.getString("LUAS"));
				h.put("idLuas", rs.getString("ID_LUAS") == null ? "0" : rs.getString("ID_LUAS"));
				h.put("noWarta", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
				h.put("tarikhWarta", rs.getDate("TARIKH_WARTA") == null ? "" : sdf.format(rs.getDate("TARIKH_WARTA")));
				h.put("mukim", rs.getString("NAMA_MUKIM") == null ? "" : rs.getString("NAMA_MUKIM").toUpperCase());
				h.put("daerah", rs.getString("NAMA_DAERAH") == null ? "" : rs.getString("NAMA_DAERAH").toUpperCase());
				h.put("negeri", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("kategoriTanah", rs.getString("KATEGORI") == null ? "" : rs.getString("KATEGORI").toUpperCase());
				h.put("subKategoriTanah", rs.getString("SUBKATEGORI") == null ? "" : rs.getString("SUBKATEGORI").toUpperCase());
				h.put("syarat", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT").toUpperCase());
				h.put("sekatan", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN").toUpperCase());
				h.put("kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN").toUpperCase());
				h.put("agensi", rs.getString("NAMA_AGENSI") == null ? "" : rs.getString("NAMA_AGENSI").toUpperCase());
				h.put("namaSeksyen", rs.getString("NAMA_SEKSYENUPI") == null ? "TIADA SEKSYEN" : rs.getString("NAMA_SEKSYENUPI"));beanMaklumatTanah.addElement(h);
				//bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}

	public void saveHakmilikUrusan(String idHakmilik, String idPermohonan, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		//String sqlSelect = "";
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT * FROM TBLHTPHAKMILIK WHERE ID_HAKMILIK = '" + idHakmilik + "'";
			myLog.info("sql popup hakmilik : " + sql);
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()){
				//TBLHTPHAKMILIKURUSAN
				//String isSubcategory = rs.getString("ID_SUBKATEGORI");
				long idHakmilikUrusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
				r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
				r.add("ID_PERMOHONAN", idPermohonan);
				r.add("PEGANGAN_HAKMILIK", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
				r.add("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
				r.add("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
//				r.add("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
				r.add("TARIKH_WARTA", rs.getDate("TARIKH_WARTA") == null ? "" : rs.getDate("TARIKH_WARTA"));
				r.add("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
				r.add("ID_LUAS", rs.getString("ID_LUAS") == null ? "" : rs.getString("ID_LUAS"));
				r.add("LUAS", rs.getString("LUAS") == null ? "" : rs.getString("LUAS"));
				r.add("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT"));
				r.add("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
				r.add("SYARAT", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT"));
				r.add("SEKATAN", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN"));
				r.add("CUKAI", rs.getString("CUKAI") == null ? "" : rs.getString("CUKAI"));
				r.add("STATUS_SWASTA", rs.getString("STATUS_SWASTA") == null ? "" : rs.getString("STATUS_SWASTA"));

				/*if(!rs.getString("ID_SUBKATEGORI").equals(null) && !rs.getString("ID_SUBKATEGORI").equals("0")){
					r.add("ID_SUBKATEGORI", rs.getString("ID_SUBKATEGORI"));
				}else{
					r.add("ID_SUBKATEGORI", "96");//set this one to tiada matlumat, dia refer ke table kategori
				}*/

//				r.add("ID_SUBKATEGORI", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
				r.add("CUKAI_TERKINI", rs.getString("CUKAI_TERKINI") == null ? "" : rs.getString("CUKAI_TERKINI"));
				r.add("STATUS_RIZAB", rs.getString("STATUS_RIZAB") == null ? "" : rs.getString("STATUS_RIZAB"));
				r.add("NO_BANGUNAN", rs.getString("NO_BANGUNAN") == null ? "" : rs.getString("NO_BANGUNAN"));
				r.add("NO_TINGKAT", rs.getString("NO_TINGKAT") == null ? "" : rs.getString("NO_TINGKAT"));
				r.add("NO_PETAK", rs.getString("NO_PETAK") == null ? "" : rs.getString("NO_PETAK"));
				r.add("LOKASI", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI"));

				if(!rs.getString("ID_KATEGORI").equals(null) && !rs.getString("ID_KATEGORI").equals("0") ){
					r.add("ID_KATEGORI", rs.getString("ID_KATEGORI"));
				}else{
					r.add("ID_KATEGORI", "1");
				}

				r.add("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				r.add("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				r.add("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
				r.add("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
				r.add("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "" : rs.getString("ID_JENISHAKMILIK"));

				r.add("ID_MASUK", userId);
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

				sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
				myLog.info("fir sql TBLHTPHAKMILIKURUSAN : " + sql);
				stmt.executeUpdate(sql);
			}

			conn.commit();

		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();
		}

//		catch (SQLException ex) {
//	    	try {
//	    		conn.rollback();
//	    	} catch (SQLException e) {
//	    		throw new Exception("Rollback error : " + e.getMessage());
//	    	}
//	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
//
//	    }

		finally {
			if (db != null)
				db.close();
		}
	}

	public void saveHakmilikUrusan(String idHakmilik, String idPermohonan, HakmilikUrusan hu, HttpSession session) throws Exception {
		Db db = null;
		Connection conn = null;
		String userId = session.getAttribute("_ekptg_user_id").toString();
		//String sqlSelect = "";
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			Hashtable<String,String> t = getPR().getMaklumatAsasTanahKemaskini(hu.getIdhakmilikurusan());
			if(t!=null){
				//myLog.info("getPR() : " + t);
				r.update("ID_HAKMILIKURUSAN", hu.getIdhakmilikurusan());
				r.add("ID_LUAS", hu.getIdLuas());
				r.add("LUAS", hu.getLuas());
				r.add("LUAS_ASAL", hu.getLuasAsal());
				r.add("ID_LUAS_BERSAMAAN", hu.getIdLuasBersamaan());
				r.add("LUAS_BERSAMAAN", hu.getLuasBersamaan());
				r.add("ID_KEMASKINI", userId);
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

				sql = r.getSQLUpdate("TBLHTPHAKMILIKURUSAN");
				//myLog.info("getPR() : sql=" + sql);
				stmt.executeUpdate(sql);
				idHakmilikUrusan = String.valueOf( hu.getIdhakmilikurusan());

			}else{
				//myLog.info("getPR() else: " + t);
				sql = "SELECT * FROM TBLHTPHAKMILIK WHERE ID_HAKMILIK = '" + idHakmilik + "'";
				//myLog.info("sql popup hakmilik : " + sql);
				ResultSet rs = stmt.executeQuery(sql);

				if (rs.next()){
					//TBLHTPHAKMILIKURUSAN
					//String isSubcategory = rs.getString("ID_SUBKATEGORI");
					long idHakmilikUrusan = DB.getNextID("TBLHTPHAKMILIKURUSAN_SEQ");
					r.add("ID_HAKMILIKURUSAN", idHakmilikUrusan);
					r.add("ID_PERMOHONAN", idPermohonan);
					r.add("PEGANGAN_HAKMILIK", rs.getString("PEGANGAN_HAKMILIK") == null ? "" : rs.getString("PEGANGAN_HAKMILIK"));
					//r.add("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "0" : rs.getString("ID_JENISHAKMILIK"));
					r.add("NO_HAKMILIK", rs.getString("NO_HAKMILIK") == null ? "" : rs.getString("NO_HAKMILIK"));
					r.add("NO_WARTA", rs.getString("NO_WARTA") == null ? "" : rs.getString("NO_WARTA"));
	//				r.add("TARIKH_WARTA", rs.getString("TARIKH_WARTA") == null ? "" : rs.getString("TARIKH_WARTA"));
					r.add("TARIKH_WARTA", rs.getDate("TARIKH_WARTA") == null ? "" : rs.getDate("TARIKH_WARTA"));
					r.add("NO_LOT", rs.getString("NO_LOT") == null ? "" : rs.getString("NO_LOT"));
					r.add("ID_LUAS", hu.getIdLuas());
					r.add("LUAS", hu.getLuas());
					r.add("LUAS_ASAl", hu.getLuasAsal());
					r.add("ID_LUAS_BERSAMAAN", hu.getIdLuasBersamaan());
					r.add("LUAS_BERSAMAAN", hu.getLuasBersamaan());
					r.add("NO_SYIT", rs.getString("NO_SYIT") == null ? "" : rs.getString("NO_SYIT"));
					r.add("NO_PELAN", rs.getString("NO_PELAN") == null ? "" : rs.getString("NO_PELAN"));
					r.add("SYARAT", rs.getString("SYARAT") == null ? "" : rs.getString("SYARAT"));
					r.add("SEKATAN", rs.getString("SEKATAN") == null ? "" : rs.getString("SEKATAN"));
					r.add("CUKAI", rs.getString("CUKAI") == null ? "" : rs.getString("CUKAI"));
					r.add("STATUS_SWASTA", rs.getString("STATUS_SWASTA") == null ? "" : rs.getString("STATUS_SWASTA"));

					/*if(!rs.getString("ID_SUBKATEGORI").equals(null) && !rs.getString("ID_SUBKATEGORI").equals("0")){
						r.add("ID_SUBKATEGORI", rs.getString("ID_SUBKATEGORI"));
					}else{
						r.add("ID_SUBKATEGORI", "96");//set this one to tiada matlumat, dia refer ke table kategori
					}*/

	//				r.add("ID_SUBKATEGORI", rs.getString("ID_SUBKATEGORI") == null ? "" : rs.getString("ID_SUBKATEGORI"));
					r.add("CUKAI_TERKINI", rs.getString("CUKAI_TERKINI") == null ? "" : rs.getString("CUKAI_TERKINI"));
					r.add("STATUS_RIZAB", rs.getString("STATUS_RIZAB") == null ? "" : rs.getString("STATUS_RIZAB"));
					r.add("NO_BANGUNAN", rs.getString("NO_BANGUNAN") == null ? "" : rs.getString("NO_BANGUNAN"));
					r.add("NO_TINGKAT", rs.getString("NO_TINGKAT") == null ? "" : rs.getString("NO_TINGKAT"));
					r.add("NO_PETAK", rs.getString("NO_PETAK") == null ? "" : rs.getString("NO_PETAK"));
					r.add("LOKASI", rs.getString("LOKASI") == null ? "" : rs.getString("LOKASI"));

					if(!rs.getString("ID_KATEGORI").equals(null) && !rs.getString("ID_KATEGORI").equals("0") ){
						r.add("ID_KATEGORI", rs.getString("ID_KATEGORI"));
					}else{
						r.add("ID_KATEGORI", "1");
					}

					r.add("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					r.add("ID_DAERAH", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
					r.add("ID_MUKIM", rs.getString("ID_MUKIM") == null ? "" : rs.getString("ID_MUKIM"));
					r.add("ID_LOT", rs.getString("ID_LOT") == null ? "" : rs.getString("ID_LOT"));
					r.add("ID_JENISHAKMILIK", rs.getString("ID_JENISHAKMILIK") == null ? "0" : rs.getString("ID_JENISHAKMILIK"));

					r.add("ID_MASUK", userId);
					r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

					sql = r.getSQLInsert("TBLHTPHAKMILIKURUSAN");
					//myLog.info("fir sql TBLHTPHAKMILIKURUSAN : " + sql);
					stmt.executeUpdate(sql);
					this.idHakmilikUrusan = String.valueOf(idHakmilikUrusan);

				}
			}

			conn.commit();

		}catch(Exception e){
			conn.rollback();
			e.printStackTrace();

		}finally {
			if (db != null)
				db.close();
		}
	}

	public Vector<Hashtable<String,String>> getSenaraiTanah() {
		return senaraiTanah;
	}

	public void setSenaraiTanah(Vector<Hashtable<String,String>> senaraiTanah) {
		this.senaraiTanah = senaraiTanah;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatTanah() {
		return beanMaklumatTanah;
	}

	public void setBeanMaklumatTanah(Vector<Hashtable<String,String>> beanMaklumatTanah) {
		this.beanMaklumatTanah = beanMaklumatTanah;
	}

	private IPermohonanPerizapan getPR(){
		if(ipr == null)
			ipr = new PermohonanPerizapanBean();
		return ipr;
	}

	public HakmilikPajakan getMaklumatTanahPajakan(String idHakmilikUrusan) throws Exception {
		Db db = null;
		String sql = "";
		//beanMaklumatTanah = new Vector();
		HakmilikPajakan hp = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK,A.NO_HAKMILIK, A.NO_LOT" +
				", A.NO_WARTA,A.TARIKH_WARTA" +
				", A.LUAS, A.ID_LUAS" +
				", K.KETERANGAN AS JENIS_LUAS" +
				", K.KOD_LUAS AS KOD_LUAS " +
				", A.ID_LUAS_BERSAMAAN ID_LUAS_BERSAMAAN,A.LUAS_BERSAMAAN " +
				", A.SYARAT, A.SEKATAN" +
				", A.ID_SEKSYEN,F.NAMA_SEKSYENUPI" +
				", B.ID_LOT,B.KOD_LOT, B.KETERANGAN AS JENIS_LOT" +
				", C.ID_NEGERI,C.NAMA_NEGERI" +
				", D.ID_DAERAH,D.NAMA_DAERAH " +
				", E.ID_MUKIM,E.NAMA_MUKIM" +
				", F.ID_JENISHAKMILIK,F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK " +
				", G.KETERANGAN AS SUBKATEGORI " +
				", H.KETERANGAN AS KATEGORI" +
				", I.ID_KEMENTERIAN,I.NAMA_KEMENTERIAN" +
				", J.ID_AGENSI,J.NAMA_AGENSI " +
				", HU.LUAS LUASPAJAKAN,HU.ID_LUAS ID_LUASPAJAKAN "+
				", HU.LUAS_BERSAMAAN LUASPAJAKANBER,HU.ID_LUAS_BERSAMAAN ID_LUASPAJAKANBER " +
				" ,HU.ID_HAKMILIKURUSAN"+
				" FROM TBLHTPHAKMILIK A, TBLRUJSEKSYENUPI F, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E " +
				" ,TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I " +
				" ,TBLRUJAGENSI J, TBLRUJLUAS K,TBLHTPHAKMILIKURUSAN HU "+
				" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
				" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) " +
				" AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) " +
				" AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) " +
				//" AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS_BERSAMAAN = K.ID_LUAS " +
				" AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS " +
				" AND HU.PEGANGAN_HAKMILIK = A.PEGANGAN_HAKMILIK "+
				" AND A.ID_SEKSYEN = F.ID_SEKSYENUPI(+) " +
				" AND HU.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan + "'";
			myLog.info("getMaklumatTanahPajakan :: sql >>>> "+sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				hp = new HakmilikPajakan();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				Tblrujlot lot = new Tblrujlot();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();

				//hp.setIdHakmilikUrusan(Long.parseLong(Utils.isNull(rs.getString("ID_HAKMILIKURUSAN"))));
				hp.setIdHakmilikUrusan(Long.parseLong(idHakmilikUrusan));
				hp.setIdHakmilik(Long.parseLong(Utils.isNull(rs.getString("ID_HAKMILIK"))));
				hp.setPeganganHakmilik(Utils.isNull(rs.getString("PEGANGAN_HAKMILIK")));
				jh.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hp.setRujJenisHakmilik(jh);
				hp.setNoHakmlik(Utils.isNull(rs.getString("NO_HAKMILIK")));
				hp.setNoWarta(Utils.isNull(rs.getString("NO_WARTA")));
				hp.setIdJenisLot(rs.getLong("ID_LOT"));
				lot.setKeterangan(rs.getString("JENIS_LOT"));
				lot.setKodLot(rs.getString("KOD_LOT"));
				hp.setRujLot(lot);
				hp.setNoLot(Utils.isNull(rs.getString("NO_LOT")));
				hp.setLuasString(rs.getString("LUAS") + " - "+rs.getString("JENIS_LUAS"));
				hp.setIdLuas(rs.getLong("ID_LUAS"));
				hp.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				//Utils.formatLuas(number)
				hp.setIdLuasPajakan(rs.getLong("ID_LUASPAJAKAN") );
				hp.setLuasStringPajakan(rs.getString("LUASPAJAKAN"));
				hp.setIdLuasBersamaanPajakan(rs.getLong("ID_LUASPAJAKANBER") );
				hp.setLuasBersamaanPajakan(rs.getDouble("LUASPAJAKANBER"));
				hp.setNoWarta(Utils.isNull(rs.getString("NO_WARTA")));
				hp.setTarikhWarta(rs.getDate("TARIKH_WARTA"));

				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hp.setNegeri(negeri);

				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hp.setDaerah(daerah);

				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hp.setMukim(mukim);

				hp.setKategori(Utils.isNull(rs.getString("KATEGORI")));
				hp.setSubKategori(Utils.isNull(rs.getString("SUBKATEGORI")));
				hp.setSekatan(Utils.isNull(rs.getString("SEKATAN").toUpperCase()));
				hp.setSyarat(Utils.isNull(rs.getString("SYARAT").toUpperCase()));
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hp.setAgensi(agensi);
				//beanMaklumatTanah.addElement(h);
				//bil++;

			}

		} finally {
			if (db != null)
				db.close();

		}
		return hp;

	}
	public HakmilikPajakan getMaklumatTanahPajakan(String idHakmilikUrusan,String noLot) throws Exception {
		Db db = null;
		String sql = "";
		//beanMaklumatTanah = new Vector();
		HakmilikPajakan hp = null;

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT A.ID_HAKMILIK, A.PEGANGAN_HAKMILIK,A.NO_HAKMILIK, A.NO_LOT" +
				", A.NO_WARTA,A.TARIKH_WARTA" +
				", A.LUAS, A.ID_LUAS" +
				", K.KETERANGAN AS JENIS_LUAS" +
				", K.KOD_LUAS AS KOD_LUAS " +
				", A.ID_LUAS_BERSAMAAN ID_LUAS_BERSAMAAN,A.LUAS_BERSAMAAN " +
				", NVL(A.SYARAT,'TIADA') SYARAT,  NVL(A.SEKATAN,'TIADA') SEKATAN" +
				", B.ID_LOT,B.KOD_LOT, B.KETERANGAN AS JENIS_LOT" +
				", C.ID_NEGERI,C.NAMA_NEGERI" +
				", D.ID_DAERAH,D.NAMA_DAERAH " +
				", E.ID_MUKIM,E.NAMA_MUKIM" +
				", F.ID_JENISHAKMILIK,F.KOD_JENIS_HAKMILIK, F.KETERANGAN AS JENIS_HAKMILIK " +
				", G.KETERANGAN AS SUBKATEGORI " +
				", H.KETERANGAN AS KATEGORI" +
				", I.ID_KEMENTERIAN,I.NAMA_KEMENTERIAN" +
				", J.ID_AGENSI,J.NAMA_AGENSI " +
				", HU.LUAS LUASPAJAKAN,HU.ID_LUAS ID_LUASPAJAKAN "+
				", HU.LUAS_BERSAMAAN LUASPAJAKANBER,HU.ID_LUAS_BERSAMAAN ID_LUASPAJAKANBER " +
				" ,HU.ID_HAKMILIKURUSAN,HU.LUAS_ASAL "+
				" FROM TBLHTPHAKMILIK A, TBLRUJLOT B, TBLRUJNEGERI C, TBLRUJDAERAH D, TBLRUJMUKIM E " +
				" ,TBLRUJJENISHAKMILIK F, TBLRUJSUBKATEGORI G, TBLRUJKATEGORI H, TBLRUJKEMENTERIAN I " +
				" ,TBLRUJAGENSI J, TBLRUJLUAS K,TBLHTPHAKMILIKURUSAN HU "+
				" WHERE A.ID_LOT = B.ID_LOT(+) AND A.ID_NEGERI = C.ID_NEGERI(+) AND A.ID_DAERAH = D.ID_DAERAH(+) " +
				" AND A.ID_MUKIM = E.ID_MUKIM(+) AND A.ID_JENISHAKMILIK = F.ID_JENISHAKMILIK(+) " +
				" AND A.ID_SUBKATEGORI = G.ID_SUBKATEGORI(+) " +
				" AND G.ID_KATEGORI = H.ID_KATEGORI(+) AND A.ID_KEMENTERIAN = I.ID_KEMENTERIAN(+) " +
				//" AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS_BERSAMAAN = K.ID_LUAS " +
				" AND A.ID_AGENSI = J.ID_AGENSI(+) AND A.ID_LUAS = K.ID_LUAS " +
				" AND HU.PEGANGAN_HAKMILIK = A.PEGANGAN_HAKMILIK "+
				" AND HU.ID_HAKMILIKURUSAN = '" + idHakmilikUrusan + "'" +
				" AND A.NO_LOT = '" + noLot + "'"+
				"";
			myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				hp = new HakmilikPajakan();
				Tblrujjenishakmilik jh = new Tblrujjenishakmilik();
				Tblrujlot lot = new Tblrujlot();
				Negeri negeri = new Negeri();
				Daerah daerah = new Daerah();
				Mukim mukim = new Mukim();
				Agensi agensi = new Agensi();
				Kementerian kem = new Kementerian();

				//hp.setIdHakmilikUrusan(Long.parseLong(Utils.isNull(rs.getString("ID_HAKMILIKURUSAN"))));
				hp.setIdHakmilikUrusan(Long.parseLong(idHakmilikUrusan));
				hp.setIdHakmilik(Long.parseLong(Utils.isNull(rs.getString("ID_HAKMILIK"))));
				hp.setPeganganHakmilik(Utils.isNull(rs.getString("PEGANGAN_HAKMILIK")));
				jh.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jh.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				hp.setRujJenisHakmilik(jh);
				hp.setNoHakmlik(Utils.isNull(rs.getString("NO_HAKMILIK")));
				hp.setNoWarta(Utils.isNull(rs.getString("NO_WARTA")));
				hp.setIdJenisLot(rs.getLong("ID_LOT"));
				lot.setKeterangan(rs.getString("JENIS_LOT"));
				lot.setKodLot(rs.getString("KOD_LOT"));
				hp.setRujLot(lot);
				hp.setNoLot(Utils.isNull(rs.getString("NO_LOT")));
				Double db_ = new Double(0.0) ;
				try {
					db_ = rs.getDouble("LUAS_ASAL");
				//}(NumberFormatException e) {
				}catch(NumberFormatException e) {
					
				}
				hp.setLuasAsal(db_);
				hp.setLuasString(rs.getString("LUAS") + " - "+rs.getString("JENIS_LUAS"));
				hp.setIdLuas(rs.getLong("ID_LUAS"));
				hp.setLuasBersamaan(rs.getDouble("LUAS_BERSAMAAN"));
				//Utils.formatLuas(number)
				hp.setIdLuasPajakan(rs.getLong("ID_LUASPAJAKAN") );
				hp.setLuasStringPajakan(rs.getString("LUASPAJAKAN"));
				hp.setIdLuasBersamaanPajakan(rs.getLong("ID_LUASPAJAKANBER") );
				hp.setLuasBersamaanPajakan(rs.getDouble("LUASPAJAKANBER"));
				hp.setNoWarta(Utils.isNull(rs.getString("NO_WARTA")));
				hp.setTarikhWarta(rs.getDate("TARIKH_WARTA"));

				negeri.setIdNegeri(rs.getLong("ID_NEGERI"));
				negeri.setNamaNegeri(rs.getString("NAMA_NEGERI"));
				hp.setNegeri(negeri);

				daerah.setIdDaerah(rs.getLong("ID_DAERAH"));
				daerah.setNamaDaerah(rs.getString("NAMA_DAERAH"));
				hp.setDaerah(daerah);

				mukim.setIdMukim(rs.getLong("ID_MUKIM"));
				mukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				hp.setMukim(mukim);

				hp.setKategori(Utils.isNull(rs.getString("KATEGORI")));
				hp.setSubKategori(Utils.isNull(rs.getString("SUBKATEGORI")));
				hp.setSekatan(Utils.isNull(rs.getString("SEKATAN").toUpperCase()));
				hp.setSyarat(Utils.isNull(rs.getString("SYARAT").toUpperCase()));
				kem.setIdKementerian(rs.getLong("ID_KEMENTERIAN"));
				kem.setNamaKementerian(rs.getString("NAMA_KEMENTERIAN"));
				agensi.setIdAgensi(rs.getLong("ID_AGENSI"));
				agensi.setNamaAgensi(rs.getString("NAMA_AGENSI"));
				agensi.setKementerian(kem);
				hp.setAgensi(agensi);
				//beanMaklumatTanah.addElement(h);
				//bil++;

			}

		} finally {
			if (db != null)
				db.close();

		}
		return hp;

	}

	public String getIdHakmilikUrusan(){
		return this.idHakmilikUrusan;
	}


}

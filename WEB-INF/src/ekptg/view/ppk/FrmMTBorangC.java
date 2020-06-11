package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class FrmMTBorangC extends VTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7536191255673040672L;
	static Logger myLogger = Logger.getLogger(FrmMTBorangC.class);
	protected DbPersistence db = new DbPersistence();

	@SuppressWarnings("rawtypes")
	@Override
	public Template doTemplate() throws Exception {
		String vm = "";
		String submit = request.getParameter("command");
		String submit2 = request.getParameter("commandw");
		String noFail = request.getParameter("noFail");
		
		if (submit2 != "") 
		{
			submit = submit2;
		}

		myLogger.info("COMMAND SUBMIT FORM BORANG C" + submit);

		Hashtable get_MTKeputusan = null;
		get_MTKeputusan = MTKeputusan(noFail);
		String kepBrgC = (String) get_MTKeputusan.get("KEPUTUSAN_BORANG_C") == null ? ""
				: (String) get_MTKeputusan.get("KEPUTUSAN_BORANG_C");
		Date trkhJanaBrgC = (Date) get_MTKeputusan.get("TARIKH_JANA_BORANG_C");
		String extrctKod = (String) get_MTKeputusan.get("EXTRACTION_KOD") == null ? ""
				: (String) get_MTKeputusan.get("EXTRACTION_KOD");
		
		String catatan = (String) get_MTKeputusan.get("CATATAN") == null ? ""
				: (String) get_MTKeputusan.get("CATATAN");
		
		String ID_KAD_BIRU = (String) get_MTKeputusan.get("ID_KAD_BIRU") == null ? ""
				: (String) get_MTKeputusan.get("ID_KAD_BIRU");

		myLogger.info("KEPUTUSAN BORANG C : " + kepBrgC);
		myLogger.info("TARIKH JANA BORANG C : " + trkhJanaBrgC);
		myLogger.info("EXTRACTION KOD : " + extrctKod);

		context.put("kepBrgC", kepBrgC);
		context.put("trkhJanaBrgC", trkhJanaBrgC);
		context.put("extrctKod", extrctKod);
		context.put("kepBrgC", kepBrgC);
		context.put("catatan", catatan);
		context.put("ID_KAD_BIRU", ID_KAD_BIRU);

		Vector getMTFail = MTFail(noFail);
		context.put("MTFail", getMTFail);

		Vector getMTKaveat = MTKaveat(noFail);
		context.put("MTKaveat", getMTKaveat);

		context.put("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));

		vm = "app/ppk/integrasi/BorangC.jsp";

		Template template = this.engine.getTemplate(vm);
		return template;
	}

	public static int MTKeputusanCount(String userID) throws Exception {
		Db db = null;
		String sql = "";
		int total = 0;

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT COUNT(DISTINCT K.IDKADBIRU) AS TOTAL"
					+ " FROM TBLINTMTKEPUTUSAN K, TBLINTMTPERMOHONAN M, TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONAN P WHERE M.FLAG_REP = '3' AND K.IDKADBIRU = M.IDKADBIRU"
					+ " AND M.PETISYENNO = F.NO_FAIL AND F.ID_FAIL = P.ID_FAIL " +
					" AND K.FLAG_AKTIF ='Y' and M.FLAG_AKTIF='Y' " +
					" AND P.ID_STATUS IN (8,9,14,170) " +
					" AND K.FLAG_BUKA = 'T' " +
					" AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
					+ " TBLRUJPEJABATURUSAN u, users_internal ur where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ userID + "' ";
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userID+"  ";
					
					sql += " )";
					
					

			myLogger.info("SQL STATEMENT - KEPUTUSAN MT2 : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				total = rs.getString("TOTAL") == null ? 0 : rs.getInt("TOTAL");
			}
			return total;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector MTKeputusanPenuh(String userID) throws Exception {
		Db db = null;
		try {
			db = new Db();
			// SQLRenderer r = new SQLRenderer();
			String sql = "";

			sql = "SELECT DISTINCT K.IDKADBIRU AS ID_KAD_BIRU, K.KEPUTUSANBORANGC AS KEPUTUSAN_BORANG_C, "
					+ " K.TARIKHJANABORANGC AS TARIKH_JANA_BORANG_C,"
					+ " M.PETISYENNO AS PETISYEN_NO"
					+ " FROM TBLINTMTKEPUTUSAN K, TBLINTMTPERMOHONAN M, TBLPFDFAIL F, TBLPPKPERMOHONAN P WHERE M.FLAG_REP = '3'"
					+ " AND K.IDKADBIRU = M.IDKADBIRU  AND M.PETISYENNO = F.NO_FAIL  AND F.ID_FAIL = P.ID_FAIL"
					+ " and K.FLAG_AKTIF ='Y' and M.FLAG_AKTIF='Y' AND k.flag_buka = 'T' " 
					+ " AND  P.ID_STATUS IN (8,9,14,170) AND P.ID_DAERAHMHN in "
					+ " ( select distinct u.id_daerahurus from TBLRUJPEJABATURUSAN u, users_internal ur"
					+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
					+ userID + "' ";
					
					 sql += " UNION "+                                                                            
					" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userID+"  ";
					
					sql += " ) ORDER BY PETISYEN_NO";

			myLogger.info("SQL STATEMENT - KEPUTUSAN MT PENUH : " + sql);

			ResultSet rs = db.getStatement().executeQuery(sql);
			Vector list = new Vector();
			int bil = 0;
			while (rs.next()) {
				Hashtable h = new Hashtable();
				bil++;
				String kepBrgC = rs.getString("KEPUTUSAN_BORANG_C") == null ? ""
						: rs.getString("KEPUTUSAN_BORANG_C");
				String noPetisyen = rs.getString("PETISYEN_NO") == null ? ""
						: rs.getString("PETISYEN_NO");
				h.put("bil", bil);
				h.put("kepBrgC", kepBrgC);
				h.put("tarikhJanaBrgC",
						rs.getDate("TARIKH_JANA_BORANG_C") == null ? "" : rs
								.getDate("TARIKH_JANA_BORANG_C"));
				h.put("noPetisyen", noPetisyen);
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector MTPermohonanBaru(String userID) throws Exception {
		Db db = null;
		try {
			db = new Db();
			// SQLRenderer r = new SQLRenderer();
			String sql = "";

			sql = "SELECT MTP.*, F.ID_FAIL FROM TBLINTMTPERMOHONAN MTP, TBLPFDFAIL F "
					+ "WHERE (MTP.IDKADBIRU IS NULL OR MTP.IDKADBIRU = '') AND "
					+ "MTP.FLAG_NEGERI IN (SELECT J.ID_NEGERI FROM TBLRUJPEJABATJKPTG J "
					+ "WHERE J.ID_PEJABATJKPTG IN (SELECT U.ID_PEJABATJKPTG FROM USERS_INTERNAL U "
					+ "WHERE U.USER_ID = '"
					+ userID
					+ "')) AND F.NO_FAIL = MTP.PETISYENNO ORDER BY MTP.TARIKHJANABRGB, MTP.PETISYENNO";

			// sql =
			// "SELECT * FROM TBLINTMTPERMOHONAN WHERE (IDKADBIRU IS NULL OR IDKADBIRU = '') "
			// +
			// "AND FLAG_NEGERI IN (SELECT ID_NEGERI FROM TBLRUJPEJABATJKPTG WHERE "
			// +
			// "ID_PEJABATJKPTG IN (SELECT ID_PEJABATJKPTG FROM USERS_INTERNAL WHERE "
			// + "USER_ID = '"
			// + userID
			// + "')) "
			// + "ORDER BY TARIKHJANABRGB, PETISYENNO";

			myLogger.info("SQL STATEMENT - PERMOHONAN MT BARU : " + sql);

			ResultSet rs = db.getStatement().executeQuery(sql);
			Vector list = new Vector();
			int bil = 0;
			while (rs.next()) {
				Hashtable h = new Hashtable();
				bil++;
				String petNo = rs.getString("PETISYENNO") == null ? "" : rs
						.getString("PETISYENNO");
				String namaPemohon = rs.getString("NAMAPEMOHON") == null ? ""
						: rs.getString("NAMAPEMOHON");
				String idFail = rs.getString("ID_FAIL") == null ? "" : rs
						.getString("ID_FAIL");
				h.put("bil", bil);
				h.put("tarikhBrgB", rs.getDate("TARIKHJANABRGB") == null ? ""
						: rs.getDate("TARIKHJANABRGB"));
				h.put("noPet", petNo);
				h.put("pemohon", namaPemohon);
				h.put("noFail", idFail);
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public static Boolean checkRekodKeputusan(String no_fail) throws Exception {
		Db db = null;
		Boolean check = false;
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			String sql = r.add("K.IDKEPUTUSAN AS ID_KEPUTUSAN")
					.add("K.KEPUTUSANBORANGC AS KEPUTUSAN_BORANG_C")
					.add("K.TARIKHJANABORANGC AS TARIKH_JANA_BORANG_C")
					.add("K.IDKADBIRU AS ID_KAD_BIRU")
					.add("K.JENISTRANSAKSI AS JENIS_TRANSAKSI")
					.add("K.TARIKHPROSES AS TARIKH_PROSES")
					.add("K.BORANGCEXTRACTKOD AS EXTRACTION_KOD")
					.add("K.FLAG_REP AS FLAG_REP")
					.add("K.TARIKH_REP AS TARIKH_REP")
					.add("M.PETISYENNO AS PETISYEN_NO").add("M.FLAG_REP", "3")
					.add("M.PETISYENNO", no_fail.trim())
					.relate("K.IDKADBIRU", "M.IDKADBIRU")
					.getSQLSelect("TBLINTMTKEPUTUSAN K, TBLINTMTPERMOHONAN M");
			myLogger.info(" sql checkRekodKeputusan : "+sql);
			ResultSet rs = db.getStatement().executeQuery(sql);

			while (rs.next()) {
				check = true;
			}

			return check;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Hashtable MTKeputusan(String noPetisyen) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT K.IDKEPUTUSAN AS ID_KEPUTUSAN, K.KEPUTUSANBORANGC AS KEPUTUSAN_BORANG_C, "
					+ " K.TARIKHJANABORANGC AS TARIKH_JANA_BORANG_C, K.IDKADBIRU AS ID_KAD_BIRU, K.JENISTRANSAKSI AS JENIS_TRANSAKSI, "
					+ " K.TARIKHPROSES AS TARIKH_PROSES, K.BORANGCEXTRACTKOD AS EXTRACTION_KOD, K.FLAG_REP AS FLAG_REP, "
					+ " K.TARIKH_REP AS TARIKH_REP, K.CATATAN " +
					" FROM TBLINTMTKEPUTUSAN K "
					+ " WHERE K.IDKADBIRU IN "
					+ " (SELECT M.IDKADBIRU FROM TBLINTMTPERMOHONAN M "
					+ " WHERE M.PETISYENNO = '"
					+ noPetisyen
					+ "' AND M.FLAG_REP = '3') AND K.FLAG_AKTIF ='Y'";

			myLogger.info("SQL STATEMENT - KEPUTUSAN MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("ID_KEPUTUSAN", rs.getString("ID_KEPUTUSAN") == null ? ""
						: rs.getString("ID_KEPUTUSAN"));
				h.put("KEPUTUSAN_BORANG_C",
						rs.getString("KEPUTUSAN_BORANG_C") == null ? "" : rs
								.getString("KEPUTUSAN_BORANG_C"));
				h.put("TARIKH_JANA_BORANG_C",
						rs.getDate("TARIKH_JANA_BORANG_C") == null ? "" : rs
								.getDate("TARIKH_JANA_BORANG_C"));
				h.put("ID_KAD_BIRU", rs.getString("ID_KAD_BIRU") == null ? ""
						: rs.getString("ID_KAD_BIRU"));
				h.put("JENIS_TRANSAKSI",
						rs.getString("JENIS_TRANSAKSI") == null ? "" : rs
								.getString("JENIS_TRANSAKSI"));
				h.put("TARIKH_PROSES", rs.getDate("TARIKH_PROSES") == null ? ""
						: rs.getDate("TARIKH_PROSES"));
				h.put("EXTRACTION_KOD",
						rs.getString("EXTRACTION_KOD") == null ? "" : rs
								.getString("EXTRACTION_KOD"));
				h.put("FLAG_REP",
						rs.getString("FLAG_REP") == null ? "" : rs
								.getString("FLAG_REP"));
				h.put("TARIKH_REP",
						rs.getDate("TARIKH_REP") == null ? "" : rs
								.getDate("TARIKH_REP"));
				h.put("CATATAN",
						rs.getString("CATATAN") == null ? "" : rs
								.getString("CATATAN"));
				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector MTFail(String noPetisyen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			/*String sql = r
					.add("F.IDKEPUTUSAN")
					.add("F.NOFAILAWAL as NO_FAIL_AWAL")
					.add("F.NAMAPEMOHONAWAL as NAMA_PEMOHON_AWAL")
					.add("F.NAMAPEJAGENSI as NAMA_PEJ_AGENSI")
					.add("F.TARIKHPERMOHONANAWAL as TARIKH_PERMOHONAN_AWAL")
					.add("K.IDKEPUTUSAN")
					.add("K.IDKADBIRU")
					.add("M.IDKADBIRU")
					.add("M.PETISYENNO")
					.add("M.PETISYENNO", noPetisyen)
					.add("K.CATATAN")
					.relate("K.IDKADBIRU", "M.IDKADBIRU")
					.relate("F.IDKADBIRU", "K.IDKADBIRU")
					.relate("F.IDKADBIRU", "M.IDKADBIRU")

					.getSQLSelect(
							"TBLINTMTPERMOHONAN M, TBLINTMTKEPUTUSAN K, TBLINTMTBRGCFAIL F");
			 		*/
			String sql ="SELECT distinct f.idkeputusan, f.nofailawal AS no_fail_awal, " +
					"  f.namapemohonawal AS nama_pemohon_awal, " +
					"  f.namapejagensi AS nama_pej_agensi, " +
					"   f.tarikhpermohonanawal AS tarikh_permohonan_awal, k.idkeputusan as keputusanK, " +
					"  k.idkadbiru, m.idkadbiru, m.petisyenno, k.catatan, f.IDKEPUTUSAN as IDKEPUTUSAN " +
					"  FROM tblintmtpermohonan m, tblintmtkeputusan k , tblintmtbrgcfail f " +
					"  WHERE m.petisyenno = '"+noPetisyen+"'  " +
					"  AND k.idkadbiru = m.idkadbiru " +
					"  AND f.idkadbiru = k.idkadbiru " +
					"  AND f.idkadbiru = m.idkadbiru " +
					"  AND k.FLAG_AKTIF = 'Y' ";
			
			myLogger.info("SQL STATEMENT - ADA FAIL AWAL MT : " + sql);

			ResultSet rs = db.getStatement().executeQuery(sql);
			Vector list = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				String noFailAwal = rs.getString("NO_FAIL_AWAL") == null ? ""
						: rs.getString("NO_FAIL_AWAL");
				String namaPemohonAwal = rs.getString("NAMA_PEMOHON_AWAL") == null ? ""
						: rs.getString("NAMA_PEMOHON_AWAL");
				String namaPejAgensi = rs.getString("NAMA_PEJ_AGENSI") == null ? ""
						: rs.getString("NAMA_PEJ_AGENSI");
				
				
				String catatan = rs.getString("CATATAN") == null ? ""
						: rs.getString("CATATAN");
				
				h.put("noFailAwal", noFailAwal);
				h.put("namaPemohonAwal", namaPemohonAwal);
				h.put("namaPejAgensi", namaPejAgensi);
				h.put("tarikhPermohonanAwal",
						rs.getDate("TARIKH_PERMOHONAN_AWAL") == null ? "" : rs
								.getDate("TARIKH_PERMOHONAN_AWAL"));
				
				h.put("catatan", catatan);
				
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector MTKaveat(String noPetisyen) throws Exception {
		Db db = null;
		try {
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			String sql = r
					.add("KV.IDKEPUTUSAN")
					.add("KV.NOKAVEAT as NO_KAVEAT")
					.add("KV.TARIKHKAVEAT as TARIKH_KAVEAT")
					.add("KV.NAMAPENGKAVEAT as NAMA_PENGKAVEAT")
					.add("KV.NAMAFIRMA as NAMA_FIRMA")
					.add("K.IDKEPUTUSAN")
					.add("K.IDKADBIRU")
					.add("M.IDKADBIRU")
					.add("M.PETISYENNO")
					.add("M.PETISYENNO", noPetisyen)
					.add("K.CATATAN")
					.relate("K.IDKADBIRU", "M.IDKADBIRU")
					.relate("KV.IDKADBIRU", "K.IDKADBIRU")
					
					.getSQLSelect(
							"TBLINTMTPERMOHONAN M, TBLINTMTKEPUTUSAN K, TBLINTMTBRGCKAVEAT KV");

			myLogger.info("SQL STATEMENT - KAVEAT MT : " + sql);

			ResultSet rs = db.getStatement().executeQuery(sql);
			Vector list = new Vector();
			while (rs.next()) {
				Hashtable h = new Hashtable();
				String noKaveat = rs.getString("NO_KAVEAT") == null ? "" : rs
						.getString("NO_KAVEAT");
				String namaPengkaveat = rs.getString("NAMA_PENGKAVEAT") == null ? ""
						: rs.getString("NAMA_PENGKAVEAT");
				String namaFirma = rs.getString("NAMA_FIRMA") == null ? "" : rs
						.getString("NAMA_FIRMA");
				
				String catatan = rs.getString("CATATAN") == null ? "" : rs
						.getString("CATATAN");
				
				h.put("noKaveat", noKaveat);
				h.put("tarikhKaveat", rs.getDate("TARIKH_KAVEAT") == null ? ""
						: rs.getDate("TARIKH_KAVEAT"));
				h.put("namaPengkaveat", namaPengkaveat);
				h.put("namaFirma", namaFirma);
				h.put("catatan", catatan);
				
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}

	@SuppressWarnings("unused")
	private Date parseDate(String dateTxt) throws java.text.ParseException {
		if (dateTxt != null && !"".equals(dateTxt)) {
			try {
				return new SimpleDateFormat("dd/MM/yyyy").parse(dateTxt);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}
}

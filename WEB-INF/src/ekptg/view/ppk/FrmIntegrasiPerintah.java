package ekptg.view.ppk;

import integrasi.utils.IntLogManager;
import integrasi.ws.mt.MTManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.Utils;

public class FrmIntegrasiPerintah extends VTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmIntegrasiPerintah.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Template doTemplate() throws Exception {
		HttpSession session = this.request.getSession();
		Date now = new Date();
		String vm = "";
		String submit = request.getParameter("command");
		String idFail = request.getParameter("idfail");

		System.out.println("Command : " + submit);

		if ("borangPerintah".equals(submit)) {
			Hashtable get_checkNoFail = null;
			get_checkNoFail = checkNoFail(idFail);
			String jumlahPerintah = (String) get_checkNoFail
					.get("JUMLAH_PERINTAH");

			if ("0".equals(jumlahPerintah)) {
				context.put("semakPerintah", "tiada");
			} else {
				context.put("semakPerintah", "ada");
			}
		
			Hashtable get_bluecardId = null;
			get_bluecardId = getBlueCardId(idFail);
			String blueCardId = (String) get_bluecardId
					.get("IDKADBIRU");
					
			Hashtable get_perintahMT = null;
			get_perintahMT = perintahMT(idFail);
			String noFail = (String) get_perintahMT.get("NO_FAIL");
			String namaSimati = (String) get_perintahMT.get("NAMA_SIMATI");
			String namaSimatiLain = (String) get_perintahMT
					.get("NAMA_LAIN_SIMATI");
			String noKPSimatiBaru = (String) get_perintahMT.get("NO_KP_BARU");
			String noKPSimatiLama = (String) get_perintahMT.get("NO_KP_LAMA");
			String noKPSimatiLain = (String) get_perintahMT.get("NO_KP_LAIN");
			String tarikhMati = (String) get_perintahMT.get("TARIKH_MATI");
			String tarikhPerintah = (String) get_perintahMT
					.get("TARIKH_PERINTAH");
			String idPerintah = (String) get_perintahMT.get("ID_PERINTAH");
			String namaOBHAPerintah = (String) get_perintahMT
					.get("NAMA_OB_HA_PERINTAH");
			String jenisPerintah = (String) get_perintahMT
					.get("JENIS_PERINTAH");
			

			
			noKPSimatiBaru = noKPSimatiBaru.replace("-","");

			//COMMENT BY BELLA - MAHKAMAH CEK DENGAN JPN TARIKH MATI SAHAJA
//			//aishahlatip
//			String waktuMati = waktuMati = "0000";//			
////			String WAKTU_KEMATIAN = (String) get_perintahMT.get("WAKTU_KEMATIAN");
////			
////			String JAM = WAKTU_KEMATIAN.substring(0,2);
////			String MINIT = WAKTU_KEMATIAN.substring(2);
////			
////			String JENIS_WAKTU_MATI = (String) get_perintahMT.get("JENIS_WAKTU_MATI");
////			
////			String waktuMati = "";
////			
////			
////			int jam_1 = 0;
////			if(!JENIS_WAKTU_MATI.equals(null)){
////				
////				if(!JENIS_WAKTU_MATI.equals("1")){
////					jam_1 = Integer.parseInt(JAM) + 12;
////					waktuMati = Integer.toString(jam_1)+MINIT;
////				}
////				else{
////					waktuMati = WAKTU_KEMATIAN;
////				}
////				
////			
////			}else{
////				waktuMati = "0000";
////			}
//			
//			String formatTarikhMati =  tarikhMati+"T"+ waktuMati.substring(0,2)+":"+waktuMati.substring(2)+":00.00";
			String formatTarikhMati = tarikhMati + "T00:00:00.00";
			
			//String reportDate = dateFormat.format(date);
			
			String kodPejabat = (String) get_perintahMT.get("kodPejabat");
			String namaPejabat = getPejabatJKPTGByKodPejabat(kodPejabat);

			tarikhPerintah = tarikhPerintah+"T"+"00:00:00"+".00";
			context.put("noFail", noFail);
			context.put("namaSimati", namaSimati);
			context.put("namaSimatiLain", namaSimatiLain);
			context.put("noKPSimatiBaru", noKPSimatiBaru);
			context.put("noKPSimatiLama", noKPSimatiLama);
			context.put("noKPSimatiLain", noKPSimatiLain);
			//context.put("tarikhMati", tarikhMati);
			context.put("tarikhMati", formatTarikhMati);
			context.put("tarikhPerintah", tarikhPerintah);
			context.put("idPerintah", idPerintah);
			context.put("namaOBHAPerintah", namaOBHAPerintah);
			context.put("jenisPerintah", jenisPerintah);
			context.put("now", now);
			context.put("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
			context.put("blueCardId", blueCardId);
			context.put("kodPejabat", kodPejabat);
			context.put("namaPejabat", namaPejabat);
			

			vm = "app/ppk/integrasi/MahkamahTinggiPerintah.jsp";
		} else if ("hantarPerintah".equals(submit)) {
			
			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);

			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			String reportDate = dateFormat.format(date);
			String dateHantarNotisPerintah = reportDate.substring(0,10)+"T"+reportDate.substring(11)+".00";
			
			
			String tarikhMati = request.getParameter("tarikhMati");
			String cutDate = tarikhMati.substring(0,10);
			String cutTime = tarikhMati.substring(10);
						
			String[] parts = cutDate.split("-");
			String part1 = parts[0]; 
			String part2 = parts[1]; 
			String part3 = parts[2]; 
			
			String DDate = part3+"/"+part2+"/"+part1;
			
			
			String tarikhPerintah = request.getParameter("tarikhPerintah");
			String cutDatePerintah = tarikhPerintah.substring(0,10);
			String cutTimePerintah = tarikhPerintah.substring(10);
						
			String[] partPs = cutDate.split("-");
			String partP1 = partPs[0]; 
			String partP2 = partPs[1]; 
			String partP3 = partPs[2]; 
			
			String PDate = partP3+"/"+partP2+"/"+partP1;			
			String blueCardId = request.getParameter("blueCardId");
			
			
			Db db = null;
			String sql = "";

			db = new Db();
			Statement stmt = db.getStatement();

			SQLRenderer r = new SQLRenderer();
					
			String noFail = request.getParameter("noFail");
			r.add("NOFAIL", request.getParameter("noFail"));
			r.add("NAMASIMATI", request.getParameter("namaSimati"));
			r.add("NAMALAINSIMATI", request.getParameter("namaSimatiLain"));
			r.add("NOKPSIMATIBARU", request.getParameter("noKPSimatiBaru"));
			r.add("NOKPSIMATILAMA", request.getParameter("noKPSimatiLama"));
			r.add("NOKPSIMATILAIN", request.getParameter("noKPSimatiLain"));
			r.add("TARIKHMATI",
					r.unquote("to_date('" + DDate
							+ "','dd/MM/yyyy')"));
			r.add("TARIKHPERINTAH",
					r.unquote("to_date('"
							+ PDate
							+ "','dd/MM/yyyy')"));
			r.add("WARISPEMBAHAGIAN", request.getParameter("namaOBHAPerintah"));
			r.add("IDPERINTAH", request.getParameter("idPerintah"));
			r.add("JENISPERINTAH", request.getParameter("jenisPerintah"));
			r.add("TARIKH_HANTAR", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','DD/MM/YYYY')"));
			r.add("ID_TRANSAKSI", transactionID);

			context.put("noFail", request.getParameter("noFail"));
			context.put("namaSimati", request.getParameter("namaSimati"));
			context.put("namaLainSimati",
					request.getParameter("namaSimatiLain"));
			context.put("MyIDSimati", request.getParameter("noKPSimatiBaru"));
			context.put("ICLamaSimati", request.getParameter("noKPSimatiLama"));
			context.put("ICLainSimati", request.getParameter("noKPSimatiLain"));
			context.put("tarikhMati", request.getParameter("tarikhMati"));
			context.put("tarikhPerintah",
					request.getParameter("tarikhPerintah"));
			context.put("namaOBHAPerintah",
					request.getParameter("namaOBHAPerintah"));
			context.put("idPerintah", request.getParameter("idPerintah"));
			context.put("jenisPerintah", request.getParameter("jenisPerintah"));
			
			
			//aishah start integration ecourt
			
			MTManager manager = new MTManager();
			
			String returnMessage = "";
			returnMessage = 
			manager.sendMaklumatPerintah2Court(
					request.getParameter("noFail"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("namaPemohon"),
					request.getParameter("noKPPemohon"),
					request.getParameter("hubSimatiPemohon"),
					request.getParameter("tarikhPerintah"),
					dateHantarNotisPerintah,
					request.getParameter("kodPejabat"),
					getPejabatJKPTGByKodPejabat(request.getParameter("kodPejabat")),
					request.getParameter("idnegeri"),
					request.getParameter("jeniskp"),
					"G",request.getParameter("namaOBHAPerintah"), blueCardId, transactionID );
			
			//System.out.println("returnMessage==="+returnMessage);
			
			if(!returnMessage.equals("") || !returnMessage.contains("null")){
			
				String code = returnMessage.substring(0,1);
				String details =  returnMessage.substring(2);
				
				//System.out.println("code==="+code);
				//System.out.println("details==="+details);
				
				if (code.equals("0")){
				
					sql = r.getSQLInsert("TBLINTMTPERINTAH");
					
	
					myLogger.info("SQL INSERT TBLINTMTPERINTAH : " + sql);
					stmt.executeUpdate(sql);
					
					vm = "app/ppk/integrasi/MahkamahTinggiPerintahSuccess.jsp";
					
					IntLogManager.recordLogMT(noFail, "G", "O", "Y", "SUCCESS");
					
				}else{
					
					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiPerintahFailed.jsp";
					
					IntLogManager.recordLogMT(noFail, "G", "O", "T", details);
				}
				
			}else{
				
				System.out.println("33333333333");
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT(noFail, "G", "O", "T", returnMessage);
			}

			//vm = "app/ppk/integrasi/MahkamahTinggiPerintahSuccess.jsp";
		}

		Template template = this.engine.getTemplate(vm);
		return template;
	}

	public Hashtable checkNoFail(String id_fail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT (SELECT COUNT (*)"
					+ " FROM TBLINTMTPERINTAH MTP, TBLPFDFAIL PFD"
					+ " WHERE MTP.NOFAIL = PFD.NO_FAIL"
					+ " AND PFD.ID_FAIL = '" + id_fail
					+ "') AS JUMLAH_PERINTAH" + " FROM DUAL";

			myLogger.info("SQL STATEMENT - PERINTAH MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("JUMLAH_PERINTAH",
						rs.getString("JUMLAH_PERINTAH") == null ? "" : rs
								.getString("JUMLAH_PERINTAH"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Hashtable perintahMT(String id_fail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT DISTINCT a.no_fail, UPPER (g.nama_simati) AS nama_simati,"
					+ " NVL (g.nama_lain, '') AS nama_lain_simati,"
					+ " CASE"
					+ " WHEN g.no_kp_baru IS NOT NULL"
					+ " AND LENGTH (RTRIM (g.no_kp_baru)) = 12"
					+ " THEN    SUBSTR (g.no_kp_baru, 1, 6)"
					+ "      || '-'"
					+ "      || SUBSTR (g.no_kp_baru, 7, 2)"
					+ "      || '-'"
					+ "      || SUBSTR (g.no_kp_baru, 9, 4)"
					+ " END AS no_kp_baru,"
					+ " CASE"
					+ " WHEN g.no_kp_lama IS NOT NULL"
					+ " AND LENGTH (RTRIM (g.no_kp_lama)) = 8"
					+ " THEN g.no_kp_lama"
					+ " END AS no_kp_lama,"
					+ " CASE"
					+ " WHEN g.no_kp_lain IS NOT NULL"
					+ " AND g.no_kp_baru IS NULL"
					+ " AND g.no_kp_lama IS NULL"
					+ " THEN g.no_kp_lain"
					+ " END AS no_kp_lain,"
					+ " TO_CHAR (g.tarikh_mati, 'YYYY-MM-DD') AS tarikh_mati,"
					+ " TO_CHAR (e.tarikh_perintah, 'YYYY-MM-DD') AS tarikh_perintah, " +
					" g.WAKTU_KEMATIAN AS WAKTU_KEMATIAN, g.JENIS_WAKTU_MATI, "
					+ " e.id_perintah,"
					+ " CASE"
					+ " WHEN k.id_ob IS NOT NULL"
					+ " AND l.id_ob IS NOT NULL"
					+ " THEN (SELECT nama_ob"
					+ "         FROM tblppkob"
					+ "        WHERE id_ob = k.id_ob AND ROWNUM <= 1)"
					+ " WHEN k.id_ob IS NOT NULL AND l.id_ob IS NULL"
					+ " THEN (SELECT nama_ob"
					+ "         FROM tblppkob"
					+ "        WHERE id_ob = k.id_ob AND ROWNUM <= 1)"
					+ " ELSE (SELECT nama_ob"
					+ "      FROM tblppkob"
					+ "     WHERE id_ob = l.id_ob AND ROWNUM <= 1)"
					+ " END AS nama_ob_ha_perintah,"
					+ " CASE"
					+ " WHEN k.id_ob IS NOT NULL"
					+ " AND l.id_ob IS NOT NULL"
					+ " THEN (SELECT keterangan"
					+ "         FROM tblppkrujjenisperintah"
					+ "        WHERE id_jenisperintah = h.id_jenisperintah)"
					+ " WHEN k.id_ob IS NOT NULL AND l.id_ob IS NULL"
					+ " THEN (SELECT keterangan"
					+ "         FROM tblppkrujjenisperintah"
					+ "        WHERE id_jenisperintah = h.id_jenisperintah)"
					+ " ELSE (SELECT keterangan"
					+ "      FROM tblppkrujjenisperintah"
					+ "     WHERE id_jenisperintah = i.id_jenisperintah)"
					+ " END AS jenis_perintah,"
										
					 + " ( " + " select " + " RPU.ID_PEJABATJKPTG " +
					 " from  " +
					  " TBLRUJPEJABATURUSAN RPU,TBLRUJPEJABATJKPTG RP " +
					  " where RPU.ID_PEJABATJKPTG = RP.ID_PEJABATJKPTG " +
					  " and RPU.ID_JENISPEJABAT = 22 " +
					  " AND RP.ID_SEKSYEN = 2 " +
					  " and RPU.ID_DAERAHURUS = B.ID_DAERAHMHN " +
					  ") AS kodPejabat"
					+ " FROM tblpfdfail a,"
					+ " tblppkpermohonan b,"
					+ " tblppkkeputusanpermohonan c,"
					+ " tblppkperbicaraan d,"
					+ " tblppkperintah e,"
					+ " tblppkpermohonansimati f,"
					+ " tblppksimati g,"
					+ " tblppkperintahhtaobmst h,"
					+ " tblppkperintahhaobmst i,"
					+ " tblppkrujjenisperintah j,"
					+ " tblppkperintahhtaobdtl k,"
					+ " tblppkperintahhaobdtl l"
					+ " WHERE a.id_fail = b.id_fail"
					+ " AND b.id_permohonan = c.id_permohonan"
					+ " AND c.id_keputusanpermohonan = d.id_keputusanpermohonan"
					+ " AND d.id_perbicaraan = e.id_perbicaraan"
					+ " AND b.id_permohonan = f.id_permohonan"
					+ " AND f.id_simati = g.id_simati"
					+ " AND e.id_perintah = h.id_perintah(+)"
					+ " AND e.id_perintah = i.id_perintah(+)"
					+ " AND i.id_jenisperintah = j.id_jenisperintah(+)"
					+ " AND h.id_perintahhtaobmst = k.id_perintahhtaobmst(+)"
					+ " AND i.id_perintahhaobmst = l.id_perintahhaobmst(+)"
					+ " AND e.flag_jenis_keputusan = 0"
					+ " AND a.id_fail = '"
					+ id_fail + "'";

			myLogger.info("SQL STATEMENT - PERINTAH MT : " + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("NO_FAIL",
						rs.getString("NO_FAIL") == null ? "" : rs
								.getString("NO_FAIL"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI") == null ? ""
						: rs.getString("NAMA_SIMATI"));
				h.put("NAMA_LAIN_SIMATI",
						rs.getString("NAMA_LAIN_SIMATI") == null ? "" : rs
								.getString("NAMA_LAIN_SIMATI"));
				h.put("NO_KP_BARU", rs.getString("NO_KP_BARU") == null ? ""
						: rs.getString("NO_KP_BARU"));
				h.put("NO_KP_LAMA", rs.getString("NO_KP_LAMA") == null ? ""
						: rs.getString("NO_KP_LAMA"));
				h.put("NO_KP_LAIN", rs.getString("NO_KP_LAIN") == null ? ""
						: rs.getString("NO_KP_LAIN"));
				h.put("TARIKH_MATI", rs.getString("TARIKH_MATI") == null ? ""
						: rs.getString("TARIKH_MATI"));
				h.put("TARIKH_PERINTAH",
						rs.getString("TARIKH_PERINTAH") == null ? "" : rs
								.getString("TARIKH_PERINTAH"));
				h.put("ID_PERINTAH", rs.getString("ID_PERINTAH") == null ? ""
						: rs.getString("ID_PERINTAH"));
				h.put("NAMA_OB_HA_PERINTAH",
						rs.getString("NAMA_OB_HA_PERINTAH") == null ? "" : rs
								.getString("NAMA_OB_HA_PERINTAH"));
				h.put("JENIS_PERINTAH",
						rs.getString("JENIS_PERINTAH") == null ? "" : rs
								.getString("JENIS_PERINTAH"));
				
				h.put("WAKTU_KEMATIAN",
						rs.getString("WAKTU_KEMATIAN") == null ? "" : rs
								.getString("WAKTU_KEMATIAN"));
				
				h.put("JENIS_WAKTU_MATI",
						rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs
								.getString("JENIS_WAKTU_MATI"));
				h.put(
						"kodPejabat",
						rs.getString("kodPejabat") == null ? "" : rs
								.getString("kodPejabat"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//ecah
	public Hashtable getBlueCardId(String id_fail) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "SELECT MTM.IDKADBIRU " +
					" FROM  TBLPFDFAIL PFD, TBLINTMTKEPUTUSAN MTK, TBLINTMTPERMOHONAN MTM " +
					" WHERE MTM.PETISYENNO = PFD.NO_FAIL " +
					" AND MTM.IDKADBIRU = MTK.IDKADBIRU " +
					"  AND PFD.ID_FAIL ="+id_fail;

			myLogger.info("SQL STATEMENT - BLUECARDID MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				h.put("IDKADBIRU",
						rs.getString("IDKADBIRU") == null ? "" : rs
								.getString("IDKADBIRU"));
				
				
				System.out.println("IDKADBIRU==="+rs.getString("IDKADBIRU"));
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public String getPejabatJKPTGByKodPejabat(String kodPejabat) {
		Db db = null;
		String sql = "";
		String namaPejabat = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT pj.nama_pejabat AS namapejabat"
					+ " FROM tblintmtkodpej mpj, " + "tblrujpejabatjkptg pj"
					+ " WHERE pj.id_pejabatjkptg = mpj.kodpejabat"
					+ " AND mpj.kodpejabat = '" + kodPejabat + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				namaPejabat = rs.getString("namaPejabat");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return namaPejabat;
	}
}
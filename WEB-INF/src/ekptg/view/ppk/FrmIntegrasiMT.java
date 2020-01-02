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

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmIntegrasiMT extends VTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmIntegrasiMT.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Template doTemplate() throws Exception {

		HttpSession session = this.request.getSession();

		String user = (String) session.getAttribute("_portal_login");
		String usid = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", usid);

		String vm = "";
		String submit = request.getParameter("command");
		String fFrom = request.getParameter("frmFrom") != null ? (String) request.getParameter("frmFrom") : "";
		String idPermohonan = getParam("idPermohonan");

		if ("borangPermohonan".equals(submit)) {
			String idFail = request.getParameter("idFail");

			// CHECK PERMOHONAN
			if (existPermohonan(idFail)) {
				context.put("semakPermohonan", "ada");
			} else {
				context.put("semakPermohonan", "tiada");
			}

			// CHECK EXIST PETIONER
			if (existPetioner(idFail)) {
				context.put("semakPetioner", "ada");
			} else {
				context.put("semakPetioner", "tiada");
			}

			// CHECK SUCCESS SENT
			if (successSend(idFail)) {
				context.put("successSend", "ya");
			} else {
				context.put("successSend", "tidak");
			}

			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String noPetisyen = (String) permohonanMT.get("noPetisyen");
			String namaSimati = (String) permohonanMT.get("namaSimati");
			String namaSimatiLain = (String) permohonanMT.get("namaSimatiLain");
			String noKPSimatiBaru = (String) permohonanMT.get("noKPSimatiBaru");
			String noKPSimatiLama = (String) permohonanMT.get("noKPSimatiLama");
			String noKPSimatiLain = (String) permohonanMT.get("noKPSimatiLain");
			String jeniskp = (String) permohonanMT.get("jeniskp");
			String tarikhMati = (String) permohonanMT.get("tarikhMati");
			String namaPemohon = (String) permohonanMT.get("namaPemohon");
			String noKPBaruPemohon = (String) permohonanMT.get("noKPBaruPemohon");
			String noKPLamaPemohon = (String) permohonanMT.get("noKPLamaPemohon");
			String noKPLainPemohon = (String) permohonanMT.get("noKPLainPemohon");
			String hubSimatiPemohon = (String) permohonanMT.get("hubSimatiPemohon");
			String idhubSimatiPemohon = (String) permohonanMT.get("ID_HUBSIMATIPEMOHON");
			String idnegeri = (String) permohonanMT.get("idnegeri");
			idPermohonan = (String) permohonanMT.get("idPermohonan");
			
			//COMMENT BY BELLA - MAHKAMAH CEK DENGAN JPN TARIKH MATI SAHAJA
//			String WAKTU_KEMATIAN = (String) permohonanMT.get("WAKTU_KEMATIAN");
//			String JAM = "";
//			String MINIT = "";

//			if (!WAKTU_KEMATIAN.equals("")) {
//				JAM = WAKTU_KEMATIAN.substring(0, 2);
//				MINIT = WAKTU_KEMATIAN.substring(2);
//			}
//			String JENIS_WAKTU_MATI = (String) permohonanMT.get("JENIS_WAKTU_MATI");

//			String waktuMati = waktuMati = "0000";			
//			int jam_1 = 0;
//			if (!JENIS_WAKTU_MATI.equals("")) {
//				if (!JENIS_WAKTU_MATI.equals("1")) {
//					jam_1 = Integer.parseInt(JAM) + 12;
//					waktuMati = Integer.toString(jam_1) + MINIT;
//				} else {
//					waktuMati = WAKTU_KEMATIAN;
//				}
//			} else {
//				waktuMati = "0000";
//			}

			// 2016-06-21T17:24:01.00
//			String formatTarikhMati = tarikhMati + "T" + waktuMati.substring(0, 2) + ":" + waktuMati.substring(2) + ":00.00";
			String formatTarikhMati = tarikhMati + "T00:00:00.00";

			// GET TARIKH HANTAR BORANG B IF TELAH DIHANTAR.
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String reportDate = dateFormat.format(date);
			String dateHantarBorangB = reportDate.substring(0, 10) + "T" + reportDate.substring(11) + ".00";

			String hubSimatiPemohon_PM = "";
			String hubSimatiPemohon_OB = "";

			if (!hubSimatiPemohon_PM.equals("")) {
				hubSimatiPemohon = hubSimatiPemohon_PM;

			} else if (!hubSimatiPemohon_OB.equals("")) {
				hubSimatiPemohon = hubSimatiPemohon_OB;
			}

			String tarikhJanaBorangB = (String) permohonanMT.get("tarikhJanaBorangB");
			String kodPejabat = (String) permohonanMT.get("kodPejabat");

			String namaPejabat = getPejabatJKPTGByKodPejabat(kodPejabat);
			String jenisTransaksi = (String) permohonanMT.get("jenisTransaksi");

			context.put("noPetisyen", noPetisyen);
			context.put("namaSimati", namaSimati);
			context.put("namaSimatiLain", namaSimatiLain);
			context.put("noKPSimatiBaru", noKPSimatiBaru);
			context.put("noKPSimatiLama", noKPSimatiLama);
			context.put("noKPSimatiLain", noKPSimatiLain);
			// context.put("tarikhMati", tarikhMati);
			context.put("tarikhMati", formatTarikhMati);
			context.put("namaPemohon", namaPemohon);
			context.put("noKPBaruPemohon", noKPBaruPemohon);
			context.put("noKPLamaPemohon", noKPLamaPemohon);
			context.put("noKPLainPemohon", noKPLainPemohon);
			context.put("hubSimatiPemohon", hubSimatiPemohon);
			context.put("tarikhJanaBorangB", tarikhJanaBorangB);
			context.put("kodPejabat", kodPejabat);
			context.put("namaPejabat", namaPejabat);
			context.put("jenisTransaksi", jenisTransaksi);
			context.put("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
			context.put("idhubSimatiPemohon", idhubSimatiPemohon);
			context.put("hubSimatiPemohonXXXX", hubSimatiPemohon);
			context.put("idnegeri", idnegeri);
			context.put("dateHantarBorangB", dateHantarBorangB);
			context.put("jeniskp", jeniskp);
			context.put("idFail", idFail);
			context.put("idPermohonan", idPermohonan);

			vm = "app/ppk/integrasi/MahkamahTinggi.jsp";

		} else if ("hantarPermohonan".equals(submit)) {
			
			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);

			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");
			String BDate = tarikhJanaBorangB.substring(0, 10);

			String[] Bparts = BDate.split("-");
			String Bpart1 = Bparts[0];
			String Bpart2 = Bparts[1];
			String Bpart3 = Bparts[2];
			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;

			Db db = null;
			String sql = "";
			String sql2 = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Statement stmt2 = db.getStatement();
			SQLRenderer kptsn = new SQLRenderer();
			String noFail = request.getParameter("noPetisyen");
			r.add("PETISYENNO", request.getParameter("noPetisyen"));
			r.add("NAMASIMATI", request.getParameter("namaSimati"));
			r.add("NAMASIMATILAIN", request.getParameter("namaSimatiLain"));
			r.add("NOKPBARUSIMATI", request.getParameter("noKPSimatiBaru"));
			r.add("NOKPLAMASIMATI", request.getParameter("noKPSimatiLama"));
			r.add("NOKPLAINSIMATI", request.getParameter("noKPSimatiLain"));
			r.add("TARIKHMATI", r.unquote("to_date('" + DDate + "','DD/MM/YYYY')"));
			r.add("NAMAPEMOHON", request.getParameter("namaPemohon"));
			r.add("TARIKHJANABRGB", r.unquote("to_date('" + BDate + "','DD/MM/YYYY')"));
			r.add("NOKPBARUPEMOHON", request.getParameter("noKPPemohon"));
			r.add("HUBUNGAN", request.getParameter("hubSimatiPemohon"));
			r.add("KODPEJABAT", request.getParameter("kodPejabat"));
			r.add("JENISTRANSAKSI", request.getParameter("applicationType"));
			r.add("FLAG_REP", "0");
			r.add("FLAG_NEGERI", request.getParameter("idnegeri"));// ecah
			r.add("JENISKP", request.getParameter("jeniskp"));
			r.add("TARIKH_HANTAR", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','DD/MM/YYYY')"));
			r.add("ID_TRANSAKSI", transactionID);

			context.put("noPetisyen", request.getParameter("noPetisyen"));
			context.put("namaSimati", request.getParameter("namaSimati"));
			context.put("namaLainSimati", request.getParameter("namaSimatiLain"));
			context.put("MyIDSimati", request.getParameter("noKPSimatiBaru"));
			context.put("ICLamaSimati", request.getParameter("noKPSimatiLama"));
			context.put("ICLainSimati", request.getParameter("noKPSimatiLain"));
			context.put("tarikhMati", request.getParameter("tarikhMati"));
			context.put("namaPemohon", request.getParameter("namaPemohon"));
			context.put("MyIDPemohon", request.getParameter("noKPPemohon"));
			context.put("hubungan", request.getParameter("hubSimatiPemohon"));
			context.put("tarikhJanaBorangB", request.getParameter("tarikhJanaBorangB"));
			context.put("tarikhHantarBorangB", request.getParameter("tarikhHantarBorangB"));
			context.put("kodPejabat", request.getParameter("kodPejabat"));
			context.put("namaPejabat", request.getParameter("namaPejabat"));
			context.put("idnegeri", request.getParameter("idnegeri"));
			context.put("jeniskp", request.getParameter("jeniskp"));

			if (!"".equals(fFrom)) {
				context.put("fFrom", "ya");
			} else {
				context.put("fFrom", "tidak");
			}

			// aishah start integration ecourt
			MTManager manager = new MTManager();

			String returnMessage = "";
			returnMessage = manager.sendMaklumat2Court(
					request.getParameter("noPetisyen"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("namaPemohon"),
					request.getParameter("noKPPemohon"),
					request.getParameter("hubSimatiPemohon"),
					request.getParameter("tarikhJanaBorangB"),
					request.getParameter("tarikhHantarBorangB"),
					request.getParameter("kodPejabat"),
					getPejabatJKPTGByKodPejabat(request.getParameter("kodPejabat")),
					request.getParameter("idnegeri"),
					request.getParameter("jeniskp"),
					request.getParameter("applicationType"), transactionID);

			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					if (manager.getIdKadBiru() != null) {
						r.add("IDKADBIRU", manager.getIdKadBiru());
					}
					sql = r.getSQLInsert("TBLINTMTPERMOHONAN");
					stmt.executeUpdate(sql);

					// TODO - update tarikh hantar borang B di
					// TBLPPKKEPUTUSANPERMOHONAN
					long idKeputusanPermohonan = DB.getNextID("TBLPPKKEPUTUSANPERMOHONAN_SEQ");
					kptsn.add("ID_KEPUTUSANPERMOHONAN", idKeputusanPermohonan);
					kptsn.add("ID_PERMOHONAN", idPermohonan);
					kptsn.add("TARIKH_HANTAR_BORANGB", r.unquote("SYSDATE"));
					sql2 = kptsn.getSQLInsert("TBLPPKKEPUTUSANPERMOHONAN");
					stmt2.executeUpdate(sql2);

					vm = "app/ppk/integrasi/MahkamahTinggiSuccess.jsp";
					
					IntLogManager.recordLogMT(noFail, "I", "O", "Y", "SUCCESS");
				} else {

					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";

					IntLogManager.recordLogMT(noFail, "I", "O", "T", details);
				}

			} else {
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT(noFail, "I", "O", "T", returnMessage);
			}		

		} else if ("hantarPermohonanPetioner".equals(submit)) {
			
			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);


			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");
			String BDate = tarikhJanaBorangB.substring(0, 10);

			String[] Bparts = BDate.split("-");
			String Bpart1 = Bparts[0];
			String Bpart2 = Bparts[1];
			String Bpart3 = Bparts[2];
			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;

			Db db = null;
			String sql = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String id_Permohonan = request.getParameter("idPermohonan");
			String noFail = request.getParameter("noPetisyen");
			context.put("noPetisyen", request.getParameter("noPetisyen"));
			context.put("namaSimati", request.getParameter("namaSimati"));
			context.put("namaLainSimati", request.getParameter("namaSimatiLain"));
			context.put("MyIDSimati", request.getParameter("noKPSimatiBaru"));
			context.put("ICLamaSimati", request.getParameter("noKPSimatiLama"));
			context.put("ICLainSimati", request.getParameter("noKPSimatiLain"));
			context.put("tarikhMati", request.getParameter("tarikhMati"));
			context.put("namaPemohon", request.getParameter("namaPemohon"));
			context.put("MyIDPemohon", request.getParameter("noKPPemohon"));
			context.put("hubungan", request.getParameter("hubSimatiPemohon"));
			context.put("tarikhJanaBorangB", request.getParameter("tarikhJanaBorangB"));
			context.put("tarikhHantarBorangB", request.getParameter("tarikhHantarBorangB"));
			context.put("kodPejabat", request.getParameter("kodPejabat"));
			context.put("namaPejabat", request.getParameter("namaPejabat"));
			context.put("idnegeri", request.getParameter("idnegeri"));
			context.put("jeniskp", request.getParameter("jeniskp"));

			if (!"".equals(fFrom)) {
				context.put("fFrom", "ya");
			} else {
				context.put("fFrom", "tidak");
			}

			String no_fail = request.getParameter("noPetisyen");
			String blueCardId = "";
			// aishah add 26072017
			String sqlGetBlueCardId = "SELECT IDKADBIRU FROM TBLINTMTPERMOHONAN WHERE FLAG_AKTIF = 'Y' AND  PETISYENNO = '" + no_fail + "'";

			ResultSet rsKadBiru = stmt.executeQuery(sqlGetBlueCardId);
			if (rsKadBiru.next()) {
				if (rsKadBiru.getString("IDKADBIRU") != null) {
					blueCardId = rsKadBiru.getString("IDKADBIRU");
				}
			}		

			// aishah start integration ecourt
			MTManager manager = new MTManager();

			String returnMessage = "";
			returnMessage = manager.sendMaklumat2CourtPetioner(
					request.getParameter("noPetisyen"),
					request.getParameter("namaSimati"),
					request.getParameter("namaSimatiLain"),
					request.getParameter("noKPSimatiBaru"),
					request.getParameter("noKPSimatiLama"),
					request.getParameter("noKPSimatiLain"),
					request.getParameter("tarikhMati"),
					request.getParameter("namaPemohon"),
					request.getParameter("noKPPemohon"),
					request.getParameter("hubSimatiPemohon"),
					request.getParameter("tarikhJanaBorangB"),
					request.getParameter("tarikhHantarBorangB"),
					request.getParameter("kodPejabat"),
					getPejabatJKPTGByKodPejabat(request.getParameter("kodPejabat")),
					request.getParameter("idnegeri"),
					request.getParameter("jeniskp"),
					request.getParameter("applicationType"), blueCardId, transactionID);

			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					sql = "UPDATE TBLINTMTPERMOHONAN SET FLAG_AKTIF = 'T' WHERE PETISYENNO = '" + no_fail + "'";
					stmt.executeUpdate(sql);
					
					r = new SQLRenderer();
					r.add("PETISYENNO", request.getParameter("noPetisyen"));
					r.add("NAMASIMATI", request.getParameter("namaSimati"));
					r.add("NAMASIMATILAIN", request.getParameter("namaSimatiLain"));
					r.add("NOKPBARUSIMATI", request.getParameter("noKPSimatiBaru"));
					r.add("NOKPLAMASIMATI", request.getParameter("noKPSimatiLama"));
					r.add("NOKPLAINSIMATI", request.getParameter("noKPSimatiLain"));
					r.add("TARIKHMATI", r.unquote("to_date('" + DDate + "','DD/MM/YYYY')"));
					r.add("NAMAPEMOHON", request.getParameter("namaPemohon"));
					r.add("TARIKHJANABRGB", r.unquote("to_date('" + BDate + "','DD/MM/YYYY')"));
					r.add("NOKPBARUPEMOHON", request.getParameter("noKPPemohon"));
					r.add("HUBUNGAN", request.getParameter("hubSimatiPemohon"));
					r.add("KODPEJABAT", request.getParameter("kodPejabat"));
					r.add("JENISTRANSAKSI", request.getParameter("applicationType"));
					r.add("FLAG_REP", "0");
					r.add("FLAG_NEGERI", request.getParameter("idnegeri"));// ecah
					r.add("JENISKP", request.getParameter("jeniskp"));
					r.add("TARIKH_HANTAR", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','DD/MM/YYYY')"));
					r.add("ID_TRANSAKSI", transactionID);
					r.add("IDKADBIRU", blueCardId);
					sql = r.getSQLInsert("TBLINTMTPERMOHONAN");
					stmt.executeUpdate(sql);

					String idKeputusanPermohonan = "";
					String sqlKeputusanPermohonan = "SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN WHERE ID_PERMOHONAN = '" + id_Permohonan + "'";
					ResultSet rsKeputusanPermohonan = stmt.executeQuery(sqlKeputusanPermohonan);
					
					if (rsKeputusanPermohonan.next()) {
						if (rsKeputusanPermohonan.getString("ID_KEPUTUSANPERMOHONAN") != null) {
							idKeputusanPermohonan = rsKeputusanPermohonan.getString("ID_KEPUTUSANPERMOHONAN");
							
							r = new SQLRenderer();
							r.update("ID_KEPUTUSANPERMOHONAN", idKeputusanPermohonan);
							r.add("TARIKH_HANTAR_BORANGB", r.unquote("SYSDATE"));
							sql = r.getSQLUpdate("TBLPPKKEPUTUSANPERMOHONAN");
							stmt.executeUpdate(sql);
						}
					}

					if (db != null) { db.close(); }
					vm = "app/ppk/integrasi/MahkamahTinggiSuccess.jsp";
					
					IntLogManager.recordLogMT(noFail, "P", "O", "Y", "SUCCESS");
					
				} else {

					context.put("details", details);
					vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";

					IntLogManager.recordLogMT(noFail, "P", "O", "T", details);
				}

			} else {
				context.put("details", returnMessage);
				vm = "app/ppk/integrasi/MahkamahTinggiFailed.jsp";
				
				IntLogManager.recordLogMT(noFail, "P", "O", "T", returnMessage);
			}
		}

		Template template = this.engine.getTemplate(vm);
		return template;
	}

	private boolean existPermohonan(String idFail) {
		boolean existPermohonan = false;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PFD.ID_FAIL PERMOHONAN FROM TBLINTMTPERMOHONAN MTP, TBLPFDFAIL PFD"
					+ " WHERE MTP.PETISYENNO = PFD.NO_FAIL AND PFD.ID_FAIL = '"
					+ idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				existPermohonan = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return existPermohonan;
	}

	private boolean existPetioner(String idFail) {
		boolean existPetioner = true;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT TBLINTMTPERMOHONAN.NAMAPEMOHON, REPLACE(TBLINTMTPERMOHONAN.NOKPBARUPEMOHON, '-', '') NOKPBARUPEMOHON,"
					+ " TBLPPKPEMOHON.NAMA_PEMOHON, TBLPPKPEMOHON.NO_KP_BARU, TBLPPKPEMOHON.NO_KP_LAMA, TBLPPKPEMOHON.NO_KP_LAIN"
					+ " FROM TBLPFDFAIL, TBLINTMTPERMOHONAN, TBLPPKPERMOHONAN, TBLPPKPEMOHON"
					+ " WHERE TBLPFDFAIL.NO_FAIL = TBLINTMTPERMOHONAN.PETISYENNO AND TBLINTMTPERMOHONAN.FLAG_AKTIF = 'Y'"
					+ " AND TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL AND TBLPPKPERMOHONAN.ID_PEMOHON = TBLPPKPEMOHON.ID_PEMOHON"
					+ " AND TBLPFDFAIL.ID_FAIL = '" + idFail + "'";
			System.out.println("sql petitioner"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				String namaPemohonMT = rs.getString("NAMAPEMOHON");
				String noKPMT = rs.getString("NOKPBARUPEMOHON");

				String namaPemohon = rs.getString("NAMA_PEMOHON");
				String noKPBaru = rs.getString("NO_KP_BARU");
				String noKPLama = rs.getString("NO_KP_LAMA");
				String noKPLain = rs.getString("NO_KP_LAIN");
				
				if (noKPBaru != null){
					if (!"".equals(noKPBaru)) {
						if (noKPMT.equals(noKPBaru)) {
							existPetioner = false;
						}
					} else if (namaPemohon.equals(namaPemohonMT)){
						existPetioner = false;
					}
				} else if (namaPemohon.equals(namaPemohonMT)){
					existPetioner = false;
				}

//				if (!"".equals(noKPBaru)) {
//					if (noKPMT.equals(noKPBaru)) {
//						existPetioner = false;
//					}
//				} else if (!"".equals(noKPLama)) {
//					if (noKPMT.equals(noKPLama)) {
//						existPetioner = false;
//					}
//				} else if (!"".equals(noKPLain)) {
//					if (noKPMT.equals(noKPLain)) {
//						existPetioner = false;
//					}
//				} else {
//					if (namaPemohonMT.equals(namaPemohon)) {
//						existPetioner = false;
//					}
//				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return existPetioner;
	}

	private boolean successSend(String idFail) {
		boolean successSend = false;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT PFD.ID_FAIL "
					+ " FROM TBLINTMTPERMOHONAN MTP, TBLPFDFAIL PFD"
					+ " WHERE MTP.PETISYENNO = PFD.NO_FAIL AND MTP.IDKADBIRU IS NOT NULL"
					+ " AND PFD.ID_FAIL = '" + idFail + "'";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				successSend = true;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return successSend;
	}

	public Hashtable<String,String> getPermohonanMT(String userLogin, String idFail) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> permohonanMT = new Hashtable<String,String>();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT P.ID_PERMOHONAN, F.NO_FAIL AS noPetisyen,"
					+ " SM.NAMA_SIMATI AS namaSimati,"
					+ " SM.NAMA_LAIN AS namaSimatiLain,"
					+ " SM.NO_KP_BARU AS noKPSimatiBaru,"
					+ " SM.NO_KP_LAMA AS noKPSimatiLama,"
					+ " SM.NO_KP_LAIN AS noKPSimatiLain,"
					+ " decode( sm.JENIS_KP , 4,'PP',5,'SO',6,'PO',7,'OT') as jeniskp,"
					+ " (SELECT TO_CHAR(MAX(SM.TARIKH_MATI), 'YYYY-MM-DD') AS TARIKH_MATI_SIMATI"
					+ " FROM DUAL) AS tarikhMati,"
					+ " PM.NAMA_PEMOHON AS namaPemohon,"
					+ " PM.NO_KP_BARU AS noKPBaruPemohon,"
					+ " PM.NO_KP_LAMA AS noKPLamaPemohon,"
					+ " PM.NO_KP_LAIN AS noKPLainPemohon,"
					+ " RS.KETERANGAN AS hubSimatiPemohon,"
					+ " (SELECT TO_CHAR(MAX(SSF.TARIKH_MASUK), 'YYYY-MM-DD') AS TARIKH_HANTAR_BORANGB"
					+ " FROM TBLRUJSUBURUSANSTATUSFAIL SSF,"
					+ " TBLRUJSUBURUSANSTATUS SST,"
					+ " TBLRUJSTATUS ST"
					+ " WHERE SSF.ID_FAIL = F.ID_FAIL"
					+ " AND SSF.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS"
					+ " AND ST.ID_STATUS = SST.ID_STATUS) AS tarikhJanaBorangB "					
//					+ " ( select RPU.ID_PEJABATJKPTG " 
//					+  " from  TBLRUJPEJABATURUSAN RPU,TBLRUJPEJABATJKPTG RP" 
//					+  " where RPU.ID_PEJABATJKPTG = RP.ID_PEJABATJKPTG " 
//					+  " and RPU.ID_JENISPEJABAT = 22 " 
// 					+  " and RP.ID_SEKSYEN = 2 " 
//					+  " and RPU.ID_DAERAHURUS = P.ID_DAERAHMHN " 
//					+  ") AS kodPejabat,"		
					+ ",PEJ.ID_PEJABATJKPTG KODPEJABAT "  
					+ ", (SELECT UI.ID_NEGERI FROM USERS U,"
					+ " USERS_INTERNAL UI WHERE UI.USER_ID = U.USER_ID"
					+ " AND U.USER_LOGIN = '"
					+ userLogin
					+ "') AS idnegeri,"
					+ " ('I') AS jenisTransaksi, ob.id_saudara AS ID_HUBSIMATIPEMOHON, "
					+ " SM.WAKTU_KEMATIAN AS WAKTU_KEMATIAN, SM.JENIS_WAKTU_MATI "
					+ " FROM TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONAN P,"
					+ " TBLPPKPERMOHONANSIMATI PSM,"
					+ " TBLPPKSIMATI SM,"
					+ " TBLPPKPEMOHON PM,"
					+ " TBLPPKOB OB,"
					+ " TBLPPKRUJSAUDARA RS,"
					+ " TBLRUJJENISNOPB JKP,"
					+ " TBLRUJJENISNOPB JKP_PM "
					+ " ,( SELECT RPU.ID_PEJABATJKPTG ,RPU.ID_DAERAHURUS ID_DAERAH "
					+ " FROM  TBLRUJPEJABATURUSAN RPU,TBLRUJPEJABATJKPTG RP "
					+ " WHERE RPU.ID_PEJABATJKPTG=RP.ID_PEJABATJKPTG "
					+ " AND RPU.ID_NEGERI=RP.ID_NEGERI "
					+ " AND RPU.ID_DAERAH=RP.ID_DAERAH "
					+ " AND RP.ID_SEKSYEN = 2 "
					+ " AND RPU.ID_JENISPEJABAT = 22 "
					//+ " --AND  RPU.ID_DAERAHURUS = P.ID_DAERAHMHN "
					//+ " --ORDER BY RPU.ID_DAERAHURUS "
					+ " ) PEJ "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN"
					+ " AND PSM.ID_SIMATI = SM.ID_SIMATI"
					+ " AND P.ID_PEMOHON = PM.ID_PEMOHON"
					+ " AND  PEJ.ID_DAERAH = P.ID_DAERAHMHN "
					+ " AND PM.ID_PEMOHON = OB.ID_PEMOHON(+)"
					+ " AND OB.ID_SAUDARA = RS.ID_SAUDARA(+)"
					+ " AND SM.JENIS_KP = JKP.ID_JENISNOPB(+)"
					+ " AND PM.JENIS_KP = JKP_PM.ID_JENISNOPB(+)"
					+ " AND F.ID_FAIL = '" + idFail + "'";
			myLogger.info("SQL STATEMENT - PERMOHONAN MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				permohonanMT.put(
						"noPetisyen",
						rs.getString("noPetisyen") == null ? "" : rs
								.getString("noPetisyen"));
				permohonanMT.put(
						"namaSimati",
						rs.getString("namaSimati") == null ? "" : rs
								.getString("namaSimati"));
				permohonanMT.put(
						"namaSimatiLain",
						rs.getString("namaSimatiLain") == null ? "" : rs
								.getString("namaSimatiLain"));
				permohonanMT.put(
						"noKPSimatiBaru",
						rs.getString("noKPSimatiBaru") == null ? "" : rs
								.getString("noKPSimatiBaru"));
				permohonanMT.put(
						"noKPSimatiLama",
						rs.getString("noKPSimatiLama") == null ? "" : rs
								.getString("noKPSimatiLama"));
				permohonanMT.put(
						"noKPSimatiLain",
						rs.getString("noKPSimatiLain") == null ? "" : rs
								.getString("noKPSimatiLain"));
				permohonanMT.put(
						"jeniskp",
						rs.getString("jeniskp") == null ? "" : rs
								.getString("jeniskp"));
				permohonanMT.put(
						"tarikhMati",
						rs.getString("tarikhMati") == null ? "" : rs
								.getString("tarikhMati"));
				permohonanMT.put(
						"namaPemohon",
						rs.getString("namaPemohon") == null ? "" : rs
								.getString("namaPemohon"));
				permohonanMT.put(
						"noKPBaruPemohon",
						rs.getString("noKPBaruPemohon") == null ? "" : rs
								.getString("noKPBaruPemohon"));
				permohonanMT.put(
						"noKPLamaPemohon",
						rs.getString("noKPLamaPemohon") == null ? "" : rs
								.getString("noKPLamaPemohon"));
				permohonanMT.put(
						"noKPLainPemohon",
						rs.getString("noKPLainPemohon") == null ? "" : rs
								.getString("noKPLainPemohon"));
				permohonanMT.put(
						"hubSimatiPemohon",
						rs.getString("hubSimatiPemohon") == null ? "" : rs
								.getString("hubSimatiPemohon"));
				permohonanMT.put(
						"tarikhJanaBorangB",
						rs.getString("tarikhJanaBorangB") == null ? "" : rs
								.getString("tarikhJanaBorangB"));
				permohonanMT.put(
						"kodPejabat",
						rs.getString("kodPejabat") == null ? "" : rs
								.getString("kodPejabat"));
				permohonanMT.put(
						"jenisTransaksi",
						rs.getString("jenisTransaksi") == null ? "" : rs
								.getString("jenisTransaksi"));
				permohonanMT.put(
						"ID_HUBSIMATIPEMOHON",
						rs.getString("ID_HUBSIMATIPEMOHON") == null ? "" : rs
								.getString("ID_HUBSIMATIPEMOHON"));
				permohonanMT.put(
						"idnegeri",
						rs.getString("idnegeri") == null ? "" : rs
								.getString("idnegeri"));
				permohonanMT.put(
						"WAKTU_KEMATIAN",
						rs.getString("WAKTU_KEMATIAN") == null ? "" : rs
								.getString("WAKTU_KEMATIAN"));
				permohonanMT.put(
						"JENIS_WAKTU_MATI",
						rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs
								.getString("JENIS_WAKTU_MATI"));
				permohonanMT.put(
						"idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return permohonanMT;
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
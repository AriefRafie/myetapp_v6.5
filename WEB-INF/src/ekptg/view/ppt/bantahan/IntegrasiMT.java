package ekptg.view.ppt.bantahan;

import integrasi.utils.IntLogManager;
//import integrasi.ws.mt.MTManager;

import my.gov.kehakiman.eip.services.MTManager;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;
import lebah.portal.velocity.VTemplate;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;

//import sun.misc.BASE64Encoder;

//import com.sun.xml.internal.messaging.saaj.util.Base64;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.BantahanAgensiDaftar;
import ekptg.model.ppt.BantahanDaftar;


public class IntegrasiMT extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppt.bantahan.IntegrasiMT.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		//String user = (String) session.getAttribute("_portal_login");
		Hashtable<String,String> bdata = null;
		String idUser = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", idUser);

		String vm = "app/integrasi/PendaftaranMT.jsp";
		String submit = request.getParameter("command");
		
		String idHarta = getParam("idharta");
		String idPermohonan = getParam("idpermohonan");
		String idSiasatan = getParam("idsiasatan");
		String idWarta = getParam("idwarta");
		
		String jenisPembantah =""; //1, 2
		myLog.info("submit = "+submit);

		String socmt =""; 

		BantahanAgensiDaftar model = new BantahanAgensiDaftar();	
		BantahanDaftar modelBantahanPB = new BantahanDaftar();	
		Vector<Hashtable<String,String>> list = model.getMaklumatBantahanAP(idPermohonan,idHarta,idSiasatan,idWarta);
		context.put("getMaklumatBantahan", list);
			
		if(list.size()!=0){
			bdata = (Hashtable<String,String>) list.get(0);
			
			jenisPembantah = String.valueOf(bdata.get("jenis_pembantah"));
			String id_bantahan = String.valueOf(bdata.get("id_bantahan"));
		
			if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
	     		Vector listDokumen = modelBantahanPB.senarai_dokumen_bantahan(id_bantahan);
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			}
			socmt = HTML.SelectMahkamah("socmt");

		}

		if ("borangPermohonan".equals(submit)) {
		
		}else if ("hantarPermohonan".equals(submit)) {
			String idFail = request.getParameter("idFail");
			Hashtable permohonanMT = getPermohonanMT(user, idFail);
			String docContent = (String) permohonanMT.get("docContent");//(String) permohonanMT.get("docContent");

			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);
			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");			
			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			
//			String BDate = tarikhJanaBorangB.substring(0, 10);
//			
//						String[] Bparts = BDate.split("-");
//						String Bpart1 = Bparts[0];
//						String Bpart2 = Bparts[1];
//						String Bpart3 = Bparts[2];
//						BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;
						
			String BDate = "";
			if(tarikhJanaBorangB != ""){
			BDate = tarikhJanaBorangB.substring(0, 10);

			String[] Bparts = BDate.split("-");
			String Bpart1 = Bparts[0];
			String Bpart2 = Bparts[1];
			String Bpart3 = Bparts[2];
			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;
			}

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
			context.put("docContent", docContent);
			
//			if (!"".equals(fFrom)) {
//				context.put("fFrom", "ya");
//			} else {
//				context.put("fFrom", "tidak");
//			}

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
					docContent,
					request.getParameter("applicationType"), transactionID);
			

			if (!returnMessage.equals("")) {

				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					if (manager.getIdKadBiru() != null) {
						r.add("IDKADBIRU", manager.getIdKadBiru());
						//r.add("IDKADBIRU", "00001");
					}
					sql = r.getSQLInsert("TBLINTMTPERMOHONAN");
					stmt.executeUpdate(sql);
					myLog.info("getSQLInsert:::: "+sql);

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
		}else{
			
		}
		context.put("socmt",socmt);


		return vm;
		
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
			myLog.info("existPermohonan::::: "+sql);
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
		String sqlTukar = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
		

			sql = "SELECT TBLINTMTPERMOHONAN.NAMAPEMOHON, REPLACE(TBLINTMTPERMOHONAN.NOKPBARUPEMOHON, '-', '') NOKPBARUPEMOHON,"
					+ " TBLPPKPEMOHON.NAMA_PEMOHON, TBLPPKPEMOHON.NO_KP_BARU, TBLPPKPEMOHON.NO_KP_LAMA, TBLPPKPEMOHON.NO_KP_LAIN"
					+ " FROM TBLPFDFAIL, TBLINTMTPERMOHONAN, TBLPPKPERMOHONAN, TBLPPKPEMOHON"
					+ " WHERE TBLPFDFAIL.NO_FAIL = TBLINTMTPERMOHONAN.PETISYENNO AND TBLINTMTPERMOHONAN.FLAG_AKTIF = 'Y'"
					+ " AND TBLPFDFAIL.ID_FAIL = TBLPPKPERMOHONAN.ID_FAIL AND TBLPPKPERMOHONAN.ID_PEMOHON = TBLPPKPEMOHON.ID_PEMOHON"
					+ " AND TBLPFDFAIL.ID_FAIL = '" + idFail + "'";
			//System.out.println("sql petitioner"+sql);
			
			
			
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {//if ada idkadbiru
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
								
							}else{
								existPetioner = true;
							}
							
						} else if (namaPemohon.equals(namaPemohonMT)){
							existPetioner = false;
							
						} else {
							existPetioner = true;
						}
					} else if (namaPemohon.equals(namaPemohonMT)){
						existPetioner = false;
						
					}
				
				}else{
					existPetioner = false;
				}
				
				
		
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		myLog.info("existPetioner------ "+existPetioner);
		return existPetioner;
	}
	
	private boolean existTukarPemohon(String idFail) {
		boolean tukarPemohon = false;
		Db db = null;
	
		String sqlTukar = "";
		
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sqlTukar = "SELECT A.ID_FAIL, E.ID_SIMATI" +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKPERMOHONANSIMATI C, TBLPPKSIMATI D, TBLPPKTUKARPEMOHON E" +
					" WHERE A.ID_FAIL = B.ID_FAIL AND B.ID_PERMOHONAN = C.ID_PERMOHONAN AND C.ID_SIMATI = D.ID_SIMATI AND D.ID_SIMATI = E.ID_SIMATI" +
					" AND A.ID_FAIL = '" + idFail + "'";
			
			//System.out.println("sql petitioner"+sqlTukar);
			
			
			ResultSet rs1 = stmt.executeQuery(sqlTukar);
			if (rs1.next()) {//if ada buat pertukaran pemohon
				tukarPemohon  =  true;
			}
			
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		myLog.info("tukarPemohon------ "+tukarPemohon);
		return tukarPemohon;
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
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT P.ID_PERMOHONAN, F.NO_FAIL AS noPetisyen,"
					+ " SM.NAMA_SIMATI AS namaSimati,"
					+ " SM.ID_SIMATI AS idSimati,"					
					+ " SM.NAMA_LAIN AS namaSimatiLain,"
					+ " SM.NO_KP_BARU AS noKPSimatiBaru,"
					+ " SM.NO_KP_LAMA AS noKPSimatiLama,"
					+ " SM.NO_KP_LAIN AS noKPSimatiLain,"
					+ " decode( sm.JENIS_KP , 4,'PP',5,'SO',6,'PO',7,'OT',13,'PDC') as jeniskp,"
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
					+ " SM.WAKTU_KEMATIAN AS WAKTU_KEMATIAN, SM.JENIS_WAKTU_MATI, dsm.nama_dokumen, dsm.kandungan "
					+ " FROM TBLPFDFAIL F,"
					+ " TBLPPKPERMOHONAN P,"
					+ " TBLPPKPERMOHONANSIMATI PSM,"
					+ " TBLPPKSIMATI SM,"
					+ " TBLPPKPEMOHON PM,"
					+ " TBLPPKOB OB,"
					+ " TBLPPKRUJSAUDARA RS,"
					+ " TBLRUJJENISNOPB JKP,"
					+ " TBLRUJJENISNOPB JKP_PM, tblppkdokumensimati dsm "
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
					+ " AND sm.id_simati = dsm.id_simati(+) "
					+ " AND PM.ID_PEMOHON = OB.ID_PEMOHON(+)"
					+ " AND OB.ID_SAUDARA = RS.ID_SAUDARA(+)"
					+ " AND SM.JENIS_KP = JKP.ID_JENISNOPB(+)"
					+ " AND PM.JENIS_KP = JKP_PM.ID_JENISNOPB(+)"
					+ " AND F.ID_FAIL = '" + idFail + "'";
			myLog.info("SQL STATEMENT - PERMOHONAN MT : " + sql);

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
				permohonanMT.put(
						"idSimati",
						rs.getString("idSimati") == null ? "" : rs
								.getString("idSimati"));
				permohonanMT.put(
						"namaDokumen",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs
								.getString("NAMA_DOKUMEN"));
				
				Blob  b = rs.getBlob("KANDUNGAN");
				InputStream is = b.getBinaryStream();
				 byte [] b2 = IOUtils.toByteArray(is);
					String content = Base64.encodeToString(b2);
					myLog.info("*****KANDUNGAN*****");
				
				permohonanMT.put("docContent", content);
				
				
				
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
	
	public Hashtable<String,String> getTarikhHantarMT(String idFail) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> TarikhHantarMT = new Hashtable<String,String>();

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT MT.PETISYENNO, TO_CHAR(MT.TARIKH_HANTAR, 'YYYY-MM-DD') AS TARIKH_HANTAR, F.ID_FAIL, F.NO_FAIL  FROM TBLINTMTPERMOHONAN MT, TBLPFDFAIL F  " +
					" WHERE F.NO_FAIL = MT.PETISYENNO " +
					" AND F.ID_FAIL = '" + idFail + "'";
			myLog.info("SQL STATEMENT - PERMOHONAN MT : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				TarikhHantarMT.put(
						"TARIKH_HANTAR",
						rs.getString("TARIKH_HANTAR") == null ? "" : rs
								.getString("TARIKH_HANTAR"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return TarikhHantarMT;
	}
	
	
}
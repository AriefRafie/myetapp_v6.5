package ekptg.view.ppt.bantahan;

import integrasi.utils.IntLogManager;
import integrasi.ws.mt.reg.DeceaseInfoType;
import integrasi.ws.mt.reg.MTRegManager;
import integrasi.ws.mt.reg.PartyType;

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

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

//import sun.misc.BASE64Encoder;

//import com.sun.xml.internal.messaging.saaj.util.Base64;

import com.Ostermiller.util.Base64;

import ekptg.helpers.Utils;
import ekptg.model.utils.ILampiran;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.entities.Tblrujdokumen;
import ekptg.model.ppt.BantahanAgensiDaftar;
import ekptg.model.ppt.BantahanDaftar;
import ekptg.model.ppt.util.LampiranBean;
import ekptg.model.utils.rujukan.UtilHTMLPilihanMT;


public class IntegrasiMT extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppt.bantahan.IntegrasiMT.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfNaming = new SimpleDateFormat("yymmdd");
 	private IUtilHTMLPilihan iUtilPilihan = null;
 	private ILampiran iLampiran = null;
	private String sql = "";

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		//String user = (String) session.getAttribute("_portal_login");
		Hashtable<String,String> bdata = null;
		String idUser = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", idUser);

		String vm = "app/integrasi/PendaftaranMT.jsp";
		String submit = request.getParameter("command");
		
		String idBantahan = getParam("idbantahan");
		String idFail = getParam("idfail");
		String idHarta = getParam("idharta");
		String idPermohonan = getParam("idpermohonan");
		String idSiasatan = getParam("idsiasatan");
		String idWarta = getParam("idwarta");
		
		String transactionID = "";
		String kodmt ="";
		String jenisPembantah =""; //1, 2
		MTRegManager im = null;

		PartyType[] party = null;
		PartyType pt = null;
		myLog.info("submit = "+submit+",mode="+ ("mode"));

		String socmt =""; 

		context.put("idBantahan", idBantahan);
		context.put("idFail", idFail);
		context.put("idHarta", idHarta);
		context.put("idPermohonan", idPermohonan);
		context.put("idSiasatan", idSiasatan);
		context.put("idWarta", idWarta);

		BantahanAgensiDaftar model = new BantahanAgensiDaftar();	
		BantahanDaftar modelBantahanPB = new BantahanDaftar();	
		Vector<Hashtable<String,String>> list = null;
		if (submit.equals("bantahanap")) 
			list = model.getMaklumatBantahanAP(idPermohonan,idHarta,idSiasatan,idWarta);
		else
			list = modelBantahanPB.getMaklumatBantahan(idHarta,idSiasatan,idWarta);
	
			
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
			socmt = getPilihan().Pilihan("socmt", "onchange = \'pilihMT()\'");
					
		}

		if (submit.equals("bantahanap")) {
			myLog.info("bantahanap");
			String noKes = getNoKes(idBantahan);
			if(!noKes.equals("")){
				context.put("noKes", noKes);
			}
		
		}else if (submit.equals("bantahanpb")) {
			myLog.info("bantahanpb");
			String noKes_ = "";
			String noKes = getNoKes(idBantahan);
			myLog.info("bantahanpb="+noKes);
			if(!noKes.equals("")){
				noKes_ = noKes;
			}
			context.put("noKes", noKes_);

		}else if ("hantarpermohonan".equals(submit)) {
			Tblrujdokumen doc = null;
			//Hashtable permohonanMT = getPermohonanMT(user, idFail);
			//String docContent = (String) permohonanMT.get("docContent");//(String) permohonanMT.get("docContent");

			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);
			
			Db db = null;
			//String sql = "";
			String sql2 = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			Statement stmt2 = db.getStatement();
			SQLRenderer kptsn = new SQLRenderer();
			
//			context.put("idnegeri", request.getParameter("idnegeri"));
//			context.put("jeniskp", request.getParameter("jeniskp"));
//			context.put("docContent", docContent);
			String idPejabat = getParam("socmt");

			String name	= getParam("txtNamaPembantah");
			String add = getParam("txtAlamat1");
			String add2 = getParam("txtAlamat2");
			String add3 = getParam("txtAlamat3");
			String postcode = getParam("txtPoskod");
			String stateCode = getStateCode(Integer.parseInt(getParam("txtIdNegeri")));
			String city = "Putrajaya";
			myLog.info("stateCode="+stateCode);

			String kodMT = getParam("kodmt");
			
			String[] cbsemaks = this.request.getParameterValues("cb");
			String renameDoc = "";
			//myLog.info(cbsemaks.length);
			if(cbsemaks.length ==1){
				doc = getDoc().getLampiran(cbsemaks[0]);
				doc.setDokumen(idBantahan);
				doc.setIdMasuk(Long.parseLong(idUser));
				//<source>_<sourceReferenceNo>_<currentdate>_docID>.pdf
				String renameDate = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
									+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2);
				renameDoc = "ETP_"+idBantahan+"_"+sdfNaming.format(new Date())+"_"+doc.getIdDokumen()+".pdf";
				myLog.info("renameDoc="+renameDoc);
				getDoc().simpanDokumenInt(doc);
				
			}else{
				if (cbsemaks != null) {
					for (int i = 0; i < cbsemaks.length; i++) {
						//myLog.info(i+"."+cbsemaks[i]);
					}
				}
			}
			//myLog.info("doc: content="+doc.getKandungan());

			r.add("ID_FAIL", idFail);
			r.add("ID_RUJUKAN", idBantahan);
			r.add("KOD_MT", kodMT);
						
			r.add("JENIS_TRANSAKSI","15");
			r.add("TARIKH_HANTAR", r.unquote("to_date('" + sdf.format(cal.getTime()) + "','DD/MM/YYYY')"));
			
			r.add("ID_MASUK", idUser);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
			r.add("ID_TRANSAKSI", transactionID);
			sql = r.getSQLInsert("TBLINTMTPENDAFTARAN");
			myLog.info("sql="+sql);
			stmt.executeUpdate(sql);

			im = new MTRegManager("MTREG");
	        //MTRegManager manager = new MTRegManager("15");
			pt = MTRegManager.getParty15(transactionID //id rujukan party
					,name
					,add,add2,add3,postcode,stateCode,city);
	        party = new PartyType[1];
	        party[0] = pt;

	        DeceaseInfoType deceaseInfo = new DeceaseInfoType();
	        deceaseInfo.setDeceaseInfoName(name);
	        deceaseInfo.setDeceaseInfoType("1");
	        deceaseInfo.setDeceaseInfoIDType1("IC");
	        deceaseInfo.setDeceaseInfoIDType1No("251202095009");
	        deceaseInfo.setDeceaseInfoIDType2("OC");
	        deceaseInfo.setDeceaseInfoIDType2No("12345");
	        deceaseInfo.setDeathCertNo("12345");
	        deceaseInfo.setDeceaseInfoGender("M");
	        deceaseInfo.setDeceaseInfoAge("98");
	        deceaseInfo.setDateOfDeath("2020-03-01T00:00:00");
	        deceaseInfo.setDeceaseInfoAddr1(add);
	        deceaseInfo.setDeceaseInfoAddr1(add2);
	        deceaseInfo.setDeceaseInfoAddr1(add3);
	        deceaseInfo.setDeceaseInfoPostcode("44000");
	        deceaseInfo.setDeceaseInfoCity("KUALA LUMPUR");
	        deceaseInfo.setDeceaseInfoState(stateCode);
	        deceaseInfo.setDeceaseInfoCountry("MYS");
//	        <DeceaseInfoName>IJOK ANAK EDIN KONG</DeceaseInfoName>
//			<DeceaseInfoType>1</DeceaseInfoType>
//			<DeceaseInfoIDType1>IC</DeceaseInfoIDType1>
//			<DeceaseInfoIDType1No>251202095009</DeceaseInfoIDType1No>
//			<DeceaseInfoIDType2>OC</DeceaseInfoIDType2>
//			<DeceaseInfoIDType2No>12345</DeceaseInfoIDType2No>
//			<DeathCertNo>12345</DeathCertNo>
//			<DeceaseInfoGender>M</DeceaseInfoGender>
//			<DeceaseInfoAge>99</DeceaseInfoAge>
//			<DateOfDeath>2020-03-01T00:00:00</DateOfDeath>
//			<DeceaseInfoAddr1>DECEASED INFO ADD 1</DeceaseInfoAddr1>
//			<DeceaseInfoAddr2>DECEASED INFO ADD 2</DeceaseInfoAddr2>
//			<DeceaseInfoAddr3>DECEASED INFO ADD 3</DeceaseInfoAddr3>
//			<DeceaseInfoPostcode>44000</DeceaseInfoPostcode>
//			<DeceaseInfoCity>KUALA LUMPUR</DeceaseInfoCity>
//			<DeceaseInfoState>14</DeceaseInfoState>
//			<DeceaseInfoCountry>MYS</DeceaseInfoCountry>
			String returnMessage = "1 Tidak Berjaya Dihantar";
	        returnMessage = MTRegManager. PendaftaranBaharu("15"
	        					,doc.getIdDokumen(),renameDoc,doc.getKandungan()
	        					,party
	        					,deceaseInfo
	        					, kodMT 	//MT Kangar
	        					, "3"	//Civil
	        					, "2"	//High Court
	        					, idBantahan	// Rujukan Permohonan (id bantahan)
	        					, "0.00"
	        					, transactionID);
			
			if (!returnMessage.equals("")) {
				String code = returnMessage.substring(0, 1);
				String details = returnMessage.substring(2);

				if (code.equals("0")) {
					r = new SQLRenderer();
					r.setUpdate("ID_TRANSAKSI", transactionID);
					if (MTRegManager.getReferenceNo() != null) {
						r.add("NO_KES", MTRegManager.getReferenceNo());
					}
					r.add("CATATAN",returnMessage);
					sql = r.getSQLUpdate("TBLINTMTPENDAFTARAN");
					stmt.executeUpdate(sql);
					myLog.info("getSQLUpdate:sql="+sql);

					vm = "app/integrasi/MahkamahTinggiSuccess.jsp";
					//IntLogManager.recordLogMT(noFail, "I", "O", "Y", "SUCCESS");
				
				} else {
					returnMessage = details;
					//context.put("details", details);
					vm = "app/integrasi/MahkamahTinggiFailed.jsp";
					//IntLogManager.recordLogMT(noFail, "I", "O", "T", details);
				
				}

			} else {
				returnMessage = "Tidak Berjaya Hantar";
				//context.put("details", returnMessage);
				vm = "app/integrasi/MahkamahTinggiFailed.jsp";
				//IntLogManager.recordLogMT(noFail, "I", "O", "T", returnMessage);
			}
			context.put("details", returnMessage);
			socmt = getPilihan().Pilihan("socmt",idPejabat,"onchange = \'pilihMT()\'");
			kodmt = kodMT;
			myLog.info("returnMessage="+returnMessage);
			
		}else if(submit.equals("getmahkamah")){
			String idPejabat = getParam("socmt");
			im = new MTRegManager("MTREG");
			
			socmt = getPilihan().Pilihan("socmt",idPejabat,"onchange = \'pilihMT()\'");
			kodmt = im.getKodMT(idPejabat);

		}else{
			
		}
		context.put("kodmt",kodmt);
		context.put("socMT",socmt);


		return vm;
		
	}

	private String getNoKes(String idBantahan) {
		String noKes = "";
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT NO_KES FROM TBLINTMTPENDAFTARAN "
				+ " WHERE ID_RUJUKAN = '"+ idBantahan + "'"
				+ " AND NO_KES IS NOT NULL";

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				noKes = rs.getString("NO_KES");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return noKes;
		
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

	private IUtilHTMLPilihan getPilihan(){
		if(iUtilPilihan == null){
			iUtilPilihan = new UtilHTMLPilihanMT();
		}
		return iUtilPilihan;
			
	}
	
	private ILampiran getDoc(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
			
	}

	private String getStateCode(int x){
		String abbrev = "0";
		switch (x) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			abbrev += String.valueOf(x);
			break;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
			abbrev = String.valueOf(x);
			break;
		default:
			break;
		}
		//myLog.info("getStateCode:abbrev="+abbrev);
		return abbrev;

	}

	
}
package ekptg.view.ppt.bantahan;

import integrasi.utils.IntLogManager;
import integrasi.ws.mt.reg.DeceaseInfoType;
import integrasi.ws.mt.reg.MTRegManager;
import integrasi.ws.mt.reg.PartyType;

import java.sql.ResultSet;
import java.sql.Statement;
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

import org.apache.log4j.Logger;

//import sun.misc.BASE64Encoder;

//import com.sun.xml.internal.messaging.saaj.util.Base64;


import ekptg.helpers.Utils;
import ekptg.model.utils.FrmNegeriData;
import ekptg.model.utils.IUtilHTMLPilihan;
import ekptg.model.utils.lampiran.ILampiran;
import ekptg.model.entities.Tblrujdokumen;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujpejabat;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.ppt.BantahanAgensiDaftar;
import ekptg.model.ppt.BantahanDaftar;
import ekptg.model.ppt.util.LampiranBean;
import ekptg.model.utils.rujukan.DBPPT;
import ekptg.model.utils.rujukan.UtilHTMLPilihanMT;
import ekptg.model.utils.rujukan.UtilHTMLPilihanPTGD;


public class IntegrasiMT extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppt.bantahan.IntegrasiMT.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfNaming = new SimpleDateFormat("yymmdd");
 	private IUtilHTMLPilihan iUtilPilihan = null;
// 	private IUtilHTMLPilihan iPilihan = null;
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
		String jenisRef ="";
		String idRujukanPB = "";
		MTRegManager im = null;

		PartyType[] party = null;
		PartyType pt = null;
		myLog.info("submit = "+submit+",mode="+ ("mode"));

		String socmt =""; 
//		String socPejabat =""; 

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
			context.put("pembantah", bdata);
			
			jenisPembantah = String.valueOf(bdata.get("jenis_pembantah"));
			myLog.info("Pembantah:"+jenisPembantah);
			String id_bantahan = String.valueOf(bdata.get("id_bantahan"));
			String idNegeriPer = String.valueOf(bdata.get("id_negeri"));
			jenisRef = String.valueOf(bdata.get("id_jenispb")); //2=Syarikat, 10-Pertubuhan
			idRujukanPB = String.valueOf(bdata.get("idRujukanPB"));
			
			if((!id_bantahan.equals("")) && (!id_bantahan.equals(null))){
	     		Vector listDokumen = modelBantahanPB.senaraiDokumenBantahan(id_bantahan,"bantahan");
	    		context.put("listDokumen", listDokumen);
	    		context.put("listDokumen_size", listDokumen.size());	
	    		
			}else{				
				context.put("listDokumen", "");
				context.put("listDokumen_size", 0);
			
			}
			
//			Vector<Hashtable<String, Object>> vecMT 
//				= ekptg.helpers.DB.getMahkamahByNegeri(Long.parseLong(idNegeriPer));
//			if(vecMT.isEmpty() || vecMT.size() > 1) {
					socmt = getPilihan().Pilihan("socmt", "onchange = \'pilihMT()\'");
		
//			Tujuan Pengujian
//			}else {
//				im = new MTRegManager();
//				socmt = String.valueOf(vecMT.get(0).get("namaPejabat"));
//				kodmt = im.getKodMT(String.valueOf(vecMT.get(0).get("id")));
//			}
			
//			Vector<Tblrujpejabat> vecPejabat = DBPPT.getMTByPermohonan(idPermohonan);
//			if(vecPejabat.size() > 1) {
//				socPejabat = getPTGD().Pilihan("socPejabat", "",idPermohonan,"onchange = \'pilihPejabat()\'");
//
//			}else {
//				socPejabat = getPTGD().Pilihan("socPejabat",String.valueOf(vecPejabat.get(0).getIdPejabat()),idPermohonan,"onchange = \'pilihPejabat()\'");				
//				PartyType partyType = MTRegManager.getPartyResponden(String.valueOf(vecPejabat.get(0).getIdPejabat()));
//				setContextPejabat(partyType);
//			
//			}

					
		}

		if (submit.equals("bantahanap")) {
			myLog.info("bantahanap");
			String noKes = getNoKes(idBantahan);
			if(!noKes.equals("")){
				context.put("noKes", noKes);
			}
			
			String DISABILITY = "";
			Hashtable pendaftaran = getPendaftaran(idBantahan);
			if(pendaftaran != null) {
				DISABILITY = " disabled class=\"disabled\" ";
				socmt = getPilihan().Pilihan("socmt",String.valueOf(pendaftaran.get("idPejabat")),"onchange = \'pilihMT()\' "+DISABILITY);
				kodmt = String.valueOf(pendaftaran.get("kod"));
			
			}
		
		}else if (submit.equals("bantahanpb")) {
			myLog.info("bantahanpb");
			String noKes_ = "";
			String noKes = getNoKes(idBantahan);
//			myLog.info("bantahanpb="+noKes);
			if(!noKes.equals("")){
				noKes_ = noKes;
			}
			context.put("noKes", noKes_);
			
			String DISABILITY = "";
			Hashtable pendaftaran = getPendaftaran(idBantahan);
			if(pendaftaran != null) {
				DISABILITY = " disabled class=\"disabled\" ";
				
				context.put("jantina", pendaftaran.get("jantina"));
				socmt = getPilihan().Pilihan("socmt",String.valueOf(pendaftaran.get("idPejabat")),"onchange = \'pilihMT()\' "+DISABILITY);
				kodmt = String.valueOf(pendaftaran.get("kod"));
			
			}

			context.put("classRead", DISABILITY);

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
			//String sql2 = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String idPejabat = getParam("socmt");
			//String idPejabatPTGD = getParam("socPejabat");
			
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
			myLog.info("jenisRef="+jenisRef);
			//Perayu PB (Individu|Syarikat)
			String gen = "U";
			String noRef = getParam("noRef");
			int umur	= 0;
			String refType = "OT";
			//String refType = getParam("noRef");
			
			if(jenisRef.equals("1")
				|| jenisRef.equals("3")
				|| jenisRef.equals("4")
				|| jenisRef.equals("5")
				|| jenisRef.equals("6")
				|| jenisRef.equals("11")) {
				gen = getParam("jantina");
				noRef = Utils.RemoveDash(noRef);
				refType = "IC";
				umur	= Integer.parseInt(getParam("umur"));
				
//			}else if(jenisRef.equals("2") || jenisRef.equals("10")) {
//				gen = "U";
//				umur ="0";
//				refType = "OT";
			}

			r.add("ID_FAIL", idFail);
			r.add("ID_RUJUKAN", idBantahan);
			r.add("KOD_MT", kodMT);
			r.add("JANTINA", gen);
			r.add("UMUR", umur);
			
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
			myLog.info("sql="+sql);
			if(jenisPembantah.equals("1")){
				pt = MTRegManager.getPartyPerayuGov(idRujukanPB //id rujukan party
					,name
					,add,add2,add3,postcode,stateCode,city);
			}else {
				//,String name,String umur,String gen,String noRef
				pt = MTRegManager.getPartyPerayu(idRujukanPB //id rujukan party
						,name, String.valueOf(umur), gen, noRef,refType
						,add,add2,add3,postcode,stateCode,city);
			}
			//PartyType pt02 = MTRegManager.getPartyResponden(idPejabatPTGD);
			
	        party = new PartyType[1];
	        party[0] = pt;
	        //party[1] = pt02;

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
					//r.setUpdate("ID_TRANSAKSI", transactionID);
					r.update("ID_RUJUKAN", idBantahan);
			
					if (MTRegManager.getReferenceNo() != null) {
						r.add("NO_KES", MTRegManager.getReferenceNo());
					}
					r.add("CATATAN",returnMessage);
					sql = r.getSQLUpdate("TBLINTMTPENDAFTARAN");
					myLog.info("getSQLUpdate:sql="+sql);
					stmt.executeUpdate(sql);

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
		
//			socPejabat = getPTGD().Pilihan("socPejabat", idPejabat,idPermohonan,"onchange = \'pilihPejabat()\'");
			myLog.info("returnMessage="+returnMessage);
			
		}else if(submit.equals("getmahkamah")){
			String idPejabat = getParam("socmt");
			im = new MTRegManager();
			
			socmt = getPilihan().Pilihan("socmt",idPejabat,"onchange = \'pilihMT()\'");
			kodmt = im.getKodMT(idPejabat);
			
			context.put("jantina", getParam("jantina"));


		}else if(submit.equals("getpejabat")){
			String idPejabat = getParam("socPejabat");
			
//			socPejabat = getPTGD().Pilihan("socPejabat", idPejabat,idPermohonan,"onchange = \'pilihPejabat()\'");
			PartyType partyType = MTRegManager.getPartyResponden(idPejabat);

			setContextPejabat(partyType);

		}else{
			
		}
		context.put("jenisRef",jenisRef);
		context.put("kodmt",kodmt);
		context.put("socMT",socmt);
//		context.put("socPejabat",socPejabat);


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
	
	private Hashtable<String,String> getPendaftaran(String idBantahan) {
		Hashtable<String,String> pendaftaran= null;
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT P.JANTINA,LM.ID_PEJABAT,P.KOD_MT FROM TBLINTMTPENDAFTARAN P"
				+ " ,TBLINTMTLOCATION L,TBLINTMTLOCATIONMAP LM "
				+ " WHERE "
				+ " TO_NUMBER(P.KOD_MT) = L.LOCATION "
				+ " AND L.ID_LOCATION=LM.ID_LOCATION "
				+ " AND P.ID_RUJUKAN = '"+ idBantahan + "'"
				+ " AND P.NO_KES IS NOT NULL";
						
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				pendaftaran = new Hashtable<String,String>();
				pendaftaran.put("jantina", Utils.isNull(rs.getString("JANTINA")));
				//pendaftaran.put("umur", rs.getString("UMUR"));
				pendaftaran.put("idPejabat", rs.getString("ID_PEJABAT"));
				pendaftaran.put("kod", rs.getString("KOD_MT"));

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

		return pendaftaran;
		
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
	
	private void setContextPejabat(PartyType partyType) throws Exception {
		context.put("txtNamaResp", partyType.getPartyName());
		context.put("txtAlamatResp1", partyType.getPartyAddr1());
		context.put("txtAlamatResp2", partyType.getPartyAddr2());
		context.put("txtAlamatResp3", partyType.getPartyAddr3());
		context.put("txtPoskodResp", partyType.getPartyPostcode());
		context.put("txtBandarResp", partyType.getPartyCity());

		String nama = String.valueOf(FrmNegeriData.getList(partyType.getPartyState()).get("nama_negeri"));
		context.put("txtNamaNegeriResp", nama);
		context.put("id_negeriResp", partyType.getPartyState());

	}
	
	private IUtilHTMLPilihan getPilihan(){
		if(iUtilPilihan == null){
			iUtilPilihan = new UtilHTMLPilihanMT();
		}
		return iUtilPilihan;
			
	}
//	
//	private IUtilHTMLPilihan getPTGD(){
//		if(iPilihan == null){
//			iPilihan = new UtilHTMLPilihanPTGD();
//		}
//		return iPilihan;
//			
//	}
	
	private ILampiran getDoc(){
		if(iLampiran == null){
			iLampiran = new LampiranBean();
		}
		return iLampiran;
			
	}


	
}
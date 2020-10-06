package ekptg.report.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.FrmSek8LaporanAwalTanahData;
import ekptg.model.ppt.PPTHeader;
/**
 * @author Syah/Man/Elly
 * @source Peje
 */


public class FrmPopupPilihPegawaiReportView extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPopupPilihPegawaiReportView.class);
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	FrmSek8LaporanAwalTanahData modelLaporanAwal = new FrmSek8LaporanAwalTanahData();
	
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception {

		Db dbx = null;
		try {
			dbx = new Db();
			checkTableWujud("TBLMAKLUMATACCAMANAHMAHKAMAH",dbx);
		} finally {
			if (dbx != null)
				dbx.close();
		}
		
		myLogger.info(".:FrmPopupPilihPegawaiReportView:.");
		@SuppressWarnings("unused")
		HttpSession session = request.getSession();
		
		Vector list = new Vector();
		Vector listMaklumatTanah = new Vector();
		Vector detailPegawai = new Vector();
		Vector dataNamaPengarah = new Vector();
		Vector getLuasAmbil = new Vector();
		Vector listTarikhSiasatan = new Vector();
		Vector malaysianDate = new Vector();
		Vector malaysianDateByDate = new Vector();
		Vector dataMT = new Vector();
		Vector checkBorangG = new Vector();
		
		checkBorangG.clear();
		dataMT.clear();
		listTarikhSiasatan.clear();
		getLuasAmbil.clear();
		detailPegawai.clear();
		listMaklumatTanah.clear();
		list.clear();
		dataNamaPengarah.clear();
		
		String vm = "";
		String nama_pegawai = "";
		String nama_pegawai1 = "";
		String nama_pegawai2 = "";
		String jawatan = "";
		String id_jawatan = "";
		String selectNoFail = getParam("selectNoFail");
		String report = getParam("report");
		String flagReport = getParam("flagReport");
		String id_fail = getParam("id_fail");
		String bilDokumen = getParam("bilDokumen");
		String id_pegawai = getParam("idpegawai");
		String id_pegawai1 = getParam("idpegawai1");
		String id_pegawai2 = getParam("idpegawai2");
		String id_permohonan = getParam("id_permohonan");
		String id_tanahumum = getParam("id_tanahumum");
		String id_siasatan = getParam("id_siasatan");
		String id_hakmilik = getParam("id_hakmilik");
		String flagJenisSuratCara = getParam("flagJenisSuratCara");
		String flagCetakJPBD = getParam("flagCetakJPBD");
		String flagCetakSiasatan = getParam("flagCetakSiasatan");
		String id_mmk = getParam("id_mmk");
		String id_permintaanukur = getParam("id_permintaanukur");
		String id_borange = getParam("id_borange");
		String flagShowTarikhCetak = getParam("flagShowTarikhCetak");
		String tarikhBorangL = getParam("tarikhBorangL");
		String tempohBL = getParam("tempohBL");
		String token = getParam("token");
		
		String txtBilKertas = getParam("txtBilKertas");
		String txtBilSalinan = getParam("txtBilSalinan");
		
		String bilLot = getParam("bilLot");
		String masa_siasatan = getParam("masa_siasatan");
		
		String txtNamaPentadbir = getParam("txtNamaPentadbir");
		String id_buktipenyampaian = getParam("id_buktipenyampaian");
		String id_borangh = getParam("id_borangh");
		String usid = (String) session.getAttribute("_portal_username");
		String login = (String) session.getAttribute("_ekptg_user_id");
		context.put("username", usid);
		myLogger.info("login : "+login);
		myLogger.info("--------- report :"+report);
		
		String bydate = getParam("bydate");
		
		String emel = "";
		
		String listLOT = "";
		String listLOTHM = "";
		String userlogin = "";
		
		//package helper
		context.put("Utils", new ekptg.helpers.Utils());
		
		//default
		context.put("sorSelectNoFail", "");
		
		// REPORT BANTAHAN
		String id_bantahan = getParam("id_bantahan");
		String id_hakmilikpb = getParam("id_hakmilikpb");
		String id_bayaran = getParam("id_bayaran");
		// END REPORT BANTAHAN
		
		if (id_pegawai == null || id_pegawai.trim().length() == 0){
			id_pegawai = "0";
		}
		
		if (id_pegawai1 == null || id_pegawai1.trim().length() == 0){
			id_pegawai1 = "0";
		}
		
		if (id_pegawai2 == null || id_pegawai2.trim().length() == 0){
			id_pegawai2 = "0";
		}
		
		//data
		String tujuanInit = "";
		String projek_daerah = "";
		String id_negeri = "";
		String no_fail = "";
		String no_rujukan_ptg = "";
		String no_rujukan_ptd = "";
		String no_rujukan_upt = "";
		String id_projekNegeri = "";
    	String idpermohonan = getParam("id_permohonan");
    	this.context.put("listPenghantarNotis", "");
    	this.context.put("token", token);
    	header.setDataHeader(idpermohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_fail = (String)dh.get("id_fail");
			id_negeri = (String)dh.get("id_projekNegeri");
			no_fail = (String)dh.get("no_fail");	
			no_rujukan_ptg = (String)dh.get("no_rujukan_ptg");	
			no_rujukan_ptd = (String)dh.get("no_rujukan_ptd");	
			no_rujukan_upt = (String)dh.get("no_rujukan_upt");
			projek_daerah = (String)dh.get("projek_daerah");
			tujuanInit = (String)dh.get("tujuanInit");
			id_projekNegeri = (String)dh.get("id_projekNegeri");
			this.context.put("listPenghantarNotis", listPenghantarNotis(id_projekNegeri));
			
		}
		//pejabat jpph
		if(id_negeri!=""){
			context.put("selectPejabatJPPH",HTML.selectPejabatJPPHByNegeri(id_negeri,"socPejabatJPPH",null,"",""));
		}else{
			context.put("selectPejabatJPPH",HTML.selectPejabatJPPH("socPejabatJPPH",null,"",""));
		}		
		
		//pejabat arb
		if(id_negeri!=""){
			context.put("selectPejabatARB",HTML.selectPejabatARBByNegeri(id_negeri,"socPejabatARB",null,"",""));
		}else{
			context.put("selectPejabatARB",HTML.selectPejabatARB("socPejabatARB",null,"",""));
		}
		
		//GET NAMA PENGARAH
	    String nama_pengarah = "";
	    modelUPT.setNamaPengarah(id_negeri);
	    dataNamaPengarah = modelUPT.getNamaPengarah();
	    if(dataNamaPengarah.size()!=0){
	    	Hashtable np = (Hashtable)dataNamaPengarah.get(0);
	    	nama_pengarah = np.get("nama_pengarah").toString();
	    }
	    	    
	    //GET TARIKH SIASATAN
	    String lbltarikhSiastan = "";
	    logic.setListTarikhSiastan(id_permohonan);
	    listTarikhSiasatan = logic.getListTarikhSiasatan();
	    if(listTarikhSiasatan.size()==1){
	    	Hashtable lts = (Hashtable)listTarikhSiasatan.get(0);
	    	lbltarikhSiastan = lts.get("tarikh2Siasatan").toString();	
	    }
	    
	    context.put("lbltarikhSiastan", lbltarikhSiastan);
	    context.put("listTarikhSiasatan", listTarikhSiasatan);
	    
		//list maklumat tanah
	    String nama2MukimInit = "";
 		modelUPT.setListMaklumatTanah(id_permohonan,"","");
 		listMaklumatTanah = modelUPT.getListMaklumatTanah();		
 		context.put("listMaklumatTanah", listMaklumatTanah);
 		context.put("saiz_listTanah", listMaklumatTanah.size());
 		String nama2Mukim = "";
 		if(listMaklumatTanah.size()!=0){
 			Hashtable lmt = (Hashtable)listMaklumatTanah.get(0);
 			nama2Mukim = (String)lmt.get("nama2Mukim");
 			listLOT = (String)lmt.get("listLOT");
 			listLOTHM = (String)lmt.get("listLOTHM");
 			nama2MukimInit = (String)lmt.get("nama2MukimInit");	
 		}
		
 		//GET TOTAL LUAS AMBIL DALAM HEKTAR
		String totalLuasAmbil = "";
		String totalLuasAmbil_nonformat = "";
		modelLaporanAwal.setTotalLuasAmbil(idpermohonan,"");
		getLuasAmbil = modelLaporanAwal.getTotalLuasAmbil();
		if(getLuasAmbil.size()!=0){
			Hashtable la = (Hashtable)getLuasAmbil.get(0);
			totalLuasAmbil = (String)la.get("totalLuasAmbil");
			totalLuasAmbil_nonformat = (String)la.get("totalLuasAmbil_nonformat");
		}
			
		String namaBankMT = "";
		String noAkaunMT = "";
		String namaARB = "";
		String namaBankARB = "";
		String noAkaunARB = "";
		
//		Hashtable dh = (Hashtable) dataHeader.get(0);
//		flag_subjaket = dh.get("flag_subjaket").toString();
		
		//GET NAMA MAHKAMAH TINGGI
				String namaMT = "";
				logic.setDataMahkamahTinggi(id_negeri);
			    dataMT = logic.getDataMahkamahTinggi();
			    if(dataMT.size()!=0){
					Hashtable mt = (Hashtable)dataMT.get(0);
					namaMT = (String)mt.get("NAMA_PEJABAT");
				}
			    		
		//1-amanah raya
		//2-mahkamah
		
				/*
				 * if(id_negeri.equals("10")){ namaBankMT = "Affin Bank (Cawangan Shah Alam)";
				 * noAkaunMT = "1059-9000-3766"; namaARB = "Amanah Raya Berhad Shah Alam";
				 * namaBankARB = "Bank Muamalat Malaysia Berhad (Cawangan Shah Alam)";
				 * noAkaunARB = "1202-0000301-71-2"; }else if(id_negeri.equals("8")){ namaBankMT
				 * = "Affin Bank Malaysia Berhad"; noAkaunMT = "106110003215"; namaARB =
				 * "Amanah Raya Berhad Ipoh"; namaBankARB = "Bank Muamalat Malaysia Berhad";
				 * noAkaunARB = "0804-0000483-71-6"; }
				 */
		
		//date in malay
		String sysdateMalay = "";
	    logic.setMalayDate();
	    malaysianDate = logic.getMalayDate();
	    if(malaysianDate.size()!=0){
	    	Hashtable md = (Hashtable)malaysianDate.get(0);
	    	sysdateMalay = (String)md.get("sysdateMalay");
	    }
	    
	    //date in english
	    String dayEng = lebah.util.Util.getDateTime(new Date(), "dd");
	    String monthEng = lebah.util.Util.getDateTime(new Date(), "MMMM");
	    String yearEng = lebah.util.Util.getDateTime(new Date(), "yyyy");
	    String sysdateEng = dayEng+" day of "+monthEng+" "+yearEng;
	    
	    String showG_MT = "";
    	String showG_ARB = "";
    	int countMT = 0;
    	int countARB = 0;    
	    if(bydate.equals("") && (report.equals("BorangG") || report.equals("BorangH"))){	
	    	myLogger.info("proceed");
	    	
	    	logic.setDataCheckBorangG(id_hakmilik,report,bydate);
		    checkBorangG = logic.getDataCheckBorangG();	    
		    if(checkBorangG.size()!=0){
				Hashtable dbg = (Hashtable)checkBorangG.get(0);
				countMT = (Integer)dbg.get("BORANGG_MT");
				countARB = (Integer)dbg.get("BORANGG_ARB");
			}
		    
		    if(countMT>0){
		    	showG_MT = "yes";
		    }
		    
		    if(countARB>0){
		    	showG_ARB = "yes";
		    }
		    
		    context.put("showG_MT", showG_MT);
		    context.put("showG_ARB", showG_ARB);
		    
	    }
		
 

	  //function
	  		String submit = getParam("command");
	      	myLogger.info("submit : " + submit);
	      //yati tambah 16092020 - v7	
	      	if ("sendDGcert".equals(submit)) {
	      		
	      		myLogger.info("send CERT SIGN");
	      		context.put("token",token);
	      		context.put("userlogin",login);
	      		myLogger.info("token adalah :"+token);
					      	
	      		
	      	}
	    
	      	else if ("simpanAcc".equals(submit)) {
				if (showG_ARB.equals("yes")) {
					Hashtable getAccAmanah = getAccAmanahRaya(id_negeri, "1");
					if (getAccAmanah.get("CHECK_VAL").toString().equals("1")) {
						updateTableAcc(id_negeri, getParam("txtNamaBankARB"), getParam("txtNamaAkaunARB"),
								getParam("txtNomborAkaunARB"), "1");
					} else {
						insertTableAcc(id_negeri, getParam("txtNamaBankARB"), getParam("txtNamaAkaunARB"),
								getParam("txtNomborAkaunARB"), "1");
					}
				}
				if (showG_MT.equals("yes")) {
					Hashtable getAccMahkamah = getAccAmanahRaya(id_negeri, "2");
					if (getAccMahkamah.get("CHECK_VAL").toString().equals("1")) {
						updateTableAcc(id_negeri, getParam("txtNamaBankMT"), getParam("txtNamaAkaunMT"),
								getParam("txtNomborAkaunMT"), "2");
					} else {
						insertTableAcc(id_negeri, getParam("txtNamaBankMT"), getParam("txtNamaAkaunMT"),
								getParam("txtNomborAkaunMT"), "2");
					}
				}
				context.put("sorSelectNoFail", getParam("sorSelectNoFail"));
			}
	    
	  	String nama_amanah = "";
		String namaBank_amanah= "";
		String noAkaun_amanah = "";
		Hashtable getAccAmanah = getAccAmanahRaya(id_negeri,"1");
		myLogger.info("getAccAmanah :::"+getAccAmanah.get("CHECK_VAL").toString());
		if(getAccAmanah.get("CHECK_VAL").toString().equals("1")){
			
			nama_amanah = getAccAmanah.get("NAMA_AKAUN").toString();
			namaBank_amanah = getAccAmanah.get("NAMA_BANK").toString();
			noAkaun_amanah = getAccAmanah.get("NO_AKAUN").toString();
			
			namaBankARB = namaBank_amanah;
			namaARB = nama_amanah;
			noAkaunARB = noAkaun_amanah;
		} else {
			if (id_negeri.equals("10")) {
				namaARB = "Amanah Raya Berhad Shah Alam";
				namaBankARB = "Bank Muamalat Malaysia Berhad (Cawangan Shah Alam)";
				noAkaunARB = "1202-0000301-71-2";
			} else if (id_negeri.equals("8")) {
				namaARB = "Amanah Raya Berhad Ipoh";
				namaBankARB = "Bank Muamalat Malaysia Berhad";
				noAkaunARB = "0804-0000483-71-6";
			}
		}
		
		String nama_mahkamah = "";
		String namaBank_mahkamah= "";
		String noAkaun_mahkamah = "";
		Hashtable getAccMahkamah = getAccAmanahRaya(id_negeri,"2");
		myLogger.info("getAccMahkamah :::"+getAccMahkamah.get("CHECK_VAL").toString());
		if(getAccMahkamah.get("CHECK_VAL").toString().equals("1")){
			nama_mahkamah = getAccMahkamah.get("NAMA_AKAUN").toString();
			namaBank_mahkamah = getAccMahkamah.get("NAMA_BANK").toString();
			noAkaun_mahkamah = getAccMahkamah.get("NO_AKAUN").toString();
			
			namaBankMT = namaBank_mahkamah;
			namaMT = nama_mahkamah;
			noAkaunMT = noAkaun_mahkamah;
		}else{
			if(id_negeri.equals("10")){
				namaBankMT = "Affin Bank (Cawangan Shah Alam)";
				noAkaunMT = "1059-9000-3766";				
			}else if(id_negeri.equals("8")){
				namaBankMT = "Affin Bank Malaysia Berhad";
				noAkaunMT = "106110003215";
			}			
		}
    	  	 
		myLogger.info("nama_mahkamah :::"+nama_mahkamah);
		myLogger.info("namaBank_mahkamah :::"+namaBank_mahkamah);
		myLogger.info("noAkaun_mahkamah :::"+noAkaun_mahkamah);
    	
		if ("doChangePegawai".equals(submit) || "doChangePegawai1".equals(submit) 
			|| "doChangePegawai2".equals(submit)) {
    		
    		sysdateMalay = getParam("txtTarikhSuratCetak");
    		sysdateEng = getParam("txtTarikhSuratCetakBI");
    		namaMT = getParam("txtNamaAkaunMT");   		
    		namaBankMT = getParam("txtNamaBankMT");
    		noAkaunMT = getParam("txtNomborAkaunMT");
    		namaARB = getParam("txtNamaAkaunARB");
    		namaBankARB = getParam("txtNamaBankARB");
    		noAkaunARB = getParam("txtNomborAkaunARB");
    		
    		//radio button choose fail
    		context.put("sorSelectNoFail", getParam("sorSelectNoFail"));
    		
    		id_pegawai = getParam("socPegawai");
        	id_pegawai1 = getParam("socPegawai1");
        	id_pegawai2 = getParam("socPegawai2");
    		
    		logic.setDetailPegawai(id_pegawai);
    		detailPegawai = logic.getDetailPegawai();
    		if(detailPegawai.size()!=0){
    			Hashtable dp = (Hashtable)detailPegawai.get(0);
    			nama_pegawai = (String)dp.get("nama_pegawai");
    			id_jawatan = (String)dp.get("id_jawatan");
    			jawatan = (String)dp.get("jawatan");
    			emel = (String)dp.get("emel");
    		}
    		
    		logic.setDetailPegawai(id_pegawai1);
    		detailPegawai = logic.getDetailPegawai();
    		if(detailPegawai.size()!=0){
    			Hashtable dp = (Hashtable)detailPegawai.get(0);
    			nama_pegawai1 = (String)dp.get("nama_pegawai");
    			//id_jawatan = (String)dp.get("id_jawatan");
    			//jawatan = (String)dp.get("jawatan");
    			//emel = (String)dp.get("emel");
    		}   		
    		
    		logic.setDetailPegawai(id_pegawai2);
    		detailPegawai = logic.getDetailPegawai();
    		if(detailPegawai.size()!=0){
    			Hashtable dp = (Hashtable)detailPegawai.get(0);
    			nama_pegawai2 = (String)dp.get("nama_pegawai");
    			//id_jawatan = (String)dp.get("id_jawatan");
    			//jawatan = (String)dp.get("jawatan");
    			//emel = (String)dp.get("emel");
    		}
    		
    	}//close doChangePegawai2
		
    	if(id_pegawai=="" || id_pegawai.isEmpty()){
    		id_pegawai = "0";
    	}
    	
    	if(id_pegawai1=="" || id_pegawai1.isEmpty()){
    		id_pegawai1 = "0";
    	}
    	
    	if(id_pegawai2=="" || id_pegawai2.isEmpty()){
    		id_pegawai2 = "0";
    	}
    	
    	if(id_negeri!=""){
			context.put("selectPegawai",HTML.SelectPegawaiPPTByNegeri(id_negeri,"socPegawai",Long.parseLong(id_pegawai),"","onChange=\"doChangePegawai();\""));
			context.put("selectPegawai1",HTML.SelectPegawaiPPTByNegeri(id_negeri,"socPegawai1",Long.parseLong(id_pegawai1),"","onChange=\"doChangePegawai1();\""));
			context.put("selectPegawai2",HTML.SelectPegawaiPPTByNegeri(id_negeri,"socPegawai2",Long.parseLong(id_pegawai2),"","onChange=\"doChangePegawai2();\""));
			
		}else{
			context.put("selectPegawai",HTML.SelectPegawaiPPT("socPegawai", Long.parseLong(id_pegawai),"","onChange=\"doChangePegawai();\""));
			context.put("selectPegawai1",HTML.SelectPegawaiPPT("socPegawai1", Long.parseLong(id_pegawai1),"","onChange=\"doChangePegawai1();\""));
			context.put("selectPegawai2",HTML.SelectPegawaiPPT("socPegawai2", Long.parseLong(id_pegawai2),"","onChange=\"doChangePegawai2();\""));
		}
    	
    	//dropdown pilihan pengarah dan bekas pengarah
		context.put("selectPengarah",HTML.SelectPengarahDanBekasPengarah(id_negeri,"socPengarah",null,"","style=width:auto"));
		context.put("selectPPengarah",HTML.SelectPenolongPengarahPPT(id_negeri,"socPPengarah",null,"","style=width:auto"));
		
		String idNegeri = getParam("socNegeri");
		context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(idNegeri),null,"style=width:auto onChange=\"onchangeNegeri();\""));

    	//set data and id
    	context.put("dateToday",sysdateMalay);
    	context.put("sysdateEng",sysdateEng);
    	context.put("flagShowTarikhCetak",flagShowTarikhCetak);
    	
		context.put("id_fail", id_fail);
		context.put("no_fail", no_fail);
		context.put("no_rujukan_ptg", no_rujukan_ptg);
		context.put("no_rujukan_ptd", no_rujukan_ptd);
		context.put("no_rujukan_upt", no_rujukan_upt);
		context.put("id_permohonan", id_permohonan);
		context.put("report", report);
		context.put("flagReport", flagReport);
		context.put("bilDokumen", bilDokumen);	
		
		context.put("bilLot", bilLot);
		context.put("masa_siasatan", masa_siasatan);
		
		//date in malay dy date
		String sysdateMalaytarikhBorangL = "";
				
		if(!tarikhBorangL.equals("")){
			logic.setMalayDateByDate(tarikhBorangL);
		    malaysianDateByDate = logic.getMalayDateByDate();
		    if(malaysianDateByDate.size()!=0){
		    	Hashtable md = (Hashtable)malaysianDateByDate.get(0);
		    	sysdateMalaytarikhBorangL = (String)md.get("sysdateMalayByDate");
		    }
		}
		
		context.put("tarikhBorangL", sysdateMalaytarikhBorangL);
		context.put("tempohBL", tempohBL);
		context.put("id_pegawai", id_pegawai);
		context.put("nama2Mukim", nama2Mukim);
    	context.put("nama_pegawai",Utils.escapeJavaScript(nama_pegawai));
    	context.put("nama_pegawai1",Utils.escapeJavaScript(nama_pegawai1));
    	context.put("nama_pegawai2",Utils.escapeJavaScript(nama_pegawai2));
    	context.put("jawatan",jawatan);
    	context.put("id_jawatan",id_jawatan);
    	context.put("nama_pengarah",Utils.escapeJavaScript(nama_pengarah));
    	context.put("id_tanahumum",id_tanahumum);
    	context.put("id_siasatan",id_siasatan);
    	context.put("id_hakmilik",id_hakmilik);
    	context.put("listLOT", listLOT);
    	context.put("listLOTHM", listLOTHM);
    	context.put("id_negeri", id_negeri);
    	context.put("totalHM", listMaklumatTanah.size());
    	context.put("flagJenisSuratCara", flagJenisSuratCara); 	
    	context.put("flagCetakJPBD", flagCetakJPBD);
    	context.put("projek_daerah", projek_daerah);
    	context.put("namaProjek", tujuanInit);
    	context.put("totalLuasAmbil", totalLuasAmbil);
    	context.put("totalLuasAmbil_nonformat", totalLuasAmbil_nonformat);
    	context.put("namaMukimInit", nama2MukimInit);
    	context.put("flagCetakSiasatan", flagCetakSiasatan);
    	context.put("id_permintaanukur", id_permintaanukur);
    	
    	context.put("id_mmk", id_mmk);
    	context.put("emel", emel);
    	
    	context.put("namaMT", namaMT);
    	context.put("namaBankMT", namaBankMT);
    	context.put("noAkaunMT", noAkaunMT);
    	context.put("namaBankARB", namaBankARB);
    	context.put("noAkaunARB", noAkaunARB);
    	context.put("namaARB", namaARB);	
    	
    	context.put("txtBilKertas", txtBilKertas);
    	context.put("txtBilSalinan", txtBilSalinan);
    	
    	context.put("txtNamaPentadbir", txtNamaPentadbir);
    	
    	context.put("id_buktipenyampaian", id_buktipenyampaian);
    	context.put("id_borangh", id_borangh);	
    	
    	context.put("bydate", bydate);
    	
    	context.put("id_borange", id_borange);
    	
    	//UNTUK PILIHAN JENIS NO FAIL
    	context.put("selectNoFail", selectNoFail);
    	
    	// REPORT BANTAHAN
    	context.put("id_bantahan",id_bantahan);
    	context.put("id_hakmilikpb",id_hakmilikpb);
    	context.put("id_bayaran", id_bayaran);
    	// END
    	    	
		//screen
		vm = "app/ppt/frmPopupCetakLaporanNew.jsp";
		return vm;
		
	}//close template
	
	
	public void checkTableWujud(String table_name,Db db)  throws Exception {		  
	  	int total = 0;
	  	String sql="";
	  	ResultSet rs = null;
		try {
			sql = " SELECT COUNT(*) AS total FROM user_tables where table_name = '"+table_name+"' ";
			//sql = " SELECT COUNT(*) as total FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+table_name+"' AND COLUMN_NAME = '"+column_name+"' ";	
			rs = db.getStatement().executeQuery(sql); 
			if ( rs.next() ) { 
				total = rs.getInt("total"); 
			} 
			
		} finally {	} 
		
		if(total==0){				
			sql = "  CREATE TABLE TBLMAKLUMATACCAMANAHMAHKAMAH "+
					" (  "+
					" ID_NEGERI          NUMBER NOT NULL,  "+
					" NAMA_BANK        VARCHAR2(4000 BYTE),  "+
					" NAMA_AKAUN         VARCHAR2(4000 BYTE),  "+
					" NO_AKAUN         VARCHAR2(4000 BYTE),  "+
					" JENIS VARCHAR2(1 BYTE)  "+
					" ) ";
			rs = db.getStatement().executeQuery(sql); 			
			//ALTER TABLE supplier ADD supplier_name varchar2(50);				
		}
		
	}
	
	
	public void updateTableAcc(String id_negeri,String NAMA_BANK,String NAMA_AKAUN,String NO_AKAUN,String jenis) 
		throws Exception {
		Db db = null;
//		String sql = "";
		try {
			db = new Db();

			Statement stmt = db.getStatement();
//			SQLRenderer r = new SQLRenderer();
			String sql1 = " UPDATE TBLMAKLUMATACCAMANAHMAHKAMAH SET NAMA_BANK = '" + NAMA_BANK + "', NAMA_AKAUN = '"
					+ NAMA_AKAUN + "',NO_AKAUN = '" + NO_AKAUN + "' WHERE ID_NEGERI = '" + id_negeri + "' AND JENIS = '"
					+ jenis + "' ";
			myLogger.info("UPDATE TBLHAKMILIKMASALAH ID_PLA :" + sql1.toUpperCase());
			stmt.executeUpdate(sql1);
			// conn.commit();
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	public void insertTableAcc(String id_negeri,String NAMA_BANK,String NAMA_AKAUN,String NO_AKAUN,String jenis) 
		throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
		
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();			
			r.clear();
			
			r.add("ID_NEGERI",id_negeri);
			r.add("JENIS",jenis);
			r.add("NAMA_BANK",NAMA_BANK);
			r.add("NAMA_AKAUN",NAMA_AKAUN);
			r.add("NO_AKAUN",NO_AKAUN);
			sql = r.getSQLInsert("TBLMAKLUMATACCAMANAHMAHKAMAH");
			stmt.executeUpdate(sql);
			//conn.commit();				
		} finally {
			if (db != null)
				db.close();
		}
	
	}
		
	public Vector listPenghantarNotis(String id_negeri) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			String sql = " 	SELECT DISTINCT U.USER_ID AS ID_PEGAWAI, U.USER_NAME AS NAMA_PEGAWAI FROM USERS U, USERS_INTERNAL UI "
					+ " 	WHERE U.USER_ID = UI.USER_ID  " +
					// " AND UI.ID_SEKSYEN = '1' "+
					// " AND ID_JAWATAN IN (26) "+
					"  AND U.USER_ROLE = '(PPT)PenghantarNotis'" + " 			AND UI.ID_NEGERI = '" + id_negeri + "' "
					+ " 			ORDER BY LPAD(U.USER_NAME,10) ";
			myLogger.info("LIST PENGHANTAR NOTIS :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable<String,String>>listPenghantarNotis = new Vector<Hashtable<String,String>>();

			Hashtable<String,String> h = null;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("ID_PEGAWAI", rs.getString("ID_PEGAWAI") == null ? "" : rs.getString("ID_PEGAWAI"));
				h.put("NAMA_PEGAWAI", rs.getString("NAMA_PEGAWAI") == null ? "" : rs.getString("NAMA_PEGAWAI"));
				listPenghantarNotis.addElement(h);
			}
			return listPenghantarNotis;

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public  Hashtable<String,String> getAccAmanahRaya(String id_negeri,String jenis_acc) throws Exception {	    
		Db db = null;
		String sql = "";
		  
		try {		      	
			db = new Db();
			Statement stmt = db.getStatement();
		     
			sql = "SELECT ID_NEGERI,NAMA_BANK,NAMA_AKAUN,NO_AKAUN,JENIS FROM TBLMAKLUMATACCAMANAHMAHKAMAH WHERE ID_NEGERI = '"+id_negeri+"' AND JENIS='"+jenis_acc+"' AND ROWNUM < 2";
			myLogger.info("GET ACC :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
//			Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();		      
			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
			int c = 0;
			while (rs.next()) {
				//h = new Hashtable();
				h.put("ID_NEGERI", rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK")==null?"":rs.getString("NAMA_BANK"));
				h.put("NAMA_AKAUN", rs.getString("NAMA_AKAUN")==null?"":rs.getString("NAMA_AKAUN"));
				h.put("NO_AKAUN", rs.getString("NO_AKAUN")==null?"":rs.getString("NO_AKAUN"));
				h.put("JENIS", rs.getString("JENIS")==null?"":rs.getString("JENIS"));
				c =1;
					//list.addElement(h);
			}
			h.put("CHECK_VAL", ""+c);
		     return h;
	    } finally {
		    if (db != null) db.close();
	    }
		  
	}//find alamat kementerian
	
	
}//close class

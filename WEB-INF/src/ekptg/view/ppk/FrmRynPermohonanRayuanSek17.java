package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmRynSek8Rayuan;
import ekptg.model.ppk.FrmRynSek8SemakPenerimaan;
/*
 * @author
 * Muhamad Syazreen bin Yahaya
 */

public class FrmRynPermohonanRayuanSek17 extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmRynPermohonanRayuanSek17.class);

	private static final long serialVersionUID = 1L;

	//model name
	FrmPrmhnnSek8Notis model1 = new FrmPrmhnnSek8Notis();
	FrmRynSek8Rayuan model2 = new FrmRynSek8Rayuan();
	FrmRynSek8SemakPenerimaan model3 = new FrmRynSek8SemakPenerimaan();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();

	@SuppressWarnings("unchecked")
	@Override
	public String doTemplate2() throws Exception{

		HttpSession session = request.getSession();

    	String vm = "";

    	String action = getParam("action");
    	headerppk_baru_default();

    	String screensenarai = "app/ppk/frmRynSenaraiSek17.jsp";
    	String screen = "app/ppk/frmRynPermohonanRayuanSemakPenerimaanSek17.jsp";
    	String mainscreen = "app/ppk/frmRynPermohonanRayuanMaklumatPerayuSek17.jsp";

    	String doPost = (String) session.getAttribute("doPost");

    	//vector
    	Vector list = new Vector();
    	Vector listCarian = new Vector();
    	Vector dataPemohon = new Vector();
    	Vector dataSemakK2 = new Vector();
    	Vector bayaranK2 = new Vector();
    	Vector maklumatRayuan = new Vector();
    	Vector maklumatSerahan = new Vector();
    	Vector listOB = new Vector();
    	Vector onchangeListOB = new Vector();
    	Vector listFirma = new Vector();
    	Vector dataLatestPeguam = new Vector();
    	Vector otherPeguam = new Vector();
    	Vector listOBatas18 = new Vector();
    	Vector listFirmaTR = new Vector();
    	Vector detailFirma = new Vector();
    	Vector listFirmaTerdahulu = new Vector();
    	Vector bayaranKR = new Vector();
    	Vector keputusanPermohonan = new Vector();
    	Vector dataNotis = new Vector();
    	Vector perintah = new Vector();
    	Vector flagRayuan = new Vector();

    	//02122009
    	Vector penghantarNotis = new Vector();
    	Vector penghantarNotisByJkptg = new Vector();
    	Vector getSelectedPenghantarNotis = new Vector();
    	Vector getSelectedPenghantarNotisByJkptg = new Vector();
    	Vector getPenghantarNotis = new Vector();
    	Vector dataMstRayuan = new Vector();
    	dataMstRayuan.clear();
    	penghantarNotisByJkptg.clear();
    	penghantarNotis.clear();
    	getSelectedPenghantarNotisByJkptg.clear();
    	getSelectedPenghantarNotis.clear();
    	getPenghantarNotis.clear();

    	//vector list clear
    	flagRayuan.clear();
    	perintah.clear();
    	dataNotis.clear();
    	keputusanPermohonan.clear();
    	listFirmaTerdahulu.clear();
    	detailFirma.clear();
    	listFirmaTR.clear();
    	list.clear();
    	listCarian.clear();
    	dataPemohon.clear();
    	dataSemakK2.clear();
    	bayaranK2.clear();
    	maklumatRayuan.clear();
    	maklumatSerahan.clear();
    	listOB.clear();
    	onchangeListOB.clear();
    	listFirma.clear();
    	dataLatestPeguam.clear();
    	otherPeguam.clear();
    	listOBatas18.clear();
    	bayaranKR.clear();

    	context.put("Util", new lebah.util.Util());
    	context.put("onchangeUPDATE", "no");
    	context.put("changeDATA", "");
    	context.put("modeBayaran","");
    	context.put("bayaranNB", "0.00");
		context.put("totalWord", "0");
		context.put("onchangeNegeri", "no");

		//03122009
		context.put("totalWordPerkaraRayu","0");
		context.put("totalWordAsasKeputusan", "0");

    	String usid="";
		usid=(String)session.getAttribute("_ekptg_user_id");

		Vector listdepan = new Vector();
   		listdepan.clear();
   		//model2.setListDefaultSek17(usid);
		//listdepan = model2.getListDefaultSek17();

    	String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
    	String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
    	String flagForView = getParam("flagForView");

    	String idprmhn = getParam("id_permohonan");

    	//get idsuburusanstatusfail
   		Vector getIdSuburusanstatusfail = new Vector();
    	getIdSuburusanstatusfail.clear();
    	getIdSuburusanstatusfail = model1.getIdSubUrusanStatusFail(idprmhn);
    	String id_suburusanstatusfail = "";
    	if(getIdSuburusanstatusfail.size()!=0){
    		Hashtable idsubf = (Hashtable) getIdSuburusanstatusfail.get(0);
    		id_suburusanstatusfail = idsubf.get("id_suburusanstatusfail").toString();
    	}

    	//get id keputusan permohonan
		keputusanPermohonan = model1.getKeputusanPermohonan(idprmhn);
		String idkp = "";
		if(keputusanPermohonan.size()!=0){
			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
			idkp = kp.get("id_keputusanpermohonan").toString();
		}

		//--get id perbicaraan by max(id_perbicaraan)
		model1.setListSemakWithData(idkp);
		dataNotis = model1.getListSemakWithData();
		String idperbicaraan = "";
		if(dataNotis.size()!=0){
			Hashtable idn = (Hashtable) dataNotis.get(0);
			idperbicaraan = idn.get("id_perbicaraan").toString();
		}


		//get id perintah
		perintah = model2.getPerintah(idperbicaraan);
		String id_perintah = "";
		if(perintah.size()!=0){
			Hashtable per = (Hashtable) perintah.get(0);
			id_perintah = per.get("id_perintah").toString();
		}

		//get info pemohon
    	model1.setListSemak(idprmhn,usid);
		dataPemohon = model1.getListSemak();
		headerppk_baru(session,idprmhn,"Y","","T");
		

		String id_permohonansimatiINT = "";

		if(dataPemohon.size()!=0){
			Hashtable ls = (Hashtable) dataPemohon.get(0);
			id_permohonansimatiINT = ls.get("id_permohonansimati").toString();
		}

    	context.put("flagRadio", "");

    	//command 1
    	String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);

    	//semak penerimaan rayuan k2
    	if ("semakRayuan".equals(submit)){


    		context.put("offCb1","");
			context.put("checkCb2","");
			context.put("checkCb1","");
			context.put("offCb2","");

    		String id_permohonan = "";
    		id_permohonan = getParam("id_permohonan");

    		int flagRadio = 0;
    		flagRadio = getParamAsInteger("flag");
    		context.put("flagRadio", flagRadio);

    		if(flagRadio!=0){
    			//lebih 14 hari
    			context.put("offCb1","disabled");
    			context.put("checkCb2","checked");
    			context.put("checkCb1","");
    			context.put("offCb2","");
    		}else{
    			//dalam 14 hari
    			context.put("checkCb1","checked");
    			context.put("offCb2","disabled");
    			context.put("offCb1","");
    			context.put("checkCb2","");
    		}

    		//reset all id
    		context.put("id_permohonan", "");
    		context.put("id_pemohon", "");
    		context.put("id_status", "");
    		context.put("id_perayu","");
    		context.put("id_rayuan","");
    		context.put("id_peguam","");
    		context.put("id_peguamX","");
    		context.put("id_serahanrayuan","");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String idFail = "";
    		String idPemohon = "";
    		int idStatus = 0;
    		String tarikh_rayuan = "";

    		if(dataPemohon.size()!=0){
    			Hashtable ls = (Hashtable) dataPemohon.get(0);
    			idFail = ls.get("idFail").toString();
    			idPemohon = ls.get("idPemohon").toString();
    			idStatus = Integer.parseInt(ls.get("id_Status").toString());
    			tarikh_rayuan = ls.get("tarikh_rayuan").toString();
    		}

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_status", idStatus);
    		context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("Util", new lebah.util.Util());
    		context.put("tarikh_rayuan", tarikh_rayuan);

    		//set zero
    		context.put("check1", "");
    		context.put("check2", "");
    		context.put("check3", "");
    		context.put("check4", "");
    		context.put("check5", "");
    		context.put("check6", "");
    		context.put("noresitF", "");
    		context.put("tarikhBF", "");
    		context.put("noresitD", "");
    		context.put("tarikhBD", "");
    		context.put("jumlahD", "");
    		context.put("id_bayaranF","");
    		context.put("id_bayaranD","");
    		context.put("id_perintah", "");


    		if(idStatus!=21 && idStatus!=25){

    			context.put("offCb1x","");
    			context.put("offCb2x","");

    			id_permohonan = getParam("id_permohonan");

        		//get checkbox
        		model2.setListSemakK2(id_permohonan);
        		dataSemakK2 = model2.getListSemakK2();

        		//get data bayaran
        		model2.setBayaranK2(id_permohonan);
        		bayaranK2 = model2.getBayaranK2();

        		//id
        		context.put("id_permohonan", id_permohonan);
        		context.put("id_status", idStatus);

        		//data & list
        		context.put("dataSemakK2", dataSemakK2);
        		context.put("bayaranK2", bayaranK2);

        		//form validation
        		context.put("newForm", "no");
        		context.put("editForm", "no");
        		context.put("Util", new lebah.util.Util());

        		//command 3
            	String submit3 = getParam("command3");
            	myLogger.info("[submit]:3 :: " +submit3);

            	if ("kemaskiniSemakanK2".equals(submit3)){

            		id_permohonan = getParam("id_permohonan");

            		//get flag rayuan from tblppkperintah
            		flagRayuan = model2.getFlagRayuan(id_perintah);
            		String flag_rayuan = "";
            		if(flagRayuan.size()!=0){
            			Hashtable fp = (Hashtable) flagRayuan.get(0);
            			flag_rayuan = fp.get("flag_rayuan").toString();
            		}


            		if(!flag_rayuan.equals("0")){
            			//lebih 14 hari
            			context.put("offCb1x","disabled");
            			context.put("offCb2x","");
            		}else{
            			//dalam 14 hari
            			context.put("offCb1x","");
            			context.put("offCb2x","disabled");
            		}


            		//form validation
            		context.put("newForm", "no");
            		context.put("editForm", "yes");

            		//id
            		context.put("id_perintah", id_perintah);

            		//command 4
                	String submit4 = getParam("command4");
                	myLogger.info("[submit]:4 :: " +submit4);

                	if ("updateSemakanK2".equals(submit4)){

                		if (doPost.equals("true")) {

                			updateSemakanK2(session);
                		}

                		//refresh data
                		//get info pemohon
                    	model1.setListSemak(id_permohonan,usid);
                		dataPemohon = model1.getListSemak();
                		headerppk_baru(session,id_permohonan,"Y","","T");


                		if(dataPemohon.size()!=0){
                			Hashtable ls = (Hashtable) dataPemohon.get(0);
                			idFail = ls.get("idFail").toString();
                			idPemohon = ls.get("idPemohon").toString();
                			tarikh_rayuan = ls.get("tarikh_rayuan").toString();
                		}

                		//get checkbox
                		model2.setListSemakK2(id_permohonan);
                		dataSemakK2 = model2.getListSemakK2();

                		//get data bayaran
                		model2.setBayaranK2(id_permohonan);
                		bayaranK2 = model2.getBayaranK2();

                		//id
                		context.put("id_permohonan",id_permohonan);
                		context.put("id_fail", idFail);
                		context.put("id_suburusanstatusfail", id_suburusanstatusfail);
                		context.put("id_pemohon",idPemohon);

                		//data & list
                		context.put("dataSemakK2", dataSemakK2);
                		context.put("bayaranK2", bayaranK2);
                		context.put("tarikh_rayuan", tarikh_rayuan);

                		//form validation
                		context.put("newForm", "no");
                		context.put("editForm", "no");

                	}//close updateSemakanK2
                }//close kemaskiniSemakanK2


    		}else if(idStatus==21 || idStatus==25){

    			//form validation
        		context.put("newForm", "yes");
        		context.put("editForm", "");

        		//data & list
        		context.put("dataSemakK2", "");
        		context.put("bayaranK2", "");

        		//command 2
            	String submit2 = getParam("command2");
            	myLogger.info("[submit]:2 :: " +submit2);

            	if ("simpanSemakanK2".equals(submit2)){

            		String id = getParam("id_permohonan");

            		if (doPost.equals("true")) {
            			simpanSemakanK2(session,id_perintah);
            		}

            		//get info pemohon
                	model1.setListSemak(id_permohonan,usid);
            		dataPemohon = model1.getListSemak();
            		headerppk_baru(session,id_permohonan,"Y","","T");

            		if(dataPemohon.size()!=0){
            			Hashtable ls = (Hashtable) dataPemohon.get(0);
            			idFail = ls.get("idFail").toString();
            			idPemohon = ls.get("idPemohon").toString();
            			tarikh_rayuan = ls.get("tarikh_rayuan").toString();
            		}

            		//id
            		context.put("id_permohonan", id_permohonan);
            		context.put("id_status", idStatus);
            		context.put("id_fail", idFail);
            		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

            		//data & list
            		context.put("dataPemohon", dataPemohon);
            		context.put("tarikh_rayuan", tarikh_rayuan);

            		//get checkbox
            		model2.setListSemakK2(id_permohonan);
            		dataSemakK2 = model2.getListSemakK2();

            		String statusID = "";

            		if(dataSemakK2.size()!=0){
            			Hashtable a = (Hashtable) dataSemakK2.get(0);
            			statusID = a.get("id_status").toString();
            		}

            		//get data bayaran
            		model2.setBayaranK2(id_permohonan);
            		bayaranK2 = model2.getBayaranK2();

            		//id
            		context.put("id_permohonan",id_permohonan);
            		context.put("id_status", statusID);
            		context.put("id_fail", idFail);
            		context.put("id_perintah", id_perintah);
            		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

            		//data & list
            		context.put("dataSemakK2", dataSemakK2);
            		context.put("bayaranK2", bayaranK2);

            		//form validation
            		context.put("newForm", "no");
            		context.put("editForm", "no");
            		context.put("onchangePP", "no");
            		context.put("onchangeXX", "");

            	}//close simpanSemakanK2

    		}//close else if(id_status==21)

    		vm = screen;

    	}//close semak penerimaan rayuan k2

    	else if("maklumatRayuan".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);

            //reset data
            context.put("checkA", "");
    		context.put("checkB", "");
    		context.put("cidob", "");
    		context.put("cnamaob", "");
	    	context.put("cnokp1", "");
	    	context.put("cnokp2", "");
	    	context.put("cnokp3", "");
	    	context.put("calamat1", "");
	    	context.put("calamat2", "");
	    	context.put("calamat3", "");
	    	context.put("cposkod", "");
	    	context.put("cbandar", "");
	    	context.put("nokplama", "");
    		context.put("nokplain", "");
    		context.put("nokpbaru", "");
    		context.put("jenis_kp", "");

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_simati = "";
    		String idFail = "";
    		String idnegerirayuan = "";
    		String idStatus = "";
    		String tarikh_rayuan = "";

    		Hashtable x = new Hashtable();

    		if(dataPemohon.size()!=0){
    			x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_simati = x.get("idSimati").toString();
    			idFail = x.get("idFail").toString();
    			idnegerirayuan = x.get("pmidnegeri").toString();
    			idStatus = x.get("id_Status").toString();
    			tarikh_rayuan = x.get("tarikh_rayuan").toString();
    		}

    		if(!idnegerirayuan.equals("0")){
    			context.put("idnegerirayuan", idnegerirayuan);
    		}else{
    			context.put("idnegerirayuan", "");
    		}


    		//get list firma
    		listFirmaTR = model2.getListFirmaTerdahulu(id_pemohon);
    		context.put("listFirmaTerdahulu",listFirmaTR);
    		context.put("listFirmaTerdahulu_size",listFirmaTR.size());

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		//get data ob
    		model1.setListOB(id_permohonansimatiINT,id_simati);
    		listOB = model1.getListOB();


    		//get data ob
    		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
    		listOBatas18 = model1.getListOBatas18();
    		context.put("listOBatas18", listOBatas18);

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_status", idStatus);
    		context.put("id_rayuan","");
    		context.put("id_peguamX","");
    		context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("listOB", listOB);
    		context.put("tarikh_rayuan", tarikh_rayuan);

    		String idp = getParam("id_permohonan");

    		//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());
    		System.out.println(listFirmaTerdahulu.size());

    		//dropdown
    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
    		context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
    		context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
    		context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",null,"style=width:240"));
    		context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));


    		//form validation
    		context.put("newform", "yes");
    		context.put("editform", "no");
    		context.put("onchangePP", "no");
    		context.put("formTambah", "no");
    		context.put("showSOC", "yes");
			context.put("showTXT", "no");
			context.put("onchangeXX", "");

    		//reset data
    		context.put("namaPerayu","");
    		context.put("nokpPerayu1","");
    		context.put("nokpPerayu2","");
    		context.put("nokpPerayu3","");
    		context.put("alamatPerayu1","");
    		context.put("alamatPerayu2","");
    		context.put("alamatPerayu3","");
    		context.put("poskodPerayu","");
    		context.put("bandarPerayu","");
    		context.put("namaFirma","");
    		context.put("noRujukan","");
    		context.put("alamatPeguam1","");
    		context.put("alamatPeguam2","");
    		context.put("alamatPeguam3","");
    		context.put("poskodPeguam","");
    		context.put("bandarPeguam","");
    		context.put("noTel","");
    		context.put("noFax","");
    		context.put("emel","");
    		context.put("idFirma", "");


    		//command x
        	String submitx = getParam("commandx");
        	myLogger.info("[submit]x :: " +submitx);

    		if ("changeGetAlamatPerayu".equals(submitx)){



    			id_permohonan = getParam("id_permohonan");
        		id_status = getParamAsInteger("id_status");

        		String socNamaFirma = getParam("socNamaFirma");

        		//get data peguam
    	    	String txtNamaFirma = getParam("txtNamaFirma");
    	    	String txtNoRujukan = getParam("txtNoRujukan");
    	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
    	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
    	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
    	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
    	    	String idBandarPeguam = getParam("txtBandarPeguam");
    	    	String txtNoTelefon = getParam("txtNoTelefon");
    	    	String txtNoFaks = getParam("txtNoFaks");
    	    	String txtEmel = getParam("txtEmel");
    	    	String socNegeriPeguam = getParam("socNegeriPeguam");

    	    	String sorPeguam = getParam("sorPeguam");

    	    	//dropdown firma terdahulu validation
        		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
        		context.put("saiz_ft", listFirmaTerdahulu.size());

        		//get data by idob
        		String idListOB = getParam("socPerayu");
        		String cidob = "";
        		String cnamaob = "";
        		String nokp1 = "";
        		String nokp2 = "";
        		String nokp3 = "";
        		String almt1 = "";
        		String almt2 = "";
        		String almt3 = "";
        		String poskod = "";
        		String bandar = "";
        		String idnegeri = "";
        		String idbandarperayu = "";
        		String nokplama = "";
        		String nokplain = "";
        		String nokpbaru = "";
        		String jenis_kp = "";

        		if(idListOB!=""){

        			onchangeListOB = model2.getOnchangeListOB(idListOB);

            		if(onchangeListOB.size()!=0)
            		{
            			Hashtable xy = (Hashtable) onchangeListOB.get(0);

            			//call data
            			cidob = xy.get("id_ob").toString();
            			cnamaob = xy.get("nama_ob").toString();
                		nokp1 = xy.get("no_kp_baru1").toString();
                		nokp2 = xy.get("no_kp_baru2").toString();
                		nokp3 = xy.get("no_kp_baru3").toString();
                		almt1 = xy.get("alamat1").toString();
                		almt2 = xy.get("alamat2").toString();
                		almt3 = xy.get("alamat3").toString();
                		poskod = xy.get("poskod").toString();
                		bandar = xy.get("bandar").toString();
                		idnegeri = xy.get("id_negeri").toString();
                		idbandarperayu = xy.get("id_bandar").toString();
                		nokplama = xy.get("no_kp_lama").toString();
                		nokplain = xy.get("no_kp_lain").toString();
                		nokpbaru = xy.get("no_kp_baru").toString();
                		jenis_kp = xy.get("jenis_kp").toString();

                	}//close if(onchangeListOB.size()!=0)

        		}//if(idListOB!="")

        		//set data perayu
        		context.put("cidob", cidob);
        		context.put("cnamaob", cnamaob);
        		context.put("nokplama", nokplama);
        		context.put("nokplain", nokplain);
        		context.put("nokpbaru", nokpbaru);
        		context.put("jenis_kp", jenis_kp);
    	    	context.put("cnokp1", nokp1);
    	    	context.put("cnokp2", nokp2);
    	    	context.put("cnokp3", nokp3);
    	    	context.put("calamat1", almt1);
    	    	context.put("calamat2", almt2);
    	    	context.put("calamat3", almt3);
    	    	context.put("cposkod", poskod);
    	    	context.put("cbandar", bandar);


    	    	if(idnegeri!=""){
    	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
    	    		if(idbandarperayu!=""){
            			context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"txtBandarPerayu",Utils.parseLong(idbandarperayu),"style=width:240"));
                	}else{
                		context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"txtBandarPerayu",null,"style=width:240"));
                    }
    	    	}else{
    	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
    	    		if(idbandarperayu!=""){
    					context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",Utils.parseLong(idbandarperayu),"style=width:240"));
    				}else{
    					context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",null,"style=width:240"));
        			}
    	    	}

        		//set data peguam
        		context.put("cnamafirma", txtNamaFirma);
        		context.put("cnorujukan", txtNoRujukan);
        		context.put("calamatpeguam1", txtAlamatPeguam1);
        		context.put("calamatpeguam2", txtAlamatPeguam2);
        		context.put("calamatpeguam3", txtAlamatPeguam3);
        		context.put("cposkodpeguam", txtPoskodPeguam);
        		context.put("cnotel", txtNoTelefon);
        		context.put("cnofaks", txtNoFaks);
        		context.put("cemel", txtEmel);


        		if(socNegeriPeguam!=""){
        			context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
        			if(idBandarPeguam!=""){
         				context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
         	    	}else{
         				context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
         	    	}
        		}else{
    	    		context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
    	    		if(idBandarPeguam!=""){
     	    			context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
     	       		}else{
         				context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
         	    	}
        		}


        		if(sorPeguam.equals("1")){
        			context.put("showSOC", "yes");
        			context.put("showTXT", "no");
    	    		context.put("checkA", "checked");
    	    		context.put("checkB", "");
    	    	}else if(sorPeguam.equals("2")){
    	    		context.put("showSOC", "no");
        			context.put("showTXT", "yes");
    	    		context.put("checkA", "");
    	    		context.put("checkB", "checked");
    	    	}

        		idp = getParam("id_permohonan");

    	    	if(socNamaFirma!=""){
        			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
        		}else{
        			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
            	}

    	    	//validation
    	    	context.put("onchangePP", "yes");
    	    	context.put("newform", "yes");
        		context.put("editform", "no");
        		context.put("formTambah", "no");
        		context.put("onchangeXX", "");

        		//id
    	    	context.put("id_permohonan", id_permohonan);
    	    	context.put("id_status", id_status);

        	}//close onchange


    		if(maklumatRayuan.size()!=0){

    			String idnegeriPerayu = "";
        		String idbandarPerayu = "";
        		String id_perayu = "";
        		String id_rayuan = "";

    			//maklumat perayu
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);

        		idnegeriPerayu = mr.get("negeri_perayu").toString();
        		idbandarPerayu = mr.get("id_bandar_perayu").toString();
        		id_perayu = mr.get("id_perayu").toString();
        		id_rayuan = mr.get("id_rayuan").toString();


        		String idnegeriPeguam = "";
        		String idbandarPeguam = "";
        		String id_peguam = "";

        		//get maklumat peguam yang latest
        		model2.setLatestPeguam(id_perayu);
        		dataLatestPeguam = model2.getLatestPeguam();

        		if(dataLatestPeguam.size()!=0){
        			Hashtable lat = (Hashtable) dataLatestPeguam.get(0);
        			idnegeriPeguam = lat.get("id_negeri").toString();
        			idbandarPeguam = lat.get("id_bandar").toString();
        			id_peguam = lat.get("id_peguam").toString();
        		}

        		//get list firma
        		listFirma = model2.getListFirma(id_perayu);
        		context.put("listFirma",listFirma);
        		context.put("listFirma_size",listFirma.size());


        		//get data ob
        		model1.setListOB(id_permohonansimatiINT,id_simati);
        		listOB = model1.getListOB();

        		//get data ob
        		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
        		listOBatas18 = model1.getListOBatas18();
        		context.put("listOBatas18", listOBatas18);

    			//data & list
        		context.put("dataLatestPeguam",dataLatestPeguam);
        		context.put("maklumatRayuan", maklumatRayuan);
        		context.put("listOB", listOB);

        		//id
        		context.put("id_perayu",id_perayu);
        		context.put("id_peguam",id_peguam);
        		context.put("id_rayuan",id_rayuan);

        		//form validation
        		context.put("newform", "no");
        		context.put("editform", "no");
        		context.put("onchangePP", "no");
        		context.put("formTambah", "no");
        		context.put("onchangeXX", "");

        		//dropdown
        		if(idnegeriPerayu!=""){
        			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeriPerayu),"class=disabled disabled style=width:240"));
        			if(idbandarPerayu!=""){
            			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"class=disabled disabled style=width:240"));
                	}else{
                		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                    }
        		}else{
        			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,"class=disabled disabled style=width:240"));
        			if(idbandarPerayu!=""){
    					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"class=disabled disabled style=width:240"));
    				}else{
    					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
        			}
        		}


        		if(idnegeriPeguam!=""){
        			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(idnegeriPeguam),"class=disabled disabled style=width:240"));
        			if(idbandarPeguam!=""){
        				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"class=disabled disabled style=width:240"));
             	    }else{
             	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",null,"class=disabled disabled style=width:240"));
                  	}
        		}else{
        			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,"class=disabled disabled style=width:240"));
        			if(idbandarPeguam!=""){
        				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"class=disabled disabled style=width:240"));
             	    }else{
        				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"class=disabled disabled style=width:240"));
             	    }
        		}

        		//command 2
            	String submit2 = getParam("command2");
            	myLogger.info("[submit]2 :: " +submit2);

            	if("kemaskiniMaklumatPP".equals(submit2)){

            		//form validation
            		context.put("newform", "no");
            		context.put("editform", "yes");
            		context.put("onchangePP", "no");
            		context.put("formTambah", "no");
            		context.put("checkBx", "checked");
            		context.put("EshowTXT", "yes");
            		context.put("EshowSOC", "no");
            		context.put("onchangeXX", "");

            		//id
            		context.put("id_permohonan", id_permohonan);
            		context.put("id_pemohon", id_pemohon);
            		context.put("id_status", id_status);
            		context.put("id_perayu",id_perayu);
            		context.put("id_peguam",id_peguam);

            		//get data ob
            		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
            		listOBatas18 = model1.getListOBatas18();
            		context.put("listOBatas18", listOBatas18);

            		//dropdown firma terdahulu validation
            		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
            		context.put("saiz_ft", listFirmaTerdahulu.size());


            		//data & list
            		context.put("maklumatRayuan", maklumatRayuan);
            		context.put("dataPemohon", dataPemohon);
            		context.put("listOB", listOB);

            		//dropdown
            		if(idnegeriPerayu!=""){
            			context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",Utils.parseLong(idnegeriPerayu),null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
            			if(idbandarPerayu!=""){
                			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"style=width:240"));
                    	}else{
                    		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",null,"style=width:240"));
                        }
            		}else{
            			context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
            			if(idbandarPerayu!=""){
        					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"style=width:240"));
        				}else{
        					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
            			}
            		}

            		if(idnegeriPeguam!=""){
            			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(idnegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
            			if(idbandarPeguam!=""){
            				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"style=width:240"));
                 	    }else{
                 	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",null,"style=width:240"));
                      	}
            		}else{
            			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
            			if(idbandarPeguam!=""){
            				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"style=width:240"));
                 	    }else{
            				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
                 	    }
            		}

            		context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirmaUPDATE()"));


            		//command 3
                	String submit3 = getParam("command3");
                	myLogger.info("[submit]3 :: " +submit3);

                	if("updateMaklumatPP".equals(submit3)){


                		if (doPost.equals("true")) {

                			updateMaklumatPP(session);

                		}


                		String idFail2 = "";

                		//get info pemohon
                    	model1.setListSemak(id_permohonan,usid);
                		dataPemohon = model1.getListSemak();
                		headerppk_baru(session,id_permohonan,"Y","","T");
                		idFail2 = x.get("idFail").toString();

                		//get data Maklumat perayu
                		//model2.setDataMaklumat(id_permohonan,id_pemohon);
                		model2.setDataMaklumat(id_permohonan,id_perintah);
                		maklumatRayuan = model2.getDataMaklumat();
                		context.put("maklumatRayuan_size", maklumatRayuan.size());

                		String idnegeriPerayu2 = "";
            			String idbandarPerayu2 = "";

                		if(maklumatRayuan.size()!=0){
                			Hashtable mr2 = (Hashtable) maklumatRayuan.get(0);
                			idnegeriPerayu2 = mr2.get("negeri_perayu").toString();
                			idbandarPerayu2 = mr2.get("id_bandar_perayu").toString();
                			id_perayu = mr2.get("id_perayu").toString();
                		}

                		String idnegeriPeguam2 = "";
                		String idbandarPeguam2 = "";
                		String id_peguam2 = "";

                		//get maklumat peguam yang latest
                		model2.setLatestPeguam(id_perayu);
                		dataLatestPeguam = model2.getLatestPeguam();

                		if(dataLatestPeguam.size()!=0){
                			Hashtable lat2 = (Hashtable) dataLatestPeguam.get(0);
                			idnegeriPeguam2 = lat2.get("id_negeri").toString();
                			idbandarPeguam2 = lat2.get("id_bandar").toString();
                			id_peguam2 = lat2.get("id_peguam").toString();
                		}

                		//get list firma
                		listFirma = model2.getListFirma(id_perayu);
                		context.put("listFirma",listFirma);
                		context.put("listFirma_size",listFirma.size());

                		//form validation
                		context.put("newform", "no");
                		context.put("editform", "no");
                		context.put("onchangePP", "no");
                		context.put("formTambah", "no");
                		context.put("onchangeXX", "");

                		//refresh data
                		//data & list
                		context.put("maklumatRayuan", maklumatRayuan);
                		context.put("dataPemohon", dataPemohon);

                		//id
                		context.put("id_permohonan", id_permohonan);
                		context.put("id_pemohon", id_pemohon);
                		context.put("id_status", id_status);
                		context.put("id_perayu",id_perayu);
                		context.put("id_peguam",id_peguam2);
                		context.put("id_fail",idFail2);
                		context.put("id_suburusanstatusfail",id_suburusanstatusfail);


                		//dropdown
                		if(idnegeriPerayu2!=""){
                			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeriPerayu2),"class=disabled disabled style=width:240"));
                			if(idbandarPerayu2!=""){
                    			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu2,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu2),"class=disabled disabled style=width:240"));
                        	}else{
                        		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu2,"EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                            }
                		}else{
                			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,"class=disabled disabled style=width:240"));
                			if(idbandarPerayu2!=""){
            					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu2),"class=disabled disabled style=width:240"));
            				}else{
            					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                			}
                		}

                		if(idnegeriPeguam2!=""){
                			context.put("viewNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idnegeriPeguam2),"class=disabled disabled style=width:240"));
                			if(idbandarPeguam2!=""){
                				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam2,"EtxtBandarPeguam",Utils.parseLong(idbandarPeguam2),"class=disabled disabled style=width:240"));
                     	    }else{
                     	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam2,"EtxtBandarPeguam",null,"class=disabled disabled style=width:240"));
                     	    }
                		}else{
                			context.put("viewNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,"class=disabled disabled style=width:240"));
                			if(idbandarPeguam2!=""){
                 				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarPeguam2),"class=disabled disabled style=width:240"));
                      	    }else{
                 				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"class=disabled disabled style=width:240"));
                      	    }
                		}

                	}//close updatemaklumatpp

                	//onchange update
                	else if ("changeGetAlamatPerayuUPDATE".equals(submit3)){

            			id_permohonan = getParam("id_permohonan");
                		id_status = getParamAsInteger("id_status");

                		//get data peguam
                		String sorPeguam,txtNamaFirma,txtNoRujukan,txtAlamatPeguam1,txtAlamatPeguam2,
                		txtAlamatPeguam3,txtPoskodPeguam,idBandarPeguam,txtNoTelefon,txtNoFaks,
                		txtEmel,socNegeriPeguam,id_socNamaFirma = "";

                		sorPeguam = getParam("sorPeguamUpdate");
                		txtNamaFirma = getParam("EtxtNamaFirma");
            	    	txtNoRujukan = getParam("EtxtNoRujukan");
            	    	txtAlamatPeguam1 = getParam("EtxtAlamatPeguam1");
            	    	txtAlamatPeguam2 = getParam("EtxtAlamatPeguam2");
            	    	txtAlamatPeguam3 = getParam("EtxtAlamatPeguam3");
            	    	txtPoskodPeguam = getParam("EtxtPoskodPeguam");
            	    	idBandarPeguam = getParam("EtxtBandarPeguam");
            	    	txtNoTelefon = getParam("EtxtNoTelefon");
            	    	txtNoFaks = getParam("EtxtNoFaks");
            	    	txtEmel = getParam("EtxtEmel");
            	    	socNegeriPeguam = getParam("EsocNegeriPeguam");
            	    	id_socNamaFirma = getParam("socNamaFirma");

            	    	if(socNegeriPeguam!=""){
                			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
                			if(idBandarPeguam!=""){
                    			context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"EtxtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
                        	}else{
                        		context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"EtxtBandarPeguam",null,"style=width:240"));
                            }
            	    	}else{
            	    		context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
            	    		if(idBandarPeguam!=""){
            					context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
            				}else{
            					context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
                			}
            	    	}

            			//check balik radio
            			if(sorPeguam.equals("1")){

            				context.put("EshowSOC", "yes");
                			context.put("EshowTXT", "no");
            	    		context.put("checkAx", "checked");
            	    		context.put("checkBx", "");

            	    		if(id_socNamaFirma!=""){

                    	    	context.put("changeDATA", "databylist");

                    		}else{

                    			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
                    			context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
                    			context.put("changeDATA", "clear");

                    		}//close if(id_socNamaFirma!="")

            	    	}else if(sorPeguam.equals("2")){

            	    		context.put("EshowSOC", "no");
                			context.put("EshowTXT", "yes");
            	    		context.put("checkAx", "");
            	    		context.put("checkBx", "checked");
            	    		context.put("changeDATA", "databylist");

            	    	}else{

            	    		context.put("changeDATA", "databylist");

            	    	}

            			idp = getParam("id_permohonan");

            			//set data peguam = dropdown firma
            			if(id_socNamaFirma!=""){
            				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(id_socNamaFirma),"style=width:240 onchange=getDetailFirmaX()"));
            			}else{
            				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"style=width:240 onchange=getDetailFirmaX()"));
            			}

            			//set data peguam
                		context.put("namaFirma_", txtNamaFirma);
                		context.put("noRujukan_", txtNoRujukan);
                		context.put("alamatPeguam1_", txtAlamatPeguam1);
                		context.put("alamatPeguam2_", txtAlamatPeguam2);
                		context.put("alamatPeguam3_", txtAlamatPeguam3);
                		context.put("poskodpeguam_", txtPoskodPeguam);
            			//context.put("bandarpeguam_", txtBandarPeguam);
            			context.put("notel_", txtNoTelefon);
            			context.put("nofaks_", txtNoFaks);
            			context.put("emel_", txtEmel);


                		//get data perayu by idob
                		String idListOB = "";
                		String cidob = "";
                		String cnamaob = "";
                		String nokpbaru = "";
                		String nokplama = "";
                		String nokplain = "";
                		String nokp1 = "";
                		String nokp2 = "";
                		String nokp3 = "";
                		String almt1 = "";
                		String almt2 = "";
                		String almt3 = "";
                		String poskod = "";
                		String bandar = "";
                		String idnegeri = "";
                		String idbandar = "";

                		idListOB = getParam("EsocPerayu");

                		if(idListOB!=""){

                			onchangeListOB = model2.getOnchangeListOB(idListOB);
                    		Hashtable xy = (Hashtable) onchangeListOB.get(0);

                    		if(onchangeListOB.size()!=0)
                    		{
                    			//call data
                    			cidob = xy.get("id_ob").toString();
                    			cnamaob = xy.get("nama_ob").toString();
                        		nokp1 = xy.get("no_kp_baru1").toString();
                        		nokp2 = xy.get("no_kp_baru2").toString();
                        		nokp3 = xy.get("no_kp_baru3").toString();
                        		almt1 = xy.get("alamat1").toString();
                        		almt2 = xy.get("alamat2").toString();
                        		almt3 = xy.get("alamat3").toString();
                        		poskod = xy.get("poskod").toString();
                        		//bandar = xy.get("bandar").toString();
                        		idnegeri = xy.get("id_negeri").toString();
                        		idbandar = xy.get("id_bandar").toString();
                        		nokplama = xy.get("no_kp_lama").toString();
                        		nokplain = xy.get("no_kp_lain").toString();
                        		nokpbaru = xy.get("no_kp_baru").toString();

                        	}//close if(onchangeListOB.size()!=0)

                    	}//close if(idListOB!="")

                			//set data back
                			context.put("cidob", cidob);
                			context.put("cnamaob", cnamaob);
                			context.put("nokpPerayu", nokpbaru);
                			context.put("nokpLamaPerayu", nokplama);
                			context.put("nokpLainPerayu", nokplain);
                			context.put("nokpPerayu1", nokp1);
                			context.put("nokpPerayu2", nokp2);
                			context.put("nokpPerayu3", nokp3);
                			context.put("alamatPerayu1", almt1);
                			context.put("alamatPerayu2", almt2);
                			context.put("alamatPerayu3", almt3);
                			context.put("poskodPerayu", poskod);
                			//context.put("bandarPerayu", bandar);

                			if(idnegeri!=""){
                				context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
                				if(idbandar!=""){
                        			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
                            	}else{
                            		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
                                }
                			}else{
                				context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
                				if(idbandar!=""){
                					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
                				}else{
                					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
                    			}
                			}

            	    	//validation
            	    	context.put("onchangePP", "yes");
            	    	context.put("newform", "no");
                		context.put("editform", "yes");
                		context.put("formTambah", "no");
                		context.put("onchangeXX", "");

            	    	//id
            	    	context.put("id_permohonan", id_permohonan);
            	    	context.put("id_status", id_status);
            	    	context.put("id_pemohon", id_pemohon);

                	}//close onchange update

            	}//close kemaskiniMaklumatPP

            	else if("tambahPeguam".equals(submit2)){

            		id_permohonan = getParam("id_permohonan");
            		idp = getParam("id_permohonan");
            		id_status = getParamAsInteger("id_status");

            		//id
            		context.put("id_permohonan", id_permohonan);
            		context.put("id_status", id_status);

            		//dropdown firma terdahulu validation
            		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
            		context.put("saiz_ft", listFirmaTerdahulu.size());


            		//reset data
            		context.put("xidFirma", "");
            		context.put("xnamaFirma", "");
            		context.put("xnoRujukan", "");
            		context.put("xalamatPeguam1", "");
            		context.put("xalamatPeguam2", "");
            		context.put("xalamatPeguam3", "");
            		context.put("xposkodPeguam", "");
            		context.put("xbandarPeguam", "");
            		context.put("xnoTel", "");
            		context.put("xnoFax", "");
            		context.put("xemel", "");

            		//validation
        	    	context.put("onchangePP", "no");
        	    	context.put("newform", "no");
            		context.put("editform", "no");
            		context.put("formTambah", "yes");
            		context.put("onchangeXX", "");


            		context.put("tambahNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriTambah()"));
            		context.put("tambahBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
            		context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirmaTambah()"));

            		//command 2a
                	String submit2a = getParam("command2a");
                	myLogger.info("[submit]2a :: " +submit2a);

            		if("getBandarPeguamByNegeriTambah".equals(submit2a)){

            			String socNamaFirma = getParam("socNamaFirma");
            			String sorPeguam = getParam("sorPeguam");

            			//data
                		context.put("dataPemohon", dataPemohon);


            	    	//get data peguam
            	    	String txtNamaFirma = getParam("txtNamaFirma");
            	    	String txtNoRujukan = getParam("txtNoRujukan");
            	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
            	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
            	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
            	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
            	    	String txtNoTelefon = getParam("txtNoTelefon");
            	    	String txtNoFaks = getParam("txtNoFaks");
            	    	String txtEmel = getParam("txtEmel");
            	    	String socNegeriPeguam = getParam("socNegeriPeguam");

                		//set data peguam
            	    	//context.put("xidFirma", socNamaFirma);
                		context.put("namaFirma", txtNamaFirma);
                		context.put("xnoRujukan", txtNoRujukan);
                		context.put("xalamatPeguam1", txtAlamatPeguam1);
                		context.put("xalamatPeguam2", txtAlamatPeguam2);
                		context.put("xalamatPeguam3", txtAlamatPeguam3);
                		context.put("xposkodPeguam", txtPoskodPeguam);
                		context.put("xnoTel", txtNoTelefon);
                		context.put("xnoFax", txtNoFaks);
                		context.put("xemel", txtEmel);


                		if(socNegeriPeguam!=""){
                			context.put("tambahNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriTambah()"));
                			context.put("tambahBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
            	    	}else{
            	    		context.put("tambahNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriTambah()"));
            	    		context.put("tambahBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
            	    	}


                		if(sorPeguam.equals("1")){
                			context.put("showSOC", "yes");
                			context.put("showTXT", "no");
            	    		context.put("checkA", "checked");
            	    		context.put("checkB", "");
            	    	}else if(sorPeguam.equals("2")){
            	    		context.put("showSOC", "no");
                			context.put("showTXT", "yes");
            	    		context.put("checkA", "");
            	    		context.put("checkB", "checked");
            	    	}

                		idp = getParam("id_permohonan");

                		//dropdown firma terdahulu validation
                		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
                		context.put("saiz_ft", listFirmaTerdahulu.size());

            	    	if(socNamaFirma!=""){
            	    		context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirmaTambah()"));
                	    }else{
                			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirmaTambah()"));
                    	}

                		//id
            	    	context.put("id_permohonan", id_permohonan);
            	    	context.put("id_status", id_status);
            	    	context.put("id_pemohon", id_pemohon);
            	    	context.put("id_fail", idFail);
                		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

                	}//close get bandar peguam by idnegeri tambah


            	}//close tambahpeguam

            	else if("semakFirma".equals(submit2)){

            		id_permohonan = getParam("id_permohonan");
            		id_status = getParamAsInteger("id_status");

            		String id_peguamX = getParam("id_peguam");

            		otherPeguam = model2.getOtherPeguam(id_peguamX);

            		String idNegO = "";
        			String idBandO = "";

            		if(otherPeguam.size()!=0){
            			Hashtable xo = (Hashtable) otherPeguam.get(0);
            			idNegO = xo.get("id_negeri").toString();
            			idBandO = xo.get("id_bandar").toString();
            		}

            		//id
            		context.put("id_permohonan", id_permohonan);
            		context.put("id_status", id_status);
            		context.put("id_peguamX",id_peguamX);

            		//data
            		context.put("otherPeguam", otherPeguam);

            		//validation
        	    	context.put("onchangePP", "no");
        	    	context.put("newform", "no");
            		context.put("editform", "no");
            		context.put("formTambah", "viewANDedit");
            		context.put("editTambah", "no");
            		context.put("onchangeXX", "");
            		context.put("changeDATAx", "default");

            		if(idNegO!=""){
            			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idNegO),"class=disabled disabled style=width:240"));
            			if(idBandO!=""){
            				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(idNegO,"txtBandarPeguam",Utils.parseLong(idBandO),"class=disabled disabled style=width:240"));
            			}else{
            				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(idNegO,"txtBandarPeguam",null,"class=disabled disabled style=width:240"));
                		}
            		}else{
            			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,"class=disabled disabled style=width:240"));
            			if(idBandO!=""){
            				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandO),"class=disabled disabled style=width:240"));
            			}else{
            				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"class=disabled disabled style=width:240"));
                		}
            		}

            		//command xx
                	String submitxx = getParam("commandxx");
                	myLogger.info("[submit]xx :: " +submitxx);

                	if("kemaskiniX".equals(submitxx)){

                		String id_peguamXX = getParam("id_peguamX");

                		otherPeguam = model2.getOtherPeguam(id_peguamXX);

                		String idNegO2 = "";
                		String idBandO2 = "";

                		if(otherPeguam.size()!=0){
                			Hashtable xo2 = (Hashtable) otherPeguam.get(0);
                			idNegO2 = xo2.get("id_negeri").toString();
                			idBandO2 = xo2.get("id_bandar").toString();
                		}

                		//validation
            	    	context.put("onchangePP", "no");
            	    	context.put("newform", "no");
                		context.put("editform", "yes");
                		context.put("formTambah", "viewANDedit");
                		context.put("editTambah", "yes");
                		context.put("onchangeXX", "");


                		//data
                		context.put("otherPeguam", otherPeguam);

                		//id
                		context.put("id_peguamX",id_peguamXX);

                		if(idNegO2!=""){
                			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idNegO2),null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                			if(idBandO2!=""){
                				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(idNegO2,"txtBandarPeguam",Utils.parseLong(idBandO2),"style=width:240"));
                			}else{
                				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(idNegO2,"txtBandarPeguam",null,"style=width:240"));
                    		}
                		}else{
                			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                			if(idBandO2!=""){
                				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandO2),"style=width:240"));
                			}else{
                				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
                    		}
                		}



                		if(idnegeriPerayu!=""){
                			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeriPerayu),null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
                			if(idbandarPerayu!=""){
                    			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"style=width:240"));
                        	}else{
                        		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",null,"style=width:240"));
                            }
                		}else{
                			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
                			if(idbandarPerayu!=""){
            					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"style=width:240"));
            				}else{
            					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
                			}
                		}

                		//command xx
                    	String submitoc = getParam("commandoc");
                    	myLogger.info("[submit]oc :: " +submitoc);

                		//onchange update
                    	if ("changeGetAlamatPerayuUPDATETambah".equals(submitoc)){

                			id_permohonan = getParam("id_permohonan");
                    		id_status = getParamAsInteger("id_status");

                	    	//get data peguam
                	    	String txtNamaFirma = getParam("txtNamaFirma");
                	    	String txtNoRujukan = getParam("txtNoRujukan");
                	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
                	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
                	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
                	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
                	    	String idBandarPeguam = getParam("txtBandarPeguam");
                	    	String txtNoTelefon = getParam("txtNoTelefon");
                	    	String txtNoFaks = getParam("txtNoFaks");
                	    	String txtEmel = getParam("txtEmel");
                	    	String socNegeriPeguam = getParam("socNegeriPeguam");

                	    	//get data perayu by idob
                    		String idListOB = "";
                    		String cidob = "";
                    		String cnamaob = "";
                    		String nokpbaru = "";
                    		String nokplama = "";
                    		String nokplain = "";
                    		String nokp1 = "";
                    		String nokp2 = "";
                    		String nokp3 = "";
                    		String almt1 = "";
                    		String almt2 = "";
                    		String almt3 = "";
                    		String poskod = "";
                    		String bandar = "";
                    		String idnegeri = "";
                    		String idbandar = "";

                    		idListOB = getParam("EsocPerayu");

                    		if(idListOB!=""){

                    			onchangeListOB = model2.getOnchangeListOB(idListOB);
                        		Hashtable xy = (Hashtable) onchangeListOB.get(0);

                        		if(onchangeListOB.size()!=0)
                        		{
                        			//call data
                        			cidob = xy.get("id_ob").toString();
                        			cnamaob = xy.get("nama_ob").toString();
                            		nokp1 = xy.get("no_kp_baru1").toString();
                            		nokp2 = xy.get("no_kp_baru2").toString();
                            		nokp3 = xy.get("no_kp_baru3").toString();
                            		almt1 = xy.get("alamat1").toString();
                            		almt2 = xy.get("alamat2").toString();
                            		almt3 = xy.get("alamat3").toString();
                            		poskod = xy.get("poskod").toString();
                            		bandar = xy.get("bandar").toString();
                            		idnegeri = xy.get("id_negeri").toString();
                            		nokplama = xy.get("no_kp_lama").toString();
                            		nokplain = xy.get("no_kp_lain").toString();
                            		nokpbaru = xy.get("no_kp_baru").toString();
                            		idbandar = xy.get("id_bandar").toString();

                        		}//close if

                    		}//close if

                    				//set data back
                    				context.put("cidob", cidob);
                    				context.put("cnamaob", cnamaob);
                    				context.put("nokpPerayu1", nokp1);
                    				context.put("nokpPerayu2", nokp2);
                    				context.put("nokpPerayu3", nokp3);
                    				context.put("alamatPerayu1", almt1);
                    				context.put("alamatPerayu2", almt2);
                    				context.put("alamatPerayu3", almt3);
                    				context.put("poskodPerayu", poskod);
                    				context.put("bandarPerayu", bandar);
                    				context.put("nokpPerayu", nokpbaru);
                    				context.put("nokpLamaPerayu", nokplama);
                    				context.put("nokpLainPerayu", nokplain);

                    				if(idnegeri!=""){
                    					context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
                    					if(idbandar!=""){
                    						context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
                            			}else{
                            				context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
                                		}
                    				}else{
                    					context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
                    					if(idbandar!=""){
                    						context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
                            			}else{
                            				context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
                                		}
                    				}

                    		//set data peguam
                    		context.put("namaFx", txtNamaFirma);
                    		context.put("noRx", txtNoRujukan);
                    		context.put("alamatP1x", txtAlamatPeguam1);
                    		context.put("alamatP2x", txtAlamatPeguam2);
                    		context.put("alamatP3x", txtAlamatPeguam3);
                    		context.put("poskodPx", txtPoskodPeguam);
                    		//context.put("bandarPx", txtBandarPeguam);
                    		context.put("noTx", txtNoTelefon);
                    		context.put("noFx", txtNoFaks);
                    		context.put("emelOx", txtEmel);
                    		if(socNegeriPeguam!=""){
                    			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                    			if(idBandarPeguam!=""){
                    				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
                    			}else{
                    				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
                        		}
                    		}else{
                	    		context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                	    		if(idBandarPeguam!=""){
                    				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
                    			}else{
                    				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
                        		}
                    		}

                	    	//validation
                	    	context.put("onchangePP", "yes");
                	    	context.put("newform", "no");
                    		context.put("editform", "yes");
                    		context.put("formTambah", "viewANDedit");
                    		context.put("editTambah", "yes");
                    		context.put("onchangeXX", "");
                    		context.put("changeDATAx", "datafromview");

                	    	//id
                	    	context.put("id_permohonan", id_permohonan);
                	    	context.put("id_status", id_status);

                    	}//close onchange update tambah peguam


                    	else if ("getBandarPerayuByNegeriVNE".equals(submitoc)){

                    		idp = getParam("id_permohonan");

                    		//dropdown firma terdahulu validation
                    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
                    		context.put("saiz_ft", listFirmaTerdahulu.size());

                    		//get list firma
                    		listFirmaTR = model2.getListFirmaTerdahulu(id_pemohon);
                    		context.put("listFirmaTerdahulu",listFirmaTR);
                    		context.put("listFirmaTerdahulu_size",listFirmaTR.size());

                    		//get data Maklumat perayu
                    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
                    		model2.setDataMaklumat(id_permohonan,id_perintah);
                    		maklumatRayuan = model2.getDataMaklumat();
                    		context.put("maklumatRayuan", maklumatRayuan);
                    		context.put("maklumatRayuan_size", maklumatRayuan.size());

                    		//get data ob
                    		model1.setListOB(id_permohonansimatiINT,id_simati);
                    		listOB = model1.getListOB();

                    		//get data ob atas 18
                    		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
                    		listOBatas18 = model1.getListOBatas18();
                    		context.put("listOBatas18", listOBatas18);


                    		//get data peguam
                    		String sorPeguam,txtNamaFirma,txtNoRujukan,txtAlamatPeguam1,txtAlamatPeguam2,
                    		txtAlamatPeguam3,txtPoskodPeguam,idBandarPeguam,txtNoTelefon,txtNoFaks,
                    		txtEmel,socNegeriPeguam,id_socNamaFirma = "";

                    		//sorPeguam = getParam("sorPeguamUpdate");
                    		//get data peguam
                	    	txtNamaFirma = getParam("txtNamaFirma");
                	    	txtNoRujukan = getParam("txtNoRujukan");
                	    	txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
                	    	txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
                	    	txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
                	    	txtPoskodPeguam = getParam("txtPoskodPeguam");
                	    	idBandarPeguam = getParam("txtBandarPeguam");
                	    	txtNoTelefon = getParam("txtNoTelefon");
                	    	txtNoFaks = getParam("txtNoFaks");
                	    	txtEmel = getParam("txtEmel");
                	    	socNegeriPeguam = getParam("socNegeriPeguam");


                	    	sorPeguam = "";

                			//set data peguam
                    		context.put("namaFx", txtNamaFirma);
                    		context.put("noRx", txtNoRujukan);
                    		context.put("alamatP1x", txtAlamatPeguam1);
                    		context.put("alamatP2x", txtAlamatPeguam2);
                    		context.put("alamatP3x", txtAlamatPeguam3);
                    		context.put("poskodPx", txtPoskodPeguam);
                    		context.put("noTx", txtNoTelefon);
                    		context.put("noFx", txtNoFaks);
                    		context.put("emelOx", txtEmel);

                    		if(socNegeriPeguam!=""){
                    			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                    			if(idBandarPeguam!=""){
                    				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
                    			}else{
                    				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
                        		}
                    		}else{
                	    		context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                	    		if(idBandarPeguam!=""){
                    				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
                    			}else{
                    				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
                        		}
                    		}


                    		//get data perayu
                    		String almt1 = "";
                    		String almt2 = "";
                    		String almt3 = "";
                    		String poskod = "";
                    		String idnegeri = "";
                    		String idob = "";
                    		String cnamaob = "";
                    		String whatkp = "";

                    		idob = getParam("EsocPerayu");

                    		almt1 = getParam("EtxtAlamatPerayu1");
                    		almt2 = getParam("EtxtAlamatPerayu2");
                    		almt3 = getParam("EtxtAlamatPerayu3");
                			poskod = getParam("EtxtPoskodPerayu");
                			idnegeri = getParam("socNegeriPerayu");

                			whatkp = getParam("Ewhatkp");

                			context.put("nokpbaru", "");
                	    	context.put("nokplama", "");
                	    	context.put("nokplain", "");
                	    	context.put("cnokp1", "");
                	    	context.put("cnokp2", "");
                	    	context.put("cnokp3", "");

                    		if(whatkp.equals("baru")){
                    			String ax = getParam("EtxtNoKPBaru1");
                    	    	String bx = getParam("EtxtNoKPBaru2");
                    	    	String cx = getParam("EtxtNoKPBaru3");
                    	    	context.put("nokpPerayu1", ax);
                    	    	context.put("nokpPerayu2", bx);
                    	    	context.put("nokpPerayu3", cx);
                    	    	context.put("nokpPerayu", "xxx");
                    	    	context.put("nokpLamaPerayu", "");
                    	    	context.put("nokpLainPerayu", "");
                    		}else if(whatkp.equals("lama")){
                    			String lamax = getParam("EtxtNoKPLama");
                    			context.put("nokpPerayu", "");
                    			context.put("nokpLainPerayu", "");
                    	    	context.put("nokpLamaPerayu", lamax);
                    		}else if(whatkp.equals("lain")){
                    			String lainx = getParam("EtxtNoKPLain");
                    			context.put("nokpPerayu", "");
                    	    	context.put("nokpLamaPerayu", "");
                    	    	context.put("nokpLainPerayu", lainx);
                    		}else{
                    			context.put("nokpPerayu", "");
                    	    	context.put("nokpLamaPerayu", "");
                    	    	context.put("nokpLainPerayu", "");
                    		}

                			context.put("alamatPerayu1x", almt1);
                			context.put("alamatPerayu2x", almt2);
                			context.put("alamatPerayu3x", almt3);
                			context.put("poskodPX", poskod);


                			if(idnegeri!=""){
            					context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
            					context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
                            }else{
            					context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
            					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
                    		}


                    		String onchangePP = getParam("onchangex");

                    		if(onchangePP.equals("yes")){

                    			if(idob!=""){

                        			onchangeListOB = model2.getOnchangeListOB(idob);
                            		Hashtable xy = (Hashtable) onchangeListOB.get(0);

                            		if(onchangeListOB.size()!=0)
                            		{
                            			cnamaob = xy.get("nama_ob").toString();
                                	}//close if

                        		}//close if

                    			context.put("cidob", idob);
                    			context.put("cnamaob", cnamaob);
                    		}

                	    	//validation
                	    	context.put("onchangePP", onchangePP);
                	    	context.put("changeDATAx", "datafromview");
                	    	context.put("onchangeXX", "yes");

                    	}//close getBandarPerayuByNegeriVNE


                    	else if ("getBandarPeguamByNegeriVNE".equals(submitoc)){

                    		idp = getParam("id_permohonan");

                    		//dropdown firma terdahulu validation
                    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
                    		context.put("saiz_ft", listFirmaTerdahulu.size());

                    		//get list firma
                    		listFirmaTR = model2.getListFirmaTerdahulu(id_pemohon);
                    		context.put("listFirmaTerdahulu",listFirmaTR);
                    		context.put("listFirmaTerdahulu_size",listFirmaTR.size());

                    		//get data Maklumat perayu
                    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
                    		model2.setDataMaklumat(id_permohonan,id_perintah);
                    		maklumatRayuan = model2.getDataMaklumat();
                    		context.put("maklumatRayuan", maklumatRayuan);
                    		context.put("maklumatRayuan_size", maklumatRayuan.size());

                    		//get data ob
                    		model1.setListOB(id_permohonansimatiINT,id_simati);
                    		listOB = model1.getListOB();

                    		//get data ob atas 18
                    		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
                    		listOBatas18 = model1.getListOBatas18();
                    		context.put("listOBatas18", listOBatas18);


                    		//get data peguam
                    		String sorPeguam,txtNamaFirma,txtNoRujukan,txtAlamatPeguam1,txtAlamatPeguam2,
                    		txtAlamatPeguam3,txtPoskodPeguam,idBandarPeguam,txtNoTelefon,txtNoFaks,
                    		txtEmel,socNegeriPeguam,id_socNamaFirma = "";

                    		//sorPeguam = getParam("sorPeguamUpdate");
                    		//get data peguam
                	    	txtNamaFirma = getParam("txtNamaFirma");
                	    	txtNoRujukan = getParam("txtNoRujukan");
                	    	txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
                	    	txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
                	    	txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
                	    	txtPoskodPeguam = getParam("txtPoskodPeguam");
                	    	//idBandarPeguam = getParam("txtBandarPeguam");
                	    	txtNoTelefon = getParam("txtNoTelefon");
                	    	txtNoFaks = getParam("txtNoFaks");
                	    	txtEmel = getParam("txtEmel");
                	    	socNegeriPeguam = getParam("socNegeriPeguam");

                	    	sorPeguam = "";


                			//set data peguam
                    		context.put("namaFx", txtNamaFirma);
                    		context.put("noRx", txtNoRujukan);
                    		context.put("alamatP1x", txtAlamatPeguam1);
                    		context.put("alamatP2x", txtAlamatPeguam2);
                    		context.put("alamatP3x", txtAlamatPeguam3);
                    		context.put("poskodPx", txtPoskodPeguam);
                    		context.put("noTx", txtNoTelefon);
                    		context.put("noFx", txtNoFaks);
                    		context.put("emelOx", txtEmel);

                    		if(socNegeriPeguam!=""){
                    			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                    			context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
                        	}else{
                	    		context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriVNE()"));
                	    		context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
                        	}

                    		//get data perayu
                    		String almt1 = "";
                    		String almt2 = "";
                    		String almt3 = "";
                    		String poskod = "";
                    		String idnegeri = "";
                    		String idob = "";
                    		String cnamaob = "";
                    		String whatkp = "";
                    		String idbandar = "";

                    		idob = getParam("EsocPerayu");

                    		almt1 = getParam("EtxtAlamatPerayu1");
                    		almt2 = getParam("EtxtAlamatPerayu2");
                    		almt3 = getParam("EtxtAlamatPerayu3");
                			poskod = getParam("EtxtPoskodPerayu");
                			idnegeri = getParam("socNegeriPerayu");
                			idbandar = getParam("EtxtBandarPerayu");

                			whatkp = getParam("Ewhatkp");

                			context.put("nokpbaru", "");
                	    	context.put("nokplama", "");
                	    	context.put("nokplain", "");
                	    	context.put("cnokp1", "");
                	    	context.put("cnokp2", "");
                	    	context.put("cnokp3", "");

                    		if(whatkp.equals("baru")){
                    			String ax = getParam("EtxtNoKPBaru1");
                    	    	String bx = getParam("EtxtNoKPBaru2");
                    	    	String cx = getParam("EtxtNoKPBaru3");
                    	    	context.put("nokpPerayu1", ax);
                    	    	context.put("nokpPerayu2", bx);
                    	    	context.put("nokpPerayu3", cx);
                    	    	context.put("nokpPerayu", "xxx");
                    	    	context.put("nokpLamaPerayu", "");
                    	    	context.put("nokpLainPerayu", "");
                    		}else if(whatkp.equals("lama")){
                    			String lamax = getParam("EtxtNoKPLama");
                    			context.put("nokpPerayu", "");
                    			context.put("nokpLainPerayu", "");
                    	    	context.put("nokpLamaPerayu", lamax);
                    		}else if(whatkp.equals("lain")){
                    			String lainx = getParam("EtxtNoKPLain");
                    			context.put("nokpPerayu", "");
                    	    	context.put("nokpLamaPerayu", "");
                    	    	context.put("nokpLainPerayu", lainx);
                    		}else{
                    			context.put("nokpPerayu", "");
                    	    	context.put("nokpLamaPerayu", "");
                    	    	context.put("nokpLainPerayu", "");
                    		}

                			context.put("alamatPerayu1x", almt1);
                			context.put("alamatPerayu2x", almt2);
                			context.put("alamatPerayu3x", almt3);
                			context.put("poskodPX", poskod);



                			if(idnegeri!=""){
            					context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
            					if(idbandar!=""){
            						context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
            					}else{
            						context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
            					}
            				}else{
            					context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
            					if(idbandar!=""){
            						context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
                            	}else{
                            		context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
                                }
            				}

                    		String onchangePP = getParam("onchangex");

                    		if(onchangePP.equals("yes")){

                    			if(idob!=""){

                        			onchangeListOB = model2.getOnchangeListOB(idob);
                            		Hashtable xy = (Hashtable) onchangeListOB.get(0);

                            		if(onchangeListOB.size()!=0)
                            		{
                            			cnamaob = xy.get("nama_ob").toString();
                                	}//close if

                        		}//close if

                    			context.put("cidob", idob);
                    			context.put("cnamaob", cnamaob);
                    		}

                	    	//validation
                	    	context.put("onchangePP", onchangePP);
                	    	context.put("changeDATAx", "datafromview");
                	    	context.put("onchangeXX", "yes");

                    	}//close getBandarPeguamByNegeriVNE

                		//command xx
                    	String submitxxx = getParam("commandxxx");
                    	myLogger.info("[submit]xxx :: " +submitxxx);

                    	if("updateTambahPeguam".equals(submitxxx)){



                     		if (doPost.equals("true")) {

                     			updateTambahPeguam(session);

                     		}



                    		id_permohonan = getParam("id_permohonan");
                    		id_status = getParamAsInteger("id_status");

                    		String id_peguamXXX = getParam("id_peguam");

                       		usid=(String)session.getAttribute("_ekptg_user_id");

                    		//get info pemohon
                        	model1.setListSemak(id_permohonan,usid);
                    		dataPemohon = model1.getListSemak();
                    		headerppk_baru(session,id_permohonan,"Y","","T");

                    		//get data Maklumat perayu
                    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
                    		model2.setDataMaklumat(id_permohonan,id_perintah);
                    		maklumatRayuan = model2.getDataMaklumat();
                    		context.put("maklumatRayuan_size", maklumatRayuan.size());

                    		String idnegeriPerayu2 = "";
                    		String idbandarPerayu2 = "";

                    		if(maklumatRayuan.size()!=0){
                    			Hashtable mr2 = (Hashtable) maklumatRayuan.get(0);
                    			idnegeriPerayu2 = mr2.get("negeri_perayu").toString();
                    			idbandarPerayu2 = mr2.get("id_bandar_perayu").toString();
                    			id_perayu = mr2.get("id_perayu").toString();
                    		}

                    		//get list firma
                    		listFirma = model2.getListFirma(id_perayu);
                    		context.put("listFirma",listFirma);
                    		context.put("listFirma_size",listFirma.size());

                    		//get other peguam
                    		otherPeguam = model2.getOtherPeguam(id_peguamX);

                    		String idNegO3 = "";
                    		String idBandO3 = "";

                    		if(otherPeguam.size()!=0){
                    			Hashtable xo3 = (Hashtable) otherPeguam.get(0);
                    			idNegO3 = xo3.get("id_negeri").toString();
                    			idBandO3 = xo3.get("id_bandar").toString();
                    		}

                    		//id
                    		context.put("id_permohonan", id_permohonan);
                    		context.put("id_status", id_status);
                    		context.put("id_peguamX",id_peguamXXX);
                    		context.put("id_perayu",id_perayu);
                    		context.put("id_pemohon", id_pemohon);

                    		//data
                    		context.put("otherPeguam", otherPeguam);
                    		context.put("dataPemohon", dataPemohon);
                    		context.put("maklumatRayuan", maklumatRayuan);

                    		//validation
                	    	context.put("onchangePP", "no");
                	    	context.put("newform", "no");
                    		context.put("editform", "no");
                    		context.put("formTambah", "viewANDedit");
                    		context.put("editTambah", "no");
                    		context.put("onchangeXX", "");


                    		if(idnegeriPerayu2!=""){
                    			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeriPerayu2),null,"class=disabled disabled style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
                    			if(idbandarPerayu2!=""){
                        			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu2,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu2),"class=disabled disabled style=width:240"));
                            	}else{
                            		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu2,"EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                                }
                    		}else{
                    			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"class=disabled disabled style=width:240 onchange=getBandarPerayuByNegeriVNE()"));
                    			if(idbandarPerayu2!=""){
                					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu2),"class=disabled disabled style=width:240"));
                				}else{
                					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                    			}
                    		}


                    		if(idNegO3!=""){
                    			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idNegO3),"class=disabled disabled style=width:240"));
                    			if(idBandO3!=""){
                    				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(idNegO3,"txtBandarPeguam",Utils.parseLong(idBandO3),"class=disabled disabled style=width:240"));
                    			}else{
                    				context.put("vneBandarPeguam",HTML.SelectBandarByNegeri(idNegO3,"txtBandarPeguam",null,"class=disabled disabled style=width:240"));
                        		}
                    		}else{
                    			context.put("vneNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,"class=disabled disabled style=width:240"));
                    			if(idBandO3!=""){
                    				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandO3),"class=disabled disabled style=width:240"));
                    			}else{
                    				context.put("vneBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"class=disabled disabled style=width:240"));
                        		}
                    		}

                    	}//close updateTambahPeguam

                	}//close kemaskiniX

            	}//close semakFirma

    		}//close if maklumat size != 0

        	vm = mainscreen;

    	}//close maklumatRayuan

    	else if("doCheckSOCTXTUpdateTerkini".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

			id_permohonan = getParam("id_permohonan");
			id_status = getParamAsInteger("id_status");

			//get info pemohon
    		model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		context.put("dataPemohon", dataPemohon);
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";

    		if(dataPemohon.size()!=0){
    			Hashtable ls = (Hashtable) dataPemohon.get(0);
    			id_pemohon = ls.get("idPemohon").toString();
    		}

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		String idnegeriPerayu = "";
    		String id_perayu = "";
    		String id_rayuan = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			idnegeriPerayu = mr.get("negeri_perayu").toString();
    			id_perayu = mr.get("id_perayu").toString();
    			id_rayuan = mr.get("id_rayuan").toString();
    		}

    		//get maklumat peguam yang latest
    		model2.setLatestPeguam(id_perayu);
    		dataLatestPeguam = model2.getLatestPeguam();

    		String idnegeriPeguam = "";
    		String idbandarPeguam = "";
    		String id_peguam = "";

    		if(dataLatestPeguam.size()!=0){
    			Hashtable lat = (Hashtable) dataLatestPeguam.get(0);
    			idnegeriPeguam = lat.get("id_negeri").toString();
    			idbandarPeguam = lat.get("id_bandar").toString();
    			id_peguam = lat.get("id_peguam").toString();
    		}

    		//get data back
    		String alamat1,alamat2,alamat3,poskod,
    		idnegeri,idbandar = "";

			String sorPeguam = getParam("sorPeguamUpdate");

			alamat1 = getParam("EtxtAlamatPerayu1");
			alamat2 = getParam("EtxtAlamatPerayu2");
			alamat3 = getParam("EtxtAlamatPerayu3");
			poskod = getParam("EtxtPoskodPerayu");
			//bandar = getParam("EtxtBandarPerayu");
			idnegeri = getParam("EsocNegeriPerayu");
			idbandar = getParam("EtxtBandarPerayu");

			context.put("alamatPerayu1x", alamat1);
			context.put("alamatPerayu2x", alamat2);
			context.put("alamatPerayu3x", alamat3);
			context.put("poskod", poskod);
			//context.put("bandar", bandar);

			String idp = getParam("id_permohonan");

			//dropdown
    		context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
    		context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
     	    context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"style=width:240 onchange=getDetailFirmaX()"));


			//check balik radio
			if(sorPeguam.equals("1")){
				context.put("EshowSOC", "yes");
    			context.put("EshowTXT", "no");
	    		context.put("checkAx", "checked");
	    		context.put("checkBx", "");
	    		context.put("changeDATA", "clear");

	    	}else if(sorPeguam.equals("2")){
	    		context.put("EshowSOC", "no");
    			context.put("EshowTXT", "yes");
	    		context.put("checkAx", "");
	    		context.put("checkBx", "checked");
	    		context.put("changeDATA", "default");

	    		if(idnegeriPeguam!=""){
        			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(idnegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
        			if(idbandarPeguam!=""){
        				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"style=width:240"));
             	    }else{
             	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",null,"style=width:240"));
                  	}
	    		}else{
        			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
        			if(idbandarPeguam!=""){
        				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"style=width:240"));
             	    }else{
        				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
             	    }
	    		}
	    	}

    		if(idnegeri!=""){
    			context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
    			if(idbandar!=""){
        			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
            	}else{
            		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
                }
    		}else{
        		context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
        		if(idbandar!=""){
					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
				}else{
					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
    			}
    		}


			//id
			context.put("id_permohonan", id_permohonan);
    		context.put("id_status", id_status);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_perayu", id_perayu);
    		context.put("id_rayuan", id_rayuan);


			//form validation
    		context.put("newform", "no");
    		context.put("editform", "yes");
    		String onchangePP = getParam("onchangex");
    		context.put("onchangePP", onchangePP);
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "yes");


    		//command 2
        	String submit2 = getParam("command2");
        	myLogger.info("[submit2] :: " +submit2);

        	if ("getDetailFirmaX".equals(submit2)){

        		String id_socNamaFirma = "";
        		String txtNamaFirma = "";
    	    	String txtNoRujukan = "";
    	    	String txtAlamatPeguam1 = "";
    	    	String txtAlamatPeguam2 = "";
    	    	String txtAlamatPeguam3 = "";
    	    	String txtPoskodPeguam = "";
    	    	String txtBandarPeguam = "";
    	    	String txtNoTelefon = "";
    	    	String txtNoFaks = "";
    	    	String txtEmel = "";
    	    	String idneg = "";
    	    	String idbandarpeguam = "";

        		id_socNamaFirma = getParam("socNamaFirma");


        		if(id_socNamaFirma!=""){
        	    	detailFirma = model2.getDetailFirma(id_socNamaFirma);

        	    	if(detailFirma.size()!=0)
        	    	{
        	    		context.put("changeDATA", "databylist");

        	    		Hashtable onc = (Hashtable) detailFirma.get(0);
        	    			txtNamaFirma = onc.get("nama_firma").toString();
        	    			txtNoRujukan = onc.get("no_rujukan_firma").toString();
        	    			txtAlamatPeguam1 = onc.get("alamat1").toString();
        	    			txtAlamatPeguam2 = onc.get("alamat2").toString();
        	    			txtAlamatPeguam3 = onc.get("alamat3").toString();
        	    			txtPoskodPeguam = onc.get("poskod").toString();
        	    			txtBandarPeguam = onc.get("bandar").toString();
        	    			txtNoTelefon = onc.get("no_tel").toString();
        	    			txtNoFaks = onc.get("no_fax").toString();
        	    			txtEmel = onc.get("emel").toString();
        	    			idneg = onc.get("id_negeri").toString();
        	    			idbandarpeguam = onc.get("id_bandar").toString();

        	    	}else{
        	    		context.put("changeDATA", "clear");

        	    	}//close if(detailFirma.size()!=0)

        		}else{
        			context.put("changeDATA", "clear");

        		}//close if(id_socNamaFirma!="")

        			if(idneg!=""){
        				context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(idneg),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
        				if(idbandarpeguam!=""){
            				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idneg,"EtxtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
                 	    }else{
                 	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idneg,"EtxtBandarPeguam",null,"style=width:240"));
                      	}
        			}else{
        				context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
        				if(idbandarpeguam!=""){
            				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
                 	    }else{
            				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
                 	    }
        			}

        			idp = getParam("id_permohonan");

        			if(id_socNamaFirma!=""){
        				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(id_socNamaFirma),"style=width:240 onchange=getDetailFirmaX()"));
        			}else{
        				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"style=width:240 onchange=getDetailFirmaX()"));
        			}

        			context.put("noRujukan_", txtNoRujukan);
        			context.put("alamatPeguam1_", txtAlamatPeguam1);
        			context.put("alamatPeguam2_", txtAlamatPeguam2);
        			context.put("alamatPeguam3_", txtAlamatPeguam3);
        			context.put("poskodpeguam_", txtPoskodPeguam);
        			context.put("bandarpeguam_", txtBandarPeguam);
        			context.put("notel_", txtNoTelefon);
        			context.put("nofaks_", txtNoFaks);
        			context.put("emel_", txtEmel);


        	}//close getDetailFirmaX

	    	vm = mainscreen;

	}//close doCheckSOCTXTUpdateTerkini


    	else if("doCheckSOCTXT".equals(submit)){

    			String id_permohonan = "";
    			int id_status = 0;

    			id_permohonan = getParam("id_permohonan");
    			id_status = getParamAsInteger("id_status");

    			String sorPeguam = getParam("sorPeguam");

    			//get info pemohon
        		model1.setListSemak(id_permohonan,usid);
        		dataPemohon = model1.getListSemak();
        		headerppk_baru(session,id_permohonan,"Y","","T");

        		String id_pemohon = "";

        		if(dataPemohon.size()!=0){
        			Hashtable ls = (Hashtable) dataPemohon.get(0);
        			id_pemohon = ls.get("idPemohon").toString();
        		}


    			//id
    			context.put("id_permohonan",id_permohonan);
    			context.put("id_status",id_status);
    			context.put("id_pemohon",id_pemohon);


    			context.put("cnamafirma", "");
        		context.put("cnorujukan", "");
        		context.put("calamatpeguam1", "");
        		context.put("calamatpeguam2", "");
        		context.put("calamatpeguam3", "");
        		context.put("cposkodpeguam", "");
        		context.put("cbandarpeguam", "");
        		context.put("cnotel", "");
        		context.put("cnofaks", "");
        		context.put("cemel", "");

        		String idp = getParam("id_permohonan");

        		//dropdown
    	    	context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
    	    	context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
    	    	context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));

    			//form validation
    			context.put("newform", "yes");
    			context.put("editform", "no");
    			context.put("onchangePP", "yes");
    			context.put("formTambah", "no");
    			context.put("onchangeXX", "");

    			String cidobx = "";
        		String cnamaobx = "";

        		//get data perayu
        		String idListOBx = getParam("socPerayu");

        		String whatkp = getParam("whatkp");

        		context.put("nokpbaru", "");
    	    	context.put("nokplama", "");
    	    	context.put("nokplain", "");

    	    	context.put("cnokp1", "");
    	    	context.put("cnokp2", "");
    	    	context.put("cnokp3", "");

        		if(whatkp.equals("baru")){
        			String ax = getParam("txtNoKPBaru1");
        	    	String bx = getParam("txtNoKPBaru2");
        	    	String cx = getParam("txtNoKPBaru3");
        	    	context.put("cnokp1", ax);
        	    	context.put("cnokp2", bx);
        	    	context.put("cnokp3", cx);
        	    	context.put("nokpbaru", "xxx");
        	    	context.put("nokplama", "");
        	    	context.put("nokplain", "");
        		}else if(whatkp.equals("lama")){
        			String lamax = getParam("txtNoKPLama");
        			context.put("nokpbaru", "");
        			context.put("nokplain", "");
        	    	context.put("nokplama", lamax);
        		}else if(whatkp.equals("lain")){
        			String lainx = getParam("txtNoKPLain");
        			context.put("nokpbaru", "");
        	    	context.put("nokplama", "");
        	    	context.put("nokplain", lainx);
        		}else{
        			context.put("nokpbaru", "");
        	    	context.put("nokplama", "");
        	    	context.put("nokplain", "");
        		}


    	    	String txtAlamatPerayu1x = getParam("txtAlamatPerayu1");
    	    	String txtAlamatPerayu2x = getParam("txtAlamatPerayu2");
    	    	String txtAlamatPerayu3x = getParam("txtAlamatPerayu3");
    	    	String txtPoskodPerayux = getParam("txtPoskodPerayu");
    	    	String txtBandarPerayux = getParam("txtBandarPerayu");
    	    	String socNegeriPerayux = getParam("socNegeriPerayu");


    	    	if(idListOBx!=""){
    	    		onchangeListOB = model2.getOnchangeListOB(idListOBx);

    	    		if(onchangeListOB.size()!=0){
    	    			Hashtable xy = (Hashtable) onchangeListOB.get(0);
    	    			cidobx = xy.get("id_ob").toString();
    	    			cnamaobx = xy.get("nama_ob").toString();
    	    		}
    	    	}

    	    	//set data back
        		context.put("cidob", cidobx);
        		context.put("cnamaob", cnamaobx);
    	    	context.put("calamat1", txtAlamatPerayu1x);
    	    	context.put("calamat2", txtAlamatPerayu2x);
    	    	context.put("calamat3", txtAlamatPerayu3x);
    	    	context.put("cposkod", txtPoskodPerayux);
    	    	//context.put("cbandar", txtBandarPerayux);

    	    	if(socNegeriPerayux!=""){
    	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(socNegeriPerayux),null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
    	    		if(txtBandarPerayux!=""){
            			context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayux,"txtBandarPerayu",Utils.parseLong(txtBandarPerayux),"style=width:240"));
                	}else{
                		context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayux,"txtBandarPerayu",null,"style=width:240"));
                    }
    	    	}else{
    	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
    	    		if(txtBandarPerayux!=""){
    					context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",Utils.parseLong(txtBandarPerayux),"style=width:240"));
    				}else{
    					context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",null,"style=width:240"));
        			}
    	    	}

    	    	//validation
        		if(sorPeguam.equals("1")){
        			context.put("showSOC", "yes");
        			context.put("showTXT", "no");
    	    		context.put("checkA", "checked");
    	    		context.put("checkB", "");
    	    	}else if(sorPeguam.equals("2")){
    	    		context.put("showSOC", "no");
        			context.put("showTXT", "yes");
    	    		context.put("checkA", "");
    	    		context.put("checkB", "checked");
    	    	}

    	    	vm = mainscreen;

    	}//close onchange sor

    	else if("getDetailFirma".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

			String socNamaFirma = getParam("socNamaFirma");
			String sorPeguam = getParam("sorPeguam");

			//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    		}

			//data
    		context.put("dataPemohon", dataPemohon);

	    	if(sorPeguam.equals("1")){
	    		context.put("checkA", "checked");
	    		context.put("checkB", "");
	    	}else if(sorPeguam.equals("2")){
	    		context.put("checkA", "");
	    		context.put("checkB", "checked");
	    	}

    		String cidob = "";
    		String cnamaob = "";

    		//get data perayu
    		String idListOB = getParam("socPerayu");
	    	String a = getParam("txtNoKPBaru1");
	    	String b = getParam("txtNoKPBaru2");
	    	String c = getParam("txtNoKPBaru3");
	    	String txtAlamatPerayu1 = getParam("txtAlamatPerayu1");
	    	String txtAlamatPerayu2 = getParam("txtAlamatPerayu2");
	    	String txtAlamatPerayu3 = getParam("txtAlamatPerayu3");
	    	String txtPoskodPerayu = getParam("txtPoskodPerayu");
	    	String txtBandarPerayu = getParam("txtBandarPerayu");
	    	String socNegeriPerayu = getParam("socNegeriPerayu");

	    	if(idListOB!=""){
	    		onchangeListOB = model2.getOnchangeListOB(idListOB);

	    		if(onchangeListOB.size()!=0){
	    			Hashtable xy = (Hashtable) onchangeListOB.get(0);
	    			cidob = xy.get("id_ob").toString();
	    			cnamaob = xy.get("nama_ob").toString();
	    		}
	    	}

	    	//set data back
    		context.put("cidob", cidob);
    		context.put("cnamaob", cnamaob);
	    	context.put("cnokp1", a);
	    	context.put("cnokp2", b);
	    	context.put("cnokp3", c);
	    	context.put("calamat1", txtAlamatPerayu1);
	    	context.put("calamat2", txtAlamatPerayu2);
	    	context.put("calamat3", txtAlamatPerayu3);
	    	context.put("cposkod", txtPoskodPerayu);
	    	//context.put("cbandar", txtBandarPerayu);

	    	if(socNegeriPerayu!=""){
	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(socNegeriPerayu),null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
	    		if(txtBandarPerayu!=""){
        			context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayu,"txtBandarPerayu",Utils.parseLong(txtBandarPerayu),"style=width:240"));
            	}else{
            		context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayu,"txtBandarPerayu",null,"style=width:240"));
                }
	    	}else{
	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
	    		if(txtBandarPerayu!=""){
					context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",Utils.parseLong(txtBandarPerayu),"style=width:240"));
				}else{
					context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",null,"style=width:240"));
    			}
	    	}

	    	String txtNamaFirma = "";
	    	String txtNoRujukan = "";
	    	String txtAlamatPeguam1 = "";
	    	String txtAlamatPeguam2 = "";
	    	String txtAlamatPeguam3 = "";
	    	String txtPoskodPeguam = "";
	    	//String txtBandarPeguam = "";
	    	String txtNoTelefon = "";
	    	String txtNoFaks = "";
	    	String txtEmel = "";
	    	String idneg = "";
	    	String idbandarpeguam = "";

	    	if(socNamaFirma!=""){
	    	detailFirma = model2.getDetailFirma(socNamaFirma);

	    		if(detailFirma.size()!=0)
	    		{
	    			Hashtable onc = (Hashtable) detailFirma.get(0);
	    			txtNamaFirma = onc.get("nama_firma").toString();
	    			txtNoRujukan = onc.get("no_rujukan_firma").toString();
	    			txtAlamatPeguam1 = onc.get("alamat1").toString();
	    			txtAlamatPeguam2 = onc.get("alamat2").toString();
	    			txtAlamatPeguam3 = onc.get("alamat3").toString();
	    			txtPoskodPeguam = onc.get("poskod").toString();
	    			//txtBandarPeguam = onc.get("bandar").toString();
	    			txtNoTelefon = onc.get("no_tel").toString();
	    			txtNoFaks = onc.get("no_fax").toString();
	    			txtEmel = onc.get("emel").toString();
	    			idneg = onc.get("id_negeri").toString();
	    			idbandarpeguam = onc.get("id_bandar").toString();

	    		}
	    	}

	    	//set data peguam
	    	context.put("idFirma", socNamaFirma);
    		context.put("cnamafirma", txtNamaFirma);
    		context.put("cnorujukan", txtNoRujukan);
    		context.put("calamatpeguam1", txtAlamatPeguam1);
    		context.put("calamatpeguam2", txtAlamatPeguam2);
    		context.put("calamatpeguam3", txtAlamatPeguam3);
    		context.put("cposkodpeguam", txtPoskodPeguam);
    		//context.put("cbandarpeguam", txtBandarPeguam);
    		context.put("cnotel", txtNoTelefon);
    		context.put("cnofaks", txtNoFaks);
    		context.put("cemel", txtEmel);

    		if(idneg!=""){
     			context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idneg),null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
     			if(idbandarpeguam!=""){
     				context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(idneg,"txtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
     	    	}else{
     				context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(idneg,"txtBandarPeguam",null,"style=width:240"));
     	    	}
    		}else{
 	    		context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
 	    		if(idbandarpeguam!=""){
 	    			context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
 	       		}else{
     				context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
     	    	}
    		}

    		String idp = getParam("id_permohonan");

    		if(socNamaFirma!=""){
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
    		}else{
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
        	}

	    	//validation
	    	context.put("onchangePP", "yes");
	    	context.put("newform", "yes");
    		context.put("editform", "no");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "");

    		vm = mainscreen;

    	}

    	else if("getBandarPerayuByNegeriUpdate".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_simati = "";
    		String idFail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_simati = x.get("idSimati").toString();
    			idFail = x.get("idFail").toString();
    		}



    		//get list firma
    		listFirmaTR = model2.getListFirmaTerdahulu(id_pemohon);
    		context.put("listFirmaTerdahulu",listFirmaTR);
    		context.put("listFirmaTerdahulu_size",listFirmaTR.size());

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		String id_perayu = "";
    		String id_rayuan = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_perayu = mr.get("id_perayu").toString();
    			id_rayuan = mr.get("id_rayuan").toString();
    		}

    		//get data ob
    		model1.setListOB(id_permohonansimatiINT,id_simati);
    		listOB = model1.getListOB();

    		//get data ob
    		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
    		listOBatas18 = model1.getListOBatas18();
    		context.put("listOBatas18", listOBatas18);


    		//get data peguam
    		String sorPeguam,txtNamaFirma,txtNoRujukan,txtAlamatPeguam1,txtAlamatPeguam2,
    		txtAlamatPeguam3,txtPoskodPeguam,idBandarPeguam,txtNoTelefon,txtNoFaks,
    		txtEmel,socNegeriPeguam,id_socNamaFirma = "";

    		sorPeguam = getParam("sorPeguamUpdate");
    		txtNamaFirma = getParam("EtxtNamaFirma");
	    	txtNoRujukan = getParam("EtxtNoRujukan");
	    	txtAlamatPeguam1 = getParam("EtxtAlamatPeguam1");
	    	txtAlamatPeguam2 = getParam("EtxtAlamatPeguam2");
	    	txtAlamatPeguam3 = getParam("EtxtAlamatPeguam3");
	    	txtPoskodPeguam = getParam("EtxtPoskodPeguam");
	    	idBandarPeguam = getParam("EtxtBandarPeguam");
	    	txtNoTelefon = getParam("EtxtNoTelefon");
	    	txtNoFaks = getParam("EtxtNoFaks");
	    	txtEmel = getParam("EtxtEmel");
	    	socNegeriPeguam = getParam("EsocNegeriPeguam");
	    	id_socNamaFirma = getParam("socNamaFirma");


	    	//check balik radio
			if(sorPeguam.equals("1")){

				context.put("EshowSOC", "yes");
    			context.put("EshowTXT", "no");
	    		context.put("checkAx", "checked");
	    		context.put("checkBx", "");

	    		id_socNamaFirma = getParam("socNamaFirma");

	    	 	if(id_socNamaFirma!=""){

        	    	context.put("changeDATA", "databylist");

        		}else{
        			context.put("changeDATA", "clear");
        		}//close if(id_socNamaFirma!="")


	    	}else if(sorPeguam.equals("2")){
	    		context.put("EshowSOC", "no");
    			context.put("EshowTXT", "yes");
	    		context.put("checkAx", "");
	    		context.put("checkBx", "checked");
	    		context.put("changeDATA", "databylist");

	    	}else{
	    		context.put("changeDATA", "databylist");
	    	}

			String idp = getParam("id_permohonan");

			//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());

			//set data peguam = dropdown firma
			if(id_socNamaFirma!=""){
				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(id_socNamaFirma),"style=width:240 onchange=getDetailFirmaX()"));
			}else{
				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"style=width:240 onchange=getDetailFirmaX()"));
			}

			//set data peguam
    		context.put("namaFirma_", txtNamaFirma);
    		context.put("noRujukan_", txtNoRujukan);
    		context.put("alamatPeguam1_", txtAlamatPeguam1);
    		context.put("alamatPeguam2_", txtAlamatPeguam2);
    		context.put("alamatPeguam3_", txtAlamatPeguam3);
    		context.put("poskodpeguam_", txtPoskodPeguam);
    		//context.put("bandarPeguam", txtBandarPeguam);
    		context.put("notel_", txtNoTelefon);
    		context.put("nofaks_", txtNoFaks);
    		context.put("emel_", txtEmel);

    		if(socNegeriPeguam!=""){
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
    			if(idBandarPeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"EtxtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
         	    }else{
         	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"EtxtBandarPeguam",null,"style=width:240"));
              	}
    		}else{
	    		context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
	    		if(idBandarPeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
         	    }else{
    				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
         	    }
    		}

    		//get data perayu by idob
    		String almt1 = "";
    		String almt2 = "";
    		String almt3 = "";
    		String poskod = "";
    		//String bandar = "";
    		String idnegeri = "";

    		almt1 = getParam("EtxtAlamatPerayu1");
    		almt2 = getParam("EtxtAlamatPerayu2");
    		almt3 = getParam("EtxtAlamatPerayu3");
			poskod = getParam("EtxtPoskodPerayu");
			idnegeri = getParam("EsocNegeriPerayu");
			//bandar = getParam("EtxtBandarPerayu");

			context.put("alamatPerayu1x", almt1);
			context.put("alamatPerayu2x", almt2);
			context.put("alamatPerayu3x", almt3);
			context.put("poskodPX", poskod);

    			if(idnegeri!=""){
    				context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
    				context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
                }else{
    				context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
    				context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
        		}


    		String onchangePP = getParam("onchangex");


	    	//validation
	    	context.put("onchangePP", onchangePP);
	    	context.put("newform", "no");
    		context.put("editform", "yes");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "yes");


	    	//id
	    	context.put("id_permohonan", id_permohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_perayu", id_perayu);
	    	context.put("id_rayuan", id_rayuan);
	    	context.put("id_pemohon", id_pemohon);
	    	context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		vm = mainscreen;

    	}//close getBandarPerayuByNegeriUpdate

    	else if("getBandarPeguamByNegeriUpdate".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_simati = "";
    		String idFail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_simati = x.get("idSimati").toString();
    			idFail = x.get("idFail").toString();
    		}



    		//get list firma
    		listFirmaTR = model2.getListFirmaTerdahulu(id_pemohon);
    		context.put("listFirmaTerdahulu",listFirmaTR);
    		context.put("listFirmaTerdahulu_size",listFirmaTR.size());

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		String id_perayu = "";
    		String id_rayuan = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_perayu = mr.get("id_perayu").toString();
    			id_rayuan = mr.get("id_rayuan").toString();
    		}

    		//get data ob
    		model1.setListOB(id_permohonansimatiINT,id_simati);
    		listOB = model1.getListOB();

    		//get data ob
    		model1.setListOBatas18(id_permohonansimatiINT,id_simati,"17");
    		listOBatas18 = model1.getListOBatas18();
    		context.put("listOBatas18", listOBatas18);


    		//get data peguam
    		String sorPeguam,txtNamaFirma,txtNoRujukan,txtAlamatPeguam1,txtAlamatPeguam2,
    		txtAlamatPeguam3,txtPoskodPeguam,idBandarPeguam,txtNoTelefon,txtNoFaks,
    		txtEmel,socNegeriPeguam,id_socNamaFirma = "";

    		sorPeguam = getParam("sorPeguamUpdate");
    		txtNamaFirma = getParam("EtxtNamaFirma");
	    	txtNoRujukan = getParam("EtxtNoRujukan");
	    	txtAlamatPeguam1 = getParam("EtxtAlamatPeguam1");
	    	txtAlamatPeguam2 = getParam("EtxtAlamatPeguam2");
	    	txtAlamatPeguam3 = getParam("EtxtAlamatPeguam3");
	    	txtPoskodPeguam = getParam("EtxtPoskodPeguam");
	    	idBandarPeguam = getParam("EtxtBandarPeguam");
	    	txtNoTelefon = getParam("EtxtNoTelefon");
	    	txtNoFaks = getParam("EtxtNoFaks");
	    	txtEmel = getParam("EtxtEmel");
	    	socNegeriPeguam = getParam("EsocNegeriPeguam");
	    	id_socNamaFirma = getParam("socNamaFirma");


	    	//check balik radio
			if(sorPeguam.equals("1")){
				context.put("EshowSOC", "yes");
    			context.put("EshowTXT", "no");
	    		context.put("checkAx", "checked");
	    		context.put("checkBx", "");

	    		id_socNamaFirma = getParam("socNamaFirma");

	    	 	if(id_socNamaFirma!=""){

        	    	context.put("changeDATA", "databylist");

        		}else{
        			context.put("changeDATA", "clear");
        		}//close if(id_socNamaFirma!="")


	    	}else if(sorPeguam.equals("2")){
	    		context.put("EshowSOC", "no");
    			context.put("EshowTXT", "yes");
	    		context.put("checkAx", "");
	    		context.put("checkBx", "checked");
	    		context.put("changeDATA", "databylist");

	    	}else{
	    		context.put("changeDATA", "databylist");
	    	}

			String idp = getParam("id_permohonan");

			//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());

			//set data peguam = dropdown firma
			if(id_socNamaFirma!=""){
				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(id_socNamaFirma),"style=width:240 onchange=getDetailFirmaX()"));
			}else{
				context.put("selectFirmaTerdahuluX",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"style=width:240 onchange=getDetailFirmaX()"));
			}

			//set data peguam
			context.put("namaFirma_", txtNamaFirma);
    		context.put("noRujukan_", txtNoRujukan);
			context.put("alamatPeguam1_", txtAlamatPeguam1);
			context.put("alamatPeguam2_", txtAlamatPeguam2);
			context.put("alamatPeguam3_", txtAlamatPeguam3);
			context.put("poskodpeguam_", txtPoskodPeguam);
			//context.put("bandarpeguam_", txtBandarPeguam);
			context.put("notel_", txtNoTelefon);
			context.put("nofaks_", txtNoFaks);
			context.put("emel_", txtEmel);


    		if(socNegeriPeguam!=""){
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
    			context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"EtxtBandarPeguam",null,"style=width:240"));
    		}else{
	    		context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
	    		context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
         	}

    		//get data perayu by idob
    		String almt1 = "";
    		String almt2 = "";
    		String almt3 = "";
    		String poskod = "";
    		//String bandar = "";
    		String idnegeri = "";
    		String idbandar = "";

    		almt1 = getParam("EtxtAlamatPerayu1");
    		almt2 = getParam("EtxtAlamatPerayu2");
    		almt3 = getParam("EtxtAlamatPerayu3");
			poskod = getParam("EtxtPoskodPerayu");
			idnegeri = getParam("EsocNegeriPerayu");
			idbandar = getParam("EtxtBandarPerayu");

			context.put("alamatPerayu1x", almt1);
			context.put("alamatPerayu2x", almt2);
			context.put("alamatPerayu3x", almt3);
			context.put("poskodPX", poskod);

    			if(idnegeri!=""){
    				context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",Utils.parseLong(idnegeri),null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
    				if(idbandar!=""){
            			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
                	}else{
                		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeri,"EtxtBandarPerayu",null,"style=width:240"));
                    }
    			}else{
    				context.put("viewNegeriPerayu",HTML.SelectNegeri("EsocNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeriUpdate()"));
    				if(idbandar!=""){
    					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandar),"style=width:240"));
    				}else{
    					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"style=width:240"));
        			}
    			}


    		String onchangePP = getParam("onchangex");


	    	//validation
	    	context.put("onchangePP", onchangePP);
	    	context.put("newform", "no");
    		context.put("editform", "yes");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "yes");

	    	//id
	    	context.put("id_permohonan", id_permohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_perayu", id_perayu);
	    	context.put("id_rayuan", id_rayuan);
	    	context.put("id_pemohon", id_pemohon);
	    	context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		vm = mainscreen;

    	}//close getBandarPeguamByNegeriUpdate

    	else if("getBandarPerayuByNegeri".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");
			String socNamaFirma = getParam("socNamaFirma");
			String sorPeguam = getParam("sorPeguam");

			//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String idFail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			idFail = x.get("idFail").toString();
    		}

			//data
    		context.put("dataPemohon", dataPemohon);


    		String cidob = "";
    		String cnamaob = "";

    		//get data perayu
    		String idListOB = getParam("socPerayu");
	    	String a = getParam("txtNoKPBaru1");
	    	String b = getParam("txtNoKPBaru2");
	    	String c = getParam("txtNoKPBaru3");
	    	String txtAlamatPerayu1 = getParam("txtAlamatPerayu1");
	    	String txtAlamatPerayu2 = getParam("txtAlamatPerayu2");
	    	String txtAlamatPerayu3 = getParam("txtAlamatPerayu3");
	    	String txtPoskodPerayu = getParam("txtPoskodPerayu");
	    	//String txtBandarPerayu = getParam("txtBandarPerayu");
	    	String socNegeriPerayu = getParam("socNegeriPerayu");

	    	if(idListOB!=""){
	    		onchangeListOB = model2.getOnchangeListOB(idListOB);

	    		if(onchangeListOB.size()!=0){
	    			Hashtable xy = (Hashtable) onchangeListOB.get(0);
	    			cidob = xy.get("id_ob").toString();
	    			cnamaob = xy.get("nama_ob").toString();
	    		}
	    	}

	    	//set data back
    		context.put("cidob", cidob);
    		context.put("cnamaob", cnamaob);
	    	context.put("cnokp1", a);
	    	context.put("cnokp2", b);
	    	context.put("cnokp3", c);
	    	context.put("calamat1", txtAlamatPerayu1);
	    	context.put("calamat2", txtAlamatPerayu2);
	    	context.put("calamat3", txtAlamatPerayu3);
	    	context.put("cposkod", txtPoskodPerayu);
	    	//context.put("cbandar", txtBandarPerayu);

	    	if(socNegeriPerayu!=""){
	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(socNegeriPerayu),null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
	    		context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayu,"txtBandarPerayu",null,"style=width:240"));
            }else{
	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
	    		context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",null,"style=width:240"));
    		}

	    	//get data peguam
	    	String txtNamaFirma = getParam("txtNamaFirma");
	    	String txtNoRujukan = getParam("txtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
	    	String idBandarPeguam = getParam("txtBandarPeguam");
	    	String txtNoTelefon = getParam("txtNoTelefon");
	    	String txtNoFaks = getParam("txtNoFaks");
	    	String txtEmel = getParam("txtEmel");
	    	String socNegeriPeguam = getParam("socNegeriPeguam");



    		//set data peguam
    		context.put("cnamafirma", txtNamaFirma);
    		context.put("cnorujukan", txtNoRujukan);
    		context.put("calamatpeguam1", txtAlamatPeguam1);
    		context.put("calamatpeguam2", txtAlamatPeguam2);
    		context.put("calamatpeguam3", txtAlamatPeguam3);
    		context.put("cposkodpeguam", txtPoskodPeguam);
    		//context.put("cbandarpeguam", txtBandarPeguam);
    		context.put("cnotel", txtNoTelefon);
    		context.put("cnofaks", txtNoFaks);
    		context.put("cemel", txtEmel);

    		if(socNegeriPeguam!=""){
    			context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
    			if(idBandarPeguam!=""){
    				context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
    		    }else{
    		    	context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
    	    	}
    		}else{
	    		context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
	    		if(idBandarPeguam!=""){
	    			context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idBandarPeguam),"style=width:240"));
	    	    }else{
	    			context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
	    	    }
    		}

    		if(sorPeguam.equals("1")){
    			context.put("showSOC", "yes");
    			context.put("showTXT", "no");
	    		context.put("checkA", "checked");
	    		context.put("checkB", "");
	    	}else if(sorPeguam.equals("2")){
	    		context.put("showSOC", "no");
    			context.put("showTXT", "yes");
	    		context.put("checkA", "");
	    		context.put("checkB", "checked");
	    	}

    		String idp = getParam("id_permohonan");

    		//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());

	    	//context.put("idFirma", socNamaFirma);
	    	if(socNamaFirma!=""){
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
    		}else{
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
        	}

	    	//validation
	    	context.put("onchangePP", "yes");
	    	context.put("newform", "yes");
    		context.put("editform", "no");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "");

    		//id
	    	context.put("id_permohonan", id_permohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_pemohon", id_pemohon);
	    	context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);


    		vm = mainscreen;

    	}//close get bandar perayu by idnegeri

    	else if("getBandarPeguamByNegeri".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

			String socNamaFirma = getParam("socNamaFirma");
			String sorPeguam = getParam("sorPeguam");

			//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String idFail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			idFail = x.get("idFail").toString();
    		}

			//data
    		context.put("dataPemohon", dataPemohon);


    		String cidob = "";
    		String cnamaob = "";

    		//get data perayu
    		String idListOB = getParam("socPerayu");
	    	String a = getParam("txtNoKPBaru1");
	    	String b = getParam("txtNoKPBaru2");
	    	String c = getParam("txtNoKPBaru3");
	    	String txtAlamatPerayu1 = getParam("txtAlamatPerayu1");
	    	String txtAlamatPerayu2 = getParam("txtAlamatPerayu2");
	    	String txtAlamatPerayu3 = getParam("txtAlamatPerayu3");
	    	String txtPoskodPerayu = getParam("txtPoskodPerayu");
	    	String idBandarPerayu = getParam("txtBandarPerayu");
	    	String socNegeriPerayu = getParam("socNegeriPerayu");

	    	if(idListOB!=""){
	    		onchangeListOB = model2.getOnchangeListOB(idListOB);

	    		if(onchangeListOB.size()!=0){
	    			Hashtable xy = (Hashtable) onchangeListOB.get(0);
	    			cidob = xy.get("id_ob").toString();
	    			cnamaob = xy.get("nama_ob").toString();
	    		}
	    	}

	    	//set data back
    		context.put("cidob", cidob);
    		context.put("cnamaob", cnamaob);
	    	context.put("cnokp1", a);
	    	context.put("cnokp2", b);
	    	context.put("cnokp3", c);
	    	context.put("calamat1", txtAlamatPerayu1);
	    	context.put("calamat2", txtAlamatPerayu2);
	    	context.put("calamat3", txtAlamatPerayu3);
	    	context.put("cposkod", txtPoskodPerayu);

	    	if(socNegeriPerayu!=""){
	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(socNegeriPerayu),null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
	    		if(idBandarPerayu!=""){
	    			context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayu,"txtBandarPerayu",Utils.parseLong(idBandarPerayu),"style=width:240"));
	    		}else{
	    			context.put("selectBandarPerayu",HTML.SelectBandarByNegeri(socNegeriPerayu,"txtBandarPerayu",null,"style=width:240"));
	    		}
	    	}else{
	    		context.put("selectNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,null,"style=width:240 onchange=getBandarPerayuByNegeri()"));
	    		if(idBandarPerayu!=""){
	    			context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",Utils.parseLong(idBandarPerayu),"style=width:240"));
		    	}else{
	    			context.put("selectBandarPerayu",HTML.SelectBandar("txtBandarPerayu",null,"style=width:240"));
	    		}
	    	}

	    	//get data peguam
	    	String txtNamaFirma = getParam("txtNamaFirma");
	    	String txtNoRujukan = getParam("txtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
	    	//String idBandarPeguam = getParam("txtBandarPeguam");
	    	String txtNoTelefon = getParam("txtNoTelefon");
	    	String txtNoFaks = getParam("txtNoFaks");
	    	String txtEmel = getParam("txtEmel");
	    	String socNegeriPeguam = getParam("socNegeriPeguam");



    		//set data peguam
    		context.put("cnamafirma", txtNamaFirma);
    		context.put("cnorujukan", txtNoRujukan);
    		context.put("calamatpeguam1", txtAlamatPeguam1);
    		context.put("calamatpeguam2", txtAlamatPeguam2);
    		context.put("calamatpeguam3", txtAlamatPeguam3);
    		context.put("cposkodpeguam", txtPoskodPeguam);
    		//context.put("cbandarpeguam", txtBandarPeguam);
    		context.put("cnotel", txtNoTelefon);
    		context.put("cnofaks", txtNoFaks);
    		context.put("cemel", txtEmel);

    		if(socNegeriPeguam!=""){
    			context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(socNegeriPeguam),null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
    			context.put("selectBandarPeguam",HTML.SelectBandarByNegeri(socNegeriPeguam,"txtBandarPeguam",null,"style=width:240"));
	    	}else{
	    		context.put("selectNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeri()"));
	    		context.put("selectBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
	    	}

    		if(sorPeguam.equals("1")){
    			context.put("showSOC", "yes");
    			context.put("showTXT", "no");
	    		context.put("checkA", "checked");
	    		context.put("checkB", "");
	    	}else if(sorPeguam.equals("2")){
	    		context.put("showSOC", "no");
    			context.put("showTXT", "yes");
	    		context.put("checkA", "");
	    		context.put("checkB", "checked");
	    	}

    		String idp = getParam("id_permohonan");

    		//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());

	    	if(socNamaFirma!=""){
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
    		}else{
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirma()"));
        	}

	    	//validation
	    	context.put("onchangePP", "yes");
	    	context.put("newform", "yes");
    		context.put("editform", "no");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "");

    		//id
	    	context.put("id_permohonan", id_permohonan);
	    	context.put("id_status", id_status);
	    	context.put("id_pemohon", id_pemohon);
	    	context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);


    		vm = mainscreen;

    	}//close get bandar peguam by idnegeri


    	else if("getDetailFirmaUPDATE".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

			String socNamaFirma = getParam("socNamaFirma");
			String sorPeguam = getParam("sorPeguam");

			//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    		}

			//data
    		context.put("dataPemohon", dataPemohon);

	    	if(sorPeguam.equals("1")){
	    		context.put("checkA", "checked");
	    		context.put("checkB", "");
	    	}else if(sorPeguam.equals("2")){
	    		context.put("checkA", "");
	    		context.put("checkB", "checked");
	    	}

	    	String txtNamaFirma = "";
	    	String txtNoRujukan = "";
	    	String txtAlamatPeguam1 = "";
	    	String txtAlamatPeguam2 = "";
	    	String txtAlamatPeguam3 = "";
	    	String txtPoskodPeguam = "";
	    	//String txtBandarPeguam = "";
	    	String txtNoTelefon = "";
	    	String txtNoFaks = "";
	    	String txtEmel = "";
	    	String idneg = "";
	    	String idbandarpeguam = "";

	    	if(socNamaFirma!=""){
	    	detailFirma = model2.getDetailFirma(socNamaFirma);

	    		if(detailFirma.size()!=0)
	    		{
	    			Hashtable onc = (Hashtable) detailFirma.get(0);
	    			txtNamaFirma = onc.get("nama_firma").toString();
	    			txtNoRujukan = onc.get("no_rujukan_firma").toString();
	    			txtAlamatPeguam1 = onc.get("alamat1").toString();
	    			txtAlamatPeguam2 = onc.get("alamat2").toString();
	    			txtAlamatPeguam3 = onc.get("alamat3").toString();
	    			txtPoskodPeguam = onc.get("poskod").toString();
	    			//txtBandarPeguam = onc.get("bandar").toString();
	    			txtNoTelefon = onc.get("no_tel").toString();
	    			txtNoFaks = onc.get("no_fax").toString();
	    			txtEmel = onc.get("emel").toString();
	    			idneg = onc.get("id_negeri").toString();
	    			idbandarpeguam = onc.get("id_bandar").toString();
	    		}
	    	}


	    	//set data peguam
	    	context.put("idFirma", socNamaFirma);
    		context.put("namaFirma", txtNamaFirma);
    		context.put("noRujukan", txtNoRujukan);
    		context.put("alamatPeguam1", txtAlamatPeguam1);
    		context.put("alamatPeguam2", txtAlamatPeguam2);
    		context.put("alamatPeguam3", txtAlamatPeguam3);
    		context.put("poskodPeguam", txtPoskodPeguam);
    		//context.put("bandarPeguam", txtBandarPeguam);
    		context.put("noTel", txtNoTelefon);
    		context.put("noFax", txtNoFaks);
    		context.put("emel", txtEmel);

    		if(idneg!=""){
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",Utils.parseLong(idneg),null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
    			if(idbandarpeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idneg,"EtxtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
         	    }else{
         	    	context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idneg,"EtxtBandarPeguam",null,"style=width:240"));
         	    }
    		}else{
         		context.put("viewNegeriPeguam",HTML.SelectNegeri("EsocNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriUpdate()"));
         		if(idbandarpeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
         	    }else{
         	    	context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"style=width:240"));
         	    }
    		}

    		String idp = getParam("id_permohonan");

    		if(socNamaFirma!=""){
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirmaUPDATE()"));
    		}else{
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirmaUPDATE()"));
        	}

    		//form validation
    		context.put("newform", "no");
    		context.put("editform", "yes");
    		context.put("onchangePP", "no");
    		context.put("formTambah", "no");
    		context.put("onchangeUPDATE", "yes");
    		context.put("onchangeXX", "");

    		vm = mainscreen;

    	}//close getdetailfirmaupdate

    	else if("simpanTambahPeguam".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);


    		if (doPost.equals("true")) {

    			simpanTambahanPeguam(session);

    		}

    		String id_permohonan = "";
    		int id_status = 0;

            id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		String idp = getParam("id_permohonan");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String idFail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			idFail = x.get("idFail").toString();
    		}

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		String idnegeriPerayu = "";
    		String idbandarPerayu = "";
    		String id_rayuan = "";
    		String id_perayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			idnegeriPerayu = mr.get("negeri_perayu").toString();
    			idbandarPerayu = mr.get("id_bandar_perayu").toString();
    			id_rayuan = mr.get("id_rayuan").toString();
        		id_perayu = mr.get("id_perayu").toString();
    		}

    		//get maklumat peguam yang latest
    		model2.setLatestPeguam(id_perayu);
    		dataLatestPeguam = model2.getLatestPeguam();

    		String idnegeriPeguam = "";
			String idbandarPeguam = "";
			String id_peguam = "";

    		if(dataLatestPeguam.size()!=0){
    			Hashtable lat = (Hashtable) dataLatestPeguam.get(0);
    			idnegeriPeguam = lat.get("id_negeri").toString();
    			id_peguam = lat.get("id_peguam").toString();
    			idbandarPeguam = lat.get("id_bandar").toString();
    		}

    		//get list firma
    		listFirma = model2.getListFirma(id_perayu);
    		context.put("listFirma",listFirma);
    		context.put("listFirma_size",listFirma.size());

    		//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());


    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_status", id_status);
    		context.put("id_rayuan", id_rayuan);
    		context.put("id_perayu", id_perayu);
    		context.put("id_peguam", id_peguam);
    		context.put("id_fail", idFail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("maklumatRayuan", maklumatRayuan);

    		//form validation
    		context.put("newform", "no");
    		context.put("editform", "no");
    		context.put("onchangePP", "no");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "");

    		//dropdown
    		if(idnegeriPerayu!=""){
    			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeriPerayu),"class=disabled disabled style=width:240"));
    			if(idbandarPerayu!=""){
        			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"class=disabled disabled style=width:240"));
            	}else{
            		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                }
    		}else{
    			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,"class=disabled disabled style=width:240"));
    			if(idbandarPerayu!=""){
        			context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"class=disabled disabled style=width:240"));
            	}else{
            		context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                }
    		}

    		if(idnegeriPeguam!=""){
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idnegeriPeguam),"class=disabled disabled style=width:240"));
    			if(idbandarPeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"txtBandarPeguam",Utils.parseLong(idbandarPeguam),"class=disabled disabled style=width:240"));
        		}else{
        			context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"txtBandarPeguam",null,"class=disabled disabled style=width:240"));
            	}
    		}else{
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,"class=disabled disabled style=width:240"));
    			if(idbandarPeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idbandarPeguam),"class=disabled disabled style=width:240"));
    			}else{
    				context.put("viewBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"class=disabled disabled style=width:240"));
        		}
    		}

    		vm = mainscreen;

    	}//close simpanTambahPeguam

    	else if("simpanMaklumatPP".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);


            if (doPost.equals("true")) {

    			simpanMaklumatPP(session,id_perintah);

    		}

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		String idp = getParam("id_permohonan");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    		}

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		String idnegeriPerayu = "";
			String idbandarPerayu = "";
			String id_rayuan = "";
			String id_perayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			idnegeriPerayu = mr.get("negeri_perayu").toString();
    			idbandarPerayu = mr.get("id_bandar_perayu").toString();
    			id_rayuan = mr.get("id_rayuan").toString();
    			id_perayu = mr.get("id_perayu").toString();
    		}

    		//get maklumat peguam yang latest
    		model2.setLatestPeguam(id_perayu);
    		dataLatestPeguam = model2.getLatestPeguam();

    		String idnegeriPeguam = "";
			String idbandarPeguam = "";
			String id_peguam = "";

    		if(dataLatestPeguam.size()!=0){
    			Hashtable lat = (Hashtable) dataLatestPeguam.get(0);
    			idnegeriPeguam = lat.get("id_negeri").toString();
    			idbandarPeguam = lat.get("id_bandar").toString();
    			id_peguam = lat.get("id_peguam").toString();
    		}

    		//dropdown firma terdahulu validation
    		listFirmaTerdahulu = model2.getDropdownFirmaTerdahulu(id_pemohon,idp);
    		context.put("saiz_ft", listFirmaTerdahulu.size());


    		//get list firma
    		listFirma = model2.getListFirma(id_perayu);
    		context.put("listFirma",listFirma);
    		context.put("listFirma_size",listFirma.size());

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_status", id_status);
    		context.put("id_rayuan", id_rayuan);
    		context.put("id_perayu", id_perayu);
    		context.put("id_peguam", id_peguam);

    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("maklumatRayuan", maklumatRayuan);
    		context.put("dataLatestPeguam", dataLatestPeguam);

    		//form validation
    		context.put("newform", "no");
    		context.put("editform", "no");
    		context.put("onchangePP", "no");
    		context.put("formTambah", "no");
    		context.put("onchangeXX", "");

    		//dropdown
    		if(idnegeriPerayu!=""){
    			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",Utils.parseLong(idnegeriPerayu),"class=disabled disabled style=width:240"));
    			if(idbandarPerayu!=""){
        			context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"class=disabled disabled style=width:240"));
            	}else{
            		context.put("viewBandarPerayu",HTML.SelectBandarByNegeri(idnegeriPerayu,"EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
                }
    		}else{
    			context.put("viewNegeriPerayu",HTML.SelectNegeri("socNegeriPerayu",null,"class=disabled disabled style=width:240"));
    			if(idbandarPerayu!=""){
					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",Utils.parseLong(idbandarPerayu),"class=disabled disabled style=width:240"));
				}else{
					context.put("viewBandarPerayu",HTML.SelectBandar("EtxtBandarPerayu",null,"class=disabled disabled style=width:240"));
    			}
    		}


    		if(idnegeriPeguam!=""){
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idnegeriPeguam),"class=disabled disabled style=width:240"));
    			if(idbandarPeguam!=""){
					context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"class=disabled disabled style=width:240"));
     	    	}else{
     	    		context.put("viewBandarPeguam",HTML.SelectBandarByNegeri(idnegeriPeguam,"EtxtBandarPeguam",null,"class=disabled disabled style=width:240"));
     	    	}
    		}else{
    			context.put("viewNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,"class=disabled disabled style=width:240"));
    			if(idbandarPeguam!=""){
    				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",Utils.parseLong(idbandarPeguam),"class=disabled disabled style=width:240"));
         	    }else{
    				context.put("viewBandarPeguam",HTML.SelectBandar("EtxtBandarPeguam",null,"class=disabled disabled style=width:240"));
         	    }
    		}


    		vm = mainscreen;

    	}//simpan maklumat pp

    	else if("dataRayuan".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="1";
            }
            context.put("selectedTab",selectedTab);

            //reset
    		context.put("tarikhRayuan", "");
    		context.put("perkara", "");
    		//context.put("alasan", "");

            String id_permohonan = "";
            int id_status = 0;

            id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String tarikh_rayuan = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			tarikh_rayuan = x.get("tarikh_rayuan").toString();
    		}

    		//get data Maklumat perayu
    		//model.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_status", id_status);


    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("tarikh_rayuan", tarikh_rayuan);

    		//form validation
    		context.put("editable", "no");
    		context.put("editform", "no");
    		context.put("buttonBatal", "yes");

    		Hashtable mr2 = new Hashtable();

    		if(maklumatRayuan.size()!=0){

    			mr2 = (Hashtable) maklumatRayuan.get(0);
        		String id_perayu2 = mr2.get("id_perayu").toString();
        		String id_rayuan = mr2.get("id_rayuan").toString();
        		String pRayu = mr2.get("perkara_rayu").toString();

        		//--03122009
        		long totalWordPerkaraRayu = 0;
        		String perkara_rayu = "";

    			if(maklumatRayuan.size()!=0){
    				Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    				perkara_rayu = mr.get("perkara_rayu").toString();
    			}
    			if(perkara_rayu!=""){
    				totalWordPerkaraRayu = Utils.wordcount(perkara_rayu);
    			}

    			context.put("totalWordPerkaraRayu", totalWordPerkaraRayu);

    			//--03122009

        		//id
        		context.put("id_perayu", id_perayu2);
        		context.put("id_permohonan", id_permohonan);
        		context.put("id_pemohon", id_pemohon);
        		context.put("id_rayuan", id_rayuan);

        		//data
        		context.put("maklumatRayuan", maklumatRayuan);
        		context.put("tarikh_rayuan", tarikh_rayuan);

        		//form validation
        		context.put("editable", "yes");


        		if(pRayu ==""){
        			context.put("editform", "yes");
        			context.put("buttonBatal", "no");
        		}else{
        			context.put("editform", "no");
        			context.put("buttonBatal", "yes");
        		}

        		//command 2
            	String submit2 = getParam("command2");
            	myLogger.info("[submit]2 :: " +submit2);

            	if ("kemaskiniDataRayuan".equals(submit2)){

            		//form validation
            		context.put("editable", "yes");
            		context.put("editform", "yes");
            		context.put("buttonBatal", "yes");

            		//command 3
                	String submit3 = getParam("command3");
                	myLogger.info("[submit]3 :: " +submit3);

                	if ("simpanDataRayuan".equals(submit3)){

                 		if (doPost.equals("true")) {

                 			simpanDataRayuan(session);

                 		}

                   		usid=(String)session.getAttribute("_ekptg_user_id");

                		//get info pemohon
                    	model1.setListSemak(id_permohonan,usid);
                		dataPemohon = model1.getListSemak();
                		headerppk_baru(session,id_permohonan,"Y","","T");

                		//get data Maklumat perayu
                		//model2.setDataMaklumat(id_permohonan,id_pemohon);
                		model2.setDataMaklumat(id_permohonan,id_perintah);
                		maklumatRayuan = model2.getDataMaklumat();
                		context.put("maklumatRayuan_size", maklumatRayuan.size());

                		//--03122009
                		totalWordPerkaraRayu = 0;
                		perkara_rayu = "";

            			if(maklumatRayuan.size()!=0){
		    				Hashtable mr = (Hashtable) maklumatRayuan.get(0);
		    				perkara_rayu = mr.get("perkara_rayu").toString();
		    			}
		    			if(perkara_rayu!=""){
		    				totalWordPerkaraRayu = Utils.wordcount(perkara_rayu);
		    			}

		    			context.put("totalWordPerkaraRayu", totalWordPerkaraRayu);

		    			//--03122009

                		//form validation
                		context.put("editable", "yes");
                		context.put("editform", "no");
                		context.put("buttonBatal", "yes");

                		//data
                		context.put("maklumatRayuan", maklumatRayuan);
                		context.put("dataPemohon", dataPemohon);

                		//id
                		context.put("id_perayu", id_perayu2);
                		//context.put("id_peguam", id_peguam2);
                		context.put("id_permohonan", id_permohonan);
                		context.put("id_pemohon", id_pemohon);
                		context.put("id_rayuan", id_rayuan);

                	}//close simpanDataRayuan

            	}//close kemaskiniDataRayuan

    		}//close if(maklumatRayuan.size()!=0)

    		vm = mainscreen;
    	}

    	else if("maklumatSerahan".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="3";
            }
            context.put("selectedTab",selectedTab);

            //reset data
            context.put("n3", "");
            context.put("tarikh_serah","");
            context.put("namaPER", "");
            context.put("alamatPER1", "");
            context.put("alamatPER2", "");
            context.put("alamatPER3", "");
            context.put("poskodPER", "");
            context.put("bandarPER", "");

            //-- 11122009
            context.put("tarikhS", "");

            // -- 02122009 -- //
            //reset
            context.put("enableRadio1", "disabled");
    		context.put("enableRadio2", "disabled");
    		context.put("enableNoPos", "disabled");
    		context.put("catatan","");
	    	context.put("daftarPos","");
	    	context.put("checkStatus1", "");
    		context.put("checkStatus2", "");
    		context.put("checkStatus3", "");
    		context.put("checkStatus4", "");
    		context.put("jSerahCheck1", "");
    		context.put("jSerahCheck2", "");
    		context.put("ONjenis_serah","");
			context.put("ONstatus_serah","");

    		// -- 02122009 -- //

            String id_rayuan = "";
            String id_permohonan = "";
            int id_status = 0;

            id_rayuan = getParam("id_rayuan");
            id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String idFail = "";
    		String idPejabatJKPTG = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			idFail = x.get("idFail").toString();
    			idPejabatJKPTG = x.get("id_pejabatjkptg").toString();
    		}

    		// -- 02122009 -- //

    		model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
    		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();
    		//and
    		model1.setPenghantarNotis();
    		penghantarNotis = model1.getPenghantarNotis();

    		if(penghantarNotisByJkptg.size()!=0){
    			context.put("penghantarNotis",penghantarNotisByJkptg);
    		}else{
    			context.put("penghantarNotis",penghantarNotis);
    		}

    		// -- 02122009 -- //

    		//get data Maklumat perayu
    		//model.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

            //id
            context.put("id_rayuan", id_rayuan);
            context.put("id_permohonan", id_permohonan);
            context.put("id_status", id_status);
            context.put("id_pemohon", id_pemohon);
            context.put("id_fail", idFail);
            context.put("id_suburusanstatusfail", id_suburusanstatusfail);

            //data
    		context.put("maklumatRayuan", maklumatRayuan);
    		context.put("dataPemohon", dataPemohon);

    		//form validation
    		context.put("editable", "no");
    		context.put("viewformK3", "no");
			context.put("editformK3", "no");

			//reset data
			context.put("tarikhSerah", "");
			context.put("namaPenerima", "");
			context.put("alamat1Serah", "");
			context.put("alamat2Serah", "");
			context.put("alamat3Serah", "");
			context.put("poskodSerah", "");
			context.put("bandarSerah", "");
			context.put("bayaranNB", "");

    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:300"));
    		context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"class=disabled disabled style=width:280"));


    		if(maklumatRayuan.size()!=0){

    			//form validation
    			Hashtable mrx = (Hashtable) maklumatRayuan.get(0);
        		String ValAsas = mrx.get("asas_keputusan").toString();

            	if(ValAsas.isEmpty()){
            		context.put("editable", "no");
        		}else{
        			context.put("editable", "yes");
        		}


        		//tnggu k1		rayuan dtruskan   rayuan dtolak	   batal(lpas pndah)
        		if(id_status==163 || id_status==164 || id_status==165 || id_status==169 || id_status==166 || id_status==167 || id_status==180){

        			id_permohonan = getParam("id_permohonan");

        			//semak

        			String nota_bicara = mrx.get("nota_bicara").toString();
        			String asas_keputusan = mrx.get("asas_keputusan").toString();
        			long totalWord = 0;
        			long totalWordAsasKeputusan = 0;

        			if(nota_bicara!=""){
        				totalWord = Utils.wordcount(nota_bicara);
        			}
        			if(asas_keputusan!=""){
        				totalWordAsasKeputusan = Utils.wordcount(asas_keputusan);
        			}
        			context.put("totalWord", totalWord);
        			context.put("totalWordAsasKeputusan", totalWordAsasKeputusan);

        			//get data bayaran
            		model3.setBayaranKR(id_permohonan);
            		bayaranKR = model3.getBayaranKR();

            		String bayaranRekod = "";
            		String id_bayaran = "";

            		if(bayaranKR.size()!=0){
            			Hashtable b = (Hashtable) bayaranKR.get(0);
            			id_bayaran = b.get("id_bayaran").toString();
            		}

        			//data maklumat serahan
        			model2.setDataMaklumatSerahan(id_rayuan);
            		maklumatSerahan = model2.getDataMaklumatSerahan();

            		String idN = "";
        			String idB = "";
        			String id_serahanrayuan = "";

            		if(maklumatSerahan.size()!=0){
            			Hashtable zz = (Hashtable) maklumatSerahan.get(0);
            			idN = zz.get("id_negeri").toString();
            			idB = zz.get("id_bandar").toString();
            			id_serahanrayuan = zz.get("id_serahanrayuan").toString();
            		}

            		//-- 02122009
            		//data maklumat mst rayuan
        			model2.setDataMstRayuan(idperbicaraan);
            		dataMstRayuan = model2.getDataMstRayuan();
            		String id_penghantarnotis = "";
            		String jenis_serah = "";
            		String status_serah = "";
            		String jenisSerah1 = "";
        			String jenisSerah2 = "";
        			String serah1 = "";
        			String serah2 = "";
        			String pos1 = "";
        			String pos2 = "";
        			if(dataMstRayuan.size()!=0){
        				Hashtable mst = (Hashtable) dataMstRayuan.get(0);
        				id_penghantarnotis = mst.get("id_penghantarnotis").toString();
        				jenis_serah = mst.get("jenis_serah").toString();
        				status_serah = mst.get("status_serah").toString();
        			}

            		//data
        			context.put("dataMstRayuan", dataMstRayuan);
        			context.put("dataMstRayuan_size", dataMstRayuan.size());

        			//radio button jenis serah
        			if (jenis_serah.equals("1")){
        				jenisSerah1 = "checked";
        				jenisSerah2 = "";
        			}else if(jenis_serah.equals("2")){
        				jenisSerah1 = "";
        				jenisSerah2 = "checked";
        			}else{
        				jenisSerah1 = "";
        				jenisSerah2 = "";
        			}
        			context.put("jenisSerah1",jenisSerah1);
        			context.put("jenisSerah2",jenisSerah2);

        			//radio button status serah
        			if (status_serah.equals("1")){
        				serah1 = "checked";
        				serah2 = "";
        				pos1 = "";
        				pos2 = "";
        			}else if(status_serah.equals("2")){
        				serah1 = "";
        				serah2 = "checked";
        				pos1 = "";
        				pos2 = "";
        			}else if(status_serah.equals("3")){
        				serah1 = "";
        				serah2 = "";
        				pos1 = "checked";
        				pos2 = "";
        			}else if(status_serah.equals("4")){
        				serah1 = "";
        				serah2 = "";
        				pos1 = "";
        				pos2 = "checked";
        			}else{
        				serah1 = "";
        				serah2 = "";
        				pos1 = "";
        				pos2 = "";
        			}

        			context.put("serah1",serah1);
        			context.put("serah2",serah2);
        			context.put("pos1",pos1);
        			context.put("pos2",pos2);

        			//-- 02122009

            		//id
            		context.put("id_serahanrayuan", id_serahanrayuan);
            		context.put("id_permohonan", id_permohonan);
            		context.put("id_status", id_status);
            		context.put("id_bayaran", id_bayaran);
            		context.put("bayaranKR",bayaranKR);

            		//validation
        			context.put("viewformK3", "yes");
        			context.put("editformK3", "no");
        			context.put("onchangebandar","no");

        			//data
        			context.put("maklumatSerahan", maklumatSerahan);

        			//dropdown
        			if(idN!=""){
                		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idN),"class=disabled disabled style=width:300"));
                		if(idB!=""){
                			context.put("selectBandar",HTML.SelectBandarByNegeri(idN,"txtBandarSerah",Utils.parseLong(idB),"class=disabled disabled style=width:280"));
                    	}else{
                    		context.put("selectBandar",HTML.SelectBandarByNegeri(idN,"txtBandarSerah",null,"class=disabled disabled style=width:280"));
                        }
        			}else{
        				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:300"));
        				if(idB!=""){
        					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",Utils.parseLong(idB),"class=disabled disabled style=width:280"));
        				}else{
        					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"class=disabled disabled style=width:280"));
            			}
        			}

        			//command 2
                	String submit2 = getParam("command2");
                	myLogger.info("[submit]2 :: " +submit2);

                	if ("kemaskiniSerahan".equals(submit2)){

                		if(dataMstRayuan.size()!=0){
            				Hashtable mst = (Hashtable) dataMstRayuan.get(0);
            				id_penghantarnotis = mst.get("id_penghantarnotis").toString();
            			}

                		if(id_penghantarnotis==""){

                			// -- 02122009 -- //

                    		model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
                    		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();
                    		//and
                    		model1.setPenghantarNotis();
                    		penghantarNotis = model1.getPenghantarNotis();

                    		if(penghantarNotisByJkptg.size()!=0){
                    			context.put("penghantarNotis",penghantarNotisByJkptg);
                    		}else{
                    			context.put("penghantarNotis",penghantarNotis);
                    		}
                    		// -- 02122009 -- //
                		}
                		else{

                		//selected penghantar ob
    	    			getSelectedPenghantarNotisByJkptg = model1.getSelectedPenghantarNotisByJkptg(idPejabatJKPTG,id_penghantarnotis);
                		//and
    	    			getSelectedPenghantarNotis = model1.getSelectedPenghantarNotis(id_penghantarnotis);
    	    			//check penghantar notis ade o xde dlm negeri
    	    			model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
    	        		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();

                		if(idPejabatJKPTG!=""){
                			if(penghantarNotisByJkptg.size()!=0){

                				context.put("penghantarNotis",getSelectedPenghantarNotisByJkptg);
                			}else{

                				context.put("penghantarNotis",getSelectedPenghantarNotis);
                			}

                		}else{

                			context.put("penghantarNotis",getSelectedPenghantarNotis);
                		}

                		}


                		nota_bicara = mrx.get("nota_bicara").toString();
            			String kos = "";
            			totalWord = 0;

            			if(nota_bicara!=""){

            				// 1 ~ 100 = RM30.00
            				// next 1 hundred += RM30.00
            				totalWord = Utils.wordcount(nota_bicara);

            				//double eaWord = 0.30;
            				//double total = totalWord * eaWord;

            				double total = 0;

            				if(totalWord <= 100){
            					total = 30.00;
            				}else if(totalWord > 100 && totalWord <= 200){
            					total = 60.00;
            				}else if(totalWord > 200 && totalWord <= 300){
            					total = 90.00;
            				}else if(totalWord > 300 && totalWord <= 400){
            					total = 120.00;
            				}else if(totalWord > 400 && totalWord <= 500){
            					total = 150.00;
            				}else if(totalWord > 500 && totalWord <= 600){
            					total = 180.00;
            				}else if(totalWord > 600 && totalWord <= 700){
            					total = 210.00;
            				}

            				kos = Utils.format2Decimal(total);

            			}

            			context.put("bayaranNB", kos);
            			context.put("totalWord", totalWord);

                		//validation
            			context.put("viewformK3", "yes");
            			context.put("editformK3", "yes");

            			//id
                        context.put("id_rayuan", id_rayuan);
                        context.put("id_permohonan", id_permohonan);
                        context.put("id_status", id_status);
                        context.put("id_pemohon", id_pemohon);
                        context.put("id_serahanrayuan", id_serahanrayuan);

                        //dropdown
            			if(idN!=""){
                    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idN),null,"style=width:300 onchange=dochangeBandarByNegeriUpdate()"));
                    		if(idB!=""){
                    			context.put("selectBandar",HTML.SelectBandarByNegeri(idN,"txtBandarSerah",Utils.parseLong(idB),"style=width:280"));
                        	}else{
                        		context.put("selectBandar",HTML.SelectBandarByNegeri(idN,"txtBandarSerah",null,"style=width:280"));
                            }
            			}else{
            				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:300 onchange=dochangeBandarByNegeriUpdate()"));
            				if(idB!=""){
            					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",Utils.parseLong(idB),"style=width:280"));
            				}else{
            					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"style=width:280"));
                			}
            			}

            			//command 3
                    	String submit3 = getParam("command3");
                    	myLogger.info("[submit]3 :: " +submit3);

                    	if ("updateSerahan".equals(submit3)){


                     		if (doPost.equals("true")) {

                     			updateSerahan(session,idperbicaraan);
                     		}


                    		//refresh data
                       		usid=(String)session.getAttribute("_ekptg_user_id");

                    		//get info pemohon
                        	model1.setListSemak(id_permohonan,usid);
                    		dataPemohon = model1.getListSemak();
                    		headerppk_baru(session,id_permohonan,"Y","","T");

                    		//get data bayaran
                    		model3.setBayaranKR(id_permohonan);
                    		bayaranKR = model3.getBayaranKR();

                    		if(bayaranKR.size()!=0){
                    			Hashtable b = (Hashtable) bayaranKR.get(0);
                    			id_bayaran = b.get("id_bayaran").toString();
                    		}

                    		//data maklumat serahan
                			model2.setDataMaklumatSerahan(id_rayuan);
                    		maklumatSerahan = model2.getDataMaklumatSerahan();

                    		String idNeg = "";
                			String idBan = "";

                    		if(maklumatSerahan.size()!=0){
                    			Hashtable z = (Hashtable) maklumatSerahan.get(0);
                    			idNeg = z.get("id_negeri").toString();
                    			idBan = z.get("id_bandar").toString();
                    		}

                    		//data
                			context.put("maklumatSerahan", maklumatSerahan);
                			context.put("dataPemohon", dataPemohon);
                			context.put("bayaranKR", bayaranKR);

                			//id
                			context.put("id_bayaran", id_bayaran);

                			//dropdown
                			if(idNeg!=""){
                        		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNeg),"class=disabled disabled style=width:300"));
                        		if(idBan!=""){
                        			context.put("selectBandar",HTML.SelectBandarByNegeri(idNeg,"txtBandarSerah",Utils.parseLong(idBan),"class=disabled disabled style=width:280"));
                            	}else{
                            		context.put("selectBandar",HTML.SelectBandarByNegeri(idNeg,"txtBandarSerah",null,"class=disabled disabled style=width:280"));
                                }
                			}else{
                				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:300"));
                				if(idBan!=""){
                					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",Utils.parseLong(idBan),"class=disabled disabled style=width:280"));
                				}else{
                					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"class=disabled disabled style=width:280"));
                    			}
                			}

                			//-- 02122009
                    		model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
                    		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();
                    		//and
                    		model1.setPenghantarNotis();
                    		penghantarNotis = model1.getPenghantarNotis();

                    		if(penghantarNotisByJkptg.size()!=0){
                    			context.put("penghantarNotis",penghantarNotisByJkptg);
                    		}else{
                    			context.put("penghantarNotis",penghantarNotis);
                    		}

                    		//data maklumat mst rayuan
                			model2.setDataMstRayuan(idperbicaraan);
                    		dataMstRayuan = model2.getDataMstRayuan();
                    		if(dataMstRayuan.size()!=0){
                				Hashtable mst = (Hashtable) dataMstRayuan.get(0);
                				id_penghantarnotis = mst.get("id_penghantarnotis").toString();
                				jenis_serah = mst.get("jenis_serah").toString();
                				status_serah = mst.get("status_serah").toString();
                			}

                    		//data
                			context.put("dataMstRayuan", dataMstRayuan);
                			context.put("dataMstRayuan_size", dataMstRayuan.size());

                			//radio button jenis serah
                			if (jenis_serah.equals("1")){
                				jenisSerah1 = "checked";
                				jenisSerah2 = "";
                			}else if(jenis_serah.equals("2")){
                				jenisSerah1 = "";
                				jenisSerah2 = "checked";
                			}else{
                				jenisSerah1 = "";
                				jenisSerah2 = "";
                			}
                			context.put("jenisSerah1",jenisSerah1);
                			context.put("jenisSerah2",jenisSerah2);

                			//radio button status serah
                			if (status_serah.equals("1")){
                				serah1 = "checked";
                				serah2 = "";
                				pos1 = "";
                				pos2 = "";
                			}else if(status_serah.equals("2")){
                				serah1 = "";
                				serah2 = "checked";
                				pos1 = "";
                				pos2 = "";
                			}else if(status_serah.equals("3")){
                				serah1 = "";
                				serah2 = "";
                				pos1 = "checked";
                				pos2 = "";
                			}else if(status_serah.equals("4")){
                				serah1 = "";
                				serah2 = "";
                				pos1 = "";
                				pos2 = "checked";
                			}else{
                				serah1 = "";
                				serah2 = "";
                				pos1 = "";
                				pos2 = "";
                			}

                			context.put("serah1",serah1);
                			context.put("serah2",serah2);
                			context.put("pos1",pos1);
                			context.put("pos2",pos2);
                			//-- 02122009

                    		//validation
                    		context.put("viewformK3", "yes");
                			context.put("editformK3", "no");
                			context.put("onchangebandar","no");

                    	}//update serahan

                    	else if ("dochangeBandarByNegeriUpdate".equals(submit3)){

                    		//-- 02122009
                    		jenis_serah = getParam("sorJenisSerah");
                    		status_serah = getParam("sorStatus");
                    		String noDaftar = getParam("txtNoDaftarPos");
                    		String catatan = getParam("txtCatatan");
                    		String id_penghantar = getParam("txtNamaPenghantar");

                    		//-- 11122009
                    		String tarikhS = getParam("tarikhS");
                    		context.put("ONtarikhS", tarikhS);

                    		context.put("ONnoDaftar", noDaftar);
                    		context.put("ONcatatan", catatan);

                    		//radio button jenis serah
                			if (jenis_serah.equals("1")){
                				jenisSerah1 = "checked";
                				jenisSerah2 = "";
                			}else if(jenis_serah.equals("2")){
                				jenisSerah1 = "";
                				jenisSerah2 = "checked";
                			}else{
                				jenisSerah1 = "";
                				jenisSerah2 = "";
                			}
                			context.put("ONjenis_serah",jenis_serah);
                			context.put("jenisSerah1",jenisSerah1);
                			context.put("jenisSerah2",jenisSerah2);

                			//radio button status serah
                			if (status_serah.equals("1")){
                				serah1 = "checked";
                				serah2 = "";
                				pos1 = "";
                				pos2 = "";
                			}else if(status_serah.equals("2")){
                				serah1 = "";
                				serah2 = "checked";
                				pos1 = "";
                				pos2 = "";
                			}else if(status_serah.equals("3")){
                				serah1 = "";
                				serah2 = "";
                				pos1 = "checked";
                				pos2 = "";
                			}else if(status_serah.equals("4")){
                				serah1 = "";
                				serah2 = "";
                				pos1 = "";
                				pos2 = "checked";
                			}else{
                				serah1 = "";
                				serah2 = "";
                				pos1 = "";
                				pos2 = "";
                			}

                			context.put("ONstatus_serah",status_serah);
                			context.put("serah1",serah1);
                			context.put("serah2",serah2);
                			context.put("pos1",pos1);
                			context.put("pos2",pos2);


                			String namax = "";
        	    			String kodx = "";

        	    			if(id_penghantar!=""){

            	    			//get data keputusan permohonan
            	        		getPenghantarNotis = model1.getDetailPenghantarNotis(id_penghantar);
            	        		if(getPenghantarNotis.size()!=0){
            	        			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
            	        			namax = x.get("nama").toString();
            	        			kodx = x.get("kod_penghantar_notis").toString();
            	        		}

            	    			//selected penghantar ob
            	    			getSelectedPenghantarNotisByJkptg = model1.getSelectedPenghantarNotisByJkptg(idPejabatJKPTG,id_penghantar);
                        		//and
            	    			getSelectedPenghantarNotis = model1.getSelectedPenghantarNotis(id_penghantar);
            	    			//check penghantar notis ade o xde dlm negeri
            	    			model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
            	        		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();

                        		if(idPejabatJKPTG!=""){
                        			if(penghantarNotisByJkptg.size()!=0){
                        				context.put("penghantarNotisONCHANGE",getSelectedPenghantarNotisByJkptg);
                        			}else{
                        				context.put("penghantarNotisONCHANGE",getSelectedPenghantarNotis);
                        			}

                        		}else{
                        			context.put("penghantarNotisONCHANGE",getSelectedPenghantarNotis);
                        		}

                        		context.put("onchangeMyListDropdown","yes");

            	    		}else{

            	    			context.put("onchangeMyListDropdown","no");

            	    			model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
            	        		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();
            	        		//and
            	        		model1.setPenghantarNotis();
            	        		penghantarNotis = model1.getPenghantarNotis();

            	        		if(penghantarNotisByJkptg.size()!=0){
            	        			context.put("penghantarNotis",penghantarNotisByJkptg);
            	        		}else{
            	        			context.put("penghantarNotis",penghantarNotis);
            	        		}

            	    		}

        		    		context.put("SnamaPntr", namax);
        		    		context.put("SkodPntr", kodx);
        		    		context.put("SidPntr", id_penghantar);

                    		//-- 02122009

                    		//validation
                			context.put("viewformK3", "yes");
                			context.put("editformK3", "yes");
                			context.put("onchangebandar","yes");

                    		String tarikh_serah,namaP,alamat1,alamat2,alamat3,
                    		poskod,idnegeri = "";

                    		String bayaranR = getParam("txtBayaranRekod");

                    		tarikh_serah = getParam("txdTarikhSerahan");
                    		namaP = getParam("txtNamaPenerima");
                    		alamat1 = getParam("alamatSerah1");
                    		alamat2 = getParam("alamatSerah2");
                    		alamat3 = getParam("alamatSerah3");
                    		poskod = getParam("txtPoskodSerah");
                	    	idnegeri = getParam("socNegeri");

                	    	//dropdown
                			if(idnegeri!=""){
                        		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),null,"style=width:300 onchange=dochangeBandarByNegeriUpdate()"));
                        		context.put("selectBandar",HTML.SelectBandarByNegeri(idnegeri,"txtBandarSerah",null,"style=width:280"));
                            }else{
                				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:300 onchange=dochangeBandarByNegeriUpdate()"));
                				context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"style=width:280"));
                    		}

                			context.put("bayaranRx", bayaranR);
                			context.put("tarikh_serah", tarikh_serah);
                			context.put("namaPER", namaP);
                			context.put("alamatPER1", alamat1);
                			context.put("alamatPER2", alamat2);
                			context.put("alamatPER3", alamat3);
                			context.put("poskodPER", poskod);

                    	}//close dochangeBandarByNegeriUpdate

                	}//kemaskini serahan

        		}//if id_status = 163
/*new form*/    else
        		{

					if(ValAsas.isEmpty()){

        				//reset data
        				context.put("n3", "");
        				context.put("tarikh_serah", "");
        	            context.put("namaPER", "");
        	            context.put("alamatPER1", "");
        	            context.put("alamatPER2", "");
        	            context.put("alamatPER3", "");
        	            context.put("poskodPER", "");
        	            context.put("bandarPER", "");

        	            //11122009
        	            context.put("tarikhS", "");

        	            context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"disabled class=disabled style=width:300"));
        	            context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"disabled class=disabled style=width:280"));

        			}else{

        				String nota_bicara = mrx.get("nota_bicara").toString();
            			String kos = "";
            			long totalWord = 0;

            			if(nota_bicara!=""){

            				// 1 ~ 100 = RM30.00
            				// next 1 hundred += RM30.00
            				totalWord = Utils.wordcount(nota_bicara);

            				//double eaWord = 0.30;
            				//double total = totalWord * eaWord;

            				double total = 0;

            				if(totalWord <= 100){
            					total = 30.00;
            				}else if(totalWord > 100 && totalWord <= 200){
            					total = 60.00;
            				}else if(totalWord > 200 && totalWord <= 300){
            					total = 90.00;
            				}else if(totalWord > 300 && totalWord <= 400){
            					total = 120.00;
            				}else if(totalWord > 400 && totalWord <= 500){
            					total = 150.00;
            				}else if(totalWord > 500 && totalWord <= 600){
            					total = 180.00;
            				}else if(totalWord > 600 && totalWord <= 700){
            					total = 210.00;
            				}

            				kos = Utils.format2Decimal(total);

            			}

            			context.put("bayaranNB", kos);
            			context.put("totalWord", totalWord);

        			String namaPER,alamatPER1,alamatPER2,alamatPER3,poskodPER,
        			negeriPER,idbandarPER = "";

        			//newform
        			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
        			namaPER = mr.get("nama_perayu").toString();
        			alamatPER1 = mr.get("alamat_perayu1").toString();
        			alamatPER2 = mr.get("alamat_perayu2").toString();
        			alamatPER3 = mr.get("alamat_perayu3").toString();
        			poskodPER = mr.get("poskod_perayu").toString();
      //  			String bandarPER = mr.get("bandar_perayu").toString();
        			negeriPER = mr.get("negeri_perayu").toString();
        			idbandarPER = mr.get("id_bandar_perayu").toString();

        			if(namaPER!=""){
        				context.put("namaPER", namaPER);
        			}else{
        				context.put("namaPER", "");
        			}

        			if(alamatPER1!=""){
        				context.put("alamatPER1", alamatPER1);
        			}else{
        				context.put("alamatPER1", "");
        			}

        			if(alamatPER2!=""){
        				context.put("alamatPER2", alamatPER2);
        			}else{
        				context.put("alamatPER2", "");
        			}

        			if(alamatPER3!=""){
        				context.put("alamatPER3", alamatPER3);
        			}else{
        				context.put("alamatPER3", "");
        			}

        			if(poskodPER!=""){
        				context.put("poskodPER", poskodPER);
        			}else{
        				context.put("poskodPER", "");
        			}


        			//dropdown
        			if(negeriPER!=""){
                		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(negeriPER),null, "style=width:300 onchange=dochangeBandarByNegeri()"));
                		if(idbandarPER!=""){
                			context.put("selectBandar",HTML.SelectBandarByNegeri(negeriPER,"txtBandarSerah",Utils.parseLong(idbandarPER),"style=width:280"));
                    	}else{
                    		context.put("selectBandar",HTML.SelectBandarByNegeri(negeriPER,"txtBandarSerah",null,"style=width:280"));
                        }
        			}else{
        				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:300 onchange=dochangeBandarByNegeri()"));
        				if(idbandarPER!=""){
        					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",Utils.parseLong(idbandarPER),"style=width:280"));
        				}else{
        					context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"style=width:280"));
            			}
        			}


        			//validation
        			context.put("viewformK3", "no");
        			context.put("editformK3", "no");

        			//command 2
                	String submit2 = getParam("command2");
                	myLogger.info("[submit]2 :: " +submit2);

                	if ("simpanSerahan".equals(submit2)){


                 		if (doPost.equals("true")) {

                 			simpanSerahanK3(session,idperbicaraan);

                 		}


                   		usid=(String)session.getAttribute("_ekptg_user_id");

                		//get info pemohon
                    	model1.setListSemak(id_permohonan,usid);
                		dataPemohon = model1.getListSemak();
                		headerppk_baru(session,id_permohonan,"Y","","T");

                		String id_stat = "";

                		if(dataPemohon.size()!=0){
                			Hashtable xxx = (Hashtable) dataPemohon.get(0);
                			id_stat = xxx.get("id_Status").toString();
                		}

                		//get data bayaran
                		model3.setBayaranKR(id_permohonan);
                		bayaranKR = model3.getBayaranKR();

                		String id_bayaran = "";

                		if(bayaranKR.size()!=0){
                			Hashtable b = (Hashtable) bayaranKR.get(0);
                			id_bayaran = b.get("id_bayaran").toString();
                		}

                		//data maklumat serahan
            			model2.setDataMaklumatSerahan(id_rayuan);
                		maklumatSerahan = model2.getDataMaklumatSerahan();

                		String idNeg = "";
            			String idBan = "";
            			String id_serahanrayuan = "";

                		if(maklumatSerahan.size()!=0){
                			Hashtable z = (Hashtable) maklumatSerahan.get(0);
                			idNeg = z.get("id_negeri").toString();
                			idBan = z.get("id_bandar").toString();
                			id_serahanrayuan = z.get("id_serahanrayuan").toString();
                		}

                		//-- 02122009
                		model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
                		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();
                		//and
                		model1.setPenghantarNotis();
                		penghantarNotis = model1.getPenghantarNotis();

                		if(penghantarNotisByJkptg.size()!=0){
                			context.put("penghantarNotis",penghantarNotisByJkptg);
                		}else{
                			context.put("penghantarNotis",penghantarNotis);
                		}

                		//data maklumat mst rayuan
            			model2.setDataMstRayuan(idperbicaraan);
                		dataMstRayuan = model2.getDataMstRayuan();
                		String id_penghantarnotis = "";
                		String jenis_serah = "";
                		String status_serah = "";
                		String jenisSerah1 = "";
            			String jenisSerah2 = "";
            			String serah1 = "";
            			String serah2 = "";
            			String pos1 = "";
            			String pos2 = "";
            			if(dataMstRayuan.size()!=0){
            				Hashtable mst = (Hashtable) dataMstRayuan.get(0);
            				id_penghantarnotis = mst.get("id_penghantarnotis").toString();
            				jenis_serah = mst.get("jenis_serah").toString();
            				status_serah = mst.get("status_serah").toString();
            			}

                		//data
            			context.put("dataMstRayuan", dataMstRayuan);
            			context.put("dataMstRayuan_size", dataMstRayuan.size());

            			//radio button jenis serah
            			if (jenis_serah.equals("1")){
            				jenisSerah1 = "checked";
            				jenisSerah2 = "";
            			}else if(jenis_serah.equals("2")){
            				jenisSerah1 = "";
            				jenisSerah2 = "checked";
            			}else{
            				jenisSerah1 = "";
            				jenisSerah2 = "";
            			}
            			context.put("jenisSerah1",jenisSerah1);
            			context.put("jenisSerah2",jenisSerah2);

            			//radio button status serah
            			if (status_serah.equals("1")){
            				serah1 = "checked";
            				serah2 = "";
            				pos1 = "";
            				pos2 = "";
            			}else if(status_serah.equals("2")){
            				serah1 = "";
            				serah2 = "checked";
            				pos1 = "";
            				pos2 = "";
            			}else if(status_serah.equals("3")){
            				serah1 = "";
            				serah2 = "";
            				pos1 = "checked";
            				pos2 = "";
            			}else if(status_serah.equals("4")){
            				serah1 = "";
            				serah2 = "";
            				pos1 = "";
            				pos2 = "checked";
            			}else{
            				serah1 = "";
            				serah2 = "";
            				pos1 = "";
            				pos2 = "";
            			}

            			context.put("serah1",serah1);
            			context.put("serah2",serah2);
            			context.put("pos1",pos1);
            			context.put("pos2",pos2);

            			//-- 02122009

            			//id
                        context.put("id_rayuan", id_rayuan);
                        context.put("id_permohonan", id_permohonan);
                        context.put("id_status", id_stat);
                        context.put("id_pemohon", id_pemohon);
                        context.put("id_serahanrayuan", id_serahanrayuan);
                        context.put("id_bayaran", id_bayaran);

                		//data
            			context.put("maklumatSerahan", maklumatSerahan);
            			context.put("dataPemohon", dataPemohon);
            			context.put("bayaranKR",bayaranKR);

            			//dropdown
        				if(idNeg!=""){
        					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNeg),"class=disabled disabled style=width:300"));
        					if(idBan!=""){
        						context.put("selectBandar",HTML.SelectBandarByNegeri(idNeg,"txtBandarSerah",Utils.parseLong(idBan),"class=disabled disabled style=width:280"));
        					}else{
        						context.put("selectBandar",HTML.SelectBandarByNegeri(idNeg,"txtBandarSerah",null,"class=disabled disabled style=width:280"));
        					}
        				}else{
        					context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,"class=disabled disabled style=width:300"));
        					if(idBan!=""){
        						context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",Utils.parseLong(idBan),"class=disabled disabled style=width:280"));
        					}else{
        						context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"class=disabled disabled style=width:280"));
        					}
        				}

                		//validation
            			context.put("viewformK3", "yes");
            			context.put("editformK3", "no");
            			context.put("onchangebandar", "no");

                	}//close simpan serahan

                	else if ("dochangeBandarByNegeri".equals(submit2)){

                		String tarikh_serah,namaP,alamat1,alamat2,alamat3,
                		poskod,idnegeri = "";

                		String bayaran = "0.00";

                		bayaran = getParam("txtBayaranRekod");
                		tarikh_serah = getParam("txdTarikhSerahan");
                		namaP = getParam("txtNamaPenerima");
                		alamat1 = getParam("alamatSerah1");
                		alamat2 = getParam("alamatSerah2");
                		alamat3 = getParam("alamatSerah3");
                		poskod = getParam("txtPoskodSerah");
            	    	idnegeri = getParam("socNegeri");

            	    	//11122009
            	    	String tarikhS = "";
            	    	tarikhS = getParam("tarikhS");

            	    	//-- [add]02122009 --//

            	    	String jenis_serah = "";
            	    	String status_serah = "";
            	    	String noPos = "";
            	    	String catatan = "";
            	    	String id_penghantarnotis = "";
            	    	jenis_serah = getParam("sorJenisSerah");
            	    	status_serah = getParam("sorStatus");
            	    	noPos = getParam("txtNoDaftarPos");
            	    	catatan = getParam("txtCatatan");
            	    	id_penghantarnotis = getParam("txtNamaPenghantar");

            	    	if(id_penghantarnotis!=""){
        	    			String nama = "";
        	    			String kod = "";

        	        		getPenghantarNotis = model1.getDetailPenghantarNotis(id_penghantarnotis);
        	        		if(getPenghantarNotis.size()!=0){
        	        			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
        	        			nama = x.get("nama").toString();
        	        			kod = x.get("kod_penghantar_notis").toString();
        	        		}

        	    			context.put("idPenghantar", id_penghantarnotis);
        	    			context.put("namaPenghantar", nama);
        	    			context.put("kodPenghantar", kod);

        	    			//selected penghantar ob
        	    			getSelectedPenghantarNotisByJkptg = model1.getSelectedPenghantarNotisByJkptg(idPejabatJKPTG,id_penghantarnotis);
                    		//and
        	    			getSelectedPenghantarNotis = model1.getSelectedPenghantarNotis(id_penghantarnotis);
                    		//check penghantar notis ade o xde dlm negeri
        	    			model1.setPenghantarNotisByJkptg(idPejabatJKPTG);
        	        		penghantarNotisByJkptg = model1.getPenghantarNotisByJkptg();

                    		if(idPejabatJKPTG!=""){
                    			if(penghantarNotisByJkptg.size()!=0){
                    				context.put("penghantarNotisONCHANGE",getSelectedPenghantarNotisByJkptg);
                    			}else{
                    				context.put("penghantarNotisONCHANGE",getSelectedPenghantarNotis);
                    			}
                    		}else{
                    			context.put("penghantarNotisONCHANGE",getSelectedPenghantarNotis);
                    		}

                    		context.put("onchangeNegeri","yes");

        	    		}else{
        	    			context.put("onchangeNegeri","no");
        	    		}

            	    	//data
            	    	context.put("catatan",catatan);
            	    	context.put("daftarPos",noPos);

            	    	if(jenis_serah.equals("1")){
            	    		context.put("jSerahCheck1", "checked");
            	    		context.put("jSerahCheck2", "");
            	    		context.put("enableRadio1", "");
            	    		context.put("enableRadio2", "disabled");
            	    	}else{
            	    		context.put("jSerahCheck1", "");
            	    		context.put("jSerahCheck2", "checked");
            	    		context.put("enableRadio1", "disabled");
            	    		context.put("enableRadio2", "");
            	    	}

            	    	if(status_serah.equals("1")){
            	    		context.put("checkStatus1", "checked");
            	    		context.put("checkStatus2", "");
            	    		context.put("checkStatus3", "");
            	    		context.put("checkStatus4", "");
            	    		context.put("enableNoPos", "disabled");
            	    	}else if(status_serah.equals("2")){
            	    		context.put("checkStatus1", "");
            	    		context.put("checkStatus2", "checked");
            	    		context.put("checkStatus3", "");
            	    		context.put("checkStatus4", "");
            	    		context.put("enableNoPos", "disabled");
            	    	}else if(status_serah.equals("3")){
            	    		context.put("checkStatus1", "");
            	    		context.put("checkStatus2", "");
            	    		context.put("checkStatus3", "checked");
            	    		context.put("checkStatus4", "");
            	    		context.put("enableNoPos", "");
            	    	}else if(status_serah.equals("4")){
            	    		context.put("checkStatus1", "");
            	    		context.put("checkStatus2", "");
            	    		context.put("checkStatus3", "");
            	    		context.put("checkStatus4", "checked");
            	    		context.put("enableNoPos", "disabled");
            	    	}else{
            	    		context.put("checkStatus1", "checked");
            	    		context.put("checkStatus2", "");
            	    		context.put("checkStatus3", "");
            	    		context.put("checkStatus4", "");
            	    		context.put("enableNoPos", "disabled");
            	    	}

            	    	//-- [add]02122009 --//

            	    	//dropdown
            			if(idnegeri!=""){
                    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),null,"style=width:300 onchange=dochangeBandarByNegeri()"));
                    		context.put("selectBandar",HTML.SelectBandarByNegeri(idnegeri,"txtBandarSerah",null,"style=width:280"));
                        }else{
            				context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:300 onchange=dochangeBandarByNegeri()"));
            				context.put("selectBandar",HTML.SelectBandar("txtBandarSerah",null,"style=width:280"));
                		}

            			//validation
            			context.put("modeBayaran","change");

            			context.put("bayaranR",bayaran);
            			context.put("tarikh_serah", tarikh_serah);
            			context.put("namaPER", namaP);
            			context.put("alamatPER1", alamat1);
            			context.put("alamatPER2", alamat2);
            			context.put("alamatPER3", alamat3);
            			context.put("poskodPER", poskod);

            			//11122009
            			context.put("tarikhS", tarikhS);

                	}//close dochangebandarbynegeri

            		}//close else trayu n prayu ==""

        		}//close if(id_status==163)

    		}//close if(maklumatRayuan.size()!=0)


            vm = mainscreen;

    	}//close maklumatSerahan

    	else if("rekodRayuan".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="2";
            }
            context.put("selectedTab",selectedTab);

            String id_permohonan = "";
    		id_permohonan = getParam("id_permohonan");

            //get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_fail = "";
    		String id_pemohon = "";
    		int id_status = 0;
    		String id_simati = "";

    		if(dataPemohon.size()!=0){
    			Hashtable ls = (Hashtable) dataPemohon.get(0);
    			id_fail = ls.get("idFail").toString();
    			id_pemohon = ls.get("idPemohon").toString();
    			id_status = Integer.parseInt(ls.get("id_Status").toString());
    			id_simati = ls.get("idSimati").toString();
    		}

    		//get data Maklumat perayu
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan_size", maklumatRayuan.size());

    		String id_rayuan = "";
    		String id_perayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable x = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = x.get("id_rayuan").toString();
    			id_perayu = x.get("id_perayu").toString();
    		}

    		//get list ob atas 18
			model2.setListcbOB(id_permohonansimatiINT,id_rayuan,id_perayu,id_simati);
			listOBatas18 = model2.getListcbOB();
			context.put("listOB_size", listOBatas18.size());

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_fail", id_fail);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);
    		context.put("id_status", id_status);
    		context.put("id_simati", id_simati);

    		//data
    		context.put("dataPemohon", dataPemohon);
    		context.put("maklumatRayuan", maklumatRayuan);
    		context.put("listOB", listOBatas18);

    		context.put("editable", "");
    		context.put("newform", "");
			context.put("editform", "");

    		if(maklumatRayuan.size()!=0){

    			//form validation
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
        		//String ARayu = mr.get("alasan_rayuan").toString();
            	String pRayu = mr.get("perkara_rayu").toString();
            	String ValAsas = mr.get("asas_keputusan").toString();

            	if(pRayu.isEmpty()){
            		context.put("editable", "no");
        		}else{
        			context.put("editable", "yes");

        			if(ValAsas.isEmpty()){

            			//newform
        				context.put("newform", "yes");
        				context.put("editform", "yes");

        				String submit2 = getParam("command2");
        				myLogger.info("[submit]:2 :: " +submit2);

        				if ("simpanRekodRayuan".equals(submit2)){

        					if (doPost.equals("true")) {
                				simpanRekodRayuan(session);
                			}

    						id_permohonan = getParam("id_permohonan");

    						//data pemohon
    						model1.setListSemak(id_permohonan,usid);
    			    		dataPemohon = model1.getListSemak();
    			    		headerppk_baru(session,id_permohonan,"Y","","T");

    						//get data Maklumat perayu
    		    			model2.setDataMaklumat(id_permohonan,id_perintah);
    		    			maklumatRayuan = model2.getDataMaklumat();

    		    			String nota_bicara = "";
    		    			String kos = "";
                			long totalWord = 0;

                			//--03122009
                			String asas_keputusan = "";
                			long totalWordAsasKeputusan = 0;

                			if(maklumatRayuan.size()!=0){
    		    				mr = (Hashtable) maklumatRayuan.get(0);
    		    				nota_bicara = mr.get("nota_bicara").toString();
    		    				asas_keputusan = mr.get("asas_keputusan").toString();
    		    				id_perayu = mr.get("id_perayu").toString();
    		    			}

                			if(asas_keputusan!=""){
                				totalWordAsasKeputusan = Utils.wordcount(asas_keputusan);
                			}

                			context.put("totalWordAsasKeputusan", totalWordAsasKeputusan);

    		    			if(nota_bicara!=""){

                				totalWord = Utils.wordcount(nota_bicara);
                				double total = 0;

                				if(totalWord <= 100){
                					total = 30.00;
                				}else if(totalWord > 100 && totalWord <= 200){
                					total = 60.00;
                				}else if(totalWord > 200 && totalWord <= 300){
                					total = 90.00;
                				}else if(totalWord > 300 && totalWord <= 400){
                					total = 120.00;
                				}else if(totalWord > 400 && totalWord <= 500){
                					total = 150.00;
                				}else if(totalWord > 500 && totalWord <= 600){
                					total = 180.00;
                				}else if(totalWord > 600 && totalWord <= 700){
                					total = 210.00;
                				}

                				kos = Utils.format2Decimal(total);
                			}

                			context.put("bayaranNB", kos);
                			context.put("totalWord", totalWord);

    		    			//get list ob atas 18
    		    			model2.setListcbOB(id_permohonansimatiINT,id_rayuan,id_perayu,id_simati);
    		    			listOBatas18 = model2.getListcbOB();
    		    			context.put("listOB_size", listOBatas18.size());

    		    			//data
        					context.put("maklumatRayuan", maklumatRayuan);
        					context.put("dataPemohon", dataPemohon);
        					context.put("listOB", listOBatas18);

        					//size
        					context.put("maklumatRayuan_size", maklumatRayuan.size());

    						//validation kemaskini
    						context.put("newform", "no");
            				context.put("editform", "no");

        				}//close simpanRekodRayuan

        			}else{

        				//-03122009
        				String asas_keputusan = mr.get("asas_keputusan").toString();
        				long totalWordAsasKeputusan = 0;

        				if(asas_keputusan!=""){
        					totalWordAsasKeputusan = Utils.wordcount(asas_keputusan);
        				}
        				context.put("totalWordAsasKeputusan", totalWordAsasKeputusan);
        				//-03122009

        				String nota_bicara = mr.get("nota_bicara").toString();
            			String kos = "";
            			long totalWord = 0;

            			if(nota_bicara!=""){

            				// 1 ~ 100 = RM30.00
            				// next 1 hundred += RM30.00
            				totalWord = Utils.wordcount(nota_bicara);

            				//double eaWord = 0.30;
            				//double total = totalWord * eaWord;

            				double total = 0;

            				if(totalWord <= 100){
            					total = 30.00;
            				}else if(totalWord > 100 && totalWord <= 200){
            					total = 60.00;
            				}else if(totalWord > 200 && totalWord <= 300){
            					total = 90.00;
            				}else if(totalWord > 300 && totalWord <= 400){
            					total = 120.00;
            				}else if(totalWord > 400 && totalWord <= 500){
            					total = 150.00;
            				}else if(totalWord > 500 && totalWord <= 600){
            					total = 180.00;
            				}else if(totalWord > 600 && totalWord <= 700){
            					total = 210.00;
            				}

            				kos = Utils.format2Decimal(total);

            			}

            			context.put("bayaranNB", kos);
            			context.put("totalWord", totalWord);

        				//semak
        				context.put("newform", "no");
        				context.put("editform", "no");

        				//data
    					context.put("maklumatRayuan", maklumatRayuan);

    					//size
    					context.put("maklumatRayuan_size", maklumatRayuan.size());

    					String submit2 = getParam("command2");
        				myLogger.info("[submit]:2 :: " +submit2);

    					if ("kemaskiniRekodRayuan".equals(submit2)){

    						//validation kemaskini
    						context.put("newform", "no");
            				context.put("editform", "yes");

    					}//close kemaskiniRekodRayuan

    					else if ("batalRekodRayuan".equals(submit2)){

    						//validation kemaskini
    						context.put("newform", "no");
            				context.put("editform", "no");

    					}//close batalRekodRayuan

    					else if ("updateRekodRayuan".equals(submit2)){

    						if (doPost.equals("true")) {
                				simpanRekodRayuan(session);
                			}

    						id_permohonan = getParam("id_permohonan");

    						//data pemohon
    						model1.setListSemak(id_permohonan,usid);
    			    		dataPemohon = model1.getListSemak();
    			    		headerppk_baru(session,id_permohonan,"Y","","T");

    						//get data Maklumat perayu
    		    			model2.setDataMaklumat(id_permohonan,id_perintah);
    		    			maklumatRayuan = model2.getDataMaklumat();

    		    			if(maklumatRayuan.size()!=0){
    		    				mr = (Hashtable) maklumatRayuan.get(0);
    		    				nota_bicara = mr.get("nota_bicara").toString();
    		    				asas_keputusan = mr.get("asas_keputusan").toString();
    		    				id_perayu = mr.get("id_perayu").toString();
    		    			}

    		    			if(asas_keputusan!=""){
    		    				totalWordAsasKeputusan = Utils.wordcount(asas_keputusan);
    		    			}
    		    			context.put("totalWordAsasKeputusan", totalWordAsasKeputusan);

    		    			if(nota_bicara!=""){

                				totalWord = Utils.wordcount(nota_bicara);
                				double total = 0;

                				if(totalWord <= 100){
                					total = 30.00;
                				}else if(totalWord > 100 && totalWord <= 200){
                					total = 60.00;
                				}else if(totalWord > 200 && totalWord <= 300){
                					total = 90.00;
                				}else if(totalWord > 300 && totalWord <= 400){
                					total = 120.00;
                				}else if(totalWord > 400 && totalWord <= 500){
                					total = 150.00;
                				}else if(totalWord > 500 && totalWord <= 600){
                					total = 180.00;
                				}else if(totalWord > 600 && totalWord <= 700){
                					total = 210.00;
                				}

                				kos = Utils.format2Decimal(total);
                			}

                			context.put("bayaranNB", kos);
                			context.put("totalWord", totalWord);

    		    			//get list ob atas 18
    		    			model2.setListcbOB(id_permohonansimatiINT,id_rayuan,id_perayu,id_simati);
    		    			listOBatas18 = model2.getListcbOB();
    		    			context.put("listOB_size", listOBatas18.size());

    		    			//data
        					context.put("maklumatRayuan", maklumatRayuan);
        					context.put("dataPemohon", dataPemohon);
        					context.put("listOB", listOBatas18);

        					//size
        					context.put("maklumatRayuan_size", maklumatRayuan.size());

    						//validation kemaskini
    						context.put("newform", "no");
            				context.put("editform", "no");

    					}//close batalRekodRayuan

        			}//close if(ValAsas.isEmpty())

        		}//close if(ARayu.isEmpty() && pRayu.isEmpty())


    		}//close maklumatRayuan.size()!=0

            vm = mainscreen;

    	}//close rekod rayuan

    	else if("doCheckSOCTXTTambah".equals(submit)){

    		context.put("xidFirma", "");
    		context.put("xnamaFirma", "");
    		context.put("xnoRujukan", "");
    		context.put("xalamatPeguam1", "");
    		context.put("xalamatPeguam2", "");
    		context.put("xalamatPeguam3", "");
    		context.put("xposkodPeguam", "");
    		context.put("xbandarPeguam", "");
    		context.put("xnoTel", "");
    		context.put("xnoFax", "");
    		context.put("xemel", "");

    		String id_permohonan = "";
    		int id_status = 0;

			id_permohonan = getParam("id_permohonan");
			id_status = getParamAsInteger("id_status");

			//get info pemohon
    		model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";

    		if(dataPemohon.size()!=0){
    			Hashtable ls = (Hashtable) dataPemohon.get(0);
    			id_pemohon = ls.get("idPemohon").toString();
    		}

			String sorPeguam = getParam("sorPeguam");

			//id
			context.put("id_permohonan",id_permohonan);
			context.put("id_status",id_status);
			context.put("id_pemohon",id_pemohon);


			context.put("namaFirma", "");
    		context.put("noRujukan", "");
    		context.put("alamatPeguam1", "");
    		context.put("alamatPeguam2", "");
    		context.put("alamatPeguam3", "");
    		context.put("poskodPeguam", "");
    		context.put("bandarPeguam", "");
    		context.put("noTel", "");
    		context.put("noFax", "");
    		context.put("emel", "");

    		String idp = getParam("id_permohonan");

    		//dropdown
	    	context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirmaTambah()"));
	    	context.put("tambahNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriTambah()"));
	    	context.put("tambahBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));


			//form validation
			context.put("newform", "no");
			context.put("editform", "no");
			context.put("onchangePP", "no");
			context.put("formTambah", "yes");
			context.put("onchangeXX", "");

	    	//validation
    		if(sorPeguam.equals("1")){
    			context.put("showSOC", "yes");
    			context.put("showTXT", "no");
	    		context.put("checkA", "checked");
	    		context.put("checkB", "");
	    	}else if(sorPeguam.equals("2")){
	    		context.put("showSOC", "no");
    			context.put("showTXT", "yes");
	    		context.put("checkA", "");
	    		context.put("checkB", "checked");
	    	}

	    	vm = mainscreen;

	}//close onchange sor tambah

    	else if("getDetailFirmaTambah".equals(submit)){

    		String id_permohonan = "";
    		int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

			String socNamaFirma = getParam("socNamaFirma");
			String sorPeguam = getParam("sorPeguam");

			//get info pemohon
        	model1.setListSemak(id_permohonan,usid);
    		dataPemohon = model1.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    		}

			//data
    		context.put("dataPemohon", dataPemohon);

	    	if(sorPeguam.equals("1")){
	    		context.put("checkA", "checked");
	    		context.put("checkB", "");
	    	}else if(sorPeguam.equals("2")){
	    		context.put("checkA", "");
	    		context.put("checkB", "checked");
	    	}

	    	String txtNamaFirma = "";
	    	String txtNoRujukan = "";
	    	String txtAlamatPeguam1 = "";
	    	String txtAlamatPeguam2 = "";
	    	String txtAlamatPeguam3 = "";
	    	String txtPoskodPeguam = "";
	    	String txtBandarPeguam = "";
	    	String txtNoTelefon = "";
	    	String txtNoFaks = "";
	    	String txtEmel = "";
	    	String idneg = "";
	    	String idbandarpeguam = "";

	    	if(socNamaFirma!=""){
	    	detailFirma = model2.getDetailFirma(socNamaFirma);

	    		if(detailFirma.size()!=0)
	    		{
	    			Hashtable onc = (Hashtable) detailFirma.get(0);
	    			txtNamaFirma = onc.get("nama_firma").toString();
	    			txtNoRujukan = onc.get("no_rujukan_firma").toString();
	    			txtAlamatPeguam1 = onc.get("alamat1").toString();
	    			txtAlamatPeguam2 = onc.get("alamat2").toString();
	    			txtAlamatPeguam3 = onc.get("alamat3").toString();
	    			txtPoskodPeguam = onc.get("poskod").toString();
	    			txtBandarPeguam = onc.get("bandar").toString();
	    			txtNoTelefon = onc.get("no_tel").toString();
	    			txtNoFaks = onc.get("no_fax").toString();
	    			txtEmel = onc.get("emel").toString();
	    			idneg = onc.get("id_negeri").toString();
	    			idbandarpeguam = onc.get("id_bandar").toString();

	    		}
	    	}


	    	//set data peguam
	    	context.put("xidFirma", socNamaFirma);
    		context.put("xnamaFirma", txtNamaFirma);
    		context.put("xnoRujukan", txtNoRujukan);
    		context.put("xalamatPeguam1", txtAlamatPeguam1);
    		context.put("xalamatPeguam2", txtAlamatPeguam2);
    		context.put("xalamatPeguam3", txtAlamatPeguam3);
    		context.put("xposkodPeguam", txtPoskodPeguam);
    		context.put("xbandarPeguam", txtBandarPeguam);
    		context.put("xnoTel", txtNoTelefon);
    		context.put("xnoFax", txtNoFaks);
    		context.put("xemel", txtEmel);

    		if(idneg!=""){
    			context.put("tambahNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",Utils.parseLong(idneg),null,"style=width:240 onchange=getBandarPeguamByNegeriTambah()"));
    			if(idbandarpeguam!=""){
    				context.put("tambahBandarPeguam",HTML.SelectBandarByNegeri(idneg,"txtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
        	    }else{
        	    	context.put("tambahBandarPeguam",HTML.SelectBandarByNegeri(idneg,"txtBandarPeguam",null,"style=width:240"));
            	}
    		}else{
         		context.put("tambahNegeriPeguam",HTML.SelectNegeri("socNegeriPeguam",null,null,"style=width:240 onchange=getBandarPeguamByNegeriTambah()"));
         		if(idbandarpeguam!=""){
         			context.put("tambahBandarPeguam",HTML.SelectBandar("txtBandarPeguam",Utils.parseLong(idbandarpeguam),"style=width:240"));
        	    }else{
        	    	context.put("tambahBandarPeguam",HTML.SelectBandar("txtBandarPeguam",null,"style=width:240"));
            	}
    		}

    		String idp = getParam("id_permohonan");

    		if(socNamaFirma!=""){
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",Utils.parseLong(socNamaFirma),"id=socNamaFirma style=width:240 onchange=getDetailFirmaTambah()"));
    		}else{
    			context.put("selectFirmaTerdahulu",HTML.SelectFirmaTerdahuluByPemohon(id_pemohon,idp,"socNamaFirma",null,"id=socNamaFirma style=width:240 onchange=getDetailFirmaTambah()"));
        	}

	    	//validation
	    	context.put("onchangePP", "no");
	    	context.put("newform", "no");
    		context.put("editform", "no");
    		context.put("formTambah", "yes");
    		context.put("onchangeXX", "");

    		vm = mainscreen;

    	}//close dochange firma tambah

    	else if ("cari".equals(submit)){

    		carianRayuan(session);
    		listdepan = model2.getListCarianSek17();

			//data & size list carian
			context.put("listRayuan", listdepan);
    		context.put("list_size", listdepan.size());
    		setupPage(session,action,listdepan);
    		vm = screensenarai;

    	}//close cari

    	else
    	{

    		model2.setListDefaultSek17(usid);
    		listdepan = model2.getListDefaultSek17();

    		//data & size default list
    		context.put("listRayuan", listdepan);
    		context.put("list_size", listdepan.size());
    		setupPage(session,action,listdepan);

    		vm = screensenarai;

    	}//close else

       this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
       this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
       this.context.put("id_perbicaraan", idperbicaraan);
       this.context.put("flagForView", flagForView);

       //Template template = engine.getTemplate(vm);
       //return template;

      

       return vm;

	}//close template


/*
 *
 *	::Method::
 *
 */


	//carian
	private void carianRayuan(HttpSession session) throws Exception{

		String noFail = getParam("txtNoFail");
		String namaPemohon = getParam("txtPemohon");
		String namaSimati = getParam("txtSimati");
		String icSimati = getParam("txtIcSimati");
		String JenisIc = getParam("jenisIc");
		String usid="";
   		usid=(String)session.getAttribute("_ekptg_user_id");

		model2.setCarianFailSek17(noFail,namaPemohon,namaSimati,icSimati,JenisIc,usid);

	}//close carian

	//simpan semakan K2
	private void simpanSemakanK2(HttpSession session,String id_perintah) throws Exception{

			Hashtable h = new Hashtable();

			String flag = getParam("flagRadio");

			//id
			String id_permohonan = getParam("id_permohonan");
			String id_status = getParam("id_status");
			String id_fail = getParam("id_fail");
			String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

			//other data
			String noResitFee = getParam("txtNomborResitFee");
			String noResitDeposit = getParam("txtNomborResitDeposit");
			String tarikhBayaranFee = getParam("txdTarikhByrnFee");
			String tarikhBayaranDeposit = getParam("txdTarikhByrnDeposit");
			String jumlahDeposit = getParam("txtJumDeposit");
			String tarikhTerimaRayuan = getParam("txdTarikhTerimaRayuan");

			//send to model
			h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
			h.put("id_fail",id_fail);
			h.put("id_suburusanstatusfail",id_suburusanstatusfail);
			h.put("id_permohonan",id_permohonan);
			h.put("tarikhTerimaRayuan",tarikhTerimaRayuan);
			h.put("id_perintah",id_perintah);
			h.put("flag",flag);


			model2.updateFlagRayuan(h);

			String radiosemaks = getParam("radiosemaks");
	    	//radio
	    	h.put("radiosemaks",radiosemaks);
	    	model2.simpanRadioK2(h);

	    	//checkbox
	    	String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	if(cbsemaks!=null){
	    		for (int i = 0; i < cbsemaks.length; i++) {
	    			String noresit = "";
        			String tarikhbayaran = "";
        			String jumlahbayaran = "";
        			if (cbsemaks[i].equals("124")){
        				noresit = noResitFee;
        				tarikhbayaran = tarikhBayaranFee;
        			}else if (cbsemaks[i].equals("125")){
        				noresit = noResitDeposit;
        				tarikhbayaran = tarikhBayaranDeposit;
        				jumlahbayaran = jumlahDeposit;
        			}
        			model2.simpanSemakanK2Sek17(h,cbsemaks[i], String.valueOf(id_permohonan), String.valueOf(noresit), String.valueOf(tarikhbayaran),String.valueOf(jumlahbayaran));
        			}

	    		model2.updateSuburusanSFailSek17(session,h);

        	}


	  }//close simpanSemakanK2

	//Update semakan K2
	private void updateSemakanK2(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_status = getParam("id_status");
	    	String id_bayaranF = getParam("id_bayaranF");
	    	String id_bayaranD = getParam("id_bayaranD");

	    	//other data
	    	String noResitFee = getParam("EtxtNomborResitFee");
	    	String noResitDeposit = getParam("EtxtNomborResitDeposit");
	    	String tarikhBayaranFee = getParam("EtxdTarikhByrnFee");
	    	String tarikhBayaranDeposit = getParam("EtxdTarikhByrnDeposit");
	    	String jumlahDeposit = getParam("EtxtJumDeposit");
	    	String tarikhTerimaRayuan = getParam("EtxdTarikhTerimaRayuan");

	    	//send to model
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
	    	h.put("update_bayaranF", id_bayaranF);
    		h.put("update_bayaranD", id_bayaranD);
    		h.put("tarikhTerimaRayuan", tarikhTerimaRayuan);

	    	//delete checkbox current value
	    	model2.semakanDeleteSek17(id_permohonan);

	    	String radiosemaks = getParam("Eradiosemaks");
	    	//radio
	    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
	    	h.put("id_permohonan", id_permohonan);
	    	h.put("radiosemaks",radiosemaks);
	    	model2.simpanRadioK2(h);

	    	//update(addnew) checkbox
	    	String[] cbsemaks = this.request.getParameterValues("Ecbsemaks");
	    	if(cbsemaks!=null){
	    		for (int i = 0; i < cbsemaks.length; i++) {
	    			String noresit = "";
        			String tarikhbayaran = "";
        			String jumlahbayaran = "";
        			String deleteF = "";
        			if (cbsemaks[i].equals("124")){
        				noresit = noResitFee;
        				tarikhbayaran = tarikhBayaranFee;
        			}else if (cbsemaks[i].equals("125")){
        				noresit = noResitDeposit;
        				tarikhbayaran = tarikhBayaranDeposit;
        				jumlahbayaran = jumlahDeposit;
        			}
        			//  + get id bayaranF n D.... if kosong == "" ==>add
        			model2.updateSemakanK2Sek17(h,cbsemaks[i], String.valueOf(id_permohonan), String.valueOf(noresit), String.valueOf(tarikhbayaran),String.valueOf(jumlahbayaran),String.valueOf(deleteF));
        			}
        	}

	  }//close updateSemakanK2


	//simpan Maklumat PP
	private void simpanMaklumatPP(HttpSession session,String id_perintah) throws Exception{

		Hashtable h = new Hashtable();

		//id
    	String id_permohonan = getParam("id_permohonan");
    	String id_pemohon = getParam("id_pemohon");

    	String idListOB = getParam("socPerayu");
    	Vector onchangeListOB = model2.getOnchangeListOB(idListOB);

    	String txtNamaPerayu = "";

    	if(onchangeListOB.size()!=0){
    		Hashtable xyz = (Hashtable) onchangeListOB.get(0);
    		txtNamaPerayu = xyz.get("nama_ob").toString();
    	}

    	String tarikh_rayuan = getParam("txdTarikhRayuan");

    	//get data perayu
    	String a,b,c = "";
    	String kplama = "";
    	String kplain = "";
    	String jeniskplain = "";
    	a = getParam("txtNoKPBaru1");
    	b = getParam("txtNoKPBaru2");
    	c = getParam("txtNoKPBaru3");
    	kplama = getParam("txtNoKPLama");
    	kplain = getParam("txtNoKPLain");
    	jeniskplain = getParam("jenis_kp");

    	String txtNoKPBaru = a+b+c;

    	//String txtNamaPerayu = getParam("txtNamaPerayu");
    	String txtAlamatPerayu1 = getParam("txtAlamatPerayu1");
    	String txtAlamatPerayu2 = getParam("txtAlamatPerayu2");
    	String txtAlamatPerayu3 = getParam("txtAlamatPerayu3");
    	String txtPoskodPerayu = getParam("txtPoskodPerayu");
    	String txtBandarPerayu = getParam("txtBandarPerayu");
    	String socNegeriPerayu = getParam("socNegeriPerayu");

    	//get data peguam
    	String txtNoRujukan = getParam("txtNoRujukan");
    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
    	String txtBandarPeguam = getParam("txtBandarPeguam");
    	String txtNoTelefon = getParam("txtNoTelefon");
    	String txtNoFaks = getParam("txtNoFaks");
    	String txtEmel = getParam("txtEmel");
    	String socNegeriPeguam = getParam("socNegeriPeguam");

    	String socNamaFirma = "";

    	//selection peguam
    	String txtNamaFirma = getParam("txtNamaFirma");
    	socNamaFirma = getParam("socNamaFirma");

    	int sorPeguam = getParamAsInteger("sorPeguam");

    	// 1 = update
    	// 2 = insert

    	if(sorPeguam==1){
    		String nama_firma = "";
    		if(socNamaFirma!=""){
    		Vector detailFirma = model2.getDetailFirma(socNamaFirma);
    		Hashtable onc = (Hashtable) detailFirma.get(0);
    		//nama_firma = onc.get("nama_firma").toString();
    		//myLogger.info("[get new id] = " +socNamaFirma);
    		//myLogger.info("[get new] = " +nama_firma);

    		}
    		if(socNamaFirma!=""){
    			h.put("Xdefault",1);
	    		h.put("id_peguam", socNamaFirma);
    		}else{
    			h.put("Xdefault",2);
	    		h.put("id_peguam", "");
	    		h.put("txtNamaFirma",txtNamaFirma);
    		}


    	}else if(sorPeguam==2){
    		h.put("txtNamaFirma",txtNamaFirma);
    		h.put("Xdefault",2);
    		h.put("id_peguam", "");
    	}else{
    		h.put("txtNamaFirma",txtNamaFirma);
    		h.put("Xdefault",2);
    		h.put("id_peguam", "");
    	}


    	//send perayu to model
    	h.put("id_perintah",id_perintah);
    	h.put("id_permohonan",id_permohonan);
    	h.put("id_pemohon",id_pemohon);
    	h.put("id_ob",idListOB);
    	h.put("jeniskp",jeniskplain);
    	h.put("txtNoKPBaru",txtNoKPBaru);
    	h.put("txtNoKPLama",kplama);
    	h.put("txtNoKPLain",kplain);
    	h.put("txtNamaPerayu",txtNamaPerayu);
    	h.put("txtAlamatPerayu1",txtAlamatPerayu1);
    	h.put("txtAlamatPerayu2",txtAlamatPerayu2);
    	h.put("txtAlamatPerayu3",txtAlamatPerayu3);
    	h.put("txtPoskodPerayu",txtPoskodPerayu);
    	h.put("txtBandarPerayu",txtBandarPerayu);
    	h.put("socNegeriPerayu",socNegeriPerayu);
    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

    	//send peguam to model
    	//h.put("txtNamaFirma",txtNamaFirma);
    	h.put("txtNoRujukan",txtNoRujukan);
    	h.put("txtAlamatPeguam1",txtAlamatPeguam1);
    	h.put("txtAlamatPeguam2",txtAlamatPeguam2);
    	h.put("txtAlamatPeguam3",txtAlamatPeguam3);
    	h.put("txtPoskodPeguam",txtPoskodPeguam);
    	h.put("txtBandarPeguam",txtBandarPeguam);
    	h.put("txtNoTelefon",txtNoTelefon);
    	h.put("txtNoFaks",txtNoFaks);
    	h.put("txtEmel",txtEmel);
    	h.put("socNegeriPeguam",socNegeriPeguam);

    	h.put("tarikh_rayuan",tarikh_rayuan);

	    	//model2.simpanMaklumatPPSek17(h);
	    	model2.simpanMaklumatPP(h);

	  }//close simpan Maklumat PP


	//update Maklumat PP
	private void updateMaklumatPP(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_pemohon = getParam("id_pemohon");
	    	String id_perayu = getParam("id_perayu");
	    	String id_peguam = getParam("id_peguam");

	    	//validation name (save nama perayu or id ob)
	    	String onchangex = getParam("onchangex");

	    	String a,b,c = "";
	    	String kplama = "";
	    	String kplain = "";

	    	//get data perayu
	    	a = getParam("EtxtNoKPBaru1");
	    	b = getParam("EtxtNoKPBaru2");
	    	c = getParam("EtxtNoKPBaru3");
	    	String txtNoKPBaru = a+b+c;
	    	kplama = getParam("EtxtNoKPLama");
	    	kplain = getParam("EtxtNoKPLain");

	    	String txtNamaPerayu = getParam("EsocPerayu");
	    	String txtAlamatPerayu1 = getParam("EtxtAlamatPerayu1");
	    	String txtAlamatPerayu2 = getParam("EtxtAlamatPerayu2");
	    	String txtAlamatPerayu3 = getParam("EtxtAlamatPerayu3");
	    	String txtPoskodPerayu = getParam("EtxtPoskodPerayu");
	    	String txtBandarPerayu = getParam("EtxtBandarPerayu");
	    	String socNegeriPerayu = getParam("EsocNegeriPerayu");

	    	String namaPERAYU = "";

	    	if(onchangex.equals("yes")){

	    		myLogger.info("[update]:onchange(YES) = " +txtNamaPerayu);
	    		Vector onchangeListOB = model2.getOnchangeListOB(txtNamaPerayu);
	    		Hashtable xyz = (Hashtable) onchangeListOB.get(0);
		    	namaPERAYU = xyz.get("nama_ob").toString();

		    	h.put("txtNamaPerayu",namaPERAYU);

	    	}else{
	    		h.put("txtNamaPerayu",txtNamaPerayu);
	    	}


	    	//get data peguam
	    	String txtNamaFirma = getParam("EtxtNamaFirma");
	    	String txtNoRujukan = getParam("EtxtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("EtxtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("EtxtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("EtxtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("EtxtPoskodPeguam");
	    	String txtBandarPeguam = getParam("EtxtBandarPeguam");
	    	String txtNoTelefon = getParam("EtxtNoTelefon");
	    	String txtNoFaks = getParam("EtxtNoFaks");
	    	String txtEmel = getParam("EtxtEmel");
	    	String socNegeriPeguam = getParam("EsocNegeriPeguam");

	    	//send id to model
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_pemohon",id_pemohon);
	    	h.put("id_perayu",id_perayu);
	    	h.put("id_peguam",id_peguam);

	    	//send perayu to model
	    	h.put("txtNoKPBaru",txtNoKPBaru);
	    	h.put("txtNoKPLama",kplama);
	    	h.put("txtNoKPLain",kplain);
	    	h.put("txtAlamatPerayu1",txtAlamatPerayu1);
	    	h.put("txtAlamatPerayu2",txtAlamatPerayu2);
	    	h.put("txtAlamatPerayu3",txtAlamatPerayu3);
	    	h.put("txtPoskodPerayu",txtPoskodPerayu);
	    	h.put("txtBandarPerayu",txtBandarPerayu);
	    	h.put("socNegeriPerayu",socNegeriPerayu);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

	    	//send peguam to model
	    	h.put("txtNamaFirma",txtNamaFirma);
	    	h.put("txtNoRujukan",txtNoRujukan);
	    	h.put("txtAlamatPeguam1",txtAlamatPeguam1);
	    	h.put("txtAlamatPeguam2",txtAlamatPeguam2);
	    	h.put("txtAlamatPeguam3",txtAlamatPeguam3);
	    	h.put("txtPoskodPeguam",txtPoskodPeguam);
	    	h.put("txtBandarPeguam",txtBandarPeguam);
	    	h.put("txtNoTelefon",txtNoTelefon);
	    	h.put("txtNoFaks",txtNoFaks);
	    	h.put("txtEmel",txtEmel);
	    	h.put("socNegeriPeguam",socNegeriPeguam);


	    	//validation add/update

	    	String id_socNamaFirma = getParam("socNamaFirma");

	    	int sorPeguam = getParamAsInteger("sorPeguamUpdate");

	    	// 1 = delete n tambah table perayu
	    	// 2 = update cm biase

	    	model2.updatePerayuSek17(h);

	    	if(sorPeguam==1){
	    		if(id_socNamaFirma!=""){
	    			h.put("id_peguamlama",id_socNamaFirma);
	    			model2.deleteDataPeguamSek17(id_peguam);
	    			model2.tambahPeguamLamaSek17(h);
	    		}//else{
	    			//model.updatePeguam(h);
	    		//}
	    	}else if(sorPeguam==2){
	    		model2.updatePeguamSek17(h);
	    	}else{
	    		model2.updatePeguamSek17(h);
	    	}

	    	//close validation add/update

	    	if(onchangex.equals("yes")){
	    		h.put("id_ob", txtNamaPerayu);
	    		model2.inserttableOBSek17(h);
	    	}else{
	    		model2.updatetableOBSek17(h);
	    	}


	  }//close update Maklumat PP


	//simpan Data Rayuan [update]
	private void simpanDataRayuan(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//get id
			String id_rayuan = getParam("id_rayuan");

			//get data perayu
			//String tarikh_rayuan = getParam("txdTarikhRayuan");
			String perkara_rayu = getParam("txtPerkaraRayu");
			//String alasan_rayuan = getParam("txtAlasanRayuan");

			//send id to model
			//h.put("tarikh_rayuan",tarikh_rayuan);
			h.put("perkara_rayu",perkara_rayu);
			//h.put("alasan_rayuan",alasan_rayuan);
			h.put("id_rayuan",id_rayuan);
			h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

			model2.simpanDataRayuan(h);

	  }//simpan Data Rayuan [update]

	//simpan serahan k3
	private void simpanSerahanK3(HttpSession session,String idperbicaraan) throws Exception{

		Hashtable h = new Hashtable();

		//id
    	String id_rayuan = getParam("id_rayuan");
    	String id_permohonan = getParam("id_permohonan");
    	String id_fail = getParam("id_fail");
    	String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

    	String txdTarikhSerahan = getParam("txdTarikhSerahan");
    	String txtNamaPenerima = getParam("txtNamaPenerima");
    	String alamatSerah1 = getParam("alamatSerah1");
    	String alamatSerah2 = getParam("alamatSerah2");
    	String alamatSerah3 = getParam("alamatSerah3");
    	String txtPoskodSerah = getParam("txtPoskodSerah");
    	String txtBandarSerah = getParam("txtBandarSerah");
    	String idnegeri = getParam("socNegeri");

    	String bayaranRekod = getParam("txtBayaranRekod");

    	//11122009
    	String tarikhS = getParam("tarikhS");
    	h.put("tarikhS",tarikhS);

    	h.put("id_fail",id_fail);
    	h.put("id_suburusanstatusfail",id_suburusanstatusfail);
    	h.put("id_permohonan",id_permohonan);
    	h.put("id_rayuan",id_rayuan);
    	h.put("txdTarikhSerahan",txdTarikhSerahan);
    	h.put("txtNamaPenerima",txtNamaPenerima);
    	h.put("alamatSerah1",alamatSerah1);
    	h.put("alamatSerah2",alamatSerah2);
    	h.put("alamatSerah3",alamatSerah3);
    	h.put("txtPoskodSerah",txtPoskodSerah);
    	h.put("txtBandarSerah",txtBandarSerah);
    	h.put("idnegeri",idnegeri);
    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
    	h.put("bayaranRekod",bayaranRekod);

    	//02122009
    	String jenis_status = getParam("sorStatus");
    	String jenis_serah = getParam("sorJenisSerah");
    	String catatan = getParam("txtCatatan");
    	String id_penghantar = getParam("txtNamaPenghantar");
    	String no_surat_daftar = getParam("txtNoDaftarPos");

    	String nama = "";
		Vector getPenghantarNotis = model1.getDetailPenghantarNotis(id_penghantar);
		if(getPenghantarNotis.size()!=0){
			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
			nama = x.get("nama").toString();
		}

		h.put("nama_penghantar",nama);
    	h.put("jenis_status",jenis_status);
    	h.put("jenis_serah",jenis_serah);
    	h.put("catatan",catatan);
    	h.put("id_penghantar",id_penghantar);
    	h.put("no_surat_daftar",no_surat_daftar);
    	h.put("idperbicaraan",idperbicaraan);

	    model2.simpanSerahanK3Sek17(session,h);

	  }//close simpan serahan k3


	//update serahan
	private void updateSerahan(HttpSession session,String idperbicaraan) throws Exception{

		Hashtable h = new Hashtable();

		//get id
    	String id_rayuan = getParam("id_rayuan");
    	String id_serahanrayuan = getParam("id_serahanrayuan");
    	String id_permohonan = getParam("id_permohonan");

    	//get data perayu
    	String tarikh_serahan = getParam("txdTarikhSerahan");
    	String nama_penerima = getParam("txtNamaPenerima");
    	String alamatSerah1 = getParam("alamatSerah1");
    	String alamatSerah2 = getParam("alamatSerah2");
    	String alamatSerah3 = getParam("alamatSerah3");
    	String txtPoskodSerah = getParam("txtPoskodSerah");
    	String txtBandarSerah = getParam("txtBandarSerah");
    	String idnegeri = getParam("socNegeri");

    	String bayaranR = getParam("txtBayaranRekod");
    	String id_bayaran = getParam("id_bayaran");

    	//11122009
    	String tarikhS = getParam("tarikhS");
    	h.put("tarikhS", tarikhS);

    	//send id to model
    	h.put("id_serahanrayuan", id_serahanrayuan);
    	h.put("tarikh_serahan", tarikh_serahan);
    	h.put("nama_penerima",nama_penerima );
    	h.put("alamatSerah1", alamatSerah1);
    	h.put("alamatSerah2", alamatSerah2);
    	h.put("alamatSerah3", alamatSerah3);
    	h.put("txtPoskodSerah", txtPoskodSerah);
    	h.put("txtBandarSerah", txtBandarSerah);
    	h.put("idnegeri",idnegeri);
    	h.put("id_rayuan",id_rayuan);
    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
    	h.put("bayaranR",bayaranR);
    	h.put("id_bayaran",id_bayaran);
    	h.put("id_permohonan", id_permohonan);

    	//-- 02122009
    	model2.setDataMstRayuan(idperbicaraan);
		Vector dataMstRayuan = model2.getDataMstRayuan();
		String id_notisobmst = "";
		if(dataMstRayuan.size()!=0){
			Hashtable mst = (Hashtable) dataMstRayuan.get(0);
			id_notisobmst = mst.get("id_notisobmst").toString();
		}


    	String jenis_status = getParam("sorStatus");
    	String jenis_serah = getParam("sorJenisSerah");
    	String catatan = getParam("txtCatatan");
    	String id_penghantar = getParam("txtNamaPenghantar");
    	String no_surat_daftar = getParam("txtNoDaftarPos");

    	String nama = "";
		Vector getPenghantarNotis = model1.getDetailPenghantarNotis(id_penghantar);
		if(getPenghantarNotis.size()!=0){
			Hashtable x = (Hashtable) getPenghantarNotis.get(0);
			nama = x.get("nama").toString();
		}

		h.put("id_notisobmst",id_notisobmst);
		h.put("idperbicaraan",idperbicaraan);
		h.put("nama_penghantar",nama);
		h.put("id_penghantar",id_penghantar);
		h.put("jenis_serah",jenis_serah);
		h.put("jenis_status",jenis_status);
		h.put("daftar_pos",no_surat_daftar);
		h.put("catatan",catatan);

		if(id_notisobmst!=""){
			model2.updateMstRayuan(h);
		}else{
			model2.addMstRayuan(h);
		}
    	//-- 02122009

	    model2.updateSerahan(h);

	  }//update serahan


	//simpan Tambahan Peguam
	private void simpanTambahanPeguam(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			String socNamaFirma = "";

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_pemohon = getParam("id_pemohon");
	    	String id_perayu = getParam("id_perayu");

	    	int sorPeguam = getParamAsInteger("sorPeguam");

	    	String txtNamaFirma = getParam("txtNamaFirma");
	    	socNamaFirma = getParam("socNamaFirma");

	    	// 1 = update
	    	// 2 = insert


	    	if(sorPeguam==1){
	    		String nama_firma = "";
	    		if(socNamaFirma!=""){
	    		Vector detailFirma = model2.getDetailFirma(socNamaFirma);
        		Hashtable onc = (Hashtable) detailFirma.get(0);
	    		nama_firma = onc.get("nama_firma").toString();
	    		}
	    		if(socNamaFirma!=""){
	    			h.put("Xdefault",1);
		    		h.put("id_peguam", socNamaFirma);
	    		}else{
	    			h.put("Xdefault",2);
		    		h.put("id_peguam", "");
		    		h.put("txtNamaFirma",txtNamaFirma);
	    		}


	    	}else if(sorPeguam==2){
	    		h.put("txtNamaFirma",txtNamaFirma);
	    		h.put("Xdefault",2);
	    		h.put("id_peguam", "");
	    	}else{
	    		h.put("txtNamaFirma",txtNamaFirma);
	    		h.put("Xdefault",2);
	    		h.put("id_peguam", "");
	    	}

	    	//get data peguam
	    	String txtNoRujukan = getParam("txtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
	    	String txtBandarPeguam = getParam("txtBandarPeguam");
	    	String txtNoTelefon = getParam("txtNoTelefon");
	    	String txtNoFaks = getParam("txtNoFaks");
	    	String txtEmel = getParam("txtEmel");
	    	String socNegeriPeguam = getParam("socNegeriPeguam");

	    	//send perayu to model
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_pemohon",id_pemohon);
	    	h.put("id_perayu",id_perayu);
	    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

	    	//send peguam to model
	    	h.put("txtNamaFirma",txtNamaFirma);
	    	h.put("txtNoRujukan",txtNoRujukan);
	    	h.put("txtAlamatPeguam1",txtAlamatPeguam1);
	    	h.put("txtAlamatPeguam2",txtAlamatPeguam2);
	    	h.put("txtAlamatPeguam3",txtAlamatPeguam3);
	    	h.put("txtPoskodPeguam",txtPoskodPeguam);
	    	h.put("txtBandarPeguam",txtBandarPeguam);
	    	h.put("txtNoTelefon",txtNoTelefon);
	    	h.put("txtNoFaks",txtNoFaks);
	    	h.put("txtEmel",txtEmel);
	    	h.put("socNegeriPeguam",socNegeriPeguam);

	    	//printout perayu
	    	myLogger.info(":::::::::[simpanTambahanPeguam][id]::::::::::::");
	    	myLogger.info("[simpanMaklumatPP]id Permohonan = "+id_permohonan);
	    	myLogger.info("[simpanMaklumatPP]id pemohon = "+id_pemohon);
	    	myLogger.info("[simpanMaklumatPP]id perayu = "+id_perayu);
	    	myLogger.info("::::::::::::::::::::::::::::::::::::::::::");

	    	//printout peguam
	    	myLogger.info(":::::::::[simpanTambahanPeguam][Peguam]::::::::::::");
	    	myLogger.info("Nama Firma = "+txtNamaFirma);
	    	myLogger.info("No.Rujukan Firma = "+txtNoRujukan);
	    	myLogger.info("Alamat Peguam 1 = "+txtAlamatPeguam1);
	    	myLogger.info("Alamat Peguam 2 = "+txtAlamatPeguam2);
	    	myLogger.info("Alamat Peguam 3 = "+txtAlamatPeguam3);
	    	myLogger.info("Poskod Peguam = "+txtPoskodPeguam);
	    	myLogger.info("Bandar Peguam = "+txtBandarPeguam);
	    	myLogger.info("No.Tel Peguam = "+txtNoTelefon);
	    	myLogger.info("No.Faks Peguam = "+txtNoFaks);
	    	myLogger.info("Email Peguam = "+txtEmel);
	    	myLogger.info("Negeri Peguam= "+socNegeriPeguam);
	    	myLogger.info("::::::::::::::::::::::::::::::::::::::::::");

	    	model2.simpanTambahanPeguamSek17(h);

	  }//close simpan Maklumat PP

	//update Tambah Peguam
	private void updateTambahPeguam(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_pemohon = getParam("id_pemohon");
	    	String id_perayu = getParam("id_perayu");
	    	String id_peguam = getParam("id_peguamX");

	    	//validation name
	    	String onchangex = getParam("onchangex");


	    	String a,b,c = "";
	    	String kplama = "";
	    	String kplain = "";

	    	//get data perayu
	    	a = getParam("EtxtNoKPBaru1");
	    	b = getParam("EtxtNoKPBaru2");
	    	c = getParam("EtxtNoKPBaru3");
	    	String txtNoKPBaru = a+b+c;
	    	kplama = getParam("EtxtNoKPLama");
	    	kplain = getParam("EtxtNoKPLain");


	    	String txtNamaPerayu = getParam("EsocPerayu");
	    	String txtAlamatPerayu1 = getParam("EtxtAlamatPerayu1");
	    	String txtAlamatPerayu2 = getParam("EtxtAlamatPerayu2");
	    	String txtAlamatPerayu3 = getParam("EtxtAlamatPerayu3");
	    	String txtPoskodPerayu = getParam("EtxtPoskodPerayu");
	    	String txtBandarPerayu = getParam("EtxtBandarPerayu");
	    	String socNegeriPerayu = getParam("socNegeriPerayu");

	    	//get data peguam
	    	String txtNamaFirma = getParam("txtNamaFirma");
	    	String txtNoRujukan = getParam("txtNoRujukan");
	    	String txtAlamatPeguam1 = getParam("txtAlamatPeguam1");
	    	String txtAlamatPeguam2 = getParam("txtAlamatPeguam2");
	    	String txtAlamatPeguam3 = getParam("txtAlamatPeguam3");
	    	String txtPoskodPeguam = getParam("txtPoskodPeguam");
	    	String txtBandarPeguam = getParam("txtBandarPeguam");
	    	String txtNoTelefon = getParam("txtNoTelefon");
	    	String txtNoFaks = getParam("txtNoFaks");
	    	String txtEmel = getParam("txtEmel");
	    	String socNegeriPeguam = getParam("socNegeriPeguam");

	    	String namaPERAYU = "";


	    	if(onchangex.equals("yes")){

	    		Vector onchangeListOB = model2.getOnchangeListOB(txtNamaPerayu);
	    		Hashtable xyz = (Hashtable) onchangeListOB.get(0);
		    	namaPERAYU = xyz.get("nama_ob").toString();

		    	h.put("txtNamaPerayu",namaPERAYU);

	    	}else{
	    		h.put("txtNamaPerayu",txtNamaPerayu);
	    	}


	    	//send id to model
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_pemohon",id_pemohon);
	    	h.put("id_perayu",id_perayu);
	    	h.put("id_peguam",id_peguam);

	    	//send perayu to model
	    	h.put("txtNoKPBaru",txtNoKPBaru);
	    	h.put("txtNoKPLama",kplama);
	    	h.put("txtNoKPLain",kplain);
	    	h.put("txtAlamatPerayu1",txtAlamatPerayu1);
	    	h.put("txtAlamatPerayu2",txtAlamatPerayu2);
	    	h.put("txtAlamatPerayu3",txtAlamatPerayu3);
	    	h.put("txtPoskodPerayu",txtPoskodPerayu);
	    	h.put("txtBandarPerayu",txtBandarPerayu);
	    	h.put("socNegeriPerayu",socNegeriPerayu);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

	    	//send peguam to model
	    	h.put("txtNamaFirma",txtNamaFirma);
	    	h.put("txtNoRujukan",txtNoRujukan);
	    	h.put("txtAlamatPeguam1",txtAlamatPeguam1);
	    	h.put("txtAlamatPeguam2",txtAlamatPeguam2);
	    	h.put("txtAlamatPeguam3",txtAlamatPeguam3);
	    	h.put("txtPoskodPeguam",txtPoskodPeguam);
	    	h.put("txtBandarPeguam",txtBandarPeguam);
	    	h.put("txtNoTelefon",txtNoTelefon);
	    	h.put("txtNoFaks",txtNoFaks);
	    	h.put("txtEmel",txtEmel);
	    	h.put("socNegeriPeguam",socNegeriPeguam);

	    	if(onchangex.equals("yes")){
	    		h.put("id_ob", txtNamaPerayu);
	    		model2.inserttableOBSek17(h);
	    	}else{
	    		model2.updatetableOBSek17(h);
	    	}

	    	model2.updatePerayuSek17(h);
	    	model2.updatePeguamSek17(h);

	  }//close update Maklumat PP

	private void simpanRekodRayuan(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();

		//get id
    	String id_rayuan = getParam("id_rayuan");

    	//get data
    	String asas_keputusan = getParam("txtAsasKeputusan");
    	String nota_bicara = getParam("txtNotaBicara");
    	String qBorangA = getParam("qBorangA");
    	String qBorangSA = getParam("qBorangSA");
    	String qBorangDDA = getParam("qBorangDDA");
    	String qBorangP = getParam("qBorangP");
    	String q1BorangP = getParam("q1BorangP");
    	String q1BorangA = getParam("q1BorangA");
    	String q1BorangSA = getParam("q1BorangSA");
    	String q1BorangDDA = getParam("q1BorangDDA");
    	
    	String feeLain1 = getParam("textfeeLainHidden");
    	String feeLaina1 = getParam("feeLaina1");
    	String qfeeLain1 = getParam("qfeeLain1");
    	String jumlahfeeLain1 = getParam("jumlahfeeLain1");
    	
    	String feeLain2 = getParam("textfeeLainHidden2");
    	String feeLaina2 = getParam("feeLaina2a");
    	String qfeeLain2 = getParam("qfeeLain2a");
    	String jumlahfeeLain2 = getParam("jumlahfeeLain2");
    	
    	String feeLain3 = getParam("textfeeLainHidden3");
    	String feeLaina3 = getParam("feeLaina3a");
    	String qfeeLain3 = getParam("qfeeLain3a");
    	String jumlahfeeLain3 = getParam("jumlahfeeLain3");
    	
    	String feeLain4 = getParam("textfeeLainHidden4");
    	String feeLaina4 = getParam("feeLaina4a");
    	String qfeeLain4 = getParam("qfeeLain4a");
    	String jumlahfeeLain4 = getParam("jumlahfeeLain4");
    	
    	String feeLain5 = getParam("textfeeLainHidden5");
    	String feeLaina5 = getParam("feeLaina5a");
    	String qfeeLain5 = getParam("qfeeLain5a");
    	String jumlahfeeLain5 = getParam("jumlahfeeLain5");
    	
    	String feeLain6 = getParam("textfeeLainHidden6");
    	String feeLaina6 = getParam("feeLaina6a");
    	String qfeeLain6 = getParam("qfeeLain6a");
    	String jumlahfeeLain6 = getParam("jumlahfeeLain6");
    	
    	String feeLain7 = getParam("textfeeLainHidden7");
    	String feeLaina7 = getParam("feeLaina7a");
    	String qfeeLain7 = getParam("qfeeLain7a");
    	String jumlahfeeLain7 = getParam("jumlahfeeLain7");
    	
    	String feeLain8 = getParam("textfeeLainHidden8");
    	String feeLaina8 = getParam("feeLaina8a");
    	String qfeeLain8 = getParam("qfeeLain8a");
    	String jumlahfeeLain8 = getParam("jumlahfeeLain8");
    	
    	String textLain1 = getParam("textLainHidden");
    	String textLain2 = getParam("textLainHidden2");
    	String textLain3 = getParam("textLainHidden3");
    	String textLain4 = getParam("textLainHidden4");
    	String textLain5 = getParam("textLainHidden5");
    	String textLain6 = getParam("textLainHidden6");
    	String textLain7 = getParam("textLainHidden7");
    	String textLain8 = getParam("textLainHidden8");
    	String jumlahAllfee = getParam("jumlahh");
    	
    	//send id to model
    	h.put("F2LAMPIRAN1",feeLain1);
    	h.put("FLAMPIRAN1",feeLaina1);
    	h.put("Lampiran1", textLain1);
    	h.put("qfeeLain1", qfeeLain1);
    	h.put("qBorangA",qBorangA);
    	h.put("qBorangP", qBorangP);
    	h.put("qBorangDDA", qBorangDDA);
    	h.put("qBorangSA", qBorangSA);
    	
    	h.put("q1BorangA",q1BorangA);
    	h.put("q1BorangP", q1BorangP);
    	h.put("q1BorangDDA", q1BorangDDA);
    	h.put("q1BorangSA", q1BorangSA);
    	h.put("jumlahAllfee" ,jumlahAllfee);
    	
    	h.put("FLAMPIRAN2",feeLaina2);
    	h.put("F2LAMPIRAN2",feeLain2); 	
    	h.put("qfeeLain2", qfeeLain2);
    	h.put("Lampiran2", textLain2);
    	
    	h.put("FLAMPIRAN3",feeLaina3);
    	h.put("F2LAMPIRAN3",feeLain3); 	
    	h.put("qfeeLain3", qfeeLain3);
    	h.put("Lampiran3", textLain3);
    	
    	h.put("FLAMPIRAN4",feeLaina4);
    	h.put("F2LAMPIRAN4",feeLain4); 	
    	h.put("qfeeLain4", qfeeLain4);
    	h.put("Lampiran4", textLain4);
    	
    	h.put("FLAMPIRAN5",feeLaina5);
    	h.put("F2LAMPIRAN5",feeLain5); 	
    	h.put("qfeeLain5", qfeeLain5);
    	h.put("Lampiran5", textLain5);
    	
    	h.put("FLAMPIRAN6",feeLaina6);
    	h.put("F2LAMPIRAN6",feeLain6); 	
    	h.put("qfeeLain6", qfeeLain6);
    	h.put("Lampiran6", textLain6);
    	
    	h.put("FLAMPIRAN7",feeLaina7);
    	h.put("F2LAMPIRAN7",feeLain7); 	
    	h.put("qfeeLain7", qfeeLain7);
    	h.put("Lampiran7", textLain7);
    	
    	h.put("FLAMPIRAN8",feeLaina8);
    	h.put("F2LAMPIRAN8",feeLain8); 	
    	h.put("qfeeLain8", qfeeLain8);
    	h.put("Lampiran8", textLain8);
    	
    	h.put("asas_keputusan",asas_keputusan);
    	h.put("nota_bicara",nota_bicara);
    	h.put("id_rayuan",id_rayuan);
    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
    	h.put("dariseksyen", "17");

    	//delect checkbox
    	model2.cbObDelete(id_rayuan);

    	//checkbox
    	String[] cbsemaks = request.getParameterValues("cbsemaks");

    	if(cbsemaks!=null){
    		for (int i = 0; i < cbsemaks.length; i++) {
    			model2.addSelectedOB(h,cbsemaks[i]);
    		}
    	}
    	model2.simpanRekodRayuan(h);


  }//close simpanRekodRayuan

	public void setupPage(HttpSession session,String action,Vector list) {
		try {

		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");

		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}

	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }

	    Paging paging = new Paging(session,list,itemsPerPage);

		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("listRayuan",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}
	}
	
	private void headerppk_baru(HttpSession session,String id_permohonan,String flag_permohonan,String id_fail,String flag_fail) throws Exception {
		//hashtable maklumat header
		this.context.put("headerppk",mainheader.getHeaderData(session,id_permohonan,flag_permohonan,id_fail,flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan,flag_permohonan,id_fail,flag_fail);		
		this.context.put("list_sub_header",list_sub);
		this.context.put("flag_jenis_vm","ajax");
	}
	private void headerppk_baru_default()
	{
		Hashtable headerppk = null;
		this.context.put("headerppk","");
		this.context.put("list_sub_header","");
		this.context.put("flag_jenis_vm","ajax");
	}

}//close class

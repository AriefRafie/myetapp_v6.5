package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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

public class FrmRynSemakPenerimaan extends AjaxBasedModule{

	static Logger myLogger = Logger.getLogger(FrmRynSemakPenerimaan.class);

	private static final long serialVersionUID = 1L;

	//model name
	FrmRynSek8SemakPenerimaan model = new FrmRynSek8SemakPenerimaan();
	FrmRynSek8Rayuan model2 = new FrmRynSek8Rayuan();
	FrmPrmhnnSek8Notis model3 = new FrmPrmhnnSek8Notis();
	FrmHeaderPpk mainheader = new FrmHeaderPpk();

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public String doTemplate2() throws Exception{

		HttpSession session = request.getSession();

    	String vm = "";

    	String action = getParam("action");
    	headerppk_baru_default();

    	String screenlist = "app/ppk/frmRynSenaraiSemak.jsp";
    	String screen = "app/ppk/frmRynKeputusanRayuanSemakPenerimaan.jsp";

    	String doPost = (String) session.getAttribute("doPost");
    	myLogger.info(" ***** doPost : "+doPost);
    	
    	
    	//vector
    	Vector list = new Vector();
    	Vector listCarian = new Vector();
    	Vector dataPemohon = new Vector();
    	Vector maklumatRayuan = new Vector();
    	Vector dataSemakKR = new Vector();
    	Vector bayaranKR = new Vector();
    	Vector maklumatSerahanPenasihat = new Vector();
    	Vector maklumatSerahanMahkamah = new Vector();
    	Vector listMahkamah = new Vector();
    	Vector detailMahkamah = new Vector();
    	Vector keputusanPermohonan = new Vector();
    	Vector dataNotis = new Vector();
    	Vector perintah = new Vector();

    	//-- 06122009
    	Vector detailPenasihat = new Vector();
    	detailPenasihat.clear();

    	//vector clear
    	perintah.clear();
    	dataNotis.clear();
    	keputusanPermohonan.clear();
    	detailMahkamah.clear();
    	listMahkamah.clear();
    	maklumatSerahanPenasihat.clear();
    	list.clear();
    	listCarian.clear();
    	dataPemohon.clear();
    	maklumatRayuan.clear();
    	dataSemakKR.clear();
    	bayaranKR.clear();
    	maklumatSerahanMahkamah.clear();

    	//flag kembali
    	String flagFromSenaraiFailSek8 = getParam("flagFromSenaraiFailSek8");
    	String flagFromSenaraiPermohonanSek8 = getParam("flagFromSenaraiPermohonanSek8");
    	String flagForView = getParam("flagForView");

    	String usid="";
   		usid=(String)session.getAttribute("_ekptg_user_id");

   		Vector listdepan = new Vector();
   		listdepan.clear();
   		model.setListDefault(usid);
		listdepan = model.getListDefault();

		//-- 04122009
		context.put("totalWordPerkaraRayuMemo","0");
		context.put("totalWordAlasanRayuan","0");

    	String idprmhn = getParam("id_permohonan");

    	//get idsuburusanstatusfail
   		Vector getIdSuburusanstatusfail = new Vector();
    	getIdSuburusanstatusfail.clear();
    	getIdSuburusanstatusfail = model3.getIdSubUrusanStatusFail(idprmhn);
    	String id_suburusanstatusfail = "";
    	if(getIdSuburusanstatusfail.size()!=0){
    		Hashtable idsubf = (Hashtable) getIdSuburusanstatusfail.get(0);
    		id_suburusanstatusfail = idsubf.get("id_suburusanstatusfail").toString();
    	}

    	//get id keputusan permohonan
		keputusanPermohonan = model3.getKeputusanPermohonan(idprmhn);
		String idkp = "";
		if(keputusanPermohonan.size()!=0){
			Hashtable kp = (Hashtable) keputusanPermohonan.get(0);
			idkp = kp.get("id_keputusanpermohonan").toString();
		}

		//--get id perbicaraan by max(id_perbicaraan)
		model3.setListSemakWithData(idkp);
		dataNotis = model3.getListSemakWithData();
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
		myLogger.info("tab3show ::  tab3show-no");
		context.put("tab3show", "no");

    	//command 1
    	String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);

    	if ("semakKeputusanRayuan".equals(submit)){
    		myLogger.info("::[semakKeputusanRayuan] :: ");
    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="0";
            }
            context.put("selectedTab",selectedTab);

            String id_permohonan = "";
            String id_status = "";

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParam("id_status");
    		myLogger.info(":::::::::id_status " +id_status);
    		//get info pemohon
        	model3.setListSemak(id_permohonan,usid);
    		dataPemohon = model3.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_fail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_fail = x.get("idFail").toString();
    		}

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();

    		String id_rayuan = "";
    		String ARayu = "";
    		String PRayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = mr.get("id_rayuan").toString();
    			ARayu = mr.get("alasan_rayuan_memorandum").toString();
        		PRayu = mr.get("perkara_rayu_memorandum").toString();
    		}
    		myLogger.info(":::::::::ARayu:::::" + ARayu);
    		myLogger.info(":::::::::PRayu:::::" + PRayu);
    		//tab 3 validation
    		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
    			myLogger.info(":::::::::tab 3 validation:::::");
    			context.put("tab3show", "yes");
    		}
    		
    		//get data penasihat
    		model.setMaklumatSerahanPenasihat(id_rayuan);
    		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();
    		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

    		//get data mahkamah
    		model.setMaklumatSerahanMahkamah(id_rayuan);
    		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();
    		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_status", id_status);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_rayuan", id_rayuan);
    		context.put("id_fail", id_fail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);
    		//context.put("jenis_mime", jenis_mime);
    		//context.put("content", content);

    		//data & list
    		context.put("dataPemohon", dataPemohon);

    		//form validation
        	context.put("viewformSKR", "no");
        	context.put("editformSKR", "new");
        	context.put("onchangeKpts","no");
        	context.put("mandatory", "no");

        	//reset data
        	context.put("check1", "");
        	context.put("check2", "");
        	context.put("check3", "");
        	context.put("resit", "");
        	context.put("tarikhB", "");
        	context.put("checkxA", "");
			context.put("checkxB", "");
			context.put("check1x","");
			context.put("check2x","");
			context.put("check3x","");
			context.put("resitX", "");
        	context.put("tarikhBX", "");
        	context.put("catatanX", "");
        	context.put("mandatory", "");
        	context.put("checkA", "");
			context.put("checkB", "");
			context.put("catatan", "");

        	if(!id_status.equals("163")){

        		//get checkbox
        		model.setListSemakKR(id_permohonan);
        		dataSemakKR = model.getListSemakKR();

        		//get data bayaran
        		model.setBayaranKR(id_permohonan);
        		bayaranKR = model.getBayaranKR();

        		//get keputusan n catatan
        		//model2.setDataMaklumat(id_permohonan,id_pemohon);
        		model2.setDataMaklumat(id_permohonan,id_perintah);
        		maklumatRayuan = model2.getDataMaklumat();

        		String sorKeputusanPegawai = "";

        		if(maklumatRayuan.size()!=0){
        			Hashtable x = (Hashtable) maklumatRayuan.get(0);
        			sorKeputusanPegawai = x.get("id_keputusanpegawai").toString();
        		}

        		//data & list
        		context.put("dataSemakKR", dataSemakKR);
        		context.put("bayaranKR", bayaranKR);
        		context.put("maklumatRayuan", maklumatRayuan);

        		//form validation
            	context.put("viewformSKR", "yes");
            	context.put("editformSKR", "no");

            	//command 3
            	String submit3 = getParam("command3");
            	myLogger.info("[submit3] :: " +submit3);

            	if ("kemaskiniKeputusanPegawai".equals(submit3)){

            		//form validation
                	context.put("viewformSKR", "yes");
                	context.put("editformSKR", "yes");


                	if(sorKeputusanPegawai.equals("164")){
            			context.put("checkxA", "checked");
            			context.put("checkxB", "");
            			context.put("mandatory", "no");
            		}else if(sorKeputusanPegawai.equals("165")){
            			context.put("checkxA", "");
            			context.put("checkxB", "checked");
            			context.put("mandatory", "yes");
            		}
            		else{
            			context.put("checkxA", "");
            			context.put("checkxB", "");
            			context.put("mandatory", "no");
            		}

            	}//kemaskiniKeputusanPegawai

        	}//close if id_status


    		//command 2
        	String submit2 = getParam("command2");
        	myLogger.info("[submit2] :: " +submit2);

        	if ("updateKeputusanPegawai".equals(submit2)){


         		if (doPost.equals("true")) {

         			updateKeputusanPegawai(session);

         		}

        		id_permohonan = getParam("id_permohonan");

           		usid=(String)session.getAttribute("_ekptg_user_id");

        		//get info pemohon
            	model3.setListSemak(id_permohonan,usid);
        		dataPemohon = model3.getListSemak();
        		headerppk_baru(session,id_permohonan,"Y","","T");

        		String id_pemohon2 = "";
        		String id_Status2 = "";
        		String id_fail2 = "";

        		if(dataPemohon.size()!=0){
        			Hashtable x2 = (Hashtable) dataPemohon.get(0);
        			id_pemohon2 = x2.get("idPemohon").toString();
        			id_Status2 = x2.get("id_Status").toString();
        			id_fail2 = x2.get("idFail").toString();
        		}

        		//get data Maklumat perayu
        		//model2.setDataMaklumat(id_permohonan,id_pemohon);
        		model2.setDataMaklumat(id_permohonan,id_perintah);
        		maklumatRayuan = model2.getDataMaklumat();

        		String id_rayuan2 = "";

        		if(maklumatRayuan.size()!=0){
        			Hashtable mr2 = (Hashtable) maklumatRayuan.get(0);
        			id_rayuan2 = mr2.get("id_rayuan").toString();
        			ARayu = mr2.get("alasan_rayuan_memorandum").toString();
            		PRayu = mr2.get("perkara_rayu_memorandum").toString();
        		}

        		//tab 3 validation
        		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
        			context.put("tab3show", "yes");
        		}

        		//get data penasihat
        		model.setMaklumatSerahanPenasihat(id_rayuan);
        		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();
        		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

        		//get data mahkamah
        		model.setMaklumatSerahanMahkamah(id_rayuan);
        		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();
        		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);


        		//id
        		context.put("id_permohonan", id_permohonan);
        		context.put("id_status", id_Status2);
        		context.put("id_pemohon", id_pemohon2);
        		context.put("id_rayuan", id_rayuan2);
        		context.put("id_fail", id_fail2);
        		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

        		//data & list
        		context.put("dataPemohon", dataPemohon);

        		//get checkbox
        		model.setListSemakKR(id_permohonan);
        		dataSemakKR = model.getListSemakKR();

        		//get data bayaran
        		model.setBayaranKR(id_permohonan);
        		bayaranKR = model.getBayaranKR();

        		//get keputusan n catatan
        		//model2.setDataMaklumat(id_permohonan,id_pemohon);
        		model2.setDataMaklumat(id_permohonan,id_perintah);
        		maklumatRayuan = model2.getDataMaklumat();

        		//data & list
        		context.put("dataSemakKR", dataSemakKR);
        		context.put("bayaranKR", bayaranKR);
        		context.put("maklumatRayuan", maklumatRayuan);

        		//form validation
            	context.put("viewformSKR", "yes");
            	context.put("editformSKR", "no");

        	}//close updateKeputusanPegawai



        	//command 2
        	String onchange = getParam("onchange");
        	myLogger.info("[onchange] :: " +onchange);

        	if ("onclickKeputusanPegawai".equals(onchange)){

        		id_permohonan = getParam("id_permohonan");
        		id_status = getParam("id_status");

        		//id
        		context.put("id_permohonan",id_permohonan);
        		context.put("id_status",id_status);
        		context.put("id_pemohon", id_pemohon);
        		context.put("id_rayuan", id_rayuan);
        		context.put("id_fail", id_fail);
        		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

        		//reset data to zero
        		context.put("check1x","");
        		context.put("check2x","");
        		context.put("check3x","");

        		//validation
        		context.put("onchangeKpts","yes");


        		if(!id_status.equals("163")){
        			context.put("viewformSKR","yes");
        			context.put("editformSKR", "yes");
        		}else{
        			context.put("viewformSKR","no");
        			context.put("editformSKR", "new");
        		}

        		//164 - diteruskan
        		//165 - ditolak

        		String cb1 = getParam("cb1");
        		String cb2 = getParam("cb2");
        		String cb3 = getParam("cb3");


        		String txtNoResit = getParam("txtNoResit");
        		String txdTarikhBayaran = getParam("txdTarikhBayaran");
        		String sorKeputusanPegawai = getParam("sorKeputusanPegawai");
        		String txtCatatanPegawai = getParam("txtCatatanPegawai");

        		if(cb1.equals("true")){
        			context.put("check1x","checked");
        		}else{
        			context.put("check1x","");
        		}
        		if(cb2.equals("true")){
        			context.put("check2x","checked");
        		}else{
        			context.put("check2x","");
        		}
        		if(cb3.equals("true")){
        			context.put("check3x","checked");
        		}else{
        			context.put("check3x","");
        		}

        		if(txtNoResit!=""){
        			context.put("resitX",txtNoResit);
        		}else{
        			context.put("resitX","");
        		}
        		if(txdTarikhBayaran!=""){
        			context.put("tarikhBX",txdTarikhBayaran);
        		}else{
        			context.put("tarikhBX","");
        		}
        		if(txtCatatanPegawai!=""){
        			context.put("catatanX",txtCatatanPegawai);
        		}else{
        			context.put("catatanX","");
        		}

        		if(sorKeputusanPegawai.equals("164")){
        			context.put("checkxA", "checked");
        			context.put("checkxB", "");
        			context.put("mandatory", "no");
        		}else if(sorKeputusanPegawai.equals("165")){
        			context.put("checkxA", "");
        			context.put("checkxB", "checked");
        			context.put("mandatory", "yes");
        		}
        		else{
        			context.put("checkxA", "");
        			context.put("checkxB", "");
        			context.put("mandatory", "no");
        		}


        	}//close onclickKeputusanPegawai

    		vm = screen;

    	}//close semak keputusan rayuan



    //enable keputusan mahkamah


    	else if("keputusanMahkamah".equals(submit)){
    		myLogger.info("*************BACA KEPUTUSAN MAHKAMAH************");
    		String selectedTab = "";
    		selectedTab = getParam("tabId").toString();
	        if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="3";
            }
            context.put("selectedTab",selectedTab);

            String id_permohonan = getParam("id_permohonan");

            usid=(String)session.getAttribute("_ekptg_user_id");

    		//get info pemohon
        	model3.setListSemak(id_permohonan,usid);
    		dataPemohon = model3.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");
    		String id_pemohon = "";
    		String id_fail = "";
    		int id_status = 0;
    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_fail = x.get("idFail").toString();
    			id_status = Integer.parseInt(x.get("id_Status").toString());
    			myLogger.info("*************Read Here************" + id_status);
    		}

    		String ARayu = "";
    		String PRayu = "";

    		//get data Maklumat perayu
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		String id_rayuan = "";
    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = mr.get("id_rayuan").toString();
    			ARayu = mr.get("alasan_rayuan_memorandum").toString();
        		PRayu = mr.get("perkara_rayu_memorandum").toString();
    		}

    		//tab 3 validation
    		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
    			context.put("tab3show", "yes");
    		}

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_status", id_status);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_rayuan", id_rayuan);
    		context.put("id_fail", id_fail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("maklumatRayuan", maklumatRayuan);

    		//reset
    		context.put("catatanMahkamah","");
    		context.put("checkAM","");
    		context.put("checkBM","");
    		context.put("onchangeKM", "no");
    		context.put("showSubTerima","no");

    		//form validation
    		context.put("viewformMK","no");
    		context.put("editformMK","new");

    		//view mode
    		if(id_status!=164 && id_status!=163 && id_status!=165 || id_status==164){
    			myLogger.info("*************Read Here************");
    			//get keputusan n catatan
        		model2.setDataMaklumat(id_permohonan,id_perintah);
        		maklumatRayuan = model2.getDataMaklumat();

        		String sorKeputusanMahkamah = "";

        		if(maklumatRayuan.size()!=0){
        			Hashtable x = (Hashtable) maklumatRayuan.get(0);
        			sorKeputusanMahkamah = x.get("id_keputusanmahkamah").toString();
        		}

        		if(sorKeputusanMahkamah.equals("166") || sorKeputusanMahkamah.equals("180")){
        			context.put("showSubTerima", "yes");
        		}else{
        			context.put("showSubTerima", "no");
        		}

        		//data
        		context.put("maklumatRayuan",maklumatRayuan);

        		//form validation
        		context.put("viewformMK","yes");
        		context.put("editformMK","no");

        		//command 3
            	String submit3 = getParam("command3");
            	myLogger.info("[submit3] :: " +submit3);

            	if("kemaskiniKeputusanMahkamah".equals(submit3)){

            		//form validation
            		context.put("viewformMK","yes");
            		context.put("editformMK","yes");

            	}//close kemaskinikeputusanmahkamah

    		}//close id_status != 164,165,163

            //command 2
        	String submit2 = getParam("command2");
        	myLogger.info("********** [submit2] :: " +submit2);

        	if("updateKeputusanMahkamah".equals(submit2)){
        		String catatanMahkamah = getParam("txtCatatanMahkamah");
        		id_status = Integer.parseInt(getParam("id_status"));
        		
    	    	myLogger.info(":::::catatanMahkamah atas::::" + catatanMahkamah);
    	    	myLogger.info(":::::id_status::::" + id_status);
        		myLogger.info(":::::::::: atas ");
        		//if (doPost.equals("true")) 
        		{
        			myLogger.info(":::::::::: bawah " +submit2);
        			updateKeputusanMahkamah(session,id_perintah);
        		}

           		usid=(String)session.getAttribute("_ekptg_user_id");

        		//get info pemohon
            	model3.setListSemak(id_permohonan,usid);
            	headerppk_baru(session,id_permohonan,"Y","","T");
        		dataPemohon = model3.getListSemak();
        		if(dataPemohon.size()!=0){
        			Hashtable x2 = (Hashtable) dataPemohon.get(0);
        			id_pemohon = x2.get("idPemohon").toString();
        			id_fail = x2.get("idFail").toString();
        			id_status = Integer.parseInt(x2.get("id_Status").toString());
        		}

        		//get keputusan n catatan
        		model2.setDataMaklumat(id_permohonan,id_perintah);
        		maklumatRayuan = model2.getDataMaklumat();

        		//get data penasihat
        		model.setMaklumatSerahanPenasihat(id_rayuan);
        		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();
        		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

        		//get data mahkamah
        		model.setMaklumatSerahanMahkamah(id_rayuan);
        		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();
        		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);

        		String sorKeputusanMahkamah = "";

        		if(maklumatRayuan.size()!=0){
        			Hashtable x = (Hashtable) maklumatRayuan.get(0);
        			sorKeputusanMahkamah = x.get("id_keputusanmahkamah").toString();
        		}

        		if(sorKeputusanMahkamah.equals("166") || sorKeputusanMahkamah.equals("180")){
        			context.put("showSubTerima", "yes");
        		}else{
        			context.put("showSubTerima", "no");
        		}

        		//data
        		context.put("maklumatRayuan",maklumatRayuan);

        		//id
        		context.put("id_permohonan", id_permohonan);
        		context.put("id_status", id_status);
        		context.put("id_rayuan", id_rayuan);
        		context.put("id_pemohon", id_pemohon);
        		context.put("id_fail", id_fail);
        		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

        		//form validation
        		context.put("viewformMK","yes");
        		context.put("editformMK","no");
        		context.put("tab3show","yes");
        		context.put("maklumatSerahanPenasihat", "1");
        		context.put("maklumatSerahanMahkamah", "1");

        		
        	}//close updateKeputusanMahkamah

        	else if("onclickKeputusanMahkamah".equals(submit2)){

        		//get info pemohon
            	model3.setListSemak(id_permohonan,usid);
            	//get dokumen keputusan mahkamah
            	//model3.setDokumenKM(id_permohonan);
            	
        		dataPemohon = model3.getListSemak();
        		headerppk_baru(session,id_permohonan,"Y","","T");
        		if(dataPemohon.size()!=0){
        			Hashtable x2 = (Hashtable) dataPemohon.get(0);
        			id_status = Integer.parseInt(x2.get("id_Status").toString());
        		}

        		//validation
        		context.put("onchangeKM", "yes");

        		//form validation
        		if(id_status==166 || id_status==167 || id_status==180){
        			context.put("viewformMK","yes");
            		context.put("editformMK","yes");
        		}else{
        			context.put("viewformMK","no");
        			context.put("editformMK","new");
        		}


        		int sorKeputusan = 0;
        		sorKeputusan = getParamAsInteger("sorKeputusan");
        		String catatan = getParam("txtCatatanMahkamah");

        		//data
        		context.put("catatanM",catatan);

        		if(sorKeputusan==166){
        			context.put("checkx1", "checked");
        			context.put("checkx2", "");
        			context.put("showSubTerima","yes");
        		}else{
        			context.put("checkx1", "");
        			context.put("checkx2", "checked");
        			context.put("showSubTerima","no");
        		}

        	}//close onclickKeputusanMahkamah

    		vm = screen;

    	}//close keputusan mahkamah


    	else if ("maklumatSerahanK1".equals(submit)){

    		String selectedTab = "";

    		selectedTab = getParam("tabId").toString();

            if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="2";
            }
            context.put("selectedTab",selectedTab);

            String id_permohonan = "";
            int id_status = 0;

            id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");
    		myLogger.info(":::::::::id_status " +id_status);

    		//get info pemohon
        	model3.setListSemak(id_permohonan,usid);
    		dataPemohon = model3.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_negeri = "";
    		String id_fail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_negeri = x.get("pmidnegeri").toString();
    			id_fail = x.get("idFail").toString();
    		}


    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();

    		String id_rayuan = "";
    		String ARayu = "";
    		String PRayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = mr.get("id_rayuan").toString();
    			ARayu = mr.get("alasan_rayuan_memorandum").toString();
        		PRayu = mr.get("perkara_rayu_memorandum").toString();
    		}

    		//tab 3 validation
    		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
    			context.put("tab3show", "yes");
    		}

    		//get data penasihat
    		model.setMaklumatSerahanPenasihat(id_rayuan);
    		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();

    		//get data mahkamah
    		model.setMaklumatSerahanMahkamah(id_rayuan);
    		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();


    		//-- 06122009
    		if(id_negeri!=""){
    			context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
    		}else{
    			context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        	}


    		//get list mahkamah
    		listMahkamah = model.getListMahkamah(id_negeri);

    		//size mahkamah (if=0 gune textfield)
    		context.put("saiz_listM", listMahkamah.size());

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_status", id_status);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_rayuan", id_rayuan);
    		context.put("id_fail", id_fail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		//data & list
    		context.put("dataPemohon", dataPemohon);
    		context.put("listMahkamah", listMahkamah);
    		context.put("maklumatRayuan", maklumatRayuan);
    		context.put("maklumatSerahanPenasihat", maklumatSerahanPenasihat);
    		context.put("maklumatSerahanMahkamah", maklumatSerahanMahkamah);

    		//size
    		context.put("size_P", maklumatSerahanPenasihat.size());
    		context.put("size_M", maklumatSerahanMahkamah.size());

            //dropdown [new form]
    		context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
    		context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,"style=width:300px onchange=onchangeBandarByNegeri()"));
    		context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"style=width:300px"));
    		context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px"));
    		//context.put("selectNamaMahkamah",HTML.SelectMahkamah("socMahkamah",null,"style=width:300px"));


    		//reset data
    		//data p
	    	context.put("tarikhP", "");
	    	context.put("namaP", "");
	    	context.put("alamatP1", "");
	    	context.put("alamatP2", "");
	    	context.put("alamatP3", "");
	    	context.put("poskodP", "");
	    	context.put("bandarP", "");
	    	//data m
	    	context.put("tarikhM", "");
	    	context.put("namaM", "");
	    	context.put("alamatM1", "");
	    	context.put("alamatM2", "");
	    	context.put("alamatM3", "");
	    	context.put("poskodM", "");
	    	context.put("bandarM", "");

            //form validation
    		context.put("viewformSerahank1","no");
    		context.put("editformSerahank1","new");
    		context.put("onchange","no");

    		context.put("id_pejabatCH", "");

    		if(maklumatSerahanPenasihat.size()!=0){

    			String idnegP = "";
    			String idbandarP = "";


    			Hashtable negP = (Hashtable) maklumatSerahanPenasihat.get(0);
    			idnegP = negP.get("id_negeri").toString();
    			idbandarP = negP.get("id_bandar").toString();


    			//form validation
        		context.put("viewformSerahank1","yes");
        		context.put("editformSerahank1","no");

        		//data
        		context.put("maklumatSerahanPenasihat", maklumatSerahanPenasihat);


        		//-- 06122009
        		String idPejabat = "";
        		idPejabat = negP.get("id_pejabat").toString();

        		if(dataPemohon.size()!=0){
        			Hashtable x = (Hashtable) dataPemohon.get(0);
        			id_negeri = x.get("pmidnegeri").toString();
        		}

        		if(id_negeri!=""){
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),"class=disabled disabled style=width:auto",null));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,"class=disabled disabled style=width:auto",null));
        			}
        		}else{
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),"class=disabled disabled style=width:auto",null));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,"class=disabled disabled style=width:auto",null));
        			}
        		}
        		//-- 06122009


        		//dropdown [view form]
        		if(idnegP!=""){
        			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegP),"class=disabled disabled style=width:300px"));
        			if(idbandarP!=""){
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",Utils.parseLong(idbandarP),"style=width:300px class=disabled disabled"));
        			}else{
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",null,"style=width:300px class=disabled disabled "));
            		}
        		}else{
        			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,"class=disabled disabled style=width:300px"));
        			if(idbandarP!=""){
        				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarP),"style=width:300px class=disabled disabled "));
        			}else{
        				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"style=width:300px class=disabled disabled "));
            		}
        		}

        			if(maklumatSerahanMahkamah.size()!=0){

        				String idnegM = "";
        				String idbandarM = "";

        				Hashtable negM = (Hashtable) maklumatSerahanMahkamah.get(0);
            			idnegM = negM.get("id_negeri").toString();
            			idbandarM = negM.get("id_bandar").toString();

        				//data
        				context.put("maklumatSerahanMahkamah", maklumatSerahanMahkamah);

        				//dropdown [view form]
                		if(idnegM!=""){
                			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegM),"class=disabled disabled style=width:300px"));
                			if(idbandarM!=""){
                				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px class=disabled disabled "));
                			}else{
                				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",null,"style=width:300px class=disabled disabled "));
                    		}
                		}else{
                			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,"class=disabled disabled style=width:300px "));
                			if(idbandarM!=""){
                				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px class=disabled disabled "));
                			}else{
                				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px class=disabled disabled "));
                    		}
                		}

        				}//maklumatSerahanMahkamah

        			//command 3
                	String submit3 = getParam("command3");
                	myLogger.info("[submit3] :: " +submit3);

        		if ("kemaskiniMaklumatSerahan".equals(submit3)){

        			//form validation
            		context.put("viewformSerahank1","yes");
            		context.put("editformSerahank1","yes");

            		String idnegM = "";
            		String idbandarM = "";

            		Hashtable negM = (Hashtable) maklumatSerahanMahkamah.get(0);
        			idnegM = negM.get("id_negeri").toString();
        			idbandarM = negM.get("id_bandar").toString();

            		//dropdown [view form]
            		if(idnegM!=""){
            			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegM),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
            			if(idbandarM!=""){
            				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
            			}else{
            				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",null,"style=width:300px"));
            			}
            		}else{
            			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,null,"style=width:300px  onchange=onchangeBandarByNegeri()"));
            			if(idbandarM!=""){
            				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
            			}else{
            				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px "));
                		}
            		}

            		//dropdown [view form]
            		if(idnegP!=""){
            			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegP),null,"style=width:300px  onchange=onchangeBandarByNegeri()"));
            			if(idbandarP!=""){
            				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",Utils.parseLong(idbandarP),"style=width:300px"));
            			}else{
            				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",null,"style=width:300px"));
                		}
            		}else{
            			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,null,"style=width:300px  onchange=onchangeBandarByNegeri()"));
            			if(idbandarP!=""){
            				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarP),"style=width:300px"));
            			}else{
            				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"style=width:300px"));
                		}
            		}


            		//-- 06122009
            		if(id_negeri!=""){
            			if(idPejabat!=""){
            				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
            			}else{
            				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
            			}
            		}else{
            			if(idPejabat!=""){
            				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
            			}else{
            				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
            			}
            		}
            		//-- 06122009

            		//command 4
                	String submit4 = getParam("command4");
                	myLogger.info("[submit4] :: " +submit4);

                	if ("updateMaklumatSerahan".equals(submit4)){

                		//form validation
                		context.put("viewformSerahank1","yes");
                		context.put("editformSerahank1","no");


                 		if (doPost.equals("true")) {

                 			updateMaklumatSerahan(session);

                 		}


                   		usid=(String)session.getAttribute("_ekptg_user_id");

                		//get info pemohon
                    	model3.setListSemak(id_permohonan,usid);
                		dataPemohon = model3.getListSemak();
                		headerppk_baru(session,id_permohonan,"Y","","T");

                		String id_fail2 = "";
                		if(dataPemohon.size()!=0){
                			Hashtable x2 = (Hashtable) dataPemohon.get(0);
                			id_pemohon = x2.get("idPemohon").toString();
                			id_fail2 = x2.get("idFail").toString();
                			id_negeri = x2.get("pmidnegeri").toString();
                		}



                		//get data Maklumat perayu
                		//model2.setDataMaklumat(id_permohonan,id_pemohon);
                		model2.setDataMaklumat(id_permohonan,id_perintah);
                		maklumatRayuan = model2.getDataMaklumat();

                		if(maklumatRayuan.size()!=0){
                			Hashtable mr2 = (Hashtable) maklumatRayuan.get(0);
                			id_rayuan = mr2.get("id_rayuan").toString();
                			ARayu = mr2.get("alasan_rayuan_memorandum").toString();
                    		PRayu = mr2.get("perkara_rayu_memorandum").toString();
                		}

                		//tab 3 validation
                		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
                			context.put("tab3show", "yes");
                		}

                		//get data penasihat
                		model.setMaklumatSerahanPenasihat(id_rayuan);
                		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();
                		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

                		//get data mahkamah
                		model.setMaklumatSerahanMahkamah(id_rayuan);
                		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();
                		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);

                		//id
                		context.put("id_permohonan", id_permohonan);
                		context.put("id_status", id_status);
                		context.put("id_pemohon", id_pemohon);
                		context.put("id_rayuan", id_rayuan);
                		context.put("id_fail", id_fail2);
                		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

                		//data & list
                		context.put("dataPemohon", dataPemohon);

                		//size
                		context.put("size_P", maklumatSerahanPenasihat.size());
                		context.put("size_M", maklumatSerahanMahkamah.size());

                		//form validation
                		context.put("viewformSerahank1","yes");
                		context.put("editformSerahank1","no");
                		context.put("onchange","no");


                		if(maklumatSerahanPenasihat.size()!=0){

                			String idbandarP2 = "";

                			Hashtable negP2 = (Hashtable) maklumatSerahanPenasihat.get(0);
                			idnegP = negP2.get("id_negeri").toString();
                			idbandarP2 = negP2.get("id_bandar").toString();
                			idPejabat = negP2.get("id_pejabat").toString();

                    		//data
                    		context.put("maklumatSerahanPenasihat", maklumatSerahanPenasihat);

                    		//dropdown
                    		if(idnegP!=""){
                    			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegP),"class=disabled disabled style=width:300px"));
                    			if(idbandarP2!=""){
                    				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",Utils.parseLong(idbandarP2)," class=disabled disabled style=width:300px"));
                    			}else{
                    				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",null,"class=disabled disabled style=width:300px"));
                        		}
                    		}else{
                    			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,"class=disabled disabled style=width:300px"));
                    			if(idbandarP2!=""){
                        			context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarP2),"class=disabled disabled style=width:300px"));
                        		}else{
                        			context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"class=disabled disabled style=width:300px"));
                            	}
                    		}


                    		//-- 06122009
                    		if(id_negeri!=""){
                    			if(idPejabat!=""){
                    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),"class=disabled disabled style=width:auto",null));
                    			}else{
                    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,"class=disabled disabled style=width:auto",null));
                    			}
                    		}else{
                    			if(idPejabat!=""){
                    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),"class=disabled disabled style=width:auto",null));
                    			}else{
                    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,"class=disabled disabled style=width:auto",null));
                    			}
                    		}
                    		//-- 06122009



                    			if(maklumatSerahanMahkamah.size()!=0){

                    				String idbandarM2 = "";

                    				Hashtable negM2 = (Hashtable) maklumatSerahanMahkamah.get(0);
                        			idnegM = negM2.get("id_negeri").toString();
                    				idbandarM2 = negM2.get("id_bandar").toString();

                    				//data
                    				context.put("maklumatSerahanMahkamah", maklumatSerahanMahkamah);

                    				//dropdown
                            		if(idnegM!=""){
                            			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegM),"class=disabled disabled style=width:300px"));
                            			if(idbandarM2!=""){
                            				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",Utils.parseLong(idbandarM2),"class=disabled disabled style=width:300px"));
                            			}else{
                            				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",null,"class=disabled disabled style=width:300px"));
                            			}
                            		}else{
                            			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,"class=disabled disabled style=width:300px"));
                            			if(idbandarM2!=""){
                            				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM2),"class=disabled disabled style=width:300px"));
                            			}else{
                            				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"class=disabled disabled  style=width:300px "));
                                		}
                            		}

                    				}//maklumatSerahanMahkamah


                		}//maklumatSerahanPenasihat

                	}//close update maklumat serahan
        		}//close kemaskiniMaklumatSerahan

    		}//maklumatSerahanPenasihat

    		//command 2
        	String submit2 = getParam("command2");
        	myLogger.info("[submit2] :: " +submit2);

        	if ("changeGetAlamatMahkamah".equals(submit2)){

        		if(maklumatSerahanPenasihat.size()!=0){
        			context.put("viewformSerahank1","yes");
            		context.put("editformSerahank1","yes");
        		}else{
        			context.put("viewformSerahank1","no");
            		context.put("editformSerahank1","new");
        		}


        		//onchange validation
        		context.put("onchange","yes");

        		//onchange id
        		String id_namaM = getParam("socMahkamah");

        		//get previous data n set
        		//PENASIHAT
    	    	String tarikhP = getParam("txdTarikhSerahanPenasihat");
    	    	String namaP = getParam("txtNamaPenasihat");
    	    	String alamatP1 = getParam("txtAlamatPenasihat1");
    	    	String alamatP2 = getParam("txtAlamatPenasihat2");
    	    	String alamatP3 = getParam("txtAlamatPenasihat3");
    	    	String poskodP = getParam("txtPoskodPenasihat");
    	    	String idnegeriP = getParam("socNegeriPenasihat");
    	    	String idbandarPz = getParam("socBandarPenasihat");

    	    	//MAHKAMAH

    	    	String tarikhM = getParam("txdTarikhSerahanMahkamah");
    	    	String bandarM = "";
        		String alamatM1 = "";
    	    	String alamatM2 = "";
    	    	String alamatM3 = "";
    	    	String poskodM = "";
    	    	String idnegeriM = "";
    	    	String idbandarM = "";

    	    	//set data back
    	    	//data p
    	    	context.put("tarikhP", tarikhP);
    	    	context.put("namaP", namaP);
    	    	context.put("alamatP1", alamatP1);
    	    	context.put("alamatP2", alamatP2);
    	    	context.put("alamatP3", alamatP3);
    	    	context.put("poskodP", poskodP);

    	    	//data m
    	    	context.put("tarikhM", tarikhM);
    	    	context.put("id_pejabatCH", "");

    	    	//dropdown
    	    	if(idnegeriP!=""){
        			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegeriP),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
        			if(idbandarPz!=""){
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegeriP,"socBandarPenasihat",Utils.parseLong(idbandarPz),"style=width:300px"));
        			}else{
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegeriP,"socBandarPenasihat",null,"style=width:300px"));
            		}
        		}else{
            		context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
            		if(idbandarPz!=""){
            			context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarPz),"style=width:300px"));
            		}else{
            			context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"style=width:300px"));
                	}
            	}

    	    	//-- 06122009
        		String idPejabat = "";
        		idPejabat = getParam("txtNamaPenasihat");

        		if(id_negeri!=""){
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}
        		}else{
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}
        		}
        		//-- 06122009


    	    //get alamat1,2,3 , poskod n negeri by mahkamah
        	if(id_namaM!=""){

    	    	String id_pejabat = "";

        		detailMahkamah = model.getDetailMahkamah(id_namaM);

        		if(detailMahkamah.size()!=0){

        			String C_namaM = "";

        			Hashtable onc = (Hashtable) detailMahkamah.get(0);

        			id_pejabat = onc.get("id_pejabat").toString();
        			alamatM1 = onc.get("alamat1").toString();
        			alamatM2 = onc.get("alamat2").toString();
        			alamatM3 = onc.get("alamat3").toString();
        			poskodM = onc.get("poskod").toString();
        			idnegeriM = onc.get("id_negeri").toString();
        			idbandarM = onc.get("id_bandar").toString();
        			C_namaM = onc.get("nama_pejabat").toString();

        			context.put("id_pejabatCH", id_pejabat);
        	    	context.put("C_namaM", C_namaM);

        		}//close if(detailMahkamah.size()!=0)


        	}//close if get namaM != ""

    	    	context.put("alamatM1", alamatM1);
    	    	context.put("alamatM2", alamatM2);
    	    	context.put("alamatM3", alamatM3);
    	    	context.put("poskodM", poskodM);

    	    	if(idnegeriM!=""){
        			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegeriM),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
        			if(idbandarM!=""){
        				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
        			}else{
        				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",null,"style=width:300px"));
            		}
        		}else{
        			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
        			if(idbandarM!=""){
        				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
        			}else{
        				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px"));
            		}
        		}

        	}//close changeGetAlamatMahkamah


        	//-- 06122009
        	else if ("changeGetAlamatPenasihat".equals(submit2)){

        		//get info pemohon
            	model3.setListSemak(id_permohonan,usid);
        		dataPemohon = model3.getListSemak();
        		headerppk_baru(session,id_permohonan,"Y","","T");

        		if(dataPemohon.size()!=0){
        			Hashtable x = (Hashtable) dataPemohon.get(0);
        			id_negeri = x.get("pmidnegeri").toString();
        		}

        		if(maklumatSerahanPenasihat.size()!=0){
        			context.put("viewformSerahank1","yes");
            		context.put("editformSerahank1","yes");
        		}else{
        			context.put("viewformSerahank1","no");
            		context.put("editformSerahank1","new");
        		}

        		//onchange validation
        		context.put("onchange","yes");


    	    	//MAHKAMAH
    	    	//get data mahkamah
        		String tarikh_serahanM,alamatM1,alamatM2,alamatM3,
        		poskodM,idnegeriM,idbandarM = "";

        		String txtMahkamah = "";

        		txtMahkamah = getParam("txtMahkamah");

        		if(txtMahkamah!=""){
        			context.put("namaM", txtMahkamah);
        		}

        		tarikh_serahanM = getParam("txdTarikhSerahanMahkamah");
        		alamatM1 = getParam("txtAlamatMahkamah1");
        		alamatM2 = getParam("txtAlamatMahkamah2");
        		alamatM3 = getParam("txtAlamatMahkamah3");
        		poskodM = getParam("txtPoskodMahkamah");
        		idnegeriM = getParam("socNegeriMahkamah");
        		idbandarM = getParam("socBandarMahkamah");

        		//put data back [mahkamah]
        		context.put("tarikhM",tarikh_serahanM);
        		context.put("alamatM1",alamatM1);
        		context.put("alamatM2",alamatM2);
        		context.put("alamatM3",alamatM3);
        		context.put("poskodM",poskodM);

        		if(idnegeriM!=""){
        			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegeriM),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
        			if(idbandarM!=""){
        				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
        			}else{
        				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",null,"style=width:300px"));
            		}
        		}else{
        			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
        			if(idbandarM!=""){
        				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
        			}else{
        				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px"));
            		}
        		}


        		//onchange id
        		String id_namaM = getParam("socMahkamah");
        		String id_pejabat = "";

        		if(id_namaM!="" && !id_namaM.equals("0")){

            		detailMahkamah = model.getDetailMahkamah(id_namaM);
            		Hashtable onc = (Hashtable) detailMahkamah.get(0);
            		id_pejabat = onc.get("id_pejabat").toString();

            		if(id_pejabat!=""){

            			String C_namaM = onc.get("nama_pejabat").toString();

            	    	context.put("id_pejabatCH", id_pejabat);
            	    	context.put("C_namaM", C_namaM);

            		}//close if id_pejabat!=""

            	}//close if get namaM != ""


        		//get previous data n set
        		//PENASIHAT
    	    	String tarikhP = getParam("txdTarikhSerahanPenasihat");
    	    	//String namaP = getParam("txtNamaPenasihat");
    	    	String alamatP1 = "";
    	    	String alamatP2 = "";
    	    	String alamatP3 = "";
    	    	String poskodP = "";
    	    	String idnegeriP = "";
    	    	String idbandarPz = "";

    	    	String idPejabat = "";
        		idPejabat = getParam("txtNamaPenasihat");

        		if(idPejabat!=""){
        			detailPenasihat = model.getDetailPenasihat(idPejabat);

        			if(detailPenasihat.size()!=0){

            			Hashtable onc = (Hashtable) detailPenasihat.get(0);

            			alamatP1 = onc.get("alamat1").toString();
            			alamatP2 = onc.get("alamat2").toString();
            			alamatP3 = onc.get("alamat3").toString();
            			poskodP = onc.get("poskod").toString();
            			idnegeriP = onc.get("id_negeri").toString();
            			idbandarPz = onc.get("id_bandar").toString();

        			}//close if(detailPenasihat.size()!=0)
        		}


    	    	//set data back
    	    	//data p
    	    	context.put("tarikhP", tarikhP);
    	    	//context.put("namaP", namaP);
    	    	context.put("alamatP1", alamatP1);
    	    	context.put("alamatP2", alamatP2);
    	    	context.put("alamatP3", alamatP3);
    	    	context.put("poskodP", poskodP);


    	    	//dropdown
    	    	if(idnegeriP!=""){
        			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegeriP),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
        			if(idbandarPz!=""){
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegeriP,"socBandarPenasihat",Utils.parseLong(idbandarPz),"style=width:300px"));
        			}else{
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegeriP,"socBandarPenasihat",null,"style=width:300px"));
            		}
        		}else{
            		context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
            		if(idbandarPz!=""){
            			context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarPz),"style=width:300px"));
            		}else{
            			context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"style=width:300px"));
                	}
            	}


        		if(id_negeri!=""){
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}
        		}else{
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
        			}
        		}


        	}//close changeGetAlamatPenasihat

        	//-- 06122009

    		vm = screen;

    	}//close maklumat serahan k1

    	else if("memorandumRayuan".equals(submit)){

    		String selectedTab = "";
    		selectedTab = getParam("tabId").toString();

	        if (selectedTab == null || "".equals(selectedTab))
            {
            	selectedTab="1";
            }
            context.put("selectedTab",selectedTab);

            String id_permohonan = "";
            int id_status = 0;
            id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model3.setListSemak(id_permohonan,usid);
    		dataPemohon = model3.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");
    		String id_pemohon = "";
    		String id_fail = "";
    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_fail = x.get("idFail").toString();
    		}

    		String ARayu = "";
    		String PRayu = "";

    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		String id_rayuan = "";
    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = mr.get("id_rayuan").toString();
    			ARayu = mr.get("alasan_rayuan_memorandum").toString();
        		PRayu = mr.get("perkara_rayu_memorandum").toString();
    		}

    		//tab 3 validation
    		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
    			context.put("tab3show", "yes");
    		}

    		//get data penasihat
    		model.setMaklumatSerahanPenasihat(id_rayuan);
    		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();

    		//get data mahkamah
    		model.setMaklumatSerahanMahkamah(id_rayuan);
    		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();

    		//data
    		context.put("dataPemohon", dataPemohon);
    		context.put("maklumatRayuan", maklumatRayuan);
    		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);
    		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_status", id_status);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_fail", id_fail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);
    		context.put("id_rayuan", id_rayuan);

    		//new form
    		if(ARayu.isEmpty() && PRayu.isEmpty()){

    			//form validation
    			context.put("newformMR","yes");
    			context.put("editformMR","new");

    			//-- 04122009
    			long totalWordPerkaraRayu = 0;
        		String perkara_rayu = "";

    			if(maklumatRayuan.size()!=0){
    				Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    				perkara_rayu = mr.get("perkara_rayu_memorandum").toString();
    			}
    			if(perkara_rayu!=""){
    				totalWordPerkaraRayu = Utils.wordcount(perkara_rayu);
    			}
    			myLogger.info(":::::totalWordPerkaraRayuMemo1::::");
    			myLogger.info(":::::perkara_rayu::::" + perkara_rayu);
    			context.put("totalWordPerkaraRayuMemo", totalWordPerkaraRayu);
    			//-- 04122009


    		//view form
    		}else{

    			//form validation
    			context.put("newformMR","no");
    			context.put("editformMR","no");

    			//-- 04122009
    			long totalWordPerkaraRayuMemo = 0;
    			long totalWordAlasanRayuan = 0;
        		String perkara_rayu_memo = "";

    			if(maklumatRayuan.size()!=0){
    				Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    				perkara_rayu_memo = mr.get("perkara_rayu_memorandum").toString();
    				ARayu = mr.get("alasan_rayuan_memorandum").toString();
    			}
    			myLogger.info(":::::totalWordPerkaraRayuMemo2::::");
    			if(perkara_rayu_memo!=""){
    				totalWordPerkaraRayuMemo = Utils.wordcount(perkara_rayu_memo);
    			}
    			if(ARayu!=""){
    				totalWordAlasanRayuan = Utils.wordcount(ARayu);
    			}
    			myLogger.info(":::::totalWordPerkaraRayuMemo3::::");
    			context.put("totalWordPerkaraRayuMemo", totalWordPerkaraRayuMemo);
    			context.put("totalWordAlasanRayuan", totalWordAlasanRayuan);
    			//-- 04122009

    			String submit2 = getParam("command2");
            	if ("kemaskiniMemorandum".equals(submit2)){

            		//form validation
        			context.put("newformMR","no");
        			context.put("editformMR","yes");

            	}//close kemaskiniMemorandum

    		}//close if(ARayu.isEmpty() && PRayu.isEmpty())


    		String submit3 = getParam("command3");
        	if ("updateMemorandum".equals(submit3)){

        		id_permohonan = getParam("id_permohonan");
        		id_status = getParamAsInteger("id_status");

         		if (doPost.equals("true")) {
         			updateMemorandum(session);
         		}

         		//get info pemohon
            	model3.setListSemak(id_permohonan,usid);
        		dataPemohon = model3.getListSemak();
        		headerppk_baru(session,id_permohonan,"Y","","T");
        		if(dataPemohon.size()!=0){
        			Hashtable x = (Hashtable) dataPemohon.get(0);
        			id_pemohon = x.get("idPemohon").toString();
        			id_fail = x.get("idFail").toString();
        		}

        		model2.setDataMaklumat(id_permohonan,id_perintah);
        		maklumatRayuan = model2.getDataMaklumat();
        		if(maklumatRayuan.size()!=0){
        			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
        			id_rayuan = mr.get("id_rayuan").toString();
        			ARayu = mr.get("alasan_rayuan_memorandum").toString();
            		PRayu = mr.get("perkara_rayu_memorandum").toString();
        		}

        		//-- 04122009
    			long totalWordPerkaraRayuMemo = 0;
    			long totalWordAlasanRayuan = 0;

    			if(PRayu!=""){
    				myLogger.info(":::::totalWordPerkaraRayuMemo4::::");
    				totalWordPerkaraRayuMemo = Utils.wordcount(PRayu);
    			}
    			if(ARayu!=""){
    				totalWordAlasanRayuan = Utils.wordcount(ARayu);
    			}
    			myLogger.info(":::::totalWordPerkaraRayuMemo5::::");
    			context.put("totalWordPerkaraRayuMemo", totalWordPerkaraRayuMemo);
    			context.put("totalWordAlasanRayuan", totalWordAlasanRayuan);
    			//-- 04122009


        		//tab 3 validation
        		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
        			context.put("tab3show", "yes");
        		}

        		//get data penasihat
        		model.setMaklumatSerahanPenasihat(id_rayuan);
        		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();

        		//get data mahkamah
        		model.setMaklumatSerahanMahkamah(id_rayuan);
        		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();

        		//data
        		context.put("dataPemohon", dataPemohon);
        		context.put("maklumatRayuan", maklumatRayuan);
        		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);
        		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

        		//id
        		context.put("id_permohonan", id_permohonan);
        		context.put("id_status", id_status);
        		context.put("id_pemohon", id_pemohon);
        		context.put("id_fail", id_fail);
        		context.put("id_suburusanstatusfail", id_suburusanstatusfail);
        		context.put("id_rayuan", id_rayuan);

         		//form validation
    			context.put("newformMR","no");
    			context.put("editformMR","no");

        	}//close updateMemorandum

            vm = screen;

    	}//close memorandumRayuan


    	else if ("onchangeBandarByNegeri".equals(submit)){

    		String id_permohonan = "";
            int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");

    		//get info pemohon
        	model3.setListSemak(id_permohonan,usid);
    		dataPemohon = model3.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_negeri = "";
    		String id_fail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_negeri = x.get("pmidnegeri").toString();
    			id_fail = x.get("idFail").toString();
    		}

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();
    		context.put("maklumatRayuan", maklumatRayuan);

    		String id_rayuan = "";
    		String ARayu = "";
    		String PRayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = mr.get("id_rayuan").toString();
    			ARayu = mr.get("alasan_rayuan_memorandum").toString();
        		PRayu = mr.get("perkara_rayu_memorandum").toString();
    		}

    		//tab 3 validation
    		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
    			context.put("tab3show", "yes");
    		}

    		//get list mahkamah
    		listMahkamah = model.getListMahkamah(id_negeri);
    		context.put("listMahkamah", listMahkamah);

    		//size mahkamah (if=0 gune textfield)
    		context.put("saiz_listM", listMahkamah.size());

    		//get data penasihat
    		model.setMaklumatSerahanPenasihat(id_rayuan);
    		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();

    		//get data mahkamah
    		model.setMaklumatSerahanMahkamah(id_rayuan);
    		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();

    		//size
    		context.put("size_P", maklumatSerahanPenasihat.size());
    		context.put("size_M", maklumatSerahanMahkamah.size());

    		//id
    		context.put("id_permohonan",id_permohonan);
    		context.put("id_status",id_status);
    		context.put("id_pemohon",id_pemohon);
    		context.put("id_fail",id_fail);
    		context.put("id_suburusanstatusfail",id_suburusanstatusfail);
    		context.put("id_rayuan",id_rayuan);

    		//get data penasihat
    		String tarikh_serahanP,nama_penasihat,alamatP1,alamatP2,
    		alamatP3,poskodP,idnegeriP,idbandarP = "";

    		//get data mahkamah
    		String tarikh_serahanM,alamatM1,alamatM2,alamatM3,
    		poskodM,idnegeriM,idbandarM = "";

    		String txtMahkamah = "";

    		txtMahkamah = getParam("txtMahkamah");

    		if(txtMahkamah!=""){
    			context.put("namaM", txtMahkamah);
    		}

    		String id_namaM = getParam("socMahkamah");

    		tarikh_serahanP = getParam("txdTarikhSerahanPenasihat");
    		nama_penasihat = getParam("txtNamaPenasihat");
    		alamatP1 = getParam("txtAlamatPenasihat1");
    		alamatP2 = getParam("txtAlamatPenasihat2");
    		alamatP3 = getParam("txtAlamatPenasihat3");
    		poskodP = getParam("txtPoskodPenasihat");
    		idnegeriP = getParam("socNegeriPenasihat");
    		idbandarP = getParam("socBandarPenasihat");

    		tarikh_serahanM = getParam("txdTarikhSerahanMahkamah");
    		alamatM1 = getParam("txtAlamatMahkamah1");
    		alamatM2 = getParam("txtAlamatMahkamah2");
    		alamatM3 = getParam("txtAlamatMahkamah3");
    		poskodM = getParam("txtPoskodMahkamah");
    		idnegeriM = getParam("socNegeriMahkamah");
    		idbandarM = getParam("socBandarMahkamah");

    		//put data back [penasihat]
    		context.put("tarikhP",tarikh_serahanP);
    		context.put("namaP",nama_penasihat);
    		context.put("alamatP1",alamatP1);
    		context.put("alamatP2",alamatP2);
    		context.put("alamatP3",alamatP3);
    		context.put("poskodP",poskodP);

    		//put data back [mahkamah]
    		context.put("tarikhM",tarikh_serahanM);
    		context.put("alamatM1",alamatM1);
    		context.put("alamatM2",alamatM2);
    		context.put("alamatM3",alamatM3);
    		context.put("poskodM",poskodM);

    		//dropdown
    		if(idnegeriP!=""){
    			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegeriP),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
    			if(idbandarP!=""){
    				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegeriP,"socBandarPenasihat",Utils.parseLong(idbandarP),"style=width:300px"));
    			}else{
    				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegeriP,"socBandarPenasihat",null,"style=width:300px"));
        		}
    		}else{
    			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
    			if(idbandarP!=""){
    				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarP),"style=width:300px"));
    			}else{
    				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"style=width:300px"));
        		}
    		}

    		if(idnegeriM!=""){
    			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegeriM),null,"style=width:300px onchange=onchangeBandarByNegeri()"));
    			if(idbandarM!=""){
    				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
    			}else{
    				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegeriM,"socBandarMahkamah",null,"style=width:300px"));
        		}
    		}else{
    			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,null,"style=width:300px onchange=onchangeBandarByNegeri()"));
    			if(idbandarM!=""){
    				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"style=width:300px"));
    			}else{
    				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"style=width:300px"));
        		}
    		}

    		//-- 06122009
    		String idPejabat = "";
    		idPejabat = getParam("txtNamaPenasihat");

    		if(id_negeri!=""){
    			if(idPejabat!=""){
    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
    			}else{
    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
    			}
    		}else{
    			if(idPejabat!=""){
    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
    			}else{
    				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,null,"style=width:auto onchange=changeGetAlamatPenasihat()"));
    			}
    		}
    		//-- 06122009


    		String id_pejabat = "";

    		if(id_namaM!="" && !id_namaM.equals("0")){

        		detailMahkamah = model.getDetailMahkamah(id_namaM);
        		Hashtable onc = (Hashtable) detailMahkamah.get(0);
        		id_pejabat = onc.get("id_pejabat").toString();

        		if(id_pejabat!=""){

        			String C_namaM = onc.get("nama_pejabat").toString();

        	    	context.put("id_pejabatCH", id_pejabat);
        	    	context.put("C_namaM", C_namaM);

        		}//close if id_pejabat!=""

        	}//close if get namaM != ""

    		if(maklumatSerahanPenasihat.size()!= 0){
    			//form validation
    			context.put("viewformSerahank1","yes");
    			context.put("editformSerahank1","yes");
    		}
    		else{
    			//form validation
    			context.put("viewformSerahank1","no");
    			context.put("editformSerahank1","new");
    		}

    		context.put("onchange","yes");

    		vm = screen;

    	}//close onchangeBandarByNegeri

    	else if ("simpanMaklumatSerahanK1".equals(submit)){

    		String id_permohonan = "";
            int id_status = 0;

    		id_permohonan = getParam("id_permohonan");
    		id_status = getParamAsInteger("id_status");


     		if (doPost.equals("true")) {

     			simpanMaklumatSerahanK1(session);

     		}


    		//get info pemohon
        	model3.setListSemak(id_permohonan,usid);
    		dataPemohon = model3.getListSemak();
    		headerppk_baru(session,id_permohonan,"Y","","T");

    		String id_pemohon = "";
    		String id_fail = "";

    		if(dataPemohon.size()!=0){
    			Hashtable x = (Hashtable) dataPemohon.get(0);
    			id_pemohon = x.get("idPemohon").toString();
    			id_fail = x.get("idFail").toString();
    		}

    		//get data Maklumat perayu
    		//model2.setDataMaklumat(id_permohonan,id_pemohon);
    		model2.setDataMaklumat(id_permohonan,id_perintah);
    		maklumatRayuan = model2.getDataMaklumat();

    		String id_rayuan = "";
    		String ARayu = "";
    		String PRayu = "";

    		if(maklumatRayuan.size()!=0){
    			Hashtable mr = (Hashtable) maklumatRayuan.get(0);
    			id_rayuan = mr.get("id_rayuan").toString();
    			ARayu = mr.get("alasan_rayuan_memorandum").toString();
        		PRayu = mr.get("perkara_rayu_memorandum").toString();
    		}

    		//tab 3 validation
    		if(!ARayu.isEmpty() && !PRayu.isEmpty()){
    			context.put("tab3show", "yes");
    		}

    		//get data penasihat
    		model.setMaklumatSerahanPenasihat(id_rayuan);
    		maklumatSerahanPenasihat = model.getMaklumatSerahanPenasihat();
    		context.put("maklumatSerahanPenasihat",maklumatSerahanPenasihat);

    		//get data mahkamah
    		model.setMaklumatSerahanMahkamah(id_rayuan);
    		maklumatSerahanMahkamah = model.getMaklumatSerahanMahkamah();
    		context.put("maklumatSerahanMahkamah",maklumatSerahanMahkamah);

    		//id
    		context.put("id_permohonan", id_permohonan);
    		context.put("id_status", id_status);
    		context.put("id_pemohon", id_pemohon);
    		context.put("id_rayuan", id_rayuan);
    		context.put("id_fail", id_fail);
    		context.put("id_suburusanstatusfail", id_suburusanstatusfail);

    		//data & list
    		context.put("dataPemohon", dataPemohon);

    		//size
    		context.put("size_P", maklumatSerahanPenasihat.size());
    		context.put("size_M", maklumatSerahanMahkamah.size());

    		//form validation
    		context.put("viewformSerahank1","yes");
    		context.put("editformSerahank1","no");
    		context.put("onchange","no");

    		if(maklumatSerahanPenasihat.size()!=0){

    			String idnegP = "";
    			String idbandarP = "";

    			Hashtable negP = (Hashtable) maklumatSerahanPenasihat.get(0);
    			idnegP = negP.get("id_negeri").toString();
    			idbandarP = negP.get("id_bandar").toString();


    			//-- 06122009
    			String idPejabat = "";
        		idPejabat = negP.get("id_pejabat").toString();

        		String id_negeri = "";

        		if(dataPemohon.size()!=0){
        			Hashtable x = (Hashtable) dataPemohon.get(0);
        			id_negeri = x.get("pmidnegeri").toString();
        		}

        		if(id_negeri!=""){
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",Utils.parseLong(idPejabat),"class=disabled disabled style=width:auto",null));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndangByNegeri(id_negeri,"txtNamaPenasihat",null,"class=disabled disabled style=width:auto",null));
        			}
        		}else{
        			if(idPejabat!=""){
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",Utils.parseLong(idPejabat),"class=disabled disabled style=width:auto",null));
        			}else{
        				context.put("selectPejabatPenasihat",HTML.SelectPejabatPenasihatUndangUndang("txtNamaPenasihat",null,"class=disabled disabled style=width:auto",null));
        			}
        		}
        		//-- 06122009


        		//data
        		context.put("maklumatSerahanPenasihat", maklumatSerahanPenasihat);

        		//dropdown
        		if(idnegP!=""){
        			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",Utils.parseLong(idnegP),"class=disabled disabled style=width:300px"));
        			if(idbandarP!=""){
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",Utils.parseLong(idbandarP),"class=disabled disabled style=width:300px"));
        			}else{
        				context.put("selectBandarPenasihat",HTML.SelectBandarByNegeri(idnegP,"socBandarPenasihat",null,"class=disabled disabled style=width:300px"));
            		}
        		}else{
        			context.put("selectNegeriPenasihat",HTML.SelectNegeri("socNegeriPenasihat",null,"class=disabled disabled style=width:300px"));
        			if(idbandarP!=""){
        				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",Utils.parseLong(idbandarP),"class=disabled disabled style=width:300px"));
        			}else{
        				context.put("selectBandarPenasihat",HTML.SelectBandar("socBandarPenasihat",null,"class=disabled disabled style=width:300px"));
            		}
        		}

        			if(maklumatSerahanMahkamah.size()!=0){

        				String idnegM = "";
        				String idbandarM = "";

        				Hashtable negM = (Hashtable) maklumatSerahanMahkamah.get(0);
            			idnegM = negM.get("id_negeri").toString();
            			idbandarM = negM.get("id_bandar").toString();

        				//data
        				context.put("maklumatSerahanMahkamah", maklumatSerahanMahkamah);

        				//dropdown
                		if(idnegM!=""){
                			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",Utils.parseLong(idnegM),"class=disabled disabled style=width:300px"));
                			if(idbandarM!=""){
                				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",Utils.parseLong(idbandarM),"class=disabled disabled style=width:300px"));
                			}else{
                				context.put("selectBandarMahkamah",HTML.SelectBandarByNegeri(idnegM,"socBandarMahkamah",null,"class=disabled disabled  style=width:300px"));
                			}
                		}else{
                			context.put("selectNegeriMahkamah",HTML.SelectNegeri("socNegeriMahkamah",null,"class=disabled disabled style=width:300px"));
                			if(idbandarM!=""){
                				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",Utils.parseLong(idbandarM),"class=disabled disabled  style=width:300px"));
                			}else{
                				context.put("selectBandarMahkamah",HTML.SelectBandar("socBandarMahkamah",null,"class=disabled disabled  style=width:300px "));
                    		}
                		}

        				}//maklumatSerahanMahkamah


    		}//maklumatSerahanPenasihat

    		vm = screen;

    	}
    	else if ("cari".equals(submit)){

    		carianSemakPenerimaan(session);
    		listdepan = model.getListCarianSemakPenerimaan();

			//data & size list carian
			context.put("listRayuan", listdepan);
    		context.put("list_size", listdepan.size());

    		vm = screenlist;

    	}//close cari

    	else{

    		model.setListDefault(usid);
        	listdepan = model.getListDefault();

        	//data & size default list
        	context.put("listRayuan", listdepan);
        	context.put("list_size", listdepan.size());

        	vm = screenlist;
    	}

    	this.context.put("flagFromSenaraiPermohonanSek8", flagFromSenaraiPermohonanSek8);
    	this.context.put("flagFromSenaraiFailSek8", flagFromSenaraiFailSek8);
    	this.context.put("flagForView", flagForView);

    	// Template template = engine.getTemplate(vm);
        // return template;

    	setupPage(session,action,listdepan);
    	
    	myLogger.info("Baca VM:---------------" + vm); 
    	return vm;

	}//close public template

/*
 *
 * 	Method
 *
 */

	//carian
	private void carianSemakPenerimaan(HttpSession session) throws Exception{

		String noFail = getParam("txtNoFail");
		String namaPemohon = getParam("txtPemohon");
		String namaSimati = getParam("txtSimati");
		String icSimati = getParam("txtIcSimati");
		String JenisIc = getParam("jenisIc");
		String usid="";
   		usid=(String)session.getAttribute("_ekptg_user_id");

		model.setCarianFail(noFail,namaPemohon,namaSimati,icSimati,JenisIc,usid);

	}//close carian

	//update Keputusan Pegawai
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateKeputusanPegawai(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_status = getParam("id_status");
	    	String id_rayuan = getParam("id_rayuan");
	    	String id_fail = getParam("id_fail");
	    	String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

	    	String idp = getParam("id_permohonan");

	    	int id_stat = Integer.parseInt(id_status);

	    	//other data
	    	String sorKeputusanP = getParam("sorKeputusanPegawai");
	    	String catatanPegawai = getParam("txtCatatanPegawai");
	    	String no_resit = getParam("txtNoResit");
	    	String tarikh_bayaran = getParam("txdTarikhBayaran");

	    	int id_st = Integer.parseInt(sorKeputusanP);

	    	//send to model
	    	h.put("id_permohonan", id_permohonan);
	    	h.put("id_rayuan", id_rayuan);
	    	h.put("id_fail", id_fail);
	    	h.put("id_suburusanstatusfail", id_suburusanstatusfail);
	    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
	    	h.put("catatanPegawai", catatanPegawai);
	    	h.put("sorKeputusanP", sorKeputusanP);
	    	h.put("id_status", id_status);

	    	//get data bayaran
    		model.setBayaranKR(idp);
    		Vector bayaranKR = model.getBayaranKR();

	    	String id_bayaran = "";

    		if(bayaranKR.size()!=0){
    			Hashtable b = (Hashtable) bayaranKR.get(0);
    			id_bayaran = b.get("id_bayaran").toString();
    		}

    		h.put("id_bayaran", id_bayaran);

	    	//delete data bayaran
//	    	model.deleteBayaran(id_permohonan);

	    	//delete checkbox current value OR no value
	    	model.semakanDelete(id_permohonan);

	    	//update tblppkrayuan
	   	    model.updateKeputusanPegawai(h);


	    	if((id_stat != id_st) && (id_stat != 166 && id_stat != 167 && id_stat != 180)){
	    	//update suburusanstatusfail
	    	model.updateSuburusanSFail(session,h);
	    	}

	    	//update(addnew) checkbox
	    	String[] cbsemaks = this.request.getParameterValues("cbsemaks");
	    	if(cbsemaks!=null){
	    		for (int i = 0; i < cbsemaks.length; i++) {
	    			String noresit = "";
        			String tarikhbayaran = "";

        			if (cbsemaks[i].equals("142")){
        				noresit = no_resit;
        				tarikhbayaran = tarikh_bayaran;
        			}
        			model.updateSemakanKeputusanPegawai(h,cbsemaks[i], String.valueOf(id_permohonan), String.valueOf(noresit), String.valueOf(tarikhbayaran));
        			}
        	}

	  }//close updateKeputusanPegawai

	//update Keputusan Mahkamah
	private void updateKeputusanMahkamah(HttpSession session,String id_perintah) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_status = getParam("id_status");
	    	String id_rayuan = getParam("id_rayuan");
	    	String id_fail = getParam("id_fail");
	    	String id_suburusanstatusfail = getParam("id_suburusanstatusfail");

	    	//other data
	    	String sorKeputusanM = getParam("sorKeputusan");
	    	String catatanMahkamah = getParam("txtCatatanMahkamah");
	    	myLogger.info(":::::catatanMahkamah::::" + catatanMahkamah);
	    	String idKeputusan = "";

	    	if(sorKeputusanM.equals("166")){

	    		String sorTerima = getParam("sorTerima");
	    		if(sorTerima.equals("180")){
	    			idKeputusan = "180"; //lain-lain perintah
	    		}else{
	    			idKeputusan = "166"; //perbicaraan semula
	    			myLogger.info(":::::idKeputusan::::" + idKeputusan);
	    			myLogger.info(":::::id_status::::" + id_status);
	    		}

	    	}else{
	    		idKeputusan = "167"; // rayuan ditolak mahkamah
	    	}

	    	//send to model
	    	h.put("id_permohonan", id_permohonan);
	    	h.put("id_rayuan", id_rayuan);
	    	h.put("id_perintah", id_perintah);
	    	h.put("id_fail", id_fail);
	    	h.put("id_suburusanstatusfail", id_suburusanstatusfail);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
	    	h.put("catatanMahkamah", catatanMahkamah);
	    	h.put("sorKeputusanM", idKeputusan);
	    	h.put("id_status", id_status);

	    	//update tblppkrayuan
	    	model.updateKeputusanMahkamah(session,h);
	    	
	    	
	    	
	    	
	    	//myLogger.info("zxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
	        DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		    if(!isMultipart)
		    {
		    myLogger.info("BUKAN MULTIPART");
		    } 
		    else
		    {
		    myLogger.info("MULTIPART");			    	
		    }
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      String fieldname = item.getFieldName();
		      
		      myLogger.info("fieldname ------- "+fieldname);
		      
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  //saveData(item);
		    	  saveData(item, id_rayuan);
		    	  myLogger.info("item ------- "+item);
		      }
		    }
	    	
	    	


	  }//close updateKeputusanMahkamah

	
	private void saveData(FileItem item, String id_rayuan) throws Exception {
		Db db = null;
	
    try {
    	db = new Db();

    	Connection con = db.getConnection();
    	con.setAutoCommit(false);
    	PreparedStatement ps = con.prepareStatement("UPDATE Tblppkrayuan set content = ?, jenis_Mime = ? WHERE id_rayuan = ? ");		
    	
    	ps.setBinaryStream(1,item.getInputStream(),(int)item.getSize());
    	ps.setString(2,item.getContentType());
    	System.out.println("item.getInputStream = "+ item.getInputStream());
    	System.out.println("item.getSize = "+ item.getSize());
    	System.out.println("item.getContentType = "+ item.getContentType());
    	ps.setString(3,id_rayuan);
    	//ps.setString(4,getParam("id_permohonansimati_atheader"));
    	myLogger.info("Baca SaveData:---------------"); 
    	ps.executeUpdate();	
    	myLogger.info("Baca SaveData 2:---------------"); 
        con.commit();
    } finally {
	      if (db != null) db.close();
    }
}

	
	//simpan serahan k1
	private void simpanMaklumatSerahanK1(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_rayuan = getParam("id_rayuan");
	    	String id_permohonan = getParam("id_permohonan");

	    	//PENASIHAT
	    	String txdTarikhSerahanPenasihat = getParam("txdTarikhSerahanPenasihat");

	    	//-- 06122009
	    	String id_pejabatPenasihat = getParam("txtNamaPenasihat");

	    	String alamatPenasihat1 = getParam("txtAlamatPenasihat1");
	    	String alamatPenasihat2 = getParam("txtAlamatPenasihat2");
	    	String alamatPenasihat3 = getParam("txtAlamatPenasihat3");
	    	String txtPoskodPenasihat = getParam("txtPoskodPenasihat");
	    	//String txtBandarPenasihat = getParam("txtBandarPenasihat");
	    	String idnegeriPenasihat = getParam("socNegeriPenasihat");
	    	String id_bandarP = getParam("socBandarPenasihat");

	    	//MAHKAMAH
	    	String txdTarikhSerahanMahkamah = getParam("txdTarikhSerahanMahkamah");
	    	String txtAlamatMahkamah1 = getParam("txtAlamatMahkamah1");
	    	String txtAlamatMahkamah2 = getParam("txtAlamatMahkamah2");
	    	String txtAlamatMahkamah3 = getParam("txtAlamatMahkamah3");
	    	String txtPoskodMahkamah = getParam("txtPoskodMahkamah");
	    	String socNegeriMahkamah = getParam("socNegeriMahkamah");
	    	String id_bandarM = getParam("socBandarMahkamah");

	    	//NAMA MAHKAMAH GET TEXTFIELD OR DROPDOWN
	    	String socMahkamah = getParam("socMahkamah");
	    	String txtMahkamah = getParam("txtMahkamah");


	    	//-- 06122009
	    	String nama_penasihat = "";
	    	if(id_pejabatPenasihat!=""){
	    		Vector detailPenasihat = model.getDetailPenasihat(id_pejabatPenasihat);

		    	if(detailPenasihat.size()!=0){
	    			Hashtable oncp = (Hashtable) detailPenasihat.get(0);
	    			nama_penasihat = oncp.get("nama_pejabat").toString();
	    		}
	    	}

	    	h.put("id_pejabatPenasihat",id_pejabatPenasihat);

	    	//-- 06122009


	    	if(socMahkamah!=""){

	    		Vector detailMahkamah = model.getDetailMahkamah(socMahkamah);

	    		String nama_mahkamah = "";

	    		if(detailMahkamah.size()!=0){
	    			Hashtable onc = (Hashtable) detailMahkamah.get(0);
	    			nama_mahkamah = onc.get("nama_pejabat").toString();

	    		}

	    		h.put("socMahkamah",nama_mahkamah);
	    		h.put("id_mahkamah", socMahkamah);

	    	}else if(txtMahkamah!=""){
	    		h.put("socMahkamah",txtMahkamah);
	    		h.put("id_mahkamah", "");
	    	}else{
	    		h.put("socMahkamah","");
	    		h.put("id_mahkamah", "");
	    	}

	    	//PENASIHAT
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_rayuan",id_rayuan);
	    	h.put("txdTarikhSerahanPenasihat",txdTarikhSerahanPenasihat);
	    	h.put("txtNamaPenasihat",nama_penasihat);
	    	h.put("alamatPenasihat1",alamatPenasihat1);
	    	h.put("alamatPenasihat2",alamatPenasihat2);
	    	h.put("alamatPenasihat3",alamatPenasihat3);
	    	h.put("txtPoskodPenasihat",txtPoskodPenasihat);
	    	h.put("id_bandarP",id_bandarP);
	    	h.put("idnegeriPenasihat",idnegeriPenasihat);
	    	h.put("id_masuk", session.getAttribute("_ekptg_user_id"));

	    	//MAHKAMAH
	    	h.put("txdTarikhSerahanMahkamah",txdTarikhSerahanMahkamah);
	    	h.put("txtAlamatMahkamah1",txtAlamatMahkamah1);
	    	h.put("txtAlamatMahkamah2",txtAlamatMahkamah2);
	    	h.put("txtAlamatMahkamah3",txtAlamatMahkamah3);
	    	h.put("txtPoskodMahkamah",txtPoskodMahkamah);
	    	h.put("id_bandarM",id_bandarM);
	    	h.put("socNegeriMahkamah",socNegeriMahkamah);


	    	model.simpanMaklumatSerahanK1(h);

	  }//close simpan serahan k1

	//update serahan k1
	private void updateMaklumatSerahan(HttpSession session) throws Exception{

			Hashtable h = new Hashtable();

			//id
	    	String id_rayuan = getParam("id_rayuan");
	    	String id_permohonan = getParam("id_permohonan");
	    	String id_serahanrayuanP = getParam("id_serahanrayuanP");
	    	String id_serahanrayuanM = getParam("id_serahanrayuanM");

	    	//PENASIHAT
	    	String txdTarikhSerahanPenasihat = getParam("txdTarikhSerahanPenasihat");
	    	//String txtNamaPenasihat = getParam("txtNamaPenasihat");

	    	//-- 06122009
	    	String id_pejabatPenasihat = getParam("txtNamaPenasihat");

	    	String alamatPenasihat1 = getParam("txtAlamatPenasihat1");
	    	String alamatPenasihat2 = getParam("txtAlamatPenasihat2");
	    	String alamatPenasihat3 = getParam("txtAlamatPenasihat3");
	    	String txtPoskodPenasihat = getParam("txtPoskodPenasihat");
	    	String id_bandarP = getParam("socBandarPenasihat");
	    	String idnegeriPenasihat = getParam("socNegeriPenasihat");

    		//-- 06122009
    		String nama_penasihat = "";
    		if(id_pejabatPenasihat!=""){
    			Vector detailPenasihat = model.getDetailPenasihat(id_pejabatPenasihat);

    			if(detailPenasihat.size()!=0){
    				Hashtable oncp = (Hashtable) detailPenasihat.get(0);
    				nama_penasihat = oncp.get("nama_pejabat").toString();
    			}
    		}

    		h.put("id_pejabatPenasihat",id_pejabatPenasihat);

    		//-- 06122009


	    	//MAHKAMAH
	    	String txdTarikhSerahanMahkamah = getParam("txdTarikhSerahanMahkamah");
	    	String txtAlamatMahkamah1 = getParam("txtAlamatMahkamah1");
	    	String txtAlamatMahkamah2 = getParam("txtAlamatMahkamah2");
	    	String txtAlamatMahkamah3 = getParam("txtAlamatMahkamah3");
	    	String txtPoskodMahkamah = getParam("txtPoskodMahkamah");
	    	String id_bandarM = getParam("socBandarMahkamah");
	    	String socNegeriMahkamah = getParam("socNegeriMahkamah");

	    	//NAMA MAHKAMAH GET TEXTFIELD OR DROPDOWN
	    	String socMahkamah = getParam("socMahkamah");
	    	String txtMahkamah = getParam("txtMahkamah");

	    	String nm = "";

	    	if(socMahkamah != ""){

	    		int soc = Integer.parseInt(socMahkamah);
	    		if(soc==0){
	    			nm = getParam("nama_M");
	    			h.put("socMahkamah",nm);

	    		}else{

	    			Vector detailMahkamah = model.getDetailMahkamah(socMahkamah);

	    			String nama_mahkamah = "";

	    			if(detailMahkamah.size()!=0){
	    				Hashtable onc = (Hashtable) detailMahkamah.get(0);
	    				nama_mahkamah = onc.get("nama_pejabat").toString();
	    			}

		    		h.put("socMahkamah",nama_mahkamah);
		    		h.put("id_mahkamah", socMahkamah);

	    		}


	    	}else if(txtMahkamah!=""){
	    		h.put("socMahkamah",txtMahkamah);
	    		h.put("id_mahkamah", "");
	    	}

	    	//PENASIHAT
	    	h.put("id_serahanrayuanP",id_serahanrayuanP);
	    	h.put("id_permohonan",id_permohonan);
	    	h.put("id_rayuan",id_rayuan);
	    	h.put("txdTarikhSerahanPenasihat",txdTarikhSerahanPenasihat);
	    	h.put("txtNamaPenasihat",nama_penasihat);
	    	h.put("alamatPenasihat1",alamatPenasihat1);
	    	h.put("alamatPenasihat2",alamatPenasihat2);
	    	h.put("alamatPenasihat3",alamatPenasihat3);
	    	h.put("txtPoskodPenasihat",txtPoskodPenasihat);
	    	h.put("id_bandarP",id_bandarP);
	    	h.put("idnegeriPenasihat",idnegeriPenasihat);
	    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

	    	//MAHKAMAH
	    	h.put("id_serahanrayuanM",id_serahanrayuanM);
	    	h.put("txdTarikhSerahanMahkamah",txdTarikhSerahanMahkamah);
	    	h.put("txtAlamatMahkamah1",txtAlamatMahkamah1);
	    	h.put("txtAlamatMahkamah2",txtAlamatMahkamah2);
	    	h.put("txtAlamatMahkamah3",txtAlamatMahkamah3);
	    	h.put("txtPoskodMahkamah",txtPoskodMahkamah);
	    	h.put("id_bandarM",id_bandarM);
	    	h.put("socNegeriMahkamah",socNegeriMahkamah);


	    	model.updateMaklumatSerahan(h);

	  }//close update serahan k1


	//update memorandum
	private void updateMemorandum(HttpSession session) throws Exception{

		Hashtable h = new Hashtable();

		String id_rayuan = getParam("id_rayuan");

    	String perkara_rayu_memorandum = getParam("txtPerkaraRayu");
    	String alasan_rayuan = getParam("txtAlasanRayuan");
    	String alasan_rayuan_memorandum = getParam("txtAlasanRayuan");
    	//send id to model
    	h.put("perkara_rayu_memorandum",perkara_rayu_memorandum);
    	h.put("alasan_rayuan_memorandum", alasan_rayuan_memorandum);
    	h.put("alasan_rayuan",alasan_rayuan);
    	h.put("id_rayuan",id_rayuan);
    	h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));

    	model2.updateMemorandum(h);

	 }//close update memorandum

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
		myLogger.info(" ***** Read Here Headerppk Baru ******");
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

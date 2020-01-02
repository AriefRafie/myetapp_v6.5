package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraBorangMPBData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatBorangMData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiBorangMPBData;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.PPTHeader;

public class SementaraBorangM extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SementaraBorangM.class);
	
	PPTHeader header = new PPTHeader();
	FrmHakmilikSementaraMaklumatBorangMData borangMData = new FrmHakmilikSementaraMaklumatBorangMData();
	FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
	FrmHakmilikSementaraSenaraiBorangMPBData senaraiPB = new FrmHakmilikSementaraSenaraiBorangMPBData();
	FrmHakmilikSementaraBorangMPBData pbMaster = new FrmHakmilikSementaraBorangMPBData();
	
	// MODEL SEKSYEN 8
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
	public String doTemplate2() throws Exception {

		String vm = "";
		String submit = getParam("command");			
        String action = getParam("action");                          
        String mode = getParam("mode");
        
        myLogger.info("mode :: "+mode);   
        myLogger.info("action :: "+action);  
        myLogger.info("submit :: "+submit);		
        
        String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String cNegeri = "";
    	
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("id_permohonan",id_permohonan);
		context.put("idPermohonan",id_permohonan);
		String idHakmilikPB = getParam("idHakmilikPB");
		context.put("idHakmilikPB",idHakmilikPB);
		String idMahkamah = getParam("socMahkamah");
		String idNegeri = getParam("socNegeri");
		String idProjekNegeri = getParam("idProjekNegeri");
		String idBorangM = getParam("idBorangM");
		context.put("idBorangM",idBorangM);
    	HttpSession session = this.request.getSession();

    	Vector list = null;
    	Vector listAgensi = null;
    	Vector listPB = null;
    	Vector papar_BorangM = null;
    	Vector papar_PerintahMahkamah = null;
    	Vector paparPBMaster = null;
    	Vector paparMahkamah = null;
    	Vector getIdSuburusanstatusfail = new Vector();
    	
    	getIdSuburusanstatusfail.clear();
    	
    	//get user login detail
    	String id_user = (String) session.getAttribute("_ekptg_user_id");
    	userData(id_user);
    	String userIdNeg = userData(id_user);    	
    	
		//paging
    	paging();
    	
    	//anchor
    	String ScreenLocation = getParam("ScreenLocation"); 	
    	context.put("ScreenLocation", ScreenLocation);
    	
		//header
		String id_status = "";
    	header.setDataHeader(id_permohonan);
		Vector dataHeader = header.getDataHeader();
		context.put("dataHeader", dataHeader);
		if(dataHeader.size()!=0){
			Hashtable dh = (Hashtable) dataHeader.get(0);
			id_status = (String)dh.get("id_status");
			
			Vector list_sub = null;
			list_sub = header.listPerjalananFail(id_permohonan);
			this.context.put("list_sub_header", list_sub);
		}
		context.put("id_status",id_status);
		
		myLogger.info("ID STATUS :: "+id_status);
    	
		//get current idsuburusanstatusfail
		String id_suburusanstatusfailppt = "";
		modelUPT.setGetIdSuburusanstatusfail(id_permohonan);
		getIdSuburusanstatusfail = modelUPT.getGetIdSuburusanstatusfail();
		if(getIdSuburusanstatusfail.size()!=0){
			Hashtable idsb = (Hashtable)getIdSuburusanstatusfail.get(0);
			id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
		}
		
    	if("baruBorangM".equals(submit)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSenaraiBorangMPB.jsp";
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			String idAgensi = h.get("id_agensi").toString();
			
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			
			senaraiPB.setSenaraiPB(id_permohonan);
			listPB = senaraiPB.getSenaraiPB();
			context.put("SenaraiPB",listPB);
			
			
    	}
    	else if("tabBorangM".equals(action)) {
    		
		    vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiBorangM.jsp";
		    		    		
		    Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			idProjekNegeri = h.get("id_negeri").toString();
			context.put("idProjekNegeri",idProjekNegeri);
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="") {
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			
			else {
				context.put("idAgensi","-");
			}
			
			pbMaster.setDataPB(idHakmilikPB);
			paparPBMaster = pbMaster.getDataPB();
			Hashtable hP = (Hashtable)paparPBMaster.get(0);
			
			context.put("NAMA_PB",hP.get("NAMA_PB"));
			context.put("NO_PB",hP.get("NO_PB"));
			context.put("JENIS_PB",hP.get("JENIS_PB"));
			context.put("NO_HAKMILIK",hP.get("NO_HAKMILIK"));
			context.put("NO_LOT",hP.get("NO_LOT"));
			context.put("LUAS_LOT",hP.get("LUAS_LOT"));
			
			papar_BorangM = borangMData.papar(idHakmilikPB);
			
			
			if(papar_BorangM.size() == 0) {
				context.put("modeBorangM", "baru");
				context.put("readonlyBorangMA","readonly");
				context.put("disabledBorangMA","disabled");
				context.put("readonlyBorangMB","");
				context.put("disabledBorangMB","");
				
				context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",null, "onChange=\"doChangeMahkamah();\""));
				context.put("ALAMAT1","");
				context.put("ALAMAT2","");
				context.put("ALAMAT3","");
				context.put("POSKOD","");
				context.put("TUJUAN","");
				context.put("PERKARA_RUJUKAN","");
				context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(idNegeri),"class=disabled disabled"));

			}
				
			else {
				context.put("modeBorangM", "papar");
				context.put("readonlyBorangMA","readonly");
				context.put("disabledBorangMA","disabled");
				context.put("readonlyBorangMB","readonly");
				context.put("disabledBorangMB","disabled");
				
				Hashtable hM = (Hashtable)papar_BorangM.get(0);
				context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",Utils.parseLong(hM.get("ID_MAHKAMAH").toString()), "class=disabled disabled"));
				context.put("ALAMAT1",hM.get("ALAMAT1"));
				context.put("ALAMAT2",hM.get("ALAMAT2"));
				context.put("ALAMAT3",hM.get("ALAMAT3"));
				context.put("POSKOD",hM.get("POSKOD"));
				context.put("TUJUAN",hM.get("TUJUAN"));
				context.put("PERKARA_RUJUKAN",hM.get("PERKARA_RUJUKAN"));
				context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(hM.get("ID_NEGERI").toString()),"class=disabled disabled"));
				context.put("idBorangM", hM.get("ID_BORANGM"));
			}
		}
    	else if("doChangeMahkamah".equals(submit)){
    		
		    vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiBorangM.jsp";
		    context.put("modeBorangM", "baru");
			context.put("readonlyBorangMA","readonly");
			context.put("disabledBorangMA","disabled");
			context.put("readonlyBorangMB","");
			context.put("disabledBorangMB","");
			
		    Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			idProjekNegeri = h.get("id_negeri").toString();
			context.put("idProjekNegeri",idProjekNegeri);
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="") {
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			
			else {
				context.put("idAgensi","-");
			}
			
			pbMaster.setDataPB(idHakmilikPB);
			paparPBMaster = pbMaster.getDataPB();
			Hashtable hP = (Hashtable)paparPBMaster.get(0);
			
			context.put("NAMA_PB",hP.get("NAMA_PB"));
			context.put("NO_PB",hP.get("NO_PB"));
			context.put("JENIS_PB",hP.get("JENIS_PB"));
			context.put("NO_HAKMILIK",hP.get("NO_HAKMILIK"));
			context.put("NO_LOT",hP.get("NO_LOT"));
			context.put("LUAS_LOT",hP.get("LUAS_LOT"));
			
			paparMahkamah = borangMData.paparMahkamah(idMahkamah);
			Hashtable hM = (Hashtable)paparMahkamah.get(0);
			
			context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",Utils.parseLong(idMahkamah), "onChange=\"doChangeMahkamah();\""));
			context.put("ALAMAT1", hM.get("ALAMAT1"));
			context.put("ALAMAT2", hM.get("ALAMAT2"));
			context.put("ALAMAT3", hM.get("ALAMAT3"));
			context.put("POSKOD", hM.get("POSKOD"));
			context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(hM.get("ID_NEGERI").toString()),"class=disabled disabled"));
			context.put("TUJUAN",getParam("txtTujuan"));
			context.put("PERKARA_RUJUKAN",getParam("txtPerkaraRujuk"));
    		
    	}
    	else if("doChangeMahkamahUpdate".equals(submit)){
    		
		    vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiBorangM.jsp";
		    context.put("modeBorangM", "kemaskini");
			context.put("readonlyBorangMA","readonly");
			context.put("disabledBorangMA","disabled");
			context.put("readonlyBorangMB","");
			context.put("disabledBorangMB","");
			
		    Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			idProjekNegeri = h.get("id_negeri").toString();
			context.put("idProjekNegeri",idProjekNegeri);
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="") {
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			
			else {
				context.put("idAgensi","-");
			}
			
			pbMaster.setDataPB(idHakmilikPB);
			paparPBMaster = pbMaster.getDataPB();
			Hashtable hP = (Hashtable)paparPBMaster.get(0);
			
			context.put("NAMA_PB",hP.get("NAMA_PB"));
			context.put("NO_PB",hP.get("NO_PB"));
			context.put("JENIS_PB",hP.get("JENIS_PB"));
			context.put("NO_HAKMILIK",hP.get("NO_HAKMILIK"));
			context.put("NO_LOT",hP.get("NO_LOT"));
			context.put("LUAS_LOT",hP.get("LUAS_LOT"));
			
			paparMahkamah = borangMData.paparMahkamah(idMahkamah);
			Hashtable hM = (Hashtable)paparMahkamah.get(0);
			
			context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",Utils.parseLong(idMahkamah), "onChange=\"doChangeMahkamah();\""));
			context.put("ALAMAT1", hM.get("ALAMAT1"));
			context.put("ALAMAT2", hM.get("ALAMAT2"));
			context.put("ALAMAT3", hM.get("ALAMAT3"));
			context.put("POSKOD", hM.get("POSKOD"));
			context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(hM.get("ID_NEGERI").toString()),"class=disabled disabled"));
			
			papar_BorangM = borangMData.papar(idHakmilikPB);
			Hashtable hK = (Hashtable)papar_BorangM.get(0);

			context.put("TUJUAN",hK.get("TUJUAN"));
			context.put("PERKARA_RUJUKAN",hK.get("PERKARA_RUJUKAN"));
    		
    	}
    	else if ("simpanBorangM".equals(action)){
    		
    		String id_BorangM = tambahBorangM(session);
    		context.put("idBorangM",id_BorangM);
    		
    		updateSuburusanStatusFailPPT(session,id_permohonan,id_fail,id_suburusanstatusfailppt);
    		
    		vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiBorangM.jsp";

    		context.put("modeBorangM", "papar");
			context.put("readonlyBorangMA","readonly");
			context.put("disabledBorangMA","disabled");
			context.put("readonlyBorangMB","readonly");
			context.put("disabledBorangMB","disabled");
			
    		papar_BorangM = borangMData.papar(idHakmilikPB);
			Hashtable hM = (Hashtable)papar_BorangM.get(0);
			context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",Utils.parseLong(hM.get("ID_MAHKAMAH").toString()), "class=disabled disabled"));
			context.put("ALAMAT1",hM.get("ALAMAT1"));
			context.put("ALAMAT2",hM.get("ALAMAT2"));
			context.put("ALAMAT3",hM.get("ALAMAT3"));
			context.put("POSKOD",hM.get("POSKOD"));
			context.put("TUJUAN",hM.get("TUJUAN"));
			context.put("PERKARA_RUJUKAN",hM.get("PERKARA_RUJUKAN"));
			context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(hM.get("ID_NEGERI").toString()),"class=disabled disabled"));

			
    	}
    	else if ("kemaskiniBorangM".equals(action)) {
    		
    		vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiBorangM.jsp";

    		context.put("modeBorangM", "kemaskini");
			context.put("readonlyBorangMA","readonly");
			context.put("disabledBorangMA","disabled");
			context.put("readonlyBorangMB","");
			context.put("disabledBorangMB","");
			
    		papar_BorangM = borangMData.papar(idHakmilikPB);
			Hashtable hM = (Hashtable)papar_BorangM.get(0);
    		context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",Utils.parseLong(hM.get("ID_MAHKAMAH").toString()), "onChange=\"doChangeMahkamahUpdate();\""));
			context.put("ALAMAT1",hM.get("ALAMAT1"));
			context.put("ALAMAT2",hM.get("ALAMAT2"));
			context.put("ALAMAT3",hM.get("ALAMAT3"));
			context.put("POSKOD",hM.get("POSKOD"));
			context.put("TUJUAN",hM.get("TUJUAN"));
			context.put("PERKARA_RUJUKAN",hM.get("PERKARA_RUJUKAN"));
			context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(hM.get("ID_NEGERI").toString()),"class=disabled disabled"));

	    }
    	
    	else if("updateBorangM".equals(action))
    	{
    		
    		kemaskiniBorangM(session);
    		vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiBorangM.jsp";

    		
    		context.put("modeBorangM", "papar");
			context.put("readonlyBorangMA","readonly");
			context.put("disabledBorangMA","disabled");
			context.put("readonlyBorangMB","readonly");
			context.put("disabledBorangMB","disabled");
			
    		papar_BorangM = borangMData.papar(idHakmilikPB);
			Hashtable hM = (Hashtable)papar_BorangM.get(0);
    		context.put("SelectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(idProjekNegeri),"socMahkamah",Utils.parseLong(hM.get("ID_MAHKAMAH").toString()), "class=disabled disabled"));
			context.put("ALAMAT1",hM.get("ALAMAT1"));
			context.put("ALAMAT2",hM.get("ALAMAT2"));
			context.put("ALAMAT3",hM.get("ALAMAT3"));
			context.put("POSKOD",hM.get("POSKOD"));
			context.put("TUJUAN",hM.get("TUJUAN"));
			context.put("PERKARA_RUJUKAN",hM.get("PERKARA_RUJUKAN"));
			context.put("SelectNegeri", HTML.SelectNegeri("SelectNegeri",Utils.parseLong(hM.get("ID_NEGERI").toString()),"class=disabled disabled"));

    		
    	}
    	
		else if("tabPerintahMahkamah".equals(action)) {
		    		
		    vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiPerintahMahkamah.jsp";
		    		    		
		    Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
							
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="") {
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			
			else {
				context.put("idAgensi","-");
			}
			
			papar_PerintahMahkamah = borangMData.papar(idHakmilikPB);
			Hashtable hP = (Hashtable)papar_PerintahMahkamah.get(0);
						
			if(hP.get("NO_RUJUKAN_PROSIDING").equals("")) {
				context.put("modePerintah", "baru");
				context.put("readonlyPerintah","");
				context.put("disabledPerintah","");
				
				context.put("KEPUTUSAN_MAHKAMAH","");
				context.put("NO_RUJUKAN_PROSIDING","");
				context.put("PERINTAH_MAHKAMAH","");
				context.put("TARIKH_PERINTAH","");
				context.put("TARIKH_TERIMA_PERINTAH","");
				context.put("PAMPASAN_MAHKAMAH","");
				
			}
				
			else {
				context.put("modePerintah", "papar");
				context.put("readonlyPerintah","readonly");
				context.put("disabledPerintah","disabled");

				context.put("KEPUTUSAN_MAHKAMAH",hP.get("KEPUTUSAN_MAHKAMAH"));
				context.put("NO_RUJUKAN_PROSIDING",hP.get("NO_RUJUKAN_PROSIDING"));
				context.put("PERINTAH_MAHKAMAH",hP.get("PERINTAH_MAHKAMAH"));
				context.put("TARIKH_PERINTAH",hP.get("TARIKH_PERINTAH"));
				context.put("TARIKH_TERIMA_PERINTAH",hP.get("TARIKH_TERIMA_PERINTAH"));
				context.put("PAMPASAN_MAHKAMAH",Utils.format2Decimal(Double.parseDouble(hP.get("PAMPASAN_MAHKAMAH").toString())));
				
			}
		}
		else if ("simpanPerintahMahkamah".equals(action)){
    		
			kemaskiniPerintahMahkamah(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiPerintahMahkamah.jsp";
    		context.put("modePerintah", "papar");
			context.put("readonlyPerintah","readonly");
			context.put("disabledPerintah","disabled");
			
    		papar_PerintahMahkamah = borangMData.papar(idHakmilikPB);
			Hashtable hP = (Hashtable)papar_PerintahMahkamah.get(0);
			context.put("KEPUTUSAN_MAHKAMAH",hP.get("KEPUTUSAN_MAHKAMAH"));
			context.put("NO_RUJUKAN_PROSIDING",hP.get("NO_RUJUKAN_PROSIDING"));
			context.put("PERINTAH_MAHKAMAH",hP.get("PERINTAH_MAHKAMAH"));
			context.put("TARIKH_PERINTAH",hP.get("TARIKH_PERINTAH"));
			context.put("TARIKH_TERIMA_PERINTAH",hP.get("TARIKH_TERIMA_PERINTAH"));
			context.put("PAMPASAN_MAHKAMAH",Utils.format2Decimal(Double.parseDouble(hP.get("PAMPASAN_MAHKAMAH").toString())));
			
    	}
    	else if ("kemaskiniPerintahMahkamah".equals(action)) {
    		
    		vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiPerintahMahkamah.jsp";
    		context.put("modePerintah", "kemaskini");
			context.put("readonlyPerintah","");
			context.put("disabledPerintah","");
			
    		papar_PerintahMahkamah = borangMData.papar(idHakmilikPB);
			Hashtable hP = (Hashtable)papar_PerintahMahkamah.get(0);
			context.put("KEPUTUSAN_MAHKAMAH",hP.get("KEPUTUSAN_MAHKAMAH"));
			context.put("NO_RUJUKAN_PROSIDING",hP.get("NO_RUJUKAN_PROSIDING"));
			context.put("PERINTAH_MAHKAMAH",hP.get("PERINTAH_MAHKAMAH"));
			context.put("TARIKH_PERINTAH",hP.get("TARIKH_PERINTAH"));
			context.put("TARIKH_TERIMA_PERINTAH",hP.get("TARIKH_TERIMA_PERINTAH"));
			context.put("PAMPASAN_MAHKAMAH",hP.get("PAMPASAN_MAHKAMAH"));

	    }
    	
    	else if("updatePerintahMahkamah".equals(action))
    	{	
    		kemaskiniPerintahMahkamah(session);
		    vm = "app/ppt/frmHakmilikSementaraRujPampasanTdkDiSetujuiPerintahMahkamah.jsp";

    		context.put("modePerintah", "papar");
			context.put("readonlyPerintah","readonly");
			context.put("disabledPerintah","disabled");
			
    		papar_PerintahMahkamah = borangMData.papar(idHakmilikPB);
			Hashtable hP = (Hashtable)papar_PerintahMahkamah.get(0);
			context.put("KEPUTUSAN_MAHKAMAH",hP.get("KEPUTUSAN_MAHKAMAH"));
			context.put("NO_RUJUKAN_PROSIDING",hP.get("NO_RUJUKAN_PROSIDING"));
			context.put("PERINTAH_MAHKAMAH",hP.get("PERINTAH_MAHKAMAH"));
			context.put("TARIKH_PERINTAH",hP.get("TARIKH_PERINTAH"));
			context.put("TARIKH_TERIMA_PERINTAH",hP.get("TARIKH_TERIMA_PERINTAH"));
			context.put("PAMPASAN_MAHKAMAH",Utils.format2Decimal(Double.parseDouble(hP.get("PAMPASAN_MAHKAMAH").toString())));

    	}
    	else {
    		vm = "app/ppt/frmHakmilikSementaraSenaraiRujPampasanTdkDiSetujui.jsp";
    		
    		if (!"".equals(getParam("txdTarikh")));
    		tarikhmohon = getParam("txdTarikh");
    	
    		if (!"".equals(getParam("txtNoFail")));
    			nofail = getParam("txtNoFail");
    			
    		if (!"".equals(getParam("txtNoRujJKPTGNegeri")));
    			noJKPTG = getParam("txtNoRujJKPTGNegeri");
    		
    		if(!"".equals(getParam("socStatus")))
    			cStatus = getParam("socStatus");
    		
    		if(!"".equals(getParam("socNegeri")))
    			cStatus = getParam("socNegeri");
    		
    		borangMData.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG, userIdNeg);		
    		list = borangMData.getList();
    		
    		//Hashtable h = (Hashtable) listNegeri.get(0);
    	
    	    context.put("PermohonanList", list);
    	    context.put("list_size", list.size());
    	    context.put("CarianFail", nofail);  
    	    context.put("CarianNoJKPTG",noJKPTG );
    	    context.put("tarikhPermohonan", tarikhmohon);
    	    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    	    context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(cNegeri),"style=width:300px"));
    	    setupPage(session,action,list);
    	}
    	
		return vm;
	}
	
	private String userData(String id_user) throws Exception{

		Vector listUserid = new Vector();
		listUserid.clear();

		modelUPT.setGetUserId(id_user);
	    listUserid = modelUPT.getUserIds();
	    String userIdNeg = "";
	    if(listUserid.size()!=0){
	    	Hashtable t = (Hashtable)listUserid.get(0);
	    	userIdNeg = t.get("idNegeri").toString();
	    }

	    return userIdNeg;

	}//close userData


	// METHOD
	@SuppressWarnings({ "unchecked", "static-access" })
	private void updateSuburusanStatusFailPPT(HttpSession session,String id_permohonan,String id_fail,String id_suburusanstatusfailppt) throws Exception{

		Hashtable h = new Hashtable();

		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_user", session.getAttribute("_ekptg_user_id"));

		//update suburusanstatusfailppt
		modelUPT.updateSuburusanStatusFailPPT(h,id_suburusanstatusfailppt,"1555");

	}//close updateSuburusanStatusFailPPT	
	
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
		this.context.put("PermohonanList",paging.getPage(page));
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
			
		private String tambahBorangM(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idFail = getParam("id_fail");
			String idPermohonan= getParam("id_permohonan");
			
			h.put("idFail",idFail);
			h.put("idPermohonan",idPermohonan);
			h.put("idHakmilikPB",getParam("idHakmilikPB"));
			h.put("idMahkamah",getParam("socMahkamah"));
			h.put("tujuan",getParam("txtTujuan"));
			h.put("perkaraRujuk",getParam("txtPerkaraRujuk"));
			h.put("idMasuk",user_id);
			
			return FrmHakmilikSementaraMaklumatBorangMData.tambah(h);
		
	}
		
		private void kemaskiniBorangM(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("idBorangM", getParam("idBorangM"));
			h.put("idMahkamah", getParam("socMahkamah"));
			h.put("tujuan", getParam("txtTujuan"));
			h.put("perkaraRujuk", getParam("txtPerkaraRujuk"));
			h.put("idKemaskini", (String) session.getAttribute("_ekptg_user_id"));
			borangMData.kemaskini(h);
			
		}
		
		private void kemaskiniPerintahMahkamah(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
		    
			String user_id = (String)session.getAttribute("_ekptg_user_id");
			String idBorangM = getParam("idBorangM");
			
			h.put("idBorangM", getParam("idBorangM"));
			h.put("noRujProsiding",getParam("txtNoRujProsiding"));
			h.put("perintahMahkamah",getParam("txtPerintahMahkamah"));
			h.put("keputusanPerintah",getParam("sorKptsnMahkamah"));
			h.put("tarikhPerintah",getParam("txdTkhPerintah"));			
			h.put("tarikhTerimaPerintah",getParam("txdTkhTrmPerintah"));
			h.put("amaunPampasanMahkamah",getParam("txtAmaunPamMahkamah"));
			h.put("amaunPampasanBantah",getParam("txtAmaunPamBantah"));
			h.put("amaunBezaPampasan",getParam("txtAmaunBezaPam"));
			h.put("idKemaskini", (String) session.getAttribute("_ekptg_user_id"));

			borangMData.kemaskiniPerintah(h);			
		}
		
		private void paging() throws Exception{
			String flagPaging = getParam("paging");
	    	if(flagPaging!=""){
	    		context.put("paging", getParam("paging"));
	    	}else{
	    		context.put("paging", "15");
	    	}
		}//close paging			
		
}

package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;
import ekptg.model.ppk.FrmSenaraiFailSek8ForViewData;
import ekptg.model.ppk.FrmTukaranStatus;
import ekptg.model.ppk.PPKUtilHTML;

/**
 * @author Salnizam 
 *
 */
public class FrmPraPerbicaraan extends AjaxBasedModule{

	private static final long serialVersionUID = 1L;
	FrmHeaderPpk mainheader = new FrmHeaderPpk();
	FrmSenaraiFailSek8ForViewData logic = null;
	static Logger myLogger = Logger.getLogger(FrmSenaraiFailSek8ForView.class);
	HttpSession session = null;
	String action = null;
	String role = null;
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();
	
	@Override
	public String doTemplate2() throws Exception {
	
		session = request.getSession();
		Vector listOB = new Vector();
		Vector listPra = new Vector();
		String bolehsimpan = "";
		Vector alamatTempatBicara = new Vector();
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {		
			bolehsimpan = "yes";
		}
		myLogger.info("bolehsimpan :"+bolehsimpan);
		
		FrmTukaranStatus model = new FrmTukaranStatus();
		FrmSenaraiFailSek8ForViewData logic = new FrmSenaraiFailSek8ForViewData();
		String usid=(String)session.getAttribute("_ekptg_user_id");
		String vm = "app/ppk/frmPraPerbicaraan.jsp";
		role = (String)session.getAttribute("myrole");
		
		
		Hashtable user_name = (Hashtable) getUsername(usid);
		context.put("user_name",(String)user_name.get("USER_NAME"));		
		context.put("usid",usid);
		context.put("role",role);
		
		String submit = getParam("command");
		action = getParam("action");
    	myLogger.info("[submit] :: " +submit);
    	String command2= getParam("command2");
    	String command3= getParam("command3");
    	context.put("ScrollX",getParam("ScrollX"));
    	context.put("ScrollY",getParam("ScrollY"));
    	
    	context.put("listSemak","");
		context.put("listSemak_size","");
		context.put("nofail","");
		context.put("id_permohonan","");
		context.put("id_fail","");
		context.put("id_status","");
		context.put("id_suburusanstatusfail","");
		context.put("keterangan_status", "");
		context.put("seksyen","");
		context.put("id_perintah","");
		context.put("id_keputusanpermohonan","");
		context.put("id_perbicaraan","");
		context.put("id_permohonan","");
		context.put("txtNoFailSub","");
		context.put("list_sub",""); 
		context.put("list_fail","");  
		context.put("cari_sub","");    	
		context.put("view_list_fail","");
		context.put("id_fail_carian","");
		context.put("latest_idstatus","");
		context.put("flag_kebenaran_edit","");		
		context.put("user_id_kebenaran_edit","");
		context.put("id_permohonan_kebenaran","");
		context.put("nama_user_yg_beri_kebenaran","");
		context.put("catatan_kebenaran_edit","");
		context.put("txtNoFailSub_selected","");		
		context.put("catatan_batal","");
		context.put("check_flag_batal","");
		

		
    	Vector list = new Vector();
    	
    	
		if ("cariMainSub".equals(submit)){
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());     		
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","","")); 
    		context.put("view_list_fail","yes"); 
    		context.put("buttonKemaskini", "no");
    		context.put("buttonSimpan", "no");
    
    	}
		
		
		else if("kemaskini".equals(submit)){ 
			modelNotis.setTblppkpraperbicaraan2(getParam("id_fail_carian"));
			listPra = modelNotis.getListPraPerbicaraan();
			if (listPra.size() != 0) {
				this.context.put("edit", "yes");
				this.context.put("buttonKemaskini", "no");
				this.context.put("buttonSimpan", "yes");
			}
				else{
				this.context.put("edit", "no");
				this.context.put("buttonKemaskini", "yes");
				this.context.put("buttonSimpan", "no");
				}
					
			String idPermohonan = getParam("idPermohonan");	
    		context.put("listPra",listPra);
    		context.put("id_fail_carian",getParam("id_fail_carian"));		
    		String id_permohonansimati = getParam("idPermohonanSimati");  
    		String idSimati = getParam("id_Simati");
    		myLogger.info("id_permohonansimati = "+id_permohonansimati);
    		myLogger.info("idSimati = "+idSimati);
			// get list ob
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "8",null);
			listOB = modelNotis.getListSemuaOB();
			context.put("listOB",listOB);
			
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
			//hantar balik ke skrin frmPraPerbicaraan.jsp
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "8",null);
			listOB = modelNotis.getListSemuaOB();
			context.put("listOB",listOB);
    		/*if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}*/
    		context.put("txtNoFailSub",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		this.context.put("proses", "kemaskini");
    		//query semula
    		
    		modelNotis.setTblppkpraperbicaraan(idPermohonan);
    		listPra = modelNotis.getListPraPerbicaraan();
    		context.put("listPra",listPra);
    		context.put("edit", "yes");
		}
		
		else if ("simpan".equals(submit)){
			context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    
    		String id_praperbicaraan = getParam("id_praperbicaraan");    		
    		String id_permohonansimati = getParam("idPermohonanSimati"); 
    		String idPermohonan = getParam("idPermohonan"); 
    		String txtPendeteLite = "";
    		String catatan_keputusan = "";
    		String txtSummaryDistribution = "";
    		this.context.put("proses", "simpan");
    		String idSimati = getParam("id_Simati");
    		
			Hashtable h = new Hashtable();
			h.put("txtMasaBicara", getParam("txtMasaBicara"));
			h.put("socJenisWaktu", getParam("socJenisWaktu"));
			h.put("id_praperbicaraan", id_praperbicaraan);
			h.put("txdTarikhInkuiri", getParam("txdTarikhInkuiri"));
			h.put("socSebab", getParam("socSebab"));
			h.put("txtSebabsebabLain", getParam("txtSebabsebabLain"));			
			h.put("txtCatatan", getParam("txtCatatan"));
			h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
			h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
			h.put("idPermohonan", idPermohonan);
			h.put("alamat_bicara1", getParam("txtAlamatBicara1"));
			h.put("alamat_bicara2", getParam("txtAlamatBicara2"));
			h.put("alamat_bicara3", getParam("txtAlamatBicara3"));
			h.put("poskod_bicara", getParam("txtPoskod"));
			h.put("negeri_bicara", getParam("socNegeriBicara"));
			h.put("socKeputusan", getParam("socKeputusan"));
			txtPendeteLite = getParam("txtPendeteLite");
			txtSummaryDistribution = getParam("txtSummaryDistribution");
			catatan_keputusan ="";
			
			//h.put("txtPendeteLite", getParam("txtPendeteLite"));
			if (!txtPendeteLite.equals(""))
			{
				catatan_keputusan = txtPendeteLite;
			}
			if (!txtSummaryDistribution.equals(""))
			{
				catatan_keputusan = txtSummaryDistribution;
			}
			if ((txtPendeteLite.equals("")) && (txtSummaryDistribution.equals("")))
			{
				catatan_keputusan = "";
			}
			h.put("catatan_keputusan", catatan_keputusan);
			if ("".equals(id_praperbicaraan))
			{
				
				modelNotis.addNotisPraPerbicaraan(h);
			}
			else
			{
				modelNotis.updateNotisPraPerbicaraan(h);
			}
			
			
			//hantar balik ke skrin frmPraPerbicaraan.jsp
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "8",null);
			listOB = modelNotis.getListSemuaOB();
			context.put("listOB",listOB);
    		/*if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}*/
    		context.put("txtNoFailSub",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    		//query semula
    		
    		modelNotis.setTblppkpraperbicaraan(idPermohonan);
    		listPra = modelNotis.getListPraPerbicaraan();
    		context.put("listPra",listPra);
    		context.put("edit", "no");
    		this.context.put("buttonKemaskini", "yes");	
    		this.context.put("buttonSimpan", "no");
		
	}
		else if ("simpanKeputusan".equals(submit)){
			myLogger.info("simpanKeputusan");
			context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    
    		String id_praperbicaraan = getParam("id_praperbicaraan");    		
    		String id_permohonansimati = getParam("idPermohonanSimati"); 
    		String idPermohonan = getParam("idPermohonan"); 
    		this.context.put("proses", "simpan");
    		String idSimati = getParam("id_Simati");
    		String txtPendeteLite = "";
    		String catatan_keputusan = "";
    		String txtSummaryDistribution = "";
    		
			Hashtable h = new Hashtable();
			h.put("txtMasaBicara", getParam("txtMasaBicara"));
			h.put("socJenisWaktu", getParam("socJenisWaktu"));
			h.put("id_praperbicaraan", id_praperbicaraan);
			h.put("txdTarikhInkuiri", getParam("txdTarikhInkuiri"));
			h.put("socSebab", getParam("socSebab"));
			h.put("txtSebabsebabLain", getParam("txtSebabsebabLain"));			
			h.put("txtCatatan", getParam("txtCatatan"));
			h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
			h.put("id_kemaskini", session.getAttribute("_ekptg_user_id"));
			h.put("idPermohonan", idPermohonan);
			h.put("alamat_bicara1", getParam("txtAlamatBicara1"));
			h.put("alamat_bicara2", getParam("txtAlamatBicara2"));
			h.put("alamat_bicara3", getParam("txtAlamatBicara3"));
			h.put("poskod_bicara", getParam("txtPoskod"));
			h.put("negeri_bicara", getParam("socNegeriBicara"));
			h.put("socKeputusan", getParam("socKeputusan"));
			txtPendeteLite = getParam("txtPendeteLite");
			txtSummaryDistribution = getParam("txtSummaryDistribution");
			catatan_keputusan ="";
			//h.put("txtPendeteLite", getParam("txtPendeteLite"));
			if (!txtPendeteLite.equals(""))
			{
				catatan_keputusan = txtPendeteLite;
			}
			if (!txtSummaryDistribution.equals(""))
			{
				catatan_keputusan = txtSummaryDistribution;
			}
			if ((!txtPendeteLite.equals("")) && (!txtSummaryDistribution.equals("")))
			{
				catatan_keputusan = "";
			}
			h.put("catatan_keputusan", catatan_keputusan);
			modelNotis.updateNotisPraPerbicaraan(h);
			
			
			
			//hantar balik ke skrin frmPraPerbicaraan.jsp
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "8",null);
			listOB = modelNotis.getListSemuaOB();
			context.put("listOB",listOB);
    		/*if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}*/
    		context.put("txtNoFailSub",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    		//query semula
    		
    		modelNotis.setTblppkpraperbicaraan(idPermohonan);
    		listPra = modelNotis.getListPraPerbicaraan();
    		context.put("listPra",listPra);
    		context.put("edit", "no");
    		this.context.put("buttonKemaskini", "yes");	
    		this.context.put("buttonSimpan", "no");
		
	}
		else if ("paparSub".equals(submit)){
			//String idPermohonan = getParam("idPermohonan");
			//modelNotis.setTblppkpraperbicaraan(idPermohonan);
    		//listPra = modelNotis.getListPraPerbicaraan();
			modelNotis.setTblppkpraperbicaraan2(getParam("id_fail_carian"));
			listPra = modelNotis.getListPraPerbicaraan();
			if (listPra.size() != 0) {
				this.context.put("edit", "no");
				this.context.put("buttonKemaskini", "yes");
				this.context.put("buttonSimpan", "no");
			}
				else{
				this.context.put("edit", "yes");
				this.context.put("buttonKemaskini", "no");
				this.context.put("buttonSimpan", "yes");
				}
					
			
			
			this.context.put("proses", "paparSub");
    		context.put("listPra",listPra);
			//context.put("edit", "");
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		String id_permohonansimati = getParam("idPermohonanSimati");  
    		String idSimati = getParam("id_Simati");
    		myLogger.info("id_permohonansimati = "+id_permohonansimati);
    		myLogger.info("idSimati = "+idSimati);
			// get list ob
			modelNotis.setListSemuaOB(id_permohonansimati, idSimati, "8",null);
			listOB = modelNotis.getListSemuaOB();
			context.put("listOB",listOB);
    		/*if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}*/
    		context.put("txtNoFailSub",model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		context.put("list_fail",model.search_nofail(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")+"",usid,"","",""));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    		
    		
    		
    		
    	}
		else if ("simpanFlag".equals(submit)){
    		context.put("id_fail_carian",getParam("id_fail_carian"));
    		String txtNoFailSub = getParam("txtNoFailSub");    		
    		context.put("txtNoFailSub", txtNoFailSub.trim());
    		
    		String idp = "";
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN")!=null)
    		{    		 		
    		context.put("ID_PERMOHONAN", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    		idp = model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		myLogger.info("GET IDP :"+idp);
    		}
    		
    		if(bolehsimpan.equals("yes"))
    		{
	    		setFlagKebenaranBicara(session,getParam("id_permohonan_kebenaran"),getParam("user_id_kebenaran_edit"),getParam("flag_kebenaran_edit"),getParam("catatan_batal"));		 	
	    		if(!idp.equals(""))
	    		{
	    		getAllFail(idp,usid); 	
	    		}
    		}
    		
    		if(model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("txtNoFailSub_selected", model.getMainFail(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}    	
    		context.put("list_fail",model.search_nofail(txtNoFailSub.trim(),usid,"","",""));  
    		context.put("cari_sub","yes");    	
    		context.put("view_list_fail","yes");
    		
    		myLogger.info("getParam(id_fail_carian) : "+getParam("id_fail_carian"));
    		list = logic.carianFail_byID(getParam("id_fail_carian"), session);
    		this.context.put("SenaraiFail", list);
    		
    			 	
    			
    		
    		
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("STATUS")!=null)
    		{    		 		
    		context.put("STATUS", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("STATUS")); 
    		}    		
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SEBELUM")!=null)
    		{    		 		
    		context.put("NO_FAIL_SEBELUM", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SEBELUM")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SELEPAS")!=null)
    		{    		 		
    		context.put("NO_FAIL_SELEPAS", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SELEPAS")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN_SEBELUM_BICARA")!=null)
    		{    		 		
    		context.put("ID_PERMOHONAN_SEBELUM_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN_SEBELUM_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN_SELEPAS_BICARA")!=null)
    		{    		 		
    		context.put("ID_PERMOHONAN_SELEPAS_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN_SELEPAS_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("CATATAN_BICARA_SEMULA")!=null)
    		{    		 		
    		context.put("CATATAN_BICARA_SEMULA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("CATATAN_BICARA_SEMULA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("USER_NAME")!=null)
    		{    		 		
    		context.put("USER_NAME", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("USER_NAME")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("USER_ID_BICARA_SEMULA")!=null)
    		{    		 		
    		context.put("USER_ID_BICARA_SEMULA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("USER_ID_BICARA_SEMULA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("FLAG_KEBENARAN_BICARA_SEMULA")!=null)
    		{    		 		
    		context.put("FLAG_KEBENARAN_BICARA_SEMULA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("FLAG_KEBENARAN_BICARA_SEMULA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{    		 		
    		context.put("ID_FAIL", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_FAIL")); 
    		}
    		
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN")!=null)
    		{    		 		
    		context.put("ID_PERMOHONAN", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONAN"));
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL")!=null)
    		{    		 		
    		context.put("NO_FAIL", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_SUBURUSAN")!=null)
    		{    		 		
    		context.put("ID_SUBURUSAN", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_SUBURUSAN")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_STATUS")!=null)
    		{    		 		
    		context.put("ID_STATUS", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_STATUS")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SELEPAS_BICARA")!=null)
    		{    		 		
    		context.put("NO_FAIL_SELEPAS_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SELEPAS_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SEBELUM_BICARA")!=null)
    		{    		 		
    		context.put("NO_FAIL_SEBELUM_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("NO_FAIL_SEBELUM_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONANSIMATI")!=null)
    		{    		 		
    		context.put("ID_PERMOHONANSIMATI", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONANSIMATI")); 
    		}    		
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("SEKSYEN")!=null)
    		{    		 		
    		context.put("SEKSYEN", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("SEKSYEN")); 
    		}
    		
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_STATUS_SEBELUM_BICARA")!=null)
    		{    		 		
    		context.put("ID_STATUS_SEBELUM_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_STATUS_SEBELUM_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("SEKSYEN_SEBELUM_BICARA")!=null)
    		{    		 		
    		context.put("SEKSYEN_SEBELUM_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("SEKSYEN_SEBELUM_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONANSIMATI_SEBELUM")!=null)
    		{    		 		
    		context.put("ID_PERMOHONANSIMATI_SEBELUM", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONANSIMATI_SEBELUM")); 
    		}
    		
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_STATUS_SELEPAS_BICARA")!=null)
    		{    		 		
    		context.put("ID_STATUS_SELEPAS_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_STATUS_SELEPAS_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("SEKSYEN_SELEPAS_BICARA")!=null)
    		{    		 		
    		context.put("SEKSYEN_SELEPAS_BICARA", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("SEKSYEN_SELEPAS_BICARA")); 
    		}
    		if(model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONANSIMATI_SELEPAS")!=null)
    		{    		 		
    		context.put("ID_PERMOHONANSIMATI_SELEPAS", model.getMainFail_bicara_semula(getParam("id_fail_carian")).get("ID_PERMOHONANSIMATI_SELEPAS")); 
    		}
    		
    		String id_permohonan ="";
    		if(model.getMainFail(getParam("id_fail_carian")).get("ID_FAIL")!=null)
    		{
    			id_permohonan = model.getMainFail(getParam("id_fail_carian")).get("ID_PERMOHONAN")+"";
    		}
    		
    		headerppk_baru(session, id_permohonan, "Y", "", "T");
    		
    	} 
		else{
    		
		}
		return vm;
	}
	
	
	public Hashtable getUsername(String user_id) throws Exception {
		
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT USER_NAME FROM USERS WHERE USER_ID = '"+user_id+"'";		
				
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("USER_NAME") == null) {
					h.put("USER_NAME", "");
				} else {
					h.put("USER_NAME", rs.getString("USER_NAME"));
				}				
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	
	
	public void getAllFail(String idPermohonan,String user_id) throws Exception
 	{ 
 	Db db = null;
    String sql = "";
    String sql1 = "";
    try
    {	
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      db = new Db();
      Statement stmt = db.getStatement();

		sql1 = " SELECT 'SEBELUM' AS TYPE ,PSM.ID_PERMOHONANSIMATI,PSM.ID_PERMOHONAN,PP.SEKSYEN,FF.ID_FAIL,PSM.ID_SIMATI,PM.ID_PEMOHON, "+
			" FF.NO_FAIL,PP.NO_SUBJAKET  FROM TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN PP,TBLPFDFAIL FF,TBLPPKPEMOHON PM "+
			" WHERE "+
			" PSM.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.ID_FAIL = FF.ID_FAIL  "+
			" AND PP.ID_PEMOHON = PM.ID_PEMOHON AND "+
			" PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+idPermohonan+"') AND   "+
			" PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN "+
			" FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "+
			" WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND Y.ID_STATUS <> '999' "+
			" AND NVL(Y.NO_SUBJAKET,'0') < (SELECT NVL(NO_SUBJAKET,'0') "+
			" FROM TBLPPKPERMOHONAN WHERE ID_STATUS <> '999' AND  ID_PERMOHONAN = '"+idPermohonan+"')) "+
			" UNION "+
			" SELECT 'CURRENT' AS TYPE ,PSM.ID_PERMOHONANSIMATI,PSM.ID_PERMOHONAN,PP.SEKSYEN,FF.ID_FAIL,PSM.ID_SIMATI,PM.ID_PEMOHON, "+
			" FF.NO_FAIL,PP.NO_SUBJAKET  FROM TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN PP,TBLPFDFAIL FF,TBLPPKPEMOHON PM "+
			" WHERE "+
			" PSM.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.ID_FAIL = FF.ID_FAIL  "+
			" AND PP.ID_PEMOHON = PM.ID_PEMOHON AND "+
			" PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+idPermohonan+"') AND  "+
			" PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN "+
			" FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "+
			" WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND Y.ID_STATUS <> '999' "+
			" AND NVL(Y.NO_SUBJAKET,'0') = (SELECT NVL(NO_SUBJAKET,'0') "+
			" FROM TBLPPKPERMOHONAN WHERE ID_STATUS <> '999' AND  ID_PERMOHONAN = '"+idPermohonan+"')) "+
			" UNION "+
			" SELECT 'SELEPAS' AS TYPE ,PSM.ID_PERMOHONANSIMATI,PSM.ID_PERMOHONAN,PP.SEKSYEN,FF.ID_FAIL,PSM.ID_SIMATI,PM.ID_PEMOHON, "+
			" FF.NO_FAIL,PP.NO_SUBJAKET  FROM TBLPPKPERMOHONANSIMATI PSM,TBLPPKPERMOHONAN PP,TBLPFDFAIL FF,TBLPPKPEMOHON PM "+
			" WHERE "+
			" PSM.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.ID_FAIL = FF.ID_FAIL "+
			" AND PP.ID_PEMOHON = PM.ID_PEMOHON AND "+
			" PSM.ID_SIMATI = (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN = '"+idPermohonan+"') AND  "+
			" PSM.ID_PERMOHONAN IN  (SELECT X.ID_PERMOHONAN "+
			" FROM TBLPPKPERMOHONANSIMATI X,TBLPPKPERMOHONAN Y "+
			" WHERE X.ID_PERMOHONAN = Y.ID_PERMOHONAN AND Y.ID_STATUS <> '999' "+
			" AND NVL(Y.NO_SUBJAKET,'0') > (SELECT NVL(NO_SUBJAKET,'0') "+
			" FROM TBLPPKPERMOHONAN WHERE ID_STATUS <> '999' AND  ID_PERMOHONAN = '"+idPermohonan+"')) "+
			" ORDER BY NO_SUBJAKET";
		
		myLogger.info("CHECK FAIL BY SIMATI :"+sql1);
		
		ResultSet rs = stmt.executeQuery(sql1);
		Hashtable h;
		h = new Hashtable();
		while (rs.next()) {	
		String id_permohonansimati = rs.getString("ID_PERMOHONANSIMATI") == null ? "" : rs.getString("ID_PERMOHONANSIMATI");	
		String id_permohonan = rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN");
		String seksyen = rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN");
		String id_fail = rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL");
		String id_simati = rs.getString("ID_SIMATI") == null ? "" : rs.getString("ID_SIMATI");
		String id_pemohon = rs.getString("ID_PEMOHON") == null ? "" : rs.getString("ID_PEMOHON");
		String no_fail = rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL");
		String no_subjaket = rs.getString("NO_SUBJAKET") == null ? "" : rs.getString("NO_SUBJAKET");
		String type = rs.getString("TYPE") == null ? "" : rs.getString("TYPE");
		
		
		String setTempId = "'10000000000'";
		String setTempId_x = "10000000000"; 
		
		
		myLogger.info("id_fail : "+id_fail);
		
		if(type.equals("SEBELUM") || type.equals("CURRENT"))
		{
		//CREATE FAIL BARU AS HISTORY	
		deleteAllData(session,(Long.parseLong(setTempId_x)+Long.parseLong(id_fail))+"",id_permohonan,seksyen,user_id,setTempId);
		generateFailBaru(seksyen,user_id,id_fail,id_permohonan,id_pemohon,id_simati,id_permohonansimati,session,setTempId);	
		
		}
		if(type.equals("CURRENT"))
		{	 
	    deleteNotisData(session,id_fail,id_permohonan,seksyen,user_id); 
		//DELETE DATA CURRENT FAIL SAMPAI NOTIS	
		}
		if(type.equals("SELEPAS"))
		{
		//SET STATUS HAPUS PADA FAIL2 SELEPAS
		updateHapusFail(session,id_fail);
		}
		
		}

    } finally {
	      if (db != null) db.close();
	    }
	  }

	
	private void setFlagKebenaran(HttpSession session,String id_permohonan,String user_id_kebenaran_edit,String flag_kebenaran_edit,String catatan_kebenaran_edit) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sql1="";		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_PERMOHONAN",id_permohonan);
					if(flag_kebenaran_edit.equals(""))
					{
					r.add("user_id_kebenaran_edit","");
					r.add("catatan_kebenaran_edit","");
					}
					else
					{
					r.add("user_id_kebenaran_edit",user_id_kebenaran_edit);
					r.add("catatan_kebenaran_edit",catatan_kebenaran_edit);
					}
					r.add("flag_kebenaran_edit", flag_kebenaran_edit);				
					sql1 = r.getSQLUpdate("TBLPPKPERMOHONAN");
					myLogger.info("UPDATE FLAG KEBENARAN:"+sql1);
					stmt.executeUpdate(sql1);			
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private void setFlagKebenaranBicara(HttpSession session,String id_permohonan,
			String user_id_bicara_semula,String flag_kebenaran_bicara_semula,
			String catatan_bicara_semula) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sql1="";	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
					r.update("ID_PERMOHONAN",id_permohonan);
					if(flag_kebenaran_bicara_semula.equals(""))
					{
					r.add("user_id_bicara_semula","");
					r.add("catatan_bicara_semula","");
					}
					else
					{
					r.add("user_id_bicara_semula",user_id_bicara_semula);
					r.add("catatan_bicara_semula",catatan_bicara_semula);
					}
					if(!id_permohonan.equals(""))
					{
					r.add("id_permohonan_sebelum_bicara",Long.parseLong("10000000000") + Long.parseLong(id_permohonan));
					}					
					r.add("flag_kebenaran_bicara_semula", flag_kebenaran_bicara_semula);	
					r.add("TARIKH_MOHON_BICARA_SEMULA", r.unquote("sysdate"));
					sql1 = r.getSQLUpdate("TBLPPKPERMOHONAN");
					myLogger.info("UPDATE FLAG KEBENARANs:"+sql1);
					stmt.executeUpdate(sql1);
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	public static Hashtable generateFailBaru(String seksyen,String ekptg_user_id,String id_fail_lama,String id_permohonan_lama,
			String id_pemohon_lama,String id_simati_lama,String id_permohonansimati_lama,HttpSession session,String setTempId)
	throws Exception{
	    Db db = null;
	    Connection conn = null;
	    String getNoFile;
	    String sqlSelect = "";
	    String sqlInsert = "";
	    //long id_fail_baru;
	    //long id_permohonan_baru;
	    Hashtable output = null;
	    try {
	    	  output = new Hashtable();
		      db = new Db();
	          conn = db.getConnection();
	          conn.setAutoCommit(false);
		      Statement stmt = db.getStatement();
		      ResultSet rs;

		    //long id_fail_baru = DB.getNextID(db,"TBLPFDFAIL_SEQ");
		    //id_permohonan_baru = DB.getNextID(db,"TBLPPKPERMOHONAN_SEQ");
		    //long id_pemohon_baru =  DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
		    //long id_peguam_baru =  DB.getNextID(db,"TBLPPKPEGUAM_SEQ");
		    //long id_peguampemohon_baru =  DB.getNextID(db,"TBLPPKPEGUAMPEMOHON_SEQ");
		    //long id_simati_baru = DB.getNextID(db,"TBLPPKSIMATI_SEQ");
		    //long id_permohonansimati_baru = DB.getNextID(db,"TBLPPKPERMOHONANSIMATI_SEQ");
		    //long id_bayaran_baru = DB.getNextID(db,"TBLPPKBAYARAN_SEQ");
		    
//		    long idSemakanHantarBaru = DB.getNextID(db,"TBLSEMAKANHANTAR_SEQ");
//		    long idPermohonanSimatiBaru = DB.getNextID(db,"TBLPPKPERMOHONANSIMATI_SEQ");
//		    long idPemohonBaru =  DB.getNextID(db,"TBLPPKPEMOHON_SEQ");
//		    long idHtaBaru = DB.getNextID(db,"TBLPPKHTA_SEQ");
//		    long idHaBaru = DB.getNextID(db,"TBLPPKHA_SEQ");
//		    long idPeguamPemohonBaru = DB.getNextID(db,"TBLPPKPEGUAMPEMOHON_SEQ");
//		    long idKeputusanPermohonanBaru = DB.getNextID(db,"TBLPPKKEPUTUSANPERMOHONAN_SEQ");

		    /*
		    --TBLPFDFAIL
			--TBLPPKPERMOHONAN
			--TBLPPKPEMOHON
			TBLPPKPEGUAM
			--TBLPPKPEGUAMPEMOHON
			--TBLPPKSIMATI
			--TBLPPKPERMOHONANSIMATI			
			--TBLPPKBAYARAN
			--TBLSEMAKANHANTAR
			
			--TBLPPKOB
			--TBLPPKOBPERMOHONAN
			TBLPPKHUBUNGANOB
			TBLPPKHUBUNGANOBPERMOHONAN
			
			//TBLPPKPENGHUTANG
			
			TBLPPKHTA
			TBLPPKHTAPERMOHONAN
			
			TBLPPKHA			
			TBLPPKHAPERMOHONAN
			
			TBLPPKKEPUTUSANPERMOHONAN 
			TBLPPKPERBICARAAN 
		    */
		    
		   
		      
		     
		    
		   //TBLPFDFAIL
		    sqlInsert = " INSERT INTO TBLPFDFAIL ( "+
		    	" ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, "+
		    	" ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, "+
		    	" TAJUK_FAIL, NO_FAIL, NO_FAIL_ROOT, "+
		    	" ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, "+
		    	" ID_FAHARASAT, FLAG_FAIL, ID_STATUS, "+
		    	" CATATAN, ID_MASUK, TARIKH_MASUK, "+
		    	" ID_KEMASKINI, TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, "+
		    	" ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, "+
		    	" LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "+
		    	" FLAG_VIEW_FILE, ID_SUBSUBURUSAN, ID_AKTIVITI, "+
		    	" ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, "+
		    	" NO_TURUTAN, FLAG_HAPUS_FAIL) ";
		    sqlSelect = " SELECT "+
		    	" "+setTempId+" + ID_FAIL, ID_TARAFKESELAMATAN, ID_SEKSYEN, "+
		    	" ID_URUSAN, ID_SUBURUSAN, TARIKH_DAFTAR_FAIL, "+
		    	" TAJUK_FAIL, NO_FAIL || '(H)', NO_FAIL_ROOT, "+
		    	" ID_LOKASIFAIL, ID_NEGERI, ID_KEMENTERIAN, "+
		    	" ID_FAHARASAT, FLAG_FAIL, ID_STATUS, "+
		    	" CATATAN, '"+ekptg_user_id+"', TARIKH_MASUK, "+
		    	" '"+ekptg_user_id+"', TARIKH_KEMASKINI, TARIKH_TUKAR_TARAF, "+
		    	" ID_DB, NO_PERSERAHAN, FLAG_JENIS_FAIL, "+
		    	" LOKASI_FAIL, FAHARASAT, NAMA_PEGAWAI_SJ, "+
		    	" FLAG_VIEW_FILE, ID_SUBSUBURUSAN, ID_AKTIVITI, "+
		    	" ID_DAERAH, NO_TURUTAN_JLD, NO_TURUTAN_SUBJAKET, "+
		    	" NO_TURUTAN, FLAG_HAPUS_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail_lama+"' ";
		    myLogger.info("TBLPFDFAIL :: "+sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		 

		    
		    
		    
		    
		    sqlSelect = " SELECT COUNT(*) AS CHECK_PEMOHON FROM TBLPPKPEMOHON WHERE ID_PEMOHON " +
				    " IN (SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' ) " +
				    		" AND "+setTempId+" + ID_PEMOHON NOT IN (SELECT  PM.ID_PEMOHON " +
				    		" FROM TBLPPKPEMOHON PM,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PSM " +
				    		" WHERE P.ID_PEMOHON = PM.ID_PEMOHON AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI " +
				    " IN ('"+id_simati_lama+"')) ";
		    myLogger.info("CHECK PEMOHON :"+sqlSelect);
		    
		    ResultSet rs_sqlSelect = stmt.executeQuery(sqlSelect);

			Hashtable h;
			h = new Hashtable();
			Integer check_pemohon = 0;
			while (rs_sqlSelect.next()) {
				if (rs_sqlSelect.getInt("CHECK_PEMOHON") != 0) {
					check_pemohon = rs_sqlSelect.getInt("CHECK_PEMOHON") ;
				}
			}
		    
			myLogger.info("TOTAL CHECK PEMOHON :"+check_pemohon);
			
			//if(check_pemohon==0)
			//{
		    //TBLPPKPEMOHON 
		    sqlInsert = " INSERT INTO TBLPPKPEMOHON ( "+
		    " ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA, JENIS_AGAMA, "+
		    " JENIS_WARGA, ALAMAT_1, ALAMAT_2,ALAMAT_3, BANDAR, ID_BANDAR,POSKOD, NO_HP, NO_TEL, "+
		    " EMEL, NO_FAX, CATATAN,ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI,STATUS_PEGUAM, STATUS_PEMOHON, ALAMAT1_SURAT, "+
		    " ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, "+
		    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, "+
		    " ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA, JENIS_AGAMA, "+
		    " JENIS_WARGA, ALAMAT_1, ALAMAT_2,ALAMAT_3, BANDAR, ID_BANDAR,POSKOD, NO_HP, NO_TEL, "+
		    " EMEL, NO_FAX, CATATAN,ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI,STATUS_PEGUAM, STATUS_PEMOHON, ALAMAT1_SURAT, "+
		    " ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, "+
		    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, "+
		    " ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB FROM TBLPPKPEMOHON WHERE ID_PEMOHON " +
		    " IN (SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' ) " +
		    		" AND "+setTempId+" + ID_PEMOHON NOT IN (SELECT  PM.ID_PEMOHON " +
		    		" FROM TBLPPKPEMOHON PM,TBLPPKPERMOHONAN P,TBLPPKPERMOHONANSIMATI PSM " +
		    		" WHERE P.ID_PEMOHON = PM.ID_PEMOHON AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND PSM.ID_SIMATI " +
		    " IN ("+setTempId+" + '"+id_simati_lama+"')) ";/*
		    sqlSelect = " SELECT "+setTempId+" + ID_PEMOHON, NAMA_PEMOHON, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,UMUR, JANTINA, JENIS_AGAMA, "+
				    " JENIS_WARGA, ALAMAT_1, ALAMAT_2,ALAMAT_3, BANDAR, ID_BANDAR,POSKOD, NO_HP, NO_TEL, "+
				    " EMEL, NO_FAX, CATATAN,ID_TARAFKPTG, ID_SAUDARA, ID_NEGERI,STATUS_PEGUAM, STATUS_PEMOHON, ALAMAT1_SURAT, "+
				    " ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT,POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, "+
				    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, "+
				    " ID_NEGERISURAT, ID_PEMOHONLAMA, ID_ARB FROM TBLPPKPEMOHON WHERE ID_PEMOHON " +
				    " = (SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' ) ";*/
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
			//}
			
		    
			
			//TBLPPKPERMOHONAN
		    sqlInsert = "INSERT INTO TBLPPKPERMOHONAN ( "+
		    	" ID_PERMOHONAN, ID_FAIL, NO_PERMOHONAN, "+ 
		    	" NO_PERSERAHAN, TARIKH_MOHON, BIL_BICARA, "+ 
		    	" JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON, "+
		    	" JUMLAH_HA_TARIKHMATI, JUMLAH_HARTA_TARIKHMOHON, JUMLAH_HARTA_TARIKHMATI, "+ 
		    	" BIDANG_KUASA, FLAG_JENIS_BORANGC, KEPUTUSAN, "+ 
		    	" CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+ 
		    	" ID_NEGERIMHN, ID_DAERAHMHN, ID_STATUS, "+
		    	" SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
		    	" ID_DAERAHAWAL, ID_PEJAWAL, NO_FAIL_AWAL, "+
		    	" BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
		    	" LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
		    	" FLAG_STATUS_PEGUAM, JENIS_FAIL, NILAI_TERDAHULU, "+
		    	" FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE, ID_MASUK, "+
		    	" TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
		    	" FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, "+
		    	" ID_DB, NO_SUBJAKET, ID_NEGERITERTINGGI, "+
		    	" ID_DAERAHTERTINGGI, ID_PERMOHONANTERDAHULU, ID_PEMOHON, "+
		    	" PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, "+
		    	" FLAG_PERMOHONAN, LAIN_TUJUAN, TARIKH_RAYUAN, "+
		    	" ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, "+
		    	" JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, "+
		    	" JUM_HA_TAMBAHAN_TKHMATI, USER_ID_KEBENARAN_EDIT, FLAG_KEBENARAN_EDIT, "+
		    	" CATATAN_KEBENARAN_EDIT, CATATAN_MAKLUMAT_TAMBAHAN, FLAG_KEBENARAN_BICARA_SEMULA, "+
		    	" ID_PERMOHONAN_SEBELUM_BICARA, ID_PERMOHONAN_SELEPAS_BICARA, CATATAN_BICARA_SEMULA, "+
		    	" USER_ID_BICARA_SEMULA) ";
		    sqlSelect = "SELECT "+
		    	" "+setTempId+" + ID_PERMOHONAN, "+setTempId+" + ID_FAIL, NO_PERMOHONAN, "+ 
		    	" NO_PERSERAHAN, TARIKH_MOHON, BIL_BICARA, "+ 
		    	" JUMLAH_HTA_TARIKHMOHON, JUMLAH_HTA_TARIKHMATI, JUMLAH_HA_TARIKHMOHON, "+
		    	" JUMLAH_HA_TARIKHMATI, JUMLAH_HARTA_TARIKHMOHON, JUMLAH_HARTA_TARIKHMATI, "+ 
		    	" BIDANG_KUASA, FLAG_JENIS_BORANGC, KEPUTUSAN, "+ 
		    	" CATATAN, ID_BUKTIMATI, ID_TARAFKPTG, "+ 
		    	" ID_NEGERIMHN, ID_DAERAHMHN, '47', "+
		    	" SEKSYEN, ID_UNITPSKAWAL, ID_NEGERIAWAL, "+
		    	" ID_DAERAHAWAL, ID_PEJAWAL, NO_FAIL_AWAL, "+
		    	" BATAL_KUASA_PENTADBIR, LANTIK_PENTADBIR, BATAL_P_AMANAH, "+
		    	" LANTIK_P_AMANAH, HARTA_TINGGAL, NAMA_PEMOHON_AWAL, "+
		    	" FLAG_STATUS_PEGUAM, JENIS_FAIL, NILAI_TERDAHULU, "+
		    	" FLAG_JENIS_PERMOHONAN, NO_PERMOHONAN_ONLINE, '"+ekptg_user_id+"', "+
		    	" TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, "+
		    	" FLAG_HUBUNGAN_PEMOHON, ID_HUBUNGANPEMOHON, TARIKH_MOHON_ONLINE, "+
		    	" ID_DB, NO_SUBJAKET, ID_NEGERITERTINGGI, "+
		    	" ID_DAERAHTERTINGGI, ID_PERMOHONANTERDAHULU,"+setTempId+" + ID_PEMOHON, "+
		    	" PERINTAH_LAMA, PERINTAH_BARU, CATATAN_PERINTAH, "+
		    	" FLAG_PERMOHONAN, LAIN_TUJUAN, TARIKH_RAYUAN, "+
		    	" ID_ARB, JUM_HARTA_TAMBAHAN_TKHMOHON, JUM_HARTA_TAMBAHAN_TKHMATI, "+
		    	" JUM_HTA_TAMBAHAN_TKHMOHON, JUM_HTA_TAMBAHAN_TKHMATI, JUM_HA_TAMBAHAN_TKHMOHON, "+
		    	" JUM_HA_TAMBAHAN_TKHMATI, USER_ID_KEBENARAN_EDIT, FLAG_KEBENARAN_EDIT, "+
		    	" CATATAN_KEBENARAN_EDIT, CATATAN_MAKLUMAT_TAMBAHAN, FLAG_KEBENARAN_BICARA_SEMULA, "+
		    	" '', '"+id_permohonan_lama+"', CATATAN_BICARA_SEMULA, "+
		    	" USER_ID_BICARA_SEMULA FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    //TBLPPKPEGUAMPEMOHON
		    sqlInsert = " INSERT INTO TBLPPKPEGUAMPEMOHON ( ID_PEGUAMPEMOHON, ID_PEGUAM, ID_PEMOHON, "+
		    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI) ";
		    sqlSelect = "SELECT "+setTempId+" + ID_PEGUAMPEMOHON, ID_PEGUAM, "+setTempId+" + ID_PEMOHON,'"+ekptg_user_id+"', TARIKH_MASUK, " +
		    		" '"+ekptg_user_id+"',TARIKH_KEMASKINI" +
		    		" FROM TBLPPKPEGUAMPEMOHON WHERE ID_PEMOHON = '"+id_pemohon_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    if(seksyen.equals("8"))
		    {
		    //TBLPPKSIMATI
		    sqlInsert = " INSERT INTO TBLPPKSIMATI ( "+
		    " ID_SIMATI, NAMA_SIMATI, NAMA_LAIN, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, "+
		    " NO_KP_LAIN, UMUR, JANTINA, NO_SIJIL_MATI, TEMPAT_MATI, ALAMAT_1, "+
		    " ALAMAT_2, ALAMAT_3, BANDAR, POSKOD, TARIKH_MATI, WAKTU_KEMATIAN, "+
		    " JENIS_WAKTU_MATI, SEBAB_MATI, CATATAN, ID_NEGERI, ID_BUKTIMATI, JENIS_AGAMA, "+
		    " JENIS_WARGA, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, "+
		    " ID_BANDAR, ID_SIMATILAMA) ";
		    sqlSelect = " SELECT "+
		    " "+setTempId+" + ID_SIMATI, NAMA_SIMATI, NAMA_LAIN, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, "+
		    " NO_KP_LAIN, UMUR, JANTINA, NO_SIJIL_MATI, TEMPAT_MATI, ALAMAT_1, "+
		    " ALAMAT_2, ALAMAT_3, BANDAR, POSKOD, TARIKH_MATI, WAKTU_KEMATIAN, "+
		    " JENIS_WAKTU_MATI, SEBAB_MATI, CATATAN, ID_NEGERI, ID_BUKTIMATI, JENIS_AGAMA, "+
		    " JENIS_WARGA, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, "+
		    " ID_BANDAR, ID_SIMATILAMA FROM TBLPPKSIMATI WHERE ID_SIMATI = '"+id_simati_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    }
		    
		    //TBLPPKPERMOHONANSIMATI
		    sqlInsert = " INSERT INTO TBLPPKPERMOHONANSIMATI (ID_PERMOHONANSIMATI, ID_PERMOHONAN, ID_SIMATI, "+ 
		    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB)";
		    sqlSelect = " SELECT "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_PERMOHONAN, "+setTempId+" + ID_SIMATI, "+ 
		    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',TARIKH_KEMASKINI, ID_DB FROM TBLPPKPERMOHONANSIMATI " +
		    " WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    //tblppkbayaran
		    sqlInsert = " INSERT INTO TBLPPKBAYARAN ( ID_BAYARAN, ID_PERMOHONAN, ID_JENISBAYARAN, NO_RESIT, "+
		    " TARIKH_BAYARAN, JUMLAH_BAYARAN,ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_BAYARAN, "+setTempId+" + ID_PERMOHONAN,ID_JENISBAYARAN, NO_RESIT, "+
		    " TARIKH_BAYARAN, JUMLAH_BAYARAN,'"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',TARIKH_KEMASKINI, ID_DB " +
		    " FROM TBLPPKBAYARAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' AND ID_JENISBAYARAN = '2' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);	
		    /*
		    sqlSelect = " SELECT ID_BAYARAN, ID_PERMOHONAN,ID_JENISBAYARAN, NO_RESIT, "+
		    " TARIKH_BAYARAN, JUMLAH_BAYARAN,ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB " +
		    " FROM TBLPPKBAYARAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' AND ID_JENISBAYARAN = '2' ";
		    rs = stmt.executeQuery(sqlSelect);
			while (rs.next()){	
				//long id_bayaran_baru = DB.getNextID(db,"TBLPPKBAYARAN_SEQ");
				String id_bayaran_lama = rs.getString("ID_BAYARAN")==null?"":rs.getString("ID_BAYARAN");
				addBayaran(session,id_bayaran_lama,ekptg_user_id);
			}*/
		    
			//tblsemakanhantar			
			sqlInsert = " INSERT INTO TBLSEMAKANHANTAR (ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, ID_PERMOHONAN, "+
			" PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, ID_DOKUMEN, "+
			" FLAG_ADA, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI)  ";				
			sqlSelect = " SELECT "+setTempId+" + ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, "+setTempId+" + ID_PERMOHONAN, "+
				" PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, ID_DOKUMEN, "+
				" FLAG_ADA, '"+ekptg_user_id+"', TARIKH_MASUK,'"+ekptg_user_id+"', TARIKH_KEMASKINI" +
		    " FROM TBLSEMAKANHANTAR WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"'  ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);			
			/*
		    sqlSelect = " SELECT ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, ID_PERMOHONAN, "+
			" PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, ID_DOKUMEN, "+
		    	" FLAG_ADA, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI FROM TBLSEMAKANHANTAR " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"' ";
		    rs = stmt.executeQuery(sqlSelect);
			while (rs.next()){	
				String id_semakanhantar_lama = rs.getString("ID_SEMAKANHANTAR")==null?"":rs.getString("ID_SEMAKANHANTAR");
				addSemakanHantar(session,id_semakanhantar_lama,ekptg_user_id);
			}*/
			
			/* */
			//tblppkob & tblppkobpermohonan & tblppkhubunganob & tblppkhubunganobpermohonan
		    sqlSelect = " SELECT  ID_OB, ID_SIMATI, NAMA_OB, "+
		    " NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, NO_SURAT_BERANAK, TARIKH_LAHIR, "+
		    " JANTINA, UMUR, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, "+
		    " ID_BANDAR, POSKOD, NO_HP, NO_TEL, CATATAN, STATUS_HIDUP, "+
		    " ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB, JENIS_AGAMA, JENIS_WARGA, "+
		    " ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
		    " NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, JENIS_PEMIUTANG, "+
		    " ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
		    " POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
		    " TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU, ID_OBLAMA, "+
		    " ID_ARB, NO_FAX, FLAG_DAFTAR " +
		    " FROM TBLPPKOB WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' ";
		    rs = stmt.executeQuery(sqlSelect);
			while (rs.next()){	
				//long id_ob_baru = DB.getNextID(db,"TBLPPKOB_SEQ");
				String id_ob_lama = rs.getString("ID_OB")==null?"":rs.getString("ID_OB");
				//addOb(session,id_permohonansimati_lama,id_ob_lama,ekptg_user_id);
				//addObPermohonan(session,id_permohonansimati_lama,id_ob_lama,ekptg_user_id);
				addObHubungan(session,id_permohonansimati_lama,id_ob_lama,ekptg_user_id,setTempId);
				//addObHubunganPermohonan(session,id_permohonansimati_lama,id_ob_lama,ekptg_user_id);
			}
			
			
			sqlInsert = "  INSERT INTO TBLPPKHUBUNGANOBPERMOHONAN ( "+
			"  ID_HUBUNGANOBPERMOHONAN, ID_PERMOHONANSIMATI, ID_HUBUNGANOB,  "+
			"  ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,  "+
			"  TARIKH_KEMASKINI, ID_DB, ID_OB,  "+
			"  ID_PARENTOB, ID_SAUDARA)  ";			
			sqlSelect = " SELECT "+setTempId+" + ID_HUBUNGANOBPERMOHONAN, "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_HUBUNGANOB,  "+
			"  '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',  "+
			"  TARIKH_KEMASKINI, ID_DB, "+setTempId+" + ID_OB,  "+
			"  "+setTempId+" + ID_PARENTOB, ID_SAUDARA " +
			" FROM TBLPPKHUBUNGANOBPERMOHONAN " +
			" WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
			
			
			sqlInsert = " INSERT INTO TBLPPKOB (ID_OB, ID_SIMATI, NAMA_OB, "+
			" NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, NO_SURAT_BERANAK, TARIKH_LAHIR, "+
			" JANTINA, UMUR, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, "+
			" ID_BANDAR, POSKOD, NO_HP, NO_TEL, CATATAN, STATUS_HIDUP, "+
			" ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB, JENIS_AGAMA, JENIS_WARGA, "+
			" ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
			" NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, JENIS_PEMIUTANG, "+
			" ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
			" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
			" TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU, ID_OBLAMA, "+
			" ID_ARB, NO_FAX, FLAG_DAFTAR)";			
			sqlSelect = " SELECT "+setTempId+" + ID_OB, "+setTempId+" + ID_SIMATI, NAMA_OB, "+
			" NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, NO_SURAT_BERANAK, TARIKH_LAHIR, "+
			" JANTINA, UMUR, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, "+
			" ID_BANDAR, POSKOD, NO_HP, NO_TEL, CATATAN, STATUS_HIDUP, "+
			" ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB, JENIS_AGAMA, JENIS_WARGA, "+
			" ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
			" NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, JENIS_PEMIUTANG, "+
			" ID_PEMOHON, "+setTempId+" + ID_PERMOHONANSIMATI, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
			" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', "+
			" TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU, ID_OBLAMA, "+
			" ID_ARB, NO_FAX, FLAG_DAFTAR " +
			" FROM TBLPPKOB WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' ";
			
			sqlInsert = " INSERT INTO TBLPPKOBPERMOHONAN (ID_OBPERMOHONAN, ID_PERMOHONANSIMATI, ID_OB, "+
			" ID_SIMATI, NAMA_OB, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,  "+
			" NO_SURAT_BERANAK, TARIKH_LAHIR, JANTINA, UMUR, ALAMAT_1, ALAMAT_2,  "+
			" ALAMAT_3, BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL,  "+
			" CATATAN, STATUS_HIDUP, ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB,  "+
			" JENIS_AGAMA, JENIS_WARGA, ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN,  "+
			" JENIS_WAKTU_KEMATIAN, STATUS_OB, NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS,  "+
			" BUTIRAN_HUTANG, JENIS_PEMIUTANG, ID_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT,  "+
			" ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK,  "+
			" ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU,  "+
			" ID_OBLAMA, ID_ARB, NO_FAX, FLAG_DAFTAR) ";			
			sqlSelect = " SELECT "+setTempId+" + ID_OBPERMOHONAN, "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_OB, "+
			" "+setTempId+" + ID_SIMATI, NAMA_OB, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,  "+
			" NO_SURAT_BERANAK, TARIKH_LAHIR, JANTINA, UMUR, ALAMAT_1, ALAMAT_2,  "+
			" ALAMAT_3, BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL,  "+
			" CATATAN, STATUS_HIDUP, ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB,  "+
			" JENIS_AGAMA, JENIS_WARGA, ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN,  "+
			" JENIS_WAKTU_KEMATIAN, STATUS_OB, NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS,  "+
			" BUTIRAN_HUTANG, JENIS_PEMIUTANG, ID_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT,  "+
			" ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"', TARIKH_MASUK,  "+
			" '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU,  "+
			" ID_OBLAMA, ID_ARB, NO_FAX, FLAG_DAFTAR FROM TBLPPKOBPERMOHONAN " +
			" WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'  ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);			
			
		    //tblppkpenghutang
		    sqlInsert = " INSERT INTO TBLPPKPENGHUTANG ( ID_PENGHUTANG, ID_SIMATI, NAMA_PENGHUTANG, "+ 
		    	" NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN, ALAMAT_1, ALAMAT_2, ALAMAT_3, "+ 
		    	" BANDAR, POSKOD, NO_AKAUN, NAMA_BANK, JUMLAH_HUTANG, BUTIRAN_HUTANG, "+ 
		    	" ID_NEGERI, JENIS_PENGHUTANG, JENIS_AGAMA, JENIS_WARGA, JENIS_KP, ID_PERMOHONANSIMATI, "+ 
		    	" ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, ID_BANDAR) ";
		    sqlSelect = "SELECT "+setTempId+" + ID_PENGHUTANG, "+setTempId+" + ID_SIMATI, NAMA_PENGHUTANG, "+ 
		    	" NO_KP_BARU, NO_KP_LAMA, NO_KP_LAIN, ALAMAT_1, ALAMAT_2, ALAMAT_3, "+ 
		    	" BANDAR, POSKOD, NO_AKAUN, NAMA_BANK, JUMLAH_HUTANG, BUTIRAN_HUTANG, "+ 
		    	" ID_NEGERI, JENIS_PENGHUTANG, JENIS_AGAMA, JENIS_WARGA, JENIS_KP, ID_PERMOHONANSIMATI, "+ 
		    	" '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, ID_BANDAR" +
		    	" FROM TBLPPKPENGHUTANG WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    //tblppkhta
		    sqlInsert = " INSERT INTO TBLPPKHTA ("+
		    	" ID_HTA, NO_HAKMILIK, ID_SIMATI, NO_PT, NILAI_HTA_TARIKHMOHON, NILAI_HTA_TARIKHMATI,  "+
		    	" ID_KATEGORI, ID_JENISHM, ID_JENISPB, ID_NEGERI, ID_DAERAH, ID_MUKIM, "+
		    	" ID_LUAS, LUAS, LUAS_HMP, NO_CAGARAN, NO_PAJAKAN, JENIS_TNH, "+
		    	" ALAMAT_HTA1, ALAMAT_HTA2, ALAMAT_HTA3, BANDAR_HTA, POSKOD_HTA, TARIKH_PERJANJIAN, "+
		    	" NAMA_PEMAJU, ALAMAT_PEMAJU1, ALAMAT_PEMAJU2,  ALAMAT_PEMAJU3, BANDAR_PEMAJU, POSKOD_PEMAJU, "+
		    	" ID_NEGERIPEMAJU, CATATAN, BA_SIMATI, BB_SIMATI, NO_BANGUNAN, NO_TINGKAT, "+
		    	" NO_PETAK, NO_STRATA, MAKLUMAT_TANAH, NO_PERJANJIAN, JENIS_HTA, STATUS_PEMILIKAN, "+
		    	" TANGGUNGAN, NO_PERSERAHAN, NAMA_RANCANGAN, NO_ROH, NO_LOT_ID, FLAG_KATEGORI_HTA, "+
		    	" JENIS_KEPENTINGAN, ID_PERMOHONANSIMATI, ID_MASUK, "+
		    	" TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
		    	" ID_DB, ID_BANDARPEMAJU, ID_BANDARHTA, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    	" ID_PERINTAHOBMST, ID_HAKMILIK, FLAG_DAFTAR)";
		    sqlSelect = " SELECT "+
		    	" "+setTempId+" + ID_HTA, NO_HAKMILIK, "+setTempId+" + ID_SIMATI, NO_PT, NILAI_HTA_TARIKHMOHON, NILAI_HTA_TARIKHMATI,  "+
		    	" ID_KATEGORI, ID_JENISHM, ID_JENISPB, ID_NEGERI, ID_DAERAH, ID_MUKIM, "+
		    	" ID_LUAS, LUAS, LUAS_HMP, NO_CAGARAN, NO_PAJAKAN, JENIS_TNH, "+
		    	" ALAMAT_HTA1, ALAMAT_HTA2, ALAMAT_HTA3, BANDAR_HTA, POSKOD_HTA, TARIKH_PERJANJIAN, "+
		    	" NAMA_PEMAJU, ALAMAT_PEMAJU1, ALAMAT_PEMAJU2,  ALAMAT_PEMAJU3, BANDAR_PEMAJU, POSKOD_PEMAJU, "+
		    	" ID_NEGERIPEMAJU, CATATAN, BA_SIMATI, BB_SIMATI, NO_BANGUNAN, NO_TINGKAT, "+
		    	" NO_PETAK, NO_STRATA, MAKLUMAT_TANAH, NO_PERJANJIAN, JENIS_HTA, STATUS_PEMILIKAN, "+
		    	" TANGGUNGAN, NO_PERSERAHAN, NAMA_RANCANGAN, NO_ROH, NO_LOT_ID, FLAG_KATEGORI_HTA, "+
		    	" JENIS_KEPENTINGAN, "+setTempId+" + ID_PERMOHONANSIMATI, '"+ekptg_user_id+"', "+
		    	" TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, "+
		    	" ID_DB, ID_BANDARPEMAJU, ID_BANDARHTA, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    	" ID_PERINTAHOBMST, ID_HAKMILIK, FLAG_DAFTAR FROM TBLPPKHTA " +
		    	" WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    //tblppkhtapermohonan
		    sqlInsert = " INSERT INTO TBLPPKHTAPERMOHONAN ("+
		    	" ID_HTAPERMOHONAN,ID_HTA, NO_HAKMILIK, ID_SIMATI, NO_PT, NILAI_HTA_TARIKHMOHON, NILAI_HTA_TARIKHMATI,  "+
		    	" ID_KATEGORI, ID_JENISHM, ID_JENISPB, ID_NEGERI, ID_DAERAH, ID_MUKIM, "+
		    	" ID_LUAS, LUAS, LUAS_HMP, NO_CAGARAN, NO_PAJAKAN, JENIS_TNH, "+
		    	" ALAMAT_HTA1, ALAMAT_HTA2, ALAMAT_HTA3, BANDAR_HTA, POSKOD_HTA, TARIKH_PERJANJIAN, "+
		    	" NAMA_PEMAJU, ALAMAT_PEMAJU1, ALAMAT_PEMAJU2,  ALAMAT_PEMAJU3, BANDAR_PEMAJU, POSKOD_PEMAJU, "+
		    	" ID_NEGERIPEMAJU, CATATAN, BA_SIMATI, BB_SIMATI, NO_BANGUNAN, NO_TINGKAT, "+
		    	" NO_PETAK, NO_STRATA, MAKLUMAT_TANAH, NO_PERJANJIAN, JENIS_HTA, STATUS_PEMILIKAN, "+
		    	" TANGGUNGAN, NO_PERSERAHAN, NAMA_RANCANGAN, NO_ROH, NO_LOT_ID, FLAG_KATEGORI_HTA, "+
		    	" JENIS_KEPENTINGAN, ID_PERMOHONANSIMATI, ID_MASUK, "+
		    	" TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
		    	" ID_DB, ID_BANDARPEMAJU, ID_BANDARHTA, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    	" ID_PERINTAHOBMST, ID_HAKMILIK, FLAG_DAFTAR)";
		    sqlSelect = " SELECT "+setTempId+" + ID_HTAPERMOHONAN,"+
		    	" "+setTempId+" + ID_HTA, NO_HAKMILIK, "+setTempId+" + ID_SIMATI, NO_PT, NILAI_HTA_TARIKHMOHON, NILAI_HTA_TARIKHMATI,  "+
		    	" ID_KATEGORI, ID_JENISHM, ID_JENISPB, ID_NEGERI, ID_DAERAH, ID_MUKIM, "+
		    	" ID_LUAS, LUAS, LUAS_HMP, NO_CAGARAN, NO_PAJAKAN, JENIS_TNH, "+
		    	" ALAMAT_HTA1, ALAMAT_HTA2, ALAMAT_HTA3, BANDAR_HTA, POSKOD_HTA, TARIKH_PERJANJIAN, "+
		    	" NAMA_PEMAJU, ALAMAT_PEMAJU1, ALAMAT_PEMAJU2,  ALAMAT_PEMAJU3, BANDAR_PEMAJU, POSKOD_PEMAJU, "+
		    	" ID_NEGERIPEMAJU, CATATAN, BA_SIMATI, BB_SIMATI, NO_BANGUNAN, NO_TINGKAT, "+
		    	" NO_PETAK, NO_STRATA, MAKLUMAT_TANAH, NO_PERJANJIAN, JENIS_HTA, STATUS_PEMILIKAN, "+
		    	" TANGGUNGAN, NO_PERSERAHAN, NAMA_RANCANGAN, NO_ROH, NO_LOT_ID, FLAG_KATEGORI_HTA, "+
		    	" JENIS_KEPENTINGAN, "+setTempId+" + ID_PERMOHONANSIMATI, '"+ekptg_user_id+"', "+
		    	" TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, "+
		    	" ID_DB, ID_BANDARPEMAJU, ID_BANDARHTA, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    	" ID_PERINTAHOBMST, ID_HAKMILIK, FLAG_DAFTAR FROM TBLPPKHTAPERMOHONAN " +
		    	" WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    //tblppkha
		    sqlInsert = "    INSERT INTO TBLPPKHA ( "+
		    " ID_HA, BIL, ID_SIMATI, ID_JENISHA, ID_NEGERI, ID_DAERAH, JENAMA, NO_DAFTAR, NO_SIJIL, "+
		    " BIL_UNIT, TARIKH_HARTA, ALAMAT_HA1, ALAMAT_HA2, ALAMAT_HA3, POSKOD, "+
		    " NILAI_HA_TARIKHMOHON, NILAI_HA_TARIKHMATI, BA_SIMATI, "+
		    " BB_SIMATI, CATATAN, NILAI_SEUNIT, ID_PERMOHONANSIMATI, ID_MASUK, TARIKH_MASUK, "+
		    " ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    " ID_PERINTAHOBMST, NAMA_SAHAM, BUTIRAN, ID_HA_LAMA, FLAG_DAFTAR) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_HA, BIL, "+setTempId+" + ID_SIMATI, ID_JENISHA, ID_NEGERI, ID_DAERAH, JENAMA, NO_DAFTAR, NO_SIJIL, "+
		    " BIL_UNIT, TARIKH_HARTA, ALAMAT_HA1, ALAMAT_HA2, ALAMAT_HA3, POSKOD, "+
		    " NILAI_HA_TARIKHMOHON, NILAI_HA_TARIKHMATI, BA_SIMATI, "+
		    " BB_SIMATI, CATATAN, NILAI_SEUNIT, "+setTempId+" + ID_PERMOHONANSIMATI, '"+ekptg_user_id+"', TARIKH_MASUK, "+
		    " '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    " ID_PERINTAHOBMST, NAMA_SAHAM, BUTIRAN, ID_HA_LAMA, FLAG_DAFTAR FROM TBLPPKHA " +
		    "  WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    //tblppkha
		    sqlInsert = "    INSERT INTO TBLPPKHAPERMOHONAN ( "+
		    " ID_HA, BIL, ID_SIMATI, ID_JENISHA, ID_NEGERI, ID_DAERAH, JENAMA, NO_DAFTAR, NO_SIJIL, "+
		    " BIL_UNIT, TARIKH_HARTA, ALAMAT_HA1, ALAMAT_HA2, ALAMAT_HA3, POSKOD, "+
		    " NILAI_HA_TARIKHMOHON, NILAI_HA_TARIKHMATI, BA_SIMATI, "+
		    " BB_SIMATI, CATATAN, NILAI_SEUNIT, ID_PERMOHONANSIMATI, ID_MASUK, TARIKH_MASUK, "+
		    " ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    " ID_PERINTAHOBMST, NAMA_SAHAM, BUTIRAN, ID_HA_LAMA, FLAG_DAFTAR) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_HA, BIL, "+setTempId+" + ID_SIMATI, ID_JENISHA, ID_NEGERI, ID_DAERAH, JENAMA, NO_DAFTAR, NO_SIJIL, "+
		    " BIL_UNIT, TARIKH_HARTA, ALAMAT_HA1, ALAMAT_HA2, ALAMAT_HA3, POSKOD, "+
		    " NILAI_HA_TARIKHMOHON, NILAI_HA_TARIKHMATI, BA_SIMATI, "+
		    " BB_SIMATI, CATATAN, NILAI_SEUNIT, "+setTempId+" + ID_PERMOHONANSIMATI, '"+ekptg_user_id+"', TARIKH_MASUK, "+
		    " '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, FLAG_PA, FLAG_PT, FLAG_SELESAI, "+
		    " ID_PERINTAHOBMST, NAMA_SAHAM, BUTIRAN, ID_HA_LAMA, FLAG_DAFTAR FROM TBLPPKHA " +
		    "  WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		 
		    
		    sqlInsert = " INSERT INTO TBLPPKPILIHANHTA (ID_PILIHANHTA, ID_PERMOHONANSIMATI, ID_HTA, "+
		    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, FLAG_DAFTAR_PERINTAH)";
		    sqlSelect = "SELECT "+setTempId+" + ID_PILIHANHTA, "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_HTA, "+
		    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, FLAG_DAFTAR_PERINTAH" +
		    " FROM TBLPPKPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    sqlInsert = " INSERT INTO TBLPPKOBPILIHANHTA (ID_OBPILIHANHTA, ID_PILIHANHTA, ID_PERMOHONANSIMATI, "+
		    " ID_OB, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI, PA1, "+
		    " PA2, PA3, PA4, FLAG_DAFTAR_PERINTAH, ID_PERINTAHHTAOBDTL) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_OBPILIHANHTA, "+setTempId+" + ID_PILIHANHTA,"+setTempId+" + ID_PERMOHONANSIMATI, "+
		    " "+setTempId+" + ID_OB, '"+ekptg_user_id+"', TARIKH_MASUK,'"+ekptg_user_id+"', TARIKH_KEMASKINI, "+setTempId+" + PA1, "+
		    " "+setTempId+" + PA2, "+setTempId+" + PA3, "+setTempId+" + PA4, FLAG_DAFTAR_PERINTAH, " +
		    " "+setTempId+" + ID_PERINTAHHTAOBDTL FROM TBLPPKOBPILIHANHTA WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    sqlInsert = " INSERT INTO TBLPPKPILIHANHA (ID_PILIHANHA, ID_PERMOHONANSIMATI, ID_HA, "+
		    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, FLAG_DAFTAR_PERINTAH)";
		    sqlSelect = "SELECT "+setTempId+" + ID_PILIHANHA, "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_HA, "+
		    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, FLAG_DAFTAR_PERINTAH" +
		    " FROM TBLPPKPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    sqlInsert = " INSERT INTO TBLPPKOBPILIHANHA (ID_OBPILIHANHA, ID_PILIHANHA, ID_PERMOHONANSIMATI, "+
		    " ID_OB, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI, PA1, "+
		    " PA2, PA3, PA4, FLAG_DAFTAR_PERINTAH, ID_PERINTAHHAOBDTL) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_OBPILIHANHA, "+setTempId+" + ID_PILIHANHA,"+setTempId+" + ID_PERMOHONANSIMATI, "+
		    " "+setTempId+" + ID_OB, '"+ekptg_user_id+"', TARIKH_MASUK,'"+ekptg_user_id+"', TARIKH_KEMASKINI, "+setTempId+" + PA1, "+
		    " "+setTempId+" + PA2, "+setTempId+" + PA3, "+setTempId+" + PA4, FLAG_DAFTAR_PERINTAH, " +
		    " "+setTempId+" + ID_PERINTAHHAOBDTL FROM TBLPPKOBPILIHANHA WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    	    		    
		    sqlInsert = " INSERT INTO TBLPPKKEPUTUSANPERMOHONAN (ID_PERMOHONAN, CATATAN, TARIKH_HANTAR_BORANGB, "+ 
		    " TARIKH_TERIMA_BORANGC, KEPUTUSAN_PERMOHONAN, TARIKH_HANTAR_NILAIAN, "+
		    " TARIKH_TERIMA_NILAIAN, ID_NEGERIMAHKAMAH, ID_DAERAH_MAHKAMAH, "+
		    " ID_KEPUTUSANPERMOHONAN, JENIS_BORANGC, ID_MASUK,TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_DB)";
		    sqlSelect = " SELECT "+setTempId+" + ID_PERMOHONAN, CATATAN, TARIKH_HANTAR_BORANGB, "+ 
		    " TARIKH_TERIMA_BORANGC, KEPUTUSAN_PERMOHONAN, TARIKH_HANTAR_NILAIAN, "+
		    " TARIKH_TERIMA_NILAIAN, ID_NEGERIMAHKAMAH, ID_DAERAH_MAHKAMAH, "+
		    " "+setTempId+" + ID_KEPUTUSANPERMOHONAN, JENIS_BORANGC, '"+ekptg_user_id+"',TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI,ID_DB" +
		    " FROM TBLPPKKEPUTUSANPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		       
		    sqlInsert = "  INSERT INTO TBLPPKPERBICARAAN ( ID_PERBICARAAN, ID_KEPUTUSANPERMOHONAN, ID_NEGERI, "+
		    	"  ID_UNITPSK, TARIKH_NOTIS, TARIKH_BICARA, MASA_BICARA, TEMPAT_BICARA, BIL_BICARA, "+
		    	"  ALAMAT_BICARA1, ALAMAT_BICARA2, ALAMAT_BICARA3, BANDAR, POSKOD, ID_NEGERIBICARA, "+
		    	"  PEG_PENGENDALI, CATATAN, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
		    	"  ID_DB, ID_PEJABAT, ID_JENISPEJABAT,ID_NOTISBICARALAMA, JENIS_MASA_BICARA)";
		    sqlSelect = " SELECT  "+setTempId+" + ID_PERBICARAAN, "+setTempId+" + ID_KEPUTUSANPERMOHONAN, ID_NEGERI, "+
		    	"  ID_UNITPSK, TARIKH_NOTIS, TARIKH_BICARA, MASA_BICARA, TEMPAT_BICARA, BIL_BICARA, "+
		    	"  ALAMAT_BICARA1, ALAMAT_BICARA2, ALAMAT_BICARA3, BANDAR, POSKOD, ID_NEGERIBICARA, "+
		    	"  PEG_PENGENDALI, CATATAN, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, "+
		    	"  ID_DB, ID_PEJABAT, ID_JENISPEJABAT,"+setTempId+" + ID_NOTISBICARALAMA, JENIS_MASA_BICARA " +
		    	" FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"') ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    sqlInsert = " INSERT INTO TBLPPKNOTISOBMST (ID_NOTISOBMST, BIL, TARIKH_SERAHAN, STATUS_SERAH, JENIS_SERAH, "+
	    	" STATUS_AKUAN_SUMPAH, CATATAN, NAMA_PENGHANTAR_NOTIS, NO_SURAT_DAFTAR, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
	    	" TARIKH_KEMASKINI, ID_DB, ID_NOTISOBMSTLAMA,ID_PENGHANTARNOTIS) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_NOTISOBMST, BIL, TARIKH_SERAHAN, STATUS_SERAH, JENIS_SERAH, "+
	    	" STATUS_AKUAN_SUMPAH, CATATAN, NAMA_PENGHANTAR_NOTIS, NO_SURAT_DAFTAR, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', "+
	    	" TARIKH_KEMASKINI, ID_DB, "+setTempId+" + ID_NOTISOBMSTLAMA,ID_PENGHANTARNOTIS FROM TBLPPKNOTISOBMST " +
	    	" WHERE ID_NOTISOBMST IN (SELECT DISTINCT ID_NOTISOBMST FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN " +
			    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
			    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
			    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"'))) "; 
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    sqlInsert = " INSERT INTO TBLPPKNOTISOBDTL (ID_NOTISOBDTL, ID_NOTISOBMST, ID_OB,NAMA_PENERIMA, NO_KP_BARU, ID_MASUK, "+
			" TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_DB, NO_KP_LAMA, NO_KP_LAIN) ";
		    sqlSelect = " SELECT "+setTempId+" +  ID_NOTISOBDTL, "+setTempId+" + ID_NOTISOBMST, "+setTempId+" +  ID_OB,NAMA_PENERIMA, NO_KP_BARU, '"+ekptg_user_id+"', "+
			" TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI,ID_DB, NO_KP_LAMA, NO_KP_LAIN FROM TBLPPKNOTISOBDTL " +
	    	" WHERE ID_NOTISOBMST IN (SELECT DISTINCT ID_NOTISOBMST FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN " +
			    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
			    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
			    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"'))) "; 
		    
		    
		    /*
		    sqlSelect = "SELECT ID_NOTISPERBICARAAN,ID_PERBICARAAN, ID_NOTISOBMST, "+
		    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, FLAG_JENIS_NOTIS " +
		    " FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN " +
		    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')) "; 
		    rs = stmt.executeQuery(sqlSelect);
			while (rs.next()){					
				String id_notisobmst = rs.getString("ID_NOTISOBMST")==null?"":rs.getString("ID_NOTISOBMST");
				addNotisobmst(session,id_notisobmst,ekptg_user_id);
				addNotisobdtl(session,id_notisobmst,ekptg_user_id);
			}
			*/
			
			 sqlInsert = " INSERT INTO TBLPPKNOTISPERBICARAAN (ID_NOTISPERBICARAAN, ID_PERBICARAAN, ID_NOTISOBMST, "+
			    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, FLAG_JENIS_NOTIS)";
			    sqlSelect = "SELECT "+setTempId+" + ID_NOTISPERBICARAAN, "+setTempId+" + ID_PERBICARAAN, "+setTempId+" + ID_NOTISOBMST, "+
			    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, FLAG_JENIS_NOTIS " +
			    " FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN " +
			    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
			    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
			    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')) "; 
			    myLogger.info(sqlInsert+sqlSelect);
			    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    sqlInsert = " INSERT INTO TBLPPKPERINTAH (ID_PERINTAH, ID_PERBICARAAN, TARIKH_PERINTAH, "+ 
		    " ID_NEGERI, ID_UNITPSK, ID_JENISPERINTAH,NOTIS_GERAN, FLAG_JENIS_KEPUTUSAN, SEBAB_TANGGUH, "+ 
		    " SEBAB_BATAL, KEPUTUSAN_MAHKAMAH, FLAG_TANGGUH,FLAG_BATAL, TEMPOH_TUNGGU_FARAID, ID_DAERAHMAHKAMAH, "+ 
		    " ID_PEJABATMAHKAMAH, JENIS_KELUAR_PERINTAH, ID_MASUK,TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+ 
		    " ID_DB, FLAG_RAYUAN, CATATAN_PERINTAH,CATATAN, CHECK_KIV, DATE_KIV,CATATAN_KIV)";
		    sqlSelect = "SELECT "+setTempId+" + ID_PERINTAH, "+setTempId+" + ID_PERBICARAAN, TARIKH_PERINTAH, "+ 
		    " ID_NEGERI, ID_UNITPSK, ID_JENISPERINTAH,NOTIS_GERAN, FLAG_JENIS_KEPUTUSAN, SEBAB_TANGGUH, "+ 
		    " SEBAB_BATAL, KEPUTUSAN_MAHKAMAH, FLAG_TANGGUH,FLAG_BATAL, TEMPOH_TUNGGU_FARAID, ID_DAERAHMAHKAMAH, "+ 
		    " ID_PEJABATMAHKAMAH, JENIS_KELUAR_PERINTAH,  '"+ekptg_user_id+"',TARIKH_MASUK,  '"+ekptg_user_id+"', TARIKH_KEMASKINI, "+ 
		    " ID_DB, FLAG_RAYUAN, CATATAN_PERINTAH,CATATAN, CHECK_KIV, DATE_KIV,CATATAN_KIV " +
		    " FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
		    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')) "; 
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    
		    sqlInsert = "INSERT INTO TBLPPKPERINTAHHTAOBMST (ID_PERINTAHHTAOBMST, ID_HTA, ID_PERINTAH, "+
		    " CATATAN, NILAI_BERSIH, CUKAI_HARTA,NAMA_PEMBAYAR_CUKAI, TARIKH_JUALAN, AMAUN, "+
		    " JENIS_LELONG, HARGA_RIZAB, ID_JENISPERINTAH,ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
		    " TARIKH_KEMASKINI, ID_DB, FLAG_HARTA,FLAG_BATAL, ID_WARIS, BA_WARIS,BB_WARIS)";
		    sqlSelect = "SELECT "+setTempId+" + ID_PERINTAHHTAOBMST, "+setTempId+" + ID_HTA, "+setTempId+" + ID_PERINTAH, "+
		    " CATATAN, NILAI_BERSIH, CUKAI_HARTA,NAMA_PEMBAYAR_CUKAI, TARIKH_JUALAN, AMAUN, "+
		    " JENIS_LELONG, HARGA_RIZAB, ID_JENISPERINTAH,'"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', "+
		    " TARIKH_KEMASKINI, ID_DB, FLAG_HARTA,FLAG_BATAL, ID_WARIS, BA_WARIS,BB_WARIS " +
		    " FROM TBLPPKPERINTAHHTAOBMST WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
		    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"'))) ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    sqlInsert = "INSERT INTO TBLPPKPERINTAHHAOBMST ( ID_PERINTAHHAOBMST, ID_PERINTAH, CATATAN, "+ 
		    " NILAI_BERSIH, CUKAI_HARTA, NAMA_PEMBAYAR_CUKAI,TARIKH_JUALAN, AMAUN, JENIS_LELONG,  "+
		    " HARGA_RIZAB, ID_JENISPERINTAH, ID_HA,ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,  "+
		    " TARIKH_KEMASKINI, ID_DB, FLAG_JENISPEMBAHAGIAN,FLAG_HARTA, FLAG_BATAL, ID_WARIS,BA_WARIS, BB_WARIS) ";
		    sqlSelect = "SELECT "+setTempId+" + ID_PERINTAHHAOBMST, "+setTempId+" + ID_PERINTAH, CATATAN, "+ 
		    " NILAI_BERSIH, CUKAI_HARTA, NAMA_PEMBAYAR_CUKAI,TARIKH_JUALAN, AMAUN, JENIS_LELONG,  "+
		    " HARGA_RIZAB, ID_JENISPERINTAH, ID_HA,'"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',  "+
		    " TARIKH_KEMASKINI, ID_DB, FLAG_JENISPEMBAHAGIAN,FLAG_HARTA, FLAG_BATAL, ID_WARIS,BA_WARIS, BB_WARIS " +
		    " FROM TBLPPKPERINTAHHAOBMST WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
		    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"'))) ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    sqlInsert = " INSERT INTO TBLPPKPERINTAHHTAOBDTL (ID_PERINTAHHTAOBDTL, ID_PERINTAHHTAOBMST, ID_OB, "+
		    " BA, BB, PEKALI,MINOR, ID_PENJAGA1, KAVEAT1,BATAL_PA1, ID_PENJAGA2, KAVEAT2,BATAL_PA2, ID_PENJAGA3, KAVEAT3, "+ 
		    " BATAL_PA3, ID_PENJAGA4, KAVEAT4,BATAL_PA4, STATUS_TADBIR, CATATAN,ID_PA1, ID_PA2, ID_PA3,  "+
		    " ID_PA4, WAKIL, KETERANGAN_OB,ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB, BA_WARIS,BB_WARIS) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_PERINTAHHTAOBDTL, "+setTempId+" + ID_PERINTAHHTAOBMST, "+setTempId+" + ID_OB, "+
		    " BA, BB, PEKALI,MINOR, "+setTempId+" + ID_PENJAGA1, KAVEAT1,BATAL_PA1,"+setTempId+" +  ID_PENJAGA2, KAVEAT2,BATAL_PA2, "+setTempId+" + ID_PENJAGA3, " +
		    " KAVEAT3, BATAL_PA3, "+setTempId+" + ID_PENJAGA4, KAVEAT4,BATAL_PA4, STATUS_TADBIR, CATATAN,"+setTempId+" + ID_PA1," +
		    " "+setTempId+" + ID_PA2,"+setTempId+" +  ID_PA3,  "+
		    " "+setTempId+" + ID_PA4, WAKIL, KETERANGAN_OB,'"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',TARIKH_KEMASKINI, ID_DB, BA_WARIS,BB_WARIS " +
		    " FROM TBLPPKPERINTAHHTAOBDTL " +
		    " WHERE ID_PERINTAHHTAOBMST IN (SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
		    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
		    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    sqlInsert = "INSERT INTO TBLPPKPERINTAHHAOBDTL ( ID_PERINTAHHAOBDTL, ID_PERINTAHHAOBMST, ID_OB, "+
		    " ID_HA, TARIKH_PERINTAH, MINOR,BA, BB, PEKALI, ID_PENJAGA1, BATAL_PA1, ID_PENJAGA2, BATAL_PA2, ID_PENJAGA3, BATAL_PA3,  "+
		    " ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR,CATATAN, ID_PA1, ID_PA2,ID_PA3, ID_PA4, BAHAGIAN_WARIS,  "+
		    " ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB, BA_WARIS, BB_WARIS)";
		    sqlSelect = " SELECT "+setTempId+" + ID_PERINTAHHAOBDTL, "+setTempId+" + ID_PERINTAHHAOBMST, "+setTempId+" + ID_OB, "+
		    " "+setTempId+" + ID_HA, TARIKH_PERINTAH, MINOR,BA, BB, PEKALI, "+setTempId+" + ID_PENJAGA1, BATAL_PA1, "+setTempId+" + ID_PENJAGA2, BATAL_PA2, " +
		    " "+setTempId+" + ID_PENJAGA3, BATAL_PA3,  "+
		    " "+setTempId+" + ID_PENJAGA4, BATAL_PA4, STATUS_TADBIR,CATATAN, "+setTempId+" + ID_PA1, "+setTempId+" + ID_PA2,"+setTempId+" + ID_PA3, "+setTempId+" + ID_PA4, " +
		    " BAHAGIAN_WARIS,  "+
		    " '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',TARIKH_KEMASKINI, ID_DB, BA_WARIS, BB_WARIS " +
		    " FROM TBLPPKPERINTAHHAOBDTL " +
		    " WHERE ID_PERINTAHHAOBMST IN (SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
		    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
		    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
		    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
		    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    sqlSelect = " SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHTAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA1 = P.ID_PENJAGA "+
		    " AND ID_PERINTAHHTAOBMST IN (SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+
				    	" UNION "+
				    	" SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHTAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA2 = P.ID_PENJAGA "+
				    	" AND ID_PERINTAHHTAOBMST IN (SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+
				    	" UNION "+
				    	" SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHTAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA3 = P.ID_PENJAGA "+
				    	" AND ID_PERINTAHHTAOBMST IN (SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+
				    	" UNION "+
				    	" SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHTAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA4 = P.ID_PENJAGA "+
				    	" AND ID_PERINTAHHTAOBMST IN (SELECT ID_PERINTAHHTAOBMST FROM TBLPPKPERINTAHHTAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+	
		    
				    	"UNION SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA1 = P.ID_PENJAGA "+
		    " AND ID_PERINTAHHAOBMST IN (SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+
				    	" UNION "+
				    	" SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA2 = P.ID_PENJAGA "+
				    	" AND ID_PERINTAHHAOBMST IN (SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+
				    	" UNION "+
				    	" SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA3 = P.ID_PENJAGA "+
				    	" AND ID_PERINTAHHAOBMST IN (SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) "+
				    	" UNION "+
				    	" SELECT DISTINCT ID_PENJAGA FROM TBLPPKPERINTAHHAOBDTL A,TBLPPKPENJAGA P WHERE A.ID_PENJAGA4 = P.ID_PENJAGA "+
				    	" AND ID_PERINTAHHAOBMST IN (SELECT ID_PERINTAHHAOBMST FROM TBLPPKPERINTAHHAOBMST " +
				    " WHERE ID_PERINTAH IN (SELECT ID_PERINTAH FROM TBLPPKPERINTAH WHERE ID_PERBICARAAN " +
				    " IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_KEPUTUSANPERMOHONAN " +
				    	" IN (SELECT ID_KEPUTUSANPERMOHONAN FROM TBLPPKKEPUTUSANPERMOHONAN " +
				    	" WHERE ID_PERMOHONAN = '"+id_permohonan_lama+"')))) ";
		    myLogger.info(sqlSelect);
		    rs = stmt.executeQuery(sqlSelect);
			while (rs.next()){					
				String id_penjaga = rs.getString("ID_PENJAGA")==null?"":rs.getString("ID_PENJAGA");
				addPenjaga(session,id_penjaga,ekptg_user_id,setTempId);
			}
		    
		    	   
		    sqlInsert = " INSERT INTO TBLRUJSUBURUSANSTATUSFAIL (ID_SUBURUSANSTATUSFAIL, ID_PERMOHONAN, ID_SUBURUSANSTATUS, "
			+" URL, AKTIF, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_FAIL) ";
		    sqlSelect = "SELECT "+setTempId+" + ID_SUBURUSANSTATUSFAIL, "+setTempId+" + ID_PERMOHONAN, ID_SUBURUSANSTATUS, "
			+" URL, '0', '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI," +
					" "+setTempId+" + ID_FAIL FROM " +
					" TBLRUJSUBURUSANSTATUSFAIL WHERE ID_FAIL = '"+id_fail_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);
		    
		    
		    
		    //ADD STATUS BATAL
		    sqlInsert = " INSERT INTO TBLRUJSUBURUSANSTATUSFAIL (ID_PERMOHONAN, ID_SUBURUSANSTATUS, "
			+"  AKTIF, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_FAIL) ";
			if(seksyen.equals("8"))
			{
				String temp_val = "10000000000";	
			    sqlSelect = " VALUES ('"+(Long.parseLong(temp_val)+Long.parseLong(id_permohonan_lama))+"','398','0'," +
			    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+(Long.parseLong(temp_val)+Long.parseLong(id_fail_lama))+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
			}
			else
			{
				String temp_val = "10000000000";	
			    sqlSelect = " VALUES ('"+(Long.parseLong(temp_val)+Long.parseLong(id_permohonan_lama))+"','425','0'," +
			    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+(Long.parseLong(temp_val)+Long.parseLong(id_fail_lama))+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
			}
					    
		    
		    conn.commit();
	    }
	    catch (SQLException se) {
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Pindah Fail:"+se.getMessage());
	    }
	    finally {
	      if (db != null) db.close();
	    }
	    
		//myLogger.info("SEKSYEN 8 <<<<< ::" + id_permohonan_baru + "|" + id_fail_baru);
		//output.put("id_permohonan_baru", id_permohonan_baru);
		//output.put("id_fail_baru", id_fail_baru);
		return output;
	}
	
	
	private static void addPenjaga(HttpSession session,String id_penjaga,String ekptg_user_id,String setTempId) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			 
					
			sqlInsert = " INSERT INTO TBLPPKPENJAGA (ID_PENJAGA, ID_OBMINOR, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, "+
			" NAMA_PENJAGA, JANTINA, JENIS_AGAMA,JENIS_WARGA, UMUR, ALAMAT1,ALAMAT2, ALAMAT3, POSKOD, "+
			" BANDAR, ID_NEGERI, CATATAN,ID_OB, BIL_PENJAGA, ID_MASUK,TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI, "+
			" ID_DB, ID_BANDAR) ";
	    sqlSelect = " SELECT "+setTempId+" + ID_PENJAGA, "+setTempId+" + ID_OBMINOR, NO_KP_BARU,NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, "+
			" NAMA_PENJAGA, JANTINA, JENIS_AGAMA,JENIS_WARGA, UMUR, ALAMAT1,ALAMAT2, ALAMAT3, POSKOD, "+
			" BANDAR, ID_NEGERI, CATATAN,ID_OB, BIL_PENJAGA, '"+ekptg_user_id+"',TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI, "+
			" ID_DB, ID_BANDAR FROM TBLPPKPENJAGA " +
	    	" WHERE ID_PENJAGA = '"+id_penjaga+"'";
	    myLogger.info(sqlInsert+sqlSelect);
	    stmt.executeUpdate(sqlInsert+sqlSelect);				
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private static void addNotisobmst(HttpSession session,String id_notisobmst_lama,String ekptg_user_id,String setTempId) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = " INSERT INTO TBLPPKNOTISOBMST (ID_NOTISOBMST, BIL, TARIKH_SERAHAN, STATUS_SERAH, JENIS_SERAH, "+
	    	" STATUS_AKUAN_SUMPAH, CATATAN, NAMA_PENGHANTAR_NOTIS, NO_SURAT_DAFTAR, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', "+
	    	" TARIKH_KEMASKINI, ID_DB, ID_NOTISOBMSTLAMA,ID_PENGHANTARNOTIS) ";
	    sqlSelect = " SELECT "+setTempId+" + ID_NOTISOBMST, BIL, TARIKH_SERAHAN, STATUS_SERAH, JENIS_SERAH, "+
	    	" STATUS_AKUAN_SUMPAH, CATATAN, NAMA_PENGHANTAR_NOTIS, NO_SURAT_DAFTAR, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', "+
	    	" TARIKH_KEMASKINI, ID_DB, "+setTempId+" + ID_NOTISOBMSTLAMA,ID_PENGHANTARNOTIS FROM TBLPPKNOTISOBMST " +
	    	" WHERE ID_NOTISOBMST = '"+id_notisobmst_lama+"'";
	    myLogger.info(sqlInsert+sqlSelect);
	    stmt.executeUpdate(sqlInsert+sqlSelect);				
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private static void addNotisobdtl(HttpSession session,String id_notisobmst_lama,String ekptg_user_id,String setTempId) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			 
					
			sqlInsert = " INSERT INTO TBLPPKNOTISOBDTL (ID_NOTISOBDTL, ID_NOTISOBMST, ID_OB,NAMA_PENERIMA, NO_KP_BARU, ID_MASUK, "+
			" TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_DB, NO_KP_LAMA, NO_KP_LAIN) ";
	    sqlSelect = " SELECT "+setTempId+" +  ID_NOTISOBDTL, "+setTempId+" + ID_NOTISOBMST, "+setTempId+" +  ID_OB,NAMA_PENERIMA, NO_KP_BARU, '"+ekptg_user_id+"', "+
			" TARIKH_MASUK, '"+ekptg_user_id+"', TARIKH_KEMASKINI,ID_DB, NO_KP_LAMA, NO_KP_LAIN FROM TBLPPKNOTISOBDTL " +
	    	" WHERE ID_NOTISOBMST = '"+id_notisobmst_lama+"'";
	    myLogger.info(sqlInsert+sqlSelect);
	    stmt.executeUpdate(sqlInsert+sqlSelect);				
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	
	private static void addBayaran(HttpSession session,String id_bayaran_lama,String ekptg_user_id,String setTempId) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = " INSERT INTO TBLPPKBAYARAN ( ID_BAYARAN, ID_PERMOHONAN, ID_JENISBAYARAN, NO_RESIT, "+
		    " TARIKH_BAYARAN, JUMLAH_BAYARAN,ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,TARIKH_KEMASKINI, ID_DB) ";
		    sqlSelect = " SELECT "+setTempId+" + ID_BAYARAN, "+setTempId+" + ID_PERMOHONAN,ID_JENISBAYARAN, NO_RESIT, "+
		    " TARIKH_BAYARAN, JUMLAH_BAYARAN,'"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',TARIKH_KEMASKINI, ID_DB " +
		    " FROM TBLPPKBAYARAN WHERE ID_BAYARAN = '"+id_bayaran_lama+"' AND ID_JENISBAYARAN = '2' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private static void addSemakanHantar(HttpSession session,String id_semakanhantar_lama,String ekptg_user_id,String setTempId) throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = " INSERT INTO TBLSEMAKANHANTAR (ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, ID_PERMOHONAN, "+
				" PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, ID_DOKUMEN, "+
				" FLAG_ADA, ID_MASUK, TARIKH_MASUK,ID_KEMASKINI, TARIKH_KEMASKINI)  ";				
			sqlSelect = " SELECT "+setTempId+" + ID_SEMAKANHANTAR, ID_SEMAKANSENARAI, "+setTempId+" + ID_PERMOHONAN, "+
				" PEMOHON, PENTADBIR, STATUS,CATATAN, TARIKH_PELBAGAI, ID_DOKUMEN, "+
				" FLAG_ADA, '"+ekptg_user_id+"', TARIKH_MASUK,'"+ekptg_user_id+"', TARIKH_KEMASKINI" +
		    " FROM TBLPPKBAYARAN WHERE ID_SEMAKANHANTAR = '"+id_semakanhantar_lama+"'  ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	
	private static void addOb(HttpSession session,String id_permohonansimati_lama,String id_ob_lama,String ekptg_user_id,String setTempId)
	 throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = " INSERT INTO TBLPPKOB (ID_OB, ID_SIMATI, NAMA_OB, "+
				" NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, NO_SURAT_BERANAK, TARIKH_LAHIR, "+
				" JANTINA, UMUR, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, "+
				" ID_BANDAR, POSKOD, NO_HP, NO_TEL, CATATAN, STATUS_HIDUP, "+
				" ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB, JENIS_AGAMA, JENIS_WARGA, "+
				" ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
				" NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, JENIS_PEMIUTANG, "+
				" ID_PEMOHON, ID_PERMOHONANSIMATI, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
				" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, "+
				" TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU, ID_OBLAMA, "+
				" ID_ARB, NO_FAX, FLAG_DAFTAR)";			
			sqlSelect = " SELECT "+setTempId+" + ID_OB, "+setTempId+" + ID_SIMATI, NAMA_OB, "+
				" NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN, NO_SURAT_BERANAK, TARIKH_LAHIR, "+
				" JANTINA, UMUR, ALAMAT_1, ALAMAT_2, ALAMAT_3, BANDAR, "+
				" ID_BANDAR, POSKOD, NO_HP, NO_TEL, CATATAN, STATUS_HIDUP, "+
				" ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB, JENIS_AGAMA, JENIS_WARGA, "+
				" ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN, JENIS_WAKTU_KEMATIAN, STATUS_OB, "+
				" NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS, BUTIRAN_HUTANG, JENIS_PEMIUTANG, "+
				" ID_PEMOHON, "+setTempId+" + ID_PERMOHONANSIMATI, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT, ID_BANDARSURAT, "+
				" POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"', "+
				" TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU, ID_OBLAMA, "+
				" ID_ARB, NO_FAX, FLAG_DAFTAR " +
		    " FROM TBLPPKOB WHERE ID_OB = '"+id_ob_lama+"'";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private static void addObPermohonan(HttpSession session,String id_permohonansimati_lama,String id_ob_lama,String ekptg_user_id,String setTempId)
	 throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = " INSERT INTO TBLPPKOBPERMOHONAN (ID_OBPERMOHONAN, ID_PERMOHONANSIMATI, ID_OB, "+
			" ID_SIMATI, NAMA_OB, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,  "+
			" NO_SURAT_BERANAK, TARIKH_LAHIR, JANTINA, UMUR, ALAMAT_1, ALAMAT_2,  "+
			" ALAMAT_3, BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL,  "+
			" CATATAN, STATUS_HIDUP, ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB,  "+
			" JENIS_AGAMA, JENIS_WARGA, ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN,  "+
			" JENIS_WAKTU_KEMATIAN, STATUS_OB, NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS,  "+
			" BUTIRAN_HUTANG, JENIS_PEMIUTANG, ID_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT,  "+
			" ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, ID_MASUK, TARIKH_MASUK,  "+
			" ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU,  "+
			" ID_OBLAMA, ID_ARB, NO_FAX, FLAG_DAFTAR) ";			
			sqlSelect = " SELECT ID_OBPERMOHONAN, "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_OB, "+
			" "+setTempId+" + ID_SIMATI, NAMA_OB, NO_KP_BARU, NO_KP_LAMA, JENIS_KP, NO_KP_LAIN,  "+
			" NO_SURAT_BERANAK, TARIKH_LAHIR, JANTINA, UMUR, ALAMAT_1, ALAMAT_2,  "+
			" ALAMAT_3, BANDAR, ID_BANDAR, POSKOD, NO_HP, NO_TEL,  "+
			" CATATAN, STATUS_HIDUP, ID_TARAFKPTG, ID_NEGERI, ID_SAUDARA, ID_JENISPB,  "+
			" JENIS_AGAMA, JENIS_WARGA, ID_BANK, NO_AKAUN, TARIKH_MATI, WAKTU_KEMATIAN,  "+
			" JENIS_WAKTU_KEMATIAN, STATUS_OB, NILAI_HUTANG, BA_FARAID, BB_FARAID, LAPIS,  "+
			" BUTIRAN_HUTANG, JENIS_PEMIUTANG, ID_PEMOHON, ALAMAT1_SURAT, ALAMAT2_SURAT, ALAMAT3_SURAT,  "+
			" ID_BANDARSURAT, POSKOD_SURAT, NO_HP_SURAT, NO_TEL_SURAT, '"+ekptg_user_id+"', TARIKH_MASUK,  "+
			" '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB, BANDAR_SURAT, ID_NEGERISURAT, ID_PERAYU,  "+
			" ID_OBLAMA, ID_ARB, NO_FAX, FLAG_DAFTAR FROM TBLPPKOBPERMOHONAN " +
			" WHERE ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' AND ID_OB = '"+id_ob_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private static void addObHubungan(HttpSession session,String id_permohonansimati_lama,String id_ob_lama,String ekptg_user_id,String setTempId)
	 throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = " INSERT INTO TBLPPKHUBUNGANOB ( "+
			" ID_HUBUNGANOB, ID_OB, ID_PARENTOB, "+
			" ID_SAUDARA, ID_MASUK, TARIKH_MASUK, "+
			" ID_KEMASKINI, TARIKH_KEMASKINI, ID_DB)  ";			
			sqlSelect = " SELECT "+setTempId+" + ID_HUBUNGANOB, "+setTempId+" + ID_OB, "+setTempId+" + ID_PARENTOB, "+
			" ID_SAUDARA, '"+ekptg_user_id+"', TARIKH_MASUK, "+
			" '"+ekptg_user_id+"', TARIKH_KEMASKINI, ID_DB " +
			" FROM TBLPPKHUBUNGANOB " +
			" WHERE ID_OB = '"+id_ob_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	private static void addObHubunganPermohonan(HttpSession session,String id_permohonansimati_lama,String id_ob_lama,String ekptg_user_id,String setTempId)
	 throws Exception {		 	
	 	Connection conn = null;
		Db db = null;			
		String sqlInsert="";
		String sqlSelect="";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);				
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();					
			sqlInsert = "  INSERT INTO TBLPPKHUBUNGANOBPERMOHONAN ( "+
			"  ID_HUBUNGANOBPERMOHONAN, ID_PERMOHONANSIMATI, ID_HUBUNGANOB,  "+
			"  ID_MASUK, TARIKH_MASUK, ID_KEMASKINI,  "+
			"  TARIKH_KEMASKINI, ID_DB, ID_OB,  "+
			"  ID_PARENTOB, ID_SAUDARA)  ";			
			sqlSelect = " SELECT "+setTempId+" + ID_HUBUNGANOBPERMOHONAN, "+setTempId+" + ID_PERMOHONANSIMATI, "+setTempId+" + ID_HUBUNGANOB,  "+
			"  '"+ekptg_user_id+"', TARIKH_MASUK, '"+ekptg_user_id+"',  "+
			"  TARIKH_KEMASKINI, ID_DB, "+setTempId+" + ID_OB,  "+
			"  "+setTempId+" + ID_PARENTOB, ID_SAUDARA " +
			" FROM TBLPPKHUBUNGANOBPERMOHONAN " +
			" WHERE ID_OB = '"+id_ob_lama+"' AND ID_PERMOHONANSIMATI = '"+id_permohonansimati_lama+"' ";
		    myLogger.info(sqlInsert+sqlSelect);
		    stmt.executeUpdate(sqlInsert+sqlSelect);						
		conn.commit();
	} catch (SQLException se) {
		/*
		try {
			conn.rollback();
		} catch (SQLException se2) {
			throw new Exception("Rollback error:" + se2.getMessage());
		}
		se.printStackTrace();
		throw new Exception("Ralat Simpan Aduan:" + se.getMessage());*/
	} finally {
		if (conn != null)
			conn.close();
		if (db != null)
			db.close();
	}
				
	}
	
	
	public void deleteNotisData(HttpSession session,String id_fail,String id_permohonan,String seksyen,String ekptg_user_id) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		String sql11 = "";
	
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String no_fail_temp = "";
							
				sql11 = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";			
				ResultSet rs = stmt.executeQuery(sql11);	
				myLogger.info("SQL no_fail_temp :"+sql11);
				while (rs.next()){				
					no_fail_temp = rs.getString("NO_FAIL");				
			    }
			myLogger.info("no_fail_temp :"+no_fail_temp);
				
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			//set temp in ID_NOTISOBMST
			sql = " select distinct ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("set temp in ID_NOTISOBMST :"+sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			String ID_NOTISOBMST_TEMP = "";
			while (rs.next()){	
					 if(rs.getString("ID_NOTISOBMST")!= null && !rs.getString("ID_NOTISOBMST").equals(""))
					 {
						 ID_NOTISOBMST_TEMP += "'"+rs.getString("ID_NOTISOBMST")+"',";
					 }
			}
			ID_NOTISOBMST_TEMP += "'0000000'";
			myLogger.info("ID_NOTISOBMST_TEMP :::"+ID_NOTISOBMST_TEMP);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
							
			sql = " delete from tblppkperbicaraan where id_keputusanpermohonan in ( "+
			      " select id_keputusanpermohonan from TBLPPKKEPUTUSANPERMOHONAN where "+
	            " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			//RESET SUBURUSANSTATUSFAIL
			sql = "DELETE FROM TBLRUJSUBURUSANSTATUSFAIL WHERE ID_FAIL = '"+id_fail+"'";
			stmt.executeUpdate(sql);
			
			//RESET ID_STATUS
			sql = "UPDATE TBLPPKPERMOHONAN SET ID_STATUS = '53' WHERE ID_FAIL = '"+id_fail+"'";
			stmt.executeUpdate(sql);
			
			 //ADD STATUS NOTIS
		    String sqlInsert = "";
		    String sqlSelect = "";		    
		    sqlInsert = " INSERT INTO TBLRUJSUBURUSANSTATUSFAIL (ID_PERMOHONAN, ID_SUBURUSANSTATUS, "
			+"  AKTIF, ID_MASUK, TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI,ID_FAIL) ";
			if(seksyen.equals("8"))
			{
				sqlSelect = " VALUES ('"+id_permohonan+"','340','0'," +
	    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+id_fail+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				
				sqlSelect = " VALUES ('"+id_permohonan+"','353','0'," +
	    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+id_fail+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				
			    sqlSelect = " VALUES ('"+id_permohonan+"','406','1'," +
			    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+id_fail+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
			}
			else
			{
				sqlSelect = " VALUES ('"+id_permohonan+"','430','0'," +
	    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+id_fail+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				
				sqlSelect = " VALUES ('"+id_permohonan+"','434','0'," +
	    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+id_fail+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
				
			    sqlSelect = " VALUES ('"+id_permohonan+"','435','1'," +
			    		"'"+ekptg_user_id+"',sysdate,'"+ekptg_user_id+"',sysdate,'"+id_fail+"')";
				myLogger.info(sqlInsert+sqlSelect);
				stmt.executeUpdate(sqlInsert+sqlSelect);
			}
			
			
			
			conn.commit();				
			
			AuditTrail at = new AuditTrail();						
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT NOTIS PERBICARAAN DIHAPUSKAN");						
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT KEPUTUSAN PERBICARAAN DIHAPUSKAN");		
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT PERINTAH DIHAPUSKAN");
			
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
	public void deleteAllData(HttpSession session,String id_fail,String id_permohonan,String seksyen,String ekptg_user_id,String setTempId) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		String sql11 = "";
	
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			String no_fail_temp = "";
							
				sql11 = "SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL = '"+id_fail+"'";			
				ResultSet rs = stmt.executeQuery(sql11);	
				myLogger.info("SQL no_fail_temp :"+sql11);
				while (rs.next()){				
					no_fail_temp = rs.getString("NO_FAIL");				
			    }
			myLogger.info("no_fail_temp :"+no_fail_temp);
				
			
 			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHAOBdtl where ID_PERINTAHHAOBMST in ( "+
				  " select ID_PERINTAHHAOBMST from TBLPPKPERINTAHHAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBdtl where ID_PERINTAHHTAOBMST in ( "+
				  " select ID_PERINTAHHTAOBMST from TBLPPKPERINTAHHTAOBMST where "+
				  " id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBdtl :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAHHAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
			
			sql = " delete from TBLPPKPERINTAHHTAOBMST where id_perintah in ( "+
				  " select id_perintah from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  " select id_perbicaraan from tblppkperbicaraan a where "+
				  " id_keputusanpermohonan in ( "+
				  " select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  " id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
			myLogger.info("DELETE TBLPPKPERINTAHHTAOBMST :"+sql.toUpperCase());
			stmt.executeUpdate(sql);	
				
			sql = " delete from TBLPPKPERINTAH where id_perbicaraan in ( "+
				  "	select id_perbicaraan from tblppkperbicaraan a where "+
				  "	id_keputusanpermohonan in ( "+
				  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
				  "	id_permohonan in ( "+
				  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE TBLPPKPERINTAH :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			//set temp in ID_NOTISOBMST
			sql = " select distinct ID_NOTISOBMST from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("set temp in ID_NOTISOBMST :"+sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			String ID_NOTISOBMST_TEMP = "";
			while (rs.next()){	
					 if(rs.getString("ID_NOTISOBMST")!= null && !rs.getString("ID_NOTISOBMST").equals(""))
					 {
						 ID_NOTISOBMST_TEMP += "'"+rs.getString("ID_NOTISOBMST")+"',";
					 }
			}
			ID_NOTISOBMST_TEMP += "'0000000'";
			myLogger.info("ID_NOTISOBMST_TEMP :::"+ID_NOTISOBMST_TEMP);
			
			sql = " delete from tblppknotisperbicaraan where id_perbicaraan in ( "+
			  "	select id_perbicaraan from tblppkperbicaraan a where "+
			  "	id_keputusanpermohonan in ( "+
			  "	select id_keputusanpermohonan from tblppkkeputusanpermohonan where "+
			  "	id_permohonan in ( "+
			  "	select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  "	select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
			myLogger.info("DELETE tblppknotisperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);			
			
			sql = " delete from tblppknotisobDTL where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";		
			myLogger.info("DELETE tblppknotisobDTL :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			sql = " delete from tblppknotisobmst where ID_NOTISOBMST in ("+ID_NOTISOBMST_TEMP+")";
			myLogger.info("DELETE tblppknotisobmst :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
							
			sql = " delete from tblppkperbicaraan where id_keputusanpermohonan in ( "+
			      " select id_keputusanpermohonan from TBLPPKKEPUTUSANPERMOHONAN where "+
	            " id_permohonan in ( "+
			      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
			myLogger.info("DELETE tblppkperbicaraan :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
			
			sql = " delete from TBLPPKKEPUTUSANPERMOHONAN where id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
		myLogger.info("DELETE TBLPPKKEPUTUSANPERMOHONAN :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		sql = " delete from TBLPPKOBPILIHANHA where id_pilihanha in (" +
		" select id_pilihanha from tblppkpilihanha where id_ha in  (" +
		" select id_ha from tblppkha where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
		myLogger.info("DELETE tblppkobpilihanha :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
			
			sql = " delete from tblppkpilihanha where id_ha in  " +
					" (select id_ha from tblppkha where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
		myLogger.info("DELETE tblppkpilihanha :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		sql = " delete from TBLPPKOBPILIHANHTA where id_pilihanhta in (" +
				" select id_pilihanhta from tblppkpilihanhta where id_hta in  (" +
				" select id_hta from tblppkhta where id_permohonansimati in ( "+
	      " select id_permohonansimati from tblppkpermohonansimati where "+
	      " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
	      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))))";		
	myLogger.info("DELETE tblppkobpilihanhta :"+sql.toUpperCase());
	stmt.executeUpdate(sql);
		
		sql = " delete from tblppkpilihanhta where id_hta in  (select id_hta from tblppkhta where id_permohonansimati in ( "+
	      " select id_permohonansimati from tblppkpermohonansimati where "+
	      " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
	      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
	myLogger.info("DELETE tblppkpilihanhta :"+sql.toUpperCase());
	stmt.executeUpdate(sql);
		
		sql = " delete from tblppkhapermohonan where id_permohonansimati in ( "+
	      " select id_permohonansimati from tblppkpermohonansimati where "+
	      " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
	      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkhapermohonan :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
						
		sql = " delete from tblppkha where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
		      " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkha :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		sql = " delete from tblppkhtapermohonan where id_permohonansimati in ( "+
	      " select id_permohonansimati from tblppkpermohonansimati where "+
	      " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
	      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkhtapermohonan :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
			
		sql = " delete from tblppkhta where id_permohonansimati in ( "+
		      " select id_permohonansimati from tblppkpermohonansimati where "+
		      " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		      " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkhta :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		sql = " delete from tblppkhubunganobpermohonan where id_permohonansimati in" +
		"  (select id_permohonansimati from tblppkpermohonansimati where "+
		  " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkhubunganobpermohonan :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		sql = " delete from tblppkhubunganob where id_ob in" +
				"  (select id_ob from tblppkob where id_permohonansimati in ( "+
		  " select id_permohonansimati from tblppkpermohonansimati where "+
		  " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))))";		
		myLogger.info("DELETE tblppkhubunganob :"+sql.toUpperCase());
		stmt.executeUpdate(sql);	
		
		sql = " delete from tblppkobpermohonan where id_permohonansimati in ( "+
		  " select id_permohonansimati from tblppkpermohonansimati where "+
		  " id_permohonan in ( "+
		  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
		  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkobpermohonan :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
			
		sql = " delete from tblppkob where id_permohonansimati in ( "+
			  " select id_permohonansimati from tblppkpermohonansimati where "+
			  " id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppkob :"+sql.toUpperCase());
		stmt.executeUpdate(sql);	
		
		sql = " delete from tblsemakanhantar where id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
		myLogger.info("DELETE tblsemakanhantar :"+sql.toUpperCase());
		stmt.executeUpdate(sql);	
			
		sql = " delete from tblppkbayaran where id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
		myLogger.info("DELETE tblppkbayaran :"+sql.toUpperCase());
		stmt.executeUpdate(sql);	
			
		
		sql = "SELECT COUNT(*) AS TOTAL_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_SIMATI IN ( "+
		"	SELECT SM.ID_SIMATI FROM TBLPPKPERMOHONANSIMATI SM,TBLPPKPERMOHONAN P,TBLPFDFAIL F "+
		"	WHERE SM.ID_PERMOHONAN = P.ID_PERMOHONAN AND P.ID_FAIL = F.ID_FAIL AND F.ID_FAIL = '"+id_fail+"' ) ";		
		myLogger.info("SQL MAIN FAIL DETAIL :" + sql);			
		ResultSet rs1 = stmt.executeQuery(sql);
		Integer total_permohonansimati = 0;
		while (rs1.next()) {
			total_permohonansimati = rs1.getInt("TOTAL_PERMOHONANSIMATI");
		}
		
		if(total_permohonansimati == 1)
		{
		sql = " delete from tblppksimati where id_simati in ( "+
			  " select id_simati from tblppkpermohonansimati where id_permohonan in ( "+
			  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
			  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"'))))";		
		myLogger.info("DELETE tblppksimati :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		}
			
		
		
		sql = " delete from TBLPPKPEGUAMPEMOHON where id_pemohon in ( "+
		" select id_pemohon from tblppkpermohonan where id_fail in ( "+
		" select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
		myLogger.info("DELETE tblppkpemohon :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		
		sql = " delete from tblppkpermohonansimati where id_permohonan in ( "+
				  " select id_permohonan from tblppkpermohonan where id_fail in ( "+
				  " select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
			myLogger.info("DELETE tblppkpermohonansimati :"+sql.toUpperCase());
			stmt.executeUpdate(sql);
			
		
		if(total_permohonansimati == 1)
		{	
	    sql = " delete from tblppkpemohon where id_pemohon in ( "+
		" select id_pemohon from tblppkpermohonan where id_fail in ( "+
		" select id_fail from tblpfdfail where id_fail in ('"+id_fail+"')))";		
		myLogger.info("DELETE tblppkpemohon :"+sql.toUpperCase());
		stmt.executeUpdate(sql);	
		}
			
		sql = " delete from tblppkpermohonan where id_fail in ( "+
		" select id_fail from tblpfdfail where id_fail in "+
		" ('"+id_fail+"'))";		
		myLogger.info("DELETE tblppkpermohonan :"+sql.toUpperCase());
		stmt.executeUpdate(sql);			
		
		sql = " delete from tblrujsuburusanstatusfail where id_SUBURUSANSTATUSFAIL in "+
		      " (select ssf.ID_SUBURUSANSTATUSFAIL from tblrujsuburusanstatusfail ssf,tblrujsuburusanstatus ss,tblpfdfail f,tblrujstatus s "+
			  " where ssf.ID_SUBURUSANSTATUS = ss.ID_SUBURUSANSTATUS "+
			  " and ssf.ID_FAIL = f.id_fail "+
			  " and ss.ID_STATUS = s.id_status "+
			  " and f.id_fail = '"+id_fail+"' ";
		//sql +=" and s.id_status in ()";
		sql +=" ) ";
		myLogger.info("DELETE tblrujsuburusanstatusfail :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
		
		sql = " delete from tblpfdfail where id_fail in ('"+id_fail+"')";		
		myLogger.info("DELETE tblpfdfail :"+sql.toUpperCase());
		stmt.executeUpdate(sql);
	
		
	
			
			conn.commit();				
			
			AuditTrail at = new AuditTrail();	
						
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT PENDAFTARAN DIHAPUSKAN");						
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT KEPUTUSAN PERMOHONAN APUSKAN");			
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT NOTIS PERBICARAAN DIHAPUSKAN");						
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT KEPUTUSAN PERBICARAAN DIHAPUSKAN");		
			at.logActivity("","2",null,session,"DEL","FAIL ["+no_fail_temp+"] SEMUA MAKLUMAT PERINTAH DIHAPUSKAN");
			
			
			
		} finally {
			if (db != null)
				db.close();
		}
	}
	

		public void updateHapusFail(HttpSession session,String id_fail) throws Exception {
		Db db = null;
		Connection conn = null;
		String sql = "";
		
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				conn = db.getConnection();
				conn.setAutoCommit(false);			
				sql = "UPDATE TBLPPKPERMOHONAN SET ID_STATUS = '999' WHERE ID_FAIL = '"+id_fail+"'";
				stmt.executeUpdate(sql);	
				
				sql = "UPDATE TBLRUJSUBURUSANSTATUSFAIL SET AKTIF = '0' WHERE ID_FAIL = '"+id_fail+"'";
				stmt.executeUpdate(sql);
				conn.commit();				
		
		
			} finally {
				if (db != null)
					db.close();
			}
		}
		

		private void headerppk_baru(HttpSession session, String id_permohonan,
				String flag_permohonan, String id_fail, String flag_fail)
				throws Exception {
			// hashtable maklumat header
			this.context.put("headerppk", mainheader.getHeaderData(session,
					id_permohonan, flag_permohonan, id_fail, flag_fail));
			Vector list_sub = null;
			list_sub = mainheader.carianFail(id_permohonan, flag_permohonan,
					id_fail, flag_fail);
			this.context.put("list_sub_header", list_sub);
			this.context.put("flag_jenis_vm", "utiliti_ajax");
		}

		private void headerppk_baru_default() {
			Hashtable headerppk = null;
			this.context.put("headerppk", "");
			this.context.put("list_sub_header", "");
			this.context.put("flag_jenis_vm", "utiliti_ajax");
		}

}

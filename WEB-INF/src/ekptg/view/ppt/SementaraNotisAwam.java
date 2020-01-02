package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiNotisAwamData;

public class SementaraNotisAwam extends AjaxBasedModule{

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        context.put("action",action);
        String tarikhmohon = "";
    	String nofail = "";    
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String mode = getParam("mode");
		context.put("mode",mode);
		String id_NotisAwam = getParam("id_NotisAwam");
    	FrmHakmilikSementaraSenaraiNotisAwamData listNotisAwam = new FrmHakmilikSementaraSenaraiNotisAwamData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
				
    	Vector list = null;
    	Vector carianFail = null;
    	Vector listAgensi = null;
    	Vector paparNotis = null;
    	Vector jenisTempatTampal = null;
    	
    	HttpSession session = this.request.getSession();
    	
    	if ("newNotis".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraNotisAwam.jsp";
    		context.put("mode", "newNotis");
    		context.put("readonly", "");
    		context.put("disabled","");
    		context.put("disabledJenisTampal", "");
    		
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
			
			context.put("JENIS_TEMPAT_TAMPAL","0");
			context.put("TEMPAT","");
    		context.put("TARIKH_TAMPAL","");
			
			
			listNotisAwam.listTampalNotis(id_permohonan);		
			list = listNotisAwam.getListNotis();					
		    context.put("TampalNotisList", list);
		    
		    
		    jenisTempatTampal = listNotisAwam.jenisTempatTampal(id_permohonan); 
		    for(int i = 0; i < jenisTempatTampal.size(); i++){
		    	
		    	Hashtable hJ = (Hashtable)jenisTempatTampal.get(i);
		    	
		    	if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("1")){
		    		context.put("PTG", "1");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("2")){
		    		context.put("PAPAN_KENYATAAN", "2");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("3")){
		    		context.put("ATAS_TANAH", "3");
		    	}	
		    }
    		
    	}
    	else if ("simpanNotis".equals(action)){
    		
    		String idNotisAwam = simpanNotis(session);
    		vm = "app/ppt/frmHakmilikSementaraNotisAwam.jsp";
    		context.put("mode", "newNotis");
    		context.put("readonly", "");
    		context.put("disabled","");
    		context.put("disabledJenisTampal", "");
    		
    		context.put("JENIS_TEMPAT_TAMPAL","0");
			context.put("TEMPAT","");
    		context.put("TARIKH_TAMPAL","");
  		
			listNotisAwam.listTampalNotis(id_permohonan);		
			list = listNotisAwam.getListNotis();
		    context.put("TampalNotisList", list);
		    
		    jenisTempatTampal = listNotisAwam.jenisTempatTampal(id_permohonan); 
		    for(int i = 0; i < jenisTempatTampal.size(); i++){
		    	
		    	Hashtable hJ = (Hashtable)jenisTempatTampal.get(i);
		    	
		    	if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("1")){
		    		context.put("PTG", "1");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("2")){
		    		context.put("PAPAN_KENYATAAN", "2");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("3")){
		    		context.put("ATAS_TANAH", "3");
		    	}	
		    }
		    
		  
		    
    	}
    	else if ("viewNotis".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraNotisAwam.jsp";
    		context.put("mode", "viewNotis");
    		context.put("readonly", "readonly");
    		context.put("disabled","disabled");
    		context.put("disabledJenisTampal", "disabled");
    		
    		listNotisAwam.paparNotis(id_NotisAwam);
    		paparNotis = listNotisAwam.getPaparNotis();
    		Hashtable h = (Hashtable)paparNotis.get(0);
    		context.put("ID_NOTISAWAM",h.get("ID_NOTISAWAM"));
    		context.put("TEMPAT",h.get("TEMPAT"));
    		context.put("TARIKH_TAMPAL",h.get("TARIKH_TAMPAL"));
    		context.put("JENIS_TEMPAT_TAMPAL",h.get("JENIS_TEMPAT_TAMPAL"));
    		
    		  jenisTempatTampal = listNotisAwam.jenisTempatTampal(id_permohonan); 
  		    for(int i = 0; i < jenisTempatTampal.size(); i++){
  		    	
  		    	Hashtable hJ = (Hashtable)jenisTempatTampal.get(i);
  		    	
  		    	if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("1")){
  		    		context.put("PTG", "1");
  		    	}
  		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("2")){
  		    		context.put("PAPAN_KENYATAAN", "2");
  		    	}
  		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("3")){
  		    		context.put("ATAS_TANAH", "3");
  		    	}	
  		    }
    		
    		
    	}
    	else if ("updateNotis".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraNotisAwam.jsp";
    		context.put("mode", "updateNotis");
    		context.put("readonly", "");
    		context.put("disabled","");
    		context.put("disabledJenisTampal", "disabled");
    		
    		listNotisAwam.paparNotis(id_NotisAwam);
    		paparNotis = listNotisAwam.getPaparNotis();
    		Hashtable h = (Hashtable)paparNotis.get(0);
    		context.put("ID_NOTISAWAM",h.get("ID_NOTISAWAM"));
    		context.put("TEMPAT",h.get("TEMPAT"));
    		context.put("TARIKH_TAMPAL",h.get("TARIKH_TAMPAL"));
    		context.put("JENIS_TEMPAT_TAMPAL",h.get("JENIS_TEMPAT_TAMPAL"));
    		
    		 jenisTempatTampal = listNotisAwam.jenisTempatTampal(id_permohonan); 
 		    for(int i = 0; i < jenisTempatTampal.size(); i++){
 		    	
 		    	Hashtable hJ = (Hashtable)jenisTempatTampal.get(i);
 		    	
 		    	if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("1")){
 		    		context.put("PTG", "1");
 		    	}
 		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("2")){
 		    		context.put("PAPAN_KENYATAAN", "2");
 		    	}
 		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("3")){
 		    		context.put("ATAS_TANAH", "3");
 		    	}	
 		    }
    		
    		
    		
    	}
    	else if ("kemaskiniNotis".equals(action)){
    		
    		updateNotisAwam(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraNotisAwam.jsp";
    		context.put("mode", "viewNotis");
    		context.put("readonly", "readonly");
    		context.put("disabled","disabled");
    		context.put("disabledJenisTampal", "disabled");
    		
    		listNotisAwam.paparNotis(id_NotisAwam);
    		paparNotis = listNotisAwam.getPaparNotis();
    		Hashtable h = (Hashtable)paparNotis.get(0);
    		context.put("ID_NOTISAWAM",h.get("ID_NOTISAWAM"));
    		context.put("TEMPAT",h.get("TEMPAT"));
    		context.put("TARIKH_TAMPAL",h.get("TARIKH_TAMPAL"));
    		context.put("JENIS_TEMPAT_TAMPAL",h.get("JENIS_TEMPAT_TAMPAL"));
    		
    		listNotisAwam.listTampalNotis(id_permohonan);		
			list = listNotisAwam.getListNotis();
		    context.put("TampalNotisList", list);	
		    
		    jenisTempatTampal = listNotisAwam.jenisTempatTampal(id_permohonan); 
		    for(int i = 0; i < jenisTempatTampal.size(); i++){
		    	
		    	Hashtable hJ = (Hashtable)jenisTempatTampal.get(i);
		    	
		    	if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("1")){
		    		context.put("PTG", "1");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("2")){
		    		context.put("PAPAN_KENYATAAN", "2");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("3")){
		    		context.put("ATAS_TANAH", "3");
		    	}	
		    }
    		
    		
    	}
    	else if ("hapusNotis".equals(action)){
    		
    		deleteNotisAwam(session);
    		context.put("mode", "newNotis");
    		context.put("readonly", "");
    		context.put("disabled","");
    		context.put("disabledJenisTampal", "");
    		vm = "app/ppt/frmHakmilikSementaraNotisAwam.jsp";
			
    		context.put("ID_NOTISAWAM","");
    		context.put("TEMPAT","");
    		context.put("TARIKH_TAMPAL","");
    		context.put("JENIS_TEMPAT_TAMPAL","");
			
			
			listNotisAwam.listTampalNotis(id_permohonan);		
			list = listNotisAwam.getListNotis();				
		    context.put("TampalNotisList", list);
		    
		    jenisTempatTampal = listNotisAwam.jenisTempatTampal(id_permohonan); 
		    for(int i = 0; i < jenisTempatTampal.size(); i++){
		    	
		    	Hashtable hJ = (Hashtable)jenisTempatTampal.get(i);
		    	
		    	if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("1")){
		    		context.put("PTG", "1");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("2")){
		    		context.put("PAPAN_KENYATAAN", "2");
		    	}
		    	else if(hJ.get("JENIS_TEMPAT_TAMPAL").equals("3")){
		    		context.put("ATAS_TANAH", "3");
		    	}	
		    }
    		
    	}
    	else{
    		 	vm = "app/ppt/frmHakmilikSementaraSenaraiNotisAwam.jsp";
    			
    			if (!"".equals(getParam("txdTarikh")));
    				tarikhmohon = getParam("txdTarikh");
    		
    			if (!"".equals(getParam("txtNoFail")));
    				nofail = getParam("txtNoFail");
    			
    			if(!"".equals(getParam("socStatus")))
    				cStatus = getParam("socStatus");
    			
    			listNotisAwam.setCarianFail(nofail, tarikhmohon, cStatus);		
    			carianFail = listNotisAwam.getList();
    								
    		
    		    context.put("PermohonanList", carianFail);
    		    context.put("list_size", carianFail.size());
    		    context.put("CarianFail", nofail);  
    		    context.put("tarikhPermohonan", tarikhmohon);
    		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    		    setupPage(session,action,carianFail);
    	}
       
		
		
		return vm;
	}
	private String simpanNotis(HttpSession session) throws Exception {
		// add data dalam table notis awam (tempat tampal = PTGPTD)
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan= getParam("id_permohonan");
		
		h.put("idPermohonan",idPermohonan);
		h.put("jenisTempatTampal",getParam("socTempatTampal"));
		h.put("tempat",getParam("txtTempat"));
		h.put("tarikhTampal",getParam("txdTarikhTampal"));
		
		
		return FrmHakmilikSementaraSenaraiNotisAwamData.simpanNotis(h);
	}
	private void updateNotisAwam(HttpSession session) throws Exception{
		// update table notis awam jika berlaku kemaskini - PTG
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idNotisAwam= getParam("id_NotisAwam");
		
		h.put("idNotisAwam",idNotisAwam);
		h.put("jenisTempatTampal",getParam("socTempatTampal"));
		h.put("tempat",getParam("txtTempat"));
		h.put("tarikhTampal",getParam("txdTarikhTampal"));
		
		FrmHakmilikSementaraSenaraiNotisAwamData.updateNotis(h);
		
	}
	private void deleteNotisAwam(HttpSession session)throws Exception {
		// delete row Atas Tanah
		String idNotisAwam= getParam("id_NotisAwam");
		
		FrmHakmilikSementaraSenaraiNotisAwamData.deleteNotisAwam(idNotisAwam);
		
	}
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

}

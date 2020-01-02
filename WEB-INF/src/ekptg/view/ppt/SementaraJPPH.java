package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiJPPHData;

public class SementaraJPPH extends AjaxBasedModule{
	
	static Logger myLogger = Logger.getLogger(SementaraJPPH.class);
	FrmHakmilikSementaraSenaraiJPPHData logic = new FrmHakmilikSementaraSenaraiJPPHData();
	FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();

	public String doTemplate2() throws Exception {

		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        String mode = getParam("mode");
        String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String idulasanteknikal = getParam("idUlasanTeknikal");
		context.put("idUlasanTeknikal",idulasanteknikal);
				
		vm = "app/ppt/frmHakmilikSementaraSenaraiJPPH.jsp";
		HttpSession session = request.getSession();

    	Vector list = null;
    	Vector listAgensi = null;
    	Vector view_JPPH = null;   
    	
    	if("newJPPH".equals(action)) {
    		myLogger.info("NEW");
    		vm = "app/ppt/frmHakmilikSementaraJPPH.jsp";  				
    		
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
			view_JPPH = logic.papar_JPPH(id_permohonan);
			
			
			if(view_JPPH.size() == 0) {
				
				context.put("readmode", "new");
				context.put("readonlymode","");
				context.put("disabledmode","");
				
				context.put("bilSurat", "");
				context.put("tarikhSurat", "");
				context.put("tarikhAkhir", "");
//				context.put("kodPejabat", "");
				context.put("noRujSuratJPPH", "");
				context.put("tarikhSuratJPPH", "");
				context.put("tarikhTerima", "");
				
			}
				
			else {
				Hashtable hJ = (Hashtable)view_JPPH.get(0);
				
				if (hJ.get("KOD_PEJABAT_JPPH")== null || hJ.get("KOD_PEJABAT_JPPH") == " "){
					context.put("readmode", "new");
					context.put("readonlymode","");
					context.put("disabledmode","");
					
					context.put("bilSurat", "");
					context.put("tarikhSurat", "");
					context.put("tarikhAkhir", "");
//					context.put("kodPejabat", "");
					context.put("noRujSuratJPPH", "");
					context.put("tarikhSuratJPPH", "");
					context.put("tarikhTerima", "");
				}
				else{
					
					context.put("readmode", "view");
					context.put("readonlymode","readonly");
					context.put("disabledmode","disabled");
					
					context.put("idUlasanTeknikal",hJ.get("ID_ULASANTEKNIKAL"));
					context.put("bilSurat", hJ.get("BIL_SURAT"));
					context.put("tarikhSurat",  hJ.get("TARIKH_SURAT"));
					context.put("tarikhAkhir",  hJ.get("TARIKH_AKHIR"));
//					context.put("kodPejabat",  hJ.get("KOD_PEJABAT_JPPH"));
					context.put("noRujSuratJPPH",  hJ.get("NO_RUJUKANSURATJT"));
					context.put("tarikhSuratJPPH",  hJ.get("TARIKH_TERIMAJT"));
					context.put("tarikhTerima",  hJ.get("TARIKH_SURATJT"));
					
				}
				
			}
    	}
    	
    	else if ("add".equals(action)) {  
    		String idUlasanTeknikal = addJPPH(session);
    			
			
			vm = "app/ppt/frmHakmilikSementaraJPPH.jsp";
			context.put("readmode","view");
			context.put("readonlymode","readonly");
			context.put("disabledmode","disabled");
			
			view_JPPH = logic.papar_JPPH(id_permohonan);
			Hashtable hJ = (Hashtable)view_JPPH.get(0);
			context.put("idUlasanTeknikal",hJ.get("ID_ULASANTEKNIKAL"));
			context.put("bilSurat", hJ.get("BIL_SURAT"));
			context.put("tarikhSurat",  hJ.get("TARIKH_SURAT"));
			context.put("tarikhAkhir",  hJ.get("TARIKH_AKHIR"));
//			context.put("kodPejabat",  hJ.get("KOD_PEJABAT_JPPH"));
			context.put("noRujSuratJPPH",  hJ.get("NO_RUJUKANSURATJT"));
			context.put("tarikhSuratJPPH",  hJ.get("TARIKH_TERIMAJT"));
			context.put("tarikhTerima",  hJ.get("TARIKH_SURATJT"));

		
    	}
    	
    	else if ("kemaskini".equals(action)) {

			vm = "app/ppt/frmHakmilikSementaraJPPH.jsp";	
			context.put("readmode","update");
			context.put("readonlymode","");
			context.put("disabledmode","");
			
			view_JPPH = logic.papar_JPPH(id_permohonan);
			Hashtable hJ = (Hashtable)view_JPPH.get(0);
			context.put("bilSurat", hJ.get("BIL_SURAT"));
			context.put("tarikhSurat",  hJ.get("TARIKH_SURAT"));
			context.put("tarikhAkhir",  hJ.get("TARIKH_AKHIR"));
//			context.put("kodPejabat",  hJ.get("KOD_PEJABAT_JPPH"));
			context.put("noRujSuratJPPH",  hJ.get("NO_RUJUKANSURATJT"));
			context.put("tarikhSuratJPPH",  hJ.get("TARIKH_TERIMAJT"));
			context.put("tarikhTerima",  hJ.get("TARIKH_SURATJT"));

	    }
    	
    	else if("update".equals(action))
    	{
    		
    		updateJPPH(session);
    		vm = "app/ppt/frmHakmilikSementaraJPPH.jsp";	
			context.put("readmode","view");
			context.put("readonlymode","readonly");
			context.put("disabledmode","disabled");
			
			view_JPPH = logic.papar_JPPH(id_permohonan);
			Hashtable hJ = (Hashtable)view_JPPH.get(0);
			context.put("bilSurat", hJ.get("BIL_SURAT"));
			context.put("tarikhSurat",  hJ.get("TARIKH_SURAT"));
			context.put("tarikhAkhir",  hJ.get("TARIKH_AKHIR"));
//			context.put("kodPejabat",  hJ.get("KOD_PEJABAT_JPPH"));
			context.put("noRujSuratJPPH",  hJ.get("NO_RUJUKANSURATJT"));
			context.put("tarikhSuratJPPH",  hJ.get("TARIKH_TERIMAJT"));
			context.put("tarikhTerima",  hJ.get("TARIKH_SURATJT"));
    		
    	}
    		
    	else if ("hapus".equals(action)) {

    			logic.hapus_JPPH(idulasanteknikal);
    			vm = "app/ppt/frmHakmilikSementaraJPPH.jsp";
    			context.put("readmode", "new");
				context.put("readonlymode","");
				context.put("disabledmode","");
				
				context.put("bilSurat", "");
				context.put("tarikhSurat", "");
				context.put("tarikhAkhir", "");
//				context.put("kodPejabat", "");
				context.put("noRujSuratJPPH", "");
				context.put("tarikhSuratJPPH", "");
				context.put("tarikhTerima", "");

    			
    		
	    }
    	
    	else if("kosongkan".equals(action)) {
    		    		
    		vm = "app/ppt/frmHakmilikSementaraSenaraiJPPH.jsp";
    		  		
    		logic.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG);		
    		list = logic.getList();
    							
    	
    	    context.put("PermohonanList", list);
    	    context.put("list_size", list.size());
    	    context.put("CarianFail", "");  
    	    context.put("CarianNoJKPTG","" );
    	    context.put("tarikhPermohonan", tarikhmohon);
    	    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",null,"style=width:130px"));
    	    setupPage(session,action,list);
    	}
    	
    	else {
    		    		
    		if (!"".equals(getParam("txdTarikh")));
    		tarikhmohon = getParam("txdTarikh");
    	
    		if (!"".equals(getParam("txtNoFail")));
    			nofail = getParam("txtNoFail");
    			
    		if (!"".equals(getParam("txtNoRujJKPTGNegeri")));
    			noJKPTG = getParam("txtNoRujJKPTGNegeri");
    		
    		if(!"".equals(getParam("socStatus")))
    			cStatus = getParam("socStatus");
    		
    		logic.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG);		
    		list = logic.getList();
    							
    	
    	    context.put("PermohonanList", list);
    	    context.put("list_size", list.size());
    	    context.put("CarianFail", nofail);  
    	    context.put("CarianNoJKPTG",noJKPTG );
    	    context.put("tarikhPermohonan", tarikhmohon);
    	    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    	    setupPage(session,action,list);
    	}
    	
		return vm;
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
			
		private String addJPPH(HttpSession session) throws Exception{
			
			Hashtable h = new Hashtable();	
			
						
//			h.put("txtKodJPPH",getParam("txtKodJPPH"));
			h.put("txtBilSurat",getParam("txtBilSurat"));
			h.put("txdTkhSurat",getParam("txdTkhSurat"));
			h.put("txdTkhAkhirJwpnDiterima",getParam("txdTkhAkhirJwpnDiterima"));
			h.put("txtNoRujSuratJPPH",getParam("txtNoRujSuratJPPH"));
			h.put("txdTkhTerima",getParam("txdTkhTerima"));
			h.put("txdTkhSuratJPPH",getParam("txdTkhSuratJPPH"));
			h.put("id_permohonan",getParam("id_permohonan"));
			h.put("id_Masuk", (String) session.getAttribute("_ekptg_user_id"));
			return logic.addJPPH(h);
		
	}
		private void updateJPPH(HttpSession session) throws Exception {
			Hashtable h = new Hashtable();
			h.put("idUlasanTeknikal", getParam("idUlasanTeknikal"));
//			h.put("txtKodJPPH", getParam("txtKodJPPH"));
			h.put("txtBilSurat", getParam("txtBilSurat"));
			h.put("txdTkhSurat", getParam("txdTkhSurat"));
			h.put("txdTkhAkhirJwpnDiterima", getParam("txdTkhAkhirJwpnDiterima"));
			h.put("txtNoRujSuratJPPH", getParam("txtNoRujSuratJPPH"));
			h.put("txdTkhTerima", getParam("txdTkhTerima"));
			h.put("txdTkhSuratJPPH", getParam("txdTkhSuratJPPH"));
			h.put("id_Kemaskini", (String) session.getAttribute("_ekptg_user_id"));
			logic.updateJPPH(h);
			
		}

}

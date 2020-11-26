
package ekptg.view.ppk;

import integrasi.rest.etanah.wpkl.ppk.EtanahWPKLPPKManager;
import integrasi.ws.etanah.melaka_ns.ppk.EtanahN9MelPPKManager;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.ppk.FrmPopupCapaianHakmilikEtanahData;

/**
 * @author mohd faizal
 */
public class FrmPopupCapaianHakmilikEtanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPopupCapaianHakmilikEtanahData logic = new FrmPopupCapaianHakmilikEtanahData();	

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String idPengguna = (String)session.getAttribute("_ekptg_user_id");

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPopup = getParam("actionPopup");
		String hitButt = getParam("hitButt");
		String submit = getParam("command");
		String idPermohonanSimati = getParam("idPermohonanSimati");
		
		String noResit = getParam("txtNoResit").trim();
		String idHakmilik = getParam("txtHakmilik").trim();
		
		//VECTOR
        Vector list = null;
        Vector beanMaklumatHakmilik = null;
        
        this.context.put("flagMsg", "");
		this.context.put("outputMsg", "");	

		if (hitButt.equals("hantar")){
			String kodNegeri = idHakmilik.substring(0, 2);
			if ("04".equals(kodNegeri) || "05".equals(kodNegeri)){
				EtanahN9MelPPKManager manager = new EtanahN9MelPPKManager();
				manager.getMaklumatHakmilikFromEtanah(idPermohonanSimati, noResit, idHakmilik, kodNegeri);
				this.context.put("flagMsg", manager.getFlagMsg());
				this.context.put("outputMsg", manager.getOutputMsg());
			
			}else if ("14".equals(kodNegeri)){
				EtanahWPKLPPKManager manager = new EtanahWPKLPPKManager();
				manager.getMaklumatHakmilikFromEtanah(idPermohonanSimati, noResit, idHakmilik, idPengguna);
				this.context.put("flagMsg", manager.getFlagMsg());
				this.context.put("outputMsg", manager.getOutputMsg());
			
			}
			
		}	
		
		if (hitButt.equals("daftar")){
			logic.daftarHakmilik(getParam("idPPKHTA"), getParam("noResit"), getParam("idHakmilik"), idPermohonanSimati, session);
		}
		
		if (actionPopup.equals("papar")){
			
			vm = "app/ppk/frmPopupMaklumatHakmilikEtanah.jsp";
			
			beanMaklumatHakmilik = new Vector();
			logic.setMaklumatHakmilik(getParam("idPPKHTA"));
			beanMaklumatHakmilik = logic.getBeanMaklumatHakmilik();
			this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
			
		} else {
			
			vm = "app/ppk/frmPopupCapaianHakmilikEtanah.jsp";		
			
			list = new Vector();
			logic.senaraiCarian(idPermohonanSimati);
			list = logic.getSenaraiHakmilik();
			this.context.put("SenaraiCarianRasmi", list);
			
			setupPage(session,action,list);
		}
		
		this.context.put("txtNoResit", noResit);
		this.context.put("txtHakmilik", idHakmilik);
		this.context.put("idPermohonanSimati", idPermohonanSimati);			
		
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
				this.context.put("SenaraiTanah",paging.getPage(page));
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

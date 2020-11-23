
package ekptg.view.integrasi.etanah;

import integrasi.rest.etanah.wpkl.ppk.EtanahWPKLPPKManager;
import integrasi.ws.etanah.ppt.ETanahCarianManager;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.Paging;
import ekptg.model.integrasi.CapaianHakmilikeTanahHTP;
import ekptg.model.integrasi.CapaianHakmilikeTanahPPK;
import ekptg.model.integrasi.FrmPopupCapaianHakmilikeTanahData;
import ekptg.model.integrasi.IIntegrasieTanahCarian;

/**
 * @author mohamad Rosli
 */
public class FrmPopupCapaianHakmilikeTanah extends AjaxBasedModule {

	private static final long serialVersionUID = 2124227445129379348L;
	static Logger myLog = Logger.getLogger(FrmPopupCapaianHakmilikeTanah.class);
	
 	private IIntegrasieTanahCarian carianHTP = null;  
 	private IIntegrasieTanahCarian carianPPK = null;  
 	FrmPopupCapaianHakmilikeTanahData logic = new FrmPopupCapaianHakmilikeTanahData();	

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		String idPengguna = (String)session.getAttribute("_ekptg_user_id");

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPopup = getParam("actionPopup");
		String hitButt = getParam("hitButt");
//		String submit = getParam("command");
		String idPermohonan = getParam("idPermohonan");
		
		String noResit = getParam("txtNoResit").trim();
		String idHakmilik = getParam("txtHakmilik").trim();
		// akses css
		String modul = getParam("modul");
		this.context.put("modul", modul);

		//VECTOR
        Vector<Hashtable<String,String>> list = null;
        Vector beanMaklumatHakmilik = null;
        
        this.context.put("flagMsg", "");
		this.context.put("outputMsg", "");	
		myLog.info("actionPopup="+actionPopup+",hitButt="+hitButt);
		myLog.info("modul="+modul+",idPermohonan="+idPermohonan);

		if (hitButt.equals("hantar")){
			String kodNegeri = idHakmilik.substring(0, 2);
			if ("04".equals(kodNegeri) || "05".equals(kodNegeri)){
				ETanahCarianManager cm = new ETanahCarianManager("E-TANAH");
				if(modul.equals("htp")) {
					ETanahCarianManager.getMaklumatHakmilikeTanahHTP(noResit, idHakmilik, idPermohonan, kodNegeri);
				}else if (modul.equals("ppk")){
					ETanahCarianManager.getMaklumatHakmilikeTanahPPK(noResit, idHakmilik, idPermohonan, kodNegeri);
				}else if (modul.equals("ppt")) {
					ETanahCarianManager.getMaklumatHakmilikFromEtanah(noResit, idHakmilik, idPermohonan, kodNegeri);
				}
				
				this.context.put("flagMsg", ETanahCarianManager.getFlagMsg());
				this.context.put("outputMsg", ETanahCarianManager.getOutputMsg());
				
//				EtanahN9MelPPKManager manager = new EtanahN9MelPPKManager();
//				manager.getMaklumatHakmilikFromEtanah(idPermohonanSimati, noResit, idHakmilik, kodNegeri);
//				this.context.put("flagMsg", manager.getFlagMsg());
//				this.context.put("outputMsg", manager.getOutputMsg());
			}
			if ("14".equals(kodNegeri)){
				EtanahWPKLPPKManager manager = new EtanahWPKLPPKManager();
				manager.getMaklumatHakmilikFromEtanah(idPermohonan, noResit, idHakmilik, idPengguna);
				this.context.put("flagMsg", manager.getFlagMsg());
				this.context.put("outputMsg", manager.getOutputMsg());
			}			
		
		}else if (hitButt.equals("daftar")){
			String idHarta = getParam("idPPKHTA");
			if(modul.equals("htp")) {
				getCarianHTP().daftarHakmilik(idHarta, getParam("noResit"), getParam("idHakmilik"), idPermohonan, idPengguna);
			}else if (modul.equals("ppk")) { 
				getCarianPPK().daftarHakmilik(idHarta, getParam("noResit"), getParam("idHakmilik"), idPermohonan, idPengguna);
			}else if (modul.equals("ppt")) { 
				logic.daftarHakmilik(idHarta, getParam("noResit"), getParam("idHakmilik"), idPermohonan, idPengguna);
			}
			
		}
		
		if (actionPopup.equals("papar")){			
			vm = "app/integrasi/etanah/frmPopupMaklumatHakmilikeTanah.jsp";
			idHakmilik = getParam("idPPKHTA");
			
			Hashtable<String,String> mt = null;
			if(modul.equals("htp")) {
				mt = getCarianHTP().setMaklumatHakmilik(idHakmilik);
				beanMaklumatHakmilik = getCarianHTP().getBeanMaklumatHakmilik();
			
			}else if (modul.equals("ppk")) { 
				mt = getCarianPPK().setMaklumatHakmilik(idHakmilik);
				beanMaklumatHakmilik = getCarianPPK().getBeanMaklumatHakmilik();
			
			}else if (modul.equals("ppt")) { 
//				mt = getCarianPPK().setMaklumatHakmilik(getParam("idPPKHTA"));
//				beanMaklumatHakmilik = getCarianPPK().getBeanMaklumatHakmilik();
//				beanMaklumatHakmilik = new Vector();
				mt = logic.setMaklumatHakmilik(idHakmilik);
				beanMaklumatHakmilik = logic.getBeanMaklumatHakmilik();			
			
			}

			this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
			
		} else {			
			vm = "app/integrasi/etanah/frmPopupCapaianHakmilikeTanah.jsp";		
			
			list = new Vector<Hashtable<String,String>>();
			list = logic.senaraiCarian(idPermohonan);
			//list = logic.getSenaraiHakmilik();
			this.context.put("SenaraiCarianRasmi", list);
			
			setupPage(session,action,list);
			
		}
		
		this.context.put("txtNoResit", noResit);
		this.context.put("txtHakmilik", idHakmilik);
		this.context.put("idPermohonan", idPermohonan);			
		
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
		  
	private IIntegrasieTanahCarian getCarianHTP(){
		if(carianHTP== null)
			carianHTP = new CapaianHakmilikeTanahHTP();
		return carianHTP;
	}
	private IIntegrasieTanahCarian getCarianPPK(){
		if(carianPPK== null)
			carianPPK = new CapaianHakmilikeTanahPPK();
		return carianPPK;
	}


}

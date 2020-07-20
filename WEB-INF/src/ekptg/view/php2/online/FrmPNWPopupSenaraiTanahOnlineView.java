/**
 * 
 */
package ekptg.view.php2.online;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.online.FrmPNWPopupSenaraiTanahOnlineData;


public class FrmPNWPopupSenaraiTanahOnlineView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPNWPopupSenaraiTanahOnlineData logic = new FrmPNWPopupSenaraiTanahOnlineData();
	static Logger myLog = Logger.getLogger(FrmTKROnlinePopupSenaraiTanahView.class);

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String submit = getParam("command");
	    String idHakmilikAgensi = getParam("idHakmilikAgensi");
	    String idKategoriPemohon = getParam("idKategoriPemohon");
	    String idKementerian = getParam("idKementerian");
	    String idAgensi = getParam("idAgensi");
	    
	    //VECTOR
        Vector list = null;        
        Vector beanMaklumatTanah = null;
        Vector beanMaklumatAgensi = null;
	    
	    //GET DROPDOWN PARAM
        String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}
		String jenisHakmilik = getParam("socJenisHakmilik");
		if (jenisHakmilik == null || jenisHakmilik.trim().length() == 0){
			jenisHakmilik = "99999";
		}
		String jenisLot = getParam("socJenisLot");
		if (jenisLot == null || jenisLot.trim().length() == 0){
			jenisLot = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}
		String idAgensiC = getParam("socAgensi");
		if (idAgensiC == null || idAgensiC.trim().length() == 0){
			idAgensiC = "99999";
		}
	    
	    if ("papar".equals(actionPopup)){
	    	
	    	//GO TO DETAIL MAKLUMAT TANAH        	
        	vm = "app/php2/online/frmPNWPopupMaklumatTanah.jsp";
        	
        	beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilikAgensi);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
	    	
	    } else {
	    	
	    	//GO TO LIST TANAH        	
        	vm = "app/php2/online/frmPNWPopupSenaraiTanah.jsp";
        	        	
        	if ("1".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "selected");
				this.context.put("selected2", "");
				this.context.put("idJenisTanah", idJenisTanah);
        	} else if ("2".equals(idJenisTanah)) {
				this.context.put("selected", "");
				this.context.put("selected1", "");
				this.context.put("selected2", "selected");
				this.context.put("idJenisTanah", idJenisTanah);
        	} else {
        		this.context.put("selected", "selected");
				this.context.put("selected1", "");
				this.context.put("selected2", "");
				this.context.put("idJenisTanah", "0");
        	}
        	
        	if ("doChangeNegeri".equals(submit)){
        		idDaerah = "99999";
        		idMukim = "99999";
        	}
        	if ("doChangeDaerah".equals(submit)){
        		idMukim = "99999";
        	}
        	
        	this.context.put("selectJenisHakmilik", HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(jenisHakmilik), ""));
			this.context.put("selectJenisLot", HTML.SelectLot("socJenisLot",Long.parseLong(jenisLot), ""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
			this.context.put("selectDaerah", HTML.SelectDaerahByNegeri(idNegeri, "socDaerah", Long.parseLong(idDaerah), "", " onChange=\"doChangeDaerah();\""));
			this.context.put("selectMukim", HTML.SelectMukimByDaerah(idDaerah, "socMukim", Long.parseLong(idMukim), ""));
			this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensiC), ""));
        	
        	this.context.put("txtPeganganHakmilik", getParam("txtPeganganHakmilik"));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("tarikhWarta", getParam("tarikhWarta"));
			
			myLog.info("idKementerian popup view============"+idKementerian);;
			
			list = new Vector();
        	logic.carianTanah(idJenisTanah, getParam("txtPeganganHakmilik"), jenisHakmilik, getParam("txtNoHakmilik"), jenisLot,
        			getParam("txtNoLot"), getParam("txtNoWarta"), getParam("tarikhWarta"), idNegeri, idDaerah, idMukim, idKementerian, idAgensiC);
    		list = logic.getSenaraiTanah();
			this.context.put("SenaraiTanah", list);
			
			beanMaklumatAgensi = new Vector();
			logic.setMaklumatAgensi(idAgensi);
			beanMaklumatAgensi = logic.getBeanMaklumatAgensi();
			this.context.put("BeanMaklumatAgensi", beanMaklumatAgensi);
			
			
			
        	setupPage(session,action,list);
	    }
	    
	    this.context.put("idKategoriPemohon", idKategoriPemohon);
	    this.context.put("idKementerian", idKementerian);
	    this.context.put("idAgensi", idAgensi);
	    
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

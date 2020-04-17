/**
 * 
 */
package ekptg.view.online.htp.pajakan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.htp.FrmPajakanPopupSenaraiTanahData;

/**
 * 
 *
 */
public class FrmPajakanPopupSenaraiTanahView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPajakanPopupSenaraiTanahData logic = new FrmPajakanPopupSenaraiTanahData();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();

		//GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String submit = getParam("command");
	    String hitButton = getParam("hitButton");
	    String idHakmilik = getParam("idHakmilik");
	    String idPermohonan = getParam("idPermohonan");
	    String idKementerian =  getParam("idKementerian");
	    
	    //VECTOR
        Vector list = null;
        
        Vector beanMaklumatTanah = null;
	    
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
		
		if ("simpanHakmilik".equals(hitButton)){
			logic.saveHakmilikUrusan(idHakmilik, idPermohonan, session);
		}
	    
	    if ("papar".equals(actionPopup)){
	    	
	    	//GO TO DETAIL MAKLUMAT TANAH        	
        	vm = "app/htp/frmPajakanPopupMaklumatTanah.jsp";
        	
        	beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idHakmilik);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
	    	
	    } else {
	    	
	    	//GO TO LIST TANAH        	
        	vm = "app/htp/frmPajakanPopupSenaraiTanahOnline.jsp";        	
        	
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
        	
        	this.context.put("txtPeganganHakmilik", getParam("txtPeganganHakmilik"));
			this.context.put("txtNoHakmilik", getParam("txtNoHakmilik"));
			this.context.put("txtNoLot", getParam("txtNoLot"));
			this.context.put("txtNoWarta", getParam("txtNoWarta"));
			this.context.put("tarikhWarta", getParam("tarikhWarta"));
			
			list = new Vector();
        	logic.carianTanahOnline(idKementerian,idJenisTanah, getParam("txtPeganganHakmilik"), jenisHakmilik, getParam("txtNoHakmilik"), jenisLot,
        			getParam("txtNoLot"), getParam("txtNoWarta"), getParam("tarikhWarta"), idNegeri, idDaerah, idMukim);
    		list = logic.getSenaraiTanah();
			this.context.put("SenaraiTanah", list);
        	
        	setupPage(session,action,list);
	    }
	    
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
}

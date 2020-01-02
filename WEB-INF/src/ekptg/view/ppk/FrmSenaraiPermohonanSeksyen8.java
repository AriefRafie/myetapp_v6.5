/**
 * 
 */
package ekptg.view.ppk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmSenaraiPermohonanSeksyen8Data;


/**
 * 
 *
 */

public class FrmSenaraiPermohonanSeksyen8 extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiPermohonanSeksyen8.class);

	FrmSenaraiPermohonanSeksyen8Data logic = null;
	//Vector list = null;
	List list = null;

	@Override
	public String doTemplate2() throws Exception {
		logic = new FrmSenaraiPermohonanSeksyen8Data();
		//myLogger.info(".:FrmSenaraiPermohonanSeksyen8:.");
		
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String vm = ""; 
		String flagAdvSearch = getParam("flagAdvSearch");
		String action = getParam("action"); // Second Level
		
		//list = new Vector();
		//list.clear();
		
		list = Collections.synchronizedList(new ArrayList());
		
		String command = getParam("command");
		String noFail = getParam("txtNoFail");
		String tarikhMohon = getParam("tarikhMohon");
		String namaPemohon = getParam("txtPemohon");
		String kpPemohon = getParam("txtIcPemohon");
		String jenisKpPemohon = getParam("socJenisKpPemohon");
		if (jenisKpPemohon == null || jenisKpPemohon.trim().length() == 0){
			jenisKpPemohon = "0";
		}
		String namaSimati = getParam("txtSimati");
		String kpSimati = getParam("txtIcSimati");
		String jenisKp = getParam("socJenisKp");
		if (jenisKp == null || jenisKp.trim().length() == 0){
			jenisKp = "0";
		}
		String status = getParam("socStatus");
		if (status == null || status.trim().length() == 0){
			status = "0";
		}
		String noHakmilik = getParam("txtNoHakmilik");
		String noSijilMati = getParam("txtNoSijil");
		String tarikhBicara = getParam("tarikhBicara");
		String noLot = getParam("txtNoLot");
		
		String open_dashboard = "no";
		
		vm = "app/ppk/frmSenaraiPermohonanSeksyen8.jsp";
		
		if(command.equals("open_dashboard")){
			open_dashboard = "yes";	
		}
			
		else
		{
			open_dashboard = "no";
			
		if (flagAdvSearch.equals("")){
			namaPemohon = "";
			//jenisKpPemohon = "0";
			//kpPemohon = "";
			namaSimati = "";
			//jenisKp = "0";
			//kpSimati = "";
			noHakmilik = "";
			noSijilMati = "";
			tarikhBicara = "";
			noLot = "";
		}
		
		//komen, nampak mcm no point.. dah la berat
		//this.context.put("JumlahFail",logic.totalFail(session));
		
		//comment dlu...sebab berat
		long start1 = System.currentTimeMillis();       
		list = logic.carianFail(noFail, namaPemohon, namaSimati, jenisKp, kpSimati, session, tarikhMohon, status, 
				jenisKpPemohon, kpPemohon, noHakmilik, noSijilMati,tarikhBicara,noLot);
		 double finish1 = (double) (System.currentTimeMillis() - start1) / 1000.0;
		 myLogger.info("**MASA DIAMBIL LOAD MYINFO : "+finish1+" s");
		
		
		this.context.put("SenaraiFail", list);
		
		this.context.put("selectJenisKpPemohon",HTML.SelectPPKJenisKp("socJenisKpPemohon", Long.parseLong(jenisKpPemohon), "", "style=\"width:100px\""));
		this.context.put("selectJenisKp",HTML.SelectPPKJenisKp("socJenisKp", Long.parseLong(jenisKp), "", "style=\"width:100px\""));
		this.context.put("selectStatus",HTML.SelectStatusSek8("socStatus", Long.parseLong(status), "", "style=\"width:320px\""));
		
		this.context.put("txtNoFail", noFail.trim());
		this.context.put("tarikhMohon", tarikhMohon);
		this.context.put("txtPemohon", namaPemohon.trim());
		this.context.put("txtIcPemohon", kpPemohon);
		this.context.put("txtSimati", namaSimati.trim());
		this.context.put("txtIcSimati", kpSimati);
		this.context.put("txtNoHakmilik", noHakmilik.trim());
		this.context.put("txtNoSijil", noSijilMati.trim());
		this.context.put("tarikhBicara", tarikhBicara);
		this.context.put("txtNoLot", noLot.trim());
		
		this.context.put("flagAdvSearch", flagAdvSearch);
		
		this.context.put("totalFailPindahMasuk",logic.totalFailPindahMasuk(userId));
		
		setupPage(session,action,list);
		
		}
		
		this.context.put("open_dashboard", open_dashboard);
		
		return vm;
	}
	/*
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
		this.context.put("SenaraiFail",paging.getPage(page));
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
	*/
}

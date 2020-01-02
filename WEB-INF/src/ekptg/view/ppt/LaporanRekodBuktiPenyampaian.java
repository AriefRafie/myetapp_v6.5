package ekptg.view.ppt;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPembatalanInternalData;


public class LaporanRekodBuktiPenyampaian extends AjaxBasedModule{	
	static Logger myLogger = Logger.getLogger(FrmPembatalanInternal.class);
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	
	@Override
	public String doTemplate2() throws Exception{
		
		HttpSession session = request.getSession();		
		String vm = ""; 
		String main_command = getParam("command");
		String sub_command = getParam("sub_command");
		String subminor_command = getParam("subminor_command");
		myLogger.info("main_command :"+main_command+";sub_command :"+sub_command);
		
		String paging_action = getParam("action");
		String location = getParam("location");
		String point = getParam("point");
		String buka_cari = getParam("buka_cari");
		String jenis_permohonan = "4";
		String jenis_button = "";		
			
		
		Vector listdepan = null;
		Vector list_kementerian = null;
		Vector list_urusan = null;
		Vector list_status = null;
		Vector nama_user = null;
		Vector list_pejabat = null;

		this.context.put("list_pejabat","");				
		this.context.put("list_kementerian","");
		this.context.put("list_urusan","");
		this.context.put("list_status","");
		this.context.put("listdepan","");
		this.context.put("listdepan_size","");
	
		
		
		context.put("DATEUTIL", new DateUtil());
		this.context.put("Util", new lebah.util.Util());
		
		String bolehsimpan = "";
		String readmode = "";
		
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {			
			bolehsimpan = "yes";
		}
		myLogger.info("BOLEH SIMPAN :"+bolehsimpan);
		
	
		
			
					
		    
		  
				myLogger.info("MASUK");				
				String txtNoFail = getParam("txtNoFail");
				String txtNoRujJkptgNegeri = getParam("txtNoRujJkptgNegeri");
				String socKementerian = getParam("socKementerian");
				String socUrusan = getParam("socUrusan");
				String socStatus = getParam("socStatus");
				String socPejabat = getParam("socPejabat");
				this.context.put("socPejabat", socPejabat);
				this.context.put("txtNoFail", txtNoFail);
				this.context.put("txtNoRujJkptgNegeri", txtNoRujJkptgNegeri);
				this.context.put("socKementerian", socKementerian);
				this.context.put("socUrusan", socUrusan);
				this.context.put("socStatus", socStatus);			
				list_kementerian = logic.list_kementerian();
				this.context.put("list_kementerian",list_kementerian);
				list_urusan = logic.list_urusan();
				this.context.put("list_urusan",list_urusan);
				list_status = logic.list_status();
				this.context.put("list_status",list_status);
				listdepan = logic.senarai_permohonan_laporan((String) session.getAttribute("_ekptg_user_negeri"),socPejabat,txtNoFail,txtNoRujJkptgNegeri,socKementerian,socUrusan,socStatus,"2",(String) session.getAttribute("_portal_role"),(String) session.getAttribute("_ekptg_user_negeri"));						
				this.context.put("listdepan",listdepan);
				this.context.put("listdepan_size",listdepan.size());				
				list_pejabat = logic.list_pejabat();
				this.context.put("list_pejabat",list_pejabat);
				
				
				
				
				
				vm = "app/ppt/LAPORAN/LaporanRekodBuktiPenyampaianCarian.jsp";	
			    setupPage(session,paging_action,listdepan);	
			
		    
		
		
		
		     myLogger.info("USER ID:"+(String) session.getAttribute("_ekptg_user_id"));
		    myLogger.info("ROLE:"+(String) session.getAttribute("_portal_role"));
		    myLogger.info("NEGERI USER:"+ (String) session.getAttribute("_ekptg_user_negeri"));
		    this.context.put("portal_role",(String) session.getAttribute("_portal_role"));
		    this.context.put("negeri_user",(String) session.getAttribute("_ekptg_user_negeri"));
		
		
		    nama_user = logic.nama_user((String) session.getAttribute("_ekptg_user_id"));
		    this.context.put("nama_user",nama_user);
		    this.context.put("jenis_button", jenis_button);
            return vm;
     
		
	}// close doTemplate2

	

	
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
		this.context.put("listdepan",paging.getPage(page));
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
	
	
	

	 
	 
}// close class

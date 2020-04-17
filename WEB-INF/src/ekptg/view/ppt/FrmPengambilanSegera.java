package ekptg.view.ppt;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging;
import ekptg.model.ppt.FrmPengambilanSegeraData;
//Remark by azam - since we are not using it anyway.and it causing an error.
//import ekptg.model.ppt.TestData;

public class FrmPengambilanSegera extends AjaxBasedModule{
	
	static Logger myLogger = Logger.getLogger(FrmPengambilanSegera.class);
	
	FrmPengambilanSegeraData logic = new FrmPengambilanSegeraData();
	
	@Override
	public String doTemplate2() throws Exception {
	
		HttpSession session = request.getSession();
		String vm = "";
		String main_command = getParam("command");	
		String sub_command = getParam("sub_command");	
		String paging_action = getParam("action");
		
		
		Vector listdepan = null;
		Vector list_kementerian = null;
		Vector list_urusan = null;
		Vector list_status = null;
		Vector header = null;
		Vector maklumat_pembatalan = null;
		Vector senarai_hakmilik = null;
		Vector senarai_hakmilik_batal = null;
		Vector senarai_pihak_penting = null;
		Vector listDokumen = null;
		Vector view_details_dokumen = null;
		Vector senarai_hakmilik_overall = null;
		
		
		
		 
		myLogger.info("COMMAND:" + main_command);		
	
		//=--------------------------------------------------------------------------
	
		if ("senarai".equals(main_command)){			
			if ("papar".equals(sub_command)){
				this.context.put("first_masuk","yes");	
				String id_permohonan = getParam("id_permohonan");
				header = logic.content_header(id_permohonan);
				this.context.put("header",header);				
				
				
				
				vm = "app/ppt/frmSek8AmbilSegeraBrgl.jsp";	
	    	}
			
    	}
		
		else if ("borangL".equals(main_command)) {
			
			if ("simpan".equals(sub_command)) {			
				this.context.put("readmode","view");
				vm = "app/ppt/frmSek8AmbilSegeraBrgl.jsp";
			}
			
			
			else if ("kemaskini".equals(sub_command)){
				this.context.put("readmode","edit");
				}
			
			else if ("hapus".equals(sub_command)) {
					
				this.context.put("readmode","edit");
				vm = "app/ppt/frmSek8AmbilSegeraBrgl.jsp";	
		    }
			
			vm = "app/ppt/frmSek8AmbilSegeraBrgl.jsp";
			
			
			
	    }
		
		//=-------------------------------------------------------------------------
		else if ("info".equals(main_command)) {
			
			if ("simpan".equals(sub_command)) {			
				this.context.put("readmode","view");
				vm = "app/ppt/frmSek8AmbilSegeraInfo.jsp";
			}
			
			
			else if ("kemaskini".equals(sub_command)){
				this.context.put("readmode","edit");
				}
			
			else if ("hapus".equals(sub_command)) {
					
				this.context.put("readmode","edit");
				vm = "app/ppt/frmSek8AmbilSegeraInfo.jsp";	
		    }
			
			vm = "app/ppt/frmSek8AmbilSegeraInfo.jsp";
	    }
		
		//=---------------------------------------------------------------------
		else if ("borangJ".equals(main_command)) {
			
			if ("simpan".equals(sub_command)) {			
				this.context.put("readmode","view");
				vm = "app/ppt/frmSek8AmbilSegeraJanaBrgJ.jsp";
			}
			
			
			else if ("kemaskini".equals(sub_command)){
				this.context.put("readmode","edit");
				}
			
			else if ("hapus".equals(sub_command)) {
					
				this.context.put("readmode","edit");
				vm = "app/ppt/frmSek8AmbilSegeraJanaBrgJ.jsp";	
		    }
			
			vm = "app/ppt/frmSek8AmbilSegeraJanaBrgJ.jsp";
	    }
		else
		{

			String txtNoFail = getParam("txtNoFail");
			String txtNoRujJkptgNegeri = getParam("txtNoRujJkptgNegeri");
			String socKementerian = getParam("socKementerian");
			String socUrusan = getParam("socUrusan");
			String socStatus = getParam("socStatus");
			
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
	
			listdepan = logic.senarai_pembatalan_carian(txtNoFail,txtNoRujJkptgNegeri,socKementerian,socUrusan,socStatus,"2");
						
			this.context.put("listdepan",listdepan);
			this.context.put("listdepan_size",listdepan.size());
		
		vm = "app/ppt/frmSek8AmbilSegeraSenarai.jsp";
		}
		setupPage(session,paging_action,listdepan);
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
}
package ekptg.view.ppt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;

import ekptg.model.ppt.BantahanDaftar;

import ekptg.model.ppt.PPTHeader;

public class FrmFailBantahanSenarai extends AjaxBasedModule {

	private static final long serialVersionUID = 3587088402775617834L;
	static Logger myLogger = Logger.getLogger(FrmFailBantahanSenarai.class);
	BantahanDaftar model = new BantahanDaftar();

	//FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	PPTHeader header = new PPTHeader();
	//Kegunaan Lampiran
	// private String jenisDokumen = "pptbantahan";

	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		// get user login detail
		String usid = "";
		String action = getParam("action"); 	
		usid = (String) session.getAttribute("_ekptg_user_id");
		String userIdNeg = (String) session.getAttribute("_ekptg_user_negeri");

		context.put("userIdNeg", userIdNeg);
		Vector list = null;
		Vector listPageDepan = null;
		
		String vm = "app/ppt/frmFailBantahanSenarai.jsp"; 	
		carianBantahan(usid,userIdNeg);
		list = model.getListBantahan(userIdNeg);

		//data & size default list
		listPageDepan = model.getListBantahan(userIdNeg);

		context.put("JumlahFail", listPageDepan.size());
		context.put("PermohonanList", listPageDepan);

		myLogger.info("listPageDepan " +listPageDepan);
		//maintain searching value
		this.context.put("txtNoFail", getParam("txtNoFail"));
		String idKementerian = getParam("socKementerian");
		context.put("selectKementerian",HTML.SelectKementerian("socKementerian",Utils.parseLong(idKementerian),"style=width:470px"));
		setupPageBantahan(session,action,list);
		
			
		return vm;
		}
	
		private void carianBantahan(String usid,String userIdNeg) throws Exception {
		String txtNoFail = getParam("txtNoFail");
		String idKementerian = getParam("socKementerian");
		model.setCarianFailBantahan(usid,txtNoFail,idKementerian,userIdNeg);		
	}
		
		 protected void setupPageBantahan(HttpSession session, String action, Vector lists)
		    {
		        if(lists == null) {
		            context.put("totalRecords", Integer.valueOf(0));
		            context.remove("SenaraiFail");
		            context.put("page", Integer.valueOf(0));
		            context.put("itemsPerPage", Integer.valueOf(0));
		            context.put("totalPages", Integer.valueOf(0));
		            context.put("startNumber", Integer.valueOf(0));
		            context.put("isFirstPage", Boolean.valueOf(true));
		            context.put("isLastPage", Boolean.valueOf(true));
		        } else {
		            context.put("totalRecords", Integer.valueOf(lists.size()));
		            int page = getParam("page") != "" ? getParamAsInteger("page") : 1;
		            int itemsPerPage;
		            if(context.get("itemsPerPage") == null || "".equals(context.get("itemsPerPage")) || "0".equals(context.get("itemsPerPage")))
		                itemsPerPage = getParam("itemsPerPage") != "" ? getParamAsInteger("itemsPerPage") : 10;
		            else
		                itemsPerPage = ((Integer)context.get("itemsPerPage")).intValue();
		            if("getNext".equals(action))
		                page++;
		            else
		            if("getPrevious".equals(action))
		                page--;
		            else
		            if("getPage".equals(action))
		                page = getParamAsInteger("value");
		            else
		            if("doChangeItemPerPage".equals(action))
		                itemsPerPage = getParamAsInteger("itemsPerPage");
		            if(itemsPerPage == 0)
		                itemsPerPage = 10;
		            Paging paging = new Paging(session, lists, itemsPerPage);
		            if(page > paging.getTotalPages())
		                page = 1;
		            context.put("SenaraiFail", paging.getPage(page));
		            context.put("page", new Integer(page));
		            context.put("itemsPerPage", new Integer(itemsPerPage));
		            context.put("totalPages", new Integer(paging.getTotalPages()));
		            context.put("startNumber", new Integer(paging.getTopNumber()));
		            context.put("isFirstPage", new Boolean(paging.isFirstPage()));
		            context.put("isLastPage", new Boolean(paging.isLastPage()));
		        }
		    }

}

	
	

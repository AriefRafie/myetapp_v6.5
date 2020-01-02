package ekptg.view.online;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.online.FrmAgihAduanKeSeksyenData;
import ekptg.model.online.FrmListAduanBaruData;

public class AgihanAduanOlehSPPT extends AjaxBasedModule{
	
	public String doTemplate2() throws Exception
    {
		String vm = "";
		String submit = getParam("command");
		String action = getParam("action");
		HttpSession session = this.request.getSession();
        Vector list = new Vector();
        String jenisAduan = "0";
        String noAduan = "";
        String tkhAduan = "";
        String statusAduan = "0";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if ("papar".equals(action)){
			
			vm = "app/online/frmAgihAduanKeSeksyen.jsp";
			viewAduan(session);
			list = FrmAgihAduanKeSeksyenData.getData();
  	    	this.context.put("ViewAduan", list);
         	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",null,null));
         	this.context.put("tarikhAgihanKeSeksyen", sdf.format(now));
			
			
		}
        else if ("agihAduan".equals(action)){
        	agihAduan(session);
        	vm = "app/online/frmListAduanBaru.jsp";
			FrmListAduanBaruData.setCarianAduan(noAduan, jenisAduan, tkhAduan, statusAduan);
			list = FrmListAduanBaruData.getList();
			session.setAttribute("SenaraiAduan", list);
			prepareItemForDisplay(session,list,10,"first");
	    	this.context.put("selectJenisAduan",HTML.SelectJenisAduan("socJenisAduan",Long.parseLong(jenisAduan),"",null));
	    	this.context.put("selectStatus",HTML.SelectStatusAduan("socStatusAduan",Long.parseLong(statusAduan),""));
	    	this.context.put("txtNoAduan", noAduan);
	    	this.context.put("txdTarikhAduan", tkhAduan);
	    	this.context.put("socStatusAduan", statusAduan);
        	
        	
        }
		else if ("kembali".equals(action)){
			
			vm = "app/online/frmListAduanBaru.jsp";
			FrmListAduanBaruData.setCarianAduan(noAduan, jenisAduan, tkhAduan, statusAduan);
			list = FrmListAduanBaruData.getList();
			session.setAttribute("SenaraiAduan", list);
			prepareItemForDisplay(session,list,10,"first");
	    	this.context.put("selectJenisAduan",HTML.SelectJenisAduan("socJenisAduan",Long.parseLong(jenisAduan),"",null));
	    	this.context.put("selectStatus",HTML.SelectStatusAduan("socStatusAduan",Long.parseLong(statusAduan),""));
	    	this.context.put("txtNoAduan", noAduan);
	    	this.context.put("txdTarikhAduan", tkhAduan);
	    	this.context.put("socStatusAduan", statusAduan);
			
		}
		else{
			
			vm = "app/online/frmListAduanBaru.jsp";
			if(!"".equals(getParam("txtNoAduan"))){
				noAduan = getParam("txtNoAduan");
			}
			if(!"".equals(getParam("socJenisAduan"))){
				jenisAduan = getParam("socJenisAduan");
			}
			if(!"".equals(getParam("txdTarikhAduan"))){
				tkhAduan = getParam("txdTarikhAduan");
			}
			if(!"".equals(getParam("socStatusAduan"))){
				statusAduan = getParam("socStatusAduan");
			}
			FrmListAduanBaruData.setCarianAduan(noAduan, jenisAduan, tkhAduan, statusAduan);
			list = FrmListAduanBaruData.getList();
			session.setAttribute("SenaraiAduan", list);
			prepareItemForDisplay(session,list,10,"first");
	    	this.context.put("selectJenisAduan",HTML.SelectJenisAduan("socJenisAduan",Long.parseLong(jenisAduan),"",null));
	    	this.context.put("selectStatus",HTML.SelectStatusAduan("socStatusAduan",Long.parseLong(statusAduan),""));
	    	this.context.put("txtNoAduan", noAduan);
	    	this.context.put("txdTarikhAduan", tkhAduan);
	    	this.context.put("socStatusAduan", statusAduan);
		}
		
		return vm;
    }
	public void viewAduan(HttpSession session)throws Exception{
		
		int id = Integer.parseInt(getParam("idAduan"));
		
		FrmAgihAduanKeSeksyenData.setData(id);
	}
	public void agihAduan(HttpSession session)throws Exception{
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		Hashtable h = new Hashtable();
		h.put("id_Aduan", Integer.parseInt(getParam("idAduan")));
		h.put("id_Seksyen",getParam("socSeksyen"));
		h.put("id_Kemaskini",user_id);
		
		FrmAgihAduanKeSeksyenData.updateAduan(h);
	}
	private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String action)
	  {
	    int x;
	    int startno = 0;
	    if (action == null) action = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (action.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (action.equals("first"))
	      startno = 0;
	    	
	    else if (action.equals("last"))
	      x = cntItemPage;
	    else if (action.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    
	   
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));
	    
	  }

}

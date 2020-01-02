package ekptg.view.online;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.online.FrmEAduanData;
import ekptg.model.online.FrmEAduanListData;

public class PendaftaranAduanOnline extends AjaxBasedModule{
	
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

        if ("aduanBaru".equals(action)){	
        	vm = "app/online/frmEAduan.jsp";
        	this.context.put("selectJenisAduan",HTML.SelectJenisAduan("socJenisAduan",Long.parseLong(jenisAduan),"",null));
    		this.context.put("tarikhAduan",sdf.format(now));
	
        }
		else if ("hantar".equals(action)){
			
			vm = "app/online/frmEAduan.jsp";
			simpanAduan(session);
			vm = "app/online/frmEAduanList.jsp";
			FrmEAduanListData.setCarianAduan(noAduan, jenisAduan, tkhAduan, statusAduan);
			list = FrmEAduanListData.getList();
			session.setAttribute("SenaraiAduan", list);
			prepareItemForDisplay(session,list,10,"first");
	    	this.context.put("selectJenisAduan",HTML.SelectJenisAduan("socJenisAduan",Long.parseLong(jenisAduan),"",null));
	    	this.context.put("selectStatus",HTML.SelectStatusAduan("socStatusAduan",Long.parseLong(statusAduan),""));
	    	this.context.put("txtNoAduan", noAduan);
	    	this.context.put("txdTarikhAduan", tkhAduan);
	    	this.context.put("socStatusAduan", statusAduan);
			
		}
		else if ("editAduan".equals(action)){
			vm = "app/online/frmEAduan.jsp";
			viewAduan(session);
		}
		else if ("kembali".equals(action)){
			
			vm = "app/online/frmEAduanList.jsp";
			FrmEAduanListData.setCarianAduan(noAduan, jenisAduan, tkhAduan, statusAduan);
			list = FrmEAduanListData.getList();
			session.setAttribute("SenaraiAduan", list);
			prepareItemForDisplay(session,list,10,"first");
	    	this.context.put("selectJenisAduan",HTML.SelectJenisAduan("socJenisAduan",Long.parseLong(jenisAduan),"",null));
	    	this.context.put("selectStatus",HTML.SelectStatusAduan("socStatusAduan",Long.parseLong(statusAduan),""));
	    	this.context.put("txtNoAduan", noAduan);
	    	this.context.put("txdTarikhAduan", tkhAduan);
	    	this.context.put("socStatusAduan", statusAduan);
			
		}
		else{
			
			vm = "app/online/frmEAduanList.jsp";
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
			FrmEAduanListData.setCarianAduan(noAduan, jenisAduan, tkhAduan, statusAduan);
			list = FrmEAduanListData.getList();
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
	
	private void viewAduan(HttpSession session) throws Exception {
    	int id = Integer.parseInt(getParam("idAduan"));
    	FrmEAduanData.setData(id);
	   
	  }
	public void simpanAduan(HttpSession session)throws Exception{
		
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		Hashtable h = new Hashtable();
 	    h.put("id_Jenisaduan", Integer.parseInt(getParam("socJenisAduan")));
 	    h.put("aduan", getParam("txtAduan"));
 	    h.put("nama_Pengadu", getParam("txtNamaPengadu"));
 	    h.put("home_Tel", getParam("txtNoTelRumah"));
 	    h.put("hp_Tel", getParam("txtNoTelBimbit"));
 	    h.put("emel", getParam("txtEmel"));
 	    h.put("id_Masuk",user_id);
 	    h.put("id_Pengguna",user_id);

 	   FrmEAduanData.addAduan(h);
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

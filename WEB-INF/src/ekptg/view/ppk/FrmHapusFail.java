package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.AuditTrail;
import ekptg.model.ppk.FrmTukaranStatus;


public class FrmHapusFail extends VTemplate{
	static Logger myLogger = Logger.getLogger(FrmHapusFail.class);
	FrmTukaranStatus model = new FrmTukaranStatus();
	
	public Template doTemplate() throws Exception{

		HttpSession session = request.getSession();

		String doPost = (String) session.getAttribute("doPost");
		
    	String vm = "";
    	
    	Vector list = new Vector();
    	
    	list.clear();
    	
    	String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");
    	
   		context.put("carirekod","");
   		
    	String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);
    	String noFail = getParam("txtNoFail");
    	if ("Cari".equals(submit)){
    		
    		String id_permohonan = "";
			String id_fail = "";
			String id_status = "";
			String id_suburusanstatusfail = "";
			String keterangan_status = "";
    		
    		
    		
    		model.setList(noFail,usid);
    		list = model.getList();
    		
    		if(list.size()!=0){
    			Hashtable x = (Hashtable) list.get(0);
    			id_permohonan = x.get("id_permohonan").toString();
    			id_fail = x.get("id_fail").toString();
    			id_status = x.get("id_status").toString();	
    			id_suburusanstatusfail = x.get("id_suburusanstatusfail").toString();	
    			keterangan_status = x.get("keterangan").toString();
    			
        		context.put("id_permohonan", id_permohonan);
    			context.put("id_fail", id_fail);
    			context.put("id_status", id_status);
    			context.put("keterangan_status", keterangan_status);
    			context.put("id_suburusanstatusfail", id_suburusanstatusfail);
    			
        		context.put("carirekod","");
        		
    		} else {
    			
    			if(noFail!="")
    			{
    				context.put("carirekod","tiada");
    			}else{
    				context.put("carirekod","");
    			}
    		}
    		
    		context.put("nofail", noFail.trim());
    		context.put("listSemak",list);
    		context.put("listSemak_size",list.size());
    		
    		myLogger.info("[cari] = "+list);
    		
    		vm = "app/ppk/frmHapusFail.jsp";
    		
    	}//close cari
    	else if ("Hapus".equals(submit)){
    		
    		if (doPost.equals("true")) {
    			hapusfail(session);    			
    			String id_seksyen = "2";
    			String id_status = "999";
    			AuditTrail.logActivity(id_status,id_seksyen,this,session,"DEL","FAIL ["+noFail+"] DIHAPUSKAN");
    		}    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
    		
    		vm = "app/ppk/frmHapusFail.jsp";
    	}
    	else{

    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			
   		   	vm = "app/ppk/frmHapusFail.jsp";
   		   	
    	}//close else
    	
    	Template template = engine.getTemplate(vm);
        return template;
    	
	}//close dotemplate

	//tukarstatus
	private void hapusfail(HttpSession session) throws Exception{
		   
		Hashtable h = new Hashtable();
		
		String id_permohonan = getParam("id_permohonan");
		String id_fail = getParam("id_fail");
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_masuk", session.getAttribute("_ekptg_user_id"));
		model.hapusfail(h);
	    
	  }//close hapusfail
	
}//close class

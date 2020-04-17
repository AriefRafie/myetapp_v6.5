package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

//import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.AuditTrail;
import ekptg.model.htp.FrmTukaranStatusData;
import ekptg.model.htp.FrmUtilData;

public class FrmHapusFail extends VTemplate{
	/**
	 * 
	 */
	private final String PATH="app/htp/";
	private static final long serialVersionUID = -1986009574694346304L;
//	private static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmHapusFail.class);
	FrmTukaranStatusData model = new FrmTukaranStatusData();
	
	public Template doTemplate() throws Exception{
		HttpSession session = request.getSession();
		String doPost = (String) session.getAttribute("doPost");
    	String vm = PATH+"frmHapusFail.jsp";
   
    	//Vector list = new Vector();   	
    	Vector<Hashtable<String,String>> vStatus = null;
    	Hashtable<String,String> hash = null;
//    	list.clear();
    	
    	String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");   	
   		context.put("carirekod","");
   		
    	String submit = getParam("command");
//    	mylog.info("[submit] :: " +submit);
    	String noFail = getParam("txtNoFail");
    	if ("Cari".equals(submit)){    		
    		String id_permohonan = "";
			String id_fail = "";
			String id_status = "";
			String id_suburusanstatusfail = "";
			String id_suburusanstatus = "";
			String keterangan_status = "";
    		
    		hash = (Hashtable<String,String>)FrmTukaranStatusData.getListV2(noFail,usid);
    		
    		if(hash.isEmpty() != true){
    			//Hashtable x = (Hashtable) list.get(0);
    			Hashtable<String,String> x = hash;
    			id_permohonan = x.get("id_permohonan").toString();
    			id_fail = x.get("id_fail").toString();
    			id_status = x.get("id_status").toString();	
    			id_suburusanstatus = x.get("id_suburusanstatus").toString();	
    			id_suburusanstatusfail = x.get("id_suburusanstatusfail").toString();	
    			keterangan_status = x.get("keterangan").toString();
    			//seksyen = x.get("seksyen").toString();
    	   		context.remove("listSemak");
        		context.remove("listSemak_size");
     
    	    	vStatus = (Vector<Hashtable<String,String>>)FrmUtilData.getSenaraiStatusMengikutSubUrusan(id_status);
        		context.put("listSemak",vStatus);
        		//context.put("listSemak_size",vStatus.size());
        		context.put("listSemak_size",false);			
    			context.put("carianrekod","");
    			
    		}else{
    			if(noFail!=""){
    				context.put("carianrekod","tiada");
    			}else{
    				context.put("listSemak",vStatus);
    				context.put("listSemak_size",true);
    				context.put("carianrekod","");
    				
    			}
    			
    		}
    		
    		
    		//context.put("id_perintah", id_perintah);
    		//context.put("id_keputusanpermohonan", id_keputusanpermohonan);
    		//context.put("id_perbicaraan", id_perbicaraan);
    		context.put("id_permohonan", id_permohonan);
			context.put("id_fail", id_fail);
			context.put("id_status", id_status);
			context.put("keterangan_status", keterangan_status);
			context.put("id_suburusanstatusfail", id_suburusanstatusfail);
			context.put("id_suburusanstatus", id_suburusanstatus);
			//context.put("seksyen", seksyen);
			
	    	//vStatus.clear();
	    	//vStatus = (Vector)FrmUtilData.getSenaraiStatusMengikutSubUrusan(id_status);
    		context.put("nofail", noFail.trim());
    		
    	}else if ("Hapus".equals(submit)){//close cari
    		
    		if (doPost.equals("true")) {
    			hapusfail(session); 
    			AuditTrail.logActivity("999","3",this,session,"DEL","FAIL ["+noFail+"] DIHAPUSKAN");
    		}
    		
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
    		
    		//vm = "app/ppk/frmHapusFail.jsp";
    	
    	}else{
    		context.put("listSemak","");
    		context.put("listSemak_size","");
    		context.put("nofail", "");
    		context.put("id_permohonan", "");
			context.put("id_fail", "");
			context.put("id_status", "");
			context.put("id_suburusanstatusfail", "");
			context.put("keterangan_status", "");
			
   		   	//vm = PATH+"frmHapusFail.jsp";
   		   	
    	}//close else
    	
    	Template template = engine.getTemplate(vm);
        return template;
    	
	}//close dotemplate

	//tukarstatus
	private void hapusfail(HttpSession session) throws Exception{		   
		Hashtable<String,String> h = new Hashtable<String,String>();		
		String id_permohonan = getParam("id_permohonan");
		String id_fail = getParam("id_fail");
		h.put("id_permohonan", id_permohonan);
		h.put("id_fail", id_fail);
		h.put("id_masuk", String.valueOf(session.getAttribute("_ekptg_user_id")));
		FrmTukaranStatusData.hapusfail(h);
	    
	  }//close hapusfail
	
}//close class

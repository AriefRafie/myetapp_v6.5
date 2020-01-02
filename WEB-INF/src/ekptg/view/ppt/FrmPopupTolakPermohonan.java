package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import org.apache.log4j.Logger;
import ekptg.model.ppt.FrmPermohonanUPTData;
/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmPopupTolakPermohonan extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPopupTolakPermohonan.class);	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
	@Override
	public String doTemplate2() throws Exception{		
		HttpSession session = request.getSession();
    	//default
    	String vm = "app/ppt/frmPopupTolakPermohonan.jsp";		
    	//item
    	String jenisTolak = getParam("jenisTolak");
    	context.put("jenisTolak",jenisTolak);
    	String id_permohonan = getParam("id_permohonan");
    	String formnew = getParam("formnew");
    	//myLogger.info("jenisTolak ::::::::::: "+jenisTolak+"formnew ::::::::::: "+formnew);
    	
    	//default
		context.put("mode","");
		context.put("isEdit","");	
    	String submit = getParam("command");
//    	myLogger.info("formnew :"+formnew+",submit : " + submit);
    	   	
    	//NEW FORM
    	if(formnew.equals("yes")){    		
    		//screen validation
    		context.put("mode","new");
    		context.put("isEdit","no");  		
    		if("simpanCatatanTolak".equals(submit)){       		
//    	    	myLogger.info("jenisTolak : " + jenisTolak);
    			if(jenisTolak.equals("internal")){
    				simpanCatatanTolak(session,id_permohonan);
    			}else if(jenisTolak.equals("pelulus") || jenisTolak.equals("penyemak")){
    					//simpanCatatanTolak(session,id_permohonan);
    				modelUPT.simpanCatatanTolakKJP((String)session.getAttribute("_ekptg_user_id"),id_permohonan,jenisTolak, getParam("txtCatatan").toUpperCase());
    			}   			            	
    			//screen validation
    			context.put("mode","view");
        		context.put("isEdit","no");
         		
        	}//close simpanCatatanTolak
    		
    		//data
    		if(jenisTolak.equals("internal")){
    			getDataTolakPermohonan(id_permohonan);
    		}else if(jenisTolak.equals("pelulus") || jenisTolak.equals("penyemak")){
    			getDataTolakPermohonanKJP(id_permohonan,jenisTolak);
			}
    	
    	}else{	//VIEW FORM	  		
    		//screen validation
    		context.put("mode","view");
    		context.put("isEdit","no");   		
    		
    		if("kemaskiniCatatanTolak".equals(submit)){       		
    			//screen validation
        		context.put("mode","view");
        		context.put("isEdit","yes");   			
        		String submit2 = getParam("command2");
            	//myLogger.info("submit[2] : " + submit2);
        		
            	if("updateCatatanTolak".equals(submit2)){            		
            		//simpanCatatanTolak(session,id_permohonan);            		
            		if(jenisTolak.equals("internal")){
        				simpanCatatanTolak(session,id_permohonan);
        			}else if(jenisTolak.equals("pelulus") || jenisTolak.equals("penyemak")){
        				//simpanCatatanTolak(session,id_permohonan);
        				modelUPT.simpanCatatanTolakKJP((String)session.getAttribute("_ekptg_user_id"),id_permohonan,jenisTolak, getParam("txtCatatan").toUpperCase());
        			}
                 	
        			//screen validation
        			context.put("mode","view");
            		context.put("isEdit","no");
             		
            	}//close updateCatatanTolak
            	
    		}//close kemaskiniCatatanTolak
    		//data
    		if(jenisTolak.equals("internal")){
    			getDataTolakPermohonan(id_permohonan);
    		}else if(jenisTolak.equals("pelulus") || jenisTolak.equals("penyemak")){
    			getDataTolakPermohonanKJP(id_permohonan,jenisTolak);
			}

    	}
      	
    	context.put("id_permohonan",id_permohonan);
    	return vm;
    		
	    }//close public template
		
//--METHOD--//		
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDataTolakPermohonanKJP(String id_permohonan,String jenisTolak) throws Exception{		
		Vector dataTolakPermohonan = new Vector();
		dataTolakPermohonan.clear();	
		dataTolakPermohonan = modelUPT.getDataTolakPermohonanKJP(id_permohonan,jenisTolak);	
		//data
		context.put("dataTolakPermohonan",dataTolakPermohonan);
		
		String catatan_status_online = "";
		if (dataTolakPermohonan.size() != 0) {
			Hashtable h = (Hashtable) dataTolakPermohonan.get(0);
			catatan_status_online = h.get("catatan_status_online").toString();
		}
		
		if(!catatan_status_online.equals("")){
			context.put("mode","view");
		}		
		
	}//close getDataTolakPermohonan
	
	@SuppressWarnings({ "unchecked", "static-access" })
	private void getDataTolakPermohonan(String id_permohonan) throws Exception{	
		Vector dataTolakPermohonan = new Vector();
		dataTolakPermohonan.clear();		
		dataTolakPermohonan = modelUPT.getDataTolakPermohonan(id_permohonan);		
		//data
		context.put("dataTolakPermohonan",dataTolakPermohonan);
		
		String catatan_status_online = "";
		if (dataTolakPermohonan.size() != 0) {
			Hashtable h = (Hashtable) dataTolakPermohonan.get(0);
			catatan_status_online = h.get("catatan_status_online").toString();
		}
		
		if(!catatan_status_online.equals("")){
			context.put("mode","view");
		}
		
	}//close getDataTolakPermohonan
	
	private void simpanCatatanTolak(HttpSession session,String id_permohonan) throws Exception{		
		Hashtable<String,String> h = new Hashtable<String,String>();		
		h.put("id_permohonan", id_permohonan);
		h.put("txtCatatan", getParam("txtCatatan").toUpperCase());
		h.put("id_user", String.valueOf(session.getAttribute("_ekptg_user_id")));	
		//modelUPT.simpanCatatanTolak(h);
		FrmPermohonanUPTData.simpanCatatanTolak(h);
		
	}//close simpanCatatanTolak
	
	
}//close class

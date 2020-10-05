package ekptg.view;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.utils.status.IStatus;
import ekptg.model.utils.status.StatusBean;


public class FrmPopupTolakPermohonan extends AjaxBasedModule {
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmPopupTolakPermohonan.class);	
	//model
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	private IStatus iStatus = null;

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
    	myLogger.info("jenisTolak= "+jenisTolak+",formnew="+formnew);
    	
    	//default
		context.put("mode","");
		context.put("isEdit","");	
    	String submit = getParam("command");
    	myLogger.info("submit=" + submit);
		String userId = (String)session.getAttribute("_ekptg_user_id");
    	String modul = getParam("modul");
    	context.put("modul",modul);
    	myLogger.info("modul : " + modul);

    	//NEW FORM
    	if(formnew.equals("yes")){    		
    		//screen validation
    		context.put("mode","new");
    		context.put("isEdit","no");  		
    		if("simpanCatatanTolak".equals(submit)){       		
//    	    	myLogger.info("jenisTolak : " + jenisTolak);
    			if(jenisTolak.equals("internal")){
    				//simpanCatatanTolak(session,id_permohonan);
    				
    				FrmPermohonanUPTData dt = new FrmPermohonanUPTData();
    				Hashtable<String,String> hashPermohonan  = null ;
    				if(modul.equals("ppk"))
    					hashPermohonan = dt.getPermohonanPPK(id_permohonan);
    				else
    					hashPermohonan = dt.getPermohonan(id_permohonan);
    				
    		    	myLogger.info("getParam(\"txtCatatan\")= " + getParam("txtCatatan"));
    				Hashtable<String,String> hash  = new Hashtable<String,String> () ;

    		    	hash.put("catatan", getParam("txtCatatan"));
    				hash.put("idUser", userId);
    				hash.put("langkah", "50");
    				hash.put("modul", modul);
    				
    				hash.put("idPermohonan",hashPermohonan.get("idPermohonan"));
					hash.put("idFail", hashPermohonan.get("idFail"));
					hash.put("idSuburusan", hashPermohonan.get("idSuburusan"));
					myLogger.info(hash);
	
//    				String idSuburusanStatus = getStatus().getIdSuburusanStatusByLangkah("50",String.valueOf(hash.get("idSuburusan")), "=");   				
//    				Tblrujsuburusanstatusfail susf = new Tblrujsuburusanstatusfail();
//    				susf.setIdFail(Long.parseLong(String.valueOf(hash.get("idFail"))));
//    				susf.setIdPermohonan(Long.parseLong(id_permohonan));
//    				susf.setIdSuburusanstatus(Long.parseLong(idSuburusanStatus));
//    				susf.setUrl(getParam("txtCatatan"));
//    				susf.setAktif("1");
//    				susf.setIdMasuk(Long.parseLong(userId));
//    				susf.setTarikhMasuk("sysdate");
//    				susf.setIdKemaskini(Long.parseLong(userId));
//    				susf.setTarikhKemaskini("sysdate");
					if(modul.equals("php"))
						getStatus().simpanPhp(hash);
					else
						getStatus().simpan(hash);
    				
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
    			context.put("modul",modul);
    			if(modul.equals("php"))
    				getDataTolakPermohonanPhp(id_permohonan);
    			else 
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
            			if(modul.equals("php"))
            				simpanCatatanTolakPhp(session,id_permohonan);
            				
            			else
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
	
	private void getDataTolakPermohonanPhp(String id_permohonan) throws Exception{
		Vector dataTolakPermohonan = new Vector();
		dataTolakPermohonan.clear();		
		dataTolakPermohonan = modelUPT.getDataTolakPermohonanPhp(id_permohonan);
		
		context.put("dataTolakPermohonan",dataTolakPermohonan);
		
		String catatan_status_online = "";
		if (dataTolakPermohonan.size() != 0) {
			Hashtable h = (Hashtable) dataTolakPermohonan.get(0);
			catatan_status_online = h.get("catatan_status_online").toString();
		}
		
		if(!catatan_status_online.equals("")){
			context.put("mode","view");
		}
	}
	
	private void simpanCatatanTolak(HttpSession session,String id_permohonan) throws Exception{		
		Hashtable<String,String> h = new Hashtable<String,String>();		
		h.put("id_permohonan", id_permohonan);
		h.put("txtCatatan", getParam("txtCatatan").toUpperCase());
		h.put("id_user", String.valueOf(session.getAttribute("_ekptg_user_id")));	
		//modelUPT.simpanCatatanTolak(h);
		FrmPermohonanUPTData.simpanCatatanTolak(h);
		
	}//close simpanCatatanTolak
	
	private void simpanCatatanTolakPhp(HttpSession session,String id_permohonan) throws Exception{		
		Hashtable<String,String> h = new Hashtable<String,String>();		
		h.put("id_permohonan", id_permohonan);
		h.put("txtCatatan", getParam("txtCatatan").toUpperCase());
		h.put("id_user", String.valueOf(session.getAttribute("_ekptg_user_id")));	
		FrmPermohonanUPTData.simpanCatatanTolakPhp(h);
		
	}
	
	private IStatus getStatus(){
		if (iStatus==null){
			iStatus=new StatusBean();
		}
		return iStatus;
	}
	
	
}//close class

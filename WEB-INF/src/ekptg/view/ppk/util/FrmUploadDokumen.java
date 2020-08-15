
package ekptg.view.ppk.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

public class FrmUploadDokumen extends AjaxBasedModule {

	private final String PATH="app/ppk/util/";
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.ppk.util.FrmUploadDokumen.class);
	
	String readability = "";
	String disability = "";
	String idUser = "0"; 
    String idRujukan = "";

	@Override
	public String doTemplate2() throws Exception {		
		HttpSession session = this.request.getSession();
		//GET DEFAULT PARAM
	    //String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = "";
	    String actionPopup = getParam("actionPopup");
	    String hitButton = getParam("hitButton");	    
	    String idHarta = getParam("idHarta");
	    String flagOnline = getParam("flagOnline");
	    //lampiran simati
	    idRujukan = getParam("rujukan");
	    
	    //VECTOR
		Vector<Hashtable<String, String>> dokumens = null;
        //Vector<Hashtable<String,String>> senarai = null;      
        //Vector<Hashtable<String,String>> beanMaklumatTanah = null;
		idUser = (String) session.getAttribute("_ekptg_user_id");
		ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();

		myLog.info("actionPopup="+actionPopup);
		myLog.info("hitButton="+hitButton);
//		myLog.info("idHarta="+idHarta);
		
		if ("simpanHakmilik".equals(hitButton)){

		}else if(hitButton.equals("simpan")){
			uploadFiles(idHarta,false);
			hitButton = "";
			
		}else if(hitButton.equals("hapus")){
			String iDokumen = getParam("iDokumen");
			l.hapusLampiran(iDokumen, null,false);
			uploadFiles(idHarta,false);
			hitButton = "";
		
		}else if(hitButton.equals("simpanHA")){
			uploadFiles(idHarta,true);
			hitButton = "";
		
		}else if(hitButton.equals("hapusHA")){
			String iDokumen = getParam("iDokumen");
			l.hapusLampiran(iDokumen, null,true);
			uploadFiles(idHarta,true);
			hitButton = "";
		
		}else if(hitButton.equals("simpanMyID")){
			//99201 (dokumen pdf)
			l.lampiranSimati(request,idRujukan,"99201",idUser);
			hitButton = "";
			//this.context.put("num_files", jumLampiran);

		}else if(hitButton.equals("hapusmyid")){
			String iDokumen = getParam("iDokumen");
			l.hapusLampiranSimati(iDokumen, null,true);
			//uploadFiles(idHarta,true);
			hitButton = "";

		}else if(hitButton.equals("simpancod")){
			//99202 (dokumen)
			l.lampiranSimati(request,idRujukan,"99202",idUser);
			hitButton = "";
			//this.context.put("num_files", jumLampiran);
		}else if(hitButton.equals("simpanlampiran")){ //30/06/2020
			//99203 (S17) , 99204 (Bantahan) , 99210 (Tukar pemohon)
			l.lampiranSimati(request,idRujukan,getParam("jenisdokumen"),idUser);
			hitButton = "";
			//this.context.put("num_files", jumLampiran);
		}
		else if(hitButton.equals("simpanboranga")){ //14/08/2020
			//99211 (Borang A)
			l.lampiranBorangA(request,idRujukan,getParam("jenisdokumen"),idUser);
			hitButton = "";
			//this.context.put("num_files", jumLampiran);
		}

		//myLog.info("actionPopup="+actionPopup);
		//myLog.info("mode="+getParam("mode"));
        vm = PATH+"frmUploadDokumen.jsp";
        int jumLampiran = 1;
		String mode = getParam("mode");
		String RO_General = "readonly=\"readonly\"";
	    
	    if (actionPopup.equals("papar")){
			disability = "";
	   		readability = "";			
			//Lampiran
			//myLog.info("lampira:mode_="+getParam("mode_"));
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");

			}
			dokumens = l.lampiranMengikutHarta(idHarta, null,false);
			// end Lampiran
	    }else if (actionPopup.equals("paparlampiran")){
			disability = "";
		   	readability = "";
			//Lampiran
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");

			}			
			dokumens = l.getLampiranSimati(idRujukan, null,getParam("jenisdokumen"));
			// End
	    }else if (actionPopup.equals("paparboranga")){
			disability = "";
		   	readability = "";
			//Lampiran
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");

			}			
			dokumens = l.getLampiranBorangA(idRujukan, null,getParam("jenisdokumen"));
			// End
	    }else if (actionPopup.equals("paparbantahan")){
			disability = "";
		   	readability = "";
			//Lampiran
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");
			}
			dokumens = l.getBantahanMaklumat(idRujukan, null,getParam("jenisdokumen"));
	    }else if (actionPopup.equals("paparHA")){
			disability = "";
		   	readability = "";
			//Lampiran
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");

			}			
			dokumens = l.lampiranMengikutHarta(idHarta, null,true);
			// end Lampiran
	    
	    }else if (actionPopup.equals("MyID")){
			disability = "";
		   	readability = "";
			//Lampiran
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");

			}			
			dokumens = l.getLampiranSimati(idRujukan, null,"99201");
			// end Lampiran

	    }else if (actionPopup.equals("cod")){
			disability = "";
		   	readability = "";
			//Lampiran
			if (mode.equals("bilampiran")) {
				RO_General = "";
				jumLampiran = getParamAsInteger("jumlahlampiran");

			}			
			dokumens = l.getLampiranSimati(idRujukan, null,"99202");
			// end Lampiran
	    		    	
	    }else if (actionPopup.equals("kemaskini")){
	    	myLog.info("kemaskini:mode="+getParam("mode"));	    	
	    	
	    } else {	    	
        	//myLog.info("idPermohonan="+idPermohonan);
        	vm = PATH+"pajakan/fail/tanah/frmPajakanPopupSenaraiTanah.jsp";        	
	    }
	    //lampiran simati
	   	this.context.put("idRujukan",idRujukan);
	   	this.context.put("jenisdokumen",getParam("jenisdokumen"));
	   	
		this.context.put("senaraidokumen",dokumens);
		this.context.put("num_files", jumLampiran);

		this.context.put("disability",disability);
		this.context.put("disabled",disability);
		this.context.put("mode",mode);
		this.context.put("readability",readability);
		this.context.put("readonly",readability);		
	    
	   	this.context.put("RO_General",RO_General);
	   	this.context.put("actionPopup",actionPopup);
	   	this.context.put("actionRefresh",getParam("actionrefresh"));
	   	this.context.put("flagOnline",flagOnline);
	   	this.context.put("hitButton",hitButton);
	   	this.context.put("idHarta",idHarta);

	   	return vm;
		
	}
	
	private void uploadFiles(String idhtaam,boolean isHA) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Enumeration<?> allparam = request.getParameterNames();
		String name = "";
		String value = "";
		for (; allparam.hasMoreElements();) {
			// Get the name of the request parameter
			name = (String) allparam.nextElement();
			// Get the value of the request parameter
			value = request.getParameter(name);
			// System.out.println(name +"="+value);
			//myLog.info(name + "=" + value);
		}
		List<?> items = upload.parseRequest(request);
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			if ((!(item.isFormField())) && (item.getName() != null)
					&& (!("".equals(item.getName())))) {
				ekptg.model.ppk.util.LampiranBean l = new ekptg.model.ppk.util.LampiranBean();
				l.saveData(idhtaam,idUser, item,isHA);
				
			}
		}
	}

	
}

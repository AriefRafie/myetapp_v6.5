package ekptg.view.utils;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.FrmLaporanKemasukanBilFailData;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;
import ekptg.model.utils.FrmMappingUserPegawaiData;

public class FrmMappingUserPegawai extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(ekptg.view.utils.FrmMappingUserPegawai.class);
	String socNegeri = "";
	String socUnit = "";
	String socDaerah = "";
	String socUsers = "";
	Vector<?> vecHash = null;

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/utils/frmMappingUserPegawai.jsp";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		mylog.info("doTemplate2:submit::"+submit);
		//if(FrmUtilData.isUserRole(userId,"PENGGUNAUNIT")) 
		if(FrmUtilData.isUserRole(userId,"penggunaunit")) 
			userLevel = 1;
		else if(FrmUtilData.isUserRole(userId,"pengarahunit")) 
			userLevel = 2;
		else if(FrmUtilData.isUserRole(userId,"penggunanegeri")) 
			userLevel = 3;
		else if(FrmUtilData.isUserRole(userId,"pengarahnegeri")) 
			userLevel = 4;
		else if(FrmUtilData.isUserRole(userId,"penggunahq")) 
			userLevel = 5;
		else if(FrmUtilData.isUserRole(userId,"pengarahhq")) 
			userLevel = 6;
		
   		String idPejabat = (getParam("socUnit")== null) ?"0":getParam("socUnit");
       	String idLaporanFail = (getParam("idlaporanbilfail")== "0") ?"0":getParam("idlaporanbilfail");
       	Long tempIdNegeri = 0L;
		User user = null;
		user = PrepareUser.getUserById(userId);
		//Long tempIdNegeri01 = Long.parseLong(getParam("idnegeri").equals("0")?"0L":getParam("idnegeri"));

    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));
//			socNegeri = UtilHTML.SelectNegeriXKod("idnegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
//			socUsers = HTML.SelectPegawaiPengendaliByNegeri(""+tempIdNegeri,"socUnit");
//			socDaerah = UtilHTML.SelectUsersByNegeri("Form_id_Daerah", null, "", ""+tempIdNegeri);  			
//			vecHash = FrmMappingUserPegawaiData.getSenaraiPegawaiMapping(""+tempIdNegeri);
  			display(tempIdNegeri);
    	}else if ("Delete".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));
      		if (FrmMappingUserPegawaiData.delete(idLaporanFail))
     			this.context.put("result", "success");
     		else 
     			this.context.put("result", "failed");
  			display(tempIdNegeri);  		
     	} else if ("simpan".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("idnegeri"));

       		String idPegawai = (getParam("socUnit")== null) ?"0":getParam("socUnit");
       		String userid = (getParam("Form_id_Daerah")== null) ?"0":getParam("Form_id_Daerah");
       		Hashtable<String, String> parameters = new Hashtable<String, String>();
       		parameters.put("id_unitpsk", idPegawai);
       		parameters.put("user_id", userid);
       		if(idLaporanFail.equals("0")){
       			if (FrmMappingUserPegawaiData.insert(parameters, (String)session.getAttribute("_ekptg_user_id")))
       				this.context.put("result", "success");
       			else 
       				this.context.put("result", "failed");
       		}else{
         		if (FrmMappingUserPegawaiData.update(parameters, idLaporanFail))
         			this.context.put("result", "success");
         		else 
         			this.context.put("result", "failed");    			      			
       		}
  			display(tempIdNegeri);
 		
     	}else{
    		//mylog.info("userLevel="+userLevel);
			if(userLevel==1){
    			vm = "app/ppk/frmSenaraiLaporanUnit.jsp";
     			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());
    			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah");
       			vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah","L");
    			//vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","daerah","R");
    		}else if(userLevel==2){
    			//vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
    			Tblrujpejabatjkptg pej = null;
    			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
     			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"javascript:doChangeUnit()\"",""+pej.getIdNegeri());   			
    			socDaerah =  UtilHTML.SelectDaerahByNegeri(""+pej.getIdNegeri(),"Form_id_Daerah");
    			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,null);

    		}else if(userLevel==6){
    			//socNegeri = UtilHTML.SelectNegeri("socNegeri",0L,"style=width:340"," onChange=\"javascript:doChangeNegeri()\"");
    			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null,"style=width:340"," onChange=\"javascript:doChangeUnit()\"");
    			socUsers = UtilHTML.SelectDaerah("Form_id_Daerah",null,"style=width:340");
     		}else{
//    			socNegeri = UtilHTML.SelectNegeriXKod("idnegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
//    			socUsers = HTML.SelectPegawaiPengendaliByNegeri(""+tempIdNegeri,"socUnit");  			
//    			socDaerah = UtilHTML.SelectUsersByNegeri("Form_id_Daerah", null, "", ""+tempIdNegeri);  			 			
//    			vecHash = FrmMappingUserPegawaiData.getSenaraiPegawaiMapping(""+tempIdNegeri);
      			display(tempIdNegeri);
     		}
    	}
		context.put("senaraiRekod",vecHash);
   		//dropdown
		context.put("selectNegeri",socNegeri);
		context.put("selectUnit",socUsers);
		context.put("selectDaerah",socDaerah);

		return vm;
		
	}
	
	public void display(Long tempIdNegeri) throws Exception{
		
		socNegeri = UtilHTML.SelectNegeriXKod("idnegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUsers = HTML.SelectPegawaiPengendaliByNegeri(""+tempIdNegeri,"socUnit");
		socDaerah = UtilHTML.SelectUsersByNegeri("Form_id_Daerah", null, "", ""+tempIdNegeri);  			
		vecHash = FrmMappingUserPegawaiData.getSenaraiPegawaiMapping(""+tempIdNegeri);
	}
	
	public void setParameterValues(Hashtable<String, String> parameters) {
		String name="";
		String value="";
		Enumeration<?> allparam = request.getParameterNames();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        if (name.indexOf("Form_") != -1) { // get only parameters with name like Form_
		        value = request.getParameter(name);
		        parameters.put(name,value);
	        }
		}
	}
}

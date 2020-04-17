package ekptg.view.meps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.FrmLaporanKemasukanBilFailData;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class PPK_FrmLaporanKemasukanBilFail extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(PPK_FrmLaporanKemasukanBilFail.class);
	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmLaporanKemasukanBilFail.jsp";
		//String socNegeri = "";
		String socUnit = "";
		String socDaerah = "";
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
		
		Vector<?> vecHash = null;
		//Vector<?> vecRekod = null;
   		String idPejabat = (getParam("socUnit")== null) ?"0":getParam("socUnit");
   		String idDaerah = (getParam("Form_id_Daerah")== null) ?"0":getParam("Form_id_Daerah");
       	String idLaporanFail = (getParam("idlaporanbilfail")== "0") ?"0":getParam("idlaporanbilfail");
		User user = null;
		user = PrepareUser.getUserById(userId);
	
    	if ("SelectDaerah".equals(submit)){
  			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",Long.parseLong(idPejabat)," style=width:340 "," onChange=\"javascript:doChangeUnit()\"");
			socDaerah = UtilHTML.SelectDaerahByUnitPPKXKod("Form_id_Daerah",null,"style=width:340","", idPejabat);
			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,idDaerah);

    	}else if ("Delete".equals(submit)) {
       		//mylog.info("idLaporanFail:"+idLaporanFail);
       		if (FrmLaporanKemasukanBilFailData.delete(idLaporanFail))
     			this.context.put("result", "success");
     		else 
     			this.context.put("result", "failed");
  			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",Long.parseLong(idPejabat)," style=width:340 "," onChange=\"javascript:doChangeUnit()\"");
			socDaerah = UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah",null,"style=width:340","", idPejabat);
	
			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,null);
  		
     	} else if ("simpan".equals(submit)) {
       		//String idDaerah = (getParam("socDaerah")== null) ?"0":getParam("socDaerah");
       		String bilangan = (getParam("Form_bilangan")== null) ?"0":getParam("Form_bilangan");
       		Hashtable<String, String> parameters = new Hashtable<String, String>();
       		parameters.put("id_Daerah", idDaerah);
       		parameters.put("bilangan", bilangan);
       		if(idLaporanFail.equals("0")){
       			if (FrmLaporanKemasukanBilFailData.insert(parameters, (String)session.getAttribute("_ekptg_user_id")))
       				this.context.put("result", "success");
       			else 
       				this.context.put("result", "failed");
       		}else{
         		if (FrmLaporanKemasukanBilFailData.update(parameters, idLaporanFail))
         			this.context.put("result", "success");
         		else 
         			this.context.put("result", "failed");    			
       			
       		}
       		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",Long.parseLong(idPejabat)," style=width:340 "," onChange=\"javascript:doChangeUnit()\"");
			socDaerah = UtilHTML.SelectDaerahByUnitPPKXKod("Form_id_Daerah",null,"style=width:340","", idPejabat);
	
			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,null);
  		
     	}else if("SelectUnit".equals(submit)){
    		Tblrujpejabatjkptg pej = null;
			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
 	
   			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",Long.parseLong(idPejabat)," style=width:340 "," onChange=\"javascript:doChangeUnit()\"",""+pej.getIdNegeri());
			socDaerah = UtilHTML.SelectDaerahByUnitPPKXKod("Form_id_Daerah",null,"style=width:340","", idPejabat);
			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,null);

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
    			socDaerah = UtilHTML.SelectDaerah("Form_id_Daerah",null,"style=width:340");
     		}else{
    			//socNegeri = UtilHTML.SelectNegeri("socNegeri",0L,"style=width:340");
    			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"javascript:doChangeUnit()\"");
    			socDaerah = UtilHTML.SelectDaerah("Form_id_Daerah",null,"style=width:340");  			
    			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(null,null);
    		}
    	}
		context.put("senaraiRekod",vecHash);
		//context.put("senaraiRekod",vecRekod);
 		
   		//dropdown
		//context.put("selectNegeri",socNegeri);
		context.put("selectUnit",socUnit);
		context.put("selectDaerah",socDaerah);

		return vm;
		
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

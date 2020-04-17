package ekptg.view.meps;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class PPK_FrmSenaraiLaporan extends AjaxBasedModule {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(PPK_FrmSenaraiLaporan.class);
	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/meps/ppk/frmSenaraiLaporan.jsp";
		String socNegeri = "";
		String socUnit = "";
		String socDaerah = "";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;

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
		Vector<?> vecRekod = null;

    	if ("doChangeSelect".equals(submit)){

    	}else{
    		User user = null;
    		user = PrepareUser.getUserById(userId);
			mylog.info("userLevel="+userLevel);
			if(userLevel==1){
    			vm = "app/ppk/frmSenaraiLaporanUnit.jsp";
     			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());
    			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah");
       			vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah","L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","daerah","R");
    		}else if(userLevel==2){
    			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";
    			Tblrujpejabatjkptg pej = null;
    			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
     			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null,"style=width:340",null,""+pej.getIdNegeri());
    			//mylog.info("userLevel="+userLevel+"::idnegeri="+pej.getIdNegeri());
    			socDaerah =  UtilHTML.SelectDaerahByNegeri(""+pej.getIdNegeri(),"socDaerah");
    			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("","unit");
       			vecHash = FrmUtilData.getLaporanMengikutSeksyen("","unit","L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","unit","R");
    		}else if(userLevel==6){
    			socNegeri = UtilHTML.SelectNegeri("socNegeri",0L,"style=width:340");
    			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null,"style=width:340",null);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");
    			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("");
    			vecHash = FrmUtilData.getLaporanMengikutSeksyen("",null,"L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("",null,"R");
    		}else{
    			socNegeri = UtilHTML.SelectNegeri("socNegeri",0L,"style=width:340");
    			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null,"style=width:340",null);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");
    			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("");
    		}
    	}
		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);



   		//dropdown
		context.put("selectNegeri",socNegeri);
		context.put("selectUnit",socUnit);
		context.put("selectDaerah",socDaerah);

		return vm;
	}
}

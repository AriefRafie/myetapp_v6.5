package ekptg.view.ppk;
 
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
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;
import ekptg.model.utils.ILaporan;
import ekptg.model.utils.LaporanBean;

public class LaporanBaitulmalPBN1a extends AjaxBasedModule {
	/**
	 *  
	 */ 
	private final String PATH = "app/ppk/";
	private final String ID_SEKSYEN = "2";
 	private ILaporan iLaporan = null;  
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.ppk.LaporanBaitulmalPBN1a.class); 
	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmSenaraiLaporanUnitBaitulmalPBN1.jsp";
		String socNegeri = "";
		String socUnit = "";
		String socIdUnit = getParam("socUnit").equals("")?"0":getParam("socUnit");
		String socDaerah = "";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 6;

		Vector<?> vecHash = null;
		Vector<?> vecRekod = null;
		User user = null;
		user = PrepareUser.getUserById(userId);
		Tblrujpejabatjkptg pej = null;
		pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
		String idNegeri = pej.getIdNegeri().toString();
		
    	if ("doChangeSelect".equals(submit)){

    	}else{ 
    		//myLog.info("userLevel="+userLevel);
			if(userLevel==1){
    			vm = PATH+"frmSenaraiLaporanUnitBaitulmalPBN.jsp?";
     			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());
       			vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah","L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","daerah","R");
    			
    		}else if(userLevel==2){
    			vm = PATH+"frmSenaraiLaporanUnitBaitulmalPBN.jsp";			   			
				context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit",null,"style=width:340"," onChange=\"doChangeUnit();\"",idNegeri));
    			//mylog.info("userLevel="+userLevel+"::idnegeri="+pej.getIdNegeri());
				socDaerah =  HTML.SelectDaerahByUnitPPK("socDaerah", null, "", "",socIdUnit);
       			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("","unit","L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","unit","R");
    			vecHash = getILaporan().getLaporanMengikutSeksyen(ID_SEKSYEN,"unit","L");
    			if("doChangeUnit".equals(submit)){
    				context.put("selectUnit",HTML.SelectUnitPPKByNegeri("socUnit",Long.parseLong(socIdUnit),"style=width:400"," onChange=\"doChangeUnit();\"",idNegeri));
    				//context.put("selectDaerah",HTML.SelectDaerahByUnitPPK("socDaerah", null, "", "",socUnit));
    				socDaerah =  HTML.SelectDaerahByUnitPPK("socDaerah", null, "", "",socIdUnit);
    	    	}
    			
    		}else if(userLevel==6){
    			System.out.println("userLevel==6");
    			socNegeri = UtilHTML.SelectNegeri("socNegeri",0L," style=width:340  onChange=\"doChangeNegeri();\"");
    			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",Long.parseLong(socIdUnit)," style=width:340",null);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null," style=width:340");
    			//vecHash = FrmUtilData.getLaporanMengikutSeksyen("",null,"L");
    			vecHash = getILaporan().getLaporanMengikutSeksyen(ID_SEKSYEN,null,"L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("",null,"R");
        		//myLog.info("userLevel="+submit);

    			if(submit.equals("PilihNegeri")){
    	    		//myLog.info("idNegeriPilih="+getParam("socNegeri"));
    	    		//myLog.info("socIdUnit="+socIdUnit);
    	    		String idNegeriPilih = getParam("socNegeri").equals("")?"0":getParam("socNegeri");
        			socNegeri = UtilHTML.SelectNegeri("socNegeri",Long.parseLong(idNegeriPilih)," style=width:340  onChange=\"doChangeNegeri();\"");
        			socUnit = HTML.SelectUnitPPKByNegeri("socUnit",Long.parseLong(socIdUnit)," style=width:340"," onChange=\"doChangeNegeri();\"",idNegeriPilih);
    				socDaerah =  HTML.SelectDaerahByUnitPPK("socDaerah", null, "", " style=width:340",socIdUnit);
    				
    	    	}
    			context.put("selectNegeri",socNegeri);
    			context.put("selectUnit",socUnit);
    			
    		}else{
    			vm = PATH+"frmSenaraiLaporanUnitBaitulmalPBN.jsp";
    			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());
//       		vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah","L");
    			vecHash = getILaporan().getLaporanMengikutSeksyen(ID_SEKSYEN,"daerah","L");
    			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","daerah","R");
   			
    		}
    	}
		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);    		
   		//dropdown
//		context.put("selectNegeri",socNegeri);
//		context.put("selectUnit",socUnit);
		context.put("selectDaerah",socDaerah);
		context.put("userLevel",userLevel);
		return vm;
		
	}
	
	private ILaporan getILaporan(){
		if(iLaporan == null)
			iLaporan = new LaporanBean();
		return iLaporan;
		
	}	
	
	
}

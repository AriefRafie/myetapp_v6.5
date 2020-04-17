package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class FrmSenaraiLaporanHTPPajakanKecil extends AjaxBasedModule {
	/**
	 * 
	 */
	private InternalUser iu = null;
	private String DISABILITY = " disabled class=disabled ";
	
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmSenaraiLaporanHTPPajakanKecil.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	String socDaerahBaru = "";

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/htp/frmSenaraiLaporanHTP.jsp";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		//String userId = (String)session.getAttribute("_portal_login");
		String userId = (String)session.getAttribute("_ekptg_user_id");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
		
		iu = InternalUserUtil.getSeksyenId(userId);
		String idNegeri = iu.getIdNegeri();
	
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
		
			User user = null;
    		user = PrepareUser.getUserById(userId);
		
    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			String id_kementerian = getParam("socUnit");
			displayNegeriUnit(tempIdNegeri,0L);
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(""+tempIdNegeri,"socDaerahBaru");
   			socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1")," style=\"width:400\"");
			paparanRekod(userLevel,userId);
			
 /*   	}else if ("PilihUnit".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
 			String id_kementerian = getParam("socUnit");
 			//socUnit = HTML.SelectKementerian("socUnit", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeUnit()\" ");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(""+tempIdNegeri,"socDaerahBaru");
 			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1")," style=\"width:400\"");
			paparanRekod(userLevel,userId);
 
    	}*/
    	}else if ("PilihUnit".equals(submit)) {
    		tempIdNegeri = Long.parseLong(getParam("socNegeri"));
 			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		} 
 			String id_kementerian = getParam("socUnit");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1")," style=\"width:400\"");
  			paparanRekod(userLevel,userId);
    	}
			else if ("PilihUnitLevel".equals(submit)) {
			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
 			String idPejabat = getParam("socUnit");
			Tblrujpejabatjkptg pej = null;
			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
 			displayNegeriUnit(pej.getIdNegeri(),Long.parseLong(idPejabat));  		
 			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", idPejabat);
			//paparanRekod(userLevel);
 			
     	}else{
 			//mylog.info("userLevel="+userLevel);
			if(userLevel==1){
    			vm = "app/ppk/frmSenaraiLaporanHTPUnit.jsp";
     			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());
    			//paparanRekod(userLevel);
    		}else if(userLevel==2){
    			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
    			Tblrujpejabatjkptg pej = null;
    			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
     			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+pej.getIdNegeri());   			
    			socDaerah =  UtilHTML.SelectDaerahByNegeri(""+pej.getIdNegeri(),"socDaerah");
    			//paparanRekod(userLevel);
    		}else if(userLevel==6){
      			displayNegeriUnitAll(tempIdNegeri);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");
    			//paparanRekod(userLevel);
    		}else{
         		displayNegeri(Long.parseLong(idNegeri));
         		socDaerahBaru = UtilHTML.SelectDaerahByNegeri(idNegeri,"socDaerahBaru",null,"");
        		socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
        		socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"style=\"width:400\"");
        		paparanRekod(userLevel,userId);

    		}
     		
     	}
		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);
  		
   		//dropdown
		context.put("selectNegeri",socNegeri);
		context.put("selectUnit",socUnit);
		context.put("selectDaerah",socDaerah);
		context.put("selectDaerahBaru",socDaerahBaru);
		context.put("idNegeri",idNegeri);

		return vm;
	}
	
/*	public void displayNegeri(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		//socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", "", null);
	}*/
	
	public void displayNegeri(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, DISABILITY	, " onChange=\"doChangeNegeri()\" ", null);
	}
	
	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);   			
	}
	
	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", "", null);
		//socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"");   			
		socUnit = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
	}
	
	public void displayNegeriUnitAll(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"");   			
	}
	
	public void display(Long tempIdNegeri) throws Exception{	
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);   			
	}
	
	private void paparanRekod(int userLevel,String login) throws Exception{
		if(userLevel==1){
   			vecHash = FrmUtilData.getLaporanMengikutSeksyen("","daerah","L");
			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","daerah","R");
		}else if(userLevel==2){
			vecHash = FrmUtilData.getLaporanMengikutSeksyen("","unit","L");
			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("","unit","R");
		}else if(userLevel==6){
			vecHash = FrmUtilData.getLaporanMengikutSeksyen("",null,"L");
			vecRekod = FrmUtilData.getLaporanMengikutSeksyen("",null,"R");
		}else{
			Vector<?> v = FrmUtilData.getSubUrusanByRole(login);
			Tblrujsuburusan f = null;
			String s = "TIADA";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);				
				
				if (i==0) {
					s = ""+f.getIdSuburusan();
				} else {
					s += ","+f.getIdSuburusan();
				}
			}
			vecHash = FrmUtilData.getLaporanMengikutUrusan("44",null,"L");
			
		}
		
	}
}

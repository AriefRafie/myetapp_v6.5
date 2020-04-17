package ekptg.view.htp;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class FrmSenaraiLaporanHTPPerletakhakan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmSenaraiLaporanHTPPerletakhakan.class);
	String socNegeri = "";
	String socKementerian = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	String socDaerahBaru = "";

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/htp/frmSenaraiLaporanHTP.jsp";
   		String submit = getParam("command");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
		Long tempIdKementerian = 0L;
		Long tempIdAgensi = 0L;
		User user = null;

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
		
    		user = PrepareUser.getUserById(userId);
    	String tarikhMula = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
    	String tarikhAkhir = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");	
    	
    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
       		if(!"".equals(getParam("socUnit"))){
       			tempIdKementerian = Long.parseLong(getParam("socUnit"));
    		}else{
    			tempIdKementerian = Long.parseLong(String.valueOf("0"));
    		}
       		displayNegeriUnit(tempIdNegeri,tempIdKementerian);
  			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(""+tempIdNegeri,"socDaerahBaru");
       		if(!"".equals(getParam("socDaerah"))){
       			tempIdAgensi = Long.parseLong(getParam("socDaerah"));
    		}else{
    			tempIdAgensi = Long.parseLong(String.valueOf("0"));
    		}  
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
       		socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKementerian),tempIdAgensi,"");
			paparanRekod(userLevel,userId);
			
    	}else if ("PilihUnit".equals(submit)) {
       		if(!"".equals(getParam("socNegeri"))){
    			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
    		}else{
    			tempIdNegeri = Long.parseLong(String.valueOf("0"));
    		}
  			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		} 
 			String id_kementerian = getParam("socUnit");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1"),"");
			paparanRekod(userLevel,userId);
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
 

     	}else if ("PilihUnitLevel".equals(submit)) {
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
     			socKementerian = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+pej.getIdNegeri());   			
    			socDaerah =  UtilHTML.SelectDaerahByNegeri(""+pej.getIdNegeri(),"socDaerah");
    			//paparanRekod(userLevel);
    		}else if(userLevel==6){
      			displayNegeriUnitAll(tempIdNegeri);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");
    			//paparanRekod(userLevel);
    		}else{
   				displayNegeri(0L);
      			socDaerahBaru = UtilHTML.SelectDaerah("socDaerahBaru",null,"");
    			socKementerian = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\" ");
    			socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"");
    			paparanRekod(userLevel,userId);

    		}
    	}
		this.context.put("txdMula", tarikhMula);  
		this.context.put("txdAkhir", tarikhAkhir);  

		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);
  		
   		//dropdown
		context.put("selectNegeri",socNegeri);
		context.put("selectUnit",socKementerian);
		context.put("selectDaerah",socDaerah);
		context.put("selectDaerahBaru",socDaerahBaru);

		return vm;
	}
	
	public void displayNegeri(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
	}
	
	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socKementerian = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\" ");
	}
	
	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socKementerian = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\" ");
	}
	
	public void displayNegeriUnitAll(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socKementerian = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"");   			
	}
	
	public void display(Long tempIdNegeri) throws Exception{	
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socKementerian = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);   			
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
			vecHash = FrmUtilData.getLaporanMengikutUrusan("16,19,20,21,22",null,"L");
			
		}
		
	}
}

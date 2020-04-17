package ekptg.view.htp;

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

public class FrmSenaraiLaporanHTPGadaian extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmSenaraiLaporanHTPGadaian.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	String socDaerahBaru = "";
	String jenisLaporan = "";
    String tarikhMula = "";		
    String tarikhAkhir = "";	

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/htp/laporan/gadaian/frmSenaraiLaporanHTP.jsp";
   		String submit = getParam("command");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
        jenisLaporan =  getParam("socjenislaporan");
	
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
			displayNegeriUnit(tempIdNegeri,0L);
			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		}
      		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), " style=\"width:400\"");
  			socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\"  style=\"width:400\"");
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"  style=\"width:400\"");
			paparanRekod(userLevel,userId);
			
    	}else if ("PilihUnit".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
 			String id_kementerian = getParam("socUnit");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		}
      		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), " style=\"width:400\"");
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1"),"  style=\"width:400\"");
			paparanRekod(userLevel,userId);
			
     	}else if ("PilihUnitLevel".equals(submit)) {
			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
 			String idPejabat = getParam("socUnit");
			Tblrujpejabatjkptg pej = null;
			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
 			displayNegeriUnit(pej.getIdNegeri(),Long.parseLong(idPejabat));  		
 			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, " style=\"width:400\"", "", idPejabat);
			//paparanRekod(userLevel);
			
     	}else if ("senarailaporan".equals(submit)) {
 			myLog.info("senarailaporan");
			tarikhMula = "";
			tarikhAkhir = "";

    		if(!"".equals(getParam("socNegeri"))){
    			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
    		}else{
    			tempIdNegeri = Long.parseLong(String.valueOf("0"));
    		}
//			String tempIdDaerah = getParam("socDaerahBaru");
//      		if("".equals(getParam("socDaerahBaru"))){
//     			tempIdDaerah = "0";
//    		} 
//      		if(!"".equals(getParam("socUnit"))){
//       			tempIdKementerian = Long.parseLong(getParam("socUnit"));
//    		}else{
//    			tempIdKementerian = Long.parseLong(String.valueOf("0"));
//    		}
      		displayNegeriUnitXNegeri(tempIdNegeri,0L);  	
//			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
//  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKementerian),Long.parseLong("1")," style=\"width:400\"");
//			paparanRekod(userLevel,userId);
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		} 			
     	}else{
 			//mylog.info("userLevel="+userLevel);
     		displayNegeri(0L);
     		//socDaerahBaru = UtilHTML.SelectDaerah("socDaerahBaru",null," style=\"width:400\"");
     		//socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\"  style=\"width:400\"");
     		//socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"  style=\"width:400\"");
     		//paparanRekod(userLevel,userId);

    		
    	}
		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);
		
		String checkN = "";
    	String checkD = "";
    	String checK = "";
    	String checkA = "";
    	String checkL = "";
    	if(jenisLaporan.equals("N"))
    		checkN = "checked";		
		else if(jenisLaporan.equals("D"))			
    		checkD = "checked";		
		else if(jenisLaporan.equals("K"))		
    		checK = "checked";		
		else if(jenisLaporan.equals("A"))		
    		checkA = "checked";		
		else if(jenisLaporan.equals("L"))		
    		checkL = "checked";		
		
		context.put("checkN",checkN);
		context.put("checkD",checkD);
		context.put("checK",checK);
		context.put("checkA",checkA);
		context.put("checkL",checkL);
		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);
		this.context.put("txdMula", tarikhMula);  
		this.context.put("txdAkhir", tarikhAkhir); 
  		
   		//dropdown
		context.put("selectNegeri",socNegeri);
//		context.put("selectUnit",socUnit);
//		context.put("selectDaerah",socDaerah);
//		context.put("selectDaerahBaru",socDaerahBaru);
		context.put("jenisLaporan",jenisLaporan);

		return vm;
	}
	
	public void displayNegeri(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"  style=\"width:400\"", null);
	}
	
	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"  style=\"width:400\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit,"  style=\"width:400\""," onChange=\"doChangeUnit()\"",""+tempIdNegeri);   			
	}
	
	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", "  style=\"width:400\"", null);
		//socUnit = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\"  style=\"width:400\"");
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
			vecHash = FrmUtilData.getLaporanMengikutUrusan("38",null,"L");
			
		}
		
	}
	
	
}

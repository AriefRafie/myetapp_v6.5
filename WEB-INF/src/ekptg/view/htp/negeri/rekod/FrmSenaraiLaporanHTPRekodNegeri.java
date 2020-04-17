package ekptg.view.htp.negeri.rekod;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpLaporanBean;
import ekptg.model.htp.IHtpLaporan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class FrmSenaraiLaporanHTPRekodNegeri extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.negeri.rekod.FrmSenaraiLaporanHTPRekodNegeri.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	String templates = "";
	private IHtpLaporan iLaporan = null;
	private InternalUser iu = null;
	private String jenisTanah ="";

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/htp/rekod/laporan/frmSenaraiLaporanHTP.jsp";
   		String submit = getParam("command");
		String userLogin = (String)session.getAttribute("_portal_login");
		String userId = (String)session.getAttribute("_ekptg_user_id");

		int userLevel = 0;
		Long tempIdNegeri = 0L;
		String socDaerahBaru = "";
		Long tempIdKementerian = 0L;

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
//    	user = PrepareUser.getUserById(userId);
		iu = InternalUserUtil.getSeksyenId(userId);
		tempIdNegeri = Long.parseLong(iu.getIdNegeri());
// 		myLog.info("tempIdNegeri="+tempIdNegeri);

        String tarikhMula = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
        String tarikhAkhir = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
        jenisTanah = getParam("socJenisTanah");		
 		//myLog.info("jenisTanah="+jenisTanah);

    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			displayNegeriUnit(tempIdNegeri,0L);
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru");
    		Long tempIdKem = 0L;
    		paparanRekod(userLevel,userLogin);
    		getLaporanTemplates(jenisTanah,"neg");
    		
    		if(!"".equals(getParam("socUnit"))){
    			tempIdKem = Long.parseLong(getParam("socUnit"));
        		//getLaporanTemplates(jenisTanah,"Neg");
    		}else{
    			tempIdKem = Long.parseLong(String.valueOf("0"));
    		}
   			socUnit = HTML.SelectKementerian("socUnit", tempIdKem, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
   	   		Long tempIdAgensi= 0L;
    		if(!"".equals(getParam("socDaerah"))){
    			tempIdAgensi = Long.parseLong(getParam("socDaerah"));
    		}else{
    			tempIdAgensi = Long.parseLong(String.valueOf("0"));
    		}
    		socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKem),tempIdAgensi," style=\"width:400\"");

    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
    		
    	}else if ("PilihUnit".equals(submit)) {
//			paparanRekod(userLevel,userLogin);
    		getLaporanTemplates(jenisTanah,"neg");
    		if(!"".equals(getParam("socNegeri"))){
    			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
    		}else{
    			tempIdNegeri = Long.parseLong(String.valueOf("0"));
    		}
    		Long tempIdDaerah = 0L;
    		if(!"".equals(getParam("socDaerahBaru"))){
    			tempIdDaerah = Long.parseLong(getParam("socDaerahBaru"));
    		}else{
    			tempIdDaerah = Long.parseLong(String.valueOf("0"));
    		}
    		String id_kementerian = getParam("socUnit");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",tempIdDaerah, "");
   	   		Long tempIdAgensi= 0L;
    		if(!"".equals(getParam("socDaerah"))){
    			tempIdAgensi = Long.parseLong(getParam("socDaerah"));
    		}else{
    			tempIdAgensi = Long.parseLong(String.valueOf("0"));
    		}
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,tempIdAgensi," style=\"width:400\"");
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

    	}else if ("senarailaporan".equals(submit)) {
 			myLog.info("senarailaporan");
    		if(!jenisTanah.equals("")){
				if(jenisTanah.equals("1")){
					templates = "Milik" ;
					
				}else if(jenisTanah.equals("2")){
					templates = "Rizab" ;
		
				}
	    		vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);
			    			
    		}
    		myLog.info(getParam("socNegeri"));
    		if(!getParam("socNegeri").equals("0")){
    			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
        		if(!jenisTanah.equals("")){
        			if(jenisTanah.equals("1")){
        				templates = "Milik%%Negeri" ;
      				
        			}else if(jenisTanah.equals("2")){
        				templates = "Rizab%%Negeri" ;
    		
        			}
            		vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
        		}
    		}else{
    			tempIdNegeri = Long.parseLong(String.valueOf("0"));
    		}
			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		} 
      		if(!"".equals(getParam("socUnit"))){
      			tempIdKementerian = Long.parseLong(getParam("socUnit"));
        		if(!jenisTanah.equals("")){
        			//vecHash = FrmUtilData.getLaporanMengikutUrusan("61",null,"L");
        			if(jenisTanah.equals("1")){
        				templates = "Milik%%Kem" ;
      				
        			}else if(jenisTanah.equals("2")){
        				templates = "Rizab%%Kem" ;
    		
        			}
            		vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
        		}
    		}else{
    			tempIdKementerian = Long.parseLong(String.valueOf("0"));
    		}
      		displayNegeriUnitXNegeri(tempIdNegeri,tempIdKementerian);  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKementerian),Long.parseLong("1")," style=\"width:400\"");
//			paparanRekod(userLevel,userId);
  			if(!"".equals(getParam("socNegeri")) && !"".equals(getParam("socUnit"))){
      			tempIdKementerian = Long.parseLong(getParam("socUnit"));
        		if(!jenisTanah.equals("")){
        			//vecHash = FrmUtilData.getLaporanMengikutUrusan("61",null,"L");
        			if(jenisTanah.equals("1")){
        				templates = "MilikNeg%%Kementerian" ;
      				
        			}else if(jenisTanah.equals("2")){
        				templates = "RizabNeg%%Kementerian" ;
    		
        			}
            		vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
        		}
    		}

			if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}			
 
     	}else{
     		myLog.info("userLevel="+userLevel);
			if(userLevel==1){
    			vm = "app/ppk/frmSenaraiLaporanHTPUnit.jsp";
     			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", user.getId_pejabatjkptg());

			}else if(userLevel==2){
    			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
    			Tblrujpejabatjkptg pej = null;
    			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
     			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+pej.getIdNegeri());   			
    			socDaerah =  UtilHTML.SelectDaerahByNegeri(""+pej.getIdNegeri(),"socDaerah");

			}else if(userLevel==6){
      			displayNegeriUnitAll(tempIdNegeri);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");

			}else{
 				displayNegeri(tempIdNegeri);
    			//socDaerahBaru = UtilHTML.SelectDaerah("socDaerahBaru",null,"");
    			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru");
    			socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
    			socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1")," style=\"width:400\"");
    			paparanRekod(userLevel,userLogin);

    		}
			
			
    	}
		this.context.put("idJenisTanah", jenisTanah);	

		context.put("senaraiLaporan",vecHash);
		context.put("senaraiRekod",vecRekod);
  		
   		//dropdown
		context.put("selectNegeri",socNegeri);
		context.put("selectUnit",socUnit);
		context.put("selectDaerah",socDaerah);
		context.put("selectDaerahBaru",socDaerahBaru);
		context.put("tempIdNegeri",tempIdNegeri);
		this.context.put("txdMula", tarikhMula);  
		this.context.put("txdAkhir", tarikhAkhir); 		
		return vm;
		
	}
	
	public void displayNegeri(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, " disabled class=\"disabled\" ", " onChange=\"doChangeNegeri()\"", null);

	}
	
	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, " disabled=\"disabled\"", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);   			
	}
	
	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, " disabled class=\"disabled\"", " onChange=\"doChangeNegeri()\"", null);
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
//			vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", "Negeri");       			    			
			vecHash =getILaporan().getLaporanMengikutUrusan("61",null,"L","neg","'HTPRekodRingkasanRizabNegeri','HTPRekodRingkasanMilikNegeri'");

		}
		
	}
	
	private IHtpLaporan getILaporan(){
		if(iLaporan==null){
			iLaporan = new HtpLaporanBean();
		}
		return iLaporan;
		
	}
	
	private void getLaporanTemplates(String jenisTanah,String template) throws Exception{
		if(!jenisTanah.equals("")){
			if(jenisTanah.equals("1")){
				templates = "milik%%"+template ;
				
			}else if(jenisTanah.equals("2")){
				templates = "rizab%%"+template ;
	
			}
			myLog.info("jenisTanah:templates="+templates);
			//vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
			vecHash = getILaporan().getLaporanMengikutUrusan("61",null,"L",templates,"'HTPRekodRingkasanRizabNegeri','HTPRekodRingkasanMilikNegeri'");

		}else{
			myLog.info("!jenisTanah:template="+template);
			if(!"".equals(getParam("socUnit"))){
				template = template+"%%kem" ;   		
    		}		
			vecHash = getILaporan().getLaporanMengikutUrusan("61",null,"L",template,"'HTPRekodRingkasanRizabNegeri','HTPRekodRingkasanMilikNegeri'");
	
		}
	
	}
	
}
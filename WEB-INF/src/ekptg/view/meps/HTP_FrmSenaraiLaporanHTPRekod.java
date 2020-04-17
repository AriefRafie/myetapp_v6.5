package ekptg.view.meps;

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

public class HTP_FrmSenaraiLaporanHTPRekod extends AjaxBasedModule {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(HTP_FrmSenaraiLaporanHTPRekod.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/meps/htp/frmSenaraiLaporanHTP.jsp";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
		String socDaerahBaru = "";

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
        	String tarikhMula = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");
        	String tarikhAkhir = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");

    	if ("PilihNegeri".equals(submit)){

			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			displayNegeriUnit(tempIdNegeri,0L);
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(""+tempIdNegeri,"socDaerahBaru");
    		Long tempIdKem = 0L;
    		if(!"".equals(getParam("socUnit"))){
    			tempIdKem = Long.parseLong(getParam("socUnit"));
    		}else{
    			tempIdKem = Long.parseLong(String.valueOf("0"));
    		}
   			socUnit = HTML.SelectKementerian("socUnit", tempIdKem, null, "onChange=\"doChangeUnit()\" ");
   	   		Long tempIdAgensi= 0L;
    		if(!"".equals(getParam("socDaerah"))){
    			tempIdAgensi = Long.parseLong(getParam("socDaerah"));
    		}else{
    			tempIdAgensi = Long.parseLong(String.valueOf("0"));
    		}
    		socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKem),tempIdAgensi,"");
			paparanRekod(userLevel,userId);
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}

    	}else if ("PilihUnit".equals(submit)) {
			/*tempIdNegeri = Long.parseLong(getParam("socNegeri"));
 			String id_kementerian = getParam("socUnit");
 			//socUnit = HTML.SelectKementerian("socUnit", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeUnit()\" ");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(""+tempIdNegeri,"socDaerahBaru");
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1"),"");
			paparanRekod(userLevel,userId);
     	*/
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
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,tempIdAgensi,"");
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
     			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null," style=width:340 "," onChange=\"doChangeUnit()\"",""+pej.getIdNegeri());
    			socDaerah =  UtilHTML.SelectDaerahByNegeri(""+pej.getIdNegeri(),"socDaerah");
    			//paparanRekod(userLevel);
    		}else if(userLevel==6){
      			displayNegeriUnitAll(tempIdNegeri);
    			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");
    			//paparanRekod(userLevel);
    		}else{
   				//displayNegeri(0L);
 				displayNegeri(0L);
    			socDaerahBaru = UtilHTML.SelectDaerah("socDaerahBaru",null,"style=width:340");
    			socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\" ");
    			socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"");
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
		this.context.put("txdMula", tarikhMula);
		this.context.put("txdAkhir", tarikhAkhir);

		return vm;
	}

	public void displayNegeri(Long tempIdNegeri) throws Exception{
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		//socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", "", null);
	}

	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);
	}

	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		//socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"");
		socUnit = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\" ");
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
			//vecHash = FrmUtilData.getLaporanMengikutUrusan("16,19,20,21,22",null,"L");
			vecHash = FrmUtilData.getLaporanMengikutUrusan("61",null,"L");

		}

	}
}

package ekptg.view.meps;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpLaporanBean;
import ekptg.model.htp.IHtpLaporan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.ppk.PPKUtilHTML;

public class FrmLaporanPermohonanOnline extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.meps.FrmLaporanPermohonanOnline.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	private IHtpLaporan iLaporan = null;
	private String jenisTanah ="";
	String templates = "";
	private String jenisTempoh = "seluruh";
	private String bulan = lebah.util.Util.getDateTime(new Date(), "MM");
	private String tahun = lebah.util.Util.getDateTime(new Date(), "yyyy");
	private String bulanSE = "";
	private String tahunSE = "";

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/meps/frmSenaraiLaporanPermohonanOnline.jsp";
   		String submit = getParam("command");
		String userId = (String)session.getAttribute("_portal_login");
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
    	user = PrepareUser.getUserById(userId);
        String tarikhMula = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
        String tarikhAkhir = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
        jenisTanah = getParam("socJenisTanah");		
 		//myLog.info("jenisTanah="+jenisTanah);

    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			displayNegeriUnit(tempIdNegeri,0L);
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru");
    		Long tempIdKem = 0L;
    		paparanRekod(userLevel,userId);
    		//getLaporanTemplates(jenisTanah,"Neg");
    		
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
    		//socJenisTanahtemp
//    		paparanRekod(userLevel,userId);
    		if(!jenisTanah.equals("")){
    			if(jenisTanah.equals("1")){
    				templates = "Milik" ;
  				
    			}else if(jenisTanah.equals("2")){
    				templates = "Rizab" ;
			
    			}
         		vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);
         			
    		}
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
    		
    	}else if ("PilihUnit".equals(submit)) {
			paparanRekod(userLevel,userId);
    		//getLaporanTemplates(jenisTanah,"Kem");
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
    		if(!jenisTanah.equals("")){
    			if(jenisTanah.equals("1")){
    				templates = "Milik" ;
  				
    			}else if(jenisTanah.equals("2")){
    				templates = "Rizab" ;
			
    			}
         		vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);
         			
    		}
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
 			
     	}else if ("senarailaporan".equals(submit)) {
 			//myLog.info("senarailaporan");
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
        			//vecHash = FrmUtilData.getLaporanMengikutUrusan("61",null,"L");
        			if(jenisTanah.equals("1")){
        				templates = "MilikNeg" ;
      				
        			}else if(jenisTanah.equals("2")){
        				templates = "RizabNeg" ;
    		
        			}
            		//vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
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
            		//vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
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
            		//vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
        		}
    		}

			if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}			
 
     	}else if("doChange".equals(submit)){
        		
        		bulan = getParam("socBulan");
        		tahun = getParam("socTahun"); 
        		bulanSE = getParam("socBulanSehingga");
        		tahunSE = getParam("socTahunSehingga"); 
        	
        		jenisTempoh = getParam("sorJenisTempoh");   
        		myLog.info("jenisTempoh="+jenisTempoh);
//        		String tempIdPejabat = getParam("id_pejabat");
//        		if(tempIdPejabat.equals(id_pejabat)){
//        			id_daerah = getParam("socDaerah");  
//        		}else{
//        			id_daerah = "000";
//        		}

        		getAndSetDataLaporan("","",bulan,tahun,bulanSE,tahunSE);
        		
    	}else{
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
 				displayNegeri(0L);
    			socDaerahBaru = UtilHTML.SelectDaerah("socDaerahBaru",null,"");
    			socUnit = HTML.SelectKementerian("socUnit", null, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
    			socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1")," style=\"width:400\"");
    			paparanRekod(userLevel,userId);

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
		this.context.put("txdMula", tarikhMula);  
		this.context.put("txdAkhir", tarikhAkhir); 	
		
	   	context.put("jenisTempoh", jenisTempoh);
     	context.put("bulan",bulan);
    	context.put("tahun",tahun);
    	context.put("bulanSE",bulanSE);
    	context.put("tahunSE",tahunSE);

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
			//vecHash = FrmUtilData.getLaporanMengikutUrusan("16,19,20,21,22",null,"L");
			vecHash = FrmUtilData.getLaporanMengikutUrusan("61",null,"L");
			
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
				templates = "Milik%%"+template ;
				
			}else if(jenisTanah.equals("2")){
				templates = "Rizab%%"+template ;
	
			}
			vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", templates);       			    			
		}else{
			vecHash = getILaporan().getLaporanMengikutUrusanLike("61", null, "L", template);       			    			
		
		}
	
	}
	
	private void getAndSetDataLaporan(String id_pejabat, String id_daerah, String bulan, String tahun, String bulanSE, String tahunSE) throws Exception{	
//		context.put("selectPejabat",HTML.SelectPejabatJKPTGLaporan("socPejabat",Utils.parseLong(id_pejabat),null,"id='socPejabat' style=width:auto onChange=\"doChange()\""));		
//		if(id_pejabat!=""){
//			context.put("selectDaerah",HTML.SelectDaerahLaporanByIdPejabatJKPTG(id_pejabat,"socDaerah",Utils.parseLong(id_daerah),null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
//		}else{
//			context.put("selectDaerah",HTML.SelectDaerahLaporan("socDaerah",Utils.parseLong(id_daerah),null,"id='socDaerah' style=width:325px onChange=\"doChange()\""));
//		}
		
		context.put("selectBulan",HTML.SelectBulan("socBulan",Utils.parseLong(bulan),null,"id='socBulan' style=width:200px onChange=\"doChange()\""));
		context.put("selectTahun",HTML.SelectTahun("socTahun",tahun,null,"id='socTahun' style=width:200px onChange=\"doChange()\""));
		
		//tambahan untuk julat masa
		context.put("selectBulanSehingga",HTML.SelectBulan("socBulanSehingga",Utils.parseLong(bulanSE),null,"id='socBulanSehingga' style=width:200px onChange=\"doChange()\""));
		context.put("selectTahunSehingga",HTML.SelectTahun("socTahunSehingga",tahunSE,null,"id='socTahunSehingga' style=width:200px onChange=\"doChange()\""));
		
		
	}//close getAndSetDataLaporan

	
}

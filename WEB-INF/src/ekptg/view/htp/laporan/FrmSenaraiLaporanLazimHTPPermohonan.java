package ekptg.view.htp.laporan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.HTPPermohonanTanahBean;
import ekptg.model.htp.HtpLaporanBean;
import ekptg.model.htp.IHtpLaporan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilHTML;

public class FrmSenaraiLaporanLazimHTPPermohonan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3726384873910325630L;

	private final String PATH="app/htp/laporan/";
	private IHtpLaporan iLaporan = null;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.laporan.FrmSenaraiLaporanLazimHTPPermohonan.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	String socDaerahBaru = "";
	String subUrusan = "";
	String templates = "";
	String jenisLaporan = "";
	HTPPermohonanTanahBean tanahBean =null;

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = PATH+"permohonan/frmSenaraiLaporanLazimHTP.jsp";
   		String submit = getParam("command");
   		String userId = (String)session.getAttribute("_ekptg_user_id");
		int userLevel = 0;
		Long tempIdAgensi = 0L;
		Long tempIdKementerian = 0L;
		Long tempIdNegeri = 0L;
        ///String tarikhMula = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
        //String tarikhAkhir = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");	
        String tarikhMula = "";		
        String tarikhAkhir = "";	
        subUrusan =  getParam("socJenisTanah");
        jenisLaporan =  getParam("socjenislaporan");
 		tanahBean = new HTPPermohonanTanahBean();
        
    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			tanahBean.setLabelDaerah(context, getParam("socNegeri"));

       		if(!"".equals(getParam("socUnit"))){
       			tempIdKementerian = Long.parseLong(getParam("socUnit"));
    		}else{
    			tempIdKementerian = Long.parseLong(String.valueOf("0"));
    		}
       		displayNegeriUnit(tempIdNegeri,tempIdKementerian);

 			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		}
      		socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
       		if(!"".equals(getParam("socDaerah"))){
       			tempIdAgensi = Long.parseLong(getParam("socDaerah"));
    		}else{
    			tempIdAgensi = Long.parseLong(String.valueOf("0"));
    		} 
       		socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKementerian),tempIdAgensi," style=\"width:400\"");

// 			if(subUrusan.equals(""))
// 				paparanRekod(); 				
// 			else
// 				paparanRekod(subUrusan);
 			
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
    	
    	}else if ("PilihUnit".equals(submit)) {
    		if(!"".equals(getParam("socNegeri"))){
    			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
    		}else{
    			tempIdNegeri = Long.parseLong(String.valueOf("0"));
    		}
 			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru")) || "-1".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "-1";
    		} 
 			String id_kementerian = getParam("socUnit");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong(tempIdDaerah)," style=\"width:400\"");
 
//			if(subUrusan.equals(""))
// 				paparanRekod(); 				
// 			else
// 				paparanRekod(subUrusan);
			
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}			
			
     	}else if ("PilihUnitLevel".equals(submit)) {
//			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
// 			String idPejabat = getParam("socUnit");
//			Tblrujpejabatjkptg pej = null;
			
     	}else if ("senarailaporan".equals(submit)) {
 			myLog.info("senarailaporan");
			tarikhMula = "";
			tarikhAkhir = "";

			tanahBean.setLabelDaerah(context, getParam("socNegeri"));
    		if(!"".equals(getParam("socNegeri"))){
    			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
    		}else{
    			tempIdNegeri = Long.parseLong(String.valueOf("0"));
    		}
			String tempIdDaerah = getParam("socDaerahBaru");
      		if("".equals(getParam("socDaerahBaru"))){
     			tempIdDaerah = "0";
    		} 
      		if(!"".equals(getParam("socUnit"))){
       			tempIdKementerian = Long.parseLong(getParam("socUnit"));
    		}else{
    			tempIdKementerian = Long.parseLong(String.valueOf("0"));
    		}
      		displayNegeriUnitXNegeri(tempIdNegeri,tempIdKementerian);  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri), "socDaerahBaru",Long.parseLong(tempIdDaerah), "");
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",String.valueOf(tempIdKementerian),Long.parseLong("1")," style=\"width:400\"");
			paparanRekod(userLevel,userId);
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}			
		
//			if(subUrusan.equals(""))
// 				paparanRekod(); 				
// 			else
// 				paparanRekod(subUrusan);

     	}else{
			tanahBean.setLabelDaerah(context, "");

     		displayNegeri(0L);
     		socDaerahBaru = UtilHTML.SelectDaerah("socDaerahBaru",null,"");
    		socUnit = UtilHTML.selectKementerianLaporan("socUnit", 0L, "", "onChange=\"doChangeUnit()\" style=\"width:400\"");
    		socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"style=\"width:400\"");
    		//paparanRekod();

    	}
    	String checkN = "";
    	String checkD = "";
    	String checK = "";
    	String checkA = "";
    	String checkL = "";
    	myLog.info("jenisLaporan="+jenisLaporan);
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
		context.put("selectUnit",socUnit);
		context.put("selectDaerah",socDaerah);
		context.put("selectDaerahBaru",socDaerahBaru);
		context.put("idUrusan",subUrusan);
		context.put("jenisTanah",subUrusan);
		context.put("jenisLaporan",jenisLaporan);
		context.put("pk",getParam("pilihkeputusan"));
	return vm;
		
	}
	
	public void displayNegeri(Long tempIdNegeri) throws Exception{		
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\" ", null);
	}
	
	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\" ", null);
		socUnit = HTML.SelectKementerian("socUnit",idUnit, null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
	}
	
	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\" ", null);
		socUnit = UtilHTML.selectKementerianLaporan("socUnit",idUnit, "", "onChange=\"doChangeUnit()\" style=\"width:400\"");
	
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
			//1-Lain2 Rizab, 42-Permohonan

			vecHash = getILaporan().getLaporanMengikutUrusan("42,1",null,"L");
		
	}
	
	private void paparanRekod(String subUrusan) throws Exception{
		//1-Lain2 Rizab, 42-Permohonan
//		vecHash = getILaporan().getLaporanMengikutUrusan(subUrusan,null,"L");
		if(subUrusan.equals("42")){
			templates = "TanahMilik" ;
			
		}else if(subUrusan.equals("1")){
			templates = "TanahRizab" ;

		}
		if(!"".equals(getParam("socNegeri"))){
			templates += "%%Negeri" ;

		}
  		if(!"".equals(getParam("socUnit"))){
			templates += "%%Kementerian" ;
		}
  		
		vecHash = getILaporan().getLaporanMengikutUrusanLike(subUrusan, null, "L", templates);       			    			

	}
	
	private void paparanRekod() throws Exception{
		//1-Lain2 Rizab, 42-Permohonan
		vecHash = getILaporan().getLaporanMengikutUrusan("42,1",null,"L");
		//templates = "Negeri" ;
		if(!"".equals(getParam("socNegeri"))){
			templates = "Negeri" ;

		}
  		if(!"".equals(getParam("socUnit"))){
			templates = "Kementerian" ;
		}
		//if(!"".equals(getParam("socNegeri"))){
			vecHash = getILaporan().getLaporanMengikutUrusanLike("42,1", null, "L", templates);  		
		//}
		
	}
	
	private IHtpLaporan getILaporan(){
		if(iLaporan==null){
			iLaporan = new HtpLaporanBean();
		}
		return iLaporan;
		
	}
	
	
}

package ekptg.view.esaduan;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.HtpLaporanBean;
import ekptg.model.htp.IHtpLaporan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilHTML;

public class LaporanAduan extends AjaxBasedModule {
	/**
	 * 
	 */
	private IHtpLaporan iLaporan = null;
	private final String PATH="app/esaduan/laporan/";
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.esaduan.LaporanAduan.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	String socDaerahBaru = "";

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = PATH+"index.jsp";
   		String submit = getParam("command");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
	
    	String idSuburusan = getParam("socsuburusan");
    	if (idSuburusan == null || idSuburusan.trim().length() == 0){
    		idSuburusan = "99999";
    	}		
        String tarikhMula = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");		
        String tarikhAkhir = lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy");	

    	if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			displayNegeriUnit(tempIdNegeri,0L);
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(String.valueOf(tempIdNegeri),"socDaerahBaru");
   			socUnit = UtilHTML.selectKementerianLaporan("socUnit", Long.parseLong(getParam("socUnit")==""?"0":getParam("socUnit")), null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah","0",Long.parseLong("1"),"style=\"width:400\"");
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socsuburusan" ,Long.parseLong(idSuburusan), "", ""));
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
    		//paparanRekod(userLevel,userId);
    		//public Vector<Hashtable<String, Comparable>> getLaporanMengikutUrusanLike
			//(String suburusan, String level, String jlaporan,String template) throws Exception {
			vecHash = getILaporan().getLaporanMengikutUrusanLike("7,17,18",null,"L","Negeri");

    	}else if ("PilihUnit".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			Long tempIdDaerah = Long.parseLong(getParam("socNegeri"));
 			String id_kementerian = getParam("socUnit");
			displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
			socDaerahBaru =  UtilHTML.SelectDaerahByNegeri(""+tempIdNegeri,"socDaerahBaru",tempIdDaerah, "");
   			socUnit = UtilHTML.selectKementerianLaporan("socUnit", Long.parseLong(id_kementerian), null, "onChange=\"doChangeUnit()\" style=\"width:400\"");
			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1")," style=\"width:400\"");
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socsuburusan" ,Long.parseLong(idSuburusan), "", ""));
    		if(!"".equals(getParam("txdMula"))){
    			tarikhMula = getParam("txdMula");
    		}
    		if(!"".equals(getParam("txdAkhir"))){
    			tarikhAkhir = getParam("txdAkhir");
    		}
			//paparanRekod(userLevel,userId);
     	
//    	}else if ("PilihUnitLevel".equals(submit)) {
//			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
// 			String idPejabat = getParam("socUnit");
//			Tblrujpejabatjkptg pej = null;
//			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
// 			displayNegeriUnit(pej.getIdNegeri(),Long.parseLong(idPejabat));  		
// 			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", idPejabat);
     	
    	}else{
   				displayNegeri(0L);
 
    	}
		
    	String sorLaporan = getParam("sorLaporan");   	
    	myLog.info("sorLaporan="+sorLaporan);
    	String checkA = "";
    	String checkB = "";
    	String checkC = "";
    	if(sorLaporan.equals("1")){
			//checked laporan
    		checkA = "checked";		
		}else if(sorLaporan.equals("2")){			
			//checked
    		checkB = "checked";		
		}else if(sorLaporan.equals("3")){		
			//checked
    		checkC = "checked";		
		}
		context.put("checkA",checkA);
		context.put("checkB",checkB);
		context.put("checkC",checkC);
   	
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
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
	}
	
	public void displayNegeriUnit(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.selectNegeriLaporan("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
		socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",idUnit," style=width:340 "," onChange=\"doChangeUnit()\"",""+tempIdNegeri);   			
	}
	
	public void displayNegeriUnitXNegeri(Long tempIdNegeri,Long idUnit) throws Exception{		
		socNegeri = UtilHTML.SelectNegeriXKod("socNegeri",tempIdNegeri, "", " onChange=\"doChangeNegeri()\"", null);
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
	
	private IHtpLaporan getILaporan(){
		if(iLaporan==null){
			iLaporan = new HtpLaporanBean();
		}
		return iLaporan;
		
	}
}

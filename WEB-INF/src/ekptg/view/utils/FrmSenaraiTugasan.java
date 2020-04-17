package ekptg.view.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPPermohonanBean;
import ekptg.model.htp.IHTPPermohonan;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.CukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.ICukaiPenyata;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.rekod.HTPStatusRekodBean;

public class FrmSenaraiTugasan extends AjaxBasedModule{
	/**
	 * 
	 */
	private final String IDURUSANCUKAI = "11";
	private final String IDSUBURUSANREKOD = "61";
	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(ekptg.view.utils.FrmSenaraiTugasan.class);
	Long idHakmilikUrusan = 0L;
	private ICukai iCukai = null;
	private ICukaiPenyata iCukaiPenyata = null;
	private IHTPPermohonan iHTPPermohonan = null;
	private IOnline iOnline = null;
	private IHTPStatus iStatus = null;
	private InternalUser iu = null;	
	private Vector vecSenaraiOnline = null;
	private Vector vecCukaiSenarai = null;
	private boolean isMenuOnline = false;
	
	public String doTemplate2()throws Exception {		 
	    HttpSession session = this.request.getSession();
	    String template_name = "app/utils/";
	    this.context.put("util", new lebah.util.Util());

	    Vector<?> senaraiFail = null;
	    String submit = getParam("command");
	    
	    //penambahan admin 10/1/2017
	    String userId = (String)session.getAttribute("_ekptg_user_id");
	    /*String log_terakhir = LogTerakhir(userId);*/
	    
	    String log_terakhir = LogTerakhir(userId);
		context.put("log_terakhir",log_terakhir);
		System.out.println("********************** log_terakhir : "+log_terakhir);
	    
		String idNegeri = getParam("socNegeriC");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String portal_role = (String)session.getAttribute("myrole");
		context.put("portal_role_",portal_role);

	    if (!("".equals(submit))) {
	    	myLog.info("1-1");


	    
	    }else{ // !=submit
	    	//myLog.info("1");
	    	template_name += "frmSenaraiTugasan.jsp";
	    	senaraiFail = FrmUtilData.getSenaraiTugasan("",(String)session.getAttribute("_ekptg_user_id"),(String)session.getAttribute("_portal_role"));
	    	this.context.put("senaraitugasan", senaraiFail);  
	    	context.remove("senaraionline");
	    	vecSenaraiOnline = getIOnline().findFailOnlineAktif(getParam("txtTajukFail"), getParam("txtNoFail"), idNegeri, idKementerian);
	    	
	    	//cukai
	    	//if((String)session.getAttribute("_portal_role").){
	    		
	    	//}
	    	if(isTab(portal_role,"Cukai")){
	    	 	String socTahun = getParam("Form_tahun")==null||getParam("Form_tahun")==""?lebah.util.Util.getDateTime(new Date(), "yyyy"):getParam("Form_tahun");

	    		context.remove("cukaiSenaraiPenyata");
	    		vecCukaiSenarai = getICukai().getSenaraiNegeriXPenyata(socTahun);
	    		String idNegeris = "";
          		for (int i = 0; i < vecCukaiSenarai.size(); i++) {
          			Hashtable hTemp = (Hashtable)vecCukaiSenarai.elementAt(i);
          			if (i==0) {
          				idNegeris = String.valueOf(hTemp.get("idNegeri"));
    				} else {
    					idNegeris += ","+String.valueOf(hTemp.get("idNegeri"));
    				}
          		}
          	    myLog.info(idNegeris);
          	    Vector vecSenaraiFailXPenyata = getICukaiPenyata().getSenaraiFailXPenyata("11", "", "", idNegeris); 
    	    	this.context.put("vecSenaraiFailXPenyata", vecSenaraiFailXPenyata); 
    	    	
    			Vector vecCukaiHantar = null;
    			String langkahCukaiHantar = "5";
    	  		if(portal_role.contains("HQPengguna")){
    	  			langkahCukaiHantar = "5";
    	 
    			}else if(portal_role.contains("HQPegawai")){
    				langkahCukaiHantar = "6";
    	 		
    			}else if(portal_role.contains("HQPengarah")){
    				langkahCukaiHantar = "7";
    	 
    			}
    	  		vecCukaiHantar = getHTPermohonan().getPermohonanAktifLangkah(IDURUSANCUKAI, langkahCukaiHantar); 
    	    	this.context.put("vecCukaiHantar", vecCukaiHantar);  


	    	}
	    	this.context.put("cukaiSenaraiPenyata", vecCukaiSenarai); 
	    	
			String langkahPajakanKecil= "0";
			String langkahRekod = "0";
			Vector vecMaklumatPembangunan = null;
			Vector vecPajakanKecil = null;
			if(portal_role.contains("PenggunaNegeri")){
	  			langkahRekod = "18";
	  			langkahPajakanKecil = "81";
	 
			}else if(portal_role.contains("PegawaiNegeri")){
	 			langkahRekod = "19";
	  			langkahPajakanKecil = "82";
	 		
			}else if(portal_role.contains("PengarahNegeri")){
	 			langkahRekod = "20";
	  			langkahPajakanKecil = "83";
	 
			}

	    	if(isTab(portal_role,"Rekod")){
	    		int bilTugasanRekod = 0; 
	    		if(portal_role.contains("PengarahNegeri")||portal_role.contains("PegawaiNegeri")||portal_role.contains("PenggunaNegeri")){
	    			/*String userId = (String)session.getAttribute("_ekptg_user_id");*/
	    			iu = InternalUserUtil.getSeksyenId(userId);
	    			idNegeri = iu.getIdNegeri();    			
	    			vecMaklumatPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahRekod,idNegeri);
	    		
	    		}else{
		      		vecMaklumatPembangunan = getStatusRekod().getInfoStatusPermohonan("", IDSUBURUSANREKOD, langkahRekod);   			
	    		}
	    	
	    		bilTugasanRekod = vecMaklumatPembangunan.size();
	    		myLog.info("bilTugasanRekod="+bilTugasanRekod);
	    		context.put("bilTugasanRekodInt",bilTugasanRekod);
	    		context.put("bilTugasanRekod",String.valueOf(bilTugasanRekod));
	    		context.put("vecRekodMaklumatPembangunan",vecMaklumatPembangunan);

	    	}

		 }
	    isMenuOnline = isTab(portal_role,"<i>Online</i>");
    	this.context.put("senaraionline", vecSenaraiOnline);  
    	context.put("isMenuOnline", isMenuOnline);  
    	
    	
	    return template_name;
	    
	}
	
	public String LogTerakhir(String user_id) throws Exception {
		String log_terakhir = "";
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT   WL.USER_NAME,  TO_CHAR(MAX(WL.LOG_DATE),'DD-MM-YYYY hh24:mi:ss') AS LAST_LOGIN "+
				" FROM   USERS U, WEB_LOGGER WL "+ 
				" WHERE   U.USER_LOGIN = WL.USER_NAME(+) AND U.USER_ID = "+user_id+" "+
				" AND U.USER_LOGIN = WL.USER_NAME(+) "+
				" GROUP BY   WL.USER_NAME ";
		
			
			System.out.println(" SQL USER LIST FROM MODEL :"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				log_terakhir = rs.getString("LAST_LOGIN") == null ? "" : rs.getString("LAST_LOGIN");
			}
			System.out.println(" SENARAI USER LIST FROM MODEL :"+log_terakhir);
			return log_terakhir;
		}catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}

	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
		
	}		
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	private ICukaiPenyata getICukaiPenyata(){
		if(iCukaiPenyata==null){
			iCukaiPenyata = new CukaiPenyataBean();
		}
		return iCukaiPenyata;
		
	}
		
	private IHTPPermohonan getHTPermohonan(){
		if(iHTPPermohonan == null){
			iHTPPermohonan = new HTPPermohonanBean();
		}
		return iHTPPermohonan;
		
	}
	
	private boolean isTab(String role, String tab) throws Exception {
		boolean returnValue = false;
		Utils utils = new Utils();
		if(!utils.getTabID(tab,role).equals(""))
			returnValue = true;

		// 2017/01/10 myLog.info(utils.getTabID(tab,role));
		return returnValue;
	}
	
	
	private IHTPStatus getStatusRekod(){
		if(iStatus==null){
			iStatus = new HTPStatusRekodBean();
		}
		return iStatus;
				
	}


	
}

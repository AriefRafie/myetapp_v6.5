package ekptg.view.meps;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujjenisbayaran;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.FrmLaporanKemasukanBayaranData;
import ekptg.model.ppk.FrmLaporanKemasukanBilFailData;
import ekptg.model.ppk.PPKUtilHTML;
import ekptg.model.utils.FrmJenisBayaranData;

public class PPK_FrmLaporanKemasukanBayaran extends AjaxBasedModule {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(PPK_FrmLaporanKemasukanBayaran.class);
	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmLaporanKemasukanBayaran.jsp";
		//String socNegeri = "";
		String socUnit = "";
		String socDaerah = "";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		String jumlahBayaran = "";
		String noFail = "";
		String idPermohonan = "";

		mylog.info("doTemplate2:submit::"+submit);
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

		Vector<?> vecHash = null;
		Vector<?> vecSenaraiCarian = null;
		//Vector<?> vecRekod = null;
   		String idPejabat = (getParam("socUnit")== null) ?"0":getParam("socUnit");
   		String idDaerah = (getParam("Form_id_Daerah")== null) ?"0":getParam("Form_id_Daerah");
       	String idLaporanFail = (getParam("idlaporanbilfail")== "0") ?"0":getParam("idlaporanbilfail");
       	String idJenisBayaran = (getParam("socJenisbayaran")== "0") ?"0":getParam("socJenisbayaran");

 		User user = null;
		user = PrepareUser.getUserById(userId);

    	if ("SelectDaerah".equals(submit)){
  			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",Long.parseLong(idPejabat)," style=width:340 "," onChange=\"javascript:doChangeUnit()\"");
			socDaerah = UtilHTML.SelectDaerahByUnitPPKXKod("Form_id_Daerah",null,"style=width:340","", idPejabat);
			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,idDaerah);

    	}else if ("PilihFail".equals(submit)) {
           	noFail = (getParam("txtNoFail")== null) ?"0":getParam("txtNoFail");
           	idPermohonan = (getParam("idPermohonan")== null) ?"0":getParam("idPermohonan");
           	vecSenaraiCarian = FrmLaporanKemasukanBayaranData.carianFail(noFail, session);
           	socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
			context.put("vecSenaraiCarian",vecSenaraiCarian);

    	}else if ("Carian".equals(submit)) {
           	noFail = (getParam("txtNoFail")== null) ?"0":getParam("txtNoFail");
           	vecSenaraiCarian = FrmLaporanKemasukanBayaranData.carianFail(noFail, session);
   			mylog.info("vecSenaraiCarian:"+vecSenaraiCarian.size());
           	socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
			context.put("vecSenaraiCarian",vecSenaraiCarian);

    	}else if ("Delete".equals(submit)) {
       		//mylog.info("idLaporanFail:"+idLaporanFail);
       		if (FrmLaporanKemasukanBayaranData.delete(idLaporanFail))
     			this.context.put("result", "success");
     		else
     			this.context.put("result", "failed");
			socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
  			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
   			vecHash = FrmLaporanKemasukanBayaranData.getSenarai(user.getId_pejabatjkptg());

     	} else if ("simpan".equals(submit)) {
           	idPermohonan = (getParam("idPermohonan")== null) ?"0":getParam("idPermohonan");
       		String tarikh = (getParam("txdBayar")== null) ?"0":getParam("txdBayar");
       		String rujukan = (getParam("Form_rujukan")== null) ?"0":getParam("Form_rujukan");
       		String bilangan = (getParam("Form_bilangan")== null) ?"0":getParam("Form_bilangan");
       		Hashtable<String, String> parameters = new Hashtable<String, String>();

       		parameters.put("id_Permohonan", idPermohonan);
       		parameters.put("id_JenisBayaran", idJenisBayaran);
       		//parameters.put("tarikh_bayaran", tarikh);
       		parameters.put("no_resit", rujukan);
       		parameters.put("jumlah_bayaran", bilangan);
       		if(idLaporanFail.equals("0")){
       			if (FrmLaporanKemasukanBayaranData.insert(parameters,tarikh, (String)session.getAttribute("_ekptg_user_id")))
       				this.context.put("result", "success");
       			else
       				this.context.put("result", "failed");
       		}else{
         		if (FrmLaporanKemasukanBayaranData.update(parameters, idLaporanFail))
         			this.context.put("result", "success");
         		else
         			this.context.put("result", "failed");

       		}
   			socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
  			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
   			vecHash = FrmLaporanKemasukanBayaranData.getSenarai(user.getId_pejabatjkptg());

     	}else if("SelectJenisBayaran".equals(submit)){
          	noFail = (getParam("txtNoFail")== null) ?"0":getParam("txtNoFail");
           	idPermohonan = (getParam("idPermohonan")== null) ?"0":getParam("idPermohonan");

  			socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",Long.parseLong(idJenisBayaran)," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
			Tblrujjenisbayaran r = FrmJenisBayaranData.getJenisBayaran(idJenisBayaran);
			jumlahBayaran = ""+r.getAmaun();
 			vecHash = FrmLaporanKemasukanBilFailData.getSenarai(idPejabat,null);

    	}else{
    		//User user = null;
    		//user = PrepareUser.getUserById(userId);
			mylog.info("userLevel="+userLevel);
			if(userLevel==1){
    			//Tblrujpejabatjkptg pej = null;
    			//pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
       			socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
    			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
    			vecHash = FrmLaporanKemasukanBayaranData.getSenarai(user.getId_pejabatjkptg());
			}else if(userLevel==2){
       			socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
    			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
    			vecHash = FrmLaporanKemasukanBayaranData.getSenarai(user.getId_pejabatjkptg());

    		}else if(userLevel==6){
    			//socNegeri = UtilHTML.SelectNegeri("socNegeri",0L,"style=width:340"," onChange=\"javascript:doChangeNegeri()\"");
    			socUnit = PPKUtilHTML.SelectUnitPPK("socUnit",null,"style=width:340"," onChange=\"javascript:doChangeUnit()\"");
    			socDaerah = UtilHTML.SelectDaerah("Form_id_Daerah",null,"style=width:340");
     		}else{
    			socUnit = PPKUtilHTML.SelectJenisBayaranBySeksyen("2","socJenisbayaran",null," style=width:340 "," onChange=\"javascript:doJenisBayaran()\"");
    			socDaerah = PPKUtilHTML.SelectCaraBayaran("socCarabayaran",null,"style=width:340",null);
    			vecHash = FrmLaporanKemasukanBayaranData.getSenarai(user.getId_pejabatjkptg());
    		}
    	}
		context.put("senaraiRekod",vecHash);
		//context.put("senaraiRekod",vecRekod);

   		//dropdown
		//context.put("selectNegeri",socNegeri);
		context.put("selectJenisBayaran",socUnit);
		context.put("selectCaraBayaran",socDaerah);
		context.put("jumlahBayaran",jumlahBayaran);
		context.put("noFail",noFail);
		context.put("idPermohonan",idPermohonan);

		return vm;

	}

	public void setParameterValues(Hashtable<String, String> parameters) {
		String name="";
		String value="";
		Enumeration<?> allparam = request.getParameterNames();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        if (name.indexOf("Form_") != -1) { // get only parameters with name like Form_
		        value = request.getParameter(name);
		        parameters.put(name,value);
	        }
		}
	}
}

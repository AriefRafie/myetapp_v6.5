package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.portal.db.PrepareUser;
import lebah.portal.element.User;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.utils.FrmMappingUserPegawaiData;

public class FrmSenaraiSuratHTPPajakanKecil extends AjaxBasedModule {
	
	/**
	 * Sama dengan fungsi class "FrmPajakanKecilSenaraiSurat"
	 */
	private static final long serialVersionUID = 5299932488418079540L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmSenaraiSuratHTPPajakanKecil.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
    String idUrusan = "309";

	public String doTemplate2() throws Exception	{
		
	    HttpSession session = this.request.getSession();
		String vm = "app/htp/frmSenaraiSuratHTP.jsp";
   		String submit = getParam("command");
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
    	Vector<?> list = null;
		//Hashtable<String, String> permohonan = null;
		Hashtable permohonan = null; 
		String idPermohonan = "";

	
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
		
    	/*Vector<?> v = FrmUtilData.getSubUrusanByRole(userId);
    	Tblrujsuburusan f = null;
		String s = "TIADA";
		for (int i = 0; i < v.size(); i++) {
			f = (Tblrujsuburusan) v.get(i);				
				
			if (i==0) {
				s = ""+f.getIdSuburusan();
			} else {
				s += ","+f.getIdSuburusan();
			}
		}	*/
		
		if("surat".equals(submit)){//senarai surat
	    	vm = "app/htp/frmSenaraiSuratHTP.jsp";

			idPermohonan = getParam("idpermohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
			paparanRekod(userId);
			mylog.info("doTemplate2::surat||idPermohonan:::"+idPermohonan);
		    
	    }else if ("pegawai".equals(submit)){
			vm = "app/htp/frmPilihPegawai.jsp";
			permohonan = new Hashtable();
			
			idPermohonan = getParam("idpermohonan");			
			String template = getParam("template");		
			String idPegawai = getParam("idpegawai");
			Long idPeg = 0L;
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
			String idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String)session.getAttribute("_ekptg_user_id"));
			
			if (!("".equals(idPegawai))) {
				idPeg = Long.parseLong(idPegawai);
				Hashtable<?, ?> hPegawai = null;
				hPegawai = (Hashtable<?, ?>)FrmMappingUserPegawaiData.getSenaraiPegawai(idPegawai);
				permohonan.put("namapegawai",(String)hPegawai.get("namapegawai"));
				permohonan.put("notelefon", (String)hPegawai.get("notelefon"));
				permohonan.put("email", (String)hPegawai.get("email"));
				permohonan.put("jawatan", (String)hPegawai.get("jawatan"));
				//mylog.info("doTemplate2::pegawai-0||idPeg:::"+hPegawai.get("namapegawai"));
				//mylog.info("doTemplate2::pegawai-0||idPeg:::"+hPegawai.get("notelefon"));
				//mylog.info("doTemplate2::pegawai-0||idPeg:::"+hPegawai.get("email"));
		    }
			//mylog.info("doTemplate2::pegawai-1||idPeg:::"+idPeg);
			//mylog.info("doTemplate2::pegawai||idPermohonan:::"+idPermohonan);
			//mylog.info("doTemplate2::pegawai||template:::"+template);
			permohonan.put("template", template);

			String pegawai = HTML.SelectPegawaiMengikutSeksyen(idSeksyen,"0","idpegawai", idPeg, "", "onChange=\"doChangePegawai()\" ");
		    context.put("selectNamaPegawai", pegawai);	    
		    context.put("bilDokumen", getParam("txtBilDokumen")==null?"":getParam("txtBilDokumen"));
		    if(template.equals("HTPPajakanKecilSuratMaklumanBebanan")){
				mylog.info("Simpan Status:"+idPermohonan);
 	
		    }
		    
	    }else if ("PilihPegawai".equals(submit)){
			Long idPeg = 0L;
			vm = "app/htp/frmPilihPegawai.jsp";
			permohonan = new Hashtable();
			String template = getParam("template");	
			String idPegawai = getParam("idpegawai");
		
			idPermohonan = getParam("idpermohonan");			
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
			if (!("".equals(idPegawai))) {
				idPeg = Long.parseLong(idPegawai);
				Hashtable<?, ?> hPegawai = null;
				hPegawai = (Hashtable<?, ?>)FrmMappingUserPegawaiData.getSenaraiPegawai(idPegawai);
				permohonan.put("namapegawai",(String)hPegawai.get("namapegawai"));
				permohonan.put("notelefon", (String)hPegawai.get("notelefon"));
				permohonan.put("email", (String)hPegawai.get("email"));
				permohonan.put("jawatan", (String)hPegawai.get("jawatan"));
		    }
			mylog.info("doTemplate2::PilihPegawai||idPermohonan:::"+idPermohonan);
			
			permohonan.put("template", template);
			String idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String)session.getAttribute("_ekptg_user_id"));

			String pegawai = HTML.SelectPegawaiMengikutSeksyen(idSeksyen,"0", "idpegawai", Long.parseLong(idPegawai), "", "onChange=\"doChangePegawai()\" ");
		    context.put("selectNamaPegawai", pegawai);
		    context.put("bilDokumen", getParam("txtBilDokumen")==null?"":getParam("txtBilDokumen"));


	    }else if ("PilihNegeri".equals(submit)){
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
			//displayNegeriUnit(tempIdNegeri,0L);
			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");
			//paparanRekod(userLevel);
    	}else if ("PilihUnit".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
 			String id_kementerian = getParam("socUnit");
 			//socUnit = HTML.SelectKementerian("socUnit", (id_kementerian == "") ? null : Long.parseLong(id_kementerian), null, "onChange=\"doChangeUnit()\" ");
			//displayNegeriUnitXNegeri(tempIdNegeri,Long.parseLong(id_kementerian));  	
  			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1"),"");
			//paparanRekod(userLevel,userId);
     	}else if ("PilihUnitLevel".equals(submit)) {
			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
 			String idPejabat = getParam("socUnit");
			Tblrujpejabatjkptg pej = null;
			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
 			//displayNegeriUnit(pej.getIdNegeri(),Long.parseLong(idPejabat));  		
 			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", idPejabat);
     	}else if ("Senaraifailsurat".equals(submit)) {
			vm = "app/htp/frmSenaraiFailUntukSurat.jsp";			
			String key_cari = getParam("NamaFail")==""?null:getParam("NamaFail");
			String keyNo_cari = getParam("NoFail")==""?null:getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			Vector<?> v = FrmUtilData.getSubUrusanByRole(userId);
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
			Vector list1 = FrmUtilData.getSenaraiFailMengikutUrusanPengguna(s,key_cari,keyNo_cari,idNegeri);
			this.context.put("SenaraiFail", list1);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
			socNegeri = HTML.SelectNegeri("socNegeri",idNegeri,"");
			this.context.put("SenaraiFail", list1);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", idNegeri);
    	}else{
			vm = "app/htp/frmSenaraiFailUntukSurat.jsp";
     		//senaraiFail(userLevel,userId);
			String key_cari = getParam("NamaFail")==""?null:getParam("NamaFail");
			String keyNo_cari = getParam("NoFail")==""?null:getParam("NoFail");
			String Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			Long idNegeri = Long.parseLong(Negeri);
			Vector<?> v = FrmUtilData.getSubUrusanByRole(userId);
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

			//Vector list1 = (Vector<?>)FrmUtilData.getSenaraiFailMengikutUrusanPengguna(s,key_cari,keyNo_cari,idNegeri);
			Vector list1 = FrmSenaraiFailPajakanKecilData.getList("",(String)session.getAttribute("_ekptg_user_id"));
			this.context.put("SenaraiFail", list1);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
			socNegeri = HTML.SelectNegeri("socNegeri",idNegeri,"");
		    this.context.put("Negeri", idNegeri);

		    mylog.info("else : list :: "+list1.size());


    	}
		this.context.put("selectNegeri", socNegeri);
	    context.put("permohonanInfo", permohonan);
		context.put("senaraiSurat",vecHash);
		
		return vm;
	}
	
	private void paparanRekod(String login) throws Exception{
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
			vecHash = FrmUtilData.getLaporanSuratMengikutUrusan(idUrusan,null,"T");
			
	}
}

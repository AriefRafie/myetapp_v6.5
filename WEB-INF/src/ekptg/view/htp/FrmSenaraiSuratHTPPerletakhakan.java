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
import ekptg.model.htp.FrmPerletakhakanPendaftaranData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.ppk.PPKUtilData;
import ekptg.model.utils.FrmMappingUserPegawaiData;

public class FrmSenaraiSuratHTPPerletakhakan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmSenaraiSuratHTPPerletakhakan.class);
	String socNegeri = "";
	String socUnit = "";
	Vector<?> vecHash = null;
	Vector<?> vecRekod = null;
	String socDaerah = "";
	private final String IDURUSAN = "5";
    String isCarian = "tidak";
    FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); //data tuk view

	public String doTemplate2() throws Exception {
	    HttpSession session = this.request.getSession();
		String vm = "app/htp/frmSenaraiSuratHTP.jsp";
   		String submit = getParam("command");
		String action = getParam("action"); //untuk setup paging je
		//String userRole = (String)session.getAttribute("_portal_role");
		String userId = (String)session.getAttribute("_portal_login");
		int userLevel = 0;
		Long tempIdNegeri = 0L;
    	Vector<?> list = null;
		Hashtable permohonan = null;
		String idPermohonan = "";
		User user = null;
		String key_cari = "";
		String keyNo_cari = "";
		String Negeri = "";
		
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
		
 		if("surat".equals(submit)){//senarai surat
	    	vm = "app/htp/frmSenaraiSuratHTP.jsp";
			idPermohonan = getParam("idpermohonan");
			session.setAttribute("permohonan", idPermohonan);
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
			paparanRekod(userId);
			//mylog.info("doTemplate2::surat||idPermohonan:::"+idPermohonan);
		    
	    }else if ("pegawai".equals(submit)){
			vm = "app/htp/frmPilihPegawai.jsp";
			permohonan = new Hashtable();			
			idPermohonan = getParam("idpermohonan");			
			String template = getParam("template");		
			String idPegawai = getParam("idpegawai");
			Long idPeg = 0L;
			permohonan = (Hashtable<?, ?>)FrmUtilData.getPermohonanInfo(idPermohonan);
			String idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String)session.getAttribute("_ekptg_user_id"));
			//mylog.info("pegawai||idSeksyen:::"+idSeksyen);
			
			if (!("".equals(idPegawai))) {
				idPeg = Long.parseLong(idPegawai);
				Hashtable<?, ?> hPegawai = null;
				hPegawai = (Hashtable<?, ?>)FrmMappingUserPegawaiData.getSenaraiPegawai(idPegawai);
				permohonan.put("namapegawai",(String)hPegawai.get("namapegawai"));
				permohonan.put("notelefon", (String)hPegawai.get("notelefon"));
				permohonan.put("email", (String)hPegawai.get("email"));
				permohonan.put("jawatan", (String)hPegawai.get("jawatan"));
		    }
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
			socDaerah = UtilHTML.SelectDaerah("socDaerah",null,"style=width:340");

	    }else if ("PilihUnit".equals(submit)) {
			tempIdNegeri = Long.parseLong(getParam("socNegeri"));
 			String id_kementerian = getParam("socUnit");
 			socDaerah = HTML.SelectAgensiByKementerian("socDaerah",id_kementerian,Long.parseLong("1"),"");
		
	    }else if ("PilihUnitLevel".equals(submit)) {
			vm = "app/ppk/frmSenaraiLaporanPUnit.jsp";			
 			String idPejabat = getParam("socUnit");
			Tblrujpejabatjkptg pej = null;
			pej = (Tblrujpejabatjkptg)PPKUtilData.getPejabatMengikutId(user.getId_pejabatjkptg());
 			socDaerah =  UtilHTML.SelectDaerahByUnitPPKXKod("socDaerah", null, "style=width:340", "", idPejabat);
     	
	    }else if ("Senaraifailsurat".equals(submit)) {
     		//mylog.info("Senaraifailsurat");
			vm = "app/htp/frmSenaraiFailUntukSurat.jsp";		
			key_cari = getParam("NamaFail")==""?null:getParam("NamaFail");
			keyNo_cari = getParam("NoFail")==""?null:getParam("NoFail");
			Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			
			list = FrmUtilData.getSenaraiFailMengikutUrusanDanPengguna(IDURUSAN, getParam("NamaFail"), getParam("NoFail"),Negeri,null);		
			isCarian = "ya";		
			this.context.put("SenaraiFail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
		    this.context.put("Negeri", Negeri);
			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(Negeri),String.valueOf(""));
			setupPage(session,action,list);
		    
    	}else{
			vm = "app/htp/frmSenaraiFailUntukSurat.jsp";
			key_cari = getParam("NamaFail")==""?null:getParam("NamaFail");
			keyNo_cari = getParam("NoFail")==""?null:getParam("NoFail");
			Negeri = getParam("socNegeri")==""?"20":getParam("socNegeri");
			//mylog.info(key_cari+":"+keyNo_cari+":"+Negeri);
			list = FrmUtilData.getSenaraiFailMengikutUrusanDanPengguna(IDURUSAN, getParam("NamaFail"), getParam("NoFail"),Negeri,(String)session.getAttribute("_ekptg_user_id"));
			isCarian = getParam("carian");
			if(isCarian.equals("ya")){
				list = FrmUtilData.getSenaraiFailMengikutUrusanDanPengguna(IDURUSAN, getParam("NamaFail"), getParam("NoFail"),Negeri,null);		
				isCarian = "ya";		
			}
			//list = logic.getSenaraiFail(keyNo_cari, key_cari, null, null, getParam("socNegeri"), null, null, (String)session.getAttribute("_ekptg_user_id"));
			this.context.put("SenaraiFail", list);
		    this.context.put("carian", getParam("NamaFail"));
		    this.context.put("carianNoFail", getParam("NoFail"));
			socNegeri = HTML.SelectNegeri("socNegeri",Long.parseLong(Negeri),String.valueOf(""));
		    this.context.put("Negeri", Negeri);
			setupPage(session,action,list);

    	}
		this.context.put("iscarian", isCarian);
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
				s = String.valueOf(f.getIdSuburusan());
			
			} else {
				s += String.valueOf(",")+ String.valueOf(f.getIdSuburusan());
			
			}
		}
		vecHash = FrmUtilData.getLaporanSuratMengikutUrusan(IDURUSAN,null,String.valueOf("T"));
			
	}

}

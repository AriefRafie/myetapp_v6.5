
package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPenswastaaan2CadanganProjekData;
import ekptg.model.htp.FrmPenswastaan2HeaderData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;

/**
 * 
 *
 */
public class FrmPenswastaan2CadanganProjek extends AjaxBasedModule {
	
	private IHtp iHTP = null; 
 	private IHTPFail iHTPFail = null;  
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPenswastaan2CadanganProjek.class);
	
	FrmPenswastaan2HeaderData logicHeader = new FrmPenswastaan2HeaderData();
	FrmPenswastaaan2CadanganProjekData logic = new FrmPenswastaaan2CadanganProjekData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String actionPenswastaan = getParam("actionPenswastaan"); // our main submit
		if (actionPenswastaan.isEmpty()){
			actionPenswastaan = "papar";
		}
		String submit = getParam("command"); // for doAjax only
		String mode = getParam("mode");
		if (mode.isEmpty()){
			mode = "view";
		}
		String hitButton = getParam("hitButton");
		myLog.info("action : "+getParam("action"));
		// GET ID PARAM
		String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
		String idFail = getParam("idFail");
        myLog.info(idFailSession+",idFail="+idFail);
		String idPermohonan = getParam("idPermohonan");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		
		//GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idMukim = getParam("socMukim");
		if (idMukim == null || idMukim.trim().length() == 0){
			idMukim = "99999";
		}
		String idLot = getParam("socLot");
		if (idLot == null || idLot.trim().length() == 0){
			idLot = "99999";
		}
		String idJenisHakmilik = getParam("socJenisHakmilik");
		if (idJenisHakmilik == null || idJenisHakmilik.trim().length() == 0){
			idJenisHakmilik = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		String idKategori = getParam("socKategori");
		if (idKategori == null || idKategori.trim().length() == 0){
			idKategori = "99999";
		}
		String idSubkategori = getParam("socSubkategori");
		if (idSubkategori == null || idSubkategori.trim().length() == 0){
			idSubkategori = "99999";
		}
		
		// VECTOR
		Vector beanHeader = null;
		Vector senaraiHakmilik = null;
		Vector beanMaklumatHakmilik = null;
		
		vm = "app/htp/frmPenswastaan2CadanganProjek.jsp";
		
		//HITBUTTON
		if (postDB){
			if ("saveHakmilik".equals(hitButton)){
				saveHakmilik(idPermohonan, session);
	        }
			if ("saveUpdateHakmilik".equals(hitButton)){
				saveUpdateHakmilik(idHakmilikUrusan, session);
	        }
			if ("hapusHakmilik".equals(hitButton)){
				logic.hapusHakmilik(idHakmilikUrusan);
	        }
	    }
		
		beanHeader = new Vector();
	    //logicHeader.setMaklumatPermohonan(idFailSession);
	    //beanHeader = logicHeader.getBeanMaklumatPermohonan();
	    beanHeader = getIHTPFail().getMaklumatPermohonans(idFailSession);
		this.context.put("BeanHeader", beanHeader);
			
		if (beanHeader.size() != 0){
			//Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			Hashtable hashHeader = (Hashtable) beanHeader.get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();	
			
			/*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
			String flagMohonFail = hashHeader.get("flagMohonFail").toString();
			if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
				throw new Exception(getIHTP().getErrorHTML("PERMOHONAN BELUM DISAHKAN"));
			}
			
			senaraiHakmilik = new Vector();
			logic.setListHakmilik(idPermohonan);
			senaraiHakmilik = logic.getSenaraiHakmilik();
			this.context.put("SenaraiHakmilik", senaraiHakmilik);
			
			if ("newHakmilik".equals(mode)){
				
				//MAKLUMAT PERMOHONAN
				beanMaklumatHakmilik = new Vector();
				Hashtable hashHakmilik = new Hashtable();
				hashHakmilik.put("noLot", getParam("txtNoLot"));
				hashHakmilik.put("noHakmilik", getParam("txtNoHakmilik"));
				hashHakmilik.put("luas", getParam("txtLuas"));
				hashHakmilik.put("noWarta", getParam("txtNoWarta"));
				hashHakmilik.put("tarikhWarta", getParam("tarikhWarta"));
				hashHakmilik.put("syarat", getParam("txtSyarat"));
				hashHakmilik.put("sekatan", getParam("txtSekatan"));
				beanMaklumatHakmilik.addElement(hashHakmilik);
				this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
				
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
				this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idNegeri, "socDaerah" ,Long.parseLong(idDaerah), "", " onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim",HTML.SelectMukimByDaerah(idDaerah, "socMukim" ,Long.parseLong(idMukim), "", ""));				
				this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong(idLot), "", " "));
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik" ,Long.parseLong(idJenisHakmilik), "", ""));
				this.context.put("selectLuas",HTML.SelectLuas("socLuas" ,Long.parseLong(idLuas), "", ""));
				this.context.put("selectKategori",HTML.SelectKategori("socKategori" ,Long.parseLong(idKategori), "", " onChange=\"doChangeKategori();\""));
				this.context.put("selectSubkategori",FrmPenswastaaan2CadanganProjekData.SelectSubKategori(idKategori, "socSubkategori" ,Long.parseLong(idSubkategori), "", ""));
				
				if ("1".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected1", "selected");
				} else {
					this.context.put("selected1", "");
				}
				if ("2".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected2", "selected");
				} else {
					this.context.put("selected2", "");
				}
				if ("3".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected3", "selected");
				} else {
					this.context.put("selected3", "");
				}
				if ("4".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected4", "selected");
				} else {
					this.context.put("selected4", "");
				}
				if ("5".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected5", "selected");
				} else {
					this.context.put("selected5", "");
				}
				if ("6".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected6", "selected");
				} else {
					this.context.put("selected6", "");
				}
				if ("7".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected7", "selected");
				} else {
					this.context.put("selected7", "");
				}
				if ("8".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected8", "selected");
				} else {
					this.context.put("selected8", "");
				}
				if ("9".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected9", "selected");
				} else {
					this.context.put("selected9", "");
				}
				if ("10".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected10", "selected");
				} else {
					this.context.put("selected10", "");
				}
				if ("11".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected11", "selected");
				} else {
					this.context.put("selected11", "");
				}
				this.context.put("readonly", "");
    			this.context.put("inputTextClass", "");
    			this.context.put("socDisabled", "");
    			this.context.put("socClass", "");
				
			} else if ("viewHakmilik".equals(mode)){
				beanMaklumatHakmilik = new Vector();
			    logic.setMaklumatHakmilik(idHakmilikUrusan);
			    beanMaklumatHakmilik = logic.getBeanMaklumatHakmilik();
				this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
				
				Hashtable hashHakmilik = (Hashtable) logic.getBeanMaklumatHakmilik().get(0);
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashHakmilik.get("idNegeri")), "disabled", " class=\"disabled\""));
				this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashHakmilik.get("idNegeri"), "socDaerah" ,Long.parseLong((String) hashHakmilik.get("idDaerah")), "disabled", " class=\"disabled\""));
				this.context.put("selectMukim",HTML.SelectMukimByDaerah((String) hashHakmilik.get("idDaerah"), "socMukim" ,Long.parseLong((String) hashHakmilik.get("idMukim")), "disabled", " class=\"disabled\""));				
				this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong((String) hashHakmilik.get("idLot")), "disabled", " class=\"disabled\""));
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik" ,Long.parseLong((String) hashHakmilik.get("idJenisHakmilik")), "disabled", " class=\"disabled\""));
				this.context.put("selectLuas",HTML.SelectLuas("socLuas" ,Long.parseLong((String) hashHakmilik.get("idLuas")), "disabled", " class=\"disabled\""));
				this.context.put("selectKategori",HTML.SelectKategori("socKategori" ,Long.parseLong((String) hashHakmilik.get("idKategori")), "disabled", " class=\"disabled\""));
				this.context.put("selectSubkategori",FrmPenswastaaan2CadanganProjekData.SelectSubKategori((String) hashHakmilik.get("idKategori"), "socSubkategori" ,Long.parseLong((String) hashHakmilik.get("idSubkategori")), "disabled", " class=\"disabled\""));
				this.context.put("luas",(String) hashHakmilik.get("luas"));
				
				if ("1".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected1", "selected");
				} else {
					this.context.put("selected1", "");
				}
				if ("2".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected2", "selected");
				} else {
					this.context.put("selected2", "");
				}
				if ("3".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected3", "selected");
				} else {
					this.context.put("selected3", "");
				}
				if ("4".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected4", "selected");
				} else {
					this.context.put("selected4", "");
				}
				if ("5".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected5", "selected");
				} else {
					this.context.put("selected5", "");
				}
				if ("6".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected6", "selected");
				} else {
					this.context.put("selected6", "");
				}
				if ("7".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected7", "selected");
				} else {
					this.context.put("selected7", "");
				}
				if ("8".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected8", "selected");
				} else {
					this.context.put("selected8", "");
				}
				if ("9".equals(hashHakmilik.get("idTindakanLanjut"))){
					this.context.put("selected9", "selected");
				} else {
					this.context.put("selected9", "");
				}

				this.context.put("readonly", "readonly");
    			this.context.put("inputTextClass", "disabled");
    			this.context.put("socDisabled", "disabled");
    			this.context.put("socClass", "class='disabled'");
				
			} else if ("updateHakmilik".equals(mode)){
				
				//MAKLUMAT PERMOHONAN
				beanMaklumatHakmilik = new Vector();
				Hashtable hashHakmilik = new Hashtable();
				hashHakmilik.put("noLot", getParam("txtNoLot"));
				hashHakmilik.put("noHakmilik", getParam("txtNoHakmilik"));
				hashHakmilik.put("noWarta", getParam("txtNoWarta"));
				hashHakmilik.put("tarikhWarta", getParam("tarikhWarta"));
				hashHakmilik.put("syarat", getParam("txtSyarat"));
				hashHakmilik.put("sekatan", getParam("txtSekatan"));
				hashHakmilik.put("luas", getParam("txtLuas"));
				beanMaklumatHakmilik.addElement(hashHakmilik);
				this.context.put("BeanMaklumatHakmilik", beanMaklumatHakmilik);
				
				this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
				this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idNegeri, "socDaerah" ,Long.parseLong(idDaerah), "", " onChange=\"doChangeDaerah();\""));
				this.context.put("selectMukim",HTML.SelectMukimByDaerah(idDaerah, "socMukim" ,Long.parseLong(idMukim), "", ""));				
				this.context.put("selectLot",HTML.SelectLot("socLot", Long.parseLong(idLot), "", " "));
				this.context.put("selectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik" ,Long.parseLong(idJenisHakmilik), "", ""));
				this.context.put("selectLuas",HTML.SelectLuas("socLuas" ,Long.parseLong(idLuas), "", ""));
				this.context.put("selectKategori",HTML.SelectKategori("socKategori" ,Long.parseLong(idKategori), "", " onChange=\"doChangeKategori();\""));
				this.context.put("selectSubkategori",FrmPenswastaaan2CadanganProjekData.SelectSubKategori(idKategori, "socSubkategori" ,Long.parseLong(idSubkategori), "", ""));

				
				if ("1".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected1", "selected");
				} else {
					this.context.put("selected1", "");
				}
				if ("2".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected2", "selected");
				} else {
					this.context.put("selected2", "");
				}
				if ("3".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected3", "selected");
				} else {
					this.context.put("selected3", "");
				}
				if ("4".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected4", "selected");
				} else {
					this.context.put("selected4", "");
				}
				if ("5".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected5", "selected");
				} else {
					this.context.put("selected5", "");
				}
				if ("6".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected6", "selected");
				} else {
					this.context.put("selected6", "");
				}
				if ("7".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected7", "selected");
				} else {
					this.context.put("selected7", "");
				}
				if ("8".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected8", "selected");
				} else {
					this.context.put("selected8", "");
				}
				if ("9".equals(getParam("socTindakanLanjut"))){
					this.context.put("selected9", "selected");
				} else {
					this.context.put("selected9", "");
				}

				this.context.put("readonly", "");
    			this.context.put("inputTextClass", "");
    			this.context.put("socDisabled", "");
    			this.context.put("socClass", "");
			}
		}
		
		//SET DEFAULT PARAM
        this.context.put("actionPenswastaan", actionPenswastaan);
		this.context.put("mode", mode);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idHakmilikUrusan", idHakmilikUrusan);
		
		return vm;
	}

	private void saveHakmilik(String idPermohonan, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("idNegeri", getParam("socNegeri"));
		hash.put("idDaerah", getParam("socDaerah"));
		hash.put("idMukim", getParam("socMukim"));
		hash.put("idLot", getParam("socLot"));
		hash.put("noLot", getParam("txtNoLot"));
	    if (!("".equals(getParam("socJenisHakmilik")))) 
	    	hash.put("idJenisHakmilik", getParam("socJenisHakmilik"));
	    else
		    hash.put("idJenisHakmilik", "0");
	    		
		hash.put("noHakmilik", getParam("txtNoHakmilik"));
		hash.put("idLuas", getParam("socLuas"));
		hash.put("luas", getParam("txtLuas"));
		hash.put("noWarta", getParam("txtNoWarta"));
		hash.put("tarikhWarta", getParam("tarikhWarta"));		
		hash.put("idKategori", getParam("socKategori"));
		hash.put("idSubkategori", getParam("socSubkategori"));
		hash.put("syarat", getParam("txtSyarat"));
		hash.put("sekatan", getParam("txtSekatan"));
		hash.put("tindakanLanjut", getParam("socTindakanLanjut"));
		
		logic.saveHakmilik(idPermohonan, hash, session);
	}
	
	private void saveUpdateHakmilik(String idHakmilikUrusan, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("idNegeri", getParam("socNegeri"));
		hash.put("idDaerah", getParam("socDaerah"));
		hash.put("idMukim", getParam("socMukim"));
		hash.put("idLot", getParam("socLot"));
		hash.put("noLot", getParam("txtNoLot"));
		hash.put("idJenisHakmilik", getParam("socJenisHakmilik"));
		hash.put("noHakmilik", getParam("txtNoHakmilik"));
		hash.put("idLuas", getParam("socLuas"));
		hash.put("luas", getParam("txtLuas"));
		hash.put("noWarta", getParam("txtNoWarta"));
		hash.put("tarikhWarta", getParam("tarikhWarta"));		
		hash.put("idKategori", getParam("socKategori"));
		hash.put("idSubkategori", getParam("socSubkategori"));
		hash.put("syarat", getParam("txtSyarat"));
		hash.put("sekatan", getParam("txtSekatan"));
		hash.put("tindakanLanjut", getParam("socTindakanLanjut"));
		
		logic.saveUpdateHakmilik(idHakmilikUrusan, hash, session);
	}
	
	private IHTPFail getIHTPFail(){
		if (iHTPFail==null){
			iHTPFail = new HTPFailBean();
		}
		return iHTPFail;
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
}

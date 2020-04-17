package ekptg.view.htp.negeri.penswastaan;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPenswastaan2HeaderData;
import ekptg.model.htp.FrmPenswastaan2SyarikatData;
import ekptg.model.htp.penswastaan.IPenswastaan;
import ekptg.model.htp.penswastaan.PenswastaanBean;
/**
 * 
 *
 */
public class FrmPenswastaanSyarikat extends AjaxBasedModule {	

	private final String jenisAkses = "Readonly";
	private final String PATH = "app/htp/negeri/penswastaan/syarikat/";
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.negeri.penswastaan.FrmPenswastaanSyarikat.class);
	private IPenswastaan iP= null;	
	FrmPenswastaan2HeaderData logicHeader = new FrmPenswastaan2HeaderData();
	FrmPenswastaan2SyarikatData logic = new FrmPenswastaan2SyarikatData();

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

		// GET ID PARAM
		String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idPemaju = getParam("idPemaju");
		String idPengarah = getParam("idPengarah");
		//GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idDaerah = getParam("socDaerah");
		if (idDaerah == null || idDaerah.trim().length() == 0){
			idDaerah = "99999";
		}
		String idWarganegara = getParam("socWarganegara");
		if (idWarganegara == null || idWarganegara.trim().length() == 0){
			idWarganegara = "99999";
		}

		// VECTOR
		Vector beanHeader = null;
		Vector beanMaklumatSyarikat= null;
		Vector senaraiPengarah = null;
		Vector beanMaklumatPengarah = null;
		
		vm = PATH+"index.jsp";
		 
		//HITBUTTON
		if (postDB){
			if ("simpanUpdateSyarikat".equals(hitButton)){
				simpanUpdateSyarikat(idPemaju, session);
	        }
			if ("savePengarah".equals(hitButton)){
				logic.savePengarah(idPemaju, getParam("txtNamaPengarah"), getParam("txtNoPengenalanPengarah"), session);
	        }
			if ("saveUpdatePengarah".equals(hitButton)){
				logic.saveUpdatePengarah(idPengarah, getParam("txtNamaPengarah"), getParam("txtNoPengenalanPengarah"), session);
	        }
			if ("hapusPengarah".equals(hitButton)){
				logic.hapusPengarah(idPengarah);
	        }
			if ("hapusyarikat".equals(hitButton)){
				getISwasta().hapusPemaju(idPemaju);
	        }
	    }
		
		//HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFailSession);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			if (idPemaju.isEmpty()){
				idPemaju = logic.getIdPemajuByIdPermohonan(idPermohonan);
			}			
			
			if ("papar".equals(actionPenswastaan)){	
				
				//MAKLUMAT SYARIKAT
				beanMaklumatSyarikat = new Vector();
		        logic.setMaklumatSyarikat(idPemaju);
		        beanMaklumatSyarikat = logic.getBeanMaklumatSyarikat();
				this.context.put("BeanMaklumatSyarikat", beanMaklumatSyarikat);
				
				if ("update".equals(mode)){
					
					//MAKLUMAT PERMOHONAN
					beanMaklumatSyarikat = new Vector();
					Hashtable hashSyarikat = new Hashtable();
					hashSyarikat.put("nama", getParam("txtNamaSyarikat"));
					hashSyarikat.put("noRujukan", getParam("txtNoPendaftaran"));
					hashSyarikat.put("noTel", getParam("txtNoTel"));
					hashSyarikat.put("noFax", getParam("txtNoFax"));
					hashSyarikat.put("alamat1", getParam("txtAlamat1"));
					hashSyarikat.put("alamat2", getParam("txtAlamat2"));
					hashSyarikat.put("alamat3", getParam("txtAlamat3"));
					hashSyarikat.put("poskod", getParam("txtPoskod"));
					beanMaklumatSyarikat.addElement(hashSyarikat);
					this.context.put("BeanMaklumatSyarikat", beanMaklumatSyarikat);
					
					this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
					this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(idNegeri, "socDaerah" ,Long.parseLong(idDaerah), "", ""));
					
					this.context.put("readonly", "");
	    			this.context.put("inputTextClass", "");
	        		
				} else {
					myLog.info("beanMaklumatSyarikat:"+beanMaklumatSyarikat);
					if(beanMaklumatSyarikat.isEmpty()==false){
						myLog.info("beanMaklumatSyarikat:FALSE="+beanMaklumatSyarikat);
						Hashtable hashSyarikat = (Hashtable) logic.getBeanMaklumatSyarikat().get(0);
						this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong((String) hashSyarikat.get("idNegeri")), "disabled", " class=\"disabled\""));
						this.context.put("selectDaerah",HTML.SelectDaerahByNegeri((String) hashSyarikat.get("idNegeri"), "socDaerah" ,Long.parseLong((String) hashSyarikat.get("idDaerah")), "disabled", " class=\"disabled\""));
						this.context.put("readonly", "readonly");
		    			this.context.put("inputTextClass", "disabled");
					}else{
						myLog.info("beanMaklumatSyarikat:TRUE="+beanMaklumatSyarikat);
						this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(String.valueOf(hashHeader.get("idNegeri"))), "", ""));
						this.context.put("selectDaerah",HTML.SelectDaerahByNegeri(String.valueOf(hashHeader.get("idNegeri")), "socDaerah" ,Long.parseLong(String.valueOf("0")), "", ""));
	        			this.context.put("readonly", "");
		    			this.context.put("inputTextClass", "");						
					}
				}
				
				
				//MAKLUMAT PENGARAH
				senaraiPengarah = new Vector();
        		logic.setListPengarah(idPemaju);
        		senaraiPengarah = logic.getSenaraiPengarah();
        		this.context.put("SenaraiPengarah", senaraiPengarah);
        		
        		if ("newPengarah".equals(mode)){
        			
        			//MAKLUMAT PERMOHONAN
        			beanMaklumatPengarah = new Vector();
					Hashtable hashPengarah = new Hashtable();
					hashPengarah.put("namaPengarah", getParam("txtNamaPengarah"));
					hashPengarah.put("noPengenalan", getParam("txtNoPengenalanPengarah"));
					beanMaklumatPengarah.addElement(hashPengarah);
					this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);
					
					this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", ""));
        			
        			this.context.put("readonly", "");
	    			this.context.put("inputTextClass", "");
        			
        		} else if ("viewPengarah".equals(mode)){
        			
        			beanMaklumatPengarah = new Vector();
            		logic.setMaklumatPengarah(idPengarah);
            		beanMaklumatPengarah = logic.getBeanMaklumatPengarah();
            		this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);
            		
            		this.context.put("readonly", "readonly");
	    			this.context.put("inputTextClass", "disabled");
            		
        		} else if ("updatePengarah".equals(mode)){
        			
        			this.context.put("readonly", "");
	    			this.context.put("inputTextClass", "");
        		}
			}
		}
		
		
		
		//SET DEFAULT PARAM
        this.context.put("actionPenswastaan", actionPenswastaan);
		this.context.put("mode", mode);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        this.context.put("idPemaju", idPemaju);
        this.context.put("idPengarah", idPengarah);
		myLog.info("idPermohonan:"+idPermohonan);
	    this.context.put("jenisAkses", jenisAkses);

		return vm;
		
	}

	private void simpanUpdateSyarikat(String idPemaju, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("nama", getParam("txtNamaSyarikat"));
		hash.put("noRujukan", getParam("txtNoPendaftaran"));
		hash.put("noTel", getParam("txtNoTel"));
		hash.put("noFax", getParam("txtNoFax"));
		hash.put("alamat1", getParam("txtAlamat1"));
		hash.put("alamat2", getParam("txtAlamat2"));
		hash.put("alamat3", getParam("txtAlamat3"));
		hash.put("poskod", getParam("txtPoskod"));
		hash.put("idNegeri", getParam("socNegeri"));
		hash.put("idDaerah", getParam("socDaerah"));
		hash.put("idPermohonan", getParam("idPermohonan"));
		
		logic.simpanUpdateSyarikat(idPemaju, hash, session);
	}
	
	private IPenswastaan getISwasta(){
		if (iP==null){
			iP = new PenswastaanBean();
		}
		return iP;
	} 
	
}

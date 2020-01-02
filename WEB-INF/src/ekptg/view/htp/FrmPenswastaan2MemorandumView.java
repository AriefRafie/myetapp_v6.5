package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPenswastaan2HeaderData;
import ekptg.model.htp.FrmPenswastaan2MemorandumData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;



public class FrmPenswastaan2MemorandumView extends AjaxBasedModule {
	
	private final String PATH = "app/htp/penswastaan/mjm/";
	private IHtp iHTP = null; 
 	private IHTPFail iHTPFail = null;  
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPenswastaan2SyarikatView.class);
	
	FrmPenswastaan2HeaderData logicHeader = new FrmPenswastaan2HeaderData();
	FrmPenswastaan2MemorandumData logic = new FrmPenswastaan2MemorandumData();

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
		String idJemaahMenteri = getParam("idJemaahMenteri");			
		
		
		// VECTOR
		Vector beanHeader = null;
		Vector beanMemorandum = null;

		
		 vm = PATH+"frmPenswastaan2Memorandum.jsp";
		 
		//HITBUTTON
			if (postDB){
				if ("simpanUpdateMemorandum".equals(hitButton)){
					simpanUpdateMemorandum(idJemaahMenteri, session);
		        }
		    }
			
			//HEADER
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
				if (idJemaahMenteri.isEmpty()){
					idJemaahMenteri = logic.getIdJemaahMenteriByIdPermohonan(idPermohonan);
				}			
				
				
				/*CHecking jika telah disahkan pengarah atau belum (bagi fail baru didaftar selepas penambahbaikan). syaz. 01122014 */
				String flagMohonFail = hashHeader.get("flagMohonFail").toString();
				if((flagMohonFail!="") && !flagMohonFail.equalsIgnoreCase("S") ){
					throw new Exception(getIHTP().getErrorHTML("PERMOHONAN BELUM DISAHKAN"));
				}
				
				
				if ("papar".equals(actionPenswastaan)){	
					
					//MAKLUMAT SYARIKAT ---------- tukar
					beanMemorandum = new Vector();
			        logic.setbeanMemorandum(idJemaahMenteri);
			        beanMemorandum = logic.getBeanMemorandum();
					this.context.put("BeanMemorandum", beanMemorandum);
								
					
					//MAKLUMAT PENGARAH -------------------- tukar
				
	        		
	        		if ("update".equals(mode)){
	        			
	        			beanMemorandum = new Vector();
	        			logic.setbeanMemorandum(idJemaahMenteri);
	        			beanMemorandum = logic.getBeanMemorandum();
	        			this.context.put("BeanMemorandum", beanMemorandum);
	            			        			
//	        			
	        			//MAKLUMAT PERMOHONAN
//	        			beanMaklumatPengarah = new Vector();
//						Hashtable hashPengarah = new Hashtable();
//						hashPengarah.put("namaPengarah", getParam("txtNamaSyarikat"));
//						hashPengarah.put("noPengenalan", getParam("txtNoPendaftaran"));
//						beanMaklumatPengarah.addElement(hashPengarah);
//						this.context.put("BeanMaklumatPengarah", beanMaklumatPengarah);
//						
//						this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", ""));
//	        			
	        			this.context.put("readonly", "");
	        			this.context.put("inputTextClass", "");
	        			this.context.put("disabled", "");

	        			
	        		} 
	        			else if ("view".equals(mode)){
	        			
	        			beanMemorandum = new Vector();
		        		logic.setbeanMemorandum(idJemaahMenteri);
		        		beanMemorandum = logic.getBeanMemorandum();
		        		this.context.put("BeanMemorandum", beanMemorandum);
	            		
//	            		Hashtable hashPengarah = (Hashtable) logic.getBeanMaklumatPengarah().get(0);
//	            		this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong((String) hashPengarah.get("idWarganegara")), "disabled", " class=\"disabled\""));
	            		
	            		this.context.put("readonly", "readonly");
		    			this.context.put("inputTextClass", "disabled");
		    			this.context.put("disabled", "disabled");

	            		
	        		} 
//	        				else if ("updatePengarah".equals(mode)){
//	        			
//	        			this.context.put("selectWarganegara",HTML.SelectWarganegara("socWarganegara", Long.parseLong(idWarganegara), "", ""));
//	        			
//	        			this.context.put("readonly", "");
//		    			this.context.put("inputTextClass", "");
//	        		}
				}
			}
			
			
			
			//SET DEFAULT PARAM
	        this.context.put("actionPenswastaan", actionPenswastaan);
			this.context.put("mode", mode);
			
	        //SET ID PARAM
			this.context.put("idFail", idFail);
	        this.context.put("idPermohonan", idPermohonan);
	        this.context.put("idJemaahMenteri", idJemaahMenteri);
//	        this.context.put("idPengarah", idPengarah);
	        
			return vm;
		}

//		private void simpanUpdateSyarikat(String idPemaju, HttpSession session) throws Exception {
//			Hashtable hash = new Hashtable();
//			hash.put("nama", getParam("txtNamaSyarikat"));
//			hash.put("noRujukan", getParam("txtNoPendaftaran"));
//			hash.put("noTel", getParam("txtNoTel"));
//			hash.put("noFax", getParam("txtNoFax"));
//			hash.put("alamat1", getParam("txtAlamat1"));
//			hash.put("alamat2", getParam("txtAlamat2"));
//			hash.put("alamat3", getParam("txtAlamat3"));
//			hash.put("poskod", getParam("txtPoskod"));
//			hash.put("idNegeri", getParam("socNegeri"));
//			hash.put("idDaerah", getParam("socDaerah"));
//			
//			logic.simpanUpdateSyarikat(idPemaju, hash, session);
//		}
	
	private void simpanUpdateMemorandum(String idPemaju, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("txdTerimaDariKSU", getParam("txdTerimaDariKSU"));
		hash.put("txdUlasanKeKSU", getParam("txdUlasanKeKSU"));
		hash.put("txtNoMemorandum", getParam("txtNoMemorandum"));
		hash.put("txtNoFax", getParam("txtNoFax"));
		hash.put("txdMystJM", getParam("txdMystJM"));
		hash.put("txdKeputusan", getParam("txdKeputusan"));
		hash.put("txtUlasan", getParam("txtUlasan"));

		logic.simpanUpdateMemorandum(idPemaju, hash, session);
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

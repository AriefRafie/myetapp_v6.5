/**
 * 
 */
package ekptg.view.htp.negeri.pajakan;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanSenaraiFailData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;

/**
 * 
 *
 */
public class FrmPajakanSenaraiFail extends AjaxBasedModule {

	private final String jenisAkses="Readonly";
	private final String JENISTANAH = "2,4";	//TR-TANAH RIZAB,TM-TANAH MILIK
	private final String PATH="app/htp/negeri/pajakan/fail/";
	private String DISABILITY = " disabled class=disabled ";
	private IHTPFail iHTPFail = null;  
	private InternalUser iu = null;
	private static final long serialVersionUID = 1L;	
	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmPajakanSenaraiFailData logic = new FrmPajakanSenaraiFailData();
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.negeri.pajakan.FrmPajakanSenaraiFail.class);
	private PfdFail fail = null;
	private HakmilikUrusan urusan = null;
	private IPembelian iPembelian = null;
	private HtpBean iHtp = null;
	private HtpPermohonan htpPermohonan = null;
	//19/08/2010
	String flagAdvSearch = "";
	private String idNegeri = "";
	private String userId = "";
	private String socDaerah = "";
	private String socMukim = "";
	private String idDaerah = "";
	private String idMukim = "";

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();		
	    userId = session.getAttribute("_ekptg_user_id").toString();
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionPajakan = getParam("actionPajakan");
        String submit = getParam("command");   
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
        
        //VECTOR
        Vector beanHeader = null;
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector senaraiHakmilik = null;
        
        //GET DROPDOWN PARAM
        //String idNegeri = getParam("socNegeri");
		iu = InternalUserUtil.getSeksyenId(userId);
		idNegeri = iu.getIdNegeri();
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
	 	idDaerah = getParam("socDaerah");
	 	idMukim = getParam("socMukim");
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		String idStatusTanah = getParam("socStatusTanah");
		if (idStatusTanah == null || idStatusTanah.trim().length() == 0){
			idStatusTanah = "99999";
		}
		String idJenisFail = getParam("socJenisFail");
		if (idJenisFail == null || idJenisFail.trim().length() == 0){
			idJenisFail = "99999";
		}
		myLog.info("actionPajakan:"+actionPajakan);
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");

		//HITBUTTON
		if (postDB){
    		if ("simpan".equals(hitButton)){
         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
        	if ("simpanbyswasta".equals(hitButton)){
         		idFail = logic.simpan(idPermohonan,idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
         		session.setAttribute("idFail", idFail);
        	}
    	}
		
		if ("papar".equals(actionPajakan)){
        	vm = PATH+"frmDaftar.jsp";
			//GO TO PAPAR PAJAKAN  
			logicHeader.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = new Vector();

    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			/*hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
	*/		
			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri),DISABILITY, ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled class=disabled", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), " disabled class=disabled", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), " disabled class=disabled", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), " disabled class=disabled", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), " disabled class=disabled", ""));

    		
        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        	MaklumatPermohonanView("view");
        	this.context.put("mode", mode);
	        	
		} else if ("daftarBaru".equals(actionPajakan)){  //ACTION PAJAKAN     	
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	//vm = PATH+"frmPajakanPenerimaanPermohonanDaftar.jsp";
        	vm = PATH+"frmDaftar.jsp";
       	
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
			//this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectStatusTanah",UtilHTML.SelectJenisTanah("socStatusTanah",Long.parseLong(idStatusTanah),"",JENISTANAH));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        	
        }else if ("daftarBaruSwasta".equals(actionPajakan)){       	
		//if ("daftarBaru".equals(submit)){       	
        	//GO TO DAFTAR BARU PAJAKAN   
        	vm = PATH+"frmPajakanPenerimaanPermohonanDaftarSwasta.jsp";
        	
        	this.context.put("mode", "new");
        	this.context.put("readonly", "");
        	this.context.put("inputTextClass", "");
        	
        	//MAKLUMAT PERMOHONAN
        	// idHakmilik (parameter dari skrin)
			urusan = getIPembelian().findByHakmilikUrusanId(getParam("idHakmilik"));
			//idPermohonan = urusan.getPermohonan().getNoPermohonan(); 
			htpPermohonan = getIPembelian().findPermohonan(urusan.getPermohonan().getNoPermohonan(), "");
			fail = htpPermohonan.getPermohonan().getPfdFail();
			
        	beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail", "");
			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			
			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(urusan.getIdNegeri()), "", ""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",fail.getIdKementerian(), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,htpPermohonan.getIdAgensi(), "", ""));
			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(String.valueOf("18")), " disabled", ""));
			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
        
        } else if("carian".equals(actionPajakan)){
        	vm = PATH+"index.jsp";
		    String noFail = getParam("txtNoFail");
		    String tajukFail = getParam("txtTajukFail");
		    String tarikhTerima = getParam("txdTarikhTerima");
		    String pemohon = getParam("txtNamaPemohon");
			this.context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", DISABILITY));
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri, "socDaerah",Utils.parseLong(idDaerah),null, "onChange=\"cmdChangeDaerahCarian()\"");
			socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim) , "");
			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", " style=\"width:400\""));
			if(flagAdvSearch.equals("Y")){
				//list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi);
				list = getIHTPFail().getSenaraiFail(null, noFail, tajukFail, idKementerian, idAgensi
						, idNegeri, idDaerah, idMukim
						, "3", tarikhTerima, null, null, pemohon, false);
	    	
			}else{
				//list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi);
				list = getIHTPFail().getSenaraiFail(null, noFail, tajukFail, idKementerian, idAgensi
						, idNegeri, idDaerah, idMukim
						, "3", tarikhTerima, null, null, pemohon, false);
		    	
		    }		
			flagAdvSearch = "Y";
			this.context.put("SenaraiFail", list);			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));			
		    this.context.put("socDaerah", socDaerah);
		    this.context.put("socMukim", socMukim);
			setupPage(session,action,list);
			
        } else {       	
        	//GO TO LIST FAIL PAJAKAN        	
        	vm = PATH+"index.jsp";
			list = new Vector();
		    String noFail = getParam("txtNoFail");
		    String tajukFail = getParam("txtTajukFail");
		    String tarikhTerima = getParam("txdTarikhTerima");
		    String pemohon = getParam("txtNamaPemohon");
			this.context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", DISABILITY));
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri, "socDaerah",Utils.parseLong(idDaerah),null, "onChange=\"cmdChangeDaerahCarian()\"");
			socMukim = HTML.SelectMukimByDaerah(idDaerah, "socMukim", Utils.parseLong(idMukim) , "");
			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", " style=\"width:400\""));
			if(flagAdvSearch.equals("Y")){
			   	//logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"));
				//list = logic.getSenaraiFail();
				list = getIHTPFail().getSenaraiFail(null, noFail, tajukFail, idKementerian, idAgensi
						, idNegeri, idDaerah, idMukim
						, "3", tarikhTerima, null, null, pemohon, false);
	    	
			}else{
				//logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"));
				//list = logic.getSenaraiFail();
				list = getIHTPFail().getSenaraiFail(null, noFail, tajukFail, idKementerian, idAgensi
						, idNegeri, idDaerah, idMukim
						, "3", tarikhTerima, null, null, pemohon, false);
			}
			if (mode.indexOf("change") != -1) {
				//logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"));
				//list = logic.getSenaraiFail();
				list = getIHTPFail().getSenaraiFail(null, noFail, tajukFail, idKementerian, idAgensi
						, idNegeri, idDaerah, idMukim
						, "3", tarikhTerima, null, null, pemohon, false);
		    	flagAdvSearch = "Y";
		    	
		    }

			this.context.put("SenaraiFail", list);		
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
		    this.context.put("socDaerah", socDaerah);
		    this.context.put("socMukim", socMukim);
			
			setupPage(session,action,list);
			
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionPajakan", actionPajakan);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("subUrusan", subUrusan);
	    // 19/08/2010
    	this.context.put("flagAdvSearch",flagAdvSearch);	
	    this.context.put("jenisAkses", jenisAkses);
		return vm;
		
	}

	public void setupPage(HttpSession session,String action,Vector list) {
		
		try {
		
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraiFail",paging.getPage(page));
			    this.context.put("page", new Integer(page));
			    this.context.put("itemsPerPage", new Integer(itemsPerPage));
			    this.context.put("totalPages", new Integer(paging.getTotalPages()));
			    this.context.put("startNumber", new Integer(paging.getTopNumber()));
			    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
			    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
	  public void MaklumatPermohonanView(String mode) throws Exception{

	    	try {
	    		
	    		if (mode.equalsIgnoreCase("view")){    			

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	        		this.context.put("inputTextClass", "disabled");
	        		
	        	}
	    		//mode = update
	    		else if(mode.equalsIgnoreCase("update")){	    			

	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	    		}

	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
	    }

		
		private IPembelian getIPembelian(){
			if (iPembelian==null){
				iPembelian=new PembelianBean();
			}
			return iPembelian;
		} 
		
		private IHtp getIHTP(){
			if(iHtp == null)
				iHtp = new HtpBean();
			return iHtp;
		}	

		private IHTPFail getIHTPFail(){
			if (iHTPFail==null){
				iHTPFail = new HTPFailBean();
			}
			return iHTPFail;
		}
		  

	  
}

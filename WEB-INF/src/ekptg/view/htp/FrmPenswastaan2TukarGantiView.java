/**
 * 
 */
package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPenswastaan2HeaderData;
import ekptg.model.htp.FrmPenswastaan2TukarGantiData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.utiliti.fail.HTPFailBean;
import ekptg.model.htp.utiliti.fail.IHTPFail;

/**
 * 
 *
 */
public class FrmPenswastaan2TukarGantiView extends AjaxBasedModule {
	
	private IHtp iHTP = null; 
 	private IHTPFail iHTPFail = null;  
	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(FrmPenswastaan2TukarGantiView.class);
	
	FrmPenswastaan2HeaderData logicHeader = new FrmPenswastaan2HeaderData();
	FrmPenswastaan2TukarGantiData logic = new FrmPenswastaan2TukarGantiData();

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
		String idTukarGanti = getParam("idTukarGanti");
		
		// VECTOR
		Vector beanHeader = null;
		Vector senaraiHakmilik = null;
		Vector senaraiHakmilikTukarGanti = null;
		
		//HITBUTTON
		if (postDB){
			if ("hapusHakmilikTukarGanti".equals(hitButton)){
				logic.hapusHakmilikTukarGanti(idTukarGanti);
	        }
	    }
		
		vm = "app/htp/frmPenswastaan2TukarGanti.jsp";
		
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
			
			senaraiHakmilikTukarGanti = new Vector();
			logic.setListHakmilikTukarGanti(idPermohonan);
			senaraiHakmilikTukarGanti = logic.getSenaraiHakmilikTukarGanti();
			this.context.put("SenaraiHakmilikTukarGanti", senaraiHakmilikTukarGanti);
		}
		
		//SET DEFAULT PARAM
        this.context.put("actionPenswastaan", actionPenswastaan);
		this.context.put("mode", mode);
		
        //SET ID PARAM
		this.context.put("idFail", idFail);
        this.context.put("idPermohonan", idPermohonan);
        
		return vm;
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

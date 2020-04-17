/**
 * 
 */
package ekptg.view.php2.kpi;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.kpi.FrmPLPLeadingKPIData;

/**
 * 
 *
 */
public class FrmPLPLeadingKPIView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPLPLeadingKPIData logic = new FrmPLPLeadingKPIData();

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  (String) session.getAttribute("doPost");
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String submit = getParam("command");  
	    String vm = ""; 
	    String hitButton = getParam("hitButton");
		String flagOpenKPI = getParam("flagOpenKPI");
		String selectedTab = (String)getParam("selectedTab");
		if (selectedTab == null || "".equals(selectedTab) ) {
			selectedTab = "0";
		}
		String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        
        //GET DROPDOWN PARAM
        String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		
		//GET ID PARAM
		String idKPISasaran = getParam("idKPISasaran");
		
		// VECTOR
		Vector beanMaklumatKPISasaran = null;
		Vector beanMaklumatKPIPelepasan = null;
		
		vm = "app/php2/kpi/frmPLPLeadingKPIMain.jsp";
		
		if (!"99999".equals(idSuburusan) && !"".equals(getParam("txdTarikhMula")) && !"".equals(getParam("txdTarikhAkhir"))){
			flagOpenKPI = "Y";
		} else {
			flagOpenKPI = "T";
		}			
		
		//SEND TO MODEL
        if (postDB) {
        	if ("simpanKemaskini".equals(hitButton)){
				simpanKemaskini(idKPISasaran, idSuburusan, session);
    		}
        }
        
        //GET ROW KPISASARAN
		idKPISasaran = logic.getIdKPISasaran(idSuburusan);
		
        if ("0".equals(selectedTab)){
			if ("Y".equals(flagOpenKPI)){
				beanMaklumatKPIPelepasan = new Vector();
				logic.setMaklumatKPIPelepasan(idKPISasaran, getParam("txdTarikhMula"), getParam("txdTarikhAkhir"));
				beanMaklumatKPIPelepasan = logic.getBeanMaklumatKPIPelepasan();
				this.context.put("BeanMaklumatKPIPelepasan", beanMaklumatKPIPelepasan);
			}			
		} 
        
        // TETAPAN KPI SASARAN
		if ("1".equals(selectedTab)){
			if ("view".equals(mode)){
				
				this.context.put("readonly", "readonly");
				this.context.put("inputTextClass", "disabled");
				
				beanMaklumatKPISasaran = new Vector();
				logic.setMaklumatKPISasaran(idKPISasaran);
				beanMaklumatKPISasaran = logic.getBeanMaklumatKPISasaran();
				this.context.put("BeanMaklumatKPISasaran", beanMaklumatKPISasaran);
				
				if (beanMaklumatKPISasaran.size() != 0){
					Hashtable hashSasaran = (Hashtable) logic.getBeanMaklumatKPISasaran().get(0);
					
					this.context.put("P1", Integer.parseInt((String) hashSasaran.get("F5")) + 1);
					this.context.put("P2", Integer.parseInt((String) hashSasaran.get("F6")) + 1);				
					this.context.put("P3", Integer.parseInt((String) hashSasaran.get("F7")) + 1);
					this.context.put("P4", Integer.parseInt((String) hashSasaran.get("F8")) + 1);				
					this.context.put("P5", Integer.parseInt((String) hashSasaran.get("F9")) + 1);
					this.context.put("P6", Integer.parseInt((String) hashSasaran.get("F10")) + 1);				
				}
				
			} else if ("update".equals(mode)){
				
				this.context.put("readonly", "");
				this.context.put("inputTextClass", "");
				
				beanMaklumatKPISasaran = new Vector();
				Hashtable hashMaklumatKPISasaran = new Hashtable();
				hashMaklumatKPISasaran.put("F1", getParam("F1"));
				hashMaklumatKPISasaran.put("F2", getParam("F2"));
				hashMaklumatKPISasaran.put("F3", getParam("F3"));
				hashMaklumatKPISasaran.put("F4", getParam("F4"));				
				hashMaklumatKPISasaran.put("F5", getParam("F5"));
				hashMaklumatKPISasaran.put("F6", getParam("F6"));
				hashMaklumatKPISasaran.put("F7", getParam("F7"));
				hashMaklumatKPISasaran.put("F8", getParam("F8"));				
				hashMaklumatKPISasaran.put("F9", getParam("F9"));
				hashMaklumatKPISasaran.put("F10", getParam("F10"));
    			
    			beanMaklumatKPISasaran.addElement(hashMaklumatKPISasaran);
				this.context.put("BeanMaklumatKPISasaran", beanMaklumatKPISasaran);
				
				this.context.put("P1", Integer.parseInt((String) getParam("F5")) + 1);
				this.context.put("P2", Integer.parseInt((String) getParam("F6")) + 1);				
				this.context.put("P3", Integer.parseInt((String) getParam("F7")) + 1);
				this.context.put("P4", Integer.parseInt((String) getParam("F8")) + 1);				
				this.context.put("P5", Integer.parseInt((String) getParam("F9")) + 1);
				this.context.put("P6", Integer.parseInt((String) getParam("F10")) + 1);				
			}
		}
		
		//PILIHAN KPI
		this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("6", "socSuburusan", Long.parseLong(idSuburusan), "", " onChange=\"doChangeSuburusan();\""));
		this.context.put("txdTarikhMula", getParam("txdTarikhMula"));
		this.context.put("txdTarikhAkhir", getParam("txdTarikhAkhir"));
		
		//SET DEFAULT PARAM
		this.context.put("mode", mode);
		this.context.put("selectedTab", selectedTab);
		this.context.put("flagOpenKPI", flagOpenKPI);
		
		//SET ID PARAM
		this.context.put("idSuburusan", idSuburusan);
		this.context.put("idKPISasaran", idKPISasaran);

		return vm;
	}

	private void simpanKemaskini(String idKPISasaran, String idSuburusan, HttpSession session) throws Exception {
		Hashtable hash = new Hashtable();
		hash.put("F1",getParam("F1"));
		hash.put("F2",getParam("F2"));
		hash.put("F3",getParam("F3"));
		hash.put("F4",getParam("F4"));
		hash.put("F5",getParam("F5"));
		hash.put("F6",getParam("F6"));
		hash.put("F7",getParam("F7"));
		hash.put("F8",getParam("F8"));
		hash.put("F9",getParam("F9"));
		hash.put("F10",getParam("F10"));
		hash.put("F11",getParam("F11"));
		hash.put("F12",getParam("F12"));
		hash.put("F13",getParam("F13"));
		hash.put("F14",getParam("F14"));
		hash.put("F15",getParam("F15"));
		hash.put("F16",getParam("F16"));
		hash.put("F17",getParam("F17"));
		hash.put("F18",getParam("F18"));
		hash.put("F19",getParam("F19"));
		hash.put("F20",getParam("F20"));
		hash.put("F21",getParam("F21"));
		hash.put("F22",getParam("F22"));
		hash.put("F23",getParam("F23"));
		hash.put("F24",getParam("F24"));
		hash.put("F25",getParam("F25"));
		hash.put("F26",getParam("F26"));
		hash.put("F27",getParam("F27"));
		hash.put("F28",getParam("F28"));
		hash.put("F29",getParam("F29"));
		hash.put("F30",getParam("F30"));
		
		if ("".equals(idKPISasaran)){
			logic.saveKPISasaran(idSuburusan, hash, session);
		} else {
			logic.updateKPISasaran(idKPISasaran, idSuburusan, hash, session);
		}
	}
}

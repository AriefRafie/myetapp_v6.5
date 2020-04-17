/**
 * 
 */
package ekptg.view.htp.online;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.online.FrmOnlineMaklumatPajakanData;

/**
 * @author Firzan.Fir
 *
 */
public class FrmPajakanOnlineMaklumatPajakanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	
	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmOnlineMaklumatPajakanData logic = new FrmOnlineMaklumatPajakanData();
	private static Logger log = Logger.getLogger(FrmPajakanOnlineMaklumatPajakanView.class);
	
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionPajakan = getParam("actionPajakan");
        if (actionPajakan.isEmpty()){
        	actionPajakan = "papar";
        }
        String submit = getParam("command");   
        String mode = getParam("mode");
        if (mode.isEmpty()){
        	mode = "view";
        }
        String hitButton = getParam("hitButton");
        String selectedTab = getParam("selectedTab");
        if(selectedTab.equals("") || selectedTab.equals(null)){
        	selectedTab = "0";
        }
        
        //GET ID PARAM
        String idFailSession = "";
        if (session.getAttribute("idFail") != null){
        	idFailSession = (String) session.getAttribute("idFail");
        }
        String idKementarianSession = "";
        if (session.getAttribute("idKementerian") != null){
        	idKementarianSession = (String) session.getAttribute("idKementerian");
        }
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
        System.out.println("session ollie"+idFailSession+",  "+idKementarianSession);
        //VECTOR
        Vector beanHeader = null;
        Vector senaraiHakmilik = null;
        Vector beanMaklumatPermohonan = null;
        
        vm = "app/htp/online/frmOnlineMaklumatPajakan.jsp";
        
        //HITBUTTON
		if (postDB){
    		/*if ("seterusnya".equals(hitButton)){
         		logic.seterusnya(idFail, idPermohonan, subUrusan, session);
        	}*/
    		if ("deleteHakmilik".equals(hitButton)){
         		logic.deleteHakmilik(idHakmilikUrusan);
        	}
    		if ("saveUpdateFail".equals(hitButton)){
    			//saveUpdateFail(idFail,session);
    			saveUpdateFail(idFail,subUrusan,session);
        	}
    	}
		
        //HEADER
        beanHeader = new Vector();
        logicHeader.setMaklumatPermohonan(idFail);
        beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);
		
		if (beanHeader.size() != 0){
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = hashHeader.get("idFail").toString();
			idPermohonan = hashHeader.get("idPermohonan").toString();
			idStatus = hashHeader.get("idStatus").toString();
			subUrusan = hashHeader.get("subUrusan").toString();
		}
		
		//ACTION PAJAKAN
		if ("papar".equals(actionPajakan)){
			
			//GO TO PAPAR PAJAKAN  
			if(selectedTab.equals("0")){
				logicHeader.setMaklumatPermohonan(idFail);
		
				beanMaklumatPermohonan = new Vector();
    			beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
        		this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
        		MaklumatPermohonanView(mode);
				
			}
			
			else if(selectedTab.equals("1")){
				System.out.println("papar 1");
        		senaraiHakmilik = new Vector();
    			logic.setListHakmilik(idPermohonan);
    			senaraiHakmilik = logic.getSenaraiHakmilik();
    			this.context.put("SenaraiHakmilik", senaraiHakmilik);
    			this.context.put("idKementarian", idKementarianSession);
        	}
        	
		}
		
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("mode", mode);
		this.context.put("subUrusan", subUrusan);
		this.context.put("selectedTab", selectedTab);
	    
		log.info(vm);
		return vm;
	}
	
	  public void MaklumatPermohonanView(String mode) throws Exception{

	    	try {
	    		
	    		if (mode.equalsIgnoreCase("view")){    			

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
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
	  
	  private void saveUpdateFail(String idFail,String idSubUrusan,HttpSession session) throws Exception {
	    	Hashtable hash = new Hashtable();
	    	hash.put("noFailKJP", getParam("txtNoFailKJP"));
			hash.put("tarikhSuratKJP", getParam("tarikhSuratKJP"));
			hash.put("noFailLain", getParam("txtNoFailLain"));
			hash.put("tarikhAgihan", getParam("tarikhAgihan"));
			hash.put("tajuk", getParam("txtTajuk"));
			hash.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon"));			
			//logic.saveUpdateFail(idFail, hash, session);
			logic.saveUpdateFail(idFail, idSubUrusan, hash, session);
			
		}

}

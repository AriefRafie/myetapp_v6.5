package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import ekptg.model.php2.FrmAPBSenaraiFailData;

import lebah.portal.AjaxBasedModule;

public class FrmAPBPopupSejarahMaklumatLesen extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	
	FrmAPBSenaraiFailData logic = new FrmAPBSenaraiFailData();
	
	public String doTemplate2() throws Exception {
		//GET DEFAULT PARAM
		Vector<Hashtable<String,String>> listSyarikat = null;
		
	    String vm = "app/php2/frmPopupSejarahMaklumatLesen.jsp";
	    context.put("SenaraiFailSyarikat","");
	    
	    String noFail=getParam("txtCarianNoFail");
	    String noLesen=getParam("txtCarianNoLesen");
	   
	    listSyarikat = new Vector<Hashtable<String,String>>();
	    listSyarikat = logic.getCarianFailSyarikat(noFail,noLesen);
		this.context.put("SenaraiFailSyarikat", listSyarikat);
		context.put("totalRecords",listSyarikat.size());
	    
		return vm;
	}
}

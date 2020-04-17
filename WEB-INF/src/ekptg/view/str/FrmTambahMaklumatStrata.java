package ekptg.view.str;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

public class FrmTambahMaklumatStrata extends AjaxBasedModule {

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = request.getSession();
		
        String moduleName = (String) session.getAttribute("_portal_module");
        String[] mName = moduleName.split("_");
        
        String vm = "";
        
        String paramNegeri = "";
        String paramKodLot = "";
        String paramNoLot = "";
        String paramNoCF = "";
        String paramNamaPemaju = "";
        String paramNamaSkim = "";
        
//        context.put("moduleName", mName[1].toLowerCase());
        
//        if ("searchNilaianHTA".equalsIgnoreCase(action2) || "".equalsIgnoreCase(action2) || "emptyNilaianHTA".equalsIgnoreCase(action2)) {
        	// cater for page carian
            paramNegeri = getParam("paramNegeri");
            paramKodLot = getParam("paramKodLot");
            paramNoLot = getParam("paramNoLot");
            paramNoCF = getParam("paramNoCF");
            paramNamaPemaju = getParam("paramNamaPemaju");
            paramNamaSkim = getParam("paramNamaSkim");
            
//            vm = "app/integrasi/frmJPPHNilaianHTACarian.jsp";
//            if ("searchNilaianHTA".equalsIgnoreCase(action2)) {
//            	vList = modelHTA.searchHTA(Carian_NoFail, Carian_NoPermohonan, Carian_NamaSiMati, Carian_NoKPSiMati);
//            	context.put("ListPermohonan", vList);
//        		setupPage(session, action, vList);
//        		context.put("pagingTitle", "title");
//            }
//    		Page_Carian = true;
//        }
            this.context.put("selectNegeriD",HTML.SelectNegeri("paramNegeri",Utils.parseLong(paramNegeri),""));
            

          vm = "app/str/frmTambahMaklumatStrata.jsp"; 
	    return vm;
	}

}

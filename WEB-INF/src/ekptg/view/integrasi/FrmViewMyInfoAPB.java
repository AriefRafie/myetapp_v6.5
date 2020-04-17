package ekptg.view.integrasi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmAPBModel;

public class FrmViewMyInfoAPB extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String doTemplate2() throws Exception{
		
		String vm = "app/integrasi/frmMyInfoAPB.jsp";
		HttpSession session = this.request.getSession();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String CARIAN_NOFAIL = "", CARIAN_TARIKHSURAT = "", CARIAN_NAMAPEMOHON = "", CARIAN_NEGERI = "";
		Date carianTarikhSurat = date;
		long idNegeri = 0;
		CARIAN_NOFAIL = getParam("CARIAN_NOFAIL");
		CARIAN_TARIKHSURAT = getParam("CARIAN_TARIKHSURAT");
    	CARIAN_NAMAPEMOHON = getParam("CARIAN_NAMAPEMOHON");
    	CARIAN_NEGERI = getParam("CARIAN_NEGERI");
    	if (!"".equalsIgnoreCase(CARIAN_TARIKHSURAT.trim())) {
    		carianTarikhSurat = (Date) sdf.parse(CARIAN_TARIKHSURAT);
    	} else {
    		carianTarikhSurat = null;
    	}
    	if (!"".equalsIgnoreCase(CARIAN_NEGERI.trim())) {
    		idNegeri = Long.valueOf(CARIAN_NEGERI);
    	}
        
        FrmAPBModel model = new FrmAPBModel();
        
        String userID = "", namaAgensi = "";
        long idAgensi = 0;
        userID = (String) session.getAttribute("_ekptg_user_id") == null ? "" : (String) session.getAttribute("_ekptg_user_id");
        idAgensi = model.getIdAgensiFromIdUser(Utils.parseLong(userID));
        namaAgensi = model.getNamaAgensi(idAgensi);
        context.put("NAMA_AGENSI", namaAgensi.toUpperCase());
        
    	Vector<Object> vList = new Vector<Object>();
    	
        vList = model.viewMyInfo(idAgensi);
        context.put("ListFail", vList);
        
        String action  = getParam("action");
        String action2 = getParam("action2");
        if ("searchAPB".equalsIgnoreCase(action2.trim())) {
        	// search record
        	model.noFail = CARIAN_NOFAIL;
        	model.tarikhSurat = carianTarikhSurat;
        	model.namaPemohon = CARIAN_NAMAPEMOHON;
        	model.idNegeri = idNegeri;
        	vList = model.searchMaklumat();
        	context.put("ListCarian", vList);
        	setupPage(session, action, vList);
    		context.put("pagingTitle", "title");
    	}
    	context.put("selCarianNegeri", HTML.SelectNegeri("CARIAN_NEGERI", idNegeri == 0 ? null : idNegeri));
        
    	return vm;
	}
}
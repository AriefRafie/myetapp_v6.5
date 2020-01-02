package ekptg.view.htp.utiliti;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmRekodPembangunanImejData;

public class FrmSenaraiImej extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.utiliti.FrmSenaraiImej.class);

	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
    	String PATH = "app/htp/utiliti/";
    	String vm = PATH+"frmSenaraiImej.jsp";
     	Vector list = new Vector();
    	list.clear();
		String submit = getParam("command");
		String idHakmilik = getParam("idHakmilik");
		this.context.put("idHakmilik", idHakmilik);
		//idHakmilik = "161095645";
		//javascript:hakmilik_detail('161099248','DAFTAR');
		//javascript:hakmilik_detail('161095645','DAFTAR');
		
		if(submit.equals("view")){
			String idGambar = getParam("idGambar");
		    this.context.put("idGambar", idGambar);
		    this.context.put("modeImej",submit );
			//myLog.info("idGambar="+idGambar);			
			this.context.remove("SenaraiImejDist");
			//list = FrmRekodPembangunanImejData.getMaklumatImejByIdHakmilik(idHakmilik);
			
		}else if(submit.equals("viewFull")){
			String idGambar = getParam("idGambar");
			this.context.put("idGambar", idGambar);
			this.context.put("modeImej",submit );

		}else{
			this.context.remove("SenaraiImejDist");			
		}	
		list = FrmRekodPembangunanImejData.getMaklumatImejByIdHakmilik(idHakmilik);
		//myLog.info(list.size());
		this.context.put("senaraiImej", list);
        return vm;
        
	}


}

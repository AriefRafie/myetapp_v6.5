package ekptg.view.htp.perletakhakan;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.perletakhakan.HakmilikPerletakhakanBean;
import ekptg.model.htp.perletakhakan.IHakmilikPerletakhakan;
import ekptg.model.htp.rekod.FrmRekodUtilData;

public class FrmPerletakhakanPindah extends AjaxBasedModule {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8487877676979529353L;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.perletakhakan.FrmPerletakhakanPindah.class);
	//FrmPerletakhakanPendaftaranData logic = new FrmPerletakhakanPendaftaranData(); //data tuk view
	private FrmRekodUtilData frmRekodUtilData = null;
	private final String IDURUSAN = "5";
	String isCarian = "tidak";
    String idPermohonan = "";
	private IHakmilikPerletakhakan iCukai = null;

	@Override
	public String doTemplate2() throws Exception {
		
		HttpSession session = this.request.getSession();
		
		Boolean postDB = false;
		String doPost = session.getAttribute("doPost").toString();
		if (doPost.equals("true")) {
			postDB = true;
		}

		String vm="app/htp/frmFailPerletakhakanPindah.jsp";
		
		String submit = getParam("command"); //untuk doAjaxCall
		String action = getParam("action"); //untuk setup paging je
		
		String idFail = getParam("idFail");		
		String idPermohonan = getParam("idPermohonan");
	

		//SENARAI PENDAFTARAN PERLETAHAKAN
		if("".equals(submit)){
		}else if ("pindah".equals(submit)) {	//TAMBAH PENDAFTARAN PERLETAHAKAN
			
			//getICukai().transferRecord("1610109376");
			getICukai().transferRecord("1610111581");
		}


	  return vm;
	  
   }

	private IHakmilikPerletakhakan getICukai(){
		if(iCukai==null){
			iCukai = new HakmilikPerletakhakanBean();
		}
		
		return iCukai;
		
	}
}
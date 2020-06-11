package ekptg.view.htp.rekod;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.rekod.ITanahCarian;
import ekptg.model.htp.rekod.PenawaranTukarLepasBean;

public class FrmRekodTukargunaPenawaranPelepasan extends AjaxBasedModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2848587214174652673L;
	private final String PATH="app/htp/rekod/";
	private static Logger log = Logger.getLogger(ekptg.view.htp.rekod.FrmRekodTukargunaPenawaranPelepasan.class);
	private ITanahCarian iPenawaran = null;
	Vector<?> senaraiFail = null;

	@Override
	public String doTemplate2() throws Exception {

		String vm = "";
		Vector list =null;
		HttpSession session = this.request.getSession();
		
		String action = getParam("action");
		String command = getParam("command");
		String negeri = getParam("negeri");
		String noFail = getParam("txtNoFail");
		//String peminjam = getParam("peminjam");
		//String urusan = getParam("urusan");
		
		this.context.put("action",action);
		
		//VM UNTUK LIST
		vm = PATH+"frmRekodSenaraiTukargunaPenawaranPelepasan.jsp";
		
		if("".equals(command)){
			senaraiFail = getIPenawaran().getCarianSenaraiHakmilikRizab("",noFail,"",""
					,"","",""
					,"","",""
					,"","","");

			//senaraiFail = getIPenawaran().carianFail(noFail,"","","","","","");		
			setupPage(session,action,senaraiFail);
			
		}else{
			senaraiFail = getIPenawaran().getCarianSenaraiHakmilikRizab("",noFail,"",""
					,"","",""
					,"","",""
					,"","","");
			//senaraiFail = getIPenawaran().carianFail(noFail,"","","","","","");		
			setupPage(session,action,senaraiFail);		
			
		}

		//this.context.put("senaraiFail",senaraiFail);
		this.context.put("txtNoFail",noFail);
		return vm;
	}
		
		private ITanahCarian getIPenawaran(){
			if(iPenawaran==null){
				iPenawaran = new PenawaranTukarLepasBean();
			}
			return iPenawaran;
			
		}		
	
}

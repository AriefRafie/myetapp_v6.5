package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;

public class FrmPajakanKecilSenaraiPeringatan extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = -609204869152059236L;
	Long idHakmilikUrusan = 0L;
	static Logger mylog = Logger.getLogger(ekptg.view.htp.FrmPajakanKecilSenaraiPeringatan.class);

	@SuppressWarnings("unchecked")
	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "app/htp/frmPajakanKecilSenaraiPeringatan.jsp";
      	String disability = "disabled";
	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
		Vector semakanSenarai = new Vector();

	    this.context.put("util", new lebah.util.Util());

	    Vector senaraiFail = null;
	    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
	    
        String id_kementerian = getParam("sockementerian");
	    String submit = getParam("command");
	    String idFail = getParam("fail");
	    String pageMode = getParam("pagemode");
	    String langkah = getParam("langkah");

	    if (!("".equals(submit))) {
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");	
	    	
// PAGE 1
	    	if("pksenaraifailcari".equals(submit)){ //carian
	    	    mylog.info("equals("+submit+")::pksenaraifailcari");
	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("pksenaraipermohonan".equals(submit)){
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraipermohonan");
			    //String fail = getParam("fail");
		    	Vector senaraiPermohonan = null;
		    	senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
		    	this.context.put("senaraiList", senaraiPermohonan);	    	
		    	this.context.put("idFail", idFail);	    	
		    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
		    }
	    }else{
	    	//template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";	
	    	senaraiFail = FrmUtilData.getSenaraiPeringatan("",(String)session.getAttribute("_ekptg_user_id"));

		}
	    this.context.put("senaraiPeringatan", senaraiFail);  

	    return template_name;
	  }

	 
}

package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPenswastaanMaklumatProjekData;



public class FrmPenswastaanMaklumatProjekView extends AjaxBasedModule {

static Logger myLogger = Logger.getLogger(FrmPenswastaanMaklumatProjekView.class);
	
FrmPenswastaanMaklumatProjekData logic = new FrmPenswastaanMaklumatProjekData();
	
	Hashtable permohonan = null;

	public String doTemplate2() throws Exception {
		
		
		HttpSession session = request.getSession();	
		
		String vm = "";
		String usid = "";
		String socAgensi = "";
		String id_permohonan = getParam("idpermohonan");
		String socKementerian = "";
		String main_command = getParam("command");
		
		String id_negeri = getParam("socNegeri");
		String id_kementerian = getParam("sockementerian");
		String id_agensi = getParam("socAgensi");
		int idNegeri = getParamAsInteger("socNegeri");
		
		
		
		
		
		this.context.put("modeMemorandum", "");
		this.context.put("readonly", "");
		this.context.put("inputTextClass", "");
		
		
		Hashtable permohonan = null;
		
		this.context.put("id_permohonan", id_permohonan);
		
		//UNTUK HEADER
		permohonan = (Hashtable) FrmPenswastaanMaklumatProjekData.getPermohonanInfo(id_permohonan);
		label(permohonan);
		
		myLogger.info("COMMAND :"+main_command);
		
		vm = "app/htp/frmPenswastaan2_MaklumatProjek.jsp";
	
		if ("papar".equals(main_command)){
			Vector maklumatProjek = new Vector();
			maklumatProjek = FrmPenswastaanMaklumatProjekData.getmaklumatProjek(id_permohonan);
			this.context.put("maklumatProjek",maklumatProjek);
			
			
		}
		
		
		
		return vm;
		
		
		
	
	
}

	private void label(Hashtable permohonan) {

		String labelNegeri = (String) permohonan.get("negeri");
		String labelKementerian = (String) permohonan.get("kementerian");
		String labelAgensi = (String) permohonan.get("agensi");
		String labelTajuk = (String) permohonan.get("tujuan");
		String labelNoFail = (String) permohonan.get("fail");
		String labelNoFailLain = (String) permohonan.get("rujukankjp");
		String labelTarikhSuratKJP = (String) permohonan.get("rtterima");
		String labelTarikhBukaFail = (String) permohonan.get("tdaftar");

		this.context.put("labelNegeri", labelNegeri);
		this.context.put("labelKementerian", labelKementerian);
		this.context.put("labelAgensi", labelAgensi);
		this.context.put("labelTajuk", labelTajuk);
		this.context.put("labelNoFail", labelNoFail);
		this.context.put("labelNoFailLain", labelNoFailLain);
		this.context.put("labelTarikhSuratKJP", labelTarikhSuratKJP);
		this.context.put("labelTarikhBukaFail", labelTarikhBukaFail);

	}
	
}

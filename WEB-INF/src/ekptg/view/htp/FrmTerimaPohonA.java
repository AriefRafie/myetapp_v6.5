package ekptg.view.htp;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;
//import org.apache.poi.util.SystemOutLogger;

public class FrmTerimaPohonA extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger mylog = Logger.getLogger(FrmTerimaPohonA.class);

	public String doTemplate2() throws Exception {
		
		  HttpSession session = this.request.getSession(); 
		  //String template_name = "app/htp/testTabb.jsp";
		  String template_name = "app/htp/frmTerimaPohonTambahMaklumat.jsp";
		  
		  String submit = getParam("command"); 
		  String socKementerian = "";
		  String socAgensi = ""; 
		  String socNegeri = ""; 
		  String socUrusan = "";
		  String socStatustanah = "";
		  String socjenisFail = ""; String
		  socUPPegawai = "";
		  
		  //Hashtable permohonan = FrmSenaraiFailTerimaPohonData.getPermohonanInfo(id);
		 //String socDaerah = HTML.SelectDaerahByNegeri((String) permohonan.get("iddaerah"), "socDaerah");
			
		 //String socMukim = HTML.SelectMukimByNegeri((String) permohonan.get("nama_mukim"), "socMukim");
		
			//context.put("socDaerah", socDaerah);
			//context.put("socMukim", socMukim);
		  
		  if ("AsasTanah".equals(submit)) {
		  
		  String fail1 = getParam("AsasTanah"); System.out.println("Mode ::"+fail1);
		  
		  }
		 
/*
		HttpSession session = this.request.getSession();
		String template_name = "app/htp/frmTerimaPohonMaklumatAsasTanah.jsp";

		String socDaerah = "";
		String socMukim = "";
		String socNegeri = "";
		String id = "0";

		//Hashtable permohonan = FrmPajakanKecilSenaraiPermohonanData.getPermohonanInfo(id);
		Hashtable permohonan = FrmSenaraiFailTerimaPohonData.getPermohonanInfo(id);
		socNegeri = HTML.SelectNegeri("socNegeri", (String) permohonan.get("idnegeri"));
		socDaerah = HTML.SelectDaerahByNegeri((String) permohonan.get("iddaerah"), "socDaerah");
		socMukim = HTML.SelectMukimByNegeri((String) permohonan.get("nama_mukim"), "socMukim");
		context.put("socNegeri", socNegeri);
		context.put("socDaerah", socDaerah);
		context.put("socMukim", socMukim);
*/
		return template_name;

	}
}

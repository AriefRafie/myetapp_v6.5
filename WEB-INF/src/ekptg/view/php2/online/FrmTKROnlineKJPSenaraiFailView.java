/**
 * 
 */
package ekptg.view.php2.online;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.php2.FrmTKRHeaderData;
import ekptg.model.php2.FrmTKRJabatanTeknikalData;
import ekptg.model.php2.online.FrmTKROnlineKJPSenaraiFailData;

public class FrmTKROnlineKJPSenaraiFailView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLog = Logger.getLogger(FrmPLPOnlineKJPSenaraiFailView.class);

	FrmTKRHeaderData logicHeader = new FrmTKRHeaderData();
	FrmTKRJabatanTeknikalData logicJabatanTeknikal = new FrmTKRJabatanTeknikalData();
	FrmTKROnlineKJPSenaraiFailData logic = new FrmTKROnlineKJPSenaraiFailData();
	private String templateDir = "app/php2/online/ulasanKJP/tkr";


	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP PAGING SHJ
		String vm = "";
		String command = getParam("command");

		// VECTOR
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatAgensi = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatBorangK = null;

		// GET DROPDOWN PARAM
		String idKategoriPemohon = getParam("socKategoriPemohon");
		if (idKategoriPemohon == null || idKategoriPemohon.trim().length() == 0) {
			idKategoriPemohon = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idNegeriJKPTG = getParam("socNegeriJKPTG");
		if (idNegeriJKPTG == null || idNegeriJKPTG.trim().length() == 0){
			idNegeriJKPTG = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0){
			idPejabat = "99999";
		}
		String idLuasKegunaan = getParam("socLuasKegunaan");
		if (idLuasKegunaan == null || idLuasKegunaan.trim().length() == 0){
			idLuasKegunaan = "99999";
		}
		String idJenisTanah = getParam("socJenisTanah");
		if (idJenisTanah == null || idJenisTanah.trim().length() == 0){
			idJenisTanah = "99999";
		}

		context.put("command", command);
		context.put("templateDir", templateDir);

		String idFail = getParam("idFail");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");

		//DATE
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();

		try {
			
			if ("refreshDokumenMuatNaik".equals(command)) {

				logicJabatanTeknikal.setMaklumatKJP(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				Hashtable maklumatUlasan = (Hashtable) logicJabatanTeknikal.getBeanMaklumatKJP().get(0);
				this.context.put("maklumatUlasan", maklumatUlasan);
				this.context.put("idUlasanTeknikal", idUlasanTeknikal);

				Hashtable lampiran = logic.getMaklumatLampiran(idUlasanTeknikal, logic.getIdPermohonanByIdUlasanTeknikal(idUlasanTeknikal));
				this.context.put("lampiran", lampiran);

				vm = "/maklumatUlasan.jsp";	
			}
			} catch (Exception ex) {
				ex.printStackTrace();

			}
		return templateDir + vm;

	}
	
}

/**
 * 
 */
package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import de.hunsicker.jalopy.language.ClassRepositoryEntry.Info;
import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmAPBHeaderData;
import ekptg.model.php2.FrmAPBJabatanTeknikalData;
import ekptg.model.utils.emel.EmailConfig;

/**
 * @author hilda
 * 
 */
public class FrmAPBJabatanTeknikalView extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmAPBJabatanTeknikalView.class);
	private static final long serialVersionUID = 1L;

	FrmAPBHeaderData logicHeader = new FrmAPBHeaderData();
	FrmAPBJabatanTeknikalData logic = new FrmAPBJabatanTeknikalData();
	Utils utils = new Utils();
	EmailConfig email = new EmailConfig();
	
	//KEMENTERIAN DI HQ
	String idKementerianJUPEM = "18";
	String idAgensiJUPEM = "435";	
	String idKementerianJMG = "18";
	String idAgensiJMG = "438";
	String idKementerianJP = "6";
	String idAgensiJP = "603";
	String idKementerianJLM = "8";
	String idAgensiJLM = "1614803";
	String idKementerianPHM = "7";
	String idAgensiPHM = "1615806";
	String idKementerianJPS = "18";
	String idAgensiJPS = "747";	

	//BASED ON NEGERI PERAIRAN
	String idKementerianJAS = "18";
	String idKementerianPTG = "18";
	
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String submit = getParam("command");

		String selectedTabUpper = (String) getParam("selectedTabUpper");
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}

		String vm = "";
		
		String hitButton = getParam("hitButton");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		
		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idStatus = getParam("idStatus");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idPertindihan = getParam("idPertindihan");
		String flagLampiran = getParam("flagLampiran");

		
		String flagStatus = getParam("flagStatus");
        String flagAktif = getParam("flagAktif");
        String flagNotifikasi = getParam("flagNotifikasi");
        String idKementerianTanah = getParam("idKementerianTanah");
        String idAgensiTanah = getParam("idAgensiTanah");
		// GET DROPDOWN PARAM

		// VECTOR
		Vector beanHeader = null; 
		Vector senaraiJUPEM = null;
		Vector beanMaklumatJUPEM = null;
		Vector beanMaklumatDokumenJUPEM = null;
		Vector senaraiJAS = null;
		Vector beanMaklumatJAS = null;
		Vector beanMaklumatDokumenJAS = null;
		Vector senaraiJMG = null;
		Vector beanMaklumatJMG = null;
		Vector beanMaklumatDokumenJMG = null;
		Vector senaraiJP = null;
		Vector beanMaklumatJP = null;
		Vector beanMaklumatDokumenJP = null;
		Vector senaraiJLM = null;
		Vector beanMaklumatJLM = null;
		Vector beanMaklumatDokumenJLM = null;
		Vector senaraiPHM = null;
		Vector beanMaklumatPHM = null;
		Vector beanMaklumatDokumenPHM = null;
		Vector senaraiJPS = null;
		Vector beanMaklumatJPS = null;
		Vector beanMaklumatDokumenJPS = null;
		Vector senaraiPertindihan = null;
		Vector beanMaklumatPertindihan = null;
		Vector senaraiPTG = null;
		Vector beanMaklumatPTG = null;
		Vector beanMaklumatDokumenPTG = null;
		Vector senaraiNotifikasi = null;

		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0) {
			idPejabat = "99999";
		}
        String idSuratKe = getParam("idSuratKe");
		if (idSuratKe == null || idSuratKe.trim().length() == 0){
			idSuratKe = "99999";
		}
		
		String step = getParam("step");	

		vm = "app/php2/frmAPBJabatanTeknikal.jsp";

		if (postDB) {
			if ("simpanMaklumatJUPEM".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJUPEM(idPermohonan, idKementerianJUPEM, idAgensiJUPEM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJUPEM".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJUPEM(idUlasanTeknikal, idPermohonan, idKementerianJUPEM, idAgensiJUPEM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJUPEM".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJUPEM(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"),  session);
        			logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatJAS".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJAS".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJAS(idUlasanTeknikal, idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJAS".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJAS(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"),  session);
        			logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatJMG".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJMG".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJMG(idUlasanTeknikal, idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJMG".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJMG(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"),  session);
        		    logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatJP".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJP".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJP(idUlasanTeknikal, idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJP".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJP(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"),  session);
        			logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatJLM".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJLM".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJLM(idUlasanTeknikal, idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJLM".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJLM(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"),  session);
        		    logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatPHM".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganPHM".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganPHM(idUlasanTeknikal, idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatPHM".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanPHM(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"),  session);
        		logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatJPS".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganJPS".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganJPS(idUlasanTeknikal, idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatJPS".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanJPS(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"), session);
        		    logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	if ("simpanMaklumatPTG".equals(hitButton)){    			
        		idUlasanTeknikal = logic.simpanMaklumatPTG(idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		
        		//GENERATE UTK JABATAN TEKNIKAL YG LAIN
        		logic.simpanMaklumatJAS(idPermohonan, idKementerianJAS, logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJMG(idPermohonan, idKementerianJMG, idAgensiJMG, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJP(idPermohonan, idKementerianJP, idAgensiJP, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJLM(idPermohonan, idKementerianJLM, idAgensiJLM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatPHM(idPermohonan, idKementerianPHM, idAgensiPHM, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
        		logic.simpanMaklumatJPS(idPermohonan, idKementerianJPS, idAgensiJPS, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanMaklumatUlanganPTG".equals(hitButton)){
        		idUlasanTeknikal = logic.simpanMaklumatUlanganPTG(idUlasanTeknikal, idPermohonan, idKementerianPTG, logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)), getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), session);
    		}
        	if ("simpanKemaskiniMaklumatPTG".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatKJT(idUlasanTeknikal, getParam("txtTarikhHantar"), 
        				getParam("txtJangkaMasa"), getParam("txtTarikhJangkaTerima"), flagStatus, getParam("txtTarikhTerima"),
        				getParam("txtTarikhSurat"), getParam("txtNoRujukanSurat"), getParam("txtUlasan"), getParam("txtNamaPengulas"), getParam("txtNoTelPengulas"), session);
        		if (getParam("txtUlasan").trim().length() > 0)
        			logic.updateUlasanPTG(idPermohonan, getParam("txtNoRujukanSurat"), getParam("txtTarikhTerima"), getParam("txtUlasan"), session);
        		    logic.sendEmailMaklumanJT("apbteam@yopmail.com",idPermohonan);
        	}
        	
        	if ("simpanDokumenKJT".equals(hitButton)) {
        		logic.hapusDokumen(idUlasanTeknikal, flagLampiran, session);
				uploadFiles(idUlasanTeknikal, idPermohonan, flagLampiran, session);
			}
    		if ("hapusMaklumatUlasan".equals(hitButton)){
    			logic.hapusMaklumatUlasan(idUlasanTeknikal, session);
    		} 
    		if ("simpanMaklumatPertindihanKoordinat".equals(hitButton)){
        		idPertindihan = logic.simpanMaklumatPertindihan(idPermohonan, getParam("txtNoFailTindih"), getParam("txtNamaSyarikat"), getParam("socJenisTindih"), 
        				getParam("socStatusTindih"), getParam("txtLain"), session);
    		}
    		if ("simpanKemaskiniMaklumatPertindihanKoordinat".equals(hitButton)){
        		logic.simpanKemaskiniMaklumatPertindihan(idPertindihan, getParam("txtNoFailTindih"), getParam("txtNamaSyarikat"), getParam("socJenisTindih"), 
        				getParam("socStatusTindih"), getParam("txtLain"), session);
    		}
    		if ("hapusMaklumatPertindihanKoordinat".equals(hitButton)) {
        		logic.hapusMaklumatPertindihan(idPertindihan, session);
			}    		
			
			// SETERUSNYA
			if ("doSeterusnya".equals(hitButton))  {
				logic.updateStatus(idFail, idPermohonan, session);
			}
			if ("doBatalPermohonan".equals(hitButton)){
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus, getParam("tarikhBatal"), getParam("txtSebab"), session);
    			step = "";
    		}
			//rozai add 10/6/2020
			if ("simpanRekodEmailJUPEM".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailJUPEM(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
			if ("simpanRekodEmailJAS".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailJAS(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
			if ("simpanRekodEmailJMG".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailJMG(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
			if ("simpanRekodEmailJP".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailJP(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
			if ("simpanRekodEmailJLM".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailJLM(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
			if ("simpanRekodEmailPHM".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailPHM(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan,getParam("txtEmel"));
			}
			if ("simpanRekodEmailJPS".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailJPS(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
			if ("simpanRekodEmailPTG".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanRekodEmailPTG(
						idUlasanTeknikal,idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, getParam("txtNamaPegawai"), 
						getParam("txtJawatan"), getParam("txtEmel"),session);
				logic.sendEmailNotifikasi(idPermohonan, getParam("txtEmel"));
			}
		}

		// HEADER
		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader
					.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			String status = (String) hashHeader.get("status");
			this.context.put("status", status.toUpperCase());
		}
		
		// GET FLAG OPEN DETAIL		
		boolean flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 3); // 3 = JABATAN TEKNIKAL
		this.context.put("flagOpenDetail", flagOpenDetail);
		
		
		//ULASAN JUPEM
		if ("0".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT JUPEM
			if ("openJUPEM".equals(flagPopup)){
				this.context.put("idSuratKe", idSuratKe);
				this.context.put("selectNegeri", HTML.SelectNegeri(
						"socNegeri", Long.parseLong(idNegeri), "",
						" onChange=\"doChangeNegeri();\""));
				this.context.put("selectPejabat", HTML
						.SelectPejabatByIdNegeriAndJenisPejabat(
								"socPejabat", Long.parseLong(idPejabat),
								"", " onChange=\"doChangePejabat();\"",
								idNegeri, "2"));
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJUPEM = new Vector();    			
		    			Hashtable hashMaklumatJUPEM = new Hashtable();
		    			hashMaklumatJUPEM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJUPEM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJUPEM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJUPEM)); //JUPEM
		    			hashMaklumatJUPEM.put("tarikhHantar", "");
		    			hashMaklumatJUPEM.put("jangkamasa", "");
		    			hashMaklumatJUPEM.put("tarikhJangkaTerima", "");

		    			beanMaklumatJUPEM.addElement(hashMaklumatJUPEM);
						this.context.put("BeanMaklumatJUPEM", beanMaklumatJUPEM);
						
	    			} else {
	    				
	    				beanMaklumatJUPEM = new Vector();    			
		    			Hashtable hashMaklumatJUPEM = new Hashtable();
		    			hashMaklumatJUPEM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJUPEM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJUPEM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJUPEM)); //JUPEM
		    			hashMaklumatJUPEM.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJUPEM.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJUPEM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJUPEM.addElement(hashMaklumatJUPEM);
						this.context.put("BeanMaklumatJUPEM", beanMaklumatJUPEM);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJUPEM = new Vector();    			
		    			Hashtable hashMaklumatJUPEM = new Hashtable();
		    			hashMaklumatJUPEM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJUPEM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJUPEM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJUPEM)); //JUPEM
		    			hashMaklumatJUPEM.put("tarikhHantar", "");
		    			hashMaklumatJUPEM.put("jangkamasa", "");
		    			hashMaklumatJUPEM.put("tarikhJangkaTerima", "");

		    			beanMaklumatJUPEM.addElement(hashMaklumatJUPEM);
						this.context.put("BeanMaklumatJUPEM", beanMaklumatJUPEM);
						
	    			} else {
	    				
	    				beanMaklumatJUPEM = new Vector();    			
		    			Hashtable hashMaklumatJUPEM = new Hashtable();
		    			hashMaklumatJUPEM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJUPEM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJUPEM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJUPEM)); //JUPEM
		    			hashMaklumatJUPEM.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJUPEM.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJUPEM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJUPEM.addElement(hashMaklumatJUPEM);
						this.context.put("BeanMaklumatJUPEM", beanMaklumatJUPEM);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJUPEM = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatJUPEM = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatJUPEM",beanMaklumatJUPEM);
					
					if (beanMaklumatJUPEM.size() != 0){
						Hashtable hashMaklumatJUPEM = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatJUPEM.get("flagStatus");
						flagAktif = (String) hashMaklumatJUPEM.get("flagAktif");
					}
					
					beanMaklumatDokumenJUPEM = new Vector();
					beanMaklumatDokumenJUPEM = logic.findDocJUPEM(idUlasanTeknikal);
					this.context.put("BeanDocJUPEM",beanMaklumatDokumenJUPEM);
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN JUPEM
						beanMaklumatDokumenJUPEM = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenJUPEM = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenJUPEM",beanMaklumatDokumenJUPEM);
					}
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiJUPEM(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJUPEM = new Vector();    			
	    			Hashtable hashMaklumatJUPEM = new Hashtable();
	    			hashMaklumatJUPEM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJUPEM)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatJUPEM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJUPEM)); //JUPEM
	    			hashMaklumatJUPEM.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJUPEM.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJUPEM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatJUPEM.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJUPEM.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJUPEM.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJUPEM.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJUPEM.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatJUPEM.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatJUPEM.addElement(hashMaklumatJUPEM);
					this.context.put("BeanMaklumatJUPEM", beanMaklumatJUPEM);
				}
			}
		}
		
		//ULASAN JAS
		if ("1".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT JAS
			if ("openJAS".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJAS = new Vector();    			
		    			Hashtable hashMaklumatJAS = new Hashtable();
		    			hashMaklumatJAS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJAS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJAS.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //JAS NEGERI
		    			hashMaklumatJAS.put("tarikhHantar", "");
		    			hashMaklumatJAS.put("jangkamasa", "");
		    			hashMaklumatJAS.put("tarikhJangkaTerima", "");

		    			beanMaklumatJAS.addElement(hashMaklumatJAS);
						this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
						
	    			} else {
	    				
	    				beanMaklumatJAS = new Vector();    			
		    			Hashtable hashMaklumatJAS = new Hashtable();
		    			hashMaklumatJAS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJAS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJAS.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //JAS NEGERI
		    			hashMaklumatJAS.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJAS.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJAS.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJAS.addElement(hashMaklumatJAS);
						this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJAS = new Vector();    			
		    			Hashtable hashMaklumatJAS = new Hashtable();
		    			hashMaklumatJAS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJAS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJAS.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //JAS NEGERI
		    			hashMaklumatJAS.put("tarikhHantar", "");
		    			hashMaklumatJAS.put("jangkamasa", "");
		    			hashMaklumatJAS.put("tarikhJangkaTerima", "");

		    			beanMaklumatJAS.addElement(hashMaklumatJAS);
						this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
						
	    			} else {
	    				
	    				beanMaklumatJAS = new Vector();    			
		    			Hashtable hashMaklumatJAS = new Hashtable();
		    			hashMaklumatJAS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJAS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJAS.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //JAS NEGERI
		    			hashMaklumatJAS.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJAS.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJAS.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJAS.addElement(hashMaklumatJAS);
						this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJAS = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatJAS = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatJAS",beanMaklumatJAS);
					
					if (beanMaklumatJAS.size() != 0){
						Hashtable hashMaklumatJAS = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatJAS.get("flagStatus");
						flagAktif = (String) hashMaklumatJAS.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN JAS
						beanMaklumatDokumenJAS = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenJAS = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenJAS",beanMaklumatDokumenJAS);
					}
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiJAS(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJAS = new Vector();    			
	    			Hashtable hashMaklumatJAS = new Hashtable();
	    			hashMaklumatJAS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJAS)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatJAS.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatJASByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //JAS NEGERI
	    			hashMaklumatJAS.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJAS.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJAS.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatJAS.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJAS.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJAS.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJAS.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJAS.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatJAS.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatJAS.addElement(hashMaklumatJAS);
					this.context.put("BeanMaklumatJAS", beanMaklumatJAS);
				}
			}
		}
		
		//ULASAN JMG
		if ("2".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT JMG
			if ("openJMG".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJMG = new Vector();    			
		    			Hashtable hashMaklumatJMG = new Hashtable();
		    			hashMaklumatJMG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJMG)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJMG.put("agensi", logic.getAgensiByIdAgensi(idAgensiJMG)); //JMG
		    			hashMaklumatJMG.put("tarikhHantar", "");
		    			hashMaklumatJMG.put("jangkamasa", "");
		    			hashMaklumatJMG.put("tarikhJangkaTerima", "");

		    			beanMaklumatJMG.addElement(hashMaklumatJMG);
						this.context.put("BeanMaklumatJMG", beanMaklumatJMG);
						
	    			} else {
	    				
	    				beanMaklumatJMG = new Vector();    			
		    			Hashtable hashMaklumatJMG = new Hashtable();
		    			hashMaklumatJMG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJMG)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJMG.put("agensi", logic.getAgensiByIdAgensi(idAgensiJMG)); //JMG
		    			hashMaklumatJMG.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJMG.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJMG.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJMG.addElement(hashMaklumatJMG);
						this.context.put("BeanMaklumatJMG", beanMaklumatJMG);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJMG = new Vector();    			
		    			Hashtable hashMaklumatJMG = new Hashtable();
		    			hashMaklumatJMG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJMG)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJMG.put("agensi", logic.getAgensiByIdAgensi(idAgensiJMG)); //JMG
		    			hashMaklumatJMG.put("tarikhHantar", "");
		    			hashMaklumatJMG.put("jangkamasa", "");
		    			hashMaklumatJMG.put("tarikhJangkaTerima", "");

		    			beanMaklumatJMG.addElement(hashMaklumatJMG);
						this.context.put("BeanMaklumatJMG", beanMaklumatJMG);
						
	    			} else {
	    				
	    				beanMaklumatJMG = new Vector();    			
		    			Hashtable hashMaklumatJMG = new Hashtable();
		    			hashMaklumatJMG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJMG)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJMG.put("agensi", logic.getAgensiByIdAgensi(idAgensiJMG)); //JMG
		    			hashMaklumatJMG.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJMG.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJMG.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJMG.addElement(hashMaklumatJMG);
						this.context.put("BeanMaklumatJMG", beanMaklumatJMG);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJMG = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatJMG = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatJMG",beanMaklumatJMG);
					
					if (beanMaklumatJMG.size() != 0){
						Hashtable hashMaklumatJMG = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatJMG.get("flagStatus");
						flagAktif = (String) hashMaklumatJMG.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN JMG
						beanMaklumatDokumenJMG = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenJMG = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenJMG",beanMaklumatDokumenJMG);
					}
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiJMG(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJMG = new Vector();    			
	    			Hashtable hashMaklumatJMG = new Hashtable();
	    			hashMaklumatJMG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJMG)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatJMG.put("agensi", logic.getAgensiByIdAgensi(idAgensiJMG)); //JMG
	    			hashMaklumatJMG.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJMG.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJMG.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatJMG.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJMG.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJMG.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJMG.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJMG.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatJMG.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatJMG.addElement(hashMaklumatJMG);
					this.context.put("BeanMaklumatJMG", beanMaklumatJMG);
				}
			}
		}
		
		//ULASAN JP
		if ("3".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT JP
			if ("openJP".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJP = new Vector();    			
		    			Hashtable hashMaklumatJP = new Hashtable();
		    			hashMaklumatJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJP)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiJP)); //JP
		    			hashMaklumatJP.put("tarikhHantar", "");
		    			hashMaklumatJP.put("jangkamasa", "");
		    			hashMaklumatJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatJP.addElement(hashMaklumatJP);
						this.context.put("BeanMaklumatJP", beanMaklumatJP);
						
	    			} else {
	    				
	    				beanMaklumatJP = new Vector();    			
		    			Hashtable hashMaklumatJP = new Hashtable();
		    			hashMaklumatJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJP)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiJP)); //JP
		    			hashMaklumatJP.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJP.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJP.addElement(hashMaklumatJP);
						this.context.put("BeanMaklumatJP", beanMaklumatJP);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJP = new Vector();    			
		    			Hashtable hashMaklumatJP = new Hashtable();
		    			hashMaklumatJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJP)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiJP)); //JP
		    			hashMaklumatJP.put("tarikhHantar", "");
		    			hashMaklumatJP.put("jangkamasa", "");
		    			hashMaklumatJP.put("tarikhJangkaTerima", "");

		    			beanMaklumatJP.addElement(hashMaklumatJP);
						this.context.put("BeanMaklumatJP", beanMaklumatJP);
						
	    			} else {
	    				
	    				beanMaklumatJP = new Vector();    			
		    			Hashtable hashMaklumatJP = new Hashtable();
		    			hashMaklumatJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJP)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiJP)); //JP
		    			hashMaklumatJP.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJP.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJP.addElement(hashMaklumatJP);
						this.context.put("BeanMaklumatJP", beanMaklumatJP);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJP = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatJP = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatJP",beanMaklumatJP);
					
					if (beanMaklumatJP.size() != 0){
						Hashtable hashMaklumatJP = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatJP.get("flagStatus");
						flagAktif = (String) hashMaklumatJP.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN JP
						beanMaklumatDokumenJP = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenJP = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenJP",beanMaklumatDokumenJP);
					}
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiJP(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJP = new Vector();    			
	    			Hashtable hashMaklumatJP = new Hashtable();
	    			hashMaklumatJP.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJP)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatJP.put("agensi", logic.getAgensiByIdAgensi(idAgensiJP)); //JP
	    			hashMaklumatJP.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJP.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJP.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatJP.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJP.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJP.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJP.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJP.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatJP.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatJP.addElement(hashMaklumatJP);
					this.context.put("BeanMaklumatJP", beanMaklumatJP);
				}
			}
		}
		
		//ULASAN JLM
		if ("4".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT JLM
			if ("openJLM".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJLM = new Vector();    			
		    			Hashtable hashMaklumatJLM = new Hashtable();
		    			hashMaklumatJLM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJLM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJLM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJLM)); //JLM
		    			hashMaklumatJLM.put("tarikhHantar", "");
		    			hashMaklumatJLM.put("jangkamasa", "");
		    			hashMaklumatJLM.put("tarikhJangkaTerima", "");

		    			beanMaklumatJLM.addElement(hashMaklumatJLM);
						this.context.put("BeanMaklumatJLM", beanMaklumatJLM);
						
	    			} else {
	    				
	    				beanMaklumatJLM = new Vector();    			
		    			Hashtable hashMaklumatJLM = new Hashtable();
		    			hashMaklumatJLM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJLM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJLM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJLM)); //JLM
		    			hashMaklumatJLM.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJLM.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJLM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJLM.addElement(hashMaklumatJLM);
						this.context.put("BeanMaklumatJLM", beanMaklumatJLM);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJLM = new Vector();    			
		    			Hashtable hashMaklumatJLM = new Hashtable();
		    			hashMaklumatJLM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJLM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJLM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJLM)); //JLM
		    			hashMaklumatJLM.put("tarikhHantar", "");
		    			hashMaklumatJLM.put("jangkamasa", "");
		    			hashMaklumatJLM.put("tarikhJangkaTerima", "");

		    			beanMaklumatJLM.addElement(hashMaklumatJLM);
						this.context.put("BeanMaklumatJLM", beanMaklumatJLM);
						
	    			} else {
	    				
	    				beanMaklumatJLM = new Vector();    			
		    			Hashtable hashMaklumatJLM = new Hashtable();
		    			hashMaklumatJLM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJLM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJLM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJLM)); //JLM
		    			hashMaklumatJLM.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJLM.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJLM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJLM.addElement(hashMaklumatJLM);
						this.context.put("BeanMaklumatJLM", beanMaklumatJLM);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJLM = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatJLM = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatJLM",beanMaklumatJLM);
					
					if (beanMaklumatJLM.size() != 0){
						Hashtable hashMaklumatJLM = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatJLM.get("flagStatus");
						flagAktif = (String) hashMaklumatJLM.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN JLM
						beanMaklumatDokumenJLM = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenJLM = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenJLM",beanMaklumatDokumenJLM);
					}
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiJLM(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJLM = new Vector();    			
	    			Hashtable hashMaklumatJLM = new Hashtable();
	    			hashMaklumatJLM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJLM)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatJLM.put("agensi", logic.getAgensiByIdAgensi(idAgensiJLM)); //JLM
	    			hashMaklumatJLM.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJLM.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJLM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatJLM.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJLM.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJLM.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJLM.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJLM.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatJLM.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatJLM.addElement(hashMaklumatJLM);
					this.context.put("BeanMaklumatJLM", beanMaklumatJLM);
				}
			}
		}
		
		//ULASAN PHM
		if ("5".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT PHM
			if ("openPHM".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatPHM = new Vector();    			
		    			Hashtable hashMaklumatPHM = new Hashtable();
		    			hashMaklumatPHM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPHM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatPHM.put("agensi", logic.getAgensiByIdAgensi(idAgensiPHM)); //PHM
		    			hashMaklumatPHM.put("tarikhHantar", "");
		    			hashMaklumatPHM.put("jangkamasa", "");
		    			hashMaklumatPHM.put("tarikhJangkaTerima", "");

		    			beanMaklumatPHM.addElement(hashMaklumatPHM);
						this.context.put("BeanMaklumatPHM", beanMaklumatPHM);
						
	    			} else {
	    				
	    				beanMaklumatPHM = new Vector();    			
		    			Hashtable hashMaklumatPHM = new Hashtable();
		    			hashMaklumatPHM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPHM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatPHM.put("agensi", logic.getAgensiByIdAgensi(idAgensiPHM)); //PHM
		    			hashMaklumatPHM.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatPHM.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatPHM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatPHM.addElement(hashMaklumatPHM);
						this.context.put("BeanMaklumatPHM", beanMaklumatPHM);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatPHM = new Vector();    			
		    			Hashtable hashMaklumatPHM = new Hashtable();
		    			hashMaklumatPHM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPHM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatPHM.put("agensi", logic.getAgensiByIdAgensi(idAgensiPHM)); //PHM
		    			hashMaklumatPHM.put("tarikhHantar", "");
		    			hashMaklumatPHM.put("jangkamasa", "");
		    			hashMaklumatPHM.put("tarikhJangkaTerima", "");

		    			beanMaklumatPHM.addElement(hashMaklumatPHM);
						this.context.put("BeanMaklumatPHM", beanMaklumatPHM);
						
	    			} else {
	    				
	    				beanMaklumatPHM = new Vector();    			
		    			Hashtable hashMaklumatPHM = new Hashtable();
		    			hashMaklumatPHM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPHM)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatPHM.put("agensi", logic.getAgensiByIdAgensi(idAgensiPHM)); //PHM
		    			hashMaklumatPHM.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatPHM.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatPHM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatPHM.addElement(hashMaklumatPHM);
						this.context.put("BeanMaklumatPHM", beanMaklumatPHM);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatPHM = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatPHM = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatPHM",beanMaklumatPHM);
					
					if (beanMaklumatPHM.size() != 0){
						Hashtable hashMaklumatPHM = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatPHM.get("flagStatus");
						flagAktif = (String) hashMaklumatPHM.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN PHM
						beanMaklumatDokumenPHM = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenPHM = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenPHM",beanMaklumatDokumenPHM);
					}
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiPHM(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatPHM = new Vector();    			
	    			Hashtable hashMaklumatPHM = new Hashtable();
	    			hashMaklumatPHM.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPHM)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatPHM.put("agensi", logic.getAgensiByIdAgensi(idAgensiPHM)); //PHM
	    			hashMaklumatPHM.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatPHM.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatPHM.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatPHM.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatPHM.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatPHM.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatPHM.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatPHM.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatPHM.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatPHM.addElement(hashMaklumatPHM);
					this.context.put("BeanMaklumatPHM", beanMaklumatPHM);
				}
			}
		}
		
		//ULASAN JPS
		if ("6".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT JPS
			if ("openJPS".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJPS = new Vector();    			
		    			Hashtable hashMaklumatJPS = new Hashtable();
		    			hashMaklumatJPS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJPS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJPS.put("agensi", logic.getAgensiByIdAgensi(idAgensiJPS)); //JPS
		    			hashMaklumatJPS.put("tarikhHantar", "");
		    			hashMaklumatJPS.put("jangkamasa", "");
		    			hashMaklumatJPS.put("tarikhJangkaTerima", "");

		    			beanMaklumatJPS.addElement(hashMaklumatJPS);
						this.context.put("BeanMaklumatJPS", beanMaklumatJPS);
						
	    			} else {
	    				
	    				beanMaklumatJPS = new Vector();    			
		    			Hashtable hashMaklumatJPS = new Hashtable();
		    			hashMaklumatJPS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJPS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJPS.put("agensi", logic.getAgensiByIdAgensi(idAgensiJPS)); //JPS
		    			hashMaklumatJPS.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPS.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPS.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJPS.addElement(hashMaklumatJPS);
						this.context.put("BeanMaklumatJPS", beanMaklumatJPS);
						
	    			}
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");

	    			if ("".equals(submit)){
	    				
	    				beanMaklumatJPS = new Vector();    			
		    			Hashtable hashMaklumatJPS = new Hashtable();
		    			hashMaklumatJPS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJPS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJPS.put("agensi", logic.getAgensiByIdAgensi(idAgensiJPS)); //JPS
		    			hashMaklumatJPS.put("tarikhHantar", "");
		    			hashMaklumatJPS.put("jangkamasa", "");
		    			hashMaklumatJPS.put("tarikhJangkaTerima", "");

		    			beanMaklumatJPS.addElement(hashMaklumatJPS);
						this.context.put("BeanMaklumatJPS", beanMaklumatJPS);
						
	    			} else {
	    				
	    				beanMaklumatJPS = new Vector();    			
		    			Hashtable hashMaklumatJPS = new Hashtable();
		    			hashMaklumatJPS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJPS)); //KEMENTERIAN SUMBER ASLI
		    			hashMaklumatJPS.put("agensi", logic.getAgensiByIdAgensi(idAgensiJPS)); //JPS
		    			hashMaklumatJPS.put("tarikhHantar", getParam("txtTarikhHantar"));
		    			hashMaklumatJPS.put("jangkamasa", getParam("txtJangkaMasa"));
		    			hashMaklumatJPS.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

		    			beanMaklumatJPS.addElement(hashMaklumatJPS);
						this.context.put("BeanMaklumatJPS", beanMaklumatJPS);
						
	    			}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatJPS = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatJPS = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatJPS",beanMaklumatJPS);
					
					if (beanMaklumatJPS.size() != 0){
						Hashtable hashMaklumatJPS = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatJPS.get("flagStatus");
						flagAktif = (String) hashMaklumatJPS.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN JPS
						beanMaklumatDokumenJPS = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenJPS = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenJPS",beanMaklumatDokumenJPS);
					}
					
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiJPS(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatJPS = new Vector();    			
	    			Hashtable hashMaklumatJPS = new Hashtable();
	    			hashMaklumatJPS.put("kementerian", logic.getKementerianByIdKementerian(idKementerianJPS)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatJPS.put("agensi", logic.getAgensiByIdAgensi(idAgensiJPS)); //JPS
	    			hashMaklumatJPS.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatJPS.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatJPS.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatJPS.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatJPS.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatJPS.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatJPS.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatJPS.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatJPS.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatJPS.addElement(hashMaklumatJPS);
					this.context.put("BeanMaklumatJPS", beanMaklumatJPS);
				}
			}
		}
		
		//ULASAN PTG -AIN-
		if ("7".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT PTG
			if ("openPTG".equals(flagPopup)){
						
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
			    	this.context.put("inputTextClassPopup", "");
			    	this.context.put("disabled", "");
			    			
			    	if ("".equals(submit)){
			    				
			    		beanMaklumatPTG = new Vector();    			
			    		Hashtable hashMaklumatPTG = new Hashtable();
			    		hashMaklumatPTG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPTG)); //KEMENTERIAN SUMBER ASLI
			    		hashMaklumatPTG.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //PTG
			    		hashMaklumatPTG.put("tarikhHantar", "");
			    		hashMaklumatPTG.put("jangkamasa", "");
			    		hashMaklumatPTG.put("tarikhJangkaTerima", "");

			    		beanMaklumatPTG.addElement(hashMaklumatPTG);
						this.context.put("BeanMaklumatPTG", beanMaklumatPTG);
								
			    	} else {
			    				
			    		beanMaklumatPTG = new Vector();    			
					    Hashtable hashMaklumatPTG = new Hashtable();
					    hashMaklumatPTG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPTG)); //KEMENTERIAN SUMBER ASLI
					    hashMaklumatPTG.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //PTG
					    hashMaklumatPTG.put("tarikhHantar", getParam("txtTarikhHantar"));
					    hashMaklumatPTG.put("jangkamasa", getParam("txtJangkaMasa"));
					    hashMaklumatPTG.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
	
					    beanMaklumatPTG.addElement(hashMaklumatPTG);
						this.context.put("BeanMaklumatPTG", beanMaklumatPTG);
								
			    	 }
				} else if ("newUlangan".equals(modePopup)){
					this.context.put("readonlyPopup", "");
			    	this.context.put("inputTextClassPopup", "");
			    	this.context.put("disabled", "");

			    	if ("".equals(submit)){
			    				
			    		beanMaklumatPTG = new Vector();    			
					    Hashtable hashMaklumatPTG = new Hashtable();
					    hashMaklumatPTG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPTG)); //KEMENTERIAN SUMBER ASLI
					    hashMaklumatPTG.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //PTG
					    hashMaklumatPTG.put("tarikhHantar", "");
					    hashMaklumatPTG.put("jangkamasa", "");
					    hashMaklumatPTG.put("tarikhJangkaTerima", "");
	
					    beanMaklumatPTG.addElement(hashMaklumatPTG);
						this.context.put("BeanMaklumatPTG", beanMaklumatPTG);
								
			    	} else {
			    				
			    		beanMaklumatPTG = new Vector();    			
				    	Hashtable hashMaklumatPTG = new Hashtable();
				    	hashMaklumatPTG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPTG)); //KEMENTERIAN SUMBER ASLI
				    	hashMaklumatPTG.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //PTG
				    	hashMaklumatPTG.put("tarikhHantar", getParam("txtTarikhHantar"));
				    	hashMaklumatPTG.put("jangkamasa", getParam("txtJangkaMasa"));
				    	hashMaklumatPTG.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));

				    	beanMaklumatPTG.addElement(hashMaklumatPTG);
						this.context.put("BeanMaklumatPTG", beanMaklumatPTG);
								
			    	}					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatPTG = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatPTG = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatPTG",beanMaklumatPTG);
					
					if (beanMaklumatPTG.size() != 0){
						Hashtable hashMaklumatPTG = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						flagStatus = (String) hashMaklumatPTG.get("flagStatus");
						flagAktif = (String) hashMaklumatPTG.get("flagAktif");
					}
					
					if ("2".equals(flagStatus)){
						// MAKLUMAT DOKUMEN PTG
						beanMaklumatDokumenPTG = new Vector();
						logic.setMaklumatDokumen(idUlasanTeknikal);
						beanMaklumatDokumenPTG = logic.getBeanMaklumatDokumen();
						this.context.put("BeanMaklumatDokumenPTG",beanMaklumatDokumenPTG);
					}
					
					// SENARAI NOTIFIKASI
					senaraiNotifikasi = new Vector();
					logic.setSenaraiNotifikasiPTG(idUlasanTeknikal);
					senaraiNotifikasi = logic.getListNotifikasi();
					this.context.put("SenaraiNotifikasiEmel", senaraiNotifikasi);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatPTG = new Vector();    			
	    			Hashtable hashMaklumatPTG = new Hashtable();
	    			hashMaklumatPTG.put("kementerian", logic.getKementerianByIdKementerian(idKementerianPTG)); //KEMENTERIAN SUMBER ASLI
	    			hashMaklumatPTG.put("pejabat", logic.getPejabatByIdPejabat(logic.getIdPejabatPTGByNegeri(logic.getIdNegeriPerairan(idPermohonan)))); //PTG
	    			hashMaklumatPTG.put("tarikhHantar", getParam("txtTarikhHantar"));
	    			hashMaklumatPTG.put("jangkamasa", getParam("txtJangkaMasa"));
	    			hashMaklumatPTG.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));	    			
	    			hashMaklumatPTG.put("tarikhTerima", getParam("txtTarikhTerima"));
	    			hashMaklumatPTG.put("tarikhSurat", getParam("txtTarikhSurat"));
	    			hashMaklumatPTG.put("noRujukan", getParam("txtNoRujukanSurat"));
	    			hashMaklumatPTG.put("ulasan", getParam("txtUlasan"));
	    			hashMaklumatPTG.put("namaPengulas", getParam("txtNamaPengulas"));
	    			hashMaklumatPTG.put("noTelPengulas", getParam("txtNoTelPengulas"));
	    			beanMaklumatPTG.addElement(hashMaklumatPTG);
					this.context.put("BeanMaklumatPTG", beanMaklumatPTG);
				}
			}
		}
		
		//MAKLUMAT PERTINDIHAN
		if ("8".equals(selectedTabUpper)){
			//OPEN POPUP DETAIL MAKLUMAT PERTINDIHAN
			if ("openPertindihanKoordinat".equals(flagPopup)){
				
				if ("new".equals(modePopup)){
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatPertindihan = new Vector();    			
	    			Hashtable hashMaklumatPertindihan = new Hashtable();
	    			hashMaklumatPertindihan.put("txtNoFailTindih", "");
	    			hashMaklumatPertindihan.put("txtNamaSyarikat", "");
	    			hashMaklumatPertindihan.put("socJenisTindih", "");
	    			hashMaklumatPertindihan.put("socStatusTindih", "");
	    			hashMaklumatPertindihan.put("txtLain", "");

	    			beanMaklumatPertindihan.addElement(hashMaklumatPertindihan);
					this.context.put("BeanMaklumatPertindihan", beanMaklumatPertindihan);
					
				} else if ("view".equals(modePopup)){
					this.context.put("readonlyPopup", "readonly");
	    			this.context.put("inputTextClassPopup", "disabled");
	    			this.context.put("disabled", "disabled");
					
	    			beanMaklumatPertindihan = new Vector();
					logic.setMaklumatPertindihan(idPertindihan);
					beanMaklumatPertindihan = logic.getBeanMaklumatPertindihan();
					this.context.put("BeanMaklumatPertindihan",beanMaklumatPertindihan);
					
				} else if ("update".equals(modePopup)){					
					this.context.put("readonlyPopup", "");
	    			this.context.put("inputTextClassPopup", "");
	    			this.context.put("disabled", "");
	    			
	    			beanMaklumatPertindihan = new Vector();
					logic.setMaklumatPertindihan(idPertindihan);
					beanMaklumatPertindihan = logic.getBeanMaklumatPertindihan();
					this.context.put("BeanMaklumatPertindihan",beanMaklumatPertindihan);
				}
			}
		}
		
		//DOKUMEN JUPEM
		senaraiJUPEM = new Vector();
		logic.setSenaraiJUPEM(idPermohonan);
		senaraiJUPEM = logic.getListJUPEM();
		this.context.put("SenaraiJUPEM", senaraiJUPEM);
		
		//DOKUMEN JAS
		senaraiJAS = new Vector();
		logic.setSenaraiJAS(idPermohonan);
		senaraiJAS = logic.getListJAS();
		this.context.put("SenaraiJAS", senaraiJAS);
		
		//DOKUMEN JMG
		senaraiJMG = new Vector();
		logic.setSenaraiJMG(idPermohonan);
		senaraiJMG = logic.getListJMG();
		this.context.put("SenaraiJMG", senaraiJMG);
		
		//DOKUMEN JP
		senaraiJP = new Vector();
		logic.setSenaraiJP(idPermohonan);
		senaraiJP = logic.getListJP();
		this.context.put("SenaraiJP", senaraiJP);
		
		//DOKUMEN JLM
		senaraiJLM = new Vector();
		logic.setSenaraiJLM(idPermohonan);
		senaraiJLM = logic.getListJLM();
		this.context.put("SenaraiJLM", senaraiJLM);
		
		//DOKUMEN PHM
		senaraiPHM = new Vector();
		logic.setSenaraiPHM(idPermohonan);
		senaraiPHM = logic.getListPHM();
		this.context.put("SenaraiPHM", senaraiPHM);
		
		//DOKUMEN JPS
		senaraiJPS = new Vector();
		logic.setSenaraiJPS(idPermohonan);
		senaraiJPS = logic.getListJPS();
		this.context.put("SenaraiJPS", senaraiJPS);
		
		//DOKUMEN PTG -AIN-
		senaraiPTG = new Vector();
		logic.setSenaraiPTG(idPermohonan);
		senaraiPTG = logic.getListPTG();
		this.context.put("SenaraiPTG", senaraiPTG);
		
		//SENARAI PERTINDIHAN
		senaraiPertindihan = new Vector();
		logic.setSenaraiPertindihan(idPermohonan);
		senaraiPertindihan = logic.getListPertindihan();
		this.context.put("SenaraiPertindihan", senaraiPertindihan);
		
		if ("selesaiPermohonan".equals(step)){
        	vm = "app/php2/frmSelesaiPermohonan.jsp";
        }
		if ("batalPermohonan".equals(step)){
        	vm = "app/php2/frmBatalPermohonan.jsp";
        }

		// SET DEFAULT PARAM
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("modePopup", modePopup);
		this.context.put("flagPopup", flagPopup);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idStatus", idStatus);
		this.context.put("idPertindihan", idPertindihan);
		
		this.context.put("flagStatus", flagStatus);
        this.context.put("flagAktif", flagAktif);
        this.context.put("flagNotifikasi", flagNotifikasi);
		return vm;
	}

	public void setupPage(HttpSession session, String action, Vector list) {

		try {

			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");

			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null
					|| this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10
						: getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage");
			}

			if ("getNext".equals(action)) {
				page++;
			} else if ("getPrevious".equals(action)) {
				page--;
			} else if ("getPage".equals(action)) {
				page = getParamAsInteger("value");
			} else if ("doChangeItemPerPage".equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage");
			}

			Paging paging = new Paging(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1; // reset page number
			this.context.put("SenaraiKJPKJT", paging.getPage(page));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}

	// UPLOAD FILE
	private void uploadFiles(String idUlasanTeknikal, String idPermohonan, String flagLampiran, HttpSession session)
			throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, session, idUlasanTeknikal, idPermohonan, flagLampiran);
				}
			}
		}
	}

	private void saveData(FileItem item, HttpSession session,
			String idUlasanTeknikal, String idPermohonan, String flagLampiran) throws Exception {
		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String sql = "";
		SQLRenderer r = null;
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumenUpload = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("insert into TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_ULASANTEKNIKAL,FLAG_DOKUMEN,ID_PERMOHONAN) "
							+ "values(?,?,?,?,SYSDATE,?,?,?,?,?,?)");
			ps.setLong(1, idDokumenUpload);
			ps.setString(2, getParam("txtNamaImej"));
			ps.setString(3, getParam("txtCatatan"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idUlasanTeknikal);
			ps.setString(9, flagLampiran);
			ps.setString(10, idPermohonan);
			ps.executeUpdate();
			
			con.commit();
			
			myLogger.info("keluarlaaaaaa : " +sql);

		} finally {
			if (db != null)
				db.close();
		}
		
		this.context.put("completed", true);
	}
}

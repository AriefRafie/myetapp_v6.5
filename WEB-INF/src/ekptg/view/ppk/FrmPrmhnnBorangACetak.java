package ekptg.view.ppk;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.ppk.FrmPrmhnnSek8SecaraOnlineData;

public class FrmPrmhnnBorangACetak extends VTemplate{
	
	FrmPrmhnnSek8SecaraOnlineData logiconline = new FrmPrmhnnSek8SecaraOnlineData();
	
	public Template doTemplate() throws Exception {
	HttpSession session = this.request.getSession();
	String vm = "app/ppk/frmBorangAMaklumatPemohonCetak.jsp";
	String submit = getParam("command");
	int eventStatus = 0;
	int Submission = 0;
	int idAlert = 0;
	
	System.out.println("FrmPrmhnnBorangACetak submit--->>>"+submit);
	
	if("cetak_form".equals(submit)){
		eventStatus = 1;
		this.context.put("EventStatus", eventStatus);
		this.context.put("command", "cetak_form");
		
		Submission = 1;
		this.context.put("submission", Submission);
		
		idAlert = 0;
		this.context.put("IdAlert", idAlert);
		
		String Waris = getParam("sorWaris");
		String OB = getParam("sorOB");
		this.context.put("sorWaris", Waris);
		this.context.put("sorOB", OB);
		
		String valueSor = getParam("sorAdaHTA");
		this.context.put("sorAdaHTA", valueSor);
		
		String NoKPLama = "";
		String NoKPLain = "";
		String NoKPBaru = getParam("icPemohon");
		check_data_pemohon(session,NoKPBaru,NoKPLama,NoKPLain);
		Vector detailPemohon = logiconline.getDetailsPemohon();
		this.context.put("DetailPemohon", detailPemohon);
		
		vm = "app/ppk/frmBorangAMaklumatPemohonCetak.jsp";
		}
	else if ("cetak_surat".equals(submit)){
	
		this.context.put("command","cetak_surat");
		
		String idPermohonan = getParam("idPermohonan");
		int idnegeri = Integer.parseInt(getParam("idNegeri"));
		int iddaerah = Integer.parseInt(getParam("idDaerah"));
		
		//logiconline.insertDaerahMohon(idnegeri,iddaerah,idPermohonan);
		
		int idDaerah = Integer.parseInt(getParam("idDaerah"));
    	Vector getAddressPpk = logiconline.getAddress(idDaerah);
        this.context.put("selectedPpkAddress",getAddressPpk);
        
		vm = "app/ppk/frmBorangAMaklumatPemohonCetak.jsp";
	}
	Template template = this.engine.getTemplate(vm);
	return template;
	}
	private void check_data_pemohon(HttpSession session, String kpBaru, String kpLama, String kpLain) throws Exception {
		logiconline.semakDetailPemohon(kpBaru,kpLama,kpLain);
	}
}

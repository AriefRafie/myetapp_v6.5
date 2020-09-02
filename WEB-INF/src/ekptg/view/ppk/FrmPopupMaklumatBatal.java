package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;


public class FrmPopupMaklumatBatal extends AjaxBasedModule {
private static final long serialVersionUID = 1L;
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	static Logger myLog = Logger.getLogger(FrmPopupMaklumatBatal.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String noFail = getParam("noFail");
		String namaS = getParam("namaS");
		String status = getParam("status");
		String catatan = getParam("catatan");
		String nofailawal = getParam("nofailawal");
		String namapemohonawal = getParam("namapemohonawal");
		String pejabatawal = getParam("pejabatawal");
		
		FrmPrmhnnSek8KeputusanPermohonanInternalData
		.setMaklumatMahkamahM();
		Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
				.getMaklumatMahkamahM();
		this.context
				.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);
		
		vm = "app/ppk/frmPopupMaklumatBatal.jsp";	
		
		this.context.put("noFail", noFail);
		this.context.put("status", status);
		this.context.put("namaS", namaS);
		this.context.put("catatan", catatan);
		this.context.put("nofailawal", nofailawal);
		this.context.put("namapemohonawal", namapemohonawal);
		this.context.put("pejabatawal", pejabatawal);
	
		return vm;
		
	}
	
}

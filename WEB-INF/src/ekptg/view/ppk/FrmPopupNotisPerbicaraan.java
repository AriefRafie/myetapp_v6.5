package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;


public class FrmPopupNotisPerbicaraan extends AjaxBasedModule {
private static final long serialVersionUID = 1L;
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	static Logger myLog = Logger.getLogger(FrmPopupNotisPerbicaraan.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String tarikh_notis = getParam("tarikh_notis");
		String tarikh_bicara = getParam("tarikh_bicara");
		String masa_bicara = getParam("masa_bicara");
		String tempat_bicara = getParam("tempat_bicara");
		String alamat1 = getParam("alamat1");
		String alamat2 = getParam("alamat2");
		String alamat3 = getParam("alamat3");
		String poskod = getParam("poskod");
		String bandar = getParam("bandar");
		String nama_negeri = getParam("nama_negeri");
		
		String signedData = "";
		
		String seksyen = getParam("seksyen");
		String noFail = getParam("noFail");
		String idPerbicaraan = getParam("idPerbicaraan");
		String idFail = getParam("idFail");
		String icSimati = getParam("icSimati");
		String id_Permohonan = getParam("id_Permohonan");
		
		vm = "app/ppk/frmPopupNotisPerbicaraan.jsp";	
		
		this.context.put("tarikh_notis", tarikh_notis);
		this.context.put("tarikh_bicara", tarikh_bicara);
		this.context.put("masa_bicara", masa_bicara);
		this.context.put("tempat_bicara", tempat_bicara);
		this.context.put("alamat1", alamat1);
		this.context.put("alamat2", alamat2);
		this.context.put("alamat3", alamat3);
		this.context.put("poskod", poskod);
		this.context.put("bandar", bandar);
		this.context.put("nama_negeri", nama_negeri);
		
		signedData = logic.getSignedData(idPerbicaraan);
		this.context.put("signedData", signedData);
		
		this.context.put("seksyen", seksyen);
		this.context.put("noFail", noFail);
		this.context.put("idPerbicaraan", idPerbicaraan);
		this.context.put("idFail", idFail);
		this.context.put("icSimati", icSimati);
		this.context.put("id_Permohonan", id_Permohonan);
		
	
		return vm;
		
	}
	
}

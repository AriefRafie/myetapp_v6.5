package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import ekptg.model.ppk.FrmPrmhnnStatusPengunaOnlineData;
import ekptg.model.ppk.FrmSenaraiFailKeputusanPermohonanInternalData;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;

public class FrmMaklumatPindahMT extends AjaxBasedModule {
private static final long serialVersionUID = 1L;
	
	static Logger myLog = Logger.getLogger(FrmMaklumatPindahMT.class);
	
	@Override
	public String doTemplate2() throws Exception {
		
		Vector senaraiMaklumatPindahMT = new Vector();
		Vector flag5juta =  new Vector();
		String vm = "";
		String idNegeri = "1";
		
		FrmPrmhnnSek8KeputusanPermohonanInternalData.checkFlag5Juta(id);
		flag5juta = FrmPrmhnnSek8KeputusanPermohonanInternalData.getFlag5Juta();
		this.context.put("flag5juta", flag5juta);
		
		senaraiMaklumatPindahMT = FrmPrmhnnStatusPengunaOnlineData.getMaklumatPindahMT("", getParam("idPermohonan"),"no");
		this.context.put("senaraiMaklumat", senaraiMaklumatPindahMT);
		
		FrmSenaraiFailKeputusanPermohonanInternalData.setListNegeri();
		Vector listNegeri = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListNegeri();
		this.context.put("ListNegeri", listNegeri);
		
		FrmSenaraiFailKeputusanPermohonanInternalData.setListDaerahall();
		Vector listDaerah = FrmSenaraiFailKeputusanPermohonanInternalData
				.getListDaerahall();
		this.context.put("ListDaerah", listDaerah);
		myLog.info("DAERAH "+listDaerah);
		
		FrmPrmhnnSek8KeputusanPermohonanInternalData.setMaklumatMahkamahM();
		Vector listMaklumatMahkamahM = FrmPrmhnnSek8KeputusanPermohonanInternalData
				.getMaklumatMahkamahM();
		this.context
				.put("listMaklumatMahkamahJ", listMaklumatMahkamahM);
				
		vm = "app/ppk/frmMaklumatPindahMT.jsp";		
	
		return vm;
		
	}
	
}

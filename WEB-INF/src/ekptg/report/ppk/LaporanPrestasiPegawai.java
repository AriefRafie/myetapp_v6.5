/**
 * 
 */
package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

/**
 * 
 *
 */
public class LaporanPrestasiPegawai extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public LaporanPrestasiPegawai() {
		super.setReportName("LaporanPrestasiPegawai");
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
		parameters.put("ID_UNIT",parameters.get("idUnit"));
		parameters.put("UNIT",logic.getUnit(parameters.get("idUnit").toString()));
		parameters.put("NEGERI",logic.getNegeri(parameters.get("idNegeri").toString()));
		parameters.put("TAHUN",parameters.get("tahun").toString());
	}
}

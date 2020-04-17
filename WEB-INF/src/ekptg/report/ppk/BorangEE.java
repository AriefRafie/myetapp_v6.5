/**
 * 
 */
package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

/**
 * 
 *
 */
public class BorangEE extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangEE (){
		super.setReportName("BORANGEE") ;
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		if (parameters.get("idfail") != null){
			idfail = parameters.get("idfail").toString();
		}
		
		String nofail = logic.getNoFailByIdFail(idfail);

		Vector list = new Vector();
		list.clear();
		
		String idPegawai = logic.getIdUnitPSKByIdFail(idfail);
		
		logic.setMaklumatPegawai(idPegawai, "B");
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",h.get("nama").toString());
			parameters.put("jawatan",h.get("jawatan").toString());
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(nofail, "B"));
		
		String flagVersion = (String)parameters.get("flagVersion");
		doVersioning("BorangEE",idfail,nofail,flagVersion);
	}
}

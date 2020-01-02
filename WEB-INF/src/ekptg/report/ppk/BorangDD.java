package ekptg.report.ppk;

import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;



public class BorangDD extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	static Logger myLogger = Logger.getLogger(FrmPopupPilihPegawaiReportData.class);
	
	public BorangDD (){
		super.setReportName("BorangDD") ;
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
	
		Vector list = new Vector();
		list.clear();
		//myLogger.info("ID PEGAWAI :"+parameters.get("idPegawai").toString());
	/*	
    	logic.setMaklumatPegawai(parameters.get("idPegawai").toString(),parameters.get("flagReport").toString());
		logic.setMaklumatPegawai("",parameters.get("flagReport").toString());
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",h.get("nama").toString());
			parameters.put("jawatan",h.get("jawatan").toString());
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(parameters.get("nofail").toString(),
				parameters.get("flagReport").toString()));
	*/
	}

}

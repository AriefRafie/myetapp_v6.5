package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.faraid.FrmFaraidData;
import ekptg.report.EkptgReportServlet;

public class SuratAkuanFaraid  extends EkptgReportServlet{
	static Logger myLogger = Logger.getLogger(SuratAkuanFaraid.class);
	FrmFaraidData logic = new FrmFaraidData();
	
	public SuratAkuanFaraid() {
		super.setReportName("SuratAkuanFaraid");
		super.setFolderName("ppk");
	}


	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Vector list = new Vector();
		list.clear();

		logic.setMaklumatSimati((parameters.get("idPermohonan").toString()));
		list = logic.getBeanMaklumatSimati();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaSimati",h.get("namaSimati"));
			myLogger.info("Nama Simati:"+h.get("namaSimati"));
			if (h.get("noKPLama") != null){
				parameters.put("noKP", h.get("noKPBaru") == null?"": h.get("noKPBaru" + "(" +  h.get("noKPLama") + ")"));
			}
			else{
				parameters.put("noKP", h.get("noKPBaru"));
			}
			parameters.put("noKP", h.get("noKPBaru"));
			parameters.put("tarikhMati",h.get("tarikhMati") == null ?"":h.get("tarikhMati"));
			parameters.put("idSimati",h.get("idSimati"));
			parameters.put("bhgnBaitulMal",parameters.get("bhgnBaitulMal")== null?"":parameters.get("bhgnBaitulMal"));
			parameters.put("noFail",h.get("noFail"));
		}

		
	}

}

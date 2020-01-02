package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class BorangT2 extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangT2 (){
		super.setReportName("BorangT2") ;
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
		
		logic.getPemohonTerdahulu(idfail);
		Vector pemohon = new Vector(); 
		pemohon = logic.getBeanPemohonTerdahulu();
		
		Hashtable h1 = new Hashtable();
		if(pemohon.size() != 0){
			
			h1 = (Hashtable)pemohon.get(0);
			parameters.put("namaPemohon",h1.get("namaPemohon"));
			parameters.put("alamatPemohon",h1.get("alamatPemohon"));
			
			if("".equals(h1.get("kpBaru").toString())){
				
				parameters.put("kpPemohon", "");
				
			}
			else{
				parameters.put("kpPemohon", h1.get("kpPemohon"));
			}
			
			
		}
		
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
		doVersioning("BorangT2",idfail,nofail,flagVersion);
	}
}

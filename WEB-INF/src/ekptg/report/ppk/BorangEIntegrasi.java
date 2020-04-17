package ekptg.report.ppk;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;



public class BorangEIntegrasi extends EkptgReportServlet{
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public BorangEIntegrasi (){
		super.setReportName("BorangE_LHDNLatest") ;
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String idfail = "";
		if (parameters.get("idfail") != null){
			idfail = (String)parameters.get("ID_FAIL");
		}
		
		String idSimati = "";
		if (parameters.get("idSimati") != null){
			idSimati = (String)parameters.get("idSimati");
		}
		
		String nofail = logic.getNoFailByIdFail(idfail);
		
		Vector list = new Vector();
		Vector listPentadbir = new Vector();
		Vector flagBorangF = new Vector();
		list.clear();
		listPentadbir.clear();
		flagBorangF.clear();
		
		String idPegawai = logic.getIdUnitPSKByIdFail(idfail);
		
		logic.setMaklumatPegawai(idPegawai, "B");
		list = logic.getBeanMaklumatPegawai();
		if (list.size() != 0){
			Hashtable h = (Hashtable) list.get(0);
			parameters.put("namaPegawai",(String)h.get("nama"));
			parameters.put("jawatan",(String)h.get("jawatan"));
		}

		parameters.put("daerahMohon",logic.getDaerahMohon(nofail, "B"));

		logic.setSenaraiOBPentadbir(idfail);
		listPentadbir = logic.getBeanSenaraiOBPentadbir();
		String namaPentadbir = "";
		if (listPentadbir.size() != 0){
			for(int i = 0; i < listPentadbir.size(); i++){
				Hashtable h = (Hashtable)listPentadbir.get(i);
				namaPentadbir = (String)h.get("maklumatPentadbirUtkBorangE");
				parameters.put("namaPentadbir", namaPentadbir);
			}
		}else{
			parameters.put("namaPentadbir", namaPentadbir);
		}
		
		
		parameters.put("flagBorangF", logic.getFlagBorangF(idfail));
		
		
		String flagVersion = (String)parameters.get("flagVersion");
		System.out.println("id fail" +idfail);
		doVersioning("BorangE_LHDN",idfail,nofail,flagVersion);
		
	}
}

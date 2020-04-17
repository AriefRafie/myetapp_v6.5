package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiByNegeriTempoh extends EkptgReportServlet {
	
	static Logger mylog = Logger.getLogger(LaporanPrestasiByNegeriTempoh.class);
	
	public LaporanPrestasiByNegeriTempoh() {
		super.setReportName("htpLaporanPrestasiNegeriTempoh");
		//super.setReportName("htpLaporanPrestasiNegeri");
		super.setFolderName("ppk");
	
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		String bulanMula =(String)parameters.get("bulan");
		String bulanTamat =(String)parameters.get("bulanhingga");
		String negeri =(String)parameters.get("negeri");
		String tahun =(String)parameters.get("tahun");
		ekptg.report.Utils utils = new ekptg.report.Utils(); 
		parameters.put("BULANSEBELUM",utils.getBulanSebelum(Integer.parseInt(bulanMula))+"/"+utils.getTahunSebelum(Integer.parseInt(bulanMula),Integer.parseInt(tahun)));
		parameters.put("BULANMULA",utils.getBulanNum(Integer.parseInt(bulanMula))+"/"+tahun);
		parameters.put("BULANTAMAT",utils.getBulanNum(Integer.parseInt(bulanTamat))+"/"+tahun  );

		//parameters.put("LAPORAN","LAPORAN PRESTASI NEGERI SEKSYEN PEMBAHAGIAN PUSAKA");
		parameters.put("LAPORANRANGE","BAGI BULAN "+utils.getBulan(Integer.parseInt(bulanMula))+" HINGGA "+utils.getBulan(Integer.parseInt(bulanTamat)) + " "+tahun  );
		parameters.put("ID_NEGERI",Integer.parseInt(negeri));
		
		
		

	}

}
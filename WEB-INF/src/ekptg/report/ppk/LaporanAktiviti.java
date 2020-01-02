package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanAktiviti extends EkptgReportServlet{
	
	public LaporanAktiviti() {
		super.setReportName("LaporanAktiviti");
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		ekptg.report.Utils utils = new ekptg.report.Utils(); 
		String bulan =(String)parameters.get("bulan");
		String userid =(String)parameters.get("userid");
		String tahun =(String)parameters.get("tahun");
		parameters.put("userid",userid);
		parameters.put("LAPORAN","BAGI BULAN "+getBulan(Integer.parseInt(bulan))+" "+tahun  );
		parameters.put("bulan", bulan);
		parameters.put("tahun",tahun);
		
	}
	
	public String getBulan(int i){
		String m="";
		String[]names = {"JANUARI", "FBBRUARI", "MAC","APRIL", "MEI", "JUN","JULAI", "OGOS", "SEPTEMBER","OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		return m;
	}

}

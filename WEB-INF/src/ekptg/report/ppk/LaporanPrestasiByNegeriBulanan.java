package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiByNegeriBulanan extends EkptgReportServlet {
	
	public LaporanPrestasiByNegeriBulanan() {
		super.setReportName("htpLaporanPrestasiNegeriBulanan");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		String bulanMula =(String)parameters.get("bulan");
		String negeri =(String)parameters.get("negeri");
		String tahun =(String)parameters.get("tahun");
		ekptg.report.Utils utils = new ekptg.report.Utils(); 
		parameters.put("BULANSEBELUM",this.getBulanSebelum(Integer.parseInt(bulanMula))+"/"+getTahunSebelum(Integer.parseInt(bulanMula),Integer.parseInt(tahun)));
		parameters.put("BULANTAHUN",utils.getBulanNum(Integer.parseInt(bulanMula))+"/"+tahun);
		parameters.put("LAPORAN","BAGI BULAN "+getBulan(Integer.parseInt(bulanMula))+" "+tahun  );
		parameters.put("ID_NEGERI",Integer.parseInt(negeri));
		
		
	}
	
	public String getBulanSebelum(int i){
		String m="";
		String[]names = {"12", "01", "02","03", "04", "05","06", "07", "08","09", "10", "11"};
		m = names[i-1];
		return m;
	}
	
	public String getTahunSebelum(int bulan,int i){
		String m="";
		if(bulan==1) 
			m = ""+(i-1);
		else
			m = ""+i;
		return m;
	}
	
	public String getBulan(int i){
		String m="";
		String[]names = {"JANUARI", "FBBRUARI", "MAC","APRIL", "MEI", "JUN","JULAI", "OGOS", "SEPTEMBER","OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		return m;
	}
}
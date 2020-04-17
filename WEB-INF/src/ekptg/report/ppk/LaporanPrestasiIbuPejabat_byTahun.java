package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiIbuPejabat_byTahun extends EkptgReportServlet {
	
	public LaporanPrestasiIbuPejabat_byTahun() {
		super.setReportName("LaporanPrestasiIbuPejabat_byTahun");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String year = parameters.get("tahun").toString();
		String mCurr = "1";
		parameters.put("TAJUK",year);
		parameters.put("BULAN_SBLM",this.getBulanSebelum(Integer.parseInt(mCurr))+"/"+getTahunSebelum(Integer.parseInt(mCurr),Integer.parseInt(year)));
		parameters.put("TAHUN",year);
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
	
	public String getBulanName(int i){
		String m="";
		String[]names = {"JANUARI", "FEBRUARI", "MAC", "APRIL", "MEI", "JUN", "JULAI", "OGOS", "SEPTEMBER", "OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		
		return m;
	}
}
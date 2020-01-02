package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiIbuPejabat_byBulan extends EkptgReportServlet {
	
	public LaporanPrestasiIbuPejabat_byBulan() {
		super.setReportName("LaporanPrestasiIbuPejabat_byBulan");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		String mCurr = parameters.get("bulan").toString();
		String year = parameters.get("tahun").toString();
		
		parameters.put("TAJUK",getBulanName(Integer.parseInt(mCurr)) +" "+ year);
		parameters.put("BULAN_SKRG",getBulanNum(Integer.parseInt(mCurr)));
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
	public String getBulanNum(int i){
		String m2="";
		String[]num = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		m2 = num[i-1];
		return m2;
	}
}
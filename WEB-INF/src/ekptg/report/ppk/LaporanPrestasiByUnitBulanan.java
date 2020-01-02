package ekptg.report.ppk;

import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiByUnitBulanan extends EkptgReportServlet {
	
	public LaporanPrestasiByUnitBulanan() {
		super.setReportName("LaporanPrestasiByUnitBulanan");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Vector list = new Vector();
		list.clear();
		
		String mfrom =(String)parameters.get("BULAN");
		String tahun =(String)parameters.get("TAHUN");
		String idnegeri =(String)parameters.get("ID_NEGERI");
		String idunit =(String)parameters.get("ID_UNIT");
		
		parameters.put("BULAN_NAME",getBulanName(Integer.parseInt(mfrom)));
		parameters.put("BULAN",getBulanNum(Integer.parseInt(mfrom)));
		parameters.put("BULAN_SEBELUM",getBulanSebelum(Integer.parseInt(mfrom)));
		parameters.put("TAHUN",(String)parameters.get("TAHUN"));
		parameters.put("ID_NEGERI",(String)parameters.get("ID_NEGERI"));
		parameters.put("ID_UNIT",(String)parameters.get("ID_UNIT"));
		parameters.put("TAHUN_SEBELUM",getTahunSebelum(Integer.parseInt(mfrom),Integer.parseInt(tahun)));

		
		
		
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

package ekptg.report.ppk;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;

public class LaporanPrestasiByUnitTempoh extends EkptgReportServlet {

FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public LaporanPrestasiByUnitTempoh() {
		super.setReportName("LaporanPrestasiByUnitTempoh");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Vector list = new Vector();
		list.clear();
		
		String tahun_dari =(String)parameters.get("TAHUN_DARI");
		String tahun_hingga =(String)parameters.get("TAHUN_HINGGA");
		String bulan_dari =(String)parameters.get("BULAN_DARI");
		String bulan_hingga =(String)parameters.get("BULAN_HINGGA");
		
		DateFormat dateFormat = new SimpleDateFormat("MM");
	    Date date = new Date();
	    String currentDate = dateFormat.format(date);
	    
	    parameters.put("ID_NEGERI",(String)parameters.get("ID_NEGERI"));
		parameters.put("ID_UNIT",(String)parameters.get("ID_UNIT"));
		parameters.put("TAHUN_DARI",tahun_dari);
		parameters.put("TAHUN_HINGGA",tahun_hingga);
		parameters.put("BULAN_DARI",getBulanNum(Integer.parseInt(bulan_dari)));
		parameters.put("BULAN_HINGGA",getBulanNum(Integer.parseInt(bulan_hingga)));
		parameters.put("BULAN_DARI_NAME",getBulanName(Integer.parseInt(bulan_dari)));
		parameters.put("BULAN_HINGGA_NAME",getBulanName(Integer.parseInt(bulan_hingga)));
		parameters.put("BULAN_SEBELUM",getTahunSebelum(Integer.parseInt(bulan_dari),Integer.parseInt(tahun_dari)));
	
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

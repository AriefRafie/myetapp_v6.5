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

public class LaporanPrestasiByUnitTahun extends EkptgReportServlet {

	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public LaporanPrestasiByUnitTahun() {
		super.setReportName("LaporanPrestasiByUnitTahun");
		super.setFolderName("ppk");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		
		Vector list = new Vector();
		list.clear();
		
		String tahun =(String)parameters.get("TAHUN");
		String idnegeri =(String)parameters.get("NEGERI");
		String idunit =(String)parameters.get("UNIT");
		String mCurr = "1";
		
		DateFormat dateFormat = new SimpleDateFormat("MM");
	    Date date = new Date();
	    String currentDate = dateFormat.format(date);
	    
		parameters.put("TAHUN",tahun);
		parameters.put("ID_NEGERI",idnegeri);
		parameters.put("ID_UNIT",idunit);
		parameters.put("BULAN_SBLM",this.getBulanSebelum(Integer.parseInt(mCurr))+"/"+getTahunSebelum(Integer.parseInt(mCurr),Integer.parseInt(tahun)));
	
		
	
	
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

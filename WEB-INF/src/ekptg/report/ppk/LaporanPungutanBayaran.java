/**
 * 
 */
package ekptg.report.ppk;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.report.EkptgReportServlet;


public class LaporanPungutanBayaran extends EkptgReportServlet {
	
	FrmPopupPilihPegawaiReportData logic = new FrmPopupPilihPegawaiReportData();
	
	public LaporanPungutanBayaran() {
		super.setReportName("LaporanPungutanBayaran");
		super.setFolderName("ppk");
	}

	@Override
	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map parameters)
			throws Exception {
		// TODO Auto-generated method stub
		
		parameters.put("TAJUK",getBulanPenuh(Integer.parseInt(parameters.get("bulan").toString())) +" "+ parameters.get("tahun").toString());
		parameters.put("bulan",getBulan(Integer.parseInt(parameters.get("bulan").toString())));
		parameters.put("tahun",parameters.get("tahun").toString());
		parameters.put("idnegeri",parameters.get("idnegeri").toString());
		parameters.put("idjkptg",parameters.get("idjkptg").toString());	
		
		
	}
	public String getBulan(int i){
		String m="";
		String[]names = {"01", "02","03", "04", "05","06", "07", "08","09", "10", "11","12"};
		m = names[i-1];
		return m;
	}
	
	public String getTahun(int bulan,int i){
		String m="";
		if(bulan==1) 
			m = ""+(i-1);
		else
			m = ""+i;
		return m;
	}
	
	public String getBulanPenuh(int i){
		String m="";
		String[]names = {"JANUARI", "FBBRUARI", "MAC","APRIL", "MEI", "JUN","JULAI", "OGOS", "SEPTEMBER","OKTOBER", "NOVEMBER", "DISEMBER"};
		m = names[i-1];
		return m;
	}
}

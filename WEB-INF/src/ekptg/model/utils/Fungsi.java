package ekptg.model.utils;

import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

	
public class Fungsi {
	
	public static int lebar = 400 ;
	public static int tinggi = 300 ;
	
	public String getParam(HttpServletRequest request, String param) throws Exception {
		return request.getParameter(param) == null ? "" : request.getParameter(param);
	}
	public int getParamInt(HttpServletRequest request, String param) throws Exception {
		return request.getParameter(param) == null ? 0 : Integer.parseInt(request.getParameter(param));
	}	
	
	//width=800,height=500
	public static void setWin800600() {
		lebar = 800;
		tinggi = 600;
		 
	}
	public static void setWin400300() {
		lebar = 400;
		tinggi = 300;
		 
	}
	
	/**
	 * Fungsi mendapatkan nama folder ( fizikal laporan) 
	 * dan nama context pada file.properties
	 * Dibuat Oleh	: Mohamad Rosli
	 * Dibuat Pada	: 11/11/2020
	 * Dikemaskini Oleh	: 
	 * Dikemaskini Pada :
	 * @return
	 */
	public static String getReportPath(ServletContext context){
		String rtype ="/reports";
		ResourceBundle rb = ResourceBundle.getBundle("file");
		rtype = rb.getString("laporanpath") == null?rtype:rb.getString("laporanpath");

		String realPathReport = context.getRealPath("").substring(0,context.getRealPath("").length() - (getAppContext().length())) + rtype;
		return realPathReport;
	
	}	
	/**
	 * Fungsi mendapatkan nama context (folder fizikal applikasi)
	 * Dibuat Oleh	: Mohamad Rosli
	 * Dibuat Pada	: 11/11/2020
	 * Dikemaskini Oleh	: 
	 * Dikemaskini Pada :
	 * @return
	 */
	private static String getAppContext(){
		String appContext ="myetapp";
		ResourceBundle rb = ResourceBundle.getBundle("file");
		appContext = rb.getString("context_name");
//		myLogger.info("getAppContext="+appContext);
		return appContext;
		
	}
	
	
}
// 20200822
package ekptg.model.utils;

import javax.servlet.http.HttpServletRequest;

	
public class Fungsi {
	
	public static int lebar = 0 ;
	public static int tinggi = 0 ;
	
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
	
}

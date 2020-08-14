package ekptg.model.utils;

import javax.servlet.http.HttpServletRequest;

public class Fungsi {
	
	public String getParam(HttpServletRequest request, String param) throws Exception {
		return request.getParameter(param) == null ? "" : request.getParameter(param);
	}
	public int getParamInt(HttpServletRequest request, String param) throws Exception {
		return request.getParameter(param) == null ? 0 : Integer.parseInt(request.getParameter(param));
	}	
	
}

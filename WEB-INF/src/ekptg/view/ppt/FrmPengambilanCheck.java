/**
 * 
 */
package ekptg.view.ppt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPermohonanUPTData;

public class FrmPengambilanCheck implements IServlet2 {
	static Logger myLogger = Logger.getLogger(FrmPengambilanCheck.class);
	@SuppressWarnings("unchecked")
	public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		String submit = request.getParameter("command");

		if ("checkExistLot".equals(submit)) {
			myLogger.info("CHECK :::: MASUK LA LA LA");
			FrmPermohonanUPTData dataHM = FrmPermohonanUPTData.getInstance();
    		Vector checkExistLot = new Vector();
    		checkExistLot.clear();
        	
    		try {
    			myLogger.info("CHECK :::: MASUK");
    			//get maklumat lot by nolot dan idpermohonan
    			dataHM.setCheckExistLot(request.getParameter("id_permohonan"),request.getParameter("txtNoLot"),request.getParameter("id_hakmilik"));
    			checkExistLot = dataHM.getCheckExistLot();
    			if(checkExistLot.size()!=0){
    				myLogger.info("CHECK :::: 1");
    				//alert yang dipaparkan pada jsp
    				out.println("No. Lot telah wujud di dalam permohonan ini!" +
    						"<input type='hidden' id='check_lot' name='check_lot' value='Y' > ");
        		}else {
        			myLogger.info("CHECK :::: 2");
					out.println("<input type='hidden' id='check_lot' name='check_lot' value='N' >");
        		}
			 
    		}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}//close checkExistLot 
	}
}


/**
 * 
 */
package ekptg.view.php2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmPNWKeputusanData;

/**
 * 
 *
 */
public class FrmPNWServlet implements IServlet2 {

	 public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException, ServletException {
		
		FrmPNWKeputusanData logic = new FrmPNWKeputusanData();
		 
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String submit = request.getParameter("command");
		
		if ("calculateTotal".equals(submit)) {
			
			String ids[] = request.getParameterValues("ids");
			String txtLuasTawar = request.getParameter("txtLuasTawar");
			String txtLuas[] = request.getParameterValues("txtLuas");
			Double luasAsal = 0D;
			if (!"".equals(txtLuasTawar)){
				luasAsal = Double.valueOf(txtLuasTawar);
			}
			Double total = 0D;
			
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					if (!"".equals(txtLuas[i])){
						total = total + Double.valueOf(txtLuas[i]);						
					}					
				}
			}
			total = Double.valueOf(Utils.formatLuas(total));
			out.println("<script>setTotalLuas('" + Utils.formatLuas(total) + "');</script>");
			
			if (total > luasAsal){
				out.println("Jumlah luas yang dimasukkan telah melebihi luas yang ditawarkan.");
			} else if (total < luasAsal){
				out.println("Jumlah luas yang dimasukkan kurang daripada luas yang ditawarkan.");
			}
		}				
	}
}

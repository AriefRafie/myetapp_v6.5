package ekptg.view.ppt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lebah.servlets.IServlet2;
import ekptg.model.ppt.FrmHakmilikSementaraPermohonanUPTData;

public class PPTCheck implements IServlet2{

	public void doService(HttpServletRequest request, HttpServletResponse response,
			ServletContext arg2) throws IOException, ServletException {
		
		 PrintWriter out = response.getWriter();
		 String no_hakmilik = request.getParameter("no_hakmilik");
		 String jenis_hakmilik = request.getParameter("jenisHakMilik");
		 String idHakmilik = request.getParameter("id_hakmilik");
		 String submit = request.getParameter("command");
	     FrmHakmilikSementaraPermohonanUPTData prmhnnUPT = FrmHakmilikSementaraPermohonanUPTData.getInstance();
	     
	     Vector jenisHakmilik = null;
	     
		 if ("check_no_hakmilik".equals(submit)) {
			 try {
				 if(no_hakmilik!= ""){
				 if(prmhnnUPT.check_no_hakmilik(no_hakmilik,jenis_hakmilik,idHakmilik) == true)
				 {
					 jenisHakmilik = prmhnnUPT.getJenisHakmilik(jenis_hakmilik);
					 Hashtable h = (Hashtable)jenisHakmilik.get(0);
					 
					 out.println("<div>No. Hakmilik '"+h.get("kodJenisHakmilik")+" "+no_hakmilik+"' sudah wujud!</div>"+"<input type='hidden' id='validation_field' name='validation_field' value='invalid' >");
				 } else {
					 out.println("<input type='hidden' id='validation_field' name='validation_field' value='valid' >");
					 
					 
				 }}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
		
		
	}

}

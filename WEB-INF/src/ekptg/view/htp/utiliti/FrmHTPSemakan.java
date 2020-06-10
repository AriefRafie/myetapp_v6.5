package ekptg.view.htp.utiliti;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;
import ekptg.model.htp.rekod.ITanah;
import ekptg.model.htp.rekod.TanahBean;

public class FrmHTPSemakan implements IServlet2 {

	private ITanah iHakmilik = null;
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(FrmHTPSemakan.class);

	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
	  		throws IOException, ServletException {
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 String contextPath = request.getContextPath();
		 String submit = request.getParameter("command");
		 
		 if ("XXXXX".equals(submit)) {
			
		 }else if ("semakanhakmilikrizab".equals(submit)) {
			 String noHakmilik = request.getParameter("txtNoHakmilik");
			 String idJenisHakmilik = request.getParameter("socJenisHakmilik");
			 String idNegeri = request.getParameter("socNegeri");
			 String idDaerah = request.getParameter("socDaerah");
			 String idMukim = request.getParameter("socMukim");
			 try {
				 //out.println("semakanhakmilikrizab");
				 if(geTanah().isHakmilik(idNegeri, idDaerah, idMukim
					, idJenisHakmilik
					, noHakmilik)){
					 out.println("" +
						"  <script type='text/javascript'> " +
					 		" alert('Maklumat Hakmilik/Warta telah wujud!'); " +
//					 		" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.isexistxx.value = '1';"+
					 		"");
					 //if(!noHakmilik.equals("")){
					// out.println("" +" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.txtNoHakmilik.value = '';"+
				 	//	"");
					// }else{
						 out.println("" +" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.txtNoWarta.value = '';"+
					 		"");
					 //}
					 out.println("" +"  "+
					 	"</script> ");
					 
				 }else{
					 out.println("" +
						"  <script type='text/javascript'> " +
//				 		" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.isexistxx.value = '0';"+
//				 		" alert('Maklumat Hakmilik/Warta telah wujud!'); " +
				 		"  "+
				 	"</script> ");			
					 
				 }
			 } catch (Exception e) {
				 //e.printStackTrace();
				StringBuffer sb = new StringBuffer("");
				sb.append("<table width=\"100%\" border=\"0\">");
				sb.append("  <tr>");
				sb.append("    <td>");
				sb.append("    	&nbsp;<div class=\"warning\">RALAT:"+e.getMessage()+"</div>");
				sb.append("    </td>");
				sb.append("  </tr>");
				sb.append(" </table>");
				sb.append("</select>");

			 }
			 
		 }else if ("semakanrizab".equals(submit)) {
			 String noLot = request.getParameter("txtNoLot");
			 String idLot = request.getParameter("socLot");
			 String noWarta = request.getParameter("txtNoWarta");
			 String idNegeri = request.getParameter("socNegeri");
			 String idDaerah = request.getParameter("socDaerah");
			 String idMukim = request.getParameter("socMukim");

			 try {
				 //out.println("semakanhakmilikrizab");
				 if(geTanah().isWarta(idNegeri, idDaerah, idMukim
						 , noWarta
						 , idLot
						 , noLot)){
					 out.println("" +
						"  <script type='text/javascript'> " +
						" alert('Maklumat Hakmilik/Warta telah wujud!'); " +
//					 	" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.isexistxx.value = '1';"+
					 	"");
					 out.println("" +" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.txtNoLot.value = '';"+
					 	"");
					 //}
					 out.println("" +"  "+
					 	"</script> ");
					 
				 }else{
					 out.println("" +
						"  <script type='text/javascript'> " +
//				 		" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.isexistxx.value = '0';"+
//				 		" alert('Maklumat Hakmilik/Warta telah wujud!'); " +
				 		"  "+
				 	"</script> ");			
					 
				 }
			 } catch (Exception e) {
				 //e.printStackTrace();
				StringBuffer sb = new StringBuffer("");
				sb.append("<table width=\"100%\" border=\"0\">");
				sb.append("  <tr>");
				sb.append("    <td>");
				sb.append("    	&nbsp;<div class=\"warning\">RALAT:"+e.getMessage()+"</div>");
				sb.append("    </td>");
				sb.append("  </tr>");
				sb.append(" </table>");
				sb.append("</select>");

			 }
		 
		 
		 }

	  }

	private ITanah geTanah(){
		if (iHakmilik==null){
			iHakmilik=new TanahBean();
		}
		return iHakmilik;
	
	}
		

}

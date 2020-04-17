package ekptg.view.htp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class FrmHTPSemakan implements IServlet2 {

	private HakmilikInterface iHakmilik = null;
	//private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmHTPSemakan.class);

	public void doService(HttpServletRequest request,
			HttpServletResponse response, ServletContext context)
	  		throws IOException, ServletException {
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 //String contextPath = request.getContextPath();
		 String submit = request.getParameter("command");
		 
		 String idNegeri = request.getParameter("socNegeri");
		 String idDaerah = request.getParameter("socDaerah");
		 String idMukim = request.getParameter("socMukim");
		 String noLot = request.getParameter("txtNoLot");
		 
		 if ("XXXXX".equals(submit)) {
			
		 }else if ("semakanhakmilikrizab".equals(submit)) {
			 String noHakmilik = request.getParameter("txtNoHakmilik");
			 String idJenisHakmilik = request.getParameter("socJenisHakmilik");
			 String noBangunan = request.getParameter("txtNoBangunan");
			 String noTingkat = request.getParameter("txtNoTingkat");
			 String noPetak = request.getParameter("txtNoPetak");
			 
			 String idLot = request.getParameter("noLot");
			 
			 try {
				 //out.println("semakanhakmilikrizab");
				 String semakan = "";
				 if(getIHakmilik().isHakmilik(idNegeri, idDaerah, idMukim, idJenisHakmilik, noHakmilik
						 ,noBangunan,noTingkat,noPetak,idLot,noLot)){
					 out.println("" +
						"  <script type='text/javascript'> " +
					 		" alert('Maklumat Hakmilik/Warta telah wujud!'); " +
//					 		" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.isexistxx.value = '1';"+
					 		"");
					 //if(!noHakmilik.equals("")){
					// out.println("" +" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.txtNoHakmilik.value = '';"+
				 	//	"");
					// }else{
					//	 out.println("" +" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.txtNoWarta.value = '';"+
					 if(noBangunan.equals("")){
					 out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.txtNoHakmilik.value = '';" +
					 		"");
					 out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.txtNoLot.value = '';" +
				 		"");
					 }else if(!noBangunan.equals("")){
						 out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.txtNoBangunan.value = '';" +
					 		"");
						 out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.txtNoTingkat.value = '';" +
					 		"");
						 out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.txtNoPetak.value = '';" +
					 		"");

					 }
//					out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.semakantanah.value = '1';" +
//			 		"");
					 //}
					 out.println("" +"  "+
					 	"</script> ");
					 semakan = "ada"; 
				 }else{
					 out.println("" +
						"  <script type='text/javascript'> " +
//				 		" document.Fekptg_view_htp_FrmPendaftaranHakmilikRizabRekod.isexistxx.value = '0';"+
//				 		" alert('Maklumat Hakmilik/Warta telah wujud!'); " +
				 		"  "+
				 		"");
//					out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.semakantanah.value = '0';" +
//			 		"");
					out.println("</script> ");			
					semakan = "tiada";
					
				 }
				 session.setAttribute("semakTanah", semakan);
				 context.setAttribute("semakTanah1", semakan);
				 
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
			 String idLot = request.getParameter("socLot");
			 String noWarta = request.getParameter("txtNoWarta");

			 try {
				 //out.println("semakanhakmilikrizab");
				 if(getIHakmilik().isWarta(idNegeri, idDaerah, idMukim, noWarta, idLot, noLot)){
					 out.println("" +
						" <script> \n" +
						"	alert('Maklumat Hakmilik/Warta telah wujud!'); " +
					 	"");
					 out.println("" +" document.Fekptg_view_htp_rekod_FrmPendaftaranTanah.txtNoLot.value = '';" +
					 	"");
//					 }
					 out.println("" +"  "+
					 	" </script> ");
					 
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

		
	private HakmilikInterface getIHakmilik(){
		if (iHakmilik==null){
			iHakmilik=new HakmilikBean();
		}
		return iHakmilik;
	}
		

}

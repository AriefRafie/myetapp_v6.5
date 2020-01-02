package ekptg.view.ppt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.servlets.IServlet2;

import org.apache.log4j.Logger;

import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmSek8SiasatanData;

public class PenarikanBalikCheck implements IServlet2 {
	static Logger myLogger = Logger.getLogger(PenarikanBalikCheck.class);
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	FrmPembatalanInternalData logic = new FrmPembatalanInternalData();
	FrmSek8SiasatanData logic1 = new FrmSek8SiasatanData();
	
	
	
	 public void doService(HttpServletRequest request, 
			 HttpServletResponse response, ServletContext context)
	  throws IOException, ServletException
	  {
		 HttpSession session = request.getSession();
		 PrintWriter out = response.getWriter();
		 String contextPath = request.getContextPath();
		 String submit = request.getParameter("command");
		 String message = request.getParameter("alert_message");	 
		 String jumlah_dipilih = request.getParameter("jumlah_dipilih");
		 String jumlah_semua = request.getParameter("jumlah_semua");
		 String readmode = request.getParameter("readmode");
		 
		 String id_hakmilikpb = request.getParameter("id_hakmilikpb");
		 String id_permohonan = request.getParameter("id_permohonan");
		 String id_pembatalan = request.getParameter("id_pembatalan");
		 String id_bahagianpb = request.getParameter("id_bahagianpb");
		 
		 
		 
		
		 Vector maklumat_siasatan_pb = null;
		 Vector list_bahagian = null;
	
		
		 if("checking_validation".equals(submit))
		 {
			 try
			 {
				 if(message!="")
				 {
							 
			     if(readmode.equals("view"))	 
			     {
				 out.println("<input type='hidden' id='validation_field' name='validation_field' value='invalid' > "); 
			     }
			     else
			     {
			     out.println(message + " <input type='hidden' id='validation_field' name='validation_field' value='invalid' > "); 
				 }
					 
					 }
				 else
				 {
				 out.println("<input type='hidden' id='validation_field' name='validation_field' value='valid' >"); 
				 }
			 }
			 catch (Exception e) {
					
					e.printStackTrace();
				}
		 }
		
		 
		 else if("jumlah_dipilih".equals(submit))
		 {
			 try
			 {
			 out.println("<span  style='color:#0000FF'>"+jumlah_dipilih+"</span> / <span  style='color:#0000FF'>"+jumlah_semua+"</span>"); 
			 }
			 catch (Exception e) {
					
					e.printStackTrace();
				}
		 }
		 
		 else if ("getSyer".equals(submit))
			{
     				
			try {
			
				
	        list_bahagian = logic1.list_bahagian(id_bahagianpb);
			
			if(list_bahagian.size()>0)
			{
				
			Hashtable h = (Hashtable) list_bahagian.get(0);				
			if(!h.get("ID_BAHAGIANPB").toString().equals(""))
			{
			myLogger.info("BACAAN 1");
		    out.println("<script>  " 
		    +" hantarSyer('"+h.get("SYER_ATAS").toString()+"','"+h.get("SYER_BAWAH").toString()+"','"+h.get("KETERANGAN_SYER").toString()+"') "
		    +"</script> ");	
			}
			else
			{	
				 myLogger.info("BACAAN 2");
				  out.println("<script>  " 
						    +" hantarSyer('','','') "
						    +"</script> ");	
			}
			
			}
			else
			{	
				 myLogger.info("BACAAN 2");
				  out.println("<script>  " 
						    +" hantarSyer('','','') "
						    +"</script> ");	
			}
			
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			}
     		 
		 else if("check_pampasan".equals(submit))
		 {
			 try
			 {
			maklumat_siasatan_pb = logic.maklumat_siasatan_pb(id_hakmilikpb,id_pembatalan);
			myLogger.info("SIZE PB :"+maklumat_siasatan_pb.size());
			
			if(maklumat_siasatan_pb.size()>0)
			{
		    out.println(" <script> " +
		    	
		    		" tuntutanX("+id_hakmilikpb+","+id_permohonan+","+id_pembatalan+"); "+
		   
				" </script>");						
			}else
			{
		    out.println("<script> alert('Sila lengkapkan maklumat siasatan');  </script> ");		
			}
				
				 }
			 catch (Exception e) {
					
					e.printStackTrace();
				}
		 }
		
	
			
		 }
		
}

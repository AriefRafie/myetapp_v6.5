package ekptg.view.htp.pajakan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmPajakanKecilPendaftaranData;
import ekptg.model.htp.FrmPajakanKecilSenaraiPermohonanData;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.HtpPeringatanBean;
import ekptg.model.htp.IHtpPeringatan;

public class FrmPajakanUtamaSenaraiPeringatan extends AjaxBasedModule{
	/**
	 * 
	 */
	private final String PATH="app/htp/pajakan/";
	private IHtpPeringatan iHTPP = null;  
	private static final long serialVersionUID = 3879148285213314850L;
	Long idHakmilikUrusan = 0L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanUtamaSenaraiPeringatan.class);
	SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
	SimpleDateFormat sdfBulan = new SimpleDateFormat("MM");

	@SuppressWarnings("unchecked")
	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = PATH+"peringatan/index.jsp";
      	String disability = "disabled";
	    String socAgensi = "";
	    String socKementerian = "";
	    String socNegeri = "";
	    String socUrusan = "";
		Vector semakanSenarai = new Vector();

	    this.context.put("util", new lebah.util.Util());

	    Vector senaraiFail = null;
	    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
	    
        String id_kementerian = getParam("sockementerian");
	    String submit = getParam("command");
	    String idFail = getParam("fail");
	    String pageMode = getParam("pagemode");
	    String langkah = getParam("langkah");

	    if (!("".equals(submit))) {
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");	
	    	
// PAGE 1
	    	if("pksenaraifailcari".equals(submit)){ //carian
	    	    myLog.info("equals("+submit+")::pksenaraifailcari");
	    		String nofail = getParam("nofail");
	    		template_name = "app/htp/frmPajakanKecilSenaraiFail.jsp";
	    		senaraiFail = FrmSenaraiFailPajakanKecilData.getList(nofail);
	    		this.context.put("senaraiList", senaraiFail);  

	    	}else if("pksenaraipermohonan".equals(submit)){
	    	    System.out.println("FrmPajakanKecilA: equals(submit)::pksenaraipermohonan");
			    //String fail = getParam("fail");
		    	Vector senaraiPermohonan = null;
		    	senaraiPermohonan = FrmPajakanKecilSenaraiPermohonanData.getList(idFail);
		    	this.context.put("senaraiList", senaraiPermohonan);	    	
		    	this.context.put("idFail", idFail);	    	
		    	template_name = "app/htp/frmPajakanKecilSenaraiPermohonan.jsp";	
		    }
	    }else{
	    	myLog.info("Tahun:"+sdfTahun.format(new Date()));
	    	myLog.info("Bulan:"+sdfBulan.format(new Date()));
	    	//senaraiFail = FrmUtilData.getSenaraiPeringatan("",(String)session.getAttribute("_ekptg_user_id"));
	    	int tahunHadapan = Integer.parseInt(sdfTahun.format(new Date()))+ 1;
	    	if (sdfBulan.format(new Date()).equals("12")){
		    	//myLog.info("Bulan:"+sdfBulan.format(new Date()));
	    		senaraiFail = getIHTPP().getSenaraiPeringatanBayaran("", "3",String.valueOf(tahunHadapan));
	    	
	    	}else{
	    		senaraiFail = getIHTPP().getSenaraiPeringatanBayaran("", "3",sdfTahun.format(new Date()));
	    		
	    	}
	    	myLog.info("Tarikh:"+new Date());

	    }
	    this.context.put("senaraiPeringatan", senaraiFail);  

	    return template_name;
	  }


	private IHtpPeringatan getIHTPP(){
		if(iHTPP== null)
			iHTPP = new HtpPeringatanBean();
		return iHTPP;
	}	
	
}

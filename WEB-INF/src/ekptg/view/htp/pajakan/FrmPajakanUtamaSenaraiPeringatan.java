package ekptg.view.htp.pajakan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.HTPeringatanBean;
import ekptg.model.htp.IHTPeringatan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.utils.emel.EmailConfig;

public class FrmPajakanUtamaSenaraiPeringatan extends AjaxBasedModule{
	/**
	 * 
	 */
	private final String PATH="app/htp/pajakan/";
	private IHTPeringatan iHTPP = null;  
	private static final long serialVersionUID = 3879148285213314850L;
	Long idHakmilikUrusan = 0L;
	static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanUtamaSenaraiPeringatan.class);
	SimpleDateFormat sdfTahun = new SimpleDateFormat("yyyy");
	SimpleDateFormat sdfBulan = new SimpleDateFormat("MM");

	public String doTemplate2()throws Exception {
		 
	    String template_name = PATH+"peringatan/index.jsp";
	    this.context.put("util", new lebah.util.Util());

	    Vector<HtpPermohonan> senaraiFail = null;
//	    socNegeri = FrmPajakanKecilPendaftaranData.SelectNegeri("socNegeri");
	    
//      String id_kementerian = getParam("sockementerian");
	    String submit = getParam("command");
	    String idFail = getParam("fail");
//	    String pageMode = getParam("pagemode");
//	    String langkah = getParam("langkah");
    	EmailConfig ec = new EmailConfig();
    	myLog.info("submit="+submit);

    	int tahunHadapan = Integer.parseInt(sdfTahun.format(new Date()))+ 1;

    	if (!("".equals(submit))) {
	    	this.context.put("idsuburusan","44");
	    	this.context.put("idurusan","309");	
	    	
	    	if("emelperingatan".equals(submit)){
		    	ec.sendTo("roslizakaria@gmail.com", "Peringatan Bayaran", "");
		    	//htpEmailService.setEmail(emUrusan,emEmailto,emMaincontent,emNofail,emTitle,emTarikhMohon,emAgensi);
		    
		    }else if("emelewat".equals(submit)) {
		    	ec.sendTo("roslizakaria@gmail.com", "Bayaran Lewat", "");
		    	//ec.sendTo("roslizakaria@gmail.com", "Bayaran Lewat", "");
		   	    	//htpEmailService.setEmail(emUrusan,emEmailto,emMaincontent,emNofail,emTitle,emTarikhMohon,emAgensi);
  
			}
	    	
	    	if (sdfBulan.format(new Date()).equals("12")){
		    	//myLog.info("Bulan:"+sdfBulan.format(new Date()));
	    		senaraiFail = getIHTPP().getSenaraiPeringatanBayaran("", "3",String.valueOf(tahunHadapan));
	    	
	    	}else{
	    		senaraiFail = getIHTPP().getSenaraiPeringatanBayaran("", "3",sdfTahun.format(new Date()));
	    		
	    	}

	    }else{
//	    	myLog.info("Tahun:"+sdfTahun.format(new Date())+"Bulan:"+sdfBulan.format(new Date()));
	    	if (sdfBulan.format(new Date()).equals("12")){
		    	//myLog.info("Bulan:"+sdfBulan.format(new Date()));
	    		senaraiFail = getIHTPP().getSenaraiPeringatanBayaran("", "3",String.valueOf(tahunHadapan));
	    	
	    	}else{
	    		senaraiFail = getIHTPP().getSenaraiPeringatanBayaran("", "3",sdfTahun.format(new Date()));
	    		
	    	}
//	    	myLog.info("Tarikh:"+new Date());

	    }
	    this.context.put("senaraiPeringatan", senaraiFail);  

	    return template_name;
	    
	}

	private IHTPeringatan getIHTPP(){
		if(iHTPP== null)
			iHTPP = new HTPeringatanBean();
		return iHTPP;
	}	
	
	
}

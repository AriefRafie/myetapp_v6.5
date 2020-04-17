package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.report.EkptgReportServlet;

public class SuratHTP extends EkptgReportServlet {
	
	static Logger myLog = Logger.getLogger(ekptg.report.htp.SuratHTP.class);
	FrmUtilData frmUtil = null; 
	private IHtp iHTP = null;  
	
	public void LaporanHtp() {
		//super.setReportName("LaporanHTP");
		super.setFolderName("htp");
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
			throws Exception {
		frmUtil = new FrmUtilData();

		String bildok =(String)parameters.get("bil");
		String idPermohonan =(String)parameters.get("idpermohonan");
		String template =(String)parameters.get("template");
		String pegawai =(String)parameters.get("pegawai");
		String jawatan =(String)parameters.get("jawatan");
		String email =(String)parameters.get("email");
		String noTelefon =(String)parameters.get("notelefon");
		String up =(String)parameters.get("up");
		String beban =(String)parameters.get("beban");
		String tahun =(String)parameters.get("tahun");
		//mylog.info("beban:"+beban"email:"+email"noTelefon"+noTelefon);
		String idFail =(String)parameters.get("idfail");
		String langkah =(String)parameters.get("langkah");
		String idUser =(String)parameters.get("iduser");
		String idSuburusan =(String)parameters.get("idsuburusan");
		if(idPermohonan!="" && idFail!=""){
			frmUtil.simpanKemaskiniStatusPermohonan(idSuburusan,langkah,idPermohonan,idFail,idUser)	;	
		}
		super.setFolderName("htp");
		super.setReportName(template);	
	
		parameters.put("BILDOKUMEN",bildok);
		parameters.put("ID_PERMOHONAN",idPermohonan);
		
		String perkataanTajuk = "BAGI";
		//myLog.info(perkataanTajuk);
		HtpPermohonan htpPermohonan = getIHTP().findPermohonan(idPermohonan,"");
		//myLog.info(htpPermohonan);
		if(htpPermohonan.getPermohonan().getPfdFail().getTajukFail().toUpperCase().contains(perkataanTajuk)){
			String tajuk = "";
//			myLog.info("tajuk="+tajuk);
//			myLog.info("tajuk 1="+htpPermohonan.getPermohonan().getPfdFail().getTajukFail());
//			myLog.info("tajuk 2="+htpPermohonan.getPermohonan().getPfdFail().getTajukFail().toUpperCase().indexOf(perkataanTajuk));
//			myLog.info("tajuk 3="+htpPermohonan.getPermohonan().getPfdFail().getTajukFail().length()+1);
			tajuk = htpPermohonan.getPermohonan().getPfdFail().getTajukFail().substring(
					htpPermohonan.getPermohonan().getPfdFail().getTajukFail().toUpperCase().indexOf(perkataanTajuk)
					,htpPermohonan.getPermohonan().getPfdFail().getTajukFail().length());
			//myLog.info("tajuk 4="+tajuk);
			//parameters.put("TAJUK_INTERNAL",tajuk);
		
		}

		parameters.put("NAMAPEGAWAI",pegawai);
		parameters.put("JAWATAN",jawatan);
		parameters.put("EMAIL",email);
		parameters.put("NO_TELEFON",noTelefon);
		parameters.put("UP",up);
		parameters.put("BEBANAN",beban);
		parameters.put("RUJ_SURAT",up);
		parameters.put("RUJ_TARIKH",beban);
		//30/04/2010
		parameters.put("TAHUN",tahun);
	
		
	}
		
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();

		return iHTP;
	
	}

		
}
	
	
package ekptg.report.htp;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ekptg.report.EkptgReportServlet;

public class SuratPajakanKepadaKJP extends EkptgReportServlet{
	static Logger myLog = Logger.getLogger(SuratPajakanKepadaKJP.class);
	public SuratPajakanKepadaKJP() {
		//myLog.info("print SuratKepadaKJPPajakan...");
		//super.setReportName("SURAT ULASAN KEPADA KJP");
		super.setReportName("HTPajakanSuratUlasanKJP");
		super.setFolderName("htp");
		
	}

	public void doProcessing(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
			throws Exception {
		String idPermohonan = String.valueOf(parameters.get("idpermohonan"));
		//String template =(String)parameters.get("template");
		String pegawai =(String)parameters.get("namaPegawai");
		//String jawatan =(String)parameters.get("jawatan");
		String email =(String)parameters.get("emelPegawai");
		//String noTelefon =(String)parameters.get("notelefon");

		//parameters.put("BILDOKUMEN",bildok);
		parameters.put("idPermohonan",idPermohonan);
		parameters.put("namaPegawai",pegawai);
		//parameters.put("JAWATAN",jawatan);
		parameters.put("emelPegawai",email);
	}
	
}
